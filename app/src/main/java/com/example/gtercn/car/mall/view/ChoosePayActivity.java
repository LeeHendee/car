package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.mall.entity.AliPaySignEntity;
import com.example.gtercn.car.mall.entity.ResultEntity;
import com.example.gtercn.car.mall.entity.ReviewsEntity;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/16.
 * Used to : 选择支付方式
 */

public class ChoosePayActivity extends BaseActivity {

    private static final String TAG = "ChoosePayActivity";

    @BindView(R.id.tv_need_pay)
    TextView mNeedPayTv;

    @BindView(R.id.tv_go_pay)
    TextView mGoToPayTv;

    @BindView(R.id.iv_select_alipay)
    ImageView mAliPayIv;

    @BindView(R.id.iv_select_wechat)
    ImageView mWechatPayIv;

    private String mOrderId;

    private int SDK_PAY_FLAG = 101;

    private double mSum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_pay;
    }

    @Override
    protected void initView() {
        super.initView();
        initRightTvBar("车驿站收银台", null, null);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        mOrderId = intent.getStringExtra("orderId");
        mSum = intent.getDoubleExtra("sum",0);
        mNeedPayTv.setText(getResources().getString(R.string.rmb)+ mSum);
        mWechatPayIv.setImageResource(R.drawable.cart1_checkbox_check);
        mGoToPayTv.setText("使用支付宝支付￥" + mSum);
    }

    @OnClick({R.id.tv_go_pay, R.id.iv_select_alipay, R.id.iv_select_wechat})
    void myClick(View view) {
        switch (view.getId()) {
            case R.id.iv_select_alipay:
                mAliPayIv.setImageResource(R.drawable.cart1_checkbox_check);
                mWechatPayIv.setImageResource(R.drawable.cart1_checkbox_unable);
                mGoToPayTv.setText("使用支付宝支付￥" + mSum);
                getAliPaySign();
                break;
            case R.id.iv_select_wechat:
                mWechatPayIv.setImageResource(R.drawable.cart1_checkbox_check);
                mAliPayIv.setImageResource(R.drawable.cart1_checkbox_unable);
                mGoToPayTv.setText("使用微信支付￥" + mSum);
                break;
            case R.id.tv_go_pay:
                Toast.makeText(this, "跳转到相应的支付页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void goToAliPay(final String sign) {
        //需要支付的常量封装在constants
        // 订单信息
        Runnable payRunnable = new Runnable() {//要在子线程
            @Override
            public void run() {
                PayTask aliPay = new PayTask(ChoosePayActivity.this);
                Map<String, String> result = aliPay.payV2(sign, true);//这里的orderInfo就是上面说的orderInfo
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            Toast.makeText(ChoosePayActivity.this, "----查看结果----msg is " + msg.toString(),
                    Toast.LENGTH_LONG).show();

//            AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
//            switch (payResult.getResultStatus()) {
//                case "9000":
//                    GMToastUtil.showToast("支付成功");
//                    break;
//                case "8000":
//                    GMToastUtil.showToast("正在处理中");
//                    break;
//                case "4000":
//                    GMToastUtil.showToast("订单支付失败");
//                    break;
//                case "5000":
//                    GMToastUtil.showToast("重复请求");
//                    break;
//                case "6001":
//                    GMToastUtil.showToast("已取消支付");
//                    break;
//                case "6002":
//                    GMToastUtil.showToast("网络连接出错");
//                    break;
//                case "6004":
//                    GMToastUtil.showToast("正在处理中");
//                    break;
//                default:
//                    GMToastUtil.showToast("支付失败");
//                    break;
//            }
            /**
             * partner="2088921700888610"
             * &seller_id="m13941850400@163.com"
             * &out_trade_no="G88E01518146368915"
             * &subject="车驿站订单"
             * &body="车驿站订单"
             * &total_fee="399.00"
             * &service="mobile.securitypay.pay"
             * &_input_charset="UTF-8"¬ify_url="http://114.215.71.170/car_inn/v1/ali/notifyurl"
             * &payment_type="1"
             * &app_id=" 2018011901977798"
             * &it_b_pay="5m"
             * &sign="J34KkrNNOCZ%2BGbC1s6DG0u40%2BUAj1ipnaMOJSbc6%2BLsFnWjNxriKOr8U0rc04hXBG14ZiBNd8CeXEBfFHhBLOOEOkJTdY4t673iKp573ld65dazxrwfQ8wYef62qcq5HDb9PyFLxhFEbAl8AR0OSO63S3huTudX5%2F4zqUmLrA%2FZ1KUnxjl2bxhJEgQNUZByJdHWImnMd%2B0XQC8a7Pgc%2Frqr7W7Xz5yHS5wMENbRWSPSdpzYLhBMo0WuYrP1p8WmFYf%2B%2FQTneARGucjbnc1F7zYMKLsdeNDJsB1EYU9kTwG6pGr%2FvU8CvC2cGvGSb65cYHOAV2dj%2F0Sl3hd%2FWold8OA%3D%3D"
             * &sign_type="RSA""
             */
        }
    };

    /**
     * 起调支付宝支付接口前通过服务器获取订单签名，在成功返回后调用支付宝支付
     */
    private void getAliPaySign() {
        String time = "t";
        String sign = "sign";
        String url = ApiManager.URL_GET_ALIPAY_SIGN + "?token=" + Constants.TOKEN + "&t=" + time + "&sign=" + sign;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("order_id", mOrderId)
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
                            AliPaySignEntity result = gson.fromJson(response, AliPaySignEntity.class);
                            if (result != null && result.getErr_code().equals("0")) {
                                Log.e(TAG, "onResponse: aliPaySign = " + result.getResult().getKey());
                                Toast.makeText(ChoosePayActivity.this, "接下来该调用支付宝支付", Toast.LENGTH_SHORT).show();
                                goToAliPay(result.getResult().getKey());
                            }

                        }
                    }
                });
    }
}
