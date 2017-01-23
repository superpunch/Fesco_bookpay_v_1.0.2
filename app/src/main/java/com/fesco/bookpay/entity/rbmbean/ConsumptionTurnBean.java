package com.fesco.bookpay.entity.rbmbean;

import java.util.List;

/**
 * Created by gong.min on 2017/1/18.
 */
public class ConsumptionTurnBean {


    /**
     * applyTypes : [{"model_Id":1,"model_Title":"日常报销单","cust_Id":29,"memo":"日常办公报销用单据","available_Memo":1,"available_Group":2,"available_Apply_Date":2,"available_Account":1,"available_Sensitive_Field":0,"model_Status":1,"model_Status_Str":"启用中","edit_Time":1484205324000,"type_Code":1,"type_Name":"日常报销单"},{"model_Id":2,"model_Title":"差旅报销单","cust_Id":29,"memo":"memo","available_Memo":1,"available_Group":1,"available_Apply_Date":1,"available_Account":2,"available_Sensitive_Field":1,"model_Status":1,"model_Status_Str":"启用中","edit_Time":1484205318000,"type_Code":2,"type_Name":"差旅报销单"},{"model_Id":21,"model_Title":"展会报销单","cust_Id":29,"memo":"展会报销单","available_Memo":2,"available_Group":1,"available_Apply_Date":2,"available_Account":2,"available_Sensitive_Field":1,"model_Status":1,"model_Status_Str":"启用中","edit_Time":1484290289000,"type_Code":21,"type_Name":"展会报销单"}]
     * bankAccounts : [{"emp_Bank_Id":1423,"cust_Id":29,"emp_Id":4302,"emp_Name":null,"bank_Type":1,"bank_Type_Str":null,"emp_Bank_No":"23434345454543","open_Bank":"测试行","bank_Pay_Name":"张玉涛","bank_Account_Status":1,"bank_Account_Status_Str":null,"bank_Use":0,"bank_Use_Str":null,"modify_Time":null,"modifier":"husong82@foxmail.com"}]
     * errcode : 0
     * groups : [{"id":98,"cust_Id":29,"group_Id":1,"group_Name":"中瑞方胜金融服务外包（北京）有限公司","parentid":0,"group_Status":1,"memo":null},{"id":100,"cust_Id":29,"group_Id":2,"group_Name":"管理咨询部","parentid":1,"group_Status":1,"memo":null},{"id":97,"cust_Id":29,"group_Id":3,"group_Name":"会计事业部","parentid":1,"group_Status":1,"memo":" "},{"id":95,"cust_Id":29,"group_Id":4,"group_Name":"薪酬事业部","parentid":1,"group_Status":1,"memo":null},{"id":94,"cust_Id":29,"group_Id":6,"group_Name":"行政部","parentid":1,"group_Status":1,"memo":null},{"id":99,"cust_Id":29,"group_Id":7,"group_Name":"财务部","parentid":1,"group_Status":1,"memo":null},{"id":96,"cust_Id":29,"group_Id":8,"group_Name":"人力资源部","parentid":1,"group_Status":1,"memo":null},{"id":101,"cust_Id":29,"group_Id":9,"group_Name":"管理层","parentid":1,"group_Status":1,"memo":null},{"id":121,"cust_Id":29,"group_Id":10,"group_Name":"营销管理部","parentid":1,"group_Status":1,"memo":null},{"id":142,"cust_Id":29,"group_Id":11,"group_Name":"业务外包部","parentid":1,"group_Status":1,"memo":null},{"id":443,"cust_Id":29,"group_Id":12,"group_Name":"互联网事业部","parentid":6,"group_Status":1,"memo":null}]
     * message : success
     * apply : {"apply_Id":337,"cust_Id":29,"memo":"ceshi","type":1,"type_Str":"日常报销单","title":"UUUU","group_Id":4,"group_Name":null,"apply_Date":1484150400000,"apply_Date_Str":null,"edit_Time":1484731214770,"exam_End_Is":0,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":4302,"emp_Name":null,"account_Id":1423,"account_Name":null,"sensitive_Field":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":526,"detail_Id_Before_Imported":null,"apply_Id":337,"spend_Type_Str":"体检费","spend_Type":104,"cust_Id":null,"money_Amount":100,"bill_Num":1,"spend_Begin":1484150400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":"fa fa-heartbeat fa-lg","android_Icon":null,"emp_Id":null,"pics":[],"pic_Ids":null}]}
     */

