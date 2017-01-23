package com.fesco.bookpay.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.impl.PermissionListenter;
import com.fesco.bookpay.util.ACache;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gong.min on 2016/9/13.
 */
public  class BaseActivity extends AppCompatActivity {

    public static final int PERMISSIONS_DENIED = 1; // 权限拒绝
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    public ACache aCache;
    public LoginEntity loginEntity;
    public String token;
    public int emp_Id;
    public int cust_Id;
    public Gson gson;
    public Context context;
    private PermissionListenter mListenter;
    public boolean isPermission;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        gson=new Gson();
        aCache = ACache.get(this);
        loginEntity = (LoginEntity) aCache.getAsObject("loginEntity");
        emp_Id=loginEntity.getEmp_Id();
        cust_Id=loginEntity.getCust_Id();
        token=loginEntity.getToken();
        context=this;


//        Bundle bundle = getIntent().getExtras();
//        intentEntity = (LoginEntity) bundle.getSerializable("SUCCESS");
//        if (intentEntity != null) {
//            token = intentEntity.getToken();
//            emp_Id = intentEntity.getCust_Id();
//            cust_Id = intentEntity.getCust_Id();
//
//        }

//        initVariables();
//        initViews(savedInstanceState);
//        loadData();

    }

//    public abstract void initVariables();
//
//    public abstract void initViews(Bundle savedInstanceState);
//
//    public abstract void loadData();


    public void requestBasePermissions(String[] permissions, PermissionListenter permissionListenter) {
        mListenter = permissionListenter;
        List<String> permissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PermissionChecker.PERMISSION_GRANTED) {
                permissionList.add(permission);
            } else {
                  // Toast.makeText(this, permission + "权限已赋予！", Toast.LENGTH_SHORT).show();

            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[]{}), 2);
        }else  isPermission=true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                if (grantResults.length > 0) {
                    List<String> dendiedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PermissionChecker.PERMISSION_GRANTED) {
                            dendiedPermissions.add(permission);
                        }

                    }
                    if(dendiedPermissions.isEmpty()){
                        mListenter.onGranted();
                    }else {
                        mListenter.onDenied(dendiedPermissions);
                    }
                }
                break;
        }
    }








}
