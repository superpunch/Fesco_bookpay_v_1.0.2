package com.fesco.bookpay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.RestRecordBean;

import java.text.SimpleDateFormat;
import java.util.List;

public class RestPatchApprovalAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private static final int VIEW_EMPTY = 0;
    private static final int VIEW_TYPE = 1;
    private List<RestRecordBean.ListBean> ListBean;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private OnItemClickListener onItemClickListener;

    public RestPatchApprovalAdapter(Context mContext) {
        this.mLayoutInflater = LayoutInflater.from(mContext);

    }

    public void setListBean(List<RestRecordBean.ListBean> ListBean) {
        this.ListBean = ListBean;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE:
                return new ViewHolder(mLayoutInflater.inflate(R.layout.list_tab_restrecord, parent, false));
            default:
                return new ViewHolderEmpty(mLayoutInflater.inflate(R.layout.list_tab_empty, parent, false));
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (ListBean == null) {
                return;
            }
            viewHolder.linear_list_rest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onDeleteClick(position);
                }
            });

            RestRecordBean.ListBean listBean = ListBean.get(position);
            int exam = listBean.getExam_End_Is();


            // 未通过 色值:  d2d2d2    图片:    apply_no ;  // 通过  图片 apply_pass, 色值  主题蓝
            // ;  // 审批中  色值 323a45 图片apply_ing


            viewHolder.mPathName.setText("审批人:" + listBean.getCurrApprovalMan());//审批人
            if (1 == exam) {
                viewHolder.mPathReslut.setText("通过");  // 1 通过, 2 正在审批 , 3 审批未通过
                viewHolder.mPathReslut.setBackgroundResource(R.drawable.apply_pass);
                viewHolder.tv_side.setBackgroundResource(R.drawable.shape_overtime_blue);
                viewHolder.mPathName.setBackgroundColor(Color.parseColor("#00b6d8"));
                viewHolder.mPathName.setTextColor(Color.parseColor("#ffffff"));

                viewHolder.mRestType.setBackgroundColor(Color.parseColor("#00b6d8"));
                viewHolder.mRestType.setTextColor(Color.parseColor("#ffffff"));

            } else if (2 == exam) {
                viewHolder.mPathReslut.setText("审批中");  // 1 通过, 2 正在审批 , 3 审批未通过
                viewHolder.mPathReslut.setBackgroundResource(R.drawable.apply_ing);
                viewHolder.tv_side.setBackgroundResource(R.drawable.shape_overtime_black);

                viewHolder.mPathName.setBackgroundColor(Color.parseColor("#323a45"));
                viewHolder.mPathName.setTextColor(Color.parseColor("#00b6d8"));

                viewHolder.mRestType.setBackgroundColor(Color.parseColor("#323a45"));
                viewHolder.mRestType.setTextColor(Color.parseColor("#00b6d8"));
            } else if (0 == exam) {
                viewHolder.mPathReslut.setText("未通过");  // 1 通过, 2 正在审批 , 3 审批未通过
                viewHolder.mPathReslut.setBackgroundResource(R.drawable.apply_no);
                viewHolder.tv_side.setBackgroundResource(R.drawable.shape_overtime_gray);


                viewHolder.mPathName.setBackgroundColor(Color.parseColor("#d2d2d2"));
                viewHolder.mPathName.setTextColor(Color.parseColor("#000000"));

                viewHolder.mRestType.setBackgroundColor(Color.parseColor("#d2d2d2"));
                viewHolder.mRestType.setTextColor(Color.parseColor("#000000"));
            }

            long begin = listBean.getHol_Begin();
            long end = listBean.getHol_End();
            long applyDate = listBean.getAppl_Date();

            viewHolder.mRestType.setText(listBean.getHol_Name());//休假类型
            viewHolder.mRestName.setText(listBean.getEmp_Name());//休假name
            viewHolder.mApplicaTime.setText(sdf.format(applyDate)); // //申请时间
            viewHolder.mStarTime.setText(sdf.format(begin)); // //开始时间
            viewHolder.mEndTime.setText(sdf.format(end)); // //结束时间

            System.out.println("4  -----getCurrApprovalMan-------->>>" + listBean.getCurrApprovalMan() + "-----------");


        }


    }


    @Override
    public int getItemCount() {
        System.out.println("  -----getItemCount-------->>>");

        return ListBean != null ? ListBean.size() : 1;
    }


    @Override
    public int getItemViewType(int position) {

        if (ListBean == null || ListBean.size() <= 0) {
            return VIEW_EMPTY;
        } else
            return VIEW_TYPE;
    }

    public void removeItem(int position) {
        ListBean.remove(position);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mRestType;
        public TextView mRestName;
        public TextView mStarTime;
        public TextView mEndTime;
        public TextView mApplicaTime;
        public TextView mPathReslut;
        public TextView mPathName;
        public TextView tv_side;
        public TextView delete;
        public LinearLayout linear_list_rest;

        public ViewHolder(View view) {
            super(view);
            mRestType = (TextView) view.findViewById(R.id.resttype);
            mRestName = (TextView) view.findViewById(R.id.tv_rest_name);
            mStarTime = (TextView) view.findViewById(R.id.rest_start_time);
            mEndTime = (TextView) view.findViewById(R.id.rest_end_time);
            mApplicaTime = (TextView) view.findViewById(R.id.rest_applica_time);

            tv_side = (TextView) view.findViewById(R.id.tv_side);
            mPathReslut = (TextView) view.findViewById(R.id.path_pass);
            mPathName = (TextView) view.findViewById(R.id.path_pass_name);

            linear_list_rest = (LinearLayout) itemView.findViewById(R.id.linear_list_rest);
            delete = (TextView) itemView.findViewById(R.id.item_delete);


        }
    }

    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {


        public ViewHolderEmpty(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        /**
         * item点击回调
         *
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);

        /**
         * 删除按钮回调
         *
         * @param
         */
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
