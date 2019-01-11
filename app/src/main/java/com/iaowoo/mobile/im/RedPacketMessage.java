package com.iaowoo.mobile.im;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

@SuppressLint("ParcelCreator")
@MessageTag(value = "PP:RedEnvelopeMsg", flag = MessageTag.ISPERSISTED | MessageTag.ISCOUNTED)
public class RedPacketMessage extends MessageContent {
    public static final Creator<RedPacketMessage> CREATOR = new Creator<RedPacketMessage>() {

        @Override
        public RedPacketMessage createFromParcel(Parcel source) {
            return new RedPacketMessage(source);
        }

        @Override
        public RedPacketMessage[] newArray(int size) {
            return new RedPacketMessage[size];
        }
    };
    private String remark;
    private String amount;
    private String extra; // 0/messageUId 1/messageUId 其中0 ，1各代表红包未领取和红包领取的状态

    public RedPacketMessage() {

    }

    public RedPacketMessage(byte[] data) {
        super(data);
        String jsonStr = null;
        try {
            jsonStr = new String(data, "UTF-8");
            JSONObject object = JSON.parseObject(jsonStr);
            setRemark(object.getString("remark"));
            setAmount(object.getString("amount"));
            setExtra(object.getString("extra"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RedPacketMessage(Parcel parcel) {
        amount = ParcelUtils.readFromParcel(parcel);
        remark = ParcelUtils.readFromParcel(parcel);
        extra = ParcelUtils.readFromParcel(parcel);
        this.setUserInfo((UserInfo) ParcelUtils.readFromParcel(parcel, UserInfo.class));
    }

    public static RedPacketMessage setRedPacketData(String remark, String amount, String extra) {
        RedPacketMessage info = new RedPacketMessage();
        info.remark = remark;
        info.amount = amount;
        info.extra = extra;
        return info;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public byte[] encode() {
        JSONObject object = new JSONObject();
        object.put("remark", remark);
        object.put("amount", amount);
        object.put("extra", extra);
        try {
            return object.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ParcelUtils.writeToParcel(dest, remark);
        ParcelUtils.writeToParcel(dest, amount);
        ParcelUtils.writeToParcel(dest, extra);
        ParcelUtils.writeToParcel(dest, this.getUserInfo());
    }
}
