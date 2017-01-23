package com.fesco.bookpay.entity.approvalbean;

import java.util.List;

/**
 * Created by gong.min on 2016/10/17.
 */
public class CheckDetailBean {

    /**
     * apply_Id : 31
     * cust_Id : 29
     * emp_Id : 1618
     * check_Type : 2
     * cust_Addr : 雨霖大厦
     * check_Time : 1471513440000
     * memo : 忘了
     * exam_End_Is : 2
     * exam_Step_Is_Over : 0
     * emp_Name : 屠玲燕
     * apply_Date : 1471575442000
     * currApprovalMan : null
     */

    private ApplyBean apply;
    /**
     * step_Id : 220
     * apply_Id : 31
     * is_Pass : 1
     * is_Pass_Str : 通过审批
     * approval_Man : 163
     * approval_Man_Str : 胡松
     * approval_Time : 1476691856000
     * next_Approval_Man : 163
     * memo : null
     * is_Over : 1
     */

    private LastApprovalStepBean lastApprovalStep;
    /**
     * emp_Id : 163
     * cust_Id : 29
     * emp_Name : 胡松
     * cust_Inter_No : null
     * certificate_Type : 4
     * certificate_No : 22
     * email : hu.song@fesco.com.cn
     * phone : 010-65874733
     * login_Name : husong
     * login_Password : f9uiGzKzuGu6G2GsGnoKtQ==
     * nationality : null
     * address : null
     * zipcode : null
     * emp_Status : 1
     * tax_Base : 3500
     * photo_Url : null
     * memo : null
     * modify_Time : 1474300800000
     * modifier : 胡松

     * position : null
     * mobile : 18611279997
     * gender : 1
     * weixinid : hughsong
     * group_Id : 6
     * weixin_Status : 1
     * synch_time : 1459872000000
     * endowment_Rate : null
     * medical_Rate : null
     * unemployment_Rate : null
     * maternity_Rate : null
     * injury_Rate : null
     * housing_Fund_Rate : null
     * salary : 8000
     * isspecial : 0
     * group_Name : null
     * exam_Authority : 1
     * yearly_Hol_Num : null
     * methodname : null
     * extra_Work_Authority : 0
     */

    private List<AvailableApprovalManListBean> availableApprovalManList;

    public ApplyBean getApply() {
        return apply;
    }

    public void setApply(ApplyBean apply) {
        this.apply = apply;
    }

    public LastApprovalStepBean getLastApprovalStep() {
        return lastApprovalStep;
    }

    public void setLastApprovalStep(LastApprovalStepBean lastApprovalStep) {
        this.lastApprovalStep = lastApprovalStep;
    }

    public List<AvailableApprovalManListBean> getAvailableApprovalManList() {
        return availableApprovalManList;
    }

    public void setAvailableApprovalManList(List<AvailableApprovalManListBean> availableApprovalManList) {
        this.availableApprovalManList = availableApprovalManList;
    }

    public static class ApplyBean {
        private int apply_Id;
        private int cust_Id;
        private int emp_Id;
        private int check_Type;
        private String cust_Addr;
        private long check_Time;
        private String memo;
        private int exam_End_Is;
        private int exam_Step_Is_Over;
        private String emp_Name;
        private long apply_Date;
        private Object currApprovalMan;

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

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }

        public int getCheck_Type() {
            return check_Type;
        }

        public void setCheck_Type(int check_Type) {
            this.check_Type = check_Type;
        }

        public String getCust_Addr() {
            return cust_Addr;
        }

        public void setCust_Addr(String cust_Addr) {
            this.cust_Addr = cust_Addr;
        }

        public long getCheck_Time() {
            return check_Time;
        }

        public void setCheck_Time(long check_Time) {
            this.check_Time = check_Time;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getExam_End_Is() {
            return exam_End_Is;
        }

        public void setExam_End_Is(int exam_End_Is) {
            this.exam_End_Is = exam_End_Is;
        }

        public int getExam_Step_Is_Over() {
            return exam_Step_Is_Over;
        }

        public void setExam_Step_Is_Over(int exam_Step_Is_Over) {
            this.exam_Step_Is_Over = exam_Step_Is_Over;
        }

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public long getApply_Date() {
            return apply_Date;
        }

        public void setApply_Date(long apply_Date) {
            this.apply_Date = apply_Date;
        }

        public Object getCurrApprovalMan() {
            return currApprovalMan;
        }

        public void setCurrApprovalMan(Object currApprovalMan) {
            this.currApprovalMan = currApprovalMan;
        }
    }

    public static class LastApprovalStepBean {
        private int step_Id;
        private int apply_Id;
        private int is_Pass;
        private String is_Pass_Str;
        private int approval_Man;
        private String approval_Man_Str;
        private long approval_Time;
        private int next_Approval_Man;
        private Object memo;
        private int is_Over;

