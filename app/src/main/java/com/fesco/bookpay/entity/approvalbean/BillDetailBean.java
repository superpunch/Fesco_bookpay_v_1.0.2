package com.fesco.bookpay.entity.approvalbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gong.min on 2017/1/10.
 */
public class BillDetailBean {


    /**
     * message : success
     * apply : {"apply_Id":182,"cust_Id":29,"memo":"cs","type":1,"type_Str":null,"title":"1112","group_Id":2,"group_Name":"管理咨询部","apply_Date":1483891200000,"apply_Date_Str":null,"edit_Time":1483959808000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":"胡松","account_Id":1401,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":266,"detail_Id_Before_Imported":null,"apply_Id":182,"spend_Type_Str":"通讯","spend_Type":7,"cust_Id":null,"money_Amount":333,"bill_Num":2,"spend_Begin":1484323200000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"3334444444","trId":null,"icon":"fa fa-phone fa-lg","emp_Id":null,"pics":[{"id":null,"detail_Id":266,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]}
     * lastApprovalStep : {"step_Id":102,"apply_Id":182,"is_Pass":2,"approval_Man":163,"approval_Time":null,"next_Approval_Man":null,"memo":null,"is_Over":0,"approval_Man_Str":"胡松","is_Pass_Str":"通过审批"}
     * approvalManList : [{"emp_Id":163,"cust_Id":29,"emp_Name":"胡松","cust_Inter_No":null,"certificate_Type":4,"certificate_No":"22","email":"hu.song@fesco.com.cn","phone":"18611279997","login_Name":"husong","login_Password":"f9uiGzKzuGu6G2GsGnoKtQ==","nationality":null,"address":null,"zipcode":null,"emp_Status":1,"tax_Base":3500,"photo_Url":"S:/photo/29/163/163_pic.jpg","memo":null,"modify_Time":1483459200000,"modifier":"胡松","other_Item1":null,"other_Item2":"8000","other_Item3":null,"other_Item4":null,"other_Item5":null,"other_Item6":null,"other_Item7":null,"other_Item8":null,"other_Item9":null,"other_Item10":null,"other_Item11":null,"other_Item12":null,"other_Item13":null,"other_Item14":null,"other_Item15":null,"other_Item16":null,"other_Item17":null,"other_Item18":null,"other_Item19":null,"other_Item20":null,"other_Item21":null,"other_Item22":null,"other_Item23":null,"other_Item24":null,"other_Item25":null,"other_Item26":null,"other_Item27":null,"other_Item28":null,"other_Item29":null,"other_Item30":null,"other_Item31":null,"other_Item32":null,"other_Item33":null,"other_Item34":null,"other_Item35":null,"other_Item36":null,"other_Item37":null,"other_Item38":null,"other_Item39":null,"other_Item40":null,"position":null,"mobile":"010-65874734","gender":1,"weixinid":"hughsong","group_Id":6,"weixin_Status":1,"synch_time":1459872000000,"endowment_Rate":null,"medical_Rate":null,"unemployment_Rate":null,"maternity_Rate":null,"injury_Rate":null,"housing_Fund_Rate":null,"salary":8000,"isspecial":0,"group_Name":null,"exam_Authority":1,"yearly_Hol_Num":null,"methodname":null,"entry_Time":null,"departure_Time":null,"extra_Work_Authority":0},{"emp_Id":164,"cust_Id":29,"emp_Name":"解朝辉","cust_Inter_No":null,"certificate_Type":null,"certificate_No":null,"email":"xie.zhao.hui@fesco.com.cn","phone":null,"login_Name":"xie.zhao.hui","login_Password":null,"nationality":null,"address":null,"zipcode":null,"emp_Status":1,"tax_Base":3500,"photo_Url":null,"memo":null,"modify_Time":1474905600000,"modifier":"胡松","other_Item1":null,"other_Item2":null,"other_Item3":null,"other_Item4":null,"other_Item5":null,"other_Item6":null,"other_Item7":null,"other_Item8":null,"other_Item9":null,"other_Item10":null,"other_Item11":null,"other_Item12":null,"other_Item13":null,"other_Item14":null,"other_Item15":null,"other_Item16":null,"other_Item17":null,"other_Item18":null,"other_Item19":null,"other_Item20":null,"other_Item21":null,"other_Item22":null,"other_Item23":null,"other_Item24":null,"other_Item25":null,"other_Item26":null,"other_Item27":null,"other_Item28":null,"other_Item29":null,"other_Item30":null,"other_Item31":null,"other_Item32":null,"other_Item33":null,"other_Item34":null,"other_Item35":null,"other_Item36":null,"other_Item37":null,"other_Item38":null,"other_Item39":null,"other_Item40":null,"position":null,"mobile":"18601358858","gender":1,"weixinid":null,"group_Id":9,"weixin_Status":4,"synch_time":1461600000000,"endowment_Rate":null,"medical_Rate":null,"unemployment_Rate":null,"maternity_Rate":null,"injury_Rate":null,"housing_Fund_Rate":null,"salary":null,"isspecial":0,"group_Name":null,"exam_Authority":1,"yearly_Hol_Num":null,"methodname":null,"entry_Time":null,"departure_Time":null,"extra_Work_Authority":0},{"emp_Id":261,"cust_Id":29,"emp_Name":"赵洪滨","cust_Inter_No":null,"certificate_Type":4,"certificate_No":"11","email":"zhao.hong.bin@fesco.com.cn","phone":null,"login_Name":"zhaohongbin","login_Password":"FVAKki8DD9UGLGs8Dsp8Ww==","nationality":null,"address":null,"zipcode":null,"emp_Status":1,"tax_Base":3500,"photo_Url":null,"memo":null,"modify_Time":1462723200000,"modifier":"胡松","other_Item1":null,"other_Item2":null,"other_Item3":null,"other_Item4":null,"other_Item5":null,"other_Item6":null,"other_Item7":null,"other_Item8":null,"other_Item9":null,"other_Item10":null,"other_Item11":null,"other_Item12":null,"other_Item13":null,"other_Item14":null,"other_Item15":null,"other_Item16":null,"other_Item17":null,"other_Item18":null,"other_Item19":null,"other_Item20":null,"other_Item21":null,"other_Item22":null,"other_Item23":null,"other_Item24":null,"other_Item25":null,"other_Item26":null,"other_Item27":null,"other_Item28":null,"other_Item29":null,"other_Item30":null,"other_Item31":null,"other_Item32":null,"other_Item33":null,"other_Item34":null,"other_Item35":null,"other_Item36":null,"other_Item37":null,"other_Item38":null,"other_Item39":null,"other_Item40":null,"position":null,"mobile":"18601358860","gender":1,"weixinid":"zhaohongbin","group_Id":3,"weixin_Status":1,"synch_time":1461600000000,"endowment_Rate":null,"medical_Rate":null,"unemployment_Rate":null,"maternity_Rate":null,"injury_Rate":null,"housing_Fund_Rate":null,"salary":null,"isspecial":null,"group_Name":null,"exam_Authority":1,"yearly_Hol_Num":null,"methodname":null,"entry_Time":null,"departure_Time":null,"extra_Work_Authority":0},{"emp_Id":1659,"cust_Id":29,"emp_Name":"孙晓川","cust_Inter_No":null,"certificate_Type":null,"certificate_No":null,"email":"sun.xiao.chuan@fesco.com.cn","phone":"85691738","login_Name":"frank","login_Password":"9MDlY59qWOuFm9syHOGTcg==","nationality":null,"address":null,"zipcode":null,"emp_Status":1,"tax_Base":3500,"photo_Url":null,"memo":null,"modify_Time":1478534400000,"modifier":"1659","other_Item1":null,"other_Item2":null,"other_Item3":null,"other_Item4":null,"other_Item5":null,"other_Item6":null,"other_Item7":null,"other_Item8":null,"other_Item9":null,"other_Item10":null,"other_Item11":null,"other_Item12":null,"other_Item13":null,"other_Item14":null,"other_Item15":null,"other_Item16":null,"other_Item17":null,"other_Item18":null,"other_Item19":null,"other_Item20":null,"other_Item21":null,"other_Item22":null,"other_Item23":null,"other_Item24":null,"other_Item25":null,"other_Item26":null,"other_Item27":null,"other_Item28":null,"other_Item29":null,"other_Item30":null,"other_Item31":null,"other_Item32":null,"other_Item33":null,"other_Item34":null,"other_Item35":null,"other_Item36":null,"other_Item37":null,"other_Item38":null,"other_Item39":null,"other_Item40":null,"position":null,"mobile":"13811578033","gender":1,"weixinid":"448085195","group_Id":7,"weixin_Status":null,"synch_time":null,"endowment_Rate":null,"medical_Rate":null,"unemployment_Rate":null,"maternity_Rate":null,"injury_Rate":null,"housing_Fund_Rate":null,"salary":null,"isspecial":null,"group_Name":null,"exam_Authority":1,"yearly_Hol_Num":null,"methodname":null,"entry_Time":null,"departure_Time":null,"extra_Work_Authority":0},{"emp_Id":1674,"cust_Id":29,"emp_Name":"朱迎","cust_Inter_No":null,"certificate_Type":null,"certificate_No":null,"email":"zhu.ying@fesco.com.cn","phone":"18601358835","login_Name":"zhu.ying","login_Password":"3Sv7o5jLToFeAbaNzvx7EA==","nationality":null,"address":null,"zipcode":null,"emp_Status":1,"tax_Base":3500,"photo_Url":null,"memo":null,"modify_Time":1473696000000,"modifier":"胡松","other_Item1":null,"other_Item2":null,"other_Item3":null,"other_Item4":null,"other_Item5":null,"other_Item6":null,"other_Item7":null,"other_Item8":null,"other_Item9":null,"other_Item10":null,"other_Item11":null,"other_Item12":null,"other_Item13":null,"other_Item14":null,"other_Item15":null,"other_Item16":null,"other_Item17":null,"other_Item18":null,"other_Item19":null,"other_Item20":null,"other_Item21":null,"other_Item22":null,"other_Item23":null,"other_Item24":null,"other_Item25":null,"other_Item26":null,"other_Item27":null,"other_Item28":null,"other_Item29":null,"other_Item30":null,"other_Item31":null,"other_Item32":null,"other_Item33":null,"other_Item34":null,"other_Item35":null,"other_Item36":null,"other_Item37":null,"other_Item38":null,"other_Item39":null,"other_Item40":null,"position":null,"mobile":"18601358835","gender":null,"weixinid":null,"group_Id":4,"weixin_Status":null,"synch_time":null,"endowment_Rate":null,"medical_Rate":null,"unemployment_Rate":null,"maternity_Rate":null,"injury_Rate":null,"housing_Fund_Rate":null,"salary":null,"isspecial":null,"group_Name":null,"exam_Authority":1,"yearly_Hol_Num":null,"methodname":null,"entry_Time":null,"departure_Time":null,"extra_Work_Authority":0},{"emp_Id":1660,"cust_Id":29,"emp_Name":"黄卫兰","cust_Inter_No":null,"certificate_Type":null,"certificate_No":null,"email":"huang.wei.lan@fesco.com.cn","phone":"010-85664762","login_Name":null,"login_Password":null,"nationality":null,"address":null,"zipcode":null,"emp_Status":1,"tax_Base":3500,"photo_Url":null,"memo":null,"modify_Time":1481644800000,"modifier":"胡松","other_Item1":null,"other_Item2":null,"other_Item3":null,"other_Item4":null,"other_Item5":null,"other_Item6":null,"other_Item7":null,"other_Item8":null,"other_Item9":null,"other_Item10":null,"other_Item11":null,"other_Item12":null,"other_Item13":null,"other_Item14":null,"other_Item15":null,"other_Item16":null,"other_Item17":null,"other_Item18":null,"other_Item19":null,"other_Item20":null,"other_Item21":null,"other_Item22":null,"other_Item23":null,"other_Item24":null,"other_Item25":null,"other_Item26":null,"other_Item27":null,"other_Item28":null,"other_Item29":null,"other_Item30":null,"other_Item31":null,"other_Item32":null,"other_Item33":null,"other_Item34":null,"other_Item35":null,"other_Item36":null,"other_Item37":null,"other_Item38":null,"other_Item39":null,"other_Item40":null,"position":null,"mobile":"18601358839","gender":null,"weixinid":null,"group_Id":2,"weixin_Status":null,"synch_time":null,"endowment_Rate":null,"medical_Rate":null,"unemployment_Rate":null,"maternity_Rate":null,"injury_Rate":null,"housing_Fund_Rate":null,"salary":null,"isspecial":null,"group_Name":null,"exam_Authority":1,"yearly_Hol_Num":null,"methodname":null,"entry_Time":1480521600000,"departure_Time":null,"extra_Work_Authority":0},{"emp_Id":1671,"cust_Id":29,"emp_Name":"赵静(HR)","cust_Inter_No":null,"certificate_Type":null,"certificate_No":null,"email":"zhao.jing3@fesco.com.cn","phone":"18600852789","login_Name":"zhao.jing3","login_Password":"ujNdo/DUDoDVDwZPTRUqDg==","nationality":null,"address":null,"zipcode":null,"emp_Status":1,"tax_Base":3500,"photo_Url":null,"memo":null,"modify_Time":1480953600000,"modifier":"赵静(HR)","other_Item1":null,"other_Item2":null,"other_Item3":null,"other_Item4":null,"other_Item5":null,"other_Item6":null,"other_Item7":null,"other_Item8":null,"other_Item9":null,"other_Item10":null,"other_Item11":null,"other_Item12":null,"other_Item13":null,"other_Item14":null,"other_Item15":null,"other_Item16":null,"other_Item17":null,"other_Item18":null,"other_Item19":null,"other_Item20":null,"other_Item21":null,"other_Item22":null,"other_Item23":null,"other_Item24":null,"other_Item25":null,"other_Item26":null,"other_Item27":null,"other_Item28":null,"other_Item29":null,"other_Item30":null,"other_Item31":null,"other_Item32":null,"other_Item33":null,"other_Item34":null,"other_Item35":null,"other_Item36":null,"other_Item37":null,"other_Item38":null,"other_Item39":null,"other_Item40":null,"position":null,"mobile":"010-85691480","gender":2,"weixinid":null,"group_Id":8,"weixin_Status":null,"synch_time":null,"endowment_Rate":null,"medical_Rate":null,"unemployment_Rate":null,"maternity_Rate":null,"injury_Rate":null,"housing_Fund_Rate":null,"salary":null,"isspecial":null,"group_Name":null,"exam_Authority":1,"yearly_Hol_Num":null,"methodname":null,"entry_Time":null,"departure_Time":null,"extra_Work_Authority":0},{"emp_Id":1195,"cust_Id":29,"emp_Name":"刘晶","cust_Inter_No":null,"certificate_Type":4,"certificate_No":"222","email":"liu.jing5@fesco.com.cn","phone":null,"login_Name":"liujing","login_Password":"Fn0NZNYJi7fc4BH2p+O9fA==","nationality":null,"address":null,"zipcode":null,"emp_Status":1,"tax_Base":3500,"photo_Url":null,"memo":null,"modify_Time":1472054400000,"modifier":"胡松","other_Item1":null,"other_Item2":null,"other_Item3":null,"other_Item4":null,"other_Item5":null,"other_Item6":null,"other_Item7":null,"other_Item8":null,"other_Item9":null,"other_Item10":null,"other_Item11":null,"other_Item12":null,"other_Item13":null,"other_Item14":null,"other_Item15":null,"other_Item16":null,"other_Item17":null,"other_Item18":null,"other_Item19":null,"other_Item20":null,"other_Item21":null,"other_Item22":null,"other_Item23":null,"other_Item24":null,"other_Item25":null,"other_Item26":null,"other_Item27":null,"other_Item28":null,"other_Item29":null,"other_Item30":null,"other_Item31":null,"other_Item32":null,"other_Item33":null,"other_Item34":null,"other_Item35":null,"other_Item36":null,"other_Item37":null,"other_Item38":null,"other_Item39":null,"other_Item40":null,"position":null,"mobile":"18610105320","gender":0,"weixinid":"heibaidoudou","group_Id":10,"weixin_Status":4,"synch_time":1461600000000,"endowment_Rate":null,"medical_Rate":null,"unemployment_Rate":null,"maternity_Rate":null,"injury_Rate":null,"housing_Fund_Rate":null,"salary":null,"isspecial":null,"group_Name":null,"exam_Authority":1,"yearly_Hol_Num":null,"methodname":null,"entry_Time":null,"departure_Time":null,"extra_Work_Authority":0},{"emp_Id":4061,"cust_Id":29,"emp_Name":"马楠","cust_Inter_No":null,"certificate_Type":null,"certificate_No":null,"email":"ma.nan@fesco.com.cn","phone":null,"login_Name":null,"login_Password":null,"nationality":null,"address":null,"zipcode":null,"emp_Status":1,"tax_Base":3500,"photo_Url":null,"memo":null,"modify_Time":1462896000000,"modifier":"胡松","other_Item1":null,"other_Item2":null,"other_Item3":null,"other_Item4":null,"other_Item5":null,"other_Item6":null,"other_Item7":null,"other_Item8":null,"other_Item9":null,"other_Item10":null,"other_Item11":null,"other_Item12":null,"other_Item13":null,"other_Item14":null,"other_Item15":null,"other_Item16":null,"other_Item17":null,"other_Item18":null,"other_Item19":null,"other_Item20":null,"other_Item21":null,"other_Item22":null,"other_Item23":null,"other_Item24":null,"other_Item25":null,"other_Item26":null,"other_Item27":null,"other_Item28":null,"other_Item29":null,"other_Item30":null,"other_Item31":null,"other_Item32":null,"other_Item33":null,"other_Item34":null,"other_Item35":null,"other_Item36":null,"other_Item37":null,"other_Item38":null,"other_Item39":null,"other_Item40":null,"position":null,"mobile":"18601358296","gender":null,"weixinid":null,"group_Id":9,"weixin_Status":null,"synch_time":null,"endowment_Rate":null,"medical_Rate":null,"unemployment_Rate":null,"maternity_Rate":null,"injury_Rate":null,"housing_Fund_Rate":null,"salary":null,"isspecial":null,"group_Name":null,"exam_Authority":1,"yearly_Hol_Num":null,"methodname":null,"entry_Time":null,"departure_Time":null,"extra_Work_Authority":0},{"emp_Id":4081,"cust_Id":29,"emp_Name":"盛铮","cust_Inter_No":null,"certificate_Type":null,"certificate_No":null,"email":"sheng.zheng@fesco.com.cn","phone":"010-85664757","login_Name":"shengzheng","login_Password":"SSAtVGM7mwCCdt4+C1BPUw==","nationality":null,"address":"C102","zipcode":null,"emp_Status":1,"tax_Base":3500,"photo_Url":null,"memo":null,"modify_Time":1480435200000,"modifier":"胡松","other_Item1":null,"other_Item2":null,"other_Item3":null,"other_Item4":null,"other_Item5":null,"other_Item6":null,"other_Item7":null,"other_Item8":null,"other_Item9":null,"other_Item10":null,"other_Item11":null,"other_Item12":null,"other_Item13":null,"other_Item14":null,"other_Item15":null,"other_Item16":null,"other_Item17":null,"other_Item18":null,"other_Item19":null,"other_Item20":null,"other_Item21":null,"other_Item22":null,"other_Item23":null,"other_Item24":null,"other_Item25":null,"other_Item26":null,"other_Item27":null,"other_Item28":null,"other_Item29":null,"other_Item30":null,"other_Item31":null,"other_Item32":null,"other_Item33":null,"other_Item34":null,"other_Item35":null,"other_Item36":null,"other_Item37":null,"other_Item38":null,"other_Item39":null,"other_Item40":null,"position":null,"mobile":"18601358247","gender":2,"weixinid":"shengzheng123","group_Id":11,"weixin_Status":null,"synch_time":null,"endowment_Rate":null,"medical_Rate":null,"unemployment_Rate":null,"maternity_Rate":null,"injury_Rate":null,"housing_Fund_Rate":null,"salary":null,"isspecial":null,"group_Name":null,"exam_Authority":1,"yearly_Hol_Num":0,"methodname":null,"entry_Time":null,"departure_Time":null,"extra_Work_Authority":0}]
     * errcode : 0
     */

