package com.iaowoo.mobile.Ui.classification.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Utils.UtilsAll;

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
 * @Description: ${todo}(fragment的基类)
 * @date 2018/9/3
 * @email ${18011009889@163.com}
 */
public abstract class BaseBufferFragment  extends Fragment {
    private Unbinder unbinder;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutResId();
        View view = inflater.inflate(layoutId, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * 初始化layout
     * @return
     */
    public abstract int getLayoutResId();

    protected void initView() {

    }
    protected   void initData() {
    }

    /**
     * 动态设置标题栏距离顶部高度
     * @param viewMarginTop
     */
    public void setViewMarginTop(View viewMarginTop){
        //获取状态栏的高度
        int height= SingleOverAll.getInstance().getStatusBarHeight1(ZApplication.getContext());
        //动态设置高度
        UtilsAll.setMargins(viewMarginTop,0,height,0,0);
    }
}
