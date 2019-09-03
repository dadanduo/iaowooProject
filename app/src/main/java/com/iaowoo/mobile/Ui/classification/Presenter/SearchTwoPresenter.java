package com.iaowoo.mobile.Ui.classification.Presenter;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
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
 * @Description: ${todo}(搜索prosenter)
 * @date 2018/8/27
 * @email ${18011009889@163.com}
 */
public class SearchTwoPresenter {
    private Context context=null;
    private SearchCallBack searchCallBack;
    public SearchTwoPresenter(Context context){
        this.context=context;
    }
    public void  setCallBack(SearchCallBack searchCallBack){
        this.searchCallBack=searchCallBack;
    }
    public interface  SearchCallBack{
        void Shop(List<Shop.BodyBean.ContentBean.ListBean> shops);
        void allPages(int pages);
        void NoHaveData();
    }

    /**
     * 搜索产品数据
     */
    public void getShopType(String category,String queryType,String orderBy,String orderType,int pageNum,int pageSize,String goodsName){
        LogPrint.printError("当前页数:"+pageNum+"总共条数"+pageSize);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("goodsName",goodsName);
        paramsMap.put("parentCategory",category);
        paramsMap.put("orderBy",orderBy);
        paramsMap.put("orderType",orderType);
        paramsMap.put("queryType",queryType);
        paramsMap.put("goodsType","");

        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true,UtilsOkHttpAll.SEARCH_SHOPS,"SEARCH_A",pageNum, paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Shop shop = JSON.parseObject(result.toString(), Shop.class);
                    if (shop.getCode().equals(OkhttpManager.SUCESS)) {
                        if(shop.getBody().getContent().getList()!=null){
                            if (shop.getBody().getContent().getList().size() != 0) {
                                LogPrint.printError("这里的总条数" + shop.getBody().getContent().getList().size());
                                searchCallBack.Shop(shop.getBody().getContent().getList());
                                searchCallBack.allPages(shop.getBody().getContent().getPages());
                            } else {
//                                searchCallBack.NoHaveData();
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
