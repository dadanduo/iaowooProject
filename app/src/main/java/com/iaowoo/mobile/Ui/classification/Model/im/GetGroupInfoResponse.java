package com.iaowoo.mobile.Ui.classification.Model.im;

public class GetGroupInfoResponse {
    private GetUserInfoResponse.BodyBean body;
    private String describe;
    private String code;

    public GetUserInfoResponse.BodyBean getBody() {
        return body;
    }

    public void setBody(GetUserInfoResponse.BodyBean body) {
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

        private GetUserInfoResponse.BodyBean.ContentBean content;

        public GetUserInfoResponse.BodyBean.ContentBean getContent() {
            return content;
        }

        public void setContent(GetUserInfoResponse.BodyBean.ContentBean content) {
            this.content = content;
        }

        public static class ContentBean {
            private String groupId;
            private String name;
            private String profile;
            private String portrait;

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProfile() {
                return profile;
            }

            public void setProfile(String profile) {
                this.profile = profile;
            }

            public String getPortrait() {
                return portrait;
            }

            public void setPortrait(String portrait) {
                this.portrait = portrait;
            }
        }
    }
}
