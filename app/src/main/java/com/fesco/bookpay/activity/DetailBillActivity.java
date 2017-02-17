package com.fesco.bookpay.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.fesco.bookpay.adapter.approvaladapter.ApprovalBillDetailAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.ProvinceBean;
import com.fesco.bookpay.entity.approvalbean.BillApprovelBean;
import com.fesco.bookpay.entity.approvalbean.BillDetailBean;
import com.fesco.bookpay.fragment.ApprovalBillFragment;
import com.fesco.bookpay.impl.ItemClickListener;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.CommonUtils;
import com.fesco.bookpay.util.ConversionUtil;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 加班页面
 * Created by gong.min on 2016/9/29.
 */
public class DetailBillActivity extends BaseActivity {
    public static final String DETAIL_BILL = "DETAIL_BILL";
    String TAG = "Fragment";
    private Toolbar toolbar;
    private int apply_Id;
    private String approval_Man = "";
    private View layout_lastapproval;

    private TextView textView;
    private Button btnAagree;
    private Button btnDisAagree;

    private TextView mAccount;
    private TextView mApprovalTime;
    private RecyclerView mRecyclerView;
    private EditText mOpinion;
    private EditText mPerson;
    private LinearLayout linear_person;
    private boolean mFirst; //初始化默认不弹出
    private Context mContext;
    private ApprovalBillDetailAdapter approvalBillDetailAdapter;
    private Typeface font;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tab_approval_billdetail);
        mContext = this;
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        apply_Id = getIntent().getIntExtra(ApprovalBillFragment.APPLY_ID, 0);
        initViews();
        loadData(HttpUtil.loadExpenseExamInfo, 3);
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.bill_detail_toolbar);
        textView = (TextView) toolbar.findViewById(R.id.toolbar_text);
        btnAagree = (Button) findViewById(R.id.bill_detail_agree);
        btnDisAagree = (Button) findViewById(R.id.bill_detail_disagree);
        mAccount = (TextView) findViewById(R.id.bill_detail_account);
        mApprovalTime = (TextView) findViewById(R.id.bill_detail_day);
        mOpinion = (EditText) findViewById(R.id.bill_detail_opinion);
        mPerson = (EditText) findViewById(R.id.bill_detail_person);

        layout_lastapproval = this.findViewById(R.id.layout_last_approval);

        mRecyclerView = (RecyclerView) findViewById(R.id.bill_detail_recycle);
        approvalBillDetailAdapter = new ApprovalBillDetailAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(approvalBillDetailAdapter);
        mRecyclerView.setFocusable(false);
        initializeLinearBackground();
        textView.setText("审批进度");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailBillActivity.this.finish();
            }
        });

        btnAagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(HttpUtil.saveExpenseExamResult, 1);
            }
        });
        btnDisAagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(HttpUtil.saveExpenseExamResult, 0);

            }
        });
        mPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("setOnClickListener");
                pvOptionsPerson.show();
            }
        });


    }

    private void initializeLinearBackground() {


        final LinearLayout linear_opinion = (LinearLayout) findViewById(R.id.linear_bill_detail_opinion);
        linear_person = (LinearLayout) findViewById(R.id.linear_bill_detail_person);
        mPerson.setInputType(InputType.TYPE_NULL);
        linear_opinion.setSelected(true);
        mPerson.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                linear_opinion.setSelected(false);
                linear_person.setSelected(true);
                return false;
            }
        });

        mOpinion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                linear_opinion.setSelected(true);
                linear_person.setSelected(false);
                return false;
            }
        });


//        mPerson.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                Log.d(TAG, "mPerson is MotionEvent.hasFocus" + hasFocus);
//                if (hasFocus) {
//                    if (mFirst) {
//                        pvOptionsPerson.show();
//                    }
//                    linear_person.setBackgroundResource(R.drawable.bg_edittext_focused);
//                } else {
//                    linear_person.setBackgroundResource(R.drawable.bg_edittext_normal);
//                }
//            }
//        });
//        mOpinion.setFocusable(true);
//        mOpinion.setFocusableInTouchMode(true);
////        mOpinion.requestFocus();