    private String is_Other_Party;

    public String getIs_Other_Party() {
        return is_Other_Party == null ? "" : is_Other_Party;
    }

    public void setIs_Other_Party(String is_Other_Party) {
        this.is_Other_Party = is_Other_Party;
    }


    private String message;
    /**
     * apply_Id : 182
     * cust_Id : 29
     * memo : cs
     * type : 1
     * type_Str : null
     * title : 1112
     * group_Id : 2
     * group_Name : 管理咨询部
     * apply_Date : 1483891200000
     * apply_Date_Str : null
     * edit_Time : 1483959808000
     * exam_End_Is : 1
     * exam_End_Is_Str : null
     * exam_Step_Is_Over : 0
     * exam_Step_Is_Over_Str : null
     * emp_Id : 163
     * emp_Name : 胡松
     * account_Id : 1401
     * account_Name : null
     * money_Sum : null
     * approval_Man : null
     * search_Begin : null
     * search_End : null
     * details : [{"detail_Id":266,"detail_Id_Before_Imported":null,"apply_Id":182,"spend_Type_Str":"通讯","spend_Type":7,"cust_Id":null,"money_Amount":333,"bill_Num":2,"spend_Begin":1484323200000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"3334444444","trId":null,"icon":"fa fa-phone fa-lg","emp_Id":null,"pics":[{"id":null,"detail_Id":266,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]
     */

