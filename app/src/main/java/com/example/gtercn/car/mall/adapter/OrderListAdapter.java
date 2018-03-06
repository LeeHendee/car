package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.mall.IListenerTwo;
import com.example.gtercn.car.mall.entity.OrderListEntity;
import com.example.gtercn.car.mall.view.ChoosePayActivity;
import com.example.gtercn.car.mall.view.OrderDetailActivity;
import com.example.gtercn.car.mall.view.ReviewPostActivity;
import com.example.gtercn.car.utils.Constants;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/5.
 * Used to : 订单管理适配器
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder> {

    private static final String TAG = "OrderListAdapter";

    public static final int CANCEL = 101;

    public static final int DEL = 102;

    private Context context;

    private List<OrderListEntity.ResultBean> list;

    private IListenerTwo cancelListener;

    private IListenerTwo delListener;

    public void setCancelListener(IListenerTwo cancelListener) {
        this.cancelListener = cancelListener;
    }

    public void setDelListener(IListenerTwo delListener) {
        this.delListener = delListener;
    }

    public OrderListAdapter(Context context, List<OrderListEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        OrderViewHolder holder = new OrderViewHolder(view);
        return holder;
    }

    /**
     * 订单状态：0:全部订单，1待付款, 2已付款(待发货), 3关闭订单(超时未付款), 4已发货(待收货),
     * 5确认收货(待评价), 6已评价(订单完成),7退货申请, 8退货中, 9已退货, 10用户取消订单
     */
    @Override
    public void onBindViewHolder(OrderViewHolder holder, final int position) {
        final OrderListEntity.ResultBean entity = list.get(position);
        holder.countTv.setText("共" + entity.getItem_count() + "件商品 实付款: ");
        holder.totalTv.setText("￥" + entity.getTotal_amount());
        List<OrderListEntity.ResultBean.OrderDetailsBean> itemList = entity.getOrder_details();

        for (int i = 0; i < itemList.size(); i++) {
            holder.wrapperLayout.removeAllViews();
            OrderListEntity.ResultBean.OrderDetailsBean single = itemList.get(i);
            View view = LayoutInflater.from(context).inflate(R.layout.item_single_product, null, false);
            TextView title = (TextView) view.findViewById(R.id.tv_title);
            TextView price = (TextView) view.findViewById(R.id.tv_price);
            TextView count = (TextView) view.findViewById(R.id.tv_count);
            ImageView itemIv = (ImageView) view.findViewById(R.id.iv_item);
//            title.setText(single.getTitle());
//            price.setText(single.getPrice());
            count.setText(single.getNumber() + "");
            if (single.getSmall_picture_list() != null)
                Picasso.with(context).load(single.getSmall_picture_list().get(0)).into(itemIv);
            TextView line = new TextView(context);
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 3));
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
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("orderId", entity.getId());
                context.startActivity(intent);
            }
        });

        holder.delIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delListener.listener(position, DEL);
            }
        });

        holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelListener.listener(position, CANCEL);
            }
        });
        holder.toPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent payIntent = new Intent(context, ChoosePayActivity.class);
                payIntent.putExtra("orderId", entity.getId());
                payIntent.putExtra("sum", entity.getTotal_amount());
                context.startActivity(payIntent);
            }
        });
        holder.checkDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "查看物流", Toast.LENGTH_SHORT).show();
            }
        });
        holder.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "确认", Toast.LENGTH_SHORT).show();
            }
        });
        holder.reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "评价", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ReviewPostActivity.class);
                intent.putExtra("orderId",entity.getId());
