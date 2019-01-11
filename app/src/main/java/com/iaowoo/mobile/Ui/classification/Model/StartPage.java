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
 * @Description: ${todo}()
 * @date 2018/8/7
 * @email ${18011009889@163.com}
 */
public class StartPage {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":1,"pageSize":15,"total":1,"pages":1,"list":[{"id":37,"createTime":"2018-08-02 09:49:03","updateTime":"2018-08-02 09:49:03","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474513958011666432","bannerImg":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-08-02/474513767653179392.png","bannerDetailImg":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-08-02/474513796249944064.png","jumpUrl":"https://m.shbs008.com/#/activity/qixi","sort":1,"type":1,"bannerDescribe":"大转盘","businessType":null,"location":9,"startTime":"2018-08-02 00:00:00","startTimeQEnd":null,"endTime":"2018-09-30 23:59:59","endTimeQStart":null,"businessId":null,"createUser":"admin","updateUser":"admin","title":"大转盘"}]}}
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
         * content : {"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":1,"pageSize":15,"total":1,"pages":1,"list":[{"id":37,"createTime":"2018-08-02 09:49:03","updateTime":"2018-08-02 09:49:03","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474513958011666432","bannerImg":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-08-02/474513767653179392.png","bannerDetailImg":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-08-02/474513796249944064.png","jumpUrl":"https://m.shbs008.com/#/activity/qixi","sort":1,"type":1,"bannerDescribe":"大转盘","businessType":null,"location":9,"startTime":"2018-08-02 00:00:00","startTimeQEnd":null,"endTime":"2018-09-30 23:59:59","endTimeQStart":null,"businessId":null,"createUser":"admin","updateUser":"admin","title":"大转盘"}]}
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
             * id : null
             * createTime : null
             * updateTime : null
             * logicDelete : 0
             * pageNum : 1
             * pageSize : 15
             * total : 1
             * pages : 1
             * list : [{"id":37,"createTime":"2018-08-02 09:49:03","updateTime":"2018-08-02 09:49:03","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474513958011666432","bannerImg":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-08-02/474513767653179392.png","bannerDetailImg":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-08-02/474513796249944064.png","jumpUrl":"https://m.shbs008.com/#/activity/qixi","sort":1,"type":1,"bannerDescribe":"大转盘","businessType":null,"location":9,"startTime":"2018-08-02 00:00:00","startTimeQEnd":null,"endTime":"2018-09-30 23:59:59","endTimeQStart":null,"businessId":null,"createUser":"admin","updateUser":"admin","title":"大转盘"}]
             */

            private Object id;
            private Object createTime;
            private Object updateTime;
            private int logicDelete;
            private int pageNum;
            private int pageSize;
            private int total;
            private int pages;
            private List<ListBean> list;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public int getLogicDelete() {
                return logicDelete;
            }

            public void setLogicDelete(int logicDelete) {
                this.logicDelete = logicDelete;
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

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 37
                 * createTime : 2018-08-02 09:49:03
                 * updateTime : 2018-08-02 09:49:03
                 * logicDelete : 0
                 * pageNum : null
                 * pageSize : null
                 * bannerId : 474513958011666432
                 * bannerImg : https://test-files.shzhuoji.com/images/bannerPictureImg/2018-08-02/474513767653179392.png
                 * bannerDetailImg : https://test-files.shzhuoji.com/images/bannerPictureImg/2018-08-02/474513796249944064.png
                 * jumpUrl : https://m.shbs008.com/#/activity/qixi
                 * sort : 1
                 * type : 1
                 * bannerDescribe : 大转盘
                 * businessType : null
                 * location : 9
                 * startTime : 2018-08-02 00:00:00
                 * startTimeQEnd : null
                 * endTime : 2018-09-30 23:59:59
                 * endTimeQStart : null
                 * businessId : null
                 * createUser : admin
                 * updateUser : admin
                 * title : 大转盘
                 */

                private int id;
                private String createTime;
                private String updateTime;
                private int logicDelete;
                private Object pageNum;
                private Object pageSize;
                private String bannerId;
                private String bannerImg;
                private String bannerDetailImg;
                private String jumpUrl;
                private int sort;
                private int type;
                private String bannerDescribe;
                private Object businessType;
                private int location;
                private String startTime;
                private Object startTimeQEnd;
                private String endTime;
                private Object endTimeQStart;
                private Object businessId;
                private String createUser;
                private String updateUser;
                private String title;

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

                public String getBannerId() {
                    return bannerId;
                }

                public void setBannerId(String bannerId) {
                    this.bannerId = bannerId;
                }

                public String getBannerImg() {
                    return bannerImg;
                }

                public void setBannerImg(String bannerImg) {
                    this.bannerImg = bannerImg;
                }

                public String getBannerDetailImg() {
                    return bannerDetailImg;
                }

                public void setBannerDetailImg(String bannerDetailImg) {
                    this.bannerDetailImg = bannerDetailImg;
                }

                public String getJumpUrl() {
                    return jumpUrl;
                }

                public void setJumpUrl(String jumpUrl) {
                    this.jumpUrl = jumpUrl;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getBannerDescribe() {
                    return bannerDescribe;
                }

                public void setBannerDescribe(String bannerDescribe) {
                    this.bannerDescribe = bannerDescribe;
                }

                public Object getBusinessType() {
                    return businessType;
                }

                public void setBusinessType(Object businessType) {
                    this.businessType = businessType;
                }

                public int getLocation() {
                    return location;
                }

                public void setLocation(int location) {
                    this.location = location;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public Object getStartTimeQEnd() {
                    return startTimeQEnd;
                }

                public void setStartTimeQEnd(Object startTimeQEnd) {
                    this.startTimeQEnd = startTimeQEnd;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public Object getEndTimeQStart() {
                    return endTimeQStart;
                }

                public void setEndTimeQStart(Object endTimeQStart) {
                    this.endTimeQStart = endTimeQStart;
                }

                public Object getBusinessId() {
                    return businessId;
                }

                public void setBusinessId(Object businessId) {
                    this.businessId = businessId;
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

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
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
