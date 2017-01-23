package com.fesco.bookpay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;

/**
 * Created by gong.min on 2016/9/5.
 */
public class GridViewAdapter extends BaseAdapter {
    private int[] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.m, R.drawable.j, R.drawable.k, R.drawable.l};
    private String[] textsTotal = {
            // 九宫格图片下方文字的设置
            "个人信息", "考勤", "休假"
            , "审批", "加班", "通讯录",
            "签到统计", "迟到排行", "加班排行","报销",
            "薪资列表", "HRS数据录入", "HRS数据勘察"
    };
    private LayoutInflater inflater;


    public GridViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.grid_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.ItemImage);
            holder.textView = (TextView) convertView.findViewById(R.id.ItemText);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setBackgroundResource(images[position]);
        holder.textView.setText(textsTotal[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;

    }
}
