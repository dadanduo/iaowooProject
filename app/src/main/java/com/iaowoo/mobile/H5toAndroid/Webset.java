package com.iaowoo.mobile.H5toAndroid;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.iaowoo.mobile.H5toAndroid.modle.ANDROIDHEIGT;
import com.iaowoo.mobile.H5toAndroid.modle.CALLTELEPHONE;
import com.iaowoo.mobile.H5toAndroid.modle.COPY;
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
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.H5toAndroid.modle.ANDROIDHEIGT;
import com.iaowoo.mobile.H5toAndroid.modle.CALLTELEPHONE;
import com.iaowoo.mobile.H5toAndroid.modle.COPY;
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
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.CodeDetailsActivity;
import com.iaowoo.mobile.Ui.classification.Activity.ImagePagerActivity;
import com.iaowoo.mobile.Ui.classification.Activity.LoginActivity;
import com.iaowoo.mobile.Ui.classification.Activity.PhotoActivity;
import com.iaowoo.mobile.Ui.classification.Activity.ScanActicvity;
import com.iaowoo.mobile.Ui.classification.Activity.SettingActivity;
import com.iaowoo.mobile.Ui.classification.Activity.UseguideActivity;
import com.iaowoo.mobile.Ui.classification.Activity.WebviewAcitvity;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Model.ImageS_Z;
import com.iaowoo.mobile.Ui.classification.Model.JS;
import com.iaowoo.mobile.Ui.classification.Model.SearchGoods;
import com.iaowoo.mobile.Utils.CacheUtils;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideImageLoader;
import com.iaowoo.mobile.Utils.HttpImgThread;
import com.iaowoo.mobile.Utils.LocationApiUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionListener;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionsUtil;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * webview的封装
 * Created by chenda on 2018/4/2.
 */
public class Webset implements MyWebviewClientCallBack,LocationApiUtils.LocationCallBack{

    private   WebSettings mWebSettings;
    private static final String APP_CACAHE_DIRNAME = "/data/data/package_name/cache/webviewCache";
    private Context context;
    private BridgeWebView bridgeWebView;
    private H5CallBack h5CallBack;
    private MyWebChromeClient myWebChromeClient;
    private MyWebViewClient myWebViewClient;
    private Dialog showCode_up;
    private CacheUtils cacheUtils=null;
    private LocationApiUtils locationApiUtils;
    private  JsMethod jsMethod;
    DialogUtils dialogUtils;
    private RdioBroadCast rdioBroadCast;
    private SoftReference<DialogUtils> dialogUtilsSoftReference;


    /**
     * 不带缓存图片
     * @param context
     * @param bridgeWebView
     * @param h5CallBack
     */
    public Webset(Context context,BridgeWebView bridgeWebView,H5CallBack h5CallBack) {
        this.context=context;
        this.bridgeWebView=bridgeWebView;
        this.h5CallBack=h5CallBack;

    }

    /**
     * 带缓存图片
     * @param context
     * @param bridgeWebView
     * @param h5CallBack
     * @param cacheUtils
     */
    public Webset(Context context, BridgeWebView bridgeWebView, H5CallBack h5CallBack, CacheUtils cacheUtils) {
        this.context = context;
        this.bridgeWebView = bridgeWebView;
        this.h5CallBack = h5CallBack;
        this.cacheUtils = cacheUtils;
    }

    /**
     * 带缓存图片和定位服务
     * @param context
     * @param bridgeWebView
     * @param h5CallBack
     * @param cacheUtils
     */
    public Webset(Context context, BridgeWebView bridgeWebView, H5CallBack h5CallBack, CacheUtils cacheUtils,String tag) {
        this.context = context;
        this.bridgeWebView = bridgeWebView;
        this.h5CallBack = h5CallBack;
        this.cacheUtils = cacheUtils;

        locationApiUtils=new LocationApiUtils();
        locationApiUtils.setInterface(this);
        requestLoacation();

    }
    /**
     * 带定位服务
     * @param context
     * @param bridgeWebView
     * @param h5CallBack
     */
    public Webset(Context context, BridgeWebView bridgeWebView, H5CallBack h5CallBack,String tag) {
        this.context = context;
        this.bridgeWebView = bridgeWebView;
        this.h5CallBack = h5CallBack;
        this.cacheUtils = cacheUtils;

        locationApiUtils=new LocationApiUtils();
        locationApiUtils.setInterface(this);
        requestLoacation();

    }



