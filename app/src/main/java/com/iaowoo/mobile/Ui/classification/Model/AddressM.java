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
 * @Description: ${todo}(默认地址modle)
 * @date 2018/11/28
 * @email ${18011009889@163.com}
 */
public class AddressM {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":[{"id":5,"createTime":"2018-09-11 16:58:23","updateTime":"2018-09-13 19:02:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":5,"deliveryId":"489117518087065600","recipientsName":"我的","recipientsMobilePhone":"13512345678","recipientsProvince":"天津","recipientsCity":"天津市","recipientsDistrict":"和平区","recipientsDetailAddress":"和平","isDefault":1,"usageCount":0,"provinceCode":"120000","cityCode":"120100","districtCode":"120101"},{"id":9,"createTime":"2018-09-13 19:02:36","updateTime":"2018-09-13 19:02:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":5,"deliveryId":"489873554473488384","recipientsName":"222","recipientsMobilePhone":"18334771358","recipientsProvince":"北京","recipientsCity":"北京市","recipientsDistrict":"东城区","recipientsDetailAddress":"222","isDefault":0,"usageCount":0,"provinceCode":"110000","cityCode":"110100","districtCode":"110101"},{"id":173,"createTime":"2018-11-08 09:59:50","updateTime":"2018-11-08 09:59:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":5,"deliveryId":"510030680776769536","recipientsName":"控制","recipientsMobilePhone":"13167015675","recipientsProvince":"河北省","recipientsCity":"秦皇岛市","recipientsDistrict":"山海关区","recipientsDetailAddress":"兔子","isDefault":0,"usageCount":0,"provinceCode":"130000","cityCode":"130300","districtCode":"130303"},{"id":174,"createTime":"2018-11-08 09:59:51","updateTime":"2018-11-08 09:59:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":5,"deliveryId":"510030687164694528","recipientsName":"控制","recipientsMobilePhone":"13167015675","recipientsProvince":"河北省","recipientsCity":"秦皇岛市","recipientsDistrict":"山海关区","recipientsDetailAddress":"兔子","isDefault":0,"usageCount":0,"provinceCode":"130000","cityCode":"130300","districtCode":"130303"},{"id":175,"createTime":"2018-11-08 09:59:52","updateTime":"2018-11-08 09:59:52","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":5,"deliveryId":"510030689375092736","recipientsName":"控制","recipientsMobilePhone":"13167015675","recipientsProvince":"河北省","recipientsCity":"秦皇岛市","recipientsDistrict":"山海关区","recipientsDetailAddress":"兔子","isDefault":0,"usageCount":0,"provinceCode":"130000","cityCode":"130300","districtCode":"130303"}]}
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
         * content : [{"id":5,"createTime":"2018-09-11 16:58:23","updateTime":"2018-09-13 19:02:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":5,"deliveryId":"489117518087065600","recipientsName":"我的","recipientsMobilePhone":"13512345678","recipientsProvince":"天津","recipientsCity":"天津市","recipientsDistrict":"和平区","recipientsDetailAddress":"和平","isDefault":1,"usageCount":0,"provinceCode":"120000","cityCode":"120100","districtCode":"120101"},{"id":9,"createTime":"2018-09-13 19:02:36","updateTime":"2018-09-13 19:02:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":5,"deliveryId":"489873554473488384","recipientsName":"222","recipientsMobilePhone":"18334771358","recipientsProvince":"北京","recipientsCity":"北京市","recipientsDistrict":"东城区","recipientsDetailAddress":"222","isDefault":0,"usageCount":0,"provinceCode":"110000","cityCode":"110100","districtCode":"110101"},{"id":173,"createTime":"2018-11-08 09:59:50","updateTime":"2018-11-08 09:59:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":5,"deliveryId":"510030680776769536","recipientsName":"控制","recipientsMobilePhone":"13167015675","recipientsProvince":"河北省","recipientsCity":"秦皇岛市","recipientsDistrict":"山海关区","recipientsDetailAddress":"兔子","isDefault":0,"usageCount":0,"provinceCode":"130000","cityCode":"130300","districtCode":"130303"},{"id":174,"createTime":"2018-11-08 09:59:51","updateTime":"2018-11-08 09:59:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":5,"deliveryId":"510030687164694528","recipientsName":"控制","recipientsMobilePhone":"13167015675","recipientsProvince":"河北省","recipientsCity":"秦皇岛市","recipientsDistrict":"山海关区","recipientsDetailAddress":"兔子","isDefault":0,"usageCount":0,"provinceCode":"130000","cityCode":"130300","districtCode":"130303"},{"id":175,"createTime":"2018-11-08 09:59:52","updateTime":"2018-11-08 09:59:52","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":5,"deliveryId":"510030689375092736","recipientsName":"控制","recipientsMobilePhone":"13167015675","recipientsProvince":"河北省","recipientsCity":"秦皇岛市","recipientsDistrict":"山海关区","recipientsDetailAddress":"兔子","isDefault":0,"usageCount":0,"provinceCode":"130000","cityCode":"130300","districtCode":"130303"}]
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
             * id : 5
             * createTime : 2018-09-11 16:58:23
             * updateTime : 2018-09-13 19:02:51
             * logicDelete : 0
             * pageNum : null
             * pageSize : null
             * operator : null
             * userId : 5
             * deliveryId : 489117518087065600
             * recipientsName : 我的
             * recipientsMobilePhone : 13512345678
             * recipientsProvince : 天津
             * recipientsCity : 天津市
             * recipientsDistrict : 和平区
             * recipientsDetailAddress : 和平
             * isDefault : 1
             * usageCount : 0
             * provinceCode : 120000
             * cityCode : 120100
             * districtCode : 120101
             */

