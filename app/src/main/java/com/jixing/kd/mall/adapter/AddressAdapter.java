package com.jixing.kd.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jixing.kd.R;
import com.jixing.kd.mall.IListenerTwo;
import com.jixing.kd.mall.entity.AddressEntity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/9.
 * Used to : 地址管理adapter
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private static final String TAG = "AddressAdapter";

    public static final int SET_DEFAULT = 0;

    public static final int DO_EDIT = 1;

    public static final int DO_DEL = 2;

    private Context context;

    private List<AddressEntity.ResultBean> list;

    private IListenerTwo mListener;

    public void setListener(IListenerTwo mListener) {
        this.mListener = mListener;
    }

    public AddressAdapter(Context context, List<AddressEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_address, parent, false);
        AddressViewHolder holder = new AddressViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, final int position) {
        AddressEntity.ResultBean bean = list.get(position);
        holder.nameTv.setText(bean.getName());
        holder.telTv.setText(bean.getPhone());
        holder.addressTv.setText(bean.getAddress());

        if (bean.getDefault_flag().equals("Y")) {
            holder.defaultIv.setImageResource(R.drawable.cart1_checkbox_check);
        } else {
            holder.defaultIv.setImageResource(R.drawable.cart1_checkbox_normal);
        }

        holder.defaultIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.listener(position, SET_DEFAULT);
            }
        });

        holder.delLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.listener(position, DO_DEL);
            }
        });

        holder.editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.listener(position, DO_EDIT);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class AddressViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView telTv;
        TextView addressTv;
        ImageView defaultIv;
        LinearLayout delLayout;
        LinearLayout editLayout;

        public AddressViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            telTv = (TextView) itemView.findViewById(R.id.tv_tel);
            addressTv = (TextView) itemView.findViewById(R.id.tv_address);
            defaultIv = (ImageView) itemView.findViewById(R.id.iv_default);
            delLayout = (LinearLayout) itemView.findViewById(R.id.ll_del);
            editLayout = (LinearLayout) itemView.findViewById(R.id.ll_edit);
        }
    }


}
