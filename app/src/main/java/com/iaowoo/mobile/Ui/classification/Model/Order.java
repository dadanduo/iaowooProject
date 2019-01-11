package com.iaowoo.mobile.Ui.classification.Model;

/**
 * Created by chenda on 2018/4/10.
 */

public class Order {


    /**
     * userIdString : da
     * bodyString : da
     * totPriceString  : da
     */

    private String userId;
    private String body;
    private String totPrice;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTotPrice() {
        return totPrice;
    }

    public void setTotPrice(String totPrice) {
        this.totPrice = totPrice;
    }
}
