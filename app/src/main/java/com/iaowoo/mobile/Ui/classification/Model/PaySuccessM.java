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
 * @Description: ${todo}()
 * @date 2018/12/3
 * @email ${18011009889@163.com}
 */
public class PaySuccessM {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"productName":null,"mainImage":null,"orderId":"2018120318180169651400263680","orderStatus":20,"payAmount":0.05,"orderAmount":0.05,"profit":0,"integralValue":null,"gemNum":0,"unionBuyInfoVOList":null,"leastCount":null,"isJoined":null,"shareRedOrderId":"2018120318180169651400263680","haveShareRedEnvelope":true,"paySuccessTime":"2018-12-03 18:18:02","haveBigGiftBag":false}}
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
         * content : {"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"productName":null,"mainImage":null,"orderId":"2018120318180169651400263680","orderStatus":20,"payAmount":0.05,"orderAmount":0.05,"profit":0,"integralValue":null,"gemNum":0,"unionBuyInfoVOList":null,"leastCount":null,"isJoined":null,"shareRedOrderId":"2018120318180169651400263680","haveShareRedEnvelope":true,"paySuccessTime":"2018-12-03 18:18:02","haveBigGiftBag":false}
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
             * productName : null
             * mainImage : null
             * orderId : 2018120318180169651400263680
             * orderStatus : 20
             * payAmount : 0.05
             * orderAmount : 0.05
             * profit : 0
             * integralValue : null
             * gemNum : 0
             * unionBuyInfoVOList : null
             * leastCount : null
             * isJoined : null
             * shareRedOrderId : 2018120318180169651400263680
             * haveShareRedEnvelope : true
             * paySuccessTime : 2018-12-03 18:18:02
             * haveBigGiftBag : false
             */

            private Object id;
            private Object createTime;
            private Object updateTime;
            private int logicDelete;
            private Object pageNum;
            private Object pageSize;
            private Object operator;
            private Object productName;
            private Object mainImage;
            private String orderId;
            private int orderStatus;
            private double payAmount;
            private double orderAmount;
            private int profit;
            private String integralValue;
            private int gemNum;
            private Object unionBuyInfoVOList;
            private Object leastCount;
            private Object isJoined;
            private String shareRedOrderId;
            private boolean haveShareRedEnvelope;
            private String paySuccessTime;
            private boolean haveBigGiftBag;

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

            public Object getProductName() {
                return productName;
            }

            public void setProductName(Object productName) {
                this.productName = productName;
            }

            public Object getMainImage() {
                return mainImage;
            }

            public void setMainImage(Object mainImage) {
                this.mainImage = mainImage;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public double getPayAmount() {
                return payAmount;
            }

            public void setPayAmount(double payAmount) {
                this.payAmount = payAmount;
            }

            public double getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(double orderAmount) {
                this.orderAmount = orderAmount;
            }

            public int getProfit() {
                return profit;
            }

            public void setProfit(int profit) {
                this.profit = profit;
            }

            public Object getIntegralValue() {
                return integralValue;
            }

            public void setIntegralValue(String integralValue) {
                this.integralValue = integralValue;
            }

            public int getGemNum() {
                return gemNum;
            }

            public void setGemNum(int gemNum) {
                this.gemNum = gemNum;
            }

            public Object getUnionBuyInfoVOList() {
                return unionBuyInfoVOList;
            }

            public void setUnionBuyInfoVOList(Object unionBuyInfoVOList) {
                this.unionBuyInfoVOList = unionBuyInfoVOList;
            }

            public Object getLeastCount() {
                return leastCount;
            }

            public void setLeastCount(Object leastCount) {
                this.leastCount = leastCount;
            }

            public Object getIsJoined() {
                return isJoined;
            }

            public void setIsJoined(Object isJoined) {
                this.isJoined = isJoined;
            }

            public String getShareRedOrderId() {
                return shareRedOrderId;
            }

            public void setShareRedOrderId(String shareRedOrderId) {
                this.shareRedOrderId = shareRedOrderId;
            }

            public boolean isHaveShareRedEnvelope() {
                return haveShareRedEnvelope;
            }

            public void setHaveShareRedEnvelope(boolean haveShareRedEnvelope) {
                this.haveShareRedEnvelope = haveShareRedEnvelope;
            }

            public String getPaySuccessTime() {
                return paySuccessTime;
            }

            public void setPaySuccessTime(String paySuccessTime) {
                this.paySuccessTime = paySuccessTime;
            }

            public boolean isHaveBigGiftBag() {
                return haveBigGiftBag;
            }

            public void setHaveBigGiftBag(boolean haveBigGiftBag) {
                this.haveBigGiftBag = haveBigGiftBag;
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
