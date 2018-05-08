package com.jixing.kd.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jixing.kd.R;
import com.jixing.kd.mall.IListener;
import com.jixing.kd.mall.entity.ShopListEntity;

import java.util.List;

/**
 * Created by LIHANG on 2018/3/19.
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.MyViewHolder> {

    private static final String TAG = "ShopListAdapter";

    private Context context;

    private List<ShopListEntity.ResultBean> list;

    private IListener listener;

    public void setListener(IListener listener) {
        this.listener = listener;
    }

    public ShopListAdapter(Context context, List<ShopListEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ShopListEntity.ResultBean entity = list.get(position);
        holder.shopNameTv.setText(entity.getShop_name());
        holder.addressTv.setText(entity.getDetail_address());
        holder.telTv.setText(entity.getTel_number_list());
        holder.chooseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClickListener(position);

            }
        });
        holder.telLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "拨打电话", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView shopNameTv;
        TextView addressTv;
        TextView telTv;
        TextView chooseTv;
        LinearLayout telLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            shopNameTv = (TextView) itemView.findViewById(R.id.tv_shop_name);
            addressTv = (TextView) itemView.findViewById(R.id.tv_address);
            telTv = (TextView) itemView.findViewById(R.id.tv_tel);
            chooseTv = (TextView) itemView.findViewById(R.id.tv_choose);
            telLayout = (LinearLayout) itemView.findViewById(R.id.ll_tel);
        }
    }
}
