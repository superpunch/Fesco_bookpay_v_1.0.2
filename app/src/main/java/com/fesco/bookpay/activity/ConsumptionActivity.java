package com.fesco.bookpay.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.fesco.bookpay.FApplication;
import com.fesco.bookpay.activity.ptui.ImageZoomActivity;
import com.fesco.bookpay.activity.ptui.KSelectImagesActivity;
import com.fesco.bookpay.adapter.photoadapter.ImagePublishAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.ConsumptionBean;
import com.fesco.bookpay.entity.MessageBean;
import com.fesco.bookpay.entity.rbmbean.SpendTypesBean;
import com.fesco.bookpay.entity.rbmbean.UpImagePicsBean;
import com.fesco.bookpay.event.ConsumptionEvent;
import com.fesco.bookpay.event.RemoveImageEvent;
import com.fesco.bookpay.impl.DeleteImageClickListener;
import com.fesco.bookpay.impl.PermissionListenter;
import com.fesco.bookpay.impl.RetrofitService;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.ConversionUtil;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.CropSquareTrans;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.util.okhttp.RetrofitClient;
import com.fesco.bookpay.util.ptutils.Constants;
import com.fesco.bookpay.util.ptutils.FileUtils;
import com.fesco.bookpay.util.ptutils.JBitmapUtils;
import com.fesco.bookpay.util.ptutils.KUtils;
import com.fesco.bookpay.util.ptutils.ShowUtils;
import com.fesco.bookpay.util.ptutils.SnackBarUtils;
import com.fesco.bookpay.weight.dialog.PermissionDialogInfo;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.bookpay.greendao.Consume;
import me.bookpay.greendao.ConsumeDao;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gong.min on 2016/11/7.
 */
public class ConsumptionActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, DialogInterface.OnClickListener {
    private static final int CAPTURE_CODE = 1;
    private static final int IMAGE_CODE = 2;
    private static final int CITY_CODE = 3;

    private TextView tvIcon;
    private TextView tvType;
    private TextView tvCount;
    private EditText tvMoney;
    private TextView tvstartDay;
    private TextView tvendDay;
    private TextView tvCity;
    private EditText tvDesc;
    private ImageView attImage;

    private Button btnSave;
    private Button btnAgain;
    private boolean notConsumption = false;  //   未制报销单页面Item 跳转 true;
    private boolean previewConsumption = false;  // 新建报销单页面的Item 跳转 true;

    private LinearLayout linear_date_start;
    private LinearLayout linear_date_end;
    private LinearLayout linear_need_city;
    private String detail_Id;  // 上次已保存过的 detail_Id   保存过才有值
    private String spend_Type = "";//ID 类型
    private String picIds = "";//服务器返回多张图片ID：[ 72,73]  需上传图片转化成ID: 72,73
    private int date_Type;//  日期  ：2：开始、结束 ，1 开始
    private int need_City;//  城市  1:需要 0：不需要
    private long millStart;
    private long millEnd;
    private String money;
    private String city;
    private String end;
    private String start;
    private View inflate;

    private int number;
    private Button choosePhoto;
    private Button takePhoto;
    private Button cancel;
    private Dialog dialog;
    private TimePickerView pvStartTime;
    private TimePickerView pvEndTime;


    private static final int MSG_PB = 0x01;

    private final static int REQUEST_IMAGE = 4;

    private ImagePublishAdapter mAdapter;

    private ProgressDialog progressDialog;

    private ArrayList<String> mSelectPath = new ArrayList<>(); //原始图片路径
    private ArrayList<String> mYSelectPath = new ArrayList<>(); //压缩后的图片路径
    private List<String> listPicId = new ArrayList<>();    //上传成功后的图片返回的 id 如: 700,701
    private boolean isLoadImage; //是否下载过图片
    private MessageHandler mMessageHandler;

    LinearLayout mRootView;


