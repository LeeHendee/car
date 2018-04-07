package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.IListener;
import com.example.gtercn.car.mall.entity.CartEntity;
import com.example.gtercn.car.mall.view.ProductDetailActivity;
import com.example.gtercn.car.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yan on 2018/1/6.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private static final String TAG = "CartAdapter";

    private Context context;

    private List<CartEntity.ResultBean> list;

    private IListener listener;

    private IChangeCount iChangeCount;

    public void setiChangeCount(IChangeCount iChangeCount) {
        this.iChangeCount = iChangeCount;
    }

    public void setListener(IListener listener) {
        this.listener = listener;
    }

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
    public void onBindViewHolder(CartViewHolder holder, final int position) {
        final CartEntity.ResultBean bean = list.get(position);
        holder.titleTv.setText(bean.getGoods_title());
        holder.priceTv.setText(context.getResources().getString(R.string.rmb)+bean.getPromotion_price());
        holder.countTv.setText(bean.getNumber()+"");
        if (!
                TextUtils.isEmpty(bean.getSmall_picture())) {
            Picasso.with(context).load(bean.getSmall_picture()).into(holder.productIv);
        }
        if (bean.isSelected()){
            holder.checkIv.setImageResource(R.drawable.cart1_checkbox_check);
        }else {
            holder.checkIv.setImageResource(R.drawable.cart1_checkbox_normal);
        }
        holder.checkIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClickListener(position);

            }
        });

        holder.minusTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iChangeCount.changeCount(false,position);
            }
        });

        holder.plusTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iChangeCount.changeCount(true,position);
            }
        });

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("goodId", bean.getId());
                intent.putExtra("cityCode", Constants.CITY_CODE);
                context.startActivity(intent);
            }
        });

        if (bean.getNumber() == 1){
            holder.minusTv.setTextColor(context.getResources().getColor(R.color.text_note_color));
        }else {
            holder.minusTv.setTextColor(context.getResources().getColor(R.color.text_common_color));
        }


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
        LinearLayout itemLayout;


        public CartViewHolder(View itemView) {
            super(itemView);
            checkIv = (ImageView) itemView.findViewById(R.id.iv_check);
            productIv = (ImageView) itemView.findViewById(R.id.iv_product);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
            priceTv = (TextView) itemView.findViewById(R.id.tv_price);
            minusTv = (TextView) itemView.findViewById(R.id.tv_minus);
            plusTv = (TextView) itemView.findViewById(R.id.tv_plus);
            countTv = (TextView) itemView.findViewById(R.id.tv_count);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }

    public interface IChangeCount{
        void changeCount(boolean isAdd,int pos);
    }
}
