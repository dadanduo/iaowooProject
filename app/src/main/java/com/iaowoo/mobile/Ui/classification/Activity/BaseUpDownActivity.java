package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Ui.classification.Fragment.BaseFragment;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Fragment.BaseFragment;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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
 * @Description: ${todo}(带上拉加载，下拉刷新功能的activity)
 * @date 2018/9/3
 * @email ${18011009889@163.com}
 */
public abstract  class BaseUpDownActivity<T> extends BaseActivity{

    protected View rootView;
    protected Context mContext;
    protected Unbinder unbinder;
    public int curPageIndex = 1;//当前页数
    public int pageSize = 15;//每一页的列表项数目
    private boolean haveData=true;
    private WRecyclerView recyclerView=null;
    public boolean First=true;
    private SwipeRefreshLayout swipeRefreshLayout=null;
    public List<T> datas=null;
    /**
     * gride : true   list : true
     */
    public boolean HENGZHE=true;
    public  int tempY = 0;
    private int START=0;
    private int MAX=255;

    /**
     * 刷新风格  0:google原生   1:自定义  默认为原生
     */
    private int refreshStyle=0;

    /**
     * 是不是在刷新数据
     */
    public Boolean mIsRefreshing=false;
    private SmartRefreshLayout smartRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        int layoutId = getLayoutResId();
        //初始化butterknife
        if (layoutId > 0) {
            rootView = LayoutInflater.from(mContext).inflate(layoutId, null);
            setContentView(rootView);
            unbinder = ButterKnife.bind(this);
        }
        initView();
        initData();

    }
    public abstract int getLayoutResId();


    protected void initView() {
    }
    protected   void initData() {
    }
    /**
     * 分页加载
     * @param curPageIndex
     * @param pageSize
     */
    public abstract  void getDatas(int curPageIndex,int pageSize);

    /**
     * 没有数据
     */
    public abstract  void  noHaveDatas();

    /**
     * 刷新
     */
    protected   abstract void refreshOk();



    public void initDatas() {
        //===================网络请求获取列表=====================
        //解决在第二页的时候进行查询的时候返回到该界面时显示的还是第二页数据
        datas=new ArrayList<>();
        datas.clear();
        curPageIndex = 1;
    }

    /**
     * 竖向
     * @param recyclerView
     */
    public void listShow(WRecyclerView recyclerView){
        this.recyclerView=recyclerView;
        HENGZHE=false;
        recyclerView.setHasFixedSize(true);//设置固定大小
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //为recyclerView设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);

    }
    /**
     * 竖向
     * @param recyclerView
     */
    public void listShow1(WRecyclerView recyclerView){
        this.recyclerView=recyclerView;
    }
    /**
     * 横向
     * @param recyclerView
     */
    public void grideShow(WRecyclerView recyclerView,int showSize){
        this.recyclerView=recyclerView;
        HENGZHE=true;
        //===================网络请求获取列表=====================
        GridLayoutManager manager = new GridLayoutManager(this,showSize, LinearLayoutManager.VERTICAL,false);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return Bigeall.get(position);
//            }
//        });
        recyclerView.setLayoutManager(manager);
    }


    /**
     * 初始化SwipeRefresh刷新控件
     */
    public void initSwipeRefreshView1(SmartRefreshLayout smartRefreshLayout) {
        refreshStyle=1;
        this.smartRefreshLayout=smartRefreshLayout;
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                refresh();
                refreshOk();
            }
        });
        //自定义上拉加载的监听
        recyclerView.setWRecyclerListener(new WRecyclerView.WRecyclerViewListener() {
            @Override
            public void onLoadMore() {
                if(haveData){
                    curPageIndex = curPageIndex + 1;
                    getDatas(curPageIndex,pageSize);
                }else{
                    noHaveDatas();
                }
            }
        });
    }

    /**
     * 初始化SwipeRefresh刷新控件
     */
    public void initSwipeRefreshView111(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout=swipeRefreshLayout;
        //设置进度条的颜色主题，最多能设置四种
        swipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color_1,
                R.color.swiperefresh_color_2,
                R.color.swiperefresh_color_3,
                R.color.swiperefresh_color_4);
        //调整进度条距离屏幕顶部的距离 scale:true则下拉的时候从小变大
        swipeRefreshLayout.setProgressViewOffset(true, 0, dip2px(this,50));


        //为SwipeRefreshLayout布局添加一个Listener，下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                refreshOk();
            }
        });

        //自定义上拉加载的监听
        recyclerView.setWRecyclerListener(new WRecyclerView.WRecyclerViewListener() {
            @Override
            public void onLoadMore() {
                if(haveData){
                    curPageIndex = curPageIndex + 1;
                    getDatas(curPageIndex,pageSize);
                }else{
                    LogPrint.printError("已经是最后一页了");
                    noHaveDatas();
                }
            }
        });
    }

    /**
     * 停止刷新和上拉加载
     */
    public void stopRefreshAndLoading() {
        if(refreshStyle==0) {
            //检查是否处于刷新状态
            if (swipeRefreshLayout.isRefreshing()) {
                //显示或隐藏刷新进度条，一般是在请求数据的时候设置为true，在数据被加载到View中后，设置为false。
                swipeRefreshLayout.setRefreshing(false);
            }
        }
        //如果正在加载，则获取数据后停止加载动画
        if(recyclerView.ismPullLoading()){
            //停止加载动画
            recyclerView.stopLoadMore();
        }
        //设置处于下拉刷新状态中[否]
        recyclerView.setPullRefresh(false);
        //停止刷新或者加载时候可以允许滑动
        mIsRefreshing=false;
    }
    /**
     * 刷新数据
     */
    private   void  refresh(){
        datas.clear();
        First=true;
        recyclerView.setPullLoadEnable(false);//禁用上拉加载功能
        recyclerView.setPullRefresh(true);//设置处于下拉刷新状态中
        curPageIndex = 1;
        //开始加载分页数据
        getDatas(curPageIndex, pageSize);//更新列表项集合
    }
    /**
     * 设置没有更多数据
     */
    public void setNoData(Boolean noData){
        this.haveData=noData;
    }

    /**
     * dp转px
     * 16dp - 48px
     * 17dp - 51px*/
    private int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)((dpValue * scale) + 0.5f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getLayoutResId() > 0 && unbinder != null) {
            unbinder.unbind();
        }
    }
    /**
     * 设置没有更多数据
     */
    public void setData(Boolean noData){
        this.haveData=noData;
    }
    /**
     * 动态设置标题栏距离顶部高度
     * @param viewMarginTop
     */
    public void setViewMarginTop(View viewMarginTop){
        //获取状态栏的高度
        int height= SingleOverAll.getInstance().getStatusBarHeight1(ZApplication.getContext());
        //动态设置高度
        UtilsAll.setMargins(viewMarginTop,0,height,0,0);
    }

    public void setRecyclerOnscrocListenr(WRecyclerView recyclerView , final BaseFragment.ScrollListenerCallBack scrollListenerCallBack){

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * Callback method to be invoked when the RecyclerView has been scrolled. This will be
             * called after the scroll has completed.
             * This callback will also be called if visible item range changes after a layout
             * calculation. In that case, dx and dy will be 0.
             * @param recyclerView The RecyclerView which scrolled.
             * @param dx           The amount of horizontal scroll.
             * @param dy           The amount of vertical scroll.
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                tempY += dy;
                if (tempY > START&&tempY<MAX) {
                    scrollListenerCallBack.updateTransparency(tempY);
                } else if (tempY>MAX) {
                    scrollListenerCallBack.LoadingOK();

                }else if(tempY==0||tempY<0||tempY<150){
                    tempY=0;
                    scrollListenerCallBack.HideTile();
                }
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }


}
