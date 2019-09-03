package com.iaowoo.mobile.Ui.classification.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaowoo.mobile.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 关于我们
 */
public class AboutOurActivity extends TitleActivity {

    @BindView(R.id.tv_version)
    TextView tv_version;
    @BindView(R.id.show_Imag)
    ImageView show_Imag;
    @BindView(R.id.show_button)
    Button show_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_ours);
        setTitle("关于我们");
        showBackwardView_show(true);
        ButterKnife.bind(this);
        init();
    }
    public void init() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(pkName, 0).versionName;
            tv_version.setText("当前版本"+versionName);
        } catch (Exception e) {
        }
    }
}
