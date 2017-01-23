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
import com.fesco.bookpay.entity.approvalbean.RestDetailBean;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.CommonUtils;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 加班页面
 * Created by gong.min on 2016/9/29.
 */
public class DetailRestActivity extends BaseActivity {
    private Toolbar toolbar;
    private LoginEntity loginEntity;
    private int emp_Id;
    private int cust_Id;
    private int holEmpExamId;
    private String approval_Man="";
    private  String token;
    private  View   layout_lastapproval;
    private TextView textView;
    private Button btnAagree;
    private Button btnDisAagree;

    private TextView mType;
    private TextView mStart;
    private TextView mEnd;
    private TextView mPerson;
    private EditText mOpinion;
    private TextView mPatch;
    private Gson gson;
    private Context mContext;

    private boolean mFirst; //初始化默认不弹出
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tab_approval_restdetail);
        Bundle bundle = getIntent().getExtras();
        holEmpExamId = bundle.getInt("holEmpExamId");
        loginEntity = (LoginEntity) bundle.getSerializable("DetailRestActivity");
        if (loginEntity != null) {
            emp_Id = loginEntity.getEmp_Id();
            cust_Id = loginEntity.getCust_Id();
            token = loginEntity.getToken();
        }

        initViews();
        loadData(HttpUtil.getHolEmpExam, 3);
    }

    private void initViews() {
        mContext=this;
        gson = new Gson();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView) findViewById(R.id.toolbar_text);
        btnAagree = (Button) findViewById(R.id.restdetail_agree);
        btnDisAagree = (Button) findViewById(R.id.restdetail_disagree);

        mType = (TextView) findViewById(R.id.restdetail_type);
        mStart = (TextView) findViewById(R.id.restdetail_startime);
        mEnd = (TextView) findViewById(R.id.restdetail_endtime);
        mPerson = (TextView) findViewById(R.id.restdetail_person);
        mOpinion = (EditText) findViewById(R.id.restdetail_momo);
        mPatch = (TextView) findViewById(R.id.restdetail_patch);

        layout_lastapproval=this.findViewById(R.id.layout_lastapproval);
        initializeLinearBackground();


        textView.setText("请假审批");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailRestActivity.this.finish();
            }
        });

        btnAagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   loadData(HttpUtil.saveHolEmpExamStep, 1);

            }
        });
        btnDisAagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(HttpUtil.saveHolEmpExamStep, 0);
            }
        });
        mPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  loadData(HttpUtil.saveHolEmpExamStep, 0);
                pvOptionsPerson.show();
            }
        });


    }

    private void initializeLinearBackground() {
        final LinearLayout linear_opinion = (LinearLayout) findViewById(R.id.linear_restdetali_momo);
        final LinearLayout   linear_person = (LinearLayout) findViewById(R.id.linear_restdetali_patch);
        mPatch.setInputType(InputType.TYPE_NULL);
        mPatch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
     * type : 0 不通过 , 1 通过
     * 请假审批保存：{'methodname':'kq/saveHolEmpExamStep.json','emp_Id':'','cust_Id':'','holEmpExamId':'','type':'','next_Approval_Man':'','momo':''}
     *
     * @param loadEmpInfo
     */

    public void loadData(String loadEmpInfo, final int type) {
        String[] key = null;
        String[] value = null;
        if (type == 3) {
            key = new String[]{"emp_Id", "cust_Id", "holEmpExamId"};
            value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), Integer.toString(holEmpExamId)};
        } else {
            key = new String[]{"emp_Id", "cust_Id", "holEmpExamId", "type", "next_Approval_Man", "momo"};
            value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), Integer.toString(holEmpExamId)
                    , Integer.toString(type), approval_Man,  mOpinion.getText().toString()};
        }

        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        if (type == 3) {
                            RestDetailBean restDetailBean = gson.fromJson(jsonObject.toString(), RestDetailBean.class);
                            List<RestDetailBean.AvailableApprovalManListBean> availableApprovalManListBean = restDetailBean.getAvailableApprovalManList();
                            loadPersonOption(availableApprovalManListBean);

                            RestDetailBean.HolEmpExamBean holEmpExamBean=      restDetailBean.getHolEmpExam();
                            initValuation(holEmpExamBean);
                            RestDetailBean.LastApprovalStepBean loadPersonOption = restDetailBean.getLastApprovalStep();
                            if (loadPersonOption != null) {
                                CommonUtils.setLastApprovalResult(layout_lastapproval, null, null, loadPersonOption);
                            }
                            } else if (type == 1) {

                            CommonUtils.getMessageResult(1,jsonObject.toString(),DetailRestActivity.this);
                        } else if (type == 0) {
                            CommonUtils.getMessageResult(0,jsonObject.toString(),DetailRestActivity.this);
                        }


                    }
                }
        );
    }

    private void getMessageResult(int count,String jsonObject) {

        JSONObject   object = null;
        try {
            object = new JSONObject(jsonObject.toString());
            String message = object.getString("message");
            if ("success".equals(message) ) {
                if(1==count){
                    AppToast.showShortText(DetailRestActivity.this, "同意成功");
                }else
                AppToast.showShortText(DetailRestActivity.this, "驳回成功");
            }else{
                AppToast.showShortText(DetailRestActivity.this, message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void initValuation(RestDetailBean.HolEmpExamBean holEmpExamBean) {
        if(holEmpExamBean.getHol_Name()!=null){
            mType.setText(holEmpExamBean.getHol_Name().toString());
        }
//        if(holEmpExamBean.getHol_Source()!=null){
//            mResult.setText(holEmpExamBean.getHol_Source().toString());
//        }
        mPerson.setText(holEmpExamBean.getMomo());

        mStart.setText( CommonUtils.sdf.format(holEmpExamBean.getHol_Begin()));
        mEnd.setText( CommonUtils.sdf.format(holEmpExamBean.getHol_End()));
    }

    private OptionsPickerView pvOptionsPerson;
    private ArrayList<ProvinceBean> optionsItems = new ArrayList<>();

    /**
     * 加载选项 pickerview
     *
     * @param listBeen
     */
    private void loadPersonOption(final List<RestDetailBean.AvailableApprovalManListBean> listBeen) {
        //选项选择器
        pvOptionsPerson = new OptionsPickerView(this);
        //选项1
        int i = 0;
        int j = 0;
        for (RestDetailBean.AvailableApprovalManListBean availableApprovalManListBean : listBeen) {
            optionsItems.add(new ProvinceBean(i++, availableApprovalManListBean.getEmp_Name(), "11", "1"));
        }

        pvOptionsPerson.setPicker(optionsItems);
        pvOptionsPerson.setTitle("请选择");
        pvOptionsPerson.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsPerson.setSelectOptions(1);
        mFirst = true;
        pvOptionsPerson.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = optionsItems.get(options1).getPickerViewText();
                mPatch.setText(tx);
                approval_Man= Integer.toString(listBeen.get(options1).getEmp_Id());

            }
        });
    }

}