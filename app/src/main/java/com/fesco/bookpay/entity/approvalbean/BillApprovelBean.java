package com.fesco.bookpay.entity.approvalbean;

/**
 * Created by gong.min on 2017/1/12.
 */
public class BillApprovelBean {


    /**
     * message : success
     * errcode : 0
     * checkMan : {"id":22,"cust_Id":29,"type_Id":61,"type_Name":"报销审单","type":2,"title":"第三方"}
     */

    private String message;
    private int errcode;
    /**
     * id : 22
     * cust_Id : 29
     * type_Id : 61
     * type_Name : 报销审单
     * type : 2
     * title : 第三方
     */

    private CheckManBean checkMan;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public CheckManBean getCheckMan() {
        return checkMan;
    }

    public void setCheckMan(CheckManBean checkMan) {
        this.checkMan = checkMan;
    }

    public static class CheckManBean {
        private int id;
        private int cust_Id;
        private int type_Id;
        private String type_Name;
        private int type;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public int getType_Id() {
            return type_Id;
        }

        public void setType_Id(int type_Id) {
            this.type_Id = type_Id;
        }

        public String getType_Name() {
            return type_Name;
        }

        public void setType_Name(String type_Name) {
            this.type_Name = type_Name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
