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
 * @Description: ${todo}(查询配置信息MODLE)
 * @date 2018/10/26
 * @email ${18011009889@163.com}
 */
public class APPCONFIG {


    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"iOSVersion":[{"id":4,"createTime":"2018-08-09 10:29:07","updateTime":"2018-10-25 16:18:23","logicDelete":0,"configId":"4","configKey":"iOSVersion","configName":"iOS版本","version":"1.0.6","versionCode":10,"linkUrl":"https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk","forceUpdate":0,"description":"啦啦啦","apiUrl":"https://api.shbs008.com/","isReview":0,"status":1}],"AndroidVersion":[{"id":2,"createTime":"2018-05-14 18:49:20","updateTime":"2018-10-25 16:18:21","logicDelete":0,"configId":"2","configKey":"AndroidVersion","configName":"安卓版本","version":"1.0.6","versionCode":10,"linkUrl":"https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk","forceUpdate":1,"description":"啦啦啦","apiUrl":"https://api.shbs008.com/","isReview":0,"status":1},{"id":3,"createTime":"2018-07-03 09:25:08","updateTime":"2018-10-25 16:18:23","logicDelete":0,"configId":"3","configKey":"AndroidVersion","configName":"安卓版本","version":"1.0.2","versionCode":10,"linkUrl":"https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk","forceUpdate":1,"description":"啦啦啦","apiUrl":"https://api.shbs008.com/","isReview":0,"status":1}],"thirdLogin":[{"id":5,"createTime":"2018-08-09 10:31:06","updateTime":"2018-10-25 18:10:21","logicDelete":0,"configId":"5","configKey":"thirdLogin","configName":"第三方登录","version":"1.0.6","versionCode":10,"linkUrl":"https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk","forceUpdate":0,"description":"啦啦啦","apiUrl":"https://api.shbs008.com/","isReview":0,"status":0},{"id":7,"createTime":"2018-08-09 10:32:47","updateTime":"2018-10-25 16:18:32","logicDelete":0,"configId":"7","configKey":"thirdLogin","configName":"第三方登录","version":"1.0.6","versionCode":10,"linkUrl":"https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk","forceUpdate":1,"description":"啦啦啦","apiUrl":"https://api.shbs008.com/","isReview":0,"status":1}]}}
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
         * content : {"iOSVersion":[{"id":4,"createTime":"2018-08-09 10:29:07","updateTime":"2018-10-25 16:18:23","logicDelete":0,"configId":"4","configKey":"iOSVersion","configName":"iOS版本","version":"1.0.6","versionCode":10,"linkUrl":"https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk","forceUpdate":0,"description":"啦啦啦","apiUrl":"https://api.shbs008.com/","isReview":0,"status":1}],"AndroidVersion":[{"id":2,"createTime":"2018-05-14 18:49:20","updateTime":"2018-10-25 16:18:21","logicDelete":0,"configId":"2","configKey":"AndroidVersion","configName":"安卓版本","version":"1.0.6","versionCode":10,"linkUrl":"https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk","forceUpdate":1,"description":"啦啦啦","apiUrl":"https://api.shbs008.com/","isReview":0,"status":1},{"id":3,"createTime":"2018-07-03 09:25:08","updateTime":"2018-10-25 16:18:23","logicDelete":0,"configId":"3","configKey":"AndroidVersion","configName":"安卓版本","version":"1.0.2","versionCode":10,"linkUrl":"https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk","forceUpdate":1,"description":"啦啦啦","apiUrl":"https://api.shbs008.com/","isReview":0,"status":1}],"thirdLogin":[{"id":5,"createTime":"2018-08-09 10:31:06","updateTime":"2018-10-25 18:10:21","logicDelete":0,"configId":"5","configKey":"thirdLogin","configName":"第三方登录","version":"1.0.6","versionCode":10,"linkUrl":"https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk","forceUpdate":0,"description":"啦啦啦","apiUrl":"https://api.shbs008.com/","isReview":0,"status":0},{"id":7,"createTime":"2018-08-09 10:32:47","updateTime":"2018-10-25 16:18:32","logicDelete":0,"configId":"7","configKey":"thirdLogin","configName":"第三方登录","version":"1.0.6","versionCode":10,"linkUrl":"https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk","forceUpdate":1,"description":"啦啦啦","apiUrl":"https://api.shbs008.com/","isReview":0,"status":1}]}
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
            private List<IOSVersionBean> iOSVersion;
            private List<AndroidVersionBean> AndroidVersion;
            private List<ThirdLoginBean> thirdLogin;

            public List<IOSVersionBean> getIOSVersion() {
                return iOSVersion;
            }

            public void setIOSVersion(List<IOSVersionBean> iOSVersion) {
                this.iOSVersion = iOSVersion;
            }

            public List<AndroidVersionBean> getAndroidVersion() {
                return AndroidVersion;
            }

            public void setAndroidVersion(List<AndroidVersionBean> AndroidVersion) {
                this.AndroidVersion = AndroidVersion;
            }

            public List<ThirdLoginBean> getThirdLogin() {
                return thirdLogin;
            }

            public void setThirdLogin(List<ThirdLoginBean> thirdLogin) {
                this.thirdLogin = thirdLogin;
            }

            public static class IOSVersionBean {
                /**
                 * id : 4
                 * createTime : 2018-08-09 10:29:07
                 * updateTime : 2018-10-25 16:18:23
                 * logicDelete : 0
                 * configId : 4
                 * configKey : iOSVersion
                 * configName : iOS版本
                 * version : 1.0.6
                 * versionCode : 10
                 * linkUrl : https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk
                 * forceUpdate : 0
                 * description : 啦啦啦
                 * apiUrl : https://api.shbs008.com/
                 * isReview : 0
                 * status : 1
                 */

                private int id;
                private String createTime;
                private String updateTime;
                private int logicDelete;
                private String configId;
                private String configKey;
                private String configName;
                private String version;
                private int versionCode;
                private String linkUrl;
                private int forceUpdate;
                private String description;
                private String apiUrl;
                private int isReview;
                private int status;

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

                public String getConfigId() {
                    return configId;
                }

                public void setConfigId(String configId) {
                    this.configId = configId;
                }

                public String getConfigKey() {
                    return configKey;
                }

                public void setConfigKey(String configKey) {
                    this.configKey = configKey;
                }

                public String getConfigName() {
                    return configName;
                }

                public void setConfigName(String configName) {
                    this.configName = configName;
                }

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }

                public int getVersionCode() {
                    return versionCode;
                }

                public void setVersionCode(int versionCode) {
                    this.versionCode = versionCode;
                }

                public String getLinkUrl() {
                    return linkUrl;
                }

                public void setLinkUrl(String linkUrl) {
                    this.linkUrl = linkUrl;
                }

                public int getForceUpdate() {
                    return forceUpdate;
                }

                public void setForceUpdate(int forceUpdate) {
                    this.forceUpdate = forceUpdate;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getApiUrl() {
                    return apiUrl;
                }

                public void setApiUrl(String apiUrl) {
                    this.apiUrl = apiUrl;
                }

                public int getIsReview() {
                    return isReview;
                }

                public void setIsReview(int isReview) {
                    this.isReview = isReview;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }

            public static class AndroidVersionBean {
                /**
                 * id : 2
                 * createTime : 2018-05-14 18:49:20
                 * updateTime : 2018-10-25 16:18:21
                 * logicDelete : 0
                 * configId : 2
                 * configKey : AndroidVersion
                 * configName : 安卓版本
                 * version : 1.0.6
                 * versionCode : 10
                 * linkUrl : https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk
                 * forceUpdate : 1
                 * description : 啦啦啦
                 * apiUrl : https://api.shbs008.com/
                 * isReview : 0
                 * status : 1
                 */

                private int id;
                private String createTime;
                private String updateTime;
                private int logicDelete;
                private String configId;
                private String configKey;
                private String configName;
                private String version;
                private int versionCode;
                private String linkUrl;
                private int forceUpdate;
                private String description;
                private String apiUrl;
                private int isReview;
                private int status;

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

                public String getConfigId() {
                    return configId;
                }

                public void setConfigId(String configId) {
                    this.configId = configId;
                }

                public String getConfigKey() {
                    return configKey;
                }

                public void setConfigKey(String configKey) {
                    this.configKey = configKey;
                }

                public String getConfigName() {
                    return configName;
                }

                public void setConfigName(String configName) {
                    this.configName = configName;
                }

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }

                public int getVersionCode() {
                    return versionCode;
                }

                public void setVersionCode(int versionCode) {
                    this.versionCode = versionCode;
                }

                public String getLinkUrl() {
                    return linkUrl;
                }

                public void setLinkUrl(String linkUrl) {
                    this.linkUrl = linkUrl;
                }

                public int getForceUpdate() {
                    return forceUpdate;
                }

                public void setForceUpdate(int forceUpdate) {
                    this.forceUpdate = forceUpdate;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getApiUrl() {
                    return apiUrl;
                }

                public void setApiUrl(String apiUrl) {
                    this.apiUrl = apiUrl;
                }

                public int getIsReview() {
                    return isReview;
                }

                public void setIsReview(int isReview) {
                    this.isReview = isReview;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }

            public static class ThirdLoginBean {
                /**
                 * id : 5
                 * createTime : 2018-08-09 10:31:06
                 * updateTime : 2018-10-25 18:10:21
                 * logicDelete : 0
                 * configId : 5
                 * configKey : thirdLogin
                 * configName : 第三方登录
                 * version : 1.0.6
                 * versionCode : 10
                 * linkUrl : https://files.shbs008.com/app/android/plp_baoshi_v1.1.2.apk
                 * forceUpdate : 0
                 * description : 啦啦啦
                 * apiUrl : https://api.shbs008.com/
                 * isReview : 0
                 * status : 0
                 */

                private int id;
                private String createTime;
                private String updateTime;
                private int logicDelete;
                private String configId;
                private String configKey;
                private String configName;
                private String version;
                private int versionCode;
                private String linkUrl;
                private int forceUpdate;
                private String description;
                private String apiUrl;
                private int isReview;
                private int status;

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

                public String getConfigId() {
                    return configId;
                }

                public void setConfigId(String configId) {
                    this.configId = configId;
                }

                public String getConfigKey() {
                    return configKey;
                }

                public void setConfigKey(String configKey) {
                    this.configKey = configKey;
                }

                public String getConfigName() {
                    return configName;
                }

                public void setConfigName(String configName) {
                    this.configName = configName;
                }

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }

                public int getVersionCode() {
                    return versionCode;
                }

                public void setVersionCode(int versionCode) {
                    this.versionCode = versionCode;
                }

                public String getLinkUrl() {
                    return linkUrl;
                }

                public void setLinkUrl(String linkUrl) {
                    this.linkUrl = linkUrl;
                }

                public int getForceUpdate() {
                    return forceUpdate;
                }

                public void setForceUpdate(int forceUpdate) {
                    this.forceUpdate = forceUpdate;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getApiUrl() {
                    return apiUrl;
                }

                public void setApiUrl(String apiUrl) {
                    this.apiUrl = apiUrl;
                }

                public int getIsReview() {
                    return isReview;
                }

                public void setIsReview(int isReview) {
                    this.isReview = isReview;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
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
