package com.fesco.bookpay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.activity.RankOverTimeActivity;
import com.fesco.bookpay.entity.RankOverBean;

import java.util.List;

public class RankOverAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private static final int VIEW_EMPTY = 0;
    private static final int VIEW_TYPE = 1;
    private List<RankOverBean.RankListBean>  rankOverBean;


    public RankOverAdapter(RankOverTimeActivity mContext) {
        this.mLayoutInflater = LayoutInflater.from(mContext);

    }
    public  void setListBean(List<RankOverBean.RankListBean> rankOverBean){

        this.rankOverBean = rankOverBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_EMPTY) {
            return new ViewHolderEmpty(mLayoutInflater.inflate(R.layout.list_tab_empty, parent, false));
        } else {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_rank_overtime, parent, false));
        }



    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (rankOverBean == null) {
                return;
            }
            RankOverBean.RankListBean listBean = rankOverBean.get(position);
            if(position==0){
                viewHolder.rankTrohpy.setImageResource(R.drawable.trophy);
            }else if(position==1){
                viewHolder.rankTrohpy.setImageResource(R.drawable.twotrophy);
            }else if(position==2){
                viewHolder.rankTrohpy.setImageResource(R.drawable.threetrophy);
            }else
                viewHolder.rankTrohpy.setVisibility(View.GONE);

            viewHolder.rankPosition.setText(Integer.toString(position+1));
            viewHolder.rankName.setText(listBean.getEmp_Name());
            viewHolder.rankDuration.setText(listBean.getDuration()+"H");
        }


    }
    @Override
    public int getItemViewType(int position) {
        if (rankOverBean == null || rankOverBean.size() <= 0) {
            return VIEW_EMPTY;
        } else
            return VIEW_TYPE;
    }
    @Override
    public int getItemCount() {
        if (rankOverBean != null&& rankOverBean.size()>0) {
            return rankOverBean.size();
        }else return 1;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView rankName;
        public TextView rankPosition;
        public TextView rankDuration;
        public ImageView rankTrohpy;

        public ViewHolder(View view) {
            super(view);
            rankName = (TextView) view.findViewById(R.id.rank_name);
            rankPosition = (TextView) view.findViewById(R.id.rank_position);
            rankDuration = (TextView) view.findViewById(R.id.rank_duration);
            rankTrohpy = (ImageView) view.findViewById(R.id.rank_trophy);


        }
    }


    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View itemView) {
            super(itemView);
        }
    }


}
