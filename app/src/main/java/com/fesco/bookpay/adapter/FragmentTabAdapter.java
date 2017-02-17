package com.fesco.bookpay.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.SearchActivity;
import com.fesco.bookpay.entity.ContactsChangeBean;
import com.fesco.bookpay.util.ACache;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class FragmentTabAdapter implements RadioGroup.OnCheckedChangeListener {
    private List<Fragment> fragments; // tab页面对应Fragment
    private RadioGroup rgs; // 用于切换tab
    private FragmentActivity fragmentActivity; // Fragment�?��的Activity
    private int fragmentContentId; // Activity中所要被替换的区域的id

    private int currentTab; // 当前Tab页面索引

    private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener; // 用于让调用�?在切换tab时�?增加新的功能

    private TextView title;
    private TextView back;
    private ImageView search;
    public RadioButton rbWroid;
    public RadioButton rbHelp;
    private RadioButton rbCounsle;

    public void setRadioButton(RadioButton rbWroid, RadioButton rbHelp, RadioButton rbCounsle, TextView title, ImageView search, TextView back,final ACache aCache) {
        this.rbWroid = rbWroid;
        this.rbHelp = rbHelp;
        this.rbCounsle = rbCounsle;
        this.title = title;
        this.back = back;
        this.search = search;
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactsChangeBean contactsChangeBean = (ContactsChangeBean) aCache.getAsObject("contactsChangeBean");

                Intent inent = new Intent(fragmentActivity, SearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("empsBeanList", (Serializable) contactsChangeBean.getEmps());
                inent.putExtras(bundle);
                fragmentActivity.startActivity(inent);
            }
        });
    }

    public FragmentTabAdapter(final FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId, RadioGroup rgs) {
        this.fragments = fragments;
        this.rgs = rgs;
        this.fragmentActivity = fragmentActivity;
        this.fragmentContentId = fragmentContentId;

        // 默认显示第一�?
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(0));
        ft.commit();

        rgs.setOnCheckedChangeListener(this);


    }

    public void setCheckedChanged(int idx) {
        RadioButton rb = ((RadioButton) rgs.getChildAt(idx));
        rb.setChecked(true);
    }

    public void setRadioButtonText(int idx, String textString) {
        RadioButton rb = ((RadioButton) rgs.getChildAt(idx));
        if (rb != null) {
            rb.setText(textString);
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (rbWroid.getId() == checkedId) {
            title.setText("工作");
            search.setVisibility(View.GONE);
            back.setVisibility(View.VISIBLE);
        } else if (rbHelp.getId() == checkedId) {
            title.setText("社保");
            search.setVisibility(View.GONE);
            back.setVisibility(View.VISIBLE);
        } else if (rbCounsle.getId() == checkedId) {
            title.setText("通讯录");
            search.setVisibility(View.VISIBLE);
            back.setVisibility(View.GONE);
        }
        for (int i = 0; i < rgs.getChildCount(); i++) {
            if (rgs.getChildAt(i).getId() == checkedId) {
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction(i);

                getCurrentFragment().onPause(); // 暂停当前tab
//                getCurrentFragment().onStop(); // 暂停当前tab

                if (fragment.isAdded()) {
//                    fragment.onStart(); // 启动目标tab的onStart()
                    fragment.onResume(); // 启动目标tab的onResume()
                } else {
                    ft.add(fragmentContentId, fragment);
                }
                showTab(i); // 显示目标tab
                ft.commit();

                // 如果设置了切换tab额外功能功能接口
                if (null != onRgsExtraCheckedChangedListener) {
                    onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(radioGroup, checkedId, i);
                }

            }
        }

    }

    /**
     * 切换tab
     *
     * @param idx
     */
    private void showTab(int idx) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);

            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = idx; // 更新目标tab为当前tab
    }

    /**
     * 获取�?��带动画的FragmentTransaction
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
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

    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    public OnRgsExtraCheckedChangedListener getOnRgsExtraCheckedChangedListener() {
        return onRgsExtraCheckedChangedListener;
    }

    public void setOnRgsExtraCheckedChangedListener(OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
        this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
    }


    /**
     * 切换tab额外功能功能接口
     */
    public static class OnRgsExtraCheckedChangedListener {
        public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {

        }
    }

}
