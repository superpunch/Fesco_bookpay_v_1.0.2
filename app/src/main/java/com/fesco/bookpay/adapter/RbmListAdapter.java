package com.fesco.bookpay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.ExpenseApplyListBean;

import java.util.List;

public class RbmListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private static final int VIEW_EMPTY = 0;
    private static final int VIEW_TYPE = 1;
    private List<ExpenseApplyListBean.ListBean> expenseApplyListBeen;

    public OnItemListener onItemListener;

    public interface OnItemListener {

        void setOpenItem(int position);
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;

    }


    public RbmListAdapter(Context mContext) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }


    public void setExpenseApplyListBean(List<ExpenseApplyListBean.ListBean> expenseApplyListBeen) {
        this.expenseApplyListBeen = expenseApplyListBeen;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_EMPTY) {
            return new ViewHolderEmpty(mLayoutInflater.inflate(R.layout.list_tab_empty, parent, false));
        } else {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_rbm_approve, parent, false), onItemListener);
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (expenseApplyListBeen == null) {
                return;
            }
            ExpenseApplyListBean.ListBean listBean = expenseApplyListBeen.get(position);
            String type ="";
            //0待提交，1待审批，2待支付，3未通过，4已支付
            switch (listBean.getExam_End_Is()) {
                case 0:
                    type = "待提交";
                    break;
                case 1:
                    type = "待审批";
                    break;
                case 2:
                    type = "待支付";
                    break;
                case 3:
                    type = "未通过";
                    break;
                case 4:
                    type = "已支付";
                    break;
                default:
                    type = "状态类型";
                    break;
            }
            viewHolder.typeState.setText(type);


            if(!TextUtils.isEmpty(listBean.getTitle())){

                viewHolder.typeTitle.setText(listBean.getTitle());
            }else {
                viewHolder.typeTitle.setText("无标题");
            }


            switch (listBean.getType()) {
                case 0:
                    viewHolder.typeImage.setImageResource(R.drawable.approval_bill_one);
                    break;
                case 1:
                    viewHolder.typeImage.setImageResource(R.drawable.approval_bill_two);
                    break;
                case 2:
                    viewHolder.typeImage.setImageResource(R.drawable.approval_bill_three);
                    break;
            }
            viewHolder.typeName.setText(listBean.getType_Str());

            //   int money_Amount = 0;

//            List<ExpenseApplyListBean.ListBean.DetailsBean> detailsBeanList = listBean.getDetails();
//
//                for (ExpenseApplyListBean.ListBean.DetailsBean detailsBean : detailsBeanList) {
//                    money_Amount += detailsBean.getMoney_Amount();
//                }

            //    viewHolder.typeMoney.setText(Integer.toString(money_Amount));


            if (null != listBean.getMoney_Sum()) {
                viewHolder.typeMoney.setText(listBean.getMoney_Sum());
            }
            else {
                viewHolder.typeMoney.setText("0.00");
                Log.e("Fragment","Sb") ;
            }

        }


    }

    @Override
    public int getItemViewType(int position) {
        if (expenseApplyListBeen == null || expenseApplyListBeen.size() <= 0) {
            return VIEW_EMPTY;
        } else
            return VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        if (expenseApplyListBeen != null && expenseApplyListBeen.size() > 0) {
            return expenseApplyListBeen.size();
        } else return 1;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView typeTitle;
        public TextView typeName;//差旅报销单
        public TextView typeMoney;
        public TextView typeState; //待审批
        public ImageView typeImage;


        public ViewHolder(View view, final OnItemListener onItemListener) {
            super(view);
            typeTitle = (TextView) view.findViewById(R.id.rbm_approve_title);
            typeMoney = (TextView) view.findViewById(R.id.rbm_approve_money);
            typeName = (TextView) view.findViewById(R.id.rbm_approve_type);
            typeState = (TextView) view.findViewById(R.id.rbm_approve_state);
            typeImage = (ImageView) view.findViewById(R.id.image_reimburse);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemListener.setOpenItem(getAdapterPosition());
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
