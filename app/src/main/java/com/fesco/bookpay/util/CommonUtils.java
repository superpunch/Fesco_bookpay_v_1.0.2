package com.fesco.bookpay.util;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.entity.approvalbean.BillDetailBean;
import com.fesco.bookpay.entity.approvalbean.CheckDetailBean;
import com.fesco.bookpay.entity.approvalbean.OverDetailBean;
import com.fesco.bookpay.entity.approvalbean.RestDetailBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by gong.min on 2016/10/17.
 */
public class CommonUtils {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    public static Date nowTime = new Date(System.currentTimeMillis());
    public  static Calendar calendar = Calendar.getInstance();

    public static void getMessageResult(int count, String jsonObject, Activity mContext) {

        JSONObject object = null;
        try {
            object = new JSONObject(jsonObject.toString());
            String message = object.getString("message");
            if ("success".equals(message)) {
                if (1 == count) {
                    AppToast.showShortText(mContext, "审批成功");
                    mContext.finish();
                } else
                    AppToast.showShortText(mContext, "驳回成功");
                mContext.finish();
            } else {
                AppToast.showShortText(mContext, message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static void setLastApprovalResult(View layout_lastapproval, OverDetailBean.LastApprovalStepBean overLast, CheckDetailBean.LastApprovalStepBean checkLast, RestDetailBean.LastApprovalStepBean restLast) {
        layout_lastapproval.setVisibility(View.VISIBLE);
        TextView lastPatch = (TextView) layout_lastapproval.findViewById(R.id.restdetail_last);
        TextView lastResult = (TextView) layout_lastapproval.findViewById(R.id.restdetail_result);
        TextView lastOpinion = (TextView) layout_lastapproval.findViewById(R.id.restdetail_opinion);
        if (overLast != null) {
            lastPatch.setText(sdf.format(overLast.getApproval_Time()));
            if (1 == overLast.getIs_Pass()) {
                lastResult.setText("同意");
            } else lastResult.setText("驳回");
            if (overLast.getMemo() != null) {
                lastOpinion.setText(overLast.getMemo().toString());
            }
        } else if (checkLast != null) {
            lastPatch.setText(sdf.format(checkLast.getApproval_Time()));
            if (1 == checkLast.getIs_Pass()) {
                lastResult.setText("同意");
            } else lastResult.setText("驳回");
            if (checkLast.getMemo() != null) {
                lastOpinion.setText(checkLast.getMemo().toString());
            }
        } else if (restLast != null) {
            lastPatch.setText(sdf.format(restLast.getApproval_Time()));
            if (1 == restLast.getIs_Pass()) {
                lastResult.setText("同意");
            } else lastResult.setText("驳回");
            if (restLast.getMemo() != null) {
                lastOpinion.setText(restLast.getMemo().toString());
            }
        }

    }

    public static void setLastApprovalResult(View layout_lastapproval,  BillDetailBean.LastApprovalStepBean billBean) {
        layout_lastapproval.setVisibility(View.VISIBLE);
        TextView lastPatch = (TextView) layout_lastapproval.findViewById(R.id.restdetail_last);
        TextView lastResult = (TextView) layout_lastapproval.findViewById(R.id.restdetail_result);
        TextView lastOpinion = (TextView) layout_lastapproval.findViewById(R.id.restdetail_opinion);
        if (billBean != null) {
            if(!TextUtils.isEmpty(billBean.getApproval_Time())){
                lastPatch.setText(billBean.getApproval_Man_Str()+" 于 "+ConversionUtil.getLongDateTime(billBean.getApproval_Time()));
            }

                lastResult.setText(billBean.getIs_Pass_Str());
                lastOpinion.setText(billBean.getMemo());
        }

    }

    public static String currentTime() {
        String startTime = sdf.format(nowTime);
        return startTime;
    }

    public static long millisecond(String date1) {
        Date start = null;
        try {
              start = sdfDay.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start.getTime();
    }



    //判断是否超过24小时
    public static boolean isCompare(String date1, String date2) {
        Date start = null;
        Date end = null;
        try {
            start = sdf.parse(date1);
            end = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long cha = end.getTime() - start.getTime();
        double result = cha * 1.0 / (1000 * 60 * 60);
        if (result <= 24) {
            //System.out.println("可用");
            return true;
        } else {
            //System.out.println("已过期");
            return false;
        }
    }

}
