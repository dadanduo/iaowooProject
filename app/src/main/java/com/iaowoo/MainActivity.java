package com.iaowoo;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.J_push.J_edit;
import com.iaowoo.mobile.Ui.classification.Activity.BaseBufferActivity;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Model.StartPage;
import com.iaowoo.mobile.Ui.classification.Model.VERSION;
import com.iaowoo.mobile.Utils.CacheUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.Tools;
import com.iaowoo.mobile.Utils.UtilsTimer;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.umeng.WBCallNewAppActivity;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.J_push.J_edit;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.BaseBufferActivity;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Model.StartPage;
import com.iaowoo.mobile.Ui.classification.Model.VERSION;
import com.iaowoo.mobile.Utils.CacheUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.Tools;
import com.iaowoo.mobile.Utils.UtilsTimer;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.umeng.WBCallNewAppActivity;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnreadCountChangeListener;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}(主页面)
 * @date 2018/6/4
 */
public class MainActivity extends BaseBufferActivity implements H5CallBack {
    @BindView(R.id.bridge)
    BridgeWebView bridge;
    @BindView(R.id.nowifi)
    FrameLayout nowifi;
    @BindView(R.id.re_loading)
    Button re_loading;
    @BindView(R.id.ll_bar)
    LinearLayout ll_bar;
    @BindView(R.id.start_page)
    FrameLayout start_page;
    @BindView(R.id.image_start)
    ImageView image_start;
    @BindView(R.id.goon)
    Button goon;
    @BindView(R.id.web)
    LinearLayout web;
    private GlobalBroadcastReceiver globalBroadcastReceiver;
    private Dialog showCode;
    private CacheUtils cacheUtils=null;
    private StartPage startPage;
    private Webset webset;
    private int TIME=5;
    private UtilsTimer utilsTimer;
    private RdioBroadCast rdioBroadCast;
    /**
     * 加载是否成功
     */
    private boolean isOkLoading=false;
    @Override
    public int getLayoutResId() {
        return R.layout.bridge_webview;
    }

    @Override
    protected void initData() {
        super.initData();
        rdioBroadCast=new RdioBroadCast();
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //扣扣分享权限的适配开启需要用到所有的权限
        this.adaptive();
        //全屏显示
        this.allState();
        /****************磁盘缓存开启*************/
        cacheUtils=new CacheUtils();
        /****************************************/
        webset=new Webset(MainActivity.this,bridge,this,cacheUtils);
        //重新加载
        re_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webLoading(ConfigH5Url.BAOSHI_URL);
            }
        });
        //告诉js状态栏和tabbar的高度
        webset.tellAndroidToJs();
        //主动推给H5token
        webset.pushTokenToJs();
        pulianpuBroadCast();
        String msg=PrefManager.getInstance().getJGMsm();
        LogPrint.printError(msg);
        if (!TextUtils.isEmpty(msg)) {
            LogPrint.printError("有极光消息需要处理");
            J_edit j_edit=new J_edit();
            j_edit.setCostomMsg1(msg,MainActivity.this);
        }
        //android8.0以后版本通知栏的适配
//        NotificationUtil.initNotificationChannel(getApplicationContext());
        // 获取本版本号，是否更新
        Tools tools=new Tools();
        int vision =tools.getVersion(this);
        LogPrint.printError("版本号："+vision);
        tools.getVersionStart(vision,this);
        //极光消息监控
        addUnreadCountChangeListener(true);
        //1.0.4 和 1.0.3 的界限pushappversiontojs表示是1.0.4
//        webset.pushAppVersionToJs();
        webset.openQyNativeChat();
        //版本控制（监控器）
        XutilsHttp.getInstance().getViewShowOrHidden("Android", SingleOverAll.getInstance().getVersionName(MainActivity.this), new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                VERSION version= JSON.parseObject(json,VERSION.class);
                if(version.getCode().equals(OkhttpManager.SUCESS)){
                    PrefManager.getInstance().setIsReview(version.getBody().getContent().getIsReview()+"");
                }
            }
            @Override
            public void OnFaild(String faildM) {
            }
        });
