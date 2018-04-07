package com.example.gtercn.car.mall.entity;

/**
 * Created by Yan on 2018/1/8.
 */

public class ResultEntity {
    /**
     * err_code : 0
     * err_message : OK
     * result : {}
     * message : 添加成功
     */

    private String err_code;
    private String err_message;
    private ResultBean result;
    private String message;

    @Override
    public String toString() {
        return "ResultEntity{" +
                "err_code='" + err_code + '\'' +
                ", err_message='" + err_message + '\'' +
                ", result=" + result +
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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean {
    }

}
