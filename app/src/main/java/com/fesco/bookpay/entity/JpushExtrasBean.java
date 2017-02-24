package com.fesco.bookpay.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gong.min on 2017/2/22.
 */
public class JpushExtrasBean implements Parcelable {

    /**
     * jumpTo : xxx
     */

    private ExtrasBean extras;

    protected JpushExtrasBean(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<JpushExtrasBean> CREATOR = new Creator<JpushExtrasBean>() {
        @Override
        public JpushExtrasBean createFromParcel(Parcel in) {
            return new JpushExtrasBean(in);
        }

        @Override
        public JpushExtrasBean[] newArray(int size) {
            return new JpushExtrasBean[size];
        }
    };

    public ExtrasBean getExtras() {
        return extras;
    }

    public void setExtras(ExtrasBean extras) {
        this.extras = extras;
    }

    public static class ExtrasBean {
        private String jumpTo;

        public String getJumpTo() {
            return jumpTo;
        }

        public void setJumpTo(String jumpTo) {
            this.jumpTo = jumpTo;
        }
    }



}
