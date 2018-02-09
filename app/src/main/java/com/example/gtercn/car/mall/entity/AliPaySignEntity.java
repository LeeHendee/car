package com.example.gtercn.car.mall.entity;

/**
 * Author ：LeeHang
 * CreateTime ：2018/2/8.
 * Used to :
 */

public class AliPaySignEntity {

    /**
     * err_code : 0
     * err_message : OK
     * result : {"key":"partner=\"2088921700888610\"&seller_id=\"m13941850400@163.com\"&out_trade_no=\"GB77E1518067190997\"&subject=\"车驿站订单\"&body=\"车驿站订单\"&total_fee=\"1289.00\"&service=\"mobile.securitypay.pay\"&_input_charset=\"UTF-8\"&notify_url=\"http://114.215.71.170/car_inn/v1/ali/notifyurl\"&payment_type=\"1\"&app_id=\" 2018011901977798\"&it_b_pay=\"5m\"&sign=\"iwQnSCYbs7KKQgdeNH666lESGz8%2FKQJKM6nkrRwBNdThg61HjPHSWisny6kaGFOYsS%2FfQK5s6oseemQIT5cvKj%2FoW4FzDgoIs%2Fg8n%2BD3kqNv%2Bv%2BdM4zx8f7ZFa8VuWEq9rXk8jEUMSEcLvXXNIRg1FCTcl%2Fq3xttgEVyFbtITiHg9juDL2YlBlh%2F%2FrlBKKBuVDpo3wwS8wD%2Bi7bhA8yu4ex8h%2FZs5mOrZLFnmbdPE8uR3JDnKHXBa09swGfDpEWpVPK0q002X0ihg8X4DTZN7ljW4VeEalua4mKtscpKpuziLQhragahd91X%2BMjhC6oDLo4zEgZkUjc85PzffH6CJg%3D%3D\"&sign_type=\"RSA\""}
     */

    private String err_code;
    private String err_message;
    private ResultBean result;

    @Override
    public String toString() {
        return "AliPaySignEntity{" +
                "err_code='" + err_code + '\'' +
                ", err_message='" + err_message + '\'' +
                ", result=" + result +
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

    public static class ResultBean {
        /**
         * key : partner="2088921700888610"&seller_id="m13941850400@163.com"&out_trade_no="GB77E1518067190997"&subject="车驿站订单"&body="车驿站订单"&total_fee="1289.00"&service="mobile.securitypay.pay"&_input_charset="UTF-8"&notify_url="http://114.215.71.170/car_inn/v1/ali/notifyurl"&payment_type="1"&app_id=" 2018011901977798"&it_b_pay="5m"&sign="iwQnSCYbs7KKQgdeNH666lESGz8%2FKQJKM6nkrRwBNdThg61HjPHSWisny6kaGFOYsS%2FfQK5s6oseemQIT5cvKj%2FoW4FzDgoIs%2Fg8n%2BD3kqNv%2Bv%2BdM4zx8f7ZFa8VuWEq9rXk8jEUMSEcLvXXNIRg1FCTcl%2Fq3xttgEVyFbtITiHg9juDL2YlBlh%2F%2FrlBKKBuVDpo3wwS8wD%2Bi7bhA8yu4ex8h%2FZs5mOrZLFnmbdPE8uR3JDnKHXBa09swGfDpEWpVPK0q002X0ihg8X4DTZN7ljW4VeEalua4mKtscpKpuziLQhragahd91X%2BMjhC6oDLo4zEgZkUjc85PzffH6CJg%3D%3D"&sign_type="RSA"
         */

        private String key;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "key='" + key + '\'' +
                    '}';
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
