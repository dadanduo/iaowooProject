package com.iaowoo.mobile.Ui.classification.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Ui.classification.Model.FenLei;
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
 * @Description: ${todo}(分类主fragmnet)
 * @date 2018/8/27
 * @email ${18011009889@163.com}
 */
public class EearthFragmentPresenter {
    private Context context=null;
    private EearthCallBack earthCallBack;
    private EearthCallBack1 earthCallBack1;

    public EearthFragmentPresenter(Context context){
        this.context=context;
    }

    public void  setCallBack(EearthCallBack earthCallBack){
        this.earthCallBack=earthCallBack;
    }

    public void  setCallBack1(EearthCallBack1 earthCallBack1){
        this.earthCallBack1=earthCallBack1;
    }
    public interface  EearthCallBack{
        void Fenlei(FenLei fenLei);
        void Shops(List<Shop.BodyBean.ContentBean.ListBean> shops);
        void NohaveData();
    }
    public interface  EearthCallBack1{
        void getSlideShow(Banner banner);
    }
    /**
     * 获得分类数据
     */
    public void getFenLei(){
        HashMap<String,Object> pare=new HashMap<>();
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.FEN_LEI_LIE_BIAO,"getFenLei_EEARTH",-1, pare, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())){
                    FenLei fenLei= JSON.parseObject(result.toString(),FenLei.class);
                    if(fenLei.getCode().equals(OkhttpManager.SUCESS)){
                        if(fenLei.getBody().getContent()!=null) {
                            if (fenLei.getBody().getContent().size() != 0) {
                                earthCallBack.Fenlei(fenLei);
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
     * 大类商品
     */
    public void getBigType(String category,String queryType,String orderBy,String orderType,int pageNum,int pageSize){
        LogPrint.printError("当前页数:"+pageNum+"总共条数"+pageSize+"category："+category);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("parentCategory",category);
        paramsMap.put("orderBy",orderBy);
        paramsMap.put("orderType",orderType);
        paramsMap.put("queryType",queryType);
        paramsMap.put("goodsType","");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.SHOP_SIZE,"getBigType", pageNum,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Shop shop= JSON.parseObject(result.toString(),Shop.class);
                    if(shop.getCode().equals(OkhttpManager.SUCESS)){
                        if(shop.getBody().getContent().getList()!=null) {
                            if (shop.getBody().getContent().getList().size() != 0) {
                                LogPrint.printError("数据" + shop.getBody().getContent().getList().size());
                                earthCallBack.Shops(shop.getBody().getContent().getList());
                            } else {
                                earthCallBack.NohaveData();
                            }
                        }else{
                            earthCallBack.NohaveData();
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
    public void getSlideShowData(String categoryCode){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",1);
        paramsMap.put("pageSize",100);
        paramsMap.put("location","");
        paramsMap.put("categoryCode",categoryCode);

        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.BANNER,"getSlideShowData",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Banner banner= JSON.parseObject(result.toString(),Banner.class);
                    if (banner.getCode().equals(OkhttpManager.SUCESS)) {
                        if(banner.getBody().getContent().getList()!=null) {
                            if(banner.getBody().getContent().getList().size()>0){
                               earthCallBack1.getSlideShow(banner);
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

}
