package com.fesco.bookpay.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fesco.bookpay.adapter.RbmBillListAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.rbmbean.SpendTypesBean;
import com.fesco.bookpay.event.ConsumptionEvent;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gong.min on 2016/12/20.
 */
public class ConsumptionNotActivity extends BaseActivity  {
    public static final String SPEND_TYPES = "SPEND_TYPES"; // 被点击的Item 标识
    public static final String AGAIN_SPEND_TYPE = "AGAIN_SPEND_TYPE"; //标识 再次创建新的ConsumptionActivity页面

    public RecyclerView mRecyclerView;
    public RbmBillListAdapter rbmBillListAdapter;
    public   SpendTypesBean spendTypesBean;
    private Typeface font;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notconsume);
        initView();
        loadData(HttpUtil.getExpenseRecords);

    }

    private void initView() {
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.not_toolbar);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_text);
        textView.setText("消费信息");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsumptionNotActivity.this.finish();
            }
        });

        //spendTypesBean= (SpendTypesBean) getIntent().getExtras().getSerializable(ReimbursementActivity.SPENDTYES_BEANE);

        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_not);
        rbmBillListAdapter = new RbmBillListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(rbmBillListAdapter);

//        if(spendTypesBean !=null ) {
//            rbmBillListAdapter.setListBean(spendTypesBean.getList(), true);
//        }else
//            rbmBillListAdapter.setListBean(null, true);



        Button button = (Button) this.findViewById(R.id.btn_again_csp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new ConsumptionEvent(AGAIN_SPEND_TYPE));

                Intent intent = new Intent(ConsumptionNotActivity.this, ConsumptionTypeActivity.class);
                startActivity(intent);
            }
        });


        rbmBillListAdapter.setOnItemDeleteListener(new RbmBillListAdapter.OnDelelteListener() {
            @Override
            public void itemDelete(int adapterPosition) {

            }

            @Override
            public void itemOpenActivity(int adapterPosition) {
                EventBus.getDefault().postSticky(new ConsumptionEvent(AGAIN_SPEND_TYPE));
                List<SpendTypesBean.ListBean>      listBeen =spendTypesBean.getList();
                SpendTypesBean.ListBean spendTypes= listBeen.get(adapterPosition);
              //  EventBus.getDefault().postSticky(new ConsumptionEvent("open_spendType"));
                Intent intent =new Intent(ConsumptionNotActivity.this,ConsumptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(SPEND_TYPES, spendTypes);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    //    loadData(HttpUtil.getExpenseRecords);



    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadData(HttpUtil.getExpenseRecords);


    }

    public void loadData(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id"};
        String[] value = new String[]{Integer.toString(emp_Id)};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                      spendTypesBean = gson.fromJson(jsonObject.toString(), SpendTypesBean.class);
                        if (spendTypesBean.getErrcode()==0) {
                            if (spendTypesBean.getList().size() > 0) {

                                rbmBillListAdapter.setListBean(spendTypesBean.getList(), true, font);
                               rbmBillListAdapter.notifyDataSetChanged();
                            }

                        } else
                        {
                            rbmBillListAdapter.setListBean(null, true,font);
                            rbmBillListAdapter.notifyDataSetChanged();
                        }


                    }

                }

        );


    }

}
