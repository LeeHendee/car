package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.base.AppManager;
import com.example.gtercn.car.base.BaseActivity;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.mall.IListenerTwo;
import com.example.gtercn.car.mall.adapter.BrandAdapter;
import com.example.gtercn.car.mall.adapter.DialogExpertAdapter;
import com.example.gtercn.car.mall.entity.BrandListEntity;
import com.example.gtercn.car.mall.entity.ExpertListEntity;
import com.example.gtercn.car.mall.entity.SecKillEntity;
import com.example.gtercn.car.utils.Constants;
import com.example.gtercn.car.utils.ContactUtil;
import com.example.gtercn.car.widget.PopPhoneMenu;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Yan on 2017/12/24.
 */

public class BrandActivity extends BaseActivity {

    private static final String TAG = "BrandActivity";

    private SwipeRefreshLayout mSwipe;

    private RecyclerView mBrandRv;

    private RelativeLayout mLoadingRl;

    private List<BrandListEntity.ResultBean> mList;

    private BrandAdapter mAdapter;

    private String mBrandName;

    private RelativeLayout mEmptyRl;

    private FloatingActionButton mFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        //需要传入一个分类id
        Intent intent = getIntent();
        String classifyId = intent.getStringExtra("classifyId");
        mBrandName = intent.getStringExtra("classifyName");
        initRightIvBar(mBrandName, R.drawable.icon_search, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goSearch = new Intent(BrandActivity.this, SearchActivity.class);
                startActivity(goSearch);
            }
        });
        String cityCode = Constants.CITY_CODE;
        //获取达人列表数据
        getExpertList(Constants.CITY_CODE, "7");
        ApiManager.getBrandList(classifyId, cityCode, new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                if (mSwipe.isRefreshing()) {
                    mSwipe.setRefreshing(false);
                } else {
                    mLoadingRl.setVisibility(View.GONE);
                }
                if (response != null) {
                    Log.e(TAG, "onSuccessResponse: response is " + response);
                    Gson gson = new Gson();
                    BrandListEntity brandList = gson.fromJson(response, BrandListEntity.class);
                    if (brandList != null) {
                        if (TextUtils.equals(brandList.getErr_code(), "0")) {
                            mList = brandList.getResult();
                            if (mList != null && mList.size() > 0) {
                                mEmptyRl.setVisibility(View.GONE);
                                mAdapter = new BrandAdapter(BrandActivity.this, mList);
                                mBrandRv.setLayoutManager(new LinearLayoutManager(BrandActivity.this));
                                mBrandRv.setAdapter(mAdapter);
                            } else {
                                mEmptyRl.setVisibility(View.VISIBLE);
                            }
                        }
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
        }, 1, TAG);
    }

    private DialogExpertAdapter dialogAdapter;

    private void initView() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_brand, null);
        setContentView(view);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mBrandRv = (RecyclerView) findViewById(R.id.rec_brand);
        mLoadingRl = (RelativeLayout) findViewById(R.id.rl_loading);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        mSwipe.setColorSchemeResources(R.color.blue1);
        mEmptyRl = (RelativeLayout) findViewById(R.id.empty_rl);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (expertList == null) {
                    Toast.makeText(BrandActivity.this, "获取达人失败", Toast.LENGTH_SHORT).show();
                    return;
                }
                //自定义对话框
                final AlertDialog.Builder builder = new AlertDialog.Builder(BrandActivity.this);
                View dialogView = LayoutInflater.from(BrandActivity.this).inflate(R.layout.dialog_expert, null);
                ListView lv = (ListView) dialogView.findViewById(R.id.dialog_lv);

                dialogAdapter = new DialogExpertAdapter(BrandActivity.this, expertList);
                lv.setAdapter(dialogAdapter);
                builder.setView(dialogView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                dialogAdapter.setListener(new IListenerTwo() {
                    @Override
                    public void listener(int pos, int doWhat) {
                        if (doWhat == dialogAdapter.WECHAT) {
                            ContactUtil.getWeChatNumber(expertList.get(pos), BrandActivity.this);
                            dialog.dismiss();
                        } else {

                            String telNumber = expertList.get(pos).getExpert_tel_number();
                            List<String> telList = null;
                            if (telNumber != null) {
                                String[] sourceStrArray = telNumber.split(",");
                                telList = Arrays.asList(sourceStrArray);

                            }
                            PopPhoneMenu mPopMenu = new PopPhoneMenu(BrandActivity.this, telList);

                            if (telNumber == null) {
                                Toast.makeText(BrandActivity.this, "此达人没有留下电话号码!", Toast.LENGTH_SHORT).show();
                            } else {
                                dialog.dismiss();
                                mPopMenu.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,
                                        0, 0);
                            }
                        }
                    }
                });
            }
        });
    }

    private List<ExpertListEntity.ResultBean> expertList;

    private void getExpertList(String cityCode, String categoryId) {
        OkHttpUtils
                .get()
                .url(ApiManager.URL_GET_EXPERT_LIST)
                .addParams("city_code", cityCode)
                .addParams("category_id", categoryId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: response is " + response);
                        if (response != null) {
                            Gson gson = new Gson();

                            ExpertListEntity entity = gson.fromJson(response, ExpertListEntity.class);
                            if (entity != null && "0".equals(entity.getErr_code())) {
                                expertList = entity.getResult();
                            }
                        }
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
