package com.iaowoo.mobile.Ui.classification.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
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
 * @Description: ${todo}(中国好物)
 * @date 2018/9/11
 * @email ${18011009889@163.com}
 */
public class ChinaGoodsPresenter {

    private Context mContext;
    private ChinaGoodsCallBack chinaGoodsCallBack;
    private ChinaGoodsMainCallBack chinaGoodsMainCallBack;

    public ChinaGoodsPresenter(Context context, ChinaGoodsMainCallBack chinaGoodsMainCallBack){
        this.mContext=context;
        this.chinaGoodsMainCallBack=chinaGoodsMainCallBack;
    }

    public void setCallBack(ChinaGoodsCallBack chinaGoodsCallBack){
        this.chinaGoodsCallBack=chinaGoodsCallBack;
    }

    public interface ChinaGoodsCallBack{
        void getBanner(Banner banner);
    }

    public interface  ChinaGoodsMainCallBack{
        void Shop(List<Shop.BodyBean.ContentBean.ListBean> shops);
        void NohaveData();

    }


    /**
     * 中国好物为你推荐
     */
    public void getShopType(int pageNum,int pageSize){
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
                        if(shop.getBody().getContent().getList()!=null){
                            if(shop.getBody().getContent().getList().size()!=0){
                                LogPrint.printError("这里的总条数"+shop.getBody().getContent().getList().size());
                                chinaGoodsMainCallBack.Shop(shop.getBody().getContent().getList());
                            }else{
                                chinaGoodsMainCallBack.NohaveData();
                            }
                        }}
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                ToastUtilsAll.getInstance().showNetEoor();
                chinaGoodsMainCallBack.NohaveData();

            }
        });
    }
    /**
     * 获得中国好物的bannar所有的数据
     */
    public void getBanner(){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",1);
        paramsMap.put("pageSize",100);
        paramsMap.put("location","");
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.BANNER,"GOODS_BANNER",-1, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Banner banner= JSON.parseObject(result.toString(),Banner.class);
                    if (banner.getCode().equals(OkhttpManager.SUCESS)) {
                        if(banner.getBody().getContent().getList()!=null) {
                            if (banner.getBody().getContent().getList().size() > 0) {
                                chinaGoodsCallBack.getBanner(banner);
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
}
