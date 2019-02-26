package com.plp.underlying.networkframwork;

/**
 * Created by chenda on 2018/1/23.
 */

public class UtilsOkHttpAll {
    // 环境配置
    public final static int ENV_TYPE = 2; // 环境配置参数 0:开发，1：测试，2：生产
    /**
     * 服务器路径接口
     */
    public static String HTTP_BASE;
    /**
     * H5页面url
     */
    public static String HTTP_H5;
    /**
     * weex页面
     */
    public static String WEEX_HTTP;

    /**
     * 商品详情图文详情
     */
    public static String GOODES_IMAGE_PAGE;

    static {
        switch (ENV_TYPE) {
            case 0:
//                HTTP_BASE = "http://192.168.1.112:8881";
//                HTTP_BASE = "http://172.16.4.8:9989";
                HTTP_BASE = "https://dev-api.iaowoo.com";
                HTTP_H5 = "https://dev-m.iaowoo.com";
                WEEX_HTTP = "https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/front-page/weex";
                GOODES_IMAGE_PAGE = "https://test-sp.iaowoo.com/product_details.html";
                break;

            case 1:
                HTTP_BASE = "https://test-api.iaowoo.com";
                HTTP_H5 = "https://test-m.iaowoo.com";
                WEEX_HTTP = "https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/front-page/weex";
                GOODES_IMAGE_PAGE = "https://test-sp.iaowoo.com/product_details.html";
                break;

            case 2:
                HTTP_BASE = "https://api.iaowoo.com";
                HTTP_H5 = "https://m.iaowoo.com";
                WEEX_HTTP = "https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/front-page/weex";
                GOODES_IMAGE_PAGE = "https://sp.iaowoo.com/product_details.html";
                break;
        }
    }
    /********************************************************接口配置区域*********************************************/

    /**
     * 注册接口
     */
    public static final String REGISTERTEST = HTTP_BASE + "/customer_center/register_by_valid_code";
    /**
     * 设置密码接口(暂时不需要)
     */
    public static final String SETPASSWORD = HTTP_BASE + "/setPassword";
    /**
     * 登出
     */
    public static final String LOGOUT = HTTP_BASE + "/customer_center/logout";
    /**
     * 获取注册短信验证码接口
     */
    public static final String GETSMS = HTTP_BASE + "/customer_center/send_register_sms_code";
    /**
     * 通过获取登录短信验证码接口登录
     */
    public static final String GETLOGINSMS = HTTP_BASE + "/customer_center/login_by_valid_code";
    /**
     * 发送设置支付密码密短信验证码
     */
    public static final String SEND_PAY_PASSWORD_SMS = "/customer_center/send_modify_password_sms_code";
    /**
     * 设置支付密码
     */
    public static final String SETTING_PAY_PASSWORD_SMS = "/customer_center/payment_password";
    /**
     * 通过密码登录
     */
    public static final String BYPASSWORDLOGIN = HTTP_BASE + "/customer_center/login_by_password";
    /**
     * 忘记密码
     */
    public static final String FORGETPASSWORD = HTTP_BASE + "/customer_center/modify_password_by_valid_code";
    /**
     * 发送登录验证码
     */
    public static final String SENDLOGINCODE = HTTP_BASE + "/customer_center/send_login_sms_code";
    /**
     * 发送忘记密码的验证码
     */
    public static final String FORGETPASSWORDBYSMS = HTTP_BASE + "/customer_center/send_modify_password_sms_code";
    /**
     * 获取图形验证码的接口
     */
    public static final String GETIMAGCODE = HTTP_BASE + "/getImageCode";
    /**
     * 将registerid推给后台
     */
    public static final String SENDREGISTERID = HTTP_BASE + "/customer_center/upload_registrationId";
    /**
     * 图片上传
     */
    public static final String UPFITE = HTTP_BASE + "/customer_center/upload_picture";
    /**
     * android自动更新
     */
    public static final String UPDATE_ANDROID = HTTP_BASE + "/customer_center/getConfig";
    /**
     * 判断控件是否显示
     */
    public static final String ISOR_SHOW_VIEW = HTTP_BASE + "/config_center/api_version";
    /**
     * 分类列表
     */
    public static final String FEN_LEI_LIE_BIAO = "/product_center/product/query_categoryInfos";
    /**
     * banner
     */
    public static final String BANNER = "/product_center/query_banners";
    /**
     * 附近门店
     */
    public static final String LO_LA_NEAR_SHOPS = "/order_center/merchant/query_merchant_details_info_by_distance";
    /**
     * 商品分页查询
     */
    public static final String SHOP_SIZE = "/product_center/es_template/searchPagedGoodsInfo";
    /**
     * 每日推荐  爱拼团
     */
    public static final String SEARCH_PAGED_HUNDRED = "/product_center/searchPagedHundredCityTemplateList";

