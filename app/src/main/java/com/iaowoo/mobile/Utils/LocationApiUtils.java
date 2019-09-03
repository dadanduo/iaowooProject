package com.iaowoo.mobile.Utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Application.ZApplication;

/**
 * ////////////////////////
 * //  ┏┓　　　┏┓///////////
 * //┏┛┻━━━┛┻┓ ////////////
 * //┃　　　　　　　┃     ////
 * //┃　　　━　　　┃     ////
 * //┃　┳┛　┗┳　┃       /////
 * //┃　　　　　　　┃     ////
 * //┃　　　┻　　　┃         //
 * //┃　　　　　　　┃        ///
 * //┗━┓　　　┏━┛           ///
 * //    ┃　　　┃   神兽保佑  ///
 * //    ┃　　　┃   代码无BUG！///
 * //    ┃　　　┗━━━┓     ///
 * //    ┃　　　　　　　┣┓ ///
 * //    ┃　　　　　　　┏┛ ///
 * //    ┗┓┓┏━┳┓┏┛      ///
 * //      ┃┫┫　┃┫┫     ///
 * ///////////////////////
 *
 * @author ${chenda}
 * @version V1.0
 * @Description: ${todo}(系统定位)
 * @date 2018/8/9
 * @email ${18011009889@163.com}
 */
public class LocationApiUtils {
    private Context context;
    public LocationApiUtils() {
        context=ZApplication.getContext();
    }


    private LocationManager locationManager = null;

    private LocationListener locationListener=null;

    private LocationCallBack locationCallBack=null;


    public interface LocationCallBack {
        void location(double la, double lo);
    }

    public void setInterface( LocationCallBack locationCallBack){
        this.locationCallBack=locationCallBack;
    }

    public void instance(){
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        boolean ok = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ok) {//开了定位服务
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // 没有权限，申请权限。
//                        Toast.makeText(context, "没有权限", Toast.LENGTH_SHORT).show();
            } else {
                // 有权限了，去放肆吧。
//                        Toast.makeText(context, "有权限", Toast.LENGTH_SHORT).show();
            }
        } else {
//            ToastUtilsAll.getInstance().showShortToast("系统检测到未开启GPS定位服务");
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
            context.startActivity(intent);
        }
    }
    /**
     * 开始定位
     */
    @SuppressLint("MissingPermission")
    public void startLo() {
        instance();
        locationListener=  new LocationListener() {
            //当位置改变的时候调用
            @Override
            public void onLocationChanged(Location location) {
                //经度
                double longitude = location.getLongitude();
                //纬度
                double latitude = location.getLatitude();
                //海拔
                double altitude = location.getAltitude();
                double[] lo_la= CoordinateTransformUtil.wgs84togcj02(longitude,latitude);
                locationCallBack.location(lo_la[1],lo_la[0]);
                LogPrint.printError("经度:==>" + longitude + " \n 纬度==>" + latitude + "\n" + "海拔==>" + altitude);
                LogPrint.printError("经度转换后:==>" + lo_la[0] + " \n 纬度转换后==>" + lo_la[1]+ "\n" + "海拔==>" + altitude);

            }
            //当GPS状态发生改变的时候调用
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                switch (status) {
                    case LocationProvider.AVAILABLE:
                        LogPrint.printError("当前GPS为可用状态!");
                        break;
                    case LocationProvider.OUT_OF_SERVICE:
                        LogPrint.printError("当前GPS不在服务内");
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        LogPrint.printError("当前GPS为暂停服务状态");
                        break;
                }
            }
            //GPS开启的时候调用
            @Override
            public void onProviderEnabled(String provider) {
//                Toast.makeText(context,"GPS开启了",Toast.LENGTH_SHORT).show();
                LogPrint.printError("GPS开启了");
            }
            //GPS关闭的时候调用
            @Override
            public void onProviderDisabled(String provider) {
                stopLo();
                if(locationCallBack!=null) {
                    locationCallBack.location(0.000000, 0.000000);
                    ToastUtilsAll.getInstance().showShortToast("系统检测到未开启GPS定位服务");
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        };
        if(isNetworkConnected(context)){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, locationListener);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, locationListener);
        }
    }/**
     * 是否有网
     * @param context
     * @return
     */
    public  boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    /**
     * 停止定位
     */
    public void stopLo(){
        if(locationManager!=null) {
            if(locationListener!=null) {
                locationManager.removeUpdates(locationListener);
            }
        }else{
            LogPrint.printError("定位对象为空");
        }
    }
}