    private ApplyBean apply;
    /**
     * step_Id : 102
     * apply_Id : 182
     * is_Pass : 2
     * approval_Man : 163
     * approval_Time : null
     * next_Approval_Man : null
     * memo : null
     * is_Over : 0
     * approval_Man_Str : 胡松
     * is_Pass_Str : 通过审批
     */

    private LastApprovalStepBean lastApprovalStep;
    private int errcode;
    /**
     * emp_Id : 163
     * cust_Id : 29
     * emp_Name : 胡松
     * cust_Inter_No : null
     * certificate_Type : 4
     * certificate_No : 22
     * email : hu.song@fesco.com.cn
     * phone : 18611279997
     * login_Name : husong
     * login_Password : f9uiGzKzuGu6G2GsGnoKtQ==
     * nationality : null
     * address : null
     * zipcode : null
     * emp_Status : 1
     * tax_Base : 3500
     * photo_Url : S:/photo/29/163/163_pic.jpg
     * memo : null
     * modify_Time : 1483459200000
     * modifier : 胡松
     * other_Item1 : null
     * other_Item2 : 8000
     * other_Item3 : null
     * other_Item4 : null
     * other_Item5 : null
     * other_Item6 : null
     * other_Item7 : null
     * other_Item8 : null
     * other_Item9 : null
     * other_Item10 : null
     * other_Item11 : null
     * other_Item12 : null
     * other_Item13 : null
     * other_Item14 : null
     * other_Item15 : null
     * other_Item16 : null
     * other_Item17 : null
     * other_Item18 : null
     * other_Item19 : null
     * other_Item20 : null
     * other_Item21 : null
     * other_Item22 : null
     * other_Item23 : null
     * other_Item24 : null
     * other_Item25 : null
     * other_Item26 : null
     * other_Item27 : null
     * other_Item28 : null
     * other_Item29 : null
     * other_Item30 : null
     * other_Item31 : null
     * other_Item32 : null
     * other_Item33 : null
     * other_Item34 : null
     * other_Item35 : null
     * other_Item36 : null
     * other_Item37 : null
     * other_Item38 : null
     * other_Item39 : null
     * other_Item40 : null
     * position : null
     * mobile : 010-65874734
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
     * entry_Time : null
     * departure_Time : null
     * extra_Work_Authority : 0
     */

