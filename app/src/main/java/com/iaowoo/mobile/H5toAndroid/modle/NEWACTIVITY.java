package com.iaowoo.mobile.H5toAndroid.modle;

import java.io.Serializable;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}(新人活动实体)
 * @date 2018/6/7
 */
public class NEWACTIVITY  implements  Serializable{

    /**
     * titile : dd
     * url :
     */

    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
