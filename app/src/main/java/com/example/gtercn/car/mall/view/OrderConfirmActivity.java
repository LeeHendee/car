package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.gtercn.car.R;
import com.example.gtercn.car.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/10.
 * Used to :
 */

public class OrderConfirmActivity extends BaseActivity {

    private static final String TAG = "OrderConfirmActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_order_confirm);
        ButterKnife.bind(this);
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
