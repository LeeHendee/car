package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.gtercn.car.R;
import com.example.gtercn.car.base.BaseActivity;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/4.
 * Used to : 订单管理页面
 */

public class OrderActivity extends BaseActivity {

    private static final String TAG = "OrderActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_order);
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
