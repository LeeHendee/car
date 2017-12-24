package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.entity.SecKillEntity;

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
        SecKillEntity.ResultBean bean = secList.get(position);
        holder.secIv.setImageResource(R.drawable.ic_launcher);
        holder.salePrice.setText("￥" + bean.getPromotion_price());
        holder.orgPrice.setText("￥" + bean.getPrime_price());
        holder.orgPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }


    @Override
    public int getItemCount() {
        return secList != null ? secList.size() : 0;
    }

    class SecKillViewHolder extends RecyclerView.ViewHolder {
        ImageView secIv;
        TextView salePrice;
        TextView orgPrice;

        public SecKillViewHolder(View itemView) {
            super(itemView);
            secIv = (ImageView) itemView.findViewById(R.id.iv_seckill);
            salePrice = (TextView) itemView.findViewById(R.id.tv_sale_price);
            orgPrice = (TextView) itemView.findViewById(R.id.tv_org_price);
        }
    }
}
