package com.example.gtercn.car.mall.entity;

import java.util.List;

/**
 * Created by Yan on 2018/1/6.
 */

public class CartEntity {

    /**
     * result : [{"id":"1","goods_id":"1","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","number":1,"create_time":1515204722000,"spec_item_ids":"1,6,20","goods_title":"米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来","promotion_price":"500.00","small_picture":"http://114.215.71.170:81/inn/advertisement/9c93.jpg","weight":0,"spec_item_list":[{"花纹性能":{"id":"1","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"经济耐用型"}},{"扁平比":{"id":"6","spec_id":"1d7994389f4311e79954180373af859e","item":"30"}},{"轮胎特性":{"id":"20","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"雪地胎"}}]},{"id":"cb3a2c3ebc1a44f5a03154dd0d6de47f","goods_id":"2","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","number":3,"create_time":1515210898000,"spec_item_ids":"","goods_title":"米其林(Michelin)轮胎/汽车轮胎 235/65R17 108V 竞驰","promotion_price":"1289.00","small_picture":"http://114.215.71.170:81/inn/advertisement/31da.jpg","weight":0},{"id":"f13f18a1fbe54322af0b23e30456f52e","goods_id":"10","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","number":12,"create_time":1515210793000,"spec_item_ids":"","goods_title":"米其林(Michelin)轮胎/汽车轮胎 235/65R17 108V 旅悦 PRIMACY SUV 适配沃尔沃XC60/XC90/新胜达/哈佛H5等","promotion_price":"399.00","small_picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg","weight":0}]
     * err_code : 0
     * err_message : OK
     */

    private String err_code;
    private String err_message;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "CartEntity{" +
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
         * goods_id : 1
         * user_id : 0779243b57824b0da9e79ed4a7f8f4b6
         * number : 1
         * create_time : 1515204722000
         * spec_item_ids : 1,6,20
         * goods_title : 米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来
         * promotion_price : 500.00
         * small_picture : http://114.215.71.170:81/inn/advertisement/9c93.jpg
         * weight : 0
         * spec_item_list : [{"花纹性能":{"id":"1","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"经济耐用型"}},{"扁平比":{"id":"6","spec_id":"1d7994389f4311e79954180373af859e","item":"30"}},{"轮胎特性":{"id":"20","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"雪地胎"}}]
         */

        private String id;
        private String goods_id;
        private String user_id;
        private int number;
        private long create_time;
        private String spec_item_ids;
        private String goods_title;
        private String promotion_price;
        private String small_picture;
        private int weight;
        private List<SpecItemListBean> spec_item_list;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", goods_id='" + goods_id + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", number=" + number +
                    ", create_time=" + create_time +
                    ", spec_item_ids='" + spec_item_ids + '\'' +
                    ", goods_title='" + goods_title + '\'' +
                    ", promotion_price='" + promotion_price + '\'' +
                    ", small_picture='" + small_picture + '\'' +
                    ", weight=" + weight +
                    ", spec_item_list=" + spec_item_list +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getSpec_item_ids() {
            return spec_item_ids;
        }

        public void setSpec_item_ids(String spec_item_ids) {
            this.spec_item_ids = spec_item_ids;
        }

        public String getGoods_title() {
            return goods_title;
        }

        public void setGoods_title(String goods_title) {
            this.goods_title = goods_title;
        }

        public String getPromotion_price() {
            return promotion_price;
        }

        public void setPromotion_price(String promotion_price) {
            this.promotion_price = promotion_price;
        }

        public String getSmall_picture() {
            return small_picture;
        }

        public void setSmall_picture(String small_picture) {
            this.small_picture = small_picture;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public List<SpecItemListBean> getSpec_item_list() {
            return spec_item_list;
        }

        public void setSpec_item_list(List<SpecItemListBean> spec_item_list) {
            this.spec_item_list = spec_item_list;
        }

        public static class SpecItemListBean {
            /**
             * 花纹性能 : {"id":"1","spec_id":"fe3e0e27e6d911e79bd5180373af859e","item":"经济耐用型"}
             * 扁平比 : {"id":"6","spec_id":"1d7994389f4311e79954180373af859e","item":"30"}
             * 轮胎特性 : {"id":"20","spec_id":"3d664d53e6da11e79bd5180373af859e","item":"雪地胎"}
             */

//            private 花纹性能Bean 花纹性能;
//            private 扁平比Bean 扁平比;
//            private 轮胎特性Bean 轮胎特性;
//
//            public 花纹性能Bean get花纹性能() {
//                return 花纹性能;
//            }
//
//            public void set花纹性能(花纹性能Bean 花纹性能) {
//                this.花纹性能 = 花纹性能;
//            }
//
//            public 扁平比Bean get扁平比() {
//                return 扁平比;
//            }
//
//            public void set扁平比(扁平比Bean 扁平比) {
//                this.扁平比 = 扁平比;
//            }
//
//            public 轮胎特性Bean get轮胎特性() {
//                return 轮胎特性;
//            }
//
//            public void set轮胎特性(轮胎特性Bean 轮胎特性) {
//                this.轮胎特性 = 轮胎特性;
//            }
//
//            public static class 花纹性能Bean {
//                /**
//                 * id : 1
//                 * spec_id : fe3e0e27e6d911e79bd5180373af859e
//                 * item : 经济耐用型
//                 */
//
//                private String id;
//                private String spec_id;
//                private String item;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getSpec_id() {
//                    return spec_id;
//                }
//
//                public void setSpec_id(String spec_id) {
//                    this.spec_id = spec_id;
//                }
//
//                public String getItem() {
//                    return item;
//                }
//
//                public void setItem(String item) {
//                    this.item = item;
//                }
//            }
//
//            public static class 扁平比Bean {
//                /**
//                 * id : 6
//                 * spec_id : 1d7994389f4311e79954180373af859e
//                 * item : 30
//                 */
//
//                private String id;
//                private String spec_id;
//                private String item;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getSpec_id() {
//                    return spec_id;
//                }
//
//                public void setSpec_id(String spec_id) {
//                    this.spec_id = spec_id;
//                }
//
//                public String getItem() {
//                    return item;
//                }
//
//                public void setItem(String item) {
//                    this.item = item;
//                }
//            }
//
//            public static class 轮胎特性Bean {
//                /**
//                 * id : 20
//                 * spec_id : 3d664d53e6da11e79bd5180373af859e
//                 * item : 雪地胎
//                 */
//
//                private String id;
//                private String spec_id;
//                private String item;
//
//                public String getId() {
//                    return id;
//                }
//
//                public void setId(String id) {
//                    this.id = id;
//                }
//
//                public String getSpec_id() {
//                    return spec_id;
//                }
//
//                public void setSpec_id(String spec_id) {
//                    this.spec_id = spec_id;
//                }
//
//                public String getItem() {
//                    return item;
//                }
//
//                public void setItem(String item) {
//                    this.item = item;
//                }
//            }
        }
    }
}
