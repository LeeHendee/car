package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.mall.adapter.ProductListAdapter;

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

    private SwipeRefreshLayout mRefresh;

    private RecyclerView mRecyclerView;

    private ProductListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_production_list);
        mComprehensiveSortTv = (TextView) findViewById(R.id.tv_comprehensive_sort);
        mSaleSortTv = (TextView) findViewById(R.id.tv_sale_sort);
        mPriceSortTv = (TextView) findViewById(R.id.tv_price_sort);
        mSortTv = (TextView) findViewById(R.id.tv_sort);
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.recy_production);
        mBackIv = (ImageView) findViewById(R.id.iv_back);
        mSearchTv = (TextView) findViewById(R.id.tv_search);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));


    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
