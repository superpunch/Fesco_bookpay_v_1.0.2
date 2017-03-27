package com.fesco.bookpay.util;

/**
 * Created by gong.min on 2016/8/31.
 */
public class HttpUtil {
    // http://11.0.162.82:8080/payroll/expense/loadAddApply.json  报销接口
    //
    public  static  String  updateApkUrl="https://www.payrollpen.com/payroll/app.apk";


    private  static String baseUrl="https://www.payrollpen.com";


     // private  static String baseUrl="https://10.0.17.234:8443";

    // public static String baseRetrofitUrl = baseUrl+"/payroll/";
    public static String baseRetrofitUrl = baseUrl+"/payroll/";


    public  static  String  getAppStore=baseUrl+"/payroll/appStore/getAppStore.json";





    public static String getEmpsPhotos = baseUrl+"/payroll/emp/getEmpsPhotos.json";//获取通讯录头像

    //  @"token":tokenkey
    public static String tokenKey = baseUrl+"/payroll/getNewToken.json";//获取最新token值
    public static String login_path = baseUrl+"/payroll/login.json";
    public static String getMenuPath = baseUrl+"/payroll/getMenu.json";//首页URl信息
    public static String getAllPhoneNumber = baseUrl+"/payroll/emp/getAllPhoneNumber.json";//通讯录
    public static String loadEmpInfo = baseUrl+"/payroll/emp/loadEmpInfo.json";//个人信息
    public static String updateEmpInfo = baseUrl+"/payroll/emp/updateEmpInfo.json";//员工信息修改
    public static String validatePswd = baseUrl+"/payroll/user/validatePswd.json";//校验旧密码
    public static String modifyPswd = baseUrl+"/payroll/user/modifyPswd.json";//保存新密码
    public static String preRegister = baseUrl+"/payroll/user/preRegister.json";//发送验证码
    public static String preReset = baseUrl+"/payroll/user/preReset.json";//忘记密码时发送验证码
    public static String register = baseUrl+"/payroll/user/register.json";//注册
    public static String reset = baseUrl+"/payroll/user/reset.json";//重置密码


/**************考勤模块***********************/
    /**
     * 1.签到/签退/外勤
     * 方法：/kq/sign.json
     * 参数：cust_Id,emp_Id,longitude,latitude,type,memo（type=1为签到，2为签退，3为外勤）
     * 2 .签到列表
     * 方法：/kq/getCedList.json
     * 参数：emp_Id,pageNum页码,pageSize每页条数
     * 3.补签申请页面 获取有审批权限的员工及默认审批人
     * 方法：/kq/getApprovalMans.json
     * 参数：emp_Id,cust_Id
     * 4.保存补签申请
     * 方法：/kq/saveSignLater.json
     * 参数：emp_Id,cust_Id,check_Type,cust_Addr,check_Time(String),memo,approval_Man(long)
     * 5.获取自己的补签列表
     * 方法：/kq/getSignLaterList.json
     * 参数：emp_Id,cust_Id
     */
    public static String sign = baseUrl+"/payroll/kq/sign.json"; // 签到/签退/外勤
    public static String getCedList = baseUrl+"/payroll/kq/getCedList.json"; // 签到列表
    public static String getApprovalMans = baseUrl+"/payroll/kq/getApprovalMans.json"; // 补签申请页面
    public static String saveSignLater = baseUrl+"/payroll/kq/saveSignLater.json"; // 保存补签申请
    public static String getSignLaterList = baseUrl+"/payroll/kq/getSignLaterList.json"; // 自己的补签列表

    /**************加班模块*********************/
    /**
     * time_Unit 单位   1 天 , 2 小时, 3 半天
     * 4. 加班申请页面加载：{'methodname':'kq/workApply.json','cust_Id':'','emp_Id':''}
     * 5. 加班申请保存：{'methodname':'kq/saveWorkApply.json','cust_Id':'','emp_Id':'','time_Unit':'','work_Duration':'','begin_Time':'','end_Time':'','reason':'','approval_Man':''}
     * 6. 本人加班列表：{'methodname':'kq/getEmpWorkList.json','cust_Id':'','emp_Id':''}
     *********************/


    public static String workApply = baseUrl+"/payroll/kq/workApply.json"; // 加班申请
    public static String saveWorkApply = baseUrl+"/payroll/kq/saveWorkApply.json"; // 加班申请保存即提交
    public static String getEmpWorkList = baseUrl+"/payroll/kq/getEmpWorkList.json"; // 加班列表


    /**************请假模块*********************/
    /**
     * 4. 本人请假列表：{'methodname':'kq/getEmpHol.json','emp_Id':'','cust_Id':''}
     * 8. 请假申请页面加载：{'methodname':'kq/holApply.json','cust_Id':'','emp_Id':''}
     * 9. 请假申请保存：{'methodname':'kq/saveHolApply.json','emp_Id':'','cust_Id':'','hol_Set_Id':'','hol_Begin':'','hol_End':'','hol_Begin_Apm':'','hol_End_Apm':'','hol_Num':'','momo':'','approval_Man':'','hol_Unit':''}
     * 10.删除请假申请（仅限当月）：{'methodname':'kq/delHolApply.json','cust_Id':'','holEmpExamId':''}
     *********************/
    public static String holApply = baseUrl+"/payroll/kq/holApply.json"; // 请假申请页面加载

