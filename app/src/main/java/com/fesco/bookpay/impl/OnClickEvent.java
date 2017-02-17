package com.fesco.bookpay.impl;

import android.view.View;

/**
 * Created by gong.min on 2017/1/11.
 */
public abstract class OnClickEvent implements View.OnClickListener {

    public static long lastTime;

    public abstract void singleClick(View v);

    @Override
    public void onClick(View v) {
        if (onDoubClick()) {
            return;
        }
        singleClick(v);
    }

    public boolean onDoubClick() {
        boolean flag = false;
        long time = System.currentTimeMillis() - lastTime;

        if (time < 1000) {
            flag = true;
        }
        lastTime = System.currentTimeMillis();
        return flag;
    }
}