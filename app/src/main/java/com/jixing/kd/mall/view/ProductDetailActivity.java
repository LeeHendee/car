package com.jixing.kd.mall.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.jixing.kd.R;
import com.jixing.kd.activity.LoginActivity;
import com.jixing.kd.api.ApiManager;
import com.jixing.kd.base.BaseActivity;
import com.jixing.kd.base.CarApplication;
import com.jixing.kd.bean.User;
import com.jixing.kd.interfaces.ResponseCallbackHandler;
import com.jixing.kd.mall.adapter.ProductDetailPagerAdapter;
import com.jixing.kd.mall.adapter.ReviewsAdapter;
import com.jixing.kd.mall.entity.CreatePreOrderEntity;
import com.jixing.kd.mall.entity.PostAddressEntity;
import com.jixing.kd.mall.entity.ProductDetailEntity;
import com.jixing.kd.mall.entity.PropertyListEntity;
import com.jixing.kd.mall.entity.ResultEntity;
import com.jixing.kd.mall.entity.ReviewsEntity;
import com.jixing.kd.mall.view.custom_view.FlowLayout;
import com.jixing.kd.mall.view.custom_view.RecyItemSpace;
import com.jixing.kd.utils.Constants;
import com.jixing.kd.utils.MD5;
import com.jixing.kd.utils.ShareUtil;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Author ：LeeHang
 * CreateTime ：2017/12/29.
 * Used to : 产品展示页
 */
public class ProductDetailActivity extends BaseActivity {

    private static final String TAG = "ProductDetailActivity";

    private ViewPager mImagePager;

    private TextView mDescriptionTv;

    private TextView mSalePriceTv;

    private TextView mGoodReviewsRateTv;

    private TextView mSoldCountTv;

    private TextView mTitleProductTv;

    private TextView mTitleDetailTv;

    private TextView mTitleReviewsTv;

    private TextView mTitleIndexOneTv;

    private TextView mTitleIndexTwoTv;

    private TextView mTitleIndexThreeTv;

    private ImageView mTitleLeftIv;

    private ImageView mTitleRightIv;

    private ImageView mFavorIv;

    private ProductDetailEntity.ResultBean mEntity;

    private ProductDetailPagerAdapter mAdapter;

    private TextView mOrgPriceTv;

    private List<TextView> titleTvList;

    private RelativeLayout mTitleRl;

    private TextView mIndexCurTv;

    private TextView mIndexTotalTv;

    private TextView mBuyTv;

    private TextView mAddCartTv;

    private ImageView mCartIv;

    private RelativeLayout mLoadingRl;

    private LinearLayout mSelectPropertyLayout;

    private int curPosition;

    private int count;

    private View mView;

    private TextView minusTv;

    private TextView plusTv;

    private TextView countTv;

    private List<PropertyListEntity.ResultBean.SpecListBean> propertyList;
    private CreatePreOrderEntity params;
    private LinearLayout productLl;
    private LinearLayout reviewsLl;
    private LinearLayout detailLl;
    private LinearLayout reviewTitleLl;
    private RecyclerView reviewsRv;
    private ReviewsAdapter reviewsAdapter;
    private LinearLayout allLl;
    private LinearLayout goodLl;
    private LinearLayout midLl;
    private LinearLayout badLl;
    private LinearLayout addLl;
    private LinearLayout picLl;
    private TextView allReviewTv;
    private TextView allNumberTv;
    private TextView allLineTv;
    private TextView goodReviewTv;
    private TextView goodNumberTv;
    private TextView goodLineTv;
    private TextView midReviewTv;
    private TextView midNumberTv;
    private TextView midLineTv;
    private TextView badReviewTv;
    private TextView badNumberTv;
    private TextView badLineTv;
    private TextView addReviewTv;
    private TextView addNumberTv;
    private TextView addLineTv;
    private TextView picReviewTv;
    private TextView picNumberTv;
    private TextView picLineTv;
    private TextView introduceTv;
    private TextView descTv;
    private CarApplication mApp;
    private User mUser;
    private List<String> mBigPicList;

