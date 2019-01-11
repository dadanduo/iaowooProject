package com.iaowoo.mobile.interfaceCallback;

/**
 * Created by chenda on 2018/4/16.
 */
public interface ReCallBack<T> {
    /**
     * 响应成功
     */
    void onReqSuccess(T result, int tag);

    /**
     * 响应失败
     */
    void onReqFailed(String errorMsg);
}