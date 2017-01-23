package com.fesco.bookpay.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.ProvinceBean;
import com.fesco.bookpay.entity.approvalbean.CheckDetailBean;
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
public class DetailCheckActivity extends BaseActivity {
    private Toolbar toolbar;
    private LoginEntity loginEntity;
    int emp_Id;
    int cust_Id;
    int apply_Id;
    String approval_Man = "";
    String token;
    private View layout_lastapproval;

    private TextView textView;
    private Button btnAagree;
    private Button btnDisAagree;

    private TextView mType;
    private TextView mCheckTime;
    private TextView mAddress;
    private TextView mApproval;
    private TextView mMomo;
    private EditText mOpinion;
    private EditText mPerson;
    private boolean mFirst; //初始化默认不弹出
    private Gson gson;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tab_approval_checkdetail);
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        apply_Id = bundle.getInt("Apply_Id");
        loginEntity = (LoginEntity) bundle.getSerializable("DetailCheckActivity");
        if (loginEntity != null) {
            emp_Id = loginEntity.getEmp_Id();
            cust_Id = loginEntity.getCust_Id();
            token = loginEntity.getToken();
        }

        initViews();
        loadData(HttpUtil.getSignLaterExamInfo, 3);
    }

    private void initViews() {
        gson = new Gson();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView) findViewById(R.id.toolbar_text);
        btnAagree = (Button) findViewById(R.id.checkdetail_agree);
        btnDisAagree = (Button) findViewById(R.id.checkdetail_disagree);

        mType = (TextView) findViewById(R.id.checkdetail_type);

        mCheckTime = (TextView) findViewById(R.id.checkdetail_checktime);
        mAddress = (TextView) findViewById(R.id.checkdetail_address);
        mApproval = (TextView) findViewById(R.id.checkdetail_approval);
        mMomo = (TextView) findViewById(R.id.checkdetail_momo);
        mOpinion = (EditText) findViewById(R.id.checkdetail_opinion);
        mPerson = (EditText) findViewById(R.id.checkdetail_person);
        layout_lastapproval = this.findViewById(R.id.layout_lastapproval);
        initializeLinearBackground();
        textView.setText("请假审批");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailCheckActivity.this.finish();
            }
        });

        btnAagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(HttpUtil.saveSignLaterExamStep, 1);
            }
        });
        btnDisAagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(HttpUtil.saveSignLaterExamStep, 0);

            }
        });
        mPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("setOnClickListener");
                pvOptionsPerson.show();
            }
        });
        //按钮的touch触摸事件
        mOpinion.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: //按下的动作
                        Log.d("Fragment", "btn is MotionEvent.ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE: //滑动的动作
                        Log.d("Fragment", "btn is MotionEvent.ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP: //离开的动作
                        Log.d("Fragment", "btn is MotionEvent.ACTION_UP");
                        break;
                }

                return false;  //默认的返回值
            }
        });

    }

    private void initializeLinearBackground() {
        final LinearLayout linear_opinion = (LinearLayout) findViewById(R.id.linear_checkdetali_opinion);
        final LinearLayout linear_person = (LinearLayout) findViewById(R.id.linear_checkdetali_person);
        mPerson.setInputType(InputType.TYPE_NULL);
        mPerson.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                System.out.println("  -CCCCCCccc-mPerson---onFocusChange-------hasFocus->>>"+hasFocus);
                if (hasFocus) {
                    if (mFirst) {
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
     * 补签审批保存：{'methodname':'kq/saveSignLaterExamStep.json','emp_Id':'','cust_Id':'','apply_Id':'','is_Pass':'','next_Approval_Man':'','memo':''}
     *
     * @param loadEmpInfo
     */

    public void loadData(String loadEmpInfo, final int type) {
        String[] key = null;
        String[] value = null;
        if (type == 3) {
            key = new String[]{"emp_Id", "cust_Id", "apply_Id"};
            value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), Integer.toString(apply_Id)};
        } else {
            key = new String[]{"emp_Id", "cust_Id", "apply_Id", "is_Pass", "next_Approval_Man", "memo"};
            value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), Integer.toString(apply_Id)
                    , Integer.toString(type), approval_Man, mOpinion.getText().toString()};
        }

        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());
                if (type == 3) {
                    CheckDetailBean checkDetailBean = gson.fromJson(jsonObject.toString(), CheckDetailBean.class);
                    List<CheckDetailBean.AvailableApprovalManListBean> availableApprovalManListBean = checkDetailBean.getAvailableApprovalManList();
                    loadPersonOption(availableApprovalManListBean);
                    CheckDetailBean.ApplyBean apply = checkDetailBean.getApply();
                    initValuation(apply);
                    CheckDetailBean.LastApprovalStepBean loadPersonOption = checkDetailBean.getLastApprovalStep();
                    if (checkDetailBean.getLastApprovalStep() != null) {
                        CommonUtils.setLastApprovalResult(layout_lastapproval, null,loadPersonOption,null);
                    }

                } else if (type == 1) {
                    CommonUtils.getMessageResult(1, jsonObject.toString(), DetailCheckActivity.this);
                } else if (type == 0) {
                    CommonUtils.getMessageResult(0, jsonObject.toString(), DetailCheckActivity.this);

                }
            }
        });


    }


    private void initValuation(CheckDetailBean.ApplyBean applyBean) {
        int checkType = applyBean.getCheck_Type();
        String mCheckType = "";
        if (1 == checkType) {
            mCheckType = "签到";
        } else if (2 == checkType) {
            mCheckType = "签退";
        } else if (3 == checkType) {
            mCheckType = "外勤";
        }
        mType.setText(mCheckType);
        mCheckTime.setText( CommonUtils.sdf.format(applyBean.getCheck_Time()));
        mApproval.setText( CommonUtils.sdf.format(applyBean.getApply_Date()));
        mAddress.setText(applyBean.getCust_Addr());
        mMomo.setText(applyBean.getMemo());


    }

    private OptionsPickerView pvOptionsPerson;
    private ArrayList<ProvinceBean> optionsItems = new ArrayList<>();

    /**
     * 加载选项 pickerview
     *
     * @param listBeen
     */
    private void loadPersonOption(final List<CheckDetailBean.AvailableApprovalManListBean> listBeen) {
        //选项选择器
        pvOptionsPerson = new OptionsPickerView(this);
        //选项1
        int i = 0;
        for (CheckDetailBean.AvailableApprovalManListBean availableApprovalManListBean : listBeen) {
            optionsItems.add(new ProvinceBean(i++, availableApprovalManListBean.getEmp_Name(), "11", "1"));
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