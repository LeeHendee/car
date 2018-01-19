package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.AppManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.mall.adapter.BrandAdapter;
import com.example.gtercn.car.mall.entity.BrandListEntity;
import com.example.gtercn.car.mall.entity.SecKillEntity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan on 2017/12/24.
 */

public class BrandActivity extends BaseActivity {

    private static final String TAG = "BrandActivity";

    private SwipeRefreshLayout mSwipe;

    private RecyclerView mBrandRv;

    private RelativeLayout mLoadingRl;

    private List<BrandListEntity.ResultBean> mList;

    private BrandAdapter mAdapter;

    private String mBrandName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        //需要传入一个分类id
        Intent intent = getIntent();
        String classifyId = intent.getStringExtra("classifyId");
        mBrandName = intent.getStringExtra("classifyName");
        initRightIvBar(mBrandName, R.drawable.icon_search, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BrandActivity.this, "搜索", Toast.LENGTH_SHORT).show();
            }
        });
        ApiManager.getBrandList(classifyId, new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                if (mSwipe.isRefreshing()) {
                    mSwipe.setRefreshing(false);
                } else {
                    mLoadingRl.setVisibility(View.GONE);
                }
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: response is " + response);
                    Gson gson = new Gson();
                    BrandListEntity brandList = gson.fromJson(response, BrandListEntity.class);
                    if (brandList != null) {
                        if (TextUtils.equals(brandList.getErr_code(), "0")) {
                            mList = brandList.getResult();
                            mAdapter = new BrandAdapter(BrandActivity.this, mList);
                            mBrandRv.setLayoutManager(new LinearLayoutManager(BrandActivity.this));
                            mBrandRv.setAdapter(mAdapter);
                        }
                    }
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

            }
        }, 1, TAG);
    }

    private void initView() {
        setContentView(R.layout.activity_brand);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mBrandRv = (RecyclerView) findViewById(R.id.rec_brand);
        mLoadingRl = (RelativeLayout) findViewById(R.id.rl_loading);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        mSwipe.setColorSchemeResources(R.color.blue1);
    }


    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
