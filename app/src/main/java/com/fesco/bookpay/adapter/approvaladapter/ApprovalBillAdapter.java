package com.fesco.bookpay.adapter.approvaladapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.approvalbean.BillListBean;
import com.fesco.bookpay.impl.ItemClickListener;
import com.fesco.bookpay.util.ConversionUtil;

import java.util.List;

public class ApprovalBillAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int emp_Id, cust_Id;
    private String token;
    private static final int VIEW_TYPE = -1;
    private List<BillListBean.ListBean> ListBean;

    private ItemClickListener mItemClickListener;

    public  void setOnItemClickListener(ItemClickListener mItemClickListener){
        this.mItemClickListener=mItemClickListener;
    }
    public ApprovalBillAdapter(Context mContext) {
        this.mContext = mContext;

    }
    public  void setListBean(List<BillListBean.ListBean> ListBean){
        this.ListBean = ListBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mContext);

        if(viewType == VIEW_TYPE){
            return new ViewHolderEmpty(  inflater.inflate(R.layout.list_tab_empty, parent, false));
        }else

        return new ViewHolder( inflater.inflate(R.layout.list_tab_approval_rebill, parent, false),mItemClickListener);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (ListBean == null || ListBean.size()<=0) {
                return;
            }
            BillListBean.ListBean listBean = ListBean.get(position);


//         switch (listBean.getType()){
//             case 1:
//                 viewHolder.mIcon.setImageResource(R.drawable.approval_bill_one);
//                 break;
//             case 2:
//                 viewHolder.mIcon.setImageResource(R.drawable.approval_bill_two);
//                 break;
//             case 3:
//                 viewHolder.mIcon.setImageResource(R.drawable.approval_bill_three);
//                 break;
//         }


            viewHolder.mMoney.setText("￥"+listBean.getMoney_Sum());

            viewHolder.mType.setText(listBean.getType_Str());
            if(TextUtils.isEmpty(listBean.getEmp_Name())){
                viewHolder.mName.setText("暂无");
            }else{
                if(listBean.getEmp_Name().length()>4){
               String name=listBean.getEmp_Name().substring(0,3)+"...";
                    viewHolder.mName.setText(name);
                }else
                    viewHolder.mName.setText(listBean.getEmp_Name());

            }
            String apply_Date=  ConversionUtil.getLongDateTimeHm(listBean.getApply_Date());
            viewHolder.mTime.setText(apply_Date);
            viewHolder.mTitle.setText(listBean.getTitle());
            viewHolder.mDesc.setText(listBean.getMemo());
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
        }else return 1;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
     //   public ImageView mIcon;
        public ImageView mHead;
        public TextView mType;
        public TextView mName;
        public TextView mMoney;
        public TextView mTime;
        public TextView mDesc;
        public TextView mTitle;
        public ViewHolder(View view, final ItemClickListener mItemClickListener) {
            super(view);
         //   mIcon = (ImageView) view.findViewById(R.id.image_icon);

            mHead = (ImageView) view.findViewById(R.id.image_bill_head);
            mType = (TextView) view.findViewById(R.id.approval_bill_type);
            mName = (TextView) view.findViewById(R.id.approval_bill_name);
            mMoney = (TextView) view.findViewById(R.id.approval_bill_time);
            mTime = (TextView) view.findViewById(R.id.approval_bill_title); //申请时间
            mTitle = (TextView) view.findViewById(R.id.approval_bill_titetv);
            mDesc = (TextView) view.findViewById(R.id.approval_bill_desc);

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
