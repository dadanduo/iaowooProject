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
 * @Description: ${todo}(评价model)
 * @date 2018/11/26
 * @email ${18011009889@163.com}
 */
public class CommentsModel {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"avgScore":null,"feedback":"100.00","judges":{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":1,"pageSize":15,"operator":null,"total":2,"pages":1,"list":[{"id":43,"createTime":"2018-11-26 10:52:00","updateTime":"2018-11-26 10:52:00","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"orderId":"2018112610390384034201491456","userId":40,"templateId":"SH_456556063869632512","subTemplateId":"456556063919964160","score":100,"content":"好号给我一下，谢谢老板！！！1111","images":"https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566672929193984.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566700162813952.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566735927644160.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566755829616640.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566786204766208.jpg","scoreQStart":null,"scoreQEnd":null,"nickname":"**rm Warm","headImgUrl":"https://test-files.shzhuoji.com/images/businessProveImg/2018-11-05/509113529102499840.jpg","sku":{"规格":"750ml"}},{"id":28,"createTime":"2018-09-29 17:12:07","updateTime":"2018-09-29 17:12:07","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"orderId":"2018092916535478771865653248","userId":93,"templateId":"SH_456556063869632512","subTemplateId":"456556063919964160","score":100,"content":"我我我哦","images":"","scoreQStart":null,"scoreQEnd":null,"nickname":"匿名用户","headImgUrl":null,"sku":{"规格":"750ml"}}]},"judgeKeyWordCount":null}}
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
         * content : {"avgScore":null,"feedback":"100.00","judges":{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":1,"pageSize":15,"operator":null,"total":2,"pages":1,"list":[{"id":43,"createTime":"2018-11-26 10:52:00","updateTime":"2018-11-26 10:52:00","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"orderId":"2018112610390384034201491456","userId":40,"templateId":"SH_456556063869632512","subTemplateId":"456556063919964160","score":100,"content":"好号给我一下，谢谢老板！！！1111","images":"https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566672929193984.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566700162813952.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566735927644160.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566755829616640.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566786204766208.jpg","scoreQStart":null,"scoreQEnd":null,"nickname":"**rm Warm","headImgUrl":"https://test-files.shzhuoji.com/images/businessProveImg/2018-11-05/509113529102499840.jpg","sku":{"规格":"750ml"}},{"id":28,"createTime":"2018-09-29 17:12:07","updateTime":"2018-09-29 17:12:07","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"orderId":"2018092916535478771865653248","userId":93,"templateId":"SH_456556063869632512","subTemplateId":"456556063919964160","score":100,"content":"我我我哦","images":"","scoreQStart":null,"scoreQEnd":null,"nickname":"匿名用户","headImgUrl":null,"sku":{"规格":"750ml"}}]},"judgeKeyWordCount":null}
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
             * avgScore : null
             * feedback : 100.00
             * judges : {"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":1,"pageSize":15,"operator":null,"total":2,"pages":1,"list":[{"id":43,"createTime":"2018-11-26 10:52:00","updateTime":"2018-11-26 10:52:00","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"orderId":"2018112610390384034201491456","userId":40,"templateId":"SH_456556063869632512","subTemplateId":"456556063919964160","score":100,"content":"好号给我一下，谢谢老板！！！1111","images":"https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566672929193984.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566700162813952.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566735927644160.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566755829616640.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566786204766208.jpg","scoreQStart":null,"scoreQEnd":null,"nickname":"**rm Warm","headImgUrl":"https://test-files.shzhuoji.com/images/businessProveImg/2018-11-05/509113529102499840.jpg","sku":{"规格":"750ml"}},{"id":28,"createTime":"2018-09-29 17:12:07","updateTime":"2018-09-29 17:12:07","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"orderId":"2018092916535478771865653248","userId":93,"templateId":"SH_456556063869632512","subTemplateId":"456556063919964160","score":100,"content":"我我我哦","images":"","scoreQStart":null,"scoreQEnd":null,"nickname":"匿名用户","headImgUrl":null,"sku":{"规格":"750ml"}}]}
             * judgeKeyWordCount : null
             */

            private Object avgScore;
            private String feedback;
            private JudgesBean judges;
            private Object judgeKeyWordCount;

            public Object getAvgScore() {
                return avgScore;
            }

            public void setAvgScore(Object avgScore) {
                this.avgScore = avgScore;
            }

            public String getFeedback() {
                return feedback;
            }

            public void setFeedback(String feedback) {
                this.feedback = feedback;
            }

            public JudgesBean getJudges() {
                return judges;
            }

            public void setJudges(JudgesBean judges) {
                this.judges = judges;
            }

            public Object getJudgeKeyWordCount() {
                return judgeKeyWordCount;
            }

            public void setJudgeKeyWordCount(Object judgeKeyWordCount) {
                this.judgeKeyWordCount = judgeKeyWordCount;
            }

            public static class JudgesBean {
                /**
                 * id : null
                 * createTime : null
                 * updateTime : null
                 * logicDelete : 0
                 * pageNum : 1
                 * pageSize : 15
                 * operator : null
                 * total : 2
                 * pages : 1
                 * list : [{"id":43,"createTime":"2018-11-26 10:52:00","updateTime":"2018-11-26 10:52:00","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"orderId":"2018112610390384034201491456","userId":40,"templateId":"SH_456556063869632512","subTemplateId":"456556063919964160","score":100,"content":"好号给我一下，谢谢老板！！！1111","images":"https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566672929193984.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566700162813952.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566735927644160.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566755829616640.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566786204766208.jpg","scoreQStart":null,"scoreQEnd":null,"nickname":"**rm Warm","headImgUrl":"https://test-files.shzhuoji.com/images/businessProveImg/2018-11-05/509113529102499840.jpg","sku":{"规格":"750ml"}},{"id":28,"createTime":"2018-09-29 17:12:07","updateTime":"2018-09-29 17:12:07","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"orderId":"2018092916535478771865653248","userId":93,"templateId":"SH_456556063869632512","subTemplateId":"456556063919964160","score":100,"content":"我我我哦","images":"","scoreQStart":null,"scoreQEnd":null,"nickname":"匿名用户","headImgUrl":null,"sku":{"规格":"750ml"}}]
                 */

                private Object id;
                private Object createTime;
                private Object updateTime;
                private int logicDelete;
                private int pageNum;
                private int pageSize;
                private Object operator;
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

                public Object getOperator() {
                    return operator;
                }

                public void setOperator(Object operator) {
                    this.operator = operator;
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
                     * id : 43
                     * createTime : 2018-11-26 10:52:00
                     * updateTime : 2018-11-26 10:52:00
                     * logicDelete : 0
                     * pageNum : null
                     * pageSize : null
                     * operator : null
                     * orderId : 2018112610390384034201491456
                     * userId : 40
                     * templateId : SH_456556063869632512
                     * subTemplateId : 456556063919964160
                     * score : 100
                     * content : 好号给我一下，谢谢老板！！！1111
                     * images : https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566672929193984.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566700162813952.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566735927644160.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566755829616640.jpg,https://test-files.shzhuoji.com/images/spareSpace/2018-11-26/516566786204766208.jpg
                     * scoreQStart : null
                     * scoreQEnd : null
                     * nickname : **rm Warm
                     * headImgUrl : https://test-files.shzhuoji.com/images/businessProveImg/2018-11-05/509113529102499840.jpg
                     * sku : {"规格":"750ml"}
                     */

                    private int id;
                    private String createTime;
                    private String updateTime;
                    private int logicDelete;
                    private Object pageNum;
                    private Object pageSize;
                    private Object operator;
                    private String orderId;
                    private int userId;
                    private String templateId;
                    private String subTemplateId;
                    private int score;
                    private String content;
                    private String images;
                    private Object scoreQStart;
                    private Object scoreQEnd;
                    private String nickname;
                    private String headImgUrl;
                    private String  sku;

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

                    public String getOrderId() {
                        return orderId;
                    }

                    public void setOrderId(String orderId) {
                        this.orderId = orderId;
                    }

                    public int getUserId() {
                        return userId;
                    }

                    public void setUserId(int userId) {
                        this.userId = userId;
                    }

                    public String getTemplateId() {
                        return templateId;
                    }

                    public void setTemplateId(String templateId) {
                        this.templateId = templateId;
                    }

                    public String getSubTemplateId() {
                        return subTemplateId;
                    }

                    public void setSubTemplateId(String subTemplateId) {
                        this.subTemplateId = subTemplateId;
                    }

                    public int getScore() {
                        return score;
                    }

                    public void setScore(int score) {
                        this.score = score;
                    }

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
                    }

                    public String getImages() {
                        return images;
                    }

                    public void setImages(String images) {
                        this.images = images;
                    }

                    public Object getScoreQStart() {
                        return scoreQStart;
                    }

                    public void setScoreQStart(Object scoreQStart) {
                        this.scoreQStart = scoreQStart;
                    }

                    public Object getScoreQEnd() {
                        return scoreQEnd;
                    }

                    public void setScoreQEnd(Object scoreQEnd) {
                        this.scoreQEnd = scoreQEnd;
                    }

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }

                    public String getHeadImgUrl() {
                        return headImgUrl;
                    }

                    public void setHeadImgUrl(String headImgUrl) {
                        this.headImgUrl = headImgUrl;
                    }

                    public String getSku() {
                        return sku;
                    }

                    public void setSku(String  sku) {
                        this.sku = sku;
                    }
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
