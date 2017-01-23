package com.fesco.bookpay.entity.rbmbean;

import java.util.List;

/**
 * Created by gong.min on 2016/11/25.
 */
public class SpendTypeBean {


    /**
     * message : success
     * spendTypes : [{"id":3,"need_City":1,"icon":"fa fa-plane fa-lg","selected":0,"type_Name":"长途","is_Root":1,"p_Id":null,"cust_Id":29,"date_Type":2,"sub_Types":[{"id":17,"need_City":null,"icon":null,"selected":0,"type_Name":"灰机","is_Root":0,"p_Id":3,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null},{"id":23,"need_City":null,"icon":null,"selected":0,"type_Name":"高铁","is_Root":0,"p_Id":3,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null},{"id":24,"need_City":null,"icon":null,"selected":0,"type_Name":"动车","is_Root":0,"p_Id":3,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null}],"type_Code":1,"has_Children":null},{"id":4,"need_City":0,"icon":"fa fa-car fa-lg","selected":0,"type_Name":"交通","is_Root":1,"p_Id":null,"cust_Id":29,"date_Type":1,"sub_Types":[{"id":18,"need_City":null,"icon":null,"selected":0,"type_Name":"公交","is_Root":0,"p_Id":4,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null},{"id":19,"need_City":null,"icon":null,"selected":0,"type_Name":"地铁","is_Root":0,"p_Id":4,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null},{"id":20,"need_City":null,"icon":null,"selected":0,"type_Name":"自行车","is_Root":0,"p_Id":4,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null}],"type_Code":2,"has_Children":null},{"id":5,"need_City":0,"icon":"fa fa-cutlery fa-lg","selected":0,"type_Name":"餐饮","is_Root":1,"p_Id":null,"cust_Id":29,"date_Type":1,"sub_Types":[{"id":21,"need_City":null,"icon":null,"selected":0,"type_Name":"肯德基","is_Root":0,"p_Id":5,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null},{"id":33,"need_City":null,"icon":null,"selected":0,"type_Name":"麦当劳","is_Root":0,"p_Id":5,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null},{"id":34,"need_City":null,"icon":null,"selected":0,"type_Name":"必胜客","is_Root":0,"p_Id":5,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null}],"type_Code":3,"has_Children":null},{"id":6,"need_City":1,"icon":"fa fa-hotel fa-lg","selected":0,"type_Name":"住宿","is_Root":1,"p_Id":null,"cust_Id":29,"date_Type":2,"sub_Types":[{"id":28,"need_City":null,"icon":null,"selected":0,"type_Name":"如家","is_Root":0,"p_Id":6,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null}],"type_Code":4,"has_Children":null},{"id":7,"need_City":0,"icon":"fa fa-phone fa-lg","selected":0,"type_Name":"通讯","is_Root":1,"p_Id":null,"cust_Id":29,"date_Type":1,"sub_Types":null,"type_Code":5,"has_Children":null},{"id":8,"need_City":0,"icon":"fa fa-heart fa-lg","selected":0,"type_Name":"补助","is_Root":1,"p_Id":null,"cust_Id":29,"date_Type":1,"sub_Types":null,"type_Code":6,"has_Children":null},{"id":15,"need_City":0,"icon":"fa fa-ellipsis-h fa-lg","selected":0,"type_Name":"其他","is_Root":1,"p_Id":null,"cust_Id":29,"date_Type":1,"sub_Types":null,"type_Code":7,"has_Children":null}]
     * bankAccounts : [{"emp_Bank_No":"1922226633855887","cust_Id":29,"bank_Type_Str":null,"emp_Name":null,"bank_Use":0,"modifier":"husong82@foxmail.com","emp_Bank_Id":1401,"open_Bank":"中国银行","bank_Account_Status_Str":null,"bank_Type":1,"bank_Account_Status":1,"modify_Time":null,"bank_Pay_Name":"胡松","emp_Id":163,"bank_Use_Str":null}]
     * applyTypes : [{"type_Code":1,"type_Name":"日常报销单","cust_Id":29},{"type_Code":2,"type_Name":"差旅报销单","cust_Id":29},{"type_Code":3,"type_Name":"付款申请单","cust_Id":29}]
     * groups : [{"id":98,"group_Id":1,"parentid":0,"memo":null,"cust_Id":29,"group_Name":"中瑞方胜金融服务外包（北京）有限公司","group_Status":1},{"id":100,"group_Id":2,"parentid":1,"memo":null,"cust_Id":29,"group_Name":"管理咨询部","group_Status":1},{"id":97,"group_Id":3,"parentid":1,"memo":" ","cust_Id":29,"group_Name":"会计事业部","group_Status":1},{"id":95,"group_Id":4,"parentid":1,"memo":null,"cust_Id":29,"group_Name":"薪酬事业部","group_Status":1},{"id":94,"group_Id":6,"parentid":1,"memo":null,"cust_Id":29,"group_Name":"行政部","group_Status":1},{"id":99,"group_Id":7,"parentid":1,"memo":null,"cust_Id":29,"group_Name":"财务部","group_Status":1},{"id":96,"group_Id":8,"parentid":1,"memo":null,"cust_Id":29,"group_Name":"人力资源部","group_Status":1},{"id":101,"group_Id":9,"parentid":1,"memo":null,"cust_Id":29,"group_Name":"管理层","group_Status":1},{"id":121,"group_Id":10,"parentid":1,"memo":null,"cust_Id":29,"group_Name":"营销管理部","group_Status":1},{"id":142,"group_Id":11,"parentid":1,"memo":null,"cust_Id":29,"group_Name":"业务外包部","group_Status":1}]
     * errcode : 0
     */

