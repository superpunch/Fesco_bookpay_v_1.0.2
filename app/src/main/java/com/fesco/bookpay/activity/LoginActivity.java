package com.fesco.bookpay.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fesco.bookpay.base.BasePermissionActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.impl.PermissionListenter;
import com.fesco.bookpay.util.ACache;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.NetworkUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.weight.dialog.PermissionDialogInfo;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


/**
 * login页面
 */
public class LoginActivity extends BasePermissionActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    private String login_pathc = "http://api2.hichao.com/stars?category=%E5%85%A8%E9%83%A8&pin=&ga=%2Fstars&flag=&gv=63&access_token=&gi=862949022047018&gos=5.2.3&p=2013022&gc=xiaomi&gn=mxyc_adr&gs=720x1280&gf=android&page=2";
    private EditText user;
    private EditText password;
    private Button btnLogin;
    private TextView login_enroll;
    private Gson gs;
    public OKManager manager;
    public String DEVICE_ID = "";
    public Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpay);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        Logger.init("LoginActivity");
        context = this;
        initview();


    }

    public void initview() {

        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.buttonlogin);
        login_enroll = (TextView) findViewById(R.id.login_enroll);
        manager = OKManager.getInstance(this);
        gs = new Gson();
        //operaTionPermission();


        loginOnClickListener();
        login_enroll();
    }

    private void loginOnClickListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(isPermission){
//                    Log.e("Fragment", "isPermission"+isPermission);
//                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//                    DEVICE_ID = tm.getDeviceId();
//                    loginLoading();//权限已开
//                }else


                //    operaTionPermission();
                Log.e("Fragment", "onFirstGranted");
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PermissionChecker.PERMISSION_GRANTED) {
                    PermissionDialogInfo permissionDialogInfo = new PermissionDialogInfo(context);
                    permissionDialogInfo.setMessage("电话和手机信息权限");
                    permissionDialogInfo.show();
                } else {


                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    DEVICE_ID = tm.getDeviceId();
                    if (TextUtils.isEmpty(DEVICE_ID)) {
                        PermissionDialogInfo permissionDialogInfo = new PermissionDialogInfo(context);
                        permissionDialogInfo.setMessage("电话和手机信息权限");
                        permissionDialogInfo.show();
                    } else
                        loginLoading();//权限已开

                }
            }

        });

    }

    public void operaTionPermission() {
        String[] permissions = new String[]{
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE
        };
        requestBasePermissions(permissions, new PermissionListenter() {
            @Override
            public void onGranted() {
//                TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//                DEVICE_ID = tm.getDeviceId();
                Log.e("Fragment", "onGranted");
                loginLoading();
            }



            @Override
            public void onDenied(List<String> denied) {
                for (String permission : denied) {
                    AppToast.makeShortToast(LoginActivity.this, "被拒绝的权限：" + permission);
                }
                showMissingPermissionDialog();


            }
        });
    }

    private void login_enroll() {
        login_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, EnrollActivity.class);
                startActivity(intent);
            }
        });

    }


    private void loginLoading() {
        String userStr = user.getText().toString().trim();
        String passwordStr = password.getText().toString().trim();
        if (userStr != null && userStr.length() != 0 && passwordStr != null && passwordStr.length() != 0) {
            if (!NetworkUtils.isNetworkAvailable(LoginActivity.this)) {
                Toast.makeText(LoginActivity.this, "当前无网络~", Toast.LENGTH_LONG).show();
                return;
            }
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("login_Name", userStr);
            map.put("password", passwordStr);
//            Log.e("Fragment", DEVICE_ID);
            map.put("deviceId", DEVICE_ID);//
            map.put("deviceType", "0");
            Log.e("Fragment", "-----------map: " + map.toString());
            manager.sendComplexForm(HttpUtil.login_path, map, new OKManager.Func4() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Logger.json(jsonObject.toString());  //   "ERROR": "get token error."
                    Log.e("Fragment", "DEVICE_ID:" + DEVICE_ID);
                    LoginEntity loginEntity = gs.fromJson(jsonObject.toString(), LoginEntity.class);

                    JSONObject object = null;
                    try {
                        object = new JSONObject(jsonObject.toString());
                        Log.i("Fragment", "-object--Token: " + object.getString("token"));
                        String token = object.getString("token");
                        token = token.replace("\'", "").replace("\r\n", "");
                        Log.i("Fragment", "-新的--Token: " + token);
                        loginEntity.setToken(token);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Logger.i(loginEntity.toString());
                    if (loginEntity != null) {

                        if (!TextUtils.isEmpty(loginEntity.getToken())) {
                            ACache aCache = ACache.get(LoginActivity.this);
                            aCache.put("loginEntity", loginEntity);
                            Log.i("Fragment", "-put--Token: " + loginEntity.getToken());
                            //  aCache.put("loginEntity", loginEntity,ACache.TIME_DAY);
                            Calendar calendar = Calendar.getInstance();
                            int day = calendar.get(Calendar.DATE);//获取日
                            aCache.put("newDay", Integer.toString(day));

                            String  alias=Integer.toString(loginEntity.getEmp_Id());
                            //调用JPush API设置Alias   别名以 emp_Id
                            mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS,alias ));

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("SUCCESS", loginEntity);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            AppToast.makeShortToast(LoginActivity.this, "用户名或密码错误");
                        }
                    }

                }
            });
        } else {
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);

            AppToast.makeShortToast(LoginActivity.this, "用户名或密码不能为空！");
        }


    }



    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;


    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:;
                    Log.d(TAG, "Set alias in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
                    break;
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    break;

                case 6002://设置超时	建议重试
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    if (NetworkUtils.isNetworkAvailable(LoginActivity.this)) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                        Log.i(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }
        }

    };
}
