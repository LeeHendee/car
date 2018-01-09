package com.example.gtercn.car.mall.entity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/9.
 * Used to :
 */

public class AddressEntity {

    /**
     * result : [{"id":"11bd2214e4dd4dd0b331239db5aa19da","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","province":"辽宁省","city":"大连市","district":"高新区","address":"高能街42号","postal_code":"116000","name":"张三","phone":"18642690085","default_flag":"N","insert_time":1515313198000,"update_time":1515317877000},{"id":"4139fcda1e5c4badb299ae4182180cbd","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","province":"辽宁省","city":"大连市","district":"中山区","address":"独立街12号012","postal_code":"116000","name":"李四","phone":"18642690085","default_flag":"N","insert_time":1515314153000,"update_time":1515483675000},{"id":"50092d7161254033bd6d5dc17fcb4ed9","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","province":"辽宁","city":"大连","district":"沙河口区","postal_code":"122100","name":"lee","phone":"888888","default_flag":"Y","insert_time":1515491063000,"update_time":1515491063000},{"id":"5471fef3dad84ddca3289eaf18866690","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","province":"辽宁","city":"大连","district":"沙河口区","postal_code":"122100","name":"lee","phone":"888888","default_flag":"N","insert_time":1515491061000,"update_time":1515491062000},{"id":"59ef83c7ada2429780ee1edc58abd899","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","province":"辽宁省","city":"大连市","district":"沙河口区","address":"交通大学","postal_code":"116000","name":"Lee","phone":"116000","default_flag":"N","insert_time":1515491525000,"update_time":1515491525000},{"id":"640b4adb25614c0a90653c971f6ce852","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","province":"辽宁","city":"大连","district":"沙河口区","address":"交大附近","postal_code":"122100","name":"lee","phone":"888888","default_flag":"N","insert_time":1515483675000,"update_time":1515491057000},{"id":"8d259bc6304d4f10ac8ee47b89659405","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","province":"辽宁","city":"大连","district":"沙河口区","postal_code":"122100","name":"lee","phone":"888888","default_flag":"N","insert_time":1515491062000,"update_time":1515491063000},{"id":"ac250bd71d6a408285529c6fe2a8075b","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","province":"辽宁省","city":"大连市","district":"旅顺口区","address":"你猜","postal_code":"116000","name":"嘿嘿","phone":"116000","default_flag":"N","insert_time":1515491716000,"update_time":1515491716000},{"id":"ced86bb4afa0485781665aea68e69597","user_id":"0779243b57824b0da9e79ed4a7f8f4b6","province":"辽宁","city":"大连","district":"沙河口区","postal_code":"122100","name":"lee","phone":"888888","default_flag":"N","insert_time":1515491057000,"update_time":1515491061000}]
     * err_code : 0
     * err_message : OK
     */

    private String err_code;
    private String err_message;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "AddressEntity{" +
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
         * id : 11bd2214e4dd4dd0b331239db5aa19da
         * user_id : 0779243b57824b0da9e79ed4a7f8f4b6
         * province : 辽宁省
         * city : 大连市
         * district : 高新区
         * address : 高能街42号
         * postal_code : 116000
         * name : 张三
         * phone : 18642690085
         * default_flag : N
         * insert_time : 1515313198000
         * update_time : 1515317877000
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
