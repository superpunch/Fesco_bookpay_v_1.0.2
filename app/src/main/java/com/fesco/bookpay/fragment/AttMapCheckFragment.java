package com.fesco.bookpay.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.fesco.bookpay.activity.AttendanceActivity;
import com.fesco.bookpay.activity.InforPersonActivity;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.base.BasePageFragment;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.util.ACache;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.ConversionUtil;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.SharedPrefUtil;
import com.fesco.bookpay.util.StringUtils;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.view.CustomDialogCheck;
import com.fesco.bookpay.view.MapTypeView;
import com.fesco.bookpay.view.ZoomControlView;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;


/**
 * 签到
 * Created by gong.min on 2016/9/7.
 */
public class AttMapCheckFragment extends BasePageFragment {

    private static final String KEY = "EXTRA";
    private LoginEntity flag;
    private int emp_Id, cust_Id;
    private String token;
    private View view;
    private AttendanceActivity mActivity;
    private TextView tvTime;
    private Button btnCheckIn;
    private Button btnCheckOut;
    private Button btnCheckField;
    private CustomDialogCheck dialog;


    private MapView mMapView = null;
    private double mLatitude;
    private double mLongtitude;
    private boolean isFirstIn = true;
    // 相关定位
    private LocationClient mLocationClient;
    private BaiduMap mBaiduMap;
    private MyLocationListener mlocationListenter;

    private boolean mRunning = true;

    public static AttMapCheckFragment getInstance(LoginEntity flag) {
//传值

        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        AttMapCheckFragment attMapCheckFragment = new AttMapCheckFragment();
        attMapCheckFragment.setArguments(bundle);
        return attMapCheckFragment;

    }

    ZoomControlView zoomControlView;
    MapTypeView mapTypeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //     SDKInitializer.initialize(mActivity.getApplicationContext());
        Bundle bundle = getArguments();
        if (bundle != null) {
            flag = (LoginEntity) bundle.getSerializable(KEY);
            emp_Id = flag.getEmp_Id();
            cust_Id = flag.getCust_Id();
            token = flag.getToken();

        }

