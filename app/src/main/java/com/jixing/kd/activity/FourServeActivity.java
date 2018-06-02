package com.jixing.kd.activity;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jixing.kd.R;
import com.jixing.kd.adapter.CarWashDistanceAdapter;
import com.jixing.kd.api.ApiManager;
import com.jixing.kd.base.BaseActivity;
import com.jixing.kd.bean.FourServiceBean;
import com.jixing.kd.bean.FourServiceEntity;
import com.jixing.kd.bean.RescueCategoryBean;
import com.jixing.kd.bean.RescueListBean;
import com.jixing.kd.db.RescueService;
import com.jixing.kd.loader.FourServiceListCursorLoader;
import com.jixing.kd.net.THttpOpenHelper;
import com.jixing.kd.utils.Constants;
import com.jixing.kd.utils.SortUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;


/**
 * 四服务类
 */
public class FourServeActivity extends BaseActivity implements View.OnClickListener,
        LoaderManager.LoaderCallbacks<List<FourServiceBean>> {
    private static final String TAG = "FourServeActivity";
    private TextView mToolText;
    private View mToolBackText;
    private ListView mListView;
    private View mGradeLayout;
    private View mDistanceLayout;
    private PopupWindow popupWindow2;
    private PopupWindow popupWindow3;
    private String type;
    private View view;

    private ArrayList<FourServiceBean> arrayListAll = new ArrayList<>();
    private ArrayList<FourServiceBean> arrayList3 = new ArrayList<>();
    private CarWashDistanceAdapter carWashDistanceAdapter;
    private ImageView mDistanceArrowImageView;
    private ImageView mGradeArrowImageView;
    private TextView mDistanceArrowTextView;
    private TextView mGradeArrowTextView;

    private int start = 0;
    private int count = 20;

    private List<FourServiceEntity.ResultBean> fourServiceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash);
        type = getIntent().getStringExtra("type");
        mToolBackText = findViewById(R.id.four_activity_toolbar_backcall_relativelayout);
        mToolText = (TextView) findViewById(R.id.activity_car_wash_toolbar_textview);
        mListView = (ListView) findViewById(R.id.activity_car_wash_listview);
        mDistanceLayout = findViewById(R.id.activity_car_wash_distance_relativelayout);
        mGradeLayout = findViewById(R.id.activity_car_wash_grade_relativelayout);
        mDistanceArrowImageView = (ImageView) findViewById(R.id.activity_car_wash_distance_arrow_Imageview);
        mGradeArrowImageView = (ImageView) findViewById(R.id.activity_car_wash_grade_arrow_Imageview);
        mDistanceArrowTextView = (TextView) findViewById(R.id.activity_car_wash_distance_textview);
        mGradeArrowTextView = (TextView) findViewById(R.id.activity_car_wash_grade_textview);
        view = findViewById(R.id.activity_four_serve_include);
        mGradeLayout.setOnClickListener(this);
        mDistanceLayout.setOnClickListener(this);
        mToolBackText.setOnClickListener(this);
        if (type.equals("4100")) {
            mToolText.setText("洗车商家列表");
        } else if (type.equals("7100")) {
            mToolText.setText("轮胎商家列表");
        } else if (type.equals("6100")) {
            mToolText.setText("保养商家列表");
        } else if (type.equals("5100")) {
            mToolText.setText("修车商家列表");
        }
