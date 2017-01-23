package com.fesco.bookpay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fesco.bookpay.adapter.ContactsListAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.ContactsChangeBean;
import com.fesco.bookpay.util.other.MyDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 通讯录搜索模块
 * 人名称进行检索
 * Created by gong.min on 2016/9/9.
 */
public class SearchActivity extends BaseActivity {
    private List<ContactsChangeBean.EmpsBean> empsBeanList;
    public RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ContactsListAdapter recyclerViewAdapter;

    private  Toolbar toolbar;
    private  SearchView searchView;
    private List<ContactsChangeBean.EmpsBean> empsBeanSearchList = new ArrayList<ContactsChangeBean.EmpsBean>();
  //  List<String> stringList=new ArrayList<>();
 //   private  List<String>    mData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tab_recycler);
        initViews();
        empsBeanList = (List<ContactsChangeBean.EmpsBean>) getIntent().getSerializableExtra("empsBeanList");

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if(empsBeanList !=null)
        recyclerViewAdapter = new ContactsListAdapter(this, empsBeanList);//显示所有数据
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));

    }


    private void initViews() {
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("通讯录");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }


    private Toolbar.OnMenuItemClickListener onMenuItemClickListener=new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.action_search_ac:
                    Log.e("wzj", "action_search_ac-->");
                    break;



            }

            return true;
        }
    };

    /**
     * 该方法是用来加载菜单布局的
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_search).setVisible(false);//隐藏
        menu.findItem(R.id.action_search_ac).setVisible(true);//显示
        //用MenuItem的`getActionView()`方法获取`android:actionViewClass`对应的实例,这里是`android.widget.SearchView`
        searchView = (SearchView) menu.findItem(R.id.action_search_ac).getActionView();

        searchView.setSubmitButtonEnabled(true);//是否显示确认搜索按钮
//        searchView.setIconifiedByDefault(true);//设置展开后图标的样式,这里只有两种,一种图标在搜索框外,一种在搜索框内
//        searchView.setIconified(false);//设置
//        searchView.clearFocus();//清除焦点
        /*
        这里是重点,SearchView并没有提供样式的修改方法,所以只能
        1.用获取到的实例调用getContext()方法,返回当前view的上下文
        2.调用getResources()方法,获取该view的资源实例(Return a Resources instance)
        3.调用getIdentifier()方法,获取相同名字的ID,(Return a resource identifier for the given resource name)
        4.通过findViewById()获取该ID的实例,然后就可以做相应的操作了
        */
//        int search_mag_icon_id = searchView.getContext().getResources().getIdentifier("android:id/search_mag_icon", null, null);
//        ImageView search_mag_icon = (ImageView)searchView.findViewById(search_mag_icon_id);//获取搜索图标
//        search_mag_icon.setImageResource(R.mipmap.ic_search_white_24dp);//图标都是用src的

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.e("wzj", "关闭文本-->");
                recyclerViewAdapter = new ContactsListAdapter(SearchActivity.this, empsBeanList);//测试

                mRecyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //当点击搜索按钮,输入法搜索按钮,会触发这个方法.在这里做相应的搜索事件,query为用户输入的值
                //当输入框为空或者""时,此方法没有被调用
                Log.e("wzj", "搜索文本-->"+s);


                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //当输入的文字发生变化的时候,会触发这个方法.在这里做匹配提示的操作等
                Log.e("wzj", "当输入的文字发生变化的时候,会触发这个方法-->"+s);
                empsBeanSearchList.clear();
//                  for(String data:mData){
//                      if(data.contains(s)){
//                          stringList.ValidateCode(data);
//                      }
//
//                  }
//                Log.e("wzj", "-stringList---->"+stringList.toString());
                for( ContactsChangeBean.EmpsBean empsBean:   empsBeanList){
                    if(empsBean.getEmp_Name().contains(s)){
                        empsBeanSearchList.add(empsBean);
                    }
                }


                Log.e("wzj", "-stringList---->"+empsBeanSearchList.toString());

                recyclerViewAdapter = new ContactsListAdapter(SearchActivity.this, empsBeanSearchList);//测试

                mRecyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();





//                for(ContactsChangeBean.EmpsBean empsBean:empsBeanList){
//                    if(empsBean.getEmp_Name().contains(s)){
//
//                        empsBeanLists.ValidateCode(empsBean);
//
//
//                    }
//
//                }
//                Log.e("wzj", "-empsBeanList---->"+empsBeanLists.toString());

                return false;
            }
        });
//        String str=searchView.getQuery().toString();

        return true;
    }




}
