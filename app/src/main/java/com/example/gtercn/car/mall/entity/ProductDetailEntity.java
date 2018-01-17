package com.example.gtercn.car.mall.entity;

import java.io.Serializable;

/**
 * Created by Yan on 2017/12/29.
 */

public class ProductDetailEntity implements Serializable {

    /**
     * err_code : 0
     * err_message : OK
     * result : {"id":"1","product_id":"d8d95ae59f4111e79954180373af859e","sku_code":"g_001","prime_price":1000,"promotion_price":500,"cost_price":430,"product_area":"0","goods_title":"米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来","goods_synopsis":"更出色的坚韧性，更短的刹车距离，更长的行驶里程，米其林全能表现","goods_description":"米其林Energy XM2韧悦轮胎采用全新的IronFlex技术","goods_detail":"<div><\/div>","search_tag":"米其林,韧悦","stock":220,"big_picture":"http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg","small_picture":"http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg","sold_number":320,"is_hot":"Y","is_new":"Y","up_time":1505997988000,"down_time":1505997988000,"status":0,"city_code":"0411","update_time":1514384800000}
     */

    private String err_code;
    private String err_message;
    private ResultBean result;

    @Override
    public String toString() {
        return "ProductDetailEntity{" +
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

    public static class ResultBean implements Serializable {
        /**
         * id : 1
         * product_id : d8d95ae59f4111e79954180373af859e
         * sku_code : g_001
         * prime_price : 1000
         * promotion_price : 500
         * cost_price : 430
         * product_area : 0
         * goods_title : 米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来
         * goods_synopsis : 更出色的坚韧性，更短的刹车距离，更长的行驶里程，米其林全能表现
         * goods_description : 米其林Energy XM2韧悦轮胎采用全新的IronFlex技术
         * goods_detail : <div></div>
         * search_tag : 米其林,韧悦
         * stock : 220
         * big_picture : http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg
         * small_picture : http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg
         * sold_number : 320
         * is_hot : Y
         * is_new : Y
         * up_time : 1505997988000
         * down_time : 1505997988000
         * status : 0
         * city_code : 0411
         * update_time : 1514384800000
         */

        private String id;
        private String product_id;
        private String sku_code;
        private int prime_price;
        private int promotion_price;
        private int cost_price;
        private String product_area;
        private String goods_title;
        private String goods_synopsis;
        private String goods_description;
        private String goods_detail;
        private String search_tag;
        private int stock;
        private String big_picture;
        private String small_picture;
        private int sold_number;
        private String is_hot;
        private String is_new;
        private long up_time;
        private long down_time;
        private int status;
        private String city_code;
        private long update_time;
        private String category_id;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", product_id='" + product_id + '\'' +
                    ", sku_code='" + sku_code + '\'' +
                    ", prime_price=" + prime_price +
                    ", promotion_price=" + promotion_price +
                    ", cost_price=" + cost_price +
                    ", product_area='" + product_area + '\'' +
                    ", goods_title='" + goods_title + '\'' +
                    ", goods_synopsis='" + goods_synopsis + '\'' +
                    ", goods_description='" + goods_description + '\'' +
                    ", goods_detail='" + goods_detail + '\'' +
                    ", search_tag='" + search_tag + '\'' +
                    ", stock=" + stock +
                    ", big_picture='" + big_picture + '\'' +
                    ", small_picture='" + small_picture + '\'' +
                    ", sold_number=" + sold_number +
                    ", is_hot='" + is_hot + '\'' +
                    ", is_new='" + is_new + '\'' +
                    ", up_time=" + up_time +
                    ", down_time=" + down_time +
                    ", status=" + status +
                    ", city_code='" + city_code + '\'' +
                    ", update_time=" + update_time +
                    ", category_id='" + category_id + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getSku_code() {
            return sku_code;
        }

        public void setSku_code(String sku_code) {
            this.sku_code = sku_code;
        }

        public int getPrime_price() {
            return prime_price;
        }

        public void setPrime_price(int prime_price) {
            this.prime_price = prime_price;
        }

        public int getPromotion_price() {
            return promotion_price;
        }

        public void setPromotion_price(int promotion_price) {
            this.promotion_price = promotion_price;
        }

        public int getCost_price() {
            return cost_price;
        }

        public void setCost_price(int cost_price) {
            this.cost_price = cost_price;
        }

        public String getProduct_area() {
            return product_area;
        }

        public void setProduct_area(String product_area) {
            this.product_area = product_area;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getGoods_synopsis() {
            return goods_synopsis;
        }

        public void setGoods_synopsis(String goods_synopsis) {
            this.goods_synopsis = goods_synopsis;
        }

        public String getGoods_description() {
            return goods_description;
        }

        public void setGoods_description(String goods_description) {
            this.goods_description = goods_description;
        }

        public String getGoods_detail() {
            return goods_detail;
        }

        public void setGoods_detail(String goods_detail) {
            this.goods_detail = goods_detail;
        }

        public String getSearch_tag() {
            return search_tag;
        }

        public void setSearch_tag(String search_tag) {
            this.search_tag = search_tag;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getBig_picture() {
            return big_picture;
        }

        public void setBig_picture(String big_picture) {
            this.big_picture = big_picture;
        }

        public String getSmall_picture() {
            return small_picture;
        }

        public void setSmall_picture(String small_picture) {
            this.small_picture = small_picture;
        }

        public int getSold_number() {
            return sold_number;
        }

        public void setSold_number(int sold_number) {
            this.sold_number = sold_number;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public String getIs_new() {
            return is_new;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public long getUp_time() {
            return up_time;
        }

        public void setUp_time(long up_time) {
            this.up_time = up_time;
        }

        public long getDown_time() {
            return down_time;
        }

        public void setDown_time(long down_time) {
            this.down_time = down_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public long getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }
    }
}
