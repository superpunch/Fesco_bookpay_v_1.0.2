package com.fesco.bookpay.activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.fesco.bookpay.adapter.ContactsFragmentAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.fragment.AttMapCheckFragment;
import com.fesco.bookpay.fragment.AttPatchApplicationFragment;
import com.fesco.bookpay.fragment.AttPatchRecordFragment;
import com.fesco.bookpay.fragment.AttRecordFragment;
import com.fesco.bookpay.impl.PermissionListenter;
import com.fesco.bookpay.weight.dialog.PermissionDialogInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 考勤模块
 * 主要包含 考勤，考勤记录，补签申请，补签记录
 * Fragment预计加载onCreateView，可见时加载网络数据
 * 自定义View百度地图界面
 * 使用LRecyclerView 处理上下刷新
 * 接口同一使用 数组拼接 来完成对 sign，jsonParam的Value的值
 * Created by gong.min on 2016/9/21.
 */
public class AttendanceActivity extends BaseActivity {
    private List<String> listTitle = new ArrayList<>();
    private TabLayout mTabLayout;
    private Toolbar toolbar;
    private ViewPager mViewPager;
    private LoginEntity loginEntity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_tab_layout);
        loginEntity = (LoginEntity) getIntent().getExtras().getSerializable("AttendanceActivity");
        operaTionPermission();
        initViews();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setBackgroundColor(Color.parseColor("#ffffff"));
      //  mTabLayout.setBackgroundColor(0xFF000000); //setBackgroundColor(Color.rgb(0,122,0));
        mTabLayout.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#00b6d8"));
        mTabLayout.setTabMode(TabLayout.MODE_FIXED); //填充
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(4);
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("考勤");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttendanceActivity.this.finish();
            }
        });
          initViewPager();


    }
    public void operaTionPermission() {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
        };
        requestBasePermissions(permissions, new PermissionListenter() {
            @Override
            public void onGranted() {
                Log.e("Fragment", "onGranted 赵---信");
            }
            @Override
            public void onDenied(List<String> denied) {
                PermissionDialogInfo permissionDialogInfo=new PermissionDialogInfo(context);
                permissionDialogInfo.setMessage("定位权限");
                permissionDialogInfo.show();

            }
        });
    }

    private void initViewPager() {
        listTitle.add("考勤");
        listTitle.add("考勤记录");
        listTitle.add("补签申请");
        listTitle.add("补签记录");
        for (int i = 0; i < listTitle.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(listTitle.get(i)));
        }
         List<Fragment> fragments = new ArrayList<>();

            fragments.add(AttMapCheckFragment.getInstance(loginEntity));
            fragments.add(AttRecordFragment.getInstance(loginEntity));
            fragments.add(AttPatchApplicationFragment.getInstance(loginEntity));
            fragments.add(AttPatchRecordFragment.getInstance(loginEntity));


        ContactsFragmentAdapter mFragmentAdapteradapter =
                new ContactsFragmentAdapter(getSupportFragmentManager(), fragments, listTitle);


        //   mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
        //     mTabLayout.setOnTabSelectedListener(new ViewPagerOnTabSelectedListenerClass(mViewPager));


    }
}