    private List<ApprovalManListBean> approvalManList;

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

    public LastApprovalStepBean getLastApprovalStep() {
        return lastApprovalStep;
    }

    public void setLastApprovalStep(LastApprovalStepBean lastApprovalStep) {
        this.lastApprovalStep = lastApprovalStep;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public List<ApprovalManListBean> getApprovalManList() {
        return approvalManList;
    }

    public void setApprovalManList(List<ApprovalManListBean> approvalManList) {
        this.approvalManList = approvalManList;
    }

    public static class ApplyBean  implements  Serializable{
        private static final long serialVersionUID = -1768862348342480777L;
        private int apply_Id;
        private int cust_Id;
        private String memo;
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
        private String account_Name;
        private Object money_Sum;
        private String approval_Man;
        private Object search_Begin;
        private Object search_End;



        /**
         * detail_Id : 266
         * detail_Id_Before_Imported : null
         * apply_Id : 182
         * spend_Type_Str : 通讯
         * spend_Type : 7
         * cust_Id : null
         * money_Amount : 333
         * bill_Num : 2
         * spend_Begin : 1484323200000
         * spend_Begin_Str : null
         * spend_End : null
         * spend_End_Str : null
         * expense_Date : null
         * spend_City : null
         * detail_Memo : 3334444444
         * trId : null
         * icon : fa fa-phone fa-lg
         * emp_Id : null
         * pics : [{"id":null,"detail_Id":266,"pic_Url":null,"pic_Desc":null}]
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

        public String getAccount_Name() {
            return account_Name == null ? "" : account_Name;
        }

        public void setAccount_Name(String account_Name) {
            this.account_Name = account_Name;
        }

        public Object getMoney_Sum() {
            return money_Sum;
        }

        public void setMoney_Sum(Object money_Sum) {
            this.money_Sum = money_Sum;
        }

        public String getApproval_Man() {
            return approval_Man == null ? "" : approval_Man;
        }

        public void setApproval_Man(String approval_Man) {
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

        public static class DetailsBean implements Serializable {
            private static final long serialVersionUID = 8083758492660785893L;
            private int detail_Id;
            private Object detail_Id_Before_Imported;
            private int apply_Id;
            private String spend_Type_Str;
            private int spend_Type;
            private Object cust_Id;
            private String money_Amount;
            private int bill_Num;
            private long spend_Begin;
            private Object spend_Begin_Str;
            private String spend_End;
            private Object spend_End_Str;
            private Object expense_Date;
            private String spend_City;
            private String detail_Memo;
            private Object trId;
            private String icon;
            private String android_Icon;

            public String getAndroid_Icon() {
                return android_Icon;
            }

            public void setAndroid_Icon(String android_Icon) {
                this.android_Icon = android_Icon;
            }

            private Object emp_Id;
            private Object pic_Ids;
            /**
             * id : null
             * detail_Id : 266
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

            public String getMoney_Amount() {
                return money_Amount == null ? "" : money_Amount;
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

            public String getSpend_End() {
                return spend_End == null ? "" : spend_End;
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
                return spend_City == null ? "" : spend_City;
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

            public static class PicsBean implements  Serializable{
                private static final long serialVersionUID = 685995345165472446L;
                private String id;
                private int detail_Id;
                private Object pic_Url;
                private Object pic_Desc;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
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

    public static class LastApprovalStepBean {
        private int step_Id;
        private int apply_Id;
        private int is_Pass;
        private int approval_Man;
        private String approval_Time;
        private Object next_Approval_Man;
        private String memo;
        private int is_Over;
        private String approval_Man_Str;
        private String is_Pass_Str;


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

        public int getApproval_Man() {
            return approval_Man;
        }

        public void setApproval_Man(int approval_Man) {
            this.approval_Man = approval_Man;
        }

        public String getApproval_Time() {
            return approval_Time == null ? "" : approval_Time;
        }

        public void setApproval_Time(String approval_Time) {
            this.approval_Time = approval_Time;
        }

        public Object getNext_Approval_Man() {
            return next_Approval_Man;
        }

        public void setNext_Approval_Man(Object next_Approval_Man) {
            this.next_Approval_Man = next_Approval_Man;
        }

        public String getMemo() {
            return memo == null ? "" : memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getIs_Over() {
            return is_Over;
        }

        public void setIs_Over(int is_Over) {
            this.is_Over = is_Over;
        }

        public String getApproval_Man_Str() {
            return approval_Man_Str;
        }

        public void setApproval_Man_Str(String approval_Man_Str) {
            this.approval_Man_Str = approval_Man_Str;
        }

        public String getIs_Pass_Str() {
            return is_Pass_Str == null ? "" : is_Pass_Str;
        }

        public void setIs_Pass_Str(String is_Pass_Str) {
            this.is_Pass_Str = is_Pass_Str;
        }
    }

    public static class ApprovalManListBean {
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
        private String photo_Url;
        private Object memo;
        private long modify_Time;
        private String modifier;
        private Object other_Item1;
        private String other_Item2;
        private Object other_Item3;
        private Object other_Item4;
        private Object other_Item5;
        private Object other_Item6;
        private Object other_Item7;
        private Object other_Item8;
        private Object other_Item9;
        private Object other_Item10;
        private Object other_Item11;
        private Object other_Item12;
        private Object other_Item13;
        private Object other_Item14;
        private Object other_Item15;
        private Object other_Item16;
        private Object other_Item17;
        private Object other_Item18;
        private Object other_Item19;
        private Object other_Item20;
        private Object other_Item21;
        private Object other_Item22;
        private Object other_Item23;
        private Object other_Item24;
        private Object other_Item25;
        private Object other_Item26;
        private Object other_Item27;
        private Object other_Item28;
        private Object other_Item29;
        private Object other_Item30;
        private Object other_Item31;
        private Object other_Item32;
        private Object other_Item33;
        private Object other_Item34;
        private Object other_Item35;
        private Object other_Item36;
        private Object other_Item37;
        private Object other_Item38;
        private Object other_Item39;
        private Object other_Item40;
        private Object position;
        private String mobile;
        private int gender;
        private String weixinid;
        private int group_Id;
        private int weixin_Status;
        private long synch_time;
        private Object endowment_Rate;
        private Object medical_Rate;
        private Object unemployment_Rate;
        private Object maternity_Rate;
        private Object injury_Rate;
        private Object housing_Fund_Rate;
        private int salary;
        private int isspecial;
        private Object group_Name;
        private int exam_Authority;
        private Object yearly_Hol_Num;
        private Object methodname;
        private Object entry_Time;
        private Object departure_Time;
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

        public String getPhoto_Url() {
            return photo_Url;
        }

        public void setPhoto_Url(String photo_Url) {
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

        public Object getOther_Item1() {
            return other_Item1;
        }

        public void setOther_Item1(Object other_Item1) {
            this.other_Item1 = other_Item1;
        }

        public String getOther_Item2() {
            return other_Item2;
        }

        public void setOther_Item2(String other_Item2) {
            this.other_Item2 = other_Item2;
        }

        public Object getOther_Item3() {
            return other_Item3;
        }

        public void setOther_Item3(Object other_Item3) {
            this.other_Item3 = other_Item3;
        }

        public Object getOther_Item4() {
            return other_Item4;
        }

        public void setOther_Item4(Object other_Item4) {
            this.other_Item4 = other_Item4;
        }

        public Object getOther_Item5() {
            return other_Item5;
        }

        public void setOther_Item5(Object other_Item5) {
            this.other_Item5 = other_Item5;
        }

        public Object getOther_Item6() {
            return other_Item6;
        }

        public void setOther_Item6(Object other_Item6) {
            this.other_Item6 = other_Item6;
        }

        public Object getOther_Item7() {
            return other_Item7;
        }

        public void setOther_Item7(Object other_Item7) {
            this.other_Item7 = other_Item7;
        }

        public Object getOther_Item8() {
            return other_Item8;
        }

        public void setOther_Item8(Object other_Item8) {
            this.other_Item8 = other_Item8;
        }

        public Object getOther_Item9() {
            return other_Item9;
        }

        public void setOther_Item9(Object other_Item9) {
            this.other_Item9 = other_Item9;
        }

        public Object getOther_Item10() {
            return other_Item10;
        }

        public void setOther_Item10(Object other_Item10) {
            this.other_Item10 = other_Item10;
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

        public Object getOther_Item15() {
            return other_Item15;
        }

        public void setOther_Item15(Object other_Item15) {
            this.other_Item15 = other_Item15;
        }

        public Object getOther_Item16() {
            return other_Item16;
        }

        public void setOther_Item16(Object other_Item16) {
            this.other_Item16 = other_Item16;
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

        public Object getOther_Item19() {
            return other_Item19;
        }

        public void setOther_Item19(Object other_Item19) {
            this.other_Item19 = other_Item19;
        }

        public Object getOther_Item20() {
            return other_Item20;
        }

        public void setOther_Item20(Object other_Item20) {
            this.other_Item20 = other_Item20;
        }

        public Object getOther_Item21() {
            return other_Item21;
        }

        public void setOther_Item21(Object other_Item21) {
            this.other_Item21 = other_Item21;
        }

        public Object getOther_Item22() {
            return other_Item22;
        }

        public void setOther_Item22(Object other_Item22) {
            this.other_Item22 = other_Item22;
        }

        public Object getOther_Item23() {
            return other_Item23;
        }

        public void setOther_Item23(Object other_Item23) {
            this.other_Item23 = other_Item23;
        }

        public Object getOther_Item24() {
            return other_Item24;
        }

        public void setOther_Item24(Object other_Item24) {
            this.other_Item24 = other_Item24;
        }

        public Object getOther_Item25() {
            return other_Item25;
        }

        public void setOther_Item25(Object other_Item25) {
            this.other_Item25 = other_Item25;
        }

        public Object getOther_Item26() {
            return other_Item26;
        }

        public void setOther_Item26(Object other_Item26) {
            this.other_Item26 = other_Item26;
        }

        public Object getOther_Item27() {
            return other_Item27;
        }

        public void setOther_Item27(Object other_Item27) {
            this.other_Item27 = other_Item27;
        }

        public Object getOther_Item28() {
            return other_Item28;
        }

        public void setOther_Item28(Object other_Item28) {
            this.other_Item28 = other_Item28;
        }

        public Object getOther_Item29() {
            return other_Item29;
        }

        public void setOther_Item29(Object other_Item29) {
            this.other_Item29 = other_Item29;
        }

        public Object getOther_Item30() {
            return other_Item30;
        }

        public void setOther_Item30(Object other_Item30) {
            this.other_Item30 = other_Item30;
        }

        public Object getOther_Item31() {
            return other_Item31;
        }

        public void setOther_Item31(Object other_Item31) {
            this.other_Item31 = other_Item31;
        }

        public Object getOther_Item32() {
            return other_Item32;
        }

        public void setOther_Item32(Object other_Item32) {
            this.other_Item32 = other_Item32;
        }

        public Object getOther_Item33() {
            return other_Item33;
        }

        public void setOther_Item33(Object other_Item33) {
            this.other_Item33 = other_Item33;
        }

        public Object getOther_Item34() {
            return other_Item34;
        }

        public void setOther_Item34(Object other_Item34) {
            this.other_Item34 = other_Item34;
        }

        public Object getOther_Item35() {
            return other_Item35;
        }

        public void setOther_Item35(Object other_Item35) {
            this.other_Item35 = other_Item35;
        }

        public Object getOther_Item36() {
            return other_Item36;
        }

        public void setOther_Item36(Object other_Item36) {
            this.other_Item36 = other_Item36;
        }

        public Object getOther_Item37() {
            return other_Item37;
        }

        public void setOther_Item37(Object other_Item37) {
            this.other_Item37 = other_Item37;
        }

        public Object getOther_Item38() {
            return other_Item38;
        }

        public void setOther_Item38(Object other_Item38) {
            this.other_Item38 = other_Item38;
        }

        public Object getOther_Item39() {
            return other_Item39;
        }

        public void setOther_Item39(Object other_Item39) {
            this.other_Item39 = other_Item39;
        }

        public Object getOther_Item40() {
            return other_Item40;
        }

        public void setOther_Item40(Object other_Item40) {
            this.other_Item40 = other_Item40;
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

        public Object getEndowment_Rate() {
            return endowment_Rate;
        }

        public void setEndowment_Rate(Object endowment_Rate) {
            this.endowment_Rate = endowment_Rate;
        }

        public Object getMedical_Rate() {
            return medical_Rate;
        }

        public void setMedical_Rate(Object medical_Rate) {
            this.medical_Rate = medical_Rate;
        }

        public Object getUnemployment_Rate() {
            return unemployment_Rate;
        }

        public void setUnemployment_Rate(Object unemployment_Rate) {
            this.unemployment_Rate = unemployment_Rate;
        }

        public Object getMaternity_Rate() {
            return maternity_Rate;
        }

        public void setMaternity_Rate(Object maternity_Rate) {
            this.maternity_Rate = maternity_Rate;
        }

        public Object getInjury_Rate() {
            return injury_Rate;
        }

        public void setInjury_Rate(Object injury_Rate) {
            this.injury_Rate = injury_Rate;
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

        public Object getEntry_Time() {
            return entry_Time;
        }

        public void setEntry_Time(Object entry_Time) {
            this.entry_Time = entry_Time;
        }

        public Object getDeparture_Time() {
            return departure_Time;
        }

        public void setDeparture_Time(Object departure_Time) {
            this.departure_Time = departure_Time;
        }

        public int getExtra_Work_Authority() {
            return extra_Work_Authority;
        }

        public void setExtra_Work_Authority(int extra_Work_Authority) {
            this.extra_Work_Authority = extra_Work_Authority;
        }
    }
}
