package com.jixing.kd.mall.view;

import android.content.Intent;
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

import com.android.volley.VolleyError;
import com.jixing.kd.R;
import com.jixing.kd.api.ApiManager;
import com.jixing.kd.base.BaseActivity;
import com.jixing.kd.base.CarApplication;
import com.jixing.kd.bean.User;
import com.jixing.kd.interfaces.ResponseCallbackHandler;
import com.jixing.kd.mall.IListenerTwo;
import com.jixing.kd.mall.adapter.ChooseAddressAdapter;
import com.jixing.kd.mall.entity.AddressEntity;
import com.jixing.kd.mall.view.custom_view.RecyItemSpace;
import com.jixing.kd.utils.MD5;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/10.
 * Used to : 选择收货地址页面
 */

public class ChooseAddressActivity extends BaseActivity implements IListenerTwo {

    private static final String TAG = "ChooseAddressActivity";

    @BindView(R.id.rl_choose_shop)
    RelativeLayout mSelectShopLayout;

    @BindView(R.id.tv_default_shop_name)
    TextView mDefaultShopNameTv;

    @BindView(R.id.recy_choose_address)
    RecyclerView mChooseAddressRv;

    @BindView(R.id.rl_loading)
    RelativeLayout mLoadingRl;

    private List<AddressEntity.ResultBean> mAddressList;

    private ChooseAddressAdapter mAdapter;

    private String categoryId;

    private CarApplication mApp;

    private User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mAddressList = new ArrayList<>();
        mApp = (CarApplication) getApplication();
        mUser = mApp.getUser();
        categoryId = getIntent().getStringExtra("category_id");
        Log.e(TAG, "initData: categoryId is " + categoryId);
        String sign = MD5.getSign(ApiManager.URL_MANAGE_ADDRESS, mUser);
        String time = MD5.gettimes();
        ApiManager.getAddressList(sign, time, new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: " + response);
                    Gson g = new Gson();
                    AddressEntity entity = g.fromJson(response, AddressEntity.class);
                    if (entity != null && entity.getErr_code().equals("0")) {
                        mAddressList = entity.getResult();
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
        mAdapter = new ChooseAddressAdapter(this, mAddressList);
        mChooseAddressRv.setAdapter(mAdapter);
        mAdapter.setListener(this);
    }

    private void initView() {
        setContentView(R.layout.activity_choose_address);
        ButterKnife.bind(this);
        mChooseAddressRv.setLayoutManager(new LinearLayoutManager(this));
        mChooseAddressRv.addItemDecoration(new RecyItemSpace(30));
        initRightTvBar("选择收货地址", "管理", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseAddressActivity.this, ManageAddressActivity.class);
                startActivity(intent);
            }
        });

        mSelectShopLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseAddressActivity.this, ShopListActivity.class);
                intent.putExtra("category_id", categoryId);
                startActivityForResult(intent, 102);
            }
        });
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
            if (requestCode == 101) {
                mLoadingRl.setVisibility(View.VISIBLE);
                initData();
            } else if (requestCode == 102) {
                Intent backIntent = new Intent();
                String shopId = data.getStringExtra("shopId");
                backIntent.putExtra("name", data.getStringExtra("name"));
                backIntent.putExtra("tel", data.getStringExtra("tel"));
                backIntent.putExtra("address", data.getStringExtra("address"));
                backIntent.putExtra("addressId", "");
                backIntent.putExtra("shopId", TextUtils.isEmpty(shopId) ? "" : shopId);
                backIntent.putExtra("flag", "1");
                setResult(RESULT_OK, backIntent);
                finish();
            }
        }
    }

    @Override
    public void listener(int pos, int doWhat) {
        AddressEntity.ResultBean bean = mAddressList.get(pos);
        switch (doWhat) {
            case ChooseAddressAdapter.DO_EDIT:
                Intent intent = new Intent(this, PostAddressActivity.class);
                intent.putExtra("address_entity", bean);
                startActivityForResult(intent, 101);
                break;
            case ChooseAddressAdapter.SET_SELECTED:
                if (bean.isSelected()) {
                    Toast.makeText(this, "选择已选的", Toast.LENGTH_SHORT).show();
                } else {
                    bean.setSelected(true);
                }
                mAdapter.notifyDataSetChanged();
                Intent backIntent = new Intent();
                backIntent.putExtra("name", bean.getName());
                backIntent.putExtra("tel", bean.getPhone());
                backIntent.putExtra("address", bean.getProvince() + bean.getCity() + bean.getDistrict() + bean.getAddress());
                backIntent.putExtra("addressId", bean.getId());
                backIntent.putExtra("shopId", "");
                backIntent.putExtra("flag", "0");
                setResult(RESULT_OK, backIntent);
                finish();
                break;
        }
    }
}
