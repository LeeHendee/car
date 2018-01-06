package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.entity.CartEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yan on 2018/1/6.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private static final String TAG = "CartAdapter";

    private Context context;

    private List<CartEntity.ResultBean> list;

    public CartAdapter(Context context, List<CartEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false);
        CartViewHolder holder = new CartViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        CartEntity.ResultBean bean = list.get(position);
        holder.titleTv.setText(bean.getGoods_title());
        holder.priceTv.setText("￥"+bean.getPromotion_price());
        holder.countTv.setText(bean.getNumber()+"");
        Picasso.with(context).load(bean.getSmall_picture()).into(holder.productIv);
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView checkIv;
        ImageView productIv;
        TextView titleTv;
        TextView priceTv;
        TextView minusTv;
        TextView plusTv;
        TextView countTv;

        public CartViewHolder(View itemView) {
            super(itemView);
            checkIv = (ImageView) itemView.findViewById(R.id.iv_check);
            productIv = (ImageView) itemView.findViewById(R.id.iv_product);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
            priceTv = (TextView) itemView.findViewById(R.id.tv_price);
            minusTv = (TextView) itemView.findViewById(R.id.tv_minus);
            plusTv = (TextView) itemView.findViewById(R.id.tv_plus);
            countTv = (TextView) itemView.findViewById(R.id.tv_count);
        }
    }
}
