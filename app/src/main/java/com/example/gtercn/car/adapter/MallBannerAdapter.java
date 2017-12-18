package com.example.gtercn.car.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.bean.HomeAdBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan on 2017/12/18.
 */

public class MallBannerAdapter extends PagerAdapter {

    private static final String TAG = "MallBannerAdapter";

    private List<HomeAdBean> mBannerViews;

    private Context mContext;

    public MallBannerAdapter(List<HomeAdBean> views, Context context) {
        this.mBannerViews = views;
        mContext = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(mBannerViews.get(position % mBannerViews.size()).getRes());
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
