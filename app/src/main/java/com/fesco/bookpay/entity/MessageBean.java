package com.fesco.bookpay.entity;

import java.util.List;

/**
 * Created by gong.min on 2016/11/15.
 */
public class MessageBean {


    /**
     * {"picIds":[67],"errcode":0}
     * message : error
     * errcode : 2
     */

    private String message;
    private int errcode;

    public List<Integer> getPicIds() {
        return picIds;
    }

    public void setPicIds(List<Integer> picIds) {
        this.picIds = picIds;
    }

    private List<Integer> picIds;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
}
