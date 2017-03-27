package com.fesco.bookpay.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fesco.bookpay.entity.approvalbean.ValidateCode;
import com.fesco.bookpay.impl.OnClickEvent;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.NetworkUtils;
import com.fesco.bookpay.util.StringUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by gong.min on 2016/10/18.
 */
public class EnrollActivity extends AppCompatActivity {

    public final static String FORGET_PASSWORD = "forget_password";
    private EditText mMail;
    private EditText mCode;
    private TextView mSendCode;
    private EditText mUser;
    private EditText mPassword;
    private EditText mcfmPassword;
    private Button btnEnroll;
    private OKManager manager;
    private Gson gson;
    private Context mContext;
    private ValidateCode vaildateCode;

    private String strMail;
    private String strCode;
    private String strUser;
    private String strPassword;
    private String strcfmPassword;
    private boolean   isForget=false;
    private String https=HttpUtil.preRegister;
    private String btn_https=HttpUtil.register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        mContext = this;

        initview();
    }

    public void initview() {

        mMail = (EditText) findViewById(R.id.enr_mail);
        mCode = (EditText) findViewById(R.id.enr_code);
        mSendCode = (TextView) findViewById(R.id.enr_sendcode);
        mUser = (EditText) findViewById(R.id.enr_user);
        mPassword = (EditText) findViewById(R.id.enr_password);
        mcfmPassword = (EditText) findViewById(R.id.enr_confirm_password);
        btnEnroll = (Button) findViewById(R.id.enr_btn);

        manager = OKManager.getInstance(this);
        gson = new Gson();
        isForget=     getIntent().getBooleanExtra(FORGET_PASSWORD,false);
        if(isForget){
            mPassword.setHint("新密码");
            btnEnroll.setText("确定重置");
            https=HttpUtil.preReset;
            btn_https=HttpUtil.reset;

        }
        enrollCode();
        enrollLoading();



    }

    /**
     * 1. 接口：​/user/preRegister.json
     * 参数：{'methodname':'user/preRegister.json','email':''}
     * 返回：ValidateCode验证码，invalid email address系统没有该员工
     */
    private void enrollCode() {

        mSendCode.setOnClickListener(new OnClickEvent(){
            @Override
            public void singleClick(View v) {
                strMail = mMail.getText().toString().trim();
                if (!StringUtils.isEmpty(strMail)) {
                    AppToast.showShortText(mContext, "请输入邮箱");
                    return;
                }
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("emailOrPhone", strMail);

                manager.sendComplexForm(https, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                         vaildateCode = gson.fromJson(jsonObject.toString(), ValidateCode.class);
                        // {"message": invalid email address}
                        if (vaildateCode.getMessage() != null) {
                            if ("invalid email address".equals(vaildateCode.getMessage())) {
                                AppToast.showShortText(mContext, "系统没有该员工");
                                // {"ValidateCode": 913084}
                            } else if ("already exist".equals(vaildateCode.getMessage())) {
                                AppToast.showShortText(mContext, "此用户已注册");
                            }

                        } else if (vaildateCode.getValidateCode() > 0) {
                            mHandler.post(runnable);
                            AppToast.showShortText(mContext, "发送成功,请查收");
                        }
                    }
                });
            }
        });
    }

    /**
     * 1.注册接口：/user/register.json
     * 参数：{'methodname':'user/register.json','email':'','login_name':'','login_password':''}
     * 2.重置接口：/user/reset.json
     * /user/reset.json?emailOrPhone='111'&login_name='aaa'&login_password='12323'
     */
    private void enrollLoading() {
        btnEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMail = mMail.getText().toString().trim();
                strCode = mCode.getText().toString().trim();
                strUser = mUser.getText().toString().trim();
                strPassword = mPassword.getText().toString().trim();
                strcfmPassword = mcfmPassword.getText().toString().trim();
                if (StringUtils.isEmpty(strMail, strCode) && StringUtils.isEmpty(strUser, strPassword,strcfmPassword)) {

                    if (!NetworkUtils.isNetworkAvailable(EnrollActivity.this)) {
                        AppToast.showShortText(EnrollActivity.this, "当前无网络~");
                        return;
                    }

                    if (!strCode.equals(String.valueOf(vaildateCode.getValidateCode()))) {
                        AppToast.showShortText(EnrollActivity.this, "验证码不匹配请重新输入");
                        return;
                    }
                    if (!strcfmPassword.equals(strPassword)) {
                        AppToast.showShortText(EnrollActivity.this, "两次输入的密码不匹配请重新输入");
                        return;
                    }

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("emailOrPhone", strMail);
                    map.put("login_name", strUser);
                    map.put("login_password", strPassword);
                    manager.sendComplexForm(btn_https, map, new OKManager.Func4() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            Logger.json(jsonObject.toString());
                            ValidateCode vaildateCode = gson.fromJson(jsonObject.toString(), ValidateCode.class);
                            // {"message": invalid email address}
                            if (vaildateCode.getMessage() != null) {
                                if ("success".equals(vaildateCode.getMessage())) {
                                    if(isForget){
                                        AppToast.showShortText(mContext, "重置成功");
                                    }else
                                    AppToast.showShortText(mContext, "注册成功");
                                    EnrollActivity.this.finish();

                                    // {"ValidateCode": 913084}
                                }
                            }
                        }
                    });
                } else {
                    AppToast.showShortText(mContext, "请输入完成信息");
                }
            }


        });
    }

    int count = 60;
    private Handler mHandler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (count == 0) {
                mSendCode.setText("发送验证码");
                mSendCode.setTextColor(Color.parseColor("#ffffff"));
                mSendCode.setClickable(true);
                mHandler.removeCallbacks(runnable);
                count = 60;

            } else {
                count--;
                mSendCode.setText("(" + count + "S)");
                mSendCode.setTextColor(Color.parseColor("#ffffff"));
                mSendCode.setClickable(false);
                mHandler.postDelayed(this, 1000);

            }
        }
    };

//    private  class EnrollHandler extends Handler {
//
//        private WeakReference<EnrollActivity> weakReference;
//
//        EnrollHandler(EnrollActivity activity) {
//            weakReference = new WeakReference<>(activity);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            EnrollActivity enrollActivity=   weakReference.get();
//             if(enrollActivity ==null || enrollActivity.isFinishing()){
//                 return;
//             }
//            switch (msg.what){
//
//                case 1:
//                    mSendCode.setText("");
//
//
//
//                    break;
//            }
//        }
//    }'


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
    }

}
