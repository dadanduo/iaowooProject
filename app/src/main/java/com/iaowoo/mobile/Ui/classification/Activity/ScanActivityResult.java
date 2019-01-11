package com.iaowoo.mobile.Ui.classification.Activity;

import android.app.Dialog;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.View.FrameAnimation;
import com.iaowoo.mobile.Utils.LogPrint;

import java.lang.ref.SoftReference;

public class ScanActivityResult extends BaseActivity implements H5CallBack {

    private BridgeWebView scan_result;
    private Webset webset;
    private LinearLayout show_scan;
    private TextView scan_result_text;
    private FrameLayout nowifi;
    private Button re_loading;
    private Dialog dialog;
    private GlobalBroadcastReceiver globalBroadcastReceiver=null;
    /**
     * 跳一跳
     */
    private RelativeLayout Jump_jump;

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
     * loading
     */
    private ImageView plp_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_result);
        scan_result=findViewById(R.id.scan_result);
        show_scan=findViewById(R.id.show_scan);
        re_loading=findViewById(R.id.re_loading);
        nowifi=findViewById(R.id.nowifi);
        scan_result_text=findViewById(R.id.scan_result_text);
        Jump_jump=findViewById(R.id.Jump_jump);
        plp_img=findViewById(R.id.plp_img);
        webset=new Webset(this,scan_result,this,"ok");
        mFrameAnimation = new SoftReference<>(new FrameAnimation(plp_img, mImgResIds, 50, true));
        createLoadingDialogPLP();

//        dialogUtils = new SoftReference<>(new DialogUtils());
//        if(dialogUtils.get()!=null){
//            dialog=dialogUtils.get().createLoadingDialog(this,"",false);
//        }else{
//            dialogUtils = new SoftReference<>(new DialogUtils());
//            dialog=dialogUtils.get().createLoadingDialog(this,"",false);
//        }

        final String url=getIntent().getStringExtra("url");
        LogPrint.printError("路径:"+ ZApplication.SCAN_SHOP_NAME);
        pulianpuBroadCast();
        if(url.contains( ZApplication.SCAN_SHOP_NAME))
        {
            scan_result.setVisibility(View.VISIBLE);
            show_scan.setVisibility(View.GONE);
            webset.JsBridge(url+"&from=App");
        }else {
            stopLoading();
            scan_result.setVisibility(View.GONE);
            show_scan.setVisibility(View.VISIBLE);
            scan_result_text.setText(url);
        }
        webset.pushTokenToJs();
        re_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webset.JsBridge(url+"&from=App");
            }
        });


    }


    @Override
    public void loadingFaild() {
        stopLoading();
        nowifi.setVisibility(View.VISIBLE);
        scan_result.setVisibility(View.GONE);
    }

    @Override
    public void loadingSuccess() {
        stopLoading();
        nowifi.setVisibility(View.GONE);
        scan_result.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLoading();
        scan_result.destroy();
        if(globalBroadcastReceiver!=null) {
            unregisterReceiver(globalBroadcastReceiver);
        }
    }

    /**
     * 广播
     */
    private void pulianpuBroadCast()
    {
        //注册全局广播
        globalBroadcastReceiver=new GlobalBroadcastReceiver(new BroadcastCallBack() {
            @Override
            public void ReceiverData(String tag,String data) {
                switch (tag)
                {
                    case "sendBack":
                        finish();
                        break;
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

        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(RdioBroadCast.BOARD);
        registerReceiver(globalBroadcastReceiver, intentFilter);
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
