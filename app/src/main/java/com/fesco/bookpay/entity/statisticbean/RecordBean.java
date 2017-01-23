package com.fesco.bookpay.entity.statisticbean;

import java.util.List;

/**
 * Created by gong.min on 2016/10/24.
 */
public class RecordBean {


    /**
     * errcode : 0
     * ceds : [{"id":17143,"emp_Name":"胡松","check_Time":1477270980000,"check_Type":1,"check_Time_Str":null,"memo":null,"cust_Id":29,"cust_Addr":"雨霖大厦","gps":"longitude:116.458938; latitude:39.922142","emp_Id":163,"ip":null},{"id":17166,"emp_Name":"胡松","check_Time":1477293720000,"check_Type":1,"check_Time_Str":null,"memo":null,"cust_Id":29,"cust_Addr":"雨霖大厦","gps":"longitude:116.458915; latitude:39.921689","emp_Id":163,"ip":null},{"id":17167,"emp_Name":"胡松","check_Time":1477293720000,"check_Type":2,"check_Time_Str":null,"memo":null,"cust_Id":29,"cust_Addr":"雨霖大厦","gps":"longitude:116.458915; latitude:39.921689","emp_Id":163,"ip":null},{"id":17168,"emp_Name":"胡松","check_Time":1477293720000,"check_Type":3,"check_Time_Str":null,"memo":"，","cust_Id":29,"cust_Addr":"雨霖大厦","gps":"longitude:116.458915; latitude:39.921689","emp_Id":163,"ip":null},{"id":17169,"emp_Name":"胡松","check_Time":1477295460000,"check_Type":2,"check_Time_Str":null,"memo":null,"cust_Id":29,"cust_Addr":"雨霖大厦","gps":"longitude:116.458748; latitude:39.922611","emp_Id":163,"ip":null}]
     */

    private int errcode;
    /**
     * id : 17143
     * emp_Name : 胡松
     * check_Time : 1477270980000
     * check_Type : 1
     * check_Time_Str : null
     * memo : null
     * cust_Id : 29
     * cust_Addr : 雨霖大厦
     * gps : longitude:116.458938; latitude:39.922142
     * emp_Id : 163
     * ip : null
     */

    private List<CedsBean> ceds;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public List<CedsBean> getCeds() {
        return ceds;
    }

    public void setCeds(List<CedsBean> ceds) {
        this.ceds = ceds;
    }

    public static class CedsBean {
        private int id;
        private String emp_Name;
        private long check_Time;
        private int check_Type;
        private Object check_Time_Str;
        private Object memo;
        private int cust_Id;
        private String cust_Addr;
        private String gps;
        private int emp_Id;
        private Object ip;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public long getCheck_Time() {
            return check_Time;
        }

        public void setCheck_Time(long check_Time) {
            this.check_Time = check_Time;
        }

        public int getCheck_Type() {
            return check_Type;
        }

        public void setCheck_Type(int check_Type) {
            this.check_Type = check_Type;
        }

        public Object getCheck_Time_Str() {
            return check_Time_Str;
        }

        public void setCheck_Time_Str(Object check_Time_Str) {
            this.check_Time_Str = check_Time_Str;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public String getCust_Addr() {
            return cust_Addr;
        }

        public void setCust_Addr(String cust_Addr) {
            this.cust_Addr = cust_Addr;
        }

        public String getGps() {
            return gps;
        }

        public void setGps(String gps) {
            this.gps = gps;
        }

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }

        public Object getIp() {
            return ip;
        }

        public void setIp(Object ip) {
            this.ip = ip;
        }
    }
}
