package com.fesco.bookpay.adapter.approvaladapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.approvalbean.OverListBean;
import com.fesco.bookpay.impl.ItemClickListener;
import com.fesco.bookpay.util.CommonUtils;

import java.util.List;

public class ApprovalOvertimeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int emp_Id, cust_Id;
    private String token;
    private static final int VIEW_TYPE = -1;
    private List<OverListBean.ListBean> ListBean;
    private ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public ApprovalOvertimeAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void setListBean(List<OverListBean.ListBean> ListBean) {
        this.ListBean = ListBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (viewType == VIEW_TYPE) {
            return new ViewHolderEmpty(inflater.inflate(R.layout.list_tab_empty, parent, false));
        } else
            return new ViewHolder(inflater.inflate(R.layout.list_tab_approval_overtime, parent, false), mItemClickListener);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (ListBean == null || ListBean.size() <= 0) {
                return;
            }
            OverListBean.ListBean listBean = ListBean.get(position);
            String applyDate =  CommonUtils.sdf.format(listBean.getApply_Date());
            String applyStartDate =  CommonUtils.sdf.format(listBean.getBegin_Time());
            String applyEndDate =  CommonUtils.sdf.format(listBean.getEnd_Time());
            viewHolder.mTime.setText(applyDate);   //申请时间
            viewHolder.starTime.setText(applyStartDate); //
            viewHolder.endTime.setText(applyEndDate); //
            viewHolder.mName.setText(listBean.getEmp_Name());


        }
    }


    @Override
    public int getItemCount() {
        if (ListBean != null && ListBean.size() > 0) {
            return ListBean.size();
        } else return 1;

    }

    @Override
    public int getItemViewType(int position) {
        if (ListBean == null || ListBean.size() <= 0) {
            return VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mName;
        public TextView mTime;
        public TextView starTime;
        public TextView endTime;
        public ItemClickListener mItemClickListener;

        public ViewHolder(View view, ItemClickListener mItemClickListener) {
            super(view);
            mName = (TextView) view.findViewById(R.id.approval_overtime_name);
            mTime = (TextView) view.findViewById(R.id.approval_overtime_time); //申请时间
            starTime = (TextView) view.findViewById(R.id.approval_overtime_startime);
            endTime = (TextView) view.findViewById(R.id.approval_overtime_endtime);
            this.mItemClickListener = mItemClickListener;
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getPosition());
        }
    }


    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View itemView) {
            super(itemView);
        }
    }

}
