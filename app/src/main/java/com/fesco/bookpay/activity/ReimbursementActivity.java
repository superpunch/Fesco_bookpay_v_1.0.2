package com.fesco.bookpay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.fesco.bookpay.adapter.RbmListAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.ExpenseApplyListBean;
import com.fesco.bookpay.entity.rbmbean.SpendTypesBean;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.DoubleUtil;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.view.FullyLinearLayoutManager;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gong.min on 2016/11/4.
 */
public class ReimbursementActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    // private LoginEntity loginEntity;
    public static final String APPLYING = "APPLYING";//审批中
    public static final String NOTPASS= "NOTPASS"; //未通过
    public static final String SAVE_LISTBEAN = "SAVE_LISTBEAN";

    public static final String SPENDTYES_BEANE = "SPENDTYES_BEANE";

    private RecyclerView mRecyclerView;
    private RbmListAdapter rbmListAdapter;
 //   private ScrollView mScrollView;
    private SpendTypesBean intentSpendTypesBean;
    private  ExpenseApplyListBean expenseApplyListBean;


    private LinearLayout linear_rbm_not;
    private TextView notAmount;
    private TextView notMoney;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimburse);
        initView();

        loadData2(HttpUtil.getExpenseApplyList);

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.rb_toolbar);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_text);
        textView.setText("报销");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReimbursementActivity.this.finish();
            }
        });
       // mScrollView = (ScrollView) this.findViewById(R.id.scroll_reb);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_rmb);
        rbmListAdapter = new RbmListAdapter(this);
        FullyLinearLayoutManager mLayoutManager = new FullyLinearLayoutManager(this);
        //设置布局样式
        mRecyclerView.setLayoutManager(mLayoutManager);
        //  mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(rbmListAdapter);


//        notAmount = (TextView) findViewById(R.id.rbm_not_amount);
//        notMoney = (TextView) findViewById(R.id.rbm_not_money);
//        linear_rbm_not = (LinearLayout) findViewById(R.id.linear_rbm_not);
//        linear_rbm_not.setOnClickListener(this);


        ToggleButton tbA = (ToggleButton) findViewById(R.id.rb_a);
        ToggleButton tbB = (ToggleButton) findViewById(R.id.rb_b);
        ToggleButton tbC = (ToggleButton) findViewById(R.id.rb_c);
        tbA.setOnCheckedChangeListener(this);
        tbB.setOnCheckedChangeListener(this);
        tbC.setOnCheckedChangeListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {//case R.id.linear_rbm_not:
//                Intent intent = new Intent(this, ConsumptionNotActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(SPENDTYES_BEANE, intentSpendTypesBean);
//                intent.putExtras(bundle);
//                startActivity(intent);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_a:
                AppToast.makeShortToast(this,"暂未开放");
                break;
            case R.id.rb_b:
                Intent intent = new Intent(this, ReimburBillActivity.class);
                startActivity(intent);
                //   this.finish();
                break;
            case R.id.rb_c:
                Intent intentSpend = new Intent(this, ConsumptionNotActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(SPENDTYES_BEANE, intentSpendTypesBean);
                intentSpend.putExtras(bundle);
                startActivity(intentSpend);
                break;
        }
    }


    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     */
    public void loadData(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id"};
        String[] value = new String[]{loginEntity.getEmp_Id() + ""};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                     //   mScrollView.scrollTo(0, 0);
                     //   mScrollView.smoothScrollTo(0, 0);
                        //        final CheckListBean checkListBean = gson.fromJson(jsonObject.toString(), CheckListBean.class);
                        //      if (checkListBean != null || checkListBean.getList().size() > 0) {
//                            approvalCheckAdapter.setListBean(checkListBean.getList());
//                            approvalCheckAdapter.notifyDataSetChanged();
//                            approvalCheckAdapter.setOnItemClickListener(new ItemClickListener() {
//                                @Override
//                                public void onItemClick(View view, int postion) {
//                                    Intent inentApproval = new Intent(mActivity, DetailCheckActivity.class);
//                                    int applyId = checkListBean.getList().get(postion).getApply_Id();
//                                    Bundle bundleApproval = new Bundle();
//                                    bundleApproval.putInt("Apply_Id", applyId);
//                                    bundleApproval.putSerializable("DetailCheckActivity", flag);
//                                    inentApproval.putExtras(bundleApproval);
//                                    startActivity(inentApproval);
                        //      }
                    }

                    //        );
