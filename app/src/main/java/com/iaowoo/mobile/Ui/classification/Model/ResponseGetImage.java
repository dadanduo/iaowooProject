package com.iaowoo.mobile.Ui.classification.Model;

/**
 * Created by chenda on 2018/4/8.
 */

public class ResponseGetImage {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":"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"}
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
         * content : 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
         */

        private Object comment;
        private String content;

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
