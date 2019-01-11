package com.iaowoo.mobile.Utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.iaowoo.mobile.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author chenda
 * @ClassName:ScreenAdaptationUtils
 * @Description: ${描述：}(屏幕适配工具类)
 * @date 2018/5/22
 */
public class ScreenAdaptationUtils {
    public ScreenAdaptationUtils(){}
    /**
     *机型
     */
    public final static  String[] Type={"PAAM00","PAAT00"," PACM00"," PACT00","CPH1831","CPH1833"};

    //全屏显示 暂时不能用
    public static final int FLAG_NOTCH_SUPPORT=0x00010000;
    /**
     * oppon机型是否有刘海
     * @param context
     * @return返回 true为凹形屏 ，可识别OPPO的手机是否为凹形
     */
    public  boolean IsHaveBangType(Context context)
    {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /**
     *华为start
     * 判断是否是华为刘海屏
     */
    public  boolean hasNotchInScreen(Context context)
    {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);

        } catch (ClassNotFoundException e)
        { LogPrint.printError("test"+ "hasNotchInScreen ClassNotFoundException"); }
        catch (NoSuchMethodException e)
        { LogPrint.printError("test"+ "hasNotchInScreen NoSuchMethodException"); }
        catch (Exception e)
        { LogPrint.printError("test"+ "hasNotchInScreen Exception"); }
        finally
        { return ret; }
    }
    //获取华为刘海的高宽
    public  int[] getNotchSize(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize"); ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("test", "getNotchSize ClassNotFoundException"); }
        catch (NoSuchMethodException e)
        { Log.e("test", "getNotchSize NoSuchMethodException"); }
        catch (Exception e) { Log.e("test", "getNotchSize Exception"); }
        finally { return ret; }
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public  void setFullScreenWindowLayoutInDisplayCutout(Window window) {
        if (window == null) { return; }
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        try
        {
            Class layoutParamsExCls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            Constructor con=layoutParamsExCls.getConstructor(WindowManager.LayoutParams.class);
            Object layoutParamsExObj=con.newInstance(layoutParams);
            Method method=layoutParamsExCls.getMethod("addHwFlags", int.class);
            method.invoke(layoutParamsExObj, FLAG_NOTCH_SUPPORT);
        }
        catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |InstantiationException | InvocationTargetException e)
        { Log.e("test", "hw notch screen flag api error"); }
        catch (Exception e) { Log.e("test", "other Exception"); }
    }

    /**
     *voio start
     */
    public static final int NOTCH_IN_SCREEN_VOIO=0x00000020;//是否有凹槽
    public static final int ROUNDED_IN_SCREEN_VOIO=0x00000008;//是否有圆角
    public boolean hasNotchInScreenAtVoio(Context context){
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class FtFeature = cl.loadClass("com.util.FtFeature");
            Method get = FtFeature.getMethod("isFeatureSupport",int.class);
            ret = (boolean) get.invoke(FtFeature,NOTCH_IN_SCREEN_VOIO);

        } catch (ClassNotFoundException e)
        { Log.e("test", "hasNotchInScreen ClassNotFoundException"); }
        catch (NoSuchMethodException e)
        { Log.e("test", "hasNotchInScreen NoSuchMethodException"); }
        catch (Exception e)
        { Log.e("test", "hasNotchInScreen Exception"); }
        finally
        { return ret; }
    }




    //适配引导页
    public  Bitmap scaleImage(final Activity activity, int drawableResId) {

        // 获取屏幕的高宽
        Point outSize = new Point();
        activity.getWindow().getWindowManager().getDefaultDisplay().getSize(outSize);

        // 解析将要被处理的图片
        Bitmap resourceBitmap = BitmapFactory.decodeResource(activity.getResources(), drawableResId);

        if (resourceBitmap == null) {
            return null;
        }

        // 开始对图片进行拉伸或者缩放

        // 使用图片的缩放比例计算将要放大的图片的高度
        int bitmapScaledHeight = Math.round(resourceBitmap.getHeight() * outSize.x * 1.0f / resourceBitmap.getWidth());

        // 以屏幕的宽度为基准，如果图片的宽度比屏幕宽，则等比缩小，如果窄，则放大
        final Bitmap scaledBitmap = Bitmap.createScaledBitmap(resourceBitmap, outSize.x, bitmapScaledHeight, false);

        return scaledBitmap;
    }


    public   boolean screenAdapter(int height)
    {
        switch (height)
        {
            //18：9特殊屏幕
            case  2160:
                return true;
            //19：9特殊屏幕
            case  2280:
                return true;
            //19：9特殊屏幕
            case  1520:
                return true;
            //19：9特殊屏幕
            case  1440:
                return true;
            default:
                return false;
        }
    }

    /**
     * 特殊屏幕处理
     * @param guide_image
     * @param height
     * @param tag
     */
    public void screenAdapterDealWith(ImageView guide_image, int height, int tag)
    {
        switch (height) {
            //18：9特殊屏幕
            case  2160:
                switch (tag) {
                    case 0:
                        guide_image.setImageResource(R.mipmap.guide_page_first_18_9);
                        break;
                    case 1:
                        guide_image.setImageResource(R.mipmap.guide_page_second_18_9);
                        break;
                    case 2:
                        guide_image.setImageResource(R.mipmap.guide_page_third_18_9);
                        break;
                    case 3:
//                        guide_image.setImageResource(R.mipmap.guide_page_fourth_18_9);
                        break;
                    case 4:
                        guide_image.setImageResource(R.mipmap.start_page_18_9);
                        //加载小视频没网显示背景图
                    case 5:
//                        guide_image.setImageResource(R.mipmap.waiting_loading_bg_18_9);
                        break;
                        default:
                            break;

                }
                break;
            //18：9特殊屏幕
            case  1440:
                switch (tag) {
                    case 0:
                        guide_image.setImageResource(R.mipmap.guide_page_first_18_9);
                        break;
                    case 1:
                        guide_image.setImageResource(R.mipmap.guide_page_second_18_9);
                        break;
                    case 2:
                        guide_image.setImageResource(R.mipmap.guide_page_third_18_9);
                        break;
                    case 3:
//                        guide_image.setImageResource(R.mipmap.guide_page_fourth_18_9);
                        break;
                    case 4:
                        guide_image.setImageResource(R.mipmap.start_page_18_9);
                        //加载小视频没网显示背景图
                    case 5:
//                        guide_image.setImageResource(R.mipmap.waiting_loading_bg_18_9);
                        break;
                        default:
                            break;

                }
                break;
            //19：9特殊屏幕
            case  2280:
                switch (tag) {
                    case 0:
                        guide_image.setImageResource(R.mipmap.guide_page_first_19_9);
                        break;
                    case 1:
                        guide_image.setImageResource(R.mipmap.guide_page_second_19_9);
                        break;
                    case 2:
                        guide_image.setImageResource(R.mipmap.guide_page_third_19_9);
                        break;
                    case 3:
//                        guide_image.setImageResource(R.mipmap.guide_page_fourth_19_9);
                        break;
                    case 4:
                        guide_image.setImageResource(R.mipmap.start_page_19_9);
                        //加载小视频没网显示背景图
                    case 5:
//                        guide_image.setImageResource(R.mipmap.waiting_loading_bg_19_9);
                        break;
                        default:
                            break;

                }
                break;
            //19：9特殊屏幕
            case  1520:
                switch (tag) {
                    case 0:
                        guide_image.setImageResource(R.mipmap.guide_page_first_19_9);
                        break;
                    case 1:
                        guide_image.setImageResource(R.mipmap.guide_page_second_19_9);
                        break;
                    case 2:
                        guide_image.setImageResource(R.mipmap.guide_page_third_19_9);
                        break;
                    case 3:
//                        guide_image.setImageResource(R.mipmap.guide_page_fourth_19_9);
                        break;
                    case 4:
                        guide_image.setImageResource(R.mipmap.start_page_19_9);
                        break;
                    //加载小视频没网显示背景图
                    case 5:
//                        guide_image.setImageResource(R.mipmap.waiting_loading_bg_19_9);
                        break;
                        default:
                            break;
                }
                break;
            default:
                break;
        }
    }
    public  boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @TargetApi(19)
    public  void setTranslucentStatus(boolean on,Activity activity) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 修改状态栏为全透明
     * @param activity
     */
    @TargetApi(19)
    public  void transparencyBar(Activity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        } else
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window =activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     * @param window 需要设置的窗口
     * @param dark 是否把状态栏字体及图标颜色设置为深色
     * @return  boolean 成功执行返回true
     *
     */
    public  boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field  field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if(dark){
                    extraFlagField.invoke(window,darkModeFlag,darkModeFlag);//状态栏透明且黑色字体
                }else{
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result=true;
            }catch (Exception e){

            }
        }
        return result;
    }

}
