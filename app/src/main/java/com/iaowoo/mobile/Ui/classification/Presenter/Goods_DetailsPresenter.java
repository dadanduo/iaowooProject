package com.iaowoo.mobile.Ui.classification.Presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Model.AddShopCar;
import com.iaowoo.mobile.Ui.classification.Model.CommentsModel;
import com.iaowoo.mobile.Ui.classification.Model.RecommentList;
import com.iaowoo.mobile.Ui.classification.Model.SearchGoods;
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
 * @Description: ${todo}(商品详情)
 * @date 2018/10/23
 * @email ${18011009889@163.com}
 */
public class Goods_DetailsPresenter {
    /**
     *
     */
    private GoodsCallBack goodsCallBack;

    private GoodsCallBack1 goodsCallBack1;
    /**
     * @param goodsCallBack
     */
    public Goods_DetailsPresenter(GoodsCallBack goodsCallBack) {
        this.goodsCallBack = goodsCallBack;
    }

    /**
     * @param goodsCallBack1
     */
    public Goods_DetailsPresenter(GoodsCallBack1 goodsCallBack1) {
        this.goodsCallBack1 = goodsCallBack1;
    }

    /**
     *
     */
    public interface GoodsCallBack {
        /**
         * @param recommendListBeans
         */
        void RecommendList(List<RecommentList.BodyBean.ContentBean.RecommendListBean> recommendListBeans);

        /**
         * @param listBean
         */
        void ListBean(CommentsModel.BodyBean.ContentBean.JudgesBean listBean,int allNumber);

        void noComment();

        /**
         * @param contentBean
         */
        void  searchGoods( SearchGoods.BodyBean.ContentBean contentBean);

    }

    public interface GoodsCallBack1 {
        void ListBean(List<CommentsModel.BodyBean.ContentBean.JudgesBean.ListBean> listBean);
        void nohave();
    }

    public interface GoodsCallBack2 {
        void addOK();
    }

    /**
     * 根据templateId查询推广商品信息
     * @param templateId
     */
    public void getRecommendData(String templateId) {
        ++ZApplication.ALL_TAG_I;
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("templateId",templateId);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.SHOPS_DETAILS_TEMPLATE_ID, "SHOPS_DETAILS_TEMPLATE_ID", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                --ZApplication.ALL_TAG_I;
                if (!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("根据templateId查询推广商品信息" + result.toString());
                    RecommentList  recommentList = JSON.parseObject(result.toString(),RecommentList.class);
                    if (recommentList.getCode().endsWith(OkhttpManager.SUCESS)) {
                        goodsCallBack.RecommendList(recommentList.getBody().getContent().getRecommendList());
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
     * 查询商品信息
     * @param goodsId
     */
    public void getSearchGoodsInfoById(String goodsId) {
        ++ZApplication.ALL_TAG_I;
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("goodsId", goodsId);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.SHOPS_GOODS_BYID, "SHOPS_GOODS_BYID", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                --ZApplication.ALL_TAG_I;
                if (!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("查询商品信息" + result.toString());
                    SearchGoods searchGoods = JSON.parseObject(result.toString(),SearchGoods.class);
                    if (searchGoods.getCode().endsWith(OkhttpManager.SUCESS)) {
                        if(searchGoods.getBody().getContent()!=null) {
                            goodsCallBack.searchGoods(searchGoods.getBody().getContent());
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
     * 获取商品详情的评价数据
     *
     * @param goodsId
     */
    public void getGoodsCommentsData1(String goodsId,int pageNum,int pageSize) {
        ++ZApplication.ALL_TAG_I;
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("templateId", goodsId);
        paramsMap.put("loginToken", "");
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.SHOPS_COMMENTS_DETAILS, "SHOPS_COMMENTS_DETAILS", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                --ZApplication.ALL_TAG_I;
                if (!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("获取商品详情的评价数据" + result.toString());
                    CommentsModel commentsModel= JSON.parseObject(result.toString(),CommentsModel.class);
                    if(commentsModel.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(commentsModel.getBody().getContent().getJudges()!=null){
                            if(commentsModel.getBody().getContent().getJudges().getList()!=null) {
                                if (commentsModel.getBody().getContent().getJudges().getList().size()>0) {
                                    goodsCallBack.ListBean(commentsModel.getBody().getContent().getJudges(),commentsModel.getBody().getContent().getJudges().getTotal());
                                }else{
                                    goodsCallBack.noComment();
                                }
                            }
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
     * 获取商品详情的评价数据
     *
     * @param goodsId
     */
    public void getGoodsCommentsData(String goodsId,int pageNum,int pageSize) {
        ++ZApplication.ALL_TAG_I;
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("templateId", goodsId);
        paramsMap.put("loginToken", "");
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.SHOPS_COMMENTS_DETAILS, "SHOPS_COMMENTS_DETAILS", pageNum, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                --ZApplication.ALL_TAG_I;
                if (!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("获取商品详情的评价数据" + result.toString());
                    CommentsModel commentsModel= JSON.parseObject(result.toString(),CommentsModel.class);
                    if(commentsModel.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(commentsModel.getBody().getContent().getJudges()!=null){
                            if(commentsModel.getBody().getContent().getJudges().getList()!=null) {
                                if (commentsModel.getBody().getContent().getJudges().getList().size()>0) {
                                    goodsCallBack1.ListBean(commentsModel.getBody().getContent().getJudges().getList());
                                }else{
                                     goodsCallBack1.nohave();
                                }
                            }
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
     * 加入购物车
     *
     */
    public void addShopCar(String subTemplateId, int buyNum, final GoodsCallBack2 goodsCallBack2) {
        ++ZApplication.ALL_TAG_I;
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("subTemplateId", subTemplateId);
        paramsMap.put("loginToken", PrefManager.getInstance().getToken());
        paramsMap.put("buyNum",buyNum);
        paramsMap.put("inviteCode",PrefManager.getInstance().getInvite());
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.ADD_SHOP_CAR, "ADD_SHOP_CAR", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                --ZApplication.ALL_TAG_I;
                LogPrint.printError(">>>>>添加购物车"+result.toString());
                AddShopCar addShopCar =JSON.parseObject(result.toString(),AddShopCar.class);
                if(addShopCar.getCode().endsWith(OkhttpManager.SUCESS)){
                    ToastUtilsAll.getInstance().showShortToast("添加购物车成功！");
                    goodsCallBack2.addOK();
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
                --ZApplication.ALL_TAG_I;
                ToastUtilsAll.getInstance().showNetEoor();
            }
        });
    }
}
