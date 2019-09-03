package com.iaowoo.mobile.Ui.classification.Model;

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
 * @Description: ${todo}(评论成功返回实体类)
 * @date 2018/11/13
 * @email ${18011009889@163.com}
 */
public class CommentsOk {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"id":47,"createTime":"2018-11-13 14:23:50","updateTime":"2018-11-13 14:23:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"commentId":"511909061000691712","parentCommentId":"0","userId":269394,"videoId":"509313843961266176","content":"萨瓦迪卡","status":0,"praiseNum":0,"treadNum":0,"createUser":"269394","updateUser":"269394","nickname":"*达","headImgUrl":null,"praiseStatus":null}}
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
         * content : {"id":47,"createTime":"2018-11-13 14:23:50","updateTime":"2018-11-13 14:23:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"commentId":"511909061000691712","parentCommentId":"0","userId":269394,"videoId":"509313843961266176","content":"萨瓦迪卡","status":0,"praiseNum":0,"treadNum":0,"createUser":"269394","updateUser":"269394","nickname":"*达","headImgUrl":null,"praiseStatus":null}
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
             * id : 47
             * createTime : 2018-11-13 14:23:50
             * updateTime : 2018-11-13 14:23:50
             * logicDelete : 0
             * pageNum : null
             * pageSize : null
             * operator : null
             * commentId : 511909061000691712
             * parentCommentId : 0
             * userId : 269394
             * videoId : 509313843961266176
             * content : 萨瓦迪卡
             * status : 0
             * praiseNum : 0
             * treadNum : 0
             * createUser : 269394
             * updateUser : 269394
             * nickname : *达
             * headImgUrl : null
             * praiseStatus : null
             */

            private int id;
            private String createTime;
            private String updateTime;
            private int logicDelete;
            private Object pageNum;
            private Object pageSize;
            private Object operator;
            private String commentId;
            private String parentCommentId;
            private int userId;
            private String videoId;
            private String content;
            private int status;
            private int praiseNum;
            private int treadNum;
            private String createUser;
            private String updateUser;
            private String nickname;
            private String headImgUrl;
            private Object praiseStatus;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getLogicDelete() {
                return logicDelete;
            }

            public void setLogicDelete(int logicDelete) {
                this.logicDelete = logicDelete;
            }

            public Object getPageNum() {
                return pageNum;
            }

            public void setPageNum(Object pageNum) {
                this.pageNum = pageNum;
            }

            public Object getPageSize() {
                return pageSize;
            }

            public void setPageSize(Object pageSize) {
                this.pageSize = pageSize;
            }

            public Object getOperator() {
                return operator;
            }

            public void setOperator(Object operator) {
                this.operator = operator;
            }

            public String getCommentId() {
                return commentId;
            }

            public void setCommentId(String commentId) {
                this.commentId = commentId;
            }

            public String getParentCommentId() {
                return parentCommentId;
            }

            public void setParentCommentId(String parentCommentId) {
                this.parentCommentId = parentCommentId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getTreadNum() {
                return treadNum;
            }

            public void setTreadNum(int treadNum) {
                this.treadNum = treadNum;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String  getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
            }

            public Object getPraiseStatus() {
                return praiseStatus;
            }

            public void setPraiseStatus(Object praiseStatus) {
                this.praiseStatus = praiseStatus;
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
