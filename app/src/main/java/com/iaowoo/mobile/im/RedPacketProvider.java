package com.iaowoo.mobile.im;

import android.content.Context;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;

import io.rong.imkit.RongIM;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

@ProviderTag(messageContent = RedPacketMessage.class, showPortrait = true, centerInHorizontal = true)
public class RedPacketProvider extends IContainerItemProvider.MessageProvider<RedPacketMessage> {
    private Context context;

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.im_item_bribery, viewGroup, false);
        RedPacketHolder holder = new RedPacketHolder();
        holder.bgLayout = view.findViewById(R.id.bri_bg);
        holder.msgText = view.findViewById(R.id.tv_bri_mess);
        holder.statusText = view.findViewById(R.id.tv_bri_status);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, int i, RedPacketMessage redPacketMessage, UIMessage uiMessage) {
        RedPacketHolder holder = (RedPacketHolder) view.getTag();
        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            holder.bgLayout.setBackgroundResource(R.drawable.im_red_bg_right);
        } else {
            holder.bgLayout.setBackgroundResource(R.drawable.im_red_bg_left);
        }
        holder.msgText.setText(redPacketMessage.getRemark());
        holder.statusText.setText("领取红包");
    }

    @Override
    public Spannable getContentSummary(RedPacketMessage redPacketMessage) {
        return null;
    }

    @Override
    public void onItemClick(View view, int i, final RedPacketMessage redPacketMessage, final UIMessage uiMessage) {
        ToastUtilsAll.getInstance().show("niahaah");

        DialogUtils dialogUtils = new DialogUtils();
        dialogUtils.getChatRedPackets(context, new ClickCallBack() {
            @Override
            public void OnClick() {
                sendMsg(uiMessage, redPacketMessage);
            }
        });
    }

    class RedPacketHolder {
        private TextView msgText;
        private RelativeLayout bgLayout;
        private TextView statusText;
    }


    private void sendMsg(final UIMessage uiMessage, RedPacketMessage msg) {
        Log.i("TAG", "msgUid" + uiMessage.getUId());

        final RedPacketMessage redPacketMessage = new RedPacketMessage();
        redPacketMessage.setRemark("已收到" + msg.getAmount() + "钱");
        redPacketMessage.setExtra("1/" + uiMessage.getUId());

        Message message = Message.obtain(uiMessage.getTargetId(), uiMessage.getConversationType(), redPacketMessage);

        RongIM.getInstance().sendMessage(message, "niaiai", "ananan", new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {
                Log.i("TAG", "onSuccess" + message);
                RongIMClient.getInstance().getMessageByUid(message.getUId(), new RongIMClient.ResultCallback<Message>() {
                    @Override
                    public void onSuccess(Message oldMessage) {
                        RongIMClient.getInstance().setMessageExtra(oldMessage.getMessageId(), redPacketMessage.getExtra(), null);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                Log.i("TAG", "onError" + message);
            }
        });
    }
}
