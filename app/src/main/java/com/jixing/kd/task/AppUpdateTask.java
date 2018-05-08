package com.jixing.kd.task;

import android.content.Intent;
import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.jixing.kd.api.ApiManager;
import com.jixing.kd.base.CarApplication;
import com.jixing.kd.bean.UpdateBean;
import com.jixing.kd.interfaces.ResponseJSONObjectListener;
import com.jixing.kd.net.THttpOpenHelper;
import com.jixing.kd.update.UpdateUtils;
import com.jixing.kd.utils.ContextService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016-7-25.
 * 更新任务
 */
public class AppUpdateTask implements Runnable, ResponseJSONObjectListener {
    private static final String TAG = AppUpdateTask.class.getSimpleName();
    private CarApplication mApplication;

    public AppUpdateTask(CarApplication application) {
        mApplication = application;
    }

    @Override
    public void run() {
        String url = ApiManager.URL_TIMESTAMP + "?system_bj=1";
        JSONObject params = new JSONObject();
        try {
            params.put("system_bj", "1");
            THttpOpenHelper.newInstance().requestJsonObjectPost(url, params, this, 1, "appUpdate");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, int type) {

    }

    @Override
    public void onSuccessResponse(JSONObject response, int type) {
        if (response != null) {
            try {
                if (response.has("err_code")) {
                    String code = response.getString("err_code");
                    if (TextUtils.equals(code, "0")) {
                        JSONObject subObject = response.getJSONObject("result");
                        if (subObject.toString() != null) {
                            Gson gson = new Gson();
                            UpdateBean bean = gson.fromJson(subObject.toString(), new TypeToken<UpdateBean>() {
                            }.getType());
                            if (bean != null) {
                                mApplication.setUpdateBean(bean);
                                int currentVersionCode = UpdateUtils.getVerCode(mApplication.getApplicationContext());
                                int minCode = bean.getMin_code();
                                if (minCode > currentVersionCode) {
                                    sendBroadCast(bean);
                                } else {
                                }
                            }
                        }
                    } else {
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendBroadCast(UpdateBean bean) {
        /**
         * 发送更新 广播
         */
        Intent intent = new Intent(UpdateUtils.APP_FORCE);
        intent.putExtra("bean", bean);
        ContextService.getInstance().getContext().sendBroadcast(intent);
    }


}
