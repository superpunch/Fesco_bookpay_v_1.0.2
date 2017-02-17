package com.fesco.bookpay.entity;

import java.util.List;

/**
 * Created by gong.min on 2016/10/12.
 */
public class TestBean {


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
        private String download_Url;
        private String version_Desc;
        private int app_Type;
        private String version_Code;
        private String version_Size;
        private String version_Name;
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
