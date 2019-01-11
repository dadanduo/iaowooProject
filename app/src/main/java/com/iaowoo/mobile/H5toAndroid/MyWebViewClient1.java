package com.iaowoo.mobile.H5toAndroid;

import android.support.annotation.Nullable;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.iaowoo.mobile.Utils.LogPrint;

/**
 * webview封装
 */
public class MyWebViewClient1 extends BridgeWebViewClient {
    private boolean isSuccess = false;
    private boolean isError = false;
    private MyWebviewClientCallBack myWebviewClientCallBack;
    public MyWebViewClient1(BridgeWebView webView, final MyWebviewClientCallBack myWebviewClientCallBack) {
        super(webView);
        this.myWebviewClientCallBack=myWebviewClientCallBack;
    }
    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
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

}
