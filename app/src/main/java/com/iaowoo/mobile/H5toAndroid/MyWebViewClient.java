package com.iaowoo.mobile.H5toAndroid;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.iaowoo.mobile.Utils.CacheUtils;
import com.iaowoo.mobile.Utils.DiskLruCache;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.UtilsTimer;
import com.iaowoo.mobile.common.ConfigFlag;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * webview封装
 */
public class MyWebViewClient extends BridgeWebViewClient {
    private boolean isSuccess = false;
    private boolean isError = false;
    private MyWebviewClientCallBack myWebviewClientCallBack;
    private Context context;
    private UtilsTimer utilsTimer;
    private CacheUtils cacheUtils=null;
    public MyWebViewClient(CacheUtils cacheUtils,BridgeWebView webView, final MyWebviewClientCallBack myWebviewClientCallBack, Context context) {
        super(webView);
        this.myWebviewClientCallBack=myWebviewClientCallBack;
        this.context=context;
        this.cacheUtils=cacheUtils;
        utilsTimer=new UtilsTimer(new UtilsTimer.UtilsTimerCallBack() {
            @Override
            public void timeOver() {
                LogPrint.printError("timeout");
                myWebviewClientCallBack.LoadingFaild();
            }
        });
        utilsTimer.goOn(1000,1000, ConfigFlag.TIMEOUT_WEB);
    }
    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        //不是磁盘缓存不允许进入
        if(cacheUtils!=null) {
            if (!TextUtils.isEmpty(url) && url.contains("jpg")||url.contains("png")||url.contains("gif")||url.contains("jpeg")||url.contains("bmp")) {
                LogPrint.printError("图片路径：" + url);
                WebResourceResponse response = null;
                //先从缓存中读取
                try {
                    String key = cacheUtils.hashKeyForDisk(url);
                    LogPrint.printError("key："+key);
                    DiskLruCache.Snapshot snapShot = cacheUtils.mDiskLruCache.get(key);
                    //缓存中有数据返回给webveiw（有数据）
                    if (snapShot!=null) {
                        LogPrint.printError("图片磁盘存在");
                        InputStream is = snapShot.getInputStream(0);
                        LogPrint.printError("开始读缓存：" + is);
                        if(url.contains("jpg")){
                            response = new WebResourceResponse("image/jpg", "UTF-8", is);
                            LogPrint.printError("缓存图片类型:jpg");
                        }else if(url.contains("png")){
                            response = new WebResourceResponse("image/png", "UTF-8", is);
                            LogPrint.printError("缓存图片类型:png");
                        }else if(url.contains("gif")){
                            response = new WebResourceResponse("image/gif", "UTF-8", is);
                            LogPrint.printError("缓存图片类型:gif");
                        }else if(url.contains("jpeg")){
                            response = new WebResourceResponse("image/jpeg", "UTF-8", is);
                            LogPrint.printError("缓存图片类型:jpeg");
                        }else if(url.contains("bmp")){
                            response = new WebResourceResponse("image/bmp", "UTF-8", is);
                            LogPrint.printError("缓存图片类型:bmp");
                        }
                        return response;
                    }
                    //缓存为空开始缓存（没有数据）
                    else {
                        LogPrint.printError("图片磁盘不存在,开始缓存");
                        //缓存之前先判断下磁盘是否存在存在继续开始缓存不存在创建（可能被用户清空缓存）
                        File cacheDir = cacheUtils.getDiskCacheDir(context, "bitmap");
                        if (!cacheDir.exists()) {
                            cacheDir.mkdirs();
                        }else {
                            //开始写入缓存
                            cacheUtils.loadImagesByExecutors(url,key);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    LogPrint.printError("异常：" + e.getMessage());
                }
            }
        }
        return super.shouldInterceptRequest(view, url);
    }
    /**
     * 网页加载完成
     * @param view
     * @param url
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (!isError) {
            isSuccess=true;
            //回调成功后的相关操作
            myWebviewClientCallBack.LoadingOK();
            utilsTimer.destoryTimer();
            LogPrint.printError("加载成功");
        }
        else
        {
            myWebviewClientCallBack.LoadingFaild();
            isError=false;
            LogPrint.printError("加载失败");
        }



    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        isError=true;
        isSuccess=false;
    }

    public void destroy(){
        if(utilsTimer!=null){
            utilsTimer.destoryTimer();
        }


    }
}
