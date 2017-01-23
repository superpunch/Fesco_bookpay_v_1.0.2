package com.fesco.bookpay.entity.rbmbean;

import java.util.List;

/**
 * Created by gong.min on 2017/1/13.
 */
public class ApproveIngBean {

    /**
     * errcode : 0
     * message : success
     * apply : {"apply_Id":238,"cust_Id":29,"memo":null,"type":1,"type_Str":null,"title":"jkl","group_Id":2,"group_Name":"管理咨询部","apply_Date":1484236800000,"apply_Date_Str":null,"edit_Time":1484277053000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":4289,"emp_Name":"芮继伟（研发测试）","account_Id":1422,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":359,"detail_Id_Before_Imported":null,"apply_Id":238,"spend_Type_Str":"体检费","spend_Type":104,"cust_Id":null,"money_Amount":1.5,"bill_Num":2,"spend_Begin":1484236800000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":"fa fa-heartbeat fa-lg","emp_Id":null,"pics":[{"id":null,"detail_Id":359,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]}
     * steps : [{"is_Pass_Str":"通过审批","approval_Man_Str":"胡松","approvalTime":"2017-01-13 11:11:52"},{"is_Pass_Str":"通过审批","approval_Man_Str":"胡松","approvalTime":"2017-01-13 11:11:52"}]
     * approvalManList : [{"emp_Name":"胡松","emp_Id":"163"},{"emp_Name":"解朝辉","emp_Id":"164"},{"emp_Name":"赵洪滨","emp_Id":"261"},{"emp_Name":"孙晓川","emp_Id":"1659"},{"emp_Name":"朱迎","emp_Id":"1674"},{"emp_Name":"黄卫兰","emp_Id":"1660"},{"emp_Name":"赵静(HR)","emp_Id":"1671"},{"emp_Name":"刘晶","emp_Id":"1195"},{"emp_Name":"马楠","emp_Id":"4061"},{"emp_Name":"盛铮","emp_Id":"4081"}]
     */

    private int errcode;
    private String message;
    /**
     * apply_Id : 238
     * cust_Id : 29
     * memo : null
     * type : 1
     * type_Str : null
     * title : jkl
     * group_Id : 2
     * group_Name : 管理咨询部
     * apply_Date : 1484236800000
     * apply_Date_Str : null
     * edit_Time : 1484277053000
     * exam_End_Is : 1
     * exam_End_Is_Str : null
     * exam_Step_Is_Over : 0
     * exam_Step_Is_Over_Str : null
     * emp_Id : 4289
     * emp_Name : 芮继伟（研发测试）
     * account_Id : 1422
     * account_Name : null
     * money_Sum : null
     * approval_Man : null
     * search_Begin : null
     * search_End : null
     * details : [{"detail_Id":359,"detail_Id_Before_Imported":null,"apply_Id":238,"spend_Type_Str":"体检费","spend_Type":104,"cust_Id":null,"money_Amount":1.5,"bill_Num":2,"spend_Begin":1484236800000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":"fa fa-heartbeat fa-lg","emp_Id":null,"pics":[{"id":null,"detail_Id":359,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]
     */

    private ApplyBean apply;
    /**
     * is_Pass_Str : 通过审批
     * approval_Man_Str : 胡松
     * approvalTime : 2017-01-13 11:11:52
     */

    private List<StepsBean> steps;
    /**
     * emp_Name : 胡松
     * emp_Id : 163
     */

    private List<ApprovalManListBean> approvalManList;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApplyBean getApply() {
        return apply;
    }

    public void setApply(ApplyBean apply) {
        this.apply = apply;
    }

    public List<StepsBean> getSteps() {
        return steps;
    }

    public void setSteps(List<StepsBean> steps) {
        this.steps = steps;
    }

    public List<ApprovalManListBean> getApprovalManList() {
        return approvalManList;
    }

    public void setApprovalManList(List<ApprovalManListBean> approvalManList) {
        this.approvalManList = approvalManList;
    }

