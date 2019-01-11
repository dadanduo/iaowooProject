package com.iaowoo.mobile.Ui.classification.Presenter;

import android.os.CountDownTimer;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.interfaceCallback.LoginCallBack;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.DB.LoginInfo;
import com.iaowoo.mobile.Ui.classification.Model.APPCONFIG;
import com.iaowoo.mobile.Ui.classification.Model.Response;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.interfaceCallback.LoginCallBack;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.util.HashMap;
import java.util.List;


/**
 * Created by chenda on 2018/4/11.
 */

public class LoginPresenter  {
    private LoginCallBack loginCallBack;
    private  boolean click = true;
    private int tag;
    public LoginPresenter(int tag) {
        this.tag=tag;
    }

    public void setLoginInterface(LoginCallBack loginCallBack)
    {
        this.loginCallBack=loginCallBack;
    }



    //获取验证码
    public void  getValidation(String edit_phone)
    {
        if(!UtilsAll.isMobileNO(edit_phone)) {
            ToastUtilsAll.getInstance().showShortToast( "请输入正确的手机号码");
        } else {
            if (click) {
                click=false;
                loginCallBack.showD();
                //获取登录的验证码
                XutilsHttp.getInstance().postEncodedSendLoginSms(new OkhttpCallBack() {
                    @Override
                    public void OnSuccess(String json) {
                        Response response = JSON.parseObject(json, Response.class);
                        if (response.getCode().equals(OkhttpManager.SUCESS)) {
                            ToastUtilsAll.getInstance().showShortToast("验证码已经发送到你手机请注意查收");
                            startTime();
                            loginCallBack.hideD();
                        } else {
                            ToastUtilsAll.getInstance().showShortToast(response.getDescribe());
                            loginCallBack.hideD();
                            click=true;
                        }
                    }

                    @Override
                    public void OnFaild(String faildM) {
                        //没有网络
                        ToastUtilsAll.getInstance().showShortToast("网络出现问题了！");
                        loginCallBack.hideD();
                    }
                }, edit_phone, XutilsHttp.CHANNEL, XutilsHttp.PRODUCT);
            }
        }
    }

