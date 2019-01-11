package com.iaowoo.mobile.Ui.classification.Presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.ReMenResponse;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Ui.classification.Model.FenLei;
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
 * @Description: ${todo}(分类)
 * @date 2018/8/27
 * @email ${18011009889@163.com}
 */
public class ClassInfoPresenter {

    private ClassInfoCallBack classInfoCallBack;

    public void  setCallBack(ClassInfoCallBack classInfoCallBack){
        this.classInfoCallBack=classInfoCallBack;
    }
    public interface  ClassInfoCallBack{
        void Fenlei(FenLei fenLei);
        void getSlideShow(Banner banner);
        void nohaveData();
        void ReMenData(ReMenResponse.BodyBean.ContentBean contentBean);
    }
    /**
     * 获得分类数据
     */
    public void getFenLei(){
        HashMap<String,Object> pare=new HashMap<>();
        OkhttpManager.getInstance(ZApplication.getInstance().getApplicationContext()).requestPostByAsyn(true,UtilsOkHttpAll.FEN_LEI_LIE_BIAO,"getFenLei",-1, pare, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())){
                    FenLei fenLei= JSON.parseObject(result.toString(),FenLei.class);
                    if(fenLei.getCode().equals(OkhttpManager.SUCESS)){
                        if(fenLei.getBody().getContent()!=null) {
                            if (fenLei.getBody().getContent().size() != 0) {
                                classInfoCallBack.Fenlei(fenLei);
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
                                classInfoCallBack.getSlideShow(banner);
                            }else{
                                classInfoCallBack.nohaveData();
                            }
                        }}
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
                classInfoCallBack.nohaveData();
            }
        });
    }
    /**
     * 热门推荐
     */
    public void GetActivityListForUser(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",1);
        paramsMap.put("pageSize",20);
        paramsMap.put("activityId","531920073812606976");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.GET_ACTIVITY_LIST_FORUSER,"GET_ACTIVITY_LIST_FORUSER",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("热门推荐"+result.toString());
                    ReMenResponse reMenResponse=JSON.parseObject(result.toString(),ReMenResponse.class);
                    if(reMenResponse.getCode().endsWith(OkhttpManager.SUCESS)){
                      classInfoCallBack.ReMenData(reMenResponse.getBody().getContent());
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
