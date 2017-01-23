package com.fesco.bookpay.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gong.min on 2016/8/31.
 */
public class ConversionUtil {
    public static double pi = 3.141592653589793 * 3000.0 / 180.0;
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat formatYear = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat formatYh = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
    public static final SimpleDateFormat formatHm = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static final  Date apply_Date = new Date( );
    private  static String  md5_32="";

    public static String getTimeHour(Date date) {
        return formatHm.format(date);
    }

    public static String getTime(Date date) {
        return format.format(date);
    }

    // long 转 String     例 1481472000000 -"2014-12-12"
    public static String getLongDateTime(String str) {
        Date apply_Date = new Date(Long.parseLong(str) );
        return format.format(apply_Date);
    }
    public static String getLongDateTime(long str) {
        Date apply_Date = new Date(str);
        return format.format(apply_Date);
    }
    public static String getLongYearTime(long str) {
        Date apply_Date = new Date(str);
        return formatYear.format(apply_Date);
    }
    public static String getLongDateTimeHm(long str) {
        Date apply_Date = new Date(str);
        return formatHm.format(apply_Date);
    }
    public static String getLongYearHour(String str) {
        Date apply_Date = new Date(Long.parseLong(str));
        return formatYh.format(apply_Date);
    }
    public static String getLongYearHour(long str) {
        Date apply_Date = new Date(str);
        return formatYh.format(apply_Date);
    }


    /**
     * md 5
     *
     * @param sourceStr
     * @return*/
    public static String  md5Case(String sourceStr){
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
           md5_32=result;
          //  md5_16= buf.toString().substring(8, 24);
            return  md5_32.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }


    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法   将 BD-09 坐标转换成GCJ-02 坐标
     *
     * @param bd_lon
     * @param bd_lat
     * @return
     */
    public static Gps bd09_To_Gcj02(double bd_lon, double bd_lat) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return new Gps(gg_lon, gg_lat);
    }


    //Gps类
    public static class Gps {

        public double lat;
        public double lon;

        public Gps(double lon, double lat) {
            this.lat = lat;
            this.lon = lon;
        }

        public void print() {
            System.out.println(this.lon + "," + this.lat);
        }
    }




}
