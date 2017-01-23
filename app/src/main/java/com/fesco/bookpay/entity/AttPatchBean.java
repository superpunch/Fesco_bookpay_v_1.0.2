package com.fesco.bookpay.entity;

import java.util.List;

/**
 * Created by gong.min on 2016/9/27.
 */
public class AttPatchBean {


    /**
     * defaultApprovalMan : null
     * approvalManList : [{"emp_Name":"胡松"}]
     */

    private Object defaultApprovalMan;
    /**
     * emp_Name : 胡松
     */

    private List<ApprovalManListBean> approvalManList;

    public Object getDefaultApprovalMan() {
        return defaultApprovalMan;
    }

    public void setDefaultApprovalMan(Object defaultApprovalMan) {
        this.defaultApprovalMan = defaultApprovalMan;
    }

    public List<ApprovalManListBean> getApprovalManList() {
        return approvalManList;
    }

    public void setApprovalManList(List<ApprovalManListBean> approvalManList) {
        this.approvalManList = approvalManList;
    }

    public static class ApprovalManListBean {
        private String emp_Name;
        private String emp_Id;
        public String getEmp_Name() {
            return emp_Name;
        }

        public String getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(String emp_Id) {
            this.emp_Id = emp_Id;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        @Override
        public String toString() {
            return "ApprovalManListBean{" +
                    "emp_Name='" + emp_Name + '\'' +
                    ", emp_Id='" + emp_Id + '\'' +
                    '}';
        }

    }
}
