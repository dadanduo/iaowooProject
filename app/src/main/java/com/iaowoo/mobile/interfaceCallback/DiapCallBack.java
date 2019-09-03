package com.iaowoo.mobile.interfaceCallback;


import com.iaowoo.mobile.Ui.classification.Model.NearShops;
import com.iaowoo.mobile.Ui.classification.Model.PARTPOINT;

/**
 * Created by chenda on 2018/4/18.
 */

public interface DiapCallBack {
    void show_alert();
    void hide_alert();
    void push_data_to_a(PARTPOINT partpoint);
    void push_data_to_d(NearShops nearShops);
    void showdilog();
    void showItem(NearShops.BodyBean.ContentBean.ListBean listBean);
}
