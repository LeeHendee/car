package com.jixing.kd.mall.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.jixing.kd.R;
import com.jixing.kd.api.ApiManager;
import com.jixing.kd.base.BaseActivity;
import com.jixing.kd.base.CarApplication;
import com.jixing.kd.bean.User;
import com.jixing.kd.interfaces.ResponseJSONObjectListener;
import com.jixing.kd.mall.entity.AddressEntity;
import com.jixing.kd.mall.entity.PostAddressEntity;
import com.jixing.kd.utils.MD5;
import com.google.gson.Gson;
import com.jixing.kd.utils.TRegularExpression;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yan on 2018/1/8.
 * 新增/编辑 地址页面
 */

public class PostAddressActivity extends BaseActivity {

    private static final String TAG = "PostAddressActivity";

    @BindView(R.id.et_name)
    EditText mNameEt;

    @BindView(R.id.et_tel)
    EditText mTelEt;

    @BindView(R.id.et_detail_ad)
    EditText mDetailAdEt;

    @BindView(R.id.rl_pick_address)
    RelativeLayout mPickAddressRl;

    @BindView(R.id.iv_set_default)
    ImageView mSetDefaultIv;

    @BindView(R.id.tv_address)
    TextView mAddressTv;

    @BindView(R.id.ll_set_default)
    LinearLayout mSetDefaultLayout;

    @BindView(R.id.rl_loading)
    RelativeLayout mLoadingRl;

    private boolean isDefault;

    private String mProvince;

    private String mCity;

    private String mDistrict;

    private String mPostCode;

    private AddressEntity.ResultBean addressBean;

    private boolean isEditor;
    private CarApplication mApp;
    private User mUser;
    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mLoadingRl.setVisibility(View.GONE);
        Intent intent = getIntent();
        mApp = (CarApplication) getApplication();
        addressBean = (AddressEntity.ResultBean) intent.getSerializableExtra("address_entity");
        if (addressBean != null) {
            setUI();
        }
    }

    private void setUI() {
        isEditor = true;
        mProvince = addressBean.getProvince();
        mCity = addressBean.getCity();
        mDistrict = addressBean.getDistrict();
        mPostCode = addressBean.getPostal_code();
        mNameEt.setText(addressBean.getName());
        mNameEt.setSelection(addressBean.getName().length());
        mTelEt.setText(addressBean.getPhone());
        mDetailAdEt.setText(addressBean.getAddress());
        mAddressTv.setText("所在地区：" + addressBean.getProvince() + "-" + addressBean.getCity() + "-" + addressBean.getDistrict());
    }

    private void initView() {
        mView = LayoutInflater.from(this).inflate(R.layout.activity_post_ad, null);
        setContentView(mView);
        ButterKnife.bind(this);
        initRightTvBar("新增地址", "保存", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInfo();
            }
        });
    }

    private void updateInfo(String time, JSONObject params) {
        mLoadingRl.setVisibility(View.VISIBLE);
        String sign = MD5.getSign(ApiManager.URL_EDIT_ADDRESS, mUser);
        ApiManager.editAddress(sign, time, params, new ResponseJSONObjectListener() {
            @Override
            public void onSuccessResponse(JSONObject response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: " + response.toString());
                    Gson g = new Gson();
                    PostAddressEntity entity = g.fromJson(response.toString(), PostAddressEntity.class);
                    if (entity != null && entity.getErr_code().equals("0")) {
                        Toast.makeText(PostAddressActivity.this, entity.getMessage(), Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error, int type) {
                mLoadingRl.setVisibility(View.GONE);
            }
        }, 2, TAG);
    }

    private void submitNewAd(String time, JSONObject params) {
        mLoadingRl.setVisibility(View.VISIBLE);
        String sign = MD5.getSign(ApiManager.URL_SUBMIT_NEW_AD, mUser);
        ApiManager.submitNewAd(sign, time, params, new ResponseJSONObjectListener() {
            @Override
            public void onSuccessResponse(JSONObject response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: " + response.toString());
                    Gson g = new Gson();
                    PostAddressEntity entity = g.fromJson(response.toString(), PostAddressEntity.class);
                    if (entity != null && entity.getErr_code().equals("0")) {
                        Toast.makeText(PostAddressActivity.this, entity.getMessage(), Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error, int type) {
                mLoadingRl.setVisibility(View.GONE);
            }
        }, 1, TAG);

    }

    private void checkInfo() {
        mUser = mApp.getUser();
        String time = MD5.gettimes();
        String name = mNameEt.getText().toString().trim();
        String tel = mTelEt.getText().toString().trim();
        String detailAd = mDetailAdEt.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填收货人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(tel) && !TRegularExpression.isMobileNO(tel)) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(detailAd)) {
            Toast.makeText(this, "请填写详细地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mProvince)) {
            Toast.makeText(this, "请选择所在省份", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mCity)) {
            Toast.makeText(this, "请选择所在城市", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mDistrict)) {
            Toast.makeText(this, "请选择所在区域", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject params = new JSONObject();
        try {
            params.put("province", mProvince);
            params.put("city", mCity);
            params.put("district", mDistrict);
            params.put("address", detailAd);
            params.put("postal_code", mPostCode == null ? "" : mPostCode);
            params.put("name", name);
            params.put("phone", tel);
            params.put("default_flag", isDefault ? "Y" : "N");
            if (isEditor) {
                params.put("address_id", addressBean.getId());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!isEditor) {
            submitNewAd(time, params);
        } else {
            updateInfo(time, params);
        }

    }

    @OnClick({
            R.id.iv_set_default,
            R.id.rl_pick_address
    })
    void myClick(View v) {
        switch (v.getId()) {
            case R.id.rl_pick_address:
                setCityPicker();
                hideKeyboard(mView);
                break;
            case R.id.iv_set_default:
                if (isDefault) {
                    isDefault = false;
                    mSetDefaultIv.setImageResource(R.drawable.checkbox_off);
                } else {
                    isDefault = true;
                    mSetDefaultIv.setImageResource(R.drawable.checkbox_on);
                }
                break;
        }
    }

    private void setCityPicker() {
        CityPicker.Builder pickerBuilder = new CityPicker.Builder(this);
        pickerBuilder.textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("辽宁省")
                .city("大连市")
                .district("沙河口区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false);
        CityPicker picker = pickerBuilder.build();
        picker.show();

        //监听方法，获取选择结果
        picker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                mProvince = citySelected[0];
                //城市
                mCity = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                mDistrict = citySelected[2];
                //邮编
                mPostCode = citySelected[3];
                //为TextView赋值
                mAddressTv.setText("所在地区：" + mProvince.trim() + "-" + mCity.trim() + "-" + mDistrict.trim());
            }
        });
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
