package com.iaowoo.mobile.DB.im;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.DB.im.model.Friend;
import com.iaowoo.mobile.DB.im.model.GroupMember;
import com.iaowoo.mobile.DB.im.model.Groups;
import com.iaowoo.mobile.Ui.classification.Model.im.FriendshipListResponse;
import com.iaowoo.mobile.Ui.classification.Model.im.GroupListResponse;
import com.iaowoo.mobile.im.RongGenerate;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import io.rong.imlib.model.UserInfo;

public class DBManager {
    private static DBManager mInstance;
    private LinkedHashMap<String, UserInfo> mUserInfoCache;

    private DBManager() {
        mUserInfoCache = new LinkedHashMap<>();
    }

    public static DBManager getInstance() {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 登录时同步好友，群组，群组成员，黑名单数据
     */
    public void getAllUserInfo(Context context) {
        String loginToken = PrefManager.getInstance().getToken();
        if (!TextUtils.isEmpty(loginToken)) {
            fetchFriends(context, loginToken);
            fetchGroups(context, loginToken);
        }
    }

    public void fetchFriends(Context context, String loginToken) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",loginToken);
        OkhttpManager.getInstance(context).requestPostByAsyn(false, UtilsOkHttpAll.IM_QUERY_FRIEND_LIST, "IM_QUERY_FRIEND_LIST", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                FriendshipListResponse friendshipListResponse = JSON.parseObject(result.toString(), FriendshipListResponse.class);
                if (friendshipListResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    FriendshipListResponse.BodyBean.ContentBean contentBean = friendshipListResponse.getBody().getContent();
                    if (contentBean != null) {
                        List<FriendshipListResponse.BodyBean.ContentBean.ListBean> list = contentBean.getList();
                        if (list != null && list.size() > 0) {
                            deleteFriends();
                            saveFriends(list);
                        }
                    }
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }

    public void fetchGroups(Context context, String loginToken) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",loginToken);
        OkhttpManager.getInstance(context).requestPostByAsyn(false, UtilsOkHttpAll.IM_QUERY_GROUP_LIST, "IM_QUERY_GROUP_LIST", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                Log.i("TAG", "fetchGroups:" + result.toString());
                GroupListResponse groupListResponse = JSON.parseObject(result.toString(), GroupListResponse.class);
                if (groupListResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    GroupListResponse.BodyBean.ContentBean contentBean = groupListResponse.getBody().getContent();
                    if (contentBean != null) {
//                        List<GroupListResponse.BodyBean.ContentBean.ListBean> list = contentBean.getList();
//                        if (list != null && list.size() > 0) {

//                        }
                    }
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }

    /*public void queryFriendList(Context context, String loginToken) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",loginToken);
        OkhttpManager.getInstance(context).requestPostByAsyn(true, UtilsOkHttpAll.IM_QUERY_FRIEND_LIST, "IM_QUERY_FRIEND_LIST", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                Log.i("TAG", result.toString());
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }*/





    public boolean isMyFriend(String userId) {
        Friend friend = getFriendById(userId);
        if (friend != null) {
            return true;
        }
        return false;
    }

    public boolean isMe(String userId) {
        if (PrefManager.getInstance().getRongIMUserId().equals(userId)) {
            return true;
        }
        return false;
    }

    /*================== Friend begin ==================*/
    public synchronized List<Friend> getFriends() {
        return LitePal.findAll(Friend.class);
    }

    public synchronized List<Friend> getFriendsByFilter(String filterStr) {
        if (!TextUtils.isEmpty(filterStr)) {
            List<Friend> friends = LitePal
                    .where("name like ? or displayName like ? or nameSpelling like ? or displayNameSpelling like ? " , filterStr, filterStr, filterStr, filterStr)
                    .order("nameSpelling")
                    .find(Friend.class);
            return friends;
        }
        return null;
    }

    public synchronized Friend getFriendById(String userid) {
        if (!TextUtils.isEmpty(userid)) {
            List<Friend> friends = LitePal.where("userid = ?", userid).find(Friend.class);
            if (friends != null && friends.size() > 0)
                return friends.get(0);
        }
        return null;
    }

    public synchronized void saveOrUpdateFriend(Friend friend) {
        if (friend != null) {
            String portrait = friend.getPortraitUri();
            if (TextUtils.isEmpty(portrait)) {
                portrait = RongGenerate.generateDefaultAvatar(friend.getName(), friend.getUserId());
                friend.setPortraitUri(portrait);
            }
            friend.saveOrUpdate("userid = ?", friend.getUserId());
            //更新过本地好友数据后，清空内存中对应用户信息缓存
            if (mUserInfoCache != null && mUserInfoCache.containsKey(friend.getUserId())) {
                mUserInfoCache.remove(friend.getUserId());
            }
        }
    }

    public synchronized void deleteFriends() {
        LitePal.deleteAll(Friend.class);
    }

    public synchronized void deleteFriendById(String friendId) {
        LitePal.deleteAll(Friend.class, "userid = ?", friendId);
    }

    public synchronized void saveFriends(List<FriendshipListResponse.BodyBean.ContentBean.ListBean> list) {
        List<Friend> friends = new ArrayList<>();
        for (FriendshipListResponse.BodyBean.ContentBean.ListBean entity : list) {
            if (entity.getStatus() == 20) { //已经是好友
                Friend friend = new Friend(""+entity.getFriendId(), entity.getDisplayName(), entity.getFriendPortrait(), ""+entity.getStatus(), entity.getMessage());
                /*if (TextUtils.isEmpty(friend.getPortraitUri())) {
                    friend.setPortraitUri(getPortrait(friend));
                }*/
                friends.add(friend);
            }
        }
        if (friends != null && friends.size() > 0)
            LitePal.saveAll(friends);
    }

    /*================== Friend end ==================*/

    /*================== Groups start ==================*/
    public synchronized List<Groups> getGroups() {
        return LitePal.findAll(Groups.class);
    }

    public synchronized Groups getGroupsById(String groupId) {
        if (!TextUtils.isEmpty(groupId)) {
            List<Groups> groupses = LitePal.where("groupid = ?", groupId).find(Groups.class);
            if (groupses != null && groupses.size() > 0) {
                return groupses.get(0);
            }
        }
        return null;
    }

    public synchronized void saveOrUpdateGroup(Groups groups) {
        if (groups != null) {
            String portrait = groups.getPortraitUri();
            if (TextUtils.isEmpty(portrait)) {
                portrait = RongGenerate.generateDefaultAvatar(groups.getName(), groups.getGroupId());
                groups.setPortraitUri(portrait);
            }
            groups.saveOrUpdate("groupid = ?", groups.getGroupId());
        }
    }

    public synchronized void saveGroups() {

    }

    public synchronized void deleteGroups() {
        LitePal.deleteAll(Groups.class);
    }

    public synchronized void deleteGroupsById(String groupId) {
        LitePal.deleteAll(Groups.class, "groupid = ?", groupId);
    }

    /*================== Groups end ==================*/


    /*================== GroupMember start ==================*/
    public synchronized List<GroupMember> getGroupMembers(String groupId) {
        return LitePal.where("groupid = ?", groupId).find(GroupMember.class);
    }

    public synchronized List<GroupMember> getGroupNameList(String groupId, String filterStr) {
        return LitePal.where("groupid = ? and groupname like ? or groupnamespelling like ?", groupId, filterStr, filterStr).find(GroupMember.class);
    }

    public synchronized List<GroupMember> getGroupMemberNameList(String groupId, String filterStr) {
        return LitePal.where("groupid = ? and displayname like ? or name like ?", groupId, filterStr, filterStr).find(GroupMember.class);
    }


    public synchronized List<String> getGroupIdsInGroupMembers(String filterStr) {
        List<GroupMember> groupMemberList = LitePal.where("groupName like ? or groupNameSpelling like ?", "%"+filterStr+"%", "%"+filterStr+"%").find(GroupMember.class);
        List<String> list = new ArrayList<>();
        if (groupMemberList.size() > 0) {
            for (GroupMember groupMember: groupMemberList) {
                list.add(groupMember.getGroupId());
            }
        }
        return list ;
    }

    public synchronized List<GroupMember> getGroupMembersWithUserId(String userId) {
        if (TextUtils.isEmpty(userId))
            return null;
        return LitePal.where("userid = ?", userId).find(GroupMember.class);
    }

    public synchronized void updateGroupsName(String groupId, String groupName) {
        Groups groups = getGroupsById(groupId);
        if (groups != null) {
            groups.setName(groupName);
            saveOrUpdateGroup(groups);
        }
    }

    public synchronized void deleteGroupMembers() {
        LitePal.deleteAll(GroupMember.class);
    }

    public synchronized void deleteGroupMembers(String groupId, List<String> kickedUserIds) {
        if (kickedUserIds != null && kickedUserIds.size() > 0) {
            for (String userId : kickedUserIds) {
                LitePal.deleteAll(GroupMember.class, "groupid = ? and userid = ?", groupId, userId);
            }
//            BroadcastManager.getInstance(App.getContext()).sendBroadcast(AppConst.UPDATE_GROUP_MEMBER, groupId);
//            BroadcastManager.getInstance(App.getContext()).sendBroadcast(AppConst.UPDATE_CONVERSATIONS);
        }
    }

    public synchronized void deleteGroupMembersByGroupId(String groupId) {
        LitePal.deleteAll(GroupMember.class, "groupid = ?", groupId);
    }
    /*================== GroupMember end ==================*/


    private String getPortrait(Friend friend) {
        if (friend != null) {
            if (TextUtils.isEmpty(friend.getPortraitUri())) {
                if (TextUtils.isEmpty(friend.getUserId())) {
                    return null;
                } else {
                    UserInfo userInfo = mUserInfoCache.get(friend.getUserId());
                    if (userInfo != null) {
                        if (!TextUtils.isEmpty(userInfo.getPortraitUri().toString())) {
                            return userInfo.getPortraitUri().toString();
                        } else {
                            mUserInfoCache.remove(friend.getUserId());
                        }
                    }
                    String portrait = RongGenerate.generateDefaultAvatar(friend.getName(), friend.getUserId());
                    //缓存信息kit会使用,备注名存在时需要缓存displayName
                    String name = friend.getName();
                    if (friend.isExitsDisplayName()) {
                        name = friend.getDisplayName();
                    }
                    userInfo = new UserInfo(friend.getUserId(), name, Uri.parse(portrait));
                    mUserInfoCache.put(friend.getUserId(), userInfo);
                    return portrait;
                }
            } else {
                return friend.getPortraitUri();
            }
        }
        return null;
    }
}
