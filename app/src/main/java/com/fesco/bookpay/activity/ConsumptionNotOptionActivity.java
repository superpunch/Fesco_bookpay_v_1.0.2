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
import com.fesco.bookpay.util.DoubleUtil;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gong.min on 2016/12/20.
 */
public class ConsumptionNotOptionActivity extends BaseActivity {
    public static final String SPEND_TYPES = "SPEND_TYPES";


    private boolean isOnCheckBox = false;
    public RecyclerView mRecyclerView;
    public RbmBillListAdapter rbmBillListAdapter;
    public SpendTypesBean spendTypesBean;
    public double money_Amount;
    public List<SpendTypesBean.ListBean> spendTypesBeanList;
    public List<SpendTypesBean.ListBean> addSpendTypesBeanList = new ArrayList<>();
    private Typeface font;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notconsume_option);
        initView();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.not_toolbar);
        final TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_text);
        final TextView checkAll = (TextView) toolbar.findViewById(R.id.toolbar_back);
        checkAll.setVisibility(View.VISIBLE);
        checkAll.setText("全选");
        textView.setText("选择要制单的消费");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsumptionNotOptionActivity.this.finish();
            }
        });

        //  spendTypesBean= (SpendTypesBean) getIntent().getExtras().getSerializable(ReimbursementActivity.SPENDTYES_BEANE);

        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_not_option);
        rbmBillListAdapter = new RbmBillListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(rbmBillListAdapter);
//        if(spendTypesBean !=null ) {
//            rbmBillListAdapter.setListBean(spendTypesBean.getList(), true);
//        }else
//            rbmBillListAdapter.setListBean(null, true);

        final TextView option_amount = (TextView) findViewById(R.id.not_option_amount);
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOnCheckBox) {
                    checkAll.setText("全不选");
                    Map<Integer, Boolean> m = rbmBillListAdapter.getMap();
                    for (int i = 0; i < m.size(); i++) {
                        m.put(i, true);
                    }
                    isOnCheckBox = true;

                    int amount = m.size();

                    option_amount.setText("已选" + amount + "条，" + money_Amount + "元");

                } else {
                    checkAll.setText("全选");
                    Map<Integer, Boolean> m = rbmBillListAdapter.getMap();
                    for (int i = 0; i < m.size(); i++) {
                        m.put(i, false);
                    }
                    isOnCheckBox = false;
                    option_amount.setText(R.string.rbm_information);

                }
               rbmBillListAdapter.notifyDataSetChanged();


            }
        });

        rbmBillListAdapter.setOnCheckListener(new RbmBillListAdapter.OnCheckClickListener() {
            @Override
            public void onCheckAmountMoney(int position) {
                Map<Integer, Boolean> m = rbmBillListAdapter.getMap();

                int amount = 0;
                double moneyCount = 0;

                for(int i=0;i<m.size();i++){
                    if (m.get(i)) {
                        SpendTypesBean.ListBean stb = spendTypesBeanList.get(i);
                        moneyCount=DoubleUtil.add(moneyCount,Double.valueOf(stb.getMoney_Amount()));
                        amount++;

                    }
                }
                option_amount.setText("已选" + amount + "条，" + moneyCount + "元");

            }
        });


        Button button = (Button) this.findViewById(R.id.rbm_addition);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<Integer, Boolean> m = rbmBillListAdapter.getMap();
               if( m.size()>0) {
                   for (int i = 0; i < m.size(); i++) {
                       if (m.get(i)) {
                           SpendTypesBean.ListBean stb = spendTypesBeanList.get(i);
                           addSpendTypesBeanList.add(stb);
                       }

                   }
                   Intent intent = new Intent();
                   intent.putExtra(SPEND_TYPES, (Serializable) addSpendTypesBeanList);
                   setResult(RESULT_OK, intent);
                   finish();


               }
            }
        });

        loadData(HttpUtil.getExpenseRecords);
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //  loadData(HttpUtil.getExpenseRecords);


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
                        if (spendTypesBean != null) {
                            if (spendTypesBean.getList().size() > 0) {
                                spendTypesBeanList = spendTypesBean.getList();
                                rbmBillListAdapter.setListBean(spendTypesBeanList, false, font);
                                rbmBillListAdapter.notifyDataSetChanged();
                                List<SpendTypesBean.ListBean> listBeen = spendTypesBean.getList();
                             double   moneyCount=0;
                                for (SpendTypesBean.ListBean listBean : spendTypesBeanList) {
                                    moneyCount=DoubleUtil.add(moneyCount,Double.valueOf(listBean.getMoney_Amount()));
                                }
                                money_Amount =  moneyCount;
                            }

                        } else {
                            rbmBillListAdapter.setListBean(null, false, font);
                            rbmBillListAdapter.notifyDataSetChanged();
                        }


                    }

                }

        );


    }

}
