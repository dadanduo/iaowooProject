package com.iaowoo.mobile.Ui.classification.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.iaowoo.mobile.Application.MyActivityManager;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageWebview;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.JsMethod2;
import com.iaowoo.mobile.H5toAndroid.MyWebChromeClient;
import com.iaowoo.mobile.H5toAndroid.MyWebViewClient1;
import com.iaowoo.mobile.H5toAndroid.MyWebviewClientCallBack;
import com.iaowoo.mobile.H5toAndroid.modle.ANDROIDHEIGT;
import com.iaowoo.mobile.H5toAndroid.modle.CALLTELEPHONE;
import com.iaowoo.mobile.H5toAndroid.modle.COPY;
import com.iaowoo.mobile.H5toAndroid.modle.GoWeexResponse;
import com.iaowoo.mobile.H5toAndroid.modle.ImageLook;
import com.iaowoo.mobile.H5toAndroid.modle.La_lo;
import com.iaowoo.mobile.H5toAndroid.modle.NEWACTIVITY;
import com.iaowoo.mobile.H5toAndroid.modle.OPENQIYU;
import com.iaowoo.mobile.H5toAndroid.modle.PAY;
import com.iaowoo.mobile.H5toAndroid.modle.PAYGET;
import com.iaowoo.mobile.H5toAndroid.modle.QIYUCOUNT;
import com.iaowoo.mobile.H5toAndroid.modle.QIYUOUT;
import com.iaowoo.mobile.H5toAndroid.modle.SHARE;
import com.iaowoo.mobile.H5toAndroid.modle.SHOPDETAILS;
import com.iaowoo.mobile.H5toAndroid.modle.TOKEN;
import com.iaowoo.mobile.H5toAndroid.modle.TYPE;
import com.iaowoo.mobile.H5toAndroid.modle.Umcustom;
import com.iaowoo.mobile.H5toAndroid.modle.VERSION;
import com.iaowoo.mobile.H5toAndroid.modle.WECHATEPAY;
import com.iaowoo.mobile.H5toAndroid.modle.WECHATTB;
import com.iaowoo.mobile.H5toAndroid.modle.XCX;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Model.WeeXAddress;
import com.iaowoo.mobile.Ui.classification.View.FrameAnimation;
import com.iaowoo.mobile.Ui.classification.View.PhotoPopupWindow;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideImageLoader;
import com.iaowoo.mobile.Utils.LocationApiUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionListener;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionsUtil;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Weex.extend.Model.ShopDetails;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.iaowoo.mobile.umeng.ShareUtils;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}(webview)
 * @date 2018/6/4
 */
public class WebviewAcitvity extends  BaseActivity implements H5CallBack,MyWebviewClientCallBack,LocationApiUtils.LocationCallBack,SensorEventListener {
    /**
     * webview
     */
    private BridgeWebView bridgeWebView;
    /**
     * 没有网络展示布局
     */
    private FrameLayout nowifi;
    /**
     * 重新加载按钮
     */
    private Button re_loading;
    /**
     * 外层布局
     */
    private LinearLayout web;
    /**
     * 跳一跳
     */
    private RelativeLayout Jump_jump;

    /**
     * loading
     */
    private ImageView plp_img;
    /**
     * 全局广播
     */
    private GlobalBroadcastReceiver globalBroadcastReceiver = null;
    /**
     * 跳转url
     */
    private String url;
    private WebSettings mWebSettings;
    private static final String APP_CACAHE_DIRNAME = "/data/data/package_name/cache/webviewCache";
    private MyWebChromeClient myWebChromeClient;
    private MyWebViewClient1 myWebViewClient;
    private Dialog showCode_up;
    private SoftReference<LocationApiUtils> locationApiUtils;
    private SoftReference<JsMethod2> jsMethod;
    private SoftReference<DialogUtils> dialogUtils;
    private PhotoPopupWindow mPhotoPopupWindow;
    private Map<String, Object> map;
    private HashMap<String, String> hashmap;
    private Type typeToken;
    /**
     * 加速度传感器
     */
    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;
    private Dialog dialog;
    /**
     * 摇一摇控制执行一次
     */
    private boolean isWaveOk=false;
    private int[] mImgResIds = new int[]{
            R.drawable.loading_00000,
            R.drawable.loading_00001,
            R.drawable.loading_00002,
            R.drawable.loading_00003,
            R.drawable.loading_00004,
            R.drawable.loading_00005,
            R.drawable.loading_00006,
            R.drawable.loading_00007,
            R.drawable.loading_00008,
            R.drawable.loading_00009,
            R.drawable.loading_00010,
            R.drawable.loading_00011,
            R.drawable.loading_00012,
            R.drawable.loading_00013,
            R.drawable.loading_00014,
            R.drawable.loading_00015,
            R.drawable.loading_00016,
            R.drawable.loading_00017,
            R.drawable.loading_00018,
            R.drawable.loading_00019,
            R.drawable.loading_00020,
            R.drawable.loading_00021,
            R.drawable.loading_00022,
            R.drawable.loading_00023,
            R.drawable.loading_00024,
    };
    private SoftReference<FrameAnimation> mFrameAnimation=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_new);
        EventBus.getDefault().register(this);
        Initialize();
        mFrameAnimation = new SoftReference<>(new FrameAnimation(plp_img, mImgResIds, 50, true));
        createLoadingDialogPLP();
        //全屏显示
        allState();
        //得到需要跳转的url
        url = getIntent().getStringExtra("webview_url");
        LogPrint.printError("url" + url);
        String tag = getIntent().getStringExtra("tag");
        dialogUtils = new SoftReference<>(new DialogUtils());
