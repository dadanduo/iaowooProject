package com.iaowoo.mobile.Ui.classification.Model;

import java.util.List;

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
 * @Description: ${todo}(是否含有新人红包)
 * @date 2018/9/19
 * @email ${18011009889@163.com}
 */
public class ISHAVERED {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":[{"category":1,"bannerId":null,"bannerImg":null,"jumpUrl":null,"sort":null,"type":null,"bannerDescribe":null,"businessType":null,"location":null,"startTime":null,"endTime":null,"businessId":null,"createUser":null,"updateUser":null,"title":null}]}
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
         * content : [{"category":1,"bannerId":null,"bannerImg":null,"jumpUrl":null,"sort":null,"type":null,"bannerDescribe":null,"businessType":null,"location":null,"startTime":null,"endTime":null,"businessId":null,"createUser":null,"updateUser":null,"title":null}]
         */

        private Object comment;
        private List<ContentBean> content;

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * category : 1
             * bannerId : null
             * bannerImg : null
             * jumpUrl : null
             * sort : null
             * type : null
             * bannerDescribe : null
             * businessType : null
             * location : null
             * startTime : null
             * endTime : null
             * businessId : null
             * createUser : null
             * updateUser : null
             * title : null
             */

            private int category;
            private Object bannerId;
            private Object bannerImg;
            private Object jumpUrl;
            private Object sort;
            private Object type;
            private Object bannerDescribe;
            private Object businessType;
            private Object location;
            private Object startTime;
            private Object endTime;
            private Object businessId;
            private Object createUser;
            private Object updateUser;
            private Object title;

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public Object getBannerId() {
                return bannerId;
            }

            public void setBannerId(Object bannerId) {
                this.bannerId = bannerId;
            }

            public Object getBannerImg() {
                return bannerImg;
            }

            public void setBannerImg(Object bannerImg) {
                this.bannerImg = bannerImg;
            }

            public Object getJumpUrl() {
                return jumpUrl;
            }

            public void setJumpUrl(Object jumpUrl) {
                this.jumpUrl = jumpUrl;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getBannerDescribe() {
                return bannerDescribe;
            }

            public void setBannerDescribe(Object bannerDescribe) {
                this.bannerDescribe = bannerDescribe;
            }

            public Object getBusinessType() {
                return businessType;
            }

            public void setBusinessType(Object businessType) {
                this.businessType = businessType;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }

            public Object getStartTime() {
                return startTime;
            }

            public void setStartTime(Object startTime) {
                this.startTime = startTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public Object getBusinessId() {
                return businessId;
            }

            public void setBusinessId(Object businessId) {
                this.businessId = businessId;
            }

            public Object getCreateUser() {
                return createUser;
            }

            public void setCreateUser(Object createUser) {
                this.createUser = createUser;
            }

            public Object getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(Object updateUser) {
                this.updateUser = updateUser;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
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
