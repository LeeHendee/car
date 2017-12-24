package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.entity.BrandListEntity;
import com.example.gtercn.car.mall.view.ProductListActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yan on 2017/12/24.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder> {

    private static final String TAG = "BrandAdapter";

    private Context context;

    private List<BrandListEntity.ResultBean> list;

    public BrandAdapter(Context context, List<BrandListEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BrandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brand, parent, false);
        BrandViewHolder holder = new BrandViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BrandViewHolder holder, int position) {
        BrandListEntity.ResultBean bean = list.get(position);
        holder.mBrandNameTv.setText(bean.getCn_name());
        holder.mGoBuyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductListActivity.class);
            }
        });
        holder.mDescriptionTv.setText(bean.getDescrption());
        Picasso.with(context).load(bean.getPicture()).into(holder.mProductOne);
        Picasso.with(context).load(bean.getPicture()).into(holder.mProductTwo);
        Picasso.with(context).load(bean.getPicture()).into(holder.mProductThree);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class BrandViewHolder extends RecyclerView.ViewHolder {
        TextView mBrandNameTv;
        TextView mGoBuyTv;
        TextView mDescriptionTv;
        ImageView mProductOne;
        ImageView mProductTwo;
        ImageView mProductThree;

        public BrandViewHolder(View itemView) {
            super(itemView);
            mBrandNameTv = (TextView) itemView.findViewById(R.id.tv_brand_name);
            mGoBuyTv = (TextView) itemView.findViewById(R.id.tv_go_buy);
            mDescriptionTv = (TextView) itemView.findViewById(R.id.tv_description);
            mProductOne = (ImageView) itemView.findViewById(R.id.iv_product_one);
            mProductTwo = (ImageView) itemView.findViewById(R.id.iv_product_two);
            mProductThree = (ImageView) itemView.findViewById(R.id.iv_product_three);
        }
    }

}
