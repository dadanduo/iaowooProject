package com.iaowoo.mobile.Ui.classification.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.J_push.J_edit;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionListener;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionsUtil;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.UtilsTitleBar;
import com.iaowoo.mobile.Application.MyActivityManager;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.J_push.J_edit;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionListener;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionsUtil;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.UtilsTitleBar;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;

import cn.jpush.android.api.JPushInterface;

/**
 * BaseActivity
 * Created by chenda on 2018/4/5.
 */
public abstract class BaseActivity extends AppCompatActivity {
    //退出时的时间
    private long mExitTime;
    private boolean showHeigt = true;
    //是否需要处理消息
    private boolean msg_show = true;
    private msgSuccess msgSuccess;
    private RdioBroadCast rdioBroadCast;
    public GlideUtils glideUtils = null;
    public boolean SetTitleBar = false;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SetTitleBar) {
            UtilsTitleBar.setStatusBarDarkFont(this, true);
        } else {
            UtilsTitleBar.setStatusBarDarkFont(this, false);
        }
        rdioBroadCast = new RdioBroadCast();
        glideUtils = new GlideUtils();
        //不允许横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //初始化极光推送
        init();
        msgSuccess = new msgSuccess();
        rdioBroadCast.regiseterBroad(BaseActivity.this, msgSuccess, RdioBroadCast.MSGJIGUANG);
    }

    /**
     * 默认状态栏
     *
     * @param tileBar
     */
    public void setTileBar(boolean tileBar) {
        this.SetTitleBar = tileBar;
        UtilsTitleBar.setStatusBarDarkFont(this, false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogPrint.printError("baseActivityonStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogPrint.printError("baseActivity onResume");
        JPushInterface.onResume(this);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogPrint.printError("baseActivity onPause");
        JPushInterface.onPause(this);
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogPrint.printError("baseActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rdioBroadCast.unRegiseterBroad(BaseActivity.this, msgSuccess);
        rdioBroadCast = null;
        glideUtils = null;
        LogPrint.printError("注销极光推送广播");
        LogPrint.printError("baseActivityonDestroy");
    }

    /**
     * android6.0后qq分享权限适配
     */
    public void adaptive() {
        //qq,qq空间分享android6.0适配
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }


    public void requestPermission() {
        PermissionsUtil.requestPermission(getApplication(), new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION);
    }


    /**
     * 重写 getResource 方法，防止系统字体影响
     */
    @Override
    public Resources getResources() {//禁止app字体大小跟随系统字体大小调节
        Resources resources = super.getResources();
        if (resources != null && resources.getConfiguration().fontScale != 1.0f) {
            android.content.res.Configuration configuration = resources.getConfiguration();
            configuration.fontScale = 1.0f;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }

    /**
     * 再按一次退出系统
     */
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(BaseActivity.this, "再按一次退出" + this.getString(R.string.app_name), Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
            LogPrint.printError("退出时间：" + mExitTime);
        } else {
            LogPrint.printError("退出");
            onDestroy();
            finish();
            System.exit(0);
        }
    }

    /**
     * 动态设置透明状态栏
     */
    public void allState() {
        if (ZApplication.isShowHide)
            //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
    }

    /**
     * 动态的设置状态栏  实现沉浸式状态栏
     */
    public void initState(int layout) {
        if (ZApplication.isShowHide)
            //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                //
                LinearLayout linear_bar = findViewById(layout);
                linear_bar.setVisibility(View.VISIBLE);
                //获取到状态栏的高度
                int statusHeight = getStatusBarHeight();
                //动态的设置隐藏布局的高度
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linear_bar.getLayoutParams();
                if (showHeigt)
                    params.height = statusHeight;
                else
                    params.height = 0;
                linear_bar.setLayoutParams(params);
            }
    }

    /**
     * 通过反射的方式获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
     */
    private void init() {
        JPushInterface.init(getApplicationContext());
        //获取refgisterid
        ZApplication.REGISTERID = JPushInterface.getRegistrationID(this);
        LogPrint.printError("registeid:" + ZApplication.REGISTERID);
    }

    /**
     * 加载提示框
     *
     * @param context
     * @param msg
     * @return
     */
    public Dialog createLoadingDialog(Context context, String msg, boolean defalut) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v;
        if (defalut) {
            v = inflater.inflate(R.layout.loading_dilog_defalut, null);// 得到加载view
            TextView tipTextView = v.findViewById(R.id.tipTextView);// 提示文字
            tipTextView.setText(msg);// 设置加载信息
        } else {
            v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        }
        LinearLayout layout = v.findViewById(R.id.dialog_loading_view);// 加载布局
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
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
        return loadingDialog;
    }

    /**
     * 动态设置标题栏距离顶部高度
     *
     * @param viewMarginTop
     */
    public void setViewMarginTop(View viewMarginTop) {
        //获取状态栏的高度
        int height = SingleOverAll.getInstance().getStatusBarHeight1(ZApplication.getContext());
        //动态设置高度
        UtilsAll.setMargins(viewMarginTop, 0, height, 0, 0);
    }

    /**
     * 广播接受器
     */
    class msgSuccess extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(RdioBroadCast.DATA);
            LogPrint.printError("base" + msg);
            J_edit j_edit = new J_edit();
            j_edit.setCostomMsg1(msg, BaseActivity.this);
        }
    }

}
