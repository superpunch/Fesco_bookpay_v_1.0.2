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
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.activity.RestActivity;
import com.fesco.bookpay.base.BasePageFragment;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.ProvinceBean;
import com.fesco.bookpay.entity.RestPatchBean;
import com.fesco.bookpay.entity.RestRecordBean;
import com.fesco.bookpay.impl.OnClickEvent;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.ConversionUtil;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.StringUtils;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 休假申请
 * Created by gong.min on 2016/9/7.
 */
public class RestApplicationFragment extends BasePageFragment {

    private static final String KEY = "EXTRA";
    private LoginEntity flag;
    private int emp_Id, cust_Id;
    private String token;
    private View mPathView;
    private Gson gson;
    private EditText mTpye;
    private LinearLayout mLinsur;
    private TextView mSurplus;//剩余时长
    private EditText mTimeUnit;//时间单位
    private TextView mStarTime;
    private TextView mEndTime;
    private EditText mApm;
    private EditText mEndApm;
    private LinearLayout linear_tpye;
    private LinearLayout linear_startime;
    private LinearLayout linear_endtime;
    private LinearLayout linear_number;
    private EditText mTimeNumber;
    private EditText mReason;
    private TextView mPerson;
    private Button mBtnRest;


    private TimePickerView pvTimeHour;
    private TimePickerView pvTimeDay;
    private TimePickerView pvTimeEndHour;
    private TimePickerView pvTimeEndDay;


    private OptionsPickerView pvOptionsBig;//天，半天，小时
    private OptionsPickerView pvOptionsMiddle;// 天 、半天
    private OptionsPickerView pvOptionsSmall;//天
    private OptionsPickerView pvOptionsSmalls;//半天

    private OptionsPickerView pvOptionsAmp;//开始 上午/下午
    private OptionsPickerView pvOptionsEndAmp;//截止 上下午


    private OptionsPickerView pvOptionsPerson;
    private OptionsPickerView pvOptionsTpye;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ProvinceBean> options2Items = new ArrayList<>();
    private ArrayList<ProvinceBean> options3Items = new ArrayList<>();
    private ArrayList<ProvinceBean> options4Items = new ArrayList<>(); //天、半天
    private ArrayList<ProvinceBean> options5Items = new ArrayList<>(); //天
    private ArrayList<ProvinceBean> options6Items = new ArrayList<>();

    private ArrayList<ProvinceBean> options7Items = new ArrayList<>();//上下午
    private ArrayList<ProvinceBean> options8Items = new ArrayList<>();

    private String hol_Begin_Apm=""; //AM对应的ID 上午
    private String hol_End_Apm=""; //PM对应的ID    下午

    private String hol_Begin="";
    private String hol_End="";
    private String hol_Name=""; //假期类型名称


    private String reason;
    private String person; //审批人
    private int approval_Man; //审批人 对应的 emp_id
    private int hol_Set_Id; //请假类型对应的 id
    private int hol_Unit;//时间单位对应的id   1/2/3    1 天 , 2 小时, 3 半天
    private boolean mHasLoadedOnce;// 只加载一次
    private boolean mFirst; //初始化默认不弹出
    private ViewGroup.LayoutParams lpstar;
    private ViewGroup.LayoutParams lpend;

    private  static RestApplicationFragment restApplicationFragment;


    public static RestApplicationFragment getInstance(LoginEntity flag) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        restApplicationFragment = new RestApplicationFragment();
        restApplicationFragment.setArguments(bundle);
        return restApplicationFragment;

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
                    inflater.inflate(R.layout.list_tab_restapplica, container, false);
            mTpye = (EditText) mPathView.findViewById(R.id.rest_tpye_id);


            mLinsur = (LinearLayout) mPathView.findViewById(R.id.rest_linear_surplus);
            mSurplus = (TextView) mPathView.findViewById(R.id.rest_surplus);
            mTimeUnit = (EditText) mPathView.findViewById(R.id.rest_unit);
            mStarTime = (TextView) mPathView.findViewById(R.id.rest_start_time);
            mEndTime = (TextView) mPathView.findViewById(R.id.rest_end_time);
            mApm = (EditText) mPathView.findViewById(R.id.rest_apm);
            mEndApm = (EditText) mPathView.findViewById(R.id.rest_endapm);
            mTimeNumber = (EditText) mPathView.findViewById(R.id.rest_number); //请假时数