            private int id;
            private String createTime;
            private String updateTime;
            private int logicDelete;
            private Object pageNum;
            private Object pageSize;
            private Object operator;
            private int userId;
            private String deliveryId;
            private String recipientsName;
            private String recipientsMobilePhone;
            private String recipientsProvince;
            private String recipientsCity;
            private String recipientsDistrict;
            private String recipientsDetailAddress;
            private int isDefault;
            private int usageCount;
            private String provinceCode;
            private String cityCode;
            private String districtCode;

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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getDeliveryId() {
                return deliveryId;
            }

            public void setDeliveryId(String deliveryId) {
                this.deliveryId = deliveryId;
            }

            public String getRecipientsName() {
                return recipientsName;
            }

            public void setRecipientsName(String recipientsName) {
                this.recipientsName = recipientsName;
            }

            public String getRecipientsMobilePhone() {
                return recipientsMobilePhone;
            }

            public void setRecipientsMobilePhone(String recipientsMobilePhone) {
                this.recipientsMobilePhone = recipientsMobilePhone;
            }

            public String getRecipientsProvince() {
                return recipientsProvince;
            }

            public void setRecipientsProvince(String recipientsProvince) {
                this.recipientsProvince = recipientsProvince;
            }

            public String getRecipientsCity() {
                return recipientsCity;
            }

            public void setRecipientsCity(String recipientsCity) {
                this.recipientsCity = recipientsCity;
            }

            public String getRecipientsDistrict() {
                return recipientsDistrict;
            }

            public void setRecipientsDistrict(String recipientsDistrict) {
                this.recipientsDistrict = recipientsDistrict;
            }

            public String getRecipientsDetailAddress() {
                return recipientsDetailAddress;
            }

            public void setRecipientsDetailAddress(String recipientsDetailAddress) {
                this.recipientsDetailAddress = recipientsDetailAddress;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }

            public int getUsageCount() {
                return usageCount;
            }

            public void setUsageCount(int usageCount) {
                this.usageCount = usageCount;
            }

            public String getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            public String getCityCode() {
                return cityCode;
            }

            public void setCityCode(String cityCode) {
                this.cityCode = cityCode;
            }

            public String getDistrictCode() {
                return districtCode;
            }

            public void setDistrictCode(String districtCode) {
                this.districtCode = districtCode;
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