        public int getStep_Id() {
            return step_Id;
        }

        public void setStep_Id(int step_Id) {
            this.step_Id = step_Id;
        }

        public int getApply_Id() {
            return apply_Id;
        }

        public void setApply_Id(int apply_Id) {
            this.apply_Id = apply_Id;
        }

        public int getIs_Pass() {
            return is_Pass;
        }

        public void setIs_Pass(int is_Pass) {
            this.is_Pass = is_Pass;
        }

        public String getIs_Pass_Str() {
            return is_Pass_Str;
        }

        public void setIs_Pass_Str(String is_Pass_Str) {
            this.is_Pass_Str = is_Pass_Str;
        }

        public int getApproval_Man() {
            return approval_Man;
        }

        public void setApproval_Man(int approval_Man) {
            this.approval_Man = approval_Man;
        }

        public String getApproval_Man_Str() {
            return approval_Man_Str;
        }

        public void setApproval_Man_Str(String approval_Man_Str) {
            this.approval_Man_Str = approval_Man_Str;
        }

        public long getApproval_Time() {
            return approval_Time;
        }

        public void setApproval_Time(long approval_Time) {
            this.approval_Time = approval_Time;
        }

        public int getNext_Approval_Man() {
            return next_Approval_Man;
        }

        public void setNext_Approval_Man(int next_Approval_Man) {
            this.next_Approval_Man = next_Approval_Man;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public int getIs_Over() {
            return is_Over;
        }

        public void setIs_Over(int is_Over) {
            this.is_Over = is_Over;
        }
    }

    public static class AvailableApprovalManListBean {
        private int emp_Id;
        private int cust_Id;
        private String emp_Name;
        private Object cust_Inter_No;
        private int certificate_Type;
        private String certificate_No;
        private String email;
        private String phone;
        private String login_Name;
        private String login_Password;
        private Object nationality;
        private Object address;
        private Object zipcode;
        private int emp_Status;
        private int tax_Base;
        private Object photo_Url;
        private Object memo;
        private long modify_Time;
        private String modifier;
        private String mobile;
        private String weixinid;
        private int group_Id;
        private int weixin_Status;
        private long synch_time;

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public Object getCust_Inter_No() {
            return cust_Inter_No;
        }

        public void setCust_Inter_No(Object cust_Inter_No) {
            this.cust_Inter_No = cust_Inter_No;
        }

        public int getCertificate_Type() {
            return certificate_Type;
        }

        public void setCertificate_Type(int certificate_Type) {
            this.certificate_Type = certificate_Type;
        }

        public String getCertificate_No() {
            return certificate_No;
        }

        public void setCertificate_No(String certificate_No) {
            this.certificate_No = certificate_No;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLogin_Name() {
            return login_Name;
        }

        public void setLogin_Name(String login_Name) {
            this.login_Name = login_Name;
        }

        public String getLogin_Password() {
            return login_Password;
        }

        public void setLogin_Password(String login_Password) {
            this.login_Password = login_Password;
        }

        public Object getNationality() {
            return nationality;
        }

        public void setNationality(Object nationality) {
            this.nationality = nationality;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getZipcode() {
            return zipcode;
        }

        public void setZipcode(Object zipcode) {
            this.zipcode = zipcode;
        }

        public int getEmp_Status() {
            return emp_Status;
        }

        public void setEmp_Status(int emp_Status) {
            this.emp_Status = emp_Status;
        }

        public int getTax_Base() {
            return tax_Base;
        }

        public void setTax_Base(int tax_Base) {
            this.tax_Base = tax_Base;
        }

        public Object getPhoto_Url() {
            return photo_Url;
        }

        public void setPhoto_Url(Object photo_Url) {
            this.photo_Url = photo_Url;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public long getModify_Time() {
            return modify_Time;
        }

        public void setModify_Time(long modify_Time) {
            this.modify_Time = modify_Time;
        }

        public String getModifier() {
            return modifier;
        }

        public void setModifier(String modifier) {
            this.modifier = modifier;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWeixinid() {
            return weixinid;
        }

        public void setWeixinid(String weixinid) {
            this.weixinid = weixinid;
        }

        public int getGroup_Id() {
            return group_Id;
        }

        public void setGroup_Id(int group_Id) {
            this.group_Id = group_Id;
        }

        public int getWeixin_Status() {
            return weixin_Status;
        }

        public void setWeixin_Status(int weixin_Status) {
            this.weixin_Status = weixin_Status;
        }

        public long getSynch_time() {
            return synch_time;
        }

        public void setSynch_time(long synch_time) {
            this.synch_time = synch_time;
        }
    }
}
