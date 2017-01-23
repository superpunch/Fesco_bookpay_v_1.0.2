package com.fesco.bookpay.entity.statisticbean;

import java.util.List;

/**
 *
 * Created by gong.min on 2016/10/24.
 */
public class CheckListBean {


    /**
     * normal=正常，lateArrive=迟到，earlyLeave=早退，offWork=旷工，holiday=请假，extraWork=加班；
     * res : [["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["earlyLeave"],["normal"],["normal"],["normal"],["normal"],["earlyLeave"],["normal"],["normal"],["normal"],["earlyLeave"],["normal"],["normal"],["normal"],["normal"],["earlyLeave"],["normal"],["normal"],["lateArrive"],["lateArrive"],["normal"],["holiday"],["holiday"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["lateArrive","earlyLeave"],["normal"],["normal"],["lateArrive"],["earlyLeave"],["normal"],["earlyLeave"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"],["normal"]]
     * errcode : 0
     */

    private int errcode;
    private List<List<String>> res;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public List<List<String>> getRes() {
        return res;
    }

    public void setRes(List<List<String>> res) {
        this.res = res;
    }
}
