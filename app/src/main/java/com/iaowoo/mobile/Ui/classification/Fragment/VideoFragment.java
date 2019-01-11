package com.iaowoo.mobile.Ui.classification.Fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
import com.iaowoo.mobile.Utils.MockData;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.VideoPlaybackActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.OnclicKRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.VideoListRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Model.VideoEntity;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.MockData;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
 * @Description: ${todo}(小视频fragment)
 * @date 2018/11/15
 * @email ${18011009889@163.com}
 */
public class VideoFragment  extends  BaseFragment implements MockData.CallBackData {
    /**
     * 刷新
     */
    @BindView(R.id.list_swiperefreshlayout)
    SmartRefreshLayout video_swiperefreshlayout_list;

    @BindView(R.id.video_list_recycler_view)
    WRecyclerView video_list_recycler_view;

    @BindView(R.id.top_video)
    RelativeLayout top_video;
    @BindView(R.id.video_bg)
    RelativeLayout video_bg;

    @BindView(R.id.show_text)
    TextView show_text;

    @BindView(R.id.top)
    LinearLayout top;

    VideoListRecycleAdapter videoListRecycleAdapter;

    private MockData mockData=null;

    private List<VideoEntity.BodyBean.ContentBean.ListBean> listBeansAll=null;
    @Override
    public int getLayoutResId() {
        return R.layout.video_list;
    }

    @Override
    public void getDatas(int curPageIndex, int pageSize) {
        mockData.getVideoListData(PrefManager.getInstance().getToken(),"", curPageIndex, pageSize, this);
    }

    @Override
    public void noHaveDatas() {

    }

    @Override
    protected void initView() {
        super.initView();
        mockData=new MockData();
        listBeansAll=new ArrayList<>();
        initDatas();
        this.grideShow(video_list_recycler_view,2);
        this.initSwipeRefreshView1(video_swiperefreshlayout_list);
        this.setViewMarginTop(top);
        //设置为透明
        video_bg.getBackground().mutate().setAlpha(0);
        show_text.setVisibility(View.GONE);
        /****************************头部样式变化*************************************/
        this.setRecyclerOnscrocListenr(video_list_recycler_view, new BaseFragment.ScrollListenerCallBack() {
            @Override
            public void updateTransparency(int transparencyData) {
                video_bg.getBackground().mutate().setAlpha(transparencyData);
            }
            @Override
            public void HideTile() {
                video_bg.getBackground().mutate().setAlpha(0);
                show_text.setVisibility(View.GONE);
            }
            @Override
            public void LoadingOK() {
                video_bg.getBackground().mutate().setAlpha(255);
                show_text.setVisibility(View.VISIBLE);
            }
        });
        /*******************************************************************/
        EventBus.getDefault().register(this);

    }

    @Override
    protected void initData() {
        super.initData();
        videoListRecycleAdapter=new VideoListRecycleAdapter(getActivity(),mockData);
        videoListRecycleAdapter.setOnclicKRecycleAdapter(new OnclicKRecycleAdapter() {
            @Override
            public void onItemClick(int position) {
                Intent mintent=new Intent(getActivity(), VideoPlaybackActivity.class);
                //把list强制类型转换成Serializable类型
                mintent.putExtra("objectList", (Serializable) listBeansAll);
                mintent.putExtra("position",position-1);
                mintent.putExtra("pageNum",curPageIndex);
                startActivity(mintent);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

        mockData.getVideoListData(PrefManager.getInstance().getToken(),"", curPageIndex, pageSize, this);
    }

    @Override
    protected void refreshOk() {
        super.refreshOk();
        tempY=0;
        videoListRecycleAdapter.notifyDataSetChanged();
        listBeansAll.clear();
    }
    @Override
    public void getListVideos(List<VideoEntity.BodyBean.ContentBean.ListBean> listBeans) {
        setData(true);
        stopRefreshAndLoading();
        //允许上拉加载功能
        if(video_list_recycler_view!=null) {
            video_list_recycler_view.setPullLoadEnable(true);
            //获取加载的分页总数据
            for (int i = 0; i < listBeans.size(); i++) {
                datas.add(listBeans.get(i));
                listBeansAll.add(listBeans.get(i));
            }
            if (First) {
                videoListRecycleAdapter.setShops(datas);
                video_list_recycler_view.setAdapter(videoListRecycleAdapter);
                First = false;
            } else {
                videoListRecycleAdapter.setShops(datas);
                videoListRecycleAdapter.notifyDataSetChanged();
            }
        }
    }

    @OnClick({R.id.back_return})
    public void onclick(View view){
        switch (view.getId()){
            //返回
            case R.id.back_return:
                break;
            default:
                break;
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            video_list_recycler_view.scrollToPosition(0);
            tempY=0;
            EventBus.getDefault().post(new EventBusMessageShopCar(2));
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if( mockData!=null){
            mockData=null;
        }
        EventBus.getDefault().unregister(this);
    }
    /**
     * EventBus消息处理方法。
     * @param eventBusMessageRefresh
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageRefresh eventBusMessageRefresh) {
    }
}
