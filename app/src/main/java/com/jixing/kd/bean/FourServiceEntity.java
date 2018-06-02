package com.jixing.kd.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeeHang on 2018-06-02.
 * useTo：
 */

public class FourServiceEntity {


    /**
     * result : [{"id":"fba00084793c4e7d861a1b6f152acfcd","city_code":"167","repair_service":"5100","clean_service":"0","maintain_service":"6100","tyre_service":"0","shop_pic_url":"http://114.215.71.170:81/carhome/shop/fba00084793c4e7d861a1b6f152acfcd/thumbnail/1521609792639.JPG","shop_name":"东艺汽车电器","score":"5","detail_address":"辽宁省大连市沙河口区胜利路159号","tel_number_list":"18341850000","tel_num_list":["18341850000"],"longitude":"121.60886","latitude":"38.906368"},{"id":"d0fc69065d354d8c8f478b16fd3f6ed2","city_code":"167","repair_service":"5100","clean_service":"0","maintain_service":"0","tyre_service":"0","shop_pic_url":"http://114.215.71.170:81/carhome/shop/d0fc69065d354d8c8f478b16fd3f6ed2/thumbnail/1517457819446.JPG","shop_name":"万师傅汽修","score":"5","detail_address":"华东街南段","tel_number_list":"13795012112","tel_num_list":["13795012112"],"longitude":"121.701868","latitude":"42.032514"},{"id":"fdd250cac526480f986aaac5f7a3e0f9","city_code":"167","repair_service":"0","clean_service":"4100","maintain_service":"0","tyre_service":"0","shop_pic_url":"http://114.215.71.170:81/carhome/shop/fdd250cac526480f986aaac5f7a3e0f9/thumbnail/1517457479286.JPG","shop_name":"香轮宝骑","score":"5","detail_address":"矿工大街17-4-1门","tel_number_list":"0418-2166777,18342804567","tel_num_list":["0418-2166777","18342804567"],"longitude":"121.638592","latitude":"42.016916"},{"id":"776b9f5b2c404756a74c04f443219716","city_code":"167","repair_service":"0","clean_service":"4100","maintain_service":"0","tyre_service":"0","shop_pic_url":"http://114.215.71.170:81/carhome/shop/776b9f5b2c404756a74c04f443219716/thumbnail/1517457263182.JPG","shop_name":"海鑫汽车美饰广场","score":"5","detail_address":"海鑫国际南门","tel_number_list":"15542886676","tel_num_list":["15542886676"],"longitude":"121.67403","latitude":"42.034254"},{"id":"8f060c58b2514abeb6f67334ac60ee0f","city_code":"167","repair_service":"5100","clean_service":"0","maintain_service":"6100","tyre_service":"0","shop_pic_url":"http://114.215.71.170:81/carhome/shop/8f060c58b2514abeb6f67334ac60ee0f/thumbnail/1516327679471.jpg","shop_name":"顺添汽车修理厂","score":"5","detail_address":"海州区振兴路路北环峰钢材市场斜对过","tel_number_list":"0418-3871113,13384184066","tel_num_list":["0418-3871113","13384184066"],"longitude":"121.653249","latitude":"41.998975"},{"id":"cd8daa2d541f4e67826bf4affe6bd465","city_code":"167","repair_service":"5100","clean_service":"4100","maintain_service":"6100","tyre_service":"7100","shop_pic_url":"http://114.215.71.170:81/carhome/shop/cd8daa2d541f4e67826bf4affe6bd465/thumbnail/1516243036315.jpg","shop_name":"鑫南洋汽车用品销售服务中心","score":"5","detail_address":"阜新市细河区富丽国际东门","tel_number_list":"13644186532","tel_num_list":["13644186532"],"longitude":"121.674641","latitude":"42.038501"},{"id":"d62a709e19b643249f7787579b18e215","city_code":"167","repair_service":"0","clean_service":"0","maintain_service":"0","tyre_service":"7100","shop_pic_url":"http://114.215.71.170:81/carhome/shop/d62a709e19b643249f7787579b18e215/thumbnail/1512006869189.JPG","shop_name":"浩洋轮胎城（双钱轮胎）","score":"5","detail_address":"辽宁省大连市西岗区八一路222号","tel_number_list":"18341850044","tel_num_list":["18341850044"],"longitude":"121.641494","latitude":"38.884771"},{"id":"7f029580b89641508933ff2dde7943ac","city_code":"167","repair_service":"0","clean_service":"0","maintain_service":"6100","tyre_service":"0","shop_pic_url":"http://114.215.71.170:81/carhome/shop/7f029580b89641508933ff2dde7943ac/thumbnail/1512006662652.JPG","shop_name":"名扬换油中心","score":"4","detail_address":"辽宁省大连市中山区车站老汤面馆","tel_number_list":"18341850033","tel_num_list":["18341850033"],"longitude":"121.638979","latitude":"38.927308"},{"id":"cb153eb2a187465aa0414dc4980c34a2","city_code":"167","repair_service":"0","clean_service":"4100","maintain_service":"0","tyre_service":"0","shop_pic_url":"http://114.215.71.170:81/carhome/shop/cb153eb2a187465aa0414dc4980c34a2/thumbnail/1512006423385.JPG","shop_name":"路路顺汽车美容中心","score":"5","detail_address":"辽宁省大连市甘井子区第七人民医院","tel_number_list":"18341850022","tel_num_list":["18341850022"],"longitude":"121.529556","latitude":"38.881906"},{"id":"77dd08458db848d7b4b8e42208b3e5b2","city_code":"167","repair_service":"5100","clean_service":"0","maintain_service":"0","tyre_service":"0","shop_pic_url":"http://114.215.71.170:81/carhome/shop/77dd08458db848d7b4b8e42208b3e5b2/thumbnail/1512006164453.JPG","shop_name":"天添汽车修理","score":"4","detail_address":"辽宁省大连市沙河口区凌山三街9号","tel_number_list":"18341850000","tel_num_list":["18341850000"],"longitude":"121.580903","latitude":"38.943866"},{"id":"635cf30c92a94092a9ab1225fc343b6a","city_code":"167","repair_service":"5100","clean_service":"4100","maintain_service":"6100","tyre_service":"7100","shop_pic_url":"http://114.215.71.170:81/carhome/shop/635cf30c92a94092a9ab1225fc343b6a/thumbnail/1511755292183.JPG","shop_name":"兄弟汽车维修养护中心","score":"2","detail_address":"大连市华北路256号","tel_number_list":"18341850901","tel_num_list":["18341850901"],"longitude":"121.587434","latitude":"38.943634"},{"id":"2a843698753f471a8923f0bccf1d8d35","city_code":"167","repair_service":"5100","clean_service":"4100","maintain_service":"6100","tyre_service":"7100","shop_pic_url":"http://114.215.71.170:81/carhome/shop/2a843698753f471a8923f0bccf1d8d35/thumbnail/1511144623005.JPG","shop_name":"阜新兄弟汽车修理厂","score":"5","detail_address":"海州区工业街88号","tel_number_list":"0418-3980400,13514183400","tel_num_list":["0418-3980400","13514183400"],"longitude":"121.682343","latitude":"42.027303"}]
     * err_code : 0
     * err_message : OK
     * message : 查询四类服务公司成功
     */

