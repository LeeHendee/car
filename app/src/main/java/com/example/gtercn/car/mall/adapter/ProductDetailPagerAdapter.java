package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.gtercn.car.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yan on 2017/12/29.
 */

public class ProductDetailPagerAdapter extends PagerAdapter {

    private static final String TAG = "ProductDetailPagerAdapt";

    private List<String> urls;

    private Context context;

    public ProductDetailPagerAdapter(List<String> urls, Context context) {
        this.urls = urls;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pager_view, null);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_pager);
        Picasso.with(context).load(urls.get(position)).into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return urls != null ? urls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
