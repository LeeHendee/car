package com.example.gtercn.car.mall.entity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/15.
 * Used to :
 */

public class PreOrderEntity {


    /**
     * goods_attr_list : [{"goods_id":"String,商品id","number":"String,商品数量","spec_item_ids":"String,商品规格ids,多个id逗号隔开（选填）"}]
     * item_count : String,商品件数(非商品数量)
     * total_price : String,商品总价
     * address_id : String,地址id
     * customer_mark : String,客户备注信息（选填）
     * invoice : String,是否开发票,Y：是，N：否
     */

    private String item_count;
    private String total_price;
    private String address_id;
    private String customer_mark;
    private String invoice;
    private List<GoodsAttrListBean> goods_attr_list;

    @Override
    public String toString() {
        return "PreOrderEntity{" +
                "item_count='" + item_count + '\'' +
                ", total_price='" + total_price + '\'' +
                ", address_id='" + address_id + '\'' +
                ", customer_mark='" + customer_mark + '\'' +
                ", invoice='" + invoice + '\'' +
                ", goods_attr_list=" + goods_attr_list +
                '}';
    }

    public String getItem_count() {
        return item_count;
    }

    public void setItem_count(String item_count) {
        this.item_count = item_count;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getCustomer_mark() {
        return customer_mark;
    }

    public void setCustomer_mark(String customer_mark) {
        this.customer_mark = customer_mark;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public List<GoodsAttrListBean> getGoods_attr_list() {
        return goods_attr_list;
    }

    public void setGoods_attr_list(List<GoodsAttrListBean> goods_attr_list) {
        this.goods_attr_list = goods_attr_list;
    }

    public static class GoodsAttrListBean {
        /**
         * goods_id : String,商品id
         * number : String,商品数量
         * spec_item_ids : String,商品规格ids,多个id逗号隔开（选填）
         */

        private String goods_id;
        private String number;
        private String spec_item_ids;

        @Override
        public String toString() {
            return "GoodsAttrListBean{" +
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
