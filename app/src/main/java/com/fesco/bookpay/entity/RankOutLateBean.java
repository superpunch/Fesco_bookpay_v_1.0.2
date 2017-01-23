package com.fesco.bookpay.entity;

import java.util.List;

/**
 * Created by gong.min on 2016/10/27.
 */
public class RankOutLateBean {


    /**
     * errcode : 0
     * rankList : [{"emp_Name":"刘偲麒","duration":null,"counts":2}]
     */

    private int errcode;
    /**
     * emp_Name : 刘偲麒
     * duration : null
     * counts : 2
     */

    private List<RankListBean> rankList;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public List<RankListBean> getRankList() {
        return rankList;
    }

    public void setRankList(List<RankListBean> rankList) {
        this.rankList = rankList;
    }

    public static class RankListBean {
        private String emp_Name;
        private Object duration;
        private int counts;

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public Object getDuration() {
            return duration;
        }

        public void setDuration(Object duration) {
            this.duration = duration;
        }

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }
    }
}
