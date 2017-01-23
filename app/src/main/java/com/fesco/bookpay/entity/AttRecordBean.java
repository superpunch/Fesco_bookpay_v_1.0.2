package com.fesco.bookpay.entity;

import java.util.List;

/**
 * Created by gong.min on 2016/9/22.
 */
public class AttRecordBean {


    /**
     * beginTime : 1474505241000
     * check_Type : 1
     * endTime : null
     * memo : null
     * cust_Addr : 雨霖大厦
     */
    private String  message;  //error

    public String getMessage() {
        return message == null ? "" : message;

    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String beginTime;
        private int check_Type;
        private String endTime;
        private Object memo;
        private String cust_Addr;

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public int getCheck_Type() {
            return check_Type;
        }

        public void setCheck_Type(int check_Type) {
            this.check_Type = check_Type;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public String getCust_Addr() {
            return cust_Addr;
        }

        public void setCust_Addr(String cust_Addr) {
            this.cust_Addr = cust_Addr;
        }
    }
}
