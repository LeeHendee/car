package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.base.BaseActivity;

/**
 * Created by Yan on 2018/1/1.
 */

public class CartActivity extends BaseActivity {

    private static final String TAG = "CartActivity";

    private RecyclerView mCartRv;

    private ImageView mSelectAllIv;

    private LinearLayout mSelectAllLayout;

    private LinearLayout mCheckoutLayout;

    private TextView mCostTv;

    private TextView mTotalTv;

    private TextView mDiscountTv;

    private TextView mCheckoutCountTv;

    private boolean isAll = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {
        mSelectAllLayout.setOnClickListener(mListener);
        mCheckoutLayout.setOnClickListener(mListener);
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_select_all:
                    if (isAll){
                        mSelectAllIv.setImageResource(R.drawable.cart1_checkbox_normal);
                        isAll = false;
                    }else {
                        mSelectAllIv.setImageResource(R.drawable.cart1_checkbox_check);
                        isAll = true;
                    }
                    break;
                case R.id.ll_to_checkout:
                    Toast.makeText(CartActivity.this, "去结算", Toast.LENGTH_SHORT).show();
                    mCheckoutCountTv.setText("("+99+")");
                    break;
            }
        }
    };


    private void initView() {
        setContentView(R.layout.activity_cart);
        mCartRv = (RecyclerView) findViewById(R.id.recy_cart);
        mSelectAllIv = (ImageView) findViewById(R.id.iv_select_all);
        mSelectAllLayout = (LinearLayout) findViewById(R.id.ll_select_all);
        mCheckoutLayout = (LinearLayout) findViewById(R.id.ll_to_checkout);
        mCostTv = (TextView) findViewById(R.id.tv_cost);
        mTotalTv = (TextView) findViewById(R.id.tv_total);
        mDiscountTv = (TextView) findViewById(R.id.tv_discount);
        mCheckoutCountTv = (TextView) findViewById(R.id.tv_count);
        initRightTvBar("购物车", "编辑", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartActivity.this, "编辑", Toast.LENGTH_SHORT).show();
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