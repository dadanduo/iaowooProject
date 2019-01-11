//package com.iaowoo.mobile.Ui.classification.Presenter;
//
//import android.animation.Animator;
//import android.animation.AnimatorListenerAdapter;
//import android.animation.ValueAnimator;
//import android.content.Context;
//import android.view.MotionEvent;
//import android.view.animation.DecelerateInterpolator;
//
//import com.amap.api.maps.AMap;
//import com.amap.api.maps.CameraUpdate;
//import com.amap.api.maps.CameraUpdateFactory;
//import com.amap.api.maps.MapView;
//import com.amap.api.maps.model.BitmapDescriptor;
//import com.amap.api.maps.model.BitmapDescriptorFactory;
//import com.amap.api.maps.model.CameraPosition;
//import com.amap.api.maps.model.LatLng;
//import com.amap.api.maps.model.Marker;
//import com.amap.api.maps.model.MarkerOptions;
//import com.amap.api.maps.model.animation.ScaleAnimation;
//import com.amap.api.services.core.LatLonPoint;
//import com.amap.api.services.route.BusRouteResult;
//import com.amap.api.services.route.DriveRouteResult;
//import com.amap.api.services.route.RideRouteResult;
//import com.amap.api.services.route.RouteSearch;
//import com.amap.api.services.route.WalkRouteResult;
//import com.iaowoo.mobile.ScottMap.LocationTask;
//import com.iaowoo.mobile.ScottMap.PoiSearchTask;
//import com.iaowoo.mobile.ScottMap.PositionEntity;
//import com.iaowoo.mobile.ScottMap.RegeocodeGetAddress;
//import com.iaowoo.mobile.ScottMap.RegeocodeTask;
//import com.iaowoo.mobile.ScottMap.RouteTask;
//import com.iaowoo.mobile.ScottMap.Sha1;
//import com.iaowoo.mobile.ScottMap.Utils;
//import com.iaowoo.mobile.Utils.DialogUtils;
//import com.iaowoo.mobile.Utils.LogPrint;
//import com.iaowoo.mobile.interfaceCallback.DiapCallBack;
//import com.iaowoo.mobile.interfaceCallback.MarkerCallBack;
//import com.iaowoo.mobile.interfaceCallback.OnLocationGetAddressGetListener;
//import com.iaowoo.mobile.interfaceCallback.OnLocationGetListener;
//import com.iaowoo.mobile.Controller.Single.SingleOverAll;
//import com.iaowoo.mobile.R;
//import com.iaowoo.mobile.ScottMap.LocationTask;
//import com.iaowoo.mobile.ScottMap.PoiSearchTask;
//import com.iaowoo.mobile.ScottMap.PositionEntity;
//import com.iaowoo.mobile.ScottMap.RegeocodeGetAddress;
//import com.iaowoo.mobile.ScottMap.RegeocodeTask;
//import com.iaowoo.mobile.ScottMap.RouteTask;
//import com.iaowoo.mobile.ScottMap.Sha1;
//import com.iaowoo.mobile.ScottMap.Utils;
//import com.iaowoo.mobile.Ui.classification.Model.NearShops;
//import com.iaowoo.mobile.Utils.DialogUtils;
//import com.iaowoo.mobile.Utils.LogPrint;
//import com.iaowoo.mobile.interfaceCallback.DiapCallBack;
//import com.iaowoo.mobile.interfaceCallback.MarkerCallBack;
//import com.iaowoo.mobile.interfaceCallback.OnLocationGetAddressGetListener;
//import com.iaowoo.mobile.interfaceCallback.OnLocationGetListener;
//import com.plp.underlying.networkframwork.OkhttpManager;
//
//import java.lang.ref.SoftReference;
//
//
///**
// * Created by chenda on 2018/4/12.
// * 附近商家异地联盟
// */
//
//public class DifferentIndustryAlliancePresenter implements AMap.OnCameraChangeListener,
//        AMap.OnMapLoadedListener, OnLocationGetListener,RouteTask.OnRouteCalculateListener,
//        AMap.OnMapTouchListener,RouteSearch.OnRouteSearchListener,AMap.OnMapClickListener,OnLocationGetAddressGetListener {
//
//    /**
//     * context
//     */
//    private Context context;
//
//    /**
//     * 没有网络用户滑动地图会给提示
//     */
//    private boolean isOne=true;
//
//    /**
//     * amp
//     */
//    private AMap aMap;
//    /**
//     *定位
//     */
//    private LocationTask mLocationTask;
//    /**
//     * 逆地理编码功能
//     */
//    private RegeocodeTask mRegeocodeTask;
//    /**
//     * 逆地理编码功能
//     */
//    private RegeocodeGetAddress regeocodeGetAddress;
//    /**
//     * 绘制点标记
//     */
//    private Marker mPositionMark, mInitialMark,tempMark;//可移动、圆点、点击
//    /**
//     * 初始坐标、移动记录坐标
//     */
//    private LatLng mStartPosition,mRecordPositon;
//    /**
//     * 默认添加一次车辆坐标
//     */
//    private boolean mIsFirst = true;
//    /**
//     * 切换城市的封装
//     */
//    private RouteTask mRouteTask;
//    /**
//     * 就第一次显示位置
//     */
//    private boolean mIsFirstShow = true;
//    private LatLng initLocation;
//    private ValueAnimator animator = null;//坐标动画
//    private BitmapDescriptor initBitmap,moveBitmap,smallIdentificationBitmap,bigIdentificationBitmap;//定位圆点、可移动、所有标识（车）
//    private RouteSearch mRouteSearch;
//    private WalkRouteResult mWalkRouteResult;
//    private LatLonPoint mStartPoint = null;//起点，116.335891,39.942295
//    private LatLonPoint mEndPoint = null;//终点，116.481288,39.995576
//    private final int ROUTE_TYPE_WALK = 3;
//    //是否正在处于规划路线状态 false:不是true:是
//    private boolean isClickIdentification = false;
//    //    private WalkRouteOverlay walkRouteOverlay;//路线
//    private String [] time;
//    private String distance_show;
//    private MapView mapView;
//    private float rotting;
//    private DiapCallBack diapCallBack;
//    private float getZoomB = 17f;
//    private boolean isZoom=false;
//    private Utils utils;
//
//
//    private NearShops nearShopsThis;
//
//    private SoftReference<DialogUtils> dialogUtilsSoftReference;
//
//
//    public DifferentIndustryAlliancePresenter(Context context, MapView mapView)
//    {
//        this.context=context.getApplicationContext();
//        this.mapView=mapView;
//        utils=new Utils();
//        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
//    }
//
//    public void setInterface(DiapCallBack diapCallBack)
//    {
//        this.diapCallBack=diapCallBack;
//    }
//
//    public void  start()
//    {
//        initBitmap();
//        initAMap();
//        initLocation();
//        RouteTask.getInstance(context).addRouteCalculateListener(this);
//        LogPrint.printError(Sha1.sHA1(context)+"sha1");
//    }
//
//    /**
//     * 初始化icon
//     */
//    private void initBitmap()
//    {
//        initBitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.location);
//        moveBitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.homepage_wholeanchor);
//        smallIdentificationBitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.business_icon);
//        bigIdentificationBitmap = BitmapDescriptorFactory
//                .fromResource(R.mipmap.business_icon);
//    }
//
//    /**
//     * 初始化定位
//     */
//    private void initLocation() {
//        LogPrint.printError("定位进来了");
//        mLocationTask = LocationTask.getInstance(context);
//        mLocationTask.setOnLocationGetListener(this);
//        mRegeocodeTask = new RegeocodeTask(context);
//        regeocodeGetAddress=new RegeocodeGetAddress(context);
//    }
//
//    /**
//     * 初始化地图控制器对象
//     */
//    private void initAMap() {
//        if (aMap == null) {
//            aMap = mapView.getMap();
//            mRouteSearch = new RouteSearch(context);
//            mRouteSearch.setRouteSearchListener(this);
//            aMap.getUiSettings().setZoomControlsEnabled(false);
//            aMap.getUiSettings().setGestureScaleByMapCenter(false);
////            aMap.getUiSettings().setScrollGesturesEnabled(false);
//            aMap.setOnMapTouchListener(this);
//            aMap.setOnMapLoadedListener(this);
//            aMap.setOnCameraChangeListener(this);
//            aMap.setOnMapClickListener(this);
//            // 绑定 Marker 被点击事件
//            aMap.setOnMarkerClickListener(markerClickListener);
//            //选择城市回来的回调
//            mRouteTask= RouteTask.getInstance(context);
//        }
//    }
//
//
//    // 定义 Marker 点击事件监听
//    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
//        // marker 对象被点击时回调的接口
//        // 返回 true 则表示接口已响应事件，否则返回false
//        @Override
//        public boolean onMarkerClick(final Marker marker) {
////            isClickIdentification = true;
////            if(tempMark!=null)
////            {
////                tempMark.setIcon(smallIdentificationBitmap);
////                tempMark = null;
////            }
////            startAnim(marker);
//            LogPrint.printError(""+marker.getTitle());
//            for(int i=0;i<nearShopsThis.getBody().getContent().getList().size();i++){
//                if(marker.getTitle().endsWith(nearShopsThis.getBody().getContent().getList().get(i).getMerchantName())){
//                    diapCallBack.showItem(nearShopsThis.getBody().getContent().getList().get(i));
//                }
//            }
//            return true;
//        }
//    };
//
//    /**
//     * 动画
//     * @param marker
//     */
//    public void startAnim(Marker marker) {
//        ScaleAnimation anim = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f);
//        anim.setDuration(300);
//        marker.setAnimation(anim);
//        marker.startAnimation();
//    }
//
//    /**
//     * 创建初始位置图标
//     */
//    public Marker createInitialPosition(double lat, double lng) {
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.anchor(0.5f, 0.5f);
//        markerOptions.position(new LatLng(lat, lng));
//        markerOptions.icon(initBitmap);
//        mInitialMark = aMap.addMarker(markerOptions);
//        mInitialMark.setClickable(false);
//        return mInitialMark;
//    }
//
//    /**
//     * 创建移动位置图标
//     */
//    public Marker createMovingPosition() {
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(new LatLng(0, 0));
//        markerOptions.icon(moveBitmap);
//        mPositionMark = aMap.addMarker(markerOptions);
//        mPositionMark.setPositionByPixels(mapView.getWidth() / 2,
//                mapView.getHeight() / 2);
//        mPositionMark.setClickable(false);
//        return mPositionMark;
//    }
//
//    /**
//     * 抖一抖
//     */
//    public void  animMarker() {
//        if (animator != null) {
//            animator.start();
//            return;
//        }
//        animator = ValueAnimator.ofFloat(mapView.getHeight()/2, mapView.getHeight()/2 - 30);
//        animator.setInterpolator(new DecelerateInterpolator());
//        animator.setDuration(150);
//        animator.setRepeatCount(1);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                Float value = (Float) animation.getAnimatedValue();
//                mPositionMark.setPositionByPixels(mapView.getWidth() / 2, Math.round(value));
//            }
//        });
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                mPositionMark.setIcon(moveBitmap);
//            }
//        });
//        animator.start();
//    }
//
//    /**
//     * 结束抖动
//     */
//    public void endAnim() {
//        if (animator != null && animator.isRunning())
//            animator.end();
//    }
//
//    @Override
//    public void onCameraChange(CameraPosition cameraPosition) {
//        //缩放
//        if (getZoomB != cameraPosition.zoom ){
//            getZoomB = cameraPosition.zoom;
//            LogPrint.printError("缩放"+cameraPosition.zoom);
//            isZoom=true;
//            diapCallBack.hide_alert();
//        }
//        //移动
//        else {
//            isZoom=false;
//            if(isOne) {
//                if(dialogUtilsSoftReference.get()!=null) {
//                    dialogUtilsSoftReference.get().abnormal(context);
//                }else{
//                    dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
//                    dialogUtilsSoftReference.get().abnormal(context);
//                }
//                isOne=false;
//            }
//            LogPrint.printError("移动------------>");
//            diapCallBack.hide_alert();
//
//        }
//    }
//
//    @Override
//    public void onCameraChangeFinish(CameraPosition cameraPosition) {
//        if(mIsFirst) {
//            LogPrint.printError("坐标："+cameraPosition.target.latitude+cameraPosition.target.longitude);
//            createInitialPosition(cameraPosition.target.latitude, cameraPosition.target.longitude);
//            //创建移动位置坐标
//            createMovingPosition();
//            mIsFirst = false;
//        }
//        LogPrint.printError("onCameraChangeFinish" + cameraPosition.target);
//        if(!isClickIdentification) {
//            mRecordPositon = cameraPosition.target;
//        }
//        mStartPosition = cameraPosition.target;
//        mRegeocodeTask.setOnLocationGetListener(this);
//        regeocodeGetAddress.setonLocationGetAddressGetListener(this);
//        mRegeocodeTask.search(mStartPosition.latitude, mStartPosition.longitude);
//        if(!isClickIdentification)
//        {
//            utils.removeMarkers();
//            utils.ParkPointDIAA(cameraPosition.target, context, aMap, new MarkerCallBack() {
//                @Override
//                public void pushToObject(NearShops nearShops) {
//                    LogPrint.printError(nearShops.getCode()+"");
//                    diapCallBack.push_data_to_d(nearShops);
//                    if(nearShops.getCode().equals(OkhttpManager.SUCESS))
//                    {
//                        nearShopsThis=nearShops;
//                        LogPrint.printError("成功了");
//                        if(nearShops.getBody().getContent().getList().size()!=0)
//                        {
//                            if(!isZoom||!mIsFirst)
//                                diapCallBack.show_alert();
//                        }
//                    }
//                }
//            });
//        }
//        if (mInitialMark != null) {
//            mInitialMark.setToTop();
//        }
//        if (mPositionMark != null) {
//            mPositionMark.setToTop();
//            if(!isClickIdentification) {
//                animMarker();
//            }
//        }
//    }
//
//    @Override
//    public void onMapClick(LatLng latLng) {
//        clickMap();
//    }
//
//    @Override
//    public void onMapLoaded() {
//        //开启多次定位
//        mLocationTask.startLocate();
//    }
//
//    @Override
//    public void onTouch(MotionEvent motionEvent) {
//        if(motionEvent.getPointerCount()>=2)
//        {
//            aMap.getUiSettings().setScrollGesturesEnabled(false);
//        }else
//        {
//            aMap.getUiSettings().setScrollGesturesEnabled(true);
//        }
//    }
//
//    @Override
//    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
//
//    }
//
//    @Override
//    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
//
//    }
//
//    @Override
//    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
//
//    }
//
//    @Override
//    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
//
//    }
//
//
//    @Override
//    public void onLocationGet (PositionEntity entity){
//        // todo 这里在网络定位时可以减少一个逆地理编码
//        LogPrint.print("定位城市：" + entity.city + "定位详细地址：" + entity.address, 0);
//        //选择城市页面回调不再赋值
//        RouteTask.getInstance(context).setStartPoint(entity);
//        mStartPosition = new LatLng(entity.latitue, entity.longitude);
//        if (mIsFirstShow) {
//            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
//                    mStartPosition, 17);
//            aMap.animateCamera(cameraUpate);
//            mIsFirstShow = false;
//        }
//        mInitialMark.setPosition(mStartPosition);
//        initLocation = mStartPosition;
//    }
//
//    @Override
//    public void onRegecodeGet(PositionEntity entity){
//        LogPrint.print("定位城市：" + entity.city + "定位详细地址：" + entity.address, 0);
//        entity.latitue = mStartPosition.latitude;
//        entity.longitude = mStartPosition.longitude;
//        RouteTask.getInstance(context).setStartPoint(entity);
//        RouteTask.getInstance(context).search();
//
//    }
//
//    @Override
//    public void onLocationLL(double la, double lo) {
//
//    }
//
//    @Override
//    public void onLocationAddressGet (PositionEntity entity){
//        LogPrint.printError("回调的地址>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<：" + entity.address);
//        if (entity.latitue == 0 && entity.longitude == 0) {
//            LogPrint.printError(">>>>>>>>>>>>>>>没有地址");
//            PoiSearchTask poiSearchTask = new PoiSearchTask(context.getApplicationContext());
//            poiSearchTask.search(entity.address, RouteTask.getInstance(context).getStartPoint().city);
//        } else {
//            LogPrint.printError(">>>>>>>>>>>>>>>有地址");
//            mRouteTask.setEndPoint(entity);
//            mRouteTask.search();
//        }
//
//    }
//
//    @Override
//    public void onRegeAddresscodeGet (PositionEntity entity){
//        //设置终点地址
//    }
//
//    @Override
//    public void onRouteCalculate ( float cost, float distance, int duration){
//        LogPrint.printError("cost" + cost + "---" + "distance" + distance + "---" + "duration" + duration);
//        movePoint();
//
//    }
//    //刷新
//    public void clickRefresh () {
//        clickInitInfo();
//        if (initLocation != null) {
//            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
//                    initLocation, 17f);
//            aMap.animateCamera(cameraUpate);
//        }
//    }
//
//    /**
//     * 刷新地图
//     */
//    private void clickMap () {
//        clickInitInfo();
//        if (mRecordPositon != null) {
//            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
//                    mRecordPositon, 17f);
//            aMap.animateCamera(cameraUpate);
//        }
//    }
//
//    private void clickInitInfo () {
//        isClickIdentification = false;
//        if (null != tempMark) {
//            tempMark.setIcon(smallIdentificationBitmap);
//            tempMark.hideInfoWindow();
//            tempMark = null;
//        }
//    }
//    //移动点
//    public void movePoint () {
//        PositionEntity endPoint = RouteTask.getInstance(context).getEndPoint();
//        mRecordPositon = new LatLng(endPoint.latitue, endPoint.longitude);
//        clickMap();
//        RouteTask.getInstance(context).setEndPoint(null);
//    }
//    public void onresumeMethod () {
//        if (mInitialMark != null) {
//            mInitialMark.setToTop();
//        }
//        if (mPositionMark != null) {
//            mPositionMark.setToTop();
//        }
//    }
//    /**
//     * 页面销毁
//     */
//    public void ondestory () {
//        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
//        utils.removeMarkers();
//        mLocationTask.onDestroy();
//        RouteTask.getInstance(context).removeRouteCalculateListener(this);
//    }
//}
//
//
