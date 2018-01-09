package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.mall.adapter.AddressAdapter;
import com.example.gtercn.car.mall.entity.AddressEntity;
import com.example.gtercn.car.mall.view.custom_view.RecyItemSpace;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    private List<AddressEntity.ResultBean> addressList;

    private AddressAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        addressList = new ArrayList<>();
        String sign = "sign";
        String time = "time";
        ApiManager.getAddressList(sign, time, new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                if (response!=null){
                    Log.e(TAG, "onSuccessResponse: "+response );
                    Gson g = new Gson();
                    AddressEntity entity = g.fromJson(response,AddressEntity.class);
                    if (entity!=null&&entity.getErr_code().equals("0")){
                        addressList = entity.getResult();
                        setUI();
                    }
                }
            }

            @Override
            public void onSuccessResponse(JSONObject response, int type) {

            }

            @Override
            public void onSuccessResponse(JSONArray response, int type) {

            }

            @Override
            public void onErrorResponse(VolleyError error, int type) {

            }
        },1,TAG);
    }

    private void setUI() {
        mAdapter = new AddressAdapter(this,addressList);
        mAddressRv.setAdapter(mAdapter);
    }

    private void initView() {
        setContentView(R.layout.activity_manage_address);
        ButterKnife.bind(this);
        mAddressRv.setLayoutManager(new LinearLayoutManager(this));
        mAddressRv.addItemDecoration(new RecyItemSpace(2));
        initRightTvBar("地址管理",null,null);
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
