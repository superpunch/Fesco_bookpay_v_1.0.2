package com.fesco.bookpay.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fesco.bookpay.adapter.ContactsFragmentAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.RestRecordBean;
import com.fesco.bookpay.fragment.RestApplicationFragment;
import com.fesco.bookpay.fragment.RestRecordFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 请假页面  提前加载请假记录列表 利用fragment与activity之间的回调后再传入另一个fragment
 * Created by gong.min on 2016/9/29.
 */
public class RestActivity extends BaseActivity {
    private List<String> listTitle = new ArrayList<>();
    private TabLayout mTabLayout;
    private Toolbar toolbar;
    private ViewPager mViewPager;
    private LoginEntity loginEntity;
    private TextView textView;

    public interface MyCallBack {//定义回调接口

        void callBack(RestRecordBean restRecordBean, boolean isLoad);//回调方法
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        loginEntity = (LoginEntity) getIntent().getExtras().getSerializable("RestActivity");

        initViews();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#00b6d8"));

        mTabLayout.setTabMode(TabLayout.MODE_FIXED); //填充
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("请假申请");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestActivity.this.finish();
            }
        });
        initViewPager();


    }


    private void initViewPager() {
        listTitle.add("休假申请");
        listTitle.add("休假记录");
        for (int i = 0; i < listTitle.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(listTitle.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        RestApplicationFragment restApplicationFragment = RestApplicationFragment.getInstance(loginEntity);
        final RestRecordFragment restRecordFragment = RestRecordFragment.getInstance(loginEntity);
        fragments.add(restApplicationFragment);
        fragments.add(restRecordFragment);

        restApplicationFragment.setCallBack(new MyCallBack() {
                                                @Override
                    public void callBack(RestRecordBean restRecordBean, boolean isLoad) {
                                                    restRecordFragment.showParam(restRecordBean,isLoad);
                                                }
                                            }
        );


        ContactsFragmentAdapter mFragmentAdapteradapter =
                new ContactsFragmentAdapter(getSupportFragmentManager(), fragments, listTitle);
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        //    mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);

        mTabLayout.setOnTabSelectedListener(new ViewPagerOnTabSelectedListenerClass(mViewPager));
        TabLayout.TabLayoutOnPageChangeListener listener =
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
        mViewPager.addOnPageChangeListener(listener);

        //  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE|WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public class ViewPagerOnTabSelectedListenerClass implements TabLayout.OnTabSelectedListener {
        private ViewPager mViewPager;

        public ViewPagerOnTabSelectedListenerClass(ViewPager mViewPager) {
            this.mViewPager = mViewPager;

        }

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewPager.setCurrentItem(tab.getPosition());
            if (0 == tab.getPosition()) {
                textView.setText("请假申请");
            } else textView.setText("请假记录");

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

}