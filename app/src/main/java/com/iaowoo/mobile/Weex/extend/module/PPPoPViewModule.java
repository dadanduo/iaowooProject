package com.iaowoo.mobile.Weex.extend.module;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.MyActivityManager;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;
import com.iaowoo.mobile.Application.MyActivityManager;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.ImagePagerActivity;
import com.iaowoo.mobile.Ui.classification.Activity.SettingPayPasswordActivity;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Weex.extend.Model.ImageLooker;
import com.iaowoo.mobile.Weex.extend.Model.PasswordAlert;
import com.iaowoo.mobile.Weex.extend.Model.UserInfoWithToken;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chenda  密码框  图片浏览器
 */

public class PPPoPViewModule extends WXModule {
  //弹出密码框
  @JSMethod(uiThread = false)
  public void showPasswordPopView(String msg,JSCallback callback) {
    LogPrint.printError("调用了弹出密码框" +msg);
    PasswordAlert passwordAlert= JSON.parseObject(msg, PasswordAlert.class);
    SoftReference<DialogUtils> dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
    if (!TextUtils.isEmpty(PrefManager.getInstance().getPayPassword())) {
      //没有设置过密码
      if (PrefManager.getInstance().getPayPassword().endsWith("0")) {
        if (dialogUtilsSoftReference.get() != null) {
          dialogUtilsSoftReference.get().AlertDilog( MyActivityManager.getInstance().getCurrentActivity(), "操作提示！", "还没设置支付密码是否前去设置？", "确认", "取消", new alertCallBack() {
            @Override
            public void OnOk() {
              MyActivityManager.getInstance().getCurrentActivity().startActivity(new Intent(MyActivityManager.getInstance().getCurrentActivity(),SettingPayPasswordActivity.class));
            }
            @Override
            public void OnNo() {
              LogPrint.printError("取消");
            }
          });
        } else {
          dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
          dialogUtilsSoftReference.get().AlertDilog( MyActivityManager.getInstance().getCurrentActivity(), "操作提示！", "还没设置支付密码是否前去设置？", "确认", "取消", new alertCallBack() {
            @Override
            public void OnOk() {
              MyActivityManager.getInstance().getCurrentActivity().startActivity(new Intent(MyActivityManager.getInstance().getCurrentActivity(),SettingPayPasswordActivity.class));
            }
            @Override
            public void OnNo() {
              LogPrint.printError("取消");
            }
          });

        }
      } else {
        createPasswordPayDialog( MyActivityManager.getInstance().getCurrentActivity(),passwordAlert.getMobile(),passwordAlert.getTitle(),callback);
      }
    } else {
      if (dialogUtilsSoftReference.get() != null) {
        dialogUtilsSoftReference.get().AlertDilog( MyActivityManager.getInstance().getCurrentActivity(), "操作提示！", "还没设置支付密码是否前去设置？", "去设置", "暂不", new alertCallBack() {
          @Override
          public void OnOk() {
            MyActivityManager.getInstance().getCurrentActivity().startActivity(new Intent(MyActivityManager.getInstance().getCurrentActivity(),SettingPayPasswordActivity.class));
          }
          @Override
          public void OnNo() {
            LogPrint.printError("取消");
          }
        });
      } else {
        dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
        dialogUtilsSoftReference.get().AlertDilog( MyActivityManager.getInstance().getCurrentActivity(), "操作提示！", "还没设置支付密码是否前去设置？", "确认", "取消", new alertCallBack() {
          @Override
          public void OnOk() {
            MyActivityManager.getInstance().getCurrentActivity().startActivity(new Intent(MyActivityManager.getInstance().getCurrentActivity(),SettingPayPasswordActivity.class));
          }
          @Override
          public void OnNo() {
            LogPrint.printError("取消");
          }
        });
      }
    }

  }

  //图片浏览器
  @JSMethod(uiThread = false)
  public void showPhotoBrowserPopView(String msg){
    ImageLooker imageLooker=JSON.parseObject(msg,ImageLooker.class);
    List<String> images=new ArrayList<>();
    if(imageLooker.getImages()!=null){
      for(int i=0;i<imageLooker.getImages().length;i++){
        images.add(imageLooker.getImages()[i]);
      }
      startImagePagerActivity(MyActivityManager.getInstance().getCurrentActivity(),images,imageLooker.getCurrentIndex(),null);
    }
  }
  /**
   * 跳转到图片浏览器
   *
   * @param activity
   * @param imgUrls
   * @param position
   * @param imageSize
   */
  public void startImagePagerActivity(Activity activity, List<String> imgUrls, int position, ImagePagerActivity.ImageSize imageSize) {
    Intent intent = new Intent(activity, ImagePagerActivity.class);
    intent.putStringArrayListExtra(ImagePagerActivity.INTENT_IMGURLS, new ArrayList<String>(imgUrls));
    intent.putExtra(ImagePagerActivity.INTENT_POSITION, position);
    intent.putExtra(ImagePagerActivity.INTENT_IMAGESIZE, imageSize);
    activity.startActivity(intent);
    activity.overridePendingTransition(0, 0);
  }
  /**
   * 余额支付框
   */
  private  void   createPasswordPayDialog(final Context context, final String mobile,String titleMsg, final JSCallback jsCallback) {
    LayoutInflater inflater = LayoutInflater.from(context);
    View v = inflater.inflate(R.layout.password_layout, null);// 得到加载view
    RelativeLayout layout = v.findViewById(R.id.layout_ok);// 加载布局
    final EditText edite=v.findViewById(R.id.edite);
    RelativeLayout cancle=v.findViewById(R.id.cancle);
    TextView title=v.findViewById(R.id.title);
    RelativeLayout ok_queren=v.findViewById(R.id.ok_queren);
    title.setText(""+titleMsg);
    //延迟200毫秒展示系统键盘
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
                     public void run() {
                       InputMethodManager inputManager = (InputMethodManager)context. getSystemService(Context.INPUT_METHOD_SERVICE);
                       inputManager.showSoftInput( edite, 0);
                     }},
            200);
    final Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
    loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
    loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
    loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
    edite.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }
      @Override
      public void afterTextChanged(Editable s) {
      }
    });
    /**
     *将显示Dialog的方法封装在这里面
     */
    Window window = loadingDialog.getWindow();
    WindowManager.LayoutParams lp = window.getAttributes();
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    window.setGravity(Gravity.CENTER);
    window.setAttributes(lp);
    window.setWindowAnimations(R.style.PopWindowAnimStyle);
    loadingDialog.show();
    cancle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        loadingDialog.cancel();
      }
    });
    ok_queren.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        loadingDialog.cancel();
        LogPrint.printError(">>>>>>>加密前："+edite.getText().toString());
        String str1= UtilsAll.encryptionPassword(mobile,edite.getText().toString());
        String s=str1.replaceAll("\n", "");
        UserInfoWithToken userInfoWithKey= new UserInfoWithToken();
        userInfoWithKey.setData(s);
        userInfoWithKey.setResult("success");
        jsCallback.invoke(userInfoWithKey);
      }
    });
  }

}
