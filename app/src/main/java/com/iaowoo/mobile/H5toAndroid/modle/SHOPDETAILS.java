package com.iaowoo.mobile.H5toAndroid.modle;

import java.io.Serializable;

public class SHOPDETAILS implements Serializable {

    /**
     * merchantName : 红红的小店
     * rqCode : null
     * mainImg : https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-13/478566089677078528.jpg
     * detailAddress : 12345678
     */

    private String merchantName;
    private String  rqCode;
    private String mainImg;
    private String detailAddress;
    private String title;
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getRqCode() {
        return rqCode;
    }

    public void setRqCode(String rqCode) {
        this.rqCode = rqCode;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }


}
