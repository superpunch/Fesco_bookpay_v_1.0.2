package com.fesco.bookpay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.NetworkUtils;
import com.fesco.bookpay.util.StringUtils;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 修改密码页面
 *
 * Created by gong.min on 2016/9/20.
 */
public class InforPswdActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText edtOldpassword;
    private EditText edtNewpassword;
    private EditText edtconfirm;
    private Button btnsave;

    private LoginEntity loginEntity;
    private String token;
    private int cust_Id, emp_Id;
    private boolean falg = false;
    private String newPassword;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_modify_password);
        Bundle bundle = getIntent().getExtras();

            loginEntity = (LoginEntity) bundle.getSerializable("InformationActivity");
            if (loginEntity != null) {
                token = loginEntity.getToken();
              //  cust_Id = loginEntity.getCust_Id();
                emp_Id = loginEntity.getEmp_Id();
        }

        initView();
    }


    protected void initView() {
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("修改密码");
        toolbar.setTitle("");
         this.setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        edtOldpassword = (EditText) this.findViewById(R.id.infor_oldpassword);
        edtNewpassword = (EditText) this.findViewById(R.id.infor_newpassword);
        edtconfirm = (EditText) this.findViewById(R.id.infor_confirm);
        btnsave = (Button) this.findViewById(R.id.infor_savepassword);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitData();
            }


        });

    }



    private void commitData() {


        String oldPassword = edtOldpassword.getText().toString();
        newPassword = edtNewpassword.getText().toString();
        String confirm = edtconfirm.getText().toString();
        if (!NetworkUtils.isNetworkConnected(this)) {
            Toast.makeText(getApplicationContext(), "请连接网络~", Toast.LENGTH_SHORT).show();
        } else if (!StringUtils.isEmpty(oldPassword, newPassword, confirm)) {
            Toast.makeText(getApplicationContext(), "请输入所有密码哦！", Toast.LENGTH_SHORT).show();
        } else if (!newPassword.equals(confirm)) {
            //校验两次密码是否一致
            Toast.makeText(getApplicationContext(), "两次密码不一致哦！", Toast.LENGTH_SHORT).show();

        } else {
            loadData(HttpUtil.validatePswd, "oldPswd", oldPassword);
            Logger.i("falg 进来了有多少1：" + falg);


        }


    }


    /**
     * 网络请求
     *
     * @param path      路径
     * @param paramPwsd 密码key
     * @param password  密码value
     */
    public void loadData(String path, String paramPwsd, String password) {
//        ACache aCache = ACache.get(this);
//        loginEntity = (LoginEntity) aCache.getAsObject("loginEntity");
//        if (loginEntity != null) {
//            token = loginEntity.getToken();
//            cust_Id = loginEntity.getCust_Id();
//            emp_Id = loginEntity.getEmp_Id();
//
//        }
        //  initdata();
        HashMap<String, String> map = HttpOkManagerUtils.okManagerPostPwsd(path, paramPwsd, password, emp_Id + "", token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(path, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {


                Logger.json(jsonObject.toString());
                try {
                    JSONObject object = new JSONObject(jsonObject.toString());
                    String message = object.getString("message");

                    Logger.i(message);

                    if ("success".equals(message)) {

                        if(!falg){
                            falg = true;
                            loadData(HttpUtil.modifyPswd, "newPswd", newPassword);//提交新密码

                        }else  Toast.makeText(getApplicationContext(), "密码已修改！", Toast.LENGTH_SHORT).show();

                    } else {

                        if(falg){
                            Toast.makeText(getApplicationContext(), "保存失败！", Toast.LENGTH_SHORT).show();
                            falg=false;
                        }else{
                            Toast.makeText(getApplicationContext(), "旧密码输入错误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        });
    }

}


