package com.iaowoo.mobile.Ui.classification.Model;

import java.io.Serializable;

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
 * @Description: ${todo}(WO DE )
 * @date 2018/9/17
 * @email ${18011009889@163.com}
 */
public class MYMESSAGE  implements Serializable {


    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"id":42,"createTime":"2018-09-17 10:29:03","updateTime":"2018-09-17 11:08:45","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":40,"name":"陈达","idCardNo":"511621199511266713","gender":1,"birthday":"1995年11月26日","idCardArea":"四川省广安市岳池县","authentication":1,"inviteCode":"WDORPU","idCardImgFront":"images/idCardImg/2018-09-17/491203835084148736.jpg","idCardImgBack":"images/idCardImg/2018-09-17/491203847339900928.jpg","idCardImgHold":"","memberCode":"20","isCityProxy":0,"isProvinceProxy":0,"isExperienceStore":0,"qrCodeImg":"https://test-files.shzhuoji.com/images/merchantDetailQrcodeImg/2018-09-17/491193866217586688.png","authTime":"2018-09-17 11:08:45","mobileNo":"17621326229","headImgUrl":"https://test-files.shzhuoji.com/images/businessProveImg/2018-09-17/491202497826783232.jpg","nickname":"铺连铺","password":"0","payPassword":"0","authenticationState":"0","alipayStatus":"0","alipayId":null,"alipayIdHide":null,"region":"中国","memberName":"2","inviteStatus":"0","checkScore":0,"nameHide":"*达","idCardNoHide":"5****************3","inviteName":null}}
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

    public static class HeadBean  implements Serializable{
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

    public static class BodyBean  implements Serializable{
        /**
         * comment : null
         * content : {"id":42,"createTime":"2018-09-17 10:29:03","updateTime":"2018-09-17 11:08:45","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"userId":40,"name":"陈达","idCardNo":"511621199511266713","gender":1,"birthday":"1995年11月26日","idCardArea":"四川省广安市岳池县","authentication":1,"inviteCode":"WDORPU","idCardImgFront":"images/idCardImg/2018-09-17/491203835084148736.jpg","idCardImgBack":"images/idCardImg/2018-09-17/491203847339900928.jpg","idCardImgHold":"","memberCode":"20","isCityProxy":0,"isProvinceProxy":0,"isExperienceStore":0,"qrCodeImg":"https://test-files.shzhuoji.com/images/merchantDetailQrcodeImg/2018-09-17/491193866217586688.png","authTime":"2018-09-17 11:08:45","mobileNo":"17621326229","headImgUrl":"https://test-files.shzhuoji.com/images/businessProveImg/2018-09-17/491202497826783232.jpg","nickname":"铺连铺","password":"0","payPassword":"0","authenticationState":"0","alipayStatus":"0","alipayId":null,"alipayIdHide":null,"region":"中国","memberName":"2","inviteStatus":"0","checkScore":0,"nameHide":"*达","idCardNoHide":"5****************3","inviteName":null}
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

        public static class ContentBean  implements Serializable{
            /**
             * id : 42
             * createTime : 2018-09-17 10:29:03
             * updateTime : 2018-09-17 11:08:45
             * logicDelete : 0
             * pageNum : null
             * pageSize : null
             * operator : null
             * userId : 40
             * name : 陈达
             * idCardNo : 511621199511266713
             * gender : 1
             * birthday : 1995年11月26日
             * idCardArea : 四川省广安市岳池县
             * authentication : 1
             * inviteCode : WDORPU
             * idCardImgFront : images/idCardImg/2018-09-17/491203835084148736.jpg
             * idCardImgBack : images/idCardImg/2018-09-17/491203847339900928.jpg
             * idCardImgHold :
             * memberCode : 20
             * isCityProxy : 0
             * isProvinceProxy : 0
             * isExperienceStore : 0
             * qrCodeImg : https://test-files.shzhuoji.com/images/merchantDetailQrcodeImg/2018-09-17/491193866217586688.png
             * authTime : 2018-09-17 11:08:45
             * mobileNo : 17621326229
             * headImgUrl : https://test-files.shzhuoji.com/images/businessProveImg/2018-09-17/491202497826783232.jpg
             * nickname : 铺连铺
             * password : 0
             * payPassword : 0
             * authenticationState : 0
             * alipayStatus : 0
             * alipayId : null
             * alipayIdHide : null
             * region : 中国
             * memberName : 2
             * inviteStatus : 0
             * checkScore : 0
             * nameHide : *达
             * idCardNoHide : 5****************3
             * inviteName : null
             */

            private int id;
            private String createTime;
            private String updateTime;
            private int logicDelete;
            private Object pageNum;
            private Object pageSize;
            private Object operator;
            private int userId;
            private String name;
            private String idCardNo;
            private int gender;
            private String birthday;
            private String idCardArea;
            private int authentication;
            private String inviteCode;
            private String idCardImgFront;
            private String idCardImgBack;
            private String idCardImgHold;
            private String memberCode;
            private int isCityProxy;
            private int isProvinceProxy;
            private int isExperienceStore;
            private String qrCodeImg;
            private String authTime;
            private String mobileNo;
            private String headImgUrl;
            private String nickname;
            private String password;
            private String payPassword;
            private String authenticationState;
            private String alipayStatus;
            private Object alipayId;
            private Object alipayIdHide;
            private String region;
            private String memberName;
            private String inviteStatus;
            private int checkScore;
            private String nameHide;
            private String idCardNoHide;
            private Object inviteName;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIdCardNo() {
                return idCardNo;
            }

            public void setIdCardNo(String idCardNo) {
                this.idCardNo = idCardNo;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getIdCardArea() {
                return idCardArea;
            }

            public void setIdCardArea(String idCardArea) {
                this.idCardArea = idCardArea;
            }

            public int getAuthentication() {
                return authentication;
            }

            public void setAuthentication(int authentication) {
                this.authentication = authentication;
            }

            public String getInviteCode() {
                return inviteCode;
            }

            public void setInviteCode(String inviteCode) {
                this.inviteCode = inviteCode;
            }

            public String getIdCardImgFront() {
                return idCardImgFront;
            }

            public void setIdCardImgFront(String idCardImgFront) {
                this.idCardImgFront = idCardImgFront;
            }

            public String getIdCardImgBack() {
                return idCardImgBack;
            }

            public void setIdCardImgBack(String idCardImgBack) {
                this.idCardImgBack = idCardImgBack;
            }

            public String getIdCardImgHold() {
                return idCardImgHold;
            }

            public void setIdCardImgHold(String idCardImgHold) {
                this.idCardImgHold = idCardImgHold;
            }

            public String getMemberCode() {
                return memberCode;
            }

            public void setMemberCode(String memberCode) {
                this.memberCode = memberCode;
            }

            public int getIsCityProxy() {
                return isCityProxy;
            }

            public void setIsCityProxy(int isCityProxy) {
                this.isCityProxy = isCityProxy;
            }

            public int getIsProvinceProxy() {
                return isProvinceProxy;
            }

            public void setIsProvinceProxy(int isProvinceProxy) {
                this.isProvinceProxy = isProvinceProxy;
            }

            public int getIsExperienceStore() {
                return isExperienceStore;
            }

            public void setIsExperienceStore(int isExperienceStore) {
                this.isExperienceStore = isExperienceStore;
            }

            public String getQrCodeImg() {
                return qrCodeImg;
            }

            public void setQrCodeImg(String qrCodeImg) {
                this.qrCodeImg = qrCodeImg;
            }

            public String getAuthTime() {
                return authTime;
            }

            public void setAuthTime(String authTime) {
                this.authTime = authTime;
            }

            public String getMobileNo() {
                return mobileNo;
            }

            public void setMobileNo(String mobileNo) {
                this.mobileNo = mobileNo;
            }

            public String getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getPayPassword() {
                return payPassword;
            }

            public void setPayPassword(String payPassword) {
                this.payPassword = payPassword;
            }

            public String getAuthenticationState() {
                return authenticationState;
            }

            public void setAuthenticationState(String authenticationState) {
                this.authenticationState = authenticationState;
            }

            public String getAlipayStatus() {
                return alipayStatus;
            }

            public void setAlipayStatus(String alipayStatus) {
                this.alipayStatus = alipayStatus;
            }

            public Object getAlipayId() {
                return alipayId;
            }

            public void setAlipayId(Object alipayId) {
                this.alipayId = alipayId;
            }

            public Object getAlipayIdHide() {
                return alipayIdHide;
            }

            public void setAlipayIdHide(Object alipayIdHide) {
                this.alipayIdHide = alipayIdHide;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getMemberName() {
                return memberName;
            }

            public void setMemberName(String memberName) {
                this.memberName = memberName;
            }

            public String getInviteStatus() {
                return inviteStatus;
            }

            public void setInviteStatus(String inviteStatus) {
                this.inviteStatus = inviteStatus;
            }

            public int getCheckScore() {
                return checkScore;
            }

            public void setCheckScore(int checkScore) {
                this.checkScore = checkScore;
            }

            public String getNameHide() {
                return nameHide;
            }

            public void setNameHide(String nameHide) {
                this.nameHide = nameHide;
            }

            public String getIdCardNoHide() {
                return idCardNoHide;
            }

            public void setIdCardNoHide(String idCardNoHide) {
                this.idCardNoHide = idCardNoHide;
            }

            public Object getInviteName() {
                return inviteName;
            }

            public void setInviteName(Object inviteName) {
                this.inviteName = inviteName;
            }
        }
    }

    public static class TailBean  implements Serializable{
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
