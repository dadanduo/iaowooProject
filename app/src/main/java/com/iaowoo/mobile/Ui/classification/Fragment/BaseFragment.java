package com.iaowoo.mobile.Ui.classification.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.Mylayout;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
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
 * @Description: ${todo}(fragment的基类)
 * @date 2018/9/3
 * @email ${18011009889@163.com}
 */
public abstract class BaseFragment<T> extends Fragment {
    private Unbinder unbinder;
    public int curPageIndex = 1;//当前页数
    public int pageSize = 15;//每一页的列表项数目
    private boolean haveData=true;
    private WRecyclerView recyclerView=null;
    public boolean First=true;
    private SwipeRefreshLayout swipeRefreshLayout=null;
    public List<T> datas=null;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int layoutId = getLayoutResId();
        View view = inflater.inflate(layoutId, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();

        return view;
    }

    /**
     * 初始化layout
     * @return
     */
    public abstract int getLayoutResId();

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

    protected void initView() {

    }
    protected   void initData() {
    }

    protected  void refreshOk(){}

    public void initDatas() {
        //===================网络请求获取列表=====================
        //解决在第二页的时候进行查询的时候返回到该界面时显示的还是第二页数据
        datas=new ArrayList<>();
        datas.clear();
        //先加载轮播数据
        curPageIndex = 1;
    }

    /**
     * 竖向
     * @param recyclerView
     */
    public void listShow(WRecyclerView recyclerView){
        this.recyclerView=recyclerView;
        recyclerView.setHasFixedSize(true);//设置固定大小
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        Mylayout mLayoutManager = new Mylayout(getContext(), LinearLayoutManager.VERTICAL, false);
        //为recyclerView设置布局管理器
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(null);//设置动画为null来解决闪烁问题
    }
    /**
     * 横向
     * @param recyclerView
     */
    public void grideShow(WRecyclerView recyclerView,int showSize){
        this.recyclerView=recyclerView;
        GridLayoutManager manager = new GridLayoutManager(getActivity(),showSize, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
    }


    /**
     * 初始化SwipeRefresh刷新控件
     */
    public void initSwipeRefreshView11(SwipeRefreshLayout swipeRefreshLayout) {
        refreshStyle=0;
        this.swipeRefreshLayout=swipeRefreshLayout;
        //设置进度条的颜色主题，最多能设置四种
        swipeRefreshLayout.setColorSchemeResources(R.color.swiperefresh_color_1,
                R.color.swiperefresh_color_2,
                R.color.swiperefresh_color_3,
                R.color.swiperefresh_color_4);
        //调整进度条距离屏幕顶部的距离 scale:true则下拉的时候从小变大
        swipeRefreshLayout.setProgressViewOffset(true, 0, dip2px(getActivity(),50));
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
                    noHaveDatas();
                }
            }
        });
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
    public   void  refresh(){
        datas.clear();
        tempY=0;
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
    public void setData(Boolean noData){
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
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
    public interface  ScrollListenerCallBack{
        void updateTransparency(int transparencyData);
        void HideTile();
        void LoadingOK();

    }
    public void setRecyclerOnscrocListenr(WRecyclerView recyclerView , final ScrollListenerCallBack scrollListenerCallBack){

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
    /**
     * 动态设置标题栏距离顶部高度
     * @param viewMarginTop
     */
    public void setViewMarginTop1(View viewMarginTop){
        //获取状态栏的高度
        int height= SingleOverAll.getInstance().getStatusBarHeight1(ZApplication.getContext());
        //动态设置高度
        UtilsAll.setMargins(viewMarginTop,0,height+104,0,0);
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
