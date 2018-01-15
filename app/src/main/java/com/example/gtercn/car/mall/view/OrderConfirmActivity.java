package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mLoadingRl.setVisibility(View.GONE);

        Intent intent = getIntent();
        String goodId = intent.getStringExtra("goodId");
        String number = intent.getStringExtra("number");
        String totalCost = intent.getStringExtra("totalCost");
        String propertyIds = intent.getStringExtra("propertyIds");
        String addressId = intent.getStringExtra("addressId");
//        String goodId = intent.getStringExtra("goodId");

        String sign = "sign";
        String time = "time";
        String url = ApiManager.URL_BUY_NOW+"?token="+ Constants.TOKEN +"&sign="+sign+"&t="+time;

        JSONObject params = new JSONObject();
        try {
            params.put("goods_id",goodId);
            params.put("number",number);
            params.put("total_price",totalCost);
            params.put("spec_item_ids",propertyIds);
            params.put("address_id",addressId);
            params.put("customer_mark","123");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json;charset=utf-8"))
                .content(new Gson().toJson(params))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

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
                break;
            case R.id.rl_address:
                Toast.makeText(this, "跳转的选择地址", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderConfirmActivity.this, ChooseAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
