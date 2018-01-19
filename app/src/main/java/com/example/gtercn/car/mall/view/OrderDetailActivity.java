package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.mall.entity.OrderDetailEntity;
import com.example.gtercn.car.mall.entity.OrderListEntity;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
        mOrderId = getIntent().getStringExtra("orderId");
        String sign = "sign";
        String time = "time";
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
                        Log.e(TAG, "onResponse: response is " + response);
                        if (response != null) {
                            Gson gson = new Gson();
                            OrderDetailEntity entity = gson.fromJson(response, OrderDetailEntity.class);
                            if (entity != null && entity.getErr_code().equals("0")) {
                                setUi(entity.getResult());
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
        mInvoiceTypeTv.setText(bean.getInvoice_type());
//        mPayMethodTv.setText(bean.get);
        mOrderTotalTv.setText(getResources().getString(R.string.rmb) + bean.getTotal_amount());
        mActualPayTv.setText(getResources().getString(R.string.rmb) + bean.getPayment());
        mCreateTimeTv.setText(bean.getOrder_time() + "");
    }


    private void initListener() {
        mCopyTv.setOnClickListener(mListener);
    }

    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_copy:
                    break;
            }
        }
    };

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
        }
        return null;
    }
}
