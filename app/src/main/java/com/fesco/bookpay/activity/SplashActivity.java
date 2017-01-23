package com.fesco.bookpay.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.util.ACache;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by gong.min on 2016/10/21.
 */
public class SplashActivity extends AppCompatActivity {
    Handler handler = new Handler();
    public String splashTime;
    public LoginEntity loginEntity;
    public ACache aCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        aCache = ACache.get(this);
        loginEntity = (LoginEntity) aCache.getAsObject("loginEntity");
        splashTime = aCache.getAsString("newDay");
        handler.postDelayed(runnable, 1500);

        loadToken();



    }

    private void loadToken() {

        if (loginEntity != null  && splashTime !=null ) {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DATE);//获取日
            if ( ! splashTime.equals(Integer.toString(day))) {
                aCache.put("newDay", Integer.toString(day));
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("token", loginEntity.getToken());
                OKManager manager = OKManager.getInstance(this);
                manager.sendComplexForm(HttpUtil.tokenKey, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        JSONObject object = null;

                        try {
                            object = new JSONObject(jsonObject.toString());
                            String message = object.getString("SUCCESS");
                            if ("success".equals(message)) {
                                ACache aCache = ACache.get(SplashActivity.this);
                                loginEntity.setToken(object.getString("token"));
                                aCache.put("loginEntity", loginEntity);
                                Log.d("Fragment", "获取新的token " + loginEntity.getToken());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        }
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if (loginEntity != null &&  splashTime !=null) {
//                if( CommonUtils.isCompare(splashTime,startTime)){
//                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else {
//                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {

                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    public void onBackPressed() {
        // super.onBackPressed(); 	不要调用父类的方法
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        startActivity(intent);

        handler.removeCallbacks(runnable);//移除回调
    }


}
