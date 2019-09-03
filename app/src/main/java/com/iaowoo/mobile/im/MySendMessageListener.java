package com.iaowoo.mobile.im;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Message;

public class MySendMessageListener implements RongIM.OnSendMessageListener {
    @Override
    public Message onSend(Message message) {
        return message;
    }

    @Override
    public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
        return false;
    }
}
