package com.example.gtercn.car.mall.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.base.BaseActivity;
import com.lljjcoder.citypickerview.widget.CityPicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yan on 2018/1/8.
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
            }
        });
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
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                mAddressTv.setText("所在地区："+province.trim() + "-" + city.trim() + "-" + district.trim());
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
