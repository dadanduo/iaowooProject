package com.iaowoo.mobile.H5toAndroid.modle;

public class WECHATTB {

    /**
     * wexinResp : {"uid":"","openid":"","name":"","iconurl":"","unionGender":""}
     */

    private WexinRespBean wexinResp;

    public WexinRespBean getWexinResp() {
        return wexinResp;
    }

    public void setWexinResp(WexinRespBean wexinResp) {
        this.wexinResp = wexinResp;
    }

    public static class WexinRespBean {
        /**
         * uid :
         * openid :
         * name :
         * iconurl :
         * unionGender :
         * city : dada
         * country : 2
         * province : d
         */

        private String uid;
        private String openid;
        private String name;
        private String iconurl;
        private String unionGender;
        private String city;
        private String country;
        private String province;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getUnionGender() {
            return unionGender;
        }

        public void setUnionGender(String unionGender) {
            this.unionGender = unionGender;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
