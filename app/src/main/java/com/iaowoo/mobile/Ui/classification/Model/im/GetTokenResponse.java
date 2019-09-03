package com.iaowoo.mobile.Ui.classification.Model.im;

public class GetTokenResponse {

    /**
     * body : {"content":{"code":200,"msg":null,"token":"a/FeVr3q1cm/UGraK3ZB4Ak1dezbYbCOYvoM2FmBwtkvw6jFaxgDcd09ruW9SzpbL/NdKDlj0pf+9pMBUQRTQ7uYgYLq9qdd","userId":"1000007370"}}
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
         * content : {"code":200,"msg":null,"token":"a/FeVr3q1cm/UGraK3ZB4Ak1dezbYbCOYvoM2FmBwtkvw6jFaxgDcd09ruW9SzpbL/NdKDlj0pf+9pMBUQRTQ7uYgYLq9qdd","userId":"1000007370"}
         */

        private ContentBean content;

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * code : 200
             * msg : null
             * token : a/FeVr3q1cm/UGraK3ZB4Ak1dezbYbCOYvoM2FmBwtkvw6jFaxgDcd09ruW9SzpbL/NdKDlj0pf+9pMBUQRTQ7uYgYLq9qdd
             * userId : 1000007370
             */

            private String token;
            private String userId;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
