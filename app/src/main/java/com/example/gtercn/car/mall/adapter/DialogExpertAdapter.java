package com.example.gtercn.car.mall.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.IListener;
import com.example.gtercn.car.mall.IListenerTwo;
import com.example.gtercn.car.mall.entity.ExpertListEntity;
import com.example.gtercn.car.utils.ContactUtil;
import com.example.gtercn.car.widget.PopPhoneMenu;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Yan on 2018/3/18.
 */

public class DialogExpertAdapter extends BaseAdapter {

    private static final String TAG = "DialogExpertAdapter";

    private Context context;

    private List<ExpertListEntity.ResultBean> list;

    private IListenerTwo listener;

    public static final int WECHAT = 100;

    public static final int DOCALL = 101;

    public void setListener(IListenerTwo listener) {
        this.listener = listener;
    }

    public DialogExpertAdapter(Context context, List<ExpertListEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        MyHolder holder = null;
        final ExpertListEntity.ResultBean entity = list.get(i);
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_expert, viewGroup, false);
            holder = new MyHolder(view);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        Picasso.with(context).load(list.get(i).getExpert_portrait_url()).into(holder.riv);
        holder.tv.setText(entity.getExpert_name());
        holder.wechatLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.listener(i, WECHAT);
            }
        });
        holder.callLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.listener(i, DOCALL);
            }
        });
        return view;
    }

    class MyHolder {
        RoundedImageView riv;
        TextView tv;
        LinearLayout wechatLl;
        LinearLayout callLl;

        public MyHolder(View view) {
            riv = (RoundedImageView) view.findViewById(R.id.riv);
            tv = (TextView) view.findViewById(R.id.tv_expert);
            wechatLl = (LinearLayout) view.findViewById(R.id.circle_detail_wechat);
            callLl = (LinearLayout) view.findViewById(R.id.circle_detail_make_call_layout);
        }
    }
}
