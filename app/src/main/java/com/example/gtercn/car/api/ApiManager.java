package com.example.gtercn.car.api;

import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.interfaces.ResponseJSONObjectListener;
import com.example.gtercn.car.interfaces.ResponseStringListener;
import com.example.gtercn.car.utils.Constants;
import com.example.gtercn.car.utils.TLog;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2016-5-18.
 * 接口管理类
 */
public class ApiManager {
    private static final String TAG = ApiManager.class.getSimpleName();

    /**
     * sharePreference file's name
     */
    public static final String SHARE_NAME = "Car";

    /**
     * 向导控制参数，
     * true: 运行了向导
     * false：没有运行向导
     */
    public static final String APP_FIRST = "hasGuide";

    /**
     * 城市编码字段
     */
    public static final String CITY_CODE = "city_code";

    /**
     * latitude, longitude.
     */
    public static final String LAT = "latitude";
    public static final String LNG = "longitude";


    /**
     * 域名或服务器 + 端口号
     */
    //内网
//    private static final String HTTPS = "https://192.168.1.192/car_home/app/v1";
//    private static final String HTTP = "http://192.168.1.192/car_home/app/v1";

    //外网测试环境
//    private static final String HTTPS = "https://api.shunjiatianxia.com/car_home/app/v1";
//    private static final String HTTP = "http://api.shunjiatianxia.com/car_home/app/v1";

    private static final String HTTPS = "https://114.215.71.170/car_home/app/v1";
    private static final String HTTP = "http://114.215.71.170/car_home/app/v1";

    private static final String YIZHAN_HTTP = "http://114.215.71.170/car_home/app/v1";

    private static final String BaseUrl = "http://114.215.71.170/car_inn/v1/";
//    private static final String BaseUrl = "http://inn.ngrok.xiaomiqiu.cn/car_inn/v1/";

    public static final String URL_RESCUE = HTTP + "/open/service/rescuelist";

    public static final String URL_APPOINTMENT = "";

    /**
     * 同步服务器时间
     */
    public static final String URL_TIMESTAMP = HTTP + "/open/server/resources";

    /**
     * 登录地址
     */

    public static final String URL_LOGIN = HTTPS + "/account/open/login";

    /**
     * 无用户名和密码登录地址
     */

    public static final String URL_AUTO_LOGIN = HTTP + "/account/open/info";

    /**
     * 注册地址
     */
    public static final String URL_REGISTER = HTTPS + "/account/open/register";
    /**
     * 更新头像
     */
    public static final String URL_HEADER = HTTPS + "/user/avatar/change";
    /**
     * 退出登录
     */
    public static final String URL_LOGOUT = HTTPS + "/account/open/logout";
    /**
     * 注册获取验证码
     */
    public static final String URL_VERIFICATION = HTTPS + "/account/open/verify/unregister";

    /**
     * 我的收藏
     */
    public static final String URL_MY_FAVORADD = HTTP + "/user/personal/favor";

    /**
     * 添加收藏
     */
    public static final String URL_FAVORADD = HTTP + "/favor/add";

    /**
     * 删除收藏
     */
    public static final String URL_FAVOR_DEL = HTTP + "/favor/delete";


    /**
     * 删除收藏
     */
    public static final String URL_FAVORDEL = HTTP + "/favor/delete";
    /**
     * 达人榜分类
     */
    public static final String URL_EXPERT_TYPE = HTTP + "/open/expert/type";

    /**
     * 达人榜
     */
    public static final String URL_EXPERT_TOP = HTTP + "/open/expert/top";

    /**
     * 达人圈列表
     */
    public static final String URL_EXPERT_CIRCLE_LIST = HTTP + "/expert/open/article/list";
    /**
     * 达人圈浏览数增加
     */
    public static final String URL_ARTICLE_GLANCENUM = HTTP + "/expert/open/article/glancenum";

    /**
     * 达人圈详情页查询
     */
    public static final String URL_ARTICLE_DETAIL_QUERY = HTTP + "/expert/open/article/query";
    /**
     * 问题墙列表
     */
    public static final String URL_QUESTION_WALL_LIST = HTTP + "/expert/open/question/list";

