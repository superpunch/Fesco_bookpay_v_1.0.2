package com.fesco.bookpay.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.util.ConversionUtil;

import java.util.List;
import java.util.Map;

public class RbmApproveAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String, Object>> list;
    private LayoutInflater inflater;

    public RbmApproveAdapter(Context context, List<Map<String, Object>> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.activity_reimburse_timeline, null);
            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.is_pass = (TextView) convertView.findViewById(R.id.is_pass);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String approvalName = list.get(position).get("approval_Man_Str").toString();
        String isPass = list.get(position).get("is_Pass_Str").toString();
        String approvalTime = (String) list.get(position).get("approval_Time");
        if (!TextUtils.isEmpty(approvalName)) {
            viewHolder.name.setText(approvalName);
        } else {
            viewHolder.name.setText("审批人");
        }

        if (!TextUtils.isEmpty(isPass)) {
            viewHolder.is_pass.setText(isPass);
        } else {
            viewHolder.is_pass.setText("审批状态");
        }

        if (!TextUtils.isEmpty(approvalTime)) {
            Log.d("Fragment","-------时间:"+approvalTime);
            viewHolder.time.setText(ConversionUtil.getLongYearHour(approvalTime));

        } else {
            viewHolder.time.setVisibility(View.GONE);

        }
        return convertView;
    }

    static class ViewHolder {
        public TextView time;
        public TextView is_pass;
        public TextView name;
    }
}
