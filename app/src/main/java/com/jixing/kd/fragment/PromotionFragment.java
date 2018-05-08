package com.jixing.kd.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.jixing.kd.R;
import com.jixing.kd.adapter.PromotionAdapter;
import com.jixing.kd.api.ApiManager;
import com.jixing.kd.base.BaseFragment;
import com.jixing.kd.base.CarApplication;
import com.jixing.kd.bean.PromotionBean;
import com.jixing.kd.bean.User;
import com.jixing.kd.interfaces.ICityCodeChangeListener;
import com.jixing.kd.interfaces.ResponseJSONObjectListener;
import com.jixing.kd.interfaces.ResponseStringListener;
import com.jixing.kd.net.THttpOpenHelper;
import com.jixing.kd.utils.SharedPreferenceHelper;
import com.jixing.kd.utils.TAppUtils;
import com.jixing.kd.widget.DividerItemDecoration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 发现的促销的Fragment
 */
public class PromotionFragment extends BaseFragment implements ResponseStringListener, ResponseJSONObjectListener, ICityCodeChangeListener {
    private static final String TAG = "PromotionFragment";
    private static final int PROMOTION = 1916906;
    private static final int SEARCH = 22824792;
    private RecyclerView mRecyclerView;
    private PromotionAdapter mAdapter;
    private PromotionBean mPromotionBean;
    private EditText mSearchEdittext;
    private ImageView mSearchImage;
    private ArrayList<PromotionBean.ResultBean> list = new ArrayList<>();
    private ArrayList<PromotionBean.ResultBean> list2 = new ArrayList<>();
    private CarApplication mApplication;
    private User mUser;
    private RelativeLayout mCustomLayout;
    private boolean isFrist = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_promotion, container, false);
        mApplication = (CarApplication) getActivity().getApplication();
        mUser = mApplication.getUser();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_promotion_recyclerview);
        mSearchEdittext = (EditText) view.findViewById(R.id.fragment_promotion_edittext);
        mSearchImage = (ImageView) view.findViewById(R.id.fragment_promotion_search_image);
        mCustomLayout = (RelativeLayout) view.findViewById(R.id.fragment_promotion_relativelayout);
        //搜索按钮
        mSearchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                showDialog(getResources().getString(R.string.dialog_data));
                if (mUser != null && mUser.getResult().getToken() != null) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("keyword", mSearchEdittext.getText().toString());
                    map.put("token", mUser.getResult().getToken());
                    map.put("city_code", SharedPreferenceHelper.getValue(ApiManager.CITY_CODE));
                    ApiManager.PromotionSearch(map, null, PromotionFragment.this, SEARCH, TAG);
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("keyword", mSearchEdittext.getText().toString());
                    map.put("city_code", SharedPreferenceHelper.getValue(ApiManager.CITY_CODE));
                    ApiManager.PromotionSearch(map, null, PromotionFragment.this, SEARCH, TAG);
                }
            }
        });

        //键盘回车键
        mSearchEdittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                    //TODO回车键按下时要执行的操作
                    showDialog(getResources().getString(R.string.dialog_data));
                    if (mUser != null && mUser.getResult().getToken() != null) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("keyword", mSearchEdittext.getText().toString());
                        map.put("token", mUser.getResult().getToken());
                        map.put("city_code", SharedPreferenceHelper.getValue(ApiManager.CITY_CODE));
                        ApiManager.PromotionSearch(map, null, PromotionFragment.this, SEARCH, TAG);
                    } else {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("keyword", mSearchEdittext.getText().toString());
                        map.put("city_code", SharedPreferenceHelper.getValue(ApiManager.CITY_CODE));
                        ApiManager.PromotionSearch(map, null, PromotionFragment.this, SEARCH, TAG);
                    }
                }
                return false;
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        return view;
    }

    private void getData() {
        mUser = mApplication.getUser();
        //   showDialog(getString(R.string.dialog_data));
        String url = ApiManager.URL_PROMOTION;
        if (mUser != null && mUser.getResult().getToken() != null) {
            HashMap map = new HashMap();
            map.put("token", mUser.getResult().getToken());
            map.put("city_code", SharedPreferenceHelper.getValue(ApiManager.CITY_CODE));
            THttpOpenHelper.newInstance().requestFormDataFilePost(url, map, null, PromotionFragment.this, PROMOTION, TAG);
        } else {
            HashMap map = new HashMap();
            map.put("city_code", SharedPreferenceHelper.getValue(ApiManager.CITY_CODE));
            THttpOpenHelper.newInstance().requestFormDataFilePost(url, map, null, PromotionFragment.this, PROMOTION, TAG);
        }

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //pause

        } else {
            //resume
            if (list2.size() != 0) {
                mCustomLayout.setVisibility(View.GONE);
                mAdapter.refresh(list2);
                mSearchEdittext.setText("");
            } else {
                if (isFrist == false) {
                    getData();
                }
            }

        }
    }

    @Override
    protected void onExecuteSuccess(String result, int type) {

    }

    @Override
    protected void onExecuteFailure(int type) {

    }


    @Override
    public void onErrorResponse(VolleyError error, int type) {
        closeDialog();
        showToastMsg(error);
    }

    @Override
    public void onSuccessResponse(String response, int type) {
        closeDialog();
        switch (type) {
            case SEARCH:
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        if (obj.has("err_code")) {
                            String returnCode = obj.getString("err_code");
                            TAppUtils.logout(mApplication, returnCode);
                            if (TextUtils.equals(returnCode, "0")) {
                                JSONArray obj2 = obj.getJSONArray("result");
                                String message = obj.getString("message");
                                isFrist = false;
                                if (message.equals("null") == true) {
                                    mCustomLayout.setVisibility(View.GONE);
                                    Gson gson = new Gson();
                                    list.clear();
                                    list = gson.fromJson(obj2.toString(),
                                            new TypeToken<List<PromotionBean.ResultBean>>() {
                                            }.getType());

                                    mAdapter.refresh(list);

                                } else {
                                    mCustomLayout.setVisibility(View.VISIBLE);

                                    list.clear();
                                    mAdapter.refresh(list);
                                }

                            } else {

                                mCustomLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PROMOTION:
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        if (obj.has("err_code")) {
                            String returnCode = obj.getString("err_code");
                            TAppUtils.logout(mApplication, returnCode);
                            if (TextUtils.equals(returnCode, "0")) {
                                JSONArray obj2 = obj.getJSONArray("result");
                                String message = obj.getString("message");
                                Log.i(TAG, "----------------length----->" + obj2.length());
                                if (obj2.length() == 0) {
                                    mCustomLayout.setVisibility(View.VISIBLE);
                                } else {
                                    mCustomLayout.setVisibility(View.GONE);
                                }
                                Gson gson = new Gson();
                                list.clear();
                                list = gson.fromJson(obj2.toString(),
                                        new TypeToken<List<PromotionBean.ResultBean>>() {
                                        }.getType());
                                list2 = (ArrayList<PromotionBean.ResultBean>) list.clone();
                                mAdapter = new PromotionAdapter(getContext(), list);
                                mRecyclerView.setAdapter(mAdapter);


                            } else {

                                mCustomLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    public void onSuccessResponse(JSONObject response, int type) {

    }

    @Override
    public void updateCityCode() {
        getData();
    }
}
