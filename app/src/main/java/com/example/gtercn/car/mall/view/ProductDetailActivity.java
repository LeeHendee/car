package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.mall.adapter.ProductDetailPagerAdapter;
import com.example.gtercn.car.mall.entity.ProductDetailEntity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.springindicator.SpringIndicator;

/**
 * Author ：LeeHang
 * CreateTime ：2017/12/29.
 * Used to :
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

    private int curPosition;


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
                    mTitleProductTv.setTextSize(18);
                    mTitleDetailTv.setTextSize(16);
                    mTitleReviewsTv.setTextSize(16);
                    mTitleProductTv.setTextColor(Color.BLUE);
                    mTitleIndexOneTv.setVisibility(View.VISIBLE);
                    mTitleIndexOneTv.setBackgroundColor(getResources().getColor(R.color.orange_txt));
                    mTitleDetailTv.setTextColor(getResources().getColor(R.color.text_common_color));
                    mTitleIndexTwoTv.setVisibility(View.GONE);
                    mTitleReviewsTv.setTextColor(getResources().getColor(R.color.text_common_color));
                    mTitleIndexThreeTv.setVisibility(View.GONE);
                    break;
                case R.id.tv_detail:
                    mTitleDetailTv.setTextSize(18);
                    mTitleProductTv.setTextSize(16);
                    mTitleReviewsTv.setTextSize(16);
                    mTitleDetailTv.setTextColor(Color.BLUE);
                    mTitleIndexTwoTv.setVisibility(View.VISIBLE);
                    mTitleIndexTwoTv.setBackgroundColor(getResources().getColor(R.color.orange_txt));
                    mTitleProductTv.setTextColor(getResources().getColor(R.color.text_common_color));
                    mTitleIndexOneTv.setVisibility(View.GONE);
                    mTitleReviewsTv.setTextColor(getResources().getColor(R.color.text_common_color));
                    mTitleIndexThreeTv.setVisibility(View.GONE);
                    break;
                case R.id.tv_reviews:
                    mTitleReviewsTv.setTextSize(18);
                    mTitleDetailTv.setTextSize(16);
                    mTitleProductTv.setTextSize(16);
                    mTitleReviewsTv.setTextColor(Color.BLUE);
                    mTitleIndexThreeTv.setVisibility(View.VISIBLE);
                    mTitleIndexThreeTv.setBackgroundColor(getResources().getColor(R.color.orange_txt));
                    mTitleDetailTv.setTextColor(getResources().getColor(R.color.text_common_color));
                    mTitleIndexTwoTv.setVisibility(View.GONE);
                    mTitleProductTv.setTextColor(getResources().getColor(R.color.text_common_color));
                    mTitleIndexOneTv.setVisibility(View.GONE);
                    break;
            }
        }
    };

    private void initData() {
        Intent intent = getIntent();
        String cityCode = intent.getStringExtra("cityCode");
        String goodId = intent.getStringExtra("goodId");
        Log.e(TAG, "initData: cityCode is " + cityCode);
        Log.e(TAG, "initData: goodId is " + goodId);
        ApiManager.getProductDetail(goodId, cityCode, new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                Log.e(TAG, "onSuccessResponse: response is " + response);
                if (response != null) {
                    Gson gson = new Gson();
                    ProductDetailEntity entity = gson.fromJson(response, ProductDetailEntity.class);
                    if (entity != null) {
                        mEntity = entity.getResult();
                        setUI();
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
        setContentView(R.layout.activity_product_detail);
        mImagePager = (ViewPager) findViewById(R.id.vp_product_detail);
        mDescriptionTv = (TextView) findViewById(R.id.tv_product_description);
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
        titleTvList.add(mTitleProductTv);
        titleTvList.add(mTitleDetailTv);
        titleTvList.add(mTitleReviewsTv);
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
