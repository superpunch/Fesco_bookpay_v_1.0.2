package com.fesco.bookpay.entity;

import java.util.List;

/**
 * 休假记录列表
 * Created by gong.min on 2016/10/6.
 */
public class RestRecordBean {


    /**
     * exam_End_Is : 1
     * hol_Begin : 1475078400000
     * hol_End : 1475164800000
     * cust_Id : 29
     * hol_End_Str : null
     * hol_Begin_Apm : null
     * hol_Begin_Str : null
     * hol_Set_Id : 63
     * emp_Name : 胡松
     * hol_Emp_Exam_Id : 953
     * currApprovalMan : 解朝辉
     * pay_Money : 105.0903119868637
     * hol_Name : 年假
     * hol_Num_Str : null
     * hol_Unit : 1
     * hol_End_Apm : null
     * exam_End_Is_Name : null
     * hol_Source : History_Num:2.0;
     * group_Name : null
     * momo : 回趟老家
     * hol_Num : 2
     * appl_Date : 1475048780000
     * exam_Step_Is_Over : 1
     * emp_Id : 163
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
        private long hol_Begin;
        private long hol_End;
        private int cust_Id;
        private Object hol_End_Str;
        private Object hol_Begin_Apm;
        private Object hol_Begin_Str;
        private int hol_Set_Id;
        private String emp_Name;
        private int hol_Emp_Exam_Id;
        private String currApprovalMan;
        private double pay_Money;
        private String hol_Name;
        private Object hol_Num_Str;
        private int hol_Unit;
        private Object hol_End_Apm;
        private Object exam_End_Is_Name;
        private String hol_Source;
        private Object group_Name;
        private String momo;
        private String hol_Num;
        private long appl_Date;
        private int exam_Step_Is_Over;
        private int emp_Id;

        public int getExam_End_Is() {
            return exam_End_Is;
        }

        public void setExam_End_Is(int exam_End_Is) {
            this.exam_End_Is = exam_End_Is;
        }

        public long getHol_Begin() {
            return hol_Begin;
        }

        public void setHol_Begin(long hol_Begin) {
            this.hol_Begin = hol_Begin;
        }

        public long getHol_End() {
            return hol_End;
        }

        public void setHol_End(long hol_End) {
            this.hol_End = hol_End;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public Object getHol_End_Str() {
            return hol_End_Str;
        }

        public void setHol_End_Str(Object hol_End_Str) {
            this.hol_End_Str = hol_End_Str;
        }

        public Object getHol_Begin_Apm() {
            return hol_Begin_Apm;
        }

        public void setHol_Begin_Apm(Object hol_Begin_Apm) {
            this.hol_Begin_Apm = hol_Begin_Apm;
        }

        public Object getHol_Begin_Str() {
            return hol_Begin_Str;
        }

        public void setHol_Begin_Str(Object hol_Begin_Str) {
            this.hol_Begin_Str = hol_Begin_Str;
        }

        public int getHol_Set_Id() {
            return hol_Set_Id;
        }

        public void setHol_Set_Id(int hol_Set_Id) {
            this.hol_Set_Id = hol_Set_Id;
        }

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public int getHol_Emp_Exam_Id() {
            return hol_Emp_Exam_Id;
        }

        public void setHol_Emp_Exam_Id(int hol_Emp_Exam_Id) {
            this.hol_Emp_Exam_Id = hol_Emp_Exam_Id;
        }

        public String getCurrApprovalMan() {
            return currApprovalMan;
        }

        public void setCurrApprovalMan(String currApprovalMan) {
            this.currApprovalMan = currApprovalMan;
        }

        public double getPay_Money() {
            return pay_Money;
        }

        public void setPay_Money(double pay_Money) {
            this.pay_Money = pay_Money;
        }

        public String getHol_Name() {
            return hol_Name;
        }

        public void setHol_Name(String hol_Name) {
            this.hol_Name = hol_Name;
        }

        public Object getHol_Num_Str() {
            return hol_Num_Str;
        }

        public void setHol_Num_Str(Object hol_Num_Str) {
            this.hol_Num_Str = hol_Num_Str;
        }

        public int getHol_Unit() {
            return hol_Unit;
        }

        public void setHol_Unit(int hol_Unit) {
            this.hol_Unit = hol_Unit;
        }

        public Object getHol_End_Apm() {
            return hol_End_Apm;
        }

        public void setHol_End_Apm(Object hol_End_Apm) {
            this.hol_End_Apm = hol_End_Apm;
        }

        public Object getExam_End_Is_Name() {
            return exam_End_Is_Name;
        }

        public void setExam_End_Is_Name(Object exam_End_Is_Name) {
            this.exam_End_Is_Name = exam_End_Is_Name;
        }

        public String getHol_Source() {
            return hol_Source;
        }

        public void setHol_Source(String hol_Source) {
            this.hol_Source = hol_Source;
        }

        public Object getGroup_Name() {
            return group_Name;
        }

        public void setGroup_Name(Object group_Name) {
            this.group_Name = group_Name;
        }

        public String getMomo() {
            return momo;
        }

        public void setMomo(String momo) {
            this.momo = momo;
        }

        public String getHol_Num() {
            return hol_Num;
        }

        public void setHol_Num(String hol_Num) {
            this.hol_Num = hol_Num;
        }

        public long getAppl_Date() {
            return appl_Date;
        }

        public void setAppl_Date(long appl_Date) {
            this.appl_Date = appl_Date;
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
