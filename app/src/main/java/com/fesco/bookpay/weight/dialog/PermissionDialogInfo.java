package com.fesco.bookpay.weight.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;

/**
 * Created by gong.min on 2017/1/16.
 */
public class PermissionDialogInfo extends  AlertDialog {

    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    Context context;
    public  String message;
    public PermissionDialogInfo(Context context) {
        super(context);
       this.context=context;
    }

    public PermissionDialogInfo(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public PermissionDialogInfo(Context context, int themeResId) {
        super(context, themeResId);
    }
    public void setMessage(String msg) {
        message = msg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_permission_info);
        TextView tv_title = (TextView) this.findViewById(R.id.tv_dialog_title);
        tv_title.setText("权限提示");
        TextView tv_message = (TextView) this.findViewById(R.id.tv_dialog_message);
    //    tv_message.setText("当前应用缺少必要权限。请点击设置-权限-打开-"+message+"权限。\n点击后退按钮，即可返回。");
        tv_message.setText(message);
        TextView tv_setting = (TextView) this.findViewById(R.id.tv_dialog_setting);
        TextView tv_exit = (TextView) this.findViewById(R.id.tv_dialog_exit);
        tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse(PACKAGE_URL_SCHEME + context.getPackageName()));
                context.startActivity(intent);

            }
        });
        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }






}
