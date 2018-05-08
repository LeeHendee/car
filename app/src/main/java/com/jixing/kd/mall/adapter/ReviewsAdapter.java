package com.jixing.kd.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jixing.kd.R;
import com.jixing.kd.mall.entity.ReviewsEntity;
import com.jixing.kd.utils.GetTimeData;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/24.
 * Used to :
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    private static final String TAG = "ReviewTitleAdapter";

    private Context context;

    private List<ReviewsEntity.ResultBean.CommentListBean> list;

    public ReviewsAdapter(Context context, List<ReviewsEntity.ResultBean.CommentListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reviews, parent, false);
        ReviewsViewHolder holder = new ReviewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReviewsViewHolder holder, int position) {
        ReviewsEntity.ResultBean.CommentListBean bean = list.get(position);
        Picasso.with(context).load(bean.getPicture()).into(holder.riv);
        holder.nameTv.setText(bean.getNickname());
        holder.dateTv.setText(GetTimeData.formatTime(bean.getCreate_time()));
        holder.contentTv.setText(bean.getContent());
        //动态显示图片
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ReviewsViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView riv;
        TextView nameTv;
        TextView dateTv;
        TextView contentTv;
        LinearLayout picsLl;
        LinearLayout itemLayout;

        public ReviewsViewHolder(View itemView) {
            super(itemView);
            riv = (RoundedImageView) itemView.findViewById(R.id.riv_portrait);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            dateTv = (TextView) itemView.findViewById(R.id.tv_date);
            contentTv = (TextView) itemView.findViewById(R.id.tv_content);
            picsLl = (LinearLayout) itemView.findViewById(R.id.ll_pics);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.ll_product_item);
        }
    }
}
