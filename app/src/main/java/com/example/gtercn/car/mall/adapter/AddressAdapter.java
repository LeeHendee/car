package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.entity.AddressEntity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/9.
 * Used to : 地址管理adapter
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private static final String TAG = "AddressAdapter";

    private Context context;

    private List<AddressEntity.ResultBean> list;

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
    public void onBindViewHolder(AddressViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class AddressViewHolder extends RecyclerView.ViewHolder {

        public AddressViewHolder(View itemView) {
            super(itemView);

        }
    }
}
