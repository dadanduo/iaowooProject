package com.iaowoo.mobile.Ui.classification.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.Ui.classification.Adapter.SearchRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Presenter.Goods_DetailsPresenter;
import com.iaowoo.mobile.Ui.classification.Presenter.ShopGoodsDialogPresenters;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.H5toAndroid.H5CallBack;
import com.iaowoo.mobile.H5toAndroid.Webset;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.ImagePagerActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.CommentsGoodRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.OnclicKRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.for_you_RecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Model.CommentsModel;
import com.iaowoo.mobile.Ui.classification.Model.RecommentList;
import com.iaowoo.mobile.Ui.classification.Model.SearchGoods;
import com.iaowoo.mobile.Ui.classification.Presenter.Goods_DetailsPresenter;
import com.iaowoo.mobile.Ui.classification.Presenter.ShopGoodsDialogPresenters;
import com.iaowoo.mobile.Ui.classification.View.ImageViewHolder;
import com.iaowoo.mobile.Ui.classification.View.TBLayout;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.umeng.Defaultcontent;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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
 * @Description: ${todo}(商品详情fragment)
 * @date 2018/11/24
 * @email ${18011009889@163.com}
 */
public class GoodsFragment extends BaseBufferFragment implements TBLayout.OnPullListener, TBLayout.OnPageChangedListener, Goods_DetailsPresenter.GoodsCallBack, CommentsGoodRecycleAdapter.onclickItme,H5CallBack {
    @BindView(R.id.tblayout)
    TBLayout mLayout;
    @BindView(R.id.header)
    ScrollView mHeader;
    @BindView(R.id.footer)
    ScrollView mFooter;
    @BindView(R.id.goods_lunbo_item)
    ConvenientBanner goods_lunbo_item;
    @BindView(R.id.price_ok)
    TextView price_ok;
    @BindView(R.id.price_two)
    TextView price_two;
    @BindView(R.id.send_number)
    TextView send_number;
    @BindView(R.id.service_text)
    TextView service_text;
    @BindView(R.id.describe_goods)
    TextView describe_goods;
    @BindView(R.id.choose_type)
    TextView choose_type;
    @BindView(R.id.goods_comments)
    TextView goods_comments;
    @BindView(R.id.nice_comments)
    TextView nice_comments;
    @BindView(R.id.recycler_commments)
    RecyclerView recycler_commments;
    @BindView(R.id.for_you)
    RecyclerView for_you;
    @BindView(R.id.two_page_show)
    RelativeLayout two_page_show;
    @BindView(R.id.integral_text)
    TextView integral_text;

