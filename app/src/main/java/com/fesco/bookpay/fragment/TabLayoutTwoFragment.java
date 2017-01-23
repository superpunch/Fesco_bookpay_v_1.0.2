package com.fesco.bookpay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.base.BasePageFragment;

/**
 * Created by gong.min on 2016/10/11.
 */
public class TabLayoutTwoFragment extends BasePageFragment {


    @Override
    public void fetchData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView mRecyclerView =
                (RecyclerView) inflater.inflate(R.layout.list_fragment_recycler, container, false);
        return mRecyclerView;
    }
}
