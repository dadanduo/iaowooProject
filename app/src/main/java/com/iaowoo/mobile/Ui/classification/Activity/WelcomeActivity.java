package com.iaowoo.mobile.Ui.classification.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.StartPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 引导页
 */
public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private StartPageAdapter startPageAdapter;
    private int[] layouts;
    View view;
    /**
     * 第一次不执行
     */
    private boolean next=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在setContentView()前检查是否第一次运行
        setContentView(R.layout.guide_page);
        ButterKnife.bind(this);
        //让状态栏透明
        this.allState();
        if(!PrefManager.getInstance().getFirstLaunch()){
            //第二次启动直接跳闪屏页面
            nolaunchHomeScreen();
        }else{
            //初始化默认城市（只会执行一次）
            PrefManager.getInstance().savecityName("北京市");
        }
        //添加欢迎页面
        layouts = new int[]{
                R.layout.welcomeone,
                R.layout.welcometwo,
                R.layout.welcomethree,
//                R.layout.welcomefour,
        };
        startPageAdapter =new StartPageAdapter(this, layouts, new StartPageAdapter.StartCallBack() {
            @Override
            public void launchHomeScreen() {
                launchHomeScreen_Go();
            }
        });
        viewPager.setAdapter(startPageAdapter);
    }
    /**
     * 第一次进入跳主页面
     */
    private void launchHomeScreen_Go(){
        PrefManager.getInstance().setFirstLaunch(false);
        this.startActivity(HomePageActivity.class);
        finish();
    }
    /**
     * 跳闪屏页面
     */
    private void nolaunchHomeScreen() {
        PrefManager.getInstance().setFirstLaunch(false);
        this.startActivity(SplashActivity.class);
        finish();
    }
}