    private String err_code;
    private String err_message;
    private String message;
    private List<ResultBean> result;

    @Override
    public String toString() {
        return "FourServiceEntity{" +
                "err_code='" + err_code + '\'' +
                ", err_message='" + err_message + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    public static FourServiceEntity objectFromData(String str) {

        return new Gson().fromJson(str, FourServiceEntity.class);
    }

    public static FourServiceEntity objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), FourServiceEntity.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<FourServiceEntity> arrayFourServiceEntityFromData(String str) {

        Type listType = new TypeToken<ArrayList<FourServiceEntity>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<FourServiceEntity> arrayFourServiceEntityFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<FourServiceEntity>>() {
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

    public static class ResultBean {
        /**
         * id : fba00084793c4e7d861a1b6f152acfcd
         * city_code : 167
         * repair_service : 5100
         * clean_service : 0
         * maintain_service : 6100
         * tyre_service : 0
         * shop_pic_url : http://114.215.71.170:81/carhome/shop/fba00084793c4e7d861a1b6f152acfcd/thumbnail/1521609792639.JPG
         * shop_name : 东艺汽车电器
         * score : 5
         * detail_address : 辽宁省大连市沙河口区胜利路159号
         * tel_number_list : 18341850000
         * tel_num_list : ["18341850000"]
         * longitude : 121.60886
         * latitude : 38.906368
         */

        private String id;
        private String city_code;
        private String repair_service;
        private String clean_service;
        private String maintain_service;
        private String tyre_service;
        private String shop_pic_url;
        private String shop_name;
        private String score;
        private String detail_address;
        private String tel_number_list;
        private String longitude;
        private String latitude;
        private List<String> tel_num_list;

        private String distance;

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
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

        public String getRepair_service() {
            return repair_service;
        }

        public void setRepair_service(String repair_service) {
            this.repair_service = repair_service;
        }

        public String getClean_service() {
            return clean_service;
        }

        public void setClean_service(String clean_service) {
            this.clean_service = clean_service;
        }

        public String getMaintain_service() {
            return maintain_service;
        }

        public void setMaintain_service(String maintain_service) {
            this.maintain_service = maintain_service;
        }

        public String getTyre_service() {
            return tyre_service;
        }

        public void setTyre_service(String tyre_service) {
            this.tyre_service = tyre_service;
        }

        public String getShop_pic_url() {
            return shop_pic_url;
        }

        public void setShop_pic_url(String shop_pic_url) {
            this.shop_pic_url = shop_pic_url;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
        }

        public String getTel_number_list() {
            return tel_number_list;
        }

        public void setTel_number_list(String tel_number_list) {
            this.tel_number_list = tel_number_list;
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

        public List<String> getTel_num_list() {
            return tel_num_list;
        }

        public void setTel_num_list(List<String> tel_num_list) {
            this.tel_num_list = tel_num_list;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", city_code='" + city_code + '\'' +
                    ", repair_service='" + repair_service + '\'' +
                    ", clean_service='" + clean_service + '\'' +
                    ", maintain_service='" + maintain_service + '\'' +
                    ", tyre_service='" + tyre_service + '\'' +
                    ", shop_pic_url='" + shop_pic_url + '\'' +
                    ", shop_name='" + shop_name + '\'' +
                    ", score='" + score + '\'' +
                    ", detail_address='" + detail_address + '\'' +
                    ", tel_number_list='" + tel_number_list + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", tel_num_list=" + tel_num_list +
                    '}';
        }
    }
}
