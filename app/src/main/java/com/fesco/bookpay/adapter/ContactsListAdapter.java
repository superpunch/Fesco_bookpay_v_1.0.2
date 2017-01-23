package com.fesco.bookpay.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.ContactsChangeBean;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ViewHolder> {

    private Context mContext;
    private String mPhone,mMobile;
    private List<ContactsChangeBean.EmpsBean> listEmpsBean;
 //   private List<String> listEmpsBean;
    public ContactsListAdapter(Context mContext, List<ContactsChangeBean.EmpsBean> listEmpsBean) {
        this.mContext = mContext;
        this.listEmpsBean = listEmpsBean;
    }


    @Override
    public ContactsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactsListAdapter.ViewHolder holder, final int position) {
        final View view = holder.mView;
     //   holder.emps_name.setText("");
        if (holder instanceof ViewHolder) {

            ViewHolder viewHolder=holder;
         //   System.out.println("onBindViewHolder------<<<<<-----"+listEmpsBean.get(6));
//            System.out.println("onBindViewHolder----"+position+":"+ listEmpsBean.get(position).getGroup_Name()+"-----------");
//
            if(listEmpsBean ==null){
                return;
            }
            mPhone=listEmpsBean.get(position).getPhone();
            mMobile=listEmpsBean.get(position).getMobile();

            if(mPhone!=null){
                viewHolder.emps_phone.setText(mPhone);
                viewHolder.emps_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //意图
                        Intent intent=new Intent();
                        //设置要拨打的号码
                        intent.setData(Uri.parse("tel://"+listEmpsBean.get(position).getPhone()));
                        //设置动作,拨号 动作
                        intent.setAction(intent.ACTION_CALL);
                        //跳转到拨号界面
                        mContext.  startActivity(intent);

                     com.orhanobut.logger.Logger.e(listEmpsBean.get(position).getPhone());

                    }
                });




            }
            if(mMobile!=null){
                viewHolder.emps_mobile.setText(mMobile);
                viewHolder.emps_mobile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //意图
                        Intent intent=new Intent();
                        //设置要拨打的号码
                        intent.setData(Uri.parse("tel://"+listEmpsBean.get(position).getMobile()));
                        //设置动作,拨号 动作
                        intent.setAction(intent.ACTION_CALL);
                        //跳转到拨号界面
                        mContext.startActivity(intent);
                        com.orhanobut.logger.Logger.i(listEmpsBean.get(position).getMobile());
                    }
                });

            }

            viewHolder.emps_name.setText(listEmpsBean.get(position).getEmp_Name());
     //       viewHolder.emps_name.setText(listEmpsBean.get(position));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("getItemCount-----:"+listEmpsBean.size());

        return listEmpsBean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView emps_name;
        public TextView emps_phone;
        public TextView emps_mobile;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            emps_phone = (TextView) view.findViewById(R.id.emps_phone);
            emps_mobile = (TextView) view.findViewById(R.id.emps_mobile);
            emps_name = (TextView) view.findViewById(R.id.emps_name);


        }
    }
}
