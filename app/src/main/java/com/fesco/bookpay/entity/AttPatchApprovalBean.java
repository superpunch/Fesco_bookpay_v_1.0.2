package com.fesco.bookpay.entity;

import java.util.List;

/**
 * Created by gong.min on 2016/9/23.
 */
public class AttPatchApprovalBean {


    /**
     * message : success
     * list : [{"check_Time":1473411600000,"emp_Name":"胡松","check_Type":1,"currApprovalMan":"胡松","exam_End_Is":1,"memo":"Fff","apply_Id":106,"cust_Id":29,"cust_Addr":"丹棱街5号","apply_Date":1473411707000,"exam_Step_Is_Over":1,"emp_Id":163},{"check_Time":1473406320000,"emp_Name":"胡松","check_Type":1,"currApprovalMan":"胡松","exam_End_Is":0,"memo":"Fff","apply_Id":105,"cust_Id":29,"cust_Addr":"huwang","apply_Date":1473406426000,"exam_Step_Is_Over":1,"emp_Id":163},{"check_Time":1472214840000,"emp_Name":"胡松","check_Type":2,"currApprovalMan":"胡松","exam_End_Is":1,"memo":"忘记打卡","apply_Id":53,"cust_Id":29,"cust_Addr":"外企","apply_Date":1472434596000,"exam_Step_Is_Over":1,"emp_Id":163},{"check_Time":1470999060000,"emp_Name":"胡松","check_Type":3,"currApprovalMan":"胡松","exam_End_Is":1,"memo":"忘记打卡测试","apply_Id":21,"cust_Id":29,"cust_Addr":"外企","apply_Date":1471225100000,"exam_Step_Is_Over":1,"emp_Id":163}]
     */

    private String message;
    /**
     * check_Time : 1473411600000  签到时间
     * emp_Name : 胡松
     * check_Type : 1   签到状态类型 // 1 签到 , 2 签退, 3 外勤
     * currApprovalMan : 胡松
     * exam_End_Is : 1    // 1 通过, 2 正在审批 , 3 审批未通过
     * memo : Fff
     * apply_Id : 106
     * cust_Id : 29
     * cust_Addr : 丹棱街5号
     * apply_Date : 1473411707000  申请时间
     * exam_Step_Is_Over : 1  // 0 未结束 , 1 结束
     * emp_Id : 163
     */

    private List<ListBean> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String check_Time;
        private String emp_Name;
        private int check_Type;
        private String currApprovalMan;
        private int exam_End_Is;
        private String memo;
        private int apply_Id;
        private int cust_Id;
        private String cust_Addr;
        private String apply_Date;
        private int exam_Step_Is_Over;
        private int emp_Id;

        public String getCheck_Time() {
            return check_Time;
        }

        public void setCheck_Time(String check_Time) {
            this.check_Time = check_Time;
        }

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public int getCheck_Type() {
            return check_Type;
        }

        public void setCheck_Type(int check_Type) {
            this.check_Type = check_Type;
        }

        public String getCurrApprovalMan() {
            return currApprovalMan;
        }

        public void setCurrApprovalMan(String currApprovalMan) {
            this.currApprovalMan = currApprovalMan;
        }

        public int getExam_End_Is() {
            return exam_End_Is;
        }

        public void setExam_End_Is(int exam_End_Is) {
            this.exam_End_Is = exam_End_Is;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getApply_Id() {
            return apply_Id;
        }

        public void setApply_Id(int apply_Id) {
            this.apply_Id = apply_Id;
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

        public String getApply_Date() {
            return apply_Date;
        }

        public void setApply_Date(String apply_Date) {
            this.apply_Date = apply_Date;
        }

        public int getExam_Step_Is_Over() {
            return exam_Step_Is_Over;
        }

        public void setExam_Step_Is_Over(int exam_Step_Is_Over) {
            this.exam_Step_Is_Over = exam_Step_Is_Over;
        }

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }
    }
}
