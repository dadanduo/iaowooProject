package com.iaowoo.mobile.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.DB.LoginInfo;
import com.iaowoo.mobile.Ui.classification.Activity.GoodsDetailsActivity;
import com.iaowoo.mobile.Ui.classification.Activity.WebviewAcitvity;
import com.iaowoo.mobile.Ui.classification.Adapter.TopRecycleAdapter;
import com.iaowoo.mobile.Weex.WeexActicity;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 工具类集合
 * Created by chenda on 2018/1/23.
 */

public class UtilsAll {
    /**
     * SD卡判断
     *
     * @return
     */
    public static boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    public static int getBuildLevel() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getBuildVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取当前App进程的id
     *
     * @return
     */
    public static int getAppProcessId() {
        return android.os.Process.myPid();
    }

    /**
     * 验证手机号是否为正确手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^(1[3-9])\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * @param buffer
     * @return
     */
    public final static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * md5加密
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * plp app使用加密方式
     *
     * @param phone
     * @param password
     * @return
     */
    public static String encryptionPassword(String phone, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(phone.getBytes());// 产生数据的指纹
            System.out.println(md.toString());
            if (!TextUtils.isEmpty(password)) {
                md.update(password.getBytes());
                System.out.println(md.toString());
            }
            byte[] b = md.digest();
            System.out.println(md.toString());
            // Base64编码
            String d = Base64.encodeToString(b, Base64.DEFAULT);
            LogPrint.printError("加密了" + d);
            return d;// 制定一个编码
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * @param password
     * @return
     */
    public static String encryptionPasswordString(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(password.getBytes());
            LogPrint.printError(md.toString());
            // Base64编码
            String d = Base64.encodeToString(md5, Base64.DEFAULT);
            LogPrint.printError("加密成功数据为:" + d);
            return d;// 制定一个编码
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * base64编码字符集转化成图片文件。
     *
     * @param base64Str
     * @param path      文件存储路径
     * @return 是否成功
     */
    public static boolean base64ToFile(String base64Str, String path) {
        byte[] data = Base64.decode(base64Str, Base64.DEFAULT);
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 0) {
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
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * flie：要删除的文件夹的所在位置
     *
     * @param file
     */
    public static void deleteFile(File file) {
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
     * 对象是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmptyObject(Object str) {
        return (str == null || "".equals(str));
    }

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    public static void call(String phone, Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 显示虚拟键盘
     *
     * @param v
     */
    public static void ShowKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 获取当前的时间戳
     *
     * @return
     */
    public static long getSystemTime() {
        //获取当前时间
        long timeStamp = System.currentTimeMillis();
        return timeStamp;
    }

    /**
     * 将数据保留一位小数
     */
    public static double getOneDecimal(double num) {
        DecimalFormat dFormat = new DecimalFormat("#.0");
        String yearString = dFormat.format(num);
        Double temp = Double.valueOf(yearString);
        return temp;
    }

    /**
     * 随机生成文件名
     *
     * @return
     */
    private static String generateFileName() {
        return "A" + UUID.randomUUID().toString();
    }

    /**
     * 动态设置布局margin距离
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public static void fixInputMethodManagerLeak(Context context) {
        if (context == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj = null;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                }
                obj = f.get(imm);
                if (obj != null && obj instanceof View) {
                    View vGet = (View) obj;
                    if (vGet.getContext() == context) {
                        f.set(imm, null);
                    } else {
                        break;
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * wifi是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 数据流量是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static final boolean ping() {

        String result = null;
        try {
            String ip = "www.baidu.com";// ping 的地址，可以换成任何一种可靠的外网
            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次
            // 读取ping的内容，可以不加
            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuffer stringBuffer = new StringBuffer();
            String content = "";
            while ((content = in.readLine()) != null) {
                stringBuffer.append(content);
            }
            Log.d("------ping-----", "result content : " + stringBuffer.toString());
            // ping的状态
            int status = p.waitFor();
            if (status == 0) {
                result = "success";
                return true;
            } else {
                result = "failed";
            }
        } catch (IOException e) {
            result = "IOException";
        } catch (InterruptedException e) {
            result = "InterruptedException";
        } finally {
            Log.d("----result---", "result = " + result);
        }
        return false;

    }

    public static boolean isHaveNet(Context context) {
        if (isWifiConnected(context) || isMobileConnected(context)) {
            return true;
        }
        return false;
    }


    /***
     * 获取url 指定name的value;
     * @param url
     * @param name
     * @return
     */
    public static String getValueByName(String url, String name) {
        String result = "";
        int index = url.indexOf("?");
        String temp = url.substring(index + 1);
        String[] keyValue = temp.split("&");
        for (String str : keyValue) {
            if (str.contains(name)) {
                result = str.replace(name + "=", "");
                break;
            }
        }
        return result;
    }


    /**
     * 通过url获取键值对
     *
     * @param url
     * @return
     */
    public static HashMap<String, String> getUrlParameter(String url) {
        if (!TextUtils.isEmpty(url)) {
            HashMap<String, String> hashMap = new HashMap<>();
            if (url.contains("?")) {
                String url1 = url.substring(url.indexOf("?") + 1, url.length());
                if (url1.contains("&")) {
                    String[] url2 = url1.split("&");
                    for (int i = 0; i < url2.length; i++) {
                        String url3 = url2[i];
                        LogPrint.printError("url3" + url3);
                        String url4 = url3.substring(0, url3.indexOf("="));
                        LogPrint.printError("url4" + url4);
                        String url5 = url3.substring(url3.indexOf("=") + 1, url3.length());
                        LogPrint.printError("url5" + url5);
                        hashMap.put(url4, url5);
                    }
                } else {
                    String[] strs = url1.split("[=]");
                    hashMap.put(strs[0], strs[1]);
                }

            }

            return hashMap;
        }
        return null;

    }

    public static float dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }

    public static int dip2px(Context context,float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    private static long lastClickTime;
    private final static int SPACE_TIME = 1000;
    private final static int SPACE_TIME_ONE = 500;

    public static void initLastClickTime() {
        lastClickTime = 0;
    }

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime > SPACE_TIME) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        lastClickTime = currentTime;
        return isClick2;
    }

    public synchronized static boolean isDoubleClick1() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime > SPACE_TIME_ONE) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        lastClickTime = currentTime;
        return isClick2;
    }

    /**
     * 不带定位webview的跳转
     *
     * @param context
     * @param URL
     */
    public static void GoWebviewAll(Context context, String URL) {
        if (!isDoubleClick()) {
            LogPrint.printError("跳转：" + URL);
            Intent mintent = new Intent(context, WebviewAcitvity.class);
            mintent.putExtra("webview_url", URL);
            mintent.putExtra("tag", "0");
            context.startActivity(mintent);
        }
    }

    /**
     * 跳转到weex页面
     *
     * @param context
     * @param URL
     */
    public static void GoWeexAll(Context context, String URL, String color, String statusBar) {
        if (!isDoubleClick()) {
            LogPrint.printError("跳转：" + URL);
            Intent mintent = new Intent(context, WeexActicity.class);
            mintent.putExtra("weexUrl", URL);
            mintent.putExtra("titleColor", color);
            mintent.putExtra("statusBar", statusBar);
            context.startActivity(mintent);
        }
    }

    /**
     * 跳转到weex页面  销毁当前页面
     *
     * @param activity
     * @param URL
     */
    public static void GoWeexAllDestoryThis(Activity activity, String URL, String color) {
        if (!isDoubleClick()) {
            LogPrint.printError("跳转：" + URL);
            Intent mintent = new Intent(activity, WeexActicity.class);
            mintent.putExtra("weexUrl", URL);
            mintent.putExtra("titleColor", color);
            activity.startActivity(mintent);
            activity.finish();
        }
    }

    /**
     * 跳原生商品详情页面
     *
     * @param context
     * @param goodsId
     */
    public static void GoNativeGoodsDetails(Context context, String goodsId, String activityId, String invate) {
        Intent mintent = new Intent(context, GoodsDetailsActivity.class);
        mintent.putExtra("goodsId", goodsId);
        mintent.putExtra("activityId", "0");
        mintent.putExtra("tagNoReturn", "ok");
        context.startActivity(mintent);
    }

    /**
     * 带定位
     *
     * @param context
     * @param URL
     */
    public static void GoGoodDetailsAndLocation(Context context, String URL) {
        if (!isDoubleClick()) {
            LogPrint.printError("跳转：" + URL);
            Intent mintent = new Intent(context, WebviewAcitvity.class);
            mintent.putExtra("webview_url", URL);
            mintent.putExtra("tag", "1");
            context.startActivity(mintent);
        }
    }

    /**
     * 整数相除 保留一位小数
     *
     * @param a
     * @param b
     * @return
     */
    public static String division(int a, int b) {
        String result = "";
        float num = (float) a / b;
        DecimalFormat df = new DecimalFormat("0.0");
        result = df.format(num);
        return result;
    }

    public static String setMoney(float a) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        String dd = fnum.format(a);
        return dd;
    }

