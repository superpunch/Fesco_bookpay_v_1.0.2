package com.fesco.bookpay.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.base.BasePageBackFragment;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.OverPatchPerBean;
import com.fesco.bookpay.entity.ProvinceBean;
import com.fesco.bookpay.impl.OnClickEvent;
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
 * 加班申请
 * Created by gong.min on 2016/9/7.
 */
public class OvertimeApplicationFragment extends BasePageBackFragment {

    private static final String KEY = "EXTRA";
    private LoginEntity flag;
    private int emp_Id, cust_Id;
    private String token;
    private View mPathView;
    private Gson gson;

//    private LinearLayout linear_star;
//    private LinearLayout linear_end;
//    private LinearLayout linear_overtime;
//    private LinearLayout linear_unit;
//   // private LinearLayout linear_reason;
//    private LinearLayout linear_person;


    private EditText over_starttime;
    private EditText over_endtime;
    private EditText over_duration; //时长
    private EditText over_unit; //加班单位
    private EditText over_reason;
    private EditText over_person;
    private Button btn_overtime;


    private TimePickerView pvStartTime;
    private TimePickerView pvEndTime;
    private OptionsPickerView pvOptionsUnit;
    private OptionsPickerView pvOptionsPerson;

    private ArrayList<ProvinceBean> options3Items = new ArrayList<>();
    private ArrayList<ProvinceBean> options2Items = new ArrayList<>();

    private int mUnit; // 1 天 , 2 小时, 3 半天

    private String begin_Time;
    private String end_Time;
    private String work_Duration;
    private String time_Unit;
    private String reason;
    private String person; //审批人
    private String approval_Man; //审批人 对应的 emp_id
    private boolean mHasLoadedOnce;// 只加载一次

    private boolean mFirst;// 初始化不加载

    public static OvertimeApplicationFragment getInstance(LoginEntity flag) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        OvertimeApplicationFragment attendanceListFragment = new OvertimeApplicationFragment();
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
                    inflater.inflate(R.layout.list_tab_overapplica, container, false);

            over_starttime = (EditText) mPathView.findViewById(R.id.over_starttime);
            over_endtime = (EditText) mPathView.findViewById(R.id.over_endtime);
            over_duration = (EditText) mPathView.findViewById(R.id.over_duration);
            over_unit = (EditText) mPathView.findViewById(R.id.over_unit);
            over_reason = (EditText) mPathView.findViewById(R.id.over_reason_id);
            over_person = (EditText) mPathView.findViewById(R.id.over_person_id);
            btn_overtime = (Button) mPathView.findViewById(R.id.btn_overtime);


            gson = new Gson();

            over_starttime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFirst = true;
                    pvStartTime.show();


                }
            });
            over_endtime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pvEndTime.show();

                }
            });

            over_unit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pvOptionsUnit.show();
                }
            });

            over_person.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pvOptionsPerson.show();
                }
            });
            //提交补签申请
            btn_overtime.setOnClickListener(new OnClickEvent() {
                @Override
                public void singleClick(View v) {
                    subMitData();
                }
            });
            initializeLinearBackground(mPathView);
            dateTpyeOption();
            startOption();
            endOption();
         //   editSetOnlistener();
        }

        return mPathView;
    }

    private  void editSetOnlistener(){
        over_starttime.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == KeyEvent.ACTION_UP) {
                    //关闭软键盘
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(over_starttime.getWindowToken(), 0);
                    //使得根View重新获取焦点，以监听返回键
                    getFocus();
                }
                return false;
            }
        });

        over_person.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == KeyEvent.ACTION_UP) {
                    //关闭软键盘
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(over_person.getWindowToken(), 0);
                    //使得根View重新获取焦点，以监听返回键
                    getFocus();
                }
                return false;
            }
        });

    }

    private void getFocus() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                }

                return false;
            }
        });


    }


    private void initializeLinearBackground(View mPathView) {
        final LinearLayout linear_star = (LinearLayout) mPathView.findViewById(R.id.linear_over_start);
        final LinearLayout linear_end = (LinearLayout) mPathView.findViewById(R.id.linear_over_end);
        final LinearLayout linear_overtime = (LinearLayout) mPathView.findViewById(R.id.linear_over_overtime);
        final LinearLayout linear_unit = (LinearLayout) mPathView.findViewById(R.id.linear_over_unit);
        final LinearLayout linear_reason = (LinearLayout) mPathView.findViewById(R.id.linear_over_reason);
        final LinearLayout linear_person = (LinearLayout) mPathView.findViewById(R.id.linear_over_person);

        over_starttime.setInputType(InputType.TYPE_NULL);
        over_endtime.setInputType(InputType.TYPE_NULL);
        over_unit.setInputType(InputType.TYPE_NULL);
        over_person.setInputType(InputType.TYPE_NULL);
        over_starttime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.i("Fragment", "setOnFocusChangeListener    " + mFirst);
                    if (mFirst) {
                        pvStartTime.show();
                    }
                    linear_star.setBackgroundResource(R.drawable.bg_edittext_focused);
                } else {
                    linear_star.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });
        over_endtime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_end.setBackgroundResource(R.drawable.bg_edittext_focused);
                    pvEndTime.show();
                } else {
                    linear_end.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });
//加班时长
        over_duration.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_overtime.setBackgroundResource(R.drawable.bg_edittext_focused);
                } else {
                    linear_overtime.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });


        over_unit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_unit.setBackgroundResource(R.drawable.bg_edittext_focused);
                    pvOptionsUnit.show();
                } else {
                    linear_unit.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });

        over_reason.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_reason.setBackgroundResource(R.drawable.bg_edittext_focused);
                } else {
                    linear_reason.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });

        over_person.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_person.setBackgroundResource(R.drawable.bg_edittext_focused);
                    pvOptionsPerson.show();
                } else {
                    linear_person.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Fragment", "onActivityCreated---Fragment 加班申请   ");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("Fragment", "setUserVisibleHint---Fragment 加班申请   " + isVisibleToUser);
//不可见的 mFirst 默认 false;
        if (!isVisibleToUser) {
            mFirst = false;
        }

    }

    /**
     * 可见时执行网络请求
     */
    @Override
    public void fetchData() {
        if (!mHasLoadedOnce) {
            loadData(HttpUtil.workApply);
        }
        Log.d("Fragment", "fetchData---Fragment 加班申请   ");


    }

    /**
     * @return  false 进入asctivity中，true执行当前操作
     */
    @Override
    public boolean onBackPressed() {
        if (pvStartTime.isShowing()) {
            pvStartTime.dismiss();
            return true;
        }
        if (pvEndTime.isShowing()) {
            pvEndTime.dismiss();

            return true;
        }
        if (pvOptionsUnit.isShowing()) {
            pvOptionsUnit.dismiss();
            return true;
        }
        if (pvOptionsPerson.isShowing()) {
            pvOptionsPerson.dismiss();
            return true;
        }
        return false;
    }


    /**
     * 提交加班数据
     */
    private void subMitData() {


        begin_Time = over_starttime.getText().toString();
        end_Time = over_endtime.getText().toString();
        work_Duration = over_duration.getText().toString();
        time_Unit = over_unit.getText().toString();
        reason = over_reason.getText().toString();
        person = over_person.getText().toString();
      //  Logger.i("A:" + begin_Time + " B " + end_Time + " C " + work_Duration + " D " + reason + " E " + person);
        if (StringUtils.isEmpty(begin_Time, end_Time + "", work_Duration) && StringUtils.isEmpty(time_Unit, reason, person)) {
            postData(HttpUtil.saveWorkApply);
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
                        OverPatchPerBean overRecordBean = gson.fromJson(jsonObject.toString(), OverPatchPerBean.class);
                        if(overRecordBean.getMessage().equals("error")){
                            AppToast.makeShortToast(mActivity,"服务暂停,请稍等");
                            return;
                        }

                        List<OverPatchPerBean.AvailableApprovalManListBean> listBeen = overRecordBean.getAvailableApprovalManList();
                        loadPersonOption(listBeen);
                    }
                }
        );
    }

    /**
     * 提交数据
     * <p/>
     * {'methodname':'kq/saveWorkApply.json','cust_Id':'','emp_Id':'','time_Unit':'','work_Duration':'','begin_Time':'','end_Time':'','reason':'','approval_Man':''}
     *
     * @param postUrl
     */


    public void postData(String postUrl) {
        String[] key = new String[]{"emp_Id", "cust_Id", "time_Unit", "work_Duration", "begin_Time", "end_Time", "reason", "approval_Man"};
        String[] value = new String[]{emp_Id + "", cust_Id + "", mUnit + "", work_Duration, begin_Time, end_Time, reason, approval_Man};
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

                        AppToast.showShortText(mActivity, "申请成功!");
                        over_starttime.setText("");
                        over_endtime.setText("");
                        over_reason.setText("");
                        over_unit.setText("");
                        over_duration.setText("");
                        over_person.setText("");
                    } else if("duplicate".equals(message)){
                        AppToast.showShortText(mActivity, "您的加班申请与已存在的申请时间段重合!");
                    }
                    else {
                        AppToast.showShortText(mActivity, "服务器暂停 请稍后!");
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
    private void dateTpyeOption() {

        //选项选择器
        pvOptionsUnit = new OptionsPickerView(mActivity);
        //选项1
        options2Items.add(new ProvinceBean(0, "天", "11", "1"));
        options2Items.add(new ProvinceBean(1, "小时", "1", "1"));
        options2Items.add(new ProvinceBean(2, "半天", "1", "1"));
        pvOptionsUnit.setPicker(options2Items);
        pvOptionsUnit.setTitle("请选择");
        pvOptionsUnit.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsUnit.setSelectOptions(1);
        pvOptionsUnit.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options2Items.get(options1).getPickerViewText();
                over_unit.setText(tx);
                mUnit = options1 + 1;

            }
        });
    }


    /**
     * 加载选项 pickerview
     *
     * @param listBeen
     */
    private void loadPersonOption(final List<OverPatchPerBean.AvailableApprovalManListBean> listBeen) {

        //选项选择器
        pvOptionsPerson = new OptionsPickerView(mActivity);
        //选项1
        int i = 0;
        for (OverPatchPerBean.AvailableApprovalManListBean availableApprovalManListBean : listBeen) {
            options3Items.add(new ProvinceBean(i++, availableApprovalManListBean.getEmp_Name(), "11", "1"));
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
                over_person.setText(tx);
                approval_Man = listBeen.get(options1).getEmp_Id();

            }
        });
    }


    /**
     * 加载开始时间选项 pickerview
     */
    private void startOption() {

        pvStartTime = new TimePickerView(mActivity, TimePickerView.Type.YEAR_MONTH_DAY);
        pvStartTime.setTime(new Date());
        pvStartTime.setCyclic(false);
        pvStartTime.setCancelable(true);
        //时间选择后回调
        pvStartTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                over_starttime.setText(getTime(date));
            }
        });

    }

    /**
     * 加载结束时间选项 pickerview
     */
    private void endOption() {

        pvEndTime = new TimePickerView(mActivity, TimePickerView.Type.YEAR_MONTH_DAY);
        pvEndTime.setTime(new Date());
        pvEndTime.setCyclic(false);
        pvEndTime.setCancelable(true);
        //时间选择后回调
        pvEndTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                over_endtime.setText(getTime(date));
            }
        });

    }

    public String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }


}
