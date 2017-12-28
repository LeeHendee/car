package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.entity.ProductListEntity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.ls.LSException;

import java.util.List;

/**
 * Created by Yan on 2017/12/27.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductionViewHolder> {

    private static final String TAG = "ProductListAdapter";

    private Context context;

    private List<ProductListEntity.ResultBean> list;

    public ProductListAdapter(Context context, List<ProductListEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ProductionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_production, parent, false);
        ProductionViewHolder holder = new ProductionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductionViewHolder holder, int position) {
        ProductListEntity.ResultBean bean = list.get(position);
        holder.titleTv.setText(bean.getGoods_title());
        holder.priceTv.setText(bean.getPromotion_price() + "");
        holder.soldTv.setText(bean.getSold_number() + "");
        holder.reviewsTv.setText(bean.getGoods_synopsis());
        holder.reviewsRateTv.setText(bean.getGoods_title());
        Picasso.with(context).load(bean.getSmall_picture()).into(holder.productIv);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ProductionViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        TextView priceTv;
        TextView soldTv;
        TextView reviewsTv;
        TextView reviewsRateTv;
        ImageView productIv;

        public ProductionViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.tv_product_title);
            priceTv = (TextView) itemView.findViewById(R.id.tv_price);
            soldTv = (TextView) itemView.findViewById(R.id.tv_sold);
            reviewsTv = (TextView) itemView.findViewById(R.id.tv_reviews);
            reviewsRateTv = (TextView) itemView.findViewById(R.id.tv_good_reviews_rate);
            productIv = (ImageView) itemView.findViewById(R.id.iv_product);
        }
    }
}