//        }else{
//                            approvalCheckAdapter.setListBean(null);
//                            approvalCheckAdapter.notifyDataSetChanged();
//        }


                }

        );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadData2(HttpUtil.getExpenseApplyList);
        //loadNotConsumption(HttpUtil.getExpenseRecords);
    }

    public void loadData2(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id"};
        String[] value = new String[]{Integer.toString(emp_Id)};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());

                           expenseApplyListBean = gson.fromJson(jsonObject.toString(), ExpenseApplyListBean.class);


                        if (expenseApplyListBean != null || expenseApplyListBean.getList().size() > 0) {
                            rbmListAdapter.setExpenseApplyListBean(expenseApplyListBean.getList());

                            rbmListAdapter.setOnItemListener(new RbmListAdapter.OnItemListener() {
                                @Override
                                public void setOpenItem(int position) {
                                    ExpenseApplyListBean.ListBean listBean = expenseApplyListBean.getList().get(position);
                                    // 0待提交，1待审批，2待支付，3未通过，4已支付

                                    if (0 == listBean.getExam_End_Is()) {

                                        Intent intent = new Intent(ReimbursementActivity.this, ReimburBillActivity.class);
//                                        Bundle bundle = new Bundle();
//                                        bundle.putSerializable(SAVE_LISTBEAN, listBean);
//                                        intent.putExtras(bundle);
                                        intent.putExtra(SAVE_LISTBEAN,listBean.getApply_Id());
                                        startActivity(intent);


                                    } else if (1 == listBean.getExam_End_Is()) {
                                        Intent intent = new Intent(ReimbursementActivity.this, ReimbursementApproveActivity.class);
//                                        Bundle bundle = new Bundle();
//                                        bundle.putSerializable(APPLYLISTBEAN, listBean);
//                                        intent.putExtras(bundle);
                                        intent.putExtra(APPLYING,listBean.getApply_Id());
                                        startActivity(intent);
                                    }else if(3 == listBean.getExam_End_Is()){
                                        Intent intent = new Intent(ReimbursementActivity.this, ReimbursementApproveActivity.class);
//                                        Bundle bundle = new Bundle();
//                                        bundle.putSerializable(SAVE_LISTBEAN, listBean);
//                                        intent.putExtras(bundle);
                                        intent.putExtra(NOTPASS,listBean.getApply_Id());

                                        startActivity(intent);


                                    }


                                }
                            });
                            rbmListAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
    }

    /**
     * 网络请求 计算未制消费的 条数与金钱
     * emp_Id,
     *
     * @param loadEmpInfo
     */


    public void loadNotConsumption(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id"};
        String[] value = new String[]{Integer.toString(emp_Id)};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        final SpendTypesBean spendTypesBean = gson.fromJson(jsonObject.toString(), SpendTypesBean.class);
                        if (spendTypesBean != null) {
                            intentSpendTypesBean=spendTypesBean;
                            List<SpendTypesBean.ListBean> listBeen = spendTypesBean.getList();
                            int amount = listBeen.size();
                            double money = 0;
                            for (SpendTypesBean.ListBean bean : listBeen) {
                                money= DoubleUtil.add(money,Double.valueOf(bean.getMoney_Amount()));
                            }
                            notAmount.setText(Integer.toString(amount) + "条消费记录");
                            notMoney.setText("共￥" + money);
                        }
                    }

                }

        );


    }


}