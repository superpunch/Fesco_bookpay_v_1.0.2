package com.fesco.bookpay.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fesco.bookpay.activity.ptui.ImageZoomActivity;
import com.fesco.bookpay.adapter.GridViewConsumpImageAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.approvalbean.BillDetailBean;
import com.fesco.bookpay.util.ConversionUtil;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.CropSquareTrans;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.util.ptutils.Constants;
import com.fesco.bookpay.util.ptutils.FileUtils;
import com.fesco.bookpay.util.ptutils.JBitmapUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gong.min on 2017/1/11.
 */
public class ConsumptionPreviewActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.consume_pre_image)
    TextView tvImage;
    @BindView(R.id.consume_pre_type)
    TextView tvType;
    @BindView(R.id.consume_pre_money)
    TextView tvMoney;
    @BindView(R.id.consume_pre_startday)
    TextView tvstartDay;
    @BindView(R.id.consume_pre_endday)
    TextView tvendDay;
    @BindView(R.id.consume_pre_city)
    TextView tvCity;
    @BindView(R.id.consume_pre_count)
    TextView tvCount;
    @BindView(R.id.consume_pre_desc)
    TextView tvDesc;
    @BindView(R.id.consume_pre_grid)
    GridView gridView;
    @BindView(R.id.linear_date_end)
    LinearLayout linear_date_end;
    @BindView(R.id.linear_need_city)
    LinearLayout linear_need_city;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_text)
    TextView textView;

    private List<byte[]> dataList = new ArrayList<>();
    private ArrayList<String> mSelectPath = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_preview);
        ButterKnife.bind(this);
        textView.setText("消费记录详情");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsumptionPreviewActivity.this.finish();
            }
        });
        gridView.setOnItemClickListener(this);

        BillDetailBean.ApplyBean.DetailsBean detailsBeanList = (BillDetailBean.ApplyBean.DetailsBean) getIntent().getExtras().getSerializable(DetailBillActivity.DETAIL_BILL);
        if(detailsBeanList !=null) {
            detailsConsumption(detailsBeanList);
        }
    }


    private void detailsConsumption(BillDetailBean.ApplyBean.DetailsBean detailsBeanList) {

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        tvImage.setTypeface(font);

        if (!TextUtils.isEmpty(detailsBeanList.getAndroid_Icon())) {
            String icon = Html.fromHtml(detailsBeanList.getAndroid_Icon()).toString();
            tvImage.setText(icon);
        }

        tvType.setText(detailsBeanList.getSpend_Type_Str());
        tvMoney.setText(detailsBeanList.getMoney_Amount());
        if (!TextUtils.isEmpty(detailsBeanList.getSpend_City())) {
            tvCity.setText(detailsBeanList.getSpend_City());
        } else {
            linear_need_city.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(detailsBeanList.getSpend_End())) {
            linear_date_end.setVisibility(View.GONE);
        } else {
            String spend_End = ConversionUtil.getLongDateTime(detailsBeanList.getSpend_End());
            tvendDay.setText(spend_End);
        }
        String spend_Begin = ConversionUtil.getLongDateTime(detailsBeanList.getSpend_Begin());
        tvstartDay.setText(spend_Begin);
        tvCount.setText(Integer.toString(detailsBeanList.getBill_Num()));
        tvDesc.setText(detailsBeanList.getDetail_Memo());


        Log.d("Fragment", "ConsumptionPreviewActivity :" + detailsBeanList);
        for (BillDetailBean.ApplyBean.DetailsBean.PicsBean picsBeen : detailsBeanList.getPics()) {
            if (!TextUtils.isEmpty(picsBeen.getId())) {

                loadImageData(HttpUtil.getPicStream, picsBeen.getId());
            }
        }
//        GridViewConsumpImageAdapter mAdapter = new GridViewConsumpImageAdapter(this,dataList);
//        gridView.setAdapter(mAdapter);

    }




    public void loadImageData(String loadEmpInfo, String id) {

        String[] key = new String[]{"pic_Id"};
        String[] value = new String[]{id};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexImageByURL(loadEmpInfo, map, new OKManager.Func2() {
                    @Override
                    public void onResponse(byte[] data) {
                        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {

                            Log.d("TAG", "Main Thread" + data.length);
                        } else {
                            Log.d("TAG", "Not Main Thread");
                        }

                        if (data.length > 0) {


                            dataList.add(data);
                            GridViewConsumpImageAdapter mAdapter = new GridViewConsumpImageAdapter(ConsumptionPreviewActivity.this, dataList);
                            gridView.setAdapter(mAdapter);
                            Log.d("TAG", "Main Thread--- " + data.length);
                            //        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);  //为何不能写在主线程里？？？？
                            Bitmap bitmap = new CropSquareTrans().transform(BitmapFactory.decodeByteArray(data, 0, data.length));

                            // 创建临时文件
                            File mTmpFile = FileUtils.createTmpFile(context);
                            String str = JBitmapUtils.saveBitmap2File(bitmap, mTmpFile.getAbsolutePath());
                            mSelectPath.add(str);
                            Log.d("TAG", "Main Thread mTmpFile2 " + mSelectPath);


                            //  aCache.put(InforPersonActivity.IMAGE_HEAD,bitmap);
                        }


                    }
                }
        );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("TAG", "Main Thread mTmpFile2 " + mSelectPath);
        Intent intent = new Intent(ConsumptionPreviewActivity.this, ImageZoomActivity.class);
        intent.putStringArrayListExtra(Constants.EXTRA_RESULT, mSelectPath);
        intent.putExtra(Constants.EXTRA_CURRENT_IMG_POSITION, position);
        intent.putExtra(Constants.EXTRA_PREVIEW_IMG, true);
        startActivity(intent);
        overridePendingTransition(R.anim.selecter_image_alpha_enter, R.anim.selecter_image_alpha_exit);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSelectPath.size()>0){
            for (String path : mSelectPath) {
                File deleteFile = new File(path);
                deleteFile.delete();
                Log.e("options", "删除成功！" + path);
            }
        }


    }
}
