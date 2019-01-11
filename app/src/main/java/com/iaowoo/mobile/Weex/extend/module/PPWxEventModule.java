package com.iaowoo.mobile.Weex.extend.module;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.iaowoo.mobile.Application.MyActivityManager;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageWebview;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.H5toAndroid.JsMethod2;
import com.iaowoo.mobile.H5toAndroid.modle.CALLTELEPHONE;
import com.iaowoo.mobile.H5toAndroid.modle.COPY;
import com.iaowoo.mobile.H5toAndroid.modle.PAYGET;
import com.iaowoo.mobile.H5toAndroid.modle.QIYUOUT;
import com.iaowoo.mobile.H5toAndroid.modle.SHARE;
import com.iaowoo.mobile.H5toAndroid.modle.SHOPDETAILS;
import com.iaowoo.mobile.H5toAndroid.modle.TOKEN;
import com.iaowoo.mobile.H5toAndroid.modle.TYPE;
import com.iaowoo.mobile.H5toAndroid.modle.Umcustom;
import com.iaowoo.mobile.H5toAndroid.modle.WECHATEPAY;
import com.iaowoo.mobile.H5toAndroid.modle.XCX;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.CodeDetailsActivity;
import com.iaowoo.mobile.Ui.classification.Activity.LoginActivity;
import com.iaowoo.mobile.Ui.classification.Activity.PhotoActivity;
import com.iaowoo.mobile.Ui.classification.Activity.ScanActicvity;
import com.iaowoo.mobile.Ui.classification.Activity.SearchOneActivity;
import com.iaowoo.mobile.Ui.classification.Activity.SettingActivity;
import com.iaowoo.mobile.Ui.classification.Activity.ShopActivity;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Model.MYMESSAGE;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideImageLoader;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Weex.extend.Model.AddPassword;
import com.iaowoo.mobile.Weex.extend.Model.AddPs;
import com.iaowoo.mobile.Weex.extend.Model.ChannelCallback;
import com.iaowoo.mobile.Weex.extend.Model.FENLEI;
import com.iaowoo.mobile.Weex.extend.Model.PushUrl;
import com.iaowoo.mobile.Weex.extend.Model.ShopDetails;
import com.iaowoo.mobile.Weex.extend.Model.TagData;
import com.iaowoo.mobile.Weex.extend.Model.UserInfo;
import com.iaowoo.mobile.Weex.extend.Model.UserInfoWithToken;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.iaowoo.mobile.umeng.ShareUtils;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.SoftReference;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by chenda
 */
