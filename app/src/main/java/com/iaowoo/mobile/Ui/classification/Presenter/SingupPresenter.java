package com.iaowoo.mobile.Ui.classification.Presenter;

import android.os.CountDownTimer;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Model.Response;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.interfaceCallback.SingupCallBack;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;


/**
 * Created by chenda on 2018/4/11.
 */

public class SingupPresenter {

    private SingupCallBack singupCallBack;

    private boolean click=true;


    public void setInterface(SingupCallBack singupCallBack)
    {
        this.singupCallBack=singupCallBack;
    }

    public void getValidation(String singup_phone)
    {
        if (!UtilsAll.isMobileNO(singup_phone)) {
            ToastUtilsAll.getInstance().showShortToast("请输入正确的手机号码");
        } else {
            if(click){
                click=false;
                XutilsHttp.getInstance().postEncodedsendGetsms(new OkhttpCallBack() {
                    @Override
                    public void OnSuccess(String json) {
                        Response response= JSON.parseObject(json,Response.class);
                        if (response.getCode().equals(OkhttpManager.SUCESS)) {
                            ToastUtilsAll.getInstance().showShortToast("验证码已经发送到你手机请注意查收");
                            startTime();
                        }else{
                            ToastUtilsAll.getInstance().showShortToast(response.getDescribe());
                            click=true;
                        }
                    }
                    @Override
                    public void OnFaild(String faildM) {
                        ToastUtilsAll.getInstance().showShortToast("网络出现问题了！");
                    }
                }, singup_phone, XutilsHttp.CHANNEL, XutilsHttp.PRODUCT);

            }
        }
    }
    //注册
    public void register(String singup_phone,String password,String validation,String inviteCode)
    {
        if (!UtilsAll.isMobileNO(singup_phone)) {
            ToastUtilsAll.getInstance().showShortToast("请输入正确的手机号码");
        } else if (TextUtils.isEmpty(validation)) {
            ToastUtilsAll.getInstance().showShortToast( "请输入验证码");
        } else if(TextUtils.isEmpty(password)){
            ToastUtilsAll.getInstance().showShortToast( "请先输入密码");
        }else {
            singupCallBack.showD();
            String jia_password=UtilsAll.encryptionPassword(singup_phone,password);
            String password1=jia_password.replaceAll("\n", "");
            try {
                String password_new = URLEncoder.encode(password1,"UTF-8");
                register_btn(singup_phone,password_new,validation,inviteCode);
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 注册
     * @param singup_phone
     * @param password
     * @param validation
     * @param inviteCode
     */
    public void register_btn(final String singup_phone,String password, String validation, String inviteCode) {
        XutilsHttp.getInstance().postEncodedRegister(new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                if(!TextUtils.isEmpty(json)){
                    if(!TextUtils.isEmpty(json)) {
                        Response response = JSON.parseObject(json, Response.class);
                        if(response!=null) {
                            if (response.getCode().equals(OkhttpManager.SUCESS)) {
                                if (!TextUtils.isEmpty(response.getBody().getContent().getLoginToken())) {
                                    //把token存起来
                                    PrefManager.getInstance().setToken(response.getBody().getContent().getLoginToken());
                                    if (!TextUtils.isEmpty(response.getBody().getContent().getInviteCode())) {
                                        //把邀请码存起来
                                        PrefManager.getInstance().setInvite(response.getBody().getContent().getInviteCode());
                                    }
                                    PrefManager.getInstance().setPassWordLogin(false);
                                    ToastUtilsAll.getInstance().showShortToast("注册并登录成功");
                                    UtilsAll.addLoginInfo(false, singup_phone, "", "");
                                    if (TextUtils.isEmpty(ZApplication.REGISTERID)) {
                                        LogPrint.printError("register还是为空" + ZApplication.REGISTERID);
                                        ToastUtilsAll.getInstance().showShortToast("registerId为空");

                                    } else {
                                        LoginPresenter.sendRegister(ZApplication.REGISTERID, response.getBody().getContent().getLoginToken());
                                        LogPrint.print("token：" + response.getBody().getContent(), 4);
                                    }
                                    //保存账号
                                    PrefManager.getInstance().saveMobile(singup_phone);
                                    singupCallBack.singUpSuccess();
                                }
                            } else {
                                singupCallBack.hideD();
                                ToastUtilsAll.getInstance().showShortToast(response.getDescribe());
                            }
                        }
                    }
                }
            }
            @Override
            public void OnFaild(String faildM) {
                singupCallBack.hideD();
                ToastUtilsAll.getInstance().showShortToast("网络出现问题了！");
            }
        }, singup_phone,password,validation, inviteCode,XutilsHttp.CHANNEL, XutilsHttp.PRODUCT);

    }

    //开启倒计时
    public void startTime()
    {
        /** 倒计时60秒，一次1秒 */
        CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                singupCallBack.showTime(millisUntilFinished / 1000 + "秒" + "后重试");
            }
            @Override
            public void onFinish() {
                singupCallBack.showTime("获取验证码");
                click=true;
            }
        }.start();
    }

    /**
     * 注册
     */
    public void getSingUp(final String singup_phone,String password, String validation, String inviteCode){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("mobileNo",singup_phone);
        paramsMap.put("password",password);
        paramsMap.put("validCode",validation);
        paramsMap.put("inviteCode",inviteCode);
        paramsMap.put("channel","xuanhao");
        paramsMap.put("product","iaowoo-Android");
        OkhttpManager.getInstance(ZApplication.getInstance().getApplicationContext()).requestPostByAsyn(true,UtilsOkHttpAll.REGISTERTEST,"register_by_valid_code",-1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())){
                    if(!TextUtils.isEmpty(result.toString())) {
                        Response response = JSON.parseObject(result.toString(), Response.class);
                        if(response!=null) {
                            if (response.getCode().equals(OkhttpManager.SUCESS)) {
                                if (!TextUtils.isEmpty(response.getBody().getContent().getLoginToken())) {
                                    //把token存起来
                                    PrefManager.getInstance().setToken(response.getBody().getContent().getLoginToken());
                                    if (!TextUtils.isEmpty(response.getBody().getContent().getInviteCode())) {
                                        //把邀请码存起来
                                        PrefManager.getInstance().setInvite(response.getBody().getContent().getInviteCode());
                                    }
                                    PrefManager.getInstance().setPassWordLogin(false);
                                    ToastUtilsAll.getInstance().showShortToast("注册并登录成功");
                                    UtilsAll.addLoginInfo(false, singup_phone, "", "");
                                    if (TextUtils.isEmpty(ZApplication.REGISTERID)) {
                                        LogPrint.printError("register还是为空" + ZApplication.REGISTERID);
                                        ToastUtilsAll.getInstance().showShortToast("registerId为空");

                                    } else {
                                        LoginPresenter.sendRegister(ZApplication.REGISTERID, response.getBody().getContent().getLoginToken());
                                        LogPrint.print("token：" + response.getBody().getContent(), 4);
                                    }
                                    //保存账号
                                    PrefManager.getInstance().saveMobile(singup_phone);
                                    singupCallBack.singUpSuccess();
                                }
                            } else {
                                singupCallBack.hideD();
                                ToastUtilsAll.getInstance().showShortToast(response.getDescribe());
                            }
                        }
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }
        });
    }

}
