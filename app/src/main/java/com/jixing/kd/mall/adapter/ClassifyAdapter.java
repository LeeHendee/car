package com.jixing.kd.mall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jixing.kd.R;
import com.jixing.kd.mall.entity.ClassifyEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2017/12/20.
 * Used to :
 */

public class ClassifyAdapter extends BaseAdapter {

    private static final String TAG = "ClassifyAdapter";

    private List<ClassifyEntity.ResultBean> list;

    private Context mContext;

    public ClassifyAdapter(Context context, List<ClassifyEntity.ResultBean> list) {
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
        ClassifyEntity.ResultBean bean = list.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_classify, parent, false);
        TextView nameTv = (TextView) convertView.findViewById(R.id.tv_name);
        ImageView classifyIv = (ImageView) convertView.findViewById(R.id.iv_classify);
        nameTv.setText(bean.getTitle());
        Picasso.with(mContext).load(bean.getUrl()).into(classifyIv);
//        classifyIv.setImageResource(bean.getRes());
        convertView.setTag(nameTv);
        return convertView;
    }


}
