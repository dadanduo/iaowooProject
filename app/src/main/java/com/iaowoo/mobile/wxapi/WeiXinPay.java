/**
 * Copyright (c) 2015-2017, javen205  (javendev@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.iaowoo.mobile.wxapi;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 微信支付
 * Created by dada on 2016/11/20.
 */
public class WeiXinPay  {
    private static WeiXinPay mWeiXinPay;
    private Context mContext;
    private IWXAPI mIWXAPI;
    /**
     * 未安装微信或微信版本过低
     */
    public static final int WEIXIN_VERSION_LOW = 0x001;
    /**
     * 支付参数异常
     */
    public static final int PAY_PARAMETERS_ERROE = 0x002;
    /**
     * 支付失败
     */
    public static final int PAY_ERROR= 0x003;

    public WeiXinPay(Context context) {
        mContext = context;
    }

    public static WeiXinPay getInstance(Context context){
        if (mWeiXinPay == null) {
            synchronized(WeiXinPay.class){
                if (mWeiXinPay == null) {
                    mWeiXinPay = new WeiXinPay(context);
                }
            }
        }
        return mWeiXinPay;
    }

    /**
     * 初始化微信支付接口
     */
    public void init(){
        mIWXAPI = WXAPIFactory.createWXAPI(mContext, null);
        mIWXAPI.registerApp(ConfigAppkey.WEIXIN_APP_ID);
    }

    /**
     * 获取微信接口
     * @return
     */
    public IWXAPI getWXApi() {
        return mIWXAPI;
    }

    /**
     * 调起支付
     * @param appId
     * @param partnerId
     * @param prepayId
     * @param nonceStr
     * @param timeStamp
     */
    public void startWXPay(String appId,String partnerId,String prepayId,String nonceStr,String timeStamp,String packagevalue){
        init();
        if (!checkWx()) {
            LogPrint.printError("未安装微信或者微信版本过低");
            ToastUtilsAll.getInstance().showShortToast("未安装微信或者微信版本过低");
            return;
        }else{
            PayReq request = new PayReq();
            request.appId = appId;
            request.partnerId = partnerId;
            request.prepayId= prepayId;
            request.packageValue = packagevalue;
            request.nonceStr=nonceStr;
            request.timeStamp= timeStamp;
            request.sign= "Sign=WXPay";
            request.extData = "app data"; // optional
            mIWXAPI.sendReq(request);
        }
    }
    /**
     * 响应支付回调
     * @param error_code
     * @param message
     */
    public void onResp(int error_code,String message) {
        RdioBroadCast rdioBroadCast=new RdioBroadCast();
        LogPrint.printError("code"+error_code);
        if(error_code == 0) {
            LogPrint.printError("支付成功");
            EventBus.getDefault().post(new EventBusMessageWeex("wechatPayOk",""));
            rdioBroadCast.sendData(mContext,RdioBroadCast.DATA,"0", RdioBroadCast.BOARD,"paySucess");
        } else if(error_code == -1) {
            EventBus.getDefault().post(new EventBusMessageWeex("wechatPayFaild",""));
            LogPrint.printError("支付异常");
            rdioBroadCast.sendData(mContext,RdioBroadCast.DATA,"0", RdioBroadCast.BOARD,"payAbnormal");
        } else if(error_code == -2) {
            LogPrint.printError("支付失败");
            EventBus.getDefault().post(new EventBusMessageWeex("wechatPayFaild",""));
            rdioBroadCast.sendData(mContext,RdioBroadCast.DATA,"0", RdioBroadCast.BOARD,"payFaild");
        }
    }
    //检测微信客户端是否支持微信支付
    private boolean checkWx() {
        return isWeixinAvilible() && mIWXAPI.isWXAppInstalled() && mIWXAPI.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
    /**
     * 判断微信是否安装
     * @return
     */
    private  boolean isWeixinAvilible() {
        return appIsAvilible("com.tencent.mm");
    }

    /**
     * 判断app是否安装
     * @param packageName
     * @return
     */
    private  boolean appIsAvilible(String packageName) {
        final PackageManager packageManager = mContext.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
