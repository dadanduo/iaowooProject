package com.iaowoo.mobile.EvenBus;

/**
 * weex调用方法
 */
public class EventBusMessageWeex {

    private String tagName;

    private String msg;

    public EventBusMessageWeex(String tagName,String msg) {
        this.tagName =tagName;
        this.msg=msg;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}