    public static String getEmpHol = baseUrl+"/payroll/kq/getEmpHol.json"; // 请假申请列表
    public static String saveHolApply = baseUrl+"/payroll/kq/saveHolApply.json"; // 请假申请保存
    public static String delHolApply = baseUrl+"/payroll/kq/delHolApply.json"; // 删除请假申请


    /**************审批模块*********************/
    /**
     * is_Pass、 type : 0 不通过 , 1 通过
     *1. 加班审批列表：{'methodname':'kq/workExamList.json','emp_Id':''}
     *2. 加班审批信息：{'methodname':'kq/getExtraWorkApply.json','emp_Id':'','cust_Id':'','apply_Id':''}
     *3. 加班审批保存：{'methodname':'kq/saveExtraWorkExamStep.json','emp_Id':'','cust_Id':'','apply_Id':'','is_Pass':'','next_Approval_Man':'','memo':''}
     */
    public static String workExamList = baseUrl+"/payroll/kq/workExamList.json"; // 加班审批列表
    public static String getExtraWorkApply = baseUrl+"/payroll/kq/getExtraWorkApply.json"; // 加班审批信息
    public static String saveExtraWorkExamStep = baseUrl+"/payroll/kq/saveExtraWorkExamStep.json"; // 加班审批保存

    /**
     *1. 补签审批列表：{'methodname':'kq/signLaterExamList.json','emp_Id':''}
     *2. 补签审批信息：{'methodname':'kq/getSignLaterExamInfo.json','emp_Id':'','cust_Id':'','apply_Id':''}
     *3. 补签审批保存：{'methodname':'kq/saveSignLaterExamStep.json','emp_Id':'','cust_Id':'','apply_Id':'','is_Pass':'','next_Approval_Man':'','memo':''}
     */
    public static String signLaterExamList = baseUrl+"/payroll/kq/signLaterExamList.json"; // 补签审批列表
    public static String getSignLaterExamInfo = baseUrl+"/payroll/kq/getSignLaterExamInfo.json"; // 补签审批信息
    public static String saveSignLaterExamStep = baseUrl+"/payroll/kq/saveSignLaterExamStep.json"; // 补签审批保存

    /**
     *5. 请假审批列表：{'methodname':'kq/holExamList.json','emp_Id':''}
     *6. 请假审批信息：{'methodname':'kq/getHolEmpExam.json','emp_Id':'','cust_Id':'','holEmpExamId':''}
     *7. 请假审批保存：{'methodname':'kq/saveHolEmpExamStep.json','emp_Id':'','cust_Id':'','holEmpExamId':'','type':'','next_Approval_Man':'','momo':''}
     */
    public static String holExamList = baseUrl+"/payroll/kq/holExamList.json"; // 请假审批列表
    public static String getHolEmpExam = baseUrl+"/payroll/kq/getHolEmpExam.json"; // 请假审批信息
    public static String saveHolEmpExamStep = baseUrl+"/payroll/kq/saveHolEmpExamStep.json"; // 请假审批保存

    /**
     * 加载报销审批列表  {'methodname':'expense/getExpenseExamList.json','emp_Id':''}
     * 加载报销审批信息 {'methodname':'expense/loadExpenseExamInfo.json','cust_Id':'','apply_Id':''}
     * //保存审批结果    {'methodname':'expense/saveExpenseExamResult.json','result':'','apply_Id':''}
     * result：0=未通过，1=通过
     * 报销审批接口换成这个 {'methodname':'expense/saveExpenseExamResult.json',"
     + "'result':'','memo':'','next_Approval_Man':'','apply_Id':'','emp_Id':''}
     *
     */
    public static String getExpenseExamList = baseUrl+"/payroll/expense/getExpenseExamList.json"; // 加载报销审批列表
    public static String loadExpenseExamInfo = baseUrl+"/payroll/expense/loadExpenseExamInfo.json"; // 加载报销审批信息
    public static String loadExpenseExamInfox = "http://11.0.161.15:8080/payroll/expense/loadExpenseExamInfox.json"; // 加载报销审批信息 -简洁数据
    public static String loadExpenseExamInfoForEmp = baseUrl+"/payroll/expense/loadExpenseExamInfoForEmp.json"; // 加载报销审批信息


    public static String saveExpenseExamResult = baseUrl+"/payroll/expense/saveExpenseExamResult.json"; // 保存审批结果


    public static String getLastCheckMan = baseUrl+"/payroll/expense/getLastCheckMan.json"; // 选择审批人接口




