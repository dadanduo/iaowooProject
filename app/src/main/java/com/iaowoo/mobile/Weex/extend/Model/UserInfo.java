package com.iaowoo.mobile.Weex.extend.Model;

import com.iaowoo.mobile.Ui.classification.Model.MYMESSAGE;

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
 * @Description: ${todo}(用户信息modle)
 * @date 2018/12/11
 * @email ${18011009889@163.com}
 */
public class UserInfo {

    /**
     * data : >>>>>
     */
    private String result;

    private MYMESSAGE.BodyBean.ContentBean data;



    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public MYMESSAGE.BodyBean.ContentBean getData() {
        return data;
    }

    public void setData(MYMESSAGE.BodyBean.ContentBean data) {
        this.data = data;
    }
}
