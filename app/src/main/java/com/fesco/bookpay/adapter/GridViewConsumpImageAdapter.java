package com.fesco.bookpay.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.util.okhttp.CropSquareTrans;

import java.util.List;

/**
 * Created by gong.min on 2016/9/5.
 */
public class GridViewConsumpImageAdapter extends BaseAdapter {
    private int[] images = {R.drawable.a, R.drawable.b, R.drawable.c};
    private String[] textsTotal = {
            // 九宫格图片下方文字的设置
            "个人信息", "考勤", "休假"

    };
    private LayoutInflater inflater;
private   List<byte[]> dataList;

    public GridViewConsumpImageAdapter(Context context, List<byte[]> dataList) {
        this.inflater = LayoutInflater.from(context);
        this.dataList=dataList;
    }




    @Override
    public int getCount() {
        return dataList.size();
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
            convertView = inflater.inflate(R.layout.item_publish, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_grid_image);
            holder.delete_iv = (ImageView) convertView.findViewById(R.id.delete_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        byte[]    data=     dataList.get(position);
        Bitmap bitmap = new CropSquareTrans().transform(BitmapFactory.decodeByteArray(data, 0, data.length));
        holder.imageView.setImageBitmap(bitmap);
        holder.delete_iv.setVisibility(View.GONE);
       // holder.imageView.setBackgroundResource(images[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        ImageView delete_iv;

    }
}
