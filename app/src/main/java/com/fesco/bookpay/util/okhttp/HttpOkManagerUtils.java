package com.fesco.bookpay.util.okhttp;

import com.fesco.bookpay.util.ConversionUtil;
import com.fesco.bookpay.util.StringUtils;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

/**
 * Created by gong.min on 2016/9/13.
 */
public class HttpOkManagerUtils {


    /**
     * 只有 cust_Id，emp_Id 两者参数
     *
     * @param path
     * @param cust_Id
     * @param emp_Id
     * @param token
     * @return
     */
    public static HashMap<String, String> okManagerPost(String path, String cust_Id, String emp_Id, String token) {
        String sign = "";
        String jsonParam = "";
        String method = StringUtils.strSub(path);//接口方法名
        if (cust_Id != null && emp_Id != null && cust_Id.length() > 0 && emp_Id.length() > 0) {
            sign = StringUtils.strSign(4, "methodname" + method, "secretappsecret", "cust_Id", "emp_Id");
            jsonParam = "{\"methodname\":\"" + method + "\",\"cust_Id\":\"" + cust_Id + "\",\"emp_Id\":\"" + emp_Id + "\"}";
        } else if (cust_Id != null && emp_Id == null) {
            sign = StringUtils.strSign(4, "methodname" + method, "secretappsecret", "cust_Id", "");
            jsonParam = "{\"methodname\":\"" + method + "\",\"cust_Id\":\"" + cust_Id + "\"}";
        } else if (cust_Id == null && emp_Id != null) {
            sign = StringUtils.strSign(4, "methodname" + method, "secretappsecret", "", "emp_Id");
            jsonParam = "{\"methodname\":\"" + method + "\",\"emp_Id\":\"" + emp_Id + "\"}";
        }
        Logger.e(sign);
        sign = ConversionUtil.md5Case(sign);
        Logger.i(sign);
        Logger.i(jsonParam);
        Logger.i(token);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("sign", sign);
        map.put("jsonParam", jsonParam);
        map.put("tokenkey", token);

        return map;

    }


    /**
     * 封装修改密码网络请求
     *
     * @param path
     * @param paramPwsd 密码key
     * @param password  密码value
     * @param emp_Id
     * @param token
     * @return
     */
    public static HashMap<String, String> okManagerPostPwsd(String path, String paramPwsd, String password, String emp_Id, String token) {
        String sign = "";
        String jsonParam = "";
        String method = StringUtils.strSub(path);//接口方法名
        if (password != null && emp_Id != null && password.length() > 0 && emp_Id.length() > 0) {
            sign = StringUtils.strSign(4, "methodname" + method, "secretappsecret", paramPwsd, "emp_Id");
            jsonParam = "{\"methodname\":\"" + method + "\",\"" + paramPwsd + "\":\"" + password + "\",\"emp_Id\":\"" + emp_Id + "\"}";
            Logger.e(sign);
            sign = ConversionUtil.md5Case(sign);
            Logger.i(sign);
            //     jsonParam="{\"methodname\":\"user/validatePswd.json\",\"oldPswd\":\"songhu\",\"emp_Id\":\"163\"}";
            Logger.i(jsonParam);

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("sign", sign);
            map.put("jsonParam", jsonParam);
            map.put("tokenkey", token);

            return map;
        }


        return null;

    }

    /**
     * 拼接 sign 对应的key(String) ,methodname 对应的 key与value(值)连接的 sbb字符串
     *
     * @param path
     * @param key   String[] key = new String[]{"emp_Id", "emp_Name", "gender", "phone", "mobile", "weixinid", "email", "address", "zipcode"};
     * @param value String[] value = new String[]{emp_Id + "", emp_Name, gender + "", phone, mobile, weixinid, email, address, zipcode};
     * @param token
     * @return
     */
    public static HashMap<String, String> updateEmpInfoPost(String path, String[] key, String[] value, String token) {

        String sign = "";
        String jsonParam = "";
        String method = StringUtils.strSub(path);//接口方法名

        //     sign = StringUtils.strSign(4, "methodname" + method, "secretappsecret",paramPwsd, "emp_Id");
        sign = StringUtils.strSignCombine(4, "methodname" + method, "secretappsecret", key);

        String sbj=   StringBufferJson(key,value);



        jsonParam = "{\"methodname\":\"" + method + "\"," + sbj + "}";
     //   jsonParam=  "{\"emp_Id\":\"" + method + "\" }";
        Logger.e(sign);
        sign = ConversionUtil.md5Case(sign);
        Logger.i(sign);
        Logger.i(token);
        //     jsonParam="{\"methodname\":\"user/validatePswd.json\",\"oldPswd\":\"songhu\",\"emp_Id\":\"163\"}";
        Logger.i(jsonParam);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("sign", sign);
        map.put("jsonParam", jsonParam);
        map.put("tokenkey", token);

        return map;


    }






    public static String StringBufferJson(String[] key, String[] value) {
        StringBuffer sbb = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < value.length; i++) {
            sbb = sb.append("\"" + key[i] + "\":\"" + value[i] + "\"");
            if (i != (value.length - 1)) {
                sbb.append(",");
            }
        }
        return String.valueOf(sbb);
    }

    public static String StringBufferListJson(String[] key, String[] value) {
        StringBuffer sbb = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < value.length; i++) {
            if("apply".equals(key[i]) || "details".equals(key[i])){
                sbb = sb.append("\"" + key[i] + "\":" + value[i] );
            }else
            sbb = sb.append("\"" + key[i] + "\":\"" + value[i] + "\"");
            if (i != (value.length - 1)) {
                sbb.append(",");
            }
        }
        return String.valueOf(sbb);
    }
    public static HashMap<String, String> updateHashMapPost(String path, String[] key, String[] value, String token) {

        String sign = "";
        String jsonParam = "";
        String method = StringUtils.strSub(path);//接口方法名

        //     sign = StringUtils.strSign(4, "methodname" + method, "secretappsecret",paramPwsd, "emp_Id");
        sign = StringUtils.strSignCombine(4, "methodname" + method, "secretappsecret", key);

        String sbj=   StringBufferListJson(key,value);

        jsonParam = "{\"methodname\":\"" + method + "\"," + sbj + "}";
        Logger.e(sign);
        sign = ConversionUtil.md5Case(sign);
        Logger.i(sign);
        Logger.i(token);
        Logger.i(jsonParam);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("sign", sign);
        map.put("jsonParam", jsonParam);
        map.put("tokenkey", token);

        return map;


    }










}
