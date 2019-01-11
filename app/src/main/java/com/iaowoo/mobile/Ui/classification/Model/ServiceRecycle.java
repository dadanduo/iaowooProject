package com.iaowoo.mobile.Ui.classification.Model;

/**
 * Created by chenda on 2018/4/3.
 */

public class ServiceRecycle {

        private String imagurl;

        private String distance;

        private String describe;

        private int price;

        public void setImagurl(String imagurl){
            this.imagurl = imagurl;
        }
        public String getImagurl(){
            return this.imagurl;
        }
        public void setDistance(String distance){
            this.distance = distance;
        }
        public String getDistance(){
            return this.distance;
        }
        public void setDescribe(String describe){
            this.describe = describe;
        }
        public String getDescribe(){
            return this.describe;
        }
        public void setPrice(int price){
            this.price = price;
        }
        public int getPrice(){
            return this.price;
        }

}
