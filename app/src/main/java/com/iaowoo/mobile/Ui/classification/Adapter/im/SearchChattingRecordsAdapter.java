package com.iaowoo.mobile.Ui.classification.Adapter.im;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.DB.im.DBManager;
import com.iaowoo.mobile.DB.im.model.Friend;
import com.iaowoo.mobile.DB.im.model.Groups;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.im.MySearchConversationResult;
import com.iaowoo.mobile.Utils.ToastUtilsAll;

import java.util.ArrayList;
import java.util.List;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.SearchConversationResult;
import io.rong.imlib.model.UserInfo;

public class SearchChattingRecordsAdapter extends BaseAdapter {
    private List<MySearchConversationResult> searchConversationResults;
    private Context mContext;

    public SearchChattingRecordsAdapter(Context context, List<SearchConversationResult> searchConversationResults) {
        this.mContext = context;
        this.searchConversationResults = convertSearchResult(searchConversationResults);
    }

    private List<MySearchConversationResult> convertSearchResult(List<SearchConversationResult> results) {
        List<MySearchConversationResult> list = new ArrayList<>();
        for (SearchConversationResult result : results) {
            MySearchConversationResult sealSearchConversationResult = new MySearchConversationResult();
            sealSearchConversationResult.setConversation(result.getConversation());
            sealSearchConversationResult.setMatchCount(result.getMatchCount());
            list.add(sealSearchConversationResult);
        }
        return list;
    }

    @Override
    public int getCount() {
        if (searchConversationResults != null) {
            return searchConversationResults.size() > 3 ? 3 : searchConversationResults.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (searchConversationResults == null)
            return null;

        if (position >= searchConversationResults.size())
            return null;

        return searchConversationResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ChattingRecordsViewHolder viewHolder;
        final MySearchConversationResult searchResult = (MySearchConversationResult) getItem(position);
        final Conversation conversation = searchResult.getConversation();
        int searchResultCount = searchResult.getMatchCount();
        if (convertView == null) {
            viewHolder = new ChattingRecordsViewHolder();
            convertView = View.inflate(mContext, R.layout.im_item_filter_chatting_records_list, null);
            viewHolder.portraitImageView = convertView.findViewById(R.id.item_iv_record_image);
            viewHolder.chatDetailLinearLayout = (LinearLayout) convertView.findViewById(R.id.item_ll_chatting_records_detail);
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.item_tv_chat_name);
            viewHolder.chatRecordsDetailTextView = (TextView) convertView.findViewById(R.id.item_tv_chatting_records_detail);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChattingRecordsViewHolder) convertView.getTag();
        }
        if (conversation.getConversationType() == Conversation.ConversationType.PRIVATE) {
            Friend friend = DBManager.getInstance().getFriendById(conversation.getTargetId());

            String currentUserId = PrefManager.getInstance().getUserId();
            String currentUserName = PrefManager.getInstance().getToken();
            String currentUserPortrait = "";

            if (friend != null) {
                searchResult.setId(friend.getUserId());

                String portraitUri = friend.getPortraitUri();
                searchResult.setPortraitUri(portraitUri);

                // viewHolder.portraitImageView

                if (!TextUtils.isEmpty(friend.getDisplayName())) {
                    searchResult.setTitle(friend.getDisplayName());
                    viewHolder.nameTextView.setText(friend.getDisplayName());
                } else {
                    searchResult.setTitle(friend.getName());
                    viewHolder.nameTextView.setText(friend.getName());
                }
            } else if (conversation.getTargetId().equals(currentUserId)) {
                searchResult.setId(currentUserId);
                UserInfo currentUserInfo = new UserInfo(currentUserId, currentUserName, Uri.parse(currentUserPortrait));

                /*String portraitUri = SealUserInfoManager.getInstance().getPortraitUri(currentUserInfo);
                searchResult.setPortraitUri(portraitUri);
                ImageLoader.getInstance().displayImage(portraitUri, viewHolder.portraitImageView, App.getOptions());*/

                if (!TextUtils.isEmpty(currentUserName)) {
                    searchResult.setTitle(currentUserName);
                    viewHolder.nameTextView.setText(currentUserName);
                } else {
                    searchResult.setTitle(currentUserId);
                    viewHolder.nameTextView.setText(currentUserId);
                }
            } else {
                ToastUtilsAll.getInstance().show("userId"+conversation.getTargetId());
                UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(conversation.getTargetId());
                if (userInfo != null && userInfo.getPortraitUri() != null) {
                    String portraitUri = userInfo.getPortraitUri().getPath();
                    searchResult.setPortraitUri(portraitUri);
                }
//                viewHolder.portraitImageView

                searchResult.setId(conversation.getTargetId());
                if (userInfo != null) {
                    if (!TextUtils.isEmpty(userInfo.getName())) {
                        searchResult.setTitle(userInfo.getName());
                        viewHolder.nameTextView.setText(userInfo.getName());
                    } else {
                        searchResult.setTitle(userInfo.getUserId());
                        viewHolder.nameTextView.setText(userInfo.getUserId());
                    }
                } else {
                    searchResult.setId(conversation.getTargetId());
                    searchResult.setTitle(conversation.getTargetId());
                    viewHolder.nameTextView.setText(conversation.getTargetId());
                }

            }

        }
        if (conversation.getConversationType() == Conversation.ConversationType.GROUP) {
            Groups groupInfo = DBManager.getInstance().getGroupsById(conversation.getTargetId());
            if (groupInfo != null) {
                searchResult.setId(groupInfo.getGroupId());
                /*String portraitUri = SealUserInfoManager.getInstance().getPortraitUri(groupInfo);
                if (!TextUtils.isEmpty(portraitUri)) {
                    searchResult.setPortraitUri(portraitUri);
                }
                ImageLoader.getInstance().displayImage(portraitUri, viewHolder.portraitImageView, App.getOptions());*/
                if (!TextUtils.isEmpty(groupInfo.getName())) {
                    searchResult.setTitle(groupInfo.getName());
                    viewHolder.nameTextView.setText(groupInfo.getName());
                } else {
                    searchResult.setTitle(groupInfo.getGroupId());
                    viewHolder.nameTextView.setText(groupInfo.getGroupId());
                }
            }
        }
        if (searchResultCount == 1) {
            // viewHolder.chatRecordsDetailTextView.setText(mCharacterParser.getColoredChattingRecord(mFilterString, searchResult.getConversation().getLatestMessage()));
        } else {
            // viewHolder.chatRecordsDetailTextView.setText(getResources().getString(R.string.search_item_chat_records, searchResultCount));
        }
        return convertView;
    }

    static class ChattingRecordsViewHolder {
        ImageView portraitImageView;
        LinearLayout chatDetailLinearLayout;
        TextView nameTextView;
        TextView chatRecordsDetailTextView;
    }
}
