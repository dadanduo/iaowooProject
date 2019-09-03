package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.View.EditText_Phone;
import com.iaowoo.mobile.Utils.DataCleanManager;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Model.Response;
import com.iaowoo.mobile.Utils.DataCleanManager;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.qiyukf.unicorn.api.Unicorn;
import com.taobao.weex.WXSDKInstance;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenda on 2018/4/19.
 * 设置页面
 */
public class SettingActivity extends TitleActivity {
    @BindView(R.id.logout_dd)
    Button logout;
    @BindView(R.id.cacheSize)
    TextView cacheSize;
    @BindView(R.id.version)
    TextView version;
    @BindView(R.id.switch_number)
    LinearLayout switch_number;
    @BindView(R.id.goweex_layout)
    LinearLayout goweex_layout;
    @BindView(R.id.weex_edite)
    EditText_Phone weex_edite;

    SoftReference<DialogUtils> dialogUtilsSoftReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_pager);
        setTitle("设置");
        showBackwardView_show(true);
        ButterKnife.bind(this);
        version.setText("V"+SingleOverAll.getInstance().getVersionName(this));
        try {
            cacheSize.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ZApplication.isReleaseTag){
            goweex_layout.setVisibility(View.GONE);
        }else{
            goweex_layout.setVisibility(View.VISIBLE);
        }
        if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
            logout.setVisibility(View.GONE);
            switch_number.setVisibility(View.GONE);
        } else {
            logout.setVisibility(View.VISIBLE);
            switch_number.setVisibility(View.VISIBLE);
        }
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogUtilsSoftReference.get()!=null){
                    dialogUtilsSoftReference.get().AlertDilog(SettingActivity.this, "温馨提示：", "确定要退出登录？", "确认", "取消", new alertCallBack() {
                        @Override
                        public void OnOk() {
                            if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                                XutilsHttp.getInstance().postEncodedLogout(new OkhttpCallBack() {
                                    @Override
                                    public void OnSuccess(String json) {
                                        Response response = new Response();
                                        response = ZApplication.gson.fromJson(json, Response.class);
                                        ToastUtilsAll.getInstance().showShortToast("登出成功！");
                                        //登出成功后告诉weex
                                        EventBus.getDefault().post(new EventBusMessageWeex("logoutOk",""));
                                        Unicorn.logout();
                                        //把token设置为空
                                        PrefManager.getInstance().setToken("");
                                        Intent intent = new Intent();
                                        intent.putExtra(RdioBroadCast.SHOWTAG,"logout");
                                        intent.putExtra(RdioBroadCast.DATA,"");
                                        intent.setAction(RdioBroadCast.BOARD);
                                        sendBroadcast(intent);
                                        //app改版后刷新小红点
                                        EventBus.getDefault().post(new EventBusMessageRefresh(0));
                                        finish();
                                    }
                                    @Override
                                    public void OnFaild(String faildM) {
                                    }
                                }, PrefManager.getInstance().getToken());
                            }
                        }
                        @Override
                        public void OnNo() {
                        }
                    });
                }else{
                    dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                    dialogUtilsSoftReference.get().AlertDilog(SettingActivity.this, "温馨提示：", "确定要退出登录？", "确认", "取消", new alertCallBack() {
                        @Override
                        public void OnOk() {
                            if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                                XutilsHttp.getInstance().postEncodedLogout(new OkhttpCallBack() {
                                    @Override
                                    public void OnSuccess(String json) {
                                        Response response = new Response();
                                        response = ZApplication.gson.fromJson(json, Response.class);
                                        ToastUtilsAll.getInstance().showShortToast("登出成功！");
                                        Unicorn.logout();
                                        //把token设置为空
                                        PrefManager.getInstance().setToken("");
                                        Intent intent = new Intent();
                                        intent.putExtra(RdioBroadCast.SHOWTAG,"logout");
                                        intent.putExtra(RdioBroadCast.DATA,"");
                                        intent.setAction(RdioBroadCast.BOARD);
                                        sendBroadcast(intent);
                                        //app改版后刷新小红点
                                        EventBus.getDefault().post(new EventBusMessageRefresh(0));
                                        finish();
                                    }
                                    @Override
                                    public void OnFaild(String faildM) {
                                    }
                                }, PrefManager.getInstance().getToken());
                            }
                        }
                        @Override
                        public void OnNo() {
                        }
                    });
                }
            }
        });
    }


    @OnClick({R.id.history,R.id.switch_number,R.id.go_weex})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.history:
                UtilsAll.GoWebviewAll(this,ConfigH5Url.History());
                break;
            //切换账号
            case  R.id.switch_number:
                startActivityForResult(SwitchNumberActivity.class,1);
                break;
            case R.id.go_weex:
                if(TextUtils.isEmpty(weex_edite.getText().toString())){
                    ToastUtilsAll.getInstance().show("请先输入你需要跳转的weex页面url");
                }else{
                    UtilsAll.GoWeexAll(this,weex_edite.getText().toString(),"","");
                }
                break;
            default:
                break;
        }
    }

    public void update_check(View view)
    {
    }
    /**
     * 分享
     * @param view
     */
    public void share(View view) {
        Defaultcontent.title=this.getResources().getString(R.string.app_name);
        Defaultcontent.text="我发现了一款年轻化、时尚化和生活化，具有高性价比商品的APP，赶快来下载试试吧！";
        Defaultcontent.imageurl="https://ihw-files.oss-cn-hangzhou.aliyuncs.com/app-icon/app/app_logo.png";
        Defaultcontent.url=ConfigH5Url.HTTP_H5+"/#/down";
        DialogUtils dialogUtils=new DialogUtils();
        dialogUtils.Share(SettingActivity.this);
    }
    /**
     * 关于我们
     * @param view
     */
    public void about_our(View view) {
        startActivity(AboutOurActivity.class);
    }
    /**
     * 跳转到使用手册页面
     * @param view
     */
    public void user_o(View view)
    {
        Intent mintent=new Intent(SettingActivity.this,UseguideActivity.class);
        mintent.putExtra("title","使用手册");
        mintent.putExtra("url", ConfigH5Url.URL_USERS);
        startActivity(mintent);
    }
    /**
     * 清除缓存
     * @param view
     */
    public void clearCache(View view){
        DialogUtils dialogUtils=new DialogUtils();
        dialogUtils.AlertDilog(SettingActivity.this, "温馨提示！！", "确定清除本地缓存？", "确认", "取消", new alertCallBack() {
            @Override
            public void OnOk() {
                DataCleanManager.clearAllCache(SettingActivity.this);
                try {
                    cacheSize.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void OnNo() {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==2){
                PrefManager.getInstance().setToken("");
                //app改版后刷新小红点
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
                finish();
                Intent intent = new Intent();
                intent.putExtra(RdioBroadCast.SHOWTAG,"logout");
                intent.putExtra(RdioBroadCast.DATA,"");
                intent.setAction(RdioBroadCast.BOARD);
                sendBroadcast(intent);
                //新建分组
                int groudId=PrefManager.getInstance().getGroudId();
                LogPrint.printError("分组标识："+groudId);
                PrefManager.getInstance().setGroudId(groudId+1);
            }
            if(resultCode==3){
                //app改版后刷新小红点
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
                finish();
            }
        }
    }
}
