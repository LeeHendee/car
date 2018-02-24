package com.example.gtercn.car.mall.entity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/18.
 * Used to :
 */

public class OrderDetailEntity {


    /**
     * err_code : 0
     * err_message : OK
     * result : {"id":"c3d5b3af9a9449e18684e986375cc56c","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","order_no":"G29AE1516163779520","order_status":4,"order_time":1516201289000,"item_count":1,"total_amount":100,"payment":100,"address_id":"1","logistics_id":"1","customer_mark":"客户备注信息（选填）","invoice":"Y","invoice_type":"E","invoice_title":"发票抬头（选填）","invoice_content":"发票内容（选填）","order_details":[{"id":"74d174af8e2c4c03b55669d803292c12","order_id":"c3d5b3af9a9449e18684e986375cc56c","goods_id":"2","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/31da.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/31da.jpg"],"goods_title":"米其林(Michelin)轮胎/汽车轮胎 235/65R17 108V 竞驰"},{"id":"b0507a4b4ef347619a1a9d4eaf9ae7f5","order_id":"c3d5b3af9a9449e18684e986375cc56c","goods_id":"2","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/31da.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/31da.jpg"],"goods_title":"米其林(Michelin)轮胎/汽车轮胎 235/65R17 108V 竞驰"},{"id":"89b3beaef5784221b8fb7298befbeb3c","order_id":"c3d5b3af9a9449e18684e986375cc56c","goods_id":"1","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/9c93.jpg","http://114.215.71.170:81/inn/advertisement/9c93.jpg","http://114.215.71.170:81/inn/advertisement/9c93.jpg"],"goods_title":"米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来"}],"logistics_detail":[{"id":"4","logistics_id":"1","description":"【大连市】 您的订单在京东【大连分拨中心】发货完成，准备送往京东【沙河口站】","create_time":1516014761000},{"id":"3","logistics_id":"1","description":"【大连市】 您的订单在京东【大连分拨中心】分拣完成","create_time":1515930521000},{"id":"2","logistics_id":"1","description":"【沈阳市】 您的订单在京东【沈阳沈北分拣中心】发货完成，准备送往京东【大连分拨中心】","create_time":1515723478000},{"id":"1","logistics_id":"1","description":"您的订单已经拣货完成","create_time":1515633478000}],"logistics_no":"E558963265001","logistics_name":"顺丰速运","realname":"张三","telphone":"18642693215","address":"辽宁省大连市中山区友好路125号3201"}
     */

    private String err_code;
    private String err_message;
    private ResultBean result;

