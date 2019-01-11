package com.iaowoo.mobile.Weex.extend.Model;

/**
 * ////////////////////////
 * //  ┏┓　　　┏┓///////////
 * //┏┛┻━━━┛┻┓ ////////////
 * //┃　　　　　　　┃     ////
 * //┃　　　━　　　┃     ////
 * //┃　┳┛　┗┳　┃       /////
 * //┃　　　　　　　┃     ////
 * //┃　　　┻　　　┃         //
 * //┃　　　　　　　┃        ///
 * //┗━┓　　　┏━┛           ///
 * //    ┃　　　┃   神兽保佑  ///
 * //    ┃　　　┃   代码无BUG！///
 * //    ┃　　　┗━━━┓     ///
 * //    ┃　　　　　　　┣┓ ///
 * //    ┃　　　　　　　┏┛ ///
 * //    ┗┓┓┏━┳┓┏┛      ///
 * //      ┃┫┫　┃┫┫     ///
 * ///////////////////////
 *
 * @author ${chenda}
 * @version V1.0
 * @Description: ${todo}(时间选择器)
 * @date 2018/12/13
 * @email ${18011009889@163.com}
 */
public class date {
    //选中的值
    private String  value;
    //可选，default, yearMonth, yearMonthDay, yearMonthDayHour
    private String  dateType;
    //可选，日期最大值,默认2099-12-31 23:59
    private String  max;
    //可选，日期最小值,默认1900-12-31 00:00
    private String  min;
    //可选，标题的文案，默认为空
    private String title;
    //可选，默认为空,title不为空时有效，颜色值（#313131）
    private String titleColor;
    //确认按钮的文案,默认值（完成）
    private String  confirmTitle;
    //确认按钮的文字颜色，默认值(#00b4ff)
    private String confirmTitleColor;
    //取消按钮的文案,默认值（取消）
    private String cancelTitlel;
    //取消按钮的文字颜色,默认值(#313131)
    private String  cancelTitleColor;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getConfirmTitle() {
        return confirmTitle;
    }

    public void setConfirmTitle(String confirmTitle) {
        this.confirmTitle = confirmTitle;
    }

    public String getConfirmTitleColor() {
        return confirmTitleColor;
    }

    public void setConfirmTitleColor(String confirmTitleColor) {
        this.confirmTitleColor = confirmTitleColor;
    }

    public String getCancelTitlel() {
        return cancelTitlel;
    }

    public void setCancelTitlel(String cancelTitlel) {
        this.cancelTitlel = cancelTitlel;
    }

    public String getCancelTitleColor() {
        return cancelTitleColor;
    }

    public void setCancelTitleColor(String cancelTitleColor) {
        this.cancelTitleColor = cancelTitleColor;
    }
}