            mReason = (EditText) mPathView.findViewById(R.id.rest_reason);
            mPerson = (TextView) mPathView.findViewById(R.id.rest_person);
            mBtnRest = (Button) mPathView.findViewById(R.id.btn_restpatch);
            gson = new Gson();

            initOnClickListener();
            //提交补签申请
            mBtnRest.setOnClickListener(new OnClickEvent(){
                @Override
                public void singleClick(View v) {
                    subMitData();
                }
            });

            Log.i("Fragment", "马丹----初始化-View---Fragment 加班申请 001  ");
            initializePickerView();
            initializeLinearBackground(mPathView);

            loadDataRecord(HttpUtil.getEmpHol);
        }

        Log.i("Fragment", "马丹----初始化-View---Fragment 加班申请   ");
        return mPathView;
    }







    private void initTimeUnit() {

        String tx = mTpye.getText().toString();

        if (tx != null && tx.length() > 0) {
            linear_number.setVisibility(View.GONE);
            linear_endtime.setVisibility(View.VISIBLE);
            mApm.setVisibility(View.INVISIBLE);
            mEndApm.setVisibility(View.INVISIBLE);

            mStarTime.setHint("请选择开始时间");
            mEndTime.setHint("请选择截止时间");
            mApm.setHint("请选择上午或下午");
            mEndApm.setHint("请选择上午或下午");

        }

        if (tx.equals("年假")) {
            pvOptionsMiddle.show();
        } else if (tx.equals("调休")) {
            pvOptionsBig.show();  // 1,2,3  1天，2小时，3半天
        } else if (tx.equals("病假") || tx.equals("事假") || tx.equals("产前检查假")) {
            pvOptionsMiddle.show();   //1,3
        } else if (tx.equals("婚假") || tx.equals("产假") || tx.equals("丧假")) {
            pvOptionsSmall.show(); //  1
        } else if (tx.equals("家长会假")) {
            pvOptionsSmalls.show();  //3
        }
    }


    private void initStarTime() {
        String tx = mTimeUnit.getText().toString();
        if (tx.equals("天")) {
            pvTimeDay.show();
        } else if (tx.equals("半天")) {
            pvTimeDay.show();
        } else if (tx.equals("小时")) {
            pvTimeHour.show();
        }
    }

    private void initEndTime() {
        String tx = mTimeUnit.getText().toString();
        if (tx.equals("天")) {
            pvTimeEndDay.show();
        } else if (tx.equals("半天")) {
            pvTimeEndDay.show();
        } else if (tx.equals("小时")) {
            pvTimeEndHour.show();
        }
    }

    private void initOnClickListener() {

        mTpye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptionsTpye.show();
                mFirst=true;
            }
        });

        mTimeUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tx = mTpye.getText().toString();
                if (tx != null && tx.length() > 0) {
                    linear_number.setVisibility(View.GONE);
                    linear_endtime.setVisibility(View.VISIBLE);
                    mApm.setVisibility(View.INVISIBLE);
                    mEndApm.setVisibility(View.INVISIBLE);
                    mStarTime.setHint("请选择开始时间");
                    mEndTime.setHint("请选择截止时间");
                    mApm.setHint("请选择上午或下午");
                    mEndApm.setHint("请选择上午或下午");
                }
                if (tx.equals("年假")) {
                    pvOptionsMiddle.show();
                } else if (tx.equals("调休")) {
                    pvOptionsBig.show();  // 1,2,3  1天，2小时，3半天
                } else if (tx.equals("病假") || tx.equals("事假") || tx.equals("产前检查假")) {
                    pvOptionsMiddle.show();   //1,3
                } else if (tx.equals("婚假") || tx.equals("产假") || tx.equals("丧假")) {
                    pvOptionsSmall.show(); //  1
                } else if (tx.equals("家长会假")) {
                    pvOptionsSmalls.show();  //3
                }
            }
        });

        mStarTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String tx = mTimeUnit.getText().toString();
                if (tx.equals("天")) {
                    pvTimeDay.show();
                } else if (tx.equals("半天")) {
                    pvTimeDay.show();
                } else if (tx.equals("小时")) {
                    pvTimeHour.show();
                }


            }
        });

        mEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tx = mTimeUnit.getText().toString();
                if (tx.equals("天")) {
                    pvTimeEndDay.show();
                } else if (tx.equals("半天")) {
                    pvTimeEndDay.show();
                } else if (tx.equals("小时")) {
                    pvTimeEndHour.show();
                }
            }
        });
        mApm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptionsAmp.show();
            }
        });

        mEndApm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptionsEndAmp.show();
            }
        });
        mPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptionsPerson.show();

            }
        });

    }

    private void initializeLinearBackground(View mPathView) {
        linear_tpye = (LinearLayout) mPathView.findViewById(R.id.linear_tpye);
        linear_startime = (LinearLayout) mPathView.findViewById(R.id.linear_startime);
        linear_endtime = (LinearLayout) mPathView.findViewById(R.id.linear_endtime);
        linear_number = (LinearLayout) mPathView.findViewById(R.id.linear_number);

        final LinearLayout linear_time = (LinearLayout) mPathView.findViewById(R.id.linear_time);
        final LinearLayout  linear_reason = (LinearLayout) mPathView.findViewById(R.id.linear_reason);
        final LinearLayout linear_patch = (LinearLayout) mPathView.findViewById(R.id.linear_patch);

        lpstar = linear_startime.getLayoutParams();
        lpend = linear_endtime.getLayoutParams();

        mTpye.setInputType(InputType.TYPE_NULL);
        mTimeUnit.setInputType(InputType.TYPE_NULL);
        mStarTime.setInputType(InputType.TYPE_NULL);
        mEndTime.setInputType(InputType.TYPE_NULL);
        mEndApm.setInputType(InputType.TYPE_NULL);
        mApm.setInputType(InputType.TYPE_NULL);
        mEndApm.setInputType(InputType.TYPE_NULL);
        mPerson.setInputType(InputType.TYPE_NULL);

        mTpye.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_tpye.setBackgroundResource(R.drawable.bg_edittext_focused);
                    if(mFirst){
                        pvOptionsTpye.show();
                    }

                } else {
                    linear_tpye.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });
        mTimeUnit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_time.setBackgroundResource(R.drawable.bg_edittext_focused);
                    initTimeUnit();
                } else {
                    linear_time.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });
        mStarTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_startime.setBackgroundResource(R.drawable.bg_edittext_focused);
                    initStarTime();
                } else {
                    linear_startime.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });
        mEndTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_endtime.setBackgroundResource(R.drawable.bg_edittext_focused);
                    initEndTime();
                } else {
                    linear_endtime.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });
        mApm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mApm.setBackgroundResource(R.drawable.bg_edittext_focused);
                    pvOptionsAmp.show();
                } else {
                    mApm.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });
        mEndApm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mEndApm.setBackgroundResource(R.drawable.bg_edittext_focused);
                    pvOptionsEndAmp.show();
                } else {
                    mEndApm.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });


        mTimeNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_number.setBackgroundResource(R.drawable.bg_edittext_focused);

                } else {
                    linear_number.setBackgroundResource(R.drawable.bg_edittext_normal);
                }
            }
        });

        mReason.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_reason.setBackgroundResource(R.drawable.bg_edittext_focused);

                } else {
                    linear_reason.setBackgroundResource(R.drawable.bg_edittext_normal);

                }
            }
        });
        mPerson.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    linear_patch.setBackgroundResource(R.drawable.bg_edittext_focused);
                    pvOptionsPerson.show();
                } else {
                    linear_patch.setBackgroundResource(R.drawable.bg_edittext_normal);
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
        if(!isVisibleToUser){
            mFirst=false;
        }
    }

    /**
     * 可见时执行网络请求
     */
    @Override
    public void fetchData() {
        if (!mHasLoadedOnce) {
            loadData(HttpUtil.holApply);
        }
    }

    String hol_Num;

    /**
     * 提交加班数据
     */
    private void subMitData() {

        String tvTpye = mTpye.getText().toString();
        String tvTimeUnit = mTimeUnit.getText().toString();
        String tvStart = mStarTime.getText().toString();
        String tvEnd = mEndTime.getText().toString();
        String tvAmp = mApm.getText().toString();
        String tvmEndApm = mEndApm.getText().toString();
        hol_Num = mTimeNumber.getText().toString();
        reason = mReason.getText().toString();
        person = mPerson.getText().toString();

        Logger.i("类型: " + tvTpye + "  时间单位: " + tvTimeUnit + "==" + hol_Unit + " 开始时间 :" + tvStart + "==" + hol_Begin + " 结束时间 " + tvEnd + "==" + hol_End +
                " 开始APM " + tvAmp + "==" + hol_Begin_Apm + " 截止APM " + tvmEndApm + "==" + hol_End_Apm + " 请假时数  " + hol_Num + " 原因 " + reason + " 人 " + person);
        //小时
        if (hol_Unit == 2) {
            if (StringUtils.isEmpty(tvTpye, tvTimeUnit, hol_Begin) && StringUtils.isEmpty(hol_Num, reason, person)) {
                postData(HttpUtil.saveHolApply);
            } else AppToast.showShortText(mActivity, "请输入完整信息");
            //天
        } else if (hol_Unit == 1) {

            if (StringUtils.isEmpty(tvTpye, tvTimeUnit, hol_Begin) && StringUtils.isEmpty(hol_End, reason, person)) {
                postData(HttpUtil.saveHolApply);
            } else
                AppToast.showShortText(mActivity, "请输入完整信息");
            //半天
        } else if (hol_Unit == 3) {
            if (StringUtils.isEmpty(tvTpye, tvTimeUnit, hol_Begin) && StringUtils.isEmpty(hol_End, reason, person) && StringUtils.isEmpty(hol_Begin_Apm, hol_End_Apm)) {
                postData(HttpUtil.saveHolApply);
            } else
                AppToast.showShortText(mActivity, "请输入完整信息");
        } else AppToast.showShortText(mActivity, "请输入完整信息");
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
                        RestPatchBean restPatchBean = gson.fromJson(jsonObject.toString(), RestPatchBean.class);
                        if(restPatchBean.getErrcode()==1){
                            return;
                        }
                        List<RestPatchBean.AvailableApprovalManListBean> listBeen = restPatchBean.getAvailableApprovalManList();
                        List<RestPatchBean.HolPoolListBean> holPoolListBeen = restPatchBean.getHolPoolList();
                        List<RestPatchBean.HolSetListBean> holSetListBeen = restPatchBean.getHolSetList();

                        Logger.i(listBeen.toString());
                        Logger.i(holPoolListBeen.toString());
                        Logger.i(holSetListBeen.toString());

                        loadPersonOption(listBeen);

                        loadHolTpyeOption(holPoolListBeen, holSetListBeen);


                    }


                }
        );
    }

    String[] dateNumber = new String[3];

    private void loadHolTpyeOption(List<RestPatchBean.HolPoolListBean> holPoolListBeen, final List<RestPatchBean.HolSetListBean> holSetListBeen) {

        //选项选择器
        pvOptionsTpye = new OptionsPickerView(mActivity);
        //选项1
        int i = 0;
        for (RestPatchBean.HolPoolListBean poolListBean : holPoolListBeen) {
            dateNumber[i++] = String.valueOf(poolListBean.getAvailableAllNum());
        }

        for (RestPatchBean.HolSetListBean setListBeen : holSetListBeen) {
            options1Items.add(new ProvinceBean(i++, setListBeen.getHol_Name(), "11", "1"));
        }
        pvOptionsTpye.setPicker(options1Items);
        pvOptionsTpye.setTitle("请选择");
        pvOptionsTpye.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsTpye.setSelectOptions(1);
        pvOptionsTpye.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options1Items.get(options1).getPickerViewText();
                mTpye.setText(tx);
                mTimeUnit.setHint("请选择时间单位");
                mLinsur.setVisibility(View.GONE);
                if (tx.equals("年假")) {
                    mLinsur.setVisibility(View.VISIBLE);
                    mSurplus.setText(dateNumber[0] + "天");

                } else if (tx.equals("调休")) {
                    mLinsur.setVisibility(View.VISIBLE);
                    mSurplus.setText(dateNumber[1] + "小时");
                }
                hol_Set_Id = holSetListBeen.get(options1).getHol_Set_Id();
                hol_Name=tx;
            }
        });


    }

    /**
     * 初始化所有的PickerView
     */
    public void initializePickerView() {
        dateTpyeOptionBig();
        dateTpyeOptionMaddle();
        dateTpyeOptionSmall();
        dateTpyeOptionSmalls();

        timeOptionHour(); //开始时间
        timeOptionDay();
        timeOptionEndHour(); //截止时间
        timeOptionEndDay();

        dateTpyeOptionAmp(); //开始时间 上午、下午
        dateTpyeOptionEndAmp(); //截止时间 上午、下午

    }

    /**
     * 改变LinearLayout 宽
     */
    private void ChangeMatch() {
        lpstar.width = LinearLayout.LayoutParams.MATCH_PARENT;
        lpend.width = LinearLayout.LayoutParams.MATCH_PARENT;
        linear_startime.setLayoutParams(lpstar);
        linear_endtime.setLayoutParams(lpend);
    }

    private void ChangeWrap() {
        lpstar.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        lpend.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        linear_startime.setLayoutParams(lpstar);
        linear_endtime.setLayoutParams(lpend);
    }


    /**
     * 加载时间单位选项 pickerview
     */
    private void dateTpyeOptionBig() {

        //选项选择器
        pvOptionsBig = new OptionsPickerView(mActivity);
        //选项1
        options3Items.add(new ProvinceBean(0, "天", "11", "1"));
        options3Items.add(new ProvinceBean(1, "半天", "1", "1"));
        options3Items.add(new ProvinceBean(2, "小时", "1", "1"));

        pvOptionsBig.setPicker(options3Items);
        pvOptionsBig.setTitle("请选择");
        pvOptionsBig.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsBig.setSelectOptions(1);
        pvOptionsBig.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options3Items.get(options1).getPickerViewText();
                mTimeUnit.setText(tx);
//                mStarTime.setText("");
//                mEndTime.setText("");
                mStarTime.setHint("请选择开始时间");
                mEndTime.setHint("请选择截止时间");
                if (0 == options1) {
                    hol_Unit = 1;
                    ChangeMatch();
                }

                if (1 == options1) {
                    mApm.setVisibility(View.VISIBLE);
                    mEndApm.setVisibility(View.VISIBLE);
                    hol_Unit = 3;

                    ChangeWrap();


                }
                if (options1 == 2) {
                    linear_number.setVisibility(View.VISIBLE);
                    linear_endtime.setVisibility(View.GONE);
                    hol_Unit = 2;
                    ChangeMatch();
                }


            }
        });
    }

    /**
     * 加载时间单位选项 pickerview
     */
    private void dateTpyeOptionMaddle() {

        //选项选择器
        pvOptionsMiddle = new OptionsPickerView(mActivity);
        //选项1
        options4Items.add(new ProvinceBean(0, "天", "11", "1"));
        options4Items.add(new ProvinceBean(1, "半天", "1", "1"));
        pvOptionsMiddle.setPicker(options4Items);
        pvOptionsMiddle.setTitle("请选择");
        pvOptionsMiddle.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsMiddle.setSelectOptions(1);
        pvOptionsMiddle.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options4Items.get(options1).getPickerViewText();
                mTimeUnit.setText(tx);


                if ("半天".equals(tx)) {
                    mApm.setHint("请选择上午或下午");
                    mEndApm.setHint("请选择上午或下午");
                    mApm.setVisibility(View.VISIBLE);
                    mEndApm.setVisibility(View.VISIBLE);
                    hol_Unit = 3;

                    ChangeWrap();

                }

                if ("天".equals(tx)) {
                    hol_Unit = 1;
                    ChangeMatch();

                }


            }
        });
    }

    /**
     * 加载时间单位选项 pickerview
     */
    private void dateTpyeOptionSmall() {

        //选项选择器
        pvOptionsSmall = new OptionsPickerView(mActivity);
        //选项1
        options5Items.add(new ProvinceBean(0, "天", "11", "1"));
        pvOptionsSmall.setPicker(options5Items);
        pvOptionsSmall.setTitle("请选择");
        pvOptionsSmall.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsSmall.setSelectOptions(1);
        pvOptionsSmall.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options5Items.get(options1).getPickerViewText();
                mTimeUnit.setText(tx);
                hol_Unit = 1;
                ChangeMatch();

            }
        });
    }

    /**
     * 加载时间单位选项 pickerview
     */
    private void dateTpyeOptionSmalls() {

        //选项选择器
        pvOptionsSmalls = new OptionsPickerView(mActivity);
        //选项1
        options6Items.add(new ProvinceBean(0, "半天", "11", "1"));
        pvOptionsSmalls.setPicker(options6Items);
        pvOptionsSmalls.setTitle("请选择");
        pvOptionsSmalls.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsSmalls.setSelectOptions(1);
        pvOptionsSmalls.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options6Items.get(options1).getPickerViewText();
                mTimeUnit.setText(tx);


//                mApm.setVisibility(View.VISIBLE);
//                mEndApm.setVisibility(View.VISIBLE);
//                ViewGroup.LayoutParams lp;
//                lp = linear_startime.getLayoutParams();
//                lp.width = LinearLayout.LayoutParams.WRAP_CONTENT;
//                lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
//                linear_startime.setLayoutParams(lp);



                    mApm.setHint("请选择上午或下午");
                    mEndApm.setHint("请选择上午或下午");
                    mApm.setVisibility(View.VISIBLE);
                    mEndApm.setVisibility(View.VISIBLE);
                    hol_Unit = 3;
                    ChangeWrap();


            }
        });
    }


    /**
     * 加载选项 pickerview
     *
     * @param listBeen
     */
    private void loadPersonOption(final List<RestPatchBean.AvailableApprovalManListBean> listBeen) {

        //选项选择器
        pvOptionsPerson = new OptionsPickerView(mActivity);
        //选项1
        int i = 0;
        int j = 0;
        for (RestPatchBean.AvailableApprovalManListBean availableApprovalManListBean : listBeen) {
            options2Items.add(new ProvinceBean(i++, availableApprovalManListBean.getEmp_Name(), "11", "1"));
        }

        pvOptionsPerson.setPicker(options2Items);
        pvOptionsPerson.setTitle("请选择");
        pvOptionsPerson.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsPerson.setSelectOptions(1);
        pvOptionsPerson.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options2Items.get(options1).getPickerViewText();
                mPerson.setText(tx);
                approval_Man = listBeen.get(options1).getEmp_Id();


            }
        });
    }


    /**
     * yyyy-MM-dd HH:mm
     * 加载开始时间选项 pickerview
     */
    private void timeOptionHour() {

        pvTimeHour = new TimePickerView(mActivity, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTimeHour.setTime(new Date());
        pvTimeHour.setCyclic(false);
        pvTimeHour.setCancelable(true);
        //时间选择后回调
        pvTimeHour.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                hol_Begin = ConversionUtil.getTimeHour(date);
                mStarTime.setText(hol_Begin);


            }
        });

    }

    /**
     * yyyy-MM-dd
     * 加载开始时间选项 pickerview
     */
    private void timeOptionDay() {

        pvTimeDay = new TimePickerView(mActivity, TimePickerView.Type.YEAR_MONTH);
        pvTimeDay.setTime(new Date());
        pvTimeDay.setCyclic(false);
        pvTimeDay.setCancelable(true);
        //时间选择后回调
        pvTimeDay.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                hol_Begin =ConversionUtil.getTime(date);
                mStarTime.setText(hol_Begin);

            }
        });

    }


    /**
     * yyyy-MM-dd HH:mm
     * 加载开始时间选项 pickerview
     */
    private void timeOptionEndHour() {

        pvTimeEndHour = new TimePickerView(mActivity, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTimeEndHour.setTime(new Date());
        pvTimeEndHour.setCyclic(false);
        pvTimeEndHour.setCancelable(true);
        //时间选择后回调
        pvTimeEndHour.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                hol_End = ConversionUtil.getTimeHour(date);
                mEndTime.setText(hol_End);


            }
        });

    }

    /**
     * yyyy-MM-dd   TimePickerView.Type.YEAR_MONTH
     * yyyy-MM-dd HH:mm    TimePickerView.Type.YEAR_MONTH_DAY
     * 加载开始时间选项 pickerview
     */
    private void timeOptionEndDay() {

        pvTimeEndDay = new TimePickerView(mActivity, TimePickerView.Type.YEAR_MONTH);
        pvTimeEndDay.setTime(new Date());
        pvTimeEndDay.setCyclic(false);
        pvTimeEndDay.setCancelable(true);
        //时间选择后回调
        pvTimeEndDay.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                hol_End = ConversionUtil.getTime(date);
                mEndTime.setText(hol_End);

            }
        });

    }

    /**
     * 加载开始 上午/下午选项 pickerview
     */
    private void dateTpyeOptionAmp() {

        //选项选择器
        pvOptionsAmp = new OptionsPickerView(mActivity);
        //选项1
        options7Items.add(new ProvinceBean(0, "上午", "11", "1"));
        options7Items.add(new ProvinceBean(1, "下午", "11", "1"));
        pvOptionsAmp.setPicker(options7Items);
        pvOptionsAmp.setTitle("请选择");
        pvOptionsAmp.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsAmp.setSelectOptions(1);
        pvOptionsAmp.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options7Items.get(options1).getPickerViewText();
                mApm.setText(tx);
                if (0 == options1) {
                    hol_Begin_Apm = "上午";
                } else hol_Begin_Apm = "下午";
            }
        });
    }

    /**
     * 加载截止   上午/下午选项 pickerview
     */
    private void dateTpyeOptionEndAmp() {

        //选项选择器
        pvOptionsEndAmp = new OptionsPickerView(mActivity);
        //选项1
        options8Items.add(new ProvinceBean(0, "上午", "11", "1"));
        options8Items.add(new ProvinceBean(1, "下午", "11", "1"));
        pvOptionsEndAmp.setPicker(options8Items);
        pvOptionsEndAmp.setTitle("请选择");
        pvOptionsEndAmp.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsEndAmp.setSelectOptions(1);
        pvOptionsEndAmp.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options8Items.get(options1).getPickerViewText();
                mEndApm.setText(tx);
                if (0 == options1) {
                    hol_End_Apm = "上午";
                } else hol_End_Apm = "下午";
            }
        });
    }

    /**
     * 提交数据
     * . 请假申请保存：{'methodname':'kq/saveHolApply.json','emp_Id':'','cust_Id':'','hol_Name':'','hol_Set_Id':'','hol_Begin':'','hol_End':'','hol_Begin_Apm':'','hol_Begin_Apm':'','hol_Num':'','momo':'','approval_Man':'','hol_Unit':''}
     * {'methodname':'kq/saveHolApply.json','emp_Id':'','cust_Id':'','hol_Name':'','hol_Set_Id':'','hol_Begin':'','hol_End':'','hol_Begin_Apm':'','hol_End_Apm':'','hol_Num':'','momo':'','approval_Man':'','hol_Unit':''}
     *
     * @param postUrl
     */


    public void postData(String postUrl) {
        String[] key = new String[]{"emp_Id", "cust_Id", "hol_Name","hol_Set_Id", "hol_Begin", "hol_End", "hol_Begin_Apm", "hol_End_Apm", "hol_Num", "momo", "approval_Man", "hol_Unit","pic_Ids"};
        String[] value = new String[]{Integer.toString(emp_Id) , Integer.toString(cust_Id) , hol_Name,Integer.toString(hol_Set_Id), hol_Begin, hol_End, hol_Begin_Apm, hol_End_Apm, hol_Num, reason,Integer.toString(approval_Man), Integer.toString(hol_Unit),""};
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
                        hol_Num = "";
                        hol_Unit = 0;
                        hol_Begin = "";
                        hol_End = "";
                        hol_Begin_Apm = "";
                        hol_End_Apm = "";
                        mTpye.setText("");
                        mTimeUnit.setText("");
                        mReason.setText("");
                        mPerson.setText("");
                        mTimeNumber.setText("");
                        mStarTime.setText("");
                        mEndTime.setText("");
                        mApm.setText("");
                        mEndApm.setText("");
                        mApm.setVisibility(View.INVISIBLE);
                        mEndApm.setVisibility(View.INVISIBLE);
                        linear_number.setVisibility(View.GONE);
                        mLinsur.setVisibility(View.GONE);
                        ChangeMatch();
                   //     loadDataRecord(HttpUtil.getEmpHol);

                        loadDataRecord(HttpUtil.getEmpHol);
                    } else if ("not enough".equals(message)) {
                        AppToast.showShortText(mActivity, "您的时间不足够！");
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
     * 网络请求    读取 休假记录列表
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     */
    public  void loadDataRecord(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id", "cust_Id"};
        String[] value = new String[]{emp_Id + "", cust_Id + ""};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Logger.json(jsonObject.toString());
                        RestRecordBean restRecordBean=gson.fromJson(jsonObject.toString(), RestRecordBean.class);
                        cb.callBack(restRecordBean,true);

                    }
                }
        );
    }

    RestActivity.MyCallBack cb=null;

    public void setCallBack(RestActivity.MyCallBack cb){//设置回调接口

        this.cb = cb;
    }



}
