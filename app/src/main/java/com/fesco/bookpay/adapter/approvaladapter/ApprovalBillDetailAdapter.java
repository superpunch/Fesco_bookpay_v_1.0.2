package com.fesco.bookpay.adapter.approvaladapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.approvalbean.BillDetailBean;
import com.fesco.bookpay.impl.ItemClickListener;
import com.fesco.bookpay.util.ConversionUtil;

import java.util.List;

public class ApprovalBillDetailAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private static final int VIEW_TYPE = -1;
    private List<BillDetailBean.ApplyBean.DetailsBean> ListBean;
    private Typeface font;
    private ItemClickListener mItemClickListener;
    private boolean isItemClick =false;

    public  void setOnItemClickListener(ItemClickListener mItemClickListener){
        this.mItemClickListener=mItemClickListener;
    }
    public ApprovalBillDetailAdapter(Context mContext) {
        this.mContext = mContext;

    }
    public  void setListBean(List<BillDetailBean.ApplyBean.DetailsBean> ListBean, Typeface font,boolean isItemClick){
        this.ListBean = ListBean;
        this.font = font;
        this.isItemClick = isItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mContext);

//        if(viewType == VIEW_TYPE){
//            return new ViewHolderEmpty(  inflater.inflate(R.layout.list_tab_empty, parent, false));
//        }else

        return new ViewHolder( inflater.inflate(R.layout.list_item_approval_bill, parent, false),mItemClickListener,font,isItemClick);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (ListBean == null || ListBean.size()<=0) {
                return;
            }
            BillDetailBean.ApplyBean.DetailsBean listBean = ListBean.get(position);

            String apply_Date=  ConversionUtil.getLongYearTime(listBean.getSpend_Begin());
            viewHolder.mTime.setText(apply_Date);
            viewHolder.mMoney.setText("￥"+listBean.getMoney_Amount());
            //viewHolder.mIcon.setText("测试");
            if(!TextUtils.isEmpty(listBean.getAndroid_Icon())){
                String icon = Html.fromHtml(listBean.getAndroid_Icon()).toString();
                viewHolder.mIcon.setText(icon);
            }

        }


    }
    @Override
    public int getItemViewType(int position) {

        if( ListBean ==null ||  ListBean.size()<=0){
            return VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (ListBean != null && ListBean.size()>0) {
            return ListBean.size();
        }else return 0;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mIcon;
        public TextView mMoney;
        public TextView mTime;
        public ViewHolder(View view, final ItemClickListener mItemClickListener, Typeface font, boolean isItemClick) {
            super(view);
            mIcon = (TextView) view.findViewById(R.id.item_bill_icon);
            mIcon.setTypeface(font);
            mTime = (TextView) view.findViewById(R.id.item_bill_time);
            mMoney = (TextView) view.findViewById(R.id.item_bill_money);
             if(isItemClick){
                 view.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         mItemClickListener.onItemClick(v,getAdapterPosition());
                     }
                 });
             }

        }
    }


    public static class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View itemView) {
            super(itemView);
        }
    }

}
