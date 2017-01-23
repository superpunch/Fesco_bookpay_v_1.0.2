package com.fesco.bookpay.activity;

import android.content.Intent;
import android.os.Bundle;

import com.fesco.bookpay.base.BaseFragment;
import com.fesco.bookpay.base.BaseSecondActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.fragment.InformationFragment;

/**
 * Fragment流程页面与Activity组合
 * Created by gong.min on 2016/9/13.
 */
public class InformationActivity extends BaseSecondActivity  implements BaseFragment.BackHandledInterface {
    private LoginEntity loginEntity;
    @Override
    protected BaseFragment getFirstFragment() {

        return  InformationFragment.newInstance(loginEntity);

    }


    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        Bundle bundle=getIntent().getExtras();
        if(null != bundle){
            loginEntity= (LoginEntity) bundle.getSerializable("InformationActivity");
        }


    }

    private BaseFragment mBackHandedFragment;
    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {
        this.mBackHandedFragment=selectedFragment;
    }
    @Override
    public void onBackPressed() {
        if(mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()){
            if(getSupportFragmentManager().getBackStackEntryCount() == 1){
                super.onBackPressed();
            }else{
                getSupportFragmentManager().popBackStack();
            }
        }
    }

}
