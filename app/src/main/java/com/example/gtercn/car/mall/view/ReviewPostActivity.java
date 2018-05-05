package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.activity.SelfDrivingIssueActivity;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.base.CarApplication;
import com.example.gtercn.car.bean.User;
import com.example.gtercn.car.interfaces.ResponseStringListener;
import com.example.gtercn.car.mall.view.custom_view.FlowLayout;
import com.example.gtercn.car.net.THttpOpenHelper;
import com.example.gtercn.car.utils.Constants;
import com.example.gtercn.car.utils.GetPath;
import com.example.gtercn.car.utils.MD5;
import com.example.gtercn.car.utils.SharedPreferenceHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/3.
 * Used to : 发布评论页
 */

public class ReviewPostActivity extends BaseActivity implements ResponseStringListener {

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

    private RelativeLayout mLoadingRl;

    private boolean isOn = true;

    private List<String> imageList = new ArrayList<>();

    private String path;

    //"6c51b7f7c1d1485db3f6c3ee14c58da2"
    private String orderId;

    private String goodId;
    private CarApplication mApp;
    private User mUser;

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
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 100);
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri imageUri = data.getData();
            path = GetPath.getImageAbsolutePath(this, imageUri);
            imageList.add(path);
        }
    }

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
        goodId = getIntent().getStringExtra("goodId");
        orderId = getIntent().getStringExtra("orderId");
        String[] tags = {"质量不错，很满意！", "物超所值", "质量过关", "值得推荐", "不是很满意"};
        for (int i = 0; i < tags.length; i++) {
            final TextView tv = new TextView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10, 10, 10, 10);
            tv.setLayoutParams(lp);
            tv.setText(tags[i]);
            tv.setPadding(20, 20, 20, 20);
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
                        mTagsTv.add(tv);
                    } else {
                        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.tag_bg_gray));
                        tv.setTextColor(getResources().getColor(R.color.text_blue));
                        tv.setTag(true);
                        if (mTagsTv.contains(tv)) {
                            mTagsTv.remove(tv);
                        }
                    }
                }
            });
            mLoadingRl.setVisibility(View.GONE);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_review_post);
        mApp = (CarApplication) getApplication();
        mUser = mApp.getUser();
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
        mLoadingRl = (RelativeLayout) findViewById(R.id.rl_loading);
        initRightTvBar("发表评论", "发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReview();
            }
        });
    }


    /**
     * OkHttpUtils.post()//
     * .addFile("mFile", "messenger_01.png", file)//
     * .addFile("mFile", "test1.txt", file2)//
     * .url(url)
     * .params(params)//
     * .headers(headers)//
     * .build()//
     * .execute(new MyStringCallback());
     * <p>
     * 评价状态：G好评，M中评，B差评
     */
    private void submitReview() {
        String content = mReviewEt.getText().toString().trim() + getTagsContent();

        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请填写评论内容！", Toast.LENGTH_SHORT).show();
            return;
        }
        content = mReviewEt.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("order_id", orderId);
        params.put("goods_id", goodId);
        params.put("status", getRate());
        params.put("describe_status", mProductRb.getRating() + "");//描述相符(1到5)
        params.put("service_attitude", mServiceRb.getRating() + "");
        params.put("service_logistics", mDeliveryRb.getRating() + "");
        params.put("anonymous", isOn ? "Y" : "N");//是否匿名：Y是，N否
        params.put("content", content);
        params.put("pics1", "");
        String sign = MD5.getSign(ApiManager.URL_POST_REVIEW, mUser);
        String time = MD5.gettimes();

        String url = ApiManager.URL_POST_REVIEW + "?token=" + Constants.TOKEN + "&t=" + time + "&sign=" + sign ;
        Map fileMap = new HashMap();
        fileMap.put("pics1", "");
        fileMap.put("pics2","");
        fileMap.put("pics3","");
        THttpOpenHelper.newInstance().requestFormDataFilePost(url, params, fileMap, this, 1, TAG);


//        OkHttpUtils
//                .post()
//                .url(url)
////                .addFile("pics1", "p1.png", new File(""))
////                .addFile("pics2", "p2.png", new File("file2"))
////                .addFile("pics3", "p3.png", new File("file3"))
//                .addHeader("content-type","multipart/form-data; charset=utf-8")
//                .addParams("order_id", orderId)
//                .addParams("goods_id", goodId)
//                .addParams("status", getRate())
//                .addParams("describe_status", mProductRb.getRating() + "")
//                .addParams("service_attitude", mServiceRb.getRating() + "")
//                .addParams("service_logistics", mDeliveryRb.getRating() + "")
//                .addParams("anonymous", isOn ? "Y" : "N")
//                .addParams("content", content)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.e(TAG, "onError: e is " + e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.e(TAG, "onResponse: response is " + response);
//                    }
//                });
    }

    private String getTagsContent() {
        StringBuffer sb = new StringBuffer();
        if (mTagsTv != null && mTagsTv.size() > 0) {
            for (int i = 0; i < mTagsTv.size(); i++) {
                sb.append(mTagsTv.get(i).getText());
            }
            Log.e(TAG, "getTagsContent: tags is " + sb.toString());
            return sb.toString();
        }
        return null;
    }

    private String getRate() {
        float descRating = mProductRb.getRating();
        float serviceRating = mServiceRb.getRating();
        float deliveryRating = mDeliveryRb.getRating();
        float sum = descRating + serviceRating + deliveryRating;
        if (sum >= 12.0) {
            return "G";
        } else if (sum >= 9.0 && sum < 12) {
            return "M";
        } else {
            return "B";
        }
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }

    @Override
    public void onSuccessResponse(String response, int type) {
        Log.e(TAG, "onResponse: response is " + response);
    }

    @Override
    public void onErrorResponse(VolleyError error, int type) {
        Log.e(TAG, "onErrorResponse: error is " + error.toString());
    }
}
