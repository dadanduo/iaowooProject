package com.iaowoo.mobile.H5toAndroid.modle;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}()
 * @date 2018/6/25
 */
public class QIYUOUT {


    /**
     * crmParams : {"entrance":"我的","name":"Parm Warm","mobile":"17621326229","gender":"男","orderId":"","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/aOcajBpHUquIuuuAhI0QYIGCrdJMOLvPaYUDgloHX4sVPksuNON8MOv7afGfnmKgz1Yw2PfDPLHnWat3PhgbibA/132","groupId":"1163250"}
     */

    private CrmParamsBean crmParams;

    public CrmParamsBean getCrmParams() {
        return crmParams;
    }

    public void setCrmParams(CrmParamsBean crmParams) {
        this.crmParams = crmParams;
    }

    public static class CrmParamsBean {
        /**
         * entrance : 我的
         * name : Parm Warm
         * mobile : 17621326229
         * gender : 男
         * orderId :
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/aOcajBpHUquIuuuAhI0QYIGCrdJMOLvPaYUDgloHX4sVPksuNON8MOv7afGfnmKgz1Yw2PfDPLHnWat3PhgbibA/132
         * groupId : 1163250
         */

        private String entrance;
        private String name;
        private String mobile;
        private String gender;
        private String orderId;
        private String avatar;
        private String groupId;

        public String getEntrance() {
            return entrance;
        }

        public void setEntrance(String entrance) {
            this.entrance = entrance;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }
    }
}

