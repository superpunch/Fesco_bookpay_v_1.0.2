package com.fesco.bookpay.entity;

import java.util.ArrayList;

/**
 * Created by jiancheng.mei001 on 2016/7/29.
 */


public class ResponseModel<T> {
	/*
	 * 1.成功的三种情况,
	 *
	 * a.没有返回数据
	 *
	 * {"code":1, "msg": "success"}
	 *
	 * b.返回一个对象
	 *
	 * {"code": 1, "msg": "success", "obj":{...}}
	 *
	 * c.返回一个数组(列表)
	 *
	 * {"code": 1, "msg": "success", "objList":[{...}, {...}], "currentPage":
	 * 1,"hasMore":true }
	 *
	 * 2.失败 {"code": 0, "msg": "失败的原因"}
	 *
	 * 3.因账号在其他地方登录，导致当前登录失效
	 *
	 * {"code": -1, "msg": "您的账号在其他地方登录，请重新登录"}
	 *
	 *
	 * 参数说明：
	 *
	 * code: int类型，-1 表示账号登录失效；0 表示因为某种原因导致操作失败 ；1 表示操作处理成功
	 *
	 * msg:String类型,描述操作的结果信息，event 为1时，可不传
	 *
	 * obj:对象，结果是一个对象，则直接表示未obj
	 *
	 * objList:对象列表，结果是一个列表，则用objList表示
	 *
	 * currentPage:当前操作的页码
	 *
	 * hasMore:是否还有更多数据
	 */

    private int code;
    private String msg;
    private T obj;
    private ArrayList<T> objList;
    private boolean hasMore;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public ArrayList<T> getObjList() {
        return objList;
    }

    public void setObjList(ArrayList<T> objList) {
        this.objList = objList;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

}

