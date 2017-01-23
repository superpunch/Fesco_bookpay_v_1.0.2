package com.fesco.bookpay.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fesco.bookpay.adapter.ContactsFragmentAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.ContactsBean;
import com.fesco.bookpay.entity.ContactsChangeBean;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.fragment.ContactsListFragment;
import com.fesco.bookpay.impl.PermissionListenter;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.kyloading.KyLoadingBuilder;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.weight.dialog.PermissionDialogInfo;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 通讯录模块
 * 使用TabLayout，CardView
 * 网络请求：数据源list 有过多暂无用的数据，解析取有效数据
 * 缓存：采用ACache来缓存数据
 * Created by gong.min on 2016/9/6.
 */
public class ContactsActivity extends BaseActivity {


 //   private LoginEntity loginEntity;
    private List<String> listTitle = new ArrayList<>();
    private Gson gson;
    private List<ContactsChangeBean.EmpsBean> empsBeanList;
    private TabLayout mTabLayout;
    private Toolbar toolbar;
    private ViewPager mViewPager;

    private SearchView searchView = null;
    private KyLoadingBuilder builder;

   // private ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        builder = new KyLoadingBuilder(this);
        ;
        //   builder.setText("正在加载中...");
        builder.setIcon(R.drawable.loading04);
        //builder.setOutsideTouchable(false);
        //builder.setBackTouchable(true);
        builder.show();
//        aCache = ACache.get(this);
//        loginEntity = (LoginEntity) aCache.getAsObject("loginEntity");
        gson = new Gson();
        initViews();
        loadData();
        operaTionPermission();
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
    public void operaTionPermission() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE
        };
        requestBasePermissions(permissions, new PermissionListenter() {
            @Override
            public void onGranted() {
                Log.i("Fragment", "onGranted 赵信");
            }

            @Override
            public void onDenied(List<String> denied) {
                PermissionDialogInfo permissionDialogInfo=new PermissionDialogInfo(context);
                permissionDialogInfo.setMessage("读写电话权限");
                permissionDialogInfo.show();

            }
        });
    }


    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setVisibility(View.GONE);
        mTabLayout.setBackgroundColor(Color.parseColor("#000000"));
       // mTabLayout.setBackgroundColor(0xFF000000); //setBackgroundColor(Color.rgb(0,122,0));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE); //在这个模式下能包含长标签和大量的tabs 正常显示
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("通讯录");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactsActivity.this.finish();
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        //  initViewPager();

    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_search:

                    Intent inent = new Intent(ContactsActivity.this, SearchActivity.class);
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
                new ContactsFragmentAdapter(getSupportFragmentManager(), fragments, listTitle);


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

        LoginEntity intentEntity= (LoginEntity) getIntent().getExtras().getSerializable("ContactsActivity");
        String token =intentEntity.getToken();
        int cust_Id =intentEntity.getCust_Id();

        HashMap<String,String> map= HttpOkManagerUtils. okManagerPost(HttpUtil.getAllPhoneNumber,Integer.toString(cust_Id),null,token);

        OKManager manager = OKManager.getInstance(this);
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
                        Toast.makeText(ContactsActivity.this, "服务器暂停数据，请联系管理员", Toast.LENGTH_SHORT).show();
                        builder.dismiss();

                    }



            }

        });

    }

    /**
     * 该方法是用来加载菜单布局的
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_search_ac).setVisible(false);//隐藏
        menu.findItem(R.id.action_search).setVisible(true);//显示
        return true;
    }

    /**
     *   HashMap<String, List<ContactsChangeBean.EmpsBean>>
     *  key: 部门名称，list<> 对应部门下的员工信息
     * @param contactsChangeBean
     */
    private void transFormEntity(ContactsChangeBean contactsChangeBean) {

      //  ACache aCache = ACache.get(ContactsActivity.this);
        aCache.put("contactsChangeBean", contactsChangeBean);//json

        empsBeanList = contactsChangeBean.getEmps();//List<Emps> 自定义员工属性 可改bean
        Logger.i(contactsChangeBean.toString());
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
        Logger.e(listTitle.toString());
        System.out.println("通讯录信息--------:" + hashmap);
        initViewPager(hashmap);
    }


}
