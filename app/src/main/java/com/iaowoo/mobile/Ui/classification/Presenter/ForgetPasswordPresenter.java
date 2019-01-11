package com.iaowoo.mobile.Ui.classification.Presenter;

import android.os.CountDownTimer;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.interfaceCallback.ForgetPasswordCallBack;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.Ui.classification.Model.Response;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.interfaceCallback.ForgetPasswordCallBack;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.plp.underlying.networkframwork.OkhttpManager;


/**
 * Created by chenda on 2018/4/11.
 */

public class ForgetPasswordPresenter {

    private boolean click=true;

    private ForgetPasswordCallBack forgetPasswordCallBack;

    public ForgetPasswordPresenter() {

    }
    public void setInterface(ForgetPasswordCallBack forgetPasswordCallBack)
    {
        this.forgetPasswordCallBack=forgetPasswordCallBack;
    }
    public  void  getValidation(String phone_input )
    {
        if(!UtilsAll.isMobileNO(phone_input))
        {
            ToastUtilsAll.getInstance().showShortToast("请输入正确的手机号码！");
        }else{
            if(click) {
                click = false;
                XutilsHttp.getInstance().postEncodedSendForgetsmsCode(new OkhttpCallBack() {
                    @Override
                    public void OnSuccess(String json) {
                        Response response = JSON.parseObject(json, Response.class);
                        if (response.getCode().equals(OkhttpManager.SUCESS)) {
                            ToastUtilsAll.getInstance().showShortToast("验证码已经发送到你手机请注意查收");
                            startTime();
                        } else {
                            ToastUtilsAll.getInstance().showShortToast(response.getDescribe());
                            click=true;
                        }
                    }
                    @Override
                    public void OnFaild(String faildM) {
                        ToastUtilsAll.getInstance().showShortToast("网络出现问题了！");
                    }
                }, phone_input, XutilsHttp.CHANNEL, XutilsHttp.PRODUCT);
            }
        }
    }

    public void submitOk(String phone_input,String validation,String password,String repassword) {
        if(!UtilsAll.isMobileNO(phone_input)) {
            ToastUtilsAll.getInstance().showShortToast("请输入正确的手机号码！");
        }else if(TextUtils.isEmpty(validation)){
            ToastUtilsAll.getInstance().showShortToast("请输入验证码！");
        }else if(TextUtils.isEmpty(password)){
            ToastUtilsAll.getInstance().showShortToast("请输入密码！");
        }else if(TextUtils.isEmpty(repassword)){
            ToastUtilsAll.getInstance().showShortToast("请再次输入密码！");
        }else if(!password.equals(repassword)){
            ToastUtilsAll.getInstance().showShortToast("两次输入密码不一致！");
        }else{
            XutilsHttp.getInstance().postEncodedForgetOk(new OkhttpCallBack() {
                @Override
                public void OnSuccess(String json) {
                    LogPrint.printError(json);
                    Response response =JSON.parseObject(json,Response.class);
                    if (response.getCode().equals(OkhttpManager.SUCESS)) {
                        ToastUtilsAll.getInstance().showShortToast("密码修改成功");
                        forgetPasswordCallBack.finish("密码修改成功");
                    }else{
                        ToastUtilsAll.getInstance().showShortToast(response.getDescribe());
                    }
                }
                @Override
                public void OnFaild(String faildM) {
                    ToastUtilsAll.getInstance().showShortToast("网络出现问题了！");
                }
            },phone_input,UtilsAll.encryptionPassword(phone_input,password).toString(),UtilsAll.encryptionPassword(phone_input,repassword).toString(),validation, XutilsHttp.CHANNEL, XutilsHttp.PRODUCT);

        }
    }

    //开启倒计时
    public void startTime()
    {
        /** 倒计时60秒，一次1秒 */
        CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                forgetPasswordCallBack.ShowTime(millisUntilFinished / 1000 + "秒" + "后重试");
            }
            @Override
            public void onFinish() {
                forgetPasswordCallBack.ShowTime("获取验证码");
                click=true;
            }
        }.start();
    }
}
