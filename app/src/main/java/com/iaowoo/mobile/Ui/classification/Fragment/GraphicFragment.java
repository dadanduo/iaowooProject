package com.iaowoo.mobile.Ui.classification.Fragment;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.Ui.classification.Presenter.Goods_DetailsPresenter;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.CommentsModel;
import com.iaowoo.mobile.Ui.classification.Model.RecommentList;
import com.iaowoo.mobile.Ui.classification.Model.SearchGoods;
import com.iaowoo.mobile.Ui.classification.Presenter.Goods_DetailsPresenter;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.common.ConfigH5Url;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

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
 * @Description: ${todo}(图文fragment weex)
 * @date 2018/11/24
 * @email ${18011009889@163.com}
 */
public class GraphicFragment extends BaseBufferFragment implements H5CallBack, Goods_DetailsPresenter.GoodsCallBack {


    @BindView(R.id.bridge)
    BridgeWebView bridge;
    @BindView(R.id.nowifi)
    FrameLayout nowifi;
    @BindView(R.id.re_loading)
    Button re_loading;

    private Webset webset;

    private String templateId;

    private Goods_DetailsPresenter goods_detailsPresenter;

    public void setTemplateId(String templateId){
        this.templateId=templateId;
    }

    @Override
    public void RecommendList(List<RecommentList.BodyBean.ContentBean.RecommendListBean> recommendListBeans) {

    }

    @Override
    public void ListBean(CommentsModel.BodyBean.ContentBean.JudgesBean listBean, int allNumber) {

    }

    @Override
    public void noComment() {

    }

    @Override
    public void searchGoods(SearchGoods.BodyBean.ContentBean contentBean) {
        loadingWebview(contentBean);
    }


    public interface GraphicBack{
        void comeInGraphic();
    }

    private GraphicBack graphicBack;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof GraphicBack) {
            graphicBack = (GraphicBack)activity; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.graphic_layout;
    }
    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        goods_detailsPresenter = new Goods_DetailsPresenter(this);

        goods_detailsPresenter.getSearchGoodsInfoById(templateId);

    }

    @Override
    public void onStart() {
        super.onStart();
        LogPrint.printError("图文页面开始");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogPrint.printError("图文页面暂停");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        graphicBack=null;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isInit  默认是false 没有初始化控件过，不然会空指针
        if(isVisibleToUser) {
            LogPrint.printError("可见");
            graphicBack.comeInGraphic();
        }
    }

    private void  loadingWebview(SearchGoods.BodyBean.ContentBean contentBean){
        webset=new Webset(getActivity(),bridge,this);
        //重新加载
        re_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webLoading(ConfigH5Url.GOODES_IMAGE_PAGE);
            }
        });
        webLoading(ConfigH5Url.GOODES_IMAGE_PAGE);
        //告诉js状态栏和tabbar的高度
        webset.tellAndroidToJs();
        //主动推给H5token
        webset.pushTokenToJs();
        //主动推规格H5
        Map<String,Object> params = new HashMap<>();
        params.put("productParams",contentBean.getArgumentsInfoList());
        webset.getProductParams(  ZApplication.gson.toJson(params));
        webset.pushImages(contentBean.getProductInfo().getDetailImages());
    }

    /**
     * 加载路径
     * @param url
     */
    private void webLoading(final String url){
        SingleOverAll.getInstance().abnormal(getActivity(), new SingleOverAll.LoadingCallBack() {
            @Override
            public void haveWifi() {
                nowifi.setVisibility(View.GONE);
                bridge.setVisibility(View.VISIBLE);
                webset.JsBridge(url);
            }
            @Override
            public void noHaveWifi() {
                nowifi.setVisibility(View.VISIBLE);
                bridge.setVisibility(View.GONE);
            }
        });
    }
    @Override
    public void loadingFaild() {

    }

    @Override
    public void loadingSuccess() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(webset!=null){
            webset.Destroy();
            webset=null;
        }
        goods_detailsPresenter=null;
    }
}