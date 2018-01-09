package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.mall.adapter.AddressAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/9.
 * Used to : 地址管理
 */

public class ManageAddressActivity extends BaseActivity {

    private static final String TAG = "ManageAddressActivity";

    @BindView(R.id.recy_address)
    RecyclerView mAddressRv;

    @BindView(R.id.btn_add_ad)
    Button mAddNewAdbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        String sign = "sign";
        String time = "time";
//        ApiManager.delCartItem();
    }

    private void initView() {
        setContentView(R.layout.activity_manage_address);
        ButterKnife.bind(this);
        mAddressRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick({
            R.id.btn_add_ad
    })
    void myClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_ad:
                Intent intent = new Intent(this, PostAddressActivity.class);
                startActivity(intent);
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
