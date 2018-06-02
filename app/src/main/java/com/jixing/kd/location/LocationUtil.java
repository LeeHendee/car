package com.jixing.kd.location;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import com.baidu.location.BDLocation;
import com.jixing.kd.base.CarApplication;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by LeeHang on 2018-06-02.
 * useTo：
 */

public class LocationUtil {

    private static LocationUtil instance = new LocationUtil();

    public static double latitude;

    public static double longitude;


    private LocationUtil() {
        LocationManager lm = (LocationManager) CarApplication.getContext().getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);//低精度，如果设置为高精度，依然获取不了location。
        criteria.setAltitudeRequired(false);//不要求海拔
        criteria.setBearingRequired(false);//不要求方位
        criteria.setCostAllowed(true);//允许有花费
        criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗

        String locProvider = lm.getBestProvider(criteria, true);
        Location location = lm.getLastKnownLocation(locProvider);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    public static String getAddressStr() {
        BDLocation bdLocation = AppLocation.newInstance().getBDLocation();
        if (bdLocation == null) {
            return null;
        }
        String province = bdLocation.getProvince();
        String city = bdLocation.getCity();
        String district = bdLocation.getDistrict();
        String address = bdLocation.getAddrStr();
        String street = bdLocation.getStreet();
        return province + city + district + address + street;
    }

    public static String getAddressDesc(Context context) {
        Geocoder geo = new Geocoder(context, Locale.getDefault());
        List<Address> addList = null;
        String s = null;
        try {
            addList = geo.getFromLocation(latitude, longitude, 1);
            if (addList != null && addList.size() > 0) {
                Address a = addList.get(0);
                s = a.getAddressLine(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;

    }


}
