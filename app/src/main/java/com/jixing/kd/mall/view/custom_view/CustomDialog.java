package com.jixing.kd.mall.view.custom_view;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jixing.kd.R;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/19.
 * Used to : dialog封装类
 * 未完成。。。
 */

public class CustomDialog {

    public static final int FIRST_BUTTON_CLICK = 12345;

    public static final int SECOND_BUTTON_CLICK = 54321;


    public static void customDialog(Context context, String msg, String leftButton, String rightButton, Dialog.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_view, null);
        TextView contentTv = (TextView) dialogView.findViewById(R.id.tv_content);
        TextView leftTv = (TextView) dialogView.findViewById(R.id.tv_left);
        TextView rightTv = (TextView) dialogView.findViewById(R.id.tv_right);

        contentTv.setText(msg);
        leftTv.setText(leftButton);
        rightTv.setText(rightButton);
        builder.setView(dialogView);
        builder.show();

    }

}
