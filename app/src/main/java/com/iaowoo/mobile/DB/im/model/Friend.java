package com.iaowoo.mobile.DB.im.model;

import android.text.TextUtils;

import org.litepal.crud.LitePalSupport;
import java.io.Serializable;

public class Friend extends LitePalSupport implements Serializable {
    private String userId;
    private String name;
    private String portraitUri;

    private String displayName;
    private String region;
    private String phoneNumber;
    private String status;
    private Long timestamp;
    private String letters;
    private String nameSpelling;
    private String displayNameSpelling;
    private Boolean isSelected = false;
    private String message;

    public Friend() {

    }

    public Friend(String userId, String displayName, String portraitUri) {
        this.userId = userId;
        this.displayName = displayName;
        this.portraitUri = portraitUri;
    }

    public Friend(String userId, String displayName, String portraitUri, String status, String message) {
        this(userId, displayName, portraitUri);
        this.status = status;
        this.message = message;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getNameSpelling() {
        return nameSpelling;
    }

    public void setNameSpelling(String nameSpelling) {
        this.nameSpelling = nameSpelling;
    }

    public String getDisplayNameSpelling() {
        return displayNameSpelling;
    }

    public void setDisplayNameSpelling(String displayNameSpelling) {
        this.displayNameSpelling = displayNameSpelling;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isExitsDisplayName() {
        return !TextUtils.isEmpty(displayName);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            Friend friendInfo = (Friend) o;
            return (getUserId() != null && getUserId().equals(friendInfo.getUserId()));
        } else {
            return false;
        }
    }
}
