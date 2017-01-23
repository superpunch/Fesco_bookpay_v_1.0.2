package com.fesco.bookpay.adapter.approvaladapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.approvalbean.CheckListBean;
import com.fesco.bookpay.impl.ItemClickListener;
import com.fesco.bookpay.util.CommonUtils;

import java.util.List;

public class ApprovalCheckAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int emp_Id, cust_Id;
    private String token;
    private static final int VIEW_TYPE = -1;
    private List<CheckListBean.ListBean> ListBean;

    private ItemClickListener mItemClickListener;

    public  void setOnItemClickListener(ItemClickListener mItemClickListener){
        this.mItemClickListener=mItemClickListener;
    }
    public ApprovalCheckAdapter(Context mContext) {
        this.mContext = mContext;

    }
    public  void setListBean(List<CheckListBean.ListBean> ListBean){
        this.ListBean = ListBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mContext);

        if(viewType == VIEW_TYPE){
            return new ViewHolderEmpty(  inflater.inflate(R.layout.list_tab_empty, parent, false));
        }else

        return new ViewHolder( inflater.inflate(R.layout.list_tab_approval_check, parent, false),mItemClickListener);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (ListBean == null || ListBean.size()<=0) {
                return;
            }
            CheckListBean.ListBean listBean = ListBean.get(position);
            String applyDate = CommonUtils.sdf.format(listBean.getApply_Date());
            String applyStartDate =  CommonUtils.sdf.format(listBean.getCheck_Time());
            viewHolder.mTime.setText(applyDate);   //申请时间
            viewHolder.starTime.setText(applyStartDate); //
            viewHolder.mName.setText(listBean.getEmp_Name());
          //  1 签到 , 2 签退, 3 外勤
           int mCheck=  listBean.getCheck_Type();
            if(mCheck==1){
                viewHolder.mType.setText("签到");
            }else  if(mCheck==2){
                viewHolder.mType.setText("签退");
            }
            else viewHolder.mType.setText("外勤");


        }


    }
    @Override
    public int getItemViewType(int position) {
        System.out.println("  -----getItemViewType-------->>>");
        if( ListBean ==null ||  ListBean.size()<=0){
            return VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (ListBean != null && ListBean.size()>0) {
            return ListBean.size();
        }else return 1;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mTime;
        public TextView mType;
        public TextView starTime;
        public ViewHolder(View view, final ItemClickListener mItemClickListener) {
            super(view);
            mName = (TextView) view.findViewById(R.id.approval_check_name);
            mTime = (TextView) view.findViewById(R.id.approval_check_time); //申请时间
            starTime = (TextView) view.findViewById(R.id.approval_check_startime);
            mType = (TextView) view.findViewById(R.id.approval_check_typeid);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(v,getAdapterPosition());
                }
            });
        }
    }


    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View itemView) {
            super(itemView);
        }
    }

}
