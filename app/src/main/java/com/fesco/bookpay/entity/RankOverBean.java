package com.fesco.bookpay.entity;

import java.util.List;

/**
 * Created by gong.min on 2016/10/28.
 */
public class RankOverBean {


    /**
     * errcode : 0
     * rankList : [{"emp_Name":"李玲","duration":12,"counts":null}]
     */

    private int errcode;
    /**
     * emp_Name : 李玲
     * duration : 12
     * counts : null
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
        private double duration;
        private Object counts;

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(double duration) {
            this.duration = duration;
        }

        public Object getCounts() {
            return counts;
        }

        public void setCounts(Object counts) {
            this.counts = counts;
        }
    }
}
