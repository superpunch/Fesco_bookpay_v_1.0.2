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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.rbmbean.SpendTypesBean;
import com.fesco.bookpay.util.ConversionUtil;
import com.fesco.bookpay.util.DoubleUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.bookpay.greendao.Consume;

public class RbmBillListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private static final int VIEW_EMPTY = 0;
    private static final int VIEW_TYPE = 1;
    private int mAmount;//金钱总数
    private List<Consume> consumeList;
    private Typeface font;
    private List<SpendTypesBean.ListBean> listBean;
    private boolean isVisible; //是否显示CheckBox

    public OnDelelteListener onDelelteListener;
    public OnAmountListener onAmountListener;  //金钱汇总接口

    public OnCheckClickListener onCheckListener;

    // 存储勾选框状态的map集合
    public HashMap<Integer, Boolean> map = new HashMap<>();
    //  public SparseArray< Boolean> sparseArray = new SparseArray<>();


    public interface OnDelelteListener {
        void itemDelete(int adapterPosition);

        void itemOpenActivity(int adapterPosition);
    }

    public interface OnAmountListener {
        void onAmountMoney(double amount);
    }


    public interface OnCheckClickListener {
        void onCheckAmountMoney(int amount);
        //    void onCheckAllListener(int amount);

    }

    public void setOnCheckListener(OnCheckClickListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }

    public void setOnItemDeleteListener(OnDelelteListener onDelelteListener) {
        this.onDelelteListener = onDelelteListener;
    }

    public void setOnMoneyListener(OnAmountListener onAmountListener) {
        this.onAmountListener = onAmountListener;


    }

    public RbmBillListAdapter(Context mContext) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setConsumeList(List<Consume> consumeList, Typeface font) {
        this.consumeList = consumeList;
        this.font = font;
        if (consumeList.size() > 0) {
            double  mAmount = 0;

            for (Consume csm : consumeList) {

                mAmount= DoubleUtil.add(mAmount,Double.valueOf(csm.getMoney()));

            }

            onAmountListener.onAmountMoney(mAmount);
        }

    }

    public void setListBean(List<SpendTypesBean.ListBean> listBean, boolean isVisible, Typeface font) {
        this.listBean = listBean;
        this.isVisible = isVisible;
        this.font = font;
        if (!isVisible) {
            if (listBean != null) {
                for (int i = 0; i < listBean.size(); i++) {
                    map.put(i, false);
                }
            }
        }
    }

    //返回集合给Activity
    public Map<Integer, Boolean> getMap() {
        return map;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == VIEW_EMPTY) {
//            return new ViewHolderEmpty(mLayoutInflater.inflate(R.layout.list_tab_empty, parent, false));
//        } else {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.list_rbm_consume, parent, false), onDelelteListener, listBean,font);
        //  }


    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        Log.i("Fragment", "----------:onBindViewHolder  consumeList:" + consumeList + "  listBean:" + listBean);
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            if (consumeList != null) {
                if (consumeList.size() > 0) {
                    Consume consume = consumeList.get(position);
                    //   viewHolder.typeImage.setText(consume.getType());图标类型
                    viewHolder.typeName.setText(consume.getTypeName());
                    viewHolder.typeMoney.setText(consume.getMoney().toString());

                    if (!TextUtils.isEmpty(consume.getStarDate())) {
                        viewHolder.typeDay.setText(consume.getStarDate());
                    }

                    if (!TextUtils.isEmpty(consume.getTypeIcon())) {
                        String icon = Html.fromHtml(consume.getTypeIcon()).toString();
                        viewHolder.typeIcon.setText(icon);
                    }


                    if (!TextUtils.isEmpty(consume.getEndDate())) {
                        viewHolder.typeEndDay.setText(consume.getEndDate());
                        viewHolder.typeEndDay.setVisibility(View.VISIBLE);
                        viewHolder.typetv.setVisibility(View.VISIBLE);
                    } else {
                        viewHolder.typeEndDay.setVisibility(View.GONE);
                        viewHolder.typetv.setVisibility(View.GONE);
                    }
                }
            }


            if (listBean != null) {

                if (listBean.size() > 0) {


                    SpendTypesBean.ListBean stb = listBean.get(position);
                    viewHolder.typeName.setText(stb.getSpend_Type_Str());
                    viewHolder.typeMoney.setText(stb.getMoney_Amount());

                    String spend_Begin = ConversionUtil.getLongDateTime(stb.getSpend_Begin());
                    viewHolder.typeDay.setText(spend_Begin);

                    if (!TextUtils.isEmpty(stb.getAndroid_Icon())) {
                        String icon = Html.fromHtml(stb.getAndroid_Icon()).toString();
                        viewHolder.typeIcon.setText(icon);
                        Log.e("lzy","stb.getAndroid_Icon()："+stb.getAndroid_Icon()) ;
                    }else {
                        Log.d("lzy","长度："+position) ;
                        String icon = Html.fromHtml("&#xf2ba;").toString();
                        viewHolder.typeIcon.setText(icon);
                    }
                    Log.e("lzy","typeIcon："+  viewHolder.typeIcon.getText().toString()+"---------") ;

                    if (!TextUtils.isEmpty(stb.getSpend_End())) {
                        viewHolder.typeEndDay.setVisibility(View.VISIBLE);
                        viewHolder.typetv.setVisibility(View.VISIBLE);
                        String spend_End = ConversionUtil.getLongDateTime(stb.getSpend_End());
                        viewHolder.typeEndDay.setText(spend_End);

                    } else {
                        viewHolder.typeEndDay.setVisibility(View.GONE);
                        viewHolder.typetv.setVisibility(View.GONE);
                    }

                    if (!isVisible) {
                        viewHolder.checkBox.setVisibility(View.VISIBLE);
                        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                map.put(position, isChecked);

                                onCheckListener.onCheckAmountMoney(position);

                            }
                        });
                        if (map.get(position) == null) {
                            map.put(position, false);
                        }

                        viewHolder.checkBox.setChecked(map.get(position));

                    }


                }


            }


        }


    }

    @Override
    public int getItemViewType(int position) {
//        if (rankOutLateBean == null || rankOutLateBean.size() <= 0) {
//            return VIEW_EMPTY;
//        } else
        return VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        if (consumeList != null) {
            if (consumeList.size() > 0) {
                return consumeList.size();
            }
        } else if (listBean != null) {
            if (listBean.size() > 0)
                return listBean.size();

        } else

            return 0;
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView typedelete;
        public TextView typeIcon;
        public TextView typeName;
        public TextView typeDay;
        public TextView typeEndDay;
        public TextView typetv;
        public TextView typeMoney;

        public CheckBox checkBox;

        public ViewHolder(View view, final OnDelelteListener onDelelteListener, List<SpendTypesBean.ListBean> listBean, Typeface font) {
            super(view);
            typedelete = (ImageView) view.findViewById(R.id.csm_delete);
            typeIcon = (TextView) view.findViewById(R.id.csm_image);
            typeIcon.setTypeface(font);
            typeName = (TextView) view.findViewById(R.id.csm_type);
            typeDay = (TextView) view.findViewById(R.id.csm_day);
            typeEndDay = (TextView) view.findViewById(R.id.csm_endday);
            typetv = (TextView) view.findViewById(R.id.csm_tvday);
            typeMoney = (TextView) view.findViewById(R.id.csm_money);

            checkBox = (CheckBox) view.findViewById(R.id.id_check_select);


            if (listBean != null) {
                typedelete.setVisibility(View.INVISIBLE);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onDelelteListener !=null){
                        onDelelteListener.itemOpenActivity(getAdapterPosition());
                    }
                }
            });

            typedelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    onDelelteListener.itemDelete(getAdapterPosition());

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
