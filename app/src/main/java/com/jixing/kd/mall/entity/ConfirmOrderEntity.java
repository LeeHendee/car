package com.jixing.kd.mall.entity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/22.
 * Used to : 确认订单页实体类
 */

public class ConfirmOrderEntity {

    /**
     * err_code : 0
     * err_message : OK
     * result : {"addres_id":"d7d6b2fe95ec499bb2c7248670d7ac29","name":"哈哈","phone":"18642690085","address":"辽宁省大连市123","goods_list":[{"goods_id":"10","goods_title":"米其林(Michelin)轮胎/汽车轮胎 235/65R17 108V 旅悦 PRIMACY SUV 适配沃尔沃XC60/XC90/新胜达/哈佛H5等","prime_price":455,"promotion_price":399,"number":"1","picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg","spec_item_ids":"1","spec_item_name":"花纹性能:经济耐用型"}],"total_price":399}
     * message : 结算中
     */

    private String err_code;
    private String err_message;
    private ResultBean result;
    private String message;

    @Override
    public String toString() {
        return "ConfirmOrderEntity{" +
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
         * addres_id : d7d6b2fe95ec499bb2c7248670d7ac29
         * name : 哈哈
         * phone : 18642690085
         * address : 辽宁省大连市123
         * goods_list : [{"goods_id":"10","goods_title":"米其林(Michelin)轮胎/汽车轮胎 235/65R17 108V 旅悦 PRIMACY SUV 适配沃尔沃XC60/XC90/新胜达/哈佛H5等","prime_price":455,"promotion_price":399,"number":"1","picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg","spec_item_ids":"1","spec_item_name":"花纹性能:经济耐用型"}]
         * total_price : 399.0
         */

        private String addres_id;
        private String name;
        private String phone;
        private String address;
        private double total_price;
        private List<GoodsListBean> goods_list;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "addres_id='" + addres_id + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +
                    ", total_price=" + total_price +
                    ", goods_list=" + goods_list +
                    '}';
        }

        public String getAddres_id() {
            return addres_id;
        }

        public void setAddres_id(String addres_id) {
            this.addres_id = addres_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getTotal_price() {
            return total_price;
        }

        public void setTotal_price(double total_price) {
            this.total_price = total_price;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * goods_id : 10
             * goods_title : 米其林(Michelin)轮胎/汽车轮胎 235/65R17 108V 旅悦 PRIMACY SUV 适配沃尔沃XC60/XC90/新胜达/哈佛H5等
             * prime_price : 455.0
             * promotion_price : 399.0
             * number : 1
             * picture : http://114.215.71.170:81/inn/advertisement/b121b.jpg
             * spec_item_ids : 1
             * spec_item_name : 花纹性能:经济耐用型
             */

            private String goods_id;
            private String goods_title;
            private double prime_price;
            private double promotion_price;
            private String number;
            private String picture;
            private String spec_item_ids;
            private String spec_item_name;

            @Override
            public String toString() {
                return "GoodsListBean{" +
                        "goods_id='" + goods_id + '\'' +
                        ", goods_title='" + goods_title + '\'' +
                        ", prime_price=" + prime_price +
                        ", promotion_price=" + promotion_price +
                        ", number='" + number + '\'' +
                        ", picture='" + picture + '\'' +
                        ", spec_item_ids='" + spec_item_ids + '\'' +
                        ", spec_item_name='" + spec_item_name + '\'' +
                        '}';
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_title() {
                return goods_title;
            }

            public void setGoods_title(String goods_title) {
                this.goods_title = goods_title;
            }

            public double getPrime_price() {
                return prime_price;
            }

            public void setPrime_price(double prime_price) {
                this.prime_price = prime_price;
            }

            public double getPromotion_price() {
                return promotion_price;
            }

            public void setPromotion_price(double promotion_price) {
                this.promotion_price = promotion_price;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getSpec_item_ids() {
                return spec_item_ids;
            }

            public void setSpec_item_ids(String spec_item_ids) {
                this.spec_item_ids = spec_item_ids;
            }

            public String getSpec_item_name() {
                return spec_item_name;
            }

            public void setSpec_item_name(String spec_item_name) {
                this.spec_item_name = spec_item_name;
            }
        }
    }
}
