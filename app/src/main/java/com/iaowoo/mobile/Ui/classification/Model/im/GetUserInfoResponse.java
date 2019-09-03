package com.iaowoo.mobile.Ui.classification.Model.im;

public class GetUserInfoResponse {

    /**
     * body : {"content":{"userId":1000007370,"name":null,"nickname":"175******64","wechatNickname":null,"mobileNo":"17521636264","wechatHeadImgUrl":null,"headImgUrl":null,"gender":0,"isCityProxy":0,"registerTime":"2018-11-19 10:44:42"}}
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
         * content : {"userId":1000007370,"name":null,"nickname":"175******64","wechatNickname":null,"mobileNo":"17521636264","wechatHeadImgUrl":null,"headImgUrl":null,"gender":0,"isCityProxy":0,"registerTime":"2018-11-19 10:44:42"}
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
             * userId : 1000007370
             * name : null
             * nickname : 175******64
             * wechatNickname : null
             * mobileNo : 17521636264
             * wechatHeadImgUrl : null
             * headImgUrl : null
             * gender : 0
             * isCityProxy : 0
             * registerTime : 2018-11-19 10:44:42
             */

            private int userId;
            private String name;
            private String nickname;
            private String wechatNickname;
            private String mobileNo;
            private String wechatHeadImgUrl;
            private String headImgUrl;
            private int gender;
            private int isCityProxy;
            private String registerTime;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public Object getWechatNickname() {
                return wechatNickname;
            }

            public void setWechatNickname(String wechatNickname) {
                this.wechatNickname = wechatNickname;
            }

            public String getMobileNo() {
                return mobileNo;
            }

            public void setMobileNo(String mobileNo) {
                this.mobileNo = mobileNo;
            }

            public String getWechatHeadImgUrl() {
                return wechatHeadImgUrl;
            }

            public void setWechatHeadImgUrl(String wechatHeadImgUrl) {
                this.wechatHeadImgUrl = wechatHeadImgUrl;
            }

            public String getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getIsCityProxy() {
                return isCityProxy;
            }

            public void setIsCityProxy(int isCityProxy) {
                this.isCityProxy = isCityProxy;
            }

            public String getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(String registerTime) {
                this.registerTime = registerTime;
            }
        }
    }
}
