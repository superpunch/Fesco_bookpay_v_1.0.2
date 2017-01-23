package com.fesco.bookpay.entity;

import java.util.List;

/**
 * Created by gong.min on 2016/11/24.
 */
public class ReimburseBean {


    /**
     * message : success
     * list : [{"type_Str":"日常报销单","group_Id":100,"exam_End_Is":1,"search_Begin":null,"account_Id":1922226633855887,"memo":"ceshi","apply_Id":30,"cust_Id":29,"account_Name":null,"money_Sum":null,"type":1,"exam_Step_Is_Over_Str":null,"search_End":null,"edit_Time":1479883471000,"emp_Name":null,"title":"tijiao","details":[{"icon":null,"spend_Type":18,"apply_Id":30,"detail_Memo":"ceshi","spend_End_Str":null,"spend_City":null,"spend_Begin":1479225600000,"trId":null,"spend_Type_Str":"交通-公交","bill_Num":1,"money_Amount":100,"pics":[],"spend_End":null,"expense_Date":null,"spend_Begin_Str":null,"pic_Ids":null,"detail_Id":null,"emp_Id":null}],"group_Name":null,"exam_End_Is_Str":null,"apply_Date":1479398400000,"apply_Date_Str":null,"exam_Step_Is_Over":0,"approval_Man":null,"emp_Id":163},{"type_Str":"日常报销单","group_Id":94,"exam_End_Is":0,"search_Begin":null,"account_Id":1922226633855887,"memo":"ceshi","apply_Id":28,"cust_Id":29,"account_Name":null,"money_Sum":null,"type":1,"exam_Step_Is_Over_Str":null,"search_End":null,"edit_Time":1479883471000,"emp_Name":null,"title":"ceshi","details":[{"icon":null,"spend_Type":21,"apply_Id":28,"detail_Memo":"ceshi","spend_End_Str":null,"spend_City":null,"spend_Begin":1479398400000,"trId":null,"spend_Type_Str":"餐饮-肯德基","bill_Num":1,"money_Amount":100,"pics":[],"spend_End":null,"expense_Date":null,"spend_Begin_Str":null,"pic_Ids":null,"detail_Id":null,"emp_Id":null},{"icon":null,"spend_Type":18,"apply_Id":28,"detail_Memo":"ceshi","spend_End_Str":null,"spend_City":null,"spend_Begin":1479225600000,"trId":null,"spend_Type_Str":"交通-公交","bill_Num":1,"money_Amount":100,"pics":[],"spend_End":null,"expense_Date":null,"spend_Begin_Str":null,"pic_Ids":null,"detail_Id":null,"emp_Id":null}],"group_Name":null,"exam_End_Is_Str":null,"apply_Date":1479398400000,"apply_Date_Str":null,"exam_Step_Is_Over":0,"approval_Man":null,"emp_Id":163}]
     * errcode : 0
     */

