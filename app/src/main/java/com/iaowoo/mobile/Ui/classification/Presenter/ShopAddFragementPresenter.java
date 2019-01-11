package com.iaowoo.mobile.Ui.classification.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.NearShops;
import com.iaowoo.mobile.Ui.classification.Model.SJLMFL;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.util.HashMap;

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
 * @Description: ${todo}(商家联盟)
 * @date 2018/9/3
 * @email ${18011009889@163.com}
 */
public class ShopAddFragementPresenter {
    private Context context=null;
    private ShopCallBack shopCallBack=null;
    public ShopAddFragementPresenter(Context context, ShopCallBack shopCallBack){
        this.context=context;
        this.shopCallBack=shopCallBack;
    }
    public interface  ShopCallBack{
        void  NearM(NearShops nearShops);
        void  NoShops();
        void BannerNearShop(Banner banner);
        void ClassShops(SJLMFL sjlmfl);
    }

    /**
     * 获得附近的商家
     * @param lo
     * @param la
     * @param pageNum
     * @param pageSize
     * @param distance
     * @param categoryCode
     */
    public void getNearShops(double lo,double la,int pageNum,int pageSize,int distance,String categoryCode){

        LogPrint.printError("经度:"+lo+"维度"+la+"pageNum"+pageNum+"pageSize"+pageSize+"distance"+distance+"code"+categoryCode);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("lon",lo);
        paramsMap.put("lat",la);
        paramsMap.put("distance",distance);
        paramsMap.put("categoryCode",categoryCode);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(false,UtilsOkHttpAll.LO_LA_NEAR_SHOPS,"SHOP_FOR_YOU",pageNum, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    if(!TextUtils.isEmpty(result.toString())){
                        NearShops nearShops= JSON.parseObject(result.toString(),NearShops.class);
                        if(nearShops.getCode().equals(OkhttpManager.SUCESS)) {
                            if (nearShops.getBody().getContent().getList() != null) {
                                if (nearShops.getBody().getContent().getList().size() == 0) {
                                    shopCallBack.NoShops();
                                } else {
                                    shopCallBack.NearM(nearShops);
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
     * 获得商家联盟的bannar所有的数据
     */
    public void getSlideShow(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",1);
        paramsMap.put("pageSize",5);
        paramsMap.put("location",13);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.BANNER,"SHOP_BANNERS",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Banner banner= JSON.parseObject(result.toString(),Banner.class);
                    if (banner.getCode().equals(OkhttpManager.SUCESS)) {
                        if(banner.getBody().getContent().getList()!=null) {
                            int size = banner.getBody().getContent().getList().size();
                            if (size != 0) {
                                shopCallBack.BannerNearShop(banner);
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
     * 查询门店类目
     */
    public void getSJFL(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.SJLMFL,"SHOP_NAME",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    SJLMFL sjlmfl=JSON.parseObject(result.toString(),SJLMFL.class);
                    if(sjlmfl.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(sjlmfl!=null) {
                            shopCallBack.ClassShops(sjlmfl);
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
}
