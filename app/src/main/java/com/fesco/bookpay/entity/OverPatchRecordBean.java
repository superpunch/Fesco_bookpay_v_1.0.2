package com.fesco.bookpay.entity;

import java.util.List;

/**
 * 加班记录
 * Created by gong.min on 2016/9/29.
 */
public class OverPatchRecordBean {


    /**
     * exam_End_Is : 2
     * end_Time : 1475140440000
     * apply_Id : 638
     * reason : 2
     * cust_Id : 29
     * time_Unit_Name : null
     * begin_Time : 1475140440000
     * time_Unit : 2
     * emp_Name : 胡松
     * currApprovalMan : 解朝辉
     * pay_Money : null
     * exam_End_Is_Name : null
     * apply_Date : 1475140569000
     * exam_Step_Is_Over : 0
     * emp_Id : 163
     * work_Duration : 12
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int exam_End_Is;
        private long end_Time;
        private int apply_Id;
        private String reason;
        private int cust_Id;
        private Object time_Unit_Name;
        private long begin_Time;
        private int time_Unit;
        private String emp_Name;
        private String currApprovalMan;
        private Object pay_Money;
        private Object exam_End_Is_Name;
        private long apply_Date;
        private int exam_Step_Is_Over;
        private int emp_Id;
        private String work_Duration;

        public int getExam_End_Is() {
            return exam_End_Is;
        }

        public void setExam_End_Is(int exam_End_Is) {
            this.exam_End_Is = exam_End_Is;
        }

        public long getEnd_Time() {
            return end_Time;
        }

        public void setEnd_Time(long end_Time) {
            this.end_Time = end_Time;
        }

        public int getApply_Id() {
            return apply_Id;
        }

        public void setApply_Id(int apply_Id) {
            this.apply_Id = apply_Id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public Object getTime_Unit_Name() {
            return time_Unit_Name;
        }

        public void setTime_Unit_Name(Object time_Unit_Name) {
            this.time_Unit_Name = time_Unit_Name;
        }

        public long getBegin_Time() {
            return begin_Time;
        }

        public void setBegin_Time(long begin_Time) {
            this.begin_Time = begin_Time;
        }

        public int getTime_Unit() {
            return time_Unit;
        }

        public void setTime_Unit(int time_Unit) {
            this.time_Unit = time_Unit;
        }

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public String getCurrApprovalMan() {
            return currApprovalMan;
        }

        public void setCurrApprovalMan(String currApprovalMan) {
            this.currApprovalMan = currApprovalMan;
        }

        public Object getPay_Money() {
            return pay_Money;
        }

        public void setPay_Money(Object pay_Money) {
            this.pay_Money = pay_Money;
        }

        public Object getExam_End_Is_Name() {
            return exam_End_Is_Name;
        }

        public void setExam_End_Is_Name(Object exam_End_Is_Name) {
            this.exam_End_Is_Name = exam_End_Is_Name;
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

        public String getWork_Duration() {
            return work_Duration;
        }

        public void setWork_Duration(String work_Duration) {
            this.work_Duration = work_Duration;
        }
    }
}
