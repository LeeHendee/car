package com.jixing.kd.mall.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/22.
 * Used to : 预订单页参数封装实体类
 */

public class CreatePreOrderEntity implements Serializable {

    private List<GoodsListBean> goods_list;

    @Override
    public String toString() {
        return "CreatePreOrderEntity{" +
                "goods_list=" + goods_list +
                '}';
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean implements Serializable {
        /**
         * goods_id : String,商品id
         * number : String,商品数量
         * spec_item_ids : String,商品规格ids,多个id逗号隔开（商品尺寸等规格）
         */

        private String goods_id;
        private String number;
        private String spec_item_ids;

        @Override
        public String toString() {
            return "GoodsListBean{" +
                    "goods_id='" + goods_id + '\'' +
                    ", number='" + number + '\'' +
                    ", spec_item_ids='" + spec_item_ids + '\'' +
                    '}';
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getSpec_item_ids() {
            return spec_item_ids;
        }

        public void setSpec_item_ids(String spec_item_ids) {
            this.spec_item_ids = spec_item_ids;
        }
    }
}
