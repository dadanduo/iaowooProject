//package com.iaowoo.mobile.Ui.classification.Fragment;
//
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.widget.RelativeLayout;
//
//import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
//import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
//import com.iaowoo.mobile.Ui.classification.Presenter.ChinaGoodsPresenter;
//import com.iaowoo.mobile.Utils.LogPrint;
//import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
//import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
//import com.iaowoo.mobile.R;
//import com.iaowoo.mobile.Ui.classification.Adapter.GoodsRecycleAdapter;
//import com.iaowoo.mobile.Ui.classification.Model.Shop;
//import com.iaowoo.mobile.Ui.classification.Presenter.ChinaGoodsPresenter;
//import com.iaowoo.mobile.Ui.classification.View.BadgeView;
//import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
//import com.iaowoo.mobile.Utils.LogPrint;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.util.List;
//
//import butterknife.BindView;
//
///**
// * ////////////////////////
// * //  ┏┓　　　┏┓///////////
// * //┏┛┻━━━┛┻┓ ////////////
// * //┃　　　　　　　┃     ////
// * //┃　　　━　　　┃     ////
// * //┃　┳┛　┗┳　┃       /////
// * //┃　　　　　　　┃     ////
// * //┃　　　┻　　　┃         //
// * //┃　　　　　　　┃        ///
// * //┗━┓　　　┏━┛           ///
// * //    ┃　　　┃   神兽保佑  ///
// * //    ┃　　　┃   代码无BUG！///
// * //    ┃　　　┗━━━┓     ///
// * //    ┃　　　　　　　┣┓ ///
// * //    ┃　　　　　　　┏┛ ///
// * //    ┗┓┓┏━┳┓┏┛      ///
// * //      ┃┫┫　┃┫┫     ///
// * ///////////////////////
// *
// * @author ${chenda}
// * @version V1.0
// * @Description: ${todo}(中国好物)
// * @date 2018/8/27
// * @email ${18011009889@163.com}
// */
//public class ChinaGoodsFragment  extends BaseFragment implements ChinaGoodsPresenter.ChinaGoodsMainCallBack {
//
//
//    private ChinaGoodsPresenter chinaGoodsProsenter=null;
//
//    private GoodsRecycleAdapter goodsRecycleAdapter;
//
//    private BadgeView badgeView=null;
//
//
//    @BindView(R.id.goods_swiperefreshlayout)
//    SwipeRefreshLayout goods_swiperefreshlayout;
//
//    @BindView(R.id.recycler_view_goods)
//    WRecyclerView recycler_view_goods;
//
//    @BindView(R.id.top_goods)
//    RelativeLayout top_goods;
//
//    @BindView(R.id.goods_bg)
//    RelativeLayout goods_bg;
//
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.p_g_xml;
//    }
//
//    @Override
//    public void getDatas(int curPageIndex, int pageSize) {
//        chinaGoodsProsenter.getShopType(curPageIndex,pageSize);
//    }
//
//    @Override
//    public void noHaveDatas() {
//        LogPrint.printError("都说没数据了!!!!!就是不信");
//    }
//
//    @Override
//    protected void initView() {
//        super.initView();
//        EventBus.getDefault().register(this);
//        initDatas();
//        this.grideShow(recycler_view_goods,2);
//        this.initSwipeRefreshView1(goods_swiperefreshlayout);
//        this.setViewMarginTop(top_goods);
//        //设置为透明
//        goods_bg.getBackground().mutate().setAlpha(0);
//        /****************************头部样式变化*************************************/
//        this.setRecyclerOnscrocListenr(recycler_view_goods, new ScrollListenerCallBack() {
//            @Override
//            public void updateTransparency(int transparencyData) {
//                goods_bg.getBackground().mutate().setAlpha(transparencyData);
//            }
//            @Override
//            public void HideTile() {
//                goods_bg.getBackground().mutate().setAlpha(0);
//            }
//            @Override
//            public void LoadingOK() {
//                goods_bg.getBackground().mutate().setAlpha(255);
//            }
//        });
//        /*******************************************************************/
//    }
//    @Override
//    protected void initData() {
//        super.initData();
//        chinaGoodsProsenter= new ChinaGoodsPresenter(getActivity(),this);
//        goodsRecycleAdapter=new GoodsRecycleAdapter(getActivity(),chinaGoodsProsenter);
//        chinaGoodsProsenter.getShopType(curPageIndex,pageSize);
//    }
//
//    @Override
//    protected void refreshOk() {
//        super.refreshOk();
//        tempY=0;
//        goodsRecycleAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void Shop(List<Shop.BodyBean.ContentBean.ListBean> shops) {
//        this.setData(true);
//        this.stopRefreshAndLoading();
//        //允许上拉加载功能
//        if(recycler_view_goods!=null) {
//            recycler_view_goods.setPullLoadEnable(true);
//            LogPrint.printError("总共条数:" + shops.size());
//            //获取加载的分页总数据
//            for (int i = 0; i < shops.size(); i++) {
//                datas.add(shops.get(i));
//            }
//            //第一页数据加载adpter
//            if (First) {
//                goodsRecycleAdapter.setShops(datas);
//                recycler_view_goods.setAdapter(goodsRecycleAdapter);
//                First = false;
//            }
//            //第二次直接刷新
//            else {
//                goodsRecycleAdapter.setShops(datas);
//                goodsRecycleAdapter.notifyDataSetChanged();
//            }
//        }
//    }
//
//    /**
//     * EventBus消息处理方法。
//     * @param eventBusMessageRefresh
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onShowMessageEvent(EventBusMessageRefresh eventBusMessageRefresh) {
//        //刷新消息
//        if(eventBusMessageRefresh.getTag()==0){
//        }
//    }
//
//    @Override
//    public void NohaveData() {
//        this.setData(false);
//        this.stopRefreshAndLoading();
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if(!hidden){
//            recycler_view_goods.scrollToPosition(0);
//            tempY=0;
//            goods_bg.getBackground().mutate().setAlpha(0);
//            EventBus.getDefault().post(new EventBusMessageShopCar(1));
//        }
//    }
//}
