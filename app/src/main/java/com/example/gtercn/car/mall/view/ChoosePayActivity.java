package com.example.gtercn.car.mall.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.CarApplication;
import com.example.gtercn.car.bean.User;
import com.example.gtercn.car.mall.entity.AliPaySignEntity;
import com.example.gtercn.car.mall.entity.AliResultEntity;
import com.example.gtercn.car.mall.entity.ResultEntity;
import com.example.gtercn.car.mall.entity.ReviewsEntity;
import com.example.gtercn.car.mall.pay.PayResult;
import com.example.gtercn.car.utils.Constants;
import com.example.gtercn.car.utils.MD5;
import com.example.gtercn.car.utils.OrderInfoUtil2_0;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

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

    public static final int SDK_PAY_FLAG = 101;

    private double mSum;

    private boolean isAliPay = true;
    private CarApplication mApp;
    private User mUser;


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
        mApp = (CarApplication) getApplication();
        mUser = mApp.getUser();
        Intent intent = getIntent();
        mOrderId = intent.getStringExtra("orderId");
        mSum = intent.getDoubleExtra("sum", 0);
        mNeedPayTv.setText(getResources().getString(R.string.rmb) + mSum);
        mAliPayIv.setImageResource(R.drawable.cart1_checkbox_check);
        mGoToPayTv.setText("使用支付宝支付" + getResources().getString(R.string.rmb) + mSum);
    }

    @OnClick({R.id.tv_go_pay, R.id.iv_select_alipay, R.id.iv_select_wechat})
    void myClick(View view) {
        switch (view.getId()) {
            case R.id.iv_select_alipay:
                mAliPayIv.setImageResource(R.drawable.cart1_checkbox_check);
                mWechatPayIv.setImageResource(R.drawable.cart1_checkbox_unable);
                mGoToPayTv.setText("使用支付宝支付" + getResources().getString(R.string.rmb) + mSum);
                isAliPay = true;
                break;
            case R.id.iv_select_wechat:
                mWechatPayIv.setImageResource(R.drawable.cart1_checkbox_check);
                mAliPayIv.setImageResource(R.drawable.cart1_checkbox_unable);
                mGoToPayTv.setText("使用微信支付" + getResources().getString(R.string.rmb) + mSum);
                isAliPay = false;
                break;
            case R.id.tv_go_pay:
                if (isAliPay) {
                    getAliPaySign();
                } else {
                    Toast.makeText(this, "微信支付正在玩命建设中，敬请期待~", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void goToAliPay(final String sign) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        //需要支付的常量封装在constants
        //订单信息
        Runnable payRunnable = new Runnable() {//要在子线程
            @Override
            public void run() {
                PayTask aliPay = new PayTask(ChoosePayActivity.this);
                Map<String, String> result = aliPay.payV2(sign, true);
                Log.e(TAG, "ali_return :" + result);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);

            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e(TAG, "handleMessage: msg is " + msg.toString());
            switch (msg.what) {
                case SDK_PAY_FLAG:

                    Map<String, String> map = (Map<String, String>) msg.obj;
                    String resultStatus = map.get("resultStatus");

                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Log.e(TAG, "handleMessage: in 9000");
                        showToast("支付成功");
                        Intent intent = new Intent(ChoosePayActivity.this, OrderDetailActivity.class);
                        intent.putExtra("orderId", mOrderId);
                        intent.putExtra("isPayBack", true);
                        startActivity(intent);
//                        Toast.makeText(ChoosePayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ChoosePayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChoosePayActivity.this, OrderDetailActivity.class);
                        intent.putExtra("orderId", mOrderId);
                        intent.putExtra("isPayBack", true);
                        startActivity(intent);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 起调支付宝支付接口前通过服务器获取订单签名，在成功返回后调用支付宝支付
     */
    private void getAliPaySign() {

        String sign = MD5.getSign(ApiManager.URL_GET_ALIPAY_SIGN, mUser);
        String time = MD5.gettimes();
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
                                goToAliPay(result.getResult().getKey());
                            }

                        }
                    }
                });
    }

}
