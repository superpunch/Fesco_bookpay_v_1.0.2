package com.fesco.bookpay.entity.approvalbean;

/**
 * Created by gong.min on 2016/10/18.
 */
public class ValidateCode {


    /**
     * ValidateCode : 200966
     * message : already exist
     */

    private int ValidateCode;
    private String message;

    public int getValidateCode() {
        return ValidateCode;
    }

    public void setValidateCode(int ValidateCode) {
        this.ValidateCode = ValidateCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
