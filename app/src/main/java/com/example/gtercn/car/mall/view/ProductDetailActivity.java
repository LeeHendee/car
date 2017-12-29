package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.base.BaseActivity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_product_detail);
        mImagePager = (ViewPager) findViewById(R.id.vp_product_detail);
        mDescriptionTv = (TextView) findViewById(R.id.tv_product_description);
        mSalePriceTv = (TextView) findViewById(R.id.tv_sale_price);
        mGoodReviewsRateTv = (TextView) findViewById(R.id.tv_good_reviews_rate);
        mSoldCountTv = (TextView) findViewById(R.id.tv_product_title);
        mTitleProductTv = (TextView) findViewById(R.id.tv_product);
        mTitleDetailTv = (TextView) findViewById(R.id.tv_detail);
        mTitleReviewsTv = (TextView) findViewById(R.id.tv_reviews);
        mTitleIndexOneTv = (TextView) findViewById(R.id.tv_product_index);
        mTitleIndexTwoTv = (TextView) findViewById(R.id.tv_detail_index);
        mTitleIndexThreeTv = (TextView) findViewById(R.id.tv_reviews_index);

    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
