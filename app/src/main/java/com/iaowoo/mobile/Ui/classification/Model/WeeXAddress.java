package com.iaowoo.mobile.Ui.classification.Model;

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
 * @date 2018/12/13
 * @email ${18011009889@163.com}
 */
public class WeeXAddress {

    /**
     * address : {"cityCode":"150500","createTime":"2018-10-31 11:28:02","deliveryId":"507153773815140352","districtCode":"150524","id":73,"isDefault":0,"logicDelete":0,"provinceCode":"150000","recipientsCity":"通辽市","recipientsDetailAddress":"兔子","recipientsDistrict":"库伦旗","recipientsMobilePhone":"13525253636","recipientsName":"兔子","recipientsProvince":"内蒙古","updateTime":"2018-10-31 11:28:02","usageCount":0,"userId":25}
     * type : 0
     */

    private AddressBean address;
    private String type;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class AddressBean {
        /**
         * cityCode : 150500
         * createTime : 2018-10-31 11:28:02
         * deliveryId : 507153773815140352
         * districtCode : 150524
         * id : 73
         * isDefault : 0
         * logicDelete : 0
         * provinceCode : 150000
         * recipientsCity : 通辽市
         * recipientsDetailAddress : 兔子
         * recipientsDistrict : 库伦旗
         * recipientsMobilePhone : 13525253636
         * recipientsName : 兔子
         * recipientsProvince : 内蒙古
         * updateTime : 2018-10-31 11:28:02
         * usageCount : 0
         * userId : 25
         */

        private String cityCode;
        private String createTime;
        private String deliveryId;
        private String districtCode;
        private int id;
        private int isDefault;
        private int logicDelete;
        private String provinceCode;
        private String recipientsCity;
        private String recipientsDetailAddress;
        private String recipientsDistrict;
        private String recipientsMobilePhone;
        private String recipientsName;
        private String recipientsProvince;
        private String updateTime;
        private int usageCount;
        private int userId;

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDeliveryId() {
            return deliveryId;
        }

        public void setDeliveryId(String deliveryId) {
            this.deliveryId = deliveryId;
        }

        public String getDistrictCode() {
            return districtCode;
        }

        public void setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public int getLogicDelete() {
            return logicDelete;
        }

        public void setLogicDelete(int logicDelete) {
            this.logicDelete = logicDelete;
        }

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getRecipientsCity() {
            return recipientsCity;
        }

        public void setRecipientsCity(String recipientsCity) {
            this.recipientsCity = recipientsCity;
        }

        public String getRecipientsDetailAddress() {
            return recipientsDetailAddress;
        }

        public void setRecipientsDetailAddress(String recipientsDetailAddress) {
            this.recipientsDetailAddress = recipientsDetailAddress;
        }

        public String getRecipientsDistrict() {
            return recipientsDistrict;
        }

        public void setRecipientsDistrict(String recipientsDistrict) {
            this.recipientsDistrict = recipientsDistrict;
        }

        public String getRecipientsMobilePhone() {
            return recipientsMobilePhone;
        }

        public void setRecipientsMobilePhone(String recipientsMobilePhone) {
            this.recipientsMobilePhone = recipientsMobilePhone;
        }

        public String getRecipientsName() {
            return recipientsName;
        }

        public void setRecipientsName(String recipientsName) {
            this.recipientsName = recipientsName;
        }

        public String getRecipientsProvince() {
            return recipientsProvince;
        }

        public void setRecipientsProvince(String recipientsProvince) {
            this.recipientsProvince = recipientsProvince;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUsageCount() {
            return usageCount;
        }

        public void setUsageCount(int usageCount) {
            this.usageCount = usageCount;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
