package com.iaowoo.mobile.Ui.classification.Presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.MsgModel;
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
 * @date 2019/1/8
 * @email ${18011009889@163.com}
 */
public class MesssageProsenter {

    private messageNumberCallBack messageNumberCallBack;
    public void setCallBack(messageNumberCallBack messageNumberCallBack){
        this.messageNumberCallBack=messageNumberCallBack;
    }
    public interface  messageNumberCallBack{
        void Msg(MsgModel.BodyBean.ContentBean contentBean);
    }

    /**
     * 获取消息数量
     */
    public void Get_message_number(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",PrefManager.getInstance().getToken());

        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.GET_MESSAGE_NUMBER,"GET_MESSAGE_NUMBER",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("消息数量"+result.toString());
                    MsgModel msgModel=JSON.parseObject(result.toString(), MsgModel.class);
                    if(msgModel.getCode().endsWith(OkhttpManager.SUCESS)){
                        messageNumberCallBack.Msg(msgModel.getBody().getContent());
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }

        });
    }

    /**
     * 清空消息数量
     */
    public void Clear_message_number(String type){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",PrefManager.getInstance().getToken());
        paramsMap.put("type",type);

        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.EMPTY_MESSAGE_NUMBER,"EMPTY_MESSAGE_NUMBER",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("清空消息"+result.toString());
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }

        });
    }
}
