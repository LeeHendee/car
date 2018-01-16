package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.gtercn.car.R;
import com.example.gtercn.car.adapter.GalleryAdapter;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.mall.adapter.OrderAdapter;
import com.example.gtercn.car.mall.entity.OrderListEntity;
import com.example.gtercn.car.mall.entity.SingleItemEntity;
import com.example.gtercn.car.mall.view.custom_view.RecyItemSpace;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/4.
 * Used to : 订单管理页面
 */

public class OrderListActivity extends BaseActivity {

    private static final String TAG = "OrderListActivity";

    @BindView(R.id.rl_all)
    RelativeLayout mAllRl;

    @BindView(R.id.rl_wait_pay)
    RelativeLayout mWaitPayRl;

    @BindView(R.id.rl_wait_accept)
    RelativeLayout mWaitAcceptRl;

    @BindView(R.id.rl_wait_review)
    RelativeLayout mWaitReviewRl;

    @BindView(R.id.rl_done)
    RelativeLayout mDoneRl;

    @BindView(R.id.tv_index_all)
    TextView mIndexAll;

    @BindView(R.id.tv_wait_pay_index)
    TextView mIndexWaitPay;

    @BindView(R.id.tv_wait_accept_index)
    TextView mIndexWaitAccept;

    @BindView(R.id.tv_wait_review_index)
    TextView mIndexWaitReview;

    @BindView(R.id.tv_done_index)
    TextView mIndexDone;

    @BindView(R.id.recy_order)
    RecyclerView mOrderRv;

    @BindView(R.id.rl_loading)
    RelativeLayout mLoadingRl;

    private OrderAdapter mAdapter;

    private List<OrderListEntity.ResultBean> orderList;

    private String mStatus = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData(mStatus);
    }

    private void initView() {
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        mIndexAll.setVisibility(View.VISIBLE);
        initRightTvBar("我的订单", null, null);
        mOrderRv.addItemDecoration(new RecyItemSpace(30));
        mOrderRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData(String status) {
        orderList = new ArrayList<>();
        String sign = "sign";
        String time = "time";
        String url = ApiManager.URL_ORDER_LIST;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("status", status)
                .addParams("token", Constants.TOKEN)
                .addParams("sign", sign)
                .addParams("t", time)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                        mLoadingRl.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: response is " + response);
                        mLoadingRl.setVisibility(View.GONE);
                        if (response != null) {
                            Gson gson = new Gson();
                            OrderListEntity entity = gson.fromJson(response, OrderListEntity.class);
                            if (entity != null) {
                                orderList = entity.getResult();
                                setUi();
                            }
                        }


                    }
                });
    }

    private void setUi() {
        mAdapter = new OrderAdapter(this, orderList);
        mOrderRv.setAdapter(mAdapter);
    }

    @OnClick({
            R.id.rl_all,
            R.id.rl_wait_pay,
            R.id.rl_wait_accept,
            R.id.rl_wait_review,
            R.id.rl_done,
    })
    void myClick(View view) {
        switch (view.getId()) {
            case R.id.rl_all:
                mStatus = "0";
                initData(mStatus);
                mIndexAll.setVisibility(View.VISIBLE);
                mIndexWaitPay.setVisibility(View.GONE);
                mIndexWaitAccept.setVisibility(View.GONE);
                mIndexWaitReview.setVisibility(View.GONE);
                mIndexDone.setVisibility(View.GONE);
                break;
            case R.id.rl_wait_pay:
                mStatus = "1";
                initData(mStatus);
                mIndexAll.setVisibility(View.GONE);
                mIndexWaitPay.setVisibility(View.VISIBLE);
                mIndexWaitAccept.setVisibility(View.GONE);
                mIndexWaitReview.setVisibility(View.GONE);
                mIndexDone.setVisibility(View.GONE);
                break;
            case R.id.rl_wait_accept:
                mStatus = "3";
                initData(mStatus);
                mIndexAll.setVisibility(View.GONE);
                mIndexWaitPay.setVisibility(View.GONE);
                mIndexWaitAccept.setVisibility(View.VISIBLE);
                mIndexWaitReview.setVisibility(View.GONE);
                mIndexDone.setVisibility(View.GONE);
                break;
            case R.id.rl_wait_review:
                mStatus = "1";
                initData(mStatus);
                mIndexAll.setVisibility(View.GONE);
                mIndexWaitPay.setVisibility(View.GONE);
                mIndexWaitAccept.setVisibility(View.GONE);
                mIndexWaitReview.setVisibility(View.VISIBLE);
                mIndexDone.setVisibility(View.GONE);
                break;
            case R.id.rl_done:
                mStatus = "4";
                initData(mStatus);
                mIndexAll.setVisibility(View.GONE);
                mIndexWaitPay.setVisibility(View.GONE);
                mIndexWaitAccept.setVisibility(View.GONE);
                mIndexWaitReview.setVisibility(View.GONE);
                mIndexDone.setVisibility(View.VISIBLE);
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
