package com.iaowoo.mobile.Weex;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.H5toAndroid.modle.PICTURE;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Weex.extend.compontent.PPHudModule;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.H5toAndroid.modle.PICTURE;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.BaseBufferActivity;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Weex.extend.compontent.PPHudModule;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

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
 * @Description: ${todo}(weex页面)
 * @date 2018/11/9
 * @email ${18011009889@163.com}
 */
public class WeexActicity extends BaseBufferActivity implements IWXRenderListener {
    WXSDKInstance mWXSDKInstance;
    private String WeexUrl;
    private Dialog dialog;
    private boolean oneGoInt=true;
    private SoftReference<DialogUtils> dialogUtilsSoftReference;
    private String statusBar;
    public int getLayoutResId() {
        return R.layout.weex_test_demo;
    }

    @Override
    protected void initView() {
        super.initView();
        ZApplication.getInstance().addActivity(this);
        //注册evenbus
        EventBus.getDefault().register(this);
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
        WeexUrl = getIntent().getStringExtra("weexUrl");
        statusBar=getIntent().getStringExtra("statusBar");
        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        //状态栏顶上去
        if(!TextUtils.isEmpty(statusBar)) {
            if (statusBar.endsWith("Hidden")) {
                this.allState();
            }
        }
        loadingNetUrl(WeexUrl);
        Map<String,Object> params = new HashMap<>();
        //告诉weex即将显示
        mWXSDKInstance.fireGlobalEventCallback("viewWillAppear",params);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    /**
     * EventBus消息处理方法。
     * @param eventBusMessageWeex
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageWeex eventBusMessageWeex) {
        //弹出默认loading框
        if(eventBusMessageWeex.getTagName().endsWith(PPHudModule.ALERT_DEFAULT)){
            LogPrint.printError("弹出默认loading框"+eventBusMessageWeex.getMsg());
            //初始化只会调用一次
            if(oneGoInt) {
                oneGoInt = false;
                if (dialogUtilsSoftReference.get() != null) {
                    dialog = dialogUtilsSoftReference.get().createLoadingDialog(this, "", false);
                } else {
                    dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                    dialog = dialogUtilsSoftReference.get().createLoadingDialog(this, "", false);
                }
            }
        }else if(eventBusMessageWeex.getTagName().endsWith(PPHudModule.CLOSE_DIALOG)){//关闭loading框
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                LogPrint.printError("关闭loading框"+eventBusMessageWeex.getMsg());
            }
        }else if(eventBusMessageWeex.getTagName().endsWith(PPHudModule.ALERT_DEFINE)){//弹出自定义的loading框
            LogPrint.printError("弹出自定义的loading框"+eventBusMessageWeex.getMsg());
        }else if(eventBusMessageWeex.getTagName().endsWith("wechatPayFaild")){
            Map<String,Object> params = new HashMap<>();
            params.put("payState","0");
            params.put("type", PrefManager.getInstance().getPayType());
            //微信支付失败后告诉weex
            mWXSDKInstance.fireGlobalEventCallback("wechatPayCallBack",params);
        } else if(eventBusMessageWeex.getTagName().endsWith("wechatPayOk")){
            Map<String,Object> params = new HashMap<>();
            params.put("payState","1");
            params.put("type", PrefManager.getInstance().getPayType());
            //微信支付成功后告诉weex
            mWXSDKInstance.fireGlobalEventCallback("wechatPayCallBack",params);
        }else if(eventBusMessageWeex.getTagName().endsWith("aliPayOk")){
            Map<String,Object> params = new HashMap<>();
            params.put("payState","1");
            params.put("type", PrefManager.getInstance().getPayType());
            //支付宝支付成功后告诉weex
            mWXSDKInstance.fireGlobalEventCallback("aliPayCallBack",params);
        }else if(eventBusMessageWeex.getTagName().endsWith("aliPayFaild")){
            Map<String,Object> params = new HashMap<>();
            params.put("payState","0");
            params.put("type", PrefManager.getInstance().getPayType());
            //支付宝支付成功后告诉weex
            mWXSDKInstance.fireGlobalEventCallback("aliPayCallBack",params);
        }else if(eventBusMessageWeex.getTagName().endsWith("loginOk")){
            //登录成功告诉weex
            Map<String,Object> params = new HashMap<>();
            mWXSDKInstance.fireGlobalEventCallback("userLoginSuccess",params);
        }else if(eventBusMessageWeex.getTagName().endsWith("logoutOk")){
            //登出成功告诉weex
            Map<String,Object> params = new HashMap<>();
            mWXSDKInstance.fireGlobalEventCallback("userLoginOutSuccess",params);
        }else if(eventBusMessageWeex.getTagName().endsWith("wechatComplate")){
            //微信同步成功后告诉weex
            Map<String,Object> params = new HashMap<>();
            LogPrint.printError("同步成功的信息爱啦啦啦"+eventBusMessageWeex.getMsg());
            params.put("",eventBusMessageWeex.getMsg());
            mWXSDKInstance.fireGlobalEventCallback("getWechatAuthState",params);
        }else if(eventBusMessageWeex.getTagName().endsWith("upImage")){
            //上传图片成功告诉weex
            PICTURE.ResponeBean picture= JSON.parseObject( eventBusMessageWeex.getMsg(),PICTURE.ResponeBean.class);
            Map<String,Object> params = new HashMap<>();
            params.put("imageUrl",picture.getImageUrl());
            params.put("prefix",picture.getPrefix());
            mWXSDKInstance.fireGlobalEventCallback("uploadImagesSuccess",params);
        }
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        LogPrint.printError("加载异常：" + errCode + "错误详情：" + msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Map<String,Object> params = new HashMap<>();
        //告诉weex即将显示
        mWXSDKInstance.fireGlobalEventCallback("viewWillAppear",params);
    }


    //加载网络文件
    private void loadingNetUrl(String url) {
        mWXSDKInstance.renderByUrl("WXSample", url, null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
    }

    //加载本地文件
    private void loadingAssets(String url) {
        mWXSDKInstance.render("WXSample", WXFileUtils.loadAsset(url, this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
        Map<String,Object> params = new HashMap<>();
        //告诉weex即将消失
        mWXSDKInstance.fireGlobalEventCallback("viewWillDisAppear",params);
        EventBus.getDefault().unregister(this);
    }
}
