package com.iaowoo.mobile.Ui.classification.Presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Model.ActivtiyModel;
import com.iaowoo.mobile.Ui.classification.Model.AddressM;
import com.iaowoo.mobile.Ui.classification.Model.JS;
import com.iaowoo.mobile.Ui.classification.Model.OrderOk;
import com.iaowoo.mobile.Ui.classification.Model.RedBaoM;
import com.iaowoo.mobile.Ui.classification.Model.RelayItem;
import com.iaowoo.mobile.Ui.classification.Model.RelayPriceResponse;
import com.iaowoo.mobile.Ui.classification.Model.YouHuiQuan;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
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
 * @Description: ${todo}()
 * @date 2018/11/28
 * @email ${18011009889@163.com}
 */
public class MyOrderProsenter {


    private AllCallBack allCallBack;
    public MyOrderProsenter(AllCallBack allCallBack){
        this.allCallBack=allCallBack;
    }

    public interface AllCallBack{
        /**
         * 地址
         * @param contentBeans
         */
        void Address(List<AddressM.BodyBean.ContentBean> contentBeans);

        /**
         * 红包余额
         *@param contentBean
         */
        void getRedBao(RedBaoM.BodyBean.ContentBean contentBean);

        /**
         * 真实价格
         * @param relayItem
         */
        void mount( RelayItem relayItem);

        /**
         * 优惠券
         * @param listBeans
         */
        void getYouHuiQuan(List<YouHuiQuan.BodyBean.ContentBean.ListBean> listBeans);

        /**
         * 计算
         * @param list
         * @param payCount
         */
        void jisun(List<JS.BodyBean.ContentBean.CouponRedParamBean> list,double payCount );

        /**
         * 活动
         * @param bodyBean
         */
        void activity( ActivtiyModel.BodyBean bodyBean);
        /**
         * 下单
         * @param orderBean
         */
        void Order(OrderOk.BodyBean.ContentBean orderBean);
    }

