package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Utils.ScreenAdaptationUtils;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Utils.ScreenAdaptationUtils;

import java.lang.ref.SoftReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 闪屏页
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.activity_splash)
    FrameLayout layoutSplash;
    @BindView(R.id.splash)
    ImageView splash;
    private AlphaAnimation alphaAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomehome);
        ButterKnife.bind(this);
        SoftReference<ScreenAdaptationUtils> screenAdaptationUtilsSoftReference=new SoftReference<>(new ScreenAdaptationUtils());
        //特殊机型屏幕适配
        if(screenAdaptationUtilsSoftReference.get()!=null){
            screenAdaptationUtilsSoftReference.get().screenAdapterDealWith(splash,SingleOverAll.getInstance().deviceHeight(ZApplication.getContext()),4);
        }
        //全屏
        allState();
        alphaAnimation=new AlphaAnimation(0.5f,1f);
        alphaAnimation.setDuration(500);//设置动画播放时长1000毫秒（1秒）
        layoutSplash.startAnimation(alphaAnimation);
        //设置动画监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            //动画结束
            @Override
            public void onAnimationEnd(Animation animation) {
                //页面的跳转
                startActivity(new Intent(SplashActivity.this,HomePageActivity.class));
                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        alphaAnimation=null;
    }
}