    @Override
    public String toString() {
        return "OrderDetailEntity{" +
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

    public static class ResultBean {
        /**
         * id : c3d5b3af9a9449e18684e986375cc56c
         * user_id : 0779243b57824b0da9e79ed4a7f8f4b6
         * order_no : G29AE1516163779520
         * order_status : 4
         * order_time : 1516201289000
         * item_count : 1
         * total_amount : 100
         * payment : 100
         * address_id : 1
         * logistics_id : 1
         * customer_mark : 客户备注信息（选填）
         * invoice : Y
         * invoice_type : E
         * invoice_title : 发票抬头（选填）
         * invoice_content : 发票内容（选填）
         * order_details : [{"id":"74d174af8e2c4c03b55669d803292c12","order_id":"c3d5b3af9a9449e18684e986375cc56c","goods_id":"2","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/31da.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/31da.jpg"],"goods_title":"米其林(Michelin)轮胎/汽车轮胎 235/65R17 108V 竞驰"},{"id":"b0507a4b4ef347619a1a9d4eaf9ae7f5","order_id":"c3d5b3af9a9449e18684e986375cc56c","goods_id":"2","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/31da.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/31da.jpg"],"goods_title":"米其林(Michelin)轮胎/汽车轮胎 235/65R17 108V 竞驰"},{"id":"89b3beaef5784221b8fb7298befbeb3c","order_id":"c3d5b3af9a9449e18684e986375cc56c","goods_id":"1","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/9c93.jpg","http://114.215.71.170:81/inn/advertisement/9c93.jpg","http://114.215.71.170:81/inn/advertisement/9c93.jpg"],"goods_title":"米其林(Michelin)轮胎/汽车轮胎 205/55R16 91V 韧悦 ENERGY XM2 适配朗逸/马自达/速腾/本田思域/大众宝来"}]
         * logistics_detail : [{"id":"4","logistics_id":"1","description":"【大连市】 您的订单在京东【大连分拨中心】发货完成，准备送往京东【沙河口站】","create_time":1516014761000},{"id":"3","logistics_id":"1","description":"【大连市】 您的订单在京东【大连分拨中心】分拣完成","create_time":1515930521000},{"id":"2","logistics_id":"1","description":"【沈阳市】 您的订单在京东【沈阳沈北分拣中心】发货完成，准备送往京东【大连分拨中心】","create_time":1515723478000},{"id":"1","logistics_id":"1","description":"您的订单已经拣货完成","create_time":1515633478000}]
         * logistics_no : E558963265001
         * logistics_name : 顺丰速运
         * realname : 张三
         * telphone : 18642693215
         * address : 辽宁省大连市中山区友好路125号3201
         */

        private String id;
        private String user_id;
        private String order_no;
        private int order_status;
        private long order_time;
        private int item_count;
        private double total_amount;
        private double payment;
        private String address_id;
        private String logistics_id;
        private String customer_mark;
        private String invoice;
        private String invoice_type;
        private String invoice_title;
        private String invoice_content;
        private String logistics_no;
        private String logistics_name;
        private String realname;
        private String telphone;
        private String address;
        private List<OrderDetailsBean> order_details;
        private List<LogisticsDetailBean> logistics_detail;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", order_no='" + order_no + '\'' +
                    ", order_status=" + order_status +
                    ", order_time=" + order_time +
                    ", item_count=" + item_count +
                    ", total_amount=" + total_amount +
                    ", payment=" + payment +
                    ", address_id='" + address_id + '\'' +
                    ", logistics_id='" + logistics_id + '\'' +
                    ", customer_mark='" + customer_mark + '\'' +
                    ", invoice='" + invoice + '\'' +
                    ", invoice_type='" + invoice_type + '\'' +
                    ", invoice_title='" + invoice_title + '\'' +
                    ", invoice_content='" + invoice_content + '\'' +
                    ", logistics_no='" + logistics_no + '\'' +
                    ", logistics_name='" + logistics_name + '\'' +
                    ", realname='" + realname + '\'' +
                    ", telphone='" + telphone + '\'' +
                    ", address='" + address + '\'' +
                    ", order_details=" + order_details +
                    ", logistics_detail=" + logistics_detail +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public long getOrder_time() {
            return order_time;
        }

        public void setOrder_time(long order_time) {
            this.order_time = order_time;
        }

        public int getItem_count() {
            return item_count;
        }

        public void setItem_count(int item_count) {
            this.item_count = item_count;
        }

        public double getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(double total_amount) {
            this.total_amount = total_amount;
        }

        public double getPayment() {
            return payment;
        }

        public void setPayment(double payment) {
            this.payment = payment;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getLogistics_id() {
            return logistics_id;
        }

        public void setLogistics_id(String logistics_id) {
            this.logistics_id = logistics_id;
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

        public String getLogistics_no() {
            return logistics_no;
        }

        public void setLogistics_no(String logistics_no) {
            this.logistics_no = logistics_no;
        }

        public String getLogistics_name() {
            return logistics_name;
        }

        public void setLogistics_name(String logistics_name) {
            this.logistics_name = logistics_name;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getTelphone() {
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<OrderDetailsBean> getOrder_details() {
            return order_details;
        }

        public void setOrder_details(List<OrderDetailsBean> order_details) {
            this.order_details = order_details;
        }

        public List<LogisticsDetailBean> getLogistics_detail() {
            return logistics_detail;
        }

        public void setLogistics_detail(List<LogisticsDetailBean> logistics_detail) {
            this.logistics_detail = logistics_detail;
        }

        public static class OrderDetailsBean {
            /**
             * id : 74d174af8e2c4c03b55669d803292c12
             * order_id : c3d5b3af9a9449e18684e986375cc56c
             * goods_id : 2
             * number : 1
             * small_picture : http://114.215.71.170:81/inn/advertisement/31da.jpg
             * small_picture_list : ["http://114.215.71.170:81/inn/advertisement/31da.jpg"]
             * goods_title : 米其林(Michelin)轮胎/汽车轮胎 235/65R17 108V 竞驰
             */

            private String id;
            private String order_id;
            private String goods_id;
            private int number;
            private String small_picture;
            private String goods_title;
            private List<String> small_picture_list;

            @Override
            public String toString() {
                return "OrderDetailsBean{" +
                        "id='" + id + '\'' +
                        ", order_id='" + order_id + '\'' +
                        ", goods_id='" + goods_id + '\'' +
                        ", number=" + number +
                        ", small_picture='" + small_picture + '\'' +
                        ", goods_title='" + goods_title + '\'' +
                        ", small_picture_list=" + small_picture_list +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getSmall_picture() {
                return small_picture;
            }

            public void setSmall_picture(String small_picture) {
                this.small_picture = small_picture;
            }

            public String getGoods_title() {
                return goods_title;
            }

            public void setGoods_title(String goods_title) {
                this.goods_title = goods_title;
            }

            public List<String> getSmall_picture_list() {
                return small_picture_list;
            }

            public void setSmall_picture_list(List<String> small_picture_list) {
                this.small_picture_list = small_picture_list;
            }
        }

        public static class LogisticsDetailBean {
            /**
             * id : 4
             * logistics_id : 1
             * description : 【大连市】 您的订单在京东【大连分拨中心】发货完成，准备送往京东【沙河口站】
             * create_time : 1516014761000
             */

            private String id;
            private String logistics_id;
            private String description;
            private long create_time;

            @Override
            public String toString() {
                return "LogisticsDetailBean{" +
                        "id='" + id + '\'' +
                        ", logistics_id='" + logistics_id + '\'' +
                        ", description='" + description + '\'' +
                        ", create_time=" + create_time +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLogistics_id() {
                return logistics_id;
            }

            public void setLogistics_id(String logistics_id) {
                this.logistics_id = logistics_id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }
        }
    }
}
