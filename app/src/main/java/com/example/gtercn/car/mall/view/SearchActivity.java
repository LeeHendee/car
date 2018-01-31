package com.example.gtercn.car.mall.view;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.mall.view.custom_view.FlowLayout;
import com.example.gtercn.car.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/31.
 * Used to : 搜索页面
 */
public class SearchActivity extends BaseActivity {

    private static final String TAG = "SearchActivity";

    @BindView(R.id.iv_back)
    ImageView mBackIv;

    @BindView(R.id.et_search)
    EditText mSearchEt;

    @BindView(R.id.tv_search)
    TextView mSubmitTv;

    @BindView(R.id.fl_hot_search)
    FlowLayout mHotSearchFl;

    @BindView(R.id.fl_history_search)
    FlowLayout mHistorySearchFl;

    @BindView(R.id.ll_clear_history)
    LinearLayout mClearHistoryLl;

    private List<String> hotList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mall_search;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        super.initView();
        hotList = Arrays.asList(getResources().getStringArray(R.array.hot_search));
        for (int i = 0; i < hotList.size(); i++) {
            TextView tv = new TextView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 20, 20, 20);
            tv.setLayoutParams(lp);
            tv.setTextSize(12);
            tv.setText(hotList.get(i));
            tv.setPadding(40, 10, 40, 10);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(getResources().getColor(R.color.text_common_color));
            tv.setBackground(getResources().getDrawable(R.drawable.tag_bg_f2));
            mHotSearchFl.addView(tv);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_search, R.id.ll_clear_history})
    void searchClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                submitSearch();
                break;
            case R.id.ll_clear_history:
                Toast.makeText(this, "清空历史记录", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void submitSearch() {
        OkHttpUtils
                .get()
                .url(ApiManager.URL_DO_SEARCH)
                .addParams("token", Constants.TOKEN)
                .addParams("device_token", "0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: response is " + response);
                    }
                });

    }
}
