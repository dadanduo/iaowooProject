package com.iaowoo.mobile.EvenBus;

/**
 * 自定义分类点击通知事件
 */
public class EventBusMessageClass {

    private String txt;
    private String category_id;
    public EventBusMessageClass(String  category_id,String txt){
        this.txt=txt;
        this.category_id=category_id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
