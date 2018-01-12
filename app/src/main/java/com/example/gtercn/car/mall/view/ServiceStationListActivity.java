package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.mall.adapter.StationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/12.
 * Used to :
 */

public class ServiceStationListActivity extends BaseActivity {

    private static final String TAG = "ServiceStationListActivity";

    private StationAdapter mAdapter;

    @BindView(R.id.tv_province)
    TextView mProvinceTv;

    @BindView(R.id.tv_city)
    TextView mCityTv;

    @BindView(R.id.tv_district)
    TextView mDistrictTv;

    @BindView(R.id.recy_station)
    RecyclerView mStationRv;

    @BindView(R.id.rl_loading)
    RelativeLayout mLoadingRl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_station_list);
        ButterKnife.bind(this);
        initRightTvBar("选择服务站", null, null);
    }

    @OnClick({R.id.tv_province, R.id.tv_city, R.id.tv_district})
    void myClick(View v) {
        switch (v.getId()) {
            case R.id.tv_province:

                break;
            case R.id.tv_city:

                break;
            case R.id.tv_district:

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
