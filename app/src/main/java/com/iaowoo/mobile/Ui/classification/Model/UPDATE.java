package com.iaowoo.mobile.Ui.classification.Model;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}()
 * @date 2018/6/16
 */
public class UPDATE {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"app_android_version_desc":"更新啦","app_ios_version_incr_value":"1","app_android_version_incr_value":"1","app_ios_version_desc":"更新啦","app_ios_version":"V1.0.0","app_android_version":"V1.0.0","app_android_download_url":"https://files.shzhuoji.com/app/android/plp_baoshi_v1.0.0.apk"}}
     * tail : {"channel":null,"product":null,"system":null}
     * describe : 操作成功！
     * code : 00000000
     * costs : null
     */

    private HeadBean head;
    private BodyBean body;
    private TailBean tail;
    private String describe;
    private String code;
    private Object costs;

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public TailBean getTail() {
        return tail;
    }

    public void setTail(TailBean tail) {
        this.tail = tail;
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

    public Object getCosts() {
        return costs;
    }

    public void setCosts(Object costs) {
        this.costs = costs;
    }

    public static class HeadBean {
        /**
         * traceId : null
         * service : null
         * version : null
         * security : null
         * type : RESPONSE
         * direction : null
         */

        private Object traceId;
        private Object service;
        private Object version;
        private Object security;
        private String type;
        private Object direction;

        public Object getTraceId() {
            return traceId;
        }

        public void setTraceId(Object traceId) {
            this.traceId = traceId;
        }

        public Object getService() {
            return service;
        }

        public void setService(Object service) {
            this.service = service;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public Object getSecurity() {
            return security;
        }

        public void setSecurity(Object security) {
            this.security = security;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getDirection() {
            return direction;
        }

        public void setDirection(Object direction) {
            this.direction = direction;
        }
    }

    public static class BodyBean {
        /**
         * comment : null
         * content : {"app_android_version_desc":"更新啦","app_ios_version_incr_value":"1","app_android_version_incr_value":"1","app_ios_version_desc":"更新啦","app_ios_version":"V1.0.0","app_android_version":"V1.0.0","app_android_download_url":"https://files.shzhuoji.com/app/android/plp_baoshi_v1.0.0.apk"}
         */

        private Object comment;
        private ContentBean content;

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * app_android_version_desc : 更新啦
             * app_ios_version_incr_value : 1
             * app_android_version_incr_value : 1
             * app_ios_version_desc : 更新啦
             * app_ios_version : V1.0.0
             * app_android_version : V1.0.0
             * app_android_download_url : https://files.shzhuoji.com/app/android/plp_baoshi_v1.0.0.apk
             */

            private String app_android_version_desc;
            private String app_ios_version_incr_value;
            private String app_android_version_incr_value;
            private String app_ios_version_desc;
            private String app_ios_version;
            private String app_android_version;
            private String app_android_download_url;

            public String getApp_android_version_desc() {
                return app_android_version_desc;
            }

            public void setApp_android_version_desc(String app_android_version_desc) {
                this.app_android_version_desc = app_android_version_desc;
            }

            public String getApp_ios_version_incr_value() {
                return app_ios_version_incr_value;
            }

            public void setApp_ios_version_incr_value(String app_ios_version_incr_value) {
                this.app_ios_version_incr_value = app_ios_version_incr_value;
            }

            public String getApp_android_version_incr_value() {
                return app_android_version_incr_value;
            }

            public void setApp_android_version_incr_value(String app_android_version_incr_value) {
                this.app_android_version_incr_value = app_android_version_incr_value;
            }

            public String getApp_ios_version_desc() {
                return app_ios_version_desc;
            }

            public void setApp_ios_version_desc(String app_ios_version_desc) {
                this.app_ios_version_desc = app_ios_version_desc;
            }

            public String getApp_ios_version() {
                return app_ios_version;
            }

            public void setApp_ios_version(String app_ios_version) {
                this.app_ios_version = app_ios_version;
            }

            public String getApp_android_version() {
                return app_android_version;
            }

            public void setApp_android_version(String app_android_version) {
                this.app_android_version = app_android_version;
            }

            public String getApp_android_download_url() {
                return app_android_download_url;
            }

            public void setApp_android_download_url(String app_android_download_url) {
                this.app_android_download_url = app_android_download_url;
            }
        }
    }

    public static class TailBean {
        /**
         * channel : null
         * product : null
         * system : null
         */

        private Object channel;
        private Object product;
        private Object system;

        public Object getChannel() {
            return channel;
        }

        public void setChannel(Object channel) {
            this.channel = channel;
        }

        public Object getProduct() {
            return product;
        }

        public void setProduct(Object product) {
            this.product = product;
        }

        public Object getSystem() {
            return system;
        }

        public void setSystem(Object system) {
            this.system = system;
        }
    }
}
