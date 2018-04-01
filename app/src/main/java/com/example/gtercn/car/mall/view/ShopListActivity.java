package com.example.gtercn.car.mall.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.mall.adapter.ShopListAdapter;
import com.example.gtercn.car.mall.entity.ShopListEntity;
import com.example.gtercn.car.utils.Constants;
import com.example.gtercn.car.utils.RecyclerViewDivider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.security.auth.login.LoginException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by LIHANG on 2018/3/19.
 */

public class ShopListActivity extends BaseActivity {

    private static final String TAG = "ShopListActivity";

    @BindView(R.id.tv_province)
    TextView mProvinceTv;

    @BindView(R.id.tv_city)
    TextView mCityTv;

    @BindView(R.id.tv_district)
    TextView mDistrictTv;

    @BindView(R.id.rv_shop)
    RecyclerView mShopRv;

    private List<ShopListEntity.ResultBean> shopList;

    private ShopListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initView() {
        super.initView();
        initRightTvBar("服务站列表", null, null);
        mShopRv.setLayoutManager(new LinearLayoutManager(this));
        mShopRv.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));

    }

    @Override
    protected void initData() {
        super.initData();
        getShopList();
    }

    private void getShopList() {
        String categoryId = "1";
        JSONObject object = new JSONObject();
        try {
            object.put("category_id", categoryId);
            object.put("city_code", Constants.CITY_CODE);
            object.put("province", "");
            object.put("city", "");
            object.put("district", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils
                .postString()
                .url(ApiManager.URL_GET_SHOP_LIST)
                .mediaType(MediaType.parse("application/json;charset=utf8"))
                .content(object.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                        Toast.makeText(ShopListActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: response is " + response);
                        if (response != null) {
                            Gson gson = new Gson();
                            ShopListEntity entity = gson.fromJson(response, ShopListEntity.class);
                            if (entity != null && "0".equals(entity.getErr_code())) {
                                shopList = entity.getResult();
                                if (shopList != null && shopList.size() > 0) {
                                    mAdapter = new ShopListAdapter(ShopListActivity.this, shopList);
                                    mShopRv.setAdapter(mAdapter);
                                } else {
                                    //空数据页面
                                }
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.tv_province, R.id.tv_city, R.id.tv_district})
    void myClick(View view) {
        switch (view.getId()) {
            case R.id.tv_province:

                break;
            case R.id.tv_city:

                break;
            case R.id.tv_district:

                break;
        }
    }
}
