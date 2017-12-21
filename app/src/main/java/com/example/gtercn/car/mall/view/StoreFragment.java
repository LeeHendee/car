package com.example.gtercn.car.mall.view;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.interfaces.ResponseCallbackHandler;
import com.example.gtercn.car.mall.adapter.MallBannerAdapter;
import com.example.gtercn.car.base.BaseFragment;
import com.example.gtercn.car.bean.HomeAdBean;
import com.example.gtercn.car.mall.entity.BannerEntity;
import com.example.gtercn.car.mall.entity.ClassifyEntity;
import com.example.gtercn.car.mall.adapter.ClassifyAdapter;
import com.example.gtercn.car.net.THttpOpenHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class StoreFragment extends BaseFragment {

    private static final String TAG = "StoreFragment";

    private ViewPager mBannerVp;

    private GridView mClassifyGv;

    private GridView mSecKillGv;

    private ImageView mCartIv;

    private ImageView mTitleRightIv;

    private ImageView mSearchIv;

    private TextView mSearchContentTv;

    private View mView;

    private MallBannerAdapter mAdapter;

//    private List<HomeAdBean> mBeans;

    private List<BannerEntity.ResultBean> mBeans;

    private ClassifyAdapter mClassifyAdapter;

    private List<ClassifyEntity> mClassifyList;

    //是否需要轮播标志
    private boolean isContinue = true;

    private Thread mThread;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 100) {
                mAdapter = new MallBannerAdapter(mBeans, getActivity());
                mBannerVp.setAdapter(mAdapter);
                autoPlayView();
            } else if (message.what == 101) {
                mBannerVp.setCurrentItem(mBannerVp.getCurrentItem() + 1);
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
        initClassify();

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
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_home_cart:
                    showToastMsg("购物车");
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
        mBeans = new ArrayList<>();
        initBanner();

    }

    private void initBanner() {
        ApiManager.getBanner(new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                if (response != null) {
                    Gson gson = new Gson();
                    BannerEntity bannerEntity = gson.fromJson(response.toString(), BannerEntity.class);
                    if (bannerEntity != null) {
                        if (TextUtils.equals(bannerEntity.getErr_code(), "0")) {
                            mBeans = bannerEntity.getResult();
                            mHandler.sendEmptyMessage(100);
                        }
                    }


                }
                Log.e(TAG, "response is " + response.toString());

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
        for (int i = 0; i < 10; i++) {
            ClassifyEntity entity = new ClassifyEntity();
            entity.setName("分类" + (i + 1));
            entity.setRes(R.drawable.ic_launcher);
            mClassifyList.add(entity);
        }
        mClassifyAdapter = new ClassifyAdapter(getActivity(), mClassifyList);
        mClassifyGv.setAdapter(mClassifyAdapter);
        mClassifyGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), mClassifyList.get(position).getName(), Toast.LENGTH_SHORT).show();
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
