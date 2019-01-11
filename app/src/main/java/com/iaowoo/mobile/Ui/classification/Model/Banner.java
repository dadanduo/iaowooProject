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
 * @Description: ${todo}(轮播实体类)
 * @date 2018/8/28
 * @email ${18011009889@163.com}
 */
public class Banner {

    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":1,"pageSize":15,"total":15,"pages":1,"list":[{"id":224,"createTime":"2018-08-27 09:33:24","updateTime":"2018-08-27 09:33:24","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"483569715847892992","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-27/483569704439386112.png","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":null,"businessType":1,"location":3,"bgColor":null,"startTime":"2018-08-27 00:00:00","startTimeQEnd":null,"endTime":"2018-09-09 23:59:59","endTimeQStart":null,"businessId":"PT_481485341174267904","createUser":"pbanner001","updateUser":"pbanner001","title":null},{"id":221,"createTime":"2018-08-24 11:15:58","updateTime":"2018-08-24 11:15:58","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"482508364492832768","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-24/482508318619729920.jpg","bannerDetailImg":"","jumpUrl":"https://m.shbs008.com/#/activity/qixiNewZone?t=金秋美丽风暴","sort":1,"type":1,"bannerDescribe":null,"businessType":null,"location":7,"bgColor":null,"startTime":"2018-08-24 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":null,"createUser":"admin","updateUser":"admin","title":"金秋美丽风暴"},{"id":223,"createTime":"2018-08-24 11:19:55","updateTime":"2018-08-24 11:19:55","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"482509358039564288","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-24/482509345460846592.png","bannerDetailImg":"","jumpUrl":"https://m.shbs008.com/#/activity/qixiNewZone","sort":1,"type":1,"bannerDescribe":null,"businessType":null,"location":12,"bgColor":null,"startTime":"2018-08-24 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":null,"createUser":"admin","updateUser":"admin","title":"金秋美丽风暴"},{"id":218,"createTime":"2018-08-23 19:41:20","updateTime":"2018-08-28 11:51:51","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"482273154165637120","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-28/483966931620069376.png","bannerDetailImg":"","jumpUrl":"https://m2.shzhuoji.com/#/activity/qixiNewZone","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":8,"bgColor":"#FAE3E8","startTime":"2018-08-23 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"po2018","title":"金秋美丽风暴"},{"id":210,"createTime":"2018-08-21 16:22:31","updateTime":"2018-08-27 09:25:24","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481498344045150208","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481498336428294144.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_481495567113912320","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":211,"createTime":"2018-08-21 16:24:22","updateTime":"2018-08-27 09:25:36","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481498812855091200","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481498793661956096.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_473511972294361088","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":212,"createTime":"2018-08-21 16:25:10","updateTime":"2018-08-27 09:25:51","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481499014009716736","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481499002831896576.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_474979951917924352","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":213,"createTime":"2018-08-21 16:26:09","updateTime":"2018-08-27 09:26:03","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481499262106992640","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481499252694974464.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_478631889293279232","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":214,"createTime":"2018-08-21 17:54:36","updateTime":"2018-08-27 09:26:14","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481521520586260480","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481521493910487040.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_481520120846352384","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":193,"createTime":"2018-08-16 11:59:07","updateTime":"2018-08-27 09:35:58","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"479620120088412160","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-16/479620069815484416.png","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":1,"location":3,"bgColor":null,"startTime":"2018-08-16 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"PT_479233383726055424","createUser":"pm2018","updateUser":"pbanner001","title":"1"},{"id":194,"createTime":"2018-08-16 22:01:28","updateTime":"2018-08-16 22:04:41","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"479771705602998272","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-16/479772503573528576.jpeg","bannerDetailImg":"","jumpUrl":null,"sort":1,"type":0,"bannerDescribe":"","businessType":null,"location":13,"bgColor":null,"startTime":"2018-08-16 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"admin","title":""},{"id":129,"createTime":"2018-08-01 19:21:23","updateTime":"2018-08-21 16:15:13","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474295600817373184","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481496280544051200.jpg","bannerDetailImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481496417194475520.jpg","jumpUrl":"https://m.shbs008.com/#/activity/qixiZone","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":10,"bgColor":null,"startTime":"2018-08-01 00:00:00","startTimeQEnd":null,"endTime":"2019-03-15 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"po2018","title":"明星产品爆款区，口碑王牌已驾到"},{"id":130,"createTime":"2018-08-01 19:24:11","updateTime":"2018-08-01 19:28:08","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474296306592907264","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-01/474296266981900288.png","bannerDetailImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-01/474296296543354880.png","jumpUrl":"https://m.shbs008.com/#/activity/bannerDetail","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":1,"bgColor":null,"startTime":"2018-08-01 00:00:00","startTimeQEnd":null,"endTime":"2018-09-30 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"admin","title":"新人注册大礼包"},{"id":135,"createTime":"2018-08-02 11:42:32","updateTime":"2018-08-21 19:26:16","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474542517690302464","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481544577866530816.jpg","bannerDetailImg":"","jumpUrl":"https://m.shbs008.com/#/activity/qixiZone","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":11,"bgColor":"#ffef8d","startTime":"2018-08-01 00:00:00","startTimeQEnd":null,"endTime":"2019-04-05 23:59:59","endTimeQStart":null,"businessId":"","createUser":"po2018","updateUser":"po2018","title":"明星产品爆款区"},{"id":225,"createTime":"2018-08-27 09:35:27","updateTime":"2018-08-27 09:35:27","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"483570230979723264","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-27/483570210394079232.png","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":2,"type":1,"bannerDescribe":null,"businessType":1,"location":3,"bgColor":null,"startTime":"2018-08-27 00:00:00","startTimeQEnd":null,"endTime":"2018-09-11 23:59:59","endTimeQStart":null,"businessId":"PT_482227775369904128","createUser":"pbanner001","updateUser":"pbanner001","title":null}]}}
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
         * content : {"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":1,"pageSize":15,"total":15,"pages":1,"list":[{"id":224,"createTime":"2018-08-27 09:33:24","updateTime":"2018-08-27 09:33:24","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"483569715847892992","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-27/483569704439386112.png","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":null,"businessType":1,"location":3,"bgColor":null,"startTime":"2018-08-27 00:00:00","startTimeQEnd":null,"endTime":"2018-09-09 23:59:59","endTimeQStart":null,"businessId":"PT_481485341174267904","createUser":"pbanner001","updateUser":"pbanner001","title":null},{"id":221,"createTime":"2018-08-24 11:15:58","updateTime":"2018-08-24 11:15:58","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"482508364492832768","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-24/482508318619729920.jpg","bannerDetailImg":"","jumpUrl":"https://m.shbs008.com/#/activity/qixiNewZone?t=金秋美丽风暴","sort":1,"type":1,"bannerDescribe":null,"businessType":null,"location":7,"bgColor":null,"startTime":"2018-08-24 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":null,"createUser":"admin","updateUser":"admin","title":"金秋美丽风暴"},{"id":223,"createTime":"2018-08-24 11:19:55","updateTime":"2018-08-24 11:19:55","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"482509358039564288","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-24/482509345460846592.png","bannerDetailImg":"","jumpUrl":"https://m.shbs008.com/#/activity/qixiNewZone","sort":1,"type":1,"bannerDescribe":null,"businessType":null,"location":12,"bgColor":null,"startTime":"2018-08-24 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":null,"createUser":"admin","updateUser":"admin","title":"金秋美丽风暴"},{"id":218,"createTime":"2018-08-23 19:41:20","updateTime":"2018-08-28 11:51:51","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"482273154165637120","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-28/483966931620069376.png","bannerDetailImg":"","jumpUrl":"https://m2.shzhuoji.com/#/activity/qixiNewZone","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":8,"bgColor":"#FAE3E8","startTime":"2018-08-23 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"po2018","title":"金秋美丽风暴"},{"id":210,"createTime":"2018-08-21 16:22:31","updateTime":"2018-08-27 09:25:24","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481498344045150208","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481498336428294144.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_481495567113912320","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":211,"createTime":"2018-08-21 16:24:22","updateTime":"2018-08-27 09:25:36","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481498812855091200","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481498793661956096.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_473511972294361088","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":212,"createTime":"2018-08-21 16:25:10","updateTime":"2018-08-27 09:25:51","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481499014009716736","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481499002831896576.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_474979951917924352","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":213,"createTime":"2018-08-21 16:26:09","updateTime":"2018-08-27 09:26:03","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481499262106992640","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481499252694974464.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_478631889293279232","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":214,"createTime":"2018-08-21 17:54:36","updateTime":"2018-08-27 09:26:14","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481521520586260480","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481521493910487040.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_481520120846352384","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":193,"createTime":"2018-08-16 11:59:07","updateTime":"2018-08-27 09:35:58","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"479620120088412160","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-16/479620069815484416.png","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":1,"location":3,"bgColor":null,"startTime":"2018-08-16 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"PT_479233383726055424","createUser":"pm2018","updateUser":"pbanner001","title":"1"},{"id":194,"createTime":"2018-08-16 22:01:28","updateTime":"2018-08-16 22:04:41","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"479771705602998272","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-16/479772503573528576.jpeg","bannerDetailImg":"","jumpUrl":null,"sort":1,"type":0,"bannerDescribe":"","businessType":null,"location":13,"bgColor":null,"startTime":"2018-08-16 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"admin","title":""},{"id":129,"createTime":"2018-08-01 19:21:23","updateTime":"2018-08-21 16:15:13","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474295600817373184","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481496280544051200.jpg","bannerDetailImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481496417194475520.jpg","jumpUrl":"https://m.shbs008.com/#/activity/qixiZone","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":10,"bgColor":null,"startTime":"2018-08-01 00:00:00","startTimeQEnd":null,"endTime":"2019-03-15 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"po2018","title":"明星产品爆款区，口碑王牌已驾到"},{"id":130,"createTime":"2018-08-01 19:24:11","updateTime":"2018-08-01 19:28:08","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474296306592907264","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-01/474296266981900288.png","bannerDetailImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-01/474296296543354880.png","jumpUrl":"https://m.shbs008.com/#/activity/bannerDetail","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":1,"bgColor":null,"startTime":"2018-08-01 00:00:00","startTimeQEnd":null,"endTime":"2018-09-30 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"admin","title":"新人注册大礼包"},{"id":135,"createTime":"2018-08-02 11:42:32","updateTime":"2018-08-21 19:26:16","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474542517690302464","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481544577866530816.jpg","bannerDetailImg":"","jumpUrl":"https://m.shbs008.com/#/activity/qixiZone","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":11,"bgColor":"#ffef8d","startTime":"2018-08-01 00:00:00","startTimeQEnd":null,"endTime":"2019-04-05 23:59:59","endTimeQStart":null,"businessId":"","createUser":"po2018","updateUser":"po2018","title":"明星产品爆款区"},{"id":225,"createTime":"2018-08-27 09:35:27","updateTime":"2018-08-27 09:35:27","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"483570230979723264","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-27/483570210394079232.png","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":2,"type":1,"bannerDescribe":null,"businessType":1,"location":3,"bgColor":null,"startTime":"2018-08-27 00:00:00","startTimeQEnd":null,"endTime":"2018-09-11 23:59:59","endTimeQStart":null,"businessId":"PT_482227775369904128","createUser":"pbanner001","updateUser":"pbanner001","title":null}]}
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
             * total : 15
             * pages : 1
             * list : [{"id":224,"createTime":"2018-08-27 09:33:24","updateTime":"2018-08-27 09:33:24","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"483569715847892992","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-27/483569704439386112.png","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":null,"businessType":1,"location":3,"bgColor":null,"startTime":"2018-08-27 00:00:00","startTimeQEnd":null,"endTime":"2018-09-09 23:59:59","endTimeQStart":null,"businessId":"PT_481485341174267904","createUser":"pbanner001","updateUser":"pbanner001","title":null},{"id":221,"createTime":"2018-08-24 11:15:58","updateTime":"2018-08-24 11:15:58","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"482508364492832768","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-24/482508318619729920.jpg","bannerDetailImg":"","jumpUrl":"https://m.shbs008.com/#/activity/qixiNewZone?t=金秋美丽风暴","sort":1,"type":1,"bannerDescribe":null,"businessType":null,"location":7,"bgColor":null,"startTime":"2018-08-24 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":null,"createUser":"admin","updateUser":"admin","title":"金秋美丽风暴"},{"id":223,"createTime":"2018-08-24 11:19:55","updateTime":"2018-08-24 11:19:55","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"482509358039564288","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-24/482509345460846592.png","bannerDetailImg":"","jumpUrl":"https://m.shbs008.com/#/activity/qixiNewZone","sort":1,"type":1,"bannerDescribe":null,"businessType":null,"location":12,"bgColor":null,"startTime":"2018-08-24 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":null,"createUser":"admin","updateUser":"admin","title":"金秋美丽风暴"},{"id":218,"createTime":"2018-08-23 19:41:20","updateTime":"2018-08-28 11:51:51","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"482273154165637120","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-28/483966931620069376.png","bannerDetailImg":"","jumpUrl":"https://m2.shzhuoji.com/#/activity/qixiNewZone","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":8,"bgColor":"#FAE3E8","startTime":"2018-08-23 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"po2018","title":"金秋美丽风暴"},{"id":210,"createTime":"2018-08-21 16:22:31","updateTime":"2018-08-27 09:25:24","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481498344045150208","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481498336428294144.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_481495567113912320","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":211,"createTime":"2018-08-21 16:24:22","updateTime":"2018-08-27 09:25:36","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481498812855091200","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481498793661956096.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_473511972294361088","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":212,"createTime":"2018-08-21 16:25:10","updateTime":"2018-08-27 09:25:51","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481499014009716736","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481499002831896576.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_474979951917924352","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":213,"createTime":"2018-08-21 16:26:09","updateTime":"2018-08-27 09:26:03","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481499262106992640","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481499252694974464.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_478631889293279232","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":214,"createTime":"2018-08-21 17:54:36","updateTime":"2018-08-27 09:26:14","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"481521520586260480","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481521493910487040.jpg","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":3,"location":1,"bgColor":null,"startTime":"2018-08-21 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"SH_481520120846352384","createUser":"pbanner001","updateUser":"pbanner001","title":"1"},{"id":193,"createTime":"2018-08-16 11:59:07","updateTime":"2018-08-27 09:35:58","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"479620120088412160","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-16/479620069815484416.png","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":1,"type":1,"bannerDescribe":"","businessType":1,"location":3,"bgColor":null,"startTime":"2018-08-16 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"PT_479233383726055424","createUser":"pm2018","updateUser":"pbanner001","title":"1"},{"id":194,"createTime":"2018-08-16 22:01:28","updateTime":"2018-08-16 22:04:41","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"479771705602998272","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-16/479772503573528576.jpeg","bannerDetailImg":"","jumpUrl":null,"sort":1,"type":0,"bannerDescribe":"","businessType":null,"location":13,"bgColor":null,"startTime":"2018-08-16 00:00:00","startTimeQEnd":null,"endTime":"2018-08-31 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"admin","title":""},{"id":129,"createTime":"2018-08-01 19:21:23","updateTime":"2018-08-21 16:15:13","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474295600817373184","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481496280544051200.jpg","bannerDetailImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481496417194475520.jpg","jumpUrl":"https://m.shbs008.com/#/activity/qixiZone","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":10,"bgColor":null,"startTime":"2018-08-01 00:00:00","startTimeQEnd":null,"endTime":"2019-03-15 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"po2018","title":"明星产品爆款区，口碑王牌已驾到"},{"id":130,"createTime":"2018-08-01 19:24:11","updateTime":"2018-08-01 19:28:08","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474296306592907264","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-01/474296266981900288.png","bannerDetailImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-01/474296296543354880.png","jumpUrl":"https://m.shbs008.com/#/activity/bannerDetail","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":1,"bgColor":null,"startTime":"2018-08-01 00:00:00","startTimeQEnd":null,"endTime":"2018-09-30 23:59:59","endTimeQStart":null,"businessId":"","createUser":"admin","updateUser":"admin","title":"新人注册大礼包"},{"id":135,"createTime":"2018-08-02 11:42:32","updateTime":"2018-08-21 19:26:16","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"474542517690302464","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-21/481544577866530816.jpg","bannerDetailImg":"","jumpUrl":"https://m.shbs008.com/#/activity/qixiZone","sort":1,"type":1,"bannerDescribe":"","businessType":null,"location":11,"bgColor":"#ffef8d","startTime":"2018-08-01 00:00:00","startTimeQEnd":null,"endTime":"2019-04-05 23:59:59","endTimeQStart":null,"businessId":"","createUser":"po2018","updateUser":"po2018","title":"明星产品爆款区"},{"id":225,"createTime":"2018-08-27 09:35:27","updateTime":"2018-08-27 09:35:27","logicDelete":0,"pageNum":null,"pageSize":null,"bannerId":"483570230979723264","bannerImg":"https://files.shzhuoji.com/images/bannerPictureImg/2018-08-27/483570210394079232.png","bannerDetailImg":"","jumpUrl":"https://m.shzhuoji.com/","sort":2,"type":1,"bannerDescribe":null,"businessType":1,"location":3,"bgColor":null,"startTime":"2018-08-27 00:00:00","startTimeQEnd":null,"endTime":"2018-09-11 23:59:59","endTimeQStart":null,"businessId":"PT_482227775369904128","createUser":"pbanner001","updateUser":"pbanner001","title":null}]
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
                 * id : 224
                 * createTime : 2018-08-27 09:33:24
                 * updateTime : 2018-08-27 09:33:24
                 * logicDelete : 0
                 * pageNum : null
                 * pageSize : null
                 * bannerId : 483569715847892992
                 * bannerImg : https://files.shzhuoji.com/images/bannerPictureImg/2018-08-27/483569704439386112.png
                 * bannerDetailImg :
                 * jumpUrl : https://m.shzhuoji.com/
                 * sort : 1
                 * type : 1
                 * bannerDescribe : null
                 * businessType : 1
                 * location : 3
                 * bgColor : null
                 * startTime : 2018-08-27 00:00:00
                 * startTimeQEnd : null
                 * endTime : 2018-09-09 23:59:59
                 * endTimeQStart : null
                 * businessId : PT_481485341174267904
                 * createUser : pbanner001
                 * updateUser : pbanner001
                 * title : null
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
                private Object bannerDescribe;
                private int businessType;
                private int location;
                private Object bgColor;
                private String startTime;
                private Object startTimeQEnd;
                private String endTime;
                private Object endTimeQStart;
                private String businessId;
                private String createUser;
                private String updateUser;
                private Object title;

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

                public Object getBannerDescribe() {
                    return bannerDescribe;
                }

                public void setBannerDescribe(Object bannerDescribe) {
                    this.bannerDescribe = bannerDescribe;
                }

                public int getBusinessType() {
                    return businessType;
                }

                public void setBusinessType(int businessType) {
                    this.businessType = businessType;
                }

                public int getLocation() {
                    return location;
                }

                public void setLocation(int location) {
                    this.location = location;
                }

                public Object getBgColor() {
                    return bgColor;
                }

                public void setBgColor(Object bgColor) {
                    this.bgColor = bgColor;
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

                public String getBusinessId() {
                    return businessId;
                }

                public void setBusinessId(String businessId) {
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

                public Object getTitle() {
                    return title;
                }

                public void setTitle(Object title) {
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
