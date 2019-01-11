package com.iaowoo.mobile.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.Ui.classification.Model.UPDATE;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.plp.underlying.networkframwork.OkhttpManager;

/**
 * Created by chenda on 2017/3/16.
 */
public class Tools {

    /**
     * 检查是否存在SDCard
     *
     * @return
     */
    public  boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 2 * 获取版本号 3 * @return 当前应用的版本号 4
     */
    public  int getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            int versioncode = info.versionCode;
            return versioncode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    // 获取更新版本号
    public  void getVersionStart(final int vision,final Context context) {
        XutilsHttp.getInstance().getAndroidVersion(new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                if(!TextUtils.isEmpty(json)) {
                    UPDATE update = JSON.parseObject(json, UPDATE.class);
                    if (update.getCode().equals(OkhttpManager.SUCESS)) {
                        int versionCode = Integer.parseInt(update.getBody().getContent().getApp_android_version_incr_value());//更新新的版本号
                        String content = update.getBody().getContent().getApp_android_version_desc();
                        String url = update.getBody().getContent().getApp_android_download_url();//安装包下载地址
                        LogPrint.printError("安装包下载地址"+url);
                        String version_name=update.getBody().getContent().getApp_android_version();
                        if (versionCode != vision) {
                            if (vision < versionCode) {
                                // 版本号不同
                                UpdateManager updateManager = new UpdateManager(context);
                                updateManager.checkUpdateInfo(url, content,version_name);
                            }
                        }
                    }
                }
            }
            @Override
            public void OnFaild(String faildM) {
                ToastUtilsAll.getInstance().showShortToast("网络异常");
            }
        });
    }
}
