package com.fesco.bookpay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.AttPatchApprovalBean;

import java.text.SimpleDateFormat;
import java.util.List;

public class AttPatchApprovalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private static final int VIEW_EMPTY = 0;
    private static final int VIEW_TYPE = 1;
    private List<AttPatchApprovalBean.ListBean> ListBean;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public AttPatchApprovalAdapter(Context mContext, List<AttPatchApprovalBean.ListBean> ListBean) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.ListBean = ListBean;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_EMPTY) {
            return new ViewHolderEmpty(mLayoutInflater.inflate(R.layout.list_tab_empty, parent, false));
        } else {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_tab_attpatch_approval, parent, false));
        }



    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (ListBean == null) {
                return;
            }
            AttPatchApprovalBean.ListBean listBean = ListBean.get(position);
            int checkType = listBean.getCheck_Type();
            int exam = listBean.getExam_End_Is();
            String mCheckType = "";
            if (1 == checkType) {
                mCheckType = "签到";
            } else if (2 == checkType) {
                mCheckType = "签退";
            } else if (3 == checkType) {
                mCheckType = "外勤";
            }
            if (1 == exam) {                                  // 1 通过, 2 正在审批 , 0 审批未通
                viewHolder.btn_patchType.setText("通过");  //
                viewHolder.btn_patchType.setBackgroundColor(Color.parseColor("#00b6d8"));
                viewHolder.btn_patchType.setTextColor(Color.parseColor("#ffffff"));
            } else if (2 == exam) {
                viewHolder.btn_patchType.setText("正在审批");  // 1 通过, 2 正在审批 , 3 审批未通过
                viewHolder.btn_patchType.setBackgroundColor(Color.parseColor("#000000"));
                viewHolder.btn_patchType.setTextColor(Color.parseColor("#00b6d8"));
            } else if (0 == exam) {
                viewHolder.btn_patchType.setText("审批未通过");  // 1 通过, 2 正在审批 , 3 审批未通过
                viewHolder.btn_patchType.setBackgroundColor(Color.parseColor("#000000"));
                viewHolder.btn_patchType.setTextColor(Color.parseColor("#00b6d8"));
            }

            long applyDate = Long.valueOf(listBean.getApply_Date());
            long checkTime = Long.valueOf(listBean.getCheck_Time());
            viewHolder.check_Time.setText(sdf.format(checkTime));  // 1 签到 , 2 签退, 3 外勤
            viewHolder.apply_Date.setText(sdf.format(applyDate)); // //申请时间
            viewHolder.check_Type.setText(mCheckType);//签到时间

            if(null !=listBean.getCust_Addr()){
                viewHolder.cust_Addr.setText(listBean.getCust_Addr()); //
            }
            System.out.println("4  -----getCurrApprovalMan-------->>>" + listBean.getCurrApprovalMan() + "-----------");
            if(null !=listBean.getCurrApprovalMan()){
               viewHolder.approvalMan.setText(listBean.getCurrApprovalMan());
            }

        //    viewHolder.btn_patchType.setText(listBean.getExam_End_Is());  // 1 通过, 2 正在审批 , 3 审批未通过
        }


    }
    @Override
    public int getItemViewType(int position) {
        Log.i("Fragment", "getItemViewType 休假记录 —————-------ListBean "+ListBean);
        if (ListBean == null || ListBean.size() <= 0) {
            return VIEW_EMPTY;
        } else
            return VIEW_TYPE;
    }
    @Override
    public int getItemCount() {
        if (ListBean != null&& ListBean.size()>0) {
            return ListBean.size();
        }else return 1;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView check_Time;
        public TextView check_Type;
        public TextView apply_Date;
        public TextView cust_Addr;
        public TextView approvalMan;
        private Button btn_patchType;

        public ViewHolder(View view) {
            super(view);
            check_Type = (TextView) view.findViewById(R.id.att_typeroval);
            check_Time = (TextView) view.findViewById(R.id.att_typeroval_id);
            apply_Date = (TextView) view.findViewById(R.id.att_timeroval);
            cust_Addr = (TextView) view.findViewById(R.id.att_addroval);
            approvalMan = (TextView) view.findViewById(R.id.att_peroval);
            btn_patchType = (Button) view.findViewById(R.id.btn_approval);

        }
    }


    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View itemView) {
            super(itemView);
        }
    }


}
