package com.example.gtercn.car.mall.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/12.
 * Used to :
 */

public class BaseTestActivity extends BaseActivity {

    @BindView(R.id.tv_province)
    TextView mProvinceTv;

    @BindView(R.id.rl_loading)
    RelativeLayout mLoadingRl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected void initView() {
        super.initView();
        mLoadingRl.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_station_list;
    }


    @OnClick({R.id.tv_province, R.id.tv_city, R.id.tv_district})
    void myClick(View v) {
        switch (v.getId()) {
            case R.id.tv_province:
                Toast.makeText(this, "点击省", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