    /**************签到统计模块*********************/
    /**
     *1. ​打卡记录
     *{'methodname':'kq/getCheckDetailForEmp.json','emp_Id':'','dateStr':''}
     * dateStr例：20161023
     * 2. 假期剩余：
     *{'methodname':'kq/getRestHolidays.json','emp_Id':''}
     *员工个人考勤统计：
     *{'methodname':'kq/getCheckListForEmp.json','cust_Id':'','emp_Id':'','year':'','month':''}
     *返回值说明：有几天就返回几个数组；
     *normal=正常，lateArrive=迟到，earlyLeave=早退，offWork=旷工，holiday=请假，extraWork=加班；
     *可能存在一天好几个状态的情况，颜色分块显示
     */
    public static String getCheckDetailForEmp = baseUrl+"/payroll/kq/getCheckDetailForEmp.json"; // ​打卡记录
    public static String getRestHolidays = baseUrl+"/payroll/kq/getRestHolidays.json"; // ​假期剩余
    public static String getCheckListForEmp = baseUrl+"/payroll/kq/getCheckListForEmp.json"; // ​员工个人考勤统计
    /**************排行模块*********************/
    /**
     *迟到排行：
     *{'methodname':'kq/getCedRanking.json','cust_Id':''}
     *加班排行：
     *{'methodname':'kq/getWorkRanking.json','cust_Id':''}
     */
    public static String getCedRanking = baseUrl+"/payroll/kq/getCedRanking.json";
    public static String getWorkRanking = baseUrl+"/payroll/kq/getWorkRanking.json";

    /**************报销模块*********************/
    /**
     1. 加载报销列表
     {'methodname':'expense/getExpenseApplyList.json','emp_Id':''}
     2. 加载消费记录
     {'methodname':'expense/getExpenseRecords.json','emp_Id':''}
     3. 加载编辑报销单
     {'methodname':'expense/loadAddApply.json','emp_Id':'','cust_Id':''}
     d.读取上次 信息
     {'methodname':'expense/loadEditApply.json','emp_Id':'','cust_Id':'','apply_Id':''}
     4. 保存消费记录
     {'methodname':'expense/saveExpenseRecord.json','emp_Id':'','spend_Type':'','money_Amount':'','bill_Num':'',
     'detail_Memo':'','pic_Ids':'','spend_Begin':'',
     'spend_End':'','spend_City':''}
     5. 保存报销申请
     {'methodname':'expense/saveExpenseApply.json','emp_Id':'','cust_Id':'','apply':'','details':''}
     apply和details参数拼成JSONArray数组[]，不行再作调整。
     6. 提交报销申请
     {'methodname':'expense/submitExpenseApply.json',
     'emp_Id':'','cust_Id':'','apply':'','details':'','approval_Man':''}
     删除报销申请：
     {'methodname':'expense/deleteApply.json','apply_Id':''}
     删除消费记录：
     {'methodname':'expense/deleteRecord.json','detail_Id':''}
     上传图片
     expense/uploadPic.json
     参数：request
     */

    public static String getExpenseApplyList = baseUrl+"/payroll/expense/getExpenseApplyList.json"; //加载报销列表
    public static String getExpenseRecords = baseUrl+"/payroll/expense/getExpenseRecords.json"; //加载消费记录
    //   public static String loadAddApply = baseUrl+"/payroll/expense/loadAddApply.json"; //加载编辑报销单
    public static String loadAddApply = "http://11.0.161.15:8080/payroll/expense/loadAddApply.json"; //加载编辑报销单
    public static String loadEditApply = baseUrl+"/payroll/expense/loadEditApply.json"; //加载报销单信息


    public static String loadNewExpenseExamInfo = baseUrl+"/payroll/expense/loadNewExpenseExamInfo.json"; //驳回后再次编辑报销单信息

    public static String saveExpenseRecord = baseUrl+"/payroll/expense/saveExpenseRecord.json"; //保存消费记录
    //  public static String saveExpenseApply = "http://11.0.162.82:8080/payroll/expense/saveExpenseApply.json"; //保存报销申请
    public static String saveExpenseApply = baseUrl+"/payroll/expense/saveExpenseApply.json"; //保存报销申请


    public static String submitExpenseApply = baseUrl+"/payroll/expense/submitExpenseApply.json"; //提交报销申请
    public static String deleteApply = baseUrl+"/payroll/expense/deleteApply.json"; //删除报销申请
    public static String deleteRecord = baseUrl+"/payroll/expense/deleteRecord.json"; //删除消费记录


    public static String uploadPicTEST = "http://11.0.161.15/payroll/emp//uploadPic.json";

    public static String uploadPic = baseUrl+"/payroll/expense/uploadPic.json"; //上传图片
    public static String showPicture = baseUrl+"/payroll/emp/showPicture.json"; //下载图片
    //{'methodname':'expense/getPicStream.json','pic_Id':''}  获取报销图片

    public static String getPicStream = baseUrl+"/payroll/expense/getPicStream.json"; //

    // 删除凭证接口{'methodname':'expense/deletePic.json','pic_Id':''}
    public static String deletePic = baseUrl+"/payroll/expense/deletePic.json"; // 删除图片接口





}
