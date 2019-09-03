package com.iaowoo.mobile.Ui.classification.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Utils.DialogUtils;

public class TitleBarActivity extends BaseActivity {
    private RelativeLayout titleBarLayout;
    private TextView titleText;
    private RelativeLayout leftLayout;
    private RelativeLayout rightLayout;
    private TextView rightText;
    private ImageView rightImg;
    private FrameLayout mContentLayout;

    private DialogUtils dialogUtils;
    private Dialog loadingDialog;

    private TitleBarClickListener clickListener;
    private Boolean isBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        dialogUtils = new DialogUtils();
    }

    private void setupViews() {
        super.setContentView(R.layout.activity_title_bar);
        titleBarLayout = findViewById(R.id.rl_title_bar);
        titleText = findViewById(R.id.tv_title);

        leftLayout = findViewById(R.id.rl_title_bar_left);
        rightLayout = findViewById(R.id.rl_title_bar_right);
        rightText = findViewById(R.id.tv_right);
        rightImg = findViewById(R.id.iv_right);

        mContentLayout = findViewById(R.id.layout_content);

        initState(R.id.ll_bar);

        leftLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBack) {
                    finish();
                } else {
                    if (clickListener != null) {
                        clickListener.OnTitleBarClick(v.getId());
                    }
                }
            }
        });

        rightLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.OnTitleBarClick(v.getId());
                }
            }
        });
    }

    public void hidden() {
        titleBarLayout.setVisibility(View.GONE);
    }

    protected void showBackwardView(boolean show) {
        if (leftLayout != null) {
            if (show) {
                leftLayout.setVisibility(View.VISIBLE);
            } else {
                leftLayout.setVisibility(View.GONE);
            }
        }
    }

    public void setTitleTextSize(int size) {
        titleText.setTextSize(size);
    }

    protected void showRightTextView(String s) {
        if (rightLayout != null) {
            rightLayout.setVisibility(View.VISIBLE);
            rightImg.setVisibility(View.GONE);
            rightText.setVisibility(View.VISIBLE);
            rightText.setText(s);
        }
    }

    protected void setRightText(String s) {
        rightText.setText(s);
    }

    protected void showRightImgView(int resId) {
        if (rightLayout != null) {
            rightLayout.setVisibility(View.VISIBLE);
            rightText.setVisibility(View.GONE);
            rightImg.setVisibility(View.VISIBLE);
            rightImg.setImageResource(resId);
        }
    }

    protected void hideRightLayout() {
        rightLayout.setVisibility(View.GONE);
    }

    //设置标题内容
    @Override
    public void setTitle(int titleId) {
        titleText.setText(titleId);
    }

    //设置标题内容
    @Override
    public void setTitle(CharSequence title) {
        titleText.setText(title);
    }

    //设置标题文字颜色
    @Override
    public void setTitleColor(int textColor) {
        titleText.setTextColor(textColor);
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

    @Override
    public void setContentView(View view, LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }


    public void showD() {
        showD("");
    }

    public void showD(String msg) {
        loadingDialog = dialogUtils.createLoadingDialog(this, msg, true);
    }

    public void hideD() {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    protected void setTitleTarListener(TitleBarClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface TitleBarClickListener {
        void OnTitleBarClick(int id);
    }
}
