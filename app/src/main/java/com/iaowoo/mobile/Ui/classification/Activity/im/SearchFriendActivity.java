package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.AppConst;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.DB.im.DBManager;
import com.iaowoo.mobile.DB.im.model.Friend;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;
import com.iaowoo.mobile.Ui.classification.Model.im.CommonResponse;
import com.iaowoo.mobile.Ui.classification.Model.im.GetUserInfoResponse;
import com.iaowoo.mobile.Ui.classification.View.im.SelectableRoundedImageView;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFriendActivity extends TitleBarActivity {

    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_header)
    SelectableRoundedImageView searchHeader;
    @BindView(R.id.search_name)
    TextView searchName;
    @BindView(R.id.search_result)
    LinearLayout searchResult;

    private GlideUtils glideUtils;
    private String searchContent;
    private String mFriendId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_add_friends);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        glideUtils = new GlideUtils();
        setTitle("添加朋友");
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 6) {
                    searchContent = s.toString().trim();
                    hintKbTwo();
                    searchFriend(searchContent);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    // 搜索好友
    private void searchFriend(String searchContent) {
        showD();
        String loginToken = PrefManager.getInstance().getToken();
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken", loginToken);
        paramsMap.put("userId", searchContent);
        OkhttpManager.getInstance(this).requestPostByAsyn(false, UtilsOkHttpAll.IM_GET_USER_INFO, "IM_GET_USER_INFO", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                GetUserInfoResponse getUserInfoResponse = JSON.parseObject(result.toString(), GetUserInfoResponse.class);
                if (getUserInfoResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    GetUserInfoResponse.BodyBean.ContentBean contentBean = getUserInfoResponse.getBody().getContent();
                    if (contentBean != null) {
                        mFriendId = "" + contentBean.getUserId();
                        String name = contentBean.getName();
                        String portraitUri = contentBean.getHeadImgUrl();
                        String nickName = contentBean.getNickname();
                        String mobileNo = contentBean.getMobileNo();
                        searchResult.setVisibility(View.VISIBLE);

                        if (!TextUtils.isEmpty(name)) {
                            searchName.setText(name);
                        } else if (!TextUtils.isEmpty(nickName)) {
                            searchName.setText(nickName);
                        } else {
                            searchName.setText(mFriendId);
                        }

                        portraitUri = "http://pic.58pic.com/58pic/13/80/78/35V58PICrWD_1024.jpg";
                        if (!TextUtils.isEmpty(portraitUri)) {
                            glideUtils.glides(SearchFriendActivity.this, portraitUri, searchHeader);
                        }
                    } else {
                        searchResult.setVisibility(View.GONE);
                    }
                } else {
                    ToastUtilsAll.getInstance().show(getUserInfoResponse.getDescribe());
                }
                hideD();
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }

    // 添加好友
    private void addFriend(String friendId) {
        showD("添加好友请求中");
        String loginToken = PrefManager.getInstance().getToken();
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken", loginToken);
        paramsMap.put("friendId", friendId);
        paramsMap.put("status", 10);
        OkhttpManager.getInstance(this).requestPostByAsyn(false, UtilsOkHttpAll.IM_FRIEND_OPT, "IM_FRIEND_OPT", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                CommonResponse commonResponse = JSON.parseObject(result.toString(), CommonResponse.class);
                if (commonResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    ToastUtilsAll.getInstance().show("添加好友成功，请等待同意");
                } else {
                    ToastUtilsAll.getInstance().show(commonResponse.getDescribe());
                }
                hideD();
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }

    @OnClick(R.id.search_result)
    public void onViewClicked() {
        if (isFriendOrSelf(mFriendId)) {
            Intent intent = new Intent(SearchFriendActivity.this, UserDetailActivity.class);
            intent.putExtra(UserDetailActivity.CLICK_ORIGIN, AppConst.CLICK_CONVERSATION_USER_PORTRAIT);
            intent.putExtra(UserDetailActivity.USER_ID, mFriendId);
            startActivity(intent);
            return;
        }
        addFriend(mFriendId);
    }

    private boolean isFriendOrSelf(String mFriendId) {
        String imUserId = PrefManager.getInstance().getRongIMUserId();
        boolean isMyFriend = DBManager.getInstance().isMyFriend(mFriendId);
        return imUserId.equals(mFriendId) || isMyFriend;
    }
}