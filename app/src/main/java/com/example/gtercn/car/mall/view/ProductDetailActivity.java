package com.example.gtercn.car.mall.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.mall.adapter.ProductDetailPagerAdapter;
import com.example.gtercn.car.mall.adapter.ReviewsAdapter;
import com.example.gtercn.car.mall.entity.CreatePreOrderEntity;
import com.example.gtercn.car.mall.entity.ProductDetailEntity;
import com.example.gtercn.car.mall.entity.PropertyListEntity;
import com.example.gtercn.car.mall.entity.ResultEntity;
import com.example.gtercn.car.mall.entity.ReviewsEntity;
import com.example.gtercn.car.mall.view.custom_view.FlowLayout;
import com.example.gtercn.car.mall.view.custom_view.RecyItemSpace;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getProperty();
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
                case R.id.iv_title_left:
                    finish();
                    break;
                case R.id.iv_title_right:
                    Toast.makeText(ProductDetailActivity.this, "分享", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                    startActivity(intent);
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
                    params = new CreatePreOrderEntity();
                    Intent toBuy = new Intent(ProductDetailActivity.this, OrderConfirmActivity.class);
                    //传递过去数量，总价，
                    count = count == 0 ? 1 : count;
                    List<CreatePreOrderEntity.GoodsListBean> listBeen = new ArrayList<>();
                    CreatePreOrderEntity.GoodsListBean bean = new CreatePreOrderEntity.GoodsListBean();
                    bean.setGoods_id(mEntity.getId());
                    bean.setNumber(count + "");
                    bean.setSpec_item_ids("1");
                    listBeen.add(bean);
                    params.setGoods_list(listBeen);
                    toBuy.putExtra("params", params);
                    startActivity(toBuy);
                    break;
                case R.id.ll_select_property:
                    setPropertyUi();
                    break;
                case R.id.tv_add_cart:
                    //将此商品加入购物车
                    addToCart();
                    break;
                case R.id.iv_cart:
                    //跳转到购物车
                    Intent goToCart = new Intent(ProductDetailActivity.this, CartActivity.class);
                    startActivity(goToCart);
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
            }
        }
    };

    private void addToCart() {
        JSONObject params = new JSONObject();
        try {
            params.put("goods_id", "2");
            params.put("number", "2");
            params.put("spec_item_ids", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = ApiManager.URL_ADD_CART + "?token=" + Constants.TOKEN + "&sign=1&time=1";
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
                            ResultEntity entity = new Gson().fromJson(response, ResultEntity.class);
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
            if (tv.getId() == selectId){
                tv.setTextSize(18);
                tv.setTextColor(Color.BLUE);
            }else {
                tv.setTextSize(16);
                tv.setTextColor(getResources().getColor(R.color.text_common_color));
            }
            if (mTitleProductTv.getId() == selectId){
                mTitleIndexOneTv.setVisibility(View.VISIBLE);
                mTitleIndexTwoTv.setVisibility(View.GONE);
                mTitleIndexThreeTv.setVisibility(View.GONE);
                mTitleIndexOneTv.setBackgroundColor(getResources().getColor(R.color.orange_txt));
            }else if (mTitleDetailTv.getId() == selectId){
                mTitleIndexTwoTv.setVisibility(View.VISIBLE);
                mTitleIndexOneTv.setVisibility(View.GONE);
                mTitleIndexThreeTv.setVisibility(View.GONE);
                mTitleIndexTwoTv.setBackgroundColor(getResources().getColor(R.color.orange_txt));
            }else {
                mTitleIndexThreeTv.setVisibility(View.VISIBLE);
                mTitleIndexOneTv.setVisibility(View.GONE);
                mTitleIndexTwoTv.setVisibility(View.GONE);
                mTitleIndexThreeTv.setBackgroundColor(getResources().getColor(R.color.orange_txt));
            }
        }
    }

    private void initData() {
        Intent intent = getIntent();
        String cityCode = intent.getStringExtra("cityCode");
        String goodId = intent.getStringExtra("goodId");
        Log.e(TAG, "initData: cityCode is " + cityCode);
        Log.e(TAG, "initData: goodId is " + goodId);
        ApiManager.getProductDetail(goodId, cityCode, new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                Log.e(TAG, "onSuccessResponse: response is " + response);
                if (response != null) {
                    Gson gson = new Gson();
                    ProductDetailEntity entity = gson.fromJson(response, ProductDetailEntity.class);
                    if (entity != null) {
                        mEntity = entity.getResult();
                        if (mEntity == null){
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
        mSalePriceTv.setText("￥" + mEntity.getPromotion_price());
        mOrgPriceTv.setText("￥" + mEntity.getPrime_price());
        mOrgPriceTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        mGoodReviewsRateTv.setText(mEntity.);
        mSoldCountTv.setText("已售：" + mEntity.getSold_number() + "");
        mIndexCurTv.setText(curPosition + "");
        mIndexTotalTv.setText("5");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(mEntity.getBig_picture() + "");
        }
        mAdapter = new ProductDetailPagerAdapter(list, this);
        mImagePager.setAdapter(mAdapter);
    }

    private void initView() {
        titleTvList = new ArrayList<>();
        mView = LayoutInflater.from(this).inflate(R.layout.activity_product_detail, null);
        setContentView(mView);
        mImagePager = (ViewPager) findViewById(R.id.vp_product_detail);
        mDescriptionTv = (TextView) findViewById(R.id.tv_title);
        mSalePriceTv = (TextView) findViewById(R.id.tv_sale_price);
        mGoodReviewsRateTv = (TextView) findViewById(R.id.tv_good_reviews_rate);
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
        mCartIv = (ImageView) findViewById(R.id.iv_cart);
        productLl = (LinearLayout) findViewById(R.id.ll_product);
        reviewsLl = (LinearLayout) findViewById(R.id.ll_reviews);
        detailLl = (LinearLayout) findViewById(R.id.ll_detail);

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
                .addParams("status", status+"")
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
                            Log.e(TAG, "onResponse: response is " + response);
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
        String categoryId = "7";
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
                            Log.e(TAG, "onResponse: response is " + response);
                            Gson gson = new Gson();
                            PropertyListEntity entity = gson.fromJson(response, PropertyListEntity.class);
                            if (entity != null && entity.getErr_code().equals("0")) {
                                propertyList = entity.getResult().getSpec_list();
                            }
                        }
                    }
                });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setPropertyUi() {
        View popView = LayoutInflater.from(this).inflate(R.layout.custom_property_view, null);
        final PopupWindow pw = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout closeRl = (RelativeLayout) popView.findViewById(R.id.rl_close);
        TextView priceTv = (TextView) popView.findViewById(R.id.tv_price);
        TextView property1Tv = (TextView) popView.findViewById(R.id.tv_property1);
        TextView property2Tv = (TextView) popView.findViewById(R.id.tv_property2);
        TextView countTv = (TextView) popView.findViewById(R.id.tv_count);
        ImageView propertyIv = (ImageView) popView.findViewById(R.id.iv_property);
        LinearLayout propertiesLayout = (LinearLayout) popView.findViewById(R.id.ll_properties);
        for (int i = 0; i < propertyList.size(); i++) {
            //单一属性
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_property, null);
            TextView propertyName = (TextView) itemView.findViewById(R.id.tv_property_name);
            FlowLayout propertyFlow = (FlowLayout) itemView.findViewById(R.id.flow_property);
            //单一属性item
            final List<PropertyListEntity.ResultBean.SpecListBean.ItemsBean> items = propertyList.get(i).getItems();
            for (int j = 0; j < items.size(); j++) {
                final List<TextView> tvList = new ArrayList<>();
                final PropertyListEntity.ResultBean.SpecListBean.ItemsBean bean = items.get(j);
                final TextView tv = new TextView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20, 20, 20, 20);
                tv.setLayoutParams(lp);
                tv.setTextSize(12);
                tv.setText(items.get(j).getItem());
                tv.setGravity(Gravity.CENTER);
                tv.setTag(bean.isSelected());
                tv.setTextColor(getResources().getColor(R.color.text_common_color));
                tv.setBackground(getResources().getDrawable(R.drawable.tag_bg_property));
                tvList.add(tv);
                propertyFlow.addView(tv);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bean.setSelected(!bean.isSelected());
                        changeView();
                        for (int k = 0; k < tvList.size(); k++) {
                            if (tvList.get(k).isSelected()) {
                                tvList.get(k).setTextColor(Color.BLACK);
                            } else {
                                tvList.get(k).setTextColor(Color.RED);
                            }
                        }
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
            }
        });
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
    }

    private void changeView() {

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


}
