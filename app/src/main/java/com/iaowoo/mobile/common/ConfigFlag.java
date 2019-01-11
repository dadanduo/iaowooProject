package com.iaowoo.mobile.common;

import com.qiyukf.unicorn.api.YSFOptions;

/**
 *
 */
public class ConfigFlag {

    /**
     * 日志是否打印
     */
    public static boolean IS_DEBUG = true;
    /**
     * webview 加载超时时间
     */
    public static final int TIMEOUT_WEB=30;
    /**
     * 轮播图时间间隙
     */
    public static  final  int TIME_DI=5000;
    /**
     * 控制webview的弹窗
     */
    public static  boolean ISSHWODILOGO=true;
    /**
     * 存储路径
     */
    public static final String SD_PATH = "/sdcard/DCIM/camera/";
    public static final String IN_PATH = "/DCIM/camera/";
    public static final String NO_SD_PATH = "/sdcard/PLP/IMGS/";
    public static final String NO_IN_PATH = "/PLP/IMGS/";
    /**
     * 捕获异常
     */
    public final static String ERROR_FILENAME = "plp_error.log";

    /**
     * 七鱼配置选项，设置后如果实在需要中途更换，可以通过此处修改
     */
    public static YSFOptions ysfOptions;

    /**
     * 业务客服组id
     */
    public static  String YEWUZU="264131306";
    /**
     * 售前客服组id
     */
    public static  String SHOUQIAN="264134130";
    /**
     * 售后客服组id
     */
    public static  String SHOUHOU="264119595";

}
