package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;
import com.iaowoo.mobile.Ui.classification.View.im.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrivateChatDetailActivity extends TitleBarActivity {
    public static final String USER_ID = "userId";
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_add_friend)
    ImageView ivAddFriend;
    @BindView(R.id.tv_search_chat_record)
    TextView tvSearchChatRecord;
    @BindView(R.id.tv_clean_chat_record)
    TextView tvCleanChatRecord;
    @BindView(R.id.ll_avatar)
    LinearLayout llAvatar;
    @BindView(R.id.ll_detail_info)
    LinearLayout llDetailInfo;
    @BindView(R.id.sw_notice_no_disturb)
    SwitchButton swNoticeNoDisturb;
    @BindView(R.id.sw_conversation_top)
    SwitchButton swConversationTop;

    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_fr_friend_detail);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        mUserId = getIntent().getStringExtra(USER_ID);
        if (TextUtils.isEmpty(mUserId)) {
            return;
        }


    }

    @OnClick({R.id.ll_avatar, R.id.iv_add_friend, R.id.tv_search_chat_record, R.id.sw_notice_no_disturb, R.id.sw_conversation_top, R.id.tv_clean_chat_record})
    public void onViewClicked(View view) {
        if (TextUtils.isEmpty(mUserId)) {
            return;
        }
        switch (view.getId()) {
            case R.id.ll_avatar:
                UserDetailActivity.startPage(this, mUserId);
                break;
            case R.id.iv_add_friend:
                break;
            case R.id.tv_search_chat_record:
                startActivity(IMSearchActivity.class);
                break;
            case R.id.sw_notice_no_disturb:
                break;
            case R.id.tv_clean_chat_record:
                break;
        }
    }
}
