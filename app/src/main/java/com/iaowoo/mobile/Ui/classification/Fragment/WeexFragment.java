package com.iaowoo.mobile.Ui.classification.Fragment;

import android.app.Dialog;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.H5toAndroid.modle.PICTURE;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Weex.extend.compontent.PPHudModule;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

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
 * @Description: ${todo}(weexFragment)
 * @date 2019/1/2
 * @email ${18011009889@163.com}
 */
public class WeexFragment  extends  BaseBufferFragment implements IWXRenderListener {
    WXSDKInstance mWXSDKInstance;
    private boolean oneGoInt=true;
    private SoftReference<DialogUtils> dialogUtilsSoftReference;
    private Dialog dialog;

    @BindView(R.id.main_layout)
    LinearLayout main_layout;
    @Override
    public int getLayoutResId() {
        return R.layout.weex_test_demo;
    }

    @Override
    protected void initView() {
        super.initView();
        mWXSDKInstance = new WXSDKInstance(getActivity());
        mWXSDKInstance.registerRenderListener(this);
        //注册evenbus
        EventBus.getDefault().register(this);
        this.setViewMarginTop(main_layout);
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
        loadingNetUrl("https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/front-page/weex/views/homeClassification/index.js");
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
                    dialog = dialogUtilsSoftReference.get().createLoadingDialog(getActivity(), "", false);
                } else {
                    dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                    dialog = dialogUtilsSoftReference.get().createLoadingDialog(getActivity(), "", false);
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


    //加载网络文件
    private void loadingNetUrl(String url) {
        mWXSDKInstance.renderByUrl("WXSample", url, null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        main_layout.addView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }
}
