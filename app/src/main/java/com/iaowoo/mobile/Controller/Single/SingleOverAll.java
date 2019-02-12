package com.iaowoo.mobile.Controller.Single;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.DB.MessageNumber;
import com.iaowoo.mobile.Ui.classification.Activity.UseguideActivity;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
 * @Description: ${todo}(全局单列)
 * @date 2018/8/20
 * @email ${18011009889@163.com}
 */
public class SingleOverAll {
    public static  SingleOverAll singleOverAll=null;

    public SingleOverAll(){

    }
    /**
     * 单列模式
     *
     * @return 双重校验锁
     */
    public static SingleOverAll getInstance() {
        if (singleOverAll== null) {
            synchronized (SingleOverAll.class) {
                if (singleOverAll== null) {
                    singleOverAll = new SingleOverAll();
                }
            }
        }
        return singleOverAll;
    }

    /**
     * 优化内存
     * @param view
     */
    public void gcViews(View view) {
        if (view == null) {
            return;
        }
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
            if (view instanceof WebView) {
                ViewParent parent = view.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(view);
                }
                ((WebView) view).removeAllViews();
                ((WebView) view).destroy();
                return;
            }
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                gcViews(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }
    /**
     * dp 转化为 px
     * @param context
     * @param dpValue
     * @return
     */
    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        LogPrint.printError("屏幕的密度："+scale);

