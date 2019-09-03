package com.iaowoo.mobile.Ui.classification.Model;


import java.util.List;

public class DIFFRENT {


    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":null,"pageSize":null,"total":580,"pages":39,"list":[{"templateId":"MERCHANT_TEMPLATE_444174804405190657","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":58,"praise":null,"merchantId":"444174804405190657","name":"测试门店2","categoryCode":"115","telephone":"18888888888","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":null,"lon":"121.432596","lat":"31.338955","rqCode":"images/licenseImg/2018-05-10/444175444565032960l.png","judges":null},{"templateId":"MERCHANT_TEMPLATE_444175481630097408","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":1125,"praise":null,"merchantId":"444175481630097408","name":"测试门店3","categoryCode":"101","telephone":"18761722685","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"12:30--14:30","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":"images/idCardImg/2018-05-10/444211821612630016f.jpeg","lon":"121.432596","lat":"31.348955","rqCode":"","judges":null},{"templateId":"MT_446358861603930112","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":1125,"praise":null,"merchantId":"446358861603930112","name":"宜春院","categoryCode":"101","telephone":"18761722685","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰路100号","businessTime":"12:30--14:30","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":null,"lon":"121.432596","lat":"31.348955","rqCode":"images/merchantDetailQrcodeImg/2018-05-17/446785379064872960.png","judges":null},{"templateId":"MT_446417033895084032","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":58,"praise":null,"merchantId":"446417033895084032","name":"哈哈哈","categoryCode":"107","telephone":"18956245257","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"5588555","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":"","lon":"121.432596","lat":"31.338955","rqCode":"images/merchantDetailQrcodeImg/2018-05-17/446785385050144768.png","judges":null},{"templateId":"MT_446419970490892288","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":58,"praise":null,"merchantId":"446419970490892288","name":"阿木木龙虾","categoryCode":"107","telephone":"18956245257","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"5588555","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":"","lon":"121.432596","lat":"31.338955","rqCode":"images/merchantDetailQrcodeImg/2018-05-17/446785400036392960.png","judges":null}]}}
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
         * content : {"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":null,"pageSize":null,"total":580,"pages":39,"list":[{"templateId":"MERCHANT_TEMPLATE_444174804405190657","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":58,"praise":null,"merchantId":"444174804405190657","name":"测试门店2","categoryCode":"115","telephone":"18888888888","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":null,"lon":"121.432596","lat":"31.338955","rqCode":"images/licenseImg/2018-05-10/444175444565032960l.png","judges":null},{"templateId":"MERCHANT_TEMPLATE_444175481630097408","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":1125,"praise":null,"merchantId":"444175481630097408","name":"测试门店3","categoryCode":"101","telephone":"18761722685","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"12:30--14:30","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":"images/idCardImg/2018-05-10/444211821612630016f.jpeg","lon":"121.432596","lat":"31.348955","rqCode":"","judges":null},{"templateId":"MT_446358861603930112","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":1125,"praise":null,"merchantId":"446358861603930112","name":"宜春院","categoryCode":"101","telephone":"18761722685","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰路100号","businessTime":"12:30--14:30","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":null,"lon":"121.432596","lat":"31.348955","rqCode":"images/merchantDetailQrcodeImg/2018-05-17/446785379064872960.png","judges":null},{"templateId":"MT_446417033895084032","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":58,"praise":null,"merchantId":"446417033895084032","name":"哈哈哈","categoryCode":"107","telephone":"18956245257","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"5588555","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":"","lon":"121.432596","lat":"31.338955","rqCode":"images/merchantDetailQrcodeImg/2018-05-17/446785385050144768.png","judges":null},{"templateId":"MT_446419970490892288","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":58,"praise":null,"merchantId":"446419970490892288","name":"阿木木龙虾","categoryCode":"107","telephone":"18956245257","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"5588555","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":"","lon":"121.432596","lat":"31.338955","rqCode":"images/merchantDetailQrcodeImg/2018-05-17/446785400036392960.png","judges":null}]}
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
             * total : 580
             * pages : 39
             * list : [{"templateId":"MERCHANT_TEMPLATE_444174804405190657","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":58,"praise":null,"merchantId":"444174804405190657","name":"测试门店2","categoryCode":"115","telephone":"18888888888","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":null,"lon":"121.432596","lat":"31.338955","rqCode":"images/licenseImg/2018-05-10/444175444565032960l.png","judges":null},{"templateId":"MERCHANT_TEMPLATE_444175481630097408","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":1125,"praise":null,"merchantId":"444175481630097408","name":"测试门店3","categoryCode":"101","telephone":"18761722685","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"12:30--14:30","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":"images/idCardImg/2018-05-10/444211821612630016f.jpeg","lon":"121.432596","lat":"31.348955","rqCode":"","judges":null},{"templateId":"MT_446358861603930112","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":1125,"praise":null,"merchantId":"446358861603930112","name":"宜春院","categoryCode":"101","telephone":"18761722685","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰路100号","businessTime":"12:30--14:30","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":null,"lon":"121.432596","lat":"31.348955","rqCode":"images/merchantDetailQrcodeImg/2018-05-17/446785379064872960.png","judges":null},{"templateId":"MT_446417033895084032","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":58,"praise":null,"merchantId":"446417033895084032","name":"哈哈哈","categoryCode":"107","telephone":"18956245257","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"5588555","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":"","lon":"121.432596","lat":"31.338955","rqCode":"images/merchantDetailQrcodeImg/2018-05-17/446785385050144768.png","judges":null},{"templateId":"MT_446419970490892288","avgScore":0,"prefix":"https://test-files.shzhuoji.com/","distance":58,"praise":null,"merchantId":"446419970490892288","name":"阿木木龙虾","categoryCode":"107","telephone":"18956245257","province":"310000","city":"310000","district":"310113","detailAddress":"呼兰西路100号","businessTime":"5588555","mainImg":"images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg","extraImg":"","lon":"121.432596","lat":"31.338955","rqCode":"images/merchantDetailQrcodeImg/2018-05-17/446785400036392960.png","judges":null}]
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
                 * templateId : MERCHANT_TEMPLATE_444174804405190657
                 * avgScore : 0
                 * prefix : https://test-files.shzhuoji.com/
                 * distance : 58.0
                 * praise : null
                 * merchantId : 444174804405190657
                 * name : 测试门店2
                 * categoryCode : 115
                 * telephone : 18888888888
                 * province : 310000
                 * city : 310000
                 * district : 310113
                 * detailAddress : 呼兰西路100号
                 * businessTime :
                 * mainImg : images/merchantDetailMainImg/2018-05-16/446420110844887040.jpg
                 * extraImg : null
                 * lon : 121.432596
                 * lat : 31.338955
                 * rqCode : images/licenseImg/2018-05-10/444175444565032960l.png
                 * judges : null
                 */

                private String templateId;
                private int avgScore;
                private String prefix;
                private double distance;
                private Object praise;
                private String merchantId;
                private String name;
                private String categoryCode;
                private String telephone;
                private String province;
                private String city;
                private String district;
                private String detailAddress;
                private String businessTime;
                private String mainImg;
                private Object extraImg;
                private String lon;
                private String lat;
                private String rqCode;
                private Object judges;

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

                public String getPrefix() {
                    return prefix;
                }

                public void setPrefix(String prefix) {
                    this.prefix = prefix;
                }

                public double getDistance() {
                    return distance;
                }

                public void setDistance(double distance) {
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

                public String getCategoryCode() {
                    return categoryCode;
                }

                public void setCategoryCode(String categoryCode) {
                    this.categoryCode = categoryCode;
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

                public Object getExtraImg() {
                    return extraImg;
                }

                public void setExtraImg(Object extraImg) {
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

                public Object getJudges() {
                    return judges;
                }

                public void setJudges(Object judges) {
                    this.judges = judges;
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
