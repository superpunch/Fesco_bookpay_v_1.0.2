package com.fesco.bookpay.entity;

import java.util.List;

/**
 * Created by gong.min on 2016/9/7.
 */
public class ContactsBean {


    /**group_Name:按部门分组
     *
     * message : success
     * emps : [{"other_Item5":null,"other_Item4":null,"phone":"15330086200","other_Item3":null,"other_Item2":null,"other_Item9":null,"other_Item8":null,"other_Item7":null,"other_Item6":null,"injury_Rate":null,"other_Item19":null,"medical_Rate":null,"other_Item17":null,"other_Item18":null,"unemployment_Rate":null,"group_Name":"管理咨询部","other_Item11":null,"other_Item12":null,"gender":null,"other_Item10":null,"salary":null,"other_Item15":null,"other_Item1":null,"other_Item16":null,"other_Item13":null,"other_Item14":null,"weixinid":null,"housing_Fund_Rate":null,"emp_Name":"杜娇娇","modifier":null,"yearly_Hol_Num":null,"certificate_No":null,"email":null,"modify_Time":null,"photo_Url":null,"weixin_Status":null,"emp_Id":1668,"tax_Base":null,"mobile":"15330086200","position":null,"group_Id":null,"exam_Authority":null,"login_Name":null,"memo":null,"cust_Id":null,"cust_Inter_No":null,"isspecial":null,"emp_Status":null,"login_Password":null,"synch_time":null,"zipcode":null,"endowment_Rate":null,"nationality":null,"methodname":null,"address":null,"other_Item20":null,"certificate_Type":null,"maternity_Rate":null}]
     */

    private String message;
    /**
     * other_Item5 : null
     * other_Item4 : null
     * phone : 15330086200
     * other_Item3 : null
     * other_Item2 : null
     * other_Item9 : null
     * other_Item8 : null
     * other_Item7 : null
     * other_Item6 : null
     * injury_Rate : null
     * other_Item19 : null
     * medical_Rate : null
     * other_Item17 : null
     * other_Item18 : null
     * unemployment_Rate : null
     * group_Name : 管理咨询部
     * other_Item11 : null
     * other_Item12 : null
     * gender : null
     * other_Item10 : null
     * salary : null
     * other_Item15 : null
     * other_Item1 : null
     * other_Item16 : null
     * other_Item13 : null
     * other_Item14 : null
     * weixinid : null
     * housing_Fund_Rate : null
     * emp_Name : 杜娇娇
     * modifier : null
     * yearly_Hol_Num : null
     * certificate_No : null
     * email : null
     * modify_Time : null
     * photo_Url : null
     * weixin_Status : null
     * emp_Id : 1668
     * tax_Base : null
     * mobile : 15330086200
     * position : null
     * group_Id : null
     * exam_Authority : null
     * login_Name : null
     * memo : null
     * cust_Id : null
     * cust_Inter_No : null
     * isspecial : null
     * emp_Status : null
     * login_Password : null
     * synch_time : null
     * zipcode : null
     * endowment_Rate : null
     * nationality : null
     * methodname : null
     * address : null
     * other_Item20 : null
     * certificate_Type : null
     * maternity_Rate : null
     */

    private List<EmpsBean> emps;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<EmpsBean> getEmps() {
        return emps;
    }

    public void setEmps(List<EmpsBean> emps) {
        this.emps = emps;
    }