    public static class ApplyBean {
        private int apply_Id;
        private int cust_Id;
        private Object memo;
        private int type;
        private Object type_Str;
        private String title;
        private int group_Id;
        private String group_Name;
        private long apply_Date;
        private Object apply_Date_Str;
        private long edit_Time;
        private int exam_End_Is;
        private Object exam_End_Is_Str;
        private int exam_Step_Is_Over;
        private Object exam_Step_Is_Over_Str;
        private int emp_Id;
        private String emp_Name;
        private int account_Id;
        private Object account_Name;
        private Object money_Sum;
        private Object approval_Man;
        private Object search_Begin;
        private Object search_End;
        /**
         * detail_Id : 359
         * detail_Id_Before_Imported : null
         * apply_Id : 238
         * spend_Type_Str : 体检费
         * spend_Type : 104
         * cust_Id : null
         * money_Amount : 1.5
         * bill_Num : 2
         * spend_Begin : 1484236800000
         * spend_Begin_Str : null
         * spend_End : null
         * spend_End_Str : null
         * expense_Date : null
         * spend_City : null
         * detail_Memo : null
         * trId : null
         * icon : fa fa-heartbeat fa-lg
         * emp_Id : null
         * pics : [{"id":null,"detail_Id":359,"pic_Url":null,"pic_Desc":null}]
         * pic_Ids : null
         */

        private List<DetailsBean> details;

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

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getType_Str() {
            return type_Str;
        }