//        if(dialogUtils.get()!=null){
//            dialog=dialogUtils.get().createLoadingDialog(this,"",false);
//        }else{
//            dialogUtils = new SoftReference<>(new DialogUtils());
//            dialog=dialogUtils.get().createLoadingDialog(this,"",false);
//        }
        locationApiUtils = new SoftReference<>(new LocationApiUtils());
        locationApiUtils.get().setInterface(this);
        jsMethod = new SoftReference<>(new JsMethod2());
        //需要经纬度数据
        if (!TextUtils.isEmpty(tag)) {
            if (tag.endsWith("1")) {
                requestLoacation();
            }
        }
        // 重新加载
        re_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isNetworkConnected(ZApplication.getContext())){
                    openSetting(WebviewAcitvity.this);
                }
                webLoading();
            }
        });
        //webview配置
        webviewPZ();
        //主动推给H5
        initData();
        JsBridge();
        webLoading();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    /**
     * //初始化
     */
    private void Initialize() {
        bridgeWebView = findViewById(R.id.bridge_webview_all);
        nowifi = findViewById(R.id.nowifi);
        re_loading = findViewById(R.id.re_loading);
        web = findViewById(R.id.web);
        Jump_jump = findViewById(R.id.Jump_jump);
        plp_img = findViewById(R.id.plp_img);
    }

    /**
     * webview配置
     */
    private void webviewPZ() {
        mWebSettings = bridgeWebView.getSettings();
        //设置渲染效果优先级，高
        bridgeWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 设置 缓存模式.LOAD_NO_CACHE
        bridgeWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        /*********************配置localStorage****************/
        bridgeWebView.getSettings().setJavaScriptEnabled(true);
        bridgeWebView.getSettings().setDomStorageEnabled(true);// 打开本地缓存提供JS调用,至关重要
        bridgeWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);// 实现8倍缓存
        bridgeWebView.getSettings().setAllowFileAccess(true);
        bridgeWebView.getSettings().setAppCacheEnabled(true);
        String appCachePath = ZApplication.getContext().getCacheDir().getAbsolutePath();
        bridgeWebView.getSettings().setAppCachePath(appCachePath);
        bridgeWebView.getSettings().setDatabaseEnabled(true);
        /*****************************************************/
        //开启 应用缓存 功能
        bridgeWebView.getSettings().setAppCacheEnabled(true);
        String cacheDirPath = APP_CACAHE_DIRNAME;
        //设置数据库缓存路径
        bridgeWebView.getSettings().setDatabasePath(cacheDirPath);
        //设置 应用 缓存目录
        bridgeWebView.getSettings().setAppCachePath(cacheDirPath);
        //开启 DOM 存储功能
        bridgeWebView.getSettings().setDomStorageEnabled(true);
        //开启 数据库 存储功能
        bridgeWebView.getSettings().setDatabaseEnabled(true);
        //设置js可以直接打开窗口，如window.open()，默认为false
        bridgeWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        bridgeWebView.getSettings().setJavaScriptEnabled(true);
        //是否可以缩放，默认true
        bridgeWebView.getSettings().setSupportZoom(false);
        //是否显示缩放按钮，默认false
        bridgeWebView.getSettings().setBuiltInZoomControls(false);
        //设置此属性，可任意比例缩放。大视图模式
        bridgeWebView.getSettings().setUseWideViewPort(true);
        //和setUseWideViewPort(true)一起解决网页自适应问题
        bridgeWebView.getSettings().setLoadWithOverviewMode(true);
        //解决大于21版本图片不显示问题
        if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            bridgeWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        bridgeWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        /************************************让js页面用手机格式打开******************************************/
        myWebChromeClient = new MyWebChromeClient(this);
        // 支持自定义alert
        bridgeWebView.setWebChromeClient(myWebChromeClient);
        // 设置具体WebViewClient
        myWebViewClient = new MyWebViewClient1(bridgeWebView, this);
        bridgeWebView.setWebViewClient(myWebViewClient);
        /******************************************************************************/
    }

    /**
     * 推送h5初始化
     */
    private void initData() {
        //告诉js状态栏和tabbar的高度
        tellAndroidToJs();
        //主动推给H5token
        pushTokenToJs();
        pulianpuBroadCast();
        openQyNativeChat();
        pushAppVersionToJs();
        //打开微信支付
        qyUnReadMsgCount(0);
        /********************************************************************************************************************************************************/
    }

    /**
     * 加载失败
     */
    @Override
    public void loadingFaild() {
        LogPrint.printError("加载失败");
        if (nowifi != null) {
            nowifi.setVisibility(View.VISIBLE);
            bridgeWebView.setVisibility(View.GONE);
        }
        closeD();
    }

    /**
     * 加载成功
     */
    @Override
    public void loadingSuccess() {
        nowifi.setVisibility(View.GONE);
        LogPrint.printError("加载成功");
        if (bridgeWebView != null) {
            bridgeWebView.setVisibility(View.VISIBLE);
            //保存token到loacahost
            saveToken(PrefManager.getInstance().getToken(), PrefManager.getInstance().getInvite());
        }
        closeD();
    }

    /**
     * 底部tabbbar点击事件
     *
     * @param eventBusMessageWebview
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageWebview eventBusMessageWebview) {
        switch (eventBusMessageWebview.getTag()) {
            //返回
            case "sendBack":
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
                finish();
                break;
            //登陆成功
            case "login":
                LogPrint.printError("登陆成功" + eventBusMessageWebview.getData());
                loginSuceess();
                break;
            //被登出成功
            case "logout":
                LogPrint.printError("登出成功" + eventBusMessageWebview.getData());
                logoutSuceess();
                break;
            //图片上传失败
            case "uploadingFaild":
                upFailde();
                break;
            //图片上传成功
            case "uploadingSucess":
                if (!TextUtils.isEmpty(eventBusMessageWebview.getData()))
                    LogPrint.printError(eventBusMessageWebview.getData());
                uploadPictureSucc(eventBusMessageWebview.getData());
                break;
            //上传图片取消
            case "cancel":
                upFailde();
                break;
            case "paySucess":
                if (eventBusMessageWebview.getData().equals("0"))
                    //微信支付成功
                    tellWechatToJs("1");
                else
                    //支付宝支付成功
                    tellAlapayToJs("1");
                break;
            case "payFaild":
                //微信支付失败
                if (eventBusMessageWebview.getData().equals("0"))
                    //微信支付失败
                    tellWechatToJs("0");
                else
                    //支付宝支付失败
                    tellAlapayToJs("0");
                break;
            //微信同步
            case "wechatSynchronization":
                if (!TextUtils.isEmpty(eventBusMessageWebview.getData()))
                    tellMyDataToJs(eventBusMessageWebview.getData());
                break;
            //weex选择地址主动推给H5
            case "selectAddressNotification":
                    selectAddressNotification(eventBusMessageWebview.getData());
                break;
            default:
                break;
        }
    }

    /**
     * 广播
     */
    public void pulianpuBroadCast() {
        //注册全局广播
        globalBroadcastReceiver = new GlobalBroadcastReceiver(new BroadcastCallBack() {
            @Override
            public void ReceiverData(String tag, String data) {
                switch (tag) {
                    //返回
                    case "sendBack":
                        EventBus.getDefault().post(new EventBusMessageRefresh(0));
                        finish();
                        break;
                    //登陆成功
                    case "login":
                        LogPrint.printError("登陆成功" + data);
                        loginSuceess();
                        break;
                    //被登出成功
                    case "logout":
                        LogPrint.printError("登出成功" + data);
                        logoutSuceess();
                        break;
                    //图片上传失败
                    case "uploadingFaild":
                        upFailde();
                        break;
                    //图片上传成功
                    case "uploadingSucess":
                        if (!TextUtils.isEmpty(data))
                            LogPrint.printError(data);
                        uploadPictureSucc(data);
                        break;
                    //上传图片取消
                    case "cancel":
                        upFailde();
                        break;
                    case "paySucess":
                        if (data.equals("0"))
                            //微信支付成功
                            tellWechatToJs("1");
                        else
                            //支付宝支付成功
                            tellAlapayToJs("1");
                        break;
                    case "payFaild":
                        //微信支付失败
                        if (data.equals("0"))
                            //微信支付失败
                            tellWechatToJs("0");
                        else
                            //支付宝支付失败
                            tellAlapayToJs("0");
                        break;
                    //微信同步
                    case "wechatSynchronization":
                        if (!TextUtils.isEmpty(data))
                            tellMyDataToJs(data);
                        break;
                    //极光推送
                    case "jiguang":
//                        pushJiguangToJs(data);
                        break;
                    default:
                        break;
                }
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RdioBroadCast.BOARD);
        registerReceiver(globalBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 进程结束
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        //关闭弹窗
        closeD();
        myWebViewClient = null;
        myWebChromeClient = null;
        if (globalBroadcastReceiver != null) {
            unregisterReceiver(globalBroadcastReceiver);
        }
//        //防止内存泄漏
        UMShareAPI.get(this).release();
        Destroyall();
        //防止内存泄漏
        stopLoading();
        EventBus.getDefault().unregister(this);

    }

    /**
     * 当前页面finish
     */
    private void Destroyall() {
        if (bridgeWebView != null) {
            LogPrint.printError("不为空");
            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = bridgeWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(bridgeWebView);
            }
            bridgeWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            bridgeWebView.getSettings().setJavaScriptEnabled(false);
            bridgeWebView.clearHistory();
            bridgeWebView.clearView();
            bridgeWebView.removeAllViews();
            bridgeWebView.destroy();
            bridgeWebView = null;

        }
        /*********************清空缓存***********************/
        WebStorage.getInstance().deleteAllData();
        /***************************************************/
        stopLo();
        map = null;
        hashmap = null;
        // 重新加载
        re_loading = null;
        typeToken = null;
    }


    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (bridgeWebView != null) {
            if (keyCode == KeyEvent.KEYCODE_BACK && bridgeWebView.canGoBack()) {
                // 返回上一页面
                bridgeWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                bridgeWebView.goBack();
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                LogPrint.printError("返回了");
                finish();
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 关闭弹窗
     */
    public void closeD() {
        stopLoading();
    }

    /**
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
     * 加载路径
     */
    private void webLoading() {
//        String urls="file://"+ZApplication.JSLib+"/plp_h5/index.html#/home/product_details?type=5&templateId=DP_504387779975712768&from=App";
        if(isNetworkConnected(ZApplication.getContext())){
            LogPrint.printError("有网");
            nowifi.setVisibility(View.GONE);
            bridgeWebView.setVisibility(View.VISIBLE);
            bridgeWebView.loadUrl(url);
        }else{
            closeD();
            nowifi.setVisibility(View.VISIBLE);
            bridgeWebView.setVisibility(View.GONE);
        }
    }

    /**
     * 打开网络设置界面
     */
    public  void openSetting(Activity activity) {
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        activity.startActivityForResult(intent, 0);
    }
    /**
     * webview页面配置
     */
    private void JsBridge() {
        /***************************************交互方法***********************************/
        //获取token值
        bridgeWebView.registerHandler("getUserToken", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                String tokenvalue = "";
                String inviteCode = "";
                if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                    tokenvalue = PrefManager.getInstance().getToken();
                }
                if (!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
                    inviteCode = PrefManager.getInstance().getInvite();
                }
                TOKEN token = new TOKEN();
                token.setToken(tokenvalue);
                token.setInviteCode(inviteCode);
                String json = ZApplication.gson.toJson(token);
                LogPrint.printError("getUserToken" + json);
                function.onCallBack(json);
            }

        });

        //分享到小程序
        bridgeWebView.registerHandler("shareMiniProgram", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    XCX xcx = JSON.parseObject(data, XCX.class);
                    if (xcx != null) {
                        //小程序的分享
                        ShareUtils.shareSmallPc(WebviewAcitvity.this, Defaultcontent.url, xcx.getTitle(), xcx.getContent(), xcx.getImageUrl(), R.mipmap.logo_main, Defaultcontent.small_id, xcx.getOriginalPage(), SHARE_MEDIA.WEIXIN,null);
                    }
                }
            }

        });
        //登录
        bridgeWebView.registerHandler("userLogin", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                String tokenvalue = "";
                String inviteCode = "";
                if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                    tokenvalue = PrefManager.getInstance().getToken();
                }
                if (!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
                    inviteCode = PrefManager.getInstance().getInvite();
                }
                TOKEN token = new TOKEN();
                token.setToken(tokenvalue);
                token.setInviteCode(inviteCode);
                String json = ZApplication.gson.toJson(token);
                if (TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                    if (dialogUtils.get() != null) {
                        //弹框
                        dialogUtils.get().LoginTo(WebviewAcitvity.this);
                        LogPrint.printError("userLogin" + json);
                    } else {
                        dialogUtils = new SoftReference<>(new DialogUtils());
                        //弹框
                        dialogUtils.get().LoginTo(WebviewAcitvity.this);
                        LogPrint.printError("userLogin" + json);
                    }

                }
            }
        });
        //点击设置按钮
        bridgeWebView.registerHandler("userSettingClick", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                startActivity(new Intent(WebviewAcitvity.this, SettingActivity.class));
                LogPrint.printError("设置");
            }
        });
        //原生上传图片事件
        bridgeWebView.registerHandler("uploadPictures", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("原生上传图片事件" + data);
                //得到上传类型
                TYPE type = JSON.parseObject(data, TYPE.class);
                Intent mintent = new Intent(WebviewAcitvity.this, PhotoActivity.class);
                mintent.putExtra("type", type.getType());
                mintent.putExtra("tag", type.getCanEdit());
                startActivity(mintent);
//                CameraImage();
                if (dialogUtils.get() != null) {
                    showCode_up = dialogUtils.get().createLoadingDialog(WebviewAcitvity.this, "上传中请稍后", true);
                } else {
                    dialogUtils = new SoftReference<>(new DialogUtils());
                    showCode_up = dialogUtils.get().createLoadingDialog(WebviewAcitvity.this, "上传中请稍后", true);
                }
            }
        });
        //分享点击事件shareClick
        bridgeWebView.registerHandler("shareClick", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("分享点击事件" + data);
                SHARE share = JSON.parseObject(data, SHARE.class);
                Defaultcontent.title = share.getTitle();
                Defaultcontent.imageurl = share.getImageUrl();
                Defaultcontent.text = share.getContent();
                Defaultcontent.url = share.getShareUrl();
                Defaultcontent.code_url = share.getOriginalPage();
                if (dialogUtils.get() != null) {
                    dialogUtils.get().Share(WebviewAcitvity.this);
                } else {
                    dialogUtils = new SoftReference<>(new DialogUtils());
                    dialogUtils.get().Share(WebviewAcitvity.this);
                }
            }
        });
        //微信支付状态回调（拼够和充值
        bridgeWebView.registerHandler("weChatPay", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("微信支付状态回调（拼够和充值）" + data);
                PAYGET payget = JSON.parseObject(data, PAYGET.class);
                //保存type支付结束回调给H5
                PrefManager.getInstance().savePayType(payget.getType());
                LogPrint.printError("type：" + payget.getType() + ">>>>>>json" + payget.getPayInfo());
                if (!TextUtils.isEmpty(payget.getPayInfo())) {
                    WECHATEPAY wechatepay = JSON.parseObject(payget.getPayInfo(), WECHATEPAY.class);
                    if (jsMethod.get() != null) {
                        jsMethod.get().wechatPay(wechatepay.getAppid(), wechatepay.getNoncestr(), wechatepay.getPackageX(), wechatepay.getPartnerid(), wechatepay.getPrepayid(), wechatepay.getSign(), wechatepay.getTimestamp());
                    } else {
                        jsMethod = new SoftReference<JsMethod2>(new JsMethod2());
                        jsMethod.get().wechatPay(wechatepay.getAppid(), wechatepay.getNoncestr(), wechatepay.getPackageX(), wechatepay.getPartnerid(), wechatepay.getPrepayid(), wechatepay.getSign(), wechatepay.getTimestamp());
                    }
                }
            }
        });
        //H5选择支付宝支付（拼够和充值）
        bridgeWebView.registerHandler("alipayPay", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("showtime" + data);
                PAYGET payget = JSON.parseObject(data, PAYGET.class);
                //保存type支付结束回调给H5
                PrefManager.getInstance().savePayType(payget.getType());
                LogPrint.printError("H5选择支付宝支付（拼够和充值）" + data);
                if (!TextUtils.isEmpty(payget.getPayInfo())) {
                    if (jsMethod.get() != null) {
                        jsMethod.get().Alipay(WebviewAcitvity.this, payget.getPayInfo());
                    } else {
                        jsMethod = new SoftReference<JsMethod2>(new JsMethod2());
                        jsMethod.get().Alipay(WebviewAcitvity.this, payget.getPayInfo());
                    }
                }
            }
        });
        // 我的资料页面同步微信资料weChatAuth
        bridgeWebView.registerHandler("weChatAuth", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (jsMethod.get() != null) {
                    jsMethod.get().authorization(SHARE_MEDIA.WEIXIN, WebviewAcitvity.this);
                } else {
                    jsMethod = new SoftReference<JsMethod2>(new JsMethod2());
                    jsMethod.get().authorization(SHARE_MEDIA.WEIXIN, WebviewAcitvity.this);
                }
                LogPrint.printError("我的资料页面同步微信资料");
            }
        });
        //H5调用原生拨打电话makePhoneCall
        bridgeWebView.registerHandler("makePhoneCall", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("H5调用原生拨打电话makePhoneCall" + data);
                CALLTELEPHONE calltelephone = JSON.parseObject(data, CALLTELEPHONE.class);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + calltelephone.getTel()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //从外部（微信，qq，浏览器进入APP），点击H5导航根返回键，dismiss页面activityWebDismiss
        bridgeWebView.registerHandler("activityWebDismiss", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("从外部（微信，qq，浏览器进入APP），点击H5导航根返回键，");
                Intent intent = new Intent();
                intent.putExtra("SHOW", "sendBack");
                intent.putExtra(RdioBroadCast.DATA, "0");
                intent.setAction(RdioBroadCast.BOARD);
                sendBroadcast(intent);
            }
        });
        //新人活动
        bridgeWebView.registerHandler("intoActivityWebView", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("新人活动" + data);
                NEWACTIVITY newactivity = JSON.parseObject(data, NEWACTIVITY.class);
                Intent mintent = new Intent(WebviewAcitvity.this, UseguideActivity.class);
                mintent.putExtra("title", newactivity.getTitle());
                mintent.putExtra("url", newactivity.getUrl());
                startActivity(mintent);

            }
        });
        //进入七鱼客服页面
        bridgeWebView.registerHandler("openServiceChat", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (!TextUtils.isEmpty(data)) {
                    QIYUOUT qiyuout = JSON.parseObject(data, QIYUOUT.class);
                    ZApplication.getInstance().options(qiyuout.getCrmParams().getAvatar());
                    // appKey七鱼客服
                    Unicorn.init(WebviewAcitvity.this, ConfigAppkey.QIYU, ConfigFlag.ysfOptions, new GlideImageLoader(WebviewAcitvity.this));
                    setSource(qiyuout.getCrmParams().getName(), qiyuout.getCrmParams().getMobile(), "", qiyuout.getCrmParams().getGender(), "V" + SingleOverAll.getInstance().getVersionName(ZApplication.getInstance().getApplicationContext()) + "&&versionCode:" + SingleOverAll.getInstance().getVersionCode(ZApplication.getInstance().getApplicationContext()), qiyuout.getCrmParams().getEntrance(), "品牌：" + UtilsAll.getPhoneBrand() + "型号：" + UtilsAll.getPhoneModel(), qiyuout.getCrmParams().getOrderId(), "android操作系统");
                    intoQiyu(WebviewAcitvity.this, Long.parseLong(qiyuout.getCrmParams().getGroupId()));
                }
            }
        });
        //复制粘贴板
        bridgeWebView.registerHandler("copyToClipboard", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                COPY copy = JSON.parseObject(data, COPY.class);
                ClipboardManager cm = (ClipboardManager) ZApplication.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(copy.getCopyName());
                ToastUtilsAll.getInstance().showShortToast("复制成功");
            }
        });
        //跳转到weex页面
        bridgeWebView.registerHandler("pushWeexPages", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                   LogPrint.printError("跳转到weex页面"+data);
                GoWeexResponse goWeexResponse=JSON.parseObject(data, GoWeexResponse.class);
                UtilsAll.GoWeexAll(WebviewAcitvity.this,goWeexResponse.getUrl(),"","");
            }
        });
        //跳转到原生商品详情
        bridgeWebView.registerHandler("pushGoodsDetail", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("跳转到原生商品详情"+data);
                ShopDetails shopDetails=JSON.parseObject(data,ShopDetails.class);
                UtilsAll.GoNativeGoodsDetails(MyActivityManager.getInstance().getCurrentActivity(),shopDetails.getGoodsId(),shopDetails.getActivityId(),shopDetails.getInviteCode());
            }
        });

