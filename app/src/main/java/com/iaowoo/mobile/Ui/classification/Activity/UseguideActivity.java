package com.iaowoo.mobile.Ui.classification.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.JsMethod;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.JsMethod;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.View.FrameAnimation;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;

import java.lang.ref.SoftReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户条约(活动)(注册协议)公用
 * Created by chenda on 2018/4/19.
 */
public class UseguideActivity extends TitleActivity implements ClickCallBack,H5CallBack {
    @BindView(R.id.user_guide)
    BridgeWebView user_guide;
    @BindView(R.id.nowifi)
    FrameLayout nowifi;
    @BindView(R.id.re_loading)
    Button re_loading;
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

    /**
     * 跳一跳
     */
    @BindView(R.id.Jump_jump)
    RelativeLayout Jump_jump;

    /**
     * loading
     */
    @BindView(R.id.plp_img)
    ImageView plp_img;


    private Webset webset;
    private GlobalBroadcastReceiver globalBroadcastReceiver;
    JsMethod jsMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_guide);
        ButterKnife.bind(this);
//        dialogUtils = new SoftReference<>(new DialogUtils());
//        if(dialogUtils.get()!=null){
//            dialog=dialogUtils.get().createLoadingDialog(this,"",false);
//        }else{
//            dialogUtils = new SoftReference<>(new DialogUtils());
//            dialog=dialogUtils.get().createLoadingDialog(this,"",false);
//        }
        rdioBroadCast=new RdioBroadCast();
        String title=getIntent().getStringExtra("title");
        final String url=getIntent().getStringExtra("url");
        LogPrint.printError("🐔"+url);
        setTitle(title);
        showBackwardView_show(true);
        setBoreturn();
        UseguideAllH5();
        mFrameAnimation = new SoftReference<>(new FrameAnimation(plp_img, mImgResIds, 50, true));
        createLoadingDialogPLP();
        webset=new Webset(this,user_guide,this);
        webLoading(url);
        setOnclick(this);
        re_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webLoading(url);
            }
        });
        jsMethod=new JsMethod();
        jsMethod.h5GoBack(user_guide);
        webset.pushTokenToJs();
    }
    @Override
    public void OnClick() {
        // 返回上一页面
        if(user_guide.canGoBack()) {
            user_guide.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            user_guide.goBack();
        } else {
            finish();
        }
    }
    @Override
    public void loadingFaild() {
        LogPrint.printError("加载失败");
        nowifi.setVisibility(View.VISIBLE);
        user_guide.setVisibility(View.GONE);
        closeD();
    }
    @Override
    public void loadingSuccess() {
        nowifi.setVisibility(View.GONE);
        LogPrint.printError("加载成功");
        user_guide.setVisibility(View.VISIBLE);
        closeD();
    }
    public void webLoading(final String url){
        SingleOverAll.getInstance().abnormal(UseguideActivity.this, new SingleOverAll.LoadingCallBack() {
            @Override
            public void haveWifi() {
                nowifi.setVisibility(View.GONE);
                user_guide.setVisibility(View.VISIBLE);
                webset.JsBridge(url);
            }
            @Override
            public void noHaveWifi() {
                nowifi.setVisibility(View.VISIBLE);
                user_guide.setVisibility(View.GONE);
            }
        });
    }
    public void  UseguideAllH5()
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
        rdioBroadCast.regiseterBroad(this,globalBroadcastReceiver,RdioBroadCast.BOARD);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rdioBroadCast.unRegiseterBroad(this,globalBroadcastReceiver);
//        rdioBroadCast=null;
        closeD();
        webset.Destroy();
        webset=null;
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
}
