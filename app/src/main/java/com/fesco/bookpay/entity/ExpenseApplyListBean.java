package com.fesco.bookpay.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gong.min on 2016/12/12.
 */
public class ExpenseApplyListBean  {



    /**
     * message : success
     * list : [{"apply_Id":65,"cust_Id":29,"memo":null,"type":1,"type_Str":"日常报销单","title":"ceshi","group_Id":100,"group_Name":null,"apply_Date":1481472000000,"apply_Date_Str":null,"edit_Time":1481526391000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":98,"apply_Id":65,"spend_Type_Str":"其他","spend_Type":15,"cust_Id":null,"money_Amount":999,"bill_Num":0,"spend_Begin":1481990400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":102,"detail_Id":98,"pic_Url":"F://expensePics/9/7/20161212150613com_qihoo_appstore.png","pic_Desc":null}],"pic_Ids":null},{"detail_Id":97,"apply_Id":65,"spend_Type_Str":"补助","spend_Type":8,"cust_Id":null,"money_Amount":888,"bill_Num":0,"spend_Begin":1481472000000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":101,"detail_Id":97,"pic_Url":"F://expensePics/7/15/20161212150554com_zzti_fengongge_imagepickerdemo.png","pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":64,"cust_Id":29,"memo":null,"type":1,"type_Str":"日常报销单","title":"111","group_Id":100,"group_Name":null,"apply_Date":1481472000000,"apply_Date_Str":null,"edit_Time":1481526078000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":96,"apply_Id":64,"spend_Type_Str":"团建-测试","spend_Type":46,"cust_Id":null,"money_Amount":11,"bill_Num":1,"spend_Begin":1481472000000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":96,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":63,"cust_Id":29,"memo":"xxx","type":1,"type_Str":"日常报销单","title":"xss","group_Id":100,"group_Name":null,"apply_Date":1481472000000,"apply_Date_Str":null,"edit_Time":1481525991000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":95,"apply_Id":63,"spend_Type_Str":"住宿-如家","spend_Type":28,"cust_Id":null,"money_Amount":11,"bill_Num":0,"spend_Begin":1481472000000,"spend_Begin_Str":null,"spend_End":1481644800000,"spend_End_Str":null,"expense_Date":null,"spend_City":"重庆","detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":100,"detail_Id":95,"pic_Url":"F://expensePics/2/9/20161212145931com_qihoo_appstore.png","pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":62,"cust_Id":29,"memo":"zzz","type":1,"type_Str":"日常报销单","title":"cs","group_Id":100,"group_Name":null,"apply_Date":1481472000000,"apply_Date_Str":null,"edit_Time":1481523915000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":94,"apply_Id":62,"spend_Type_Str":"团建-会议室","spend_Type":45,"cust_Id":null,"money_Amount":34,"bill_Num":2,"spend_Begin":1481472000000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"zzz","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":94,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":61,"cust_Id":29,"memo":null,"type":1,"type_Str":"日常报销单","title":"cs","group_Id":100,"group_Name":null,"apply_Date":1481472000000,"apply_Date_Str":null,"edit_Time":1481523910000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":93,"apply_Id":61,"spend_Type_Str":"团建-会议室","spend_Type":45,"cust_Id":null,"money_Amount":34,"bill_Num":2,"spend_Begin":1481472000000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"zzz","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":93,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":60,"cust_Id":29,"memo":"xxx","type":1,"type_Str":"日常报销单","title":"cs","group_Id":99,"group_Name":null,"apply_Date":1481385600000,"apply_Date_Str":null,"edit_Time":1481462781000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":92,"apply_Id":60,"spend_Type_Str":"找不到了","spend_Type":null,"cust_Id":null,"money_Amount":2,"bill_Num":2,"spend_Begin":1481385600000,"spend_Begin_Str":null,"spend_End":1481385600000,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"zzz","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":92,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":59,"cust_Id":29,"memo":"aaa","type":1,"type_Str":"日常报销单","title":"123","group_Id":94,"group_Name":null,"apply_Date":1481212800000,"apply_Date_Str":null,"edit_Time":1481275964000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":91,"apply_Id":59,"spend_Type_Str":"找不到了","spend_Type":null,"cust_Id":null,"money_Amount":2222,"bill_Num":0,"spend_Begin":1481212800000,"spend_Begin_Str":null,"spend_End":1481212800000,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"zzzzz","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":91,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":58,"cust_Id":29,"memo":"sp","type":1,"type_Str":"日常报销单","title":"che fei","group_Id":95,"group_Name":null,"apply_Date":1481212800000,"apply_Date_Str":null,"edit_Time":1481275738000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":89,"apply_Id":58,"spend_Type_Str":"找不到了","spend_Type":null,"cust_Id":null,"money_Amount":21,"bill_Num":0,"spend_Begin":1481212800000,"spend_Begin_Str":null,"spend_End":1481299200000,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"rrr","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":89,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null},{"detail_Id":90,"apply_Id":58,"spend_Type_Str":"团建-测试","spend_Type":46,"cust_Id":null,"money_Amount":2,"bill_Num":0,"spend_Begin":1481212800000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":90,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":57,"cust_Id":29,"memo":"sp","type":1,"type_Str":"日常报销单","title":"che fei","group_Id":95,"group_Name":null,"apply_Date":1481212800000,"apply_Date_Str":null,"edit_Time":1481275373000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":88,"apply_Id":57,"spend_Type_Str":"找不到了","spend_Type":null,"cust_Id":null,"money_Amount":21,"bill_Num":0,"spend_Begin":1481212800000,"spend_Begin_Str":null,"spend_End":1481299200000,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"rrr","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":88,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":56,"cust_Id":29,"memo":"Ceshi","type":1,"type_Str":"日常报销单","title":"chishi","group_Id":100,"group_Name":null,"apply_Date":1481212800000,"apply_Date_Str":null,"edit_Time":1481263134000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":87,"apply_Id":56,"spend_Type_Str":"交通-公交","spend_Type":18,"cust_Id":null,"money_Amount":20,"bill_Num":1,"spend_Begin":1481212800000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"ceshi","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":49,"detail_Id":87,"pic_Url":"F://expensePics/5/8/20161209135802IMG_0002.JPG","pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":55,"cust_Id":29,"memo":"123","type":1,"type_Str":"日常报销单","title":"009","group_Id":4,"group_Name":null,"apply_Date":1481126400000,"apply_Date_Str":null,"edit_Time":1481253984000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":86,"apply_Id":55,"spend_Type_Str":"其他","spend_Type":15,"cust_Id":null,"money_Amount":666,"bill_Num":3,"spend_Begin":1478534400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":86,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":54,"cust_Id":29,"memo":"123","type":1,"type_Str":"日常报销单","title":"009","group_Id":2,"group_Name":null,"apply_Date":1481126400000,"apply_Date_Str":null,"edit_Time":1481253798000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":85,"apply_Id":54,"spend_Type_Str":"其他","spend_Type":15,"cust_Id":null,"money_Amount":666,"bill_Num":3,"spend_Begin":1478534400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":85,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null},{"detail_Id":84,"apply_Id":54,"spend_Type_Str":"团建-会议室","spend_Type":45,"cust_Id":null,"money_Amount":23,"bill_Num":1,"spend_Begin":1481126400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":84,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":53,"cust_Id":29,"memo":"123","type":1,"type_Str":"日常报销单","title":"009","group_Id":4,"group_Name":null,"apply_Date":1481126400000,"apply_Date_Str":null,"edit_Time":1481253745000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":163,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":83,"apply_Id":53,"spend_Type_Str":"团建-会议室","spend_Type":45,"cust_Id":null,"money_Amount":23,"bill_Num":1,"spend_Begin":1481126400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":83,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":52,"cust_Id":29,"memo":"次","type":1,"type_Str":"日常报销单","title":"测试","group_Id":2,"group_Name":null,"apply_Date":1481212800000,"apply_Date_Str":null,"edit_Time":1481251322000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":82,"apply_Id":52,"spend_Type_Str":"餐饮-肯德基","spend_Type":21,"cust_Id":null,"money_Amount":50,"bill_Num":1,"spend_Begin":1481212800000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":48,"detail_Id":82,"pic_Url":"F://expensePics/12/3/20161209104014IMG_0244.JPG","pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":51,"cust_Id":29,"memo":"Ceshi","type":2,"type_Str":"差旅报销单","title":"Chalv","group_Id":2,"group_Name":null,"apply_Date":1481126400000,"apply_Date_Str":null,"edit_Time":1481190584000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":81,"apply_Id":51,"spend_Type_Str":"其他","spend_Type":15,"cust_Id":null,"money_Amount":100,"bill_Num":1,"spend_Begin":1481126400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"ceshi","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":47,"detail_Id":81,"pic_Url":"F://expensePics/12/4/20161208172858IMG_0002.JPG","pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":50,"cust_Id":29,"memo":"ceshi","type":1,"type_Str":"日常报销单","title":"Mia Oahu","group_Id":2,"group_Name":null,"apply_Date":1481212800000,"apply_Date_Str":null,"edit_Time":1481267843000,"exam_End_Is":0,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":80,"apply_Id":50,"spend_Type_Str":"交通-公交","spend_Type":18,"cust_Id":null,"money_Amount":20,"bill_Num":1,"spend_Begin":1481126400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"Ceshi","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":39,"detail_Id":80,"pic_Url":"F://expensePics/3/8/20161208145502IMG_0003.JPG","pic_Desc":null},{"id":38,"detail_Id":80,"pic_Url":"F://expensePics/2/0/20161208145502IMG_0002.JPG","pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":48,"cust_Id":29,"memo":"ceshi","type":2,"type_Str":"差旅报销单","title":"YY","group_Id":2,"group_Name":null,"apply_Date":1480953600000,"apply_Date_Str":null,"edit_Time":1481009790000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":77,"apply_Id":48,"spend_Type_Str":"交通-公交","spend_Type":18,"cust_Id":null,"money_Amount":100,"bill_Num":1,"spend_Begin":1480953600000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"ceshi","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":77,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null},{"detail_Id":78,"apply_Id":48,"spend_Type_Str":"餐饮-麦当劳","spend_Type":33,"cust_Id":null,"money_Amount":50,"bill_Num":1,"spend_Begin":1480867200000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"ceshi","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":78,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":47,"cust_Id":29,"memo":"ceshi","type":1,"type_Str":"日常报销单","title":"Tupian","group_Id":5,"group_Name":null,"apply_Date":1480953600000,"apply_Date_Str":null,"edit_Time":1480988647000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":76,"apply_Id":47,"spend_Type_Str":"交通-公交","spend_Type":18,"cust_Id":null,"money_Amount":80,"bill_Num":1,"spend_Begin":1480953600000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"ceshitupian","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":29,"detail_Id":76,"pic_Url":"F://expensePics/1/4/20161206094320IMG_0002.JPG","pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":46,"cust_Id":29,"memo":"ceshi","type":1,"type_Str":"日常报销单","title":"ceshi","group_Id":6,"group_Name":null,"apply_Date":1480953600000,"apply_Date_Str":null,"edit_Time":1480988499000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":75,"apply_Id":46,"spend_Type_Str":"长途-灰机","spend_Type":17,"cust_Id":null,"money_Amount":80,"bill_Num":1,"spend_Begin":1480953600000,"spend_Begin_Str":null,"spend_End":1480953600000,"spend_End_Str":null,"expense_Date":null,"spend_City":"安阳","detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":75,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":44,"cust_Id":29,"memo":"ceshi","type":2,"type_Str":"差旅报销单","title":"ceshiTT","group_Id":1,"group_Name":null,"apply_Date":1480867200000,"apply_Date_Str":null,"edit_Time":1480928262000,"exam_End_Is":0,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":73,"apply_Id":44,"spend_Type_Str":"交通-自行车","spend_Type":20,"cust_Id":null,"money_Amount":700,"bill_Num":1,"spend_Begin":1480867200000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"ceshi","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":26,"detail_Id":73,"pic_Url":"F://expensePics/12/3/20161205150250IMG_0003.JPG","pic_Desc":null},{"id":27,"detail_Id":73,"pic_Url":"F://expensePics/11/11/20161205150250IMG_0002.JPG","pic_Desc":null}],"pic_Ids":null},{"detail_Id":74,"apply_Id":44,"spend_Type_Str":"长途-高铁","spend_Type":23,"cust_Id":null,"money_Amount":100,"bill_Num":1,"spend_Begin":1480867200000,"spend_Begin_Str":null,"spend_End":1480953600000,"spend_End_Str":null,"expense_Date":null,"spend_City":"安阳","detail_Memo":"ce","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":28,"detail_Id":74,"pic_Url":"F://expensePics/5/10/20161205164923IMG_0002.JPG","pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":43,"cust_Id":29,"memo":"ceshi","type":1,"type_Str":"日常报销单","title":"ceshiMM","group_Id":4,"group_Name":null,"apply_Date":1480867200000,"apply_Date_Str":null,"edit_Time":1480925151000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":72,"apply_Id":43,"spend_Type_Str":"交通-公交","spend_Type":18,"cust_Id":null,"money_Amount":600,"bill_Num":1,"spend_Begin":1480780800000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"cd","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":25,"detail_Id":72,"pic_Url":"F://expensePics/11/15/20161205145520IMG_0002.JPG","pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":42,"cust_Id":29,"memo":"ceshi","type":2,"type_Str":"差旅报销单","title":"Tupian","group_Id":2,"group_Name":null,"apply_Date":1480867200000,"apply_Date_Str":null,"edit_Time":1480920494000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":71,"apply_Id":42,"spend_Type_Str":"交通-地铁","spend_Type":19,"cust_Id":null,"money_Amount":50,"bill_Num":1,"spend_Begin":1480867200000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"Rupiah","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":24,"detail_Id":71,"pic_Url":"F://expensePics/15/5/20161205144622IMG_0002.JPG","pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":41,"cust_Id":29,"memo":"ceshi","type":2,"type_Str":"差旅报销单","title":"YY","group_Id":4,"group_Name":null,"apply_Date":1478188800000,"apply_Date_Str":null,"edit_Time":1480904590000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":70,"apply_Id":41,"spend_Type_Str":"交通-地铁","spend_Type":19,"cust_Id":null,"money_Amount":200,"bill_Num":1,"spend_Begin":1480867200000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"d","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":70,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":40,"cust_Id":29,"memo":"ce","type":3,"type_Str":"付款申请单","title":"500","group_Id":2,"group_Name":null,"apply_Date":1480003200000,"apply_Date_Str":null,"edit_Time":1480487162000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[]},{"apply_Id":39,"cust_Id":29,"memo":"ceshi","type":1,"type_Str":"日常报销单","title":"ceshi","group_Id":2,"group_Name":null,"apply_Date":1480262400000,"apply_Date_Str":null,"edit_Time":1480486637000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":69,"apply_Id":39,"spend_Type_Str":"交通-地铁","spend_Type":19,"cust_Id":null,"money_Amount":50,"bill_Num":1,"spend_Begin":1480262400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"Rr","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":69,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":34,"cust_Id":29,"memo":"ceshi","type":1,"type_Str":"日常报销单","title":"ID","group_Id":5,"group_Name":null,"apply_Date":1480003200000,"apply_Date_Str":null,"edit_Time":1480056936000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":65,"apply_Id":34,"spend_Type_Str":"长途-灰机","spend_Type":17,"cust_Id":null,"money_Amount":100,"bill_Num":1,"spend_Begin":1479830400000,"spend_Begin_Str":null,"spend_End":1480003200000,"spend_End_Str":null,"expense_Date":null,"spend_City":"安阳","detail_Memo":"MMmm","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":65,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":33,"cust_Id":29,"memo":"ceshi","type":3,"type_Str":"付款申请单","title":"MM","group_Id":4,"group_Name":null,"apply_Date":1479744000000,"apply_Date_Str":null,"edit_Time":1479967835000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":64,"apply_Id":33,"spend_Type_Str":"长途-灰机","spend_Type":17,"cust_Id":null,"money_Amount":400,"bill_Num":1,"spend_Begin":1479657600000,"spend_Begin_Str":null,"spend_End":1479744000000,"spend_End_Str":null,"expense_Date":null,"spend_City":"安阳","detail_Memo":"ceshi","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":64,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":32,"cust_Id":29,"memo":"ceshi","type":1,"type_Str":"日常报销单","title":"ceshi","group_Id":2,"group_Name":null,"apply_Date":1479744000000,"apply_Date_Str":null,"edit_Time":1479966870000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":63,"apply_Id":32,"spend_Type_Str":"长途-灰机","spend_Type":17,"cust_Id":null,"money_Amount":100,"bill_Num":1,"spend_Begin":1479571200000,"spend_Begin_Str":null,"spend_End":1479744000000,"spend_End_Str":null,"expense_Date":null,"spend_City":"安阳","detail_Memo":"ceshi","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":63,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]},{"apply_Id":30,"cust_Id":29,"memo":"ceshi","type":1,"type_Str":"日常报销单","title":"tijiao","group_Id":2,"group_Name":null,"apply_Date":1479398400000,"apply_Date_Str":null,"edit_Time":1479883471000,"exam_End_Is":1,"exam_End_Is_Str":null,"exam_Step_Is_Over":0,"exam_Step_Is_Over_Str":null,"emp_Id":163,"emp_Name":null,"account_Id":1922226633855887,"account_Name":null,"money_Sum":null,"approval_Man":null,"search_Begin":null,"search_End":null,"details":[{"detail_Id":60,"apply_Id":30,"spend_Type_Str":"交通-公交","spend_Type":18,"cust_Id":null,"money_Amount":100,"bill_Num":1,"spend_Begin":1479225600000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":"ceshi","trId":null,"icon":null,"emp_Id":null,"pics":[{"id":null,"detail_Id":60,"pic_Url":null,"pic_Desc":null}],"pic_Ids":null}]}]
     * errcode : 0
     */

