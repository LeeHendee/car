package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gtercn.car.R;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/12.
 * Used to :
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {


    private static final String TAG = "StationAdapter";

    private Context context;

    private List<String> list;

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_station_list, parent, false);
        StationViewHolder holder = new StationViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(StationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class StationViewHolder extends RecyclerView.ViewHolder {

        TextView mSelectTv;
        TextView mStationNameTv;
        TextView mDistanceTv;
        TextView mDetailAddressTv;
        TextView mTelTv;
        TextView mToGpsTv;


        public StationViewHolder(View itemView) {
            super(itemView);
            mSelectTv = (TextView) itemView.findViewById(R.id.tv_select);
            mStationNameTv = (TextView) itemView.findViewById(R.id.tv_station_name);
            mDistanceTv = (TextView) itemView.findViewById(R.id.tv_distance);
            mDetailAddressTv = (TextView) itemView.findViewById(R.id.tv_detail_address);
            mTelTv = (TextView) itemView.findViewById(R.id.tv_tel);
            mToGpsTv = (TextView) itemView.findViewById(R.id.tv_to_gps);
        }
    }
}
