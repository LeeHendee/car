package com.example.gtercn.car.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.adapter.MallBannerAdapter;
import com.example.gtercn.car.base.BaseFragment;
import com.example.gtercn.car.bean.HomeAdBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


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

    private List<HomeAdBean> mBeans;

    private int index = 0, preIndex = 0;
    //是否需要轮播标志
    private boolean isContinue = true;

    private void autoPlayView() {
        //自动播放图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isContinue) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mBannerVp.setCurrentItem(mBannerVp.getCurrentItem() + 1);
                        }
                    });
                    SystemClock.sleep(2000);
                }
            }
        }).start();
    }


    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_store_layout, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        autoPlayView();
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
    public void initView() {
        mCartIv = (ImageView) mView.findViewById(R.id.iv_home_cart);
        mSearchIv = (ImageView) mView.findViewById(R.id.iv_home_search);
        mTitleRightIv = (ImageView) mView.findViewById(R.id.iv_title_right);
        mBannerVp = (ViewPager) mView.findViewById(R.id.vp_banner);
    }

    @Override
    public void initData() {
        mBeans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HomeAdBean b = new HomeAdBean();
            if (i == 0) {
                b.setRes(R.drawable.banner1);
            } else if (i == 1) {
                b.setRes(R.drawable.banner2);
            } else if (i == 2) {
                b.setRes(R.drawable.banner3);
            } else if (i == 3) {
                b.setRes(R.drawable.banner4);
            } else if (i == 4) {
                b.setRes(R.drawable.banner5);
            }
            mBeans.add(b);
        }
        mAdapter = new MallBannerAdapter(mBeans, getActivity());
        mBannerVp.setAdapter(mAdapter);
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }

}
