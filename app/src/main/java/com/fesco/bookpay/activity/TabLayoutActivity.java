package com.fesco.bookpay.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fesco.bookpay.adapter.ContactsFragmentAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.fragment.TabLayoutOneFragment;
import com.fesco.bookpay.fragment.TabLayoutTwoFragment;
import com.fesco.bookpay.view.TabViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 加班页面
 * Created by gong.min on 2016/9/29.
 */
public class TabLayoutActivity extends BaseActivity {
    private List<String> listTitle = new ArrayList<>();
    private TabLayout mTabLayout;
    private Toolbar toolbar;
    private TabViewPager mViewPager;
    private LoginEntity loginEntity;

    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_viewpager);
        loginEntity = (LoginEntity) getIntent().getExtras().getSerializable("OvertimeActivity");

        initViews();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        //  mTabLayout.setBackgroundColor(0xFF000000); //setBackgroundColor(Color.rgb(0,122,0));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#00b6d8"));

        mTabLayout.setTabMode(TabLayout.MODE_FIXED); //填充
        mViewPager = (TabViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("加班申请");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabLayoutActivity.this.finish();
            }
        });
        initViewPager();


    }


    private void initViewPager() {
        listTitle.add("加班申请");
        listTitle.add("加班记录");
        for (int i = 0; i < listTitle.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(listTitle.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new TabLayoutTwoFragment());
        fragments.add(new TabLayoutOneFragment());

        ContactsFragmentAdapter mFragmentAdapteradapter =
                new ContactsFragmentAdapter(getSupportFragmentManager(), fragments, listTitle);
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
     //  mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                if(0==tab.getPosition()){
                    textView.setText("加班申请");
                }else

                    textView.setText("加班记录");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
         TabLayout.TabLayoutOnPageChangeListener listener =
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout);
        mViewPager.addOnPageChangeListener(listener);
    }
}