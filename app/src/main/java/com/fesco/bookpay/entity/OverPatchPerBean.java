package com.fesco.bookpay.entity;

import java.util.List;

/**
 * 加班 审批人对象
 * <p/>
 * Created by gong.min on 2016/9/29.
 */
public class OverPatchPerBean {
//
//    {
//          "message": "error",
//              "errcode": 1
//        }
    /**
     * emp_Name : 胡松
     * emp_Id : 163
     */
    private String  message;

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private List<AvailableApprovalManListBean> availableApprovalManList;


    public List<AvailableApprovalManListBean> getAvailableApprovalManList() {
        return availableApprovalManList;
    }

    public void setAvailableApprovalManList(List<AvailableApprovalManListBean> availableApprovalManList) {
        this.availableApprovalManList = availableApprovalManList;
    }

    public static class AvailableApprovalManListBean {
        private String emp_Name;
        private String emp_Id;

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }


        public String getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(String emp_Id) {
            this.emp_Id = emp_Id;
        }
    }
}
