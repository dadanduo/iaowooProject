/*******************************************************************************
 *
 * Copyright (c) Weaver Info Tech Co. Ltd
 *
 * TitleActivity
 *
 * app.client.TitleActivity.java
 * TODO: File description or class description.
 *
 * @author: chenda
 * @since:  2014-9-03
 * @version: 1.0.0
 *
 * @changeLogs:
 *     1.0.0: First created this class.
 *
 ******************************************************************************/
package com.iaowoo.mobile.Ui.classification.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;


/**
 * @author chenda
 * 自定义标题栏
 */
public class TitleActivity extends BaseActivity {

    //private RelativeLayout mLayoutTitleBar;
    private TextView mTitleTextView;
    private RelativeLayout mBackwardbButton;
    private RelativeLayout mForwardButton;
    private FrameLayout mContentLayout;
    private ClickCallBack clickCallBack;
    private RelativeLayout layout_titlebar;
    private TextView left_text;
    private View line_title;

    private Boolean isback=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();   //加载 activity_title 布局 ，并获取标题及两侧按钮
    }


    private void setupViews() {
        super.setContentView(R.layout.activity_title);
        mTitleTextView =  findViewById(R.id.text_title);
        mContentLayout =  findViewById(R.id.layout_content);
        mBackwardbButton =  findViewById(R.id.button_backward);
        mForwardButton =  findViewById(R.id.button_forward);
        layout_titlebar=findViewById(R.id.layout_titlebar);
        left_text=findViewById(R.id.left_text);
        line_title=findViewById(R.id. line_title);
        initState(R.id.ll_bar);
        mBackwardbButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackward(v);
            }
        });
        mForwardButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onForward(v);
            }
        });
    }

    /**
     * 隐藏头部
     */
    public void hidden() {
        layout_titlebar.setVisibility(View.GONE);
        line_title.setVisibility(View.GONE);
    }
    /**
     * 是否显示返回按钮
     * @param backwardResid  文字
     * @param show  true则显示
     */
    protected void showBackwardView(int backwardResid, boolean show) {
        if (mBackwardbButton != null) {
            if (show) {
                left_text.setText(backwardResid);
                mBackwardbButton.setVisibility(View.VISIBLE);
            } else {
                mBackwardbButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    public void setTitleTextSize(int size)
    {
        mTitleTextView.setTextSize(size);
    }

    /**
     * 是否显示返回按钮
     * @param
     * @param show  true则显示
     */
    protected void showBackwardView_show(boolean show) {
        if (mBackwardbButton != null) {
            if (show) {
                mBackwardbButton.setVisibility(View.VISIBLE);
            } else {
                mBackwardbButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }
    /**
     * 提供是否显示提交按钮
     * @param forwardResId  文字
     * @param show  true则显示
     */
    protected void showForwardView(String forwardResId, boolean show) {
        if (mForwardButton != null) {
            if (show) {
                mForwardButton.setVisibility(View.VISIBLE);
                left_text.setText(forwardResId);
            } else {
                mForwardButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    public void setBoreturn()
    {
        isback=false;
    }
    /**
     * 返回按钮点击后触发
     * @param backwardView
     */
    protected void onBackward(View backwardView) {
        if(isback) {
            finish();
        }else {
            clickCallBack.OnClick();
        }
    }

    /**
     * 提交按钮点击后触发
     * @param forwardView
     */
    protected void onForward(View forwardView) {
        clickCallBack.OnClick();
    }

    protected  void  setOnclick(ClickCallBack clickCallBack)
    {
        this.clickCallBack=clickCallBack;
    }

    //设置标题内容
    @Override
    public void setTitle(int titleId) {
        mTitleTextView.setText(titleId);
    }

    //设置标题内容
    @Override
    public void setTitle(CharSequence title) {
        mTitleTextView.setText(title);
    }

    //设置标题文字颜色
    @Override
    public void setTitleColor(int textColor) {
        mTitleTextView.setTextColor(textColor);
    }


    //取出FrameLayout并调用父类removeAllViews()方法
    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }
    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    @Override
    public void setContentView(View view, LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }
}
