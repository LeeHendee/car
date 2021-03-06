package com.jixing.kd.mall.view.custom_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jixing.kd.R;


public class ProgressBarItem extends LinearLayout {

    public ProgressBarItem(Context context) {
        super(context);
        init(context);
    }

    public ProgressBarItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ProgressBarItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View progressBarItemView = inflater.inflate(R.layout.progressbar_item, null);
        addView(progressBarItemView);
        ImageView progressImageView = (ImageView) this.findViewById(R.id.iv_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) progressImageView.getDrawable();
        animationDrawable.start();
    }
}
