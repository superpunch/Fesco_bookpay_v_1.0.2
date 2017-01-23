package com.fesco.bookpay.entity.rbmbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gong.min on 2016/12/21.
 */
public class SpendTypesBean implements  Serializable {


    private static final long serialVersionUID = -1590030147535307221L;
    /**
     * message : success
     * list : [{"icon":null,"spend_Type":8,"detail_Memo":null,"spend_City":null,"spend_Begin":1482076800000,"bill_Num":1,"spend_Type_Str":"补助","money_Amount":88,"pics":[{"id":null,"pic_Url":null,"pic_Desc":null,"detail_Id":100}],"spend_End":null,"formatted_Date":null,"pic_Ids":null,"detail_Id":100,"emp_Id":163},{"icon":null,"spend_Type":7,"detail_Memo":null,"spend_City":null,"spend_Begin":1482163200000,"bill_Num":0,"spend_Type_Str":"通讯","money_Amount":99,"pics":[{"id":null,"pic_Url":null,"pic_Desc":null,"detail_Id":101}],"spend_End":null,"formatted_Date":null,"pic_Ids":null,"detail_Id":101,"emp_Id":163},{"icon":null,"spend_Type":15,"detail_Memo":"cc","spend_City":null,"spend_Begin":1481644800000,"bill_Num":1,"spend_Type_Str":"其他","money_Amount":55,"pics":[{"id":null,"pic_Url":null,"pic_Desc":null,"detail_Id":84}],"spend_End":null,"formatted_Date":null,"pic_Ids":null,"detail_Id":84,"emp_Id":163},{"icon":null,"spend_Type":15,"detail_Memo":null,"spend_City":"北京","spend_Begin":1480867200000,"bill_Num":0,"spend_Type_Str":"其他","money_Amount":99,"pics":[{"id":null,"pic_Url":null,"pic_Desc":null,"detail_Id":28}],"spend_End":1481040000000,"formatted_Date":null,"pic_Ids":null,"detail_Id":28,"emp_Id":163},{"icon":null,"spend_Type":7,"detail_Memo":null,"spend_City":"武汉","spend_Begin":1481040000000,"bill_Num":0,"spend_Type_Str":"通讯","money_Amount":999,"pics":[{"id":null,"pic_Url":null,"pic_Desc":null,"detail_Id":31}],"spend_End":1481212800000,"formatted_Date":null,"pic_Ids":null,"detail_Id":31,"emp_Id":163},{"icon":null,"spend_Type":28,"detail_Memo":null,"spend_City":"鞍山","spend_Begin":1481817600000,"bill_Num":0,"spend_Type_Str":"住宿-如家","money_Amount":22,"pics":[{"id":null,"pic_Url":null,"pic_Desc":null,"detail_Id":95}],"spend_End":1481817600000,"formatted_Date":null,"pic_Ids":null,"detail_Id":95,"emp_Id":163},{"icon":null,"spend_Type":8,"detail_Memo":null,"spend_City":null,"spend_Begin":1481472000000,"bill_Num":0,"spend_Type_Str":"补助","money_Amount":888,"pics":[{"id":null,"pic_Url":null,"pic_Desc":null,"detail_Id":56}],"spend_End":null,"formatted_Date":null,"pic_Ids":null,"detail_Id":56,"emp_Id":163},{"icon":null,"spend_Type":45,"detail_Memo":null,"spend_City":null,"spend_Begin":1481126400000,"bill_Num":1,"spend_Type_Str":"团建-会议室","money_Amount":23,"pics":[{"id":null,"pic_Url":null,"pic_Desc":null,"detail_Id":42}],"spend_End":null,"formatted_Date":null,"pic_Ids":null,"detail_Id":42,"emp_Id":163},{"icon":null,"spend_Type":7,"detail_Memo":"22","spend_City":null,"spend_Begin":1482076800000,"bill_Num":0,"spend_Type_Str":"通讯","money_Amount":33,"pics":[{"id":null,"pic_Url":null,"pic_Desc":null,"detail_Id":97}],"spend_End":null,"formatted_Date":null,"pic_Ids":null,"detail_Id":97,"emp_Id":163}]
     * errcode : 0
     */

