/*
package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;

public class AMAPLocationActivity extends TitleBarActivity {
    private static final String TARGET_ID = "targetId";

    private ListView lvMap;
    private MapView mMapView;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);
        mMapView = findViewById(R.id.rc_ext_amap);
        //在activity执行onCreate时执行mapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

//        lvMap = findViewById(R.id.lv_map);
        setTitle("地理位置");
        initAMap();
    }


    // 启动本activity
    public static void start(Context context, String targetId) {
        Intent intent = new Intent(context, AMAPLocationActivity.class);
        intent.putExtra(TARGET_ID, targetId);
        context.startActivity(intent);
    }

    private void initAMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    private void sendLocation() {
        */
/*if (mData != null && mData.size() > mSelectedPosi) {
            Geo2AddressResultObject.ReverseAddressResult.Poi poi = mData.get(mSelectedPosi);
            Intent data = new Intent();
            LocationData locationData = new LocationData(poi.location.lat, poi.location.lng, poi.title, getMapUrl(poi.location.lat, poi.location.lng));
            data.putExtra("location", locationData);
            setResult(Activity.RESULT_OK, data);
            finish();
        }*//*

    }

    private String getMapUrl(double x, double y) {
        String url = "http://st.map.qq.com/api?size=708*270&center=" + y + "," + x + "&zoom=17&referer=weixin";
        return url;
    }
}
*/
