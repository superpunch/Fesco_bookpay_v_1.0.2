package com.fesco.bookpay.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.ConsumptionBean;

import java.util.List;

public class RbmTypeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private static final int VIEW_EMPTY = 0;
    private static final int VIEW_TYPE = 1;
    private List<ConsumptionBean.SpendTypesBean> spendTypes;
    private List<ConsumptionBean.SpendTypesBean.SubTypesBean> spendTypesDateil;


    private Typeface font;
    private ItemTypeClickListener mItemClickListener;

    public interface ItemTypeClickListener {
        void onItemClick(View view, int postion);
    }

    public RbmTypeListAdapter(Context mContext) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setSpendTypes(List<ConsumptionBean.SpendTypesBean> spendTypes, Typeface font) {
        this.spendTypes = spendTypes;
       this.font= font;
    }

    public void setSpendTypesDateil(List<ConsumptionBean.SpendTypesBean.SubTypesBean> spendTypesDateil, Typeface font) {
        this.spendTypesDateil = spendTypesDateil;
        this.font = font;

    }

    public void setOnItemClickListener(ItemTypeClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_EMPTY) {
            return new ViewHolderEmpty(mLayoutInflater.inflate(R.layout.list_tab_empty, parent, false));
        } else {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.list_type_consumption, parent, false), mItemClickListener,font);
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (spendTypes == null && spendTypesDateil == null) {
                return;
            }

            if (spendTypes != null && spendTypes.size() > 0) {
                ConsumptionBean.SpendTypesBean spendTypesBean = spendTypes.get(position);
                viewHolder.mName.setText(spendTypesBean.getType_Name());

                if(!TextUtils.isEmpty(spendTypesBean.getAndroid_Icon())){
                    String icon=Html.fromHtml(spendTypesBean.getAndroid_Icon()).toString();
                    viewHolder.mFontawesome.setText(icon);
                }else {
                    viewHolder.mFontawesome.setText(Html.fromHtml("&#xf2ba;"));
                }
            }

            if (spendTypesDateil != null) {
                ConsumptionBean.SpendTypesBean.SubTypesBean subTypesBean = spendTypesDateil.get(position);
                viewHolder.mName.setText(subTypesBean.getType_Name());
                if(!TextUtils.isEmpty(subTypesBean.getAndroid_Icon())){
                    String icon=Html.fromHtml(subTypesBean.getAndroid_Icon()).toString();
                    viewHolder.mFontawesome.setText(icon);
                }else {
                    viewHolder.mFontawesome.setVisibility(View.GONE);
                }

            }


        }


    }
int i=0;
    @Override
    public int getItemViewType(int position) {
        if (spendTypes != null ) {
            return VIEW_TYPE;
        } else  if (spendTypesDateil != null ) {
            return VIEW_TYPE;
        } else
        Log.e("Fragment", "----------:getItemViewType "+(++i));
        return VIEW_EMPTY;
    }

    @Override
    public int getItemCount() {
        if (spendTypes != null && spendTypes.size() > 0) {
            return spendTypes.size();
        } else if (spendTypesDateil != null && spendTypesDateil.size() > 0) {
            return spendTypesDateil.size();
        } else return 1;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mFontawesome;
        public TextView mName;

        public ViewHolder(View view, final ItemTypeClickListener mItemClickListener, Typeface font) {
            super(view);

            mFontawesome = (TextView) view.findViewById(R.id.csp_fontawesome);
            mName = (TextView) view.findViewById(R.id.csp_name);
            mFontawesome.setTypeface(font);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(v, getAdapterPosition());
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