    /**
     * 中国好物新接口
     */
    public static final String CHINA_GOODS = "/product_center/searchPagedHundredCityTemplateList";
    /**
     * 商品搜索
     */
    public static final String SEARCH_SHOPS = "/product_center/es_template/searchPagedGoodsInfoByProductName";
    /**
     * 根据距离查询门店信息
     */
    public static final String SEARCHSHOP = "/customer_center/query_merchant_details_info_distance";
    /**
     * 查询门店类目
     */
    public static final String SJLMFL = "/customer_center/query_category_list";
    /**
     * APP UI 配置接口
     */
    public static final String CONFIGURATION = "/config_center/appUiInfo/new/getPage";
    /**
     * 查询个人信息
     */
    public static final String PERSON = "/customer_center/query_identity_info";

    /**
     * 订单数量
     */
    public static final String DisplayOrderNum = "/order_center/displayOrderNum";


    /**
     * 打开红包的数额
     */
    public static final String redEnvelope = "/customer_center/receive_redEnvelope";
    /**
     * 获取是否领取过红包
     */
    public static final String ISGETRED = "/customer_center/eject_activity";
    /**
     * 获取购物车数量
     */
    public static final String SHOP_CAR_NUMBER = "/order_center/cart/query_cart_count";
    /**
     * (已经废弃)
     * 商品详情所有数据
     */
    public static final String SHOPS_DETAILS = "/product_center/es_template/getTemplateDetailInfoById";
    /**
     * 商品详情评价所有数据
     */
    public static final String SHOPS_COMMENTS_DETAILS = "/order_center/productJudges";
    /**
     * 查询订单支付成功信息接口
     */
    public static final String ORDER_PAY_SUCCESS = "/order_center/display_order_pay_success";
    /**
     * 待支付订单支付接口
     */
    public static final String DO_PAY = "/order_center/doPay";
    /**
     * 查询商品信息
     */
    public static final String SHOPS_GOODS_BYID = "/product_center/es_template/searchGoodsInfoById";
    /**
     * 根据templateId查询推广商品信息
     */
    public static final String SHOPS_DETAILS_TEMPLATE_ID = "/product_center/es_template/getPromotionTemplatesDetailInfoByTemplateId";
    /**
     * 非拼购商品(或购物车)下单
     */
    public static final String PLACE_ON_OREER = "/order_center/place_on_order";
    /**
     * 商品详情评价
     */
    public static final String EVALUATION = "/order_center/productJudges";

