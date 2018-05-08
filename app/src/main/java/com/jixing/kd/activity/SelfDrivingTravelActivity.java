package com.jixing.kd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.jixing.kd.R;
import com.jixing.kd.adapter.SelfTravelAdapter;
import com.jixing.kd.api.ApiManager;
import com.jixing.kd.base.BaseActivity;
import com.jixing.kd.base.CarApplication;
import com.jixing.kd.bean.SelfTravelBean;
import com.jixing.kd.bean.User;
import com.jixing.kd.interfaces.ResponseJSONObjectListener;
import com.jixing.kd.net.THttpOpenHelper;
import com.jixing.kd.utils.SharedPreferenceHelper;
import com.jixing.kd.utils.TAppUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 自驾游
 */
public class SelfDrivingTravelActivity extends BaseActivity implements View.OnClickListener, ResponseJSONObjectListener {
    private static final String TAG = "SelfDrivingTravelActivity";
    private static final int SELF = 52617468;
    private TextView mIssueTextView;
    private TextView mCallbackText;
    private ListView mListView;
    private CarApplication mApplication;
    private List<SelfTravelBean.ResultBean> list;
    private User mUser;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_driving_travel);
        mApplication = (CarApplication) getApplication();
        mCallbackText = (TextView) findViewById(R.id.self_driving_travel_callback_textview);
        mListView = (ListView) findViewById(R.id.self_driving_travel_listview);
        mIssueTextView = (TextView) findViewById(R.id.self_driving_travel_issue_textview);
        view = findViewById(R.id.activity_self_driving_include);
        mIssueTextView.setOnClickListener(this);
        mCallbackText.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();

        mUser = mApplication.getUser();
        getData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (list != null) {
            list.clear();
        }
    }

    //请求数据
    private void getData() {
        showDialog(getString(R.string.dialog_data));
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("city_code", SharedPreferenceHelper.getValue(ApiManager.CITY_CODE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (mUser == null) {
            String url = ApiManager.URL_SELF_TRAVEL;
            THttpOpenHelper.newInstance().requestJsonObjectPost(url, jsonObject, this, SELF, TAG);
        } else {
            String url = ApiManager.URL_SELF_TRAVEL + "?token=" + mUser.getResult().getToken();
            THttpOpenHelper.newInstance().requestJsonObjectPost(url, jsonObject, this, SELF, TAG);
        }
    }


    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.self_driving_travel_callback_textview:
                finish();
                break;
            case R.id.self_driving_travel_issue_textview:

                mUser = mApplication.getUser();
                if (mUser != null) {
                    Intent intent = new Intent(SelfDrivingTravelActivity.this, SelfDrivingIssueActivity.class);
                    startActivity(intent);
                } else {
                    showToastMsg(getString(R.string.null_login));
                    Intent intent = new Intent(SelfDrivingTravelActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onSuccessResponse(JSONObject response, int type) {
        closeDialog();
        if (response != null) {
            if (response.has("err_code")) {
                try {
                    String code = response.getString("err_code");
                    TAppUtils.logout(mApplication, code);
                    if (TextUtils.equals(code, "0")) {
                        Gson gson = new Gson();
                        JSONArray obj2 = response.getJSONArray("result");
                        list = gson.fromJson(obj2.toString(),
                                new TypeToken<List<SelfTravelBean.ResultBean>>() {
                                }.getType());
                        if (list != null && list.size() != 0) {
                            view.setVisibility(View.GONE);
                            SelfTravelAdapter selfTravelAdapter = new SelfTravelAdapter(this, list);
                            Parcelable state = mListView.onSaveInstanceState();
                            mListView.setAdapter(selfTravelAdapter);
                            if (state != null) {
                                mListView.onRestoreInstanceState(state);
                            }

                        } else {
                            view.setVisibility(View.VISIBLE);
                        }

                    } else {
                        closeDialog();
                        view.setVisibility(View.VISIBLE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }
        } else {
            //请求返回的为Null；
        }
    }


    @Override
    public void onErrorResponse(VolleyError error, int type) {
        closeDialog();
    }
}
