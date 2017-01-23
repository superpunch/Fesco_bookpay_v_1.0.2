package com.fesco.bookpay.fragment;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.base.BasePageFragment;
import com.fesco.bookpay.entity.AttPatchBean;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.ProvinceBean;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.StringUtils;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 补签申请
 * Created by gong.min on 2016/9/7.
 */
public class AttPatchApplicationFragment extends BasePageFragment {

    private static final String KEY = "EXTRA";
    private LoginEntity flag;
    private int emp_Id, cust_Id;
    private String token;
    private View mPathView;
    private Gson gson;

    private EditText att_type;
    private EditText att_time;
    private EditText att_address;
    private EditText att_patch;
    private EditText att_person;
    private Button btn_patch;

    private TimePickerView pvTime;
    private OptionsPickerView pvOptionsType;
    private OptionsPickerView pvOptionsAddress;
    private OptionsPickerView pvOptionsPerson;

    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ProvinceBean> options2Items = new ArrayList<>();
    private ArrayList<ProvinceBean> options3Items = new ArrayList<>();


    private int check_Type; // 1 签到,2 签退,3外勤
    private String cust_Addr;
    private String check_Time;
    private String memo;
    private String approval_Man; //名称对应的 emp_id
    private boolean mHasLoadedOnce;// 只加载一次

