package com.fesco.bookpay.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.fesco.bookpay.adapter.RbmApproveAdapter;
import com.fesco.bookpay.adapter.RbmListAdapter;
import com.fesco.bookpay.adapter.approvaladapter.ApprovalBillDetailAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.approvalbean.BillDetailBean;
import com.fesco.bookpay.entity.rbmbean.ApprovelIngBean;
import com.fesco.bookpay.util.DoubleUtil;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gong.min on 2016/11/4.
 */
public class ReimbursementApproveActivity extends BaseActivity {

    public static final String NOTPASS_ID = "NOTPASS_ID";

    private RecyclerView mRecyclerView;
    private RbmListAdapter rbmListAdapter;
    private TextView approveTitle;
    private TextView approveBill;
    private TextView approveState;
    private TextView approveMoney;
    private TextView approveIng;
    private TextView approveDepartMent;
    private TextView approveCount;
    private Button approveBtn;
    private ListView listView;
    private ApprovalBillDetailAdapter approvalBillDetailAdapter;
    private String apply_Id;
    private Typeface font;

    private boolean isBtnHide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimburse_approve);
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        String applyIng = getIntent().getStringExtra(ReimbursementActivity.APPLYING);
        String applyEdit = getIntent().getStringExtra(ReimbursementActivity.NOTPASS);
        if (!TextUtils.isEmpty(applyIng)) {
            apply_Id = applyIng;
        } else if (!TextUtils.isEmpty(applyEdit)) {
            apply_Id = applyEdit;
            isBtnHide=true;


        }
        initView();
        loadData(HttpUtil.loadExpenseExamInfo);
        loadDataApproval(HttpUtil.loadExpenseExamInfoForEmp);

    }


    private void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.rb_toolbar);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_text);
        textView.setText("报销进度");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReimbursementApproveActivity.this.finish();
            }
        });


        approveTitle = (TextView) this.findViewById(R.id.rbm_approval_title);
        approveBill = (TextView) this.findViewById(R.id.rbm_approval_bill);
        approveMoney = (TextView) this.findViewById(R.id.rbm_approval_money);
        approveIng = (TextView) this.findViewById(R.id.rbm_approveing);
        approveDepartMent = (TextView) this.findViewById(R.id.rbm_approval_department);
        // approveDay = (TextView) this.findViewById(R.id.rbm_approval_day);
        approveCount = (TextView) this.findViewById(R.id.rbm_approve_count);

        approveBtn = (Button) this.findViewById(R.id.rbm_approve_btn);


        mRecyclerView = (RecyclerView) this.findViewById(R.id.rbm_approve_recycler);
        approvalBillDetailAdapter = new ApprovalBillDetailAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(approvalBillDetailAdapter);

        listView = (ListView) this.findViewById(R.id.rbm_approve_listview);


        if(isBtnHide){

            approveBtn.setVisibility(View.VISIBLE);

        approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(apply_Id)) {
                    Intent intent = new Intent(ReimbursementApproveActivity.this, ReimburBillActivity.class);
                    intent.putExtra(NOTPASS_ID, apply_Id);
                    startActivity(intent);
                    finish();
                }


            }
        });
        }
    }


    /**
     * 网络请求
     */
    public void loadData(String loadEmpInfo) {
        String[] key = new String[]{"cust_Id", "apply_Id", "emp_Id"};
        String[] value = new String[]{Integer.toString(cust_Id), (apply_Id), Integer.toString(emp_Id)};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        BillDetailBean billDetailBean = gson.fromJson(jsonObject.toString(), BillDetailBean.class);
                        if (billDetailBean.getErrcode() != 1) {
                            BillDetailBean.ApplyBean apply = billDetailBean.getApply();
                            initValuation(apply);
                            List<BillDetailBean.ApplyBean.DetailsBean> detailsBeanList = billDetailBean.getApply().getDetails();
                            approvalBillDetailAdapter.setListBean(detailsBeanList, font, false);
                            approvalBillDetailAdapter.notifyDataSetChanged();
                        }

                    }


                }

        );
    }

    private void loadDataApproval(String url) {

        String[] key = new String[]{"cust_Id", "apply_Id"};
        String[] value = new String[]{Integer.toString(cust_Id), (apply_Id)};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(url, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(url, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());

                        ApprovelIngBean approvelIngBean = gson.fromJson(jsonObject.toString(), ApprovelIngBean.class);

                        List<ApprovelIngBean.LastApprovalStepBean> lastApprovalStepBeanList = approvelIngBean.getLastApprovalStep();
                        if (lastApprovalStepBeanList != null && lastApprovalStepBeanList.size() > 0) {
                            listView.setDividerHeight(0);
                            RbmApproveAdapter rbmApproveAdapter = new RbmApproveAdapter(context,getListData(lastApprovalStepBeanList));
                            listView.setAdapter(rbmApproveAdapter);


                        }


                    }


                }

        );


    }

    private void initValuation(BillDetailBean.ApplyBean apply) {
        approveTitle.setText(apply.getTitle());
        approveDepartMent.setText(apply.getGroup_Name());
        if (apply.getType() == 1) {
            approveBill.setText("日常报销单");
        } else if (apply.getType() == 2) {
            approveBill.setText("差旅报销单");
        } else if (apply.getType() == 3) {
            approveBill.setText("付款申请单");
        }
        approveMoney.setText(apply.getEmp_Name());
        double amount = 0;
        for (BillDetailBean.ApplyBean.DetailsBean detailsBean : apply.getDetails()) {
            amount = DoubleUtil.add(amount, Double.valueOf(detailsBean.getMoney_Amount()));
        }
        approveMoney.setText(Double.toString(amount));
        int count = apply.getDetails().size();
        approveCount.setText(Integer.toString(count));


    }

    private List<Map<String, Object>> getData(BillDetailBean.ApplyBean apply) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<>();
        map.put("title", apply.getApproval_Man());
        map.put("year", apply.getApply_Date());
        list.add(map);

        map = new HashMap<>();
        map.put("title", "这是第2行测试数据");
        map.put("year", apply.getApply_Date());
        list.add(map);

        return list;
    }

    private List<Map<String, Object>> getListData(List<ApprovelIngBean.LastApprovalStepBean> lastApprovalStepBeanList) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ApprovelIngBean.LastApprovalStepBean lastApprovalStepBean : lastApprovalStepBeanList) {
            Map<String, Object> map = new HashMap<>();
            map.put("approval_Man_Str", lastApprovalStepBean.getApproval_Man_Str()); //胡松
            map.put("is_Pass_Str", lastApprovalStepBean.getIs_Pass_Str());       ///未通过
            map.put("approval_Time", lastApprovalStepBean.getApproval_Time());   //1484806926000
            Log.d("Fragment","---111----时间:"+lastApprovalStepBean.getApproval_Time());
            list.add(map);
        }
        return list;
    }
}