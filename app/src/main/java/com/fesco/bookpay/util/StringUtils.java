package com.fesco.bookpay.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by gong.min on 2016/9/5.
 */
public class  StringUtils {



    /**
     * 截取接口方法名
     */
    public static String strSub(String path) {

        path = path.substring(path.lastIndexOf("ll/") + 3);

        return path;
    }

    /**
     * Sign多个参数（首字母排序） 链接配置
     */

    public static String strSign(int length, String method, String secret, String custId, String empId) {

        String[] s = new String[]{method, secret, empId, custId};
        s = stringSort(s);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
        }
        return sb.toString();
    }

    /**
     * Sign   多个参数String[] a,b合并（首字母排序） 链接配置
     */

    public static String strSignCombine(int length, String method, String secret,String b[]) {

        //  String[] a = new String[]{method, secret, empId, custId};
        String[] a = new String[]{method, secret};
        String[ ] result=new String[a.length+b.length];
        System.arraycopy(a,0,result,0,a.length);
        System.arraycopy(b,0,result,a.length,b.length);

        result = stringSort(result);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i]);
        }
        return sb.toString();
    }






    public static String[] stringSort(String[] s) {
        List<String> list = new ArrayList<String>(s.length);
        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }
        Collections.sort(list);
        return list.toArray(s);
    }


    public static boolean isEmpty(String param) {
        if (param != null && param.length() > 0) {
            return true;
        } else return false;
    }

    public static boolean isEmpty(String param, String paramTwo) {
        if (param != null && param.length() > 0 && paramTwo != null) {
            return true;
        } else
            return false;

    }

    public static boolean isEmpty(String param, String paramTwo, String paramThree) {
        if (param != null && param.length() > 0 && paramTwo != null && paramTwo.length() > 0 && paramThree != null && paramThree.length() > 0) {
            return true;
        } else
            return false;

    }
    public static boolean isThisTime(long time,String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
