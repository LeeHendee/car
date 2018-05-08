package com.jixing.kd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.jixing.kd.R;
import com.jixing.kd.bean.CityListBean;

import java.util.List;

/**
 *
 */
public class ResultListAdapter extends BaseAdapter {
    private Context mContext;
    private List<CityListBean.ResultBean> mCities;

    public ResultListAdapter(Context mContext, List<CityListBean.ResultBean> mCities) {
        this.mCities = mCities;
        this.mContext = mContext;
    }

    public void changeData(List<CityListBean.ResultBean> list) {
        if (mCities == null) {
            mCities = list;
        } else {
            mCities.clear();
            mCities.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCities == null ? 0 : mCities.size();
    }

    @Override
    public CityListBean.ResultBean getItem(int position) {
        return mCities == null ? null : mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ResultViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_search_result_listview, parent, false);
            holder = new ResultViewHolder();
            holder.name = (TextView) view.findViewById(R.id.tv_item_result_listview_name);
            view.setTag(holder);
        } else {
            holder = (ResultViewHolder) view.getTag();
        }
        holder.name.setText(mCities.get(position).getCity_name());
        return view;
    }

    public static class ResultViewHolder {
        TextView name;
    }
}
