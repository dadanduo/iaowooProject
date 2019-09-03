package com.iaowoo.mobile.H5toAndroid.modle;

public class PICTURE {

    /**
     * Respone : {"prefix":"http://test-files.shzhuoji.com/","imageUrl":" "}
     */

    private ResponeBean Respone;

    public ResponeBean getRespone() {
        return Respone;
    }

    public void setRespone(ResponeBean Respone) {
        this.Respone = Respone;
    }

    public static class ResponeBean {
        /**
         * prefix : http://test-files.shzhuoji.com/
         * imageUrl :
         */

        private String prefix;
        private String imageUrl;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
