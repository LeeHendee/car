package com.example.gtercn.car.mall.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan on 2017/12/29.
 */

public class ProductDetailEntity implements Serializable {


    /**
     * err_code : 0
     * err_message : OK
     * result : {"id":"30b65b3f68624d7f9693932637951962","brand_id":"dc1dab999f3d11e79954180373af859e","score":40,"sku_code":"267291521333540908","prime_price":589,"promotion_price":489,"cost_price":400,"product_area":"阜新","goods_title":"米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来","goods_synopsis":"米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来","goods_description":"米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来","goods_detail":"","search_tag":"米其林","stock":15,"big_picture":"http://114.215.71.170:81/shopping/goods/big/1521333533879.jpg","small_picture":"http://114.215.71.170:81/shopping/goods/small/1521333531048.jpg","sold_number":17,"is_hot":"Y","is_new":"Y","up_time":1521333540000,"status":0,"city_id":"aa4f5668ce0a4febaea8d20193123695","update_time":1521641483000,"create_time":1521333540000,"weight":5,"category_id":"2","comment_count":0,"percentage":0,"big_picture_list":["http://114.215.71.170:81/shopping/goods/big/1521333533879.jpg"],"small_picture_list":["http://114.215.71.170:81/shopping/goods/small/1521333531048.jpg"],"spec_item_ids":"49,73,28,33,45,59","spec_item_content":"尺寸:13英寸,车型类别:SUV轮胎,花纹性能:经济耐用型,扁平比:30,轮胎特性:半热熔轮胎,胎面宽度:155"}
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

    public static ProductDetailEntity objectFromData(String str) {

        return new Gson().fromJson(str, ProductDetailEntity.class);
    }

    public static ProductDetailEntity objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ProductDetailEntity.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ProductDetailEntity> arrayProductDetailEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<ProductDetailEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ProductDetailEntity> arrayProductDetailEntityFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ProductDetailEntity>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


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
         * id : 30b65b3f68624d7f9693932637951962
         * brand_id : dc1dab999f3d11e79954180373af859e
         * score : 40
         * sku_code : 267291521333540908
         * prime_price : 589
         * promotion_price : 489
         * cost_price : 400
         * product_area : 阜新
         * goods_title : 米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来
         * goods_synopsis : 米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来
         * goods_description : 米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来
         * goods_detail :
         * search_tag : 米其林
         * stock : 15
         * big_picture : http://114.215.71.170:81/shopping/goods/big/1521333533879.jpg
         * small_picture : http://114.215.71.170:81/shopping/goods/small/1521333531048.jpg
         * sold_number : 17
         * is_hot : Y
         * is_new : Y
         * up_time : 1521333540000
         * status : 0
         * city_id : aa4f5668ce0a4febaea8d20193123695
         * update_time : 1521641483000
         * create_time : 1521333540000
         * weight : 5
         * category_id : 2
         * comment_count : 0
         * percentage : 0
         * big_picture_list : ["http://114.215.71.170:81/shopping/goods/big/1521333533879.jpg"]
         * small_picture_list : ["http://114.215.71.170:81/shopping/goods/small/1521333531048.jpg"]
         * spec_item_ids : 49,73,28,33,45,59
         * spec_item_content : 尺寸:13英寸,车型类别:SUV轮胎,花纹性能:经济耐用型,扁平比:30,轮胎特性:半热熔轮胎,胎面宽度:155
         */

        private String id;
        private String brand_id;
        private int score;
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
        private int status;
        private String city_id;
        private long update_time;
        private long create_time;
        private int weight;
        private String category_id;
        private int comment_count;
        private int percentage;
        private String spec_item_ids;
        private String spec_item_content;
        private List<String> big_picture_list;
        private List<String> small_picture_list;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", brand_id='" + brand_id + '\'' +
                    ", score=" + score +
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
                    ", status=" + status +
                    ", city_id='" + city_id + '\'' +
                    ", update_time=" + update_time +
                    ", create_time=" + create_time +
                    ", weight=" + weight +
                    ", category_id='" + category_id + '\'' +
                    ", comment_count=" + comment_count +
                    ", percentage=" + percentage +
                    ", spec_item_ids='" + spec_item_ids + '\'' +
                    ", spec_item_content='" + spec_item_content + '\'' +
                    ", big_picture_list=" + big_picture_list +
                    ", small_picture_list=" + small_picture_list +
                    '}';
        }

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public static ResultBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ResultBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ResultBean> arrayResultBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ResultBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<ResultBean> arrayResultBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ResultBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public long getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getPercentage() {
            return percentage;
        }

        public void setPercentage(int percentage) {
            this.percentage = percentage;
        }

        public String getSpec_item_ids() {
            return spec_item_ids;
        }

        public void setSpec_item_ids(String spec_item_ids) {
            this.spec_item_ids = spec_item_ids;
        }

        public String getSpec_item_content() {
            return spec_item_content;
        }

        public void setSpec_item_content(String spec_item_content) {
            this.spec_item_content = spec_item_content;
        }

        public List<String> getBig_picture_list() {
            return big_picture_list;
        }

        public void setBig_picture_list(List<String> big_picture_list) {
            this.big_picture_list = big_picture_list;
        }

        public List<String> getSmall_picture_list() {
            return small_picture_list;
        }

        public void setSmall_picture_list(List<String> small_picture_list) {
            this.small_picture_list = small_picture_list;
        }
    }
}
