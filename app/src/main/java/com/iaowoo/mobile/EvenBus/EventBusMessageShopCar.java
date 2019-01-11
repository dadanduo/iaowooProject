package com.iaowoo.mobile.EvenBus;

/**
 * 自定义购物车消息数量
 */
public class EventBusMessageShopCar {

    private int tag;
    public EventBusMessageShopCar(int tag){
        this.tag=tag;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
