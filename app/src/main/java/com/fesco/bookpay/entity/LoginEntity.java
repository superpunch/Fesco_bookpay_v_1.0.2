package com.fesco.bookpay.entity;

import java.io.Serializable;

/**
 * Created by gong.min on 2016/9/1.
 */
public class LoginEntity implements Serializable {
    private static final long serialVersionUID = -160181988123451393L;
    /**
     * emp_Name : 胡松
     * cust_Name : 中瑞
     * SUCCESS : success
     * token : f83HkDXtOYnRBeg0+vU84k0WbcI5hR7eP65gtAuItObjRV/gy4uU7lSWon6pM5+q
     * emp_Id : 163  员工id
     * cust_Id : 29  公司id
     * ERROR :get token error.
     * login_Password  f9uiGzKzuGu6G2GsGnoKtQ==
     */

    private String login_Password;
    private String emp_Name;
    private String cust_Name;
    private String SUCCESS;
    private String ERROR;

    public String getLogin_Password() {
        return login_Password == null ? "" : login_Password;
    }

    public void setLogin_Password(String login_Password) {
        this.login_Password = login_Password;
    }

    public String getERROR() {
        return ERROR == null ? "" : ERROR;
    }

    private String token;
    private int emp_Id;
    private int cust_Id;

    public String getEmp_Name() {
        return emp_Name;
    }

    public void setEmp_Name(String emp_Name) {
        this.emp_Name = emp_Name;
    }

    public String getCust_Name() {
        return cust_Name;
    }

    public void setCust_Name(String cust_Name) {
        this.cust_Name = cust_Name;
    }

    public String getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(String SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getEmp_Id() {
        return emp_Id;
    }

    public void setEmp_Id(int emp_Id) {
        this.emp_Id = emp_Id;
    }

    public int getCust_Id() {
        return cust_Id;
    }

    public void setCust_Id(int cust_Id) {
        this.cust_Id = cust_Id;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "emp_Name='" + emp_Name + '\'' +
                ", cust_Name='" + cust_Name + '\'' +
                ", SUCCESS='" + SUCCESS + '\'' +
                ", token='" + token + '\'' +
                ", emp_Id=" + emp_Id +
                ", cust_Id=" + cust_Id +
                '}';
    }
}
