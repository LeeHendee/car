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
import com.example.gtercn.car.mall.entity.OrderEntity;
import com.example.gtercn.car.mall.entity.SingleItemEntity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/5.
 * Used to : 订单管理适配器
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private static final String TAG = "OrderAdapter";

    private Context context;

    private List<OrderEntity> list;

    public OrderAdapter(Context context, List<OrderEntity> list) {
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
        final OrderEntity entity = list.get(position);
        holder.countTv.setText("共" + entity.getCountAll() + "件商品 实付款: ");
        holder.totalTv.setText("￥" + entity.getTotalCost());
        for (int i = 0; i < entity.getSingleItemList().size(); i++) {
            SingleItemEntity single = entity.getSingleItemList().get(i);
            View view = LayoutInflater.from(context).inflate(R.layout.item_single_product, null, false);
            TextView title = (TextView) view.findViewById(R.id.tv_title);
            TextView price = (TextView) view.findViewById(R.id.tv_price);
            TextView count = (TextView) view.findViewById(R.id.tv_count);
            title.setText(single.getTitle());
            price.setText(single.getPrice());
            count.setText(single.getCount());
            TextView line = new TextView(context);
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,3));
            line.setBackgroundColor(Color.WHITE);

            holder.wrapperLayout.addView(view);
            holder.wrapperLayout.addView(line);
        }
        holder.orderNumberTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, entity.getOrderNumber(), Toast.LENGTH_SHORT).show();
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