//        getLoaderManager().initLoader(0, null, this);
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
            case R.id.four_activity_toolbar_backcall_relativelayout:
                finish();
                break;
            case R.id.activity_car_wash_distance_relativelayout:
                showPopupWindow(v);
                break;
            case R.id.activity_car_wash_grade_relativelayout:
                showPopupWindow(v);
                break;
            case R.id.popupwindow_grade1_textview:
                if (arrayListAll != null) {
                    if (fourServiceList.size() == 0) {
                        popupWindow2.dismiss();
                        view.setVisibility(View.VISIBLE);
                    } else {
                        view.setVisibility(View.GONE);
                        fourServiceList = SortUtil.HighToLow(fourServiceList);
                        carWashDistanceAdapter.refresh(fourServiceList);
                        popupWindow2.dismiss();
                    }
                } else {
                    view.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.popupwindow_grade2_textview:
                if (fourServiceList != null) {
                    if (fourServiceList.size() == 0) {
                        popupWindow2.dismiss();
                        view.setVisibility(View.VISIBLE);
                    } else {
                        fourServiceList = SortUtil.LowToHigh(fourServiceList);
                        carWashDistanceAdapter.refresh(fourServiceList);
                        popupWindow2.dismiss();
                        view.setVisibility(View.GONE);
                    }
                } else {
                    view.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.popupwindow_distance_nearby_textview:
                popupWindow3.dismiss();
                if (fourServiceList != null) {
                    if (fourServiceList.size() == 0) {
                        fourServiceList.clear();
                        view.setVisibility(View.VISIBLE);
                    } else {
//                        fourServiceList = (List<FourServiceEntity.ResultBean>) fourServiceList.clone();
//                        if (fourServiceList.size() == 0) {
//                            view.setVisibility(View.VISIBLE);
//                            showToastMsg("没有店家");
//                            carWashDistanceAdapter.refresh(fourServiceList);
//                        } else {
//                            carWashDistanceAdapter.refresh(fourServiceList);
//                            view.setVisibility(View.GONE);
//                        }
                    }

                } else {
                    view.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.popupwindow_distance1_textview:
                popupWindow3.dismiss();
                if (fourServiceList != null) {
                    if (fourServiceList.size() == 0) {
                        fourServiceList.clear();
                        view.setVisibility(View.VISIBLE);
                    } else {
//                        fourServiceList = (List<FourServiceEntity.ResultBean>) fourServiceList.clone();
//                        for (int i = fourServiceList.size() - 1; i >= 0; i--) {
//                            if (fourServiceList.get(i).getDistance() > 1 * 1000) {
//                                fourServiceList.remove(i);
//                            }
//                        }
//                        if (fourServiceList.size() == 0) {
//                            view.setVisibility(View.VISIBLE);
//                            showToastMsg("1Km之内没有店家");
//                            carWashDistanceAdapter.refresh(fourServiceList);
//                        } else {
//                            carWashDistanceAdapter.refresh(fourServiceList);
//                            view.setVisibility(View.GONE);
//                        }
                    }

                } else {
                    view.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.popupwindow_distance3_textview:
                popupWindow3.dismiss();
                if (fourServiceList != null) {
                    if (fourServiceList.size() == 0) {
                        fourServiceList.clear();
                        view.setVisibility(View.VISIBLE);
                    } else {
//                        fourServiceList = (List<FourServiceEntity.ResultBean>) fourServiceList.clone();
//                        for (int i = fourServiceList.size() - 1; i >= 0; i--) {
//
//                            if (fourServiceList.get(i).getDistance() > 3 * 1000) {
//                                fourServiceList.remove(i);
//                            }
//                        }
//                        if (fourServiceList.size() == 0) {
//                            showToastMsg("3Km之内没有店家");
//                            carWashDistanceAdapter.refresh(fourServiceList);
//                            view.setVisibility(View.VISIBLE);
//                        } else {
//                            carWashDistanceAdapter.refresh(fourServiceList);
//                            view.setVisibility(View.GONE);
//                        }
                    }

                } else {
                    view.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.popupwindow_distance5_textview:
                popupWindow3.dismiss();
                if (fourServiceList != null) {
                    if (fourServiceList.size() == 0) {
                        fourServiceList.clear();
                        view.setVisibility(View.VISIBLE);
                    } else {
//                        fourServiceList = fourServiceList.clone();
//                        for (int i = fourServiceList.size() - 1; i >= 0; i--) {
//
//                            if (arrayListAll.get(i).getDistance() > 5 * 1000) {
//                                arrayListAll.remove(i);
//                            }
//                        }
//                        if (arrayListAll.size() == 0) {
//                            showToastMsg("5Km之内没有店家");
//                            carWashDistanceAdapter.refresh(fourServiceList);
//                            view.setVisibility(View.VISIBLE);
//
//                        } else {
//                            carWashDistanceAdapter.refresh(fourServiceList);
//                            view.setVisibility(View.GONE);
//
//                        }
                    }


                } else {
                    view.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.popupwindow_distance10_textview:
                popupWindow3.dismiss();
                if (fourServiceList != null) {
                    if (fourServiceList.size() == 0) {
                        fourServiceList.clear();
                        view.setVisibility(View.VISIBLE);
                    } else {
//                        arrayListAll = (ArrayList<FourServiceBean>) fourServiceList.clone();
//                        for (int i = fourServiceList.size() - 1; i >= 0; i--) {
//
//                            if (fourServiceList.get(i).getDistance() > 10 * 1000) {
//                                arrayListAll.remove(i);
//                            }
//                        }

//                        if (fourServiceList.size() == 0) {
//                            showToastMsg("10Km之内没有店家");
//                            carWashDistanceAdapter.refresh(fourServiceList);
//                            view.setVisibility(View.VISIBLE);
//
//                        } else {
//                            carWashDistanceAdapter.refresh(fourServiceList);
//                            view.setVisibility(View.GONE);
//                        }
                    }


                } else {
                    view.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public Loader<List<FourServiceBean>> onCreateLoader(int id, Bundle args) {

        return new FourServiceListCursorLoader(this, Integer.valueOf(type));
    }

    @Override
    public void onLoadFinished(Loader<List<FourServiceBean>> loader, List<FourServiceBean> data) {
        if (data.size() > 0) {
            view.setVisibility(View.GONE);
            synchronized (arrayListAll) {
                arrayListAll.clear();
                arrayListAll.addAll(data);
                arrayList3 = (ArrayList<FourServiceBean>) arrayListAll.clone();
//                carWashDistanceAdapter = new CarWashDistanceAdapter(FourServeActivity.this, arrayListAll);
                carWashDistanceAdapter.setScrollState(false);
                mListView.setAdapter(carWashDistanceAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(FourServeActivity.this, FourServeDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("shop_id", arrayListAll.get(position).getId());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        switch (scrollState) {

                            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE: {

                                carWashDistanceAdapter.setScrollState(false);

                                int count = view.getChildCount();
                                for (int i = 0; i < count; i++) {

                                    ImageView iv_show = (ImageView) view.getChildAt(i).findViewById(R.id.car_wash_item_imageview);
                                    if (!iv_show.getTag().equals("1")) {

                                        if (!iv_show.getTag().equals("2")) {

                                            String image_url = iv_show.getTag().toString();
                                            if (image_url != null) {
                                                THttpOpenHelper.newInstance().setImageBitmap(iv_show, image_url, (int) (100 * density), (int) (75 * density),
                                                        R.drawable.icon_default,
                                                        R.drawable.icon_default);

                                                iv_show.setTag("1");
                                            } else {
                                                iv_show.setBackgroundResource(R.drawable.icon_default);
                                                iv_show.setTag("1");
                                            }
                                        } else {
                                            iv_show.setBackgroundResource(R.drawable.icon_default);
                                            iv_show.setTag("1");
                                        }
                                    }
                                }
                                break;
                            }

                            case AbsListView.OnScrollListener.SCROLL_STATE_FLING: {
                                carWashDistanceAdapter.setScrollState(true);
                                break;
                            }

                            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL: {

                                carWashDistanceAdapter.setScrollState(true);
                                break;
                            }
                        }
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                    }
                });
            }
        } else {

            view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<FourServiceBean>> loader) {
        arrayListAll.clear();

    }

    private void showPopupWindow(View v) {
        switch (v.getId()) {
            case R.id.activity_car_wash_grade_relativelayout:
                View view = LayoutInflater.from(FourServeActivity.this).inflate(R.layout.popupwindow_grade, null);
                TextView textView1 = (TextView) view.findViewById(R.id.popupwindow_grade1_textview);
                TextView textView2 = (TextView) view.findViewById(R.id.popupwindow_grade2_textview);
                textView1.setOnClickListener(this);
                textView2.setOnClickListener(this);
                popupWindow2 = new PopupWindow(view,
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                popupWindow2.setTouchable(true);
                popupWindow2.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
                popupWindow2.showAsDropDown(v);

                break;

            case R.id.activity_car_wash_distance_relativelayout:
                View view2 = LayoutInflater.from(FourServeActivity.this).inflate(R.layout.popupwindow_distance, null);
                TextView textView3 = (TextView) view2.findViewById(R.id.popupwindow_distance_nearby_textview);
                TextView textView4 = (TextView) view2.findViewById(R.id.popupwindow_distance1_textview);
                TextView textView5 = (TextView) view2.findViewById(R.id.popupwindow_distance3_textview);
                TextView textView6 = (TextView) view2.findViewById(R.id.popupwindow_distance5_textview);
                TextView textView7 = (TextView) view2.findViewById(R.id.popupwindow_distance10_textview);
                textView3.setOnClickListener(this);
                textView4.setOnClickListener(this);
                textView5.setOnClickListener(this);
                textView6.setOnClickListener(this);
                textView7.setOnClickListener(this);
                popupWindow3 = new PopupWindow(view2,
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
                popupWindow3.setTouchable(true);
                popupWindow3.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
                popupWindow3.showAsDropDown(v);
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (popupWindow2 != null && popupWindow2.isShowing()) {
            mGradeArrowImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_shop_list_shang_arrow));
            mGradeArrowTextView.setTextColor(ContextCompat.getColor(mContext, R.color.list_blue));
        } else {
            mGradeArrowImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_shop_list_xia_arrow));
            mGradeArrowTextView.setTextColor(ContextCompat.getColor(mContext, R.color.text_title_color));
        }
        if (popupWindow3 != null && popupWindow3.isShowing()) {
            mDistanceArrowImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_shop_list_shang_arrow));
            mDistanceArrowTextView.setTextColor(ContextCompat.getColor(mContext, R.color.list_blue));
        } else {
            mDistanceArrowImageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_shop_list_xia_arrow));
            mDistanceArrowTextView.setTextColor(ContextCompat.getColor(mContext, R.color.text_title_color));
        }
    }

    private void setUI() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FourServeActivity.this, FourServeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("shop_id", fourServiceList.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {

                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE: {

                        carWashDistanceAdapter.setScrollState(false);

                        int count = view.getChildCount();
                        for (int i = 0; i < count; i++) {

                            ImageView iv_show = (ImageView) view.getChildAt(i).findViewById(R.id.car_wash_item_imageview);
                            if (!iv_show.getTag().equals("1")) {

                                if (!iv_show.getTag().equals("2")) {

                                    String image_url = iv_show.getTag().toString();
                                    if (image_url != null) {
                                        THttpOpenHelper.newInstance().setImageBitmap(iv_show, image_url, (int) (100 * density), (int) (75 * density),
                                                R.drawable.icon_default,
                                                R.drawable.icon_default);

                                        iv_show.setTag("1");
                                    } else {
                                        iv_show.setBackgroundResource(R.drawable.icon_default);
                                        iv_show.setTag("1");
                                    }
                                } else {
                                    iv_show.setBackgroundResource(R.drawable.icon_default);
                                    iv_show.setTag("1");
                                }
                            }
                        }
                        break;
                    }

                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING: {
                        carWashDistanceAdapter.setScrollState(true);
                        break;
                    }

                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL: {

                        carWashDistanceAdapter.setScrollState(true);
                        break;
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void getData() {
        JSONObject params = new JSONObject();
        try {
            params.put("city_code", Constants.CITY_CODE);
            params.put("begin_number", start + "");
            params.put("over_number", count + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.postString()
                .url(ApiManager.URL_FOUR_SERVE)
                .mediaType(MediaType.parse("application/json;charset=utf8"))
                .content(params.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: e is " + e.toString());
                        showToastMsg("网络异常，请稍后重试!");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "response is " + response);
                        if (response != null) {
                            FourServiceEntity entity = new Gson().fromJson(response, FourServiceEntity.class);
                            Log.e(TAG, "onResponse: entity" + entity);
                            if (entity != null && "0".equals(entity.getErr_code())) {
                                fourServiceList = entity.getResult();
                                if (fourServiceList != null && fourServiceList.size() > 0) {
                                    carWashDistanceAdapter = new CarWashDistanceAdapter(FourServeActivity.this, fourServiceList);
                                    mListView.setAdapter(carWashDistanceAdapter);
                                    setUI();
                                } else {
                                    view.setVisibility(View.VISIBLE);
                                }
                            } else {
                                Toast.makeText(FourServeActivity.this, "网络异常，稍后重试", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


}
