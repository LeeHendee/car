package com.jixing.kd.mall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jixing.kd.mall.entity.BannerEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yan on 2017/12/18.
 */

public class MallBannerAdapter extends PagerAdapter {

    private static final String TAG = "MallBannerAdapter";

    private List<BannerEntity.ResultBean> banners;

    private Context mContext;

    public MallBannerAdapter(List<BannerEntity.ResultBean> banners, Context context) {
        this.banners = banners;
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
        Picasso.with(mContext).load(banners.get(position % banners.size()).getPicture_path()).into(imageView);

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