    private int errcode;
    private String message;
    /**
     * apply_Id : 337
     * cust_Id : 29
     * memo : ceshi
     * type : 1
     * type_Str : 日常报销单
     * title : UUUU
     * group_Id : 4
     * group_Name : null
     * apply_Date : 1484150400000
     * apply_Date_Str : null
     * edit_Time : 1484731214770
     * exam_End_Is : 0
     * exam_End_Is_Str : null
     * exam_Step_Is_Over : 0
     * exam_Step_Is_Over_Str : null
     * emp_Id : 4302
     * emp_Name : null
     * account_Id : 1423
     * account_Name : null
     * sensitive_Field : null
     * money_Sum : null
     * approval_Man : null
     * search_Begin : null
     * search_End : null
     * details : [{"detail_Id":526,"detail_Id_Before_Imported":null,"apply_Id":337,"spend_Type_Str":"体检费","spend_Type":104,"cust_Id":null,"money_Amount":100,"bill_Num":1,"spend_Begin":1484150400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":"fa fa-heartbeat fa-lg","android_Icon":null,"emp_Id":null,"pics":[],"pic_Ids":null}]
     */

    private ApplyBean apply;
    /**
     * model_Id : 1
     * model_Title : 日常报销单
     * cust_Id : 29
     * memo : 日常办公报销用单据
     * available_Memo : 1
     * available_Group : 2
     * available_Apply_Date : 2
     * available_Account : 1
     * available_Sensitive_Field : 0
     * model_Status : 1
     * model_Status_Str : 启用中
     * edit_Time : 1484205324000
     * type_Code : 1
     * type_Name : 日常报销单
     */

    private List<ApplyTypesBean> applyTypes;
    /**
     * emp_Bank_Id : 1423
     * cust_Id : 29
     * emp_Id : 4302
     * emp_Name : null
     * bank_Type : 1
     * bank_Type_Str : null
     * emp_Bank_No : 23434345454543
     * open_Bank : 测试行
     * bank_Pay_Name : 张玉涛
     * bank_Account_Status : 1
     * bank_Account_Status_Str : null
     * bank_Use : 0
     * bank_Use_Str : null
     * modify_Time : null
     * modifier : husong82@foxmail.com
     */

    private List<BankAccountsBean> bankAccounts;
    /**
     * id : 98
     * cust_Id : 29
     * group_Id : 1
     * group_Name : 中瑞方胜金融服务外包（北京）有限公司
     * parentid : 0
     * group_Status : 1
     * memo : null
     */

    private List<GroupsBean> groups;

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

    public List<ApplyTypesBean> getApplyTypes() {
        return applyTypes;
    }

    public void setApplyTypes(List<ApplyTypesBean> applyTypes) {
        this.applyTypes = applyTypes;
    }

