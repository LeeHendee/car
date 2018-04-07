package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.mall.entity.CommitOrderResultEntity;
import com.example.gtercn.car.mall.entity.ConfirmOrderEntity;
import com.example.gtercn.car.mall.entity.CreatePreOrderEntity;
import com.example.gtercn.car.mall.entity.DefaultEntity;
import com.example.gtercn.car.mall.entity.PreOrderEntity;
import com.example.gtercn.car.mall.entity.ProductDetailEntity;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;


/**
 * Author ：LeeHang
 * CreateTime ：2018/1/10.
 * Used to : 确认订单
 */

public class OrderConfirmActivity extends BaseActivity {

    private static final String TAG = "OrderConfirmActivity";

    @BindView(R.id.rl_address)
    RelativeLayout mAddressRl;

    @BindView(R.id.rl_loading)
    RelativeLayout mLoadingRl;

    @BindView(R.id.tv_name)
    TextView mNameTv;

    @BindView(R.id.tv_tel)
    TextView mTelTv;

    @BindView(R.id.tv_address)
    TextView mAddressTv;

    @BindView(R.id.tv_total_org)
    TextView mOrgTotalTv;

    @BindView(R.id.tv_delivery_fee)
    TextView mDeliveryFeeTv;

    @BindView(R.id.tv_total_pay)
    TextView mTotalPayTv;

    @BindView(R.id.tv_submit_order)
    TextView mSubmitOrderTv;

    @BindView(R.id.ll_product_view)
    LinearLayout mProductLayout;

    @BindView(R.id.iv_invoice)
    ImageView mIsInvoiceIv;

    @BindView(R.id.iv_personal)
    ImageView mPersonalIv;

    @BindView(R.id.iv_company)
    ImageView mCompanyIv;

    @BindView(R.id.et_company_name)
    EditText mCompanyNameEt;

    @BindView(R.id.et_company_tex_number)
    EditText mTexNoEt;

    @BindView(R.id.et_invoice_content)
    EditText mContentEt;

    @BindView(R.id.ll_invoice)
    LinearLayout mShowInvoiceLl;

    @BindView(R.id.ll_invoice_company)
    LinearLayout mCompanyInvoiceLl;

    private CreatePreOrderEntity params;

    private ConfirmOrderEntity mEntity;

    private List<ConfirmOrderEntity.ResultBean.GoodsListBean> list;

    private String mAddressId;

    private boolean isNeedInvoice = false;

