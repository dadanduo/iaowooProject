package com.iaowoo.mobile.H5toAndroid.modle;

import com.google.gson.annotations.SerializedName;

public class WECHATEPAY {

    /**
     * appid : wx6423c6d6a26d28b1
     * mwebUrl : null
     * noncestr : x4j9iyvn9o85tsoqpgw9o7xzmcbm48jl
     * package : Sign=WXPay
     * partnerid : 1503420071
     * prepayid : wx142011326079785f60dccd503340336839
     * sign : AB0C3188CAB3478E3DD4DE04E84236D3
     * timestamp : 1526299891
     */

    private String appid;
    private Object mwebUrl;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String sign;
    private String timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Object getMwebUrl() {
        return mwebUrl;
    }

    public void setMwebUrl(Object mwebUrl) {
        this.mwebUrl = mwebUrl;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
