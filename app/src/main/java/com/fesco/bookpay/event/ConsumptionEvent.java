package com.fesco.bookpay.event;

/**
 * Created by gong.min on 2016/12/21.
 */
public class ConsumptionEvent {

    private String msg;

    public ConsumptionEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