    private boolean isPersonalInvoice = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        params = (CreatePreOrderEntity) intent.getSerializableExtra("params");
        String sign = "sign";
        String time = "time";
        String url = ApiManager.URL_CREATE_PRE_ORDER + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + time;
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json;charset=utf8"))
                .content(new Gson().toJson(params))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mLoadingRl.setVisibility(View.GONE);
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mLoadingRl.setVisibility(View.GONE);
                        if (response != null) {
                            Log.e(TAG, "onResponse: response is " + response);
                            mEntity = new Gson().fromJson(response, ConfirmOrderEntity.class);
                            if (mEntity != null) {
                                list = mEntity.getResult().getGoods_list();
                                setUi();
                                setAddressUi();
                            }
                        }
                    }
                });
    }

    private void setUi() {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ConfirmOrderEntity.ResultBean.GoodsListBean bean = list.get(i);
                View view = LayoutInflater.from(this).inflate(R.layout.item_single_product, null);
                ImageView itemIv = (ImageView) view.findViewById(R.id.iv_item);
                ImageView lineIv = (ImageView) view.findViewById(R.id.iv_line);
                TextView titleTv = (TextView) view.findViewById(R.id.tv_title);
                TextView priceTv = (TextView) view.findViewById(R.id.tv_price);
                TextView countTv = (TextView) view.findViewById(R.id.tv_count);
                Picasso.with(this).load(bean.getPicture()).into(itemIv);
                titleTv.setText(bean.getGoods_title());
                priceTv.setText(getResources().getString(R.string.rmb) + bean.getPromotion_price());
                countTv.setText("x" + bean.getNumber());
                if (i == list.size() - 1) {
                    lineIv.setVisibility(View.GONE);
                }
                mProductLayout.addView(view);
            }
            mOrgTotalTv.setText(getResources().getString(R.string.rmb) + mEntity.getResult().getTotal_price());
            mDeliveryFeeTv.setText(getResources().getString(R.string.rmb) + 0);
            mTotalPayTv.setText("实付款:  " + getResources().getString(R.string.rmb) + mEntity.getResult().getTotal_price());
        }
    }

    private void setAddressUi() {
        mNameTv.setText(mEntity.getResult().getName());
        mTelTv.setText(mEntity.getResult().getPhone());
        mAddressTv.setText(mEntity.getResult().getAddress());
        mAddressId = mEntity.getResult().getAddres_id();
    }

    private void initView() {
        setContentView(R.layout.activity_order_confirm);
        ButterKnife.bind(this);
        initRightTvBar("确认订单", null, null);
    }

    @OnClick({R.id.rl_address, R.id.tv_submit_order, R.id.iv_invoice, R.id.iv_personal, R.id.iv_company})
    void myClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit_order:
                orderConfirm();
                break;
            case R.id.rl_address:
                Intent intent = new Intent(OrderConfirmActivity.this, ChooseAddressActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.iv_invoice:
                if (isNeedInvoice) {
                    mIsInvoiceIv.setImageResource(R.drawable.switch_off);
                    isNeedInvoice = false;
                    mShowInvoiceLl.setVisibility(View.GONE);
                } else {
                    mIsInvoiceIv.setImageResource(R.drawable.switch_on);
                    isNeedInvoice = true;
                    mShowInvoiceLl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_personal:
                isPersonalInvoice = true;
                mPersonalIv.setImageResource(R.drawable.cart1_checkbox_check);
                mCompanyIv.setImageResource(R.drawable.cart1_checkbox_normal);
                mCompanyInvoiceLl.setVisibility(View.GONE);
                break;
            case R.id.iv_company:
                isPersonalInvoice = false;
                mPersonalIv.setImageResource(R.drawable.cart1_checkbox_normal);
                mCompanyIv.setImageResource(R.drawable.cart1_checkbox_check);
                mCompanyInvoiceLl.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                mNameTv.setText(data.getStringExtra("name"));
                mTelTv.setText(data.getStringExtra("tel"));
                mAddressTv.setText(data.getStringExtra("address"));
                mAddressId = data.getStringExtra("addressId");
            }
        }
    }

    private void orderConfirm() {
        PreOrderEntity entity = new PreOrderEntity();
        if (list == null)
            return;
        List<PreOrderEntity.GoodsAttrListBean> goodsList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            PreOrderEntity.GoodsAttrListBean bean = new PreOrderEntity.GoodsAttrListBean();
            bean.setGoods_id(list.get(i).getGoods_id());
            bean.setNumber(list.get(i).getNumber());
            bean.setSpec_item_ids(list.get(i).getSpec_item_ids());
            goodsList.add(bean);
        }

        entity.setGoods_attr_list(goodsList);
        //总价格：
        entity.setTotal_price(mEntity.getResult().getTotal_price() + "");
        entity.setAddress_id(mAddressId);
        entity.setCustomer_mark("无备注信息");
        String invoiceTitle = null;
        if (isPersonalInvoice) {
            invoiceTitle = mNameTv.getText().toString();
        } else {
            invoiceTitle = mCompanyNameEt.getText().toString().trim();
        }
        entity.setInvoice_title(invoiceTitle);
        entity.setInvoice(isNeedInvoice ? "Y" : "N");
        entity.setItem_count(list.size() + "");
        entity.setExpert_id("1");
        entity.setInvoice_type(isPersonalInvoice ? "E" : "P");
        entity.setInvoice_content(mContentEt.getText().toString().trim());
        String sign = "sign";
        String time = "time";
        Log.e(TAG, "initData: entity is " + entity.toString());
        String url = ApiManager.URL_PRE_ORDER + "?token=" + Constants.TOKEN + "&sign=" + sign + "&t=" + time;
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json;charset=utf8"))
                .content(new Gson().toJson(entity))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                        mLoadingRl.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mLoadingRl.setVisibility(View.GONE);
                        if (response != null) {
                            Log.e(TAG, "onResponse: response is " + response);
                            Gson gson = new Gson();
                            CommitOrderResultEntity entity = gson.fromJson(response, CommitOrderResultEntity.class);
                            if (entity != null && entity.getErr_code().equals("0")) {
                                Intent intent = new Intent(OrderConfirmActivity.this, ChoosePayActivity.class);
                                intent.putExtra("orderId", entity.getResult().getOrderId());
                                intent.putExtra("sum", mEntity.getResult().getTotal_price());
                                startActivity(intent);
                            }
                        }
                    }
                });
    }

    private void computeTotal() {
        if (list != null && list.size() > 0) {
            double total = 0;
            double price = 0;
            double totalSingle = 0;
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                ConfirmOrderEntity.ResultBean.GoodsListBean bean = list.get(i);
                count = Integer.valueOf(bean.getNumber());
                price = Double.valueOf(bean.getPromotion_price());
                totalSingle = price * count;
                total += totalSingle;
            }
            Log.e(TAG, "computeTotal: total is " + total);
        }
    }


    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }
}