// /****************启动广告页面（倒计时3秒）获得图片路径***********************************************************************************************************/
        XutilsHttp.getInstance().getStartPage(12, "App", new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                startPage=JSON.parseObject(json,StartPage.class);
                LogPrint.printError("显示"+json);
                if(startPage!=null) {
                    if (startPage.getCode().equals(OkhttpManager.SUCESS)) {
                        //设置广告页面显示
                        if (startPage.getBody().getContent().getList().size()!=0) {
                            final String url = startPage.getBody().getContent().getList().get(0).getBannerImg();
                            if (!TextUtils.isEmpty(url)) {
                                PrefManager.getInstance().setStartUrl(url);
                                PrefManager.getInstance().setAdShow("show");
                            }
                        }
                        //设置广告页面不显示
                        else{
                            PrefManager.getInstance().setAdShow("");
                        }
                    }
                }
            }
            @Override
            public void OnFaild(String faildM) {
                ToastUtilsAll.getInstance().showShortToast("网络异常");
            }
        });
        //广告页图片的点击事件
        image_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPage.BodyBean.ContentBean.ListBean listBean=startPage.getBody().getContent().getList().get(0);
                String url="";
                if(startPage.getCode().equals(OkhttpManager.SUCESS)) {
                    if (listBean != null) {
                        if(listBean.getBusinessType() == null){
                            url = listBean.getJumpUrl() + "?t=" + listBean.getTitle() + "&form=share";
                        } else {
                            url = ConfigH5Url.HTTP_H5 + "/#/home/product_details?type=" + listBean.getBusinessType() + "&templateId=" + listBean.getBusinessId() + "&form=share";
                        }
                        Intent mintent=new Intent(MainActivity.this, WBCallNewAppActivity.class);
                        mintent.putExtra("url",url);
                        startActivity(mintent);
                    }
                }

            }
        });
        //跳过
        goon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_page.setVisibility(View.GONE); //调用弹框
                web.setVisibility(View.VISIBLE);
                utilsTimer.destoryTimer();
            }
        });
        //第二次才会进入
        if(!TextUtils.isEmpty(PrefManager.getInstance().getStartUrl())) {
            if (!TextUtils.isEmpty(PrefManager.getInstance().getAdShow())) {
                //显示广告页面
                start_page.setVisibility(View.VISIBLE);
                //倒计时
                utilsTimer = new UtilsTimer(goon, new UtilsTimer.UtilsTimerCallBack() {
                    @Override
                    public void timeOver() {
                        start_page.setVisibility(View.GONE); //调用弹框
                        web.setVisibility(View.VISIBLE);
                        //广告结束后如果还没加载完成显示loading
                        if (!isOkLoading) {
                        }
                    }
                });
                utilsTimer.goOn(0, 1000, TIME);
                GlideUtils glideUtils=new GlideUtils();
                //渲染图片
                glideUtils.glides(MainActivity.this, PrefManager.getInstance().getStartUrl(), image_start);
            }else{
                //后台没有设置广告页面不显示广告页面
                start_page.setVisibility(View.GONE);
            }
        }
        //第一次进来显示loading并隐藏广告页面
        else {
            web.setVisibility(View.VISIBLE);
        }
        webLoading(ConfigH5Url.BAOSHI_URL);

        /********************************************************************************************************************************************************/
    }

    // 退出界面时，必须撤销，以免造成资源泄露
    final UnreadCountChangeListener listener = new UnreadCountChangeListener() { // 声明一个成员变量
        @Override
        public void onUnreadCountChange(int count) {
            // 在此更新界面, count 为当前未读数，
            // 也可以用 Unicorn.getUnreadCount() 获取总的未读数
            webset.qyUnReadMsgCount(count);
        }
    };

    private void addUnreadCountChangeListener(boolean add) {
        Unicorn.addUnreadCountChangeListener(listener, add);
    }
    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK &&  bridge.canGoBack()) {
            // 返回上一页面
            bridge.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            bridge.goBack();
            return true;
        }else if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
        {
            this.exit();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 加载失败
     */
    @Override
    public void loadingFaild() {
        LogPrint.printError("加载失败");
        nowifi.setVisibility(View.VISIBLE);
        bridge.setVisibility(View.GONE);
        closeD();
    }

    /**
     * 加载成功
     */
    @Override
    public void loadingSuccess() {
        nowifi.setVisibility(View.GONE);
        LogPrint.printError("加载成功");
        bridge.setVisibility(View.VISIBLE);
        closeD();
    }

    /**
     * 广播
     */
    public void pulianpuBroadCast() {
        //注册全局广播
        globalBroadcastReceiver=new GlobalBroadcastReceiver(new BroadcastCallBack() {
            @Override
            public void ReceiverData(String tag,String data) {
                switch (tag)
                {
                    //登陆成功
                    case "login":
                        LogPrint.printError("登陆成功"+data);
                        webset.loginSuceess();
                        break;
                    //被登出成功
                    case "logout":
                        LogPrint.printError("登出成功"+data);
                        webset.logoutSuceess();
                        break;
                    //图片上传失败
                    case  "uploadingFaild":
                        webset.upFailde();
                        break;
                    //图片上传成功
                    case  "uploadingSucess":
                        if(!TextUtils.isEmpty(data))
                            LogPrint.printError(data);
                        webset.uploadPictureSucc(data);
                        break;
                    //上传图片取消
                    case  "cancel":
                        webset.upFailde();
                        break;
                    case  "paySucess":
                        if(data.equals("0"))
                            //微信支付成功
                            webset.tellWechatToJs("1");
                        else
                            //支付宝支付成功
                            webset.tellAlapayToJs("1");
                        break;
                    case "payFaild":
                        //微信支付失败
                        if(data.equals("0"))
                            //微信支付失败
                            webset.tellWechatToJs("0");
                        else
                            //支付宝支付失败
                            webset.tellAlapayToJs("0");
                        break;
                    //微信同步
                    case  "wechatSynchronization":
                        if(!TextUtils.isEmpty(data))
                            webset.tellMyDataToJs(data);
                        break;
                    //极光推送
                    case  "jiguang":
                        webset.pushJiguangToJs(data);
                        break;
                    default:
                        break;
                }
            }
        });
      rdioBroadCast.regiseterBroad(MainActivity.this,globalBroadcastReceiver,RdioBroadCast.BOARD);
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
        closeD();
        //磁盘缓存关闭
        cacheUtils.destroy();
        addUnreadCountChangeListener(false);
        rdioBroadCast.unRegiseterBroad(MainActivity.this,globalBroadcastReceiver);
        //防止内存泄漏
        UMShareAPI.get(this).release();
        webset.Destroy();

    }
    /**
     * 关闭弹窗
     */
    public void closeD(){
           if(showCode.isShowing()){
               showCode.dismiss();
           }
            isOkLoading=true;
    }
    /**
     * 加载路径
     * @param url
     */
    public void webLoading(final String url){
       SingleOverAll.getInstance().abnormal(MainActivity.this, new SingleOverAll.LoadingCallBack() {
            @Override
            public void haveWifi() {
                nowifi.setVisibility(View.GONE);
                bridge.setVisibility(View.VISIBLE);
                webset.JsBridge(url);
            }
            @Override
            public void noHaveWifi() {
                closeD();
                nowifi.setVisibility(View.VISIBLE);
                bridge.setVisibility(View.GONE);
            }
        });
    }
}
