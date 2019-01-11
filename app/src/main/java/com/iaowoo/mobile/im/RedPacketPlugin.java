package com.iaowoo.mobile.im;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.im.SendGroupEnvelopesActivity;
import com.iaowoo.mobile.Ui.classification.Activity.im.SendSingleEnvelopesActivity;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

public class RedPacketPlugin implements IPluginModule {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.mipmap.chat_red_envelope_icon);
    }

    @Override
    public String obtainTitle(Context context) {
        return "红包";
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        String targetId = rongExtension.getTargetId();
        Conversation.ConversationType conversationType = rongExtension.getConversationType();
        if ( conversationType == Conversation.ConversationType.DISCUSSION || conversationType == Conversation.ConversationType.GROUP) {
            SendGroupEnvelopesActivity.start(fragment.getActivity(), targetId);
        } else if (conversationType == Conversation.ConversationType.PRIVATE) {
            SendSingleEnvelopesActivity.start(fragment.getActivity(), targetId);
        }
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
