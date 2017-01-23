package com.fesco.bookpay.impl;

import java.util.List;

/**
 * Created by gong.min on 2017/1/8.
 */
public interface PermissionListenter {

    void  onGranted();

    void  onDenied(List<String> denied);
}
