package com.fesco.bookpay.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.base.BaseFragment;
import com.fesco.bookpay.entity.InformationBean;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.ProvinceBean;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gong.min on 2016/9/14.
 */
public class InformationFragment extends BaseFragment {
    private Button btnSaveInfor, btnMoadityPass;
    private Toolbar toolbar;
    private ImageView inforOption;
    private TextView inforName, inforSex;
    private EditText etMobile, etPhone, etWeixin, etMail, etAddress, et_postcode;
    private LoginEntity loginEntity;
    private String token;
    private int cust_Id, emp_Id;
    private  int gender = 1;
    private Gson gson;

    private OptionsPickerView pvOptions; //选项选择器
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();

    public static InformationFragment newInstance(LoginEntity loginEntity) {
        InformationFragment inforFragment = new InformationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("InformationFragment", loginEntity);
        inforFragment.setArguments(bundle);

        return inforFragment;

    }


    /**
     * 布局ID
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_information;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null != getArguments()) {
            loginEntity = (LoginEntity) getArguments().getSerializable("InformationFragment");
            if (loginEntity != null) {
                token = loginEntity.getToken();
                cust_Id = loginEntity.getCust_Id();
                emp_Id = loginEntity.getEmp_Id();
            }
        }

        gson = new Gson();

        loadSexOption();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        inforOption = (ImageView) view.findViewById(R.id.infor_option);
        inforName = (TextView) view.findViewById(R.id.tv_inforname);
        inforSex = (TextView) view.findViewById(R.id.tv_inforsex);
        etPhone = (EditText) view.findViewById(R.id.et_phone);
        etMobile = (EditText) view.findViewById(R.id.et_mobile);
        etWeixin = (EditText) view.findViewById(R.id.et_weixin);
        etMail = (EditText) view.findViewById(R.id.et_mail);
        etAddress = (EditText) view.findViewById(R.id.et_address);
        et_postcode = (EditText) view.findViewById(R.id.et_postcode);

        btnSaveInfor = (Button) view.findViewById(R.id.infor_save);
        btnMoadityPass = (Button) view.findViewById(R.id.infor_modify);
//（emp_Id, emp_Name, gender, mobile, phone, weixinid, email, address, zipcode)


        toolbar.setTitle("");

        setHasOptionsMenu(true);
        mActivity.setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });
        inforOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });

        btnMoadityPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(InforModifyFragment.newInstance());
            }
        });
        btnSaveInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpadte(HttpUtil.updateEmpInfo);//保存个人信息
            }
        });

        loadData(HttpUtil.loadEmpInfo);//加载个人信息
    }

    /**
     * 加载选项 pickerview
     */
    private void loadSexOption() {
        //选项选择器
        pvOptions = new OptionsPickerView(mActivity);
        //选项1
        options1Items.add(new ProvinceBean(0, "男", "广东省，以岭南东道、广南东路得名", "其他数据"));
        options1Items.add(new ProvinceBean(1, "女", "湖南省地处中国中部、长江中游，因大部分区域处于洞庭湖以南而得名湖南", "芒果TV"));

        //三级联动效果
        //     pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        pvOptions.setPicker(options1Items);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择性别");
        //  pvOptions.setCyclic(false, true, true);
        pvOptions.setCyclic(false);
        //设置默认选中的三级项目
        //监听确定选择按钮
        //    pvOptions.setSelectOptions(1, 1, 1);
        pvOptions.setSelectOptions(1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText();
//                        + options2Items.get(options1).get(option2)
//                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
                inforSex.setText(tx);
                gender=options1+1;

            }
        });


    }

    private void getUpadte(String updateEmpInfo) {
        String emp_Name = inforName.getText().toString();

        String phone = etPhone.getText().toString();
        String mobile = etMobile.getText().toString();
        String weixinid = etWeixin.getText().toString();
        String email = etMail.getText().toString();
        String address = etAddress.getText().toString();
        String zipcode = et_postcode.getText().toString();

        String[] key = new String[]{"emp_Id", "emp_Name", "gender", "phone", "mobile", "weixinid", "email", "address", "zipcode"};
        String[] value = new String[]{emp_Id + "", emp_Name, gender + "", phone, mobile, weixinid, email, address, zipcode};

        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(updateEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(updateEmpInfo, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());

                JSONObject object = null;
                try {
                    object = new JSONObject(jsonObject.toString());
                    String message = object.getString("message");
                    if ("success".equals(message)) {
                        Toast.makeText(mActivity, "修改成功", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(mActivity, "保存失败！", Toast.LENGTH_SHORT).show();
                    Logger.i(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }


    /**
     * 网络请求
     *
     * @param loadEmpInfo
     */
    public void loadData(String loadEmpInfo) {
        HashMap<String, String> map = HttpOkManagerUtils.okManagerPost(loadEmpInfo, cust_Id + "", emp_Id + "", token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());
                InformationBean informationBean = gson.fromJson(jsonObject.toString(), InformationBean.class);
                if(informationBean.getGender()==2){
                    inforSex.setText("女");
                }else   inforSex.setText("男");

                inforName.setText(informationBean.getEmp_Name());
                etMobile.setText(informationBean.getMobile());
                etPhone.setText(informationBean.getPhone());
                etWeixin.setText(informationBean.getWeixinid());
                etMail.setText(informationBean.getEmail());
                if (null != informationBean.getAddress()) {
                    etAddress.setText(informationBean.getAddress().toString());
                }
                if (null != informationBean.getZipcode()) {
                    et_postcode.setText(informationBean.getZipcode().toString());
                }


            }


        });
    }

    @Override
    public boolean onBackPressed() {
        if(pvOptions.isShowing()){
            pvOptions.dismiss();
            return true;
        }


        return false;
    }





}
