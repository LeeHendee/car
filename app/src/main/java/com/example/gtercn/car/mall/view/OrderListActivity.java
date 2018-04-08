package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.base.CarApplication;
import com.example.gtercn.car.bean.User;
import com.example.gtercn.car.mall.IListenerTwo;
import com.example.gtercn.car.mall.adapter.OrderListAdapter;
import com.example.gtercn.car.mall.entity.OrderListEntity;
import com.example.gtercn.car.mall.entity.ResultEntity;
import com.example.gtercn.car.mall.view.custom_view.RecyItemSpace;
import com.example.gtercn.car.utils.Constants;
import com.example.gtercn.car.utils.MD5;
import com.google.gson.Gson;
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

    @BindView(R.id.empty_rl)
    RelativeLayout mEmptyRl;

    private OrderListAdapter mAdapter;

    private List<OrderListEntity.ResultBean> orderList;

    private String mStatus = "0";
    private CarApplication mApp;
    private User mUser;

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
        mLoadingRl.setVisibility(View.VISIBLE);
        orderList = new ArrayList<>();
        mApp = (CarApplication) getApplication();
        mUser = mApp.getUser();
        String sign = MD5.getSign(ApiManager.URL_ORDER_LIST, mUser);
        String time = MD5.gettimes();
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
                                if (orderList != null && orderList.size() > 0) {
                                    mEmptyRl.setVisibility(View.GONE);
                                    setUi();
                                } else {
                                    mEmptyRl.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                });
    }

    private void setUi() {
        mAdapter = new OrderListAdapter(this, orderList);
        mOrderRv.setAdapter(mAdapter);
        mAdapter.setCancelListener(new IListenerTwo() {
            @Override
            public void listener(int pos, int doWhat) {
                cancelOrder(orderList.get(pos));
            }
        });
        
        mAdapter.setDelListener(new IListenerTwo() {
            @Override
            public void listener(int pos, int doWhat) {
                delOrder(orderList.get(pos));
            }
        });
    }

    private void delOrder(OrderListEntity.ResultBean entity) {
        String sign = MD5.getSign(ApiManager.URL_ORDER_DEL, mUser);
        String time = MD5.gettimes();
        String url = ApiManager.URL_ORDER_DEL + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + time;
        Log.e(TAG, "delOrder: url is " + url);
        OkHttpUtils
                .post()
                .url(url)
                .addParams("order_id",entity.getId())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: response is " + response);
                        if (TextUtils.isEmpty(response)){
                            return;
                        }
                        ResultEntity re = new Gson().fromJson(response,ResultEntity.class);
                        if (re!=null&&TextUtils.equals(re.getErr_code(),"0")){
                            Toast.makeText(OrderListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            initData(mStatus);
                        }else {
                            Toast.makeText(OrderListActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick({
            R.id.rl_all,
            R.id.rl_wait_pay,
            R.id.rl_wait_accept,
            R.id.rl_wait_review,
            R.id.rl_done,
    })

        //订单状态，0查询全部,1待付款,4已发货(待收货),5已签收(待评价),6已评价(订单完成),
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
                mStatus = "2";
                initData(mStatus);
                mIndexAll.setVisibility(View.GONE);
                mIndexWaitPay.setVisibility(View.GONE);
                mIndexWaitAccept.setVisibility(View.VISIBLE);
                mIndexWaitReview.setVisibility(View.GONE);
                mIndexDone.setVisibility(View.GONE);
                break;
            case R.id.rl_wait_review:
                mStatus = "5";
                initData(mStatus);
                mIndexAll.setVisibility(View.GONE);
                mIndexWaitPay.setVisibility(View.GONE);
                mIndexWaitAccept.setVisibility(View.GONE);
                mIndexWaitReview.setVisibility(View.VISIBLE);
                mIndexDone.setVisibility(View.GONE);
                break;
            case R.id.rl_done:
                mStatus = "6";
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

    private void cancelOrder(OrderListEntity.ResultBean entity) {
        JSONObject json = new JSONObject();
        try {
            json.put("order_id", entity.getId());
            json.put("cancel_type", "1");
            json.put("cancel_reason", "重复提交");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String sign = MD5.getSign(ApiManager.URL_ORDER_CANCEL, mUser);
        String time = MD5.gettimes();
        String url = ApiManager.URL_ORDER_CANCEL + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + time;
        Log.e(TAG, "cancelOrder: url is " + url);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json;charset=utf8"))
                .content(json.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: response is " + response);
                        if (TextUtils.isEmpty(response)){
                            return;
                        }
                        ResultEntity re = new Gson().fromJson(response,ResultEntity.class);
                        if (re!=null&&TextUtils.equals(re.getErr_code(),"0")){
                            Toast.makeText(OrderListActivity.this, "订单已取消", Toast.LENGTH_SHORT).show();
                            initData(mStatus);
                        }else {
                            Toast.makeText(OrderListActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
