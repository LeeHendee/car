package com.example.gtercn.car.api;


import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.interfaces.ResponseJSONObjectListener;
import com.example.gtercn.car.interfaces.ResponseStringListener;
import com.example.gtercn.car.net.THttpOpenHelper;
import com.example.gtercn.car.net.THttpsOpenHelper;
import com.example.gtercn.car.utils.TLog;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2016-5-24.
 * 网络加载数据接口，数据源在服务器
 */
public class ApiHttp {

    private static final String TAG = "ApiHttp";


//
//    /**
//     * 登陆接口
//     *
//     * @param map     请求头;
//     * @param handler 相应结果回调;
//     * @param type
//     * @param tag
//     */
//    public static void login(Map<String, String> map, ResponseCallbackHandler handler, int type,
//                             String tag) {
//
////        THttpOpenHelper.newInstance().requestPost(ApiManager.URL_LOGIN, map, handler, type, tag);
//
//
//        THttpsOpenHelper.newInstance().requestPost(ApiManager.URL_LOGIN, map, handler, type, tag);
//    }
//

    /**
     * 自驾游列表
     *
     * @param map
     * @param handler
     * @param type
     * @param tag
     */
    public static void SelfDriving(Map<String, String> map, ResponseCallbackHandler handler, int type, String tag) {
        String token = map.get("token");
        TLog.i(TAG, "-------------------->" + token);
        if (token.equals("")) {
            TLog.i(TAG, "-------------SelfDriving------>");
            String Url = ApiManager.URL_SELF_TRAVEL;
            THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
            tHttpOpenHelper.requestPostNo(Url, handler, type, tag);
        } else {
            String Url = ApiManager.URL_SELF_TRAVEL + "?token=" + token;
            TLog.i(TAG, "-------------------->" + Url);
            THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
            tHttpOpenHelper.requestPostNo(Url, handler, type, tag);
        }
    }

    /**
     * 自驾游详情评论
     *
     * @param jsonObject
     * @param handler
     * @param type
     * @param tag
     */
    public static void SelfComment(JSONObject jsonObject, ResponseJSONObjectListener handler, int type, String tag) {
        String Url = ApiManager.URL_SELF_COMMENT;
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(Url, jsonObject, handler, type, tag);
    }

    /**
     * 自驾游详情评论子评论
     *
     * @param jsonObject
     * @param handler
     * @param type
     * @param tag
     */
    public static void SelfSubComment(JSONObject jsonObject, ResponseJSONObjectListener handler, int type, String tag) {
        String Url = ApiManager.URL_SELF_SUBCLASS_COMMENT;
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(Url, jsonObject, handler, type, tag);
    }

    /**
     * 自驾游详情评论回复
     *
     * @param jsonObject
     * @param handler
     * @param type
     * @param tag
     */
    public static void SelfCommentReply(Map<String, String> map, JSONObject jsonObject, ResponseJSONObjectListener handler, int type, String tag) {
        String token = map.get("token");
        String t = map.get("t");
        String sign = map.get("sign");
        String Url = ApiManager.URL_SELF_COMMENT_REPLY + "?token=" + token + "&t=" + t + "&sign=" + sign;
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(Url, jsonObject, handler, type, tag);
    }

    /**
     * 自驾游详情评论子评论回复
     *
     * @param jsonObject
     * @param handler
     * @param type
     * @param tag
     */
    public static void SelfSubCommentReply(Map<String, String> map, JSONObject jsonObject, ResponseJSONObjectListener handler, int type, String tag) {
        String token = map.get("token");
        String t = map.get("t");
        String sign = map.get("sign");
        String Url = ApiManager.URL_SELF_SUBCLASS_COMMENT_REPLY + "?token=" + token + "&t=" + t + "&sign=" + sign;
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(Url, jsonObject, handler, type, tag);
    }

    /**
     * 自驾游发布
     */
    public static void SelfIssue(String url, Map filemap, Map map, ResponseStringListener handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestFormDataFilePost(url, map, filemap, handler, type, tag);
    }



    /**
     * 促销
     *
     * @param map
     * @param handler
     * @param type
     * @param tag
     */
    public static void Promotion(String url,JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(url, map, handler, type, tag);
    }

    /**
     * 促销搜索
     *
     * @param map
     * @param handler
     * @param type
     * @param tag
     */
    public static void PromotionSearch(Map map, Map filemap,ResponseStringListener handler, int type, String tag) {
        String Url = ApiManager.URL_PROMOTION_SEARCH;
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestFormDataFilePost(Url, map, filemap,handler, type, tag);
    }

    /**
     * 咨询
     *
     * @param map
     * @param handler
     * @param type
     * @param tag
     */
    public static void Message(JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        String url = ApiManager.URL_MESSAGE;
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(url, map, handler, type, tag);
    }