//        //自定义统计S
        bridgeWebView.registerHandler("customStatistics", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("进来了come on" + data);
                Umcustom umcustom = JSON.parseObject(data, Umcustom.class);
                switch (umcustom.getType()) {
                    case 0://友盟自定义事件
                        if (!TextUtils.isEmpty(umcustom.getEventID()))
                            MobclickAgent.onEvent(ZApplication.getContext(), umcustom.getEventID());
                        break;
                    case 1:
                        if (!TextUtils.isEmpty(umcustom.getEventID()) && !TextUtils.isEmpty(umcustom.getLabel()))
                            MobclickAgent.onEvent(ZApplication.getContext(), umcustom.getEventID(), umcustom.getLabel());
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(umcustom.getEventID()) && !TextUtils.isEmpty(umcustom.getMap())) {
                            typeToken = new TypeToken<HashMap<String, Object>>() {
                            }.getType();
                            map = JSON.parseObject(umcustom.getMap(), typeToken);
                            hashmap = new HashMap<String, String>();
                            Set<String> get = map.keySet();
                            for (String key : get) {
                                hashmap.put(key, map.get(key) + "");
                            }
                            MobclickAgent.onEventValue(ZApplication.getContext(), umcustom.getEventID(), hashmap, 2147483645);
                        }
                        break;
                }
            }
        });
        //图片浏览
        bridgeWebView.registerHandler("productImageClick", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if(!TextUtils.isEmpty(data)) {
                    ImageLook imageLook = JSON.parseObject(data, ImageLook.class);
                    if(!TextUtils.isEmpty(imageLook.getImageUrls())) {
                        LogPrint.printError("imgeLook" + imageLook.getImageUrls() + "index" + imageLook.getIndex());
                        List<String> list = new ArrayList<>();
                        String[] ary = imageLook.getImageUrls().split(",");//调用API方法按照逗号分隔字符串
                        for (String item : ary) {
                            list.add(item);
                        }
                        startImagePagerActivity(WebviewAcitvity.this, list, imageLook.getIndex(), new ImagePagerActivity.ImageSize(0, 0));
                    }
                }
            }
        });
        //震动
        bridgeWebView.registerHandler("callSystemVibrate", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("震动");
                Vibrator vibrator = (Vibrator)WebviewAcitvity.this.getSystemService(WebviewAcitvity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(300);
            }
        });
        //经纬度getLocation
        bridgeWebView.registerHandler("getLocation", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("刷新经纬度");
                requestLoacation();
            }
        });
        //H5登录失效需要清空token
        bridgeWebView.registerHandler("loginOut", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("清空token");
                PrefManager.getInstance().setToken("");
            }
        });
        //进入异地联盟扫一扫
        bridgeWebView.registerHandler("sweepClickInRecommend", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("扫一扫");
                if (TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                    //跳登录
                    startActivity(new Intent(WebviewAcitvity.this, LoginActivity.class));
                } else {
                    IntentIntegrator intentIntegrator = new IntentIntegrator(WebviewAcitvity.this);
                    intentIntegrator
                            .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                            .setPrompt(ZApplication.CODE_TEXT)//写那句提示的话
                            .setOrientationLocked(false)//扫描方向固定
                            .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
                            .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                            .setCaptureActivity(ScanActicvity.class) // 设置自定义的activity是CustomActivity
                            .initiateScan(); // 初始化扫描
                }
            }
        });
        //跳到原生商户二维码
        bridgeWebView.registerHandler("showQrCodeDetails", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("跳到原生商户二维码" + data);
                SHOPDETAILS shopdetails = JSON.parseObject(data, SHOPDETAILS.class);
                Intent mintent = new Intent(WebviewAcitvity.this, CodeDetailsActivity.class);
                mintent.putExtra("shops", shopdetails);
                startActivity(mintent);
            }
        });
        //停止监听摇一摇
        bridgeWebView.registerHandler("listenToShake", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("摇一摇");
                // 务必要在pause中注销 mSensorManager
                // 否则会造成界面退出后摇一摇依旧生效的bug
                stopListening();

            }
        });

        //开始监听摇一摇
        bridgeWebView.registerHandler("listenToShake", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("摇一摇");
                startListening();
            }
        });

    }
    /**************************************************************************************************/


    /**********************************************android推值给H5**************************************/
    /**
     * 登录成功后调用h5的方法
     */
    private void loginSuceess() {
        String tokenvalue = "";
        String inviteCode = "";
        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            tokenvalue = PrefManager.getInstance().getToken();
        }
        if (!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
            inviteCode = PrefManager.getInstance().getInvite();
        }
        TOKEN token = new TOKEN();
        token.setToken(tokenvalue);
        token.setInviteCode(inviteCode);
        String json = ZApplication.gson.toJson(token);
        LogPrint.printError("loginsuceess" + json);
        //登录成功回调
        bridgeWebView.callHandler("userLoginSucc", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("showtime");
            }
        });
    }

    /**
     * 安卓主动推经纬度给h5
     *
     * @param la
     * @param lo
     */
    private void pushLatlngToJs(double la, double lo) {
        La_lo la_lo = new La_lo();
        la_lo.setLa(la);
        la_lo.setLo(lo);
        String json = ZApplication.gson.toJson(la_lo);
        LogPrint.printError(json);
        bridgeWebView.callHandler("pushLatlngToJs", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("安卓主动推经纬度给h5");
                //收到经纬度后停止定位
                if (locationApiUtils.get() != null) {
                    locationApiUtils.get().stopLo();
                }
            }
        });
    }

    /**
     * 登出成功后调用h5的方法
     */
    private void logoutSuceess() {
        String tokenvalue = "";
        String inviteCode = "";
        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            tokenvalue = PrefManager.getInstance().getToken();
        }
        if (!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
            inviteCode = PrefManager.getInstance().getInvite();
        }
        TOKEN token = new TOKEN();
        token.setToken(tokenvalue);
        token.setInviteCode(inviteCode);
        String json = ZApplication.gson.toJson(token);
        //登出成功回调
        LogPrint.printError("logoutSuceess" + json);
        bridgeWebView.callHandler("userLoginOut", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("showtimelogout");
            }
        });
    }

    /**
     * 告诉js我是android并告H5andorid机器状态栏的高度
     */
    private void tellAndroidToJs() {
        ANDROIDHEIGT androidheigt = new ANDROIDHEIGT();
        LogPrint.printError("标题栏的高度为：" + SingleOverAll.getInstance().getStatusBarHeight(ZApplication.getContext()) + "tabbar的高度为：" + SingleOverAll.getInstance().getNavigationBarHeight(ZApplication.getContext()));
        androidheigt.setNavHeight(SingleOverAll.getInstance().getStatusBarHeight(ZApplication.getContext()));
        androidheigt.setTabBarHeight(0);
        String json = ZApplication.gson.toJson(androidheigt);
        LogPrint.printError("状态栏高度：" + json);
        //告诉js我是android
        bridgeWebView.callHandler("callNavBarAndTabBarHeight", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("告诉js我是android");
            }
        });
    }

    /**
     * 支付宝支付状态回调（拼够和充值）alipayPayNotify
     *
     * @param tag
     */
    private void tellAlapayToJs(String tag) {
        PAY pay = new PAY();
        pay.setPayState(tag);
        pay.setType(PrefManager.getInstance().getPayType());
        String json = ZApplication.gson.toJson(pay);
        bridgeWebView.callHandler("alipayPayNotify", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("支付宝支付状态回调（拼够和充值）");
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
            }
        });
    }

    /**
     * 微信支付状态回调（拼够和充值）weChatPayNotify
     *
     * @param tag
     */
    private void tellWechatToJs(String tag) {
        PAY pay = new PAY();
        pay.setPayState(tag);
        pay.setType(PrefManager.getInstance().getPayType());
        LogPrint.printError("tyoe" + PrefManager.getInstance().getPayType());
        String json = ZApplication.gson.toJson(pay);
        LogPrint.printError("json" + json);
        bridgeWebView.callHandler("weChatPayNotify", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("微信支付状态回调（拼够和充值）weChatPayNotify");
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
            }
        });
    }

    /**
     * 我的资料页面同步微信资料回调weChatAuthState
     *
     * @param data
     */
    private void tellMyDataToJs(String data) {
        //从本地缓存中取出json字符串
        String json = PrefManager.getInstance().getWechateJson();
        WECHATTB wechattb = JSON.parseObject(json, WECHATTB.class);
        wechattb.getWexinResp().setIconurl(data);
        //指定新的服务器图片url
        String new_json = ZApplication.gson.toJson(wechattb);
        LogPrint.printError(new_json);
        bridgeWebView.callHandler("weChatAuthState", new_json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("我的资料页面同步微信资料回调weChatAuthState");
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
            }
        });
    }

    /**
     * 初始化主动token值给h5
     */
    private void pushTokenToJs() {
        String tokenvalue = "";
        String inviteCode = "";
        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            tokenvalue = PrefManager.getInstance().getToken();
        }
        if (!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
            inviteCode = PrefManager.getInstance().getInvite();
        }
        TOKEN token = new TOKEN();
        token.setToken(tokenvalue);
        token.setInviteCode(inviteCode);
        String json = ZApplication.gson.toJson(token);
        LogPrint.printError(json);
        //登录成功回调
        LogPrint.printError("pushTokenToJs" + json);
        bridgeWebView.callHandler("pushTokenToJs", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("初始化主动token值给h5");
            }
        });
    }

    /**
     * 把图片上传的结果扔给h5
     *
     * @param json
     */
    private void uploadPictureSucc(String json) {
        LogPrint.printError("返回给h5的图片路径" + json);
        bridgeWebView.callHandler("uploadPictureSucc", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("把图片上传的结果扔给h5");
                if(showCode_up!=null) {
                    if (showCode_up.isShowing()) {
                        showCode_up.dismiss();
                    }
                }
//                ToastUtilsAll.getInstance().showShortToast("图片上传成功");
                EventBus.getDefault().post(new EventBusMessageRefresh(0));

            }
        });
    }

    /**
     * jpush通知事件
     *
     * @param json
     */
    private void pushJiguangToJs(String json) {
        LogPrint.printError("通知h5极光推送" + json);
        bridgeWebView.callHandler("jpushMessageNotify", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("jpush通知事件");
                //推送成功后清空数据
                PrefManager.getInstance().saveJGMsm("");
            }
        });
    }
    /**
     * 默认地址推送H5
     * @param json
     */
    private void selectAddressNotification(String json) {
        LogPrint.printError("默认地址推送H5" + json);
        WeeXAddress weeXAddress = JSON.parseObject(json, WeeXAddress.class);

        bridgeWebView.callHandler("selectAddressNotification", JSON.toJSONString( weeXAddress.getAddress()), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("默认地址推送H5");
            }
        });
    }
    /**
     * 上传失败
     */
    private void upFailde() {
        if(showCode_up!=null) {
            if (showCode_up.isShowing()) {
                showCode_up.dismiss();
            }
        }
    }

    /**
     * /未读消息
     *
     * @param qyMsgCount
     */
    private void qyUnReadMsgCount(int qyMsgCount) {
        QIYUCOUNT qiyucount = new QIYUCOUNT();
        qiyucount.setQyMsgCount(qyMsgCount);
        String json = ZApplication.gson.toJson(qiyucount);
        bridgeWebView.callHandler("qyUnReadMsgCount", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("qyUnReadMsgCount");
            }
        });
    }

    /**
     * 告诉H5版本号versionName
     */
    private void pushAppVersionToJs() {
        VERSION version = new VERSION();
        version.setVersionName(SingleOverAll.getInstance().getVersionCode(ZApplication.getContext()));
        String json = ZApplication.gson.toJson(version);
        LogPrint.printError("版本号："+SingleOverAll.getInstance().getVersionCode(ZApplication.getContext()));
        bridgeWebView.callHandler("pushAppVersionToJs", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("pushAppVersionToJs");
            }
        });
    }

    /**
     * 是否打开android原生七鱼客服
     */
    private void openQyNativeChat() {
        OPENQIYU openqiyu = new OPENQIYU();
        openqiyu.setIsNativeQy("YES");
        String json = ZApplication.gson.toJson(openqiyu);
        bridgeWebView.callHandler("openQyNativeChat", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("openQyNativeChat");
            }
        });
    }
    /**
     * 开始摇一摇
     */
    private void startWave() {
        isWaveOk=true;
        LogPrint.printError("萨瓦迪卡");
        bridgeWebView.callHandler("startWave", "", new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("摇一摇成功");
            }
        });
    }

    /**
     * 跳转到客服页面
     *
     * @param context
     */
    private void intoQiyu(Context context, long groupId) {
        String title = context.getResources().getString(R.string.app_name)+"客服";
        /**
         * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
         * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
         * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
         */
        String sourceUrl = "https://pulpu.qiyukf.com";
        String sourceTitle = context.getResources().getString(R.string.app_name);
        String custom = "custom information string";
        ConsultSource source = new ConsultSource(sourceUrl, sourceTitle, custom);
        source.groupId = groupId;
//        source.staffId=309480;
        /**
         * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
         * 如果返回为false，该接口不会有任何动作
         *
         * @param context 上下文
         * @param title   聊天窗口的标题
         * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
         */
        Unicorn.openServiceActivity(context, title, source);
    }

    /**
     * 设置来源
     */
    private void setSource(String real_name, String mobile_phone, String email, String gender, String app_version, String app_source, String device_info, String orderId, String device_sys) {
        String json = "[{\"key\":\"real_name\",\"value\":\"%s\"},{\"key\":\"mobile_phone\",\"value\":\"%s\"},{\"key\":\"email\",\"value\":\"%s\",\"hidden\":true},{\"index\":0,\"key\":\"gender\",\"label\":\"性别\",\"value\":\"%s\"},{\"index\":1,\"key\":\"app_version\",\"label\":\"版本\",\"value\":\"%s\"},{\"index\":2,\"key\":\"app_source\",\"label\":\"入口\",\"value\":\"%s\"},{\"index\":3,\"key\":\"device_info\",\"label\":\"机型\",\"value\":\"%s\"},{\"index\":3,\"key\":\"orderId\",\"label\":\"订单号\",\"value\":\"%s\"},{\"index\":4,\"key\":\"device_sys\",\"label\":\"系统\",\"value\":\"%s\"}]";
        String show = String.format(json, real_name, mobile_phone, email, gender, app_version, app_source, device_info, orderId, device_sys);
        YSFUserInfo userInfo = new YSFUserInfo();
        if (!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
            userInfo.userId = PrefManager.getInstance().getInvite();
            LogPrint.printError("sb" + PrefManager.getInstance().getInvite());
        }
        userInfo.data = show;
        Unicorn.setUserInfo(userInfo);
    }

    /**
     * 开始监听摇一摇
     */
    private void startListening(){
        isWaveOk=false;
        //获取 SensorManager 负责管理传感器
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        if (mSensorManager != null) {
            //获取加速度传感器
            mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (mAccelerometerSensor != null) {
                mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }
    }

    /**
     * 停止监听摇一摇
     */
    private void  stopListening(){
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    /**
     * 加载失败
     */
    @Override
    public void LoadingFaild() {
        loadingFaild();
    }

    /**
     * 加载成功
     */
    @Override
    public void LoadingOK() {
        loadingSuccess();
    }

    /**
     * 保存值到loachost
     *
     * @param token
     * @param invate
     */
    private void saveToken(String token, String invate) {
        //1.拼接 JavaScript 代码
        String tokenUrl = "window.localStorage.setItem('t','" + token + "');";
        LogPrint.printError("吃🐔" + token);
        String invateUrl = "window.localStorage.setItem('inviteCode','" + invate + "');";

        String tokenUrl_new = "javascript:(function({ var localStorage = window.localStorage; localStorage.setItem('t','" + token + "') })()";
        String invateUrl_new = "javascript:(function({ var localStorage = window.localStorage; localStorage.setItem('inviteCode','" + invate + "') })()";

        //2.根据不同版本，使用不同的 API 执行 Js
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bridgeWebView.evaluateJavascript(tokenUrl, null);
//            bridgeWebView.evaluateJavascript(invateUrl, null);
        } else {
            bridgeWebView.loadUrl(tokenUrl_new);
//            bridgeWebView.loadUrl(invateUrl_new);
            bridgeWebView.reload();
        }
    }

    /**
     * 传递经纬度
     *
     * @param la
     * @param lo
     */
    @Override
    public void location(double la, double lo) {
        pushLatlngToJs(la, lo);
    }

    /**
     * 停止定位
     */
    private void stopLo() {
        //定位到当前城市区域
        if (locationApiUtils.get()!= null) {
            locationApiUtils.get().stopLo();
        }
    }

    /**
     * 权限申请
     */
    private void requestLoacation() {
        LogPrint.printError("小米请求权限");
        PermissionsUtil.requestPermission(ZApplication.getContext(), new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                //定位到当前城市区域
                if (locationApiUtils.get() != null) {
                    locationApiUtils.get().startLo();
                }else{
                    locationApiUtils=new SoftReference<>(new LocationApiUtils());
                    locationApiUtils.get().startLo();
                }
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                LogPrint.printError("定位权限被拒绝了");
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    /**
     * 加载弹框
     *
     * @return
     */
    private void createLoadingDialogPLP() {
        Jump_jump.setVisibility(View.VISIBLE);
        startAnim(plp_img);
    }

    /**
     * 停止加载
     */
    private void stopLoading() {
        stopAnim();
        Jump_jump.setVisibility(View.GONE);
        if( dialog!=null){
            dialog.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        // 务必要在pause中注销 mSensorManager
        // 否则会造成界面退出后摇一摇依旧生效的bug
        isWaveOk=false;
        stopListening();
        super.onPause();
    }

    /**
     * 开始转动
     * @param mIvOpen
     */
    public void startAnim(final ImageView mIvOpen) {
        if(mFrameAnimation.get()!=null) {
            mFrameAnimation.get().setAnimationListener(new FrameAnimation.AnimationListener() {
                @Override
                public void onAnimationStart() {
                    LogPrint.printError("start");
                }

                @Override
                public void onAnimationEnd() {
                    LogPrint.printError("end");
                }

                @Override
                public void onAnimationRepeat() {
                    LogPrint.printError("repeat");
                }

                @Override
                public void onAnimationPause() {
//                    mIvOpen.setBackgroundResource(R.drawable.loading_00012);
                }
            });
        }else{
            mFrameAnimation = new SoftReference<>(new FrameAnimation(mIvOpen, mImgResIds, 50, true));
            mFrameAnimation.get().setAnimationListener(new FrameAnimation.AnimationListener() {
                @Override
                public void onAnimationStart() {
                    LogPrint.printError("start");
                }

                @Override
                public void onAnimationEnd() {
                    LogPrint.printError("end");
                }

                @Override
                public void onAnimationRepeat() {
                    LogPrint.printError("repeat");
                }

                @Override
                public void onAnimationPause() {
//                    mIvOpen.setBackgroundResource(R.drawable.loading_00012);
                }
            });
        }
    }

    /**
     * 停止转动
     */
    public void stopAnim() {
        if(mFrameAnimation!= null) {
            LogPrint.printError("");
            mFrameAnimation.get().release();
            mFrameAnimation = null;
        }
    }
    /**
     * 动态设置透明状态栏
     */
    public void allState() {
        if (ZApplication.isShowHide)
            //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //透明导航栏
//              getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
    }

    /**
     * 跳转到图片浏览器
     * @param context
     * @param imgUrls
     * @param position
     * @param imageSize
     */
    public  void startImagePagerActivity(Context context, List<String> imgUrls, int position, ImagePagerActivity.ImageSize imageSize) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putStringArrayListExtra(ImagePagerActivity.INTENT_IMGURLS, new ArrayList<String>(imgUrls));
        intent.putExtra(ImagePagerActivity.INTENT_POSITION, position);
        intent.putExtra(ImagePagerActivity.INTENT_IMAGESIZE, imageSize);
        context.startActivity(intent);
        overridePendingTransition(0,0);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER) {
            //获取三个方向值
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            if ((Math.abs(x) > 17 || Math.abs(y) > 17 || Math.abs(z) > 17)&&!isWaveOk) {
//                Thread thread = new Thread() {
//                    @Override
//                    public void run() {
//                        LogPrint.printError("摇一摇");
//                        super.run();
//                    }
//                };
//                thread.start();
                LogPrint.printError("摇一摇");
                startWave();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}