    private String message;
    private int errcode;
    /**
     * id : 3
     * need_City : 1
     * icon : fa fa-plane fa-lg
     * selected : 0
     * type_Name : 长途
     * is_Root : 1
     * p_Id : null
     * cust_Id : 29
     * date_Type : 2
     * sub_Types : [{"id":17,"need_City":null,"icon":null,"selected":0,"type_Name":"灰机","is_Root":0,"p_Id":3,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null},{"id":23,"need_City":null,"icon":null,"selected":0,"type_Name":"高铁","is_Root":0,"p_Id":3,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null},{"id":24,"need_City":null,"icon":null,"selected":0,"type_Name":"动车","is_Root":0,"p_Id":3,"cust_Id":29,"date_Type":null,"sub_Types":null,"type_Code":null,"has_Children":null}]
     * type_Code : 1
     * has_Children : null
     */

    private List<SpendTypesBean> spendTypes;
    /**
     * emp_Bank_No : 1922226633855887
     * cust_Id : 29
     * bank_Type_Str : null
     * emp_Name : null
     * bank_Use : 0
     * modifier : husong82@foxmail.com
     * emp_Bank_Id : 1401
     * open_Bank : 中国银行
     * bank_Account_Status_Str : null
     * bank_Type : 1
     * bank_Account_Status : 1
     * modify_Time : null
     * bank_Pay_Name : 胡松
     * emp_Id : 163
     * bank_Use_Str : null
     */

    private List<BankAccountsBean> bankAccounts;
    /**
     * type_Code : 1
     * type_Name : 日常报销单
     * cust_Id : 29
     */

    private List<ApplyTypesBean> applyTypes;
    /**
     * id : 98
     * group_Id : 1
     * parentid : 0
     * memo : null
     * cust_Id : 29
     * group_Name : 中瑞方胜金融服务外包（北京）有限公司
     * group_Status : 1
     */

    private List<GroupsBean> groups;

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

    public List<SpendTypesBean> getSpendTypes() {
        return spendTypes;
    }

    public void setSpendTypes(List<SpendTypesBean> spendTypes) {
        this.spendTypes = spendTypes;
    }

    public List<BankAccountsBean> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccountsBean> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<ApplyTypesBean> getApplyTypes() {
        return applyTypes;
    }

    public void setApplyTypes(List<ApplyTypesBean> applyTypes) {
        this.applyTypes = applyTypes;
    }

