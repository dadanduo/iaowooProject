package com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//全局广播
public class GlobalBroadcastReceiver extends BroadcastReceiver {
    private BroadcastCallBack broadcastCallBack;
    public GlobalBroadcastReceiver(BroadcastCallBack broadcastCallBack)
    {
        this.broadcastCallBack=broadcastCallBack;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String tag=intent.getStringExtra(RdioBroadCast.SHOWTAG);
        String data=intent.getStringExtra(RdioBroadCast.DATA);
        broadcastCallBack.ReceiverData(tag,data);
    }
}