    private String message;
    private int errcode;
    /**
     * apply_Id : 65
     * cust_Id : 29
     * memo : null
     * type : 1
     * type_Str : 日常报销单
     * title : ceshi
     * group_Id : 100
     * group_Name : null
     * apply_Date : 1481472000000
     * apply_Date_Str : null
     * edit_Time : 1481526391000
     * exam_End_Is : 1
     * exam_End_Is_Str : null
     * exam_Step_Is_Over : 0
     * exam_Step_Is_Over_Str : null
     * emp_Id : 163
     * emp_Name : null
     * account_Id : 163
     * account_Name : null
     * money_Sum : null
     * approval_Man : null
     * search_Begin : null
     * search_End : null
     * details : [{"detail_Id":98,"apply_Id":65,"spend_Type_Str":"其他","spend_Type":15,"cust_Id":null,"money_Amount":999,"bill_Num":0,"spend_Begin":1481990400000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":102,"detail_Id":98,"pic_Url":"F://expensePics/9/7/20161212150613com_qihoo_appstore.png","pic_Desc":null}],"pic_Ids":null},{"detail_Id":97,"apply_Id":65,"spend_Type_Str":"补助","spend_Type":8,"cust_Id":null,"money_Amount":888,"bill_Num":0,"spend_Begin":1481472000000,"spend_Begin_Str":null,"spend_End":null,"spend_End_Str":null,"expense_Date":null,"spend_City":null,"detail_Memo":null,"trId":null,"icon":null,"emp_Id":null,"pics":[{"id":101,"detail_Id":97,"pic_Url":"F://expensePics/7/15/20161212150554com_zzti_fengongge_imagepickerdemo.png","pic_Desc":null}],"pic_Ids":null}]
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
        private static final long serialVersionUID = -5053772334836079991L;
        private String apply_Id;
        private int cust_Id;
        private String memo;
        private int type;
        private String type_Str;
        private String title;
        private int group_Id;
        private String group_Name;
        private String apply_Date;
        private String apply_Date_Str;
        private long edit_Time;
        private int exam_End_Is;
        private String exam_End_Is_Str;
        private int exam_Step_Is_Over;
        private String exam_Step_Is_Over_Str;
        private int emp_Id;
        private String emp_Name;
        private String account_Id;
        private String account_Name;
        private String money_Sum;
        private String approval_Man;
        private String search_Begin;
        private String search_End;
        /**
         * detail_Id : 98
         * apply_Id : 65
         * spend_Type_Str : 其他
         * spend_Type : 15
         * cust_Id : null
         * money_Amount : 999
         * bill_Num : 0
         * spend_Begin : 1481990400000
         * spend_Begin_Str : null
         * spend_End : null
         * spend_End_Str : null
         * expense_Date : null
         * spend_City : null
         * detail_Memo : null
         * trId : null
         * icon : null
         * emp_Id : null
         * pics : [{"id":102,"detail_Id":98,"pic_Url":"F://expensePics/9/7/20161212150613com_qihoo_appstore.png","pic_Desc":null}]
         * pic_Ids : null
         */