    /**
     * 问题墙详情页;
     */
    public static final String URL_QUESTION_WALL_DETAIL = HTTP + "/expert/open/question/query";

    /**
     * 回复列表
     */
    public static final String URL_REPLY_LIST = HTTP + "/expert/reply/list";

    /**
     * 提交回复
     */
    public static final String URL_REPLY_SUBMIT = HTTP + "/expert/reply/add";

    /**
     * 提交问题
     */
    public static final String URL_QUESTION_SUBMIT = HTTP + "/expert/question/add";
    /**
     * 主页广告条
     */
    public static final String URL_AD = YIZHAN_HTTP + "/open/home/advert/list";

    /**
     * 忘记密码
     */
    public static final String URL_FORGETPASSWORD = HTTPS + "/account/open/password/forget";

    /**
     * 修改手机号码第一步
     */
    public static final String URL_CHAGEPHONEONE = HTTPS + "/account/phone/change";

    /**
     * 修改手机号码第二步
     */
    public static final String URL_CHAGEPHONETWO = HTTPS + "/account/phone/change/next";

    /**
     * 忘记密码手机验证码
     */
    public static final String URL_REGISTERVERIFY = HTTPS + "/account/open/verify/register";

    /**
     * 自驾游列表
     */
    public static final String URL_SELF_TRAVEL = HTTP + "/selfdriving/open/list";
    /**
     * 自驾游详情评论列表
     */
    public static final String URL_SELF_COMMENT = HTTP + "/selfdriving/open/comment/list";
    /**
     * 自驾游详情评论子评论列表
     */
    public static final String URL_SELF_SUBCLASS_COMMENT = HTTP + "/selfdriving/open/comment/reply/list";
    /**
     * 自驾游详情评论回复
     */
    public static final String URL_SELF_COMMENT_REPLY = HTTP + "/selfdriving/comment";
    /**
     * 自驾游详情评论子评论回复
     */
    public static final String URL_SELF_SUBCLASS_COMMENT_REPLY = HTTP + "/selfdriving/comment/reply";
    /**
     * 自驾游发布
     */
    public static final String URL_SELF_ISSUE = HTTP + "/selfdriving/release";
    /**
     * 自驾游报名
     */
    public static final String URL_SELF_ENROL = HTTP + "/selfdriving/enrol";
    /**
     * 自驾游取消报名
     */
    public static final String URL_SELF_REMOVE_ENROL = HTTP + "/selfdriving/remove/enrol";
    /**
     * 自驾游活动取消
     */
    public static final String URL_SELF_CANCEL = HTTP + "/selfdriving/cancel";
    /**
     * 自驾游详情
     */
    public static final String URL_SELF_DETAIL = HTTP + "/selfdriving/open/searchone";
    /**
     * 自驾游名单
     */
    public static final String URL_SELF_ROLL = HTTP + "/selfdriving/searchname";
    /**
     * 我的活动列表
     */
    public static final String URL_MY_ACTIVITY = HTTP + "/selfdriving/activity/list";

    /**
     * 四服务列表
     */
    public static final String URL_FOUR_SERVE = HTTP + "/open/service/servicetype/list";
    /**
     * 四服务详情
     */
    public static final String URL_FOUR_SERVE_DETAIL = HTTP + "/open/service/servicetype/detail";
    /**
     * 促销
     */
    public static final String URL_PROMOTION = HTTP + "/open/promotion/list";
    /**
     * 促销详情
     */
    public static final String URL_PROMOTION_DETAIL = HTTP + "/open/promotion/query";
    /**
     * 促销搜索
     */
    public static final String URL_PROMOTION_SEARCH = HTTP + "/open/promotion/search";
    /**
     * 咨询
     */
    public static final String URL_MESSAGE = HTTP + "/open/information/list";
    /**
     * 咨询详情
     */
    public static final String URL_MESSAGE_DETAIL = HTTP + "/open/information/query";
    /**
     * 救援详情
     */
    public static final String URL_RESCURE_DETAIL = HTTP + "/open/service/rescuetype/detail";
    /**
     * Home搜索
     */
    public static final String URL_SEARCH_HOME = HTTP + "/open/home/search";
    /**
     * 咨询搜索
     */
    public static final String URL_MESSAGE_SEARCH = HTTP + "/open/information/search";