    public List<GroupsBean> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsBean> groups) {
        this.groups = groups;
    }

    public static class SpendTypesBean {
        private int id;
        private int need_City;
        private String icon;
        private int selected;
        private String type_Name;
        private int is_Root;
        private Object p_Id;
        private int cust_Id;
        private int date_Type;
        private int type_Code;
        private Object has_Children;
        /**
         * id : 17
         * need_City : null
         * icon : null
         * selected : 0
         * type_Name : 灰机
         * is_Root : 0
         * p_Id : 3
         * cust_Id : 29
         * date_Type : null
         * sub_Types : null
         * type_Code : null
         * has_Children : null
         */

        private List<SubTypesBean> sub_Types;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNeed_City() {
            return need_City;
        }

        public void setNeed_City(int need_City) {
            this.need_City = need_City;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getSelected() {
            return selected;
        }

        public void setSelected(int selected) {
            this.selected = selected;
        }

        public String getType_Name() {
            return type_Name;
        }

        public void setType_Name(String type_Name) {
            this.type_Name = type_Name;
        }

        public int getIs_Root() {
            return is_Root;
        }

        public void setIs_Root(int is_Root) {
            this.is_Root = is_Root;
        }

        public Object getP_Id() {
            return p_Id;
        }

        public void setP_Id(Object p_Id) {
            this.p_Id = p_Id;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public int getDate_Type() {
            return date_Type;
        }

        public void setDate_Type(int date_Type) {
            this.date_Type = date_Type;
        }

        public int getType_Code() {
            return type_Code;
        }

        public void setType_Code(int type_Code) {
            this.type_Code = type_Code;
        }

        public Object getHas_Children() {
            return has_Children;
        }

        public void setHas_Children(Object has_Children) {
            this.has_Children = has_Children;
        }

        public List<SubTypesBean> getSub_Types() {
            return sub_Types;
        }

        public void setSub_Types(List<SubTypesBean> sub_Types) {
            this.sub_Types = sub_Types;
        }

        public static class SubTypesBean {
            private int id;
            private Object need_City;
            private Object icon;
            private int selected;
            private String type_Name;
            private int is_Root;
            private int p_Id;
            private int cust_Id;
            private Object date_Type;
            private Object sub_Types;
            private Object type_Code;
            private Object has_Children;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getNeed_City() {
                return need_City;
            }

            public void setNeed_City(Object need_City) {
                this.need_City = need_City;
            }

            public Object getIcon() {
                return icon;
            }

            public void setIcon(Object icon) {
                this.icon = icon;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }

            public String getType_Name() {
                return type_Name;
            }

            public void setType_Name(String type_Name) {
                this.type_Name = type_Name;
            }

            public int getIs_Root() {
                return is_Root;
            }

            public void setIs_Root(int is_Root) {
                this.is_Root = is_Root;
            }

            public int getP_Id() {
                return p_Id;
            }

            public void setP_Id(int p_Id) {
                this.p_Id = p_Id;
            }

            public int getCust_Id() {
                return cust_Id;
            }

            public void setCust_Id(int cust_Id) {
                this.cust_Id = cust_Id;
            }

            public Object getDate_Type() {
                return date_Type;
            }

            public void setDate_Type(Object date_Type) {
                this.date_Type = date_Type;
            }

            public Object getSub_Types() {
                return sub_Types;
            }

            public void setSub_Types(Object sub_Types) {
                this.sub_Types = sub_Types;
            }

            public Object getType_Code() {
                return type_Code;
            }

            public void setType_Code(Object type_Code) {
                this.type_Code = type_Code;
            }

            public Object getHas_Children() {
                return has_Children;
            }

            public void setHas_Children(Object has_Children) {
                this.has_Children = has_Children;
            }
        }
    }

    public static class BankAccountsBean {
        private String emp_Bank_No;
        private int cust_Id;
        private Object bank_Type_Str;
        private Object emp_Name;
        private int bank_Use;
        private String modifier;
        private int emp_Bank_Id;
        private String open_Bank;
        private Object bank_Account_Status_Str;
        private int bank_Type;
        private int bank_Account_Status;
        private Object modify_Time;
        private String bank_Pay_Name;
        private int emp_Id;
        private Object bank_Use_Str;

        public String getEmp_Bank_No() {
            return emp_Bank_No;
        }

        public void setEmp_Bank_No(String emp_Bank_No) {
            this.emp_Bank_No = emp_Bank_No;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public Object getBank_Type_Str() {
            return bank_Type_Str;
        }

        public void setBank_Type_Str(Object bank_Type_Str) {
            this.bank_Type_Str = bank_Type_Str;
        }

        public Object getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(Object emp_Name) {
            this.emp_Name = emp_Name;
        }

        public int getBank_Use() {
            return bank_Use;
        }

        public void setBank_Use(int bank_Use) {
            this.bank_Use = bank_Use;
        }

        public String getModifier() {
            return modifier;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }

        public int getEmp_Bank_Id() {
            return emp_Bank_Id;
        }

        public void setEmp_Bank_Id(int emp_Bank_Id) {
            this.emp_Bank_Id = emp_Bank_Id;
        }

        public String getOpen_Bank() {
            return open_Bank;
        }

        public void setOpen_Bank(String open_Bank) {
            this.open_Bank = open_Bank;
        }

        public Object getBank_Account_Status_Str() {
            return bank_Account_Status_Str;
        }

        public void setBank_Account_Status_Str(Object bank_Account_Status_Str) {
            this.bank_Account_Status_Str = bank_Account_Status_Str;
        }

        public int getBank_Type() {
            return bank_Type;
        }

        public void setBank_Type(int bank_Type) {
            this.bank_Type = bank_Type;
        }

        public int getBank_Account_Status() {
            return bank_Account_Status;
        }

        public void setBank_Account_Status(int bank_Account_Status) {
            this.bank_Account_Status = bank_Account_Status;
        }

        public Object getModify_Time() {
            return modify_Time;
        }

        public void setModify_Time(Object modify_Time) {
            this.modify_Time = modify_Time;
        }

        public String getBank_Pay_Name() {
            return bank_Pay_Name;
        }

        public void setBank_Pay_Name(String bank_Pay_Name) {
            this.bank_Pay_Name = bank_Pay_Name;
        }

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }

        public Object getBank_Use_Str() {
            return bank_Use_Str;
        }

        public void setBank_Use_Str(Object bank_Use_Str) {
            this.bank_Use_Str = bank_Use_Str;
        }
    }

    public static class ApplyTypesBean {
        private int type_Code;
        private String type_Name;
        private int cust_Id;

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

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }
    }

    public static class GroupsBean {
        private int id;
        private int group_Id;
        private int parentid;
        private Object memo;
        private int cust_Id;
        private String group_Name;
        private int group_Status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGroup_Id() {
            return group_Id;
        }

        public void setGroup_Id(int group_Id) {
            this.group_Id = group_Id;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public String getGroup_Name() {
            return group_Name;
        }

        public void setGroup_Name(String group_Name) {
            this.group_Name = group_Name;
        }

        public int getGroup_Status() {
            return group_Status;
        }

        public void setGroup_Status(int group_Status) {
            this.group_Status = group_Status;
        }
    }
}
