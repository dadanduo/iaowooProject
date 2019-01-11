package com.iaowoo.mobile.Weex.extend.compontent;

import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.SoftReference;

/**
 * Created by chenda   loading
 */

public class PPHudModule  extends WXModule {

  public final  static String ALERT_DEFAULT="alertDefault" ;
  public final  static String CLOSE_DIALOG="closeDialog" ;
  public final  static String ALERT_DEFINE="alertDefine" ;

  //弹出登录框
  @JSMethod(uiThread = false)
  public void showLoadingHud(String msg) {
    EventBus.getDefault().post(new EventBusMessageWeex(ALERT_DEFAULT,msg));
  }

  //弹出登录框
  @JSMethod(uiThread = false)
  public void hiddenLoading(){
    EventBus.getDefault().post(new EventBusMessageWeex(CLOSE_DIALOG,""));
  }

  //显示自定义loading
  @JSMethod(uiThread = false)
  public void showAutoMessage(String msg){
    EventBus.getDefault().post(new EventBusMessageWeex(ALERT_DEFINE,msg));
  }
}
