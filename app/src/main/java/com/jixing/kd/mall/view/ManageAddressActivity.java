package com.jixing.kd.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.jixing.kd.R;
import com.jixing.kd.api.ApiManager;
import com.jixing.kd.base.BaseActivity;
import com.jixing.kd.base.CarApplication;
import com.jixing.kd.bean.User;
import com.jixing.kd.interfaces.ResponseCallbackHandler;
import com.jixing.kd.interfaces.ResponseStringListener;
import com.jixing.kd.mall.IListenerTwo;
import com.jixing.kd.mall.adapter.AddressAdapter;
import com.jixing.kd.mall.entity.AddressEntity;
import com.jixing.kd.mall.entity.PostAddressEntity;
import com.jixing.kd.mall.entity.ResultEntity;
import com.jixing.kd.mall.view.custom_view.RecyItemSpace;
import com.jixing.kd.utils.MD5;
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

    @BindView(R.id.iv_title_left)
    ImageView backIv;

    private List<AddressEntity.ResultBean> addressList;

    private AddressAdapter mAdapter;
    private CarApplication mApp;
    private User mUser;

    private boolean needInit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {
        addressList = new ArrayList<>();
        mApp = (CarApplication) getApplication();
        mUser = mApp.getUser();
        String sign = MD5.getSign(ApiManager.URL_MANAGE_ADDRESS, mUser);
        String time = MD5.gettimes();
        needInit = getIntent().getBooleanExtra("needInit", false);
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
            case R.id.iv_title_left:
                if (needInit) {
                    setResult(101);
                }
                finish();
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
        String sign = MD5.getSign(ApiManager.URL_DEL_ADDRESS, mUser);
        String time = MD5.gettimes();
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
        String sign = MD5.getSign(ApiManager.URL_SET_DEFAULT_ADDRESS, mUser);
        String time = MD5.gettimes();
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
                    PostAddressEntity entity = g.fromJson(response, PostAddressEntity.class);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (needInit) {
                setResult(101);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
