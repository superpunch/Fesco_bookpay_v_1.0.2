package com.fesco.bookpay.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @项目名:
 * @包名:
 * @类名:	VersionInfo
 * @创建者:	wenjie
 * @创建时间:	2016-10-14	上午11:06:08
 * @描述:	app版本信息封装类
 * 
 * @svn版本:	$Rev: 1304 $
 * @更新人:	$Author: wenjie $
 * @更新时间:	$Date: 2017-02-27 14:58:42 +0800 (Sat, 27 Feb 2016) $
 * @更新描述:	TODO
 */
public class VersionInfo implements Serializable{
	private static final long serialVersionUID = -2875764952706479516L;
	/**
	 * download_Url : https://11.0.197.196:8443/payroll/app.apk
	 * version_Desc : 书薪2.0新版本
	 * app_Type : 1
	 * version_Code : 2
	 * version_Size : 13.4M
	 * version_Name : 2.0
	 * app_Name : 书薪APP
	 */

	private List<AppStoreBean> appStore;

	public List<AppStoreBean> getAppStore() {
		return appStore;
	}

	public void setAppStore(List<AppStoreBean> appStore) {
		this.appStore = appStore;
	}

	public static class AppStoreBean {
		private String download_Url; //新版本的下载路径
		private String version_Desc; //版本描述信息内容
		private int app_Type;
		private String version_Code; //版本号
		private String version_Size; //版本大小
		private String version_Name; //版本名
		private String app_Name;

		public String getDownload_Url() {
			return download_Url;
		}

		public void setDownload_Url(String download_Url) {
			this.download_Url = download_Url;
		}

		public String getVersion_Desc() {
			return version_Desc;
		}

		public void setVersion_Desc(String version_Desc) {
			this.version_Desc = version_Desc;
		}

		public int getApp_Type() {
			return app_Type;
		}

		public void setApp_Type(int app_Type) {
			this.app_Type = app_Type;
		}

		public String getVersion_Code() {
			return version_Code;
		}

		public void setVersion_Code(String version_Code) {
			this.version_Code = version_Code;
		}

		public String getVersion_Size() {
			return version_Size;
		}

		public void setVersion_Size(String version_Size) {
			this.version_Size = version_Size;
		}

		public String getVersion_Name() {
			return version_Name;
		}

		public void setVersion_Name(String version_Name) {
			this.version_Name = version_Name;
		}

		public String getApp_Name() {
			return app_Name;
		}

		public void setApp_Name(String app_Name) {
			this.app_Name = app_Name;
		}
	}












}