    /**
     * 获取活动名称
     */
    public void getActivity(String templateId){
        ++ZApplication.ALL_TAG_I;
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("templateId",templateId);
        paramsMap.put("loginToken", PrefManager.getInstance().getToken());
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.HUODONG,"HUODONG",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                --ZApplication.ALL_TAG_I;
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("活动数据"+result.toString());
                    ActivtiyModel activtiyModel=JSON.parseObject(result.toString(), ActivtiyModel.class);
                    if(activtiyModel.getCode().endsWith(OkhttpManager.SUCESS)){
                        allCallBack.activity(activtiyModel.getBody());
                    }else{
                        ToastUtilsAll.getInstance().showShortToast(activtiyModel.getDescribe());
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                --ZApplication.ALL_TAG_I;
                ToastUtilsAll.getInstance().showNetEoor();
            }
        });
    }
    /**
     * 获取我的默认地址接口
     */
    public void getMorenAddress(){
        ++ZApplication.ALL_TAG_I;
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken", PrefManager.getInstance().getToken());
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.MORENDIZHI,"MORENDIZHI",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                --ZApplication.ALL_TAG_I;
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("默认地址"+result.toString());
                    AddressM addressM=JSON.parseObject(result.toString(),AddressM.class);
                    if(addressM.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(addressM.getBody().getContent()!=null){
                            if(addressM.getBody().getContent().size()>0){
                                allCallBack.Address(addressM.getBody().getContent());
                            }
                        }
                    }else{
                        ToastUtilsAll.getInstance().showShortToast(addressM.getDescribe());
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                --ZApplication.ALL_TAG_I;
                ToastUtilsAll.getInstance().showNetEoor();
            }
        });
    }
    /**
     * 获取优惠券
     */
    public void getYouHui(){
        ++ZApplication.ALL_TAG_I;
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum", 1);
        paramsMap.put("pageSize", 100);
        paramsMap.put("model", 0);
        paramsMap.put("loginToken", PrefManager.getInstance().getToken());
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.YOUHUIQUAN,"YOUHUIQUAN",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                --ZApplication.ALL_TAG_I;
                if(!TextUtils.isEmpty(result.toString())) {
                    YouHuiQuan youHuiQuan=JSON.parseObject(result.toString(),YouHuiQuan.class);
                    if(youHuiQuan.getCode().endsWith(OkhttpManager.SUCESS)){
                        LogPrint.printError("优惠券"+result.toString());
                        allCallBack.getYouHuiQuan(youHuiQuan.getBody().getContent().getList());
                    }else{
                        ToastUtilsAll.getInstance().showShortToast(youHuiQuan.getDescribe());
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                --ZApplication.ALL_TAG_I;
                ToastUtilsAll.getInstance().showNetEoor();
            }
        });
    }

    /**
     * 计算价格
     */
    public void getJisuan(String products,String couponRedParam){
        ++ZApplication.ALL_TAG_I;
        LogPrint.printError("one:"+products+"two:"+couponRedParam);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("products",products);
        paramsMap.put("couponRedParam",couponRedParam);
        paramsMap.put("loginToken", PrefManager.getInstance().getToken());
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.SUANSUAN,"SUANSUAN",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                --ZApplication.ALL_TAG_I;
                if(!TextUtils.isEmpty(result.toString())) {
                    JS js=JSON.parseObject(result.toString(),JS.class);
                    if(js.getCode().endsWith(OkhttpManager.SUCESS)){
                        allCallBack.jisun(js.getBody().getContent().getCouponRedParam(),js.getBody().getContent().getTotalPayAmount());
                    }else{
                        ToastUtilsAll.getInstance().showShortToast(js.getDescribe());
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                --ZApplication.ALL_TAG_I;
                ToastUtilsAll.getInstance().showNetEoor();
            }
        });
    }
    /**
     * 查商品的真实价格
     */
    public void getJisuanMoney(String products, final int  activityid){
        LogPrint.printError("传递的数据"+products);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("products",products);
        paramsMap.put("loginToken", PrefManager.getInstance().getToken());
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.JISUANJINGE,"JISUANJINGE",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    RelayPriceResponse relayPriceResponse=JSON.parseObject(result.toString(),RelayPriceResponse.class);
                    if(relayPriceResponse.getCode().endsWith(OkhttpManager.SUCESS)){
                        LogPrint.printError(">>>>>>>>>"+relayPriceResponse.getBody().getContent());
                        HashMap<String,String> hashMap=new HashMap<>();
                        if (!TextUtils.isEmpty(relayPriceResponse.getBody().getContent())) {
                            try {
                                JSONObject json = new JSONObject(relayPriceResponse.getBody().getContent());
                                Iterator<String> it = json.keys();//使用迭代器
                                while (it.hasNext()) {
                                    String key = it.next();//获取key
                                    String values = json.getString(key);
                                    hashMap.put(key,values);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for(String key : hashMap.keySet()){
                            if(key.endsWith(activityid+"")){
                                String newJson= "{\"json\""+":"+hashMap.get(key)+"}";
                                LogPrint.printError("啦啦啦"+newJson);
                                RelayItem relayItem=JSON.parseObject(newJson,RelayItem.class);
                                ZApplication.ALL_TAG_I--;
                                allCallBack.mount(relayItem);
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
     * 获取红包
     */
    public void getRedBao(){
        ++ZApplication.ALL_TAG_I;
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken", PrefManager.getInstance().getToken());
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.GETREDBAO,"GETREDBAO",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                --ZApplication.ALL_TAG_I;
                if(!TextUtils.isEmpty(result.toString())) {
                    RedBaoM redBaoM=JSON.parseObject(result.toString(), RedBaoM.class);
                    if(redBaoM.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(redBaoM.getBody().getContent()!=null){
                            allCallBack.getRedBao(redBaoM.getBody().getContent());
                        }else{
                            ToastUtilsAll.getInstance().showShortToast(redBaoM.getDescribe());
                        }
                    }
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                --ZApplication.ALL_TAG_I;
                ToastUtilsAll.getInstance().showNetEoor();
            }
        });
    }

    /**
     * 下单
     */
    public void PlaceTheOrder(String products,String couponRedParam,String deliveryId,String recipientsAddress,String recipientsPhone,String recipientsName,int payMode,String password,String shopCartDetailIds){
        LogPrint.printError("one:"+products+"two:"+couponRedParam);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("products",products);
        paramsMap.put("couponRedParam",couponRedParam);
        paramsMap.put("deliveryId", deliveryId);
        paramsMap.put("recipientsAddress",recipientsAddress);
        paramsMap.put("recipientsPhone",recipientsPhone);
        paramsMap.put("recipientsName",recipientsName);
        paramsMap.put("payMode",payMode);
        paramsMap.put("password",password);
        paramsMap.put("shopCartDetailIds",shopCartDetailIds);
        paramsMap.put("loginToken", PrefManager.getInstance().getToken());
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.PLACE_ON_OREER,"PLACE_ON_OREER",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    OrderOk  ok=JSON.parseObject(result.toString(),OrderOk.class);
                    if(ok.getCode().endsWith(OkhttpManager.SUCESS)){
                       if(ok.getBody().getContent()!=null){
                           allCallBack.Order(ok.getBody().getContent());
                       }
                    }else{
                        ToastUtilsAll.getInstance().showShortToast(""+ok.getDescribe());
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
