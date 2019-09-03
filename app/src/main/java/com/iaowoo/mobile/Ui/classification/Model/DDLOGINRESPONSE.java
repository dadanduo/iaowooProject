package com.iaowoo.mobile.Ui.classification.Model;

import java.io.Serializable;

/**
 * Created by chenda on 2018/4/16.
 */

public class DDLOGINRESPONSE implements Serializable{


    /**
     * data : {"accountId":30,"birthDay":"2018-03-11","depositMoney":0,"depositState":3,"email":"","idNO":"43082219880315001X","joinTime":"2018-03-11 10:27:09","money":8.74,"name":"钟腾","nameAuth":true,"phone":"18874886658","remark":"","sex":"","imageUrl":"","token":"b287f8bf-6254-4eaa-baa4-da95ef474cfc","updateTime":"2018-03-11 10:59:58","userId":129279}
     * ret : 1
     */

    private DataBean data;
    private int ret;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public static class DataBean {
        /**
         * accountId : 30
         * birthDay : 2018-03-11
         * depositMoney : 0
         * depositState : 3
         * email :
         * idNO : 43082219880315001X
         * joinTime : 2018-03-11 10:27:09
         * money : 8.74
         * name : 钟腾
         * nameAuth : true
         * phone : 18874886658
         * remark :
         * sex :
         * imageUrl :
         * token : b287f8bf-6254-4eaa-baa4-da95ef474cfc
         * updateTime : 2018-03-11 10:59:58
         * userId : 129279
         */

        private int accountId;
        private String birthDay;
        private int depositMoney;
        private int depositState;
        private String email;
        private String idNO;
        private String joinTime;
        private double money;
        private String name;
        private boolean nameAuth;
        private String phone;
        private String remark;
        private String sex;
        private String imageUrl;
        private String token;
        private String updateTime;
        private int userId;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getBirthDay() {
            return birthDay;
        }

        public void setBirthDay(String birthDay) {
            this.birthDay = birthDay;
        }

        public int getDepositMoney() {
            return depositMoney;
        }

        public void setDepositMoney(int depositMoney) {
            this.depositMoney = depositMoney;
        }

        public int getDepositState() {
            return depositState;
        }

        public void setDepositState(int depositState) {
            this.depositState = depositState;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIdNO() {
            return idNO;
        }

        public void setIdNO(String idNO) {
            this.idNO = idNO;
        }

        public String getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(String joinTime) {
            this.joinTime = joinTime;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isNameAuth() {
            return nameAuth;
        }

        public void setNameAuth(boolean nameAuth) {
            this.nameAuth = nameAuth;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
