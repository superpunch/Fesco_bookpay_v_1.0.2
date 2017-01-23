package com.fesco.bookpay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.OverPatchRecordBean;

import java.text.SimpleDateFormat;
import java.util.List;

public class OverPatchApprovalAdapter extends RecyclerView.Adapter {

    private Context mContext;

    private static final int VIEW_TYPE = -1;
    private List<OverPatchRecordBean.ListBean> ListBean;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public OverPatchApprovalAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public  void setListBean(List<OverPatchRecordBean.ListBean> ListBean){
        this.ListBean = ListBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mContext);
        if(viewType == VIEW_TYPE){
            View view =
                    inflater.inflate(R.layout.list_tab_empty, parent, false);
            return new ViewHolderEmpty(view);
        }
        View view =
                inflater.inflate(R.layout.list_tab_overrecord, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (ListBean == null) {
                return;
            }
            OverPatchRecordBean.ListBean listBean = ListBean.get(position);
            int exam = listBean.getExam_End_Is();


            // 未通过 色值:  d2d2d2    图片:    apply_no ;  // 通过  图片 apply_pass, 色值  主题蓝
            // ;  // 审批中  色值 323a45 图片apply_ing


            viewHolder.mPathName.setText("审批人:"+listBean.getCurrApprovalMan());//审批人
            if (1 == exam) {
                viewHolder.mPathReslut.setText("通过");  // 1 通过, 2 正在审批 , 3 审批未通过
                viewHolder.mPathReslut.setBackgroundResource(R.drawable.apply_pass);
                viewHolder.tv_side.setBackgroundResource(R.drawable.shape_overtime_blue);
                viewHolder.mPathName.setBackgroundColor(Color.parseColor("#00b6d8"));
                viewHolder.mPathName.setTextColor(Color.parseColor("#ffffff"));

            } else if (2 == exam) {
                viewHolder.mPathReslut.setText("审批中");  // 1 通过, 2 正在审批 , 3 审批未通过
                viewHolder.mPathReslut.setBackgroundResource(R.drawable.apply_ing);
                viewHolder.tv_side.setBackgroundResource(R.drawable.shape_overtime_black);

                viewHolder.mPathName.setBackgroundColor(Color.parseColor("#323a45"));
                viewHolder.mPathName.setTextColor(Color.parseColor("#00b6d8"));
            } else if (0 == exam) {
                viewHolder.mPathReslut.setText("未通过");  // 1 通过, 2 正在审批 , 3 审批未通过
                viewHolder.mPathReslut.setBackgroundResource(R.drawable.apply_no);
                viewHolder.tv_side.setBackgroundResource(R.drawable.shape_overtime_gray);


                viewHolder.mPathName.setBackgroundColor(Color.parseColor("#d2d2d2"));
                viewHolder.mPathName.setTextColor(Color.parseColor("#000000"));

            }

            long begin = listBean.getBegin_Time();
            long end = listBean.getEnd_Time();
            long applyDate = listBean.getApply_Date();

            viewHolder.mOverName.setText(listBean.getEmp_Name());


            viewHolder.mApplicaTime.setText(sdf.format(applyDate)); // //申请时间
            viewHolder.mStarTime.setText(sdf.format(begin)); // //开始时间
            viewHolder.mEndTime.setText(sdf.format(end)); // //结束时间


        }


    }


    @Override
    public int getItemCount() {
      System.out.println("  -----getItemCount-------->>>" );
        if (ListBean != null) {
            return ListBean.size();
        }else return 1;

    }


    @Override
    public int getItemViewType(int position) {
        System.out.println("  -----getItemViewType-------->>>");
        if( ListBean ==null ||  ListBean.size()<=0){
            return VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mOverName;
        public TextView mStarTime;
        public TextView mEndTime;
        public TextView mApplicaTime;
        public TextView mPathReslut;
        public TextView mPathName;
        public TextView tv_side;
        public ViewHolder(View view) {
            super(view);
            mOverName = (TextView) view.findViewById(R.id.om_rec_name);
            mStarTime = (TextView) view.findViewById(R.id.om_start_time);
            mEndTime = (TextView) view.findViewById(R.id.om_end_time);
            mApplicaTime = (TextView) view.findViewById(R.id.om_applica_time);

            tv_side = (TextView) view.findViewById(R.id.tv_side);
            mPathReslut = (TextView) view.findViewById(R.id.path_pass);
            mPathName = (TextView) view.findViewById(R.id.path_pass_name);
        }
    }

    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View itemView) {
            super(itemView);
        }
    }


}
