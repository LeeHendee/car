package com.example.gtercn.car.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.mall.entity.ExpertListEntity;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Yan on 2018/3/18.
 */

public class ContactUtil {

    public static void getWeChatNumber(ExpertListEntity.ResultBean bean, final Context context) {
        final View view = LayoutInflater.from(context).inflate(R.layout.custom_add_wechat, null);
        final TextView weChatNumber = (TextView) view.findViewById(R.id.wechat_number);
        weChatNumber.setText(bean.getExpert_wechat_number());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        builder.setTitle("点击确定,复制微信号到剪切板!");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ClipboardManager cm = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                cm.setText(weChatNumber.getText().toString().trim());
                Toast.makeText(context, "成功复制微信号:", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setCancelable(true);
        builder.create().show();
    }
}