    /**
     * 添加修改数据
     *
     * @param isBD         是否为切换新账号入口
     * @param mobileNo
     * @param password
     * @param HeadImageUrl
     */
    public static void addLoginInfo(boolean isBD, String mobileNo, String password, String HeadImageUrl) {
        //表里没有数据
        if (XutilsDBManage.getInstance().loadTableAllSize(LoginInfo.class) == 0) {
            //组id设置为0
            PrefManager.getInstance().setGroudId(0);
        }
        //表里已经有数据
        LoginInfo loginInfoSearch = (LoginInfo) XutilsDBManage.getInstance().searchName(mobileNo, LoginInfo.class);
        if (loginInfoSearch != null) {
            //修改该账号为
            if (isBD) {
                LoginInfo d = new LoginInfo();
                d.setId(loginInfoSearch.getId());
                d.setGroudId(PrefManager.getInstance().getGroudId());
                d.setHeadImage(loginInfoSearch.getHeadImage());
                d.setPassWord(loginInfoSearch.getPassWord());
                d.setName(loginInfoSearch.getName());
                if (XutilsDBManage.getInstance().saveOrUpdate(d)) {
                    LogPrint.printError("修改成功");
                }
            } else {
                //直接拉过来
                int groudId = loginInfoSearch.getGroudId();
                PrefManager.getInstance().setGroudId(groudId);
            }
        } else {
            if (!isBD) {
                // 表里面没有数据新建分组
                //新建分组
                int groudIds = PrefManager.getInstance().getGroudId();
                LogPrint.printError("分组标识：" + groudIds);
                PrefManager.getInstance().setGroudId(groudIds + 1);
            }
            //新增数据
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setName(mobileNo);
            loginInfo.setPassWord(password);
            loginInfo.setHeadImage(HeadImageUrl);
            loginInfo.setGroudId(PrefManager.getInstance().getGroudId());
            if (XutilsDBManage.getInstance().saveOrUpdate(loginInfo)) {
                LogPrint.printError("新增数据成功");
            }
        }

    }

