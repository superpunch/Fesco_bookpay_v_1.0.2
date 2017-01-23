package com.fesco.bookpay.entity.approvalbean;

import java.util.List;

/**
 * Created by gong.min on 2016/10/14.
 */
public class RestDetailBean {


    /**
     * hol_Emp_Exam_Id : 942
     * cust_Id : 29
     * emp_Id : 4301
     * hol_Set_Id : 66
     * hol_Name : null
     * hol_Num : 2
     * hol_Begin : 1474905600000
     * hol_End : 1474992000000
     * momo : ceshi
     * exam_End_Is : 2
     * exam_End_Is_Name : null
     * pay_Money : 0
     * appl_Date : 1474945351000
     * emp_Name : 崔欣（研发测试）
     * exam_Step_Is_Over : 0
     * hol_Num_Str : null
     * currApprovalMan : null
     * hol_Unit : 1
     * hol_Source : null
     * group_Name : null
     * hol_Begin_Str : null
     * hol_End_Str : null
     * hol_Begin_Apm : null
     * hol_End_Apm : null
     */

    private HolEmpExamBean holEmpExam;
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
    /**
     * hol_Set_Id : 63
     * cust_Id : 29
     * hol_Type : 0
     * hol_Name : 年假
     * hol_Min_Value : 1
     * hol_Unit : 1,3
     * hol_Inhol : 0
     * hol_Max_Value : 15
     * order_Num : 1
     * hol_Count_Formula : other_Item2/21.75
     * need_Exam : 1
     * type : 1
     * hol_Units : null
     * expired_Month : 3
     * daily_Hour_Num : 7
     * am_Hour_Num : 3
     * pm_Hour_Num : 4
     */



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

    public LastApprovalStepBean getLastApprovalStep() {
        return lastApprovalStep;
    }

