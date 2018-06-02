package com.jixing.kd.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * author : LiHang.
 * data : 2016/11/16.
 * description:
 * 救援列表
 */
public class RescueListBean implements Serializable {


    /**
     * result : [{"id":"572ba3feef56498d808d4bf09cc2c713","city_code":"167","type_value":"1","head_portrait_url":"http://114.215.71.170:81/carhome/shop/fba00084793c4e7d861a1b6f152acfcd/thumbnail/1521609792639.JPG","shop_name":"东艺汽车电器","shop_score":"5","longitude":"121.60886","latitude":"38.906368","category":"[{\"key\":\"1\",\"value\":\"现场抢修\"},{\"key\":\"2\",\"value\":\"拖车\"},{\"key\":\"3\",\"value\":\"紧急加水\"},{\"key\":\"4\",\"value\":\"紧急送油\"},{\"key\":\"5\",\"value\":\"配钥匙\"}]","distance_list":["附近","1","3","5","10"],"tel_number_list":"18341850000","tel_num_list":["18341850000"],"city":"","detail_address":"辽宁省大连市沙河口区胜利路159号"},{"id":"78f66ec30ced42fb995e09b8b54a4502","city_code":"167","type_value":"1,2,3,4","head_portrait_url":"http://114.215.71.170:81/carhome/shop/8f060c58b2514abeb6f67334ac60ee0f/thumbnail/1516327679471.jpg","shop_name":"顺添汽车修理厂","shop_score":"5","longitude":"121.653249","latitude":"41.998975","category":"[{\"key\":\"1\",\"value\":\"现场抢修\"},{\"key\":\"2\",\"value\":\"拖车\"},{\"key\":\"3\",\"value\":\"紧急加水\"},{\"key\":\"4\",\"value\":\"紧急送油\"},{\"key\":\"5\",\"value\":\"配钥匙\"}]","distance_list":["附近","1","3","5","10"],"tel_number_list":"0418-3871113,13384184066","tel_num_list":["0418-3871113","13384184066"],"city":"大连市","detail_address":"海州区振兴路路北环峰钢材市场斜对过"},{"id":"34c99a7ea5894d5ea283fd5a8191fd55","city_code":"167","type_value":"1,2,3,4","head_portrait_url":"http://114.215.71.170:81/carhome/shop/cd8daa2d541f4e67826bf4affe6bd465/thumbnail/1516243036315.jpg","shop_name":"鑫南洋汽车用品销售服务中心","shop_score":"5","longitude":"121.674641","latitude":"42.038501","category":"[{\"key\":\"1\",\"value\":\"现场抢修\"},{\"key\":\"2\",\"value\":\"拖车\"},{\"key\":\"3\",\"value\":\"紧急加水\"},{\"key\":\"4\",\"value\":\"紧急送油\"},{\"key\":\"5\",\"value\":\"配钥匙\"}]","distance_list":["附近","1","3","5","10"],"tel_number_list":"13644186532","tel_num_list":["13644186532"],"city":"大连市","detail_address":"阜新市细河区富丽国际东门"}]
     * err_code : 0
     * err_message : OK
     * message : 查询救援服务公司成功
     */

    private String err_code;
    private String err_message;
    private String message;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "RescueListBean{" +
                "err_code='" + err_code + '\'' +
                ", err_message='" + err_message + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    public static RescueListBean objectFromData(String str) {

        return new Gson().fromJson(str, RescueListBean.class);
    }

    public static RescueListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), RescueListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<RescueListBean> arrayRescueListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<RescueListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<RescueListBean> arrayRescueListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<RescueListBean>>() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * id : 572ba3feef56498d808d4bf09cc2c713
         * city_code : 167
         * type_value : 1
         * head_portrait_url : http://114.215.71.170:81/carhome/shop/fba00084793c4e7d861a1b6f152acfcd/thumbnail/1521609792639.JPG
         * shop_name : 东艺汽车电器
         * shop_score : 5
         * longitude : 121.60886
         * latitude : 38.906368
         * category : [{"key":"1","value":"现场抢修"},{"key":"2","value":"拖车"},{"key":"3","value":"紧急加水"},{"key":"4","value":"紧急送油"},{"key":"5","value":"配钥匙"}]
         * distance_list : ["附近","1","3","5","10"]
         * tel_number_list : 18341850000
         * tel_num_list : ["18341850000"]
         * city :
         * detail_address : 辽宁省大连市沙河口区胜利路159号
         */

        private String id;
        private String city_code;
        private String type_value;
        private String head_portrait_url;
        private String shop_name;
        private String shop_score;
        private String longitude;
        private String latitude;
        private String category;
        private String tel_number_list;
        private String city;
        private String detail_address;
        private List<String> distance_list;
        private List<String> tel_num_list;

        private double distance;

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", city_code='" + city_code + '\'' +
                    ", type_value='" + type_value + '\'' +
                    ", head_portrait_url='" + head_portrait_url + '\'' +
                    ", shop_name='" + shop_name + '\'' +
                    ", shop_score='" + shop_score + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", category='" + category + '\'' +
                    ", tel_number_list='" + tel_number_list + '\'' +
                    ", city='" + city + '\'' +
                    ", detail_address='" + detail_address + '\'' +
                    ", distance_list=" + distance_list +
                    ", tel_num_list=" + tel_num_list +
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getType_value() {
            return type_value;
        }

        public void setType_value(String type_value) {
            this.type_value = type_value;
        }

        public String getHead_portrait_url() {
            return head_portrait_url;
        }

        public void setHead_portrait_url(String head_portrait_url) {
            this.head_portrait_url = head_portrait_url;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_score() {
            return shop_score;
        }

        public void setShop_score(String shop_score) {
            this.shop_score = shop_score;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTel_number_list() {
            return tel_number_list;
        }

        public void setTel_number_list(String tel_number_list) {
            this.tel_number_list = tel_number_list;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
        }

        public List<String> getDistance_list() {
            return distance_list;
        }

        public void setDistance_list(List<String> distance_list) {
            this.distance_list = distance_list;
        }

        public List<String> getTel_num_list() {
            return tel_num_list;
        }

        public void setTel_num_list(List<String> tel_num_list) {
            this.tel_num_list = tel_num_list;
        }
    }
}
