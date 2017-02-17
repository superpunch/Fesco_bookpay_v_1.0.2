package com.fesco.bookpay.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.UpdateStatus;
import com.fesco.bookpay.entity.VersionInfo;

/**
 * 
 * @author	wenjie
 *	版本更新的工具类
 */
public class UpdateVersionUtil {
	
	/**
	 * 接口回调
	 * @author wenjie
	 *
	 */
	public interface UpdateListener{
		void onUpdateReturned(int updateStatus, VersionInfo.AppStoreBean versionInfo);
	}
	
	public UpdateListener updateListener;
	
//	public void setUpdateListener(UpdateListener updateListener) {
//		this.updateListener = updateListener;
//	}
	

	
	
	/**
	 * 本地测试
	 */
	public static void localCheckedVersion(final Context context, VersionInfo.AppStoreBean versionInfo, final UpdateListener updateListener){
			try {
		//	mVersionInfo.setDownloadUrl("http://gdown.baidu.com/data/wisegame/57a788487345e938/QQ_358.apk");
		//	versionInfo.setVersion_Desc("\r\n更新内容：\r\n1、增加推送功能\r\n2、增加报销模块\r\n3、用户界面优化！");
		//	versionInfo.setVersion_Name("v1.0.2111");
//			versionInfo.setVersion_Size("13.54M");
			int clientVersionCode = ApkUtils.getVersionCode(context);
			int serverVersionCode = Integer.valueOf(versionInfo.getVersion_Code());
				Log.i("Fragment", "\\\\n更新内容：\\\\n1、优化用户界面\\\\n2、新增报销审批等功能.\\\\n3、增加推送服务\\\\n " + clientVersionCode);
				Log.i("Fragment", "--------serverVersionCode: " + serverVersionCode);
			//有新版本  code 2  Version_Name 1.0.2
			if(clientVersionCode < serverVersionCode){
				int i = NetworkUtils.checkedNetWorkType(context);
				if(i == NetworkUtils.NOWIFI){

					updateListener.onUpdateReturned(UpdateStatus.NOWIFI,versionInfo);
				}else if(i == NetworkUtils.WIFI){
					updateListener.onUpdateReturned(UpdateStatus.YES,versionInfo);
				}
			}else{
				//无新本
				updateListener.onUpdateReturned(UpdateStatus.NO,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			updateListener.onUpdateReturned(UpdateStatus.ERROR,null);
		}
		}


	
	
	/**
	 * 弹出新版本提示
	 * @param context 上下文
	 * @param versionInfo 更新内容
	 */
	public static void showDialog(final Context context,final VersionInfo.AppStoreBean versionInfo){
		final Dialog dialog = new AlertDialog.Builder(context).create();
	//	final File file = new File(SDCardUtils.getRootDirectory()+"/updateVersion/gdmsaec-app.apk");
		dialog.setCancelable(true);// 可以用“返回键”取消  
		dialog.setCanceledOnTouchOutside(false);//
		dialog.show();
		View view = LayoutInflater.from(context).inflate(R.layout.version_update_dialog, null);
		dialog.setContentView(view);
		
		final Button btnOk = (Button) view.findViewById(R.id.btn_update_id_ok);
		Button btnCancel = (Button) view.findViewById(R.id.btn_update_id_cancel);
		TextView tvContent = (TextView) view.findViewById(R.id.tv_update_content);
		TextView tvUpdateTile = (TextView) view.findViewById(R.id.tv_update_title);
		final TextView tvUpdateMsgSize = (TextView) view.findViewById(R.id.tv_update_msg_size);
		
		tvContent.setText(versionInfo.getVersion_Desc().replaceAll("n","\n"));
		tvUpdateTile.setText("最新版本："+versionInfo.getVersion_Name());
		
//		if(file.exists() && file.getName().equals("gdmsaec-app.apk")){
//			tvUpdateMsgSize.setText("新版本已经下载，是否安装？");
//		}else{
//			tvUpdateMsgSize.setText("新版本大小："+versionInfo.getVersionSize());
//		}
		tvUpdateMsgSize.setText("新版本大小："+versionInfo.getVersion_Size());
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				if(v.getId() == R.id.btn_update_id_ok){
					//新版本已经下载
//					if(file.exists() && file.getName().equals("gdmsaec-app.apk")){
//						Intent intent = ApkUtils.getInstallIntent(file);
//						context.startActivity(intent);
//					}else{
//						//没有下载，则开启服务下载新版本
////						Intent intent = new Intent(context,UpdateVersionService.class);
////						intent.putExtra("downloadUrl", versionInfo.getDownloadUrl());
////						context.startService(intent);
//
//						ApkUpdateUtils.download(context, "http://gdown.baidu.com/data/wisegame/57a788487345e938/QQ_358.apk", "书薪");
//
//
//					}

					ApkUpdateUtils.download(context, versionInfo.getDownload_Url(), "书薪",versionInfo.getVersion_Code());
					SpUtils.getInstance(context).putString(SpUtils.VERSION_NAME,versionInfo.getVersion_Name());


				}
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
	

}
