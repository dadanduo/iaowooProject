package com.iaowoo.mobile.Ui.classification.Activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.VideoAdapter;
import com.iaowoo.mobile.Ui.classification.Model.VideoEntity;
import com.iaowoo.mobile.Ui.classification.View.DouYinMusic.MusicalNoteLayout;
import com.iaowoo.mobile.Ui.classification.View.pagerlayoutmanager.OnViewPagerListener;
import com.iaowoo.mobile.Ui.classification.View.pagerlayoutmanager.ViewPagerLayoutManager;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.MockData;
import com.iaowoo.mobile.Utils.ScreenAdaptationUtils;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

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
 * @Description: ${todo}(视频播放器)
 * @date 2018/10/31
 * @email ${18011009889@163.com}
 */
public class VideoPlaybackActivity extends BaseUpDownActivity implements OnViewPagerListener,VideoAdapter.VieoCallBack, MockData.CallBackData {
    /**
     * recycler
     */
    @BindView(R.id.video_recycler_view)
    WRecyclerView video_recycler_view;
    /**
     * swiperefreshlayout
     */
    @BindView(R.id.list_swiperefreshlayout)
    SmartRefreshLayout video_swiperefreshlayout;
    /**
     * 暂停图片
     */
    @BindView(R.id.pause_image)
    ImageView pause_image;
    /**
     * LayoutManager
     */
    private ViewPagerLayoutManager pagerLayoutManager;
    /**
     * adapter
     */
    private  VideoAdapter videoAdapter;
    /**
     * 记录当前视频位置
     */
    private int positionTag;
    /**
     * 是否播放    true 播放  false 暂停
     */
    private   boolean isPlaying=true;
    /**
     * 软引用
     */
    private SoftReference<ScreenAdaptationUtils> screenAdaptationUtils=null;
    /**
     * 传递过来需要播放的视频的位置
     */
    private int tag;
    /**
     * 用来接收传递过来的数据
     */
    private List<VideoEntity.BodyBean.ContentBean.ListBean> listBeansAll=null;
    /**
     * 接收列表页面传递过来的加载页数
     */
    private int pageNum;
    /**
     * 数据加载
     */
    private MockData mockData=null;
    /**
     * 动画
     */
    private Animation animation=null;
    @Override
    public int getLayoutResId() {
        return R.layout.video_activity;
    }

    @Override
    protected void initView() {
        super.initView();
        mockData=new MockData();
        listBeansAll=new ArrayList<>();
        initDatas();
        listBeansAll = (List<VideoEntity.BodyBean.ContentBean.ListBean>) getIntent().getSerializableExtra("objectList");
        tag=getIntent().getIntExtra("position",0);
        pageNum=getIntent().getIntExtra("pageNum",0);
        LogPrint.printError("播放"+tag+"页数"+pageNum);
        this.curPageIndex=pageNum;
        //全屏
        allState();
        //设置装填栏为白色
        this.setTileBar(true);
        //封面图加载图片框架
        Fresco.initialize(this);
        //加载ijk
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        //没有网络状态下背景图片适配
        screenAdaptationUtils=new SoftReference<>(new ScreenAdaptationUtils());
        this.listShow1(video_recycler_view);
        this.initSwipeRefreshView1(video_swiperefreshlayout);
        First=false;
    }

