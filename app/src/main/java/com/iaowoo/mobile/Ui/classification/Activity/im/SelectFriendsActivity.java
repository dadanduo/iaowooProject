package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.DB.im.DBManager;
import com.iaowoo.mobile.DB.im.model.Friend;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.im.StartDiscussionAdapter;
import com.iaowoo.mobile.Ui.classification.Model.im.GroupCreateResponse;
import com.iaowoo.mobile.Ui.classification.View.im.SideBar;
import com.iaowoo.mobile.Utils.PingYinUtil;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.im.PinyinComparator;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;

public class SelectFriendsActivity extends TitleBarActivity implements TitleBarActivity.TitleBarClickListener  {
    public static final String CONVERSATION_TYPE = "conversationType";
    public static final String PRIVATE = "private";
    public static final String DISCUSS = "discuss";
    @BindView(R.id.ll_selected_friends)
    LinearLayout llSelectedFriends;
    @BindView(R.id.dis_show_no_friend)
    TextView mNoFriends;
    @BindView(R.id.lv_friend_list)
    ListView mListView;
    @BindView(R.id.dis_dialog)
    TextView dialog;
    @BindView(R.id.dis_side_bar)
    SideBar mSidBar;

    private PinyinComparator pinyinComparator;
    private StartDiscussionAdapter adapter;
    private String mConversationStartType = "";
    private List<Friend> mSourceDataList = new ArrayList<>();
    private Map<String, Friend> mSelectedMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_select_friends);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mConversationStartType = getIntent().getStringExtra(CONVERSATION_TYPE);
        if (mConversationStartType.equals(DISCUSS)) {
            setTitle("发起群聊");
        } else if (mConversationStartType.equals(PRIVATE)) {
            setTitle("选择联系人");
        }
        showRightTextView("确定");
        mSidBar.setTextView(dialog);
    }

    private void initListener() {
        setTitleTarListener(this);
        //设置右侧触摸监听
        mSidBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mListView.setSelection(position);
                }
            }
        });
    }
    private void initData() {
        pinyinComparator = PinyinComparator.getInstance();
        boolean isStartPrivateChat = mConversationStartType.equals("private");
        adapter = new StartDiscussionAdapter(this, mSourceDataList, isStartPrivateChat);
        mListView.setAdapter(adapter);

        mSourceDataList = DBManager.getInstance().getFriends();
        if (mSourceDataList == null) {
            return;
        }

        for (Friend friend : mSourceDataList) {
            String pinyin = PingYinUtil.converterToFirstSpell(friend.getDisplayName());
            String sortString;
            if (!TextUtils.isEmpty(pinyin)) {
                sortString = pinyin.substring(0, 1).toUpperCase();
            } else {
                sortString = "#";
            }
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                friend.setLetters(sortString);
            } else {
                friend.setLetters("#");
            }
        }
        Collections.sort(mSourceDataList, pinyinComparator);
        adapter.setData(mSourceDataList);
    }


    @Override
    public void OnTitleBarClick(int id) {
        switch (id) {
            case R.id.rl_title_bar_right:
                if (mConversationStartType.equals(PRIVATE)) {
                    startPrivateChat();
                } else if (mConversationStartType.equals(DISCUSS)) {
                    createGroup();
                }
                break;
        }
    }

    private void startPrivateChat() {
        if (adapter.getIsSelected() != -1) {
            Friend friend = mSourceDataList.get(0);
            RongIM.getInstance().startPrivateChat(this, friend.getUserId(), friend.getName());
        } else {
            ToastUtilsAll.getInstance().show("至少选择一位好友");
        }
    }

    private void createGroup() {
        if (mSelectedMap.size() > 0) {
            showD("创建群组");
            List<Integer> memberList = new ArrayList<>();

            for (String key : mSelectedMap.keySet()) {
                memberList.add(Integer.parseInt(key));
            }

            // 添加自己
            String rongUserId = PrefManager.getInstance().getRongIMUserId();
            memberList.add(Integer.parseInt(rongUserId));

            String loginToken = PrefManager.getInstance().getToken();
            HashMap<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("loginToken", loginToken);
            paramsMap.put("portrait", "");
            paramsMap.put("memberList", memberList);
            OkhttpManager.getInstance(this).requestPostByAsyn(false, UtilsOkHttpAll.IM_CREATE_GROUP, "IM_CREATE_GROUP", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
                @Override
                public void onReqSuccess(Object result) {
                    GroupCreateResponse groupCreateResponse = JSON.parseObject(result.toString(), GroupCreateResponse.class);
                    if (groupCreateResponse.getCode().equals(OkhttpManager.SUCESS)) {
                        ToastUtilsAll.getInstance().show("创建群组成功！");
                    } else {
                        ToastUtilsAll.getInstance().show(groupCreateResponse.getDescribe());
                    }
                    hideD();
                }

                @Override
                public void onReqFailed(String errorMsg) {

                }
            });

        } else {
            ToastUtilsAll.getInstance().show("至少选择一位好友");
        }
    }

    public static void start(Context context, String type) {
        Intent intent = new Intent(context, SelectFriendsActivity.class);
        intent.putExtra(CONVERSATION_TYPE, type);
        context.startActivity(intent);
    }

    public void updateSelectedSizeView() {
        int size = 0;
        for (int i = 0; i < mSourceDataList.size(); i++) {
            Friend friend = mSourceDataList.get(i);
            if (friend.getSelected()) {
                size++;
                mSelectedMap.put(friend.getUserId(), friend);
            } else if(mSelectedMap.containsKey(friend.getUserId())) {
                mSelectedMap.remove(friend.getUserId());
            }
        }
        if (size == 0) {
            setRightText("确定");
            llSelectedFriends.setVisibility(View.GONE);
        } else {
            setRightText("确定(" + size + ")");
            llSelectedFriends.setVisibility(View.GONE);
        }
    }

    public void updateSelectedFriend() {
        LinearLayout view = (LinearLayout) View.inflate(SelectFriendsActivity.this, R.layout.im_item_selected_friends, null);
        ImageView asyncImageView = view.findViewById(R.id.iv_selected_friends);
            /*String portraitUri = SealUserInfoManager.getInstance().getPortraitUri(friend);
            ImageLoader.getInstance().displayImage(portraitUri, asyncImageView);*/
        view.removeView(asyncImageView);
        llSelectedFriends.addView(asyncImageView);
    }
}