    GridView mGridView;
    Consume consume;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_consume);
        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("新建消费记录");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recoveryActivitys();
            }
        });


    }

    public void operaTionPermission(final int position) {
        String[] permissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        requestBasePermissions(permissions, new PermissionListenter() {
            @Override
            public void onGranted() {
                Log.i("Fragment", "onGranted 赵信");

                if (position == getDataSize()) {

                    //    KUtils.putAwaySoftKeyboard(ReleaseImageActivity.this, mInputContent);
                    selectAblum();
                } else {
                    Intent intent = new Intent(ConsumptionActivity.this, ImageZoomActivity.class);
                    intent.putStringArrayListExtra(Constants.EXTRA_RESULT, mSelectPath);
                    intent.putExtra(Constants.EXTRA_CURRENT_IMG_POSITION, position);
                    startActivity(intent);
                    overridePendingTransition(R.anim.selecter_image_alpha_enter, R.anim.selecter_image_alpha_exit);
                }
            }

            @Override
            public void onDenied(List<String> denied) {
                PermissionDialogInfo permissionDialogInfo = new PermissionDialogInfo(context);
                permissionDialogInfo.setMessage("读写手机储存权限");
                permissionDialogInfo.show();

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            //    Bundle bundle = intent.getExtras();
            mSelectPath = intent.getStringArrayListExtra(Constants.EXTRA_RESULT);
            mAdapter = new ImagePublishAdapter(this, mSelectPath);
            mGridView.setAdapter(mAdapter);
            mAdapter.setDeleteImageClickListener(new DeleteImageClickListener() {
                @Override
                public void onDeleteItemClick(View view, int position) {
                    deleteImage(HttpUtil.deletePic, position);
                }
            });

            Log.i("Fragment", "onNewIntent mSelectPath: " + mSelectPath);

            if (mSelectPath.size() != 0) {
                yaBitmap();
                //    uploadImageFile(mSelectPath);
            }

            Log.i("Fragment", "onNewIntent");

        }
        Log.d("Fragment", "ConsumptionEvent: onNewIntent  onNewIntent :" + mSelectPath);
    }


    private void initView() {
        TextView tvMax = (TextView) findViewById(R.id.consume_max);
        TextView tvMin = (TextView) findViewById(R.id.consume_min);
        mRootView = (LinearLayout) findViewById(R.id.mRootView);
        tvIcon = (TextView) findViewById(R.id.consume_icon);
        tvType = (TextView) findViewById(R.id.consume_type);
        tvCount = (TextView) findViewById(R.id.consume_count);

        tvMoney = (EditText) findViewById(R.id.consume_money);
        tvstartDay = (TextView) findViewById(R.id.consume_startday);
        tvendDay = (TextView) findViewById(R.id.consume_endday);
        tvCity = (TextView) findViewById(R.id.consume_city);
        tvDesc = (EditText) findViewById(R.id.consume_desc);

        linear_date_start = (LinearLayout) findViewById(R.id.linear_date_start);
        linear_date_end = (LinearLayout) findViewById(R.id.linear_date_end);
        linear_need_city = (LinearLayout) findViewById(R.id.linear_need_city);


        btnSave = (Button) findViewById(R.id.btn_save);
        btnAgain = (Button) findViewById(R.id.btn_again);
        final TextView tvMoneyIcon = (TextView) findViewById(R.id.consume_money_icon);
        tvMin.setOnClickListener(this);
        tvMax.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnAgain.setOnClickListener(this);

        tvstartDay.setOnClickListener(this);
        tvendDay.setOnClickListener(this);
        tvCity.setOnClickListener(this);
        fontType();

        tvMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    tvMoneyIcon.setTextColor(Color.parseColor("#00b6d8"));
                }
            }
        });

        tvMoney.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                String text = s.toString();
                if (text.contains(".")) {
                    int index = text.indexOf(".");
                    if (index + 3 < text.length()) {
                        text = text.substring(0, index + 3);
                        tvMoney.setText(text);
                        tvMoney.setSelection(text.length());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        if (getIntent().getExtras() != null) {
            //区分是 未制消费单页面 ConsumptionNotActivity点击Item 直接跳转过来
            SpendTypesBean.ListBean spendTypes = (SpendTypesBean.ListBean) getIntent().getExtras().getSerializable(ConsumptionNotActivity.SPEND_TYPES);
            consume = (Consume) getIntent().getExtras().getSerializable(ReimburBillActivity.OPNE_SPEND_TYPE);
            Log.e("Fragment", "ConsumptionActivity xxxx spendTypes " + spendTypes);
            Log.e("Fragment", "ConsumptionActivity xxxx consume "  + consume);

            if (spendTypes != null) {
                initNotConsumptionSpendTypes(spendTypes);
                btnAgain.setText(R.string.delete);
                notConsumption = true;
            } else if (consume != null) {
                previewConsumption(consume);
                btnAgain.setText(R.string.delete);
                previewConsumption = true;
            } else {
                initConsumptionSpendTypes();
                detail_Id = "";
            }
        }

        optionStartTime();
        optionEndTime();
        initPhotoViews();
        //     EventBus.getDefault().register(this);
        Log.d("Fragment", "ConsumptionEvent: onCreate  evntMsg :" + evntMsg);
    }


    // ReimburBillActivity 中的Item跳转过来 可删除此页面数据
    public void previewConsumption(Consume consume) {
        detail_Id = (consume.getDetail_Id());
        spend_Type = consume.getTypeID();

        if (!TextUtils.isEmpty(consume.getTypeIcon())) {
            String icon = Html.fromHtml(consume.getTypeIcon()).toString();
            tvIcon.setText(icon);
        }
        tvType.setText(consume.getTypeName());
        tvMoney.setText(consume.getMoney());
        tvCount.setText(Integer.toString(consume.getCount()));
        tvstartDay.setText(consume.getStarDate());
        if (TextUtils.isEmpty(consume.getEndDate())) {
            linear_date_end.setVisibility(View.GONE);
        } else {
            tvendDay.setText(consume.getEndDate());
        }

        if (TextUtils.isEmpty(consume.getCity())) {
            linear_need_city.setVisibility(View.GONE);
        } else {
            tvCity.setText(consume.getCity());
        }

        if (!TextUtils.isEmpty(consume.getDescription())) {
            tvDesc.setText(consume.getDescription());
        }
        String pic_IdStr = consume.getPic_Ids();//1,2,3
        Log.d("Fragment", "pic_IdStr :" + pic_IdStr);
        if(!TextUtils.isEmpty(pic_IdStr)){
        String[] pic = pic_IdStr.split(",");
        for (int i = 0; i < pic.length; i++) {
            Log.d("Fragment", "pic :" + pic);
            if (!TextUtils.isEmpty(pic[i])) {
                loadImageData(HttpUtil.getPicStream, pic[i]);
            }
        }
        }

    }


    // 从ConsumptionNotActivity 中的Item跳转过来 可删除此页面数据
    public void initNotConsumptionSpendTypes(SpendTypesBean.ListBean spendTypes) {
        detail_Id = Integer.toString(spendTypes.getDetail_Id());
        spend_Type = Integer.toString(spendTypes.getSpend_Type());


        if (!TextUtils.isEmpty(spendTypes.getAndroid_Icon())) {
            String icon = Html.fromHtml(spendTypes.getAndroid_Icon()).toString();
            saveIcon = spendTypes.getAndroid_Icon();
            tvIcon.setText(icon);
        }
        tvType.setText(spendTypes.getSpend_Type_Str());
        tvMoney.setText(spendTypes.getMoney_Amount());
        tvCount.setText(Integer.toString(spendTypes.getBill_Num()));
        String spend_Begin = ConversionUtil.getLongDateTime(spendTypes.getSpend_Begin());
        tvstartDay.setText(spend_Begin);
        if (TextUtils.isEmpty(spendTypes.getSpend_End())) {
            linear_date_end.setVisibility(View.GONE);
        } else {
            String spend_End = ConversionUtil.getLongDateTime(spendTypes.getSpend_End());
            tvendDay.setText(spend_End);
        }

        if (TextUtils.isEmpty(spendTypes.getSpend_City())) {
            linear_need_city.setVisibility(View.GONE);
        } else {
            tvCity.setText(spendTypes.getSpend_City());
        }

        if (!TextUtils.isEmpty(spendTypes.getDetail_Memo())) {
            tvDesc.setText(spendTypes.getDetail_Memo());
        }


        for (SpendTypesBean.ListBean.PicsBean picsBeen : spendTypes.getPics()) {
            if (!TextUtils.isEmpty(picsBeen.getId())) {
                loadImageData(HttpUtil.getPicStream, picsBeen.getId());
            }
        }
    }

    //区分是ConsumptionTypeActivity 页面还是ConsumptionTypedetailActivity 跳转过来的
    public void initConsumptionSpendTypes() {

        ConsumptionBean.SpendTypesBean spendTypesBean = (ConsumptionBean.SpendTypesBean) getIntent().getExtras().getSerializable(ConsumptionTypeActivity.TYPENAME);
        ConsumptionBean.SpendTypesBean.SubTypesBean subTypesBean = (ConsumptionBean.SpendTypesBean.SubTypesBean) getIntent().getExtras().getSerializable(ConsumptionTypedetailActivity.SUBTYPES_BEAN);


        if (spendTypesBean != null) {
            tvType.setText(spendTypesBean.getType_Name());
            spend_Type = Integer.toString(spendTypesBean.getId());
            date_Type = spendTypesBean.getDate_Type();
            need_City = spendTypesBean.getNeed_City();
            if (!TextUtils.isEmpty(spendTypesBean.getAndroid_Icon())) {
                String icon = Html.fromHtml(spendTypesBean.getAndroid_Icon()).toString();
                saveIcon = spendTypesBean.getAndroid_Icon();
                tvIcon.setText(icon);
            }

        }

        if (subTypesBean != null) {

            tvType.setText(subTypesBean.getType_Name());
            spend_Type = Integer.toString(subTypesBean.getId());
            date_Type = getIntent().getExtras().getInt(ConsumptionTypedetailActivity.DATE_TYPE);
            need_City = getIntent().getExtras().getInt(ConsumptionTypedetailActivity.NEED_CITY);
            if (!TextUtils.isEmpty(subTypesBean.getAndroid_Icon())) {
                String icon = Html.fromHtml(subTypesBean.getAndroid_Icon()).toString();
                saveIcon = subTypesBean.getAndroid_Icon();
                tvIcon.setText(icon);
            } else {
                String TypeIcon = getIntent().getExtras().getString(ConsumptionTypedetailActivity.TYPE_ICON);
                saveIcon = TypeIcon;
                String icon = Html.fromHtml(TypeIcon).toString();
                tvIcon.setText(icon);

            }

        }


        if (date_Type == 1) {
            linear_date_end.setVisibility(View.GONE);
        }
        if (need_City == 0) {
            linear_need_city.setVisibility(View.GONE);
        }

    }

    String evntMsg;

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN) //这种写法达到粘性的目的
    public void onEvent(ConsumptionEvent event) {
// UI updates must run on MainThread
        //    textField.setText(event.message);

        evntMsg = event.getMsg();
        if (ConsumptionNotActivity.AGAIN_SPEND_TYPE.equals(evntMsg)) {


        } else if (ReimburBillActivity.NEW_SPEND_TYPE.equals(evntMsg)) {

        }

        Log.d("Fragment", "ConsumptionEvent: onEventssss  evntMsg :" + evntMsg);
    }

    //预览删除照片

    @Subscribe  //(sticky = true, threadMode = ThreadMode.MAIN) //这种写法达到粘性的目的
    public void onImageChangeListener(RemoveImageEvent event) {
        boolean isRevoke = event.getIsRevoke();
        int index = event.getIndex();
        if (isRevoke) {
//            String path = event.getPath();
//            if (!TextUtils.isEmpty(path)) {
//                mSelectPath.add(index, event.getPath());
//            }


        } else {
            mSelectPath.remove(index);
        }
        mAdapter.notifyDataSetChanged();
        //   event.setRevoke(true);
        Log.e("Fragment", "onImageChangeListener: " + mSelectPath);
        Log.e("Fragment", "onImageChangeListener_RemoveImageEvent index: " + index + "  isRevoke:" + isRevoke);
    }

    private ConsumeDao getConsumeDao() {
        // 通过 BaseApplication 类提供的 getDaoSession() 获取具体 Dao
        return ((FApplication) this.getApplicationContext()).getDaoSession().getConsumeDao();
    }

    private SQLiteDatabase getDb() {
        // 通过 BaseApplication 类提供的 getDb() 获取具体 db
        return ((FApplication) this.getApplicationContext()).getDb();
    }


    public void initPhotoViews() {
        mGridView = (GridView) findViewById(R.id.gv_images);
        //   ActivityManager.getInstance().addActivity(this);
        mMessageHandler = new MessageHandler(this);
        Bundle bundle = getIntent().getBundleExtra(Constants.KEY);
        if (bundle != null) {
            mSelectPath = bundle.getStringArrayList(Constants.EXTRA_RESULT);
        }

        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mAdapter = new ImagePublishAdapter(this, mSelectPath);
        mGridView.setOnItemClickListener(this);
        mGridView.setAdapter(mAdapter);

    }

    private void deleteImage(String url, final int position) {

        String pic_Id = listPicId.get(position);
        Log.e("Fragment", position + " 小鸟:" + pic_Id);

        String[] key = new String[]{"pic_Id"};
        String[] value = new String[]{pic_Id};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(url, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(url, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        Log.e("Fragment", "移除了id：" + position);
                        mSelectPath.remove(position);
                        listPicId.remove(position);

                        Log.e("Fragment", "移除了后的listPicId：" + listPicId);

                        mAdapter.notifyDataSetChanged();
                    }


                }

        );


    }

    List<String> loadselectPath = new ArrayList();

    public void loadImageData(String loadEmpInfo, final String id) {

        String[] key = new String[]{"pic_Id"};
        String[] value = new String[]{id};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexImageByURL(loadEmpInfo, map, new OKManager.Func2() {
                    @Override
                    public void onResponse(byte[] data) {

                        if (data.length > 0) {
                            listPicId.add(id);
                            Log.d("TAG", "Main Thread--- " + listPicId);
                            //        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);  //为何不能写在主线程里？？？？
                            Bitmap bitmap = new CropSquareTrans().transform(BitmapFactory.decodeByteArray(data, 0, data.length));
                            // 创建临时文件
                            File mTmpFile = FileUtils.createTmpFile(context);
                            String str = JBitmapUtils.saveBitmap2File(bitmap, mTmpFile.getAbsolutePath());

                            loadselectPath.add(str);
                            mSelectPath.add(str);

                            Log.d("TAG", "Main Thread mTmpFile2 " + mSelectPath);
                            mAdapter = new ImagePublishAdapter(ConsumptionActivity.this, mSelectPath);
                            mGridView.setAdapter(mAdapter);

                            mAdapter.setDeleteImageClickListener(new DeleteImageClickListener() {
                                @Override
                                public void onDeleteItemClick(View view, int position) {
                                    deleteImage(HttpUtil.deletePic, position);
                                }
                            });

                            //  aCache.put(InforPersonActivity.IMAGE_HEAD,bitmap);
                        }


                    }
                }
        );
    }


    private void selectAblum() {
        Intent intent = new Intent(ConsumptionActivity.this, KSelectImagesActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(Constants.EXTRA_SHOW_CAMERA, true);
        // 最大可选择图片数量
        intent.putExtra(Constants.EXTRA_SELECT_COUNT, 5);
        // 选择模式
        intent.putExtra(Constants.EXTRA_SELECT_MODE, KSelectImagesActivity.MODE_MULTI);
        // 默认选择
        intent.putExtra(Constants.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);

        startActivityForResult(intent, REQUEST_IMAGE);
        overridePendingTransition(R.anim.selecter_image_alpha_enter, R.anim.selecter_image_alpha_exit);
    }

    private int getDataSize() {
        return mSelectPath == null ? 0 : mSelectPath.size();
    }

    private void recoveryActivitys() {
        if (mSelectPath != null) {
            mSelectPath.clear();
        }

        finish();
        //  ActivityManager.getInstance().finishActivitys();
    }


    private void sendSelectedImages() {
        if (mSelectPath.size() != 0) {
            if (KUtils.isNetworkAvailable()) {
                //  KUtils.putAwaySoftKeyboard(ReleaseImageActivity.this, mInputContent);
                /**
                 * 遍历图片地址，如果发现有空值的则无法上传
                 */
                for (String fp : mSelectPath) {
                    Log.e("Fragment", "路径: " + fp);
                    if (TextUtils.isEmpty(fp)) {
                        SnackBarUtils.showSnackBar(mRootView, getString(R.string.image_error));


                        return;
                    }
                }
                progressDialog = ShowUtils.showProgressDialog(this, getString(R.string.loading));

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            mMessageHandler.sendEmptyMessage(MSG_PB);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                SnackBarUtils.showSnackBar(mRootView, getString(R.string.not_network));
            }
        } else {
            SnackBarUtils.showSnackBar(mRootView, getString(R.string.at_least_one_photo));
        }
    }


    static class MessageHandler extends Handler {

        WeakReference<Activity> mWeakReference;

        public MessageHandler(ConsumptionActivity consumptionActivity) {
            this.mWeakReference = new WeakReference<Activity>(consumptionActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            ConsumptionActivity consumptionActivity = (ConsumptionActivity) this.mWeakReference.get();
            switch (msg.what) {
                case MSG_PB:
                    SnackBarUtils.showSnackBar(consumptionActivity.mRootView, consumptionActivity.getString(R.string.upload_sucess));
                    consumptionActivity.progressDialog.dismiss();
                    break;
            }
            super.handleMessage(msg);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        operaTionPermission(position);

        if (isPermission) {
            Log.i("Fragment", "onGranted 赵信" + isPermission);
            if (position == getDataSize()) {
                //    KUtils.putAwaySoftKeyboard(ReleaseImageActivity.this, mInputContent);
                selectAblum();
            } else {
                Intent intent = new Intent(ConsumptionActivity.this, ImageZoomActivity.class);
                intent.putStringArrayListExtra(Constants.EXTRA_RESULT, mSelectPath);
                intent.putExtra(Constants.EXTRA_CURRENT_IMG_POSITION, position);
                startActivity(intent);
                overridePendingTransition(R.anim.selecter_image_alpha_enter, R.anim.selecter_image_alpha_exit);

            }

        } else {
            Log.i("Fragment", "onGranted 赵信 position ：" + position);
        }
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        recoveryActivitys();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_send:
                sendSelectedImages();
                break;
            case R.id.consume_city:
                Intent intent = new Intent(ConsumptionActivity.this, CityPickerActivity.class);
                startActivityForResult(intent, CITY_CODE);

                break;
            case R.id.consume_startday:
                pvStartTime.show();
                break;
            case R.id.consume_endday:
                pvEndTime.show();
                break;
            case R.id.consume_count:
                number = Integer.parseInt(tvCount.getText().toString());
                break;
            case R.id.consume_min:
                if (number == 0) {
                    break;
                } else {
                    number--;
                    tvCount.setText(Integer.toString(number));
                    break;
                }

            case R.id.consume_max:

                number++;

                tvCount.setText(Integer.toString(number));

                break;
            case R.id.takePhoto:

                break;
            case R.id.choosePhoto:

                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;

            case R.id.btn_again:

                //   true notConsumption  删除
                if (notConsumption) {

                    deleteRecord(HttpUtil.deleteRecord);
                } else if (previewConsumption) {
                    //    true previewConsumption  删除
                    getConsumeDao().deleteByKey(consume.getId());
                    finish();
                    // rbmBillListAdapter.setConsumeList(getConsumeDao().loadAll());

                } else
                    //再记一笔
                    inSpectionValue(false);
                // loadImage();


                break;
            case R.id.btn_save:
                inSpectionValue(true);

                break;
        }
    }


    private void inSpectionValue(boolean flag) {


        type = tvType.getText().toString();
        count = tvCount.getText().toString();
        desc = tvDesc.getText().toString();
        start = tvstartDay.getText().toString();
        end = tvendDay.getText().toString();
        city = tvCity.getText().toString();
        money = tvMoney.getText().toString();

        if (TextUtils.isEmpty(money) || money.equals("0")) {
            AppToast.showShortText(this, "请填写金额");
            return;
        }


        if (date_Type == 1 && TextUtils.isEmpty(start)) {
            AppToast.makeShortToast(this, "请选择开始日期");
            return;
        }
        if (date_Type == 2) {
            if (TextUtils.isEmpty(start) || TextUtils.isEmpty(end)) {
                AppToast.makeShortToast(this, "请选择日期");
                return;
            }
        }
        if (need_City == 1 && TextUtils.isEmpty(city)) {
            AppToast.makeShortToast(this, "请选择城市");
            return;
        }


        picIds = listPicId.toString().substring(1, listPicId.toString().length() - 1);
        picIds = picIds.replaceAll(" ", "");
        Log.d("Fragment", "提交前的 图片ID: " + picIds);


        if (flag) {
            if (!TextUtils.isEmpty(evntMsg)) {
                if (ConsumptionNotActivity.AGAIN_SPEND_TYPE.equals(evntMsg)) {
                    saveHttpData(HttpUtil.saveExpenseRecord, flag);
                } else if (ReimburBillActivity.NEW_SPEND_TYPE.equals(evntMsg)) {
                    saveConsumeDao(flag);
                }
            }
            //      finish();
        } else {
            if (!TextUtils.isEmpty(evntMsg)) {
                if (ConsumptionNotActivity.AGAIN_SPEND_TYPE.equals(evntMsg)) {
                    saveHttpData(HttpUtil.saveExpenseRecord, flag);
                } else if (ReimburBillActivity.NEW_SPEND_TYPE.equals(evntMsg)) {
                    saveConsumeDao(flag);
                }
            }
//            Intent intent = new Intent(this, ConsumptionTypeActivity.class);
//            startActivity(intent);
//            finish();

        }


    }


    private String filepath = Environment.getExternalStorageDirectory() + "/BookPay/share.png";
    private File loadImageFile;

    private void loadImage() {
        loadImageFile = new File(filepath);
        if (loadImageFile.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(filepath);
            attImage.setImageBitmap(bm);
        } else Log.v("Fragment", "error");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case CITY_CODE:
                if (resultCode == RESULT_OK) {
                    String cityName = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                    tvCity.setText(cityName);
                    Log.e("Fragment", "CITY: " + cityName);
                }
                break;
            case REQUEST_IMAGE:

                if (data != null) {
                    //  Bundle bundle = getIntent().getBundleExtra(Constants.KEY);
                    mSelectPath = data.getStringArrayListExtra(Constants.EXTRA_RESULT);
                    mAdapter = new ImagePublishAdapter(this, mSelectPath);
                    mGridView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    mAdapter.setDeleteImageClickListener(new DeleteImageClickListener() {
                        @Override
                        public void onDeleteItemClick(View view, int position) {
                            deleteImage(HttpUtil.deletePic, position);
                        }
                    });

                    Log.e("Fragment", "---------mSelectPath: " + mSelectPath);

                    if (mSelectPath.size() != 0) {
//                    for (String fp : mSelectPath) {
//                        upload(HttpUtil.uploadPic, fp);
//                    }

                        yaBitmap();


                    }

                }
                break;

        }

    }


    public void yaBitmap() {
        mYSelectPath.clear();
        if (listPicId != null && listPicId.size() > 0) {
            mSelectPath.removeAll(loadselectPath);//提交前移除 上传成功的路径 防止再次提交
        }
        Log.e("Fragment", "提交要移除的路径loadselectPath :" + loadselectPath);
        Log.e("Fragment", "提交前移除后的路径mSelectPath  :" + mSelectPath);
        for (String path : mSelectPath) {
            try {
                Bitmap bitmap = JBitmapUtils.revitionImageSize(path);

//                String path0 = path.substring(0, path.lastIndexOf("/") + 1);
//                String path1 = path.substring(path.lastIndexOf("."));
//                String path2 = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".")) + "ys";
//                String filepath = path0 + path2 + path1;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Log.e("options", "bitmap19 大小:" + bitmap.getAllocationByteCount());
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
                    Log.e("options", "bitmap12 大小:" + bitmap.getByteCount());
                }
                File mTmpFile = FileUtils.createTmpFile(context);
                String str = JBitmapUtils.saveBitmap2File(bitmap, mTmpFile.getAbsolutePath());
                mYSelectPath.add(str);
                //   File f = new File( "/storage/emulated/0/DCIM/"+bitName + ".jpg");
                //      JBitmapUtils.saveMyBitmap(bitmap,"ceshi"+i++);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Log.e("Fragment", "压缩生成的文件路径：:" + mYSelectPath);
        uploadImageFile(mYSelectPath);

    }


    @Override
    protected void onStart() {
        super.onStart();
        //  EventBus.getDefault().register(this);
        Log.d("Fragment", "onStart: " + mSelectPath);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Fragment", "onRestart: " + mSelectPath);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //     EventBus.getDefault().removeAllStickyEvents();
        //    EventBus.getDefault().unregister(this);
        Log.d("Fragment", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if(loadselectPath.size()>0){
            for (String path : loadselectPath) {
                File deleteFile = new File(path);
                deleteFile.delete();
                Log.e("options", "loadselectPath 删除成功！" + path);
            }
        }


    }

    /**
     * 加载开始时间选项 pickerview
     */
    private void optionStartTime() {

        pvStartTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH);
        pvStartTime.setTime(new Date());
        pvStartTime.setCyclic(false);
        pvStartTime.setCancelable(true);
        //时间选择后回调
        pvStartTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                tvstartDay.setText(ConversionUtil.getTime(date));

                millStart = date.getTime();
            }
        });

    }

    /**
     * 加载开始时间选项 pickerview
     */
    private void optionEndTime() {

        pvEndTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH);
        pvEndTime.setTime(new Date());
        pvEndTime.setCyclic(false);
        pvEndTime.setCancelable(true);
        //时间选择后回调
        pvEndTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                tvendDay.setText(ConversionUtil.getTime(date));

                millEnd = date.getTime();
            }
        });

    }


    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     */
    public void loadData2(String loadEmpInfo) {
        String[] key = new String[]{"request"};
        String[] value = new String[]{"1232"};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                    }
                }
        );
    }

    public void upload(String path, String loadImageFile) {
        RequestBody requestBody1 =
                RequestBody.create(MediaType.parse("multipart/form-data"), loadImageFile);
        Log.e("Fragment", "文件路径:" + loadImageFile);
        RetrofitService service = RetrofitClient.getInstance(this);
        Call<MessageBean> personInfo = service.uploadImage(requestBody1);
        personInfo.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {

                // {"picIds":[67],"errcode":0}
                MessageBean messageBean = response.body();
                List<Integer> list = messageBean.getPicIds();


                Log.e("Fragment", response.message());
                Log.v("Fragment", "success " + list);
            }


            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {
                Log.e("Upload", t.toString());
            }


        });

    }

    public void uploadImageFile(ArrayList<String> pathList) {
        Map<String, RequestBody> bodyMap = new HashMap<>();
        if (pathList.size() > 0) {
            for (int i = 0; i < pathList.size(); i++) {
                File file = new File(pathList.get(i));
                bodyMap.put("emp_Id", toRequestBody(Integer.toString(emp_Id)));
                bodyMap.put("cust_Id", toRequestBody(Integer.toString(cust_Id)));
                bodyMap.put("file" + i + "\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/png"), file));

            }

        }
        Log.e("Fragment", "emp_Id:" + emp_Id + " cust_Id:" + cust_Id + " 文件路径:" + pathList);
        // RetrofitService service = RetrofitClient.getInstance(this, "http://11.0.161.15:8080/payroll/");
        RetrofitService service = RetrofitClient.getInstance(this);
        Call<UpImagePicsBean> personInfo = service.uploadImages(bodyMap);
        personInfo.enqueue(new Callback<UpImagePicsBean>() {
            @Override
            public void onResponse(Call<UpImagePicsBean> call, Response<UpImagePicsBean> response) {
                //   {"picIds":[71,72],"errcode":0}      {"message":"单个文件超出最大值！！！","errcode":1}

                UpImagePicsBean upImagePicsBean = response.body();
                if (0 == upImagePicsBean.getErrcode()) {
                    String strPic = "";
                    for (UpImagePicsBean.PicsBean picsBean : upImagePicsBean.getPics()) {
                        picIds = picsBean.getId();
                        strPic += picIds + ",";
                        listPicId.add(picIds);
                        Log.e("Fragment", "Retrofit:" + picIds);
                    }
                    strPic = strPic.substring(0, strPic.lastIndexOf(","));
                    //   picIds=strPic;
                    for (String path : mYSelectPath) {
                        File deleteFile = new File(path);
                        deleteFile.delete();
                        Log.e("options", "删除成功！" + strPic);
                    }


                    mSelectPath.addAll(0, loadselectPath);
                    Log.d("Fragment", "最后路径lmSelectPath :" + mSelectPath);
                    mAdapter.notifyDataSetChanged();
                } else if (1 == upImagePicsBean.getErrcode()) {
                    AppToast.makeShortToast(ConsumptionActivity.this, "网络错误");
                }


            }

            @Override
            public void onFailure(Call<UpImagePicsBean> call, Throwable t) {

            }


        });


    }

    public RequestBody toRequestBody(String value) {        //   text/plain
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), value);
        return body;

    }

    /**
     * 保存消费记录
     * {'methodname':'expense/saveExpenseRecord.json','emp_Id':'','spend_Type':'','money_Amount':'','bill_Num':'',
     * 'detail_Memo':'','pic_Ids':'','spend_Begin':'',
     * 'spend_End':'','spend_City':'',detail_Id:""}
     */
    public void saveHttpData(String loadEmpInfo, final boolean flag) {


        HashMap<String, String> map = saveInterData(loadEmpInfo);

        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        JSONObject object = null;


                        try {
                            object = new JSONObject(jsonObject.toString());
                            String message = object.getString("message");
                            if ("success".equals(message)) {
                                if (flag) {
                                    finish();
                                } else {
                                    Intent intent = new Intent(ConsumptionActivity.this, ConsumptionTypeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                AppToast.makeShortToast(ConsumptionActivity.this, "网络错误");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }

        );
    }

    public String saveIcon;
    public String type;
    public String count;
    public String desc;

    // true  新建报销单页面保存在数据库 ，预览报销单页面跳转的update数据
    public void saveConsumeDao(boolean flag) {

//        Log.e("Fagment", "s毫秒：" + CommonUtils.millisecond(start) + "结束 ：" + CommonUtils.millisecond(end));
        Log.d("Fragment", "Inserted new note,icon : " + saveIcon);


        if (previewConsumption) {
            Log.d("Fragment", "更新    note, ID: " + consume.getId());
            Consume updateConsume = new Consume(consume.getId(), consume.getDetail_Id(), saveIcon, type, spend_Type, picIds, money, start, end, city, Integer.valueOf(count), desc);
            getConsumeDao().update(updateConsume);

        } else {
            Consume consumeDao = new Consume(null, "", saveIcon, type, spend_Type, picIds, money, start, end, city, Integer.valueOf(count), desc);
            getConsumeDao().insert(consumeDao);
            Log.d("Fragment", "Inserted new note, ID: " + consumeDao.getId());
        }
        if (flag) {
            finish();
        } else {
            Intent intent = new Intent(this, ConsumptionTypeActivity.class);
            startActivity(intent);
            finish();
        }


    }


    public HashMap<String, String> saveInterData(String loadEmpInfo) {
        //   saveConsumeDao();


        String[] key = new String[]{"emp_Id", "spend_Type", "money_Amount", "bill_Num", "detail_Memo", "pic_Ids", "spend_Begin", "spend_End", "spend_City", "detail_Id"};
        String[] value = new String[]{Integer.toString(emp_Id), spend_Type, money, count, desc, picIds, start, end, city, detail_Id};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        return map;
    }

    /**
     * Item
     * 删除消费记录：
     * {'methodname':'expense/deleteRecord.json','detail_Id':''}
     **/


    public void deleteRecord(String url) {
        String[] key = new String[]{"detail_Id"};
        String[] value = new String[]{detail_Id};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(url, key, value, loginEntity.getToken());

        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(url, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        JSONObject object = null;
                        try {
                            object = new JSONObject(jsonObject.toString());
                            String message = object.getString("message");
                            if ("success".equals(message)) {
                                AppToast.showShortText(ConsumptionActivity.this, "删除成功");
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }

        );
    }

    private void fontType() {
        //Font Awesome 图标
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        tvIcon.setTypeface(font);
    }
}