    public void setLastApprovalStep(LastApprovalStepBean lastApprovalStep) {
        this.lastApprovalStep = lastApprovalStep;
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
















    private List<ListBean> list;

    public HolEmpExamBean getHolEmpExam() {
        return holEmpExam;
    }

    public void setHolEmpExam(HolEmpExamBean holEmpExam) {
        this.holEmpExam = holEmpExam;
    }

    public List<AvailableApprovalManListBean> getAvailableApprovalManList() {
        return availableApprovalManList;
    }

    public void setAvailableApprovalManList(List<AvailableApprovalManListBean> availableApprovalManList) {
        this.availableApprovalManList = availableApprovalManList;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class HolEmpExamBean {
        private int hol_Emp_Exam_Id;
        private int cust_Id;
        private int emp_Id;
        private int hol_Set_Id;
        private Object hol_Name;
        private String hol_Num;
        private long hol_Begin;
        private long hol_End;
        private String momo;
        private int exam_End_Is;
        private Object exam_End_Is_Name;
        private String pay_Money;
        private long appl_Date;
        private String emp_Name;
        private int exam_Step_Is_Over;
        private Object hol_Num_Str;
        private Object currApprovalMan;
        private int hol_Unit;
        private Object hol_Source;
        private Object group_Name;
        private Object hol_Begin_Str;
        private Object hol_End_Str;
        private Object hol_Begin_Apm;
        private Object hol_End_Apm;

        public int getHol_Emp_Exam_Id() {
            return hol_Emp_Exam_Id;
        }

        public void setHol_Emp_Exam_Id(int hol_Emp_Exam_Id) {
            this.hol_Emp_Exam_Id = hol_Emp_Exam_Id;
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

        public int getHol_Set_Id() {
            return hol_Set_Id;
        }

        public void setHol_Set_Id(int hol_Set_Id) {
            this.hol_Set_Id = hol_Set_Id;
        }

        public Object getHol_Name() {
            return hol_Name;
        }

        public void setHol_Name(Object hol_Name) {
            this.hol_Name = hol_Name;
        }

        public String getHol_Num() {
            return hol_Num;
        }

        public void setHol_Num(String hol_Num) {
            this.hol_Num = hol_Num;
        }

        public long getHol_Begin() {
            return hol_Begin;
        }

        public void setHol_Begin(long hol_Begin) {
            this.hol_Begin = hol_Begin;
        }

        public long getHol_End() {
            return hol_End;
        }

        public void setHol_End(long hol_End) {
            this.hol_End = hol_End;
        }

        public String getMomo() {
            return momo;
        }

        public void setMomo(String momo) {
            this.momo = momo;
        }

        public int getExam_End_Is() {
            return exam_End_Is;
        }

        public void setExam_End_Is(int exam_End_Is) {
            this.exam_End_Is = exam_End_Is;
        }

        public Object getExam_End_Is_Name() {
            return exam_End_Is_Name;
        }

        public void setExam_End_Is_Name(Object exam_End_Is_Name) {
            this.exam_End_Is_Name = exam_End_Is_Name;
        }

        public String getPay_Money() {
            return pay_Money;
        }

        public void setPay_Money(String pay_Money) {
            this.pay_Money = pay_Money;
        }

        public long getAppl_Date() {
            return appl_Date;
        }

        public void setAppl_Date(long appl_Date) {
            this.appl_Date = appl_Date;
        }

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public int getExam_Step_Is_Over() {
            return exam_Step_Is_Over;
        }

        public void setExam_Step_Is_Over(int exam_Step_Is_Over) {
            this.exam_Step_Is_Over = exam_Step_Is_Over;
        }

        public Object getHol_Num_Str() {
            return hol_Num_Str;
        }

        public void setHol_Num_Str(Object hol_Num_Str) {
            this.hol_Num_Str = hol_Num_Str;
        }

        public Object getCurrApprovalMan() {
            return currApprovalMan;
        }

        public void setCurrApprovalMan(Object currApprovalMan) {
            this.currApprovalMan = currApprovalMan;
        }

        public int getHol_Unit() {
            return hol_Unit;
        }

        public void setHol_Unit(int hol_Unit) {
            this.hol_Unit = hol_Unit;
        }

        public Object getHol_Source() {
            return hol_Source;
        }

        public void setHol_Source(Object hol_Source) {
            this.hol_Source = hol_Source;
        }

        public Object getGroup_Name() {
            return group_Name;
        }

        public void setGroup_Name(Object group_Name) {
            this.group_Name = group_Name;
        }

        public Object getHol_Begin_Str() {
            return hol_Begin_Str;
        }

        public void setHol_Begin_Str(Object hol_Begin_Str) {
            this.hol_Begin_Str = hol_Begin_Str;
        }

        public Object getHol_End_Str() {
            return hol_End_Str;
        }

        public void setHol_End_Str(Object hol_End_Str) {
            this.hol_End_Str = hol_End_Str;
        }

        public Object getHol_Begin_Apm() {
            return hol_Begin_Apm;
        }

        public void setHol_Begin_Apm(Object hol_Begin_Apm) {
            this.hol_Begin_Apm = hol_Begin_Apm;
        }

        public Object getHol_End_Apm() {
            return hol_End_Apm;
        }

        public void setHol_End_Apm(Object hol_End_Apm) {
            this.hol_End_Apm = hol_End_Apm;
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
        private Object position;
        private String mobile;
        private int gender;
        private String weixinid;
        private int group_Id;
        private int weixin_Status;
        private long synch_time;
        private Object housing_Fund_Rate;
        private int salary;
        private int isspecial;
        private Object group_Name;
        private int exam_Authority;
        private Object yearly_Hol_Num;
        private Object methodname;
        private int extra_Work_Authority;

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

        public Object getPosition() {
            return position;
        }

        public void setPosition(Object position) {
            this.position = position;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
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

        public Object getHousing_Fund_Rate() {
            return housing_Fund_Rate;
        }

        public void setHousing_Fund_Rate(Object housing_Fund_Rate) {
            this.housing_Fund_Rate = housing_Fund_Rate;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public int getIsspecial() {
            return isspecial;
        }

        public void setIsspecial(int isspecial) {
            this.isspecial = isspecial;
        }

        public Object getGroup_Name() {
            return group_Name;
        }

        public void setGroup_Name(Object group_Name) {
            this.group_Name = group_Name;
        }

        public int getExam_Authority() {
            return exam_Authority;
        }

        public void setExam_Authority(int exam_Authority) {
            this.exam_Authority = exam_Authority;
        }

        public Object getYearly_Hol_Num() {
            return yearly_Hol_Num;
        }

        public void setYearly_Hol_Num(Object yearly_Hol_Num) {
            this.yearly_Hol_Num = yearly_Hol_Num;
        }

        public Object getMethodname() {
            return methodname;
        }

        public void setMethodname(Object methodname) {
            this.methodname = methodname;
        }

        public int getExtra_Work_Authority() {
            return extra_Work_Authority;
        }

        public void setExtra_Work_Authority(int extra_Work_Authority) {
            this.extra_Work_Authority = extra_Work_Authority;
        }
    }

    public static class ListBean {
        private int hol_Set_Id;
        private int cust_Id;
        private int hol_Type;
        private String hol_Name;
        private String hol_Min_Value;
        private String hol_Unit;
        private int hol_Inhol;
        private int hol_Max_Value;
        private int order_Num;
        private String hol_Count_Formula;
        private int need_Exam;
        private int type;
        private Object hol_Units;
        private int expired_Month;
        private int daily_Hour_Num;
        private int am_Hour_Num;
        private int pm_Hour_Num;

        public int getHol_Set_Id() {
            return hol_Set_Id;
        }

        public void setHol_Set_Id(int hol_Set_Id) {
            this.hol_Set_Id = hol_Set_Id;
        }

        public int getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(int cust_Id) {
            this.cust_Id = cust_Id;
        }

        public int getHol_Type() {
            return hol_Type;
        }

        public void setHol_Type(int hol_Type) {
            this.hol_Type = hol_Type;
        }

        public String getHol_Name() {
            return hol_Name;
        }

        public void setHol_Name(String hol_Name) {
            this.hol_Name = hol_Name;
        }

        public String getHol_Min_Value() {
            return hol_Min_Value;
        }

        public void setHol_Min_Value(String hol_Min_Value) {
            this.hol_Min_Value = hol_Min_Value;
        }

        public String getHol_Unit() {
            return hol_Unit;
        }

        public void setHol_Unit(String hol_Unit) {
            this.hol_Unit = hol_Unit;
        }

        public int getHol_Inhol() {
            return hol_Inhol;
        }

        public void setHol_Inhol(int hol_Inhol) {
            this.hol_Inhol = hol_Inhol;
        }

        public int getHol_Max_Value() {
            return hol_Max_Value;
        }

        public void setHol_Max_Value(int hol_Max_Value) {
            this.hol_Max_Value = hol_Max_Value;
        }

        public int getOrder_Num() {
            return order_Num;
        }

        public void setOrder_Num(int order_Num) {
            this.order_Num = order_Num;
        }

        public String getHol_Count_Formula() {
            return hol_Count_Formula;
        }

        public void setHol_Count_Formula(String hol_Count_Formula) {
            this.hol_Count_Formula = hol_Count_Formula;
        }

        public int getNeed_Exam() {
            return need_Exam;
        }

        public void setNeed_Exam(int need_Exam) {
            this.need_Exam = need_Exam;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getHol_Units() {
            return hol_Units;
        }

        public void setHol_Units(Object hol_Units) {
            this.hol_Units = hol_Units;
        }

        public int getExpired_Month() {
            return expired_Month;
        }

        public void setExpired_Month(int expired_Month) {
            this.expired_Month = expired_Month;
        }

        public int getDaily_Hour_Num() {
            return daily_Hour_Num;
        }

        public void setDaily_Hour_Num(int daily_Hour_Num) {
            this.daily_Hour_Num = daily_Hour_Num;
        }

        public int getAm_Hour_Num() {
            return am_Hour_Num;
        }

        public void setAm_Hour_Num(int am_Hour_Num) {
            this.am_Hour_Num = am_Hour_Num;
        }

        public int getPm_Hour_Num() {
            return pm_Hour_Num;
        }

        public void setPm_Hour_Num(int pm_Hour_Num) {
            this.pm_Hour_Num = pm_Hour_Num;
        }
    }
}