    /**
     * webview页面配置
     * @param url
     */
    public void JsBridge(String url) {
        dialogUtilsSoftReference=new SoftReference<DialogUtils>(new DialogUtils());
        rdioBroadCast=new RdioBroadCast();
        dialogUtils=new DialogUtils();
        jsMethod=new JsMethod(new JsMethod.JsCallBack() {
            @Override
            public void up() {
                XutilsHttp.getInstance().uploadPicture( HttpImgThread.ALBUM_PATH+HttpImgThread.webchatUrl,"2",context,1);
            }
        });
        bridgeWebView.loadUrl(url);
        mWebSettings =bridgeWebView.getSettings();
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
        if (android.os.Build.VERSION.SDK_INT >Build.VERSION_CODES.LOLLIPOP) {
            bridgeWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        bridgeWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        /************************************让js页面用手机格式打开******************************************/
        myWebChromeClient= new MyWebChromeClient(h5CallBack);
        // 支持自定义alert
        bridgeWebView.setWebChromeClient(myWebChromeClient);
        // 设置具体WebViewClient
        myWebViewClient=new MyWebViewClient(cacheUtils,bridgeWebView,this,context);
        bridgeWebView.setWebViewClient(myWebViewClient);
        // set HadlerCallBack
        bridgeWebView.setDefaultHandler(new myHadlerCallBack());
        /******************************************************************************/



        /***************************************交互方法***********************************/
        //获取token值
        bridgeWebView.registerHandler("getUserToken", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                String tokenvalue = "";
                String inviteCode="";
                if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                    tokenvalue = PrefManager.getInstance().getToken();
                }
                if(!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
                    inviteCode=PrefManager.getInstance().getInvite();
                }
                TOKEN token=new TOKEN();
                token.setToken(tokenvalue);
                token.setInviteCode(inviteCode);
                String json = ZApplication.gson.toJson(token);
                LogPrint.printError("getUserToken"+json);
                function.onCallBack(json);
            }

        });
        //登录
        bridgeWebView.registerHandler("userLogin", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                String tokenvalue = "";
                String inviteCode="";
                if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                    tokenvalue =PrefManager.getInstance().getToken();
                }
                if(!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
                    inviteCode=PrefManager.getInstance().getInvite();
                }
                TOKEN token=new TOKEN();
                token.setToken(tokenvalue);
                token.setInviteCode(inviteCode);
                String json = ZApplication.gson.toJson(token);
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                    //弹框
                    dialogUtilsSoftReference.get().LoginTo(context);
                    LogPrint.printError("userLogin"+json);
                }
            }
        });
        //点击设置按钮
        bridgeWebView.registerHandler("userSettingClick", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                context.startActivity(new Intent(context, SettingActivity.class));
                LogPrint.printError("设置");
            }
        });
        //原生上传图片事件
        bridgeWebView.registerHandler("uploadPictures", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("原生上传图片事件"+data);
                //得到上传类型
                TYPE type= JSON.parseObject(data,TYPE.class);
                Intent mintent=new Intent(context, PhotoActivity.class);
                mintent.putExtra("type",type.getType());
                mintent.putExtra("tag",type.getCanEdit());
                context.startActivity(mintent);

                showCode_up= dialogUtils.createLoadingDialog(context,"上传中请稍后",true);
            }
        });
        //分享点击事件shareClick
        bridgeWebView.registerHandler("shareClick", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("分享点击事件"+data);
                SHARE share=JSON.parseObject(data,SHARE.class);
                Defaultcontent.title=share.getTitle();
                Defaultcontent.imageurl=share.getImageUrl();
                Defaultcontent.text=share.getContent();
                Defaultcontent.url=share.getShareUrl();
                Defaultcontent.code_url=share.getOriginalPage();
                dialogUtils.Share(context);
            }
        });
        //微信支付状态回调（拼够和充值
        bridgeWebView.registerHandler("weChatPay", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("微信支付状态回调（拼够和充值）"+data);
                PAYGET payget=JSON.parseObject(data,PAYGET.class);
                //保存type支付结束回调给H5
                PrefManager.getInstance().savePayType(payget.getType());
                LogPrint.printError("type："+payget.getType()+">>>>>>json"+payget.getPayInfo());
                if(!TextUtils.isEmpty(payget.getPayInfo())) {
                    WECHATEPAY wechatepay = JSON.parseObject(payget.getPayInfo(), WECHATEPAY.class);
                    jsMethod.wechatPay(wechatepay.getAppid(), wechatepay.getNoncestr(), wechatepay.getPackageX(), wechatepay.getPartnerid(), wechatepay.getPrepayid(), wechatepay.getSign(), wechatepay.getTimestamp());
                }
            }
        });
        //H5选择支付宝支付（拼够和充值）
        bridgeWebView.registerHandler("alipayPay", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("showtime"+data);
                PAYGET payget=JSON.parseObject(data,PAYGET.class);
                //保存type支付结束回调给H5
                PrefManager.getInstance().savePayType(payget.getType());
                LogPrint.printError("H5选择支付宝支付（拼够和充值）"+data);
                if(!TextUtils.isEmpty(payget.getPayInfo())) {
                    jsMethod.Alipay(context, payget.getPayInfo());
                }
            }
        });
        // 我的资料页面同步微信资料weChatAuth
        bridgeWebView.registerHandler("weChatAuth", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                jsMethod.authorization(SHARE_MEDIA.WEIXIN,context);
                LogPrint.printError("我的资料页面同步微信资料");
            }
        });
        //H5调用原生拨打电话makePhoneCall
        bridgeWebView.registerHandler("makePhoneCall", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("H5调用原生拨打电话makePhoneCall"+data);
                CALLTELEPHONE calltelephone=JSON.parseObject(data,CALLTELEPHONE.class);
                UtilsAll.call(calltelephone.getTel(),context);
            }
        });
        //从外部（微信，qq，浏览器进入APP），点击H5导航根返回键，dismiss页面activityWebDismiss
        bridgeWebView.registerHandler("activityWebDismiss", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("从外部（微信，qq，浏览器进入APP），点击H5导航根返回键，");
               rdioBroadCast.sendData(context,RdioBroadCast.DATA,"0",RdioBroadCast.BOARD,"sendBack");
            }
        });
        //新人活动
        bridgeWebView.registerHandler("intoActivityWebView", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("新人活动"+data);
                NEWACTIVITY newactivity =JSON.parseObject(data,NEWACTIVITY.class);
                Intent mintent=new Intent(context,UseguideActivity.class);
                mintent.putExtra("title",newactivity.getTitle());
                mintent.putExtra("url", newactivity.getUrl());
                context.startActivity(mintent);

            }
        });
        //进入七鱼客服页面
        bridgeWebView.registerHandler("openServiceChat", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if(!TextUtils.isEmpty(data)){
                    QIYUOUT qiyuout=JSON.parseObject(data,QIYUOUT.class);
                    ZApplication.getInstance().options(qiyuout.getCrmParams().getAvatar());
                    // appKey七鱼客服
                    Unicorn.init(context, ConfigAppkey.QIYU, ConfigFlag.ysfOptions, new GlideImageLoader(context));
                    setSource(qiyuout.getCrmParams().getName(),qiyuout.getCrmParams().getMobile(),"",qiyuout.getCrmParams().getGender(), "V"+ SingleOverAll.getInstance().getVersionName(ZApplication.getInstance().getApplicationContext())+"&&versionCode:"+ SingleOverAll.getInstance().getVersionCode(ZApplication.getInstance().getApplicationContext()),qiyuout.getCrmParams().getEntrance(),"品牌："+UtilsAll.getPhoneBrand()+"型号："+UtilsAll.getPhoneModel(),qiyuout.getCrmParams().getOrderId(),"android操作系统");
                    intoQiyu(context, Long.parseLong(qiyuout.getCrmParams().getGroupId()));
                }
            }
        });
        //复制粘贴板
        bridgeWebView.registerHandler("copyToClipboard", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                COPY copy=JSON.parseObject(data,COPY.class);
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(copy.getCopyName());
                ToastUtilsAll.getInstance().showShortToast("复制成功");
            }
        });
        //自定义统计S
        bridgeWebView.registerHandler("customStatistics", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                LogPrint.printError("进来了come on"+data);
                Umcustom umcustom=JSON.parseObject(data,Umcustom.class);
                switch (umcustom.getType()) {
                    case 0://友盟自定义事件
                        if(!TextUtils.isEmpty(umcustom.getEventID()))
                            MobclickAgent.onEvent(ZApplication.getContext(),umcustom.getEventID());
                        break;
                    case 1:
                        if(!TextUtils.isEmpty(umcustom.getEventID())&&!TextUtils.isEmpty(umcustom.getLabel()))
                            MobclickAgent.onEvent(ZApplication.getContext(),umcustom.getEventID(),umcustom.getLabel());
                        break;
                    case 2:
                        if(!TextUtils.isEmpty(umcustom.getEventID())&&!TextUtils.isEmpty(umcustom.getMap()))
                            LogPrint.printError("map1111111"+umcustom.getMap());
                        Map<String,Object> map = ZApplication.gson.fromJson(umcustom.getMap(), new TypeToken<HashMap<String,Object>>(){}.getType());
                        HashMap<String,String> hashmap = new HashMap<String,String>();
                        Set<String> get = map.keySet();
                        for (String key:get) {
                            hashmap.put(key,map.get(key)+"");
                        }
                        MobclickAgent.onEventValue(ZApplication.getContext(), umcustom.getEventID(),hashmap,2147483645);
                        break;
                }
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
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                    //跳登录
                    context.startActivity(new Intent(context, LoginActivity.class));
                }else {
                    IntentIntegrator intentIntegrator = new IntentIntegrator((Activity) context);
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
                LogPrint.printError("跳到原生商户二维码"+data);
                SHOPDETAILS shopdetails=JSON.parseObject(data,SHOPDETAILS.class);
                Intent mintent=new Intent(context, CodeDetailsActivity.class);
                mintent.putExtra("shops",shopdetails);
                context.startActivity(mintent);
            }
        });
        //图片浏览
        bridgeWebView.registerHandler("photoBrowser", new BridgeHandler() {
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
                        startImagePagerActivity(context, list, imageLook.getIndex(), new ImagePagerActivity.ImageSize(0, 0));
                    }
                }

            }
        });

    }
    /**************************************************************************************************/


    /**********************************************android推值给H5**************************************/
    /**
     * 登录成功后调用h5的方法
     */
    public void loginSuceess() {
        String tokenvalue = "";
        String inviteCode="";
        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            tokenvalue = PrefManager.getInstance().getToken();
        }
        if(!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
            inviteCode=PrefManager.getInstance().getInvite();
        }
        TOKEN token=new TOKEN();
        token.setToken(tokenvalue);
        token.setInviteCode(inviteCode);
        String json = ZApplication.gson.toJson(token);
        LogPrint.printError("loginsuceess"+json);
        //登录成功回调
        bridgeWebView.callHandler("userLoginSucc",json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("showtime");
            }
        });
    }

    /**
     * 安卓主动推经纬度给h5
     * @param la
     * @param lo
     */
    public void pushLatlngToJs(double la,double lo)
    {
        La_lo la_lo=new La_lo();
        la_lo.setLa(la);
        la_lo.setLo(lo);
        String json=ZApplication.gson.toJson(la_lo);
        LogPrint.printError(json);
        bridgeWebView.callHandler("pushLatlngToJs", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("安卓主动推经纬度给h5");
                //收到经纬度后停止定位
                if(locationApiUtils!=null){
                    locationApiUtils.stopLo();
                }
            }
        });
    }
    /**
     * 登出成功后调用h5的方法
     */
    public void logoutSuceess() {
        String tokenvalue = "";
        String inviteCode="";
        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            tokenvalue =PrefManager.getInstance().getToken();
        }
        if(!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
            inviteCode=PrefManager.getInstance().getInvite();
        }
        TOKEN token=new TOKEN();
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
    public void tellAndroidToJs() {
        ANDROIDHEIGT androidheigt=new ANDROIDHEIGT();
        LogPrint.printError("标题栏的高度为："+SingleOverAll.getInstance().getStatusBarHeight(context)+"tabbar的高度为："+SingleOverAll.getInstance().getNavigationBarHeight(context));
        androidheigt.setNavHeight(SingleOverAll.getInstance().getStatusBarHeight(context));
        androidheigt.setTabBarHeight(0);
        String json=ZApplication.gson.toJson(androidheigt);
        LogPrint.printError("状态栏高度："+json);
        //告诉js我是android
        bridgeWebView.callHandler("callNavBarAndTabBarHeight",json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("告诉js我是android");
            }
        });
    }

    /**
     * 支付宝支付状态回调（拼够和充值）alipayPayNotify
     * @param tag
     */
    public void tellAlapayToJs(String tag) {
        PAY pay=new PAY();
        pay.setPayState(tag);
        pay.setType( PrefManager.getInstance().getPayType());
        String json = ZApplication.gson.toJson(pay);
        bridgeWebView.callHandler("alipayPayNotify",json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("支付宝支付状态回调（拼够和充值）");
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
            }
        });
    }

    /**
     * 微信支付状态回调（拼够和充值）weChatPayNotify
     * @param tag
     */
    public void tellWechatToJs(String tag) {
        PAY pay=new PAY();
        pay.setPayState(tag);
        pay.setType( PrefManager.getInstance().getPayType());
        LogPrint.printError("tyoe"+ PrefManager.getInstance().getPayType());
        String json = ZApplication.gson.toJson(pay);
        LogPrint.printError("json"+json);
        bridgeWebView.callHandler("weChatPayNotify",json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("微信支付状态回调（拼够和充值）weChatPayNotify");
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
            }
        });
    }

    /**
     * 我的资料页面同步微信资料回调weChatAuthState
     * @param data
     */
    public void tellMyDataToJs(String data) {
        //从本地缓存中取出json字符串
        String json=PrefManager.getInstance().getWechateJson();
        WECHATTB wechattb=JSON.parseObject(json,WECHATTB.class);
        wechattb.getWexinResp().setIconurl(data);
        //指定新的服务器图片url
        String new_json=ZApplication.gson.toJson(wechattb);
        LogPrint.printError(new_json);
        bridgeWebView.callHandler("weChatAuthState",new_json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("我的资料页面同步微信资料回调weChatAuthState");
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
            }
        });
    }
    /**
     * 初始化主动推商品详情图片数组
     */
    public void  pushImages(String images) {
        ImageS_Z imageS_z=new ImageS_Z();
        imageS_z.setImages(images);
        String json = ZApplication.gson.toJson(imageS_z);
        LogPrint.printError(json);
        //登录成功回调
        LogPrint.printError("getImages" + json);
        bridgeWebView.callHandler("getImages", json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("初始化主动图片数组成功");
            }
        });
    }
    /**
     * 初始化主动token值给h5
     */
    public void  pushTokenToJs() {
        String tokenvalue = "";
        String inviteCode="";
        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            tokenvalue = PrefManager.getInstance().getToken();
        }
        if(!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
            inviteCode=PrefManager.getInstance().getInvite();
        }
        TOKEN token=new TOKEN();
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
     * @param json
     */
    public void uploadPictureSucc(String json) {
        LogPrint.printError("返回给h5的图片路径"+json);
        bridgeWebView.callHandler("uploadPictureSucc",json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("把图片上传的结果扔给h5");
                if( showCode_up.isShowing()){
                    showCode_up.dismiss();
                }                ToastUtilsAll.getInstance().showShortToast("图片上传成功");
                EventBus.getDefault().post(new EventBusMessageRefresh(0));

            }
        });
    }

    /**
     * 初始化主动图文详情
     */
    public void  getProductParams(String json) {
        //登录成功回调
        LogPrint.printError("getProductParams" + json);
        bridgeWebView.callHandler("getProductParams",json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("初始化主动图文详情规格成功");
            }
        });
    }


    /**
     * jpush通知事件
     * @param json
     */
    public void pushJiguangToJs(String json) {
        LogPrint.printError("通知h5极光推送"+json);
        bridgeWebView.callHandler("jpushMessageNotify",json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("jpush通知事件");
                //推送成功后清空数据
                PrefManager.getInstance().saveJGMsm("");
            }
        });
    }

    /**
     * 上传失败
     */
    public void upFailde() {
        if( showCode_up.isShowing()){
            showCode_up.dismiss();
        }
    }

    /**
     * /未读消息
     * @param qyMsgCount
     */
    public void qyUnReadMsgCount(int qyMsgCount) {
        QIYUCOUNT qiyucount=new QIYUCOUNT();
        qiyucount.setQyMsgCount(qyMsgCount);
        String  json=ZApplication.gson.toJson(qiyucount);
        bridgeWebView.callHandler("qyUnReadMsgCount",json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("qyUnReadMsgCount");
            }
        });
    }
    /**
     * 告诉H5版本号versionName
     */
    public void pushAppVersionToJs() {
        VERSION version=new VERSION();
        version.setVersionName(SingleOverAll.getInstance().getVersionName(ZApplication.getContext()));
        String  json=ZApplication.gson.toJson(version);
        bridgeWebView.callHandler("pushAppVersionToJs",json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("pushAppVersionToJs");
            }
        });
    }

    /**
     * 是否打开android原生七鱼客服
     */
    public void openQyNativeChat(){
        OPENQIYU openqiyu=new OPENQIYU();
        openqiyu.setIsNativeQy("YES");
        String  json=ZApplication.gson.toJson(openqiyu);
        bridgeWebView.callHandler("openQyNativeChat",json, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                LogPrint.printError("openQyNativeChat");
            }
        });
    }

    public void closeLocation(){
    }
    /**
     * android系统调用js的方法封装
     * @param webView_js
     */
    /***************************************************************************************************************************************/
    public void androidCallJs(WebView webView_js) {
        LogPrint.printError("test");
        String token= PrefManager.getInstance().getToken();
        LogPrint.printError("test"+token);
        webView_js.loadUrl("javascript:isorLogin('" + token + "')");
    }

    /**
     * 跳转到客服页面
     * @param context
     */
    public  void intoQiyu(Context context,long groupId){
        String title = "铺连铺客服";
        /**
         * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
         * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
         * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
         */
        String sourceUrl="https://pulpu.qiyukf.com";
        String sourceTitle="铺连铺";
        String custom="custom information string";
        ConsultSource source = new ConsultSource(sourceUrl, sourceTitle, custom);
        source.groupId=groupId;
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


    public void Destroy(){
//        if(bridgeWebView!=null){
//            LogPrint.printError("webview 被回收了");
//            bridgeWebView.destroy();
//        }
        if(myWebViewClient!=null){
            myWebViewClient.destroy();
        }
        /*********************清空缓存***********************/
        WebStorage.getInstance().deleteAllData();
        /***************************************************/
        stopLo();
    }

    /**
     * 设置来源
     */
    private void setSource(String real_name,String mobile_phone,String email,String gender,String app_version,String app_source,String device_info,String orderId,String device_sys) {
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
     * 加载失败
     */
    @Override
    public void LoadingFaild() {
        h5CallBack.loadingFaild();
    }

    /**
     * 加载成功
     */
    @Override
    public void LoadingOK() {
        h5CallBack.loadingSuccess();
    }

    /**
     * @param token
     * @param invate
     *
     */
    public void saveToken(String token,String invate){
        //1.拼接 JavaScript 代码
        String tokenUrl = "window.localStorage.setItem('t','" + token + "');";
        LogPrint.printError("吃🐔"+token);
        String invateUrl="window.localStorage.setItem('inviteCode','" + invate + "');";

        String tokenUrl_new = "javascript:(function({ var localStorage = window.localStorage; localStorage.setItem('t','" + token+ "') })()";
        String invateUrl_new = "javascript:(function({ var localStorage = window.localStorage; localStorage.setItem('inviteCode','" + invate+ "') })()";

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

    @Override
    public void location(double la, double lo) {
        pushLatlngToJs(la,lo);
    }

    /**
     * 跳转到图片浏览器
     * @param context
     * @param imgUrls
     * @param position
     * @param imageSize
     */
    public  void  startImagePagerActivity(Context context, List<String> imgUrls, int position, ImagePagerActivity.ImageSize imageSize) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putStringArrayListExtra(ImagePagerActivity.INTENT_IMGURLS, new ArrayList<String>(imgUrls));
        intent.putExtra(ImagePagerActivity.INTENT_POSITION, position);
        intent.putExtra(ImagePagerActivity.INTENT_IMAGESIZE, imageSize);
        context.startActivity(intent);
    }
    /**
     * 停止定位
     */
    public  void  stopLo(){
        //定位到当前城市区域
        if(locationApiUtils!=null){
            locationApiUtils.stopLo();
        }
    }
    /**
     * 权限申请
     */
    public void requestLoacation() {
        PermissionsUtil.requestPermission(ZApplication.getContext(), new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                //定位到当前城市区域
                if(locationApiUtils!=null){
                  locationApiUtils.startLo();
                }
            }
            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                LogPrint.printError("定位权限被拒绝了");
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);
    }
}
/**
 * 自定义回调
 */
class myHadlerCallBack extends DefaultHandler {
    @Override
    public void handler(String data, CallBackFunction function) {
        if (function != null) {
            LogPrint.printError(data);
        }
    }

    /**
     * 加载弹框
     *
     * @return
     */
    private Dialog createLoadingDialogPLP(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_plp, null);// 得到加载view
        ImageView plp_img = v.findViewById(R.id.plp_img);
        ((AnimationDrawable) plp_img.getBackground()).start();
        LinearLayout layout = v.findViewById(R.id.dialog_loading_view);// 加载布局
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle_plp);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        return loadingDialog;

    }
}
