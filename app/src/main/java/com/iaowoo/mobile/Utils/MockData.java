package com.iaowoo.mobile.Utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.Comments;
import com.iaowoo.mobile.Ui.classification.Model.CommentsAll;
import com.iaowoo.mobile.Ui.classification.Model.CommentsOk;
import com.iaowoo.mobile.Ui.classification.Model.VideoEntity;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.util.HashMap;
import java.util.List;

/**
 * @author ArcherYc
 * @date on 2018/9/12  下午3:47
 * @mail 247067345@qq.com
 */
public class MockData {

    public MockData(){

    }

    public interface  CallBackData{
        void getListVideos(List<VideoEntity.BodyBean.ContentBean.ListBean> listBeans);
    }
    public interface  CallBackCommetnsData{
        void getListComments(List<Comments.BodyBean.ContentBean.ListBean> listBeans,int allSizeNumber);
        void noHaveData();

    }
    public interface  CallBackCommentsOkData{
        void start();
        void getCommentsData(CommentsOk.BodyBean.ContentBean contentBean);
        void error(String error);
    }
    public interface  CallBackCommentsOk{
        void ok();
    }
    /**
     * 获取小视频列表数据
     */
    public   void getVideoListData(String loginToken,String videoId, int pageNum, int pageSize, final CallBackData callBackData){
        LogPrint.printError("当前页数:"+pageNum+"总共条数"+pageSize+"视频id"+videoId);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("loginToken",loginToken);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.SEARCH_VIDEO,"search_video",pageNum,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    VideoEntity videoEntity= JSON.parseObject(result.toString(),VideoEntity.class);
                    if(videoEntity.getCode().endsWith(UtilsOkHttpAll.SUCESS)){
                        if(videoEntity.getBody().getContent().getList()!=null){
                            if(videoEntity.getBody().getContent().getList().size()>0) {
                                callBackData.getListVideos(videoEntity.getBody().getContent().getList());
                            }
                        }else{
                            ToastUtilsAll.getInstance().showShortToast(""+videoEntity.getDescribe());
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
     * 获取小视频评论列表
     */
    public   void getCommentsListData(String videoId,String loginToken, int pageNum, int pageSize, final  CallBackCommetnsData callBackCommetnsData){
        LogPrint.printError("当前页数:"+pageNum+"总共条数"+pageSize+"视频id"+videoId);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("pageNum",pageNum);
        paramsMap.put("pageSize",pageSize);
        paramsMap.put("loginToken",loginToken);
        paramsMap.put("videoId",videoId);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.GET_COMMENTS,"GET_COMMENTS",pageNum,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    Comments comments=JSON.parseObject(result.toString(),Comments.class);
                    if(comments.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(comments.getBody().getContent()!=null){
                            if(comments.getBody().getContent().getList().size()>0){
                                callBackCommetnsData.getListComments(comments.getBody().getContent().getList(),comments.getBody().getContent().getTotal());
                            }else{
                                callBackCommetnsData.noHaveData();
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
     *开始评论
     */
    public   void CommentStart(String videoId, String loginToken, String content, final CallBackCommentsOkData callBackCommentsOkData, final DialogUtils.CommentOK commentOK){
        callBackCommentsOkData.start();
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("content",content);
        paramsMap.put("loginToken",loginToken);
        paramsMap.put("videoId",videoId);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.COMMENTS_VIDEO,"COMMENTS_VIDEO",-1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    LogPrint.printError("评论成功"+result.toString());
                    CommentsOk commentsOk=JSON.parseObject(result.toString(),CommentsOk.class);
                    if(commentsOk.getCode().endsWith(OkhttpManager.SUCESS)){
                        if(commentsOk.getBody().getContent()!=null){
                            callBackCommentsOkData.getCommentsData(commentsOk.getBody().getContent());
                            commentOK.ok();
                        }
                    }else{
                        callBackCommentsOkData.error(commentsOk.getDescribe());
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
     *开始点赞视频
     */
    public  void DokiVieo(String videoId, String loginToken,int type , final CallBackCommentsOk callBackCommentsOk){
        LogPrint.printError("一直在点赞");
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("type",type);
        paramsMap.put("loginToken",loginToken);
        paramsMap.put("videoId",videoId);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.DOKI_VIDEO,"DOKI_VIDEO",-1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    CommentsAll commentsAll=JSON.parseObject(result.toString(),CommentsAll.class);
                    if(commentsAll.getCode().endsWith(OkhttpManager.SUCESS)){
                        callBackCommentsOk.ok();
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
     *开始点赞评论
     */
    public  void DokiComments(String commentId, String loginToken, int type , final CallBackCommentsOk callBackCommentsOk){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("type",type);
        paramsMap.put("loginToken",loginToken);
        paramsMap.put("commentId",commentId);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.DOKI_COMMENTS,"DOKI_COMMENTS",-1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    CommentsAll commentsAll=JSON.parseObject(result.toString(),CommentsAll.class);
                    if(commentsAll.getCode().endsWith(OkhttpManager.SUCESS)){
                        callBackCommentsOk.ok();
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
     *删除评论
     */
    public  void NoComments(String commentId, String loginToken, final CallBackCommentsOk callBackCommentsOk){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",loginToken);
        paramsMap.put("commentId",commentId);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.NO_COMMENTS,"NO_COMMENTS",-1,paramsMap,new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                if(!TextUtils.isEmpty(result.toString())) {
                    CommentsAll commentsAll=JSON.parseObject(result.toString(),CommentsAll.class);
                    if(commentsAll.getCode().endsWith(OkhttpManager.SUCESS)){
                        callBackCommentsOk.ok();
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
    public  void getStartPage(final HomeFragmentPresenter.HomeMainCallBack homeMainCallBack){
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
                                    if(banner.getBody().getContent().getList().get(i).getLocation()==37){
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
}
