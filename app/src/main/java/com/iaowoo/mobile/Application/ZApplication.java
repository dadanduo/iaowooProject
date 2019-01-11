package com.iaowoo.mobile.Application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.Utils.Glide.GlideImageLoader;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.Weex.ImageAdapter;
import com.iaowoo.mobile.Weex.extend.compontent.PPHudModule;
import com.iaowoo.mobile.Weex.extend.module.PPPoPViewModule;
import com.iaowoo.mobile.Weex.extend.module.PPWxEventModule;
import com.iaowoo.mobile.Weex.extend.module.dateTimePicker;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.im.MyConversationClickListener;
import com.iaowoo.mobile.im.MyExtensionModule;
import com.iaowoo.mobile.im.MyReceiveMessageListener;
import com.iaowoo.mobile.im.RedPacketMessage;
import com.iaowoo.mobile.im.RedPacketProvider;
import com.lidroid.xutils.HttpUtils;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;
import com.qiyukf.unicorn.api.OnBotEventListener;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.UICustomization;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;

import org.litepal.LitePal;
import org.xutils.x;

import java.io.File;
import java.util.List;
import java.util.Stack;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * 主项目中的application
 */
public class ZApplication extends Application {
    /**
     * 解决极光bug
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        super.attachBaseContext(base);
    }

    /**
     * 是否为发布状态  true:发布模式（线上环境）  false:debug模式（测试环境）
     */
    public static boolean isRelease = UtilsOkHttpAll.ENV_TYPE == 2;
    /**
     * gson解析
     */
    public static Gson gson;
    /**
     * application
     */
    private static ZApplication zApplication;
    /**
     * xutils网络请求模块
     */
    private HttpUtils http;

    public static int ALL_TAG = 0;

    public static int ALL_TAG_I = 0;


    /**
     * 比对是否是商家二维码的字段
     */
    public static String SCAN_SHOP_NAME = "shbs008.com";
    /**
     * 是否显示沉淀状态栏
     */
    public final static boolean isShowHide = true;
    /**
     * 极光推送registerid
     */
    public static String REGISTERID = "";
    /**
     * 账号被其他设备登陆通知code
     */
    public final static String CODE = "10101";
    /**
     * 账号失效
     */
    public final static String CODE_LOSE = "10102";
    /**
     * 二维码扫描提示文字
     */
    public final static String CODE_TEXT = "对准二维码到框内即可扫描";
    /**
     * weex页面
     */
    public static final String BC_ACTION_RENDER_NET_JS = "http://192.168.1.254:8081/dist/js//views/order/index.js";
    /**
     * weex本地页面
     */
    public static final String LOCAL_FILE_SCHEMA = "file://";
    /**
     * 图片的缓存路径
     */
    public static String PATHURL = "";

    public static String DB = "";

    public static final String SEARCH_TAG = "searchs";

    private RefWatcher refWatcher;

    private static Context context;


    /**
     * xutilsDb路径
     */
    public static String SD_PATH = "";

    /**
     * 数据库名字
     */
    public static final String DATABASE_NAME = "PLP_NEW.db";

    public static String JSLib = "js_Libs";


    public Stack<Activity> mList = new Stack<>();

    //    private RefWatcher refWatcher;
    public static ZApplication getInstance() {
        return zApplication;
    }

    /**
     * @return
     */
    public HttpUtils getHttp() {
        return http;
    }

