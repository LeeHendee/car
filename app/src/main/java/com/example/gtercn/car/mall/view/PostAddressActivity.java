package com.example.gtercn.car.mall.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.interfaces.ResponseJSONObjectListener;
import com.example.gtercn.car.interfaces.ResponseStringListener;
import com.example.gtercn.car.mall.entity.ResultEntity;
import com.google.gson.Gson;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yan on 2018/1/8.
 * 新增地址页面
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

    private boolean isDefault;

    private String mProvince;

    private String mCity;

    private String mDistrict;

    private String mPostCode;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_post_ad);
        ButterKnife.bind(this);
        initRightTvBar("新增地址", "保存", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PostAddressActivity.this, "保存地址", Toast.LENGTH_SHORT).show();
                submitNewAd();

            }
        });
    }

    private void submitNewAd() {
        String time = "1";
        String sign = "sign";
        String name = mNameEt.getText().toString().trim();
        String tel = mTelEt.getText().toString().trim();
        String detailAd = mDetailAdEt.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填收货人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(tel)) {
            Toast.makeText(this, "请填写联系电话", Toast.LENGTH_SHORT).show();
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
            params.put("postal_code", mPostCode);
            params.put("name", name);
            params.put("phone", mPostCode);
            params.put("default_flag", isDefault ? "Y" : "N");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiManager.submitNewAd(sign, time, params, new ResponseJSONObjectListener() {
            @Override
            public void onSuccessResponse(JSONObject response, int type) {
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: " + response.toString());
                    Gson g = new Gson();
                    ResultEntity entity = g.fromJson(response.toString(), ResultEntity.class);
                    if (entity != null && entity.getErr_code().equals("0")) {
                        Toast.makeText(PostAddressActivity.this, entity.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error, int type) {

            }
        }, 1, TAG);

    }

    @OnClick({
            R.id.iv_set_default,
            R.id.rl_pick_address
    })

    void myClick(View v){
        switch (v.getId()){
            case R.id.rl_pick_address:
                setCityPicker();
                break;
            case R.id.iv_set_default:
                if (isDefault){
                    isDefault = false;
                    mSetDefaultIv.setImageResource(R.drawable.checkbox_off);
                }else {
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
