package com.iaowoo.mobile.Ui.classification.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Presenter.MyFragmentPresenter;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.MyRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.MyFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

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
 * @Description: ${todo}(我的页面)
 * @date 2018/8/27
 * @email ${18011009889@163.com}
 */
public class MyFragment  extends BaseFragment  implements MyFragmentPresenter.MyCallBack {

    @BindView(R.id.list_swiperefreshlayout)
    SmartRefreshLayout swiperefreshlayout_my;
    @BindView(R.id.recyclerview_my)
    WRecyclerView recyclerview_my;
    @BindView(R.id.my_head)
    RelativeLayout my_head;
    @BindView(R.id.titile_my)
    LinearLayout titile_my;

    private  String recommended="";

    private MyRecycleAdapter myRecycleAdapter=null;

    private String orderBy="",orderType="desc",queryType="common";

    private GlobalBroadcastReceiver globalBroadcastReceiver;

    /**
     * prosenter
     */
    private MyFragmentPresenter myFragmentProsenter = null;

    private RdioBroadCast rdioBroadCast;




    @Override
    public int getLayoutResId() {
        return R.layout.my_f;
    }

    @Override
    protected void initView() {
        super.initView();
        initDatas();
        this.grideShow(recyclerview_my,2);
        this.initSwipeRefreshView1(swiperefreshlayout_my);
        rdioBroadCast=new RdioBroadCast();
        pulianpuBroadCast();
        /****************************头部样式变化*************************************/
        this.setRecyclerOnscrocListenr(recyclerview_my, new ScrollListenerCallBack() {
            @Override
            public void updateTransparency(int transparencyData) {
                //获取状态栏的高度
                titile_my.setVisibility(View.VISIBLE);
                int height= SingleOverAll.getInstance().getStatusBarHeight1(ZApplication.getContext());
                UtilsAll.setMargins(my_head,0,height,0,0);
                titile_my.getBackground().mutate().setAlpha(transparencyData);
            }
            @Override
            public void HideTile() {
                titile_my.setVisibility(View.GONE);
            }
            @Override
            public void LoadingOK() {
                titile_my.getBackground().mutate().setAlpha(255);
            }
        });

        my_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /*******************************************************************/
        EventBus.getDefault().register(this);
    }

    @Override
    protected void refreshOk() {
        super.refreshOk();
        titile_my.setVisibility(View.GONE);
        tempY=0;
        LogPrint.printError("shuaxin");
        recyclerview_my.setAdapter(myRecycleAdapter);
        this.stopRefreshAndLoading();
        }

    /**
     * EventBus消息处理方法。
     * @param eventBusMessageRefresh
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageRefresh eventBusMessageRefresh) {
//        this.refresh();
        //刷新消息
        if(eventBusMessageRefresh.getTag()==0){
            LogPrint.printError("我的页面刷新了");
            titile_my.setVisibility(View.GONE);
            tempY=0;
            recyclerview_my.setAdapter(myRecycleAdapter);
        }else if(eventBusMessageRefresh.getTag()==886){
            LogPrint.printError("我的页面刷新了");
            titile_my.setVisibility(View.GONE);
            tempY=0;
            recyclerview_my.setAdapter(myRecycleAdapter);
        }

    }

    @Override
    protected void initData() {
        super.initData();
        myFragmentProsenter = new MyFragmentPresenter(getActivity());
        myFragmentProsenter.setCallBack(this);
//        List<String> historyItem= PrefManager.getInstance().getDataList(ZApplication.SEARCH_TAG);
//        if(historyItem!=null){
//            if(historyItem.size()>0){
//                StringBuffer stringBuffer=new StringBuffer();
//                for(int i=0;i<historyItem.size();i++){
//                    stringBuffer.append(historyItem.get(i));
//                }
//                LogPrint.printError("拼接好的关键字"+stringBuffer);
//                recommended=stringBuffer+"";
//                myFragmentProsenter.getShopTypeSearch("",queryType,orderBy,orderType,curPageIndex,pageSize,recommended);
//            }else{
//                myFragmentProsenter.getShopType(curPageIndex,pageSize);
//            }
//        }else{
//            myFragmentProsenter.getShopType(curPageIndex,pageSize);
//        }
        myRecycleAdapter=new MyRecycleAdapter(getActivity(),myFragmentProsenter);
        recyclerview_my.setAdapter(myRecycleAdapter);
    }

    @Override
    public void getDatas(int curPageIndex, int pageSize) {
        LogPrint.printError("当前页数啦啦啦啦："+curPageIndex);
//        recyclerview_my.setAdapter(myRecycleAdapter);
//        this.stopRefreshAndLoading();

//        if(TextUtils.isEmpty(recommended)){
//            myFragmentProsenter.getShopType(curPageIndex,pageSize);
//        }else{
//            myFragmentProsenter.getShopTypeSearch("",queryType,orderBy,orderType,curPageIndex,pageSize,recommended);
//        }
    }

    @Override
    public void noHaveDatas() {
        LogPrint.printError("没有数据了你还在滑个卵哦！！！");
    }

    @Override
    public void Shop(List<Shop.BodyBean.ContentBean.ListBean> shops) {
        this.setData(true);
        this.stopRefreshAndLoading();
        if(recyclerview_my!=null) {
            //允许上拉加载功能
            recyclerview_my.setPullLoadEnable(true);
            LogPrint.printError("总共条数:" + shops.size());
            //获取加载的分页总数据
            for (int i = 0; i < shops.size(); i++) {
                datas.add(shops.get(i));
            }
            //第一页数据加载adpter
            if (First) {
//            myRecycleAdapter.setShops(datas);
                recyclerview_my.setAdapter(myRecycleAdapter);
                First = false;
            }
            //第二次直接刷新
            else {
//            myRecycleAdapter.setShops(datas);
                myRecycleAdapter.notifyDataSetChanged();
            }
        }
    }
    @Override
    public void NohaveData() {
        this.setData(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            recyclerview_my.scrollToPosition(0);
            tempY=0;
            EventBus.getDefault().post(new EventBusMessageShopCar(2));
            titile_my.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        rdioBroadCast.unRegiseterBroad(getActivity(),globalBroadcastReceiver);
        EventBus.getDefault().unregister(this);
    }
    /**
     ** 广播
     */
    public void pulianpuBroadCast() {
        //注册全局广播
        globalBroadcastReceiver=new GlobalBroadcastReceiver(new BroadcastCallBack() {
            @Override
            public void ReceiverData(String tag,String data) {
                switch (tag)
                {
                    //登出成功
                    case "refresh":
                        LogPrint.printError("我的页面刷新了11111");
                        titile_my.setVisibility(View.GONE);
                        tempY=0;
                        recyclerview_my.setAdapter(myRecycleAdapter);
                        break;
                    default:
                        break;
                }
            }
        });
      rdioBroadCast.regiseterBroad(getActivity(),globalBroadcastReceiver, RdioBroadCast.BOARD);
    }
}
