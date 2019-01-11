package com.iaowoo.mobile.umeng;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.BaseActivity;
import com.iaowoo.mobile.Ui.classification.Activity.HomePageActivity;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.View.FrameAnimation;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LocationApiUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionListener;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionsUtil;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnreadCountChangeListener;

import java.lang.ref.SoftReference;

/**
 * 分享之深度链接唤醒界面(H5 唤醒 App跳转到此界面)
 *
 * @author Andy
 * @date 2018-5-17
 */
public class WBCallAppActivity extends BaseActivity implements H5CallBack,LocationApiUtils.LocationCallBack {

    private BridgeWebView web_commodity;
    Webset webset;
    private FrameLayout nowifi;
    private Button re_loading;
    private GlobalBroadcastReceiver globalBroadcastReceiver;
    private LinearLayout ll_bar;
    /**
     * 跳一跳
     */
    private RelativeLayout Jump_jump;

    /**
     * loading
     */
    private ImageView plp_img;


    private String key = "1";

    RdioBroadCast rdioBroadCast;

    private Dialog dialog;
    private SoftReference<DialogUtils> dialogUtils;

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

    private SoftReference<LocationApiUtils> locationApiUtils;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commodity);

//        dialogUtils = new SoftReference<>(new DialogUtils());
//        if(dialogUtils.get()!=null){
//            dialog=dialogUtils.get().createLoadingDialog(this,"",false);
//        }else{
//            dialogUtils = new SoftReference<>(new DialogUtils());
//            dialog=dialogUtils.get().createLoadingDialog(this,"",false);
//        }

        //接受从H5 传递过来的参数\
        Uri uri = getIntent().getData();
        key = uri.getQueryParameter("key");
        String data = uri.toString();
        final String url = data.substring(data.indexOf("url") + "url=".length());
        LogPrint.printError("key=" + key + ",url=" + url);
        locationApiUtils = new SoftReference<>(new LocationApiUtils());
        locationApiUtils.get().setInterface(this);
        //需要定位
        if(url.contains("/signout/recom_detail")){
            LogPrint.printError("定位来了哈哈哈");
            requestLoacation();
        }
        web_commodity = findViewById(R.id.web_commodity);
        re_loading = findViewById(R.id.re_loading);
        nowifi = findViewById(R.id.nowifi);
        webset = new Webset(this, web_commodity, this);
        webLoading(url);
        re_loading = findViewById(R.id.re_loading);
        nowifi = findViewById(R.id.nowifi);
        ll_bar = findViewById(R.id.ll_bar);
        Jump_jump=findViewById(R.id.Jump_jump);
        plp_img=findViewById(R.id.plp_img);
        rdioBroadCast=new RdioBroadCast();

        mFrameAnimation = new SoftReference<>(new FrameAnimation(plp_img, mImgResIds, 50, true));
        //调用弹框
        createLoadingDialogPLP();

        this.allState();
//        initState(R.id.ll_bar);
//        ll_bar.setBackgroundColor(getResources().getColor(R.color.colortablebg));
        webset.openQyNativeChat();
        //告诉js状态栏和tabbar的高度
        webset.tellAndroidToJs();
        //主动推给H5token
        webset.pushTokenToJs();
        //极光消息监控
        addUnreadCountChangeListener(true);
        returnH5();
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

    @Override
    public void loadingFaild() {
        LogPrint.printError("加载失败");
        nowifi.setVisibility(View.VISIBLE);
        web_commodity.setVisibility(View.GONE);
        closeD();
    }

    @Override
    public void loadingSuccess() {
        nowifi.setVisibility(View.GONE);
        LogPrint.printError("加载成功");
        web_commodity.setVisibility(View.VISIBLE);
        closeD();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        web_commodity.destroy();
        closeD();
        stopLo();
        addUnreadCountChangeListener(false);
        rdioBroadCast.unRegiseterBroad(WBCallAppActivity.this, globalBroadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        Log.e(getClass().getName(), "onBackPressed()");
        onBack();
        super.onBackPressed();
    }

    private void onBack() {
        Intent intent = new Intent();
        intent.setClass(WBCallAppActivity.this, HomePageActivity.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }

    public void  returnH5()
    {
        globalBroadcastReceiver=new GlobalBroadcastReceiver(new BroadcastCallBack() {
            @Override
            public void ReceiverData(String tag, String data) {
                switch (tag) {
                    case "sendBack":
                        onBackPressed();
                          break;
                        //登陆成功
                    case "login":
                        LogPrint.printError("登陆成功" + data);
                        webset.loginSuceess();
                        break;
                    //被登出成功
                    case "logout":
                        LogPrint.printError("登出成功" + data);
                        webset.logoutSuceess();
                        break;
                    //图片上传失败
                    case "uploadingFaild":
                        webset.upFailde();
                        break;
                    //图片上传成功
                    case "uploadingSucess":
                        if (!TextUtils.isEmpty(data))
                            LogPrint.printError(data);
                        webset.uploadPictureSucc(data);
                        break;
                    //上传图片取消
                    case "cancel":
                        webset.upFailde();
                        break;
                    case "paySucess":
                        if (data.equals("0"))
                            //微信支付成功
                            webset.tellWechatToJs("1");
                        else
                            //支付宝支付成功
                            webset.tellAlapayToJs("1");
                        break;
                    case "payFaild":
                        //微信支付失败
                        if (data.equals("0"))
                            //微信支付失败
                            webset.tellWechatToJs("0");
                        else
                            //支付宝支付失败
                            webset.tellAlapayToJs("0");
                        break;
                    //微信同步
                    case "wechatSynchronization":
                        if (!TextUtils.isEmpty(data))
                            webset.tellMyDataToJs(data);
                        break;
                    //极光推送
                    case "jiguang":
                        webset.pushJiguangToJs(data);
                        break;

                    default:
                        break;
                }
            }
        });
        rdioBroadCast.regiseterBroad(WBCallAppActivity.this,globalBroadcastReceiver,RdioBroadCast.BOARD);
    }


    public void webLoading(final String url){
        SingleOverAll.getInstance().abnormal(WBCallAppActivity.this, new SingleOverAll.LoadingCallBack() {
            @Override
            public void haveWifi() {
                nowifi.setVisibility(View.GONE);
                web_commodity.setVisibility(View.VISIBLE);
                webset.JsBridge(url);
            }
            @Override
            public void noHaveWifi() {
                nowifi.setVisibility(View.VISIBLE);
                web_commodity.setVisibility(View.GONE);
            }
        });
    }
    public void closeD(){
      stopLoading();
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
        if(dialog!=null){
            dialog.cancel();
        }
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
     * 停止定位
     */
    private void stopLo() {
        //定位到当前城市区域
        if (locationApiUtils.get() != null) {
            locationApiUtils.get().stopLo();
        }
    }

    @Override
    public void location(double la, double lo) {
        webset.pushLatlngToJs(la, lo);
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
                if (locationApiUtils!= null) {
                    locationApiUtils.get().startLo();
                }
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                LogPrint.printError("定位权限被拒绝了");
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);
    }
}