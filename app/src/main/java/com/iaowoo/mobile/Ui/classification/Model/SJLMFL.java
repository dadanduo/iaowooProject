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
 * @Description: ${todo}(商家联盟门店分类)
 * @date 2018/9/11
 * @email ${18011009889@163.com}
 */
public class SJLMFL {


    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":[{"id":1,"createTime":"2018-04-16 20:31:40","updateTime":"2018-04-16 20:31:40","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"code":"1","name":"美食","parentCode":"0"},{"id":38,"createTime":"2018-08-16 22:06:18","updateTime":"2018-08-16 22:06:18","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"code":"135","name":"服饰","parentCode":"0"},{"id":39,"createTime":"2018-08-16 22:07:00","updateTime":"2018-08-16 22:07:00","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"code":"136","name":" 休闲娱乐","parentCode":"0"},{"id":40,"createTime":"2018-08-16 22:07:52","updateTime":"2018-08-16 22:07:52","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"code":"137","name":"生活服务","parentCode":"0"},{"id":41,"createTime":"2018-08-16 22:08:30","updateTime":"2018-08-16 22:08:30","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"code":"138","name":"亲子教育","parentCode":"0"}]}
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
         * content : [{"id":1,"createTime":"2018-04-16 20:31:40","updateTime":"2018-04-16 20:31:40","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"code":"1","name":"美食","parentCode":"0"},{"id":38,"createTime":"2018-08-16 22:06:18","updateTime":"2018-08-16 22:06:18","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"code":"135","name":"服饰","parentCode":"0"},{"id":39,"createTime":"2018-08-16 22:07:00","updateTime":"2018-08-16 22:07:00","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"code":"136","name":" 休闲娱乐","parentCode":"0"},{"id":40,"createTime":"2018-08-16 22:07:52","updateTime":"2018-08-16 22:07:52","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"code":"137","name":"生活服务","parentCode":"0"},{"id":41,"createTime":"2018-08-16 22:08:30","updateTime":"2018-08-16 22:08:30","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"code":"138","name":"亲子教育","parentCode":"0"}]
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
             * id : 1
             * createTime : 2018-04-16 20:31:40
             * updateTime : 2018-04-16 20:31:40
             * logicDelete : 0
             * pageNum : null
             * pageSize : null
             * operator : null
             * code : 1
             * name : 美食
             * parentCode : 0
             */

            private int id;
            private String createTime;
            private String updateTime;
            private int logicDelete;
            private Object pageNum;
            private Object pageSize;
            private Object operator;
            private String code;
            private String name;
            private String parentCode;

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

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParentCode() {
                return parentCode;
            }

            public void setParentCode(String parentCode) {
                this.parentCode = parentCode;
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
