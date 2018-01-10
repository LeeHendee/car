package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.IListenerTwo;
import com.example.gtercn.car.mall.entity.AddressEntity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/10.
 * Used to :
 */

public class ChooseAddressAdapter extends RecyclerView.Adapter<ChooseAddressAdapter.ChooseAddressViewHolder> {

    private static final String TAG = "ChooseAddressAdapter";

    public static final int SET_SELECTED = 0;

    public static final int DO_EDIT = 1;

    private Context context;

    private List<AddressEntity.ResultBean> list;

    private IListenerTwo mListener;

    public void setListener(IListenerTwo mListener) {
        this.mListener = mListener;
    }

    public ChooseAddressAdapter(Context context, List<AddressEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ChooseAddressAdapter.ChooseAddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_choose_address, parent, false);
        ChooseAddressViewHolder holder = new ChooseAddressViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChooseAddressViewHolder holder, final int position) {
        AddressEntity.ResultBean bean = list.get(position);
        holder.nameTv.setText(bean.getName());
        holder.telTv.setText(bean.getPhone());
        holder.addressTv.setText(bean.getAddress());

        if (bean.isSelected()) {
            holder.selectedIv.setVisibility(View.VISIBLE);
            holder.selectedIv.setImageResource(R.drawable.icon_choosed_address);
        } else {
            holder.selectedIv.setVisibility(View.GONE);
        }

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.listener(position, SET_SELECTED);
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

    class ChooseAddressViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView telTv;
        TextView addressTv;
        ImageView selectedIv;
        LinearLayout editLayout;
        LinearLayout itemLayout;

        public ChooseAddressViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            telTv = (TextView) itemView.findViewById(R.id.tv_tel);
            addressTv = (TextView) itemView.findViewById(R.id.tv_address);
            selectedIv = (ImageView) itemView.findViewById(R.id.iv_selected);
            editLayout = (LinearLayout) itemView.findViewById(R.id.ll_edit);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.ll_choose_address);
        }
    }
}
