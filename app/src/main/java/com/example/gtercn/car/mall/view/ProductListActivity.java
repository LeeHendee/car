package com.example.gtercn.car.mall.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import com.example.gtercn.car.mall.entity.PropertyListEntity;
import com.example.gtercn.car.mall.view.custom_view.FlowLayout;
import com.example.gtercn.car.mall.view.custom_view.RecyItemSpace;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Yan on 2017/12/24.
 * <p>
 * 1.属性筛选产品接口问题；
 * 2.少一个收藏商品的功能；
 * 3.选择服务站；
 * 4.助销列表；
 * 5.分享功能；
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

    private String mBrandId = null;

    private String mSearchContent = null;

    private View mView;

    private RelativeLayout mEmptyView;

    private String categoryId;
    private PropertyListEntity propertyListEntity;


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
        mSearchContent = intent.getStringExtra("searchContent");
        categoryId = intent.getStringExtra("categoryId");

        if (mBrandId != null) {
            sortProduct(priceFlag, sortType);
            getProperty(categoryId, "0");
        } else {
            //从搜索页面跳转
            submitSearch();
        }

    }

    private void setData() {
        mAdapter = new ProductListAdapter(this, mProductList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setmItemListener(new IListener() {
            @Override
            public void itemClickListener(int pos) {
                String goodId = mProductList.get(pos).getId();
                String cityCode = Constants.CITY_CODE;
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
//                initData();
                sortProduct(priceFlag, sortType);
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
                    Intent searchIntent = new Intent(ProductListActivity.this, SearchActivity.class);
                    startActivity(searchIntent);
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
                    setPropertyUi();
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
                if (mRefresh.isRefreshing()) {
                    mRefresh.setRefreshing(false);
                }
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: response is " + response);
                    Gson gson = new Gson();
                    ProductListEntity entity = gson.fromJson(response, ProductListEntity.class);
                    if (entity != null && TextUtils.equals(entity.getErr_code(), "0")) {
                        if (entity.getResult() != null && entity.getResult().size() > 0) {
                            mEmptyView.setVisibility(View.GONE);
                            mProductList = entity.getResult();
                            setData();
                        } else {
                            mEmptyView.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(ProductListActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
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
                mLoadingRl.setVisibility(View.GONE);
                if (mRefresh.isRefreshing()) {
                    mRefresh.setRefreshing(false);
                }
            }
        }, 2, TAG);
    }

    private void changeSortStatus(int id) {
        for (int i = 0; i < mSortTvList.size(); i++) {
            TextView tv = mSortTvList.get(i);
            if (tv.getId() == id) {
                tv.setTextColor(getResources().getColor(R.color.orange_txt));
            } else {
                tv.setTextColor(getResources().getColor(R.color.text_common_color));
            }
        }
    }

    private void initView() {
        mView = LayoutInflater.from(this).inflate(R.layout.activity_production_list, null);
        setContentView(mView);
        mComprehensiveSortTv = (TextView) findViewById(R.id.tv_comprehensive_sort);
        mSaleSortTv = (TextView) findViewById(R.id.tv_sale_sort);
        mPriceSortTv = (TextView) findViewById(R.id.tv_price_sort);
        mSortTv = (TextView) findViewById(R.id.tv_sort);
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.recy_production);
        mBackIv = (ImageView) findViewById(R.id.iv_back);
        mSearchTv = (TextView) findViewById(R.id.tv_search);
        mLoadingRl = (RelativeLayout) findViewById(R.id.rl_loading);
        mEmptyView = (RelativeLayout) findViewById(R.id.empty_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyItemSpace(1));
        mRefresh.setColorSchemeResources(R.color.blue1);
        mSortTvList = new ArrayList<>();
        mSortTvList.add(mComprehensiveSortTv);
        mSortTvList.add(mSaleSortTv);
        mSortTvList.add(mPriceSortTv);
        mSortTvList.add(mSortTv);
    }


    private void submitSearch() {
        mLoadingRl.setVisibility(View.VISIBLE);
        if (mRefresh.isRefreshing()) {
            mRefresh.setRefreshing(false);
        }
        OkHttpUtils
                .get()
                .url(ApiManager.URL_DO_SEARCH)
                .addParams("city_code", Constants.CITY_CODE)
                .addParams("search_tag", mSearchContent)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mLoadingRl.setVisibility(View.GONE);
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mLoadingRl.setVisibility(View.GONE);
                        if (response != null) {
                            Log.e(TAG, "search: response is " + response);
                            Gson gson = new Gson();
                            ProductListEntity entity = gson.fromJson(response, ProductListEntity.class);
                            if (entity != null && TextUtils.equals(entity.getErr_code(), "0")) {
                                if (entity.getResult() != null && entity.getResult().size() > 0) {
                                    mEmptyView.setVisibility(View.GONE);
                                    mProductList = entity.getResult();
                                    categoryId = mProductList.get(0).getCategory_id();
                                    getProperty(categoryId, "0");
                                    mBrandId = entity.getResult().get(0).getBrand_id();
                                    setData();
                                } else {
                                    mEmptyView.setVisibility(View.VISIBLE);
                                }
                            } else {
                                Toast.makeText(ProductListActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private List<PropertyListEntity.ResultBean.SpecListBean> propertyList;
    private List<PropertyListEntity.ResultBean.BrandListBean> brandList;

    private void getProperty(String categoryId, String isSearch) {
        OkHttpUtils
                .get()
                .url(ApiManager.URL_PROPERTY_LIST)
                .addParams("category_id", categoryId)
                .addParams("is_search", isSearch)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null) {
                            Log.e(TAG, "onResponse: response is " + response);
                            Gson gson = new Gson();
                            propertyListEntity = gson.fromJson(response, PropertyListEntity.class);
                            if (propertyListEntity != null && propertyListEntity.getErr_code().equals("0")) {
                                propertyList = propertyListEntity.getResult().getSpec_list();
                            }
                        }
                    }
                });
    }

    private String hp;
    private String lp;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setPropertyUi() {
        final View popView = LayoutInflater.from(this).inflate(R.layout.custom_property_list, null);
        final PopupWindow pw = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout propertiesLayout = (LinearLayout) popView.findViewById(R.id.ll_properties);
        TextView confirmTv = (TextView) popView.findViewById(R.id.tv_pop_add_cart);
        TextView resetTv = (TextView) popView.findViewById(R.id.tv_pop_buy_now);
        final LinearLayout ll = (LinearLayout) popView.findViewById(R.id.ll_white);
        final EditText lowEt = (EditText) popView.findViewById(R.id.et_low_price);
        final EditText highEt = (EditText) popView.findViewById(R.id.et_high_price);
        resetTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetProperty();
            }
        });
        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用属性过滤接口
                String lowPrice = lowEt.getText().toString().trim();
                String highPrice = highEt.getText().toString().trim();
                if (!lowPrice.isEmpty() && lowPrice.equals("最低价")) {
                    lp = lowPrice;
                } else {
                    lp = "0";
                }
                String ids = getPropertyIds();
                String brandIds = "";
                Log.e(TAG, "onClick: lowp is "+lowPrice+" highP is "+highPrice+" brandIds is "+brandIds +" spec ids is "+ids);
//                if (TextUtils.isEmpty(ids)) {
//                    Toast.makeText(ProductListActivity.this, "请先选择产品属性", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                filterProperty(lowPrice, highPrice, brandIds, ids);
                pw.dismiss();
            }
        });

        if (propertyList == null) {
            Toast.makeText(this, "propertyList == null ", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < propertyList.size(); i++) {
            //单一属性
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_property, null);
            TextView propertyName = (TextView) itemView.findViewById(R.id.tv_property_name);
            FlowLayout propertyFlow = (FlowLayout) itemView.findViewById(R.id.flow_property);
            //单一属性item
            final List<PropertyListEntity.ResultBean.SpecListBean.ItemsBean> items = propertyList.get(i).getItems();
            final List<TextView> tvList = new ArrayList<>();
            if (items == null) {
                return;
            }
            for (int j = 0; j < items.size(); j++) {
                PropertyListEntity.ResultBean.SpecListBean.ItemsBean bean = items.get(j);
                TextView tv = new TextView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20, 20, 20, 20);
                tv.setLayoutParams(lp);
                tv.setTextSize(12);
                tv.setText(items.get(j).getItem());
                tv.setGravity(Gravity.CENTER);
                tv.setTag(bean);
                tv.setId(j);
                tv.setTextColor(getResources().getColor(R.color.text_common_color));
                tv.setBackground(getResources().getDrawable(R.drawable.tag_bg_property));
                tvList.add(tv);
                propertyFlow.addView(tv);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int k = 0; k < tvList.size(); k++) {
                            TextView t = tvList.get(k);
                            PropertyListEntity.ResultBean.SpecListBean.ItemsBean b = (PropertyListEntity.ResultBean.SpecListBean.ItemsBean) t.getTag();
                            if (k == v.getId()) {
                                b.setSelected(true);
                                t.setTextColor(getResources().getColor(R.color.orange_txt));
                            } else {
                                b.setSelected(false);
                                t.setTextColor(Color.BLACK);
                            }
                        }
                    }
                });
            }
            propertyName.setText(propertyList.get(i).getName());
            propertiesLayout.addView(itemView);
        }
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
        popView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = ll.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        pw.dismiss();
                    }
                }
                return true;
            }
        });
    }

    private String getBrandIds() {
        String brandIds = null;
        StringBuffer sb = new StringBuffer();
        List<PropertyListEntity.ResultBean.BrandListBean> list = propertyListEntity.getResult().getBrand_list();
        for (int i = 0; i < list.size(); i++) {
            if (i!=list.size()-1){
                sb.append(list.get(i).getId() + ",");
            }else {
                sb.append(list.get(i).getId());
            }
        }
        brandIds = sb.toString();
        return brandIds;
    }

    private void resetProperty() {
        for (int i = 0; i < propertyList.size(); i++) {
            List<PropertyListEntity.ResultBean.SpecListBean.ItemsBean> list = propertyList.get(i).getItems();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setSelected(false);
            }
        }
        setPropertyUi();
    }

    private void filterProperty(String fromPrice, String toPrice, String brandIds, String propertyIds) {
        mLoadingRl.setVisibility(View.VISIBLE);
        JSONObject params = new JSONObject();
        try {
            params.put("from_price", fromPrice);
            params.put("to_price", toPrice);
            params.put("brand_ids", brandIds);
            params.put("spec_ids", propertyIds);
            params.put("city_code", Constants.CITY_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String p = params.toString();
        OkHttpUtils
                .postString()
                .url(ApiManager.URL_FILTER_PROPERTY)
                .mediaType(MediaType.parse("application/json;charset=utf8"))
                .content(p)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                        mLoadingRl.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "filterProperty : response is " + response);
                        mLoadingRl.setVisibility(View.GONE);
                    }
                });
    }

    private String getPropertyIds() {
        String propertyIds = null;
        String ids = null;
        StringBuffer sb2 = new StringBuffer();
        for (int i = 0; i < propertyList.size(); i++) {
            List<PropertyListEntity.ResultBean.SpecListBean.ItemsBean> list = propertyList.get(i).getItems();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).isSelected()) {
                    sb2.append(list.get(j).getSpec_id() + ",");
                }
            }
        }
        ids = sb2.toString();
        if (ids.isEmpty()) {
            return null;
        }
        propertyIds = ids.substring(0, ids.length() - 1);
        return propertyIds;
    }


    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
