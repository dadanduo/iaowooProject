package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.RelativeLayout;

import com.iaowoo.mobile.Ui.classification.Fragment.BaseFragment;
import com.iaowoo.mobile.Utils.MockData;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.OnclicKRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.VideoListRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Fragment.BaseFragment;
import com.iaowoo.mobile.Ui.classification.Model.VideoEntity;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.MockData;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
 * @Description: ${todo}(视频列表页面)
 * @date 2018/11/5
 * @email ${18011009889@163.com}
 */
public class VideoListActivity extends  BaseUpDownActivity implements MockData.CallBackData {
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

    VideoListRecycleAdapter videoListRecycleAdapter;

    private MockData mockData=null;

    @Override
    public int getLayoutResId() {
        return R.layout.video_list;
    }


    @Override
    protected void initView() {
        super.initView();
        mockData=new MockData();
        this.allState();
        initDatas();
        this.grideShow(video_list_recycler_view,2);
        this.initSwipeRefreshView1(video_swiperefreshlayout_list);
        this.setViewMarginTop(top_video);
        //设置为透明
        video_bg.getBackground().mutate().setAlpha(0);
        /****************************头部样式变化*************************************/
        this.setRecyclerOnscrocListenr(video_list_recycler_view, new BaseFragment.ScrollListenerCallBack() {
            @Override
            public void updateTransparency(int transparencyData) {
                video_bg.getBackground().mutate().setAlpha(transparencyData);
            }
            @Override
            public void HideTile() {
                video_bg.getBackground().mutate().setAlpha(0);
            }
            @Override
            public void LoadingOK() {
                video_bg.getBackground().mutate().setAlpha(255);
            }
        });
        /*******************************************************************/

        videoListRecycleAdapter=new VideoListRecycleAdapter(this,mockData);
        videoListRecycleAdapter.setOnclicKRecycleAdapter(new OnclicKRecycleAdapter() {
            @Override
            public void onItemClick(int position) {
                Intent mintent=new Intent(mContext, VideoPlaybackActivity.class);
                mintent.putExtra("position",position-1);
                startActivity(mintent);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

        mockData.getVideoListData("","", curPageIndex, pageSize, this);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    /**
     * 刷新
     * @param curPageIndex
     * @param pageSize
     */
    @Override
    public void getDatas(int curPageIndex, int pageSize) {
        mockData.getVideoListData("","", curPageIndex, pageSize, this);
    }

    @Override
    public void noHaveDatas() {
        this.setData(false);
        this.stopRefreshAndLoading();
    }

    @Override
    protected void refreshOk() {
        tempY=0;
        videoListRecycleAdapter.notifyDataSetChanged();
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
                finish();
                break;
            default:
                break;
        }
    }

}
