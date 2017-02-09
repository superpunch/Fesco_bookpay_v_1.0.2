package com.fesco.bookpay.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * SharedPreferences 工具
 * 
 * @author OmiG_G
 * 
 */
public class SharedPrefUtil {

	// SharedPreferences名
	public static final String SPNAME = "check_preferences";
	public static final String SPNAME_LOGIN_CHECK = "check_preferences_login";
	public static final String SPNAMECHECKFLAG = "check_preferences_flag";
	public static final String PAGELOCATE = "sp_page_locate";

	// 提醒ToggButton的字段
	public static final String INTOCHECK = "intocheck";
	public static final String OUTCHECK = "outcheck";
	public static final String WORKCHECK = "workcheck";
	public static final String SOUDNCHECK = "soundcheck";

	// 提醒时间的字段
	public static final String INTOTIME = "intotime";
	public static final String OUTTIME = "outtime";
	public static final String WORKTIME = "worktime";
	public static final String SOUDNTIEM = "soundtime";

	//领导/员工权限
	public static final String PERMISSIONS = "permissions";
	// 登陆字段
	public static final String LOGIN = "LOGIN";
	public static final String TOKEN = "token";
	public static final String USERID = "userid";// 用户ID
	public static final String NAME = "name";// 用户姓名
	public static final String ACCOUNT = "account";// 登陆名
	public static final String USRPASS = "usrpass";// 用户密码

	// 签到 、签退 时间字段
	public static final String TIMECHECKIN = "timecheckin";
	public static final String TIMECHECKOUT = "timecheck";
	public static final String STARTADDRESS = "startaddress";
	public static final String ENDADDRESS = "endaddress";
	// 已签到
	public static final String SIGNIN = "signin";
	// 已签退
	public static final String SIGNOUT = "signout";
	// 补签
	public static final String FILLCHECK = "fillcheck";

	/**
	 * 读取
	 * 
	 * @param context
	 * @param key
	 * @return 默认值为null
	 */
	public static String read(Context context, String key) {
		String value = null;
		SharedPreferences sp = context.getSharedPreferences(SPNAME, 0);
		value = sp.getString(key, null);
		return value;
	}

	public static int readInt(Context context, String key) {
		int value = 0;
		SharedPreferences sp = context.getSharedPreferences(SPNAME, 0);
		value = sp.getInt(key, 0);
		return value;
	}

	public static boolean readBo(Context context, String key) {
		String value = null;
		SharedPreferences sp = context.getSharedPreferences(SPNAME, 0);
		value = sp.getString(key, null);
		return Boolean.valueOf(value);
	}

	public static String readCheckFlag(Context context, String key) {
		String value = null;
		SharedPreferences sp = context.getSharedPreferences(SPNAME_LOGIN_CHECK, 0);
		value = sp.getString(key, null);
		return value;
		//return Boolean.valueOf(value);
	}

	public static String readFlag(Context context, String key) {
		String value = null;
		SharedPreferences sp = context.getSharedPreferences(SPNAMECHECKFLAG, 0);
		value = sp.getString(key, null);
		return value;
	}

	/**
	 * 写入
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void writeFlag(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(SPNAMECHECKFLAG, 0);
		sp.edit().putString(key, value).commit();
		Log.d("OASharedPrefUtil", "OAborad:li写入key:" + key + "value:" + value);
	}

	public static void writeCheckFlag(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(SPNAME_LOGIN_CHECK, 0);
		sp.edit().putString(key, value).commit();
		Log.d("OASharedPrefUtil", "OAborad:li写入key:" + key + "value:" + value);
	}

	public static void write(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(SPNAME, 0);
		sp.edit().putString(key, value).commit();
		Log.d("OASharedPrefUtil", "OAborad:li写入key:" + key + "value:" + value);
	}

	public static void write(Context context, String key, int value) {
		SharedPreferences sp = context.getSharedPreferences(SPNAME, 0);
		sp.edit().putInt(key, value).commit();
		Log.d("OASharedPrefUtil", "OAborad:li写入key:" + key + "value:" + value);
	}

	public static void clear(Context context, String SPNAME) {
		SharedPreferences sp = context.getSharedPreferences(SPNAME, 0);
		sp.edit().clear().commit();
	}

	/**
	 * 这个给特殊
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void writeValue(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(key, 0);
		sp.edit().putString(key, value).commit();
	}

	/**
	 * 这个给特殊
	 * 
	 * @param context
	 * @param key
	 */
	public static String readValue(Context context, String key) {
		String value = null;
		SharedPreferences sp = context.getSharedPreferences(key, 0);
		value = sp.getString(key, null);
		return value;
	}

}
