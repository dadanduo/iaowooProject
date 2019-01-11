///**
// * Project Name:Android_Car_Example
// * File Name:Utils.java
// * Package Name:com.amap.api.car.example
// * Date:2015年4月7日下午3:43:05
// */
//
//package com.iaowoo.mobile.ScottMap;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.alibaba.fastjson.JSON;
//import com.amap.api.maps.AMap;
//import com.amap.api.maps.model.BitmapDescriptorFactory;
//import com.amap.api.maps.model.LatLng;
//import com.amap.api.maps.model.Marker;
//import com.amap.api.maps.model.MarkerOptions;
//import com.iaowoo.mobile.Application.ZApplication;
//import com.iaowoo.mobile.interfaceCallback.MarkerCallBack;
//import com.iaowoo.mobile.Application.ZApplication;
//import com.iaowoo.mobile.R;
//import com.iaowoo.mobile.Ui.classification.Model.DIFFRENT;
//import com.iaowoo.mobile.Ui.classification.Model.NearShops;
//import com.iaowoo.mobile.Utils.AnimationUtils;
//import com.iaowoo.mobile.Utils.LogPrint;
//import com.iaowoo.mobile.Utils.ToastUtilsAll;
//import com.iaowoo.mobile.Utils.XutilsHttp;
//import com.iaowoo.mobile.interfaceCallback.MarkerCallBack;
//import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
//import com.plp.underlying.networkframwork.OkhttpManager;
//import com.plp.underlying.networkframwork.UtilsOkHttpAll;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//
///**
// * ClassName:Utils <br/>
// * Function: TODO ADD FUNCTION. <br/>
// * Reason:   TODO ADD REASON. <br/>
// * Date:     2018年4月2日 下午3:43:05 <br/>
// * @author dada.chen
// * @version
// * @since JDK 1.8
// * @see
// */
//public class Utils {
//
//    private ArrayList<Marker> markers = new ArrayList<Marker>();
//    /**
//     * 移除marker
//     */
//    public  void removeMarkers() {
//        for (Marker marker : markers) {
//            marker.remove();
////            marker.destroy();
//        }
//        markers.clear();
//    }
//
//    /**
//     * 异业联盟点
//     * @param center
//     * @param context
//     * @param amap
//     * @param markerCallBack
//     */
//    public void ParkPointDIAA(LatLng center, final Context context, final AMap amap, final MarkerCallBack markerCallBack)
//    {
//
//        HashMap<String, Object> paramsMap = new HashMap<>();
//        paramsMap.put("pageNum","");
//        paramsMap.put("pageSize","");
//        paramsMap.put("lon",center.longitude);
//        paramsMap.put("lat",center.latitude);
//        paramsMap.put("distance","30000");
//        paramsMap.put("categoryCode","");
//        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(false,UtilsOkHttpAll.LO_LA_NEAR_SHOPS,"ParkPointDIAA",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
//            @Override
//            public void onReqSuccess(Object result) {
//                if(!TextUtils.isEmpty(result.toString())) {
//                    if(!TextUtils.isEmpty(result.toString())){
//                        NearShops nearShops= JSON.parseObject(result.toString(),NearShops.class);
//                        if(nearShops.getCode().equals(OkhttpManager.SUCESS)){
//                            if(nearShops!=null) {
//                                markerCallBack.pushToObject(nearShops);
//                                removeMarkers();
//                                for (int i = 0; i < nearShops.getBody().getContent().getList().size(); i++) {
//                                    LatLng latLng = new LatLng(Double.parseDouble(nearShops.getBody().getContent().getList().get(i).getLat()), Double.parseDouble(nearShops.getBody().getContent().getList().get(i).getLon()));
//                                    MarkerOptions markerOption = new MarkerOptions();
//                                    markerOption.draggable(false);//设置Marker不可拖动
//                                    Bitmap bitmap = custom_Marker(context, nearShops.getBody().getContent().getList().get(i).getMerchantName());
//                                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
//                                    markerOption.position(latLng);
//                                    Marker marker = amap.addMarker(markerOption);
//                                    marker.setTitle(nearShops.getBody().getContent().getList().get(i).getMerchantName());
//                                    marker.setZIndex(2);
//                                    markers.add(marker);
//                                }
//
//                            }
//                        }
//                    }
//                }
//            }
//            @Override
//            public void onReqFailed(String errorMsg) {
//                ToastUtilsAll.getInstance().showNetEoor();
//            }
//            });
//    }
//
//    /**
//     * view 转bitmaps
//     * @param view
//     * @return
//     */
//    private  Bitmap convertViewToBitmap(View view) {
//        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//        view.buildDrawingCache();
//        Bitmap bitmap = view.getDrawingCache();
//        return bitmap;
//    }
//
//    /**
//     * 自定义商户
//     * @param context
//     * @param text_marker
//     * @return
//     */
//    public  Bitmap custom_Marker(Context context,String text_marker)
//    {
//
//        View view = View.inflate(context, R.layout.custom_marker, null);
//
//        ImageView ratting=view.findViewById(R.id.ratting);
//
//        TextView text = view.findViewById(R.id.marker_text);
//
//        text.setText(text_marker);
//
//        Bitmap bitmap = convertViewToBitmap(view);
//        return bitmap;
//    }
//
//    /**
//     * 自定义中心点
//     * @param context
//     * @param size
//     * @return
//     */
//    public   Bitmap custom_location_Marker(Context context,int size)
//    {
//        View view =View.inflate(context,R.layout.custom_location_marker,null);
//        ImageView action_img=view.findViewById(R.id.action_img);
//        TextView text = view.findViewById(R.id.marker_text);
//        if(size==0)
//            text.setText("搜索中...");
//        else
//            text.setText("附近"+size+"停车点");
//        AnimationUtils.anima(context,action_img);
//        Bitmap bitmap =convertViewToBitmap(view);
//        return  bitmap;
//    }
//
//
//}
//
