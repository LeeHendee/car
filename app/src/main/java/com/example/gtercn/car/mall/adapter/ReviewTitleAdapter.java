package com.example.gtercn.car.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.gtercn.car.mall.entity.ReviewsEntity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/24.
 * Used to :
 */

public class ReviewTitleAdapter extends RecyclerView.Adapter<ReviewTitleAdapter.TitleAdapter> {

    private static final String TAG = "ReviewTitleAdapter";

    private Context context;

//    private List<ReviewsEntity.ResultBean.CommentListBean>

    @Override
    public TitleAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TitleAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TitleAdapter extends RecyclerView.ViewHolder {

        public TitleAdapter(View itemView) {
            super(itemView);
        }
    }
}
