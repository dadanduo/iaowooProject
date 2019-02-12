package com.iaowoo.mobile.Ui.classification.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Model.IntegralRatio;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.Configtion;
import com.iaowoo.mobile.Ui.classification.Model.ISHAVERED;
import com.iaowoo.mobile.Ui.classification.Model.PaySuccessM;
import com.iaowoo.mobile.Ui.classification.Model.SHOP_CAR;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.util.HashMap;
import java.util.List;

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
 * @Description: ${todo}(首页)
 * @date 2018/8/27
 * @email ${18011009889@163.com}
 */
public class HomeFragmentPresenter {
    private Context context=null;
    private HomeCallBack homeCallBack;
    private HomeAdapterCallBack homeAdapterCallBack;
    private HomeMainCallBack homeMainCallBack;

    public HomeFragmentPresenter(Context context){
        this.context=context;
    }


    public interface  HomeMainCallBack{
        void getSlideShowHome(Banner.BodyBean.ContentBean.ListBean bean);
        void getStartAd(Banner.BodyBean.ContentBean.ListBean bean);
        void ShopCarNumber(int number);
        void noAd();

    }

    public interface  HomeCallBack{
        void Shop(List<Shop.BodyBean.ContentBean.ListBean> shops);
        void NohaveData();
        void IshaveRed(ISHAVERED ishavered);
    }
    public interface PayOkCallBack{
        void PaySucess(PaySuccessM.BodyBean.ContentBean contentBean);

    }

    /**
     * 头部分类数， 每日
     */
    public interface  HomeAdapterCallBack{
        void DayFire(List<Shop.BodyBean.ContentBean.ListBean> shops);
        void GoodsChina(List<Shop.BodyBean.ContentBean.ListBean> shops);
        void SpellRemmand(List<Shop.BodyBean.ContentBean.ListBean> shops);
        void EearthAll(List<Shop.BodyBean.ContentBean.ListBean> shops);
        void getSlideShow(Banner banner);
        void getConfigtion(Configtion configtion);
    }

    public void  setCallBack(HomeCallBack homeCallBack){
        this.homeCallBack=homeCallBack;
    }


    public void setAdapterCallBack(HomeAdapterCallBack homeAdapterCallBack){
        this.homeAdapterCallBack=homeAdapterCallBack;
    }

    public void setHomeMainCallBack(HomeMainCallBack homeMainCallBack){
        this.homeMainCallBack=homeMainCallBack;
    }


