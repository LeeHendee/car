package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.interfaces.ResponseStringListener;
import com.example.gtercn.car.mall.IListenerTwo;
import com.example.gtercn.car.mall.adapter.AddressAdapter;
import com.example.gtercn.car.mall.entity.AddressEntity;
import com.example.gtercn.car.mall.entity.ResultEntity;
import com.example.gtercn.car.mall.view.custom_view.RecyItemSpace;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/9.
 * Used to : 地址管理
 */

public class ManageAddressActivity extends BaseActivity implements IListenerTwo {

    private static final String TAG = "ManageAddressActivity";

    @BindView(R.id.recy_address)
    RecyclerView mAddressRv;

    @BindView(R.id.btn_add_ad)
    Button mAddNewAdBtn;

    @BindView(R.id.rl_loading)
    RelativeLayout mLoadingRl;

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
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: " + response);
                    Gson g = new Gson();
                    AddressEntity entity = g.fromJson(response, AddressEntity.class);
                    if (entity != null && entity.getErr_code().equals("0")) {
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
                mLoadingRl.setVisibility(View.GONE);
            }
        }, 1, TAG);
    }

    private void setUI() {
        mAdapter = new AddressAdapter(this, addressList);
        mAddressRv.setAdapter(mAdapter);
        mAdapter.setListener(this);
    }

    private void initView() {
        setContentView(R.layout.activity_manage_address);
        ButterKnife.bind(this);
        mAddressRv.setLayoutManager(new LinearLayoutManager(this));
        mAddressRv.addItemDecoration(new RecyItemSpace(30));
        initRightTvBar("地址管理", null, null);
    }

    @OnClick({
            R.id.btn_add_ad
    })
    void myClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_ad:
                Intent intent = new Intent(this, PostAddressActivity.class);
                startActivityForResult(intent, 100);
                break;
        }
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mLoadingRl.setVisibility(View.VISIBLE);
            initData();
        }
    }

    @Override
    public void listener(int pos, int doWhat) {
        AddressEntity.ResultBean bean = addressList.get(pos);
        switch (doWhat) {
            case AddressAdapter.SET_DEFAULT:
                if (bean.getDefault_flag().equals("N")) {
                    setDefault(pos);
                }
                break;
            case AddressAdapter.DO_DEL:
                doDel(pos);
                break;
            case AddressAdapter.DO_EDIT:
                Intent intent = new Intent(this, PostAddressActivity.class);
                intent.putExtra("address_entity", bean);
                startActivityForResult(intent, 101);
                break;
        }
    }

    private void doDel(int pos) {
        String sign = "sign";
        String time = "time";
        String addressId = addressList.get(pos).getId();
        Map<String, String> params = new HashMap<>();
        params.put("address_id", addressId);
        mLoadingRl.setVisibility(View.VISIBLE);
        ApiManager.delAddress(sign, time, params, new ResponseStringListener() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: " + response);
                    Gson g = new Gson();
                    ResultEntity entity = g.fromJson(response, ResultEntity.class);
                    if (entity != null & entity.getErr_code().equals("0")) {
                        Toast.makeText(ManageAddressActivity.this, entity.getMessage(), Toast.LENGTH_SHORT).show();
                        initData();
                    }

                }
            }

            @Override
            public void onErrorResponse(VolleyError error, int type) {
                mLoadingRl.setVisibility(View.GONE);
            }
        }, 2, TAG);
    }

    private void setDefault(int pos) {
        String sign = "sign";
        String time = "time";
        String addressId = addressList.get(pos).getId();
        Map<String, String> params = new HashMap<>();
        params.put("address_id", addressId);
        mLoadingRl.setVisibility(View.VISIBLE);
        ApiManager.setDefaultAddress(sign, time, params, new ResponseStringListener() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: " + response);
                    Gson g = new Gson();
                    ResultEntity entity = g.fromJson(response, ResultEntity.class);
                    if (entity != null & entity.getErr_code().equals("0")) {
                        Toast.makeText(ManageAddressActivity.this, entity.getMessage(), Toast.LENGTH_SHORT).show();
                        initData();
                    }

                }
            }

            @Override
            public void onErrorResponse(VolleyError error, int type) {
                mLoadingRl.setVisibility(View.GONE);
            }
        }, 2, TAG);
    }
}
