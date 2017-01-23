package com.fesco.bookpay.activity;

/**
 * StatisticActivity
 * Created by gong.min on 2016/10/24.
 */

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.statisticbean.CheckListBean;
import com.fesco.bookpay.entity.statisticbean.HolPoolBean;
import com.fesco.bookpay.entity.statisticbean.RecordBean;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.kyloading.KyLoadingBuilder;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.view.calendar.CalendarCard;
import com.fesco.bookpay.view.calendar.CalendarViewAdapter;
import com.fesco.bookpay.view.calendar.CustomDate;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class StatisticActivity extends BaseActivity implements OnClickListener, CalendarCard.OnCellClickListener {
    private ViewPager mViewPager;
    private int mCurrentIndex = 498;
    private CalendarCard[] mShowViews;
    private CalendarViewAdapter<CalendarCard> adapter;
    private SildeDirection mDirection = SildeDirection.NO_SILDE;

    enum SildeDirection {
        RIGHT, LEFT, NO_SILDE;
    }

    private ImageButton preImgBtn, nextImgBtn;
    private TextView monthText;
    private TextView mRecord;
    private TextView mHoliday;
    private Toolbar toolbar;
    private TextView toolText;
    private LoginEntity loginEntity;
    private String token;
    private int emp_Id;
    private int cust_Id;
    private  KyLoadingBuilder builder;
    private Gson gson;
    private int year;
    private int month;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        loginEntity = (LoginEntity) getIntent().getExtras().getSerializable("StatisticActivity");
        token = loginEntity.getToken();
        emp_Id = loginEntity.getEmp_Id();
        cust_Id = loginEntity.getCust_Id();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolText = (TextView) this.findViewById(R.id.toolbar_text);
        toolText.setText("签到统计");
        mViewPager = (ViewPager) this.findViewById(R.id.vp_calendar);
        preImgBtn = (ImageButton) this.findViewById(R.id.btnPreMonth);
        nextImgBtn = (ImageButton) this.findViewById(R.id.btnNextMonth);
        monthText = (TextView) this.findViewById(R.id.tvCurrentMonth);
        mRecord = (TextView) this.findViewById(R.id.statistic_record);
        mHoliday = (TextView) this.findViewById(R.id.statistic_holiday);


        preImgBtn.setOnClickListener(this);
        nextImgBtn.setOnClickListener(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StatisticActivity.this.finish();
            }
        });
        CalendarCard[] views = new CalendarCard[3];
        for (int i = 0; i < 3; i++) {
            views[i] = new CalendarCard(StatisticActivity.this, StatisticActivity.this);
        }
        adapter = new CalendarViewAdapter<>(views);
        setViewPager();

        gson = new Gson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd ");
        Date date = new Date();
        String dateStr = sdf.format(date);
        loadBuilder();
        holidayData(HttpUtil.getRestHolidays);
        checkDetailData(HttpUtil.getCheckDetailForEmp,dateStr);

        checkListData(HttpUtil.getCheckListForEmp,"","", "");


    }

    public  void loadBuilder(){
        builder = new KyLoadingBuilder(this);
        //   builder.setText("正在加载中...");
        builder.setIcon(R.drawable.loading04);
        //builder.setOutsideTouchable(false);
        //builder.setBackTouchable(true);
        builder.show();
    }


    private void setViewPager() {
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(498);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                measureDirection(position);
                updateCalendarView(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPreMonth:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
             //   checkListData(HttpUtil.getCheckListForEmp,year+"",String.valueOf(month-1), "left");
                break;
            case R.id.btnNextMonth:

                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
             //   checkListData(HttpUtil.getCheckListForEmp,year+"",String.valueOf(month+1), "left");
                break;

            default:
                break;
        }
    }
    StringBuffer sb=new StringBuffer();
    @Override
    public void clickDate(CustomDate date) {

        sb=sb.append( date.year).append(date.month).append(date.getDay());
        Log.e("Fragment","-----clickDate--------- "+sb);
        checkDetailData(HttpUtil.getCheckDetailForEmp,sb.toString());
        sb.setLength(0);
    }

    @Override
    public void changeDate(CustomDate date) {
        month=date.month;
        year=date.year;
        monthText.setText(month+ "月"+year + "年");
    }

    /**
     * 计算方向
     *
     * @param arg0
     */
    private void measureDirection(int arg0) {

        if (arg0 > mCurrentIndex) {
            mDirection = SildeDirection.RIGHT;

        } else if (arg0 < mCurrentIndex) {
            mDirection = SildeDirection.LEFT;
        }
        mCurrentIndex = arg0;
    }

    // 更新日历视图
    private void updateCalendarView(int arg0) {
        loadBuilder();
        mShowViews = adapter.getAllItems();
        int i = arg0 % mShowViews.length;
        if (mDirection == SildeDirection.RIGHT) {

            checkListData(HttpUtil.getCheckListForEmp,year+"",String.valueOf(month+1),"right");
            Log.e("Fragment","-----RIGHT ");
        } else if (mDirection == SildeDirection.LEFT) {
            checkListData(HttpUtil.getCheckListForEmp,year+"",String.valueOf(month-1),"left");

            Log.e("Fragment","-----LEFT ");

        }
        mDirection = SildeDirection.NO_SILDE;
    }


    /**
     * 网络请求
     * emp_Id,  dateStr：20161023
     * 1. ​打卡记录    （type=1为签到，2为签退，3为外勤）
     * {'methodname':'kq/getCheckDetailForEmp.json','emp_Id':'','dateStr':''}
     *
     * @param loadEmpInfo
     */

    public void checkDetailData(String loadEmpInfo,String dateStr) {

        String[] key = new String[]{"emp_Id", "dateStr"};
        String[] value = new String[]{Integer.toString(emp_Id),dateStr };
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        RecordBean recordBean = gson.fromJson(jsonObject.toString(), RecordBean.class);

                        if (recordBean.getErrcode() == 0) {
                            StringBuffer sb = new StringBuffer();
                            List<RecordBean.CedsBean> ceds = recordBean.getCeds();
                            String check_Type = "";
                            for (RecordBean.CedsBean cedsBean : ceds) {
                                String date = simpleDateFormat.format(cedsBean.getCheck_Time());
                                if (cedsBean.getCheck_Type() == 1) {
                                    check_Type = "签到";
                                } else if (cedsBean.getCheck_Type() == 2) {
                                    check_Type = "签退";
                                } else {
                                    check_Type = "外勤";
                                }
                                String addr = cedsBean.getCust_Addr();
                                sb.append(date + "\t" + check_Type + "\t" + addr+"\r\n");
                            }
                                mRecord.setText(sb);

                        } else {

                        }

                    }
                }
        );
    }

    /**
     * 2. 假期剩余：
     * {'methodname':'kq/getRestHolidays.json','emp_Id':''}
     *
     * @param loadEmpInfo
     */
    public void holidayData(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id"};
        String[] value = new String[]{Integer.toString(emp_Id)};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        HolPoolBean poolBean = gson.fromJson(jsonObject.toString(), HolPoolBean.class);
                        if (poolBean.getErrcode() == 0) {
                            List<HolPoolBean.HolPoolListBean> holPoolList = poolBean.getHolPoolList();
                            StringBuffer sb = new StringBuffer();
                            for (HolPoolBean.HolPoolListBean holPoolListBean : holPoolList) {
                                sb = sb.append(holPoolListBean.getHol_Name() + "：" + holPoolListBean.getAvailableAllNum() + holPoolListBean.getTime_Unit_Name() + "\t");
                            }
                            mHoliday.setText(sb);
                        } else {

                        }


                    }
                }
        );
    }

    /**
     * 员工个人考勤统计：
     * {'methodname':'kq/getCheckListForEmp.json','cust_Id':'','emp_Id':'','year':'','month':''}
     *
     * @param loadEmpInfo
     * @param
     */
    public void checkListData(String loadEmpInfo, final String year, final String month, final String slide) {

        String[] key = new String[]{"emp_Id", "cust_Id", "year", "month"};
        String[] value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), year, month};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        CheckListBean checkListBean = gson.fromJson(jsonObject.toString(), CheckListBean.class);
                        if (checkListBean.getErrcode() == 0) {
                            List<List<String>> lists = checkListBean.getRes();
                            mShowViews = adapter.getAllItems();
                             switch (slide){
                                 case  "":
                                     mShowViews[mCurrentIndex % mShowViews.length].nowSlide(lists);
                                 break;
                                 case  "right":
                                     mShowViews[mCurrentIndex % mShowViews.length].rightSlide(lists);
                                 break;
                                 case  "left":
                                     mShowViews[mCurrentIndex % mShowViews.length].leftSlide(lists);
                                 break;

                             }
                            builder.dismiss();

                            //   adapter.notifyDataSetChanged();
//                            for (List<String> list5 : lists) {
//                                for (String s : list5) {
//                                    System.out.println("S " + s + " list5" + list5 + "---lists:" + lists.size());
//                                }
//                            }

                        } else {

                        }


                    }
                }
        );
    }


}

