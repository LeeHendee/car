package com.jixing.kd.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jixing.kd.R;
import com.jixing.kd.mall.entity.BrandListEntity;
import com.jixing.kd.mall.view.ProductDetailActivity;
import com.jixing.kd.mall.view.ProductListActivity;
import com.jixing.kd.utils.Constants;
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
        final BrandListEntity.ResultBean bean = list.get(position);
        holder.mBrandNameTv.setText(bean.getCn_name());
        holder.mGoBuyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("brandId", bean.getId());
                intent.putExtra("categoryId", bean.getCategory_id());
                context.startActivity(intent);
            }
        });

        holder.mDescriptionTv.setText(bean.getDescrption());

        holder.mProductThree.setVisibility(View.INVISIBLE);
        holder.mProductOne.setVisibility(View.INVISIBLE);
        holder.mProductTwo.setVisibility(View.INVISIBLE);

        if (bean.getPicture_list() != null && bean.getPicture_list().size() > 0) {
            for (int i = 0; i < bean.getPicture_list().size(); i++) {
                if (bean.getPicture_list().get(i).getPicture_url().isEmpty()) {
                    continue;
                }
                if (i == 0) {
                    holder.mProductOne.setVisibility(View.VISIBLE);
                    holder.mProductOne.setTag(bean.getPicture_list().get(i).getPicture_url());
                    if (holder.mProductOne.getTag() == bean.getPicture_list().get(i).getPicture_url()) {
                        Picasso.with(context).load(bean.getPicture_list().get(i).getPicture_url()).into(holder.mProductOne);
                    }
                    final int finalI1 = i;
                    holder.mProductOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i1 = new Intent(context, ProductDetailActivity.class);
                            i1.putExtra("goodId", bean.getPicture_list().get(finalI1).getGoods_id());
                            i1.putExtra("cityCode", Constants.CITY_CODE);

                            context.startActivity(i1);
                        }
                    });
                } else if (i == 1) {
                    holder.mProductTwo.setVisibility(View.VISIBLE);
                    holder.mProductTwo.setTag(bean.getPicture_list().get(i).getPicture_url());
                    if (holder.mProductTwo.getTag() == bean.getPicture_list().get(i).getPicture_url()) {
                        Picasso.with(context).load(bean.getPicture_list().get(i).getPicture_url()).into(holder.mProductTwo);
                    }
                    final int finalI = i;
                    holder.mProductTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i1 = new Intent(context, ProductDetailActivity.class);
                            i1.putExtra("goodId", bean.getPicture_list().get(finalI).getGoods_id());
                            i1.putExtra("cityCode", Constants.CITY_CODE);
                            context.startActivity(i1);
                        }
                    });
                } else if (i == 2) {
                    holder.mProductThree.setVisibility(View.VISIBLE);
                    holder.mProductThree.setTag(bean.getPicture_list().get(i).getPicture_url());
                    if (holder.mProductThree.getTag() == bean.getPicture_list().get(i).getPicture_url()) {
                        Picasso.with(context).load(bean.getPicture_list().get(i).getPicture_url()).into(holder.mProductThree);
                    }
                    final int finalI2 = i;
                    holder.mProductThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i1 = new Intent(context, ProductDetailActivity.class);
                            i1.putExtra("goodId", bean.getPicture_list().get(finalI2).getGoods_id());
                            i1.putExtra("cityCode", Constants.CITY_CODE);
                            context.startActivity(i1);
                        }
                    });
                }
            }
        }
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
