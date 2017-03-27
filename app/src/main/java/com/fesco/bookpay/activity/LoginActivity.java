package com.fesco.bookpay.activity;

import android.Manifest;
import android.app.ProgressDialog;
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
import com.fesco.bookpay.util.ACache;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.NetworkUtils;
import com.fesco.bookpay.util.SpUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.weight.dialog.PermissionDialogInfo;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


/**
 * login页面
 */
public class LoginActivity extends BasePermissionActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private static final int MSG_SET_ALIAS = 1001;
    private EditText user;
    private EditText password;
    private Button btnLogin;
    private TextView login_enroll;
    private TextView login_version;
    private TextView login_forget_passowrd;
    private Gson gs;
    public OKManager manager;
    public String DEVICE_ID = "";
    public Context context;
    public ProgressDialog   mDialog;
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
       // login_version = (TextView) findViewById(R.id.login_version);
        login_forget_passowrd = (TextView) findViewById(R.id.login_forget_passowrd);
        manager = OKManager.getInstance(this);
        gs = new Gson();


        loginOnClickListener();
        login_enroll();

        String  getVersion_Name=SpUtils.getInstance(this).getString(SpUtils.VERSION_NAME,null);
        if(getVersion_Name !=null){
          //      login_version.setText(getVersion_Name);
        }

    }



    private void loginOnClickListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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



    private void login_enroll() {
        login_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, EnrollActivity.class);
                startActivity(intent);
            }
        });
        login_forget_passowrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, EnrollActivity.class);
                intent.putExtra(EnrollActivity.FORGET_PASSWORD,true);
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
            mDialog = new ProgressDialog(this);
            mDialog.setMessage("正在登陆，请稍后...");
            mDialog.show();

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("login_Name", userStr);
            map.put("password", passwordStr);
            map.put("deviceId", DEVICE_ID);//
            map.put("deviceType", "0");
            manager.sendComplexForm(HttpUtil.login_path, map, new OKManager.Func4() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Logger.json(jsonObject.toString());  //   "ERROR": "get token error."

                    LoginEntity loginEntity = gs.fromJson(jsonObject.toString(), LoginEntity.class);

                    JSONObject object = null;
                    try {
                        object = new JSONObject(jsonObject.toString());
                        String token = object.getString("token");
                        token = token.replace("\'", "").replace("\r\n", "");
                        loginEntity.setToken(token);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Logger.i(loginEntity.toString());
                    if (loginEntity != null) {

                        if (!TextUtils.isEmpty(loginEntity.getToken())) {
                            ACache aCache = ACache.get(LoginActivity.this);
                            aCache.put("loginEntity", loginEntity);
                            Calendar calendar = Calendar.getInstance();
                            int day = calendar.get(Calendar.DATE);//获取日
                            aCache.put("newDay", Integer.toString(day));

                            String alias = Integer.toString(loginEntity.getEmp_Id());
                            //调用JPush API设置Alias   别名以 emp_Id
                            mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
                            String password = loginEntity.getLogin_Password().replace("\'", "").replace("\r\n", "");
                            String rid = JPushInterface.getRegistrationID(getApplicationContext());

                            Log.e("Fragment", "getRegistrationID:"+rid);

                            signIn(loginEntity.getEmp_Id(),password);



                            mDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("SUCCESS", loginEntity);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();

                        } else {
                            mDialog.dismiss();
                            AppToast.makeShortToast(LoginActivity.this, "用户名或密码错误");

                        }
                    }

                }
            });
        } else {
            AppToast.makeShortToast(LoginActivity.this, "用户名或密码不能为空！");
        }


    }










    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
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



    /**
     * 登录方法
     */
    private void signIn(int  emp_id,String password ) {


        String username="zrfesco_"+Integer.toString(emp_id);
        if ( TextUtils.isEmpty(password)) {
            return;
        }
        EMClient.getInstance().login(username,password , new EMCallBack() {
            /**
             * 登陆成功的回调
             */
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        // 加载所有会话到内存
                        EMClient.getInstance().chatManager().loadAllConversations();

                        // 加载所有群组到内存，如果使用了群组的话
                        // EMClient.getInstance().groupManager().loadAllGroups();
                        Log.e("Fragment", "EMClient:onSuccess");
                    }
                });
            }

            /**
             * 登陆错误的回调
             * @param i
             * @param s
             */
            @Override
            public void onError(final int i, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        Log.d("lzan13", "登录失败 Error code:" + i + ", message:" + s);
                        /**
                         * 关于错误码可以参考官方api详细说明
                         * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1_e_m_error.html
                         */
                        switch (i) {
                            // 网络异常 2
                            case EMError.NETWORK_ERROR:
                                //      Toast.makeText(context, "网络错误 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                Log.e("Fragment", "网络错误 code: " + i + ", message:" + s);
                                break;
                            // 无效的用户名 101
                            case EMError.INVALID_USER_NAME:
                                //    Toast.makeText(context, "无效的用户名 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                Log.e("Fragment", "无效的用户名 code: " + i + ", message:" + s);
                                break;
                            // 无效的密码 102
                            case EMError.INVALID_PASSWORD:
                                //     Toast.makeText(context, "无效的密码 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                Log.e("Fragment", "无效的密码 code: " + i + ", message:" + s);
                                break;
                            // 用户认证失败，用户名或密码错误 202
                            case EMError.USER_AUTHENTICATION_FAILED:
                                //   Toast.makeText(context, "用户认证失败，用户名或密码错误 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                Log.e("Fragment", "用户认证失败 code: " + i + ", message:" + s);
                                break;
                            // 用户不存在 204
                            case EMError.USER_NOT_FOUND:
                                //   Toast.makeText(context, "用户不存在 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                Log.e("Fragment", "用户不存在 code: " + i + ", message:" + s);
                                break;
                            // 无法访问到服务器 300
                            case EMError.SERVER_NOT_REACHABLE:
                                //    Toast.makeText(context, "无法访问到服务器 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                Log.e("Fragment", "无法访问到服务器 code: " + i + ", message:" + s);
                                break;
                            // 等待服务器响应超时 301
                            case EMError.SERVER_TIMEOUT:
                                //     Toast.makeText(context, "等待服务器响应超时 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                Log.e("Fragment", "等待服务器响应超时 code: " + i + ", message:" + s);
                                break;
                            // 服务器繁忙 302
                            case EMError.SERVER_BUSY:
                                //   Toast.makeText(context, "服务器繁忙 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                Log.e("Fragment", "服务器繁忙 code: " + i + ", message:" + s);
                                break;
                            // 未知 Server 异常 303 一般断网会出现这个错误
                            case EMError.SERVER_UNKNOWN_ERROR:
                                //    Toast.makeText(context, "未知的服务器异常 code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                Log.e("Fragment", "未知的服务器异常 code: " + i + ", message:" + s);
                                break;
                            default:
                                //  Toast.makeText(context, "ml_sign_in_failed code: " + i + ", message:" + s, Toast.LENGTH_LONG).show();
                                Log.e("Fragment", "ml_sign_in_failed code: " + i + ", message:" + s);
                                break;
                        }
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }



















}
