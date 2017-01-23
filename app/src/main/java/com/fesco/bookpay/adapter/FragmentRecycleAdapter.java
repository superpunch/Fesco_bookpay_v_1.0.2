package com.fesco.bookpay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.base.ListBaseAdapter;
import com.fesco.bookpay.entity.itembean.ItemModel;

/**
 * Created by gong.min on 2016/9/28.
 */
public class FragmentRecycleAdapter extends ListBaseAdapter<ItemModel> {

        private LayoutInflater mLayoutInflater;

        public FragmentRecycleAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
            mContext = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_text, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ItemModel item = mDataList.get(position);


            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.textView.setText(item.title);
            viewHolder.textView2.setText(item.imgRes+"kan");


        }

        private class ViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private TextView textView2;
            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.info_text);
                textView2 = (TextView) itemView.findViewById(R.id.info_text2);
            }
        }
    }