package com.iaowoo.mobile.H5toAndroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Bitmap;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;

import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.common.ConfigFlag;


/**
 * 自定义的js  alert提示框
 */

//****************************************************************************  
public class MyWebChromeClient extends WebChromeClient {

    private  H5CallBack h5CallBack;
    public MyWebChromeClient(H5CallBack h5CallBack)
    {
        this.h5CallBack=h5CallBack;
    }


    @Override
    public void onCloseWindow(WebView window) {
        super.onCloseWindow(window);
    }

    @Override
    public boolean onCreateWindow(WebView view, boolean dialog,
                                  boolean userGesture, Message resultMsg) {
        return super.onCreateWindow(view, dialog, userGesture, resultMsg);
    }

    /**
     * 获取加载进度
     * @param view
     * @param newProgress
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress < 100) {
            String progress = newProgress + "%";
            LogPrint.printError("进度：" + progress);
        } else if (newProgress == 100) {
            String progress = newProgress + "%";
            LogPrint.printError("进度：" + progress);
            h5CallBack.loadingSuccess();
        }
    }


    /**
     * 覆盖默认的window.alert展示界面，避免title里显示为“：来自file:////”  
     */
    public boolean onJsAlert(WebView view, String url, String message,
                             JsResult result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setTitle("温馨提示:")
                .setMessage(message)
                .setPositiveButton("确定", null);

        // 不需要绑定按键事件  
        // 屏蔽keycode等于84之类的按键  
        builder.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
                Log.v("onJsAlert", "keyCode==" + keyCode + "event="+ event);
                return true;
            }
        });
        // 禁止响应按back键的事件  
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        if(ConfigFlag.ISSHWODILOGO) {
            dialog.show();
        }
        result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。  
        return true;
        // return super.onJsAlert(view, url, message, result);  
    }

    public boolean onJsBeforeUnload(WebView view, String url,
                                    String message, JsResult result) {
        return super.onJsBeforeUnload(view, url, message, result);
    }
    /**
     * 覆盖默认的window.confirm展示界面，避免title里显示为“：来自file:////” 
     */
    public boolean onJsConfirm(WebView view, String url, String message,
                               final JsResult result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("对话框")
                .setMessage(message)
                .setPositiveButton("确定",new OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        result.confirm();
                    }
                })
                .setNeutralButton("取消", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                });
        builder.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                result.cancel();
            }
        });

        // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题  
        builder.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
                Log.v("onJsConfirm", "keyCode==" + keyCode + "event="+ event);
                return true;
            }
        });
        // 禁止响应按back键的事件  
        // builder.setCancelable(false);  
        AlertDialog dialog = builder.create();
        dialog.show();
        return true;
        // return super.onJsConfirm(view, url, message, result);  
    }

    /**
     * 覆盖默认的window.prompt展示界面，避免title里显示为“：来自file:////” 
     * window.prompt('请输入您的域名地址', '618119.com'); 
     */
    public boolean onJsPrompt(WebView view, String url, String message,
                              String defaultValue, final JsPromptResult result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setTitle("对话框").setMessage(message);

        final EditText et = new EditText(view.getContext());
        et.setSingleLine();
        et.setText(defaultValue);
        builder.setView(et)
                .setPositiveButton("确定", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm(et.getText().toString());
                    }

                })
                .setNeutralButton("取消", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                });

        // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题  
        builder.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode,KeyEvent event) {
                Log.v("onJsPrompt", "keyCode==" + keyCode + "event="+ event);
                return true;
            }
        });
        // 禁止响应按back键的事件  
        // builder.setCancelable(false);  
        AlertDialog dialog = builder.create();
        dialog.show();
        return true;
        // return super.onJsPrompt(view, url, message, defaultValue,  
        // result);  
    }


    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }

    @Override
    public void onRequestFocus(WebView view) {
        super.onRequestFocus(view);
    }
}