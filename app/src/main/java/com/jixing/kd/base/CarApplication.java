package com.jixing.kd.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.jixing.kd.bean.UpdateBean;
import com.jixing.kd.bean.User;
import com.jixing.kd.net.THttpOpenHelper;
import com.jixing.kd.utils.ContextService;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016-5-18.
 * 应用环境:
 */
public class CarApplication extends Application {

    private static final String TAG = CarApplication.class.getSimpleName();

    private static final String FILE = "user";

    public static Context mContext;

    private static Resources mResources;

    private BDLocation mLocationInfo;

    private UpdateBean mUpdateBean;

    private User mUser = null;

    private static THttpOpenHelper mTHttpOpenHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mResources = getResources();
        ContextService.getInstance().setContext(mContext);
        mTHttpOpenHelper = THttpOpenHelper.newInstance();
        Config.DEBUG = true;
        SDKInitializer.initialize(getApplicationContext());
        initOkHttpClient();
        // TODO: 2017/1/10 需要替换成自己申请的 appId和secret

        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx62824c95622f2f8d", "351092bd91f7bd01363aa374ed39a473");
        PlatformConfig.setSinaWeibo("1923305823", "09c6cb61141e9b423e68398fa5d4937d", "www.shunjiatianxia.com");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    private void initOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();

        OkHttpUtils.initClient(client);
    }

    public void setUpdateBean(UpdateBean bean) {
        mUpdateBean = bean;
    }

    public UpdateBean getUpdateBean() {
        return mUpdateBean;
    }

    public BDLocation getLocationInfo() {
        return mLocationInfo;
    }

    public void setLocationInfo(BDLocation mLocationInfo) {
        this.mLocationInfo = mLocationInfo;
    }


    public static Context getContext() {
        return mContext;
    }

    public static Resources getResource() {
        return mResources;
    }

    public void setUser(User user) {
        mUser = user;
        writeUser(mUser, FILE);
        if (mTHttpOpenHelper != null) {
            mTHttpOpenHelper.setUser(user);
        }
    }

    public void resetUser() {
        mUser = null;
        deleteCacheFile(FILE);
        if (mTHttpOpenHelper != null) {
            mTHttpOpenHelper.resetUser();
        }
    }

    public User getUser() {
        if (mUser != null) {
            return mUser;
        } else {
            mUser = readUser(FILE);
            return mUser;
        }
    }

    private void writeUser(User user, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput(file, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private User readUser(String file) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            File fs = getFileStreamPath(file);
            if (!fs.exists()) {
                return null;
            } else {
                fis = openFileInput(file);
                ois = new ObjectInputStream(fis);
                return (User) ois.readObject();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fis != null)
                    fis.close();

                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCacheFile(String file) {
        File fs = getFileStreamPath(file);
        if (fs.exists()) {
            fs.delete();
        }
    }

}
