package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;
import com.iaowoo.mobile.Ui.classification.Model.im.GetGroupInfoResponse;
import com.iaowoo.mobile.Ui.classification.Model.im.GetUserInfoResponse;
import com.iaowoo.mobile.Ui.classification.Model.im.GroupMemberListResponse;
import com.iaowoo.mobile.Ui.classification.View.im.NestGridView;
import com.iaowoo.mobile.Ui.classification.View.im.SwitchButton;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupDetailActivity extends TitleBarActivity {
    public static final String GROUP_ID = "groupId";
    @BindView(R.id.gridview)
    NestGridView gridview;
    @BindView(R.id.group_name)
    TextView groupName;
    @BindView(R.id.rl_group_name)
    RelativeLayout rlGroupName;
    @BindView(R.id.group_code)
    RelativeLayout groupCode;
    @BindView(R.id.group_notice)
    TextView groupNotice;
    @BindView(R.id.tv_search_chat_record)
    TextView tvSearchChatRecord;
    @BindView(R.id.sw_group_notice_no_disturb)
    SwitchButton swGroupNoticeNoDisturb;
    @BindView(R.id.sw_show_group_member_nickname)
    SwitchButton showGroupMemberNickname;
    @BindView(R.id.ll_group_member_nickname)
    LinearLayout llGroupMemberNickname;
    @BindView(R.id.tv_group_my_nickname)
    TextView tvGroupMyNickname;
    @BindView(R.id.ll_group_my_nickname)
    LinearLayout llGroupMyNickname;
    @BindView(R.id.tv_clean_chat_record)
    TextView tvCleanChatRecord;
    @BindView(R.id.group_quit)
    Button groupQuit;
    @BindView(R.id.group_dismiss)
    Button groupDismiss;
    private String mGroupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_group_detail);
        ButterKnife.bind(this);
        setTitle(R.string.group_info);

        initData();
    }

    private void initData() {
        mGroupId = getIntent().getStringExtra(GROUP_ID);
        if (!TextUtils.isEmpty(mGroupId)) {
            String loginToken = PrefManager.getInstance().getToken();
            getGroupInfoByGroupId(loginToken);
            getGroupMemberInfoByGroupId(loginToken);
        }
    }


    private void getGroupInfoByGroupId(String loginToken) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken", loginToken);
        paramsMap.put("groupId", mGroupId);
        OkhttpManager.getInstance(this).requestPostByAsyn(false, UtilsOkHttpAll.IM_GET_GROUP_INFO, "IM_GET_GROUP_INFO", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                GetGroupInfoResponse getGroupInfoResponse = JSON.parseObject(result.toString(), GetGroupInfoResponse.class);
                if (getGroupInfoResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    GetUserInfoResponse.BodyBean.ContentBean contentBean = getGroupInfoResponse.getBody().getContent();
                    if (contentBean != null) {
                        updateUI(contentBean);
                    }
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }

    private void getGroupMemberInfoByGroupId(String loginToken) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken", loginToken);
        paramsMap.put("groupId", mGroupId);
        OkhttpManager.getInstance(this).requestPostByAsyn(false, UtilsOkHttpAll.IM_QUERY_GROUP_MEMBER_LIST, "IM_QUERY_GROUP_MEMBER_LIST", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                GroupMemberListResponse groupMemberListResponse = JSON.parseObject(result.toString(), GroupMemberListResponse.class);
                /*if (groupMemberListResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    GetUserInfoResponse.BodyBean.ContentBean contentBean = groupMemberListResponse.getBody().getContent();
                    if (contentBean != null) {
                        updateUI(contentBean);
                    }
                }*/
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }

    private void updateUI(GetUserInfoResponse.BodyBean.ContentBean contentBean) {

    }

    @OnClick({R.id.tv_search_chat_record, R.id.sw_group_notice_no_disturb, R.id.sw_show_group_member_nickname, R.id.tv_clean_chat_record, R.id.group_quit, R.id.group_dismiss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search_chat_record:
                break;
            case R.id.sw_group_notice_no_disturb:
                break;
            case R.id.sw_show_group_member_nickname:
                break;
            case R.id.tv_clean_chat_record:
                break;
            case R.id.group_quit:
                break;
            case R.id.group_dismiss:
                break;
        }
    }
}