//                intent.putExtra("goodsId",entity.get)
            }
        });

        //根据订单状态，设置按钮的显示隐藏；
        switch (entity.getOrder_status()) {
            case 0:
                //全部订单；
                break;
            case 1:
                //待付款；
                holder.cancelBtn.setVisibility(View.VISIBLE);
                holder.toPayBtn.setVisibility(View.VISIBLE);
                holder.checkDeliveryBtn.setVisibility(View.GONE);
                holder.confirmBtn.setVisibility(View.GONE);
                holder.reviewBtn.setVisibility(View.GONE);
                break;
            case 2:
                //待收货；
                holder.cancelBtn.setVisibility(View.GONE);
                holder.toPayBtn.setVisibility(View.GONE);
                holder.checkDeliveryBtn.setVisibility(View.VISIBLE);
                holder.confirmBtn.setVisibility(View.VISIBLE);
                holder.reviewBtn.setVisibility(View.GONE);
                break;
            case 3:
                holder.cancelBtn.setVisibility(View.GONE);
                holder.toPayBtn.setVisibility(View.VISIBLE);
                holder.toPayBtn.setText("订单关闭" + "");
                holder.toPayBtn.setClickable(false);
                holder.checkDeliveryBtn.setVisibility(View.GONE);
                holder.confirmBtn.setVisibility(View.GONE);
                holder.reviewBtn.setVisibility(View.GONE);
                break;
            case 5:
                //待评价；
                holder.cancelBtn.setVisibility(View.GONE);
                holder.toPayBtn.setVisibility(View.GONE);
                holder.checkDeliveryBtn.setVisibility(View.VISIBLE);
                holder.confirmBtn.setVisibility(View.GONE);
                holder.reviewBtn.setVisibility(View.VISIBLE);
                break;
            case 6:
                //已评价（完成）；
            default:
                holder.cancelBtn.setVisibility(View.GONE);
                holder.toPayBtn.setVisibility(View.GONE);
                holder.checkDeliveryBtn.setVisibility(View.GONE);
                holder.confirmBtn.setVisibility(View.GONE);
                holder.reviewBtn.setVisibility(View.GONE);
                break;
            case 8:
                holder.cancelBtn.setVisibility(View.GONE);
                holder.toPayBtn.setVisibility(View.VISIBLE);
                holder.toPayBtn.setText("退货中" + "");
                holder.toPayBtn.setClickable(false);
                holder.checkDeliveryBtn.setVisibility(View.GONE);
                holder.confirmBtn.setVisibility(View.GONE);
                holder.reviewBtn.setVisibility(View.GONE);
                break;
            case 9:
                holder.cancelBtn.setVisibility(View.GONE);
                holder.toPayBtn.setVisibility(View.VISIBLE);
                holder.toPayBtn.setText("已退货" + "");
                holder.toPayBtn.setClickable(false);
                holder.checkDeliveryBtn.setVisibility(View.GONE);
                holder.confirmBtn.setVisibility(View.GONE);
                holder.reviewBtn.setVisibility(View.GONE);
                break;
            case 10:
                holder.cancelBtn.setVisibility(View.GONE);
                holder.toPayBtn.setVisibility(View.VISIBLE);
                holder.toPayBtn.setText("已取消" + "");
                holder.toPayBtn.setClickable(false);
                holder.checkDeliveryBtn.setVisibility(View.GONE);
                holder.confirmBtn.setVisibility(View.GONE);
                holder.reviewBtn.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderNumberTv;

        ImageView delIv;

        LinearLayout wrapperLayout;

        LinearLayout itemLayout;

        TextView totalTv;

        TextView countTv;

        Button confirmBtn;

        Button checkDeliveryBtn;

        Button cancelBtn;

        Button reviewBtn;

        Button toPayBtn;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderNumberTv = (TextView) itemView.findViewById(R.id.tv_order_number);
            totalTv = (TextView) itemView.findViewById(R.id.tv_total_pay);
            countTv = (TextView) itemView.findViewById(R.id.tv_count);
            delIv = (ImageView) itemView.findViewById(R.id.iv_del);
            wrapperLayout = (LinearLayout) itemView.findViewById(R.id.ll_wrapper);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.ll_item);
            confirmBtn = (Button) itemView.findViewById(R.id.btn_confirm);
            checkDeliveryBtn = (Button) itemView.findViewById(R.id.btn_see_delivery);
            cancelBtn = (Button) itemView.findViewById(R.id.btn_cancel_del);
            reviewBtn = (Button) itemView.findViewById(R.id.btn_review);
            toPayBtn = (Button) itemView.findViewById(R.id.btn_pay);
        }
    }
}
