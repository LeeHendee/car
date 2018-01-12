package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.mall.IListener;
import com.example.gtercn.car.mall.adapter.ProductListAdapter;
import com.example.gtercn.car.mall.entity.ProductListEntity;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    private List<ProductListEntity.ResultBean> mProductList;

    private List<TextView> mSortTvList;

    private RelativeLayout mLoadingRl;

    private int priceFlag = 0;

    private int sortType = 0;
    private String mBrandId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        mProductList = new ArrayList<>();
        Intent intent = getIntent();
        mBrandId = intent.getStringExtra("brandId");
        sortProduct(priceFlag, sortType);
    }

    private void setData() {
        mAdapter = new ProductListAdapter(this, mProductList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setmItemListener(new IListener() {
            @Override
            public void itemClickListener(int pos) {
                String goodId = mProductList.get(pos).getId();
                String cityCode = mProductList.get(pos).getCity_code();
                Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                intent.putExtra("goodId", goodId);
                intent.putExtra("cityCode", cityCode);
                startActivity(intent);
            }
        });
    }

    private void initListener() {
        mBackIv.setOnClickListener(mListener);
        mSearchTv.setOnClickListener(mListener);
        mComprehensiveSortTv.setOnClickListener(mListener);
        mSaleSortTv.setOnClickListener(mListener);
        mPriceSortTv.setOnClickListener(mListener);
        mSortTv.setOnClickListener(mListener);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
                case R.id.tv_search:

                    break;
                case R.id.tv_comprehensive_sort:
                    changeSortStatus(v.getId());
                    sortType = 0;
                    sortProduct(priceFlag, sortType);
                    break;
                case R.id.tv_sale_sort:
                    changeSortStatus(v.getId());
                    sortType = 1;
                    sortProduct(priceFlag, sortType);
                    break;
                case R.id.tv_price_sort:
                    changeSortStatus(v.getId());
                    sortType = 2;
                    if (priceFlag == 0) {
                        priceFlag = 1;
                    } else {
                        priceFlag = 0;
                    }

                    sortProduct(priceFlag, sortType);
                    break;
                case R.id.tv_sort:
                    changeSortStatus(v.getId());
                    break;
            }
        }
    };

    private void sortProduct(int priceFlag, int sortType) {
        mLoadingRl.setVisibility(View.VISIBLE);
        ApiManager.sortProduct(mBrandId, Constants.CITY_CODE, priceFlag + "", sortType + "", new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: response is " + response);
                    Gson gson = new Gson();
                    ProductListEntity entity = gson.fromJson(response, ProductListEntity.class);
                    mProductList = entity.getResult();
                    setData();
                }
            }

            @Override
            public void onSuccessResponse(JSONObject response, int type) {

            }

            @Override
            public void onSuccessResponse(JSONArray response, int type) {

            }

            @Override
            public void onErrorResponse(VolleyError error, int type) {
                mLoadingRl.setVisibility(View.GONE);
            }
        }, 2, TAG);
    }

    private void changeSortStatus(int id) {
        for (int i = 0; i < mSortTvList.size(); i++) {
            TextView tv = mSortTvList.get(i);
            if (tv.getId() == id) {
                tv.setTextColor(Color.BLUE);
            } else {
                tv.setTextColor(getResources().getColor(R.color.text_common_color));
            }
        }
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
        mLoadingRl = (RelativeLayout) findViewById(R.id.rl_loading);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRefresh.setColorSchemeResources(R.color.blue1);

        mSortTvList = new ArrayList<>();
        mSortTvList.add(mComprehensiveSortTv);
        mSortTvList.add(mSaleSortTv);
        mSortTvList.add(mPriceSortTv);
        mSortTvList.add(mSortTv);
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