    private LinearLayout mHeaderContent, mFooterContent;
    private SoftReference<DialogUtils> dialogUtilsSoftReference;
    private GlideUtils glideUtils;
    private GoodsBack goodsBack;
    private boolean isInit = false;
    private String goodsId;
    private String activityId;
    private int allnumberP_j;
    private Goods_DetailsPresenter goods_detailsPresenter;
    private Webset webset;
    /**
     * 轮播图
     */
    private List<String> LurC;
    /**
     * 为你推荐
     */
    private List<RecommentList.BodyBean.ContentBean.RecommendListBean> recommendListBeansAll;
    /**
     * 评价
     */
    private CommentsModel.BodyBean.ContentBean.JudgesBean listBeanP_J;
    /**
     * 商品信息
     */
    private SearchGoods.BodyBean.ContentBean contentBeanAll;
    /**
     * 保障
     */
    private List<SearchGoods.BodyBean.ContentBean.PromiseInfoListBean> promiseInfoListBeans;
    /**
     * 描述
     */
    private String miaoshu;
    /**
     * 当前页面状态
     */
    private int ThisPage = 1;
    // 2.1 定义用来与外部activity交互，获取到宿主activity
    private BaseFragment.ScrollListenerCallBack listterner;
    private ShopGoodsDialogPresenters shopGoodsDialogPresenters;

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean headerFootReached(MotionEvent event) {
        if (mHeader.getScrollY() + mHeader.getHeight() >= mHeaderContent.getHeight()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean footerHeadReached(MotionEvent event) {
        if (mFooter.getScrollY() == 0) {
            return true;
        }
        return false;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onPageChanged(int stub) {
        switch (stub) {
            case TBLayout.SCREEN_HEADER:
                LogPrint.printError("第一页");
                goodsBack.one();
                ThisPage = 1;
                mFooter.setVisibility(View.GONE);
                two_page_show.setVisibility(View.GONE);
                break;
            case TBLayout.SCREEN_FOOTER:
                LogPrint.printError("第二页");
                goodsBack.two();
                mFooter.setVisibility(View.VISIBLE);
                two_page_show.setVisibility(View.VISIBLE);
                ThisPage = 2;
                break;
        }
    }



    public interface GoodsBack {
        /**
         * 回到当前页面
         */
        void GoodsComeIn();

        /**
         * 回到第一页
         */
        void one();

        /**
         * 跳转到第二页
         */
        void two();

        /**
         * 跳转到评价页面直接
         */
        void select();

        /**
         * 结束当前页面
         */
        void byebye();
        /**
         * 滑动距离
         */
        void  scroDistans(int y);

        void returnMethod();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.goods_layout;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof GoodsBack) {
            goodsBack = (GoodsBack) activity; // 2.2 获取到宿主activity并赋值
        } else {
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    @Override
    protected void initView() {
        super.initView();
        //加载webview
        //第二页不显示
        mFooter.setVisibility(View.GONE);
        this.setViewMarginTop(mFooter);
        this.setViewMarginTop(two_page_show);
        goods_detailsPresenter = new Goods_DetailsPresenter(this);

        goods_detailsPresenter.getGoodsCommentsData1(goodsId, 1, 2);
        goods_detailsPresenter.getRecommendData(goodsId);
        goods_detailsPresenter.getSearchGoodsInfoById(goodsId);

        mLayout.setOnPullListener(this);
        mLayout.setOnContentChangeListener(this);
        mHeaderContent = (LinearLayout) mHeader.getChildAt(0);
        mFooterContent = (LinearLayout) mFooter.getChildAt(0);
        glideUtils = new GlideUtils();
//        EventBus.getDefault().register(this);
        isInit = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mLayout.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    LogPrint.printError("x:" + scrollX + "y:" + scrollY + "oldx:" + oldScrollX + "oldy:" + oldScrollY);
                }
            });
        }

    }

//    /**
//     * EventBus消息处理方法。
//     * @param eventBusMessageGoods
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onShowMessageEvent(EventBusMessageGoods eventBusMessageGoods) {
//    }

    @Override
    protected void initData() {
        super.initData();
        shopGoodsDialogPresenters = new ShopGoodsDialogPresenters(getActivity());

        mHeader.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int y =  mHeader.getScrollY();//获取纵向滑动距离
                goodsBack.scroDistans(y);
            }
        });


    }

    @Override
    public void onDetach() {
        super.onDetach();
//        EventBus.getDefault().unregister(this);
        glideUtils = null;
        listterner = null;
        goodsBack = null;
        shopGoodsDialogPresenters = null;
        if(webset!=null){
            webset.Destroy();
            webset=null;
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isInit  默认是false 没有初始化控件过，不然会空指针
        if (isVisibleToUser && isInit) {
            LogPrint.printError("可见");
                goodsBack.GoodsComeIn();
        }
    }

    @OnClick({R.id.see_more, R.id.choose, R.id.service, R.id.search_more_comments, R.id.return_image_comments})
    public void onclick(View view) {
        switch (view.getId()) {
            //查看更多
            case R.id.see_more:
                LogPrint.printError("查看更多评价");
                goodsBack.select();
                break;
            //选择规格
            case R.id.choose:
                LogPrint.printError("选择规格");
                buy(0, new ShopGoodsDialogPresenters.addShopCar() {
                    @Override
                    public void addSuccessful() {
                    }
                });
                break;
            //选择服务
            case R.id.service:
                LogPrint.printError("选择服务");
                shopGoodsDialogPresenters.InfrastructureAssurance(promiseInfoListBeans, glideUtils);
                break;
            case R.id.search_more_comments:
                goodsBack.select();
                break;
            case  R.id.return_image_comments:
               goodsBack.returnMethod();
                break;
            default:
                break;
        }
    }

    /**
     * 轮播图
     *
     * @param mcb
     * @param L
     */
    public void lunBo(ConvenientBanner mcb, final List<String> L) {
        if (L != null) {
            if (L.size() > 1) {
                //轮播图配置
                mcb.setPages(new CBViewHolderCreator<ImageViewHolder>() {
                    @Override
                    public ImageViewHolder createHolder() {
                        return new ImageViewHolder();
                    }
                }, L)
                        .setPageIndicator(new int[]{R.mipmap.turn_page_unselected_icon, R.mipmap.turn_page_selected_icon}) //设置两个点作为指示器
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL); //设置指示器的方向水平居中

                mcb.startTurning(ConfigFlag.TIME_DI);
            } else {
                //轮播图配置
                mcb.setPages(new CBViewHolderCreator<ImageViewHolder>() {
                    @Override
                    public ImageViewHolder createHolder() {
                        return new ImageViewHolder();
                    }
                }, L); //设置指示器的方向水平居中

            }
            mcb.setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    startImagePagerActivity(getActivity(), L, position, new ImagePagerActivity.ImageSize(0, 0));
                }
            });
        }
    }

    @Override
    public void RecommendList(List<RecommentList.BodyBean.ContentBean.RecommendListBean> recommendListBeans) {
        this.recommendListBeansAll = recommendListBeans;
        LogPrint.printError("为你推荐：" + ZApplication.ALL_TAG_I);
        /*****************************************************end*******************************************************/
        //为你推荐
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        for_you.setLayoutManager(manager);
        for_you.setNestedScrollingEnabled(false);
        for_you_RecycleAdapter for_you_recycleAdapter = new for_you_RecycleAdapter(getActivity(), glideUtils, new OnclicKRecycleAdapter() {
            @Override
            public void onItemClick(int position) {
                goodsBack.byebye();
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
        List<RecommentList.BodyBean.ContentBean.RecommendListBean> recommendListBeansNew = new ArrayList<>();
        if (recommendListBeansAll.size() > 6) {
            for (int i = 0; i < 6; i++) {
                recommendListBeansNew.add(recommendListBeansAll.get(i));
            }
            for_you_recycleAdapter.setData(recommendListBeansNew);
        } else {
            for_you_recycleAdapter.setData(recommendListBeansAll);
        }
        for_you.setAdapter(for_you_recycleAdapter);

    }

    @Override
    public void ListBean(CommentsModel.BodyBean.ContentBean.JudgesBean listBean, int allNumber) {
        this.listBeanP_J = listBean;
        this.allnumberP_j = allNumber;
        LogPrint.printError("评价：" + ZApplication.ALL_TAG_I);
        /*****************************************************评论*******************************************************/
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_commments.setLayoutManager(layoutManager);
        recycler_commments.setItemAnimator(new DefaultItemAnimator());
        recycler_commments.setNestedScrollingEnabled(false);

        CommentsGoodRecycleAdapter commentsGoodRecycleAdapter = new CommentsGoodRecycleAdapter(getActivity(), glideUtils);
        if (listBeanP_J != null) {
            if (listBeanP_J.getList().size() > 2) {
                List<CommentsModel.BodyBean.ContentBean.JudgesBean.ListBean> shops = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    shops.add(listBeanP_J.getList().get(i));
                }
                commentsGoodRecycleAdapter.setData(shops, this);
            } else {
                commentsGoodRecycleAdapter.setData(listBeanP_J.getList(), this);
            }
        }
        recycler_commments.setAdapter(commentsGoodRecycleAdapter);
        goods_comments.setText("商品评价(" + allnumberP_j + ")");

    }

    @Override
    public void noComment() {
        goods_comments.setText("商品评价(0)");
    }


    @Override
    public void searchGoods(SearchGoods.BodyBean.ContentBean contentBean) {
        this.contentBeanAll = contentBean;
        //设置分享的内容
        Defaultcontent.title = contentBeanAll.getProductInfo().getName();
        Defaultcontent.imageurl = contentBeanAll.getProductInfo().getMainImage();
        Defaultcontent.text = "我在"+getActivity().getResources().getString(R.string.app_name)+"发现了一个不错的商品，快来看看吧！";
        Defaultcontent.url = ConfigH5Url.HTTP_H5 + "/#/home/product_details?type=" + contentBeanAll.getType() + "&templateId=" + contentBeanAll.getTemplateId() + "&inviteCode=" + PrefManager.getInstance().getInvite() + "&form=share&sharePage=1";
        Defaultcontent.code_url = "/pages/goods/goodDetail?goodsId=" + contentBeanAll.getTemplateId() + "&inviteCode=" + PrefManager.getInstance().getInvite() + "@&form=share";
        Defaultcontent.price = "" + contentBeanAll.getMinSellPrice();
        Defaultcontent.desc = "" + contentBeanAll.getProductInfo().getDetail();
        this.promiseInfoListBeans = contentBeanAll.getPromiseInfoList();
        String imagelist2 = contentBean.getProductInfo().getRollImages();
        String str3 = imagelist2.replace(" ", "");
        LurC = Arrays.asList(str3.split(","));
        lunBo(goods_lunbo_item, LurC);

        loadingWebview(contentBean);

        price_ok.setText("￥" + UtilsAll.DoubleTo_2(contentBeanAll.getMinSellPrice()));
        //添加删除线
        price_two.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        price_two.setText("￥" + UtilsAll.DoubleTo_2(contentBeanAll.getMinOriginalPrice()));
        send_number.setText("销量" + contentBeanAll.getSales() + "件");
        if(PrefManager.getInstance().getIntegralRatio()!=0){
            if( contentBeanAll.getSubTemplateInfoList().get(0)!=null) {
                Float pv = (float)  contentBeanAll.getSubTemplateInfoList().get(0).getPv();
                Float integral = PrefManager.getInstance().getIntegralRatio();
              integral_text.setText(UtilsAll.DoubleTo_2(pv / integral) + "");
            }
        }

        miaoshu = contentBeanAll.getProductInfo().getName();
        describe_goods.setText((Html.fromHtml(SingleOverAll.getInstance().descString(miaoshu), SingleOverAll.getInstance().getImageGetterInstance(), null)));
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < contentBeanAll.getPromiseInfoList().size(); i++) {
            if (i == 0) {
                stringBuffer.append("" + contentBeanAll.getPromiseInfoList().get(i).getPromiseDescribe());
            } else if(i==1){
                stringBuffer.append(" · " + contentBeanAll.getPromiseInfoList().get(i).getPromiseDescribe());
            } else if(i==2){
                stringBuffer.append(" · " + contentBeanAll.getPromiseInfoList().get(i).getPromiseDescribe());
            }
        }
        service_text.setText("" + stringBuffer);
        choose_type.setText("选择规格");
        nice_comments.setText("");
        LogPrint.printError("商品信息：" + ZApplication.ALL_TAG_I);
    }

    /**
     * 立即购买和加入购物车跳转的方法
     *
     * @param type
     */
    public void buy(int type, ShopGoodsDialogPresenters.addShopCar shopCar) {
        shopGoodsDialogPresenters.ChoooseSpecifications(goods_detailsPresenter, contentBeanAll, glideUtils, miaoshu, activityId, type, shopCar);
    }

    @Override
    public void onClickNumber(String images, int position) {
        String str2 = images.replace(" ", "");
        List<String> list = Arrays.asList(str2.split(","));
        startImagePagerActivity(getActivity(), list, position, new ImagePagerActivity.ImageSize(0, 0));
    }

    /**
     * 跳转到图片浏览器
     *
     * @param context
     * @param imgUrls
     * @param position
     * @param imageSize
     */
    public void startImagePagerActivity(Context context, List<String> imgUrls, int position, ImagePagerActivity.ImageSize imageSize) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putStringArrayListExtra(ImagePagerActivity.INTENT_IMGURLS, new ArrayList<String>(imgUrls));
        intent.putExtra(ImagePagerActivity.INTENT_POSITION, position);
        intent.putExtra(ImagePagerActivity.INTENT_IMAGESIZE, imageSize);
        context.startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
    }

    @BindView(R.id.bridge_webview_all)
    BridgeWebView bridge_webview_all;
    @BindView(R.id.nowifi)
    FrameLayout nowifi;
    @BindView(R.id.re_loading)
    Button re_loading;

    private void  loadingWebview(SearchGoods.BodyBean.ContentBean contentBean){
        webset=new Webset(getActivity(),bridge_webview_all,this);
        //重新加载
        re_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webLoading(ConfigH5Url.GOODES_IMAGE_PAGE);
            }
        });
        webLoading(ConfigH5Url.GOODES_IMAGE_PAGE);

        Map<String,Object> params = new HashMap<>();
        params.put("productParams",contentBean.getArgumentsInfoList());
        webset.getProductParams(  ZApplication.gson.toJson(params));
        //告诉js状态栏和tabbar的高度
        webset.tellAndroidToJs();
        //主动推给H5token
        webset.pushTokenToJs();
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
                bridge_webview_all.setVisibility(View.VISIBLE);
                webset.JsBridge(url);
            }
            @Override
            public void noHaveWifi() {
                nowifi.setVisibility(View.VISIBLE);
                bridge_webview_all.setVisibility(View.GONE);
            }
        });
    }
    @Override
    public void loadingFaild() {

    }

    @Override
    public void loadingSuccess() {

    }
}
