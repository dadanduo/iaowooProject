package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.os.Bundle;

import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;

public class GroupNameModifyActivity extends TitleBarActivity implements TitleBarActivity.TitleBarClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_group_name_modify);
        setTitle("修改群昵称");

        showRightTextView("完成");
        setTitleTarListener(this);
    }

    @Override
    public void OnTitleBarClick(int id) {
        switch (id) {
            case R.id.rl_title_bar_right:
                break;
        }
    }
}
