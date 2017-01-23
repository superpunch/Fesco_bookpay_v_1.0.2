package com.fesco.bookpay.entity;

import java.io.Serializable;

/**
 * Created by gong.min on 2016/9/13.
 */
public class PersonInformation implements Serializable{

    private static final long serialVersionUID = 6554444588868113598L;
    /**
     * emp_Name : 胡松
     * phone : 010-65874733
     * weixinid : hughsong
     * address : null
     * email : hu.song@fesco.com.cn
     * zipcode : null
     * gender : 1
     * emp_Id : 163
     * mobile : 18611279997
     */

    private String emp_Name;
    private String phone;
    private String weixinid;
    private Object address;
    private String email;
    private Object zipcode;
    private int gender;
    private int emp_Id;
    private String mobile;

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

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getZipcode() {
        return zipcode;
    }

    public void setZipcode(Object zipcode) {
        this.zipcode = zipcode;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getEmp_Id() {
        return emp_Id;
    }

    public void setEmp_Id(int emp_Id) {
        this.emp_Id = emp_Id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
