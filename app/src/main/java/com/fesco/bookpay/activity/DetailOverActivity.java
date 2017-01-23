package com.fesco.bookpay.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.ProvinceBean;
import com.fesco.bookpay.entity.approvalbean.OverDetailBean;
import com.fesco.bookpay.util.CommonUtils;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 加班页面
 * Created by gong.min on 2016/9/29.
 */
public class DetailOverActivity extends BaseActivity {
    private Toolbar toolbar;
    private LoginEntity loginEntity;
    int emp_Id;
    int cust_Id;
    int apply_Id;
    String approval_Man="";
    String token;
    private  View   layout_lastapproval;
    private TextView textView;
    private Button btnAagree;
    private Button btnDisAagree;

    private TextView mStart;
    private TextView mEnd;
    private TextView mReason;
    private EditText mOpinion;
    private EditText mPerson;
    private Gson gson;
    private Context mContext;
    private boolean mFirst;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tab_approval_overtimedetail);
        mContext=this;
        Bundle bundle = getIntent().getExtras();
        apply_Id   = bundle.getInt("Apply_Id");
        loginEntity = (LoginEntity) bundle.getSerializable("DetailOverActivity");
        if (loginEntity != null) {
            emp_Id = loginEntity.getEmp_Id();
            cust_Id = loginEntity.getCust_Id();
            token = loginEntity.getToken();
        }

        initViews();
        loadData(HttpUtil.getExtraWorkApply, 3);
    }

    private void initViews() {
        gson = new Gson();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView) findViewById(R.id.toolbar_text);
        btnAagree = (Button) findViewById(R.id.overdetail_agree);
        btnDisAagree = (Button) findViewById(R.id.overdetail_disagree);
        mStart = (TextView) findViewById(R.id.overdetail_start);
        mEnd = (TextView) findViewById(R.id.overdetail_end);
        mReason = (TextView) findViewById(R.id.overdetail_reason);
        mOpinion = (EditText) findViewById(R.id.overdetail_opinion);
        mPerson = (EditText) findViewById(R.id.overdetail_person);

        layout_lastapproval=this.findViewById(R.id.layout_lastapproval);

        textView.setText("加班审批");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailOverActivity.this.finish();
            }
        });

        btnAagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   loadData(HttpUtil.saveExtraWorkExamStep, 1);
            }
        });
        btnDisAagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    loadData(HttpUtil.saveExtraWorkExamStep, 0);

            }
        });


        initializeLinearBackground();
    }
    private void initializeLinearBackground() {
        final LinearLayout linear_opinion = (LinearLayout) findViewById(R.id.linear_overdetali_opinion);
        final LinearLayout   linear_person = (LinearLayout) findViewById(R.id.linear_overdetali_person);
        mPerson.setInputType(InputType.TYPE_NULL);
        mPerson.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if(mFirst){
                        pvOptionsPerson.show();
                    }
                    linear_person.setBackgroundResource(R.drawable.bg_edittext_focused);
                } else {
                    linear_person.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });

        mOpinion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_opinion.setBackgroundResource(R.drawable.bg_edittext_focused);
                } else {
                    linear_opinion.setBackgroundResource(R.drawable.bg_edittext_normal);

                }
            }
        });

    }



    /**
     * 网络请求
     * emp_Id,cust_Id
     * is_Pass : 0 不通过 , 1 通过
     *3. 加班审批保存：{'methodname':'kq/saveExtraWorkExamStep.json','emp_Id':'','cust_Id':'','apply_Id':'','is_Pass':'','next_Approval_Man':'','memo':''}

     * @param loadEmpInfo
     */

    public void loadData(String loadEmpInfo, final int is_Pass) {
        String[] key = null;
        String[] value = null;
        if (is_Pass == 3) {
            key = new String[]{"emp_Id", "cust_Id", "apply_Id"};
            value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), Integer.toString(apply_Id)};
        } else {
            key = new String[]{"emp_Id", "cust_Id", "apply_Id", "is_Pass", "next_Approval_Man", "memo"};
            value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), Integer.toString(apply_Id)
                    , Integer.toString(is_Pass), approval_Man, mOpinion.getText().toString()};
        }

        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        if (is_Pass == 3) {
                            OverDetailBean overDetailBean = gson.fromJson(jsonObject.toString(), OverDetailBean.class);
                            List<OverDetailBean.AvailableApprovalManListBean> availableApprovalManListBean = overDetailBean.getAvailableApprovalManList();
                            loadPersonOption(availableApprovalManListBean);

                            OverDetailBean.ExtraWorkApplyBean extraWorkApply=      overDetailBean.getExtraWorkApply();
                             initValuation(extraWorkApply);
                            OverDetailBean.LastApprovalStepBean loadPersonOption = overDetailBean.getLastApprovalStep();
                            if (loadPersonOption != null ) {
                                CommonUtils.setLastApprovalResult(layout_lastapproval, loadPersonOption,null,null);
                            }
                        } else if (is_Pass == 1) {
                            CommonUtils.getMessageResult(1,jsonObject.toString(),  DetailOverActivity.this);
                        } else if (is_Pass == 0) {
                            CommonUtils.getMessageResult(0,jsonObject.toString(),  DetailOverActivity.this);

                    }


                    }
                }
        );
    }


    private void initValuation(OverDetailBean.ExtraWorkApplyBean extraWorkApplyBean) {

        mStart.setText( CommonUtils.sdf.format(extraWorkApplyBean.getBegin_Time()));
        mEnd.setText( CommonUtils.sdf.format(extraWorkApplyBean.getEnd_Time()));
        mReason.setText(extraWorkApplyBean.getReason());

    }

    private OptionsPickerView pvOptionsPerson;
    private ArrayList<ProvinceBean> optionsItems = new ArrayList<>();

    /**
     * 加载选项 pickerview
     *
     * @param listBeen
     */
    private void loadPersonOption(final List<OverDetailBean.AvailableApprovalManListBean> listBeen) {
        //选项选择器
        pvOptionsPerson = new OptionsPickerView(this);
        //选项1
        int i = 0;
        int j = 0;
        for (OverDetailBean.AvailableApprovalManListBean availableApprovalManListBean : listBeen) {
            optionsItems.add(new ProvinceBean(i++, availableApprovalManListBean.getEmp_Name(), "11", "1"));
        }

        pvOptionsPerson.setPicker(optionsItems);
        pvOptionsPerson.setTitle("请选择");
        pvOptionsPerson.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsPerson.setSelectOptions(1);
        pvOptionsPerson.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = optionsItems.get(options1).getPickerViewText();
                mPerson.setText(tx);
                approval_Man= Integer.toString(listBeen.get(options1).getEmp_Id());

            }
        });

        mFirst=true;
    }

}