package com.fesco.bookpay.entity.rbmbean;

import java.util.List;

/**
 * Created by gong.min on 2017/1/19.
 */
public class UpImagePicsBean {
    /**
     * pics : [{"id":679,"detail_Id":null,"pic_Url":"S://expensePics/15/0/20170119093320clip_image_20170117_023905ys.jpg","pic_Desc":null}]
     * errcode : 0
     */

    private int errcode;
    /**
     * id : 679
     * detail_Id : null
     * pic_Url : S://expensePics/15/0/20170119093320clip_image_20170117_023905ys.jpg
     * pic_Desc : null
     */

    private List<PicsBean> pics;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public List<PicsBean> getPics() {
        return pics;
    }

    public void setPics(List<PicsBean> pics) {
        this.pics = pics;
    }

    public static class PicsBean {
        private String id;
        private String detail_Id;
        private String pic_Url;
        private String pic_Desc;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDetail_Id() {
            return detail_Id;
        }

        public void setDetail_Id(String detail_Id) {
            this.detail_Id = detail_Id;
        }

        public String getPic_Url() {
            return pic_Url;
        }

        public void setPic_Url(String pic_Url) {
            this.pic_Url = pic_Url;
        }

        public String getPic_Desc() {
            return pic_Desc;
        }

        public void setPic_Desc(String pic_Desc) {
            this.pic_Desc = pic_Desc;
        }
    }
}