    /**
     * 我的提问
     */
    public static final String URL_MY_QUESTION = HTTP + "/user/personal/question";


    /**
     * 我的文章
     */
    public static final String URL_MY_ARTICLE = HTTP + "/user/personal/article";

    /**
     * 意见反馈
     */
    public static final String URL_FEEDBACK = HTTP + "/feedback/advice";

    /**
     * 个人信息
     */
    public static final String URL_PERSONAL_INFO = HTTP + "/user/personal/info";

    /**
     * 修改个人信息
     */
    public static final String URL_PERSONAL_INFO_CHANGE = HTTPS + "/user/userinfo/change";


    /**
     * 救援列表
     */
    public static final String URL_RESCUE_LIST = HTTP + "/open/service/rescue/list";

    /**
     * 城市列表
     */
    public static final String URL_CITY_LIST = YIZHAN_HTTP + "/open/home/city/list";


    /**
     * isHttp 控制接口数据
     * true:从网络访问数据
     * false：从本地加载仿真数据
     */
    public static final Boolean isHttp = true;

//    ---------------------------------- 以下是电商部分API_URL -------------------------------

    /**
     * 电商首页banner
     */
    public static final String URL_HOME_BANNER = BaseUrl + "open/advertisement/list";

    /**
     * 电商首页分类
     */
    public static final String URL_HOME_CLASSIFY = BaseUrl + "open/goods/category/list";

    /**
     * 电商首页秒杀列表
     */
    public static final String URL_HOME_SECKILL = BaseUrl + "open/goods/hot";

    /**
     * 电商品牌介绍
     */
    public static final String URL_BRAND = BaseUrl + "open/goods/brand/list";

    /**
     * 电商某品牌下列表
     */
    public static final String URL_BRAND_PRODUCT_LIST = BaseUrl + "open/goods/brand/search";

    /**
     * 电商商品详情
     */
    public static final String URL_PRODUCT_DETAIL = BaseUrl + "open/goods/page";

    /**
     * 电商购物车
     */
    public static final String URL_CART_INFO = BaseUrl + "cart/list";

    /**
     * 电商变更购物车商品数量
     */
    public static final String URL_CHANGE_COUNT = BaseUrl + "/cart/number";

    /**
     * 电商商品列表(排序)
     */
    public static final String URL_SORT_PRODUCT = BaseUrl + "open/goods/sort";

    /**
     * 电商新增地址；
     */
    public static final String URL_SUBMIT_NEW_AD = BaseUrl + "address/add";

    /**
     * 电商编辑地址；
     */
    public static final String URL_EDIT_ADDRESS = BaseUrl + "address/update";

    /**
     * 电商删除购物车
     */
    public static final String URL_DEL_PRODUCT = BaseUrl + "/cart/delete";

    /**
     * 电商加入到购物车
     */
    public static final String URL_ADD_CART = BaseUrl + "/cart/add";

    /**
     * 电商地址管理列表
     */
    public static final String URL_MANAGE_ADDRESS = BaseUrl + "address/list";

    /**
     * 电商地址管理列表-设置默认收货地址
     */
    public static final String URL_SET_DEFAULT_ADDRESS = BaseUrl + "address/default/set";

    /**
     * 电商获取默认收货地址
     */
    public static final String URL_GET_DEFAULT_ADDRESS = BaseUrl + "address/default/get";

    /**
     * 电商地址管理列表-删除地址
     */
    public static final String URL_DEL_ADDRESS = BaseUrl + "address/delete";

    /**
     * 电商生成预订单
     */
    public static final String URL_PRE_ORDER = BaseUrl + "/order/settlement";

    /**
     * 电商订单列表
     */
    public static final String URL_ORDER_LIST = BaseUrl + "/order/list";

    /**
     * 电商订单详情
     */
    public static final String URL_ORDER_DETAIL = BaseUrl + "/order/detail";

    /**
     * 电商产品属性
     */
    public static final String URL_PROPERTY_LIST = BaseUrl + "open/goods/spec/list";

