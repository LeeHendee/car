package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.entity.OrderListEntity;
import com.example.gtercn.car.mall.entity.SingleItemEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/5.
 * Used to : 订单管理适配器
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private static final String TAG = "OrderAdapter";

    private Context context;

    private List<OrderListEntity.ResultBean> list;

    public OrderAdapter(Context context, List<OrderListEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        OrderViewHolder holder = new OrderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        final OrderListEntity.ResultBean entity = list.get(position);
        holder.countTv.setText("共" + entity.getItem_count() + "件商品 实付款: ");
        holder.totalTv.setText("￥" + entity.getTotal_amount());
        List<OrderListEntity.ResultBean.OrderDetailsBean> itemList = entity.getOrder_details();
        for (int i = 0; i < itemList.size(); i++) {
            OrderListEntity.ResultBean.OrderDetailsBean single = itemList.get(i);
            View view = LayoutInflater.from(context).inflate(R.layout.item_single_product, null, false);
            TextView title = (TextView) view.findViewById(R.id.tv_title);
            TextView price = (TextView) view.findViewById(R.id.tv_price);
            TextView count = (TextView) view.findViewById(R.id.tv_count);
            ImageView itemIv = (ImageView) view.findViewById(R.id.iv_item);
//            title.setText(single.getTitle());
//            price.setText(single.getPrice());
            count.setText(single.getNumber() + "");
            Picasso.with(context).load(single.getSmall_picture()).into(itemIv);
            TextView line = new TextView(context);
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,3));
            line.setBackgroundColor(Color.WHITE);

            holder.wrapperLayout.addView(view);
            holder.wrapperLayout.addView(line);
        }
        holder.orderNumberTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, entity.getOrder_no(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderNumberTv;

        ImageView delIv;

        LinearLayout wrapperLayout;

        TextView totalTv;

        TextView countTv;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderNumberTv = (TextView) itemView.findViewById(R.id.tv_order_number);
            totalTv = (TextView) itemView.findViewById(R.id.tv_total_pay);
            countTv = (TextView) itemView.findViewById(R.id.tv_count);
            delIv = (ImageView) itemView.findViewById(R.id.iv_del);
            wrapperLayout = (LinearLayout) itemView.findViewById(R.id.ll_wrapper);
        }
    }
}
