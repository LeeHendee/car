package com.example.gtercn.car.mall.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.mall.adapter.MallBannerAdapter;
import com.example.gtercn.car.base.BaseFragment;
import com.example.gtercn.car.mall.adapter.SecKillAdapter;
import com.example.gtercn.car.mall.entity.BannerEntity;
import com.example.gtercn.car.mall.entity.ClassifyEntity;
import com.example.gtercn.car.mall.adapter.ClassifyAdapter;
import com.example.gtercn.car.mall.entity.SecKillEntity;
import com.example.gtercn.car.widget.DividerItemDecoration;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class StoreFragment extends BaseFragment {

    private static final String TAG = "StoreFragment";

    private ViewPager mBannerVp;

    private GridView mClassifyGv;

    private RecyclerView mSecKillRecLv;

    private ImageView mCartIv;

    private ImageView mTitleRightIv;

    private ImageView mSearchIv;

    private TextView mSearchContentTv;

    private View mView;

    private MallBannerAdapter mAdapter;

    private List<BannerEntity.ResultBean> mBannerList;

    private List<ClassifyEntity.ResultBean> mClassifyList;

    private ClassifyAdapter mClassifyAdapter;

    private SecKillAdapter mSecAdapter;

    private List<SecKillEntity.ResultBean> mSecList;

    //是否需要轮播标志
    private boolean isContinue = true;

    private Thread mThread;

    private RelativeLayout mLoadingRl;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 100) {
                mAdapter = new MallBannerAdapter(mBannerList, getActivity());
                mBannerVp.setAdapter(mAdapter);
                autoPlayView();
            } else if (message.what == 101) {
                mBannerVp.setCurrentItem(mBannerVp.getCurrentItem() + 1);
            } else if (message.what == 102) {
                mClassifyAdapter = new ClassifyAdapter(getActivity(), mClassifyList);
                mClassifyGv.setAdapter(mClassifyAdapter);
            } else {
                mSecAdapter = new SecKillAdapter(getActivity(), mSecList);
                mSecKillRecLv.setAdapter(mSecAdapter);
            }
            return true;
        }
    });

    public StoreFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_store_layout, container, false);
        Log.e(TAG, "onCreateView: ------------->>");
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        Log.e(TAG, "onViewCreated: ------------->>");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ------------->>");
    }

    @Override
    public void initView() {
        mCartIv = (ImageView) mView.findViewById(R.id.iv_home_cart);
        mSearchIv = (ImageView) mView.findViewById(R.id.iv_home_search);
        mTitleRightIv = (ImageView) mView.findViewById(R.id.iv_title_right);
        mBannerVp = (ViewPager) mView.findViewById(R.id.vp_banner);
        mClassifyGv = (GridView) mView.findViewById(R.id.gv_classify);
        mSecKillRecLv = (RecyclerView) mView.findViewById(R.id.rec_qiang);
        mSecKillRecLv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mSecKillRecLv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        mLoadingRl = (RelativeLayout) mView.findViewById(R.id.rl_loading);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ------------->>");
        isContinue = false;
        mThread = null;
    }

    private void initListener() {
        mCartIv.setOnClickListener(mListener);
        mSearchIv.setOnClickListener(mListener);
        mTitleRightIv.setOnClickListener(mListener);
        mClassifyGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String classifyId = mClassifyList.get((int) l).getId();
                Intent intent = new Intent(getActivity(), BrandActivity.class);
                intent.putExtra("classifyId", classifyId);
                startActivity(intent);
            }
        });
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_home_cart:
                    showToastMsg("购物车");
                    Intent intent = new Intent(getActivity(),ReviewPostActivity.class);
                    startActivity(intent);
                    break;
                case R.id.iv_title_right:
                    showToastMsg("右按键");
                    break;
                case R.id.iv_home_search:
                    showToastMsg("去搜索");
                    break;
            }
        }
    };

    @Override
    public void initData() {
        initBanner();
        initClassify();
        initSecKill();
    }

    private void autoPlayView() {
        isContinue = true;
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isContinue) {
                    try {
                        Thread.sleep(2000);
                        mHandler.sendEmptyMessage(101);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        if (!mThread.isAlive()) {
            mThread.start();
        }
    }

    private void initSecKill() {
        mLoadingRl.setVisibility(View.VISIBLE);
        ApiManager.getSeckill(new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "response is " + response);
                    Gson gson = new Gson();
                    SecKillEntity secEntity = gson.fromJson(response, SecKillEntity.class);
                    if (secEntity != null) {
                        if (TextUtils.equals(secEntity.getErr_code(), "0")) {
                            mSecList = secEntity.getResult();
                            mHandler.sendEmptyMessage(103);
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
        }, 3, TAG);
    }

    private void initBanner() {
        mLoadingRl.setVisibility(View.VISIBLE);
        ApiManager.getBanner(new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "response is " + response);
                    Gson gson = new Gson();
                    BannerEntity bannerEntity = gson.fromJson(response, BannerEntity.class);
                    if (bannerEntity != null) {
                        if (TextUtils.equals(bannerEntity.getErr_code(), "0")) {
                            mBannerList = bannerEntity.getResult();
                            mHandler.sendEmptyMessage(100);
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

    //设置分类gridView
    private void initClassify() {
        mClassifyList = new ArrayList<>();
        mLoadingRl.setVisibility(View.VISIBLE);
        ApiManager.getClassify(new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "response is " + response);
                    Gson gson = new Gson();
                    ClassifyEntity classifyEntity = gson.fromJson(response, ClassifyEntity.class);
                    if (classifyEntity != null) {
                        if (TextUtils.equals(classifyEntity.getErr_code(), "0")) {
                            mClassifyList = classifyEntity.getResult();
                            mHandler.sendEmptyMessage(102);
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
        }, 2, TAG);

    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }


}