    /**
     * 将启动的进程添加进入list中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    /**
     * 将list中的activity全部销毁
     */
    public void exit() {
        for (Activity activity : mList) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 杀进程
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogPrint.printError("杀进程");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化融云IM
        initRongIM();
        // LitePal数据库初始化
        LitePal.initialize(this);
        //weex配置
        InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this, config);
        /**********************************************weex交互注册start*********************************************/
        try {
            WXSDKEngine.registerModule("PPWxEventModule", PPWxEventModule.class);
            WXSDKEngine.registerModule("PPHudModule", PPHudModule.class);
            WXSDKEngine.registerModule("PPPopViewModule", PPPoPViewModule.class);
            WXSDKEngine.registerModule("dateTimePicker", dateTimePicker.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
        /**********************************************weex交互注册end*********************************************/

        //新版本
        //初始化sharedPreferences
        zApplication = this;
        context = this.getApplicationContext();
        gson = new Gson();
        http = new HttpUtils();
        x.Ext.init(this);
        SD_PATH = this.getExternalCacheDir() + "/plp_db";
        /**************************************************单列开启*******************************************/
        //shrefr存储
        PrefManager.getInstance();
        //网络单列
        XutilsHttp.getInstance();
        //okhttp
        OkhttpManager.getInstance(zApplication.getApplicationContext());
        //全局单列适配器
        SingleOverAll.getInstance();
        //弹框
        ToastUtilsAll.getInstance();
        //数据库
        XutilsDBManage.getInstance().Instance();
        /*****************************************************************************************************/
        File cacheDir = SingleOverAll.getInstance().getDiskCacheDir(this, "bitmap");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
            LogPrint.printError("路径:" + cacheDir.getAbsolutePath());
            PATHURL = cacheDir.getAbsolutePath();
        }

        File cacheDirDB = SingleOverAll.getInstance().getDiskCacheDir(this, "db");
        if (!cacheDirDB.exists()) {
            cacheDirDB.mkdirs();
            LogPrint.printError("路径数据库:" + cacheDirDB.getAbsolutePath());
            DB = cacheDirDB.getAbsolutePath();
        }
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        if (isRelease) {
            Config.DEBUG = false;
            ConfigFlag.IS_DEBUG = false;
            ConfigFlag.ISSHWODILOGO = false;
            //极光推送初始化
            JPushInterface.setDebugMode(false);    // 设置开启日志,发布时请关闭日志
            //um 统计初始化
            UMConfigure.setLogEnabled(false);
            x.Ext.setDebug(false); //输出debug日志，开启会影响性能
        } else {
            Config.DEBUG = true;
            ConfigFlag.IS_DEBUG = true;
            ConfigFlag.ISSHWODILOGO = true;
            //极光推送初始化
            JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
            //um 统计初始化
            UMConfigure.setLogEnabled(true);
            x.Ext.setDebug(true); //输出debug日志，开启会影响性能
            //测试环境开始检测内存泄漏leakcanary
            setupLeakCanary();
            //初始化捕获异常
//            CrashHanlder.getInstance().init(this);
        }
        JPushInterface.init(this);            // 初始化 JPush
        REGISTERID = JPushInterface.getRegistrationID(this);
        LogPrint.printError("1099" + "run:--------->registrationId： " + REGISTERID);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android 6.0 及以上的系统上，需要去请求一些用到的权限，JPush SDK 用到的一些需要请求如下权限，因为需要这些权限使统计更加精准，功能更加丰富，建议开发者调用。
            JPushInterface.requestPermission(this);
        }
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        //对应平台没有安装的时候跳转转到应用商店下载
        Config.isJumptoAppStore = true;

        //微信分享
        PlatformConfig.setWeixin(ConfigAppkey.WEIXIN_APP_ID, ConfigAppkey.WEIXIN_APP_SECRET);
        //QQ分享
        PlatformConfig.setQQZone(ConfigAppkey.QQ_APP_ID, ConfigAppkey.QQ_APP_KEY);
        //新浪微博分享(第三个参数为回调地址)
        PlatformConfig.setSinaWeibo(ConfigAppkey.SINAWEIBO_APP_KEY, ConfigAppkey.SINAWEIBO_APP_SECRET, ConfigAppkey.SINAWEIBO_REDIRECT_URL);

        //初始化捕获异常
//        CrashHanlder.getInstance().init(this);
        // appKey七鱼客服
        Unicorn.init(this, ConfigAppkey.QIYU, options(ConfigH5Url.ImageUrl), new GlideImageLoader(this));
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });

        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            LogPrint.printError("你需要的token:" + PrefManager.getInstance().getToken());
            String passwordtwo = UtilsAll.encryptionPasswordString("18011009889123456");
            LogPrint.printError("加密测试:" + passwordtwo);
        }

    }

    /**
     * 获取全局的context
     *
     * @return 返回全局context对象
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 七鱼配置
     * 如果返回值为null，则全部使用默认参数。
     *
     * @return
     */
    public YSFOptions options(String image) {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
//      options.statusBarNotificationConfig.notificationSmallIconId = R.mipmap.head_portrait;
        options.uiCustomization = new UICustomization();
        options.uiCustomization.rightAvatar = image;
        //标题居中
        options.uiCustomization.titleCenter = true;
        options.onBotEventListener = new OnBotEventListener() {
            @Override
            public boolean onUrlClick(Context context, String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(intent);
                return true;
            }
        };
        ConfigFlag.ysfOptions = options;
        return options;
    }

    /**
     * 内存泄漏检测
     */
    protected void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    private void initRongIM() {
        RongIM.init(this);
        setMyExtensionModule();
        RongIM.registerMessageType(RedPacketMessage.class);
        RongIM.registerMessageTemplate(new RedPacketProvider());
        RongIM.setConnectionStatusListener(new MyConnectionStatusListener());
        RongIM.setOnReceiveMessageListener(new MyReceiveMessageListener());
        RongIM.setConversationClickListener(new MyConversationClickListener());
    }

    public void setMyExtensionModule() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new MyExtensionModule());
            }
        }
    }

    private class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {
        @Override
        public void onChanged(ConnectionStatus connectionStatus) {

            switch (connectionStatus) {

                case CONNECTED://连接成功。

                    break;
                case DISCONNECTED://断开连接。

                    break;
                case CONNECTING://连接中。

                    break;
                case NETWORK_UNAVAILABLE://网络不可用。

                    break;
                case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                    Toast.makeText(getApplicationContext(), "被踢出了", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }


}
