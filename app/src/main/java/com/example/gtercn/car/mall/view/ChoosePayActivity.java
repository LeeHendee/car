package com.example.gtercn.car.mall.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.adapter.MainViewPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_pay;
    }

    @Override
    protected void initView() {
        super.initView();
        initRightTvBar("车驿站收银台", null, null);
    }

    @OnClick({R.id.tv_go_pay, R.id.iv_select_alipay, R.id.iv_select_wechat})
    void myClick(View view) {
        switch (view.getId()) {
            case R.id.iv_select_alipay:
                mAliPayIv.setImageResource(R.drawable.cart1_checkbox_check);
                mWechatPayIv.setImageResource(R.drawable.cart1_checkbox_unable);
                mGoToPayTv.setText("使用支付宝支付￥" + "00.00");
                break;
            case R.id.iv_select_wechat:
                mWechatPayIv.setImageResource(R.drawable.cart1_checkbox_check);
                mAliPayIv.setImageResource(R.drawable.cart1_checkbox_unable);
                mGoToPayTv.setText("使用微信支付￥" + "00.00");
                break;
            case R.id.tv_go_pay:
                Toast.makeText(this, "跳转到相应的支付页面", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
