package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;

import butterknife.ButterKnife;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/12.
 * Used to : 封装所有activity都需要处理的逻辑
 * 1.setContentView
 * 2.初始化butterKnife
 * 3.封装通用的Toolbar
 * 4.封装Toast
 * 5.activity的管理（未完成）
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected void initRightTvBar(String title, String rightContent, View.OnClickListener rightTvListener) {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        TextView rightTv = (TextView) findViewById(R.id.tv_title_right);
        ImageView leftIv = (ImageView) findViewById(R.id.iv_title_left);
        titleTv.setText(title);
        rightTv.setVisibility(View.VISIBLE);
        rightTv.setText(rightContent);
        rightTv.setOnClickListener(rightTvListener);
        leftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void initView() {
    }

    protected void initData() {
    }


    protected void initRightIvBar(String title, int rightRes, View.OnClickListener rightIvListener) {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        ImageView leftIv = (ImageView) findViewById(R.id.iv_title_left);
        ImageView rightIv = (ImageView) findViewById(R.id.iv_title_right);
        titleTv.setText(title);
        rightIv.setVisibility(View.VISIBLE);
        rightIv.setImageResource(rightRes);
        rightIv.setOnClickListener(rightIvListener);
        leftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int msg) {
        Toast.makeText(this, msg + "", Toast.LENGTH_SHORT).show();
    }

}
