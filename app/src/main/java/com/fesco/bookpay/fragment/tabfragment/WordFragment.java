package com.fesco.bookpay.fragment.tabfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.fesco.bookpay.activity.ApprovalActivity;
import com.fesco.bookpay.activity.AttendanceActivity;
import com.fesco.bookpay.activity.InforPersonActivity;
import com.fesco.bookpay.activity.OvertimeActivity;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.activity.RankOutLateActivity;
import com.fesco.bookpay.activity.RankOverTimeActivity;
import com.fesco.bookpay.activity.ReimbursementActivity;
import com.fesco.bookpay.activity.RestActivity;
import com.fesco.bookpay.activity.StatisticActivity;
import com.fesco.bookpay.adapter.GridViewAdapter;
import com.fesco.bookpay.adapter.cycleviewadapter.CycleViewPager;
import com.fesco.bookpay.entity.ADInfo;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.util.ViewFactory;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gong.min on 2016/10/19.
 */
public class WordFragment extends Fragment {
    private static final String KEY = "EXTRA";

    private View view;
    private GridView gridview;
    private Context context;
    private Activity mActivity;
    private LoginEntity loginEntity;

    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private CycleViewPager cycleViewPager;
//    private String[] imageUrls = {"http://i1.piimg.com/1949/e54063875e47230b.png",
//            "http://i1.piimg.com/1949/726e6d5c55305d67.png",
//            "http://i1.piimg.com/1949/28dc4917e61a584d.png"
//    };
    private String[] imageUrls = {"http://i1.piimg.com/1949/58d5d99fb0d400f6.png",
            "http://i1.piimg.com/1949/5004d73d3131baa4.png",
            "http://i1.piimg.com/1949/ca69ccaf0058968c.png"
    };
    public static WordFragment getInstance(LoginEntity flag) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        WordFragment wordFragment = new WordFragment();
        wordFragment.setArguments(bundle);
        return wordFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            loginEntity = (LoginEntity) bundle.getSerializable(KEY);
        }

        if (view == null) {
            /// *** Caused by: android.view.InflateException: Binary XML file line #7: Error inflating class fragment
            view = inflater.inflate(R.layout.tab_bottom_word, container, false);
            initGridview(view);
            configImageLoader();
            initialize(view);

        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.mActivity = (Activity) context;

    }

    private void initGridview(View view) {
        gridview = (GridView) view.findViewById(R.id.gridView);
        gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridview.setAdapter(new GridViewAdapter(mActivity));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent inentInfor = new Intent(mActivity, InforPersonActivity.class);
                        Bundle bundleInfor = new Bundle();
                        bundleInfor.putSerializable("InformationActivity", loginEntity);
                        inentInfor.putExtras(bundleInfor);
                        startActivity(inentInfor);
                        break;
                    case 1:
                        Intent inentAttend = new Intent(mActivity, AttendanceActivity.class);
                        Bundle bundleAttend = new Bundle();
                        bundleAttend.putSerializable("AttendanceActivity", loginEntity);
                        inentAttend.putExtras(bundleAttend);
                        startActivity(inentAttend);
                        break;
                    case 2:
                        Intent inentRest = new Intent(mActivity, RestActivity.class);
                        Bundle bundleRest = new Bundle();
                        bundleRest.putSerializable("RestActivity", loginEntity);
                        inentRest.putExtras(bundleRest);
                        startActivity(inentRest);
                        break;
                    case 3:
                        Intent inentApproval = new Intent(mActivity, ApprovalActivity.class);
                        startActivity(inentApproval);
                        break;
                    case 4:
                        Intent inentOver = new Intent(mActivity, OvertimeActivity.class);
                        Bundle bundleOver = new Bundle();
                        bundleOver.putSerializable("OvertimeActivity", loginEntity);
                        inentOver.putExtras(bundleOver);
                        startActivity(inentOver);
                        break;
//                    case 5:
//                        Intent inentContact = new Intent(mActivity, ContactsActivity.class);
//                        Bundle bundleCotact = new Bundle();
//                        bundleCotact.putSerializable("ContactsActivity", loginEntity);
//                        inentContact.putExtras(bundleCotact);
//                        startActivity(inentContact);
//                        break;
                    case 5:
                        Intent inentSatis = new Intent(mActivity, StatisticActivity.class);
                        Bundle bundleStatis = new Bundle();
                        bundleStatis.putSerializable("StatisticActivity", loginEntity);
                        inentSatis.putExtras(bundleStatis);
                        startActivity(inentSatis);
                        break;
                    case 6:
                        Intent inentOutLate = new Intent(mActivity, RankOutLateActivity.class);
                        Bundle bundleOutLate = new Bundle();
                        bundleOutLate.putSerializable("RankOutLateActivity", loginEntity);
                        inentOutLate.putExtras(bundleOutLate);
                        startActivity(inentOutLate);
                        break;
                    case 7:
                        Intent inentOverTime = new Intent(mActivity, RankOverTimeActivity.class);
                        Bundle bundleOverTime = new Bundle();
                        bundleOverTime.putSerializable("RankOverTimeActivity", loginEntity);
                        inentOverTime.putExtras(bundleOverTime);
                        startActivity(inentOverTime);
                        break;
                    case 8:
                        Intent inentRbm = new Intent(mActivity, ReimbursementActivity.class);
//                        Bundle bundleRbm = new Bundle();
//                        bundleRbm.putSerializable("ReimbursementActivity", loginEntity);
//                        inentRbm.putExtras(bundleRbm);
                        startActivity(inentRbm);
                        break;
//                    case 10:
//
//                        showDialog();
//
//
//                        break;
//                    case 11:
//                        Intent ii = new Intent(mActivity, ScrollViewActivity.class);
//                        startActivity(ii);
//                        break;
//                    case :
//                        Intent inentTab = new Intent(mActivity, TabLayoutActivity.class);
//                        Bundle bundleTab = new Bundle();
//                        bundleTab.putSerializable("OvertimeActivity", loginEntity);
//                        inentTab.putExtras(bundleTab);
//                        startActivity(inentTab);
//                        break;

                }


            }
        });
    }
    /**
     * 这是兼容的 AlertDialog
     */
    private void showDialog() {
  /*
  这里使用了 android.support.v7.app.AlertDialog.Builder
  可以直接在头部写 import android.support.v7.app.AlertDialog
  那么下面就可以写成 AlertDialog.Builder
  */
      AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mActivity);
        builder.setTitle("Material Design 11111111Dialog");
        builder.setMessage("这是 android.support.v7.app.AlertDialog 中的样式");
        builder.setNegativeButton("取消222222", null);
        builder.setPositiveButton("确定111111111", null);
        builder.create();
        builder.show();
    }

    private void initialize(View view) {

        cycleViewPager = (CycleViewPager) mActivity.getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);

        for (int i = 0; i < imageUrls.length; i++) {
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i);
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(mActivity, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(mActivity, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(mActivity, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
//                Toast.makeText(mActivity,
//                        "position-->" + info.getContent(), Toast.LENGTH_SHORT)
//                        .show();
            }

        }

    };

    /**
     * 配置ImageLoder
     */
    private void configImageLoader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mActivity.getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }


}
