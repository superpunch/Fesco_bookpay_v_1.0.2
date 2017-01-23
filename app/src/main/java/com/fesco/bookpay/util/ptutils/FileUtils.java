package com.fesco.bookpay.util.ptutils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 文件操作类
 */
public class FileUtils {
	public static int i=1;
	public static File createTmpFile(Context context) {

		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			// 已挂载
		//	File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM+ "/Camera_Select");
			/**
			 * 注意此处必须进行文件夹的判断，不进行判断在某些国产手机上会出现bug
			 */
			if (!pic.exists()) {
		       boolean b=pic.mkdirs();
				Log.d("TAG", "Main mkdirs--- " +b);
			}
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
			String fileName = "select_image_" + timeStamp + i++;

			File tmpFile = new File(pic, fileName + ".jpg");

			return tmpFile;
		} else {
			File cacheDir = context.getCacheDir();
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
			String fileName = "select_image_" + timeStamp + i++;
			File tmpFile = new File(cacheDir, fileName + ".jpg");

			return tmpFile;
		}
	}
}