//
//        mOpinion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//
//                    linear_opinion.setBackgroundResource(R.drawable.bg_edittext_focused);
//                } else {
//                    linear_opinion.setBackgroundResource(R.drawable.bg_edittext_normal);
//
//
//                }
//            }
//        });

    }


    /**
     * 网络请求 / 加载报销审批信息
     * emp_Id,cust_Id
     * is_Pass : 0 不通过 , 1 通过
     * result：0=未通过，1=通过
     * 补签审批保存：{'methodname':'kq/saveSignLaterExamStep.json','emp_Id':'','cust_Id':'','apply_Id':'','is_Pass':'','next_Approval_Man':'','memo':''}
     * 报销审批接口换成这个 {'methodname':'expense/saveExpenseExamResult.json',"'result':'','memo':'','next_Approval_Man':'','apply_Id':'','emp_Id':''}
     *
     * @param loadEmpInfo
     */
    public void loadData(final String loadEmpInfo, final int result) {
        String[] key = null;
        String[] value = null;
        if (result == 3) {
            key = new String[]{"cust_Id", "apply_Id"};
            value = new String[]{Integer.toString(cust_Id), Integer.toString(apply_Id)};
            okHttpApproalBill(loadEmpInfo, key, value, 3);
        } else if (result == 1) {  //同意 分两种情况  选择审批人   result=1, 没选 result=1;
            if (!TextUtils.isEmpty(approval_Man)) {
                key = new String[]{"emp_Id", "apply_Id", "result", "next_Approval_Man", "memo", "type"};
                value = new String[]{Integer.toString(emp_Id), Integer.toString(apply_Id)
                        , "1", approval_Man, mOpinion.getText().toString(), ""};
                okHttpApproalBill(loadEmpInfo, key, value, 1);

            } else {
                //同意没选择审批人  访问getLastCheckMan 接口    若type_Id==0时  type_Id ，type 返回 ""
                key = new String[]{"cust_Id","type_Id","type"};
                value = new String[]{Integer.toString(cust_Id),Integer.toString(emp_Id),"1"};

                HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(HttpUtil.getLastCheckMan, key, value, token);
                OKManager manager = OKManager.getInstance(this);
                manager.sendComplexForm(HttpUtil.getLastCheckMan, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        // okHttpApproalBill();
                        BillApprovelBean billApprovelBean = gson.fromJson(jsonObject.toString(), BillApprovelBean.class);
                        if (billApprovelBean != null) {
                            if (billApprovelBean.getErrcode() == 0) {
                                BillApprovelBean.CheckManBean checkManBean = billApprovelBean.getCheckMan();
                                String type = Integer.toString(checkManBean.getType());
                                String approval_Man = Integer.toString(checkManBean.getType_Id());
                                 if(checkManBean.getType_Id()==0){
                                      approval_Man="";
                                      type="";
                                 }

                                String[] key = new String[]{"emp_Id", "apply_Id", "result", "next_Approval_Man", "memo", "type"};
                                String[] value = new String[]{Integer.toString(emp_Id), Integer.toString(apply_Id)
                                        , "1", approval_Man, mOpinion.getText().toString(), type};
                                okHttpApproalBill(loadEmpInfo, key, value, 1);

                            } else if (billApprovelBean.getErrcode() == 1) {
                                  AppToast.showShortText(mContext, "同意失败");
                            } else {
                                  AppToast.showShortText(mContext, "网络错误");
                            }

                        }
                    }
                });


            }

        } else if (result == 0) {   //驳回   next_Approval_Man 永为""
            key = new String[]{"emp_Id", "apply_Id", "result", "next_Approval_Man", "memo", "type"};
            value = new String[]{Integer.toString(emp_Id), Integer.toString(apply_Id)
                    , Integer.toString(result), "", mOpinion.getText().toString(), ""};
            okHttpApproalBill(loadEmpInfo, key, value, 0);

        }


    }



    private void okHttpApproalBill(String loadEmpInfo, String[] key, String[] value, final int result) {

        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());
                if (result == 3) {
                    BillDetailBean billDetailBean = gson.fromJson(jsonObject.toString(), BillDetailBean.class);
                    List<BillDetailBean.ApprovalManListBean> approvalManListBeen = billDetailBean.getApprovalManList();
                    loadPersonOption(approvalManListBeen);
                    BillDetailBean.LastApprovalStepBean loadPersonOption = billDetailBean.getLastApprovalStep();
                    if (loadPersonOption != null) {
                        CommonUtils.setLastApprovalResult(layout_lastapproval, loadPersonOption);

                      if("0".equals(billDetailBean.getIs_Other_Party())||"1".equals(billDetailBean.getIs_Other_Party()) ){
                          linear_person.setVisibility(View.GONE);
                        }
                    }
                    BillDetailBean.ApplyBean apply = billDetailBean.getApply();
                    initValuation(apply);
                    final List<BillDetailBean.ApplyBean.DetailsBean> detailsBeanList = billDetailBean.getApply().getDetails();
                    approvalBillDetailAdapter.setListBean(detailsBeanList,font,true);
                        approvalBillDetailAdapter.notifyDataSetChanged();
                    approvalBillDetailAdapter.setOnItemClickListener(new ItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            Intent inentApproval = new Intent(mContext, ConsumptionPreviewActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(DETAIL_BILL, detailsBeanList.get(position));
                            inentApproval.putExtras(bundle);
                            startActivity(inentApproval);
                        }
                    });

                } else if (result == 1) {
                    CommonUtils.getMessageResult(1, jsonObject.toString(), DetailBillActivity.this);
                } else if (result == 0) {
                    CommonUtils.getMessageResult(0, jsonObject.toString(), DetailBillActivity.this);

                }
            }
        });


    }


    private void initValuation(BillDetailBean.ApplyBean apply) {
        mApprovalTime.setText( ConversionUtil.getLongYearHour(apply.getApply_Date()));
        mAccount.setText(apply.getAccount_Name());

    }

    private OptionsPickerView pvOptionsPerson;
    private ArrayList<ProvinceBean> optionsItems = new ArrayList<>();

    /**
     * 加载选项 pickerview
     *
     * @param listBeen
     */
    private void loadPersonOption(final List<BillDetailBean.ApprovalManListBean> listBeen) {
        //选项选择器
        pvOptionsPerson = new OptionsPickerView(this);
        //选项1
        int i = 0;
        for (BillDetailBean.ApprovalManListBean approvalManListBean : listBeen) {
            optionsItems.add(new ProvinceBean(i++, approvalManListBean.getEmp_Name(), "11", "1"));
        }

        pvOptionsPerson.setPicker(optionsItems);
        pvOptionsPerson.setTitle("请选择");
        pvOptionsPerson.setCyclic(false);
        mFirst = true;
        //设置默认选中的1级项目
        pvOptionsPerson.setSelectOptions(1);
        pvOptionsPerson.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = optionsItems.get(options1).getPickerViewText();
                mPerson.setText(tx);
                approval_Man = Integer.toString(listBeen.get(options1).getEmp_Id());

            }
        });

    }

}