    /**
     * 电商生成预订单接口
     */
    public static final String URL_CREATE_PRE_ORDER = BaseUrl + "order/tobuy";

    /**
     * 电商获取商品的评论
     */
    public static final String URL_PRODUCT_REVIEWS = BaseUrl + "open/comment/list";

    /**
     * 电商发布评论
     */
    public static final String URL_POST_REVIEW = BaseUrl + "comment/add";

    /**
     * 电商搜索页面
     */
    public static final String URL_SEARCH_PAGE = BaseUrl + "open/search/list";

    /**
     * 电商清空搜索记录
     */
    public static final String URL_HISTORY_CLEAR = BaseUrl + "open/search/delete";

    /**
     * 电商搜索商品
     */
    public static final String URL_DO_SEARCH = BaseUrl + "open/search/goods";

    /**
     * 电商增加搜索数据
     */
    public static final String URL_ADD_HISTORY = BaseUrl + "open/search/add";

    /**
     * 电商调用支付宝前获取签名接口
     */
    public static final String URL_GET_ALIPAY_SIGN= BaseUrl + "ali/pay";

    /**
     * 过滤属性
     */
    public static final String URL_FILTER_PROPERTY = BaseUrl + "open/goods/search";

    /**
     * 取消订单
     */
    public static final String URL_ORDER_CANCEL = BaseUrl + "order/cancel";

    /**
     * 删除订单
     */
    public static final String URL_ORDER_DEL = BaseUrl + "order/delete";


    /**
     * 获取助销达人列表
     */
    public static final String URL_GET_EXPERT_LIST = BaseUrl + "open/expert/list";

//    --------------------------------------以上是电商部分---------------------------------------
    /**
     * 自驾游列表
     *
     * @param map
     * @return
     */
    public static void SelfDriving(Map<String, String> map, ResponseCallbackHandler handler, int type,
                                   String tag) {
        if (isHttp) {
            ApiHttp.SelfDriving(map, handler, type, tag);
        } else {
            ApiLocal.SelfDriving(handler, type, tag);
        }
    }

    /**
     * 自驾游详情评论
     *
     * @param jsonObject
     * @return
     */
    public static void SelfComment(JSONObject jsonObject, ResponseJSONObjectListener handler, int type,
                                   String tag) {
        if (isHttp) {
            TLog.i(TAG, "------>" + "CarWash");
            ApiHttp.SelfComment(jsonObject, handler, type, tag);
        } else {
            // ApiLocal.SelfComment(handler, type, tag);
        }
    }

    /**
     * 自驾游详情评论子评论
     *
     * @param jsonObject
     * @return
     */
    public static void SelfSubComment(JSONObject jsonObject, ResponseJSONObjectListener handler, int type,
                                      String tag) {
        if (isHttp) {
            TLog.i(TAG, "------>" + "CarWash");
            ApiHttp.SelfSubComment(jsonObject, handler, type, tag);
        } else {
            // ApiLocal.SelfComment(handler, type, tag);
        }
    }

    /**
     * 自驾游详情评论回复
     */
    public static void SelfCommentReply(Map<String, String> map, JSONObject jsonObject, ResponseJSONObjectListener handler, int type,
                                        String tag) {
        if (isHttp) {
            TLog.i(TAG, "------>" + "CarWash");
            ApiHttp.SelfCommentReply(map, jsonObject, handler, type, tag);
        } else {
            // ApiLocal.SelfComment(handler, type, tag);
        }
    }

    /**
     * 自驾游详情评论子评论回复
     */
    public static void SelfSubCommentReply(Map<String, String> map, JSONObject jsonObject, ResponseJSONObjectListener handler, int type,
                                           String tag) {
        if (isHttp) {
            TLog.i(TAG, "------>" + "CarWash");
            ApiHttp.SelfSubCommentReply(map, jsonObject, handler, type, tag);
        } else {
            // ApiLocal.SelfComment(handler, type, tag);
        }
    }

    /**
     * 自驾游发布
     */
    public static void SelfIssue(String url, Map filemap, Map map, ResponseStringListener handler, int type,
                                 String tag) {
        if (isHttp) {
            ApiHttp.SelfIssue(url, filemap, map, handler, type, tag);
        } else {
            //  ApiLocal.Message(handler, type, tag);
        }
    }

