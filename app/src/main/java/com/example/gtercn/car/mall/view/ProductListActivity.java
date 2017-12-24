package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.base.BaseActivity;

/**
 * Created by Yan on 2017/12/24.
 */

public class ProductListActivity extends BaseActivity {


    private static final String TAG = "ProductListActivity";

    private ImageView mBackIv;

    private TextView mSearchTv;

    private TextView mComprehensiveSortTv;

    private TextView mSaleSortTv;

    private TextView mPriceSortTv;

    private TextView mSortTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_production_list);
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