    private String message;
    private int errcode;
    /**
     * type_Str : 日常报销单
     * group_Id : 100
     * exam_End_Is : 1
     * search_Begin : null
     * account_Id : 1922226633855887
     * memo : ceshi
     * apply_Id : 30
     * cust_Id : 29
     * account_Name : null
     * money_Sum : null
     * type : 1
     * exam_Step_Is_Over_Str : null
     * search_End : null
     * edit_Time : 1479883471000
     * emp_Name : null
     * title : tijiao
     * details : [{"icon":null,"spend_Type":18,"apply_Id":30,"detail_Memo":"ceshi","spend_End_Str":null,"spend_City":null,"spend_Begin":1479225600000,"trId":null,"spend_Type_Str":"交通-公交","bill_Num":1,"money_Amount":100,"pics":[],"spend_End":null,"expense_Date":null,"spend_Begin_Str":null,"pic_Ids":null,"detail_Id":null,"emp_Id":null}]
     * group_Name : null
     * exam_End_Is_Str : null
     * apply_Date : 1479398400000
     * apply_Date_Str : null
     * exam_Step_Is_Over : 0
     * approval_Man : null
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

    public static class ListBean {
        private String type_Str;
        private int group_Id;
        private int exam_End_Is;
        private Object search_Begin;
        private long account_Id;
        private String memo;
        private int apply_Id;
        private int cust_Id;
        private Object account_Name;
        private Object money_Sum;
        private int type;
        private Object exam_Step_Is_Over_Str;
        private Object search_End;
        private long edit_Time;
        private Object emp_Name;
        private String title;
        private Object group_Name;
        private Object exam_End_Is_Str;
        private long apply_Date;
        private Object apply_Date_Str;
        private int exam_Step_Is_Over;
        private Object approval_Man;
        private int emp_Id;
        /**
         * icon : null
         * spend_Type : 18
         * apply_Id : 30
         * detail_Memo : ceshi
         * spend_End_Str : null
         * spend_City : null
         * spend_Begin : 1479225600000
         * trId : null
         * spend_Type_Str : 交通-公交
         * bill_Num : 1
         * money_Amount : 100
         * pics : []
         * spend_End : null
         * expense_Date : null
         * spend_Begin_Str : null
         * pic_Ids : null
         * detail_Id : null
         * emp_Id : null
         */

        private List<DetailsBean> details;

        public String getType_Str() {
            return type_Str;
        }

        public void setType_Str(String type_Str) {
            this.type_Str = type_Str;
        }

        public int getGroup_Id() {
            return group_Id;
        }

        public void setGroup_Id(int group_Id) {
            this.group_Id = group_Id;
        }

        public int getExam_End_Is() {
            return exam_End_Is;
        }

        public void setExam_End_Is(int exam_End_Is) {
            this.exam_End_Is = exam_End_Is;
        }

        public Object getSearch_Begin() {
            return search_Begin;
        }

        public void setSearch_Begin(Object search_Begin) {
            this.search_Begin = search_Begin;
        }

        public long getAccount_Id() {
            return account_Id;
        }