    /**
     * 自驾游报名
     */
    public static void SelfEnrol(String url, JSONObject jsonObject, ResponseJSONObjectListener handler, int type, String tag) {
        if (isHttp) {

            ApiHttp.SelfEnrol(url, jsonObject, handler, type, tag);
        } else {
            //  ApiLocal.Message(handler, type, tag);
        }
    }

    /**
     * 自驾游取消报名
     */
    public static void SelfRemoveEnrol(String url, JSONObject jsonObject, ResponseJSONObjectListener handler, int type, String tag) {
        if (isHttp) {

            ApiHttp.SelfRemoveEnrol(url, jsonObject, handler, type, tag);
        } else {
            //  ApiLocal.Message(handler, type, tag);
        }
    }

    /**
     * 促销列表
     *
     * @param map
     * @return
     */
    public static void Promotion(String url, JSONObject map, ResponseJSONObjectListener handler, int type,
                                 String tag) {
        if (isHttp) {
            ApiHttp.Promotion(url, map, handler, type, tag);
        } else {
            //  ApiLocal.Promotion(handler, type, tag);
        }
    }

    /**
     * 促销搜索列表
     *
     * @param map
     * @return
     */
    public static void PromotionSearch(Map map, Map filemap, ResponseStringListener handler, int type,
                                       String tag) {
        if (isHttp) {
            ApiHttp.PromotionSearch(map, filemap, handler, type, tag);
        } else {
            //  ApiLocal.Promotion(handler, type, tag);
        }
    }

    /**
     * 咨询列表
     *
     * @param map
     * @return
     */
    public static void Message(JSONObject map, ResponseJSONObjectListener handler, int type,
                               String tag) {
        if (isHttp) {
            ApiHttp.Message(map, handler, type, tag);
        } else {
            //  ApiLocal.Message(handler, type, tag);
        }
    }

    /**
     * 咨询搜索列表
     *
     * @param map
     * @return
     */
    public static void MessageSearch(Map map, Map filemap, ResponseStringListener handler, int type,
                                     String tag) {
        if (isHttp) {
            ApiHttp.MessageSearch(map, filemap, handler, type, tag);
        } else {
            //  ApiLocal.Message(handler, type, tag);
        }
    }

    /**
     * 广告条
     */
    public static void HomeAd(JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        if (isHttp) {
            ApiHttp.HomeAd(map, handler, type, tag);
        } else {
            // ApiLocal.HomeAd(handler, type, tag);
        }
    }

    /**
     * 退去登录
     */
    public static void Logout(JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        if (isHttp) {
            ApiHttp.Logout(map, handler, type, tag);
        } else {
            //   ApiLocal.Logout(handler,type,tag);
        }
    }

    /**
     * 添加收藏
     */
    public static void FavorAdd(String token, JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        if (isHttp) {
            ApiHttp.FavorAdd(token, map, handler, type, tag);
        } else {
            //   ApiLocal.Logout(handler,type,tag);
        }
    }

    /**
     * 删除收藏
     */
    public static void FavorDel(String token, JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        if (isHttp) {
            ApiHttp.FavorDel(token, map, handler, type, tag);
        } else {
            //   ApiLocal.Logout(handler,type,tag);
        }
    }

    //------------------------------------------------- 以下是电商部分--------------------------------------------------------

    /**
     * 首页轮播图
     */
    public static void getBanner(ResponseCallbackHandler handler, int type, String tag) {
        String url = ApiManager.URL_HOME_BANNER + "?city_code=0411";
        ApiHttp.getBanner(url, handler, type, tag);
    }

    /**
     * 首页分类
     */
    public static void getClassify(ResponseCallbackHandler handler, int type, String tag) {
        String url = ApiManager.URL_HOME_CLASSIFY;
        ApiHttp.getClassify(url, handler, type, tag);
    }

    /**
     * 首页秒杀产品列表
     */
    public static void getSeckill(ResponseCallbackHandler handler, int type, String tag) {
        String url = ApiManager.URL_HOME_SECKILL + "?city_code=0411";
        ApiHttp.getSeckill(url, handler, type, tag);
    }

