package com.jixing.kd.mall.view.custom_view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Yan on 2018/1/6.
 */

public class RecyItemSpace extends RecyclerView.ItemDecoration {

    int mSpace;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = 0;
        outRect.right = 0;
        outRect.bottom = mSpace;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = mSpace;
        }

    }

    public RecyItemSpace(int space) {
        this.mSpace = space;
    }
}