    public static class EmpsBean {
        @Override
        public String toString() {
            return "EmpsBean{" +
                    "other_Item5=" + other_Item5 +
                    ", other_Item4=" + other_Item4 +
                    ", phone='" + phone + '\'' +
                    ", other_Item3=" + other_Item3 +
                    ", other_Item2=" + other_Item2 +
                    ", other_Item9=" + other_Item9 +
                    ", other_Item8=" + other_Item8 +
                    ", other_Item7=" + other_Item7 +
                    ", other_Item6=" + other_Item6 +
                    ", injury_Rate=" + injury_Rate +
                    ", other_Item19=" + other_Item19 +
                    ", medical_Rate=" + medical_Rate +
                    ", other_Item17=" + other_Item17 +
                    ", other_Item18=" + other_Item18 +
                    ", unemployment_Rate=" + unemployment_Rate +
                    ", group_Name='" + group_Name + '\'' +
                    ", other_Item11=" + other_Item11 +
                    ", other_Item12=" + other_Item12 +
                    ", gender=" + gender +
                    ", other_Item10=" + other_Item10 +
                    ", salary=" + salary +
                    ", other_Item15=" + other_Item15 +
                    ", other_Item1=" + other_Item1 +
                    ", other_Item16=" + other_Item16 +
                    ", other_Item13=" + other_Item13 +
                    ", other_Item14=" + other_Item14 +
                    ", weixinid=" + weixinid +
                    ", housing_Fund_Rate=" + housing_Fund_Rate +
                    ", emp_Name='" + emp_Name + '\'' +
                    ", modifier=" + modifier +
                    ", yearly_Hol_Num=" + yearly_Hol_Num +
                    ", certificate_No=" + certificate_No +
                    ", email=" + email +
                    ", modify_Time=" + modify_Time +
                    ", photo_Url=" + photo_Url +
                    ", weixin_Status=" + weixin_Status +
                    ", emp_Id=" + emp_Id +
                    ", tax_Base=" + tax_Base +
                    ", mobile='" + mobile + '\'' +
                    ", position=" + position +
                    ", group_Id=" + group_Id +
                    ", exam_Authority=" + exam_Authority +
                    ", login_Name=" + login_Name +
                    ", memo=" + memo +
                    ", cust_Id=" + cust_Id +
                    ", cust_Inter_No=" + cust_Inter_No +
                    ", isspecial=" + isspecial +
                    ", emp_Status=" + emp_Status +
                    ", login_Password=" + login_Password +
                    ", synch_time=" + synch_time +
                    ", zipcode=" + zipcode +
                    ", endowment_Rate=" + endowment_Rate +
                    ", nationality=" + nationality +
                    ", methodname=" + methodname +
                    ", address=" + address +
                    ", other_Item20=" + other_Item20 +
                    ", certificate_Type=" + certificate_Type +
                    ", maternity_Rate=" + maternity_Rate +
                    '}';
        }

        private Object other_Item5;
        private Object other_Item4;
        private String phone;
        private Object other_Item3;
        private Object other_Item2;
        private Object other_Item9;
        private Object other_Item8;
        private Object other_Item7;
        private Object other_Item6;
        private Object injury_Rate;
        private Object other_Item19;
        private Object medical_Rate;
        private Object other_Item17;
        private Object other_Item18;
        private Object unemployment_Rate;
        private String group_Name;
        private Object other_Item11;
        private Object other_Item12;
        private Object gender;
        private Object other_Item10;
        private Object salary;
        private Object other_Item15;
        private Object other_Item1;
        private Object other_Item16;
        private Object other_Item13;
        private Object other_Item14;
        private Object weixinid;
        private Object housing_Fund_Rate;
        private String emp_Name;
        private Object modifier;
        private Object yearly_Hol_Num;
        private Object certificate_No;
        private Object email;
        private Object modify_Time;
        private Object photo_Url;
        private Object weixin_Status;
        private int emp_Id;
        private Object tax_Base;
        private String mobile;
        private Object position;
        private Object group_Id;
        private Object exam_Authority;
        private Object login_Name;
        private Object memo;
        private Object cust_Id;
        private Object cust_Inter_No;
        private Object isspecial;
        private Object emp_Status;
        private Object login_Password;
        private Object synch_time;
        private Object zipcode;
        private Object endowment_Rate;
        private Object nationality;
        private Object methodname;
        private Object address;
        private Object other_Item20;
        private Object certificate_Type;
        private Object maternity_Rate;

        public Object getOther_Item5() {
            return other_Item5;
        }

        public void setOther_Item5(Object other_Item5) {
            this.other_Item5 = other_Item5;
        }

        public Object getOther_Item4() {
            return other_Item4;
        }

