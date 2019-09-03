package com.iaowoo.mobile.H5toAndroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;

import com.alibaba.fastjson.JSON;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.iaowoo.mobile.H5toAndroid.modle.WECHATTB;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.H5toAndroid.modle.WECHATTB;
import com.iaowoo.mobile.Ui.classification.Model.ALYPAY;
import com.iaowoo.mobile.Ui.classification.Model.WECHATPAY;
import com.iaowoo.mobile.Utils.HttpImgThread;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.alapi.PayAlipay;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.wxapi.WeiXinPay;
import com.taobao.weex.WXSDKInstance;
import com.umeng.debug.log.E;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by chenda on 2018/4/2.
 */

public class JsMethod2 {
    public  Bitmap bm=null;
    public JsMethod2(){

    }
    /**
     * webview返回
     * @param bridgeWebView
     */
    public void h5GoBack(final BridgeWebView bridgeWebView)
    {
        bridgeWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK &&   bridgeWebView.canGoBack()) {// 返回上一页面
                    bridgeWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                    bridgeWebView.goBack();
                    return true;
                }
                return false;
            }
        });
    }
    /**
     * 微信支付
     * @param appid
     * @param nocestr
     * @param packages
     * @param partnerid
     * @param prepayid
     * @param sign
     * @param timestamp
     */
    public void wechatPay(String appid,String nocestr,String packages,String partnerid,String prepayid,String sign,String timestamp)
    {
        WeiXinPay.getInstance(ZApplication.getContext()).startWXPay(appid, partnerid,prepayid,nocestr, timestamp,sign);
    }


    /**
     * 支付宝支付
     * @param context
     * @param data
     */
    public  void Alipay(Context context,String data)
    {
        SoftReference<PayAlipay> payAlipaySoftReference=new SoftReference<>(new PayAlipay(context));
        //调起支付宝支付
        if(payAlipaySoftReference.get()!=null) {
            payAlipaySoftReference.get().startPay(data);
        }else{
            payAlipaySoftReference=new SoftReference<>(new PayAlipay(context));
            payAlipaySoftReference.get().startPay(data);
        }
    }
    /**
     * 授权
     * @param share_media
     * @param context
     */
    public  void authorization(SHARE_MEDIA share_media,final Activity context) {
        UMShareAPI.get(context).getPlatformInfo(context, share_media, new UMAuthListener() {
            /**
             * 微信授权开始
             * @param share_media
             */
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                LogPrint.printError("onStart " + "授权开始");
            }
            /**
             * 微信授权成功
             * @param share_media
             * @param i
             * @param map
             */
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                LogPrint.printError("onComplete " + "授权完成");
                //sdk是6.4.4的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
//                String unionid = map.get("unionid");//微博没有
//                String access_token = map.get("access_token");
//                String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
//                String expires_in = map.get("expires_in");
                final String name = map.get("name");
                final String gender = map.get("gender");
                final String iconurl = map.get("iconurl");
                final String city=map.get("city");
                final String country=map.get("country");
                final String province=map.get("province");
                final String uid = map.get("uid");
                final String openid = map.get("openid");//微博没有
                WECHATTB wechattb=new WECHATTB();
                WECHATTB.WexinRespBean wexinRespBean=new WECHATTB.WexinRespBean();
                wexinRespBean.setName(name);
                wexinRespBean.setIconurl(iconurl);
                wexinRespBean.setOpenid(openid);
                wexinRespBean.setUnionGender(gender);
                wexinRespBean.setUid(uid);
                wexinRespBean.setCity(city);
                wexinRespBean.setCountry(country);
                wexinRespBean.setProvince(province);
                wechattb.setWexinResp(wexinRespBean);
                String json=ZApplication.gson.toJson(wechattb);
                //把同步的资料存起来
                PrefManager.getInstance().setWechateJson(json);

                //同步成功后告诉weex
                EventBus.getDefault().post(new EventBusMessageWeex("wechatComplate",json));

                //把微信授权成功的的icon下载成bitmap
                HttpImgThread httpImgThread=new HttpImgThread(iconurl);
                httpImgThread.startDownload(new HttpImgThread.UpdateCallBack() {
                    @Override
                    public void update(Bitmap url) {
                        bm=url;
                        if(url!=null){
                            //开启线程开始存在本地
                            new Thread(saveFileRunnable).start();
                        }
                    }
                });

            }
            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                LogPrint.printError("onError " + "授权失败");
            }
            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                LogPrint.printError("onCancel " + "授权取消");
            }
        });
    }

    /**
     * 把微信图片下载存在本地
     */
    public  Runnable saveFileRunnable = new Runnable(){
        @Override
        public void run() {
            try {
                saveFile(bm,"wechatIcon.jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            messageHandler.sendMessage(messageHandler.obtainMessage());
        }
    };

    /**
     * 开始上传图片到服务器
     */
    public  android.os.Handler messageHandler = new android.os.Handler() {
        /**
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            XutilsHttp.getInstance().uploadPicture( HttpImgThread.ALBUM_PATH+HttpImgThread.webchatUrl,"2",ZApplication.getContext(),1);
        }
    };

    /**
     * 保存文件
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public  void saveFile(Bitmap bm, String fileName) throws IOException {
        LogPrint.printError("bm"+bm.toString());
        File dirFile = new File(HttpImgThread.ALBUM_PATH);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File myCaptureFile = new File(HttpImgThread.ALBUM_PATH + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

}
