package com.iaowoo.mobile.Ui.classification.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Utils.CaptureManager;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionListener;
import com.iaowoo.mobile.Utils.PressionUtils.PermissionsUtil;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.lang.ref.SoftReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 扫描页面
 */
public class ScanActicvity extends TitleActivity implements CaptureManager.ZxingCodeCallBack {
    @BindView(R.id.scan_re)
    DecoratedBarcodeView scan_re;
    private CaptureManager captureManager;
    private SoftReference<DialogUtils> dialogUtilsSoftReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan);
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
        requestPermission();
        ButterKnife.bind(this);
        setTitle("扫一扫");
        showBackwardView_show(true);
        //重要代码，初始化捕获
        captureManager = new CaptureManager(this, scan_re);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
        captureManager.setCallBack(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
        captureManager=null;
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return scan_re.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
    @Override
    public void getCode_msm(String msm) {
        //二维码推荐码
        if(msm.contains("micro.iaowoo.com")){
            if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                Intent mintent = new Intent(this, SingupActivity.class);
                String invet = msm.substring(msm.lastIndexOf("/") + 1);
                LogPrint.printError("邀请码为：" + invet);
                mintent.putExtra("invate", invet.replace(" ",""));
                startActivity(mintent);
            }else{
                String invet = msm.substring(msm.lastIndexOf("/") + 1);
                LogPrint.printError("邀请码为：" + invet);
                if(PrefManager.getInstance().getUserYaoQing().endsWith("1")){
                    ToastUtilsAll.getInstance().showShortToast("您已经有推荐人了");
                }else {
                    UtilsAll.GoWeexAll(this, ConfigH5Url.setYaoQinUrl(invet.replace(" ","")),"","");
                }
            }
            finish();
        }else{
            //二维码付款
            //没有登录
            if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                if(dialogUtilsSoftReference.get()!=null) {
                    dialogUtilsSoftReference.get().LoginTo1(this, new ClickCallBack() {
                        @Override
                        public void OnClick() {
                            finish();
                        }
                    });
                }else{
                    dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                    dialogUtilsSoftReference.get().LoginTo1(this, new ClickCallBack() {
                        @Override
                        public void OnClick() {
                            finish();
                        }
                    });
                }
            }else {//登录
                Intent mintent = new Intent(this, ScanActivityResult.class);
                mintent.putExtra("url", msm);
                startActivity(mintent);
                finish();
            }
        }
    }

    /**
     * 相机权限
     */
    public  void requestPermission() {
        PermissionsUtil.requestPermission(getApplication(), new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
            }
            @Override
            public void permissionDenied(@NonNull String[] permissions) {
            }
        }, Manifest.permission.CAMERA);
    }

}