package com.iaowoo.mobile.im;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

public class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {
    @Override
    public boolean onReceived(Message message, int i) {
        return false;
    }
}
