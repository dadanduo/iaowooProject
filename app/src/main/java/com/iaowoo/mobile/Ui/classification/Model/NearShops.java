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
 * @Description: ${todo}(附近的门店)
 * @date 2018/9/3
 * @email ${18011009889@163.com}
 */
public class NearShops {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":null,"pageSize":null,"total":5,"pages":1,"list":[{"templateId":"MT_481537524783644672","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"481537524783644672","name":"宋薇","merchantName":"hj","categoryCode":"1","categoryName":"美食","telephone":"13525789080","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰路","businessTime":"09:00-23:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-21/481537509910642688.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-21/481537509910642688.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-08-21/481537575903821824.png","authStatus":1,"businessStatus":0,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_483697140187533312","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"483697140187533312","name":"金海","merchantName":"兔兔兔","categoryCode":"2","categoryName":"服装","telephone":"17521636569","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰路120号","businessTime":"02:00-09:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697034625290240.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697034625290240.jpg,https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697086143922176.jpg,https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697125520048128.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-08-27/483697223733870592.png","authStatus":1,"businessStatus":0,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_484299358926278656","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"484299358926278656","name":"宋浩楠","merchantName":"兔崽子","categoryCode":"1","categoryName":"美食","telephone":"17521636567","province":"310000","city":"310100","district":"310113","detailAddress":"呼兰路","businessTime":null,"mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-29/484299339926081536.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-29/484299339926081536.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-08-29/484300265839656960.png","authStatus":1,"businessStatus":0,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_485240630910390272","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"485240630910390272","name":"尚希","merchantName":"空间","categoryCode":"1","categoryName":"美食","telephone":"13525251313","province":"310000","city":"310100","district":"310113","detailAddress":"呼兰路","businessTime":"00:00-23:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485240622983155712.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485240622983155712.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-09-01/485240680847773696.png","authStatus":1,"businessStatus":1,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_485463954743627776","avgScore":100,"feedBack":100,"prefix":null,"distance":896,"praise":null,"merchantId":"485463954743627776","name":"张凤仙","merchantName":"vv","categoryCode":"1","categoryName":"美食","telephone":"13525253636","province":"310000","city":"310100","district":"310113","detailAddress":"呼兰路","businessTime":"00:00-09:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485463949110677504.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485463949110677504.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-09-01/485464026797576192.png","authStatus":1,"businessStatus":1,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null}]}}
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
         * content : {"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":null,"pageSize":null,"total":5,"pages":1,"list":[{"templateId":"MT_481537524783644672","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"481537524783644672","name":"宋薇","merchantName":"hj","categoryCode":"1","categoryName":"美食","telephone":"13525789080","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰路","businessTime":"09:00-23:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-21/481537509910642688.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-21/481537509910642688.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-08-21/481537575903821824.png","authStatus":1,"businessStatus":0,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_483697140187533312","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"483697140187533312","name":"金海","merchantName":"兔兔兔","categoryCode":"2","categoryName":"服装","telephone":"17521636569","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰路120号","businessTime":"02:00-09:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697034625290240.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697034625290240.jpg,https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697086143922176.jpg,https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697125520048128.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-08-27/483697223733870592.png","authStatus":1,"businessStatus":0,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_484299358926278656","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"484299358926278656","name":"宋浩楠","merchantName":"兔崽子","categoryCode":"1","categoryName":"美食","telephone":"17521636567","province":"310000","city":"310100","district":"310113","detailAddress":"呼兰路","businessTime":null,"mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-29/484299339926081536.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-29/484299339926081536.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-08-29/484300265839656960.png","authStatus":1,"businessStatus":0,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_485240630910390272","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"485240630910390272","name":"尚希","merchantName":"空间","categoryCode":"1","categoryName":"美食","telephone":"13525251313","province":"310000","city":"310100","district":"310113","detailAddress":"呼兰路","businessTime":"00:00-23:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485240622983155712.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485240622983155712.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-09-01/485240680847773696.png","authStatus":1,"businessStatus":1,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_485463954743627776","avgScore":100,"feedBack":100,"prefix":null,"distance":896,"praise":null,"merchantId":"485463954743627776","name":"张凤仙","merchantName":"vv","categoryCode":"1","categoryName":"美食","telephone":"13525253636","province":"310000","city":"310100","district":"310113","detailAddress":"呼兰路","businessTime":"00:00-09:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485463949110677504.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485463949110677504.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-09-01/485464026797576192.png","authStatus":1,"businessStatus":1,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null}]}
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
             * total : 5
             * pages : 1
             * list : [{"templateId":"MT_481537524783644672","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"481537524783644672","name":"宋薇","merchantName":"hj","categoryCode":"1","categoryName":"美食","telephone":"13525789080","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰路","businessTime":"09:00-23:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-21/481537509910642688.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-21/481537509910642688.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-08-21/481537575903821824.png","authStatus":1,"businessStatus":0,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_483697140187533312","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"483697140187533312","name":"金海","merchantName":"兔兔兔","categoryCode":"2","categoryName":"服装","telephone":"17521636569","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰路120号","businessTime":"02:00-09:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697034625290240.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697034625290240.jpg,https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697086143922176.jpg,https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-27/483697125520048128.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-08-27/483697223733870592.png","authStatus":1,"businessStatus":0,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_484299358926278656","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"484299358926278656","name":"宋浩楠","merchantName":"兔崽子","categoryCode":"1","categoryName":"美食","telephone":"17521636567","province":"310000","city":"310100","district":"310113","detailAddress":"呼兰路","businessTime":null,"mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-29/484299339926081536.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-29/484299339926081536.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-08-29/484300265839656960.png","authStatus":1,"businessStatus":0,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_485240630910390272","avgScore":0,"feedBack":0,"prefix":null,"distance":896,"praise":null,"merchantId":"485240630910390272","name":"尚希","merchantName":"空间","categoryCode":"1","categoryName":"美食","telephone":"13525251313","province":"310000","city":"310100","district":"310113","detailAddress":"呼兰路","businessTime":"00:00-23:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485240622983155712.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485240622983155712.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-09-01/485240680847773696.png","authStatus":1,"businessStatus":1,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null},{"templateId":"MT_485463954743627776","avgScore":100,"feedBack":100,"prefix":null,"distance":896,"praise":null,"merchantId":"485463954743627776","name":"张凤仙","merchantName":"vv","categoryCode":"1","categoryName":"美食","telephone":"13525253636","province":"310000","city":"310100","district":"310113","detailAddress":"呼兰路","businessTime":"00:00-09:00","mainImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485463949110677504.jpg","extraImg":"https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-09-01/485463949110677504.jpg","lon":"121.439654","lat":"31.340843","rqCode":"images/merchantDetailQrcodeImg/2018-09-01/485464026797576192.png","authStatus":1,"businessStatus":1,"isBanner":null,"judges":null,"totalJudge":null,"judgeKeyWordCount":null}]
             */

