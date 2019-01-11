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
public class JS {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"couponRedParam":[{"activityId":"0","couponAmount":0,"orderAmount":1290,"payAmount":1290,"redAmount":0,"gemStoneCouponAmount":21,"maxRedAmount":193.5}],"totalOrderAmount":1290,"totalPayAmount":1290}}
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
         * content : {"couponRedParam":[{"activityId":"0","couponAmount":0,"orderAmount":1290,"payAmount":1290,"redAmount":0,"gemStoneCouponAmount":21,"maxRedAmount":193.5}],"totalOrderAmount":1290,"totalPayAmount":1290}
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
             * couponRedParam : [{"activityId":"0","couponAmount":0,"orderAmount":1290,"payAmount":1290,"redAmount":0,"gemStoneCouponAmount":21,"maxRedAmount":193.5}]
             * totalOrderAmount : 1290.0
             * totalPayAmount : 1290.0
             */

            private double totalOrderAmount;
            private double totalPayAmount;
            private List<CouponRedParamBean> couponRedParam;

            public double getTotalOrderAmount() {
                return totalOrderAmount;
            }

            public void setTotalOrderAmount(double totalOrderAmount) {
                this.totalOrderAmount = totalOrderAmount;
            }

            public double getTotalPayAmount() {
                return totalPayAmount;
            }

            public void setTotalPayAmount(double totalPayAmount) {
                this.totalPayAmount = totalPayAmount;
            }

            public List<CouponRedParamBean> getCouponRedParam() {
                return couponRedParam;
            }

            public void setCouponRedParam(List<CouponRedParamBean> couponRedParam) {
                this.couponRedParam = couponRedParam;
            }

            public static class CouponRedParamBean {
                /**
                 * activityId : 0
                 * couponAmount : 0
                 * orderAmount : 1290.0
                 * payAmount : 1290.0
                 * redAmount : 0.0
                 * gemStoneCouponAmount : 21
                 * maxRedAmount : 193.5
                 */

                private String activityId;
                private int couponAmount;
                private double orderAmount;
                private double payAmount;
                private double redAmount;
                private int gemStoneCouponAmount;
                private double maxRedAmount;

                public String getActivityId() {
                    return activityId;
                }

                public void setActivityId(String activityId) {
                    this.activityId = activityId;
                }

                public int getCouponAmount() {
                    return couponAmount;
                }

                public void setCouponAmount(int couponAmount) {
                    this.couponAmount = couponAmount;
                }

                public double getOrderAmount() {
                    return orderAmount;
                }

                public void setOrderAmount(double orderAmount) {
                    this.orderAmount = orderAmount;
                }

                public double getPayAmount() {
                    return payAmount;
                }

                public void setPayAmount(double payAmount) {
                    this.payAmount = payAmount;
                }

                public double getRedAmount() {
                    return redAmount;
                }

                public void setRedAmount(double redAmount) {
                    this.redAmount = redAmount;
                }

                public int getGemStoneCouponAmount() {
                    return gemStoneCouponAmount;
                }

                public void setGemStoneCouponAmount(int gemStoneCouponAmount) {
                    this.gemStoneCouponAmount = gemStoneCouponAmount;
                }

                public double getMaxRedAmount() {
                    return maxRedAmount;
                }

                public void setMaxRedAmount(double maxRedAmount) {
                    this.maxRedAmount = maxRedAmount;
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
