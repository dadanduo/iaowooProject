package com.iaowoo.mobile.Ui.classification.Model;

import java.util.List;

/**
 * Created by chenda on 2018/4/17.
 */

public class PARTPOINT {

    /**
     * data : [{"accountId":59,"allowRange":100,"canBorrowNum":0,"la":31.345146,"laC":31.343329,"lo":121.438254,"loC":121.442881,"name":"乐活家","parkPointId":3335,"remark":""}]
     * ret : 1
     */

    private int ret;
    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * accountId : 59
         * allowRange : 100
         * canBorrowNum : 0
         * la : 31.345146
         * laC : 31.343329
         * lo : 121.438254
         * loC : 121.442881
         * name : 乐活家
         * parkPointId : 3335
         * remark :
         */

        private int accountId;
        private int allowRange;
        private int canBorrowNum;
        private double la;
        private double laC;
        private double lo;
        private double loC;
        private String name;
        private int parkPointId;
        private String remark;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public int getAllowRange() {
            return allowRange;
        }

        public void setAllowRange(int allowRange) {
            this.allowRange = allowRange;
        }

        public int getCanBorrowNum() {
            return canBorrowNum;
        }

        public void setCanBorrowNum(int canBorrowNum) {
            this.canBorrowNum = canBorrowNum;
        }

        public double getLa() {
            return la;
        }

        public void setLa(double la) {
            this.la = la;
        }

        public double getLaC() {
            return laC;
        }

        public void setLaC(double laC) {
            this.laC = laC;
        }

        public double getLo() {
            return lo;
        }

        public void setLo(double lo) {
            this.lo = lo;
        }

        public double getLoC() {
            return loC;
        }

        public void setLoC(double loC) {
            this.loC = loC;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParkPointId() {
            return parkPointId;
        }

        public void setParkPointId(int parkPointId) {
            this.parkPointId = parkPointId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
