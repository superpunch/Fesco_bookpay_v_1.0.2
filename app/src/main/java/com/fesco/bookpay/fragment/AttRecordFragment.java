package com.fesco.bookpay.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.adapter.AttRecordAdapter;
import com.fesco.bookpay.base.BasePageFragment;
import com.fesco.bookpay.entity.AttRecordBean;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.NetworkUtils;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.weight.SampleHeader;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.util.RecyclerViewUtils;
import com.github.jdsjlzx.view.LoadingFooter;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gong.min on 2016/9/7.
 */
public class AttRecordFragment extends BasePageFragment {

    private static final String TAG = "lzx";

    /**
     * 服务器端一共多少条数据
     */
    private static int TOTAL_COUNTER = 64;

    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;
    /**
     * 页码
     */
    private static int pageNumber=1;
    /**
     * 已经获取到多少条数据了
     */
    private static int mCurrentCounter = 0;

    private LRecyclerView mRecyclerView = null;

    private AttRecordAdapter mDataAdapter = null;

    private PreviewHandler mHandler = new PreviewHandler(this, mActivity);
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;

    private boolean isRefresh = false;


    private static final String KEY = "EXTRA";
    private LoginEntity flag;
    private int emp_Id;
    private String token;
    // private RecyclerView mRecyclerView;
    private Gson gson;
    private List<AttRecordBean.ListBean> listBeen;


    public static AttRecordFragment getInstance(LoginEntity flag) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        AttRecordFragment attRecordFragment = new AttRecordFragment();
        attRecordFragment.setArguments(bundle);
        return attRecordFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            flag = (LoginEntity) bundle.getSerializable(KEY);
            emp_Id = flag.getEmp_Id();
            token = flag.getToken();
        }

        if (mRecyclerView == null) {
//            mRecyclerView =
//                    (RecyclerView) inflater.inflate(R.layout.list_fragment, container, false);
//            System.out.println("2   onCreateView-------------------");

        }

        gson = new Gson();
        Log.i("Fragment", "马丹----初始化-View---Fragment2   " + mRecyclerView);
        View mRefreView = inflater.inflate(R.layout.sample_ll_activity, container, false);
        mRecyclerView = (LRecyclerView) mRefreView.findViewById(R.id.list);
        //  mDataAdapter = new DataAdapter(this);


        mDataAdapter = new AttRecordAdapter(mActivity);


        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mDataAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        RecyclerViewUtils.setHeaderView(mRecyclerView, new SampleHeader(mActivity));

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
           //     mDataAdapter.clear();
           //     mLRecyclerViewAdapter.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
                mCurrentCounter = 0;
                pageNumber=1;
                isRefresh = true;
                requestData();
            }

            @Override
            public void onScrollUp() {
            }

            @Override
            public void onScrollDown() {
            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    Log.d(TAG, "the state is Loading, just wait..");
                    return;
                }

                if (mCurrentCounter < TOTAL_COUNTER) {
                    // loading more
                    RecyclerViewStateUtils.setFooterViewState(mActivity, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
                    pageNumber++;
                    requestData();
                } else {
                    //the end
                    RecyclerViewStateUtils.setFooterViewState(mActivity, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.TheEnd, null);

                }
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {
            }

            @Override
            public void onScrollStateChanged(int state) {

            }

        });


  //      mRecyclerView.setRefreshing(true);  //默认刷新onRefresh();

        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //     ItemModel item = mDataAdapter.getDataList().get(position);
                //      AppToast.showShortText(mActivity, item.title);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //       ItemModel item = mDataAdapter.getDataList().get(position);
                //       AppToast.showShortText(mActivity, "onItemLongClick - " + item.title);
            }
        });


        return mRecyclerView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


    }

    /**
     * 可见时执行网络请求
     */
    @Override
    public void fetchData() {

        mRecyclerView.setRefreshing(true);  //默认刷新onRefresh();
     //   requestData();
        Log.i("Fragment", "马丹----初始化-View---fetchData   " + isVisibleToUser);


    }


    /**
     * 网络请求
     * pageNum页码,pageSize每页条数
     *
     * @param loadEmpInfo
     */
    AttRecordAdapter attRecordAdapter;

    public void loadData(String loadEmpInfo , final int pageNumber) {

        String[] key = new String[]{"emp_Id", "pageNum", "pageSize"};
        String[] value = new String[]{emp_Id + "", pageNumber + "", 10 + ""};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                 Logger.json(jsonObject.toString());

                AttRecordBean attRecordBean = gson.fromJson(jsonObject.toString(), AttRecordBean.class);
                if(!attRecordBean.getMessage().equals("error")){

                listBeen = attRecordBean.getList();
                TOTAL_COUNTER=Integer.valueOf(attRecordBean.getCount());
                //模拟一下网络请求失败的情况
                if (NetworkUtils.isNetAvailable(mActivity)) {
                    mHandler.sendEmptyMessage(-1);
                } else {
                    mHandler.sendEmptyMessage(-3);
                }
                }
                Log.d(TAG, "TOTAL_COUNTER总数 " +TOTAL_COUNTER +" 当前页面 "+ pageNumber);
            }
                }
        );
    }

    @Override
    public void onDestroy() {
        Log.d("Fragment", System.currentTimeMillis() + "     Fragment2");
        super.onDestroy();
    }

    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    /**
     * 添加数据
     */
    private void addItems() {
        //  listBeen  数据源
        mDataAdapter.addAll(listBeen);
        mCurrentCounter += listBeen.size();


    }

    private static class PreviewHandler extends Handler {

        private WeakReference<AttRecordFragment> ref;
        private Activity mActivity;

        PreviewHandler(AttRecordFragment activity, Activity mActivity) {
            ref = new WeakReference<>(activity);
            this.mActivity = mActivity;
        }

        @Override
        public void handleMessage(Message msg) {
            final AttRecordFragment activity = ref.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {

                case -1:
                    if (activity.isRefresh) {
                        activity.mDataAdapter.clear();
                        mCurrentCounter = 0;
                    }

                    int currentSize = activity.mDataAdapter.getItemCount();

                    activity.addItems();

                    if (activity.isRefresh) {
                        activity.isRefresh = false;
                        activity.mRecyclerView.refreshComplete();
                    }

                    RecyclerViewStateUtils.setFooterViewState(activity.mRecyclerView, LoadingFooter.State.Normal);
                    activity.notifyDataSetChanged();
                    break;
                case -2:
                    activity.notifyDataSetChanged();
                    break;
                case -3:
                    if (activity.isRefresh) {
                        activity.isRefresh = false;
                        activity.mRecyclerView.refreshComplete();
                    }
                    activity.notifyDataSetChanged();
                    RecyclerViewStateUtils.setFooterViewState(mActivity, activity.mRecyclerView, REQUEST_COUNT, LoadingFooter.State.NetWorkError, activity.mFooterClick);
                    break;
                default:
                    break;
            }
        }
    }

    private View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewStateUtils.setFooterViewState(mActivity, mRecyclerView, REQUEST_COUNT, LoadingFooter.State.Loading, null);
            requestData();
        }
    };

    /**
     * 模拟请求网络
     */
    private void requestData() {
        Log.d(TAG, "requestData");
        loadData(HttpUtil.getCedList,pageNumber);

    }
}