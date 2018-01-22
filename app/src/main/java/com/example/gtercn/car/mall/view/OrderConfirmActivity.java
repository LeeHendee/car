package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.mall.entity.ConfirmOrderEntity;
import com.example.gtercn.car.mall.entity.CreatePreOrderEntity;
import com.example.gtercn.car.mall.entity.DefaultEntity;
import com.example.gtercn.car.mall.entity.PreOrderEntity;
import com.example.gtercn.car.mall.entity.ProductDetailEntity;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;


/**
 * Author ：LeeHang
 * CreateTime ：2018/1/10.
 * Used to : 确认订单
 */

public class OrderConfirmActivity extends BaseActivity {

    private static final String TAG = "OrderConfirmActivity";

    @BindView(R.id.rl_address)
    RelativeLayout mAddressRl;

    @BindView(R.id.rl_loading)
    RelativeLayout mLoadingRl;

    @BindView(R.id.tv_name)
    TextView mNameTv;

    @BindView(R.id.tv_tel)
    TextView mTelTv;

    @BindView(R.id.tv_address)
    TextView mAddressTv;

    @BindView(R.id.tv_total_org)
    TextView mOrgTotalTv;

    @BindView(R.id.tv_delivery_fee)
    TextView mDeliveryFeeTv;

    @BindView(R.id.tv_total_pay)
    TextView mTotalPayTv;

    @BindView(R.id.tv_submit_order)
    TextView mSubmitOrderTv;

    @BindView(R.id.ll_product_view)
    LinearLayout mProductLayout;

    private ProductDetailEntity.ResultBean productEntity;
    private int mCount;

    private CreatePreOrderEntity params;

    private List<ConfirmOrderEntity.ResultBean.GoodsListBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        getDefaultAddress();
        Intent intent = getIntent();
        params = (CreatePreOrderEntity) intent.getSerializableExtra("params");

        String sign = "sign";
        String time = "time";
        String url = ApiManager.URL_CREATE_PRE_ORDER + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + time;
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json;charset=utf8"))
                .content(new Gson().toJson(params))
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
                            ConfirmOrderEntity entity = new Gson().fromJson(response, ConfirmOrderEntity.class);
                            if (entity != null) {
                                list = entity.getResult().getGoods_list();
                                setUi();
                            }
                        }
                    }
                });

    }


    // TODO: 2018/1/22 待调试
    private void setUi() {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ConfirmOrderEntity.ResultBean.GoodsListBean bean = list.get(i);
                View view = LayoutInflater.from(this).inflate(R.layout.item_single_product, null);
                ImageView itemIv = (ImageView) view.findViewById(R.id.iv_item);
                TextView titleTv = (TextView) view.findViewById(R.id.tv_title);
                TextView priceTv = (TextView) view.findViewById(R.id.tv_price);
                TextView countTv = (TextView) view.findViewById(R.id.tv_count);
                Picasso.with(this).load(bean.getPicture()).into(itemIv);
                titleTv.setText(bean.getGoods_title());
                priceTv.setText(getResources().getString(R.string.rmb) + bean.getPromotion_price());
                countTv.setText("x" + bean.getNumber());

                mProductLayout.addView(view);
            }
            mOrgTotalTv.setText(getResources().getString(R.string.rmb) + "111");
            mTotalPayTv.setText("实付款: " + getResources().getString(R.string.rmb) + "99");
        }


    }

    private void setAddressUi(DefaultEntity entity) {
        mNameTv.setText(entity.getResult().getName());
        mTelTv.setText(entity.getResult().getPhone());
        mAddressTv.setText(entity.getResult().getProvince() + entity.getResult().getCity() + entity.getResult().getDistrict() + " " + entity.getResult().getAddress());
    }

    private void getDefaultAddress() {
        String sign = "sign";
        String time = "time";
        OkHttpUtils
                .get()
                .url(ApiManager.URL_GET_DEFAULT_ADDRESS)
                .addParams("token", Constants.TOKEN)
                .addParams("sign", sign)
                .addParams("t", time)
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
                            Log.e(TAG, "onResponse: response is " + response);
                            Gson gson = new Gson();
                            DefaultEntity entity = gson.fromJson(response, DefaultEntity.class);
                            if (entity != null) {
                                setAddressUi(entity);
                            }
                        }
                    }
                });
    }

    private void initView() {
        setContentView(R.layout.activity_order_confirm);
        ButterKnife.bind(this);
        initRightTvBar("确认订单", null, null);
    }

    @OnClick({R.id.rl_address, R.id.tv_submit_order})
    void myClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit_order:
                Toast.makeText(this, "提交", Toast.LENGTH_SHORT).show();
                orderConfirm();
                break;
            case R.id.rl_address:
                Toast.makeText(this, "跳转的选择地址", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderConfirmActivity.this, ChooseAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void orderConfirm() {
        PreOrderEntity entity = new PreOrderEntity();
        List<PreOrderEntity.GoodsAttrListBean> list = new ArrayList<>();
        PreOrderEntity.GoodsAttrListBean bean = new PreOrderEntity.GoodsAttrListBean();
//        bean.setGoods_id(productEntity.getProduct_id());
        bean.setGoods_id("2");
        bean.setNumber(mCount + "");
        bean.setSpec_item_ids("1");
        list.add(bean);
        entity.setGoods_attr_list(list);
        entity.setTotal_price(productEntity.getPromotion_price() * mCount + "");
        entity.setAddress_id("1");
        entity.setCustomer_mark("生成订单1");
        entity.setInvoice("N");
        entity.setItem_count(mCount + "");
        String sign = "sign";
        String time = "time";
        Log.e(TAG, "initData: entity is " + entity.toString());
        String url = ApiManager.URL_PRE_ORDER + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + time;
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json;charset=utf8"))
                .content(new Gson().toJson(entity))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                        mLoadingRl.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mLoadingRl.setVisibility(View.GONE);
                        if (response != null) {
                            Log.e(TAG, "onResponse: response is " + response);
                            Gson gson = new Gson();
                            Intent intent = new Intent(OrderConfirmActivity.this, ChoosePayActivity.class);
                            startActivity(intent);
                        }
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
