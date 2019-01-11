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
 * @Description: ${todo}(热门推荐返回实体)
 * @date 2019/1/9
 * @email ${18011009889@163.com}
 */
public class ReMenResponse {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"total":2,"list":[{"id":848,"createTime":"2019-01-07 19:41:11","updateTime":"2019-01-07 19:41:11","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"templateId":"SH_530767000213913600","activityId":"531920073812606976","templateActivityDescribe":"1111","activityImageUrl":"https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/images/bannerPictureImg/2019-01-07/531920249633636352.png","orderBy":1},{"id":849,"createTime":"2019-01-07 19:41:30","updateTime":"2019-01-07 19:41:30","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"templateId":"SH_531124228104126464","activityId":"531920073812606976","templateActivityDescribe":"11111","activityImageUrl":"https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/images/bannerPictureImg/2019-01-07/531920332626329600.png","orderBy":2}],"pageNum":1,"pageSize":20,"size":2,"startRow":1,"endRow":2,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
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
         * content : {"total":2,"list":[{"id":848,"createTime":"2019-01-07 19:41:11","updateTime":"2019-01-07 19:41:11","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"templateId":"SH_530767000213913600","activityId":"531920073812606976","templateActivityDescribe":"1111","activityImageUrl":"https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/images/bannerPictureImg/2019-01-07/531920249633636352.png","orderBy":1},{"id":849,"createTime":"2019-01-07 19:41:30","updateTime":"2019-01-07 19:41:30","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"templateId":"SH_531124228104126464","activityId":"531920073812606976","templateActivityDescribe":"11111","activityImageUrl":"https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/images/bannerPictureImg/2019-01-07/531920332626329600.png","orderBy":2}],"pageNum":1,"pageSize":20,"size":2,"startRow":1,"endRow":2,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
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
             * total : 2
             * list : [{"id":848,"createTime":"2019-01-07 19:41:11","updateTime":"2019-01-07 19:41:11","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"templateId":"SH_530767000213913600","activityId":"531920073812606976","templateActivityDescribe":"1111","activityImageUrl":"https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/images/bannerPictureImg/2019-01-07/531920249633636352.png","orderBy":1},{"id":849,"createTime":"2019-01-07 19:41:30","updateTime":"2019-01-07 19:41:30","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"templateId":"SH_531124228104126464","activityId":"531920073812606976","templateActivityDescribe":"11111","activityImageUrl":"https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/images/bannerPictureImg/2019-01-07/531920332626329600.png","orderBy":2}]
             * pageNum : 1
             * pageSize : 20
             * size : 2
             * startRow : 1
             * endRow : 2
             * pages : 1
             * prePage : 0
             * nextPage : 0
             * isFirstPage : true
             * isLastPage : true
             * hasPreviousPage : false
             * hasNextPage : false
             * navigatePages : 8
             * navigatepageNums : [1]
             * navigateFirstPage : 1
             * navigateLastPage : 1
             * firstPage : 1
             * lastPage : 1
             */

            private int total;
            private int pageNum;
            private int pageSize;
            private int size;
            private int startRow;
            private int endRow;
            private int pages;
            private int prePage;
            private int nextPage;
            private boolean isFirstPage;
            private boolean isLastPage;
            private boolean hasPreviousPage;
            private boolean hasNextPage;
            private int navigatePages;
            private int navigateFirstPage;
            private int navigateLastPage;
            private int firstPage;
            private int lastPage;
            private List<ListBean> list;
            private List<Integer> navigatepageNums;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getStartRow() {
                return startRow;
            }

            public void setStartRow(int startRow) {
                this.startRow = startRow;
            }

            public int getEndRow() {
                return endRow;
            }

            public void setEndRow(int endRow) {
                this.endRow = endRow;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getPrePage() {
                return prePage;
            }

            public void setPrePage(int prePage) {
                this.prePage = prePage;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public boolean isIsFirstPage() {
                return isFirstPage;
            }

            public void setIsFirstPage(boolean isFirstPage) {
                this.isFirstPage = isFirstPage;
            }

            public boolean isIsLastPage() {
                return isLastPage;
            }

            public void setIsLastPage(boolean isLastPage) {
                this.isLastPage = isLastPage;
            }

            public boolean isHasPreviousPage() {
                return hasPreviousPage;
            }

            public void setHasPreviousPage(boolean hasPreviousPage) {
                this.hasPreviousPage = hasPreviousPage;
            }

            public boolean isHasNextPage() {
                return hasNextPage;
            }

            public void setHasNextPage(boolean hasNextPage) {
                this.hasNextPage = hasNextPage;
            }

            public int getNavigatePages() {
                return navigatePages;
            }

            public void setNavigatePages(int navigatePages) {
                this.navigatePages = navigatePages;
            }

            public int getNavigateFirstPage() {
                return navigateFirstPage;
            }

            public void setNavigateFirstPage(int navigateFirstPage) {
                this.navigateFirstPage = navigateFirstPage;
            }

            public int getNavigateLastPage() {
                return navigateLastPage;
            }

            public void setNavigateLastPage(int navigateLastPage) {
                this.navigateLastPage = navigateLastPage;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
            }

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public List<Integer> getNavigatepageNums() {
                return navigatepageNums;
            }

            public void setNavigatepageNums(List<Integer> navigatepageNums) {
                this.navigatepageNums = navigatepageNums;
            }

            public static class ListBean {
                /**
                 * id : 848
                 * createTime : 2019-01-07 19:41:11
                 * updateTime : 2019-01-07 19:41:11
                 * logicDelete : 0
                 * pageNum : null
                 * pageSize : null
                 * operator : null
                 * templateId : SH_530767000213913600
                 * activityId : 531920073812606976
                 * templateActivityDescribe : 1111
                 * activityImageUrl : https://ihw-files-test.oss-cn-hangzhou.aliyuncs.com/images/bannerPictureImg/2019-01-07/531920249633636352.png
                 * orderBy : 1
                 */

                private int id;
                private String createTime;
                private String updateTime;
                private int logicDelete;
                private Object pageNum;
                private Object pageSize;
                private Object operator;
                private String templateId;
                private String activityId;
                private String templateActivityDescribe;
                private String activityImageUrl;
                private int orderBy;

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

                public String getTemplateId() {
                    return templateId;
                }

                public void setTemplateId(String templateId) {
                    this.templateId = templateId;
                }

                public String getActivityId() {
                    return activityId;
                }

                public void setActivityId(String activityId) {
                    this.activityId = activityId;
                }

                public String getTemplateActivityDescribe() {
                    return templateActivityDescribe;
                }

                public void setTemplateActivityDescribe(String templateActivityDescribe) {
                    this.templateActivityDescribe = templateActivityDescribe;
                }

                public String getActivityImageUrl() {
                    return activityImageUrl;
                }

                public void setActivityImageUrl(String activityImageUrl) {
                    this.activityImageUrl = activityImageUrl;
                }

                public int getOrderBy() {
                    return orderBy;
                }

                public void setOrderBy(int orderBy) {
                    this.orderBy = orderBy;
                }
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
