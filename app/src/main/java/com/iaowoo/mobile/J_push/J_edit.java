package com.iaowoo.mobile.J_push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.EvenBus.EventBusMessageJG;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.DB.MessageNumber;
import com.iaowoo.mobile.EvenBus.EventBusMessageJG;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.Ui.classification.Activity.LoginActivity;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * 消息处理
 */
public class J_edit {
    public  static boolean oneShow=true;

    /**
     * @param json
     * @param context
     */
    //处理极光消息
    public  void setCostomMsg(String json, final Context context) {
        LogPrint.printError("极光推送json:" + json);
        if (!TextUtils.isEmpty(json)&&!json.equals("{}")) {
            JG jg = JSON.parseObject(json, JG.class);
            String json_j = jg.getExtraParams();
            LogPrint.printError("处理后的json：" + json_j);
            // 这句代码就是为了检验效果的
            JG_MESSAGE jg_message = JSON.parseObject(json_j, JG_MESSAGE.class);
            switch (jg_message.getType()){
                //公告
                case  "50000":
                    //过滤中奖消息
                    if(!jg_message.getSubType().endsWith("50102")){
                        messageM(1);
                    }
                    break;
                //系统消息
                case  "10000":
                    //账号失效或者被其他设备登录不记录
                    if(jg_message.getSubType().endsWith(ZApplication.CODE)||jg_message.getSubType().endsWith(ZApplication.CODE_LOSE)) {
                    }else{
                        messageM(2);
                    }
                    break;
                //交易消息
                case  "20000":
                    messageM(3);
                    break;
                //订单消息
                case  "30000":
                    messageM(4);
                    break;
                default:
                    break;
            }

        }
    }
    /**
     * @param json
     * @param context
     */
    //处理极光消息
    public  void setCostomMsg1(String json, final Context context) {
        LogPrint.printError("极光推送json:" + json);
        if (!TextUtils.isEmpty(json)&&!json.equals("{}")) {
            JG jg = JSON.parseObject(json, JG.class);
            String json_j = jg.getExtraParams();
            LogPrint.printError("处理后的json：" + json_j);
            // 这句代码就是为了检验效果的
            JG_MESSAGE jg_message = JSON.parseObject(json_j, JG_MESSAGE.class);
            switch (jg_message.getSubType()) {
                //账号在其他地方登陆
                case ZApplication.CODE:
                    if (oneShow)
                        J_edit.oneShow = false;
                    //处理之后清空
                    PrefManager.getInstance().saveJGMsm("");
                    if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                        PrefManager.getInstance().setToken("");
                        RdioBroadCast rdioBroadCast=new RdioBroadCast();
                        rdioBroadCast.sendData(context, RdioBroadCast.DATA, "", RdioBroadCast.BOARD, "logout");
                        DialogUtils dialogUtils=new DialogUtils();
                        dialogUtils.AlertDilog(context, "温馨提示", "您的账号已使用其他设备登录，如非本人操作，请注意保护个人隐私!", "", "确认", new alertCallBack() {
                            @Override
                            public void OnOk() {
                            }
                            @Override
                            public void OnNo() {
                                J_edit.oneShow = true;
                                context.startActivity(new Intent(context, LoginActivity.class));
                                EventBus.getDefault().post(new EventBusMessageRefresh(0));
                            }
                        });
                    }
                    break;
                //账号失效
                case ZApplication.CODE_LOSE:
                    if (J_edit.oneShow)
                        J_edit.oneShow = false;
                    //处理之后清空
                    PrefManager.getInstance().saveJGMsm("");
                    if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                        PrefManager.getInstance().setToken("");
                        RdioBroadCast rdioBroadCast=new RdioBroadCast();
                        rdioBroadCast.sendData(context, RdioBroadCast.DATA, "", RdioBroadCast.BOARD, "logout");
                        DialogUtils dialogUtils=new DialogUtils();
                        dialogUtils.AlertDilog(context, "温馨提示", "您的账号登录已失效，请重新登录!", "", "确认", new alertCallBack() {
                            @Override
                            public void OnOk() {
                            }

                            @Override
                            public void OnNo() {
                                J_edit.oneShow = true;
                                context.startActivity(new Intent(context, LoginActivity.class));
                            }
                        });
                    }
                    break;
                case "50102":
                    String j_g_two=jg_message.getExtraParams();
                    if(!TextUtils.isEmpty(j_g_two)){
                        J_Two_Edit j_two_edit=JSON.parseObject(j_g_two,J_Two_Edit.class);
                        EventBus.getDefault().post(new EventBusMessageJG( jg_message.getPushContent()+"",j_two_edit.getHeadImgUrl()+""));
                    }else{
                        EventBus.getDefault().post(new EventBusMessageJG( jg_message.getPushContent()+"",""));
                    }
                    break;
                //推给h5
                default:
                    if (J_edit.oneShow)
                        J_edit.oneShow = false;
//                    //处理之后清空
//                    PrefManager.getInstance().saveJGMsm("");
//                    RdioBroadCast rdioBroadCast=new RdioBroadCast();
//                    rdioBroadCast.sendData(context, RdioBroadCast.DATA, json, RdioBroadCast.BOARD, "jiguang");
                    break;
            }
        }
    }

    /**
     * 添加消息信息
     * @param tag
     */
    public  void  messageM(int tag){
        //刷新小红点
        EventBus.getDefault().post(new EventBusMessageRefresh(0));
        //查询表里所有数据
        List<MessageNumber> messageNumbers=XutilsDBManage.getInstance().loadTableAll(MessageNumber.class);
        if(messageNumbers!=null) {
            for (int i = 0; i < messageNumbers.size();i++){
                LogPrint.printError("数据来"+messageNumbers.get(i).getUid());
                if(messageNumbers.get(i).getUid().endsWith(PrefManager.getInstance().getInvite()==null?"":PrefManager.getInstance().getInvite())){
                    MessageNumber messageNumber=messageNumbers.get(i);
                    if (messageNumber!= null) {
                        int j;
                        switch (tag){
                            case 1:
                                j= messageNumber.getG_g();
                                LogPrint.printError("公告的消息数" + j);
                                int new1 = j + 1;
                                messageNumber.setG_g(new1);
                                break;
                            case 2:
                                j= messageNumber.getX_t();
                                LogPrint.printError("系统的消息数" + j);
                                int new2 = j + 1;
                                messageNumber.setX_t(new2);
                                break;
                            case 3:
                                j= messageNumber.getJ_y();
                                LogPrint.printError("交易的消息数" + j);
                                int new3 = j+1;
                                messageNumber.setJ_y(new3);
                                XutilsDBManage.getInstance().saveOrUpdate(messageNumber);
                                break;
                            case 4:
                                j= messageNumber.getD_d();
                                LogPrint.printError("订单的消息数" + j);
                                int new4= j + 1;
                                messageNumber.setD_d(new4);
                                break;
                            case 5:
                                j= messageNumber.getK_f();
                                LogPrint.printError("客服的消息数" + j);
                                int new5= j + 1;
                                messageNumber.setK_f(new5);
                                break;

                        }
                        XutilsDBManage.getInstance().saveOrUpdate(messageNumber);
                    }

                }
            }
        }
    }


}
