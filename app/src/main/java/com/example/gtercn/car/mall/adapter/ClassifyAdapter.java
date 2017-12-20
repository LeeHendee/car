package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.entity.ClassifyEntity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2017/12/20.
 * Used to :
 */

public class ClassifyAdapter extends BaseAdapter {

    private static final String TAG = "ClassifyAdapter";

    private List<ClassifyEntity> list;

    private Context mContext;

    public ClassifyAdapter(Context context, List<ClassifyEntity> list) {
        this.list = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_classify, parent, false);
        TextView nameTv = (TextView) convertView.findViewById(R.id.tv_name);
        ImageView classifyIv = (ImageView) convertView.findViewById(R.id.iv_classify);
        nameTv.setText(list.get(position).getName());
        classifyIv.setImageResource(list.get(position).getRes());
        convertView.setTag(nameTv);
        return convertView;
    }


}
