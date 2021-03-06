package com.jixing.kd.mall.entity;

import java.util.List;

/**
 * Created by Yan on 2017/12/20.
 */

public class BannerEntity {

    /**
     * result : [{"id":"1","title":"车驿站促销活动","picture_path":"http://114.215.71.170:81/inn/advertisement/9c93.jpg","goods_id":"4a408beb9d784b89ad9b34a5d1dbb255","city_code":"0411","delete_flag":"0","insert_time":1506043936000,"update_time":1519282056000},{"id":"2","title":"米其林新品活动","picture_path":"http://114.215.71.170:81/inn/advertisement/b121b.jpg","goods_id":"1","city_code":"0411","delete_flag":"0","insert_time":1506043936000,"update_time":1519282065000},{"id":"3","title":"途虎汽车","picture_path":"http://114.215.71.170:81/inn/advertisement/bb12.jpg","goods_id":"2","city_code":"0411","delete_flag":"0","insert_time":1506043936000,"update_time":1519282073000}]
     * err_code : 0
     * err_message : OK
     */

    private String err_code;
    private String err_message;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "BannerEntity{" +
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * title : 车驿站促销活动
         * picture_path : http://114.215.71.170:81/inn/advertisement/9c93.jpg
         * goods_id : 4a408beb9d784b89ad9b34a5d1dbb255
         * city_code : 0411
         * delete_flag : 0
         * insert_time : 1506043936000
         * update_time : 1519282056000
         */

        private String id;
        private String title;
        private String picture_path;
        private String goods_id;
        private String city_code;
        private String delete_flag;
        private long insert_time;
        private long update_time;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", picture_path='" + picture_path + '\'' +
                    ", goods_id='" + goods_id + '\'' +
                    ", city_code='" + city_code + '\'' +
                    ", delete_flag='" + delete_flag + '\'' +
                    ", insert_time=" + insert_time +
                    ", update_time=" + update_time +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicture_path() {
            return picture_path;
        }

        public void setPicture_path(String picture_path) {
            this.picture_path = picture_path;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getDelete_flag() {
            return delete_flag;
        }

        public void setDelete_flag(String delete_flag) {
            this.delete_flag = delete_flag;
        }

        public long getInsert_time() {
            return insert_time;
        }

        public void setInsert_time(long insert_time) {
            this.insert_time = insert_time;
        }

        public long getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }
    }
}
