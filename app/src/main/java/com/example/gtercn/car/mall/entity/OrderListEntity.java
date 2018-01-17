package com.example.gtercn.car.mall.entity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/5.
 * Used to : 订单列表实体类
 */

public class OrderListEntity {
    /**
     * result : [{"id":"095396f085fc4c449ee885b67e5f8d4c","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","order_no":"GA97F1516003397803","order_status":1,"order_time":1516003397000,"item_count":0,"total_amount":399,"payment":399,"address_id":"1","customer_mark":"","invoice":"N","order_details":[{"id":"d7fad5c0a9bf44ebba6a2b664b2d0662","order_id":"095396f085fc4c449ee885b67e5f8d4c","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/b121b.jpg"]}]},{"id":"5081c102d82b462489b12c2b1e5b0c2c","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","order_no":"G80A31515981365393","order_status":1,"order_time":1515981365000,"item_count":0,"total_amount":399,"payment":399,"address_id":"1","customer_mark":"","invoice":"N","order_details":[{"id":"708b9d39c9654729a624d1c1e3b4ec97","order_id":"5081c102d82b462489b12c2b1e5b0c2c","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/b121b.jpg"]}]},{"id":"5c71f6419ac940cc996ad2b1628040c8","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","order_no":"G4B9B1515981654526","order_status":1,"order_time":1515981654000,"item_count":0,"total_amount":399,"payment":399,"address_id":"1","customer_mark":"","invoice":"N","order_details":[{"id":"71ed06117aeb489a87f39dc3b4a4cf1e","order_id":"5c71f6419ac940cc996ad2b1628040c8","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/b121b.jpg"]}]},{"id":"fbc3f57f5df549518c15c433151b700c","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","order_no":"G54901515929422811","order_status":3,"order_time":1516019377000,"item_count":2,"total_amount":2000,"payment":2000,"address_id":"1234","logistics_id":"1","invoice":"Y","order_details":[{"id":"5cbba6a0c6d644bd886870622d55ac51","order_id":"fbc3f57f5df549518c15c433151b700c","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/31da.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/31da.jpg"]},{"id":"7ac908867a9944dca613a03d2ed22752","order_id":"fbc3f57f5df549518c15c433151b700c","number":2,"small_picture":"http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg,http://114.215.71.170:81/inn/advertisement/9c93.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/9c93.jpg","http://114.215.71.170:81/inn/advertisement/9c93.jpg","http://114.215.71.170:81/inn/advertisement/9c93.jpg"]}],"logistics_detail":{"id":"4","logistics_id":"1","description":"【大连市】 您的订单在京东【大连分拨中心】发货完成，准备送往京东【沙河口站】","create_time":1516014761000},"logistics_no":"E558963265001","logistics_name":"顺丰速运"}]
     * err_code : 0
     * err_message : OK
     */

    private String err_code;
    private String err_message;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "OrderListEntity{" +
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
         * id : 095396f085fc4c449ee885b67e5f8d4c
         * user_id : 0779243b57824b0da9e79ed4a7f8f4b6
         * order_no : GA97F1516003397803
         * order_status : 1
         * order_time : 1516003397000
         * item_count : 0
         * total_amount : 399
         * payment : 399
         * address_id : 1
         * customer_mark :
         * invoice : N
         * order_details : [{"id":"d7fad5c0a9bf44ebba6a2b664b2d0662","order_id":"095396f085fc4c449ee885b67e5f8d4c","number":1,"small_picture":"http://114.215.71.170:81/inn/advertisement/b121b.jpg","small_picture_list":["http://114.215.71.170:81/inn/advertisement/b121b.jpg"]}]
         * logistics_id : 1
         * logistics_detail : {"id":"4","logistics_id":"1","description":"【大连市】 您的订单在京东【大连分拨中心】发货完成，准备送往京东【沙河口站】","create_time":1516014761000}
         * logistics_no : E558963265001
         * logistics_name : 顺丰速运
         */

        private String id;
        private String user_id;
        private String order_no;
        private int order_status;
        private long order_time;
        private int item_count;
        private int total_amount;
        private int payment;
        private String address_id;
        private String customer_mark;
        private String invoice;
        private String logistics_id;
        private LogisticsDetailBean logistics_detail;
        private String logistics_no;
        private String logistics_name;
        private List<OrderDetailsBean> order_details;

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
                    ", customer_mark='" + customer_mark + '\'' +
                    ", invoice='" + invoice + '\'' +
                    ", logistics_id='" + logistics_id + '\'' +
                    ", logistics_detail=" + logistics_detail +
                    ", logistics_no='" + logistics_no + '\'' +
                    ", logistics_name='" + logistics_name + '\'' +
                    ", order_details=" + order_details +
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

        public int getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(int total_amount) {
            this.total_amount = total_amount;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
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

        public String getLogistics_id() {
            return logistics_id;
        }

        public void setLogistics_id(String logistics_id) {
            this.logistics_id = logistics_id;
        }

        public LogisticsDetailBean getLogistics_detail() {
            return logistics_detail;
        }

        public void setLogistics_detail(LogisticsDetailBean logistics_detail) {
            this.logistics_detail = logistics_detail;
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

        public List<OrderDetailsBean> getOrder_details() {
            return order_details;
        }

        public void setOrder_details(List<OrderDetailsBean> order_details) {
            this.order_details = order_details;
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

        public static class OrderDetailsBean {
            /**
             * id : d7fad5c0a9bf44ebba6a2b664b2d0662
             * order_id : 095396f085fc4c449ee885b67e5f8d4c
             * number : 1
             * small_picture : http://114.215.71.170:81/inn/advertisement/b121b.jpg
             * small_picture_list : ["http://114.215.71.170:81/inn/advertisement/b121b.jpg"]
             */

            private String id;
            private String order_id;
            private int number;
            private String small_picture;
            private List<String> small_picture_list;

            @Override
            public String toString() {
                return "OrderDetailsBean{" +
                        "id='" + id + '\'' +
                        ", order_id='" + order_id + '\'' +
                        ", number=" + number +
                        ", small_picture='" + small_picture + '\'' +
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

            public List<String> getSmall_picture_list() {
                return small_picture_list;
            }

            public void setSmall_picture_list(List<String> small_picture_list) {
                this.small_picture_list = small_picture_list;
            }
        }
    }
}
