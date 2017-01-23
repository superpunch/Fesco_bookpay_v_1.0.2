package com.fesco.bookpay.adapter.approvaladapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.approvalbean.RestListBean;
import com.fesco.bookpay.impl.ItemClickListener;
import com.fesco.bookpay.util.CommonUtils;

import java.util.List;

public class ApprovalRestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater mLayoutInflater;
    private static final int VIEW_EMPTY = 0;
    private static final int VIEW_TYPE = 1;
    private List<RestListBean.ListBean> ListBean;

    private ItemClickListener mItemClickListener;
    int i=0;
    int l=10;
    public ApprovalRestAdapter(Context mContext) {
        this.mLayoutInflater = LayoutInflater.from(mContext);

    }
    public void setListBean( List<RestListBean.ListBean> ListBean){
        this.ListBean = ListBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.i("Fragment", l+"马丹----初始化-View---Fragment 休假记录 ——————onCreateViewHolder ---"+VIEW_EMPTY);

        if (viewType == VIEW_EMPTY) {
            return new ViewHolderEmpty(mLayoutInflater.inflate(R.layout.list_tab_empty, parent, false));
        } else {
            return new ItemViewHolder(mLayoutInflater.inflate(R.layout.list_tab_approval_rest, parent, false),mItemClickListener);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (ListBean == null ||ListBean.size() <=0) {
            return;
        }

        RestListBean.ListBean listBean = ListBean.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;

        String applyDate =  CommonUtils.sdf.format(listBean.getAppl_Date());
        String applyStartDate =  CommonUtils.sdf.format(listBean.getHol_Begin());
        String applyEndDate =  CommonUtils.sdf.format(listBean.getHol_End());

        if(listBean.getEmp_Name().length()>=8 && applyDate !=null){
            itemViewHolder.mTime.setText(applyDate.substring(0,10)+"...");

        }else if(applyDate !=null ) {
            itemViewHolder.mTime.setText(applyDate);   //申请时间
        }

//        if(!TextUtils.isEmpty(listBean.getHol_Name()) ){
//            ViewGroup.LayoutParams params =   itemViewHolder.mLine.getLayoutParams();
//            if(listBean.getHol_Name().length()==4){
//                params.width =27;
//                itemViewHolder.mLine.setLayoutParams(params);
//            }else if(listBean.getHol_Name().length()==5){
//                params.width =1;
//                itemViewHolder.mLine.setLayoutParams(params);
//            }
//
//        }
        itemViewHolder.mType.setText(listBean.getHol_Name());
        itemViewHolder.starTime.setText(applyStartDate); //
        itemViewHolder.endTime.setText(applyEndDate); //
        itemViewHolder.mName.setText(listBean.getEmp_Name());





        itemViewHolder.mNumber.setText(Integer.toString(position + 1));

    }

    @Override
    public int getItemViewType(int position) {
        if (ListBean == null || ListBean.size() <= 0) {
            return VIEW_EMPTY;
        } else
            return VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        if (ListBean != null && ListBean.size()>0) {
            return ListBean.size();
        } else return 1;

    }


    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(ItemClickListener listener) {
        this.mItemClickListener = listener;
        Log.e("Fragment", l+" 休假记录 ——————setOnItemClickListener  "+listener);

    }




    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener mListener;

        public TextView mName;
        public TextView mTime;
        public TextView mType;
       // public ImageView mLine;
        public TextView starTime;
        public TextView endTime;
        public TextView mNumber;

        public ItemViewHolder(View view, ItemClickListener mListener) {
            super(view);
            mName = (TextView) view.findViewById(R.id.approval_rest_name);
            mTime = (TextView) view.findViewById(R.id.approval_rest_time);

       //     mLine = (ImageView) view.findViewById(R.id.linetwo);

            mType = (TextView) view.findViewById(R.id.approval_rest_type);
            starTime = (TextView) view.findViewById(R.id.approval_rest_startime);
            endTime = (TextView) view.findViewById(R.id.approval_rest_endtime);
            mNumber = (TextView) view.findViewById(R.id.approval_rest_number);
            this.mListener=  mListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (mListener != null) {
                mListener.onItemClick(v, getAdapterPosition());
            }
        }
    }


    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View itemView) {
            super(itemView);
        }
    }


}
