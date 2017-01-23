package com.fesco.bookpay.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.fesco.bookpay.impl.BackHandledInterface;

/**
 * Created by gong.min on 2016/9/22.
 */
public abstract class BasePageBackFragment extends Fragment {

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    public Activity mActivity;
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.mActivity = (AttendanceActivity) activity;
//
//
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
        System.out.println("BasePageFragment    onAttach--------"+mActivity);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
     //   System.out.println("onActivityCreated-----------");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        System.out.println("setUserVisibleHint----------"+isVisibleToUser);
        prepareFetchData();
    }

    public abstract void fetchData();

    public boolean prepareFetchData() {
        return prepareFetchData(true);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            System.out.println("setUserVisibleHint+prepareFetchData：" + isVisibleToUser + "----"+isViewInitiated+"-------");
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }



    protected BackHandledInterface mBackHandledInterface;

    /**
     * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
     * FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件
     * 如果没有Fragment消息时FragmentActivity自己才会消费该事件
     */
  public abstract boolean onBackPressed();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!(getActivity() instanceof BackHandledInterface)){
            throw new ClassCastException("Hosting Activity must implement BackHandledInterface");
        }else{
            this.mBackHandledInterface = (BackHandledInterface)getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //告诉FragmentActivity，当前Fragment在栈顶
        mBackHandledInterface.setSelectedFragment(this);
    }







}