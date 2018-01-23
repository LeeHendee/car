package com.example.gtercn.car.mall.entity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/15.
 * Used to : 提交订单，参数封装
 */

public class PreOrderEntity {

    /**
     * goods_attr_list : [{"goods_id":"String,商品id","number":"String,商品数量","spec_item_ids":"String,商品规格ids,多个id逗号隔开（选填）"}]
     * expert_id : String,达人id
     * item_count : String,商品件数(非商品数量)
     * total_price : String,商品总价
     * address_id : String,地址id
     * customer_mark : String,客户备注信息（选填）
     * invoice : String,是否开发票,Y：是，N：否
     * invoice_type : String,发票类型,E电子发票,P纸质发票（选填）
     * invoice_title : String,发票抬头（选填）
     * invoice_content : String,发票内容（选填）
     */
    private String expert_id;
    private String item_count;
    private String total_price;
    private String address_id;
    private String customer_mark;
    private String invoice;
    private String invoice_type;
    private String invoice_title;
    private String invoice_content;
    private List<GoodsAttrListBean> goods_attr_list;

    @Override
    public String toString() {
        return "PreOrderEntity{" +
                "expert_id='" + expert_id + '\'' +
                ", item_count='" + item_count + '\'' +
                ", total_price='" + total_price + '\'' +
                ", address_id='" + address_id + '\'' +
                ", customer_mark='" + customer_mark + '\'' +
                ", invoice='" + invoice + '\'' +
                ", invoice_type='" + invoice_type + '\'' +
                ", invoice_title='" + invoice_title + '\'' +
                ", invoice_content='" + invoice_content + '\'' +
                ", goods_attr_list=" + goods_attr_list +
                '}';
    }

    public String getExpert_id() {
        return expert_id;
    }

    public void setExpert_id(String expert_id) {
        this.expert_id = expert_id;
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

    public String getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(String invoice_type) {
        this.invoice_type = invoice_type;
    }

    public String getInvoice_title() {
        return invoice_title;
    }

    public void setInvoice_title(String invoice_title) {
        this.invoice_title = invoice_title;
    }

    public String getInvoice_content() {
        return invoice_content;
    }

    public void setInvoice_content(String invoice_content) {
        this.invoice_content = invoice_content;
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
