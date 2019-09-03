package com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.io.Serializable;

/**
 * Created by chenda on 2018/4/4.
 */

public class RdioBroadCast {
    /**
     * 传值
     */
    public final static  String DATA="TAG";
    /**
     *广播判断值
     */
    public final static  String SHOWTAG="SHOW";
    /**
     *H5交互广播
     */
    public final  static  String BOARD="com.xiaomishengtaiquan.JSandboard";
    /**
     *msg极光
     */
    public final static  String MSGJIGUANG="com.xiaomishengtaiquan.jg";

    public RdioBroadCast(){
    }


    /**
     * 注册广播
     * @param context
     * @param receiver
     * @param type
     */
    public  void  regiseterBroad(Context context,BroadcastReceiver receiver,String type)
    {
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(type);
        context.registerReceiver(receiver, intentFilter);
    }
    /**
     * 注销广播
     * @param context
     * @param receiver
     */
    public  void unRegiseterBroad(Context context,BroadcastReceiver receiver)
    {
        context.unregisterReceiver(receiver);
    }
    //发送数据支持String
    public  void  sendData(Context context ,String tag,String data,String type,String showTag )
    {
        Intent intent = new Intent();
        intent.putExtra(SHOWTAG,showTag);
        intent.putExtra(tag,data);
        intent.setAction(type);
        context.sendBroadcast(intent);
    }
    public   <T extends Serializable>void sendDataObject(Context context,String tag,T d,String type){
        Intent intent=new Intent();
        intent.putExtra(tag,d);
        intent.setAction(type);
        context.sendBroadcast(intent);
    }
}