        if (view == null) {
            view = inflater.inflate(R.layout.list_tab_attcheck_map, container, false);
            mMapView = (MapView) view.findViewById(R.id.bmapView);
            zoomControlView = (ZoomControlView) view.findViewById(R.id.zcv_zoom);
            mapTypeView = (MapTypeView) view.findViewById(R.id.zcv_zoom2);
            zoomControlView.setMapView(mMapView);
            mapTypeView.setMapView(mMapView);

            initImageHead();


            initViewMap();
            initLocation();


            tvTime = (TextView) view.findViewById(R.id.tv_time);
            btnCheckIn = (Button) view.findViewById(R.id.btn_checkin);
            btnCheckOut = (Button) view.findViewById(R.id.btn_checkout);
            btnCheckField = (Button) view.findViewById(R.id.btn_checkfield);


            btnCheckIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadData(mLongtitude, mLatitude, "1", "");
                }


            });
            btnCheckOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadData(mLongtitude, mLatitude, "2", "");
                }
            });
            btnCheckField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog(mLongtitude, mLatitude, "3");

                }
            });

        }

        return view;
    }

    private void initImageHead() {
        //  String pathList=  aCache.getAsString("pathList");
        //  bitmap= BitmapUtils.readBitmapFromFileDescriptor(pathList,80,80);
        ImageView headImage = (ImageView) view.findViewById(R.id.image_map_head);
        ACache aCache = ACache.get(mActivity);
        Bitmap bitmapHead = aCache.getAsBitmap(InforPersonActivity.IMAGE_HEAD);
        Log.d("matrix", "onCreateView handler --" + bitmapHead);
        if (bitmapHead != null) {
            headImage.setImageBitmap(bitmapHead);
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void fetchData() {

        new TimeThread().start(); //启动新的线程


    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        mActivity = (AttendanceActivity) activity;
//
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = (AttendanceActivity) context;
    }

    // 弹窗
    private void dialog(final double longitude, final double latitude, final String type) {
        if (dialog == null) {
            dialog = new CustomDialogCheck(mActivity);
        }

        final EditText editText = (EditText) dialog.getEditText();//方法在CustomDialog中实现
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText.getText().toString();
                Logger.i(message.toString());
                if (StringUtils.isEmpty(message)) {
                    loadData(longitude, latitude, type, message);
//                    InputMethodManager imm = (InputMethodManager)mActivity. getSystemService(Context.INPUT_METHOD_SERVICE);
//                    if(getActivity() != null)
//                        imm.showSoftInput(getActivity().getWindow().getDecorView(),InputMethodManager.HIDE_NOT_ALWAYS);
//
                    dialog.dismiss();
                } else Toast.makeText(mActivity, "请填写~", Toast.LENGTH_SHORT).show();

            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /**
     * 防止多人签到签退使用同一个手机 ， 每个用户只能当天使用自己的手机签到签退一次。
     *判断是否保存当天日期 当天是否第一次使用
     * y 已签到过 {
     *     判断当天日期与现在日期是否相同
     *     y 相同 {
     *     判断当前账号与存储过的账号是否相同
     *     y 相同  签到
     *     n 不同  提示 签到功能不支持多个用户签到
     *     }
     *     n 不同 签到
     * }
     * n 直接签到
     *
     *
     * @param
     */

    String calendar_day;

    public void loadData(double longitude, double latitude, final String type, String memo) {
        String already_check = SharedPrefUtil.readCheckFlag(mActivity, "already_check");
        String already_day = SharedPrefUtil.readCheckFlag(mActivity, "already_day");
        Calendar calendar = Calendar.getInstance();
        calendar_day = Integer.toString(calendar.get(Calendar.DATE));//获取当前日



        if (TextUtils.isEmpty(already_day)) {  //当天未签到直接请求网络
            httpsData(longitude, latitude, type, memo);
        } else {
            if (calendar_day.equals(already_day)) {
                if (Integer.toString(emp_Id).equals(already_check)) {
                    httpsData(longitude, latitude, type, memo);
                } else {
                    AppToast.showShortText(mActivity, "考勤功能不支持多人打卡！");
                }
            } else {
                   httpsData(longitude, latitude, type, memo);
            }


        }


    }
    /**
     * 网络请求
     * emp_Id,cust_Id
     * cust_Id,emp_Id,longitude,latitude,type,memo（type=1为签到，2为签退，3为外勤）
     *
     * @param
     */
    private void httpsData(double longitude, double latitude, final String type, String memo) {

        String[] key = new String[]{"emp_Id", "cust_Id", "longitude", "latitude", "type", "memo"};
        String[] value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), longitude + "", latitude + "", type, memo};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(HttpUtil.sign, key, value, token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(HttpUtil.sign, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {


                        Logger.json(jsonObject.toString());

                        JSONObject object = null;
                        try {
                            object = new JSONObject(jsonObject.toString());
                            String message = object.getString("message");


                            if ("success".equals(message)) {
                                SharedPrefUtil.writeCheckFlag(mActivity, "already_check", Integer.toString(emp_Id));
                                SharedPrefUtil.writeCheckFlag(mActivity, "already_day", calendar_day);
                            }

                            switch (type) {
                                case "1":

                                    if ("success".equals(message)) {
                                        AppToast.showShortText(mActivity, "签到成功");

                                    } else {

                                        AppToast.showShortText(mActivity, message);
                                    }


                                    break;
                                case "2":

                                    if ("success".equals(message)) {
                                        AppToast.showShortText(mActivity, "签退成功");
                                    } else {
                                        AppToast.showShortText(mActivity, message);
                                    }
                                    break;
                                case "3":
                                    if ("success".equals(message)) {

                                        AppToast.showShortText(mActivity, "外勤成功");
                                    } else {
                                        AppToast.showShortText(mActivity, message);
                                    }
                                    break;

                                default:
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
    }

    private class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = mHandler.obtainMessage();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (mRunning);
        }

    }

    private Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long sysTime = System.currentTimeMillis();
                    String sysTimeStr = (String) DateFormat.format("yyyy-MM-dd  kk:mm:ss ", sysTime);
                    //   String sysTimeStr2 = CommonUtils.sdf.format(sysTime);

                    tvTime.setText(sysTimeStr); //更新时间
                    break;
            }

        }
    };


    /**
     * 初始化 MAP
     */
    private void initViewMap() {
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
        mMapView.showZoomControls(false);//隐藏原生的控制按钮
        mMapView.getMap().getUiSettings().setCompassEnabled(false); //隐藏指南针
    }


    private void initLocation() {
        mLocationClient = new LocationClient(mActivity);
        mlocationListenter = new MyLocationListener();
        mLocationClient.registerLocationListener(mlocationListenter);
        setLocationOption();
        //   mBaiduMap.setOnMapLoadedCallback(callbacks);
    }

    /**
     * 设置相关参数
     */
    private void setLocationOption() {
        // LocationClientOption option = new LocationClientOption();
        // option.setOpenGps(true);
        // option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        //
        // option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        // option.setScanSpan(1000);// 设置发起定位请求的间隔时间为5000ms
        // // option.disableCache(true);// 禁止启用缓存定位
        LocationClientOption option = new LocationClientOption();
        //option.setCoorType("bd09ll");
        option.setCoorType("bd09ll");//如果是百度坐标参数为 bd0911 bd09ll  bd09ll
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);

        mLocationClient.setLocOption(option);
    }

    /**
     * 定位到我的位置
     */
    private void centerToMyLocation() {
        //      mBaiduMap.setOnMapLoadedCallback(callbacks);
        LatLng latLng = new LatLng(mLatitude, mLongtitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);

    }

    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(data);


            ConversionUtil.Gps gps = ConversionUtil.bd09_To_Gcj02(location.getLongitude(), location.getLatitude());
            // 更新经纬度
            mLatitude = gps.lat;
            mLongtitude = gps.lon;


//            mLatitude  =39.9222447722;
//            mLongtitude =116.4589169868;


            if (isFirstIn) {
                LatLng latlang = new LatLng(mLatitude, mLongtitude);
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latlang);
                mBaiduMap.animateMapStatus(msu);
                String mMapPlace = location.getAddrStr();
                Log.d("matrix", "isFirstIn: " + isFirstIn + "   mMapPlace----" + mMapPlace);
                Log.d("matrix", "-mLatitude----" + mLatitude + "  mLongtitude : " + mLongtitude);
                isFirstIn = false;

            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mRunning = true;
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        Log.d("matrix", "onResume handler --");
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
        Log.d("matrix", "onPause handler --");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("matrix", "onStart handler --");

        // 开启定位
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted())
            mLocationClient.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mRunning = false;
        // 停止定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        Log.d("matrix", "onStop handler --");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mHandler.removeCallbacks(TimeThread.currentThread());
        Log.d("matrix", "onDestroy handler --");
        Log.d("Fragment", System.currentTimeMillis() + "    Fragment1");
    }


}
