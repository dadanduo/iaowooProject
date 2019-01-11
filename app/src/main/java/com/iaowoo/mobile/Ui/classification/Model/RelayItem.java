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
 * @Description: ${todo}()
 * @date 2018/11/28
 * @email ${18011009889@163.com}
 */
public class RelayItem {


    private List<JsonBean> json;

    public List<JsonBean> getJson() {
        return json;
    }

    public void setJson(List<JsonBean> json) {
        this.json = json;
    }

    public static class JsonBean {
        /**
         * activityId : 0
         * buyNumber : 1
         * logicDelete : 0
         * salePrice : 0.05
         * sellPrice : 0.05
         * subTemplateId : 514481611434102784
         * subTemplateInfo : {"costPrice":0.01,"createTime":"2018-11-20 16:46:14","createUser":"13156756677","id":2590,"innerSubCode":"cq1","logicDelete":0,"mainImage":"images/articlesInChina/2018-11-20/514480846321750016.jpg","originalPrice":0.1,"pv":0.01,"sales":0,"sellPrice":0.05,"specificationOneVal":"514481611337633792","status":1,"stock":10000,"subCode":"cqq","subTemplateId":"514481611434102784","templateId":"DP_514481611396354048","updateTime":"2018-11-20 20:00:00","updateUser":"13156756677"}
         * templateId : DP_514481611396354048
         * totalSalePrice : 0.05
         * totalSellPrice : 0.05
         */

        private String activityId;
        private int buyNumber;
        private int logicDelete;
        private double salePrice;
        private double sellPrice;
        private String subTemplateId;
        private SubTemplateInfoBean subTemplateInfo;
        private String templateId;
        private double totalSalePrice;
        private double totalSellPrice;

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public int getBuyNumber() {
            return buyNumber;
        }

        public void setBuyNumber(int buyNumber) {
            this.buyNumber = buyNumber;
        }

        public int getLogicDelete() {
            return logicDelete;
        }

        public void setLogicDelete(int logicDelete) {
            this.logicDelete = logicDelete;
        }

        public double getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(double salePrice) {
            this.salePrice = salePrice;
        }

        public double getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(double sellPrice) {
            this.sellPrice = sellPrice;
        }

        public String getSubTemplateId() {
            return subTemplateId;
        }

        public void setSubTemplateId(String subTemplateId) {
            this.subTemplateId = subTemplateId;
        }

        public SubTemplateInfoBean getSubTemplateInfo() {
            return subTemplateInfo;
        }

        public void setSubTemplateInfo(SubTemplateInfoBean subTemplateInfo) {
            this.subTemplateInfo = subTemplateInfo;
        }

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        public double getTotalSalePrice() {
            return totalSalePrice;
        }

        public void setTotalSalePrice(double totalSalePrice) {
            this.totalSalePrice = totalSalePrice;
        }

        public double getTotalSellPrice() {
            return totalSellPrice;
        }

        public void setTotalSellPrice(double totalSellPrice) {
            this.totalSellPrice = totalSellPrice;
        }

        public static class SubTemplateInfoBean {
            /**
             * costPrice : 0.01
             * createTime : 2018-11-20 16:46:14
             * createUser : 13156756677
             * id : 2590
             * innerSubCode : cq1
             * logicDelete : 0
             * mainImage : images/articlesInChina/2018-11-20/514480846321750016.jpg
             * originalPrice : 0.1
             * pv : 0.01
             * sales : 0
             * sellPrice : 0.05
             * specificationOneVal : 514481611337633792
             * status : 1
             * stock : 10000
             * subCode : cqq
             * subTemplateId : 514481611434102784
             * templateId : DP_514481611396354048
             * updateTime : 2018-11-20 20:00:00
             * updateUser : 13156756677
             */

            private double costPrice;
            private String createTime;
            private String createUser;
            private int id;
            private String innerSubCode;
            private int logicDelete;
            private String mainImage;
            private double originalPrice;
            private double pv;
            private int sales;
            private double sellPrice;
            private String specificationOneVal;
            private int status;
            private int stock;
            private String subCode;
            private String subTemplateId;
            private String templateId;
            private String updateTime;
            private String updateUser;

            public double getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(double costPrice) {
                this.costPrice = costPrice;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getInnerSubCode() {
                return innerSubCode;
            }

            public void setInnerSubCode(String innerSubCode) {
                this.innerSubCode = innerSubCode;
            }

            public int getLogicDelete() {
                return logicDelete;
            }

            public void setLogicDelete(int logicDelete) {
                this.logicDelete = logicDelete;
            }

            public String getMainImage() {
                return mainImage;
            }

            public void setMainImage(String mainImage) {
                this.mainImage = mainImage;
            }

            public double getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(double originalPrice) {
                this.originalPrice = originalPrice;
            }

            public double getPv() {
                return pv;
            }

            public void setPv(double pv) {
                this.pv = pv;
            }

            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }

            public double getSellPrice() {
                return sellPrice;
            }

            public void setSellPrice(double sellPrice) {
                this.sellPrice = sellPrice;
            }

            public String getSpecificationOneVal() {
                return specificationOneVal;
            }

            public void setSpecificationOneVal(String specificationOneVal) {
                this.specificationOneVal = specificationOneVal;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public String getSubCode() {
                return subCode;
            }

            public void setSubCode(String subCode) {
                this.subCode = subCode;
            }

            public String getSubTemplateId() {
                return subTemplateId;
            }

            public void setSubTemplateId(String subTemplateId) {
                this.subTemplateId = subTemplateId;
            }

            public String getTemplateId() {
                return templateId;
            }

            public void setTemplateId(String templateId) {
                this.templateId = templateId;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }
        }
    }
}
