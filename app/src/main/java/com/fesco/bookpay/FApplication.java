package com.fesco.bookpay;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fesco.bookpay.activity.MainActivity;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

import me.bookpay.greendao.DaoMaster;
import me.bookpay.greendao.DaoSession;

/**
 * Created by gong.min on 2016/9/1.
 */
public class FApplication extends Application {
    private final static String TAG = MainActivity.class.getSimpleName();
    private static Context sContext;

    public DaoSession daoSession;
    public SQLiteDatabase db;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        Settings settings = Logger.init("EllisonLog"); // 配置tag
        settings.methodCount(5); // 配置Log中调用堆栈的函数行数
        // settings.hideThreadInfo(); // 隐藏Log中的线程信息
        settings.methodOffset(0); // 设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息
        settings.logLevel(LogLevel.FULL); // 设置Log的是否输出，LogLevel.NONE即无Log输出

        setupDatabase();
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
}
