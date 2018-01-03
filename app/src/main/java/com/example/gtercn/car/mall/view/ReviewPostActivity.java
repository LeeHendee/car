package com.example.gtercn.car.mall.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Gradient;
import com.example.gtercn.car.R;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.mall.view.custom_view.FlowLayout;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/3.
 * Used to :
 */

public class ReviewPostActivity extends BaseActivity {

    private static final String TAG = "ReviewPostActivity";

    private ImageView mProductIv;

    private FlowLayout mTags;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        String [] tags = {"质量不错，很满意！","物超所值","质量过关","值得推荐","不是很满意"};
        for (int i = 0; i < tags.length; i++) {
            TextView tv = new TextView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10,10,10,10);
            tv.setLayoutParams(lp);
            tv.setText(tags[i]);
            tv.setPadding(20,20,20,20);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.BLUE);
            tv.setTextColor(Color.WHITE);
            mTags.addView(tv);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_review_post);
        mProductIv = (ImageView) findViewById(R.id.iv_product);
        mTags = (FlowLayout) findViewById(R.id.fl_reviews);
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