    private  boolean mFirst;
    public static AttPatchApplicationFragment getInstance(LoginEntity flag) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        AttPatchApplicationFragment attendanceListFragment = new AttPatchApplicationFragment();
        attendanceListFragment.setArguments(bundle);
        return attendanceListFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            flag = (LoginEntity) bundle.getSerializable(KEY);
            emp_Id = flag.getEmp_Id();
            cust_Id = flag.getCust_Id();
            token = flag.getToken();
        }

        if (mPathView == null) {
            mPathView =
                    inflater.inflate(R.layout.list_tab_attpatch, container, false);
            att_type = (EditText) mPathView.findViewById(R.id.att_type_id);
            att_time = (EditText) mPathView.findViewById(R.id.att_time_id);
            att_address = (EditText) mPathView.findViewById(R.id.att_address_id);
            att_person = (EditText) mPathView.findViewById(R.id.att_person_id);
            att_patch = (EditText) mPathView.findViewById(R.id.att_patch_id);
            btn_patch = (Button) mPathView.findViewById(R.id.btn_patch);
            gson = new Gson();
            att_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pvOptionsType.show();
                }
            });
            att_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pvTime.show();

                }
            });
            att_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pvOptionsAddress.show();
                }
            });
            att_person.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pvOptionsPerson.show();
                }
            });
            //提交补签申请
            btn_patch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subMitData();
                }
            });
            initializeLinearBackground(mPathView);

            loadTpyeOption();
            loadDateOption();
            loadAddressOption();
            Log.i("Fragment", "马丹----初始化-View---Fragment AAA   ");
        }
        Log.i("Fragment", "马丹----初始化-View---Fragment BBB   ");
        return mPathView;
    }
    private void initializeLinearBackground(View mPathView) {
        final LinearLayout linear_type = (LinearLayout)mPathView.findViewById(R.id.linear_att_type);
        final LinearLayout linear_time = (LinearLayout)mPathView.findViewById(R.id.linear_att_time);
        final LinearLayout linear_address = (LinearLayout)mPathView.findViewById(R.id.linear_att_address);
        final LinearLayout linear_patch = (LinearLayout)mPathView.findViewById(R.id.linear_att_patch);
        final LinearLayout linear_person = (LinearLayout)mPathView.findViewById(R.id.linear_att_person);
        att_type.setInputType(InputType.TYPE_NULL);
        att_time.setInputType(InputType.TYPE_NULL);
        att_address.setInputType(InputType.TYPE_NULL);
        att_person.setInputType(InputType.TYPE_NULL);
        att_type.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.i("Fragment", "setOnFocusChangeListener    "+mFirst);
                    if (mFirst) {
                        pvOptionsType.show();
                    }
                    linear_type.setBackgroundResource(R.drawable.bg_edittext_focused);
                } else {
                    linear_type.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });

        att_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_time.setBackgroundResource(R.drawable.bg_edittext_focused);
                    pvTime.show();
                } else {
                    linear_time.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });
        att_address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    pvOptionsAddress.show();
                    linear_address.setBackgroundResource(R.drawable.bg_edittext_focused);
                } else {
                    linear_address.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });
        att_patch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_patch.setBackgroundResource(R.drawable.bg_edittext_focused);

                } else {
                    linear_patch.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });
        att_person.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    pvOptionsPerson.show();
                    linear_person.setBackgroundResource(R.drawable.bg_edittext_focused);
                } else {
                    linear_person.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("3  onActivityCreated --------------");

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
         if(!isVisibleToUser){
             mFirst=false;
         }
        System.out.println("3   setUserVisibleHint ------是否可见>>>>>>>" + isVisibleToUser + "-----------");
    }

    /**
     * 可见时执行网络请求
     */
    @Override
    public void fetchData() {
        if (!mHasLoadedOnce) {
            loadData(HttpUtil.getApprovalMans);
        }


    }


    /**
     * 提交补签数据
     */
    private void subMitData() {


        cust_Addr = att_address.getText().toString();
        check_Time = att_time.getText().toString();
        memo = att_patch.getText().toString();


        Logger.i("A:" + check_Type + " B " + cust_Addr + " C " + check_Time + " D " + memo.toString() + " E " + approval_Man);
        if (StringUtils.isEmpty(memo, check_Type + "", cust_Addr) && StringUtils.isEmpty(check_Time, approval_Man)) {
            postData(HttpUtil.saveSignLater);
        } else

            AppToast.showShortText(mActivity, "请输入完整信息");
    }


    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     */

    public void loadData(String loadEmpInfo) {
        mHasLoadedOnce = true;
        String[] key = new String[]{"emp_Id", "cust_Id"};
        String[] value = new String[]{emp_Id + "", cust_Id + ""};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        AttPatchBean attPatchBean = gson.fromJson(jsonObject.toString(), AttPatchBean.class);
                        List<AttPatchBean.ApprovalManListBean> listBeen = attPatchBean.getApprovalManList();
                        loadPersonOption(listBeen);
                    }
                }
        );
    }

    /**
     * 提交数据
     * emp_Id,cust_Id,check_Type,cust_Addr,check_Time(String),memo,approval_Man(long)
     *
     * @param postUrl
     */
    public void postData(String postUrl) {
        String[] key = new String[]{"emp_Id", "cust_Id", "check_Type", "cust_Addr", "check_Time", "memo", "approval_Man"};
        String[] value = new String[]{emp_Id + "", cust_Id + "", check_Type + "", cust_Addr, check_Time, memo, approval_Man};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(postUrl, key, value, token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(postUrl, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());

                JSONObject object = null;
                try {
                    object = new JSONObject(jsonObject.toString());
                    String message = object.getString("message");
                    Logger.i(message);

                    if ("success".equals(message)) {

                        AppToast.showShortText(mActivity, "申请成功！");
                        att_type.setText("");
                        att_address.setText("");
                        att_time.setText("");
                        att_patch.setText("");
                        att_person.setText("");
                    } else {
                        AppToast.showShortText(mActivity, "服务器暂停 请稍后！");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    /**
     * 加载选项 pickerview
     */
    private void loadTpyeOption() {

        //选项选择器
        pvOptionsType = new OptionsPickerView(mActivity);
        //选项1
        options2Items.add(new ProvinceBean(0, "签到", "11", "1"));
        options2Items.add(new ProvinceBean(1, "签退", "1", "1"));
        options2Items.add(new ProvinceBean(2, "外勤", "1", "1"));
        pvOptionsType.setPicker(options2Items);
        pvOptionsType.setTitle("请选择");
        pvOptionsType.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsType.setSelectOptions(1);
        Log.e("Fragment", "loadTpyeOption    "+mFirst);
        mFirst=true;
        pvOptionsType.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options2Items.get(options1).getPickerViewText();
                att_type.setText(tx);
                check_Type = options1;

            }
        });
    }

    /**
     * 加载选项 pickerview
     */
    private void loadAddressOption() {

        //选项选择器
        pvOptionsAddress = new OptionsPickerView(mActivity);
        //选项1
        options1Items.add(new ProvinceBean(0, "外企", "11", "1"));
        options1Items.add(new ProvinceBean(1, "雨霖大夏", "1", "1"));
        options1Items.add(new ProvinceBean(2, "丹棱街5号微软大厦", "1", "1"));
        options1Items.add(new ProvinceBean(3, "东方梅地亚中心", "1", "1"));
        options1Items.add(new ProvinceBean(4, "海淀区公积金", "1", "1"));
        options1Items.add(new ProvinceBean(5, "上海中心一幢", "1", "1"));
        options1Items.add(new ProvinceBean(6, "信达资本", "1", "1"));
        options1Items.add(new ProvinceBean(7, "东方广场经贸城西2座", "1", "1"));
        options1Items.add(new ProvinceBean(8, "FESCO", "1", "1"));
        options1Items.add(new ProvinceBean(9, "微软", "1", "1"));
        options1Items.add(new ProvinceBean(10, "东三环北路8号亮马大厦", "1", "1"));
        pvOptionsAddress.setPicker(options1Items);
        pvOptionsAddress.setTitle("请选择");
        pvOptionsAddress.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsAddress.setSelectOptions(1);
        pvOptionsAddress.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = options1Items.get(options1).getPickerViewText();
                att_address.setText(tx);


            }
        });
    }

    /**
     * 加载选项 pickerview
     *
     * @param listBeen
     */
    private void loadPersonOption(final List<AttPatchBean.ApprovalManListBean> listBeen) {

        //选项选择器
        pvOptionsPerson = new OptionsPickerView(mActivity);
        //选项1
        int i = 0;
        int j = 0;
        for (AttPatchBean.ApprovalManListBean approvalManListBean : listBeen) {
            Logger.i(j++ + "");
            options3Items.add(new ProvinceBean(i++, approvalManListBean.getEmp_Name(), "11", "1"));
        }

        pvOptionsPerson.setPicker(options3Items);
        pvOptionsPerson.setTitle("请选择");
        pvOptionsPerson.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsPerson.setSelectOptions(1);
        pvOptionsPerson.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options3Items.get(options1).getPickerViewText();
                att_person.setText(tx);
                approval_Man = listBeen.get(options1).getEmp_Id();

            }
        });
    }


    /**
     * 加载时间选项 pickerview
     */
    private void loadDateOption() {

        pvTime = new TimePickerView(mActivity, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                att_time.setText(getTime(date));
            }
        });
        //弹出时间选择器
        att_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });
    }


    public String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }


}
