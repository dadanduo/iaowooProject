package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.DB.im.DBManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;
import com.iaowoo.mobile.Ui.classification.Model.im.GetUserInfoResponse;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class UserDetailActivity extends TitleBarActivity {
    public static final String CLICK_ORIGIN = "type";
    public static final String USER_ID = "userId";
    @BindView(R.id.iv_user_portrait)
    ImageView ivUserPortrait;
    @BindView(R.id.tv_remark_name)
    TextView tvRemarkName;
    @BindView(R.id.iv_gender)
    ImageView ivGender;
    @BindView(R.id.tv_contact_account)
    TextView tvContactAccount;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.user_online_status)
    TextView userOnlineStatus;
    @BindView(R.id.group_info)
    LinearLayout groupInfo;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_personal_slogan)
    TextView tvPersonalSlogan;
    @BindView(R.id.ll_personal_info)
    LinearLayout llPersonalInfo;
    @BindView(R.id.btn_send_msg)
    Button btnSendMsg;
    @BindView(R.id.btn_add_friend)
    Button btnAddFriend;
    @BindView(R.id.btn_delete_friend)
    Button btnDeleteFriend;


    private GetUserInfoResponse.BodyBean.ContentBean contentBean;
    private boolean mIsFriendsRelationship;
    private String mUserId;
    private GlideUtils glideUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_user_detail);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        setTitle(R.string.user_details);
    }

    private void initData() {
        glideUtils = new GlideUtils();
        mUserId = getIntent().getStringExtra(USER_ID);
        if (!TextUtils.isEmpty(mUserId)) {
            String loginToken = PrefManager.getInstance().getToken();
            getIMUserInfoByUserId(loginToken, mUserId);
        }
    }


    private void getIMUserInfoByUserId(String loginToken, String userId) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken", loginToken);
        paramsMap.put("userId", userId);
        OkhttpManager.getInstance(this).requestPostByAsyn(false, UtilsOkHttpAll.IM_GET_USER_INFO, "IM_GET_USER_INFO", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                GetUserInfoResponse getUserInfoResponse = JSON.parseObject(result.toString(), GetUserInfoResponse.class);
                if (getUserInfoResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    contentBean = getUserInfoResponse.getBody().getContent();
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


    private void updateUI(GetUserInfoResponse.BodyBean.ContentBean contentBean) {
        String name = contentBean.getName();
        String nickName = contentBean.getNickname();
        String mobileNo = contentBean.getMobileNo();
        String headImgUrl = contentBean.getHeadImgUrl();
        int gender = contentBean.getGender();

        if (!TextUtils.isEmpty(name)) {
            tvRemarkName.setVisibility(View.VISIBLE);
            tvRemarkName.setText(name);
        }

        if (!TextUtils.isEmpty(mobileNo)) {
            tvContactAccount.setVisibility(View.VISIBLE);
            tvContactAccount.setText(getString(R.string.ac_contact_account) + " " + mobileNo);
        }

        if (!TextUtils.isEmpty(nickName)) {
            tvNickname.setVisibility(View.VISIBLE);
            tvNickname.setText(getString(R.string.ac_contact_nick_name) + " " + nickName);
        }

        if (gender == 0) {
            glideUtils.glides(this, headImgUrl, ivGender);
        } else if(gender == 1) {
            glideUtils.glides(this, headImgUrl, ivGender);
        }

        headImgUrl = "http://pic.58pic.com/58pic/13/80/78/35V58PICrWD_1024.jpg";
        if (!TextUtils.isEmpty(headImgUrl)) {
            glideUtils.glides(this, headImgUrl, ivUserPortrait);
        }

        syncPersonalInfo();
        String mySelf = PrefManager.getInstance().getUserId();
        if (mySelf.equals(mUserId)) {
            btnSendMsg.setVisibility(View.VISIBLE);
            btnAddFriend.setVisibility(View.GONE);
            btnDeleteFriend.setVisibility(View.GONE);
            return;
        }
        if (mIsFriendsRelationship) {
            btnSendMsg.setVisibility(View.VISIBLE);
            btnAddFriend.setVisibility(View.GONE);
            btnDeleteFriend.setVisibility(View.VISIBLE);
        } else {
            btnAddFriend.setVisibility(View.VISIBLE);
            btnSendMsg.setVisibility(View.GONE);
            btnDeleteFriend.setVisibility(View.GONE);
        }
    }

    private void syncPersonalInfo() {
        mIsFriendsRelationship = DBManager.getInstance().isMyFriend(mUserId);
        if (mIsFriendsRelationship) {
            String userId = mUserId;
            // 同步朋友信息
        } else {
            // 同步用户信息
        }
    }

    public void startChat(View view) {
        if (contentBean != null) {
            String nickname = contentBean.getNickname();
            String name = contentBean.getName();
            if (!TextUtils.isEmpty(nickname)) {
                RongIM.getInstance().startPrivateChat(this, mUserId, nickname);
            } else {
                RongIM.getInstance().startPrivateChat(this, mUserId, name);
            }
            finish();
        }
    }

    public static void startPage(Context context, String userId) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra(USER_ID, userId);
        context.startActivity(intent);
    }

    @OnClick({R.id.btn_send_msg, R.id.btn_add_friend, R.id.btn_delete_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_msg:
                startChat();
                break;
            case R.id.btn_add_friend:
                addFriend();
                break;
            case R.id.btn_delete_friend:
                deleteFriend();
                break;
        }
    }


    private void startChat() {

    }


    private void addFriend() {


    }

    private void deleteFriend() {

    }
}
