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
import com.example.gtercn.car.mall.entity.AliPaySignEntity;
import com.example.gtercn.car.mall.entity.ResultEntity;
import com.example.gtercn.car.mall.entity.ReviewsEntity;
import com.example.gtercn.car.utils.Constants;
import com.example.gtercn.car.utils.OrderInfoUtil2_0;
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
                getAliPaySign();
//                payV2();
                break;
            case R.id.iv_select_wechat:
                mWechatPayIv.setImageResource(R.drawable.cart1_checkbox_check);
                mAliPayIv.setImageResource(R.drawable.cart1_checkbox_unable);
                mGoToPayTv.setText("使用微信支付" + getResources().getString(R.string.rmb) + mSum);
                break;
            case R.id.tv_go_pay:
                Toast.makeText(this, "跳转到相应的支付页面", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //沙箱环境下公钥
    /**
     *MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsKWqi73
     * TxV6qg54gB86XzPigCn0BdZcsS8UkkAo6Y5yF17h38r+aW2CJZG
     * wZE+eBGVjj0AvrWkh/eTjbzQ1AvtGJs7smOkox63bIAbCh2uwRZ
     * KiicIahSInz42m1DmijPnTsN63Y+hwLrkYSBxtKm/BPSiJE5r1efezqkd
     * /wzOtrB9j36yvQcfLyBUbwapxI72GVTeNPM+Jxa5BWdvF/rueniniGak
     * R5XLkIfqw6iq4AkJDx9s0IYD+NiiPaScZC+K9IK49Kw3yMgDqELqGVgylf55atw
     * EXLbYw3hbShbVLVEIduriaZDUcNTyli8I65sH7pjOMVaht3X+TaigXSywIDAQAB
     */

    /**
     * MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA9SGGhk4
     * snjbQ97mNw9ep6uHPwjgKSj2Udxeh+EhPUG1UlRYm857q4exh
     * PdulJdkbUxuhYVfAOKHZ68s8SKz0ob+61SvFECJXkg5VbTSHS
     * lieaoedmpFm+nvLr1YIX/hMNEHPpq+CLY9kjU659g0vHcDB3o
     * JAmYPDkNj/7ZyykFjjyN14EQR99VsFd/owLIYtkqBeeBgvJok
     * FMVs1ektQ/WvVAVMnWT8lX+P5m25fUH+ZPBaGnM/9KB9zl0RL
     * BpcelfHzI8biC9aIzLvwYXA9HcisV+9Ra3C+sCWpH/7boPW5Q
     * xME0ij/BGG9/AyyDynWCGp95+2Sg5gf2wMWfkHjCwIDAQAB
     */


    private void goToAliPay(final String sign) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        //需要支付的常量封装在constants
        //订单信息
        Runnable payRunnable = new Runnable() {//要在子线程
            @Override
            public void run() {
                PayTask aliPay = new PayTask(ChoosePayActivity.this);
                String result = aliPay.pay(sign, true);
                Log.e(TAG, "ali_return :" + result.toString());
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
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            Log.e(TAG, "handleMessage: msg is " + msg.toString());
            switch (msg.what) {
//                case SDK_PAY_FLAG: {
//                    @SuppressWarnings("unchecked")
//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
//                    /**
//                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//                     */
//                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//                    String resultStatus = payResult.getResultStatus();
//                    // 判断resultStatus 为9000则代表支付成功
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//                }
//                case SDK_AUTH_FLAG: {
//                    @SuppressWarnings("unchecked")
//                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
//                    String resultStatus = authResult.getResultStatus();
//
//                    // 判断resultStatus 为“9000”且result_code
//                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
//                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
//                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
//                        // 传入，则支付账户为该授权账户
//                        Toast.makeText(PayDemoActivity.this,
//                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
//                                .show();
//                    } else {
//                        // 其他状态值则为授权失败
//                        Toast.makeText(PayDemoActivity.this,
//                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
//
//                    }
//                    break;
//                }
                default:
                    break;
            }
        }

        ;
    };


//    private Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//
//            Toast.makeText(ChoosePayActivity.this, "----查看结果----msg is " + msg.toString(),
//                    Toast.LENGTH_LONG).show();
//
////            AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
////            switch (payResult.getResultStatus()) {
////                case "9000":
////                    GMToastUtil.showToast("支付成功");
////                    break;
////                case "8000":
////                    GMToastUtil.showToast("正在处理中");
////                    break;
////                case "4000":
////                    GMToastUtil.showToast("订单支付失败");
////                    break;
////                case "5000":
////                    GMToastUtil.showToast("重复请求");
////                    break;
////                case "6001":
////                    GMToastUtil.showToast("已取消支付");
////                    break;
////                case "6002":
////                    GMToastUtil.showToast("网络连接出错");
////                    break;
////                case "6004":
////                    GMToastUtil.showToast("正在处理中");
////                    break;
////                default:
////                    GMToastUtil.showToast("支付失败");
////                    break;
////            }
//            /**
//             * partner="2088921700888610"
//             * &seller_id="m13941850400@163.com"
//             * &out_trade_no="G88E01518146368915"
//             * &subject="车驿站订单"
//             * &body="车驿站订单"
//             * &total_fee="399.00"
//             * &service="mobile.securitypay.pay"
//             * &_input_charset="UTF-8"¬ify_url="http://114.215.71.170/car_inn/v1/ali/notifyurl"
//             * &payment_type="1"
//             * &app_id=" 2018011901977798"
//             * &it_b_pay="5m"
//             * &sign="J34KkrNNOCZ%2BGbC1s6DG0u40%2BUAj1ipnaMOJSbc6%2BLsFnWjNxriKOr8U0rc04hXBG14ZiBNd8CeXEBfFHhBLOOEOkJTdY4t673iKp573ld65dazxrwfQ8wYef62qcq5HDb9PyFLxhFEbAl8AR0OSO63S3huTudX5%2F4zqUmLrA%2FZ1KUnxjl2bxhJEgQNUZByJdHWImnMd%2B0XQC8a7Pgc%2Frqr7W7Xz5yHS5wMENbRWSPSdpzYLhBMo0WuYrP1p8WmFYf%2B%2FQTneARGucjbnc1F7zYMKLsdeNDJsB1EYU9kTwG6pGr%2FvU8CvC2cGvGSb65cYHOAV2dj%2F0Sl3hd%2FWold8OA%3D%3D"
//             * &sign_type="RSA""
//             */
//        }
//    };

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
                                goToAliPay(result.getResult().getKey());
                            }

                        }
                    }
                });
    }


    public void payV2() {
        if (TextUtils.isEmpty(Constants.APPID) || (TextUtils.isEmpty(Constants.RSA2_PRIVATE)
                && TextUtils.isEmpty(Constants.RSA2_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (Constants.RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(Constants.APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        Log.e(TAG, "params is " + params.toString());
        String privateKey = Constants.RSA2_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        Log.e(TAG, "orderInfo is " + orderInfo);

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ChoosePayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
