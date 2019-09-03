package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.J_push.J_edit;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.J_push.J_edit;
import com.iaowoo.mobile.Utils.LogPrint;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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
 * @Description: ${todo}(父控件)
 * @date 2018/8/27
 * @email ${18011009889@163.com}
 */
public abstract class BaseBufferActivity extends BaseActivity {
    protected View rootView;
    protected Context mContext;
    protected Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        int layoutId = getLayoutResId();
        //初始化butterknife
        if (layoutId > 0) {
            rootView = LayoutInflater.from(mContext).inflate(layoutId, null);
            setContentView(rootView);
            unbinder = ButterKnife.bind(this);
        }
        String msg= PrefManager.getInstance().getJGMsm();
        LogPrint.printError(msg);
        if (!TextUtils.isEmpty(msg)&&msg.length()>30) {
            LogPrint.printError("有极光消息需要处理");
            J_edit j_edit=new J_edit();
            j_edit.setCostomMsg1(msg,this);
        }
        initView();
        initData();

    }
    public abstract int getLayoutResId();


    protected void initView() {
    }
    protected   void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getLayoutResId() > 0 && unbinder != null) {
            unbinder.unbind();
        }
    }
}
