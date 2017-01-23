package com.fesco.bookpay.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fesco.bookpay.adapter.RbmTypeListAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.ConsumptionBean;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.RankOutLateBean;
import com.fesco.bookpay.impl.RetrofitService;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.util.okhttp.RetrofitClient;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gong.min on 2016/11/11.
 */
public class ConsumptionTypeActivity extends BaseActivity {

    public static final String TYPENAME = "TYPENAME"; //一级页面
    public static final String TYPE_DETAIL = "TYPE_DETAIL"; //二级页面
    public static final String TYPE_ICON = "TYPE_ICON"; // icon


    private RecyclerView mRecyclerView;
    private RbmTypeListAdapter rbmTypeListAdapter;

    private List<ConsumptionBean.SpendTypesBean> spendTypes;
    private Typeface font;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_consumption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("新建报销类型");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsumptionTypeActivity.this.finish();
            }
        });
         font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
    //    spendTypes = (List<ConsumptionBean.SpendTypesBean>) getIntent().getSerializableExtra(ReimburBillActivity.SPEND_TYPES);



        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_type);
        rbmTypeListAdapter = new RbmTypeListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(rbmTypeListAdapter);
 //       rbmTypeListAdapter.setSpendTypes(spendTypes, font);
     //   rbmTypeListAdapter.notifyDataSetChanged();

      loadRetrofitData(HttpUtil.loadAddApply);
        //   loadData(HttpUtil.getCedRanking);


    }

    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     * @param loginEntity
     */
    public void loadData(String loadEmpInfo, LoginEntity loginEntity) {
        String[] key = new String[]{"cust_Id"};
        String[] value = new String[]{Integer.toString(loginEntity.getCust_Id())};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        RankOutLateBean rankOutLateBean = gson.fromJson(jsonObject.toString(), RankOutLateBean.class);
                        if (rankOutLateBean != null && 0 == rankOutLateBean.getErrcode()) {

                        } else {

                        }


                    }
                }
        );
    }
    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     */
    public void loadRetrofitData(String loadEmpInfo) {
        HashMap<String, String> map = HttpOkManagerUtils.okManagerPost(loadEmpInfo, Integer.toString(cust_Id), Integer.toString(emp_Id), token);
        RetrofitService service = RetrofitClient.getInstance(this);
        Call<ConsumptionBean> personInfo = service.postConsumptionBean(map);
        personInfo.enqueue(new Callback<ConsumptionBean>() {
            @Override
            public void onResponse(Call<ConsumptionBean> call, Response<ConsumptionBean> response) {
                Logger.d(response.body().toString());
                ConsumptionBean consumptionBean = response.body();

                spendTypes = consumptionBean.getSpendTypes();
                Log.e("Fragment", "----------: " + spendTypes.get(0).getType_Name());
                rbmTypeListAdapter.setSpendTypes(spendTypes, font);
                rbmTypeListAdapter.notifyDataSetChanged();
                rbmTypeListAdapter.setOnItemClickListener(new RbmTypeListAdapter.ItemTypeClickListener() {
                    @Override
                    public void onItemClick(View view, int postion) {
                        ConsumptionBean.SpendTypesBean spendTypesBean = spendTypes.get(postion);
                        List<ConsumptionBean.SpendTypesBean.SubTypesBean> subTypesBeen = spendTypesBean.getSub_Types();

                        if (subTypesBeen != null && subTypesBeen.size() > 0) {
                            Intent intent = new Intent(ConsumptionTypeActivity.this, ConsumptionTypedetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(TYPE_DETAIL, spendTypesBean);
                            bundle.putString(TYPE_ICON, spendTypesBean.getAndroid_Icon());

                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(ConsumptionTypeActivity.this, ConsumptionActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(TYPENAME, spendTypesBean);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }


                    }
                });


            }

            @Override
            public void onFailure(Call<ConsumptionBean> call, Throwable t) {
                Logger.i("response.body().toString()");
            }
        });

    }

}
