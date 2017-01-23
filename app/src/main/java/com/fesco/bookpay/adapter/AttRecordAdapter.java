package com.fesco.bookpay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.AttRecordBean;
import com.fesco.bookpay.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AttRecordAdapter extends RecyclerView.Adapter<AttRecordAdapter.ViewHolder> {

    public Context mContext;

   // private List<AttRecordBean.ListBean> ListBean;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public AttRecordAdapter(Context mContext) {
        this.mContext = mContext;

    }


    @Override
    public AttRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tab_attrecord, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AttRecordAdapter.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = holder;
            if (mDataList == null) {
                return;
            }
            AttRecordBean.ListBean bean = mDataList.get(position);

            if(StringUtils.isEmpty(bean.getCust_Addr())){
                viewHolder.mRecord_addr.setText(bean.getCust_Addr());
            }

            if(StringUtils.isEmpty(bean.getBeginTime())){
              long   beginTime=  Long.valueOf(bean.getBeginTime());
                viewHolder.mRecord_in.setText(sdf.format(beginTime));
            }else {
                viewHolder.mRecord_in.setText("暂无");
            }
            if(StringUtils.isEmpty(bean.getEndTime())){
                long   endTime=  Long.valueOf(bean.getEndTime());
                viewHolder.mRecord_out.setText(sdf.format(endTime));
            }else {
                viewHolder.mRecord_out.setText("暂无");
            }

              if(null != bean.getMemo()){
                  viewHolder.mRecord_bz.setText(bean.getMemo().toString());
              }




        }


    }

    @Override
    public int getItemCount() {
        if (mDataList != null) {
            return mDataList.size();
        }else return 0;
    }



    protected ArrayList<AttRecordBean.ListBean> mDataList = new ArrayList<AttRecordBean.ListBean>();


    public List<AttRecordBean.ListBean> getDataList() {
        return mDataList;
    }

//    public void setDataList(Collection<T> list) {
//        this.mDataList.clear();
//        this.mDataList.addAll(list);
//        notifyDataSetChanged();
//    }

    public void addAll(List<AttRecordBean.ListBean> ListBean) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(ListBean)) {
            //positionStart INT：被插入的第一个项目的位置   ,   ITEMCOUNT INT：插入的项目数
            notifyItemRangeInserted(lastIndex, ListBean.size());
        }
    }

    public void remove(int position) {
        if(this.mDataList.size() > 0) {
            mDataList.remove(position);
            notifyItemRemoved(position);
        }

    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }
























    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mRecord_addr;
        public TextView mRecord_in;
        public TextView mRecord_out;
        public TextView mRecord_bz;

        public ViewHolder(View view) {
            super(view);
            mRecord_addr = (TextView) view.findViewById(R.id.recod_addr_id);
            mRecord_in = (TextView) view.findViewById(R.id.recod_in_id);
            mRecord_out = (TextView) view.findViewById(R.id.recod_out_id);
            mRecord_bz = (TextView) view.findViewById(R.id.recod_bz_id);
        }
    }
}
