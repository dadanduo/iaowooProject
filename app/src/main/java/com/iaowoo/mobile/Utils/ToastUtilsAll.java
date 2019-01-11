package com.iaowoo.mobile.Utils;

import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.common.ConfigFlag;

/**
 * Created by chenda on 2018/4/2.
 */

public class ToastUtilsAll {
    private Context context;
    private static ToastUtilsAll toastUtilsAll = null;

    public static ToastUtilsAll getInstance() {
        if (toastUtilsAll == null) {
            synchronized (ToastUtilsAll.class) {
                if (toastUtilsAll == null) {
                    toastUtilsAll = new ToastUtilsAll();
                }
            }
        }
        return toastUtilsAll;
    }

    public ToastUtilsAll() {
        context = ZApplication.getContext();
    }

    //普通的弹框
    public void show(String content) {
        Toast.makeText(context, "" + content, Toast.LENGTH_LONG).show();
    }

    /**
     * @param resId
     */
    public void show(int resId) {
        Toast.makeText(context, ZApplication.getContext().getString(resId), Toast.LENGTH_SHORT).show();
    }

    /****************************防止重复弹窗*******************/
    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public void showShortToast(String msg) {
        if (toast == null) {
            /*这种写法如果传入Activity的实例进来，将有可能会导致Activvity泄露
             * 因为静态工具类的生存周期*/
//            toast =Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
            /*这样的话，不管传递什么content进来，都只会引用全局唯一的Content，不会产生内存泄露*/
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (msg.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = msg;
                toast.setText(msg);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    /**************************** end****************************/


    public void showNetEoor() {
      showShortToast("网络异常");
    }

    /**
     * @param text
     * @param duration
     */
    public void showGravity(String text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * @param resId
     */
    public void showGravity(int resId) {
        Toast toast = Toast.makeText(context, context.getString(resId),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * @param context
     * @param text
     */
    public void showGravity(Context context, String text) {
        if (context != null) {
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     * @param text
     * @param drawableId
     */
    @SuppressWarnings("deprecation")
    public void showImg(String text, int drawableId) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        ImageView imgView = new ImageView(context);
        imgView.setImageDrawable(context.getResources().getDrawable(drawableId));
        toast.setView(imgView);
        toast.show();
    }

    /**
     * @param view
     */
    public void showView(View view) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    /**
     * @param text
     */
    public void showThread(final String text) {
        new Thread(new Runnable() {
            public void run() {
                Looper.loop();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                Looper.prepare();
            }
        }).start();
    }

    /**
     * @param text
     */
    public void showError(String text) {
        if (ConfigFlag.IS_DEBUG) {
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}
