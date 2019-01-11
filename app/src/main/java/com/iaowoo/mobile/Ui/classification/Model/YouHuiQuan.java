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
 * @date 2018/11/28
 * @email ${18011009889@163.com}
 */
public class YouHuiQuan {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"total":10,"pages":1,"list":[{"couponRecorId":"514395509314555904","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:04:06","validTime":"2018-12-20 11:04:06","generateTime":"2018-11-20 11:04:06","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395330775617536","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:24","validTime":"2018-12-20 11:03:24","generateTime":"2018-11-20 11:03:24","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395295023370240","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:15","validTime":"2018-12-20 11:03:15","generateTime":"2018-11-20 11:03:15","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395266611150848","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:09","validTime":"2018-12-20 11:03:09","generateTime":"2018-11-20 11:03:09","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395234658947072","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:01","validTime":"2018-12-20 11:03:01","generateTime":"2018-11-20 11:03:01","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395195513507840","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:52","validTime":"2018-12-20 11:02:52","generateTime":"2018-11-20 11:02:52","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395143499943936","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:39","validTime":"2018-12-20 11:02:39","generateTime":"2018-11-20 11:02:39","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395117268766720","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:33","validTime":"2018-12-20 11:02:33","generateTime":"2018-11-20 11:02:33","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395089875763200","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:26","validTime":"2018-12-20 11:02:26","generateTime":"2018-11-20 11:02:26","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"496008848117600257","activityId":"0","couponName":"为我的城市打call，冠军产品优惠券","couponType":null,"couponDetail":"为我的城市打call，冠军产品优惠券","faceValue":128,"limitValue":1000,"beginTime":null,"validTime":"2018-12-31 13:48:45","generateTime":"2018-09-30 17:22:05","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null}]}}
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
         * content : {"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"total":10,"pages":1,"list":[{"couponRecorId":"514395509314555904","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:04:06","validTime":"2018-12-20 11:04:06","generateTime":"2018-11-20 11:04:06","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395330775617536","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:24","validTime":"2018-12-20 11:03:24","generateTime":"2018-11-20 11:03:24","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395295023370240","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:15","validTime":"2018-12-20 11:03:15","generateTime":"2018-11-20 11:03:15","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395266611150848","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:09","validTime":"2018-12-20 11:03:09","generateTime":"2018-11-20 11:03:09","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395234658947072","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:01","validTime":"2018-12-20 11:03:01","generateTime":"2018-11-20 11:03:01","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395195513507840","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:52","validTime":"2018-12-20 11:02:52","generateTime":"2018-11-20 11:02:52","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395143499943936","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:39","validTime":"2018-12-20 11:02:39","generateTime":"2018-11-20 11:02:39","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395117268766720","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:33","validTime":"2018-12-20 11:02:33","generateTime":"2018-11-20 11:02:33","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395089875763200","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:26","validTime":"2018-12-20 11:02:26","generateTime":"2018-11-20 11:02:26","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"496008848117600257","activityId":"0","couponName":"为我的城市打call，冠军产品优惠券","couponType":null,"couponDetail":"为我的城市打call，冠军产品优惠券","faceValue":128,"limitValue":1000,"beginTime":null,"validTime":"2018-12-31 13:48:45","generateTime":"2018-09-30 17:22:05","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null}]}
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
             * pageNum : null
             * pageSize : null
             * operator : null
             * total : 10
             * pages : 1
             * list : [{"couponRecorId":"514395509314555904","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:04:06","validTime":"2018-12-20 11:04:06","generateTime":"2018-11-20 11:04:06","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395330775617536","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:24","validTime":"2018-12-20 11:03:24","generateTime":"2018-11-20 11:03:24","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395295023370240","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:15","validTime":"2018-12-20 11:03:15","generateTime":"2018-11-20 11:03:15","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395266611150848","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:09","validTime":"2018-12-20 11:03:09","generateTime":"2018-11-20 11:03:09","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395234658947072","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:03:01","validTime":"2018-12-20 11:03:01","generateTime":"2018-11-20 11:03:01","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395195513507840","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:52","validTime":"2018-12-20 11:02:52","generateTime":"2018-11-20 11:02:52","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395143499943936","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:39","validTime":"2018-12-20 11:02:39","generateTime":"2018-11-20 11:02:39","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395117268766720","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:33","validTime":"2018-12-20 11:02:33","generateTime":"2018-11-20 11:02:33","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"514395089875763200","activityId":"0","couponName":"霸气取暖摇一摇","couponType":null,"couponDetail":"霸气取暖摇一摇","faceValue":2,"limitValue":0,"beginTime":"2018-11-20 11:02:26","validTime":"2018-12-20 11:02:26","generateTime":"2018-11-20 11:02:26","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null},{"couponRecorId":"496008848117600257","activityId":"0","couponName":"为我的城市打call，冠军产品优惠券","couponType":null,"couponDetail":"为我的城市打call，冠军产品优惠券","faceValue":128,"limitValue":1000,"beginTime":null,"validTime":"2018-12-31 13:48:45","generateTime":"2018-09-30 17:22:05","status":0,"usableAmount":null,"usedAmount":null,"expiredAmount":null}]
             */

            private Object id;
            private Object createTime;
            private Object updateTime;
            private int logicDelete;
            private Object pageNum;
            private Object pageSize;
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
                 * couponRecorId : 514395509314555904
                 * activityId : 0
                 * couponName : 霸气取暖摇一摇
                 * couponType : null
                 * couponDetail : 霸气取暖摇一摇
                 * faceValue : 2.0
                 * limitValue : 0.0
                 * beginTime : 2018-11-20 11:04:06
                 * validTime : 2018-12-20 11:04:06
                 * generateTime : 2018-11-20 11:04:06
                 * status : 0
                 * usableAmount : null
                 * usedAmount : null
                 * expiredAmount : null
                 */

                private String couponRecorId;
                private String activityId;
                private String couponName;
                private String couponType;
                private String couponDetail;
                private double faceValue;
                private double limitValue;
                private String beginTime;
                private String validTime;
                private String generateTime;
                private int status;
                private Object usableAmount;
                private Object usedAmount;
                private Object expiredAmount;

                public String getCouponRecorId() {
                    return couponRecorId;
                }

                public void setCouponRecorId(String couponRecorId) {
                    this.couponRecorId = couponRecorId;
                }

                public String getActivityId() {
                    return activityId;
                }

                public void setActivityId(String activityId) {
                    this.activityId = activityId;
                }

                public String getCouponName() {
                    return couponName;
                }

                public void setCouponName(String couponName) {
                    this.couponName = couponName;
                }

                public String getCouponType() {
                    return couponType;
                }

                public String getCouponDetail() {
                    return couponDetail;
                }

                public void setCouponDetail(String couponDetail) {
                    this.couponDetail = couponDetail;
                }

                public double getFaceValue() {
                    return faceValue;
                }

                public void setFaceValue(double faceValue) {
                    this.faceValue = faceValue;
                }

                public double getLimitValue() {
                    return limitValue;
                }

                public void setLimitValue(double limitValue) {
                    this.limitValue = limitValue;
                }

                public String getBeginTime() {
                    return beginTime;
                }

                public void setBeginTime(String beginTime) {
                    this.beginTime = beginTime;
                }

                public String getValidTime() {
                    return validTime;
                }

                public void setValidTime(String validTime) {
                    this.validTime = validTime;
                }

                public String getGenerateTime() {
                    return generateTime;
                }

                public void setGenerateTime(String generateTime) {
                    this.generateTime = generateTime;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public Object getUsableAmount() {
                    return usableAmount;
                }

                public void setUsableAmount(Object usableAmount) {
                    this.usableAmount = usableAmount;
                }

                public void setCouponType(String couponType) {
                    this.couponType = couponType;
                }

                public Object getUsedAmount() {
                    return usedAmount;
                }

                public void setUsedAmount(Object usedAmount) {
                    this.usedAmount = usedAmount;
                }

                public Object getExpiredAmount() {
                    return expiredAmount;
                }

                public void setExpiredAmount(Object expiredAmount) {
                    this.expiredAmount = expiredAmount;
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