        public void setAccount_Id(long account_Id) {
            this.account_Id = account_Id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getApply_Id() {
            return apply_Id;
        }

        public void setApply_Id(int apply_Id) {
            this.apply_Id = apply_Id;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public Object getAccount_Name() {
            return account_Name;
        }

        public void setAccount_Name(Object account_Name) {
            this.account_Name = account_Name;
        }

        public Object getMoney_Sum() {
            return money_Sum;
        }

        public void setMoney_Sum(Object money_Sum) {
            this.money_Sum = money_Sum;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getExam_Step_Is_Over_Str() {
            return exam_Step_Is_Over_Str;
        }

        public void setExam_Step_Is_Over_Str(Object exam_Step_Is_Over_Str) {
            this.exam_Step_Is_Over_Str = exam_Step_Is_Over_Str;
        }

        public Object getSearch_End() {
            return search_End;
        }

        public void setSearch_End(Object search_End) {
            this.search_End = search_End;
        }

        public long getEdit_Time() {
            return edit_Time;
        }

        public void setEdit_Time(long edit_Time) {
            this.edit_Time = edit_Time;
        }

        public Object getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(Object emp_Name) {
            this.emp_Name = emp_Name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getGroup_Name() {
            return group_Name;
        }

        public void setGroup_Name(Object group_Name) {
            this.group_Name = group_Name;
        }

        public Object getExam_End_Is_Str() {
            return exam_End_Is_Str;
        }

        public void setExam_End_Is_Str(Object exam_End_Is_Str) {
            this.exam_End_Is_Str = exam_End_Is_Str;
        }

        public long getApply_Date() {
            return apply_Date;
        }

        public void setApply_Date(long apply_Date) {
            this.apply_Date = apply_Date;
        }

        public Object getApply_Date_Str() {
            return apply_Date_Str;
        }

        public void setApply_Date_Str(Object apply_Date_Str) {
            this.apply_Date_Str = apply_Date_Str;
        }

        public int getExam_Step_Is_Over() {
            return exam_Step_Is_Over;
        }

        public void setExam_Step_Is_Over(int exam_Step_Is_Over) {
            this.exam_Step_Is_Over = exam_Step_Is_Over;
        }

        public Object getApproval_Man() {
            return approval_Man;
        }

        public void setApproval_Man(Object approval_Man) {
            this.approval_Man = approval_Man;
        }

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class DetailsBean {
            private Object icon;
            private int spend_Type;
            private int apply_Id;
            private String detail_Memo;
            private Object spend_End_Str;
            private Object spend_City;
            private long spend_Begin;
            private Object trId;
            private String spend_Type_Str;
            private int bill_Num;
            private int money_Amount;
            private Object spend_End;
            private Object expense_Date;
            private Object spend_Begin_Str;
            private Object pic_Ids;
            private Object detail_Id;
            private Object emp_Id;
            private List<?> pics;

            public Object getIcon() {
                return icon;
            }

            public void setIcon(Object icon) {
                this.icon = icon;
            }

            public int getSpend_Type() {
                return spend_Type;
            }

            public void setSpend_Type(int spend_Type) {
                this.spend_Type = spend_Type;
            }

            public int getApply_Id() {
                return apply_Id;
            }

            public void setApply_Id(int apply_Id) {
                this.apply_Id = apply_Id;
            }

            public String getDetail_Memo() {
                return detail_Memo;
            }

            public void setDetail_Memo(String detail_Memo) {
                this.detail_Memo = detail_Memo;
            }

            public Object getSpend_End_Str() {
                return spend_End_Str;
            }

            public void setSpend_End_Str(Object spend_End_Str) {
                this.spend_End_Str = spend_End_Str;
            }

            public Object getSpend_City() {
                return spend_City;
            }

            public void setSpend_City(Object spend_City) {
                this.spend_City = spend_City;
            }

            public long getSpend_Begin() {
                return spend_Begin;
            }

            public void setSpend_Begin(long spend_Begin) {
                this.spend_Begin = spend_Begin;
            }

            public Object getTrId() {
                return trId;
            }

            public void setTrId(Object trId) {
                this.trId = trId;
            }

            public String getSpend_Type_Str() {
                return spend_Type_Str;
            }

            public void setSpend_Type_Str(String spend_Type_Str) {
                this.spend_Type_Str = spend_Type_Str;
            }

            public int getBill_Num() {
                return bill_Num;
            }

            public void setBill_Num(int bill_Num) {
                this.bill_Num = bill_Num;
            }

            public int getMoney_Amount() {
                return money_Amount;
            }

            public void setMoney_Amount(int money_Amount) {
                this.money_Amount = money_Amount;
            }

            public Object getSpend_End() {
                return spend_End;
            }

            public void setSpend_End(Object spend_End) {
                this.spend_End = spend_End;
            }

            public Object getExpense_Date() {
                return expense_Date;
            }

            public void setExpense_Date(Object expense_Date) {
                this.expense_Date = expense_Date;
            }

            public Object getSpend_Begin_Str() {
                return spend_Begin_Str;
            }

            public void setSpend_Begin_Str(Object spend_Begin_Str) {
                this.spend_Begin_Str = spend_Begin_Str;
            }

            public Object getPic_Ids() {
                return pic_Ids;
            }

            public void setPic_Ids(Object pic_Ids) {
                this.pic_Ids = pic_Ids;
            }

            public Object getDetail_Id() {
                return detail_Id;
            }

            public void setDetail_Id(Object detail_Id) {
                this.detail_Id = detail_Id;
            }

            public Object getEmp_Id() {
                return emp_Id;
            }

            public void setEmp_Id(Object emp_Id) {
                this.emp_Id = emp_Id;
            }

            public List<?> getPics() {
                return pics;
            }

            public void setPics(List<?> pics) {
                this.pics = pics;
            }
        }
    }
}