        public void setType_Str(Object type_Str) {
            this.type_Str = type_Str;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getGroup_Id() {
            return group_Id;
        }

        public void setGroup_Id(int group_Id) {
            this.group_Id = group_Id;
        }

        public String getGroup_Name() {
            return group_Name;
        }

        public void setGroup_Name(String group_Name) {
            this.group_Name = group_Name;
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

        public long getEdit_Time() {
            return edit_Time;
        }

        public void setEdit_Time(long edit_Time) {
            this.edit_Time = edit_Time;
        }

        public int getExam_End_Is() {
            return exam_End_Is;
        }

        public void setExam_End_Is(int exam_End_Is) {
            this.exam_End_Is = exam_End_Is;
        }

        public Object getExam_End_Is_Str() {
            return exam_End_Is_Str;
        }

        public void setExam_End_Is_Str(Object exam_End_Is_Str) {
            this.exam_End_Is_Str = exam_End_Is_Str;
        }

        public int getExam_Step_Is_Over() {
            return exam_Step_Is_Over;
        }

        public void setExam_Step_Is_Over(int exam_Step_Is_Over) {
            this.exam_Step_Is_Over = exam_Step_Is_Over;
        }

        public Object getExam_Step_Is_Over_Str() {
            return exam_Step_Is_Over_Str;
        }

        public void setExam_Step_Is_Over_Str(Object exam_Step_Is_Over_Str) {
            this.exam_Step_Is_Over_Str = exam_Step_Is_Over_Str;
        }

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public int getAccount_Id() {
            return account_Id;
        }

        public void setAccount_Id(int account_Id) {
            this.account_Id = account_Id;
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

        public Object getApproval_Man() {
            return approval_Man;
        }

        public void setApproval_Man(Object approval_Man) {
            this.approval_Man = approval_Man;
        }

        public Object getSearch_Begin() {
            return search_Begin;
        }

        public void setSearch_Begin(Object search_Begin) {
            this.search_Begin = search_Begin;
        }

        public Object getSearch_End() {
            return search_End;
        }

        public void setSearch_End(Object search_End) {
            this.search_End = search_End;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class DetailsBean {
            private int detail_Id;
            private Object detail_Id_Before_Imported;
            private int apply_Id;
            private String spend_Type_Str;
            private int spend_Type;
            private Object cust_Id;
            private double money_Amount;
            private int bill_Num;
            private long spend_Begin;
            private Object spend_Begin_Str;
            private Object spend_End;
            private Object spend_End_Str;
            private Object expense_Date;
            private Object spend_City;
            private Object detail_Memo;
            private Object trId;
            private String icon;
            private Object emp_Id;
            private Object pic_Ids;
            /**
             * id : null
             * detail_Id : 359
             * pic_Url : null
             * pic_Desc : null
             */

            private List<PicsBean> pics;

            public int getDetail_Id() {
                return detail_Id;
            }

            public void setDetail_Id(int detail_Id) {
                this.detail_Id = detail_Id;
            }

            public Object getDetail_Id_Before_Imported() {
                return detail_Id_Before_Imported;
            }

            public void setDetail_Id_Before_Imported(Object detail_Id_Before_Imported) {
                this.detail_Id_Before_Imported = detail_Id_Before_Imported;
            }

            public int getApply_Id() {
                return apply_Id;
            }

            public void setApply_Id(int apply_Id) {
                this.apply_Id = apply_Id;
            }

            public String getSpend_Type_Str() {
                return spend_Type_Str;
            }

            public void setSpend_Type_Str(String spend_Type_Str) {
                this.spend_Type_Str = spend_Type_Str;
            }

            public int getSpend_Type() {
                return spend_Type;
            }

            public void setSpend_Type(int spend_Type) {
                this.spend_Type = spend_Type;
            }

            public Object getCust_Id() {
                return cust_Id;
            }

            public void setCust_Id(Object cust_Id) {
                this.cust_Id = cust_Id;
            }

            public double getMoney_Amount() {
                return money_Amount;
            }

            public void setMoney_Amount(double money_Amount) {
                this.money_Amount = money_Amount;
            }

            public int getBill_Num() {
                return bill_Num;
            }

            public void setBill_Num(int bill_Num) {
                this.bill_Num = bill_Num;
            }

            public long getSpend_Begin() {
                return spend_Begin;
            }

            public void setSpend_Begin(long spend_Begin) {
                this.spend_Begin = spend_Begin;
            }

            public Object getSpend_Begin_Str() {
                return spend_Begin_Str;
            }

            public void setSpend_Begin_Str(Object spend_Begin_Str) {
                this.spend_Begin_Str = spend_Begin_Str;
            }

            public Object getSpend_End() {
                return spend_End;
            }

            public void setSpend_End(Object spend_End) {
                this.spend_End = spend_End;
            }

            public Object getSpend_End_Str() {
                return spend_End_Str;
            }

            public void setSpend_End_Str(Object spend_End_Str) {
                this.spend_End_Str = spend_End_Str;
            }

            public Object getExpense_Date() {
                return expense_Date;
            }

            public void setExpense_Date(Object expense_Date) {
                this.expense_Date = expense_Date;
            }

            public Object getSpend_City() {
                return spend_City;
            }

            public void setSpend_City(Object spend_City) {
                this.spend_City = spend_City;
            }

            public Object getDetail_Memo() {
                return detail_Memo;
            }

            public void setDetail_Memo(Object detail_Memo) {
                this.detail_Memo = detail_Memo;
            }

            public Object getTrId() {
                return trId;
            }

            public void setTrId(Object trId) {
                this.trId = trId;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public Object getEmp_Id() {
                return emp_Id;
            }

            public void setEmp_Id(Object emp_Id) {
                this.emp_Id = emp_Id;
            }

            public Object getPic_Ids() {
                return pic_Ids;
            }

            public void setPic_Ids(Object pic_Ids) {
                this.pic_Ids = pic_Ids;
            }

            public List<PicsBean> getPics() {
                return pics;
            }

            public void setPics(List<PicsBean> pics) {
                this.pics = pics;
            }

            public static class PicsBean {
                private Object id;
                private int detail_Id;
                private Object pic_Url;
                private Object pic_Desc;

                public Object getId() {
                    return id;
                }

                public void setId(Object id) {
                    this.id = id;
                }

                public int getDetail_Id() {
                    return detail_Id;
                }

                public void setDetail_Id(int detail_Id) {
                    this.detail_Id = detail_Id;
                }

                public Object getPic_Url() {
                    return pic_Url;
                }

                public void setPic_Url(Object pic_Url) {
                    this.pic_Url = pic_Url;
                }

                public Object getPic_Desc() {
                    return pic_Desc;
                }

                public void setPic_Desc(Object pic_Desc) {
                    this.pic_Desc = pic_Desc;
                }
            }
        }
    }

    public static class StepsBean {
        private String is_Pass_Str;
        private String approval_Man_Str;
        private String approvalTime;

        public String getIs_Pass_Str() {
            return is_Pass_Str;
        }

        public void setIs_Pass_Str(String is_Pass_Str) {
            this.is_Pass_Str = is_Pass_Str;
        }

        public String getApproval_Man_Str() {
            return approval_Man_Str;
        }

        public void setApproval_Man_Str(String approval_Man_Str) {
            this.approval_Man_Str = approval_Man_Str;
        }

        public String getApprovalTime() {
            return approvalTime;
        }

        public void setApprovalTime(String approvalTime) {
            this.approvalTime = approvalTime;
        }
    }

    public static class ApprovalManListBean {
        private String emp_Name;
        private String emp_Id;

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public String getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(String emp_Id) {
            this.emp_Id = emp_Id;
        }
    }
}
