package com.fesco.bookpay.entity;

/**
 * author zaaach on 2016/1/26.
 */
public class CityPickerBean {
    private String name;
    private String pinyin;

    public CityPickerBean() {}

    public CityPickerBean(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