    /**
     * 删除账号数据表
     */
    public static boolean DeletLoginInfo(int id) {
        return XutilsDBManage.getInstance().deleteById(id, LoginInfo.class);
    }


    /**
     * 保留double类型小数后两位，不四舍五入，直接取小数后两位 比如：10.1269 返回：10.12
     *
     * @param doubleValue
     * @return
     */
    public static String calculateProfit(double doubleValue) {
        // 保留4位小数
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.0000");
        String result = df.format(doubleValue);

        // 截取第一位
        String index = result.substring(0, 1);

        if (".".equals(index)) {
            result = "0" + result;
        }

        // 获取小数 . 号第一次出现的位置
        int inde = firstIndexOf(result, ".");

        // 字符串截断
        return result.substring(0, inde + 3);
    }

    /**
     * 查找字符串pattern在str中第一次出现的位置
     *
     * @param str
     * @param pattern
     * @return
     */
    public static int firstIndexOf(String str, String pattern) {
        for (int i = 0; i < (str.length() - pattern.length()); i++) {
            int j = 0;
            while (j < pattern.length()) {
                if (str.charAt(i + j) != pattern.charAt(j))
                    break;
                j++;
            }
            if (j == pattern.length())
                return i;
        }
        return -1;
    }


    public static String DoubleTo_2(double a) {
        String c = String.format("%.2f", a);
//        DecimalFormat   df  = new DecimalFormat("#.00");
//        String str = df.format(a);
        return c;
    }

    /**
     * 处理显示金额
     *
     * @param a
     * @return
     */
    public static String Chuli1(double a) {
        //如果小数点后面的为零直接去掉不然保留
        if (a % 1 == 0) {
            int tmp = (int) a;
            return tmp + "";
        } else {
            return a + "";
        }
    }
}
