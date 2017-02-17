package com.fesco.bookpay.fragment.tabfragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.activity.SearchActivity;
import com.fesco.bookpay.adapter.ContactsFragmentAdapter;
import com.fesco.bookpay.base.BasePageFragment;
import com.fesco.bookpay.entity.ContactsBean;
import com.fesco.bookpay.entity.ContactsChangeBean;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.fragment.ContactsListFragment;
import com.fesco.bookpay.util.ACache;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.kyloading.KyLoadingBuilder;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gong.min on 2016/10/19.
 */
public class MyFragment extends BasePageFragment {
    private static final String KEY = "EXTRA";

    private View view;
    private List<String> listTitle = new ArrayList<>();
    private Gson gson;
    private List<ContactsChangeBean.EmpsBean> empsBeanList;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private KyLoadingBuilder builder;
    private ACache aCache;
    private LoginEntity loginEntity;

    public static MyFragment getInstance(LoginEntity flag) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        MyFragment myFragment = new MyFragment();
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            loginEntity = (LoginEntity) bundle.getSerializable(KEY);
        }


        if (view == null) {
            view = inflater.inflate(R.layout.activity_tab_layout_contacts, container, false);
        }
        builder = new KyLoadingBuilder(getActivity());
        ;
        //   builder.setText("正在加载中...");
        builder.setIcon(R.drawable.loading04);
        builder.show();
        aCache = ACache.get(mActivity);
        gson = new Gson();
        initViews();
        loadData();
       // operaTionPermission();
        return view;
    }






    private void loadData() {
        ContactsChangeBean contactsChangeBean = (ContactsChangeBean) aCache.getAsObject("contactsChangeBean");
        if (contactsChangeBean != null) {
            transFormEntity(contactsChangeBean);
            Logger.e("缓存Data");
        } else {
            Logger.e("网络data");
            initdata();
        }
    }

//    public void operaTionPermission() {
//        String[] permissions = new String[]{
//                Manifest.permission.CALL_PHONE
//        };
//        requestBasePermissions(permissions, new PermissionListenter() {
//            @Override
//            public void onGranted() {
//                Log.i("Fragment", "onGranted 赵信");
//            }
//
//            @Override
//            public void onDenied(List<String> denied) {
//                PermissionDialogInfo permissionDialogInfo = new PermissionDialogInfo(mActivity);
//                permissionDialogInfo.setMessage("读写电话权限");
//                permissionDialogInfo.show();
//
//            }
//        });
//    }


    private void initViews() {
        mTabLayout = (TabLayout)view.findViewById(R.id.tabs);
        mTabLayout.setVisibility(View.GONE);
        mTabLayout.setBackgroundColor(Color.parseColor("#000000"));
        // mTabLayout.setBackgroundColor(0xFF000000); //setBackgroundColor(Color.rgb(0,122,0));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE); //在这个模式下能包含长标签和大量的tabs 正常显示
        mViewPager = (ViewPager)view. findViewById(R.id.viewpager);

        //  initViewPager();

    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_search:

                    Intent inent = new Intent(mActivity, SearchActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("empsBeanList", (Serializable) empsBeanList);
                    inent.putExtras(bundle);
                    startActivity(inent);
                    break;


            }


            return true;
        }
    };


    private void initViewPager(HashMap<String, List<ContactsChangeBean.EmpsBean>> hashmap) {


        for (int i = 0; i < listTitle.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(listTitle.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < listTitle.size(); i++) {

            fragments.add(ContactsListFragment.getInstance(hashmap.get(listTitle.get(i))));
        }
        ContactsFragmentAdapter mFragmentAdapteradapter =
                new ContactsFragmentAdapter(getFragmentManager(), fragments, listTitle);


        //   mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
        //     mTabLayout.setOnTabSelectedListener(new ViewPagerOnTabSelectedListenerClass(mViewPager));
        mTabLayout.setVisibility(View.VISIBLE);
        builder.dismiss();
    }


    /**
     * 网络请求
     */
    private void initdata() {
        String token = loginEntity.getToken();
        int cust_Id = loginEntity.getCust_Id();

        HashMap<String, String> map = HttpOkManagerUtils.okManagerPost(HttpUtil.getAllPhoneNumber, Integer.toString(cust_Id), null, token);

        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(HttpUtil.getAllPhoneNumber, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ContactsBean contactsBean = gson.fromJson(jsonObject.toString(), ContactsBean.class);
                String message = contactsBean.getMessage();
                if (message != null && message.length() > 0 && message.equals("success")) {
                    ContactsChangeBean contactsChangeBean = gson.fromJson(jsonObject.toString(), ContactsChangeBean.class);
                    transFormEntity(contactsChangeBean);
                } else {
                    //message返回Error  数据为空
                    AppToast.showShortText(mActivity,"服务器暂停数据，请联系管理员");
                    builder.dismiss();

                }


            }

        });

    }



    /**
     * HashMap<String, List<ContactsChangeBean.EmpsBean>>
     * key: 部门名称，list<> 对应部门下的员工信息
     *
     * @param contactsChangeBean
     */
    private void transFormEntity(ContactsChangeBean contactsChangeBean) {

        aCache.put("contactsChangeBean", contactsChangeBean);//json

        empsBeanList = contactsChangeBean.getEmps();//List<Emps> 自定义员工属性 可改bean

        HashMap<String, List<ContactsChangeBean.EmpsBean>> hashmap = new HashMap<String, List<ContactsChangeBean.EmpsBean>>();
        for (ContactsChangeBean.EmpsBean cce : empsBeanList) {

            if (hashmap.keySet().contains(cce.getGroup_Name())) {
                hashmap.get(cce.getGroup_Name()).add(cce);//引用传递 执行同一个地址
            } else {
                listTitle.add(cce.getGroup_Name());
                List<ContactsChangeBean.EmpsBean> contacsEmpsBeanList = new ArrayList<ContactsChangeBean.EmpsBean>();
                contacsEmpsBeanList.add(cce);
                hashmap.put(cce.getGroup_Name(), contacsEmpsBeanList);
            }
        }


        initViewPager(hashmap);
    }


    /**
     * 可见时执行网络请求
     */
    @Override
    public void fetchData() {
    }




}
