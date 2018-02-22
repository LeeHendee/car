package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.entity.SecKillEntity;
import com.example.gtercn.car.mall.view.ProductDetailActivity;
import com.example.gtercn.car.mall.view.ProductListActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Yan on 2017/12/23.
 */

public class SecKillAdapter extends RecyclerView.Adapter<SecKillAdapter.SecKillViewHolder> {

    private Context context;

    private List<SecKillEntity.ResultBean> secList;

    public SecKillAdapter(Context context, List<SecKillEntity.ResultBean> secList) {
        this.context = context;
        this.secList = secList;
    }

    @Override
    public SecKillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_seckill, parent, false);
        SecKillViewHolder holder = new SecKillViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SecKillViewHolder holder, int position) {
        final SecKillEntity.ResultBean bean = secList.get(position);
        if (bean.getSmall_picture_list() != null && bean.getSmall_picture_list().size() > 0) {
            Picasso.with(context).load(bean.getSmall_picture_list().get(0)).into(holder.secIv);
        }

        holder.salePrice.setText(context.getResources().getString(R.string.rmb) + bean.getPromotion_price());
        holder.orgPrice.setText(context.getResources().getString(R.string.rmb) + bean.getPrime_price());
        holder.orgPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                String goodId = bean.getId();
                String cityCode = bean.getCity_code();
                intent.putExtra("goodId", goodId);
                intent.putExtra("cityCode", cityCode);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return secList != null ? secList.size() : 0;
    }

    class SecKillViewHolder extends RecyclerView.ViewHolder {
        ImageView secIv;
        TextView salePrice;
        TextView orgPrice;
        LinearLayout itemLayout;

        public SecKillViewHolder(View itemView) {
            super(itemView);
            secIv = (ImageView) itemView.findViewById(R.id.iv_seckill);
            salePrice = (TextView) itemView.findViewById(R.id.tv_sale_price);
            orgPrice = (TextView) itemView.findViewById(R.id.tv_org_price);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_seckill);
        }
    }
}