    public void startLogin(String edit_phone_style,boolean phone_and_password,String validation,String password,String password_phone_style){
        //密码登录
        if(phone_and_password) {
            if(!UtilsAll.isMobileNO(password_phone_style)) {
                ToastUtilsAll.getInstance().showShortToast("请输入正确的手机号码！");
            }else if(TextUtils.isEmpty(password)) {
                ToastUtilsAll.getInstance().showShortToast("请输入密码！");
            }else{
                passwordLogin(password_phone_style, password);
            }
        } else{  //验证码登录
            if(!UtilsAll.isMobileNO(edit_phone_style)) {
                ToastUtilsAll.getInstance().showShortToast("请输入正确的手机号码！");
            }else if(TextUtils.isEmpty(validation)){
                ToastUtilsAll.getInstance().showShortToast("请输入验证码！");
            }else {
                smsCodeLogin(edit_phone_style, validation);
            }
        }



    }
    //开启倒计时
    public  void  startTime()
    {
        /** 倒计时60秒，一次1秒 */
        CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                loginCallBack.showTime(millisUntilFinished/1000+"秒"+"后重试");
            }
            @Override
            public void onFinish() {
                loginCallBack.showTime("获取验证码");
                loginCallBack.hideD();
                click=true;
            }
        }.start();
    }

    //验证码登录
    public void  smsCodeLogin(final String mobileNo,String smsCode) {
        XutilsHttp.getInstance().postEncodedGetLoginBysms(new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                LogPrint.printError(json);
                Response response= JSON.parseObject(json,Response.class);
                if (response.getCode().equals(OkhttpManager.SUCESS)) {
                    if(!TextUtils.isEmpty(response.getBody().getContent().getLoginToken())) {
                        PrefManager.getInstance().setPassWordLogin(false);
                        //把token存起来
                        PrefManager.getInstance().setToken(response.getBody().getContent().getLoginToken());
                        if(!TextUtils.isEmpty(response.getBody().getContent().getInviteCode())) {
                            //把邀请码存起来
                            PrefManager.getInstance().setInvite(response.getBody().getContent().getInviteCode());
                        }
                        LoginInfo loginInfo= (LoginInfo) XutilsDBManage.getInstance().searchName(mobileNo,LoginInfo.class);
                        if(loginInfo!=null){
                            if(TextUtils.isEmpty(loginInfo.getPassWord())){
                                UtilsAll.addLoginInfo(false,mobileNo, "", "");
                            }
                        }else{
                            UtilsAll.addLoginInfo(false,mobileNo, "", "");
                        }
                        //把token和registerid给后台
                        if(TextUtils.isEmpty(ZApplication.REGISTERID)) {
                            loginCallBack.getRegisteId();
                            LogPrint.printError("register还是为空"+ZApplication.REGISTERID);
                        }else {
                            sendRegister(ZApplication.REGISTERID, response.getBody().getContent().getLoginToken());
                            LogPrint.print("token："+response.getBody().getContent(),4);
                        }
                        ToastUtilsAll.getInstance().showShortToast("登录成功");

                        loginCallBack.saveM(mobileNo);
                        loginCallBack.finish_activity();
                    }
                }else{
                    ToastUtilsAll.getInstance().showShortToast(response.getDescribe());
                }
            }
            @Override
            public void OnFaild(String faildM) {
                ToastUtilsAll.getInstance().showShortToast("网络出现问题了！");
            }
        },mobileNo,smsCode, XutilsHttp.CHANNEL, XutilsHttp.PRODUCT);

    }

    /**
     * 密码登录
     * @param mobileNo
     * @param password
     */
    public void  passwordLogin(final String mobileNo, final String password) {
        LogPrint.printError("账号"+mobileNo+"密码："+password);
        XutilsHttp.getInstance().postEncodedByPasswordLogin(new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                Response response= JSON.parseObject(json,Response.class);
                if (response.getCode().equals(OkhttpManager.SUCESS)) {
                    if(!TextUtils.isEmpty(response.getBody().getContent().getLoginToken())) {
                        //把token存起来
                        PrefManager.getInstance().setToken(response.getBody().getContent().getLoginToken());
                        if(!TextUtils.isEmpty(response.getBody().getContent().getInviteCode())) {
                            //把邀请码存起来
                            PrefManager.getInstance().setInvite(response.getBody().getContent().getInviteCode());
                        }
                        //设置密码登录
                        PrefManager.getInstance().setPassWordLogin(true);
                        //登录成功保存密码
                        PrefManager.getInstance().setPassWord(password);
                        if(tag==1) {
                            //表里有登录的账号
                            UtilsAll.addLoginInfo(true,mobileNo,password,"");
                        }else{
                            //表里有登录的账号
                            UtilsAll.addLoginInfo(false,mobileNo,password,"");
                        }

                        //把token和registerid给后台
                        if(TextUtils.isEmpty(ZApplication.REGISTERID)) {
                            loginCallBack.getRegisteId();
                            LogPrint.printError("register还是为空"+ZApplication.REGISTERID);
                            ToastUtilsAll.getInstance().showShortToast("registerId为空");
                        }else {
                            sendRegister(ZApplication.REGISTERID, response.getBody().getContent().getLoginToken());
                            LogPrint.print("token："+response.getBody().getContent(),4);
                        }
                        LogPrint.print("token：" + response.getBody().getContent(), 4);
                        ToastUtilsAll.getInstance().showShortToast("登录成功");
                        loginCallBack.finish_activity();
                        //记住账户
                        loginCallBack.saveM(mobileNo);
                    }
                }else{
                    ToastUtilsAll.getInstance().showShortToast(response.getDescribe());
                }
            }
            @Override
            public void OnFaild(String faildM) {
                ToastUtilsAll.getInstance().showShortToast("网络出现问题了！");
            }
        },mobileNo,UtilsAll.encryptionPassword(mobileNo,password), XutilsHttp.CHANNEL, XutilsHttp.PRODUCT);
    }

    /**
     * 发送验证码
     * @param registerid
     * @param token
     */
    public static void sendRegister(String registerid,String token) {
        XutilsHttp.getInstance().postRegisterIdSendService(new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                LogPrint.printError("register:"+json);
            }

            @Override
            public void OnFaild(String faildM) {
                LogPrint.printError("register:"+faildM);
            }
        },registerid,token);
    }

    /**
     * 查询配置信息
     */
    public void getAppConfig(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.APPCONFIG,"APPCONFIG",-1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("appConfig"+result.toString());
                    APPCONFIG appconfig=JSON.parseObject(result.toString(),APPCONFIG.class);
                    if(appconfig.getCode().endsWith(UtilsOkHttpAll.SUCESS)) {
                        if(appconfig.getBody().getContent().getThirdLogin().size()>0){
                            if(appconfig.getBody().getContent().getThirdLogin().get(0)!=null){
                                loginCallBack.AppConfig(appconfig.getBody().getContent().getThirdLogin().get(0).getStatus(),appconfig.getBody().getContent().getThirdLogin().get(0).getLinkUrl());
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