        return (int) (dpValue * scale);
    }

    /**
     * px 转化为 dp
     * @param context
     * @param pxValue
     * @return
     */
    public int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 获取设备宽度（px）
     * @param context
     * @return
     */
    public  int deviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    /**
     * 获取设备高度（px）
     * @param context
     * @return
     */
    public int deviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    public  int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result+88;
    }


    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    public  int getStatusBarHeight1(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    /**
     * 获取导航栏高度
     * @param context
     * @return
     */
    public  int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
    /**
     * 是否有网
     * @param context
     * @return
     */
    public  boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 判断是否是wifi连接
     */
    public  boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }
    /**
     * 打开网络设置界面
     */
    public  void openSetting(Activity activity) {
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 返回版本名字
     * 对应build.gradle中的versionName
     *
     * @param context
     * @return
     */
    public  String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 返回版本号
     * 对应build.gradle中的versionCode
     *
     * @param context
     * @return
     */
    public  String getVersionCode(Context context) {
        String versionCode = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = String.valueOf(packInfo.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }
    /**
     * 获取当前App进程的Name
     *
     * @param context
     * @param processId
     * @return
     */
    public  String getAppProcessName(Context context, int processId) {
        String processName = null;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // 获取所有运行App的进程集合
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = context.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == processId) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));

                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                int e1 = Log.e(UtilsAll.class.getName(), e.getMessage(), e);
            }
        }
        return processName;
    }

    /**
     * 创建App文件夹
     *
     * @param appName
     * @param application
     * @return
     */
    public  String createAPPFolder(String appName, Application application) {
        return createAPPFolder(appName, application, null);
    }

    /**
     * 创建App文件夹
     *
     * @param appName
     * @param application
     * @param folderName
     * @return
     */
    public  String createAPPFolder(String appName, Application application, String folderName) {
        File root = Environment.getExternalStorageDirectory();
        File folder;
        /**
         * 如果存在SD卡
         */
        if (UtilsAll.isSDCardAvailable() && root != null) {
            folder = new File(root, appName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        } else {
            /**
             * 不存在SD卡，就放到缓存文件夹内
             */
            root = application.getCacheDir();
            folder = new File(root, appName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        if (folderName != null) {
            folder = new File(folder, folderName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        return folder.getAbsolutePath();
    }

    /**
     * 通过Uri找到File
     *
     * @param context
     * @param uri
     * @return
     */
    public  File uri2File(Activity context, Uri uri) {
        File file;
        String[] project = {MediaStore.Images.Media.DATA};
        Cursor actualImageCursor = context.getContentResolver().query(uri, project, null, null, null);
        if (actualImageCursor != null) {
            int actual_image_column_index = actualImageCursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualImageCursor.moveToFirst();
            String img_path = actualImageCursor
                    .getString(actual_image_column_index);
            file = new File(img_path);
        } else {
            file = new File(uri.getPath());
        }
        if (actualImageCursor != null) actualImageCursor.close();
        return file;
    }
    /**
     * 获取AndroidManifest.xml里 的值
     *
     * @param context
     * @param name
     * @return
     */
    public  String getMetaData(Context context, String name) {
        String value = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
    /**
     * 获取mac地址
     * @param context
     * @return
     */
    public  String getLocalMacAddressFromWifiInfo(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo winfo = wifi.getConnectionInfo();
        String mac = winfo.getMacAddress();
        return mac;
    }

    /**
     * 网络异常提示
     * @param context
     */
    public  void abnormal(final Context context,LoadingCallBack loadingCallBack) {
        //判断是否有网络
        if (!isNetworkConnected(context)) {
            loadingCallBack.noHaveWifi();
            DialogUtils dialogUtils=new DialogUtils();
            dialogUtils.AlertDilog(context, "提示", "网络请求失败，请检查您的网络设置", "", "确认", new alertCallBack() {
                @Override
                public void OnOk() {
                }
                @Override
                public void OnNo() {
                    openSetting((Activity) context);
                }
            });
        }else{
            loadingCallBack.haveWifi();
        }
    }
    /**
     * base64转为bitmap
     * @param base64Data
     * @return
     */
    public  Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
    /**
     *base64编码字符集转化成图片文件。
     * @param base64Str
     * @param path 文件存储路径
     * @return 是否成功
     */
    public static boolean base64ToFile(String base64Str,String path){
        byte[] data = Base64.decode(base64Str,Base64.DEFAULT);
        for (int i = 0; i < data.length; i++) {
            if(data[i] < 0){
                //调整异常数据
                data[i] += 256;
            }
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(path);
            os.write(data);
            os.flush();
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * bitmap转为base64
     * @param bitmap
     * @return
     */
    public  String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * flie：要删除的文件夹的所在位置
     * @param file
     */
    public   void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
    }
    /**
     * 调用拨号界面
     * @param phone 电话号码
     */
    public   void call(String phone,Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    /**
     * 显示虚拟键盘
     * @param v
     */
    public void ShowKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);
    }

    /**
     * 获取当前的时间戳
     * @return
     */
    public   long  getSystemTime()
    {
        //获取当前时间
        long timeStamp = System.currentTimeMillis();
        return  timeStamp;
    }

    /**
     * 将数据保留一位小数
     */
    public  double getOneDecimal(double num) {
        DecimalFormat dFormat=new DecimalFormat("#.0");
        String yearString=dFormat.format(num);
        Double temp= Double.valueOf(yearString);
        return temp;
    }
    /**
     * 随机生成文件名
     *
     * @return
     */
    public  String generateFileName() {
        return "A"+ UUID.randomUUID().toString();
    }


    public  interface  LoadingCallBack{
        void haveWifi();
        void noHaveWifi();
    }

    /**
     * 获取到文件
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public  File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }


    /**
     * @param context
     * @param URL
     * @param title
     */
    public void GoBannerDetailsAndroidTitle(Context context,String URL,String title){
        Intent mintent=new Intent(context,UseguideActivity.class);
        mintent.putExtra("title",title);
        mintent.putExtra("url", URL);
        context.startActivity(mintent);
    }


    /**
     * @return
     */
    public Html.ImageGetter getImageGetterInstance() {
        Html.ImageGetter imgGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                int id = Integer.parseInt(source);
                Drawable d = ZApplication.getContext().getResources().getDrawable(id);
                int wdp = dip2px(ZApplication.getContext(), 27);
                int hdp = dip2px(ZApplication.getContext(), 15);
                d.setBounds(0, 0, wdp, hdp);
                return d;
            }
        };
        return imgGetter;
    }

    /**
     * @param context
     * @param dpValue
     * @return
     */
    public  int dip2px(Context context, float dpValue) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @param destr
     * @return
     */
    //"<img src='" + R.mipmap.mall_proprietary_icon + "'/>"+
    public  String descString(String destr) {
        //"<img src='" + R.mipmap.mall_proprietary_icon +"'/>  "+destr;
        return destr;
    }

    /**
     * @param destr
     * @return
     */
    // "<img src='" + R.mipmap.members_select_icon + "'/>"
    public  String descString1(String destr) {
        return  destr;
    }

    /**
     * @param destr
     * @return
     */
    //"<img src='" + R.mipmap.province_recommend_icon + "'/>"
    public  String descString2(String destr) {
        return   destr;
    }

    /**
     * 添加消息表数据
     */
    public void  addMessageTabble(){
        List<MessageNumber> messageNumbers=XutilsDBManage.getInstance().loadTableAll(MessageNumber.class);
        if(messageNumbers!=null){
            boolean haveok=false;
            if(messageNumbers.size()>0){
                for(int i=0;i<messageNumbers.size();i++){
                    String inv=PrefManager.getInstance().getInvite()==null?"":PrefManager.getInstance().getInvite();
                    if(messageNumbers.get(i).getUid().endsWith(inv)){
                        haveok=true;
                    }
                }
                if(!haveok){
                    MessageNumber messageNumber=new MessageNumber();
                    messageNumber.setUid(PrefManager.getInstance().getInvite()==null?"":PrefManager.getInstance().getInvite());
                    messageNumber.setK_f(0);
                    messageNumber.setG_g(0);
                    messageNumber.setX_t(0);
                    messageNumber.setJ_y(0);
                    messageNumber.setD_d(0);
                    XutilsDBManage.getInstance().saveTabble(messageNumber);
                }
            }
        }else{
            MessageNumber messageNumber=new MessageNumber();
            messageNumber.setUid(PrefManager.getInstance().getInvite()==null?"":PrefManager.getInstance().getInvite());
            messageNumber.setK_f(0);
            messageNumber.setG_g(0);
            messageNumber.setX_t(0);
            messageNumber.setJ_y(0);
            messageNumber.setD_d(0);
            XutilsDBManage.getInstance().saveTabble(messageNumber);
        }
    }

    /**
     * banner点击事件
     * @param banner
     */
    public void  bannerClick(Context mContext,Banner.BodyBean.ContentBean.ListBean banner){
        LogPrint.printError("type++++++"+banner.getType());
        //跳转H5头部
        if(banner.getType()==1){
            //跳H5商品详情
            if(banner.getBusinessType()==1){
                UtilsAll.GoWebviewAll(mContext,ConfigH5Url.setGoodsDetails(banner.getBusinessType(),banner.getBusinessId()));
            }else {
                /*if (!TextUtils.isEmpty(banner.getJumpUrl())) {
//                    if (banner.getJumpUrl().contains("?"))
//                        UtilsAll.GoWebviewAll(mContext, banner.getJumpUrl().trim() + "&t=" + banner.getTitle() + "&from=App"+"&bannerId="+banner.getBannerId()+"&");
//                    else
//                        UtilsAll.GoWebviewAll(mContext, banner.getJumpUrl().trim() + "?t=" + banner.getTitle() + "&from=App"+"&bannerId="+banner.getBannerId());
                }*/
                //跳转到原生页面
                UtilsAll.GoNativeGoodsDetails(mContext,banner.getBusinessId(),"",PrefManager.getInstance().getInvite());
            }
        }else if(banner.getType()==2){
            //安卓头部
            if (!TextUtils.isEmpty(banner.getJumpUrl())) {
                if (banner.getJumpUrl().contains("iaowoo.com")) {
                    UtilsAll.GoWebviewAll(mContext,ConfigH5Url.setGoodsDetails(banner.getBusinessType(),banner.getBusinessId()));
                }else {
                    GoBannerDetailsAndroidTitle(mContext, banner.getJumpUrl().trim() + "?t=" + banner.getTitle() + "&from=App" + "&bannerId=" + banner.getBannerId(), banner.getTitle() + "");
                }
            }
        }else{
            //weex
            if (!TextUtils.isEmpty(banner.getJumpUrl())) {
                UtilsAll.GoWeexAll(mContext, banner.getJumpUrl(), "", "");
            }

        }
    }
}
