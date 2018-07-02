package com.jixing.kd.mall.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
     * result : {"addres_id":"daeb092371484747b0c8ea1c16abcd48","flag":"0","name":"李四","phone":"123456","address":"辽宁省大连市123","category_id":"8","goods_list":[{"goods_id":"12f6a8bdf1b04369a2108b64a72c8eb8","goods_title":"洗车","prime_price":18,"promotion_price":10,"number":"1","picture":"http://114.215.71.170:81/shopping/goods/small/1521635780256.jpg","spec_item_ids":"220,224,226,229","spec_item_name":"服务类别:保养维修,服务方式:到店服务,服务种类:套餐,其他分类:普通漆"}],"total_price":10}
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

    public static ConfirmOrderEntity objectFromData(String str) {

        return new Gson().fromJson(str, ConfirmOrderEntity.class);
    }

    public static ConfirmOrderEntity objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ConfirmOrderEntity.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ConfirmOrderEntity> arrayConfirmOrderEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<ConfirmOrderEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ConfirmOrderEntity> arrayConfirmOrderEntityFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ConfirmOrderEntity>>() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean {
        /**
         * addres_id : daeb092371484747b0c8ea1c16abcd48
         * flag : 0
         * name : 李四
         * phone : 123456
         * address : 辽宁省大连市123
         * category_id : 8
         * goods_list : [{"goods_id":"12f6a8bdf1b04369a2108b64a72c8eb8","goods_title":"洗车","prime_price":18,"promotion_price":10,"number":"1","picture":"http://114.215.71.170:81/shopping/goods/small/1521635780256.jpg","spec_item_ids":"220,224,226,229","spec_item_name":"服务类别:保养维修,服务方式:到店服务,服务种类:套餐,其他分类:普通漆"}]
         * total_price : 10
         */

        private String addres_id;
        private String flag;
        private String name;
        private String phone;
        private String address;
        private String category_id;
        private int total_price;
        private List<GoodsListBean> goods_list;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "addres_id='" + addres_id + '\'' +
                    ", flag='" + flag + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +
                    ", category_id='" + category_id + '\'' +
                    ", total_price=" + total_price +
                    ", goods_list=" + goods_list +
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

        public String getAddres_id() {
            return addres_id;
        }

        public void setAddres_id(String addres_id) {
            this.addres_id = addres_id;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
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

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
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
             * goods_id : 12f6a8bdf1b04369a2108b64a72c8eb8
             * goods_title : 洗车
             * prime_price : 18
             * promotion_price : 10
             * number : 1
             * picture : http://114.215.71.170:81/shopping/goods/small/1521635780256.jpg
             * spec_item_ids : 220,224,226,229
             * spec_item_name : 服务类别:保养维修,服务方式:到店服务,服务种类:套餐,其他分类:普通漆
             */

            private String goods_id;
            private String goods_title;
            private int prime_price;
            private int promotion_price;
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

            public static GoodsListBean objectFromData(String str) {

                return new Gson().fromJson(str, GoodsListBean.class);
            }

            public static GoodsListBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), GoodsListBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<GoodsListBean> arrayGoodsListBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<GoodsListBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<GoodsListBean> arrayGoodsListBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<GoodsListBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


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
