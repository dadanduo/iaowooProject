package com.iaowoo.mobile.EvenBus;

/**
 * webview 交互
 */
public class EventBusMessageWebview {

    private String tag;

    private String data;

    public EventBusMessageWebview(String tag,String data) {
        this.tag = tag;
        this.data=data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}


