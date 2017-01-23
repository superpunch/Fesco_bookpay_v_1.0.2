package com.fesco.bookpay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.activity.RankOutLateActivity;
import com.fesco.bookpay.entity.RankOutLateBean;
import com.fesco.bookpay.view.MyProgessBar;

import java.util.List;

public class RankOutLateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private static final int VIEW_EMPTY = 0;
    private static final int VIEW_TYPE = 1;
    private List<RankOutLateBean.RankListBean>  rankOutLateBean;


    public RankOutLateAdapter(RankOutLateActivity mContext) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }
    public void setrankOutLateBean( List<RankOutLateBean.RankListBean> rankOutLateBean){
         this.rankOutLateBean = rankOutLateBean;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_EMPTY) {
            return new ViewHolderEmpty(mLayoutInflater.inflate(R.layout.list_tab_empty, parent, false));
        } else {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_rank_outlate, parent, false));
        }



    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (rankOutLateBean == null) {
                return;
            }
            RankOutLateBean.RankListBean listBean = rankOutLateBean.get(position);
            viewHolder.outlateName.setText(listBean.getEmp_Name());
            viewHolder.progrssBar.setProgress(listBean.getCounts());

        }


    }
    @Override
    public int getItemViewType(int position) {
        if (rankOutLateBean == null || rankOutLateBean.size() <= 0) {
            return VIEW_EMPTY;
        } else
            return VIEW_TYPE;
    }
    @Override
    public int getItemCount() {
        if (rankOutLateBean != null&& rankOutLateBean.size()>0) {
            return rankOutLateBean.size();
        }else return 1;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public MyProgessBar progrssBar;
        public TextView outlateName;

        public ViewHolder(View view) {
            super(view);
            progrssBar = (MyProgessBar) view.findViewById(R.id.progrssbar);
            outlateName = (TextView) view.findViewById(R.id.outlate_name);


        }
    }


    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View itemView) {
            super(itemView);
        }
    }


}
