package com.example.gtercn.car.mall.entity;

/**
 * Created by Yan on 2018/1/8.
 */

public class ResultEntity {


    /**
     * err_code : 0
     * err_message : OK
     * message : 更改成功
     */

    private String err_code;
    private String err_message;
    private String message;

    @Override
    public String toString() {
        return "ResultEntity{" +
                "err_code='" + err_code + '\'' +
                ", err_message='" + err_message + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_message() {
        return err_message;
    }

    public void setErr_message(String err_message) {
        this.err_message = err_message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