    private String message;
    private int errcode;
    /**
     * icon : null
     * spend_Type : 8
     * detail_Memo : null
     * spend_City : null
     * spend_Begin : 1482076800000
     * bill_Num : 1
     * spend_Type_Str : 补助
     * money_Amount : 88
     * pics : [{"id":null,"pic_Url":null,"pic_Desc":null,"detail_Id":100}]
     * spend_End : null
     * formatted_Date : null
     * pic_Ids : null
     * detail_Id : 100
     * emp_Id : 163
     */

    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable {
        private static final long serialVersionUID = -6624113909322249324L;
        private String icon;
        private String android_Icon;

        public String getAndroid_Icon() {
            return android_Icon == null ? "" : android_Icon;
        }

        private int spend_Type;
        private String detail_Memo;
        private String spend_City;
        private String spend_Begin;
        private int bill_Num;
        private String spend_Type_Str;
        private String money_Amount;
        private String spend_End;
        private String formatted_Date;
        private String pic_Ids;
        private int detail_Id;
        private int emp_Id;
        /**
         * id : null
         * pic_Url : null
         * pic_Desc : null
         * detail_Id : 100
         */

        private List<PicsBean> pics;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getSpend_Type() {
            return spend_Type;
        }

        public void setSpend_Type(int spend_Type) {
            this.spend_Type = spend_Type;
        }

        public String getDetail_Memo() {
            return detail_Memo;
        }

        public void setDetail_Memo(String detail_Memo) {
            this.detail_Memo = detail_Memo;
        }

        public String getSpend_City() {
            return spend_City == null ? "" : spend_City;
        }

        public void setSpend_City(String spend_City) {
            this.spend_City = spend_City;
        }

        public String getSpend_Begin() {
            return spend_Begin;
        }

        public void setSpend_Begin(String spend_Begin) {
            this.spend_Begin = spend_Begin;
        }

        public int getBill_Num() {
            return bill_Num;
        }

        public void setBill_Num(int bill_Num) {
            this.bill_Num = bill_Num;
        }

        public String getSpend_Type_Str() {
            return spend_Type_Str;
        }

        public void setSpend_Type_Str(String spend_Type_Str) {
            this.spend_Type_Str = spend_Type_Str;
        }

        public String getMoney_Amount() {
            return money_Amount;
        }

        public void setMoney_Amount(String money_Amount) {
            this.money_Amount = money_Amount;
        }

        public String getSpend_End() {
            return spend_End == null ? "" : spend_End;
        }

        public void setSpend_End(String spend_End) {
            this.spend_End = spend_End;
        }

        public String getFormatted_Date() {
            return formatted_Date;
        }

        public void setFormatted_Date(String formatted_Date) {
            this.formatted_Date = formatted_Date;
        }

        public String getPic_Ids() {
            return pic_Ids;
        }

        public void setPic_Ids(String pic_Ids) {
            this.pic_Ids = pic_Ids;
        }

        public int getDetail_Id() {
            return detail_Id;
        }

        public void setDetail_Id(int detail_Id) {
            this.detail_Id = detail_Id;
        }

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }

        public List<PicsBean> getPics() {
            return pics;
        }

        public void setPics(List<PicsBean> pics) {
            this.pics = pics;
        }

        public static class PicsBean implements Serializable {
            private static final long serialVersionUID = -5761622506398756529L;
            private String id;
            private String pic_Url;
            private String pic_Desc;
            private int detail_Id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public int getDetail_Id() {
                return detail_Id;
            }

            public void setDetail_Id(int detail_Id) {
                this.detail_Id = detail_Id;
            }
        }
    }
}