        private List<DetailsBean> details;

        public String getApply_Id() {
            return apply_Id;
        }

        public void setApply_Id(String apply_Id) {
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

        public String getApply_Date() {
            return  apply_Date;
        }

        public void setApply_Date(String apply_Date) {
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

        public void setExam_End_Is(int exam_End_Is) {
            this.exam_End_Is = exam_End_Is;
        }

        public String getExam_End_Is_Str() {
            return exam_End_Is_Str;
        }

        public void setExam_End_Is_Str(String exam_End_Is_Str) {
            this.exam_End_Is_Str = exam_End_Is_Str;
        }

        public int getExam_End_Is() {
            return exam_End_Is;
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

        public String getAccount_Id() {
            return account_Id == null ? "" : account_Id;
        }

        public void setAccount_Id(String account_Id) {
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

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class DetailsBean implements  Serializable{
            private static final long serialVersionUID = -9191541444116091919L;
            private int detail_Id;
            private int apply_Id;
            private String spend_Type_Str;
            private int spend_Type;
            private String cust_Id;
            private String money_Amount;
            private int bill_Num;
            private String spend_Begin;
            private String spend_Begin_Str;
            private String spend_End;
            private String spend_End_Str;
            private String expense_Date;
            private String spend_City;
            private String detail_Memo;
            private String trId;
            private String icon;
            private String emp_Id;
            private String pic_Ids;
            /**
             * id : 102
             * detail_Id : 98
             * pic_Url : F://expensePics/9/7/20161212150613com_qihoo_appstore.png
             * pic_Desc : null
             */

            private List<PicsBean> pics;

            public int getDetail_Id() {
                return detail_Id;
            }

            public void setDetail_Id(int detail_Id) {
                this.detail_Id = detail_Id;
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

            public String getCust_Id() {
                return cust_Id;
            }

            public void setCust_Id(String cust_Id) {
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

            public String getSpend_Begin_Str() {
                return spend_Begin_Str;
            }

            public void setSpend_Begin_Str(String spend_Begin_Str) {
                this.spend_Begin_Str = spend_Begin_Str;
            }

            public String getSpend_End() {
                return spend_End;
            }

            public void setSpend_End(String spend_End) {
                this.spend_End = spend_End;
            }

            public String getSpend_End_Str() {
                return spend_End_Str;
            }

            public void setSpend_End_Str(String spend_End_Str) {
                this.spend_End_Str = spend_End_Str;
            }

            public String getExpense_Date() {
                return expense_Date;
            }

            public void setExpense_Date(String expense_Date) {
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

            public String getTrId() {
                return trId;
            }

            public void setTrId(String trId) {
                this.trId = trId;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getEmp_Id() {
                return emp_Id;
            }

            public void setEmp_Id(String emp_Id) {
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

            public static class PicsBean implements Serializable {
                private static final long serialVersionUID = 6771665622575531854L;
                private int id;
                private int detail_Id;
                private String pic_Url;
                private String pic_Desc;

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
            }
        }
    }
}
