package com.fesco.bookpay.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Window;

import com.fesco.bookpay.FApplication;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseChatFragment;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import me.bookpay.greendao.ImagePhotos;
import me.bookpay.greendao.ImagePhotosDao;


/**
 * Created by gong.min on 2017/2/28.
 */
public class ChatActivity extends FragmentActivity {

   //  String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);
          String id=    getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID);
        final  String username=    getIntent().getStringExtra(EaseConstant.EXTRA_USER_NAME);

        Log.e("Fragment",id+ "onCreate user---: " +username);
        EaseChatFragment easeChatFragment = new EaseChatFragment();  //环信聊天界面
        Bundle args = new Bundle();
        args.putString(EaseConstant.EXTRA_USER_ID, id);
        args.putString(EaseConstant.EXTRA_USER_NAME, username);

        easeChatFragment.setArguments(args); //需要的参数
        getSupportFragmentManager().beginTransaction().add(R.id.layout_chat,easeChatFragment).commit();  //Fragment切换

        EaseUI easeUI=   EaseUI.getInstance();
        //需要easeui库显示用户头像和昵称设置此provider
        easeUI.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
            @Override
            public EaseUser getUser(String id) {
                return getUserInfo(id );
            }
        });



    }

    EaseUser user;
    private EaseUser getUserInfo(String id) {

        //获取user信息，demo是从内存的好友列表里获取，
        //实际开发中，可能还需要从服务器获取用户信息,
        //从服务器获取的数据，最好缓存起来，避免频繁的网络请求
        // 从缓存里取昵称和头像
        //  user = new EaseUser(username);

      //  EMClient.getInstance().chatManager().getConversation(id.getfrom());//获取当前登录的账号
        if(user == null){
            user = new EaseUser(id);

        }

        String  chatUserId=id.replace("zrfesco_","");

        QueryBuilder<ImagePhotos> qb = getImagePhotos().queryBuilder().where(ImagePhotosDao.Properties.Emp_Id.eq(chatUserId));
        List<ImagePhotos> imagePhotosList = qb.list();
        Log.e("Fragment", "getEmp_Id---: " + chatUserId);
        //  user.setAvatar("https://www.baidu.com/img/bdlogo.png");
        if (imagePhotosList != null && ! imagePhotosList.isEmpty()) {


            byte[] bytes = Base64.decode(imagePhotosList.get(0).getImage_Url(), Base64.DEFAULT);
            user.setBytes(bytes);



        }

//        user.setNick(username);

        return user;

    }

    private ImagePhotosDao getImagePhotos() {
        // 通过 BaseApplication 类提供的 getDaoSession() 获取具体 Dao
        return ((FApplication) this.getApplication()).getDaoSession().getImagePhotosDao();
    }





}