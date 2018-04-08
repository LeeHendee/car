package com.example.gtercn.car.mall.view;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.CarApplication;
import com.example.gtercn.car.bean.User;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.mall.entity.AddressEntity;
import com.example.gtercn.car.mall.entity.OrderDetailEntity;
import com.example.gtercn.car.mall.entity.OrderListEntity;
import com.example.gtercn.car.utils.Constants;
import com.example.gtercn.car.utils.GetTimeData;
import com.example.gtercn.car.utils.MD5;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/18.
 * Used to :
 */
public class OrderDetailActivity extends BaseActivity {

    private static final String TAG = "OrderDetailActivity";

    private String mOrderId;

    private List<AddressEntity.ResultBean> addressList;

    @BindView(R.id.tv_status)
    TextView mOrderStatusTv;

    @BindView(R.id.tv_order_number)
    TextView mOrderNumberTv;

    @BindView(R.id.tv_copy)
    TextView mCopyTv;

    @BindView(R.id.tv_name)
    TextView mNameTv;

    @BindView(R.id.tv_tel)
    TextView mTelTv;

    @BindView(R.id.tv_address)
    TextView mAddressTv;

    @BindView(R.id.ll_wrapper)
    LinearLayout mWrapperLayout;

    @BindView(R.id.tv_invoice_title)
    TextView mInvoiceTitleTv;

    @BindView(R.id.tv_invoice_type)
    TextView mInvoiceTypeTv;

    @BindView(R.id.tv_pay_method)
    TextView mPayMethodTv;

    @BindView(R.id.tv_total_pay)
    TextView mOrderTotalTv;

    @BindView(R.id.tv_delivery_fee)
    TextView mDeliveryFeeTv;

    @BindView(R.id.tv_pay_actual)
    TextView mActualPayTv;

    @BindView(R.id.tv_create_time)
    TextView mCreateTimeTv;

    @BindView(R.id.iv_title_left)
    ImageView mBackIv;

    private boolean isPayBack;
    private OrderDetailEntity mEntity;
    private CarApplication mApp;
    private User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        initRightIvBar("订单详情", 0, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mApp = (CarApplication) getApplication();
        mUser = mApp.getUser();
        mOrderId = getIntent().getStringExtra("orderId");
        isPayBack = getIntent().getBooleanExtra("isPayBack", false);
        String sign = MD5.getSign(ApiManager.URL_ORDER_DETAIL, mUser);
        String time = MD5.gettimes();
        OkHttpUtils
                .get()
                .url(ApiManager.URL_ORDER_DETAIL)
                .addParams("order_id", mOrderId)
                .addParams("token", Constants.TOKEN)
                .addParams("sign", sign)
                .addParams("t", time)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "initData: response is " + response);
                        if (response != null) {
                            Gson gson = new Gson();
                            mEntity = gson.fromJson(response, OrderDetailEntity.class);
                            if (mEntity != null && mEntity.getErr_code().equals("0")) {
                                getAddress();
                                setUi(mEntity.getResult());
                            }
                        }
                    }

                });
    }

    private void setUi(OrderDetailEntity.ResultBean bean) {
        if (bean == null)
            return;
        mOrderStatusTv.setText(getOrderStatus(bean));
        mOrderNumberTv.setText(bean.getOrder_no());
        mNameTv.setText(bean.getRealname());
        mTelTv.setText(bean.getTelphone());
        mAddressTv.setText(bean.getAddress());
        mInvoiceTitleTv.setText(bean.getInvoice_title());
//        mInvoiceTypeTv.setText(bean.getInvoice_type());
        mInvoiceTypeTv.setText("个人发票");
//        mPayMethodTv.setText(bean.get);
        mOrderTotalTv.setText(getResources().getString(R.string.rmb) + bean.getTotal_amount());
        mActualPayTv.setText(getResources().getString(R.string.rmb) + bean.getPayment());
        mCreateTimeTv.setText(GetTimeData.formatTime(bean.getOrder_time()));
    }

    private void getAddress() {
        addressList = new ArrayList<>();
        String sign = MD5.getSign(ApiManager.URL_MANAGE_ADDRESS, mUser);
        String time = MD5.gettimes();
        ApiManager.getAddressList(sign, time, new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
//                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: " + response);
                    Gson g = new Gson();
                    AddressEntity entity = g.fromJson(response, AddressEntity.class);
                    if (entity != null && entity.getErr_code().equals("0")) {
                        addressList = entity.getResult();
                        setAddressUI(entity);
//                        setUI();
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
//                .setVisibility(View.GONE);
            }
        }, 1, TAG);
    }

    private void setAddressUI(AddressEntity entity) {
        if (entity == null) return;
        for (int i = 0; i < entity.getResult().size(); i++) {
            AddressEntity.ResultBean bean = entity.getResult().get(i);
            if (TextUtils.equals(mEntity.getResult().getAddress_id(), bean.getId())) {
                mNameTv.setText(bean.getName());
                mTelTv.setText(bean.getPhone());
                mAddressTv.setText(bean.getAddress());
            }
        }
    }

    private void initListener() {
        mCopyTv.setOnClickListener(mListener);
        mBackIv.setOnClickListener(mListener);
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_copy:
                    copyStr();
                    break;
                case R.id.iv_title_left:
                    Log.e(TAG, "onClick: isPayback is" + isPayBack);
//                    if (isPayBack) {
//                        Intent intent = new Intent(OrderDetailActivity.this, OrderListActivity.class);
//                        startActivity(intent);
//                    } else {
                    finish();
//                    }
            }
        }
    };

    private void copyStr() {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(mOrderNumberTv.getText().toString().trim());
        Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
    }

    private String getOrderStatus(OrderDetailEntity.ResultBean bean) {
        if (bean == null)
            return null;
        int orderStatus = bean.getOrder_status();
        switch (orderStatus) {
            case 1:
                return "待付款";
            case 2:
                return "待发货";
            case 3:
                return "已关闭";
            case 4:
                return "待收货";
            case 5:
                return "待评价";
            case 6:
                return "已完成";
            case 8:
                return "退货中";
            case 9:
                return "已退货";
            case 10:
                return "已取消";
        }
        return null;
    }
}