    /**
     * 为你推荐
     */
    public void getShopType(int pageNum,int pageSize){
        LogPrint.printError("当前页数:"+pageNum+"总共条数"+pageSize);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("goodsName","");
        paramsMap.put("parentCategory","");
        paramsMap.put("orderBy","sales");
        paramsMap.put("orderType","desc");
        paramsMap.put("queryType","hotSale");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.SHOP_SIZE,"HOME_FOR_YOU",pageNum,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Shop shop= JSON.parseObject(result.toString(),Shop.class);
                    if(shop.getCode().equals(OkhttpManager.SUCESS)){
                        if(shop.getBody().getContent().getList()!=null) {
                            if (shop.getBody().getContent().getList().size() != 0) {
                                LogPrint.printError("这里的总条数" + shop.getBody().getContent().getList().size());
                                homeCallBack.Shop(shop.getBody().getContent().getList());
                            } else {
                                homeCallBack.NohaveData();
                            }
                        }
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
//                homeCallBack.NohaveData();

            }
        });
    }


    /**
     * 每日最火
     */
    public void getDayFire(){
        HashMap<String, Object> paramsMap = new HashMap<>();
//        paramsMap.put("pageNum",1);
//        paramsMap.put("pageSize",20);
//        paramsMap.put("goodsName","");
//        paramsMap.put("parentCategory","");
//        paramsMap.put("orderBy","dailySales");
//        paramsMap.put("orderType","desc");
//        paramsMap.put("goodsType","");
//        paramsMap.put("queryType","");
        paramsMap.put("activityId","532128065178828800");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.SEARCH_PAGED_HUNDRED,"SEARCH_PAGED_HUNDRED",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("lalala"+result.toString());
                    Shop shop= JSON.parseObject(result.toString(),Shop.class);
                    if(shop.getCode().equals(OkhttpManager.SUCESS)){
                        if(shop.getBody().getContent().getList()!=null) {
                            if (shop.getBody().getContent().getList().size() != 0) {
                                homeAdapterCallBack.DayFire(shop.getBody().getContent().getList());
                            }
                        }
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }
            });
    }
    /**
     * 拼购推荐
     */
    public void getPGTJ(){
        HashMap<String, Object> paramsMap = new HashMap<>();
//        paramsMap.put("pageNum",1);
//        paramsMap.put("pageSize",20);
//        paramsMap.put("goodsName","");
//        paramsMap.put("parentCategory","");
//        paramsMap.put("orderBy","createTime");
//        paramsMap.put("orderType","desc");
//        paramsMap.put("goodsType","1");
//        paramsMap.put("queryType","");
        paramsMap.put("activityId","532128207533506560");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.SEARCH_PAGED_HUNDRED,"getPGTJ",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("哈哈哈"+result.toString());
                    Shop shop= JSON.parseObject(result.toString(),Shop.class);
                    if(shop.getCode().equals(OkhttpManager.SUCESS)){
                        if(shop.getBody().getContent().getList()!=null) {
                            if (shop.getBody().getContent().getList().size() != 0) {
                                homeAdapterCallBack.SpellRemmand(shop.getBody().getContent().getList());
                            }
                        }
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }
        });
    }
    /**
     *
     * 全球集市推荐
     */
    public void getQQJS(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",1);
        paramsMap.put("pageSize",20);
        paramsMap.put("goodsName","");
        paramsMap.put("parentCategory","");
        paramsMap.put("orderBy","createTime");
        paramsMap.put("goodsType","3");
        paramsMap.put("orderType","desc");
        paramsMap.put("queryType","");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.SHOP_SIZE,"getQQJS",-1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Shop shop= JSON.parseObject(result.toString(),Shop.class);
                    if(shop.getCode().equals(OkhttpManager.SUCESS)){
                        if(shop.getBody().getContent().getList()!=null) {
                            if (shop.getBody().getContent().getList().size() != 0) {
                                homeAdapterCallBack.EearthAll(shop.getBody().getContent().getList());
                            }
                        }
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }

        });
    }

    /**
     * 获得bannar所有的数据
     */
    public void getSlideShowData(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",1);
        paramsMap.put("pageSize",100);
        paramsMap.put("location","");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.BANNER,"getSlideShowData",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Banner banner= JSON.parseObject(result.toString(),Banner.class);
                    if (banner.getCode().equals(OkhttpManager.SUCESS)) {
                        if(banner.getBody().getContent().getList()!=null) {
                            if(banner.getBody().getContent().getList().size()>0){
                                homeAdapterCallBack.getSlideShow(banner);
                            }
                        }}
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }

        });
    }

    /**
     *
     * APP UI 配置接口文档
     */
    public void getConfiguration(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",1);
        paramsMap.put("pageSize",100);
        paramsMap.put("environment",3);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.CONFIGURATION,"getConfiguration", -1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Configtion configtion=JSON.parseObject(result.toString(),Configtion.class);
                    if(configtion.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(configtion!=null) {
                            homeAdapterCallBack.getConfigtion(configtion);
                        }
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }

        });
    }
    /**
     *查询商品pv与积分的换算比例
     */
    public void getQureryIntegralRatio(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.QUERY_INTEGRAL_RATIO,"QUERY_INTEGRAL_RATIO", -1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    IntegralRatio  integralRatio=JSON.parseObject(result.toString(),IntegralRatio.class);
                    if(integralRatio.getCode().endsWith(OkhttpManager.SUCESS)){
                        //保存积分换算比例到本地
                        PrefManager.getInstance().setIntegralRatio((float) integralRatio.getBody().getContent().getIntegralRatio());
                    }
                   LogPrint.printError(">>>>>>>>>>>>>>>>>>"+result.toString());
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }

        });
    }


