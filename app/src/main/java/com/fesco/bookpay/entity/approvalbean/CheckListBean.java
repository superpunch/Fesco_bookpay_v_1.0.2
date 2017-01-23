package com.fesco.bookpay.entity.approvalbean;

import java.util.List;

/**审批 签到列表
 * Created by gong.min on 2016/10/13.
 */
public class CheckListBean {


    /**
     * check_Time : 1475197200000
     * emp_Name : 佟秋艳
     * check_Type : 1
     * currApprovalMan : null
     * exam_End_Is : 2
     * memo : 忘打卡
     * apply_Id : 182
     * cust_Id : 29
     * cust_Addr : FESCO西区
     * apply_Date : 1475211664000
     * exam_Step_Is_Over : 0
     * emp_Id : 1673
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private long check_Time;
        private String emp_Name;
        private int check_Type;
        private Object currApprovalMan;
        private int exam_End_Is;
        private String memo;
        private int apply_Id;
        private int cust_Id;
        private String cust_Addr;
        private long apply_Date;
        private int exam_Step_Is_Over;
        private int emp_Id;

        public long getCheck_Time() {
            return check_Time;
        }

        public void setCheck_Time(long check_Time) {
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

        public Object getCurrApprovalMan() {
            return currApprovalMan;
        }

        public void setCurrApprovalMan(Object currApprovalMan) {
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

        public long getApply_Date() {
            return apply_Date;
        }

        public void setApply_Date(long apply_Date) {
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
