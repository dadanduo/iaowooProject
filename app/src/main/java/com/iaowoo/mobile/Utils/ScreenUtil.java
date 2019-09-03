package com.iaowoo.mobile.Utils;

import android.content.Context;
import android.view.WindowManager;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Application.ZApplication;


/**
 * Created by Administrator on 2017/4/25.
 */

public class ScreenUtil {
    public static int sysWidth()
    {
        WindowManager wm = (WindowManager) ZApplication.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width/2;
    }
    public static int sysHeight()
    {
        WindowManager wm = (WindowManager) ZApplication.getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height/2;
    }
}