            private Object id;
            private Object createTime;
            private Object updateTime;
            private int logicDelete;
            private Object pageNum;
            private Object pageSize;
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
                 * templateId : MT_481537524783644672
                 * avgScore : 0
                 * feedBack : 0
                 * prefix : null
                 * distance : 896
                 * praise : null
                 * merchantId : 481537524783644672
                 * name : 宋薇
                 * merchantName : hj
                 * categoryCode : 1
                 * categoryName : 美食
                 * telephone : 13525789080
                 * province : 310000
                 * city : 310000
                 * district : 310113
                 * detailAddress : 呼兰路
                 * businessTime : 09:00-23:00
                 * mainImg : https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-21/481537509910642688.jpg
                 * extraImg : https://test-files.shzhuoji.com/images/merchantDetailMainImg/2018-08-21/481537509910642688.jpg
                 * lon : 121.439654
                 * lat : 31.340843
                 * rqCode : images/merchantDetailQrcodeImg/2018-08-21/481537575903821824.png
                 * authStatus : 1
                 * businessStatus : 0
                 * isBanner : null
                 * judges : null
                 * totalJudge : null
                 * judgeKeyWordCount : null
                 */

                private String templateId;
                private int avgScore;
                private int feedBack;
                private Object prefix;
                private int distance;
                private Object praise;
                private String merchantId;
                private String name;
                private String merchantName;
                private String categoryCode;
                private String categoryName;
                private String telephone;
                private String province;
                private String city;
                private String district;
                private String detailAddress;
                private String businessTime;
                private String mainImg;
                private String extraImg;
                private String lon;
                private String lat;
                private String rqCode;
                private int authStatus;
                private int businessStatus;
                private Object isBanner;
                private Object judges;
                private Object totalJudge;
                private Object judgeKeyWordCount;

