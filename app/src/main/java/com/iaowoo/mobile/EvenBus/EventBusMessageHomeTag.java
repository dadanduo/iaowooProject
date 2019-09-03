package com.iaowoo.mobile.EvenBus;

/**
 * 自定义首页消息
 */
public class EventBusMessageHomeTag {

    private String tag;

    public EventBusMessageHomeTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}


