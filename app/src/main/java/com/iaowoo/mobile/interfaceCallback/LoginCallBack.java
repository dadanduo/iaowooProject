package com.iaowoo.mobile.interfaceCallback;

/**
 * Created by chenda on 2018/4/11.
 */

public interface LoginCallBack {

    /**
     * 显示时间
     * @param time
     */
    void showTime(String time);

    /**
     *结束activity
     */
    void finish_activity();

    /**
     *展示loading
     */
    void  showD();

    /**
     *隐藏loading
     */
    void  hideD();

    /**
     *获取registerid
     */
    void getRegisteId();

    /**
     * 手机号保存在本地
     * @param mobile
     */
    void saveM(String mobile);

    /**
     * 第三方登录
     * @param status
     * @param h5_URL
     */
    void AppConfig(int status,String h5_URL);

    /**
     * 连接融云IM
     * @param loginToken
     */
    void connetRongIM(String loginToken);
}
