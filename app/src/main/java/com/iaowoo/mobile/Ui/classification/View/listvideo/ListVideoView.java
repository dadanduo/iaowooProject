package com.iaowoo.mobile.Ui.classification.View.listvideo;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;

import com.danikula.videocache.HttpProxyCacheServer;

import java.io.IOException;
import java.lang.ref.SoftReference;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * @author ArcherYc
 * @date on 2018/4/24  下午3:14
 * @mail 247067345@qq.com
 */
public class ListVideoView extends FrameLayout implements TextureView.SurfaceTextureListener, IMediaPlayer.OnPreparedListener {

    private Context mContext;
    private TextureView mTextureView;
    private IjkMediaPlayer mMediaPlayer;
    /**
     * 视频缓存代理
     */
    private SoftReference<HttpProxyCacheServer> mProxyCacheServer;

    private Surface mSurface;


    public ListVideoView(@NonNull Context context) {
        this(context, null);
    }

    public ListVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        mTextureView = new TextureView(mContext);
        mTextureView.setSurfaceTextureListener(this);
        mProxyCacheServer =new SoftReference(new HttpProxyCacheServer(mContext));
        addView(mTextureView);
    }

    private void createPlayer() {
        mMediaPlayer = new IjkMediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        if (mSurface != null) {
            mMediaPlayer.setSurface(mSurface);
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        mSurface = new Surface(surfaceTexture);
        if (mMediaPlayer != null) {
            mMediaPlayer.setSurface(mSurface);
            adjustVideoSize(mTextureView, getMediaPlayer().getVideoWidth(), getMediaPlayer().getVideoHeight());
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        mSurface = null;
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        if (mMediaPlayer != null) {
            adjustVideoSize(mTextureView, mMediaPlayer.getVideoWidth(), mMediaPlayer.getVideoHeight());
            mMediaPlayer.start();
        }
    }

    /**
     * 设置播放地址。同时创建一个新的player实例
     *
     * @param url
     */
    public void setVideoPath(String url) {
        try {
            createPlayer();
            if (!url.contains("http")) {
                mMediaPlayer.setDataSource(url);
            } else {
                if(mProxyCacheServer.get()!=null) {
                    mMediaPlayer.setDataSource(mProxyCacheServer.get().getProxyUrl(url));
                }else{
                    mProxyCacheServer =new SoftReference(new HttpProxyCacheServer(mContext));
                    mMediaPlayer.setDataSource(mProxyCacheServer.get().getProxyUrl(url));
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置是否循环播放
     *
     * @param loop
     */
    public void setLooping(boolean loop) {
        if (mMediaPlayer != null) {
            mMediaPlayer.setLooping(loop);
        }
    }


    /**
     * 判断是否在播放
     *
     * @return
     */
    public boolean isPlaying() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.isPlaying();
        } else {
            return false;
        }
    }


    /**
     * prepare后在回调中调用start开始播放
     */
    public void prepareAsync() {
        if (mSurface != null) {
            mMediaPlayer.setSurface(mSurface);
        }
        mMediaPlayer.prepareAsync();
    }


    /**
     * 开始播放。注意，这是在pause后调用的，直接调用不会播放。
     * 真正的播放要调用{@link ListVideoView#prepareAsync()}
     */
    public void start() {
        if (mMediaPlayer != null) {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.start();
            }
        }
    }

    /**
     * 暂停播放
     */
    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }


    /**
     * 停止播放时直接释放资源，在设置播放地址时再初始化
     */
    public void stopPlayback() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;

        }
    }

    /**
     * 释放播放器资源
     */
    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
    }

    public void onDestoryGG(){
        if(mMediaPlayer!=null){
            mMediaPlayer.stop();
        }
    }

    /**
     * 重置播放器
     */
    public void reset() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
        }
    }

    public IjkMediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    /**
     * 根据屏幕大小和视频大小，适配。如果是竖屏的视频，统一填充满屏幕。不是竖屏的视频，就居中播放。
     *
     * @param view
     * @param videoWidth
     * @param videoHeight
     */
    private void adjustVideoSize(View view, int videoWidth, int videoHeight) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        if (getMeasuredWidth() > 0 && getMeasuredHeight() > 0
                && videoWidth > 0 && videoHeight > 0
                ) {
            Pair<Integer, Integer> fitSize =getFitSize(
                    new Pair<>(getMeasuredWidth(), getMeasuredHeight()),
                    new Pair<>(videoWidth, videoHeight)
            );
            lp.gravity = Gravity.CENTER;
            lp.width = fitSize.first;
            lp.height = fitSize.second;
            view.setLayoutParams(lp);
        }
    }


    private float RATIO_FIT_CENTER = 1.6f;

    public enum ScaleType {
        /**
         * 对应于ImageView FIT_CENTER
         */
        FIT_CENTER,
        /**
         * 对应于ImageView CENTER_CROP
         */
        CENTER_CROP
    }

    public  ScaleType getImageCropType(Pair<Integer, Integer> layoutSize,
                                       Pair<Integer, Integer> videoSize) {
        int layoutWidth = layoutSize.first;
        int layoutHeight = layoutSize.second;
        int videoWidth = videoSize.first;
        int videoHeight = videoSize.second;

        float layoutAspectRatio = (float) layoutHeight / (float) layoutWidth;
        float videoAspectRatio = (float) videoHeight / (float) videoWidth;

        if (layoutAspectRatio / videoAspectRatio >= RATIO_FIT_CENTER) {
            return ScaleType.FIT_CENTER;
        } else {
            return ScaleType.CENTER_CROP;
        }
    }


    public  Pair<Integer, Integer> getFitSize(Pair<Integer, Integer> layoutSize,
                                              Pair<Integer, Integer> videoSize) {

        int layoutWidth = layoutSize.first;
        int layoutHeight = layoutSize.second;
        int videoWidth = videoSize.first;
        int videoHeight = videoSize.second;

        float layoutAspectRatio = (float) layoutHeight / (float) layoutWidth;
        float videoAspectRatio = (float) videoHeight / (float) videoWidth;
//
//        if (layoutAspectRatio > videoAspectRatio || (videoAspectRatio / layoutAspectRatio < 1.6)) {
//            return new Pair<>(layoutWidth, layoutHeight);
//        } else {
//            int fitWidth = layoutWidth;
//            int fitHeight = (int) (layoutWidth / videoAspectRatio);
//            return new Pair<>(fitWidth, fitHeight);
//        }

        if (layoutAspectRatio >= videoAspectRatio && (layoutAspectRatio / videoAspectRatio < RATIO_FIT_CENTER)) {
            int fitHeight = layoutHeight;
            int fitWidth = (int) (fitHeight / videoAspectRatio);
            return new Pair<>(fitWidth, fitHeight);
        } else {
            int fitWidth = layoutWidth;
            int fitHeight = (int) (layoutWidth * videoAspectRatio);
            return new Pair<>(fitWidth, fitHeight);
        }
    }

}
