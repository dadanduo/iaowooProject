package com.iaowoo.mobile.Ui.classification.Presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Model.SetPassword;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.util.HashMap;

/**
 * ////////////////////////
 * //  ┏┓　　　┏┓///////////
 * //┏┛┻━━━┛┻┓ ////////////
 * //┃　　　　　　　┃     ////
 * //┃　　　━　　　┃     ////
 * //┃　┳┛　┗┳　┃       /////
 * //┃　　　　　　　┃     ////
 * //┃　　　┻　　　┃         //
 * //┃　　　　　　　┃        ///
 * //┗━┓　　　┏━┛           ///
 * //    ┃　　　┃   神兽保佑  ///
 * //    ┃　　　┃   代码无BUG！///
 * //    ┃　　　┗━━━┓     ///
 * //    ┃　　　　　　　┣┓ ///
 * //    ┃　　　　　　　┏┛ ///
 * //    ┗┓┓┏━┳┓┏┛      ///
 * //      ┃┫┫　┃┫┫     ///
 * ///////////////////////
 *
 * @author ${chenda}
 * @version V1.0
 * @Description: ${todo}()
 * @date 2018/12/7
 * @email ${18011009889@163.com}
 */
public class SettingPayPasswordProsenter {


    public interface  CallBack{
        void SettingOk();
    }
    public void sendPayPassword_sms_code(String mobile){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("mobileNo",mobile);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.SEND_PAY_PASSWORD_SMS,"SEND_PAY_PASSWORD_SMS",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    SetPassword setPassword=JSON.parseObject(result.toString(),SetPassword.class);
                    if(setPassword.getCode().endsWith(OkhttpManager.SUCESS)){
                        ToastUtilsAll.getInstance().showShortToast("发送验证码成功，请您注意查收！");
                    }else{
                        ToastUtilsAll.getInstance().showShortToast(setPassword.getDescribe()+"");
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }
        });
    }

    public void setPayPassword_sms_code(String validCode , String payPwd, String comfirmPwd, final CallBack callBack){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken", PrefManager.getInstance().getToken()+"");
        paramsMap.put("validCode",validCode);
        paramsMap.put("payPwd",payPwd);
        paramsMap.put("comfirmPwd",comfirmPwd);

        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.SETTING_PAY_PASSWORD_SMS,"SETTING_PAY_PASSWORD_SMS",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    SetPassword setPassword = JSON.parseObject(result.toString(), SetPassword.class);
                    if (setPassword.getCode().endsWith(OkhttpManager.SUCESS)) {
                           callBack.SettingOk();
                    } else {
                        ToastUtilsAll.getInstance().showShortToast(setPassword.getDescribe() + "");
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