    /**
     * 咨询搜索
     *
     * @param map
     * @param handler
     * @param type
     * @param tag
     */
    public static void MessageSearch(Map map, Map filemap,ResponseStringListener handler, int type, String tag) {
        String Url = ApiManager.URL_MESSAGE_SEARCH;
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestFormDataFilePost(Url, map, filemap,handler, type, tag);
    }

    /**
     * 广告条
     *
     * @param map
     * @param handler
     * @param type
     * @param tag
     */
    public static void HomeAd(JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        String Url = ApiManager.URL_AD;
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(Url, map,handler, type, tag);
    }

    public static void Rescue(JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        String Url = ApiManager.URL_RESCUE;
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(Url, map, handler, type, tag);
    }

    /**
     * 退去登录
     *
     * @param map
     * @param handler
     * @param type
     * @param tag
     */
    public static void Logout(JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        String Url = ApiManager.URL_LOGOUT;
        THttpsOpenHelper tHttpsOpenHelper = THttpsOpenHelper.newInstance();
        tHttpsOpenHelper.requestJsonObjectPost(Url, map, handler, type, tag);
    }

    /**
     * 添加收藏
     */
    public static void FavorAdd(String token, JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {

        String url = ApiManager.URL_FAVORADD + "?token=" + token + "&t=" + "12312312" + "&sign=" + "1231231";
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(url, map, handler, type, tag);
    }

    /**
     * 删除收藏
     */
    public static void FavorDel(String token, JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {

        String url = ApiManager.URL_FAVORDEL + "?token=" + token + "&t=" + "3123" + "&sign=" + "1313";
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(url, map, handler, type, tag);
    }

    /**
     * 自驾游报名
     */
    public static void SelfEnrol(String url, JSONObject jsonObject, ResponseJSONObjectListener handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(url, jsonObject, handler, type, tag);
    }
    /**
     * 自驾游取消报名
     */
    public static void SelfRemoveEnrol(String url, JSONObject jsonObject, ResponseJSONObjectListener handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(url, jsonObject, handler, type, tag);
    }

//    ----------------------------------------------------- 以下是电商部分 -----------------------------------------------

    /**
     * 电商首页轮播图
     */
    public static void getBanner(String url, ResponseCallbackHandler handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestGet(url, handler, type, tag);
    }

    /**
     * 电商首页分类
     */
    public static void getClassify(String url, ResponseCallbackHandler handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestGet(url, handler, type, tag);
    }

    /**
     * 电商首页秒杀列表
     */
    public static void getSeckill(String url, ResponseCallbackHandler handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestGet(url, handler, type, tag);
    }

    /**
     * 电商品牌介绍列表
     */
    public static void getBrandList(String url, ResponseCallbackHandler handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestGet(url, handler, type, tag);
    }

    /**
     * 电商品牌介绍列表
     */
    public static void getProductList(String url, ResponseCallbackHandler handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestGet(url, handler, type, tag);
    }

    /**
     * 电商品牌介绍列表
     */
    public static void getProductDetail(String url, ResponseCallbackHandler handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestGet(url, handler, type, tag);
    }

    /**
     * 电商购物车
     */
    public static void getCartInfo(String url, ResponseCallbackHandler handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestGet(url, handler, type, tag);
    }

    /**
     * 电商购物车变更商品数量
     */
    public static void changeCount(String url,Map<String ,String > map, ResponseStringListener handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestFormDataFilePost(url,map,null,handler,type,tag);
    }

    /**
     * 电商商品列表筛选
     */
    public static void sortProduct(String url,ResponseCallbackHandler handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestGet(url,handler,type,tag);
    }

    /**
     * 电商新增收获地址
     */
    public static void submitNewAd(String url, JSONObject params, ResponseJSONObjectListener handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestJsonObjectPost(url, params, handler, type, tag);
    }

    /**
     * 电商删除购物车产品
     */
    public static void delCartItem(String url, Map<String, String> map, ResponseStringListener handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestFormDataFilePost(url, map, null, handler, type, tag);
    }

    /**
     * 电商地址管理列表
     */
    public static void getAddressList(String url,ResponseCallbackHandler handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestGet(url,handler,type,tag);
    }

    /**
     * 电商地址管理列表-设置默认收货地址
     */
    public static void setDefaultAddress(String url, Map<String, String> map, ResponseStringListener handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestFormDataFilePost(url, map, null, handler, type, tag);
    }

    /**
     * 电商地址管理列表-删除地址
     */
    public static void delAddress(String url, Map<String, String> map, ResponseStringListener handler, int type, String tag) {
        THttpOpenHelper tHttpOpenHelper = THttpOpenHelper.newInstance();
        tHttpOpenHelper.requestFormDataFilePost(url, map, null, handler, type, tag);
    }
}