    private String goodsId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mTitleLeftIv.setOnClickListener(mListener);
        mTitleProductTv.setOnClickListener(mListener);
        mTitleDetailTv.setOnClickListener(mListener);
        mTitleReviewsTv.setOnClickListener(mListener);
        mTitleRightIv.setOnClickListener(mListener);
        mBuyTv.setOnClickListener(mListener);
        mAddCartTv.setOnClickListener(mListener);
        mCartIv.setOnClickListener(mListener);
        allLl.setOnClickListener(mListener);
        goodLl.setOnClickListener(mListener);
        midLl.setOnClickListener(mListener);
        badLl.setOnClickListener(mListener);
        addLl.setOnClickListener(mListener);
        picLl.setOnClickListener(mListener);
        mFavorIv.setOnClickListener(mListener);
        plusTv.setOnClickListener(mListener);
        minusTv.setOnClickListener(mListener);
        mSelectPropertyLayout.setOnClickListener(mListener);
        mImagePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                curPosition = position + 1;
                mIndexCurTv.setText(curPosition + "");

                Log.e(TAG, "onPageScrolled: pos is " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: pos is " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_favor:
                    favorGoods();
                    break;
                case R.id.iv_title_left:
                    finish();
                    break;
                case R.id.iv_title_right:
                    ShareUtil.GeneralizeShare(ProductDetailActivity.this, "开得车驿站", "txt", null);
                    break;
                case R.id.tv_product:
                    changeTitleView(view.getId());
                    productLl.setVisibility(View.VISIBLE);
                    detailLl.setVisibility(View.GONE);
                    reviewsLl.setVisibility(View.GONE);
                    break;
                case R.id.tv_detail:
                    changeTitleView(view.getId());
                    productLl.setVisibility(View.GONE);
                    detailLl.setVisibility(View.VISIBLE);
                    reviewsLl.setVisibility(View.GONE);
                    break;
                case R.id.tv_reviews:
                    changeTitleView(view.getId());
                    productLl.setVisibility(View.GONE);
                    detailLl.setVisibility(View.GONE);
                    reviewsLl.setVisibility(View.VISIBLE);
                    break;
                case R.id.tv_buy:
                    //先判断是否为登录用户，否则跳转到登录页面
                    if (isLogin()) {
                        toBuyNow();
                    } else {
                        Toast.makeText(ProductDetailActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.ll_select_property:
//                    setPropertyUi();
                    break;
                case R.id.tv_add_cart:
                    if (isLogin()) {
                        //将此商品加入购物车
                        addToCart();
                    } else {
                        Toast.makeText(ProductDetailActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.iv_cart:
                    if (isLogin()) {
                        //跳转到购物车
                        Intent goToCart = new Intent(ProductDetailActivity.this, CartActivity.class);
                        startActivity(goToCart);
                    } else {
                        Toast.makeText(ProductDetailActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.ll_all:
                    mLoadingRl.setVisibility(View.VISIBLE);
                    getReviews("1", 0);
                    allReviewTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    allNumberTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    allReviewTv.setTextSize(14);
                    allLineTv.setVisibility(View.VISIBLE);
                    goodReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    goodNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    goodReviewTv.setTextSize(12);
                    goodLineTv.setVisibility(View.GONE);
                    midReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    midNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    midReviewTv.setTextSize(12);
                    midLineTv.setVisibility(View.GONE);
                    badReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    badNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    badReviewTv.setTextSize(12);
                    badLineTv.setVisibility(View.GONE);
                    addReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    addNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    addReviewTv.setTextSize(12);
                    addLineTv.setVisibility(View.GONE);
                    picReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    picNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    picReviewTv.setTextSize(12);
                    picLineTv.setVisibility(View.GONE);
                    break;
                case R.id.ll_good:
                    mLoadingRl.setVisibility(View.VISIBLE);
                    getReviews("1", 1);
                    allReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    allNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    allReviewTv.setTextSize(12);
                    allLineTv.setVisibility(View.GONE);
                    goodReviewTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    goodNumberTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    goodReviewTv.setTextSize(14);
                    goodLineTv.setVisibility(View.VISIBLE);
                    midReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    midNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    midReviewTv.setTextSize(12);
                    midLineTv.setVisibility(View.GONE);
                    badReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    badNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    badReviewTv.setTextSize(12);
                    badLineTv.setVisibility(View.GONE);
                    addReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    addNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    addReviewTv.setTextSize(12);
                    addLineTv.setVisibility(View.GONE);
                    picReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    picNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    picReviewTv.setTextSize(12);
                    picLineTv.setVisibility(View.GONE);
                    break;
                case R.id.ll_mid:
                    mLoadingRl.setVisibility(View.VISIBLE);
                    getReviews("1", 2);
                    allReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    allNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    allReviewTv.setTextSize(12);
                    allLineTv.setVisibility(View.GONE);
                    goodReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    goodNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    goodReviewTv.setTextSize(12);
                    goodLineTv.setVisibility(View.GONE);
                    midReviewTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    midNumberTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    midReviewTv.setTextSize(14);
                    midLineTv.setVisibility(View.VISIBLE);
                    badReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    badNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    badReviewTv.setTextSize(12);
                    badLineTv.setVisibility(View.GONE);
                    addReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    addNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    addReviewTv.setTextSize(12);
                    addLineTv.setVisibility(View.GONE);
                    picReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    picNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    picReviewTv.setTextSize(12);
                    picLineTv.setVisibility(View.GONE);
                    break;
                case R.id.ll_bad:
                    mLoadingRl.setVisibility(View.VISIBLE);
                    getReviews("1", 3);
                    allReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    allNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    allReviewTv.setTextSize(12);
                    allLineTv.setVisibility(View.GONE);
                    goodReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    goodNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    goodReviewTv.setTextSize(12);
                    goodLineTv.setVisibility(View.GONE);
                    midReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    midNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    midReviewTv.setTextSize(12);
                    midLineTv.setVisibility(View.GONE);
                    badReviewTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    badNumberTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    badReviewTv.setTextSize(14);
                    badLineTv.setVisibility(View.VISIBLE);
                    addReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    addNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    addReviewTv.setTextSize(12);
                    addLineTv.setVisibility(View.GONE);
                    picReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    picNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    picReviewTv.setTextSize(12);
                    picLineTv.setVisibility(View.GONE);
                    break;
                case R.id.ll_add:
                    mLoadingRl.setVisibility(View.VISIBLE);
                    getReviews("1", 5);
                    allReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    allNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    allReviewTv.setTextSize(12);
                    allLineTv.setVisibility(View.GONE);
                    goodReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    goodNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    goodReviewTv.setTextSize(12);
                    goodLineTv.setVisibility(View.GONE);
                    midReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    midNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    midReviewTv.setTextSize(12);
                    midLineTv.setVisibility(View.GONE);
                    badReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    badNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    badReviewTv.setTextSize(12);
                    badLineTv.setVisibility(View.GONE);
                    addReviewTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    addNumberTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    addReviewTv.setTextSize(14);
                    addLineTv.setVisibility(View.VISIBLE);
                    picReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    picNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    picReviewTv.setTextSize(12);
                    picLineTv.setVisibility(View.GONE);
                    break;
                case R.id.ll_pic:
                    mLoadingRl.setVisibility(View.VISIBLE);
                    getReviews("1", 4);
                    allReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    allNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    allReviewTv.setTextSize(12);
                    allLineTv.setVisibility(View.GONE);
                    goodReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    goodNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    goodReviewTv.setTextSize(12);
                    goodLineTv.setVisibility(View.GONE);
                    midReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    midNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    midReviewTv.setTextSize(12);
                    midLineTv.setVisibility(View.GONE);
                    badReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    badNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    badReviewTv.setTextSize(12);
                    badLineTv.setVisibility(View.GONE);
                    addReviewTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    addNumberTv.setTextColor(getResources().getColor(R.color.text_note_color));
                    addReviewTv.setTextSize(12);
                    addLineTv.setVisibility(View.GONE);
                    picReviewTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    picNumberTv.setTextColor(getResources().getColor(R.color.orange_txt));
                    picReviewTv.setTextSize(14);
                    picLineTv.setVisibility(View.VISIBLE);
                    break;
                case R.id.tv_plus:
                    ++mCount;
                    countTv.setText(mCount + "");

                    break;
                case R.id.tv_minus:
                    if (mCount > 1) {
                        --mCount;
                        countTv.setText(mCount + "");
                    } else {
                        Toast.makeText(ProductDetailActivity.this, "数量不能小于1", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void favorGoods() {
        String sign = MD5.getSign(ApiManager.URL_GET_ALIPAY_SIGN, mUser);
        String time = MD5.gettimes();
        String url = ApiManager.URL_FAVORADD + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + time;
        JSONObject params = new JSONObject();
        try {
            params.put("favor_id", goodsId);
            params.put("favor_type", "8");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString().url(url).mediaType(MediaType.parse("application/json;charset=utf-8")).content(params.toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e(TAG, "favorGoods: error is " + e.toString());
                showToastMsg("网络异常，稍后重试");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, "favorGoods: response is " + response);
                if (response != null) {
                    ResultEntity entity = new Gson().fromJson(response, ResultEntity.class);
                    if (entity != null && "0".equals(entity.getErr_code())) {
                        showToastMsg(entity.getErr_message());
                    }
                }
            }
        });
    }

    private void toBuyNow() {
        params = new CreatePreOrderEntity();
        Intent toBuy = new Intent(ProductDetailActivity.this, OrderConfirmActivity.class);
        //传递过去数量，总价，
        count = count == 0 ? 1 : count;
        List<CreatePreOrderEntity.GoodsListBean> listBeen = new ArrayList<>();
        CreatePreOrderEntity.GoodsListBean bean = new CreatePreOrderEntity.GoodsListBean();
        bean.setGoods_id(mEntity.getId());
        bean.setNumber(mCount + "");
        bean.setSpec_item_ids(mEntity.getSpec_item_ids());
        listBeen.add(bean);
        params.setGoods_list(listBeen);
        toBuy.putExtra("params", params);
        startActivity(toBuy);
    }

    private void addToCart() {
        JSONObject params = new JSONObject();
        propertyIds = mEntity.getSpec_item_ids();
        try {
            params.put("goods_id", mEntity.getId());
            params.put("number", mCount + "");
            params.put("spec_item_ids", propertyIds);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String sign = MD5.getSign(ApiManager.URL_ADD_CART, mUser);
        String time = MD5.gettimes();
        String url = ApiManager.URL_ADD_CART + "?token=" + Constants.TOKEN + "&sign=" + sign + "&time=" + time;
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json;charset=utf8"))
                .content(params.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null) {
                            Log.e(TAG, "response: response is " + response);
                            PostAddressEntity entity = new Gson().fromJson(response, PostAddressEntity.class);
                            if (entity != null && entity.getErr_code().equals("0")) {
                                Toast.makeText(ProductDetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void changeTitleView(int selectId) {
        for (int i = 0; i < titleTvList.size(); i++) {
            TextView tv = titleTvList.get(i);
            if (tv.getId() == selectId) {
                tv.setTextSize(18);
                tv.setTextColor(Color.BLUE);
            } else {
                tv.setTextSize(16);
                tv.setTextColor(getResources().getColor(R.color.text_common_color));
            }
            if (mTitleProductTv.getId() == selectId) {
                mTitleIndexOneTv.setVisibility(View.VISIBLE);
                mTitleIndexTwoTv.setVisibility(View.GONE);
                mTitleIndexThreeTv.setVisibility(View.GONE);
                mTitleIndexOneTv.setBackgroundColor(getResources().getColor(R.color.orange_txt));
            } else if (mTitleDetailTv.getId() == selectId) {
                mTitleIndexTwoTv.setVisibility(View.VISIBLE);
                mTitleIndexOneTv.setVisibility(View.GONE);

                mTitleIndexThreeTv.setVisibility(View.GONE);
                mTitleIndexTwoTv.setBackgroundColor(getResources().getColor(R.color.orange_txt));
            } else {
                mTitleIndexThreeTv.setVisibility(View.VISIBLE);
                mTitleIndexOneTv.setVisibility(View.GONE);
                mTitleIndexTwoTv.setVisibility(View.GONE);
                mTitleIndexThreeTv.setBackgroundColor(getResources().getColor(R.color.orange_txt));
            }
        }
    }

    private void initData() {
        mApp = (CarApplication) getApplication();
        mUser = mApp.getUser();
        Log.e(TAG, "initData: token is " + Constants.TOKEN);
        Intent intent = getIntent();
        String cityCode = Constants.CITY_CODE;
        goodsId = intent.getStringExtra("goodId");
        Log.e(TAG, "initData: cityCode is " + cityCode);
        Log.e(TAG, "initData: goodId is " + goodsId);
        ApiManager.getProductDetail(goodsId, cityCode, new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                Log.e(TAG, "initData: response is " + response);
                if (response != null) {
                    Gson gson = new Gson();
                    ProductDetailEntity entity = gson.fromJson(response, ProductDetailEntity.class);
                    if (entity != null) {
                        mEntity = entity.getResult();
                        getProperty();
                        if (mEntity == null) {
                            Toast.makeText(ProductDetailActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        setUI();
                        getReviews("1", 0);
                    }
                }
            }

            @Override
            public void onSuccessResponse(JSONObject response, int type) {

            }

            @Override
            public void onSuccessResponse(JSONArray response, int type) {

            }

            @Override
            public void onErrorResponse(VolleyError error, int type) {
                mLoadingRl.setVisibility(View.GONE);
            }
        }, 1, TAG);
    }

    private void setUI() {
        mDescriptionTv.setText(mEntity.getGoods_title());
        mSalePriceTv.setText(getResources().getString(R.string.rmb) + mEntity.getPromotion_price());
        mOrgPriceTv.setText(getResources().getString(R.string.rmb) + mEntity.getPrime_price());
        mOrgPriceTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        mGoodReviewsRateTv.setText(mEntity.);
        mSoldCountTv.setText("已售：" + mEntity.getSold_number() + "");
        mIndexCurTv.setText(curPosition + "");
        descTv.setText(mEntity.getGoods_description());
        introduceTv.setText(mEntity.getGoods_synopsis());
        String property = mEntity.getSpec_item_content();
        if (TextUtils.isEmpty(property)) {
            mSelectPropertyLayout.setVisibility(View.GONE);
        } else {
            displayArrTv.setText(mEntity.getSpec_item_content());
        }
        mBigPicList = new ArrayList<>();
        getBigPicList(mEntity.getBig_picture());

        Log.e(TAG, "setUI: list is " + mBigPicList);
        mAdapter = new ProductDetailPagerAdapter(mBigPicList, this);
        mImagePager.setAdapter(mAdapter);
        mIndexTotalTv.setText(mBigPicList != null ? mBigPicList.size() + "" : 0 + "");
    }

    private void initView() {
        titleTvList = new ArrayList<>();
        mView = LayoutInflater.from(this).inflate(R.layout.activity_product_detail, null);
        setContentView(mView);
        mImagePager = (ViewPager) findViewById(R.id.vp_product_detail);
        mSalePriceTv = (TextView) findViewById(R.id.tv_sale_price);
        mGoodReviewsRateTv = (TextView) findViewById(R.id.tv_good_reviews_rate);
        mDescriptionTv = (TextView) findViewById(R.id.tv_title);
        mSoldCountTv = (TextView) findViewById(R.id.tv_sold_count);
        mTitleProductTv = (TextView) findViewById(R.id.tv_product);
        mTitleDetailTv = (TextView) findViewById(R.id.tv_detail);
        mTitleReviewsTv = (TextView) findViewById(R.id.tv_reviews);
        mTitleIndexOneTv = (TextView) findViewById(R.id.tv_product_index);
        mTitleIndexTwoTv = (TextView) findViewById(R.id.tv_detail_index);
        mTitleIndexThreeTv = (TextView) findViewById(R.id.tv_reviews_index);
        mTitleLeftIv = (ImageView) findViewById(R.id.iv_title_left);
        mTitleRightIv = (ImageView) findViewById(R.id.iv_title_right);
        mOrgPriceTv = (TextView) findViewById(R.id.tv_org_price);
        mTitleRl = (RelativeLayout) findViewById(R.id.rl_title);
        mIndexCurTv = (TextView) findViewById(R.id.tv_index_cur);
        mIndexTotalTv = (TextView) findViewById(R.id.tv_index_total);
        mLoadingRl = (RelativeLayout) findViewById(R.id.rl_loading);
        mSelectPropertyLayout = (LinearLayout) findViewById(R.id.ll_select_property);
        mBuyTv = (TextView) findViewById(R.id.tv_buy);
        mAddCartTv = (TextView) findViewById(R.id.tv_add_cart);
        descTv = (TextView) findViewById(R.id.tv_desc);
        introduceTv = (TextView) findViewById(R.id.tv_introduce);
        mCartIv = (ImageView) findViewById(R.id.iv_cart);
        mFavorIv = (ImageView) findViewById(R.id.iv_favor);
        productLl = (LinearLayout) findViewById(R.id.ll_product);
        reviewsLl = (LinearLayout) findViewById(R.id.ll_reviews);
        detailLl = (LinearLayout) findViewById(R.id.ll_detail);
        minusTv = (TextView) findViewById(R.id.tv_minus);
        plusTv = (TextView) findViewById(R.id.tv_plus);
        countTv = (TextView) findViewById(R.id.tv_count);

        titleTvList.add(mTitleProductTv);
        titleTvList.add(mTitleDetailTv);
        titleTvList.add(mTitleReviewsTv);
        initReviewsUi();
        changeTitleView(mTitleProductTv.getId());
    }

    private void initReviewsUi() {
        allLl = (LinearLayout) findViewById(R.id.ll_all);
        goodLl = (LinearLayout) findViewById(R.id.ll_good);
        midLl = (LinearLayout) findViewById(R.id.ll_mid);
        badLl = (LinearLayout) findViewById(R.id.ll_bad);
        addLl = (LinearLayout) findViewById(R.id.ll_add);
        picLl = (LinearLayout) findViewById(R.id.ll_pic);
        reviewTitleLl = (LinearLayout) findViewById(R.id.ll_review_title);
        reviewsRv = (RecyclerView) findViewById(R.id.recy_reviews);
        reviewsRv.addItemDecoration(new RecyItemSpace(25));
        allReviewTv = (TextView) findViewById(R.id.tv_all);
        allNumberTv = (TextView) findViewById(R.id.tv_all_number);
        allLineTv = (TextView) findViewById(R.id.tv_all_line);
        goodReviewTv = (TextView) findViewById(R.id.tv_good);
        goodNumberTv = (TextView) findViewById(R.id.tv_good_number);
        goodLineTv = (TextView) findViewById(R.id.tv_good_line);
        midReviewTv = (TextView) findViewById(R.id.tv_mid);
        midNumberTv = (TextView) findViewById(R.id.tv_mid_number);
        midLineTv = (TextView) findViewById(R.id.tv_mid_line);
        badReviewTv = (TextView) findViewById(R.id.tv_bad);
        badNumberTv = (TextView) findViewById(R.id.tv_bad_number);
        badLineTv = (TextView) findViewById(R.id.tv_bad_line);
        addReviewTv = (TextView) findViewById(R.id.tv_add);
        addNumberTv = (TextView) findViewById(R.id.tv_add_number);
        addLineTv = (TextView) findViewById(R.id.tv_add_line);
        picReviewTv = (TextView) findViewById(R.id.tv_pic);
        picNumberTv = (TextView) findViewById(R.id.tv_pic_number);
        picLineTv = (TextView) findViewById(R.id.tv_pic_line);
        displayArrTv = (TextView) findViewById(R.id.tv_display_property);
        reviewsRv.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * 0全部1好评2中评3差评4有图5追评
     *
     * @param status
     */
    private void getReviews(String goodId, int status) {
        OkHttpUtils
                .get()
                .url(ApiManager.URL_PRODUCT_REVIEWS)
                .addParams("goods_id", goodId)
                .addParams("status", status + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                        mLoadingRl.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mLoadingRl.setVisibility(View.GONE);
                        if (response != null) {
                            Log.e(TAG, "getReviews: response is " + response);
                            ReviewsEntity entity = new Gson().fromJson(response, ReviewsEntity.class);
                            if (entity != null && entity.getErr_code().equals("0")) {
                                reviewsAdapter = new ReviewsAdapter(ProductDetailActivity.this, entity.getResult().getComment_list());
                                reviewsRv.setAdapter(reviewsAdapter);
                                setReviewUI(entity.getResult());
                            }
                        }
                    }
                });
    }

    private void setReviewUI(ReviewsEntity.ResultBean bean) {
        allNumberTv.setText(bean.getTotal_count() + "");
        goodNumberTv.setText(bean.getGood_count() + "");
        midNumberTv.setText(bean.getMiddle_count() + "");
        badNumberTv.setText(bean.getTotal_count() + "");
        addNumberTv.setText(bean.getTotal_count() + "");
        picNumberTv.setText(bean.getTotal_count() + "");
    }

    private void getProperty() {
        if (mEntity == null) return;
        String categoryId = mEntity.getCategory_id();
        String isSearch = "1";
        OkHttpUtils
                .get()
                .url(ApiManager.URL_PROPERTY_LIST)
                .addParams("category_id", categoryId)
                .addParams("is_search", isSearch)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null) {
                            Log.e(TAG, "getProperty: response is " + response);
                            Gson gson = new Gson();
                            PropertyListEntity entity = gson.fromJson(response, PropertyListEntity.class);
                            if (entity != null && entity.getErr_code().equals("0")) {
                                propertyList = entity.getResult().getSpec_list();
                            }
                        }
                    }
                });
    }

    private int mCount = 1;

    private String properties = null;

    private String propertyIds = null;

    private TextView displayArrTv;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setPropertyUi() {
        View popView = LayoutInflater.from(this).inflate(R.layout.custom_property_view, null);
        final PopupWindow pw = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout closeRl = (RelativeLayout) popView.findViewById(R.id.rl_close);
        TextView priceTv = (TextView) popView.findViewById(R.id.tv_price);
        final TextView property1Tv = (TextView) popView.findViewById(R.id.tv_property1);
//        TextView minusTv = (TextView) popView.findViewById(R.id.tv_minus);
//        final TextView countTv = (TextView) popView.findViewById(R.id.tv_count);
//        TextView plusTv = (TextView) popView.findViewById(R.id.tv_plus);
        final LinearLayout ll = (LinearLayout) popView.findViewById(R.id.ll_white);

//        countTv.setText(mCount + "");
        ImageView propertyIv = (ImageView) popView.findViewById(R.id.iv_property);
        LinearLayout propertiesLayout = (LinearLayout) popView.findViewById(R.id.ll_properties);
        TextView addToCartTv = (TextView) popView.findViewById(R.id.tv_pop_add_cart);
        TextView buyNowTv = (TextView) popView.findViewById(R.id.tv_pop_buy_now);
        addToCartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
                pw.dismiss();
            }
        });
        buyNowTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBuyNow();
                pw.dismiss();
            }
        });

        popView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = ll.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        pw.dismiss();
                    }
                }
                return true;
            }
        });

        if (propertyList == null) {
            Toast.makeText(this, "propertyList == null ", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < propertyList.size(); i++) {
            //单一属性
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_property, null);
            TextView propertyName = (TextView) itemView.findViewById(R.id.tv_property_name);
            FlowLayout propertyFlow = (FlowLayout) itemView.findViewById(R.id.flow_property);
            //单一属性item
            final List<PropertyListEntity.ResultBean.SpecListBean.ItemsBean> items = propertyList.get(i).getItems();
            final List<TextView> tvList = new ArrayList<>();
            if (items == null) {
                return;
            }
            for (int j = 0; j < items.size(); j++) {
                PropertyListEntity.ResultBean.SpecListBean.ItemsBean bean = items.get(j);
                TextView tv = new TextView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20, 20, 20, 20);
                tv.setLayoutParams(lp);
                tv.setTextSize(12);
                tv.setText(items.get(j).getItem());
                tv.setGravity(Gravity.CENTER);
                tv.setTag(bean);
                tv.setId(j);
                tv.setTextColor(getResources().getColor(R.color.text_common_color));
                tv.setBackground(getResources().getDrawable(R.drawable.tag_bg_property));
                tvList.add(tv);
                propertyFlow.addView(tv);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int k = 0; k < tvList.size(); k++) {
                            TextView t = tvList.get(k);
                            PropertyListEntity.ResultBean.SpecListBean.ItemsBean b = (PropertyListEntity.ResultBean.SpecListBean.ItemsBean) t.getTag();
                            if (k == v.getId()) {
                                b.setSelected(true);
                                t.setTextColor(getResources().getColor(R.color.orange_txt));
                            } else {
                                b.setSelected(false);
                                t.setTextColor(Color.BLACK);
                            }
                        }
                        changeUi(property1Tv);
                    }
                });
            }
            propertyName.setText(propertyList.get(i).getName());
            propertiesLayout.addView(itemView);
        }
        priceTv.setText(getResources().getString(R.string.rmb) + mEntity.getPromotion_price());
        Picasso.with(this).load(mEntity.getSmall_picture()).into(propertyIv);
        closeRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
                //设置属性字段的tv；
                displayArrTv.setText(properties);
            }
        });
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
        clearAllSelected();


    }

    private void clearAllSelected() {
        for (int i = 0; i < propertyList.size(); i++) {
            List<PropertyListEntity.ResultBean.SpecListBean.ItemsBean> list = propertyList.get(i).getItems();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setSelected(false);
            }
        }
    }

    private void changeUi(TextView tv) {
        String ids = null;
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < propertyList.size(); i++) {
            List<PropertyListEntity.ResultBean.SpecListBean.ItemsBean> list = propertyList.get(i).getItems();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).isSelected()) {
                    sb1.append(list.get(j).getItem() + " ");
                    sb2.append(list.get(j).getSpec_id() + ",");
                }
            }
        }
        ids = sb2.toString();
        propertyIds = ids.substring(0, ids.length() - 1);
        properties = sb1.toString() + " X" + mCount;
        tv.setText(properties);
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void getBigPicList(String pics) {
        if (TextUtils.isEmpty(pics))
            return;
        mBigPicList = Arrays.asList(pics.split(","));
    }

}
