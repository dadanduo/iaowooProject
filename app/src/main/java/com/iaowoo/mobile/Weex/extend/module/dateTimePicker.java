package com.iaowoo.mobile.Weex.extend.module;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.iaowoo.mobile.Application.MyActivityManager;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Application.MyActivityManager;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Weex.extend.Model.UserInfoWithToken;
import com.iaowoo.mobile.Weex.extend.Model.date;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenda  时间选择器
 */
public class dateTimePicker extends WXModule {
  //弹出时间选择器
  @JSMethod(uiThread = false)
  public void open(String msg,JSCallback callback) {
    date dateO= JSON.parseObject(msg,date.class);
    if(!UtilsAll.isDoubleClick()) {
      dataShow(dateO, callback);
    }
  }

  private void dataShow(final date dateo, final JSCallback callback){
    String title,cancal_txt,ok;
    boolean year=true,moth=true,day=true,hours=true,se=true,s=true;
    if(dateo.getValue().endsWith("yyyy-MM-dd HH:mm:ss")){
      year=true;
      moth=true;
      day=true;
      hours=true;
      se=true;
      s=true;
    }else if(dateo.getValue().endsWith("yyyy-MM-dd HH:mm")){
      year=true;
      moth=true;
      day=true;
      hours=true;
      se=true;
      s=false;
    }else if(dateo.getValue().endsWith("yyyy-MM-dd HH")){
      year=true;
      moth=true;
      day=true;
      hours=true;
      se=false;
      s=false;
    }else if(dateo.getValue().endsWith("yyyy-MM-dd")){
      year=true;
      moth=true;
      day=true;
      hours=false;
      se=false;
      s=false;
    }else if(dateo.getValue().endsWith("yyyy-MM")){
      year=true;
      moth=true;
      day=false;
      hours=false;
      se=false;
      s=false;
    }else if(dateo.getValue().endsWith("yyyy")){
      year=true;
      moth=false;
      day=false;
      hours=false;
      se=false;
      s=false;
    }else if(dateo.getValue().endsWith("HH:mm")){
      year=false;
      moth=false;
      day=false;
      hours=true;
      se=true;
      s=true;
    }
    Calendar selectedDate = Calendar.getInstance();
    Calendar startDate = Calendar.getInstance();
//    startDate.set(2013,1,1);
    Calendar endDate = Calendar.getInstance();
//    endDate.set(2020,1,1);

    //正确设置方式 原因：注意事项有说明
    startDate.set(1900,0,1);
    endDate.set(2099,11,31);

    if(TextUtils.isEmpty(dateo.getTitle())){
      title="选择时间";
    }else{
      title=dateo.getTitle();
    }
    if(TextUtils.isEmpty(dateo.getCancelTitlel())){
      cancal_txt="取消";
    }else{
      cancal_txt=dateo.getCancelTitlel();
    }
    if(TextUtils.isEmpty(dateo.getConfirmTitle())){
      ok="确定";
    }else{
      ok=dateo.getConfirmTitle();
    }
    TimePickerView pvTime = new TimePickerBuilder(MyActivityManager.getInstance().getCurrentActivity(), new OnTimeSelectListener() {
      @Override
      public void onTimeSelect(Date date,View v) {//选中事件回调
        UserInfoWithToken userInfoWithToken=new UserInfoWithToken();
        userInfoWithToken.setResult("success");
        userInfoWithToken.setData(getTime(date,dateo.getValue()));
        callback.invoke( userInfoWithToken);
      }
    })
            .setType(new boolean[]{year, moth, day, hours, se, s})// 默认全部显示
            .setCancelText(cancal_txt)//取消按钮文字
            .setSubmitText(ok)//确认按钮文字
            .setTitleSize(20)//标题文字大小
            .setTitleText(title)//标题文字
            .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
            .isCyclic(false)//是否循环滚动
            .setSubmitColor(0xFFD43228)//确定按钮文字颜色
            .setCancelColor(0xFF313131)//取消按钮文字颜色
            .setTitleBgColor(0xFFf5f5f5)//标题背景颜色 Night mode
            .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
            .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
            .setRangDate(startDate,endDate)//起始终止年月日设定
            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
            .isDialog(true)//是否显示为对话框样式
            .build();
    Dialog mDialog = pvTime.getDialog();
    if (mDialog != null) {

      FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.WRAP_CONTENT,
              Gravity.BOTTOM);

      params.leftMargin = 0;
      params.rightMargin = 0;
      pvTime.getDialogContainerLayout().setLayoutParams(params);

      Window dialogWindow = mDialog.getWindow();
      if (dialogWindow != null) {
        dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
        dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
      }
    }
    pvTime.show();

  }
  private String getTime(Date date,String style) {//可根据需要自行截取数据显示
    SimpleDateFormat format = new SimpleDateFormat(style);
    return format.format(date);
  }
}
