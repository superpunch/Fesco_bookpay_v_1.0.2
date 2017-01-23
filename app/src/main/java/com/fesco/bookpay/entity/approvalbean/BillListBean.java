package com.fesco.bookpay.entity.approvalbean;

import java.util.List;

/**
 * Created by gong.min on 2017/1/10.
 */
public class BillListBean  {


    /**
     * message : success
     * list : [{"apply_Id":108,"cust_Id":29,"memo":"BB","type":1,"type_Str":"日常报销单","title":"YY","group_Id":2,"group_Name":null,"apply_Date":1482336000000,"apply_Date_Str":null,"edit_Time":1482399101000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1401,"account_Name":null,"money_Sum":57,"approval_Man":null,"search_Begin":null,"search_End":null,"details":null},{"apply_Id":104,"cust_Id":29,"memo":"WeiTi","type":3,"type_Str":"付款申请单","title":"WeiTi","group_Id":4,"group_Name":null,"apply_Date":1482336000000,"apply_Date_Str":null,"edit_Time":1482386389000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1401,"account_Name":null,"money_Sum":100,"approval_Man":null,"search_Begin":null,"search_End":null,"details":null},{"apply_Id":101,"cust_Id":29,"memo":"Weizhidan","type":2,"type_Str":"差旅报销单","title":"Weizhidan","group_Id":4,"group_Name":null,"apply_Date":1482336000000,"apply_Date_Str":null,"edit_Time":1482370154000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1401,"account_Name":null,"money_Sum":10021,"approval_Man":null,"search_Begin":null,"search_End":null,"details":null},{"apply_Id":100,"cust_Id":29,"memo":"Ceshiweizhidan","type":2,"type_Str":"差旅报销单","title":"Ceshiweishenpi","group_Id":4,"group_Name":null,"apply_Date":1482336000000,"apply_Date_Str":null,"edit_Time":1482368559000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1401,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":null},{"apply_Id":86,"cust_Id":29,"memo":"ce","type":2,"type_Str":"差旅报销单","title":"MM","group_Id":2,"group_Name":null,"apply_Date":1481644800000,"apply_Date_Str":null,"edit_Time":1481784685000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1401,"account_Name":null,"money_Sum":200,"approval_Man":null,"search_Begin":null,"search_End":null,"details":null},{"apply_Id":83,"cust_Id":29,"memo":"测试","type":1,"type_Str":"日常报销单","title":"C","group_Id":2,"group_Name":null,"apply_Date":1482336000000,"apply_Date_Str":null,"edit_Time":1482400661000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1401,"account_Name":null,"money_Sum":9902,"approval_Man":null,"search_Begin":null,"search_End":null,"details":null},{"apply_Id":66,"cust_Id":29,"memo":"ceshi","type":3,"type_Str":"付款申请单","title":"FuKuang","group_Id":2,"group_Name":null,"apply_Date":1481472000000,"apply_Date_Str":null,"edit_Time":1481533235000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1401,"account_Name":null,"money_Sum":88,"approval_Man":null,"search_Begin":null,"search_End":null,"details":null},{"apply_Id":27,"cust_Id":29,"memo":null,"type":1,"type_Str":"日常报销单","title":"test2","group_Id":2,"group_Name":null,"apply_Date":1479225600000,"apply_Date_Str":null,"edit_Time":1479883471000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":2,"emp_Name":null,"account_Id":21,"account_Name":null,"money_Sum":233,"approval_Man":null,"search_Begin":null,"search_End":null,"details":null}]
     * errcode : 0
     */

    private String message;
    private int errcode;
    /**
     * apply_Id : 108
     * cust_Id : 29
     * memo : BB
     * type : 1
     * type_Str : 日常报销单
     * title : YY
     * group_Id : 2
     * group_Name : null
     * apply_Date : 1482336000000
     * apply_Date_Str : null
     * edit_Time : 1482399101000
     * exam_End_Is : 1
     * exam_End_Is_Str : null
     * exam_Step_Is_Over : 0
     * exam_Step_Is_Over_Str : null
     * emp_Id : 163
     * emp_Name : null
     * account_Id : 1401
     * account_Name : null
     * money_Sum : 57
     * approval_Man : null
     * search_Begin : null
     * search_End : null
     * details : null
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
        private int apply_Id;
        private int cust_Id;
        private String memo;
        private int type;
        private String type_Str;
        private String title;
        private int group_Id;
        private String group_Name;
        private long apply_Date;
        private String apply_Date_Str;
        private long edit_Time;
        private int exam_End_Is;
        private String exam_End_Is_Str;
        private int exam_Step_Is_Over;
        private String exam_Step_Is_Over_Str;
        private int emp_Id;
        private String emp_Name;
        private int account_Id;
        private String account_Name;
        private String money_Sum;
        private String approval_Man;
        private String search_Begin;
        private String search_End;
      //  private String details;

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

        public String getApply_Date_Str() {
            return apply_Date_Str;
        }

        public void setApply_Date_Str(String apply_Date_Str) {
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

        public String getExam_End_Is_Str() {
            return exam_End_Is_Str;
        }

        public void setExam_End_Is_Str(String exam_End_Is_Str) {
            this.exam_End_Is_Str = exam_End_Is_Str;
        }

        public int getExam_Step_Is_Over() {
            return exam_Step_Is_Over;
        }

        public void setExam_Step_Is_Over(int exam_Step_Is_Over) {
            this.exam_Step_Is_Over = exam_Step_Is_Over;
        }

        public String getExam_Step_Is_Over_Str() {
            return exam_Step_Is_Over_Str;
        }

        public void setExam_Step_Is_Over_Str(String exam_Step_Is_Over_Str) {
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
            return account_Name;
        }

        public void setAccount_Name(String account_Name) {
            this.account_Name = account_Name;
        }

        public String getMoney_Sum() {
            return money_Sum;
        }

        public void setMoney_Sum(String money_Sum) {
            this.money_Sum = money_Sum;
        }

        public String getApproval_Man() {
            return approval_Man;
        }

        public void setApproval_Man(String approval_Man) {
            this.approval_Man = approval_Man;
        }

        public String getSearch_Begin() {
            return search_Begin;
        }

        public void setSearch_Begin(String search_Begin) {
            this.search_Begin = search_Begin;
        }

        public String getSearch_End() {
            return search_End;
        }

        public void setSearch_End(String search_End) {
            this.search_End = search_End;
        }

//        public String getDetails() {
//            return details;
//        }
//
//        public void setDetails(String details) {
//            this.details = details;
//        }
    }
}
