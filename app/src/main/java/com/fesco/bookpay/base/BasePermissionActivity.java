package com.fesco.bookpay.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.impl.PermissionListenter;

import java.util.ArrayList;
import java.util.List;

public class BasePermissionActivity extends AppCompatActivity {
    public static final int PERMISSIONS_DENIED = 1; // 权限拒绝
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    private PermissionListenter mListenter;

    public boolean isPermission;
    public void requestBasePermissions(String[] permissions, PermissionListenter permissionListenter) {
        mListenter = permissionListenter;
        List<String> permissionList = new ArrayList<>();

        for (String permission : permissions) {
            if( ContextCompat.checkSelfPermission(this, permission) ==
                    PackageManager.PERMISSION_DENIED){
         //   if (ContextCompat.checkSelfPermission(this, permission) != PermissionChecker.PERMISSION_GRANTED) {
                permissionList.add(permission);
            } else {
               Toast.makeText(this, permission + "权限已赋予！", Toast.LENGTH_SHORT).show();
           //     mListenter.onFirstGranted();
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[]{}), 2);
          //  ActivityCompat. shouldShowRequestPermissionRationale(this, permissionList.toArray(new String[]{}), 2);
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

    // 显示缺失权限提示
    public void showMissingPermissionDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.help);
        builder.setMessage(R.string.string_help_text);
        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
//                setResult(PERMISSIONS_DENIED);
//                finish();
                builder.show().dismiss();
            }
        });

        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.setCancelable(false);

        builder.show();
    }

    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }


}
