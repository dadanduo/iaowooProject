package com.iaowoo.mobile.im;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.DB.im.DBManager;
import com.iaowoo.mobile.Ui.classification.Model.im.GetTokenResponse;
import com.iaowoo.mobile.Ui.classification.Model.im.GetUserInfoResponse;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.util.HashMap;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class RongIMUtils {
    // 记录token过期重新请求计数值
    private static int num = 0;

    public static void connect(final Context context, final String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d("TAG", "--onTokenIncorrect");
                if (num < 4) {
                    getToken(context);
                }
                num++;
            }

            @Override
            public void onSuccess(String userId) {
                Log.d("TAG", "--onSuccess" + userId);
                PrefManager.getInstance().setRongIMUserId(userId);
                getIMUserInfo(context);
                DBManager.getInstance().getAllUserInfo(context);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d("TAG", "--onError");
            }
        });
    }

    /**
     * 连接融云服务器
     *
     * @param context
     */
    public static void connect(Context context) {
        // 在用户登录情况下
        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            String token = PrefManager.getInstance().getRongIMToken();
            if (!TextUtils.isEmpty(token)) {
                connect(context, token);
            } else {
                String loginToken = PrefManager.getInstance().getToken();
                getToken(context, loginToken);
            }
        }
    }


    public static void getToken(final Context context){
        String loginToken = PrefManager.getInstance().getToken();
        getToken(context, loginToken);
    }
    /**
     * 从Server获取融云token
     * @param context
     */
    public static void getToken(final Context context, String loginToken) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",loginToken);
        OkhttpManager.getInstance(context).requestPostByAsyn(false, UtilsOkHttpAll.GET_IM_TOKEN, "GET_IM_TOKEN", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                GetTokenResponse getTokenResponse = JSON.parseObject(result.toString(), GetTokenResponse.class);
                if (getTokenResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    GetTokenResponse.BodyBean.ContentBean contentBean = getTokenResponse.getBody().getContent();
                    if (contentBean != null) {
                        String rongToken = contentBean.getToken();
                        if (!TextUtils.isEmpty(rongToken)) {
                            PrefManager.getInstance().setRongIMToken(rongToken);
                            connect(context, rongToken);
                        }
                    }
                } else {
                    ToastUtilsAll.getInstance().show(getTokenResponse.getDescribe());
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }


    /**
     * 退出登录的处理
     * @param context
     */
    public static void logout(Context context){
        PrefManager.getInstance().setRongIMToken("");
        RongIM.getInstance().disconnect();
    }

    public static void getIMUserInfo(final Context context) {
        String loginToken = PrefManager.getInstance().getToken();
        getIMUserInfo(context, loginToken, "", "");
    }

    public static void getIMUserInfo(final Context context, String loginToken, String userId, String mobileNo) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",loginToken);
        paramsMap.put("userId",userId);
        paramsMap.put("mobileNo",mobileNo);
        OkhttpManager.getInstance(context).requestPostByAsyn(false, UtilsOkHttpAll.IM_GET_USER_INFO, "IM_GET_USER_INFO", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                GetUserInfoResponse getUserInfoResponse = JSON.parseObject(result.toString(),GetUserInfoResponse.class);
                if (getUserInfoResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    GetUserInfoResponse.BodyBean.ContentBean contentBean = getUserInfoResponse.getBody().getContent();
                    if (contentBean != null) {
                        String nickName = contentBean.getNickname();
                        String portraitUri = contentBean.getWechatHeadImgUrl();
                        String connectResultId = "" + contentBean.getUserId();
                        if (!TextUtils.isEmpty(nickName) && !TextUtils.isEmpty(portraitUri)) {
                            RongIM.getInstance().refreshUserInfoCache(new UserInfo(connectResultId, nickName, Uri.parse(portraitUri)));
                        }
                    }
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }
}
