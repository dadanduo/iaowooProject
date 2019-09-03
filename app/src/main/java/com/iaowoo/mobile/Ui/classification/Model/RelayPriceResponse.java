package com.iaowoo.mobile.Ui.classification.Model;

import com.google.gson.annotations.SerializedName;

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
 * @Description: ${todo}(计算真实价格)
 * @date 2018/11/28
 * @email ${18011009889@163.com}
 */
public class RelayPriceResponse {


    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"0":[{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityIdList":null,"activityId":"0","supplierId":null,"templateId":"SH_456457857424949248","subTemplateId":"456457857458503680","inviteCode":null,"buyNumber":3,"salePrice":1290,"sellPrice":1290,"totalSalePrice":3870,"totalSellPrice":3870,"subTemplateInfo":{"id":5,"createTime":"2018-06-13 14:00:33","updateTime":"2018-11-28 16:00:00","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"subTemplateId":"456457857458503680","templateId":"SH_456457857424949248","originalPrice":1490,"sellPrice":1290,"costPrice":894,"limitCount":null,"sales":377,"mainImage":"images/specificationsImg/2018-06-13/456457442163687424.jpg","specificationOneVal":"456457857236205568","specificationTwoVal":null,"specificationThreeVal":null,"stock":855,"pv":300,"status":1,"createUser":"","updateUser":"13167015675","buyNumber":null,"productId":null,"shareUserId":null,"specification_code1":null,"specification_code2":null,"specification_code3":null,"onLineShopId":null,"subCode":"5011990","innerSubCode":"E2018060005"},"shareUserId":null,"onLineShopId":null}]}}
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
         * content : {"0":[{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityIdList":null,"activityId":"0","supplierId":null,"templateId":"SH_456457857424949248","subTemplateId":"456457857458503680","inviteCode":null,"buyNumber":3,"salePrice":1290,"sellPrice":1290,"totalSalePrice":3870,"totalSellPrice":3870,"subTemplateInfo":{"id":5,"createTime":"2018-06-13 14:00:33","updateTime":"2018-11-28 16:00:00","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"subTemplateId":"456457857458503680","templateId":"SH_456457857424949248","originalPrice":1490,"sellPrice":1290,"costPrice":894,"limitCount":null,"sales":377,"mainImage":"images/specificationsImg/2018-06-13/456457442163687424.jpg","specificationOneVal":"456457857236205568","specificationTwoVal":null,"specificationThreeVal":null,"stock":855,"pv":300,"status":1,"createUser":"","updateUser":"13167015675","buyNumber":null,"productId":null,"shareUserId":null,"specification_code1":null,"specification_code2":null,"specification_code3":null,"onLineShopId":null,"subCode":"5011990","innerSubCode":"E2018060005"},"shareUserId":null,"onLineShopId":null}]}
         */

        private Object comment;
        private String content;

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
