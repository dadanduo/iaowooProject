package com.iaowoo.mobile.Ui.classification.Model;

/**
 * Created by chenda on 2018/4/10.
 */

public class ResponseOrder {


    /**
     * state : true
     * code : 0
     * score : 0
     * msg : success
     * results : {"return_code":"SUCCESS","return_msg":"OK","appid":"wx417dadd53038883b","mch_id":"1488377852","nonce_str":"aK5UmYQ6XnxRhLUs","sign":"E4D03D874D03DC75CBF46055BECEE02B","result_code":"SUCCESS","prepay_id":"wx10101240949810a0a82f09640061878492","trade_type":"APP"}
     */

    private boolean state;
    private String code;
    private String score;
    private String msg;
    private ResultsBean results;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * return_code : SUCCESS
         * return_msg : OK
         * appid : wx417dadd53038883b
         * mch_id : 1488377852
         * nonce_str : aK5UmYQ6XnxRhLUs
         * sign : E4D03D874D03DC75CBF46055BECEE02B
         * result_code : SUCCESS
         * prepay_id : wx10101240949810a0a82f09640061878492
         * trade_type : APP
         */

        private String return_code;
        private String return_msg;
        private String appid;
        private String mch_id;
        private String nonce_str;
        private String sign;
        private String result_code;
        private String prepay_id;
        private String trade_type;

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }
    }
}