//    /**
//     * 中国好物
//     */
//    public void getGoodsChina() {
//        HashMap<String, Object> paramsMap = new HashMap<>();
//        paramsMap.put("pageNum",1);
//        paramsMap.put("pageSize",20);
//        paramsMap.put("goodsName","");
//        paramsMap.put("parentCategory","");
//        paramsMap.put("orderBy","sales");
//        paramsMap.put("orderType","desc");
//        paramsMap.put("queryType","");
//        paramsMap.put("goodsType","5");
//        OkhttpManager.getInstance(ZApplication.getInstance().getApplicationContext()).requestPostByAsyn(true, UtilsOkHttpAll.SHOP_SIZE,"getGoodsChina", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
//            @Override
//            public void onReqSuccess(Object result) {
//                if (!TextUtils.isEmpty(result.toString())) {
//                    Shop shop = JSON.parseObject(result.toString(), Shop.class);
//                    if (shop.getCode().equals(OkhttpManager.SUCESS)) {
//                        if (shop.getBody().getContent().getList().size() != 0) {
//                            homeAdapterCallBack.GoodsChina(shop.getBody().getContent().getList());
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onReqFailed(String errorMsg) {
//                ToastUtilsAll.getInstance().showNetEoor();
//            }
//
//            @Override
//            public void Refresh() {
//                LogPrint.printError("有数据需要更新");
//            }
//        });
//
//    }

    /**
     * 是否还有新人红包没有领
     */
    public void IsGetHaveNewRed(String token){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",token);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.ISGETRED, "RED", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())){
                    LogPrint.printError("哈哈哈哈"+result.toString());
                    ISHAVERED ishavered=JSON.parseObject(result.toString(),ISHAVERED.class);
                    if(ishavered.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(ishavered.getBody().getContent()!=null) {
                            if (ishavered.getBody().getContent().size() > 0) {
                                homeCallBack.IshaveRed(ishavered);
                            }
                        }
                    }
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }

        });

    }

    /**
     * 获得首页activity bannar所有的数据
     */
    public void getSlideShowDataHome(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",1);
        paramsMap.put("pageSize",100);
        paramsMap.put("location","");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.BANNER,"getSlideShowData",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Banner banner= JSON.parseObject(result.toString(),Banner.class);
                    if (banner.getCode().equals(OkhttpManager.SUCESS)) {
                        if(banner.getBody().getContent().getList()!=null){
                            if(banner.getBody().getContent().getList().size()>0) {
                                for (int i = 0; i < banner.getBody().getContent().getList().size(); i++) {
                                    LogPrint.printError("location:" + banner.getBody().getContent().getList().get(i).getLocation());
                                    if (banner.getBody().getContent().getList().get(i).getLocation() == 9) {
                                        homeMainCallBack.getSlideShowHome(banner.getBody().getContent().getList().get(i));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }

        });
    }

    /**
     * 获得首页activity 广告页
     */
    public void getStartPage(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",1);
        paramsMap.put("pageSize",100);
        paramsMap.put("location","");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.BANNER,"getSlideShowData",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Banner banner= JSON.parseObject(result.toString(),Banner.class);
                    if (banner.getCode().equals(OkhttpManager.SUCESS)) {
                        if(banner.getBody().getContent().getList()!=null){
                            if(banner.getBody().getContent().getList().size()>0){
                                boolean ok=false;
                                for(int i=0;i<banner.getBody().getContent().getList().size();i++){
                                    LogPrint.printError("location:"+banner.getBody().getContent().getList().get(i).getLocation());
                                    if(banner.getBody().getContent().getList().get(i).getLocation()==12){
                                        homeMainCallBack.getStartAd(banner.getBody().getContent().getList().get(i));
                                        ok=true;
                                    }
                                }
                                if(!ok){
                                    homeMainCallBack.noAd();
                                }
                            }else{
                                homeMainCallBack.noAd();
                            }
                        }
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
                homeMainCallBack.noAd();
            }

        });
    }
    /**
     * 购物车数量
     */
    public void getShopCar(String loginToken){
        HashMap<String, Object> paramsMap = new HashMap<>();
        LogPrint.printError("token----------->"+loginToken);
        paramsMap.put("loginToken",loginToken);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.SHOP_CAR_NUMBER,"getSHOP_CAR_NUMBER",-1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("购物车数量"+result.toString());
                    SHOP_CAR shop_car=JSON.parseObject(result.toString(),SHOP_CAR.class);
                    if(shop_car.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(!TextUtils.isEmpty(shop_car.getBody().getContent()+"")) {
                            homeMainCallBack.ShopCarNumber(shop_car.getBody().getContent());
                        }
                    }else{
                        homeMainCallBack.ShopCarNumber(0);
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }

        });
    }

    /**
     * 中国好物为你推荐
     */
    public void getGoodsChina(int pageNum,int pageSize){
        LogPrint.printError("当前页数:"+pageNum+"总共条数"+pageSize);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("activityId","475252186549321728");
//        paramsMap.put("goodsName","");
//        paramsMap.put("parentCategory","");
//        paramsMap.put("orderBy","sales");
//        paramsMap.put("orderType","desc");
//        paramsMap.put("queryType","");
//        paramsMap.put("goodsType","5");

        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.CHINA_GOODS,"GoodeForYou",pageNum,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Shop shop= JSON.parseObject(result.toString(),Shop.class);
                    if(shop.getCode().equals(OkhttpManager.SUCESS)){
                        if(shop.getBody().getContent().getList()!=null) {
                            if (shop.getBody().getContent().getList().size() != 0) {
                                LogPrint.printError("这里的总条数" + shop.getBody().getContent().getList().size());
                                homeAdapterCallBack.GoodsChina(shop.getBody().getContent().getList());
                            } else {
//                            homeAdapterCallBack.NohaveData();
                            }
                        }
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
//                chinaGoodsMainCallBack.NohaveData();
            }

        });
    }
    /**
     *
     * 支付成功后查询订单
     */
    public void getPaySuccessOrder(String orderId, final PayOkCallBack payOkCallBack){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("orderId",orderId);
        paramsMap.put("loginToken", PrefManager.getInstance().getToken());
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.ORDER_PAY_SUCCESS,"ORDER_PAY_SUCCESS",-1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                  LogPrint.printError("啦啦啦啦"+result.toString());
                    PaySuccessM paySuccessM=JSON.parseObject(result.toString(),PaySuccessM.class);
                    if(paySuccessM.getCode().endsWith(OkhttpManager.SUCESS)){
                       payOkCallBack.PaySucess(paySuccessM.getBody().getContent());
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
            }

        });
    }
}
