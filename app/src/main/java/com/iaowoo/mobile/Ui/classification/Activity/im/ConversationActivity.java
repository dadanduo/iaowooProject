package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;

import java.util.Locale;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

public class ConversationActivity extends TitleBarActivity implements TitleBarActivity.TitleBarClickListener {
    private Conversation.ConversationType mConversationType;
    private ConversationFragment conversationFragment;
    private String targetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);

        conversationFragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
//        conversationFragment.
        showRightImgView(R.mipmap.head_portrait);
        setTitleTarListener(this);

        Intent intent = getIntent();
        if (intent == null || intent.getData() == null)
            return;

        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.US));
        targetId = intent.getData().getQueryParameter("targetId");
        String title = intent.getData().getQueryParameter("title");

        if (!TextUtils.isEmpty(title)){
            setTitle(title);
        } else {
            setTitle("聊天");
        }
    }

    @Override
    public void OnTitleBarClick(int id) {
        switch (id) {
            case R.id.rl_title_bar_right:
                if (mConversationType.equals(Conversation.ConversationType.PRIVATE)) {
                    Intent intent = new Intent(this, PrivateChatDetailActivity.class);
                    intent.putExtra(PrivateChatDetailActivity.USER_ID, targetId);
                    startActivity(intent);
                } else if (mConversationType.equals(Conversation.ConversationType.GROUP)) {
                    Intent intent = new Intent(this, GroupDetailActivity.class);
                    intent.putExtra(GroupDetailActivity.GROUP_ID, targetId);
                    startActivity(intent);
                }
                break;
        }
    }
}
