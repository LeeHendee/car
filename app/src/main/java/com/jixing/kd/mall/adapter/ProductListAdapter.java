package com.jixing.kd.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jixing.kd.R;
import com.jixing.kd.mall.IListener;
import com.jixing.kd.mall.entity.ProductListEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yan on 2017/12/27.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductionViewHolder> {

    private static final String TAG = "ProductListAdapter";

    private Context context;

    private List<ProductListEntity.ResultBean> list;

    private IListener mItemListener;

    public void setmItemListener(IListener itemListener) {
        this.mItemListener = itemListener;
    }

    public ProductListAdapter(Context context, List<ProductListEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ProductionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_new, parent, false);
        ProductionViewHolder holder = new ProductionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductionViewHolder holder, final int position) {
        ProductListEntity.ResultBean bean = list.get(position);
        holder.titleTv.setText(bean.getGoods_title());
        holder.priceTv.setText(context.getResources().getString(R.string.rmb) + bean.getPromotion_price());
        holder.soldTv.setText(bean.getSold_number() + "");
        holder.reviewsTv.setText(bean.getStock() + "");
        holder.reviewsRateTv.setText(bean.getGoods_title());
        holder.productIv.setTag(bean.getSmall_picture());

        if (holder.productIv.getTag() == bean.getSmall_picture()) {
            if (!TextUtils.isEmpty(bean.getSmall_picture()))
                Picasso.with(context).load(bean.getSmall_picture()).into(holder.productIv);
        }
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.itemClickListener(position);
            }
        });
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
        LinearLayout itemLayout;

        public ProductionViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.tv_product_title);
            priceTv = (TextView) itemView.findViewById(R.id.tv_price);
            soldTv = (TextView) itemView.findViewById(R.id.tv_sold);
            reviewsTv = (TextView) itemView.findViewById(R.id.tv_reviews);
            reviewsRateTv = (TextView) itemView.findViewById(R.id.tv_good_reviews_rate);
            productIv = (ImageView) itemView.findViewById(R.id.iv_product);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.ll_product_item);
        }
    }
}
