package com.iaowoo.mobile.Ui.classification.Model.im;

import com.iaowoo.mobile.DB.im.model.Friend;
import com.iaowoo.mobile.DB.im.model.GroupMember;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResult {
    private String filterStr;
    private List<Friend> filterFriendList;
    private Map<String, List<GroupMember>> filterGroupNameListMap = new HashMap<>();
    private Map<String, List<GroupMember>> filterGroupMemberNameListMap = new HashMap<>();
    private List<String> filterGroupId;

    public SearchResult() {

    }

    public String getFilterStr() {
        return filterStr;
    }

    public void setFilterStr(String filterStr) {
        this.filterStr = filterStr;
    }

    public List<Friend> getFilterFriendList() {
        return filterFriendList;
    }

    public void setFilterFriendList(List<Friend> filterFriendList) {
        this.filterFriendList = filterFriendList;
    }

    public Map<String, List<GroupMember>> getFilterGroupNameListMap() {
        return filterGroupNameListMap;
    }

    public void setFilterGroupNameListMap(Map<String, List<GroupMember>> filterGroupNameListMap) {
        this.filterGroupNameListMap = filterGroupNameListMap;
    }

    public Map<String, List<GroupMember>> getFilterGroupMemberNameListMap() {
        return filterGroupMemberNameListMap;
    }

    public void setFilterGroupMemberNameListMap(Map<String, List<GroupMember>> filterGroupMemberNameListMap) {
        this.filterGroupMemberNameListMap = filterGroupMemberNameListMap;
    }

    public List<String> getFilterGroupId() {
        return filterGroupId;
    }

    public void setFilterGroupId(List<String> filterGroupId) {
        this.filterGroupId = filterGroupId;
    }
}