    public List<BankAccountsBean> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccountsBean> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<GroupsBean> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsBean> groups) {
        this.groups = groups;
    }

    public static class ApplyBean {
        private int apply_Id;
        private int cust_Id;
        private String memo;
        private int type;
        private String type_Str;
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
        private Object emp_Name;
        private int account_Id;
        private String account_Name;
        private Object sensitive_Field;
        private Object money_Sum;
        private Object approval_Man;
        private Object search_Begin;
        private Object search_End;
        /**
         * detail_Id : 526
         * detail_Id_Before_Imported : null
         * apply_Id : 337
         * spend_Type_Str : 体检费
         * spend_Type : 104
         * cust_Id : null
         * money_Amount : 100
         * bill_Num : 1
         * spend_Begin : 1484150400000
         * spend_Begin_Str : null
         * spend_End : null
         * spend_End_Str : null
         * expense_Date : null
         * spend_City : null
         * detail_Memo : null
         * trId : null
         * icon : fa fa-heartbeat fa-lg
         * android_Icon : null
         * emp_Id : null
         * pics : []
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

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getType_Str() {
            return type_Str;
        }

        public void setType_Str(String type_Str) {
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

        public Object getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(Object emp_Name) {
            this.emp_Name = emp_Name;
        }

        public int getAccount_Id() {
            return account_Id;
        }

        public void setAccount_Id(int account_Id) {
            this.account_Id = account_Id;
        }

        public String getAccount_Name() {
            return account_Name;
        }

        public void setAccount_Name(String account_Name) {
            this.account_Name = account_Name;
        }

        public Object getSensitive_Field() {
            return sensitive_Field;
        }

        public void setSensitive_Field(Object sensitive_Field) {
            this.sensitive_Field = sensitive_Field;
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
            private String money_Amount;
            private int bill_Num;
            private String spend_Begin;
            private Object spend_Begin_Str;
            private String spend_End;
            private Object spend_End_Str;
            private Object expense_Date;
            private String spend_City;
            private String detail_Memo;
            private Object trId;
            private String icon;
            private String android_Icon;
            private Object emp_Id;
            private String pic_Ids;
            /**
             * id : null
             * detail_Id : 224
             * pic_Url : null
             * pic_Desc : null
             */
         //   private List<?> pics;
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

            public String getMoney_Amount() {
                return money_Amount;
            }

            public void setMoney_Amount(String money_Amount) {
                this.money_Amount = money_Amount;
            }

            public int getBill_Num() {
                return bill_Num;
            }

            public void setBill_Num(int bill_Num) {
                this.bill_Num = bill_Num;
            }

            public String getSpend_Begin() {
                return spend_Begin;
            }

            public void setSpend_Begin(String spend_Begin) {
                this.spend_Begin = spend_Begin;
            }

            public Object getSpend_Begin_Str() {
                return spend_Begin_Str;
            }

            public void setSpend_Begin_Str(Object spend_Begin_Str) {
                this.spend_Begin_Str = spend_Begin_Str;
            }

            public String getSpend_End() {
                return spend_End;
            }

            public void setSpend_End(String spend_End) {
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

            public String getSpend_City() {
                return spend_City;
            }

            public void setSpend_City(String spend_City) {
                this.spend_City = spend_City;
            }

            public String getDetail_Memo() {
                return detail_Memo;
            }

            public void setDetail_Memo(String detail_Memo) {
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

            public String getAndroid_Icon() {
                return android_Icon == null ? "" : android_Icon;
            }

            public void setAndroid_Icon(String android_Icon) {
                this.android_Icon = android_Icon;
            }

            public Object getEmp_Id() {
                return emp_Id;
            }

            public void setEmp_Id(Object emp_Id) {
                this.emp_Id = emp_Id;
            }

            public String getPic_Ids() {
                return pic_Ids;
            }

            public void setPic_Ids(String pic_Ids) {
                this.pic_Ids = pic_Ids;
            }

            public List<PicsBean> getPics() {
                return pics;
            }

            public void setPics(List<PicsBean> pics) {
                this.pics = pics;
            }

            public static class PicsBean {
                private int id;
                private int detail_Id;
                private Object pic_Url;
                private Object pic_Desc;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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

    public static class ApplyTypesBean {
        private int model_Id;
        private String model_Title;
        private int cust_Id;
        private String memo;
        private int available_Memo;
        private int available_Group;
        private int available_Apply_Date;
        private int available_Account;
        private int available_Sensitive_Field;
        private int model_Status;
        private String model_Status_Str;
        private long edit_Time;
        private int type_Code;
        private String type_Name;

        public int getModel_Id() {
            return model_Id;
        }

        public void setModel_Id(int model_Id) {
            this.model_Id = model_Id;
        }

        public String getModel_Title() {
            return model_Title;
        }

        public void setModel_Title(String model_Title) {
            this.model_Title = model_Title;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getAvailable_Memo() {
            return available_Memo;
        }

        public void setAvailable_Memo(int available_Memo) {
            this.available_Memo = available_Memo;
        }

        public int getAvailable_Group() {
            return available_Group;
        }

        public void setAvailable_Group(int available_Group) {
            this.available_Group = available_Group;
        }

        public int getAvailable_Apply_Date() {
            return available_Apply_Date;
        }

        public void setAvailable_Apply_Date(int available_Apply_Date) {
            this.available_Apply_Date = available_Apply_Date;
        }

        public int getAvailable_Account() {
            return available_Account;
        }

        public void setAvailable_Account(int available_Account) {
            this.available_Account = available_Account;
        }

        public int getAvailable_Sensitive_Field() {
            return available_Sensitive_Field;
        }

        public void setAvailable_Sensitive_Field(int available_Sensitive_Field) {
            this.available_Sensitive_Field = available_Sensitive_Field;
        }

        public int getModel_Status() {
            return model_Status;
        }

        public void setModel_Status(int model_Status) {
            this.model_Status = model_Status;
        }

        public String getModel_Status_Str() {
            return model_Status_Str;
        }

        public void setModel_Status_Str(String model_Status_Str) {
            this.model_Status_Str = model_Status_Str;
        }

        public long getEdit_Time() {
            return edit_Time;
        }

        public void setEdit_Time(long edit_Time) {
            this.edit_Time = edit_Time;
        }

        public int getType_Code() {
            return type_Code;
        }

        public void setType_Code(int type_Code) {
            this.type_Code = type_Code;
        }

        public String getType_Name() {
            return type_Name;
        }

        public void setType_Name(String type_Name) {
            this.type_Name = type_Name;
        }
    }

    public static class BankAccountsBean {
        private int emp_Bank_Id;
        private int cust_Id;
        private int emp_Id;
        private Object emp_Name;
        private int bank_Type;
        private Object bank_Type_Str;
        private String emp_Bank_No;
        private String open_Bank;
        private String bank_Pay_Name;
        private int bank_Account_Status;
        private Object bank_Account_Status_Str;
        private int bank_Use;
        private Object bank_Use_Str;
        private Object modify_Time;
        private String modifier;

        public int getEmp_Bank_Id() {
            return emp_Bank_Id;
        }

        public void setEmp_Bank_Id(int emp_Bank_Id) {
            this.emp_Bank_Id = emp_Bank_Id;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }

        public Object getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(Object emp_Name) {
            this.emp_Name = emp_Name;
        }

        public int getBank_Type() {
            return bank_Type;
        }

        public void setBank_Type(int bank_Type) {
            this.bank_Type = bank_Type;
        }

        public Object getBank_Type_Str() {
            return bank_Type_Str;
        }

        public void setBank_Type_Str(Object bank_Type_Str) {
            this.bank_Type_Str = bank_Type_Str;
        }

        public String getEmp_Bank_No() {
            return emp_Bank_No;
        }

        public void setEmp_Bank_No(String emp_Bank_No) {
            this.emp_Bank_No = emp_Bank_No;
        }

        public String getOpen_Bank() {
            return open_Bank;
        }

        public void setOpen_Bank(String open_Bank) {
            this.open_Bank = open_Bank;
        }

        public String getBank_Pay_Name() {
            return bank_Pay_Name;
        }

        public void setBank_Pay_Name(String bank_Pay_Name) {
            this.bank_Pay_Name = bank_Pay_Name;
        }

        public int getBank_Account_Status() {
            return bank_Account_Status;
        }

        public void setBank_Account_Status(int bank_Account_Status) {
            this.bank_Account_Status = bank_Account_Status;
        }

        public Object getBank_Account_Status_Str() {
            return bank_Account_Status_Str;
        }

        public void setBank_Account_Status_Str(Object bank_Account_Status_Str) {
            this.bank_Account_Status_Str = bank_Account_Status_Str;
        }

        public int getBank_Use() {
            return bank_Use;
        }

        public void setBank_Use(int bank_Use) {
            this.bank_Use = bank_Use;
        }

        public Object getBank_Use_Str() {
            return bank_Use_Str;
        }

        public void setBank_Use_Str(Object bank_Use_Str) {
            this.bank_Use_Str = bank_Use_Str;
        }

        public Object getModify_Time() {
            return modify_Time;
        }

        public void setModify_Time(Object modify_Time) {
            this.modify_Time = modify_Time;
        }

        public String getModifier() {
            return modifier;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }
    }

    public static class GroupsBean {
        private int id;
        private int cust_Id;
        private int group_Id;
        private String group_Name;
        private int parentid;
        private int group_Status;
        private Object memo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
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

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public int getGroup_Status() {
            return group_Status;
        }

        public void setGroup_Status(int group_Status) {
            this.group_Status = group_Status;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }
    }
}
