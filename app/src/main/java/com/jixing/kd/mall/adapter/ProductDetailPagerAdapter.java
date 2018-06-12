package com.jixing.kd.mall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jixing.kd.R;
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
        Log.e(TAG, "instantiateItem: urls is " + urls);
        if (urls != null && urls.size() > 0) {
            Log.e(TAG, "instantiateItem: url is " + urls.get(position));
            if (!TextUtils.isEmpty(urls.get(position))) {
                String url = urls.get(position).trim();
                Picasso.with(context).load(url).into(imageView);
            }
        }
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
