package com.iaowoo.mobile.EvenBus;

/**
 * 自定义刷新
 */
public class EventBusMessageRefresh {

    private int tag;
    public EventBusMessageRefresh(int tag){
        this.tag=tag;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
