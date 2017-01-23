package com.fesco.bookpay.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by gong.min on 2017/1/11.
 */
public class BillRecyclerView extends RecyclerView {


    public BillRecyclerView(Context context) {
        super(context);
    }

    public BillRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BillRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }


}
