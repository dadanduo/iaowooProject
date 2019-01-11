package com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Ui.classification.Activity.HomePageActivity;
import com.iaowoo.mobile.Utils.LogPrint;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}()
 * @date 2018/6/12
 */
public class BoardcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogPrint.printError("收到消息了");
        //todo 跳转之前要处理的逻辑
        Intent newIntent = new Intent(context,HomePageActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newIntent);
    }
}
