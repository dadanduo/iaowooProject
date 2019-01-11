package com.iaowoo.mobile.EvenBus;

/**
 * 自定义极光
 */
public class EventBusMessageJG {
    private String ImageHead;
    private String content;
   public EventBusMessageJG(String content,String  imageHead){
       this.ImageHead=imageHead;
       this.content=content;
    }

    public String getImageHead() {
        return ImageHead;
    }

    public void setImageHead(String imageHead) {
        ImageHead = imageHead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
