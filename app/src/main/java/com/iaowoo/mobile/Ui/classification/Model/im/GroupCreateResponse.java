package com.iaowoo.mobile.Ui.classification.Model.im;

public class GroupCreateResponse {
    /**
     * body : {"content":null}
     * describe : 操作成功！
     * code : 00000000
     */

    private BodyBean body;
    private String describe;
    private String code;

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class BodyBean {
        /**
         * content : null
         */

        private Object content;

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }
    }
}
