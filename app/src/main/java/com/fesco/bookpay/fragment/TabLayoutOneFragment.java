package com.fesco.bookpay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.base.BasePageFragment;
import com.fesco.bookpay.view.ItemRemoveRecyclerView;
import com.fesco.bookpay.view.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gong.min on 2016/10/11.
 */
public class TabLayoutOneFragment  extends BasePageFragment {
    private List<Integer> lists = new ArrayList<>();
    private ArrayList<String> mList;
    @Override
    public void fetchData() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ItemRemoveRecyclerView mRecyclerView =
                (ItemRemoveRecyclerView) inflater.inflate(R.layout.list_fragment_recycler, container, false);
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(i + "");
        }
        final MyAdapter adapter = new MyAdapter(mActivity, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setOnItemClickListener(new ItemRemoveRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mActivity, "** " + mList.get(position) + " **", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position) {
                adapter.removeItem(position);
            }
        });



        return mRecyclerView;
    }
















}
