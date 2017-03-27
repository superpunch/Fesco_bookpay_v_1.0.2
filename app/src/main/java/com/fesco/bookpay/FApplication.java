package com.fesco.bookpay;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.fesco.bookpay.activity.MainActivity;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import me.bookpay.greendao.DaoMaster;
import me.bookpay.greendao.DaoSession;

/**
 * Created by gong.min on 2016/9/1.
 */
public class FApplication extends Application {
    // 记录是否已经初始化
    private boolean isInit = false;
   private static Context sContext;

    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        MultiDex.install(this);
        Settings settings = Logger.init("EllisonLog"); // 配置tag
        settings.methodCount(5); // 配置Log中调用堆栈的函数行数
        // settings.hideThreadInfo(); // 隐藏Log中的线程信息
        settings.methodOffset(0); // 设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息
        settings.logLevel(LogLevel.FULL); // 设置Log的是否输出，LogLevel.NONE即无Log输出

        setupDatabase();

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
        initEasemob();
    }

    public static Context getContext() {
        return sContext;
    }
    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        helper = new DaoMaster.DevOpenHelper(this, "db_bookpay", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }



    /**
     *
     */
    private void initEasemob() {
        // 获取当前进程 id 并取得进程名
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        /**
         * 如果app启用了远程的service，此application:onCreate会被调用2次
         * 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
         * 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
         */
        if (processAppName == null || !processAppName.equalsIgnoreCase(sContext.getPackageName())) {
            // 则此application的onCreate 是被service 调用的，直接返回
            return;
        }
        if (isInit) {
            return;
        }
        /**
         * SDK初始化的一些配置
         * 关于 EMOptions 可以参考官方的 API 文档
         * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
         */
        EMOptions options = new EMOptions();
        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
        // options.setAppKey("guaju");
        // 设置自动登录
        options.setAutoLogin(true);
        // 设置是否需要发送已读回执
        options.setRequireAck(true);
        // 设置是否需要发送回执，TODO 这个暂时有bug，上层收不到发送回执
        options.setRequireDeliveryAck(true);
        // 设置是否需要服务器收到消息确认
        // options.setRequireServerAck(true);
        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
        options.setAcceptInvitationAlways(false);
        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
        options.setAutoAcceptGroupInvitation(false);
        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
        options.setDeleteMessagesAsExitGroup(false);
        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
        options.allowChatroomOwnerLeave(true);
        // 调用初始化方法初始化sdk
        EaseUI easeUI=   EaseUI.getInstance();
        easeUI .init(sContext, options);




        // 设置开启debug模式
        EMClient.getInstance().setDebugMode(true);

        // 设置初始化已经完成
        isInit = true;

        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());

    }

    //实现ConnectionListener接口
    public class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
        }
        @Override
        public void onDisconnected(final int error) {

                    if(error == EMError.USER_REMOVED){
                        Log.e("Fragment", "显示帐号已经被移除");
                        // 显示帐号已经被移除
                    }else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                        Log.e("Fragment", "显示帐号在其他设备登录");
//

                        Intent intent = new Intent(sContext, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(MainActivity.ACCOUNT_CONFLICT, true);
                        sContext.startActivity(intent);

                    }
                }

    }


    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) sContext.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName;
                    // 返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }













}
