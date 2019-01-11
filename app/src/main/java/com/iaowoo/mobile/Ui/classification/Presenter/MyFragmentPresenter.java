package com.iaowoo.mobile.Ui.classification.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Ui.classification.Model.YUE_JIFEN_CAIHONGBAO;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.Configtion;
import com.iaowoo.mobile.Ui.classification.Model.MYMESSAGE;
import com.iaowoo.mobile.Ui.classification.Model.OrderNumber;
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
public class MyFragmentPresenter {
    private Context context=null;
    private MyCallBack myCallBack;
    private MyAdapterCallBack myAdapterCallBack;

    public MyFragmentPresenter(Context context){
        this.context=context;
    }
    public void  setCallBack(MyCallBack myCallBack){
        this.myCallBack=myCallBack;
    }
    public interface  MyCallBack{
        void Shop(List<Shop.BodyBean.ContentBean.ListBean> shops);
        void NohaveData();
    }
    /**
     * 头部分类
     */
    public interface  MyAdapterCallBack{
        void getConfigtion(Configtion configtion);
        void Mymessage(MYMESSAGE mymessage);
        void OrderNumber(OrderNumber orderNumber);
        void getSlideShow(Banner banner);
        void query_data(YUE_JIFEN_CAIHONGBAO.BodyBean.ContentBean contentBean);

    }

    public void setMyCallBack(MyAdapterCallBack myAdapterCallBack){
        this.myAdapterCallBack=myAdapterCallBack;
    }

    /**
     * 热销推荐
     */
    public void getShopType(int pageNum,int pageSize){
        LogPrint.printError("当前页数:"+pageNum+"总共条数"+pageSize);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("goodsName","");
        paramsMap.put("parentCategory","");
        paramsMap.put("orderBy","");
        paramsMap.put("orderType","asc");
        paramsMap.put("queryType","1,3");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.SHOP_SIZE,"MY_FOR_YOU", pageNum,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Shop shop= JSON.parseObject(result.toString(),Shop.class);
                    if(shop.getCode().equals(OkhttpManager.SUCESS)){
                        if(shop.getBody().getContent().getList()!=null) {
                            if (shop.getBody().getContent().getList().size()!= 0) {
                                LogPrint.printError("这里的总条数" + shop.getBody().getContent().getList().size());
                                myCallBack.Shop(shop.getBody().getContent().getList());
                            } else {
                                myCallBack.NohaveData();
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
     * APP UI 配置接口文档
     */
    public void getConfiguration(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",1);
        paramsMap.put("pageSize",200);
        paramsMap.put("environment",3);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.CONFIGURATION,"APP_CONFIGTION", -1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Configtion configtion=JSON.parseObject(result.toString(),Configtion.class);
                    if(configtion.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(configtion!=null) {
                            myAdapterCallBack.getConfigtion(configtion);
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
     * 用户信息
     */
    public void getPERSON(String token){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",token);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.PERSON, "USERMESSAGE",-1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    MYMESSAGE mymessage=JSON.parseObject(result.toString(),MYMESSAGE.class);
                    if(mymessage.getCode().equals(OkhttpManager.SUCESS)){
                        if(mymessage!=null) {
                            myAdapterCallBack.Mymessage(mymessage);
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
     * 订单数量
     */
    public void getDisplayOrderNum(String token){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",token);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.DisplayOrderNum,"ORDERNUMBER", -1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("订单数量"+result.toString());
                    OrderNumber orderNumber=JSON.parseObject(result.toString(),OrderNumber.class);
                    if(orderNumber.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(orderNumber!=null) {
                            myAdapterCallBack.OrderNumber(orderNumber);
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
        paramsMap.put("pageSize",30);
        paramsMap.put("location","");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.BANNER,"getSlideShowData",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Banner banner= JSON.parseObject(result.toString(),Banner.class);
                    if (banner.getCode().equals(OkhttpManager.SUCESS)) {
                        if(banner.getBody().getContent().getList()!=null) {
                            if (banner.getBody().getContent().getList().size() > 0) {
                                myAdapterCallBack.getSlideShow(banner);
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
     * 搜索产品数据
     */
    public void getShopTypeSearch(String category,String queryType,String orderBy,String orderType,int pageNum,int pageSize,String goodsName){
        LogPrint.printError("当前页数:"+pageNum+"总共条数"+pageSize);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("goodsName",goodsName);
        paramsMap.put("parentCategory",category);
        paramsMap.put("orderBy",orderBy);
        paramsMap.put("orderType",orderBy);
        paramsMap.put("queryType",queryType);
        paramsMap.put("goodsType","1,3,5");

        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.SEARCH_SHOPS,"SEARCH_A",pageNum, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Shop shop = JSON.parseObject(result.toString(), Shop.class);
                    if (shop.getCode().equals(OkhttpManager.SUCESS)) {
                        if(shop.getBody().getContent().getList()!=null) {
                            if (shop.getBody().getContent().getList().size() != 0) {
                                LogPrint.printError("这里的总条数" + shop.getBody().getContent().getList().size());
                                myCallBack.Shop(shop.getBody().getContent().getList());
                            } else {
                                myCallBack.NohaveData();
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
     * 查询个人余额、积分和彩虹宝接口
     */
    public void getQuery_data(String token){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",token);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.QUERY_DADA,"QUERY_DADA",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                   LogPrint.printError("彩虹宝"+result.toString());
                    YUE_JIFEN_CAIHONGBAO yue_jifen_caihongbao=JSON.parseObject(result.toString(),YUE_JIFEN_CAIHONGBAO.class);
                    if(yue_jifen_caihongbao.getCode().endsWith(OkhttpManager.SUCESS)){
                        myAdapterCallBack.query_data(yue_jifen_caihongbao.getBody().getContent());
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
