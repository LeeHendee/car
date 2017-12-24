package com.example.gtercn.car.mall.entity;

import java.util.List;

/**
 * Created by Yan on 2017/12/24.
 */

public class BrandListEntity {

    /**
     * result : [{"id":"dc1dab999f3d11e79954180373af859e","category_id":"7","cn_name":"米其林","en_name":"MICHELIN","sort":1,"logo":"logo.png","descrption":"米其林","picture":"http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/b121b.jpg"},{"id":"dc1daf7a9f3d11e79954180373af859e","category_id":"7","cn_name":"马牌","en_name":"Continental","sort":2,"logo":"logo.png","descrption":"马牌","picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg,http://114.215.71.170:81/inn/advertisement/b121b.jpg"},{"id":"dc1db0299f3d11e79954180373af859e","category_id":"7","cn_name":"普利司通","en_name":"Bridgestone","sort":3,"logo":"logo.png","descrption":"普利司通","picture":"http://114.215.71.170:81/inn/advertisement/bb12.jpg"},{"id":"dc1db09a9f3d11e79954180373af859e","category_id":"7","cn_name":"邓禄普","en_name":"DUNLOP","sort":4,"logo":"logo.png","descrption":"邓禄普","picture":"http://114.215.71.170:81/inn/advertisement/d875.jpg"},{"id":"dc1db1019f3d11e79954180373af859e","category_id":"7","cn_name":"固特异","en_name":"Goodyear","sort":5,"logo":"logo.png","descrption":"固特异","picture":"http://114.215.71.170:81/inn/advertisement/31da.jpg"},{"id":"dc1db1579f3d11e79954180373af859e","category_id":"7","cn_name":"佳通轮胎","en_name":"Giti","sort":6,"logo":"logo.png","descrption":"佳通轮胎","picture":"http://114.215.71.170:81/inn/advertisement/31da.jpg"},{"id":"dc1db1a99f3d11e79954180373af859e","category_id":"7","cn_name":"韩泰","en_name":"Hankook","sort":7,"logo":"logo.png","descrption":"韩泰","picture":"http://114.215.71.170:81/inn/advertisement/9c93.jpg"},{"id":"dc1db1ff9f3d11e79954180373af859e","category_id":"7","cn_name":"倍耐力","en_name":"Pirelli","sort":8,"logo":"logo.png","descrption":"倍耐力","picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg"},{"id":"dc1db2a59f3d11e79954180373af859e","category_id":"7","cn_name":"玛吉斯","en_name":"","sort":9,"logo":"logo.png","descrption":"玛吉斯","picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg"},{"id":"dc1db2f89f3d11e79954180373af859e","category_id":"7","cn_name":"朝阳","en_name":"Continental","sort":10,"logo":"logo.png","descrption":"朝阳","picture":"http://114.215.71.170:81/inn/advertisement/d875.jpg"},{"id":"dc1db3689f3d11e79954180373af859e","category_id":"7","cn_name":"锦湖","en_name":"Bridgestone","sort":11,"logo":"logo.png","descrption":"锦湖","picture":"http://114.215.71.170:81/inn/advertisement/bb12.jpg"},{"id":"dc1db3be9f3d11e79954180373af859e","category_id":"7","cn_name":"优科豪马","en_name":"yokohama","sort":12,"logo":"logo.png","descrption":"优科豪马","picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg,http://114.215.71.170:81/inn/advertisement/b121b.jpg,http://114.215.71.170:81/inn/advertisement/b121b.jpg"},{"id":"dc1db41b9f3d11e79954180373af859e","category_id":"7","cn_name":"玲珑轮胎","en_name":"","sort":13,"logo":"logo.png","descrption":"玲珑轮胎","picture":"http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg"}]
     * err_code : 0
     * err_message : OK
     */

    private String err_code;
    private String err_message;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "BrandListEntity{" +
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
         * id : dc1dab999f3d11e79954180373af859e
         * category_id : 7
         * cn_name : 米其林
         * en_name : MICHELIN
         * sort : 1
         * logo : logo.png
         * descrption : 米其林
         * picture : http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/b121b.jpg
         */

        private String id;
        private String category_id;
        private String cn_name;
        private String en_name;
        private int sort;
        private String logo;
        private String descrption;
        private String picture;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", category_id='" + category_id + '\'' +
                    ", cn_name='" + cn_name + '\'' +
                    ", en_name='" + en_name + '\'' +
                    ", sort=" + sort +
                    ", logo='" + logo + '\'' +
                    ", descrption='" + descrption + '\'' +
                    ", picture='" + picture + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCn_name() {
            return cn_name;
        }

        public void setCn_name(String cn_name) {
            this.cn_name = cn_name;
        }

        public String getEn_name() {
            return en_name;
        }

        public void setEn_name(String en_name) {
            this.en_name = en_name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDescrption() {
            return descrption;
        }

        public void setDescrption(String descrption) {
            this.descrption = descrption;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
