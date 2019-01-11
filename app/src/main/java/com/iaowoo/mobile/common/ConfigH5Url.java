package com.iaowoo.mobile.common;

import com.iaowoo.mobile.Utils.LogPrint;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

/**
 * @author Andy
 */
public class ConfigH5Url {

    /**
     * h5页面服务器域名
     */
    public static String HTTP_H5 = UtilsOkHttpAll.HTTP_H5;

    /*static {
        String[] URLS = {
                "http://192.168.1.114:3000",
                "http://192.168.1.126:8080",
                "https://test-m.iaowoo.com",
                "https://m.iaowoo.com",
                "https://stage-m.shbs008.com",
                "https://m2.shbs008.com",
                "https://dev-m2.shbs008.com",
                "https://test-files.shbs008.com",
                "https://test-sp.llzsj.cn",
                "https://test-m.llzsj.cn"};
        HTTP_H5 = URLS[UtilsOkHttpAll.H5STATUS];//配置h5页面服务器域名
    }*/

    /**
     * weex页面
     */
    public static String WEEX_HTTP = UtilsOkHttpAll.WEEX_HTTP;

    /*static {
        String[] URLS = {
                "https://test-files.shbs008.com/static/weex",
                "https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/front-page/weex",
                "http://192.168.1.254:8081/static/weex",//乐彪本地
                "http://192.168.1.126:8081/static/weex"//小红本地
        };
        WEEX_HTTP = URLS[UtilsOkHttpAll.WEEXSTATUS];//配置h5页面服务器域名
    }*/

    /**
     * 用户使用手册
     */
    public static final String URL_USERS = HTTP_H5 + "/#/handbook";
    /**
     * 用户协议
     */
    public static final String URL_USERS_AGREEMENT = HTTP_H5 + "/#/agreement";

    /*******************************路由配置***********************/
    /**
     * 宝矢
     */
    public static String BAOSHI_URL = HTTP_H5 + "/#/home";
    /**
     * 新人活动
     */
    public static String NEW_PEARSON_ACTIVITY = "";
    /**
     * 忘记密码
     */
    public static String FORGETPASSWORD = HTTP_H5 + "/#/modify/old_phone?from=App";

    /**
     * 初始化头像
     */
    public static String ImageUrl = "https://ihw-files.oss-cn-hangzhou.aliyuncs.com/app-icon/app/app_logo.png";


    /**
     * 个人资料
     */
    public static String information = "/my/information";

    /**
     * weex个人中心
     */
    public static String MsgMy() {
        return WEEX_HTTP + "/views/personal/information.js";
    }

    /**
     * 实名认证
     */
    public static String verified = "/my/verified";


    /**
     * 实名认证
     */
    public static String verifiedWeex() {
        return WEEX_HTTP + "/views/personal/verified+" + "&from=App";
    }

    /**
     * 签到领积分
     */
    public static String signin = "/my/signIn";

    /**
     * 拼购
     */
    public static String collage = "/collage";

    /**
     * 消息
     */
    public static String Meaasge_ = "/#/home/notice";

    /**
     * 订单
     */
    public static String order(int type) {
        return HTTP_H5 + "/#/order?type=" + type + "&from=App";
    }

    /**
     * 购物车
     *
     * @return
     */
    public static String shopCar() {
        return HTTP_H5 + "/#/shopCart?from=App";
    }

    /**
     * 各种消息
     *
     * @param type
     * @param title
     * @return
     */
    public static String MeassgeUrl(String type, String title) {
        return HTTP_H5 + Meaasge_ + "?type=" + type + "&title=" + title + "&from=App";
    }

    /**
     * 各种消息
     *
     * @param type
     * @param title
     * @return
     */
    public static String MeassgeUrlWeex(String type, String title) {
        return WEEX_HTTP + "/views/personal/notice.js" + "?type=" + type + "&from=App";
    }

    /**
     * 历史版本
     *
     * @return
     */
    public static String History() {
        return HTTP_H5 + "/#/news" + "?from=App";
    }


    /**
     * 分类
     *
     * @return
     */
    public static String Classinfo() {
        return HTTP_H5 + "/#/home/homeSort?from=App";
    }

    /**
     * 商家详情
     *
     * @param recommendId
     * @return
     */
    public static String merchant_r_d(String recommendId) {
        return HTTP_H5 + "/#/signout/recom_detail?templateId=" + recommendId + "&from=App";
    }

    /**
     * 商品详情
     *
     * @param type
     * @param templateId
     * @return
     */
    public static String setGoodsDetails(int type, String templateId) {
        String GoodsUrl = String.format("/#/home/product_details?type=%s&templateId=%s", type, templateId);
        return HTTP_H5 + GoodsUrl + "&from=App";
    }

    /**
     * banner详情
     *
     * @param bannerId
     * @return
     */
    public static String setbannerDetail(String bannerId) {
        String GoodsUrl = String.format("/#/activity/bannerDetail?bannerId=%s", bannerId);
        return HTTP_H5 + GoodsUrl + "&from=App";
    }


    /**
     * 获得路由地址
     *
     * @param url
     * @return
     */
    public static String getUrl(String url) {
        String Url = String.format("%s/#%s%s", HTTP_H5, url, "?from=App");
        LogPrint.printError("跳转路径：" + Url);
        return Url;
    }

    /**
     * 商品详情图文详情
     * /product_details.html
     * //生产https://sp.iaowoo.com/product_details.html  测试:https://test-sp.iaowoo.com/product_details.html
     */
    public static String GOODES_IMAGE_PAGE = "https://sp.iaowoo.com/product_details.html";
    /******************************************************/


    /*************************************************************Weex*******************************************************************************/
    /**
     * 订单页面
     * /
     */
    public static String ORDER_ER_INDEX = WEEX_HTTP + "/views/order/index.js";
    /**
     * 默认地址
     */
    public static String ADDRESS = WEEX_HTTP + "/views/address/selectAddress.js";
}
