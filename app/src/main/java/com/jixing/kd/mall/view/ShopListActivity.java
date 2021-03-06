package com.jixing.kd.mall.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.jixing.kd.R;
import com.jixing.kd.api.ApiManager;
import com.jixing.kd.mall.IListener;
import com.jixing.kd.mall.adapter.ShopListAdapter;
import com.jixing.kd.mall.entity.ShopListEntity;
import com.jixing.kd.utils.Constants;
import com.jixing.kd.utils.RecyclerViewDivider;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by LIHANG on 2018/3/19.
 */

public class ShopListActivity extends BaseActivity {

    private static final String TAG = "ShopListActivity";

    @BindView(R.id.rv_shop)
    RecyclerView mShopRv;

    private List<ShopListEntity.ResultBean> shopList;

    private ShopListAdapter mAdapter;

    private String categoryId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initView() {
        super.initView();
        initRightTvBar("服务站列表", null, null);
        categoryId = getIntent().getStringExtra("category_id");
        mShopRv.setLayoutManager(new LinearLayoutManager(this));
        mShopRv.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    protected void initData() {
        super.initData();
        getShopList();

    }

    private void itemListener() {
        mAdapter.setListener(new IListener() {
            @Override
            public void itemClickListener(int pos) {
                ShopListEntity.ResultBean bean = shopList.get(pos);
                String tel = getTel(bean);
                Intent intent = new Intent();
                intent.putExtra("name", bean.getShop_name());
                intent.putExtra("tel", tel);
                intent.putExtra("address", bean.getDetail_address());
                intent.putExtra("shopId", bean.getId());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private String getTel(ShopListEntity.ResultBean bean) {
        String telLong = bean.getTel_number_list();
        String tel = null;
        if (!TextUtils.isEmpty(telLong)) {
            if (telLong.contains(",")) {
                int index = telLong.indexOf(",");
                if (index > 0)
                    tel = telLong.substring(0, index);
            } else {
                return telLong;
            }
        }
        return tel;
    }

    private void getShopList() {
        Log.e(TAG, "getShopList: categoryId is " + categoryId);
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
                                    itemListener();
                                } else {
                                    //空数据页面
                                    showToast("暂无数据");
                                }
                            }
                        }
                    }
                });
    }

//    @OnClick({R.id.tv_province, R.id.tv_city, R.id.tv_district})
//    void myClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_province:
//
//                break;
//            case R.id.tv_city:
//
//                break;
//            case R.id.tv_district:
//
//                break;
//        }
//    }
}
