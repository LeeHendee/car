package com.jixing.kd.mall.entity;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/15.
 * Used to :
 */

public class DefaultEntity {

    /**
     * err_code : 0
     * err_message : OK
     * result : {"id":"d7d6b2fe95ec499bb2c7248670d7ac29","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","province":"辽宁省","city":"大连市","district":"沙河口区","address":"123","postal_code":"116000","name":"哈哈","phone":"116000","default_flag":"Y","insert_time":1515578916000,"update_time":1515578916000}
     */

    private String err_code;
    private String err_message;
    private ResultBean result;

    @Override
    public String toString() {
        return "DefaultEntity{" +
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
         * id : d7d6b2fe95ec499bb2c7248670d7ac29
         * user_id : 0779243b57824b0da9e79ed4a7f8f4b6
         * province : 辽宁省
         * city : 大连市
         * district : 沙河口区
         * address : 123
         * postal_code : 116000
         * name : 哈哈
         * phone : 116000
         * default_flag : Y
         * insert_time : 1515578916000
         * update_time : 1515578916000
         */

        private String id;
        private String user_id;
        private String province;
        private String city;
        private String district;
        private String address;
        private String postal_code;
        private String name;
        private String phone;
        private String default_flag;
        private long insert_time;
        private long update_time;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", district='" + district + '\'' +
                    ", address='" + address + '\'' +
                    ", postal_code='" + postal_code + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", default_flag='" + default_flag + '\'' +
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPostal_code() {
            return postal_code;
        }

        public void setPostal_code(String postal_code) {
            this.postal_code = postal_code;
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

        public String getDefault_flag() {
            return default_flag;
        }

        public void setDefault_flag(String default_flag) {
            this.default_flag = default_flag;
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
