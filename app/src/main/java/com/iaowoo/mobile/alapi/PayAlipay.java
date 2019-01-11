package com.iaowoo.mobile.alapi;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.taobao.weex.WXSDKInstance;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenda on 2018/4/9.
 * 支付宝支付
 */
public class PayAlipay {
    private Context context;
    Runnable payRunnable;
    public PayAlipay(Context context){
        this.context=context;
    }
    private static final int SDK_PAY_FLAG = 1001;
    private String RSA_PRIVATE =ConfigAppkey.ALIPAY_APPKEY;
    public static final String APPID = ConfigAppkey.ALIPAY_APPID;

    private Handler mHandler = new Handler() {
        /**
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    SoftReference<RdioBroadCast>   rdioBroadCast=new SoftReference<>(new RdioBroadCast());
                    if (TextUtils.equals(resultStatus, "9000")) {
                        EventBus.getDefault().post(new EventBusMessageWeex("aliPayOk",""));
                        if(rdioBroadCast.get()!=null) {
                            rdioBroadCast.get().sendData(context, RdioBroadCast.DATA, "1", RdioBroadCast.BOARD, "paySucess");
                        }else{
                            SoftReference<RdioBroadCast>   rdioBroadCast1=new SoftReference<>(new RdioBroadCast());
                            rdioBroadCast1.get().sendData(context, RdioBroadCast.DATA, "1", RdioBroadCast.BOARD, "paySucess");
                        }
                    } else {
                        EventBus.getDefault().post(new EventBusMessageWeex("aliPayFaild",""));
                        if(rdioBroadCast.get()!=null) {
                            rdioBroadCast.get().sendData(context,RdioBroadCast.DATA,"1",RdioBroadCast.BOARD,"payFaild");
                        }else{
                            SoftReference<RdioBroadCast>   rdioBroadCast1=new SoftReference<>(new RdioBroadCast());
                            rdioBroadCast1.get().sendData(context,RdioBroadCast.DATA,"1",RdioBroadCast.BOARD,"payFaild");
                        }
                    }
                    break;
            }
        }
    };

    /**
     * 开始支付
     * @param orderinfo
     */
    public void startPay(final String orderinfo) {
        LogPrint.printError("支付宝支付数："+orderinfo);
        //异步处理
        payRunnable = new Runnable() {
            @Override
            public void run() {
                //新建任务
                PayTask alipay = new PayTask((Activity) context);
                //获取支付结果
                Map<String, String> result = alipay.payV2(orderinfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     *获取版本
     * @param payTask
     * @return
     */
    private String getPlayVersion(PayTask payTask){
        return payTask.getVersion();
    }

    /**
     * activity销毁
     */
    public void Destroy(){
        mHandler.removeCallbacks(payRunnable);
    }
}
