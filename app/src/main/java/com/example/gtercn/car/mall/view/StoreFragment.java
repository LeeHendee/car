package com.example.gtercn.car.mall.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
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
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
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
import com.example.gtercn.car.utils.Constants;
import com.example.gtercn.car.widget.DividerItemDecoration;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class StoreFragment extends BaseFragment {

    private static final String TAG = "StoreFragment";

    //    private ViewPager mBannerVp;
    private ConvenientBanner mBannerVp;

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

    private List<Integer> piclist;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 100) {
                mAdapter = new MallBannerAdapter(mBannerList, getActivity());
//                mBannerVp.setAdapter(mAdapter);
//                autoPlayView();
            } else if (message.what == 101) {
//                mBannerVp.setCurrentItem(mBannerVp.getCurrentItem() + 1);
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
        piclist = new ArrayList<>();
        piclist.add(R.drawable.banner1);
        piclist.add(R.drawable.banner2);
        piclist.add(R.drawable.banner3);
        piclist.add(R.drawable.banner4);
        Log.e(TAG, "onCreateView: token is " + Constants.TOKEN);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void initView() {
        mCartIv = (ImageView) mView.findViewById(R.id.iv_home_cart);
        mSearchIv = (ImageView) mView.findViewById(R.id.iv_home_search);
        mTitleRightIv = (ImageView) mView.findViewById(R.id.iv_title_right);
        mBannerVp = (ConvenientBanner) mView.findViewById(R.id.vp_banner);
        mClassifyGv = (GridView) mView.findViewById(R.id.gv_classify);
        mSecKillRecLv = (RecyclerView) mView.findViewById(R.id.rec_qiang);
        mSecKillRecLv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mSecKillRecLv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        mLoadingRl = (RelativeLayout) mView.findViewById(R.id.rl_loading);
    }

    public void setBanner() {
        mBannerVp.startTurning(3000);
        mBannerVp.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String url = mBannerList.get(position).getPicture_path();
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("goodId", mBannerList.get(position).getGoods_id());
                intent.putExtra("cityCode", Constants.CITY_CODE);
                startActivity(intent);
                Log.e(TAG, "onItemClick: current path is " + url);
            }
        });
        mBannerVp.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, mBannerList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.guide_u_indicator1, R.drawable.guide_s_indicator1})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        //设置翻页的效果，不需要翻页效果可用不设
//        .setPageTransformer(Transformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        mBannerVp.setManualPageable(false);//设置不能手动影响

    }


    public class LocalImageHolderView implements Holder<BannerEntity.ResultBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerEntity.ResultBean data) {
            String url = mBannerList.get(position).getPicture_path();
//            Picasso.with(context).load(url).into(imageView);
            Glide.with(context).load(url).into(imageView);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
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
                String classifyName = mClassifyList.get((int) l).getTitle();
                Intent intent = new Intent(getActivity(), BrandActivity.class);
                intent.putExtra("classifyId", classifyId);
                intent.putExtra("classifyName", classifyName);
                startActivity(intent);
            }
        });
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_home_cart:
                    Intent intent = new Intent(getActivity(), CartActivity.class);
                    startActivity(intent);
                    break;
                case R.id.iv_title_right:
                    Intent orderManageActivity = new Intent(getActivity(), OrderListActivity.class);
                    startActivity(orderManageActivity);
                    break;
                case R.id.iv_home_search:
                    Intent searchIntent = new Intent(getActivity(), SearchActivity.class);
                    startActivity(searchIntent);
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

    private void initSecKill() {
        mLoadingRl.setVisibility(View.VISIBLE);
        ApiManager.getSeckill(new ResponseCallbackHandler() {
            @Override
            public void onSuccessResponse(String response, int type) {
                mLoadingRl.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "initSecKill is " + response);
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
                    Log.e(TAG, "initBanner is " + response);
                    Gson gson = new Gson();
                    BannerEntity bannerEntity = gson.fromJson(response, BannerEntity.class);
                    if (bannerEntity != null) {
                        if (TextUtils.equals(bannerEntity.getErr_code(), "0")) {
                            mBannerList = bannerEntity.getResult();
//                            mHandler.sendEmptyMessage(100);
                            setBanner();
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
                    Log.e(TAG, "initClassify is " + response);
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
