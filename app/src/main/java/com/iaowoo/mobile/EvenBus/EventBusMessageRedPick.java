package com.iaowoo.mobile.EvenBus;

/**
 * 自定义小红点更新数量
 */
public class EventBusMessageRedPick {

    private int tag;
    public EventBusMessageRedPick(int tag){
        this.tag=tag;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