    /**
     * 电商品牌介绍列表
     */
    public static void getBrandList(String categoryId, String cityCode, ResponseCallbackHandler handler, int type, String tag) {
        String url = ApiManager.URL_BRAND + "?category_id=" + categoryId + "&city_code=" + cityCode;
        ApiHttp.getBrandList(url, handler, type, tag);
    }

    /**
     * 电商某品牌商品列表
     */
    public static void getProductList(String brandId, String cityCode, ResponseCallbackHandler handler, int type, String tag) {
        String url = ApiManager.URL_BRAND_PRODUCT_LIST + "?brand_id=" + brandId + "&city_code=" + cityCode;
        ApiHttp.getProductList(url, handler, type, tag);
    }

    /**
     * 电商商品详情
     */
    public static void getProductDetail(String goodId, String cityCode, ResponseCallbackHandler handler, int type, String tag) {
        String url = ApiManager.URL_PRODUCT_DETAIL + "?city_code=" + cityCode + "&goods_id=" + goodId;
        ApiHttp.getProductDetail(url, handler, type, tag);

    }

    /**
     * 电商购物车
     */
    public static void getCartInfo(String token, String sign,String time, ResponseCallbackHandler handler, int type, String tag) {
        String url = ApiManager.URL_CART_INFO+ "?token=" + token + "&sign=" + sign + "&t=" +time;
        ApiHttp.getCartInfo(url, handler, type, tag);
    }

    /**
     * 电商购物车改变商品数量
     */
    public static void changeCount(String sign,String t ,Map<String,String> map,ResponseStringListener handler, int type, String tag) {
        String url = ApiManager.URL_CHANGE_COUNT+"?token="+ Constants.TOKEN+"&sign="+sign+"&t="+t ;
        ApiHttp.changeCount(url,map, handler, type, tag);
    }

    /**
     * 电商产品列表
     */
    public static void sortProduct(String brandId, String cityCode, String priceFlag, String sortType, ResponseCallbackHandler handler, int type, String tag) {
        String url = ApiManager.URL_SORT_PRODUCT + "?city_code=" + cityCode + "&sort=" + priceFlag + "&status=" + sortType + "&brand_id=" + brandId;
        ApiHttp.getProductList(url, handler, type, tag);
    }

    /**
     * 电商新增地址
     */
    public static void submitNewAd(String sign, String t, JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        String url = ApiManager.URL_SUBMIT_NEW_AD + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + t;
        ApiHttp.submitNewAd(url, map, handler, type, tag);
    }

    /**
     * 电商编辑地址
     */
    public static void editAddress(String sign, String t, JSONObject map, ResponseJSONObjectListener handler, int type, String tag) {
        String url = ApiManager.URL_EDIT_ADDRESS + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + t;
        ApiHttp.submitNewAd(url, map, handler, type, tag);
    }

    /**
     * 电商删除购物车商品
     */
    public static void delCartItem(String sign, String t, Map<String, String> map, ResponseStringListener handler, int type, String tag) {
        String url = ApiManager.URL_DEL_PRODUCT + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + t;
        ApiHttp.delCartItem(url, map, handler, type, tag);
    }

    /**
     * 电商地址管理列表
     */
    public static void getAddressList(String sign,String time,ResponseCallbackHandler handler, int type, String tag) {
        String url = ApiManager.URL_MANAGE_ADDRESS+ "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" +time;
        ApiHttp.getAddressList(url, handler, type, tag);
    }

    /**
     * 电商地址管理列表-设置默认收货地址
     */
    public static void setDefaultAddress(String sign, String t, Map<String, String> map, ResponseStringListener handler, int type, String tag) {
        String url = ApiManager.URL_SET_DEFAULT_ADDRESS + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + t;
        ApiHttp.setDefaultAddress(url, map, handler, type, tag);
    }

    /**
     * 电商地址管理列表-删除地址
     */
    public static void delAddress(String sign, String t, Map<String, String> map, ResponseStringListener handler, int type, String tag) {
        String url = ApiManager.URL_DEL_ADDRESS + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + t;
        ApiHttp.delAddress(url, map, handler, type, tag);
    }


}
