package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.interfaces.ResponseJSONObjectListener;
import com.example.gtercn.car.interfaces.ResponseStringListener;
import com.example.gtercn.car.mall.IListener;
import com.example.gtercn.car.mall.adapter.CartAdapter;
import com.example.gtercn.car.mall.entity.CartEntity;
import com.example.gtercn.car.mall.entity.CreatePreOrderEntity;
import com.example.gtercn.car.mall.entity.ResultEntity;
import com.example.gtercn.car.mall.view.custom_view.RecyItemSpace;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by Yan on 2018/1/1.
 * 购物车页面
 */

public class CartActivity extends BaseActivity implements IListener, CartAdapter.IChangeCount {

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

    private CartAdapter mAdapter;

    private List<CartEntity.ResultBean> cartList;

    private float allCost;

    private LinearLayout mTotalLayout;

    private TextView mDelTv;

    private boolean isDel;

    private RelativeLayout mLoadingRl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        cartList = new ArrayList<>();
        String sign = "1";
        String time = "time";
        ApiManager.getCartInfo(Constants.TOKEN, sign, time, new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: response is " + response);
                    Gson gson = new Gson();
                    CartEntity entity = gson.fromJson(response, CartEntity.class);
                    if (entity != null) {
                        cartList = entity.getResult();
                        mAdapter = new CartAdapter(CartActivity.this, cartList);
                        mCartRv.setAdapter(mAdapter);
                        mAdapter.setListener(CartActivity.this);
                        mAdapter.setiChangeCount(CartActivity.this);
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
            }
        }, 1, TAG);
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
                    isSelectAll(isAll);
                    isAll = !isAll;
                    break;
                case R.id.ll_to_checkout:
                    if (isDel) {
                        //调用删除接口
                        delCart();
                    } else {
                        Intent intent = new Intent(CartActivity.this, OrderConfirmActivity.class);
                        CreatePreOrderEntity params = new CreatePreOrderEntity();
                        List<CreatePreOrderEntity.GoodsListBean> listBeen = new ArrayList<>();
                        for (int i = 0; i < cartList.size(); i++) {
                            CreatePreOrderEntity.GoodsListBean bean = new CreatePreOrderEntity.GoodsListBean();
                            if (cartList.get(i).isSelected()) {
                                bean.setGoods_id(cartList.get(i).getGoods_id());
                                bean.setNumber(cartList.get(i).getNumber() + "");
                                bean.setSpec_item_ids(cartList.get(i).getSpec_item_ids());
                                listBeen.add(bean);
                            }
                        }
                        params.setGoods_list(listBeen);
                        intent.putExtra("params", params);
                        startActivity(intent);
                    }
                    break;
            }
        }
    };

    private void delCart() {
        String sign = "sign";
        String time = "time";
        String delIds = getDelIds();
        Map<String, String> params = new HashMap<>();
        params.put("cart_ids", delIds);
        mLoadingRl.setVisibility(View.VISIBLE);
        ApiManager.delCartItem(sign, time, params, new ResponseStringListener() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: " + response);
                    Gson gson = new Gson();
                    ResultEntity entity = gson.fromJson(response, ResultEntity.class);
                    if (entity != null) {
                        if (TextUtils.equals(entity.getErr_code(), "0")) {
                            Toast.makeText(CartActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            initData();
                        }

                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error, int type) {
                mLoadingRl.setVisibility(View.GONE);
            }
        }, 3, TAG);

    }

    private String getDelIds() {
        if (cartList == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cartList.size(); i++) {
            CartEntity.ResultBean b = cartList.get(i);
            if (b.isSelected()) {
                if (i != cartList.size() - 1) {
                    sb.append(b.getId() + ",");
                } else {
                    sb.append(b.getId() + "");
                }
            }
        }
        String delIds = sb.toString();

        return delIds;
    }

    private void isSelectAll(boolean isAll) {
        if (cartList == null || cartList.size() < 1) {
            return;
        }
        if (!isAll) {
            mSelectAllIv.setImageResource(R.drawable.cart1_checkbox_check);
            for (int i = 0; i < cartList.size(); i++) {
                CartEntity.ResultBean b = cartList.get(i);
                b.setSelected(true);
            }
        } else {
            mSelectAllIv.setImageResource(R.drawable.cart1_checkbox_normal);
            for (int i = 0; i < cartList.size(); i++) {
                CartEntity.ResultBean b = cartList.get(i);
                b.setSelected(false);
            }
        }
        computeCost();
        mAdapter.notifyDataSetChanged();
    }


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
        mTotalLayout = (LinearLayout) findViewById(R.id.ll_total);
        mDelTv = (TextView) findViewById(R.id.tv_checkout);
        mLoadingRl = (RelativeLayout) findViewById(R.id.rl_loading);
        mCartRv.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        mCartRv.addItemDecoration(new RecyItemSpace(30));
        setTitle(false);

    }

    private void setTitle(boolean flag) {
        if (!flag) {
            initRightTvBar("购物车", "编辑", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setTitle(true);
                    setDelUI(true);
                }
            });
        } else {
            initRightTvBar("购物车", "完成", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setTitle(false);
                    setDelUI(false);
                }
            });
        }

    }

    private void setDelUI(boolean del) {
        if (del) {
            mTotalLayout.setVisibility(View.INVISIBLE);
            mDelTv.setText("删除");
            isDel = true;
        } else {
            mTotalLayout.setVisibility(View.VISIBLE);
            mDelTv.setText("去结算");
            isDel = false;
        }
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {
    }

    @Override
    protected void onExecuteFailure(int type) {
    }

    @Override
    public void itemClickListener(int pos) {
        CartEntity.ResultBean b = cartList.get(pos);
        b.setSelected(!b.isSelected());
        mAdapter.notifyDataSetChanged();
        if (checkIsAll()) {
            mSelectAllIv.setImageResource(R.drawable.cart1_checkbox_check);
        } else {
            mSelectAllIv.setImageResource(R.drawable.cart1_checkbox_normal);
        }
        computeCost();
    }

    private boolean checkIsAll() {
        for (int i = 0; i < cartList.size(); i++) {
            CartEntity.ResultBean b = cartList.get(i);
            if (!b.isSelected()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void changeCount(boolean isAdd, int pos) {
        CartEntity.ResultBean b = cartList.get(pos);
        int count = b.getNumber();
        if (isAdd) {
            changeNumberToAPI(b.getId(), String.valueOf(count + 1));
        } else {
            if (count == 1) {
                Toast.makeText(this, "数量不能小于1", Toast.LENGTH_SHORT).show();
                return;
            }
            changeNumberToAPI(b.getId(), String.valueOf(count - 1));
        }
    }

    private void changeNumberToAPI(String cartId, String count) {
        String sign = "1";
        String time = "1";
        Map<String, String> params = new HashMap<>();
        params.put("cart_id", cartId);
        params.put("number", count);
        mLoadingRl.setVisibility(View.VISIBLE);
        ApiManager.changeCount(sign, time, params, new ResponseStringListener() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: " + response);
                    Gson gson = new Gson();
                    ResultEntity entity = gson.fromJson(response, ResultEntity.class);
                    if (entity != null && TextUtils.equals(entity.getErr_code(), "0")) {
                        initData();
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error, int type) {
                mLoadingRl.setVisibility(View.GONE);
            }
        }, 2, TAG);
    }

    private void computeCost() {
        int count = 0;
        allCost = 0;
        for (int i = 0; i < cartList.size(); i++) {
            CartEntity.ResultBean bean = cartList.get(i);
            if (bean.isSelected()) {
                allCost += Float.valueOf(TextUtils.isEmpty(bean.getPromotion_price()) ? "0" : bean.getPromotion_price()) * bean.getNumber();
                count += bean.getNumber();
            }
        }
        mCostTv.setText(getResources().getString(R.string.rmb) + allCost);
        mTotalTv.setText(getResources().getString(R.string.rmb) + allCost);
        mCheckoutCountTv.setText("(" + count + ")");
    }
}
