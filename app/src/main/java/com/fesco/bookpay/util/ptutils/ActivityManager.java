package com.fesco.bookpay.util.ptutils;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KCrason on 2016/6/7.
 */
public class ActivityManager {

    private static ActivityManager activityManage;
    private List<Activity> activities = new ArrayList<>();

    public static synchronized ActivityManager getInstance() {
        if (activityManage == null) {
            activityManage = new ActivityManager();
        }
        return activityManage;
    }

    public int getActivityCount() {
        return activities.size();
    }
 int  k=0;
    public void addActivity(Activity activity) {
        if(!activities.contains(activity)){
            activities.add(activity);
            Log.e("Fragment",++k+"mActivity----:  "+activity.hashCode());
        }






    }

    public void removeTopActivity() {
        if (getActivityCount() >= 1) {
            activities.remove(getActivityCount() - 1);
        }
    }

    public void finishActivitys() {
        for (int i = 0; i < getActivityCount(); i++) {
            activities.get(i).finish();
            Log.e("Fragment","mActivity----:  "+i);

        }
        activities.clear();
    }
}
