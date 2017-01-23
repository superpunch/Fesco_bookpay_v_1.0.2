package com.fesco.bookpay.entity.statisticbean;

import java.util.List;

/**
 * Created by gong.min on 2016/10/24.
 */
public class HolPoolBean {

    /**
     * holPoolList : [{"workday_Num_Str":null,"workday_Num":0,"availableAllNum":3.5,"cust_Id":null,"last_Year_Num_Str":null,"history_Num_Str":null,"time_Unit_Name":"天","weekend_Num_Str":null,"hol_Set_Id":63,"time_Unit":1,"emp_Name":null,"weekend_Num":0,"holiday_Num_Str":null,"hol_Name":"年假","history_Num":0,"holiday_Num":0,"year_Stamp":null,"last_Year_Num":0,"emp_Id":null},{"workday_Num_Str":null,"workday_Num":0,"availableAllNum":0,"cust_Id":null,"last_Year_Num_Str":null,"history_Num_Str":null,"time_Unit_Name":"小时","weekend_Num_Str":null,"hol_Set_Id":141,"time_Unit":2,"emp_Name":null,"weekend_Num":0,"holiday_Num_Str":null,"hol_Name":"调休","history_Num":0,"holiday_Num":0,"year_Stamp":null,"last_Year_Num":0,"emp_Id":null}]
     * errcode : 0
     */

    private int errcode;
    /**
     * workday_Num_Str : null
     * workday_Num : 0
     * availableAllNum : 3.5
     * cust_Id : null
     * last_Year_Num_Str : null
     * history_Num_Str : null
     * time_Unit_Name : 天
     * weekend_Num_Str : null
     * hol_Set_Id : 63
     * time_Unit : 1
     * emp_Name : null
     * weekend_Num : 0
     * holiday_Num_Str : null
     * hol_Name : 年假
     * history_Num : 0
     * holiday_Num : 0
     * year_Stamp : null
     * last_Year_Num : 0
     * emp_Id : null
     */

    private List<HolPoolListBean> holPoolList;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public List<HolPoolListBean> getHolPoolList() {
        return holPoolList;
    }

    public void setHolPoolList(List<HolPoolListBean> holPoolList) {
        this.holPoolList = holPoolList;
    }

    public static class HolPoolListBean {
        private Object workday_Num_Str;
        private String workday_Num;
        private double availableAllNum;
        private Object cust_Id;
        private Object last_Year_Num_Str;
        private Object history_Num_Str;
        private String time_Unit_Name;
        private Object weekend_Num_Str;
        private int hol_Set_Id;
        private int time_Unit;
        private Object emp_Name;
        private int weekend_Num;
        private Object holiday_Num_Str;
        private String hol_Name;
        private int history_Num;
        private int holiday_Num;
        private Object year_Stamp;
        private int last_Year_Num;
        private Object emp_Id;

        public Object getWorkday_Num_Str() {
            return workday_Num_Str;
        }

        public void setWorkday_Num_Str(Object workday_Num_Str) {
            this.workday_Num_Str = workday_Num_Str;
        }

        public String getWorkday_Num() {
            return workday_Num;
        }

        public void setWorkday_Num(String workday_Num) {
            this.workday_Num = workday_Num;
        }

        public double getAvailableAllNum() {
            return availableAllNum;
        }

        public void setAvailableAllNum(double availableAllNum) {
            this.availableAllNum = availableAllNum;
        }

        public Object getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(Object cust_Id) {
            this.cust_Id = cust_Id;
        }

        public Object getLast_Year_Num_Str() {
            return last_Year_Num_Str;
        }

        public void setLast_Year_Num_Str(Object last_Year_Num_Str) {
            this.last_Year_Num_Str = last_Year_Num_Str;
        }

        public Object getHistory_Num_Str() {
            return history_Num_Str;
        }

        public void setHistory_Num_Str(Object history_Num_Str) {
            this.history_Num_Str = history_Num_Str;
        }

        public String getTime_Unit_Name() {
            return time_Unit_Name;
        }

        public void setTime_Unit_Name(String time_Unit_Name) {
            this.time_Unit_Name = time_Unit_Name;
        }

        public Object getWeekend_Num_Str() {
            return weekend_Num_Str;
        }

        public void setWeekend_Num_Str(Object weekend_Num_Str) {
            this.weekend_Num_Str = weekend_Num_Str;
        }

        public int getHol_Set_Id() {
            return hol_Set_Id;
        }

        public void setHol_Set_Id(int hol_Set_Id) {
            this.hol_Set_Id = hol_Set_Id;
        }

        public int getTime_Unit() {
            return time_Unit;
        }

        public void setTime_Unit(int time_Unit) {
            this.time_Unit = time_Unit;
        }

        public Object getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(Object emp_Name) {
            this.emp_Name = emp_Name;
        }

        public int getWeekend_Num() {
            return weekend_Num;
        }

        public void setWeekend_Num(int weekend_Num) {
            this.weekend_Num = weekend_Num;
        }

        public Object getHoliday_Num_Str() {
            return holiday_Num_Str;
        }

        public void setHoliday_Num_Str(Object holiday_Num_Str) {
            this.holiday_Num_Str = holiday_Num_Str;
        }

        public String getHol_Name() {
            return hol_Name;
        }

        public void setHol_Name(String hol_Name) {
            this.hol_Name = hol_Name;
        }

        public int getHistory_Num() {
            return history_Num;
        }

        public void setHistory_Num(int history_Num) {
            this.history_Num = history_Num;
        }

        public int getHoliday_Num() {
            return holiday_Num;
        }

        public void setHoliday_Num(int holiday_Num) {
            this.holiday_Num = holiday_Num;
        }

        public Object getYear_Stamp() {
            return year_Stamp;
        }

        public void setYear_Stamp(Object year_Stamp) {
            this.year_Stamp = year_Stamp;
        }

        public int getLast_Year_Num() {
            return last_Year_Num;
        }

        public void setLast_Year_Num(int last_Year_Num) {
            this.last_Year_Num = last_Year_Num;
        }

        public Object getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(Object emp_Id) {
            this.emp_Id = emp_Id;
        }
    }
}
