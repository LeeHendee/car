package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.mall.view.custom_view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/3.
 * Used to : 发布评论页
 */

public class ReviewPostActivity extends BaseActivity {

    private static final String TAG = "ReviewPostActivity";

    private ImageView mProductIv;

    private FlowLayout mTags;

    private List<TextView> mTagsTv;

    private EditText mReviewEt;

    private ImageView mPhotoIv;

    private ImageView mSwitchIv;

    private RatingBar mProductRb;

    private RatingBar mServiceRb;

    private RatingBar mDeliveryRb;

    private TextView mReviewTypeOneTv;

    private TextView mReviewTypeTwoTv;

    private TextView mReviewTypeThreeTv;

    private boolean isOn = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mPhotoIv.setOnClickListener(mListener);
        mSwitchIv.setOnClickListener(mListener);
        mProductRb.setOnRatingBarChangeListener(mRBListener);
        mServiceRb.setOnRatingBarChangeListener(mRBListener);
        mDeliveryRb.setOnRatingBarChangeListener(mRBListener);
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_switch:
                    if (isOn) {
                        isOn = false;
                        mSwitchIv.setImageResource(R.drawable.switch_on);
                    } else {
                        isOn = true;
                        mSwitchIv.setImageResource(R.drawable.switch_off);
                    }
                    break;

                case R.id.iv_photo:
                    Toast.makeText(ReviewPostActivity.this, "选择图片逻辑待续。。。", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private RatingBar.OnRatingBarChangeListener mRBListener = new RatingBar.OnRatingBarChangeListener() {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            switch (ratingBar.getId()) {
                case R.id.rating_product:
                    if (rating * 2 >= 8) {
                        mReviewTypeOneTv.setText("好评");
                    } else if (rating * 2 < 8 && rating * 2 >= 6) {
                        mReviewTypeOneTv.setText("中评");
                    } else {
                        mReviewTypeOneTv.setText("差评");
                    }
                    break;
                case R.id.rating_service:
                    if (rating * 2 >= 8) {
                        mReviewTypeTwoTv.setText("好评");
                    } else if (rating * 2 < 8 && rating * 2 >= 6) {
                        mReviewTypeTwoTv.setText("中评");
                    } else {
                        mReviewTypeTwoTv.setText("差评");
                    }
                    break;
                case R.id.rating_delivery:
                    if (rating * 2 >= 8) {
                        mReviewTypeThreeTv.setText("好评");
                    } else if (rating * 2 < 8 && rating * 2 >= 6) {
                        mReviewTypeThreeTv.setText("中评");
                    } else {
                        mReviewTypeThreeTv.setText("差评");
                    }
                    break;
            }
        }
    };

    private void initData() {
        mTagsTv = new ArrayList<>();
        String [] tags = {"质量不错，很满意！","物超所值","质量过关","值得推荐","不是很满意"};
        for (int i = 0; i < tags.length; i++) {
            final TextView tv = new TextView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10,10,10,10);
            tv.setLayoutParams(lp);
            tv.setText(tags[i]);
            tv.setPadding(20,20,20,20);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.tag_bg_gray));
            tv.setTextColor(getResources().getColor(R.color.text_blue));
            mTags.addView(tv);
            tv.setTag(true);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean tag = (boolean) tv.getTag();
                    if (tag) {
                        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.tag_bg_orange));
                        tv.setTextColor(getResources().getColor(R.color.white));
                        tv.setTag(false);
                    } else {
                        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.tag_bg_gray));
                        tv.setTextColor(getResources().getColor(R.color.text_blue));
                        tv.setTag(true);
                    }
                }
            });
        }
    }

    private void initView() {
        setContentView(R.layout.activity_review_post);
        mProductIv = (ImageView) findViewById(R.id.iv_product);
        mTags = (FlowLayout) findViewById(R.id.fl_reviews);
        mReviewEt = (EditText) findViewById(R.id.et_review_content);
        mPhotoIv = (ImageView) findViewById(R.id.iv_photo);
        mSwitchIv = (ImageView) findViewById(R.id.iv_switch);
        mProductRb = (RatingBar) findViewById(R.id.rating_product);
        mServiceRb = (RatingBar) findViewById(R.id.rating_service);
        mDeliveryRb = (RatingBar) findViewById(R.id.rating_delivery);
        mReviewTypeOneTv = (TextView) findViewById(R.id.tv_review_type_one);
        mReviewTypeTwoTv = (TextView) findViewById(R.id.tv_review_type_two);
        mReviewTypeThreeTv = (TextView) findViewById(R.id.tv_review_type_three);
        initRightTvBar("发表评论", "发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReviewPostActivity.this, "发布", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
