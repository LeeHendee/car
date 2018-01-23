package com.example.gtercn.car.mall.entity;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/23.
 * Used to :
 */

public class CommitOrderResultEntity {


    /**
     * err_code : 0
     * err_message : OK
     * result : {"orderId":"6c51b7f7c1d1485db3f6c3ee14c58da2"}
     * message : 结算中
     */

    private String err_code;
    private String err_message;
    private ResultBean result;
    private String message;

    @Override
    public String toString() {
        return "CommitOrderResultEntity{" +
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
        /**
         * orderId : 6c51b7f7c1d1485db3f6c3ee14c58da2
         */

        private String orderId;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "orderId='" + orderId + '\'' +
                    '}';
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }
}