    /**
     * 获取默认地址
     */
    public static final String MORENDIZHI = "/customer_center/query_deliveries_info";
    /**
     * 获取活动数据
     */
    public static final String HUODONG = "/product_center/getAvailableActivity";
    /**
     * 查商品的真实价格
     */
    public static final String JISUANJINGE = "/order_center/query_goods_buy_info";
    /**
     * 获取红包
     */
    public static final String GETREDBAO = "/finance_center/query_redEnvelope_info";
    /**
     * 算一算
     */
    public static final String SUANSUAN = "/order_center/calc_order_amount";
    /**
     * 获取优惠券
     */
    public static final String YOUHUIQUAN = "/finance_center/coupon/coupon_info_list";
    /**
     * 加入购物车
     */
    public static final String ADD_SHOP_CAR = "/order_center/cart/add";
    /**
     * 查询配置信息
     */
    public static final String APPCONFIG = "/config_center/appConfig/getAppConfig";
    /**
     * 获取视频列表
     */
    public static final String SEARCH_VIDEO = "/customer_center/query_video_infos";
    /**
     * 点赞小视频
     */
    public static final String DOKI_VIDEO = "/customer_center/praise_video";
    /**
     * 评论小视频
     */
    public static final String COMMENTS_VIDEO = "/customer_center/comment_video";
    /**
     * 赞评论
     */
    public static final String DOKI_COMMENTS = "/customer_center/praise_comment";
    /**
     * 评论——评论
     */
    public static final String COMMENTS_TWO = "/customer_center/comment_comment";
    /**
     * 获取评论接口
     */
    public static final String GET_COMMENTS = "/customer_center/get_video_comment";
    /**
     * 取消评论
     */
    public static final String NO_COMMENTS = "/customer_center/cancel_comment";
    /**
     * .查询个人余额、积分和彩虹宝接口
     */
    public static final String QUERY_DADA = "/finance_center/query_data";
    /**
     * 获取消息数量
     */
    public static final String GET_MESSAGE_NUMBER = "/customer_center/get_message_number";
    /**
     * 清空消息数量
     */
    public static final String EMPTY_MESSAGE_NUMBER = "/customer_center/empty_message_number";
    /**
     * 热门推荐
     */
    public static final String GET_ACTIVITY_LIST_FORUSER = "/product_center/getTemplateActivityRelationListForUser";
    /**
     * 每日推荐   爱拼团
     */
    public static final String GET_SEEARCHPAGE_TEMPLATELIST = "/product_center/searchPagedHundredCityTemplateList";
    /**
     * 查询商品pv与积分的换算比例
     */
    public static final String QUERY_INTEGRAL_RATIO = "/order_center/profit/query_integral_ratio";

    // 融云IM相关接口api
    public static final String GET_IM_TOKEN = "/im_center/user/getImToken"; // loginToken
    public static final String IM_REFRESH_USER_INFO = "/im_center/user/refreshUserInfo"; // loginToken
    public static final String IM_GET_USER_INFO = "/im_center/user/getUserInfo"; // loginToken、userId、mobileNo

    public static final String IM_QUERY_FRIEND_LIST = "/im_center/user_friendship/friends"; // loginToken、
    public static final String IM_FRIEND_OPT = "/im_center/user_friendship/operation"; // loginToken、userId、mobileNo

    public static final String IM_CREATE_GROUP = "/im_center/user_group/create";
    public static final String IM_DISMISS_GROUP = "/im_center/user_group/dismiss";
    public static final String IM_TRANSFER_GROUP = "/im_center/user_group/transfer";
    public static final String IM_QUERY_GROUP_LIST = "/im_center/user_group/groups";
    public static final String IM_GROUP_OPT = "/im_center/user_group/memberOperation";
    public static final String IM_GROUP_UPDATE = "/im_center/user_group/modify";
    public static final String IM_GET_GROUP_INFO = "/im_center/user_group/groupInfo";

    public static final String IM_QUERY_GROUP_MEMBER_LIST = "/im_center/user_group/groupMembers";


    public static final String GENERATE_RED_ENVELOPE = "/im_center/red_envelope/generate_red_envelope"; // 生成红包
    public static final String OPEN_RED_ENVELOPE = "/im_center/red_envelope/open_red_envelope"; // 开红包

    /*******************************************************************************************************************/

    public final static String SUCESS = "00000000";

    /**
     * 错误码
     *
     * @param code
     * @return
     */
    public static String judgeCode(String code) {
        switch (code) {
            case "C001T001":
                return "参数异常";
            case "C001T002":
                return "未知操作类型";
            case "C001B001":
                return "用户已注册";
            case "C001B002":
                return "注册失败";
            case "C001B003":
                return "用户未注册";
            case "C001B004":
                return "短信验证码错误";
            case "C001B005":
                return "密码错误";
            case "C001B006":
                return "登录失败";
            case "C001B007":
                return "修改密码失败";
            case "C001B008":
                return "密码不一致";
            case "FFFFFFFF":
                return "系统异常，请稍后重试";
            default:
        }
        return "null";
    }
}
