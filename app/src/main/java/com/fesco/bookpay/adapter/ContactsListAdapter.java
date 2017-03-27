package com.fesco.bookpay.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fesco.bookpay.FApplication;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.ContactsChangeBean;
import com.fesco.bookpay.view.GlideCircleTransform;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import me.bookpay.greendao.ImagePhotos;
import me.bookpay.greendao.ImagePhotosDao;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ViewHolder> {

    private Context mContext;
    private String mPhone, mMobile;
    private List<ContactsChangeBean.EmpsBean> listEmpsBean;
    ChatUiListenterCallBack chatUiListenterCallBack;

    public interface ChatUiListenterCallBack {
        void easeUiCallBack(String chatUserId, String emp_name);
    }

    public void setChatUiListenterCallBack(ChatUiListenterCallBack chatUiListenterCallBack) {
        this.chatUiListenterCallBack = chatUiListenterCallBack;
    }

    public ContactsListAdapter(Context mContext, List<ContactsChangeBean.EmpsBean> listEmpsBean) {
        this.mContext = mContext;
        this.listEmpsBean = listEmpsBean;

    }

    private ImagePhotosDao getImagePhotos() {
        // 通过 BaseApplication 类提供的 getDaoSession() 获取具体 Dao
        return ((FApplication) (mContext.getApplicationContext())).getDaoSession().getImagePhotosDao();
    }

    @Override
    public ContactsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactsListAdapter.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {

            ViewHolder viewHolder = holder;
            if (listEmpsBean == null) {
                return;
            }
            mPhone = listEmpsBean.get(position).getPhone();
            mMobile = listEmpsBean.get(position).getMobile();

            if (mPhone != null) {
                viewHolder.emps_phone.setText(mPhone);
                viewHolder.emps_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //意图
                        Intent intent = new Intent();
                        //设置要拨打的号码
                        intent.setData(Uri.parse("tel://" + listEmpsBean.get(position).getPhone()));
                        //设置动作,拨号 动作
                        intent.setAction(intent.ACTION_CALL);
                        //跳转到拨号界面
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);

                        com.orhanobut.logger.Logger.e(listEmpsBean.get(position).getPhone());

                    }
                });


            }
            if (mMobile != null) {
                viewHolder.emps_mobile.setText(mMobile);
                viewHolder.emps_mobile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //意图
                        Intent intent = new Intent();
                        //设置要拨打的号码
                        intent.setData(Uri.parse("tel://" + listEmpsBean.get(position).getMobile()));
                        //设置动作,拨号 动作
                        intent.setAction(intent.ACTION_CALL);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //跳转到拨号界面
                        mContext.startActivity(intent);
                        com.orhanobut.logger.Logger.i(listEmpsBean.get(position).getMobile());
                    }
                });

            }

            viewHolder.emps_name.setText(listEmpsBean.get(position).getEmp_Name());

            final String   chatUserId = Integer.toString(listEmpsBean.get(position).getEmp_Id());

            viewHolder.headview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("Fragment", "final chatUserId:"+chatUserId);
                    Log.e("Fragment", "      chatUserId:"+Integer.toString(listEmpsBean.get(position).getEmp_Id()));
                    chatUiListenterCallBack.easeUiCallBack( Integer.toString(listEmpsBean.get(position).getEmp_Id()), listEmpsBean.get(position).getEmp_Name());
                }
            });


//            QueryBuilder<ImagePhotos> qb = getImagePhotos().queryBuilder();
//            List<ImagePhotos> imagePhotosList = qb.list();
//            List<ImagePhotos> imagePhotosList = getImagePhotos().loadAll();
//            Log.e("Fragment", "imagePhotosList length: " + imagePhotosList.size());


            QueryBuilder<ImagePhotos> qb = getImagePhotos().queryBuilder().where(ImagePhotosDao.Properties.Emp_Id.eq(chatUserId));
            List<ImagePhotos> imagePhotosList = qb.list();

            if (imagePhotosList != null && ! imagePhotosList.isEmpty()) {
                Log.e("Fragment", "getEmp_Id: " + chatUserId);
                byte[] bytes = Base64.decode(imagePhotosList.get(0).getImage_Url(), Base64.DEFAULT);
                Glide.with(mContext).load(bytes).transform(new GlideCircleTransform(mContext)).placeholder(R.drawable.headmax).error(R.drawable.headmax).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.headview);

            }else  Glide.with(mContext).load("").transform(new GlideCircleTransform(mContext)).placeholder(R.drawable.headmax).error(R.drawable.headmax).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.headview);



        }


    }

    @Override
    public int getItemCount() {

        return listEmpsBean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView emps_name;
        public TextView emps_phone;
        public TextView emps_mobile;
        public ImageView headview;

        public ViewHolder(View view) {
            super(view);
            emps_phone = (TextView) view.findViewById(R.id.emps_phone);
            emps_mobile = (TextView) view.findViewById(R.id.emps_mobile);
            emps_name = (TextView) view.findViewById(R.id.emps_name);
            headview = (ImageView) view.findViewById(R.id.headview);


        }
    }
}
