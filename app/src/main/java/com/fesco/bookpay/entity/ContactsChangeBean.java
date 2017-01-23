package com.fesco.bookpay.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gong.min on 2016/9/8.
 */
public class ContactsChangeBean implements  Serializable {

    private static final long serialVersionUID = 8791581427589306933L;

    @Override
    public String toString() {
        return "ContactsChangeBean{" +
                "emps=" + emps +
                '}';
    }

    /**
     * emp_Id : 1668
     * cust_Id : null
     * emp_Name : 杜娇娇
     * phone : 15330086200
     * mobile : 15330086200
     * group_Name : 管理咨询部
     */

    private List<EmpsBean> emps;

    public List<EmpsBean> getEmps() {
        return emps;
    }

    public void setEmps(List<EmpsBean> emps) {
        this.emps = emps;
    }

    public static class EmpsBean implements Serializable {
        private static final long serialVersionUID = 8858322951552266950L;

        @Override
        public String toString() {
            return "EmpsBean{" +
                    "emp_Id=" + emp_Id +
                    ", cust_Id=" + cust_Id +
                    ", emp_Name='" + emp_Name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", group_Name='" + group_Name + '\'' +
                    '}';
        }

        private int emp_Id;
        private Object cust_Id;
        private String emp_Name;
        private String phone;
        private String mobile;
        private String group_Name;

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }

        public Object getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(Object cust_Id) {
            this.cust_Id = cust_Id;
        }

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGroup_Name() {
            return group_Name;
        }

        public void setGroup_Name(String group_Name) {
            this.group_Name = group_Name;
        }
    }
}
