package com.fesco.bookpay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 
 */
public class FragmentTabAdapter implements RadioGroup.OnCheckedChangeListener{
    private List<Fragment> fragments; // tab页面对应Fragment
    private RadioGroup rgs; // 用于切换tab
    private FragmentActivity fragmentActivity; // Fragment�?��的Activity
    private int fragmentContentId; // Activity中所要被替换的区域的id

    private int currentTab; // 当前Tab页面索引

    private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener; // 用于让调用�?在切换tab时�?增加新的功能

    private TextView title;
    public FragmentTabAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId, RadioGroup rgs, TextView title) {
        this.fragments = fragments;
        this.rgs = rgs;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;
        this.title=title;
        // 默认显示第一�?
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(0));
        ft.commit();

        rgs.setOnCheckedChangeListener(this);


    }
    public void setCheckedChanged(int idx){
    	RadioButton rb = ((RadioButton)rgs.getChildAt(idx));
        rb.setChecked(true);
    }
    public void setRadioButtonText(int idx,String textString ){
    	RadioButton rb = ((RadioButton)rgs.getChildAt(idx));
    	if(rb!=null){
    		 rb.setText(textString);
    	}
       
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        switch (checkedId) {
            case 0:
                title.setText("首页");
                break;
            case 1:
                title.setText("自助");
                break;
            case 2:
                title.setText("咨询");
                break;
            case 3:
                title.setText("工具");
                break;
            case 4:
                title.setText("我的");
                break;
        }


        for(int i = 0; i < rgs.getChildCount(); i++){
            if(rgs.getChildAt(i).getId() == checkedId){
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction(i);

                getCurrentFragment().onPause(); // 暂停当前tab
//                getCurrentFragment().onStop(); // 暂停当前tab

                if(fragment.isAdded()){
//                    fragment.onStart(); // 启动目标tab的onStart()
                    fragment.onResume(); // 启动目标tab的onResume()
                }else{
                    ft.add(fragmentContentId, fragment);
                }
                showTab(i); // 显示目标tab
                ft.commit();

                // 如果设置了切换tab额外功能功能接口
                if(null != onRgsExtraCheckedChangedListener){
                    onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(radioGroup, checkedId, i);
                }

            }
        }

    }

    /**
     * 切换tab
     * @param idx
     */
    private void showTab(int idx){
        for(int i = 0; i < fragments.size(); i++){
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);

            if(idx == i){
                ft.show(fragment);
            }else{
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = idx; // 更新目标tab为当前tab
    }

    /**
     * 获取�?��带动画的FragmentTransaction
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index){
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
//        // 设置切换动画
//        if(index > currentTab){
//            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
//        }else{
//            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
//        }
        return ft;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public Fragment getCurrentFragment(){
        return fragments.get(currentTab);
    }

    public OnRgsExtraCheckedChangedListener getOnRgsExtraCheckedChangedListener() {
        return onRgsExtraCheckedChangedListener;
    }

    public void setOnRgsExtraCheckedChangedListener(OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
        this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
    }

    /**
     *  切换tab额外功能功能接口
     */
    public static class OnRgsExtraCheckedChangedListener{
        public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index){

        }
    }

}