    @Override
    protected void initData() {
        super.initData();
        listShow();
        //手势判断
        video_recycler_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                final int action = e.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN://手指按下
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    case MotionEvent.ACTION_MOVE://手指移动（从手指按下到抬起 move多次执行）
                        break;
                    case MotionEvent.ACTION_UP://手指抬起
                        if (video_recycler_view.getScrollState() == RecyclerView.SCROLL_STATE_DRAGGING &&
                                pagerLayoutManager.findSnapPosition() == 0) {
                            if (video_recycler_view.getChildAt(0).getY() == 0 && video_recycler_view.canScrollVertically(1)) {//下滑操作
                                video_recycler_view.stopScroll();
                            }
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void getDatas(int curPageIndex, int pageSize) {
        mockData.getVideoListData(PrefManager.getInstance().getToken(),"", curPageIndex, pageSize, this);
    }

    @Override
    public void noHaveDatas() {
        this.setData(false);
        this.stopRefreshAndLoading();
    }

    @Override
    protected void refreshOk() {
        videoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onInitComplete() {
        playVideo(tag);
        positionTag=tag;
    }
    @Override
    public void onPageSelected(int position, boolean isBottom) {
        LogPrint.printError("刷新"+position);
        positionTag = position;
        playVideo(position);
    }
    @Override
    public void onPageRelease(boolean isNext, int position) {
        LogPrint.printError(""+position);
        releaseVideo(position);
    }
    /**
     * prepare后在回调中调用start开始播放
     *
     * @param position
     */
    private void playVideo(int position) {
        isPlaying=true;
        //暂停按钮隐藏
        if(pause_image!=null) {
            pause_image.setVisibility(View.GONE);
        }
        if (video_recycler_view != null) {
            //得到当前的布局
            final VideoAdapter.VideoViewHolder viewHolder = (VideoAdapter.VideoViewHolder) video_recycler_view.findViewHolderForLayoutPosition(position);
            //得到当前页面的数据
            VideoEntity.BodyBean.ContentBean.ListBean videoEntity = videoAdapter.getDataByPosition(position);
            if(videoEntity!=null) {
                if (viewHolder != null && !viewHolder.videoView.isPlaying()) {
                    //设置音乐名称
                    if (videoEntity.getMusic() != null) {
                        //设置音乐来源
                        if (!TextUtils.isEmpty(videoEntity.getMusic().getTitle() + "")) {
                            List<String> datasmusic = new ArrayList<>();
                            datasmusic.add("" + videoEntity.getMusic().getTitle());
                            datasmusic.add("" + videoEntity.getMusic().getTitle());
                            datasmusic.add("" + videoEntity.getMusic().getTitle());
                            viewHolder.tv_banner.setDatas(datasmusic);
                            viewHolder.tv_banner.startViewAnimator();
                        }else{
                            List<String> datasmusic = new ArrayList<>();
                            datasmusic.add("");
                            datasmusic.add("");
                            datasmusic.add("");
                            viewHolder.tv_banner.setDatas(datasmusic);
                            viewHolder.tv_banner.startViewAnimator();
                        }
                    }
                    //设置视频的播放地址
                    viewHolder.videoView.setVideoPath(videoEntity.getVideo().getPlayUrl());
                    LogPrint.printError("视频播放地址：" + videoEntity.getVideo().getPlayUrl());
                    viewHolder.videoView.getMediaPlayer().setOnInfoListener(new IMediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(IMediaPlayer iMediaPlayer, int what, int extra) {
                            if (what == IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                                //有网络显示封面图
                                if (UtilsAll.isHaveNet(ZApplication.getContext())) {
                                    viewHolder.sdvCover.setVisibility(View.INVISIBLE);
                                }
                            }
                            return false;
                        }
                    });
                    //没有网络显示背景图
                    if (!UtilsAll.isHaveNet(ZApplication.getContext())) {
                        viewHolder.background_image.setVisibility(View.VISIBLE);
                        viewHolder.sdvCover.setVisibility(View.GONE);
                        viewHolder.videoView.setVisibility(View.GONE);
                        //获取设备的高度
                        int height = SingleOverAll.getInstance().deviceHeight(ZApplication.getContext());
                        if (screenAdaptationUtils.get() != null) {
                            if (!screenAdaptationUtils.get().screenAdapter(height))
                                viewHolder.background_image.setImageResource(R.mipmap.waiting_loading_bg);
                            else
                                screenAdaptationUtils.get().screenAdapterDealWith(viewHolder.background_image, height, 5);
                        } else {
                            screenAdaptationUtils = new SoftReference<>(new ScreenAdaptationUtils());
                            if (!screenAdaptationUtils.get().screenAdapter(height))
                                viewHolder.background_image.setImageResource(R.mipmap.waiting_loading_bg);
                            else
                                screenAdaptationUtils.get().screenAdapterDealWith(viewHolder.background_image, height, 5);
                        }
                        viewHolder.videoView.stopPlayback();
                        ToastUtilsAll.getInstance().showShortToast("网络异常");
                    } else {
                        //有网络
                        viewHolder.background_image.setVisibility(View.GONE);
                        viewHolder.sdvCover.setVisibility(View.VISIBLE);
                        viewHolder.videoView.setVisibility(View.VISIBLE);
                        PlayVideo(viewHolder.music_note_layout,viewHolder.music_image);
                        //循环播放
                        viewHolder.videoView.setLooping(true);
                        viewHolder.videoView.prepareAsync();
                    }
                }
            }
        }
    }

    /**
     * 视频停止播放时直接释放资源
     *
     * @param position
     */
    private void releaseVideo(int position) {
        if (video_recycler_view != null) {
            VideoAdapter.VideoViewHolder viewHolder = (VideoAdapter.VideoViewHolder) video_recycler_view.findViewHolderForLayoutPosition(position);
            if (viewHolder != null) {
                viewHolder.videoView.stopPlayback();
                viewHolder.tv_banner.stopViewAnimator();
                viewHolder.sdvCover.setVisibility(View.VISIBLE);
                StopPlay(viewHolder.music_note_layout,viewHolder.music_image);
            }
        }
    }

    /**
     * 暂停视频播放
     *
     * @param position
     */
    private void pauseVideo(int position) {
        pause_image.setVisibility(View.VISIBLE);
        if (video_recycler_view != null) {
            VideoAdapter.VideoViewHolder viewHolder = (VideoAdapter.VideoViewHolder) video_recycler_view.findViewHolderForLayoutPosition(position);
            if (viewHolder != null) {
                viewHolder.videoView.pause();
                viewHolder.tv_banner.stopViewAnimator();
                StopPlay(viewHolder.music_note_layout,viewHolder.music_image);
            }
        }
    }
    /**
     * 视频继续播放
     *
     * @param position
     */
    private void startVideo(int position) {
        pause_image.setVisibility(View.GONE);
        if (video_recycler_view != null) {
            VideoAdapter.VideoViewHolder viewHolder = (VideoAdapter.VideoViewHolder) video_recycler_view.findViewHolderForLayoutPosition(position);
            if (viewHolder != null) {
                viewHolder.videoView.start();
                viewHolder.tv_banner.startViewAnimator();
                PlayVideo(viewHolder.music_note_layout,viewHolder.music_image);
            }
        }
    }

    /**
     * 页面
     */
    @Override
    protected void onResume() {
        super.onResume();
        startVideo(positionTag);
    }

    /**
     * 页面暂停
     */
    @Override
    protected void onStop() {
        super.onStop();
        pauseVideo(positionTag);
    }

    /**
     * 页面销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoAdapter!=null) {
            videoAdapter = null;
        }
        releaseVideo(positionTag);
        if(animation!=null) {
            animation.cancel();
        }
    }

    @OnClick({R.id.back})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.back:
                if(videoAdapter!=null) {
                    videoAdapter = null;
                }
                releaseVideo(positionTag);
                if(animation!=null) {
                    animation.cancel();
                }
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 竖向
     */
    private void listShow() {
        pagerLayoutManager = new ViewPagerLayoutManager(this, LinearLayoutManager.VERTICAL);
        pagerLayoutManager.setOnViewPagerListener(this);
        //加载adpter
        videoAdapter = new VideoAdapter(this, video_recycler_view,mockData,this);
        video_recycler_view.setLayoutManager(pagerLayoutManager);
        for(int i=0;i<listBeansAll.size();i++){
            datas.add(listBeansAll.get(i));
            //设置点赞数量
            videoAdapter.integers.add(i, listBeansAll.get(i).getVideo().getPraiseNum());
            //设置点赞状态
            videoAdapter.DokiStatus.add(i,listBeansAll.get(i).getVideo().getPraiseStatus());
            //设置评论数量
            videoAdapter.commentsIntegar.add(i,listBeansAll.get(i).getStatistics().getCommentNum());
        }
        videoAdapter.addData(datas);
        video_recycler_view.scrollToPosition(tag);
        if(videoAdapter!=null) {
            video_recycler_view.setAdapter(videoAdapter);
        }
    }

    /**
     * 单击事件
     */
    @Override
    public void oneClick() {
        //暂停
        if(isPlaying){
            isPlaying=false;
            pauseVideo(positionTag);
        }else{
            //播放
            isPlaying=true;
            startVideo(positionTag);
        }
    }

    /**
     * 双击事件
     */
    @Override
    public void doubleClick() {

    }

    /**
     * 继续转动
     * @param musicalNoteLayout
     */
    private void  PlayVideo(MusicalNoteLayout musicalNoteLayout, CircleImageView music_image){
        //动画
        animation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_image);
        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
        animation.setInterpolator(lin);
        music_image.startAnimation(animation);
        animation.cancel();
        musicalNoteLayout.start(true);
    }

    /**
     * 停止转动
     * @param musicalNoteLayout
     */
    private void StopPlay(MusicalNoteLayout musicalNoteLayout, CircleImageView music_image){
        musicalNoteLayout.start(false);
        music_image.clearAnimation();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            LogPrint.printError("返回了");
            if(videoAdapter!=null) {
                videoAdapter = null;
            }
            releaseVideo(positionTag);
            if(animation!=null) {
                animation.cancel();
            }
            finish();


            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void getListVideos(List<VideoEntity.BodyBean.ContentBean.ListBean> listBeansNew) {
        setData(true);
        stopRefreshAndLoading();
        //允许上拉加载功能
        if(video_recycler_view!=null) {
            video_recycler_view.setPullLoadEnable(true);
            //获取加载的分页总数据
            for (int i = 0; i < listBeansNew.size(); i++) {
                datas.add(listBeansNew.get(i));
            }
            if(First){
                videoAdapter.addData(datas);
                video_recycler_view.setAdapter(videoAdapter);
                First=false;
            }else{
                videoAdapter.addData(datas);
                videoAdapter.notifyDataSetChanged();
            }

        }
    }
}
