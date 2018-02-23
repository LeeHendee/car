package com.example.gtercn.car.mall.view;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtercn.car.R;
import com.example.gtercn.car.api.ApiManager;
import com.example.gtercn.car.mall.entity.ResultEntity;
import com.example.gtercn.car.mall.entity.SearchPageEntity;
import com.example.gtercn.car.mall.view.custom_view.FlowLayout;
import com.example.gtercn.car.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

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

    private List<String> historyList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mall_search;
    }

    @SuppressLint("NewApi")
    private void initLabel(List<String> list, FlowLayout fl) {
        for (int i = 0; i < list.size(); i++) {
            final TextView tv = new TextView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 20, 20, 20);
            tv.setLayoutParams(lp);
            tv.setTextSize(12);
            tv.setText(list.get(i));
            tv.setPadding(40, 10, 40, 10);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(getResources().getColor(R.color.text_common_color));
            tv.setBackground(getResources().getDrawable(R.drawable.tag_bg_f2));
            fl.addView(tv);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSearchEt.setText(tv.getText());
                    mSearchEt.setSelection(tv.length());
                }
            });
        }
    }

    @Override
    protected void initData() {
        super.initData();
        OkHttpUtils
                .get()
                .url(ApiManager.URL_SEARCH_PAGE)
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
                        Log.e(TAG, "initData: response is " + response);
                        if (response != null) {
                            Gson gson = new Gson();
                            SearchPageEntity entity = gson.fromJson(response, SearchPageEntity.class);
                            if (entity != null && entity.getErr_code().equals("0")) {
                                hotList = entity.getResult().getHot_search();
                                historyList = entity.getResult().getSearch_list();
                                initLabel(hotList, mHotSearchFl);
                                initLabel(historyList, mHistorySearchFl);
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.iv_back, R.id.tv_search, R.id.ll_clear_history})
    void searchClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                goSearch(mSearchEt.getText().toString().trim());
                break;
            case R.id.ll_clear_history:
                clearHistory();
                break;
        }
    }

    private void clearHistory() {
        OkHttpUtils
                .post()
                .url(ApiManager.URL_HISTORY_CLEAR)
                .addParams("device_token", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "clearHistory: response is " + response);
                        if (response != null) {
                            Gson gson = new Gson();
                            ResultEntity entity = gson.fromJson(response, ResultEntity.class);
                            if (entity != null && entity.getErr_code().equals("0")) {
                                Toast.makeText(SearchActivity.this, entity.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goSearch(String s) {
        addInHistoryDB(s);
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra("searchContent", s);
        startActivity(intent);
    }

    private void addInHistoryDB(String s) {
        JSONObject params = new JSONObject();
        try {
            params.put("search_tag", s);
            params.put("device_token", "1");
            params.put("device_type", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = ApiManager.URL_ADD_HISTORY + "?token=" + Constants.TOKEN;
        String p = params.toString();
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json;charset=utf8"))
                .content(p)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "addToHistory: response is " + response);
                    }
                });
    }
}