public class PPWxEventModule extends WXModule {
    //弹出登录框
    @JSMethod(uiThread = false)
    public void showUserLogin() {
        SoftReference<DialogUtils> dialogUtils=new SoftReference<>(new DialogUtils());
        String tokenvalue = "";
        String inviteCode = "";
        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            tokenvalue = PrefManager.getInstance().getToken();
        }
        if (!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
            inviteCode = PrefManager.getInstance().getInvite();
        }
        TOKEN token = new TOKEN();
        token.setToken(tokenvalue);
        token.setInviteCode(inviteCode);
        String json = ZApplication.gson.toJson(token);
        if (TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            if (dialogUtils.get() != null) {
                //弹框
                dialogUtils.get().LoginTo(MyActivityManager.getInstance().getCurrentActivity());
                LogPrint.printError("userLogin" + json);
            } else {
                dialogUtils = new SoftReference<>(new DialogUtils());
                //弹框
                dialogUtils.get().LoginTo(MyActivityManager.getInstance().getCurrentActivity());
                LogPrint.printError("userLogin" + json);
            }

        }    }
    //获取渠道
    @JSMethod(uiThread = false)
    public void getChannelCallback(JSCallback callback) {
        ChannelCallback channelCallback=new ChannelCallback();
        channelCallback.setChannel("2");
        callback.invoke(channelCallback);
    }

    //字符串加密
    @JSMethod(uiThread = false)
    public void  passwordEncryption(String msg,JSCallback callback) {
        AddPassword addPassword=JSON.parseObject(msg,AddPassword.class);
        String passwordone=addPassword.getPassword();
        String passwordtwo=UtilsAll.encryptionPasswordString(passwordone);
        AddPassword newObject=new AddPassword();
        newObject.setPassword(passwordtwo);
        AddPs addPs=new AddPs();
        addPs.setResult("success");
        addPs.setData(newObject);
        callback.invoke(addPs);
    }


    //获取用户token 或者 用户信息
    @JSMethod(uiThread = false)
    public void   getUserInfoWithKey(String msg,JSCallback callback) {
        //给token给weex
        if(msg.endsWith("loginToken")){
            UserInfoWithToken userInfoWithKey= new UserInfoWithToken();
            userInfoWithKey.setData(PrefManager.getInstance().getToken());
            userInfoWithKey.setResult("success");
            callback.invoke(userInfoWithKey);
        }else{
            //给用户信息给weex
            String userJson=PrefManager.getInstance().getUserMsg();
            if(!TextUtils.isEmpty(userJson)){
                MYMESSAGE.BodyBean.ContentBean contentBean=JSON.parseObject(userJson, MYMESSAGE.BodyBean.ContentBean.class);
                UserInfo userInfoWithKey= new UserInfo();
                userInfoWithKey.setData(contentBean);
                userInfoWithKey.setResult("success");
                callback.invoke(userInfoWithKey);
            }

        }
    }
    //图片上传
    @JSMethod(uiThread = false)
    public void   uploadImages(String msg) {
        //给token给weex
        TYPE type = JSON.parseObject(msg, TYPE.class);
        Intent mintent = new Intent(MyActivityManager.getInstance().getCurrentActivity(), PhotoActivity.class);
        mintent.putExtra("type", type.getType());
        mintent.putExtra("tag", type.getCanEdit());
        MyActivityManager.getInstance().getCurrentActivity().startActivity(mintent);
    }

    //微信授权
    @JSMethod(uiThread = false)
    public void  wechatAuth() {
        SoftReference<JsMethod2> jsMethod=new SoftReference<>(new JsMethod2());
        if (jsMethod.get() != null) {
            jsMethod.get().authorization(SHARE_MEDIA.WEIXIN, MyActivityManager.getInstance().getCurrentActivity());
        } else {
            jsMethod = new SoftReference<JsMethod2>(new JsMethod2());
            jsMethod.get().authorization(SHARE_MEDIA.WEIXIN, MyActivityManager.getInstance().getCurrentActivity());
        }
    }
    //分享小程序
    @JSMethod(uiThread = false)
    public void   shareMiniPrograms(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            XCX xcx = JSON.parseObject(msg, XCX.class);
            if (xcx != null) {
                //小程序的分享
                ShareUtils.shareSmallPc(MyActivityManager.getInstance().getCurrentActivity(), Defaultcontent.url, xcx.getTitle(), xcx.getContent(), xcx.getImageUrl(), R.mipmap.logo_main, Defaultcontent.small_id, xcx.getOriginalPage(), SHARE_MEDIA.WEIXIN,null);
            }
        }
    }
    //分享
    @JSMethod(uiThread = false)
    public void  showSharePanel(String msg) {
        LogPrint.printError("分享点击事件" + msg);
        SHARE share = JSON.parseObject(msg, SHARE.class);
        Defaultcontent.title = share.getTitle();
        Defaultcontent.imageurl = share.getImageUrl();
        Defaultcontent.text = share.getContent();
        Defaultcontent.url = share.getShareUrl();
        Defaultcontent.code_url = share.getOriginalPage();
        SoftReference<DialogUtils> dialogUtils=new SoftReference<>(new DialogUtils());
        if (dialogUtils.get() != null) {
            dialogUtils.get().Share(MyActivityManager.getInstance().getCurrentActivity());
        } else {
            dialogUtils = new SoftReference<>(new DialogUtils());
            dialogUtils.get().Share(MyActivityManager.getInstance().getCurrentActivity());
        }
    }
    //微信支付callWechatPay
    @JSMethod(uiThread = false)
    public void  callWechatPay(String msg) {
        LogPrint.printError("微信支付状态回调（拼够和充值）" + msg);
        SoftReference<JsMethod2> jsMethod=new SoftReference<>(new JsMethod2());
        PAYGET payget = JSON.parseObject(msg, PAYGET.class);
        //保存type支付结束回调给H5
        PrefManager.getInstance().savePayType(payget.getType());
        LogPrint.printError("type：" + payget.getType() + ">>>>>>json" + payget.getPayInfo());
        if (!TextUtils.isEmpty(payget.getPayInfo())) {
            WECHATEPAY wechatepay = JSON.parseObject(payget.getPayInfo(), WECHATEPAY.class);
            if (jsMethod.get() != null) {
                jsMethod.get().wechatPay(wechatepay.getAppid(), wechatepay.getNoncestr(), wechatepay.getPackageX(), wechatepay.getPartnerid(), wechatepay.getPrepayid(), wechatepay.getSign(), wechatepay.getTimestamp());
            } else {
                jsMethod = new SoftReference<JsMethod2>(new JsMethod2());
                jsMethod.get().wechatPay(wechatepay.getAppid(), wechatepay.getNoncestr(), wechatepay.getPackageX(), wechatepay.getPartnerid(), wechatepay.getPrepayid(), wechatepay.getSign(), wechatepay.getTimestamp());
            }
        }
    }
    //支付宝支付    callAliPay
    @JSMethod(uiThread = false)
    public void  callAliPay(String msg) {
        LogPrint.printError("showtime" + msg);
        SoftReference<JsMethod2> jsMethod=new SoftReference<>(new JsMethod2());
        PAYGET payget = JSON.parseObject(msg, PAYGET.class);
        //保存type支付结束回调给H5
        PrefManager.getInstance().savePayType(payget.getType());
        LogPrint.printError("H5选择支付宝支付（拼够和充值）" + msg);
        if (!TextUtils.isEmpty(payget.getPayInfo())) {
            if (jsMethod.get() != null) {
                jsMethod.get().Alipay(MyActivityManager.getInstance().getCurrentActivity(), payget.getPayInfo());
            } else {
                jsMethod = new SoftReference<JsMethod2>(new JsMethod2());
                jsMethod.get().Alipay(MyActivityManager.getInstance().getCurrentActivity(), payget.getPayInfo());
            }
        }
    }

    //调用客服        callServiceChat
    @JSMethod(uiThread = false)
    public void  callServiceChat(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            QIYUOUT qiyuout = JSON.parseObject(msg, QIYUOUT.class);
            ZApplication.getInstance().options(qiyuout.getCrmParams().getAvatar());
            // appKey七鱼客服
            Unicorn.init(MyActivityManager.getInstance().getCurrentActivity(), ConfigAppkey.QIYU, ConfigFlag.ysfOptions, new GlideImageLoader(MyActivityManager.getInstance().getCurrentActivity()));
            setSource(qiyuout.getCrmParams().getName(), qiyuout.getCrmParams().getMobile(), "", qiyuout.getCrmParams().getGender(), "V" + SingleOverAll.getInstance().getVersionName(ZApplication.getInstance().getApplicationContext()) + "&&versionCode:" + SingleOverAll.getInstance().getVersionCode(ZApplication.getInstance().getApplicationContext()), qiyuout.getCrmParams().getEntrance(), "品牌：" + UtilsAll.getPhoneBrand() + "型号：" + UtilsAll.getPhoneModel(), qiyuout.getCrmParams().getOrderId(), "android操作系统");
            intoQiyu(MyActivityManager.getInstance().getCurrentActivity(), Long.parseLong(qiyuout.getCrmParams().getGroupId()));
        }
    }

    //复制        callServiceChat
    @JSMethod(uiThread = false)
    public void copyToClipBoard(String msg) {
        COPY copy = JSON.parseObject(msg, COPY.class);
        ClipboardManager cm = (ClipboardManager) ZApplication.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(copy.getCopyString());
        ToastUtilsAll.getInstance().showShortToast("复制成功");
    }
    //自定义统计
    @JSMethod(uiThread = false)
    public void  customStatistics(String msg) {
        LogPrint.printError("进来了come on" + msg);
        Umcustom umcustom = JSON.parseObject(msg, Umcustom.class);
        switch (umcustom.getType()) {
            case 0://友盟自定义事件
                if (!TextUtils.isEmpty(umcustom.getEventID()))
                    MobclickAgent.onEvent(ZApplication.getContext(), umcustom.getEventID());
                break;
            case 1:
                if (!TextUtils.isEmpty(umcustom.getEventID()) && !TextUtils.isEmpty(umcustom.getLabel()))
                    MobclickAgent.onEvent(ZApplication.getContext(), umcustom.getEventID(), umcustom.getLabel());
                break;
            case 2:
                if (!TextUtils.isEmpty(umcustom.getEventID()) && !TextUtils.isEmpty(umcustom.getMap())) {
                    Type typeToken = new TypeToken<HashMap<String, Object>>() {
                    }.getType();
                    HashMap<String, String> map = JSON.parseObject(umcustom.getMap(), typeToken);
                    HashMap<String, String>  hashmap = new HashMap<String, String>();
                    Set<String> get = map.keySet();
                    for (String key : get) {
                        hashmap.put(key, map.get(key) + "");
                    }
                    MobclickAgent.onEventValue(ZApplication.getContext(), umcustom.getEventID(), hashmap, 2147483645);
                }
                break;
        }
    }
    //弹出原生二维码页面
    @JSMethod(uiThread = false)
    public void showQRCodeDetails(String data) {
        LogPrint.printError("跳到原生商户二维码" + data);
        SHOPDETAILS shopdetails = JSON.parseObject(data, SHOPDETAILS.class);
        Intent mintent = new Intent(MyActivityManager.getInstance().getCurrentActivity(), CodeDetailsActivity.class);
        mintent.putExtra("shops", shopdetails);
        MyActivityManager.getInstance().getCurrentActivity().startActivity(mintent);
    }
    //调出原生扫一扫
    @JSMethod(uiThread = false)
    public void sweepSaoYiSao() {
        LogPrint.printError("扫一扫");
        if (TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            //跳登录
            MyActivityManager.getInstance().getCurrentActivity().startActivity(new Intent( MyActivityManager.getInstance().getCurrentActivity(), LoginActivity.class));
        } else {
            IntentIntegrator intentIntegrator = new IntentIntegrator( MyActivityManager.getInstance().getCurrentActivity());
            intentIntegrator
                    .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                    .setPrompt(ZApplication.CODE_TEXT)//写那句提示的话
                    .setOrientationLocked(false)//扫描方向固定
                    .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
                    .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                    .setCaptureActivity(ScanActicvity.class) // 设置自定义的activity是CustomActivity
                    .initiateScan(); // 初始化扫描
        }
    }
    //调用原生打电话功能
    @JSMethod(uiThread = false)
    public void makePhoneCall(String data) {
        LogPrint.printError("H5调用原生拨打电话makePhoneCall" + data);
        CALLTELEPHONE calltelephone = JSON.parseObject(data, CALLTELEPHONE.class);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + calltelephone.getTel()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyActivityManager.getInstance().getCurrentActivity().startActivity(intent);
    }
    //调用原生退出登录功能
    @JSMethod(uiThread = false)
    public void loginOut() {
        MyActivityManager.getInstance().getCurrentActivity().startActivity(new Intent( MyActivityManager.getInstance().getCurrentActivity(), SettingActivity.class));
        LogPrint.printError("设置");
    }
    //改变状态栏颜色
    @JSMethod(uiThread = false)
    public void changeStatusStyle(String msg) {
    }
    //系统声音
    @JSMethod(uiThread = false)
    public void callSystemVibrate() {
        LogPrint.printError("震动");
        Vibrator vibrator = (Vibrator)MyActivityManager.getInstance().getCurrentActivity().getSystemService(MyActivityManager.getInstance().getCurrentActivity().VIBRATOR_SERVICE);
        vibrator.vibrate(300);
    }
    //导航栏显示控制
    @JSMethod(uiThread = false)
    public void hideNavigationBar(String msg) {

    }
    //跳转事件
    @JSMethod(uiThread = false)
    public void pushWeex(String msg) {
        PushUrl pushUrl=JSON.parseObject(msg,PushUrl.class);
        UtilsAll.GoWeexAll(MyActivityManager.getInstance().getCurrentActivity(),pushUrl.getUrl(),pushUrl.getColor(),pushUrl.getStatusBar());
    }
    //返回上一层事件
    @JSMethod(uiThread = false)
    public void popWeex() {
        EventBus.getDefault().post(new EventBusMessageRefresh(886));
        MyActivityManager.getInstance().getCurrentActivity().finish();
    }
    //返回同一栈内指定位置事件
    @JSMethod(uiThread = false)
    public void popWeexIndex(String msg) {
        TagData tagData=JSON.parseObject(msg,TagData.class);
        int tag=tagData.getIndex();
        if(ZApplication.getInstance().mList.size()>0){
            for(int i=tag;i<ZApplication.getInstance().mList.size();i++){
                ZApplication.getInstance().mList.get(i).finish();
            }
        }
    }
    //去除栈内上一个页面
    @JSMethod(uiThread = false)
    public void pushWeexWithRemoveBeforeVc(String msg) {
        PushUrl pushUrl=JSON.parseObject(msg,PushUrl.class);
        UtilsAll.GoWeexAllDestoryThis(MyActivityManager.getInstance().getCurrentActivity(),pushUrl.getUrl(),pushUrl.getColor());
    }
    //跳转到搜索
    @JSMethod(uiThread = false)
    public void pushSearch() {
        MyActivityManager.getInstance().getCurrentActivity().startActivity(new Intent(MyActivityManager.getInstance().getCurrentActivity(), SearchOneActivity.class));
        MyActivityManager.getInstance().getCurrentActivity().overridePendingTransition(0, 0);
    }

    /**
     * 跳转到原生分类
     */
    @JSMethod(uiThread = false)
    public void pushGoodsCategoryVC(String msg) {
        LogPrint.printError("点击"+msg);
        FENLEI fenlei= JSON.parseObject(msg,FENLEI.class);
        Intent mintent =new Intent(MyActivityManager.getInstance().getCurrentActivity(),ShopActivity.class);
        mintent.putExtra("title_name",fenlei.getTitle());
        mintent.putExtra("categroy",fenlei.getCode());
        MyActivityManager.getInstance().getCurrentActivity().startActivity(mintent);
    }
    //返回上个页面加参数
    @JSMethod(uiThread = false)
    public void popWeexWithOptions(String msg) {
        LogPrint.printError("返回上一个页面加参数"+msg);
        EventBus.getDefault().post(new EventBusMessageWeex("returnWithOptions",msg));
        EventBus.getDefault().post(new EventBusMessageWebview("selectAddressNotification",msg));
        MyActivityManager.getInstance().getCurrentActivity().finish();
    }
    //跳到原生商品详情
    @JSMethod(uiThread = false)
    public void pushGoodsDetail(String msg) {
        LogPrint.printError("商品详情"+msg);
        ShopDetails shopDetails=JSON.parseObject(msg,ShopDetails.class);
        UtilsAll.GoNativeGoodsDetails(MyActivityManager.getInstance().getCurrentActivity(),shopDetails.getGoodsId(),shopDetails.getActivityId(),shopDetails.getInviteCode());
    }
    //跳转到H5界面
    @JSMethod(uiThread = false)
    public void pushWebDetail(String msg) {
    }
    /**
     * 跳转到客服页面
     *
     * @param context
     */
    private void intoQiyu(Context context, long groupId) {
        String title = "铺连铺客服";
        /**
         * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
         * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
         * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
         */
        String sourceUrl = "https://pulpu.qiyukf.com";
        String sourceTitle = "铺连铺";
        String custom = "custom information string";
        ConsultSource source = new ConsultSource(sourceUrl, sourceTitle, custom);
        source.groupId = groupId;
//        source.staffId=309480;
        /**
         * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
         * 如果返回为false，该接口不会有任何动作
         *
         * @param context 上下文
         * @param title   聊天窗口的标题
         * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
         */
        Unicorn.openServiceActivity(context, title, source);
    }

    /**
     * 设置来源
     */
    private void setSource(String real_name, String mobile_phone, String email, String gender, String app_version, String app_source, String device_info, String orderId, String device_sys) {
        String json = "[{\"key\":\"real_name\",\"value\":\"%s\"},{\"key\":\"mobile_phone\",\"value\":\"%s\"},{\"key\":\"email\",\"value\":\"%s\",\"hidden\":true},{\"index\":0,\"key\":\"gender\",\"label\":\"性别\",\"value\":\"%s\"},{\"index\":1,\"key\":\"app_version\",\"label\":\"版本\",\"value\":\"%s\"},{\"index\":2,\"key\":\"app_source\",\"label\":\"入口\",\"value\":\"%s\"},{\"index\":3,\"key\":\"device_info\",\"label\":\"机型\",\"value\":\"%s\"},{\"index\":3,\"key\":\"orderId\",\"label\":\"订单号\",\"value\":\"%s\"},{\"index\":4,\"key\":\"device_sys\",\"label\":\"系统\",\"value\":\"%s\"}]";
        String show = String.format(json, real_name, mobile_phone, email, gender, app_version, app_source, device_info, orderId, device_sys);
        YSFUserInfo userInfo = new YSFUserInfo();
        if (!TextUtils.isEmpty(PrefManager.getInstance().getInvite())) {
            userInfo.userId = PrefManager.getInstance().getInvite();
            LogPrint.printError("sb" + PrefManager.getInstance().getInvite());
        }
        userInfo.data = show;
        Unicorn.setUserInfo(userInfo);
    }
}