        public void setOther_Item4(Object other_Item4) {
            this.other_Item4 = other_Item4;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getOther_Item3() {
            return other_Item3;
        }

        public void setOther_Item3(Object other_Item3) {
            this.other_Item3 = other_Item3;
        }

        public Object getOther_Item2() {
            return other_Item2;
        }

        public void setOther_Item2(Object other_Item2) {
            this.other_Item2 = other_Item2;
        }

        public Object getOther_Item9() {
            return other_Item9;
        }

        public void setOther_Item9(Object other_Item9) {
            this.other_Item9 = other_Item9;
        }

        public Object getOther_Item8() {
            return other_Item8;
        }

        public void setOther_Item8(Object other_Item8) {
            this.other_Item8 = other_Item8;
        }

        public Object getOther_Item7() {
            return other_Item7;
        }

        public void setOther_Item7(Object other_Item7) {
            this.other_Item7 = other_Item7;
        }

        public Object getOther_Item6() {
            return other_Item6;
        }

        public void setOther_Item6(Object other_Item6) {
            this.other_Item6 = other_Item6;
        }

        public Object getInjury_Rate() {
            return injury_Rate;
        }

        public void setInjury_Rate(Object injury_Rate) {
            this.injury_Rate = injury_Rate;
        }

        public Object getOther_Item19() {
            return other_Item19;
        }

        public void setOther_Item19(Object other_Item19) {
            this.other_Item19 = other_Item19;
        }

        public Object getMedical_Rate() {
            return medical_Rate;
        }

        public void setMedical_Rate(Object medical_Rate) {
            this.medical_Rate = medical_Rate;
        }

        public Object getOther_Item17() {
            return other_Item17;
        }

        public void setOther_Item17(Object other_Item17) {
            this.other_Item17 = other_Item17;
        }

        public Object getOther_Item18() {
            return other_Item18;
        }

        public void setOther_Item18(Object other_Item18) {
            this.other_Item18 = other_Item18;
        }

        public Object getUnemployment_Rate() {
            return unemployment_Rate;
        }

        public void setUnemployment_Rate(Object unemployment_Rate) {
            this.unemployment_Rate = unemployment_Rate;
        }

        public String getGroup_Name() {
            return group_Name;
        }

        public void setGroup_Name(String group_Name) {
            this.group_Name = group_Name;
        }

        public Object getOther_Item11() {
            return other_Item11;
        }

        public void setOther_Item11(Object other_Item11) {
            this.other_Item11 = other_Item11;
        }

        public Object getOther_Item12() {
            return other_Item12;
        }

        public void setOther_Item12(Object other_Item12) {
            this.other_Item12 = other_Item12;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getOther_Item10() {
            return other_Item10;
        }

        public void setOther_Item10(Object other_Item10) {
            this.other_Item10 = other_Item10;
        }

        public Object getSalary() {
            return salary;
        }

        public void setSalary(Object salary) {
            this.salary = salary;
        }

        public Object getOther_Item15() {
            return other_Item15;
        }

        public void setOther_Item15(Object other_Item15) {
            this.other_Item15 = other_Item15;
        }

        public Object getOther_Item1() {
            return other_Item1;
        }

        public void setOther_Item1(Object other_Item1) {
            this.other_Item1 = other_Item1;
        }

        public Object getOther_Item16() {
            return other_Item16;
        }

        public void setOther_Item16(Object other_Item16) {
            this.other_Item16 = other_Item16;
        }

        public Object getOther_Item13() {
            return other_Item13;
        }

        public void setOther_Item13(Object other_Item13) {
            this.other_Item13 = other_Item13;
        }

        public Object getOther_Item14() {
            return other_Item14;
        }

        public void setOther_Item14(Object other_Item14) {
            this.other_Item14 = other_Item14;
        }

        public Object getWeixinid() {
            return weixinid;
        }

        public void setWeixinid(Object weixinid) {
            this.weixinid = weixinid;
        }

        public Object getHousing_Fund_Rate() {
            return housing_Fund_Rate;
        }

        public void setHousing_Fund_Rate(Object housing_Fund_Rate) {
            this.housing_Fund_Rate = housing_Fund_Rate;
        }

        public String getEmp_Name() {
            return emp_Name;
        }

        public void setEmp_Name(String emp_Name) {
            this.emp_Name = emp_Name;
        }

        public Object getModifier() {
            return modifier;
        }

        public void setModifier(Object modifier) {
            this.modifier = modifier;
        }

        public Object getYearly_Hol_Num() {
            return yearly_Hol_Num;
        }

        public void setYearly_Hol_Num(Object yearly_Hol_Num) {
            this.yearly_Hol_Num = yearly_Hol_Num;
        }

        public Object getCertificate_No() {
            return certificate_No;
        }

        public void setCertificate_No(Object certificate_No) {
            this.certificate_No = certificate_No;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getModify_Time() {
            return modify_Time;
        }

        public void setModify_Time(Object modify_Time) {
            this.modify_Time = modify_Time;
        }

        public Object getPhoto_Url() {
            return photo_Url;
        }

        public void setPhoto_Url(Object photo_Url) {
            this.photo_Url = photo_Url;
        }

        public Object getWeixin_Status() {
            return weixin_Status;
        }

        public void setWeixin_Status(Object weixin_Status) {
            this.weixin_Status = weixin_Status;
        }

        public int getEmp_Id() {
            return emp_Id;
        }

        public void setEmp_Id(int emp_Id) {
            this.emp_Id = emp_Id;
        }

        public Object getTax_Base() {
            return tax_Base;
        }

        public void setTax_Base(Object tax_Base) {
            this.tax_Base = tax_Base;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getPosition() {
            return position;
        }

        public void setPosition(Object position) {
            this.position = position;
        }

        public Object getGroup_Id() {
            return group_Id;
        }

        public void setGroup_Id(Object group_Id) {
            this.group_Id = group_Id;
        }

        public Object getExam_Authority() {
            return exam_Authority;
        }

        public void setExam_Authority(Object exam_Authority) {
            this.exam_Authority = exam_Authority;
        }

        public Object getLogin_Name() {
            return login_Name;
        }

        public void setLogin_Name(Object login_Name) {
            this.login_Name = login_Name;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public Object getCust_Id() {
            return cust_Id;
        }

        public void setCust_Id(Object cust_Id) {
            this.cust_Id = cust_Id;
        }

        public Object getCust_Inter_No() {
            return cust_Inter_No;
        }

        public void setCust_Inter_No(Object cust_Inter_No) {
            this.cust_Inter_No = cust_Inter_No;
        }

        public Object getIsspecial() {
            return isspecial;
        }

        public void setIsspecial(Object isspecial) {
            this.isspecial = isspecial;
        }

        public Object getEmp_Status() {
            return emp_Status;
        }

        public void setEmp_Status(Object emp_Status) {
            this.emp_Status = emp_Status;
        }

        public Object getLogin_Password() {
            return login_Password;
        }

        public void setLogin_Password(Object login_Password) {
            this.login_Password = login_Password;
        }

        public Object getSynch_time() {
            return synch_time;
        }

        public void setSynch_time(Object synch_time) {
            this.synch_time = synch_time;
        }

        public Object getZipcode() {
            return zipcode;
        }

        public void setZipcode(Object zipcode) {
            this.zipcode = zipcode;
        }

        public Object getEndowment_Rate() {
            return endowment_Rate;
        }

        public void setEndowment_Rate(Object endowment_Rate) {
            this.endowment_Rate = endowment_Rate;
        }

        public Object getNationality() {
            return nationality;
        }

        public void setNationality(Object nationality) {
            this.nationality = nationality;
        }

        public Object getMethodname() {
            return methodname;
        }

        public void setMethodname(Object methodname) {
            this.methodname = methodname;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getOther_Item20() {
            return other_Item20;
        }

        public void setOther_Item20(Object other_Item20) {
            this.other_Item20 = other_Item20;
        }

        public Object getCertificate_Type() {
            return certificate_Type;
        }

        public void setCertificate_Type(Object certificate_Type) {
            this.certificate_Type = certificate_Type;
        }

        public Object getMaternity_Rate() {
            return maternity_Rate;
        }

        public void setMaternity_Rate(Object maternity_Rate) {
            this.maternity_Rate = maternity_Rate;
        }
    }
}