                public String getTemplateId() {
                    return templateId;
                }

                public void setTemplateId(String templateId) {
                    this.templateId = templateId;
                }

                public int getAvgScore() {
                    return avgScore;
                }

                public void setAvgScore(int avgScore) {
                    this.avgScore = avgScore;
                }

                public int getFeedBack() {
                    return feedBack;
                }

                public void setFeedBack(int feedBack) {
                    this.feedBack = feedBack;
                }

                public Object getPrefix() {
                    return prefix;
                }

                public void setPrefix(Object prefix) {
                    this.prefix = prefix;
                }

                public int getDistance() {
                    return distance;
                }

                public void setDistance(int distance) {
                    this.distance = distance;
                }

                public Object getPraise() {
                    return praise;
                }

                public void setPraise(Object praise) {
                    this.praise = praise;
                }

                public String getMerchantId() {
                    return merchantId;
                }

                public void setMerchantId(String merchantId) {
                    this.merchantId = merchantId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getMerchantName() {
                    return merchantName;
                }

                public void setMerchantName(String merchantName) {
                    this.merchantName = merchantName;
                }

                public String getCategoryCode() {
                    return categoryCode;
                }

                public void setCategoryCode(String categoryCode) {
                    this.categoryCode = categoryCode;
                }

                public String getCategoryName() {
                    return categoryName;
                }

                public void setCategoryName(String categoryName) {
                    this.categoryName = categoryName;
                }

                public String getTelephone() {
                    return telephone;
                }

                public void setTelephone(String telephone) {
                    this.telephone = telephone;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }

                public String getDetailAddress() {
                    return detailAddress;
                }

                public void setDetailAddress(String detailAddress) {
                    this.detailAddress = detailAddress;
                }

                public String getBusinessTime() {
                    return businessTime;
                }

                public void setBusinessTime(String businessTime) {
                    this.businessTime = businessTime;
                }

                public String getMainImg() {
                    return mainImg;
                }

                public void setMainImg(String mainImg) {
                    this.mainImg = mainImg;
                }

                public String getExtraImg() {
                    return extraImg;
                }

                public void setExtraImg(String extraImg) {
                    this.extraImg = extraImg;
                }

                public String getLon() {
                    return lon;
                }

                public void setLon(String lon) {
                    this.lon = lon;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getRqCode() {
                    return rqCode;
                }

                public void setRqCode(String rqCode) {
                    this.rqCode = rqCode;
                }

                public int getAuthStatus() {
                    return authStatus;
                }

                public void setAuthStatus(int authStatus) {
                    this.authStatus = authStatus;
                }

                public int getBusinessStatus() {
                    return businessStatus;
                }

                public void setBusinessStatus(int businessStatus) {
                    this.businessStatus = businessStatus;
                }

                public Object getIsBanner() {
                    return isBanner;
                }

                public void setIsBanner(Object isBanner) {
                    this.isBanner = isBanner;
                }

                public Object getJudges() {
                    return judges;
                }

                public void setJudges(Object judges) {
                    this.judges = judges;
                }

                public Object getTotalJudge() {
                    return totalJudge;
                }

                public void setTotalJudge(Object totalJudge) {
                    this.totalJudge = totalJudge;
                }

                public Object getJudgeKeyWordCount() {
                    return judgeKeyWordCount;
                }

                public void setJudgeKeyWordCount(Object judgeKeyWordCount) {
                    this.judgeKeyWordCount = judgeKeyWordCount;
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
