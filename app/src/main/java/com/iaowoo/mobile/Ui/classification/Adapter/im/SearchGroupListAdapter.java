package com.iaowoo.mobile.Ui.classification.Adapter.im;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.iaowoo.mobile.DB.im.DBManager;
import com.iaowoo.mobile.DB.im.model.GroupMember;
import com.iaowoo.mobile.DB.im.model.Groups;
import com.iaowoo.mobile.R;
import java.util.List;
import java.util.Map;

public class SearchGroupListAdapter extends BaseAdapter {
    private Context mContext;
    private Map<String, List<GroupMember>> filterGroupNameListMap;
    private Map<String, List<GroupMember>> filterGroupMemberNameListMap;
    private List<String> filterGroupId;

    public SearchGroupListAdapter(Context context, List<String> filterGroupId, Map<String, List<GroupMember>> filterGroupNameListMap, Map<String, List<GroupMember>> filterGroupMemberNameListMap) {
        this.mContext = context;
        this.filterGroupId = filterGroupId;
        this.filterGroupNameListMap = filterGroupNameListMap;
        this.filterGroupMemberNameListMap = filterGroupMemberNameListMap;
    }

    @Override
    public int getCount() {
        if (filterGroupId != null) {
            return filterGroupId.size() > 3 ? 3 : filterGroupId.size();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        String groupId = (String) getItem(position);
        Groups groupInfo = DBManager.getInstance().getGroupsById(groupId);
        if (convertView == null) {
            viewHolder = new GroupViewHolder();
            convertView = View.inflate(mContext, R.layout.im_item_filter_group_list, null);
            viewHolder.portraitImageView = convertView.findViewById(R.id.item_iv_group_image);
            viewHolder.nameDisplayNameLinearLayout = (LinearLayout) convertView.findViewById(R.id.item_ll_group_contains_member);
            viewHolder.displayNameTextView = (TextView) convertView.findViewById(R.id.item_tv_group_name);
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.item_tv_friend_display_name);
            viewHolder.nameSingleTextView = (TextView) convertView.findViewById(R.id.item_tv_group_name_single);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        if (groupInfo != null) {
          /*  String portraitUri = SealUserInfoManager.getInstance().getPortraitUri(groupInfo);
            ImageLoader.getInstance().displayImage(portraitUri, viewHolder.portraitImageView, App.getOptions());*/
            List<GroupMember> filterGroupMemberNameList = filterGroupMemberNameListMap.get(groupId);
            if (filterGroupNameListMap.get(groupId) != null) {
                viewHolder.nameSingleTextView.setVisibility(View.VISIBLE);
                viewHolder.nameDisplayNameLinearLayout.setVisibility(View.GONE);
                viewHolder.nameSingleTextView.setText(groupInfo.getName());
            } else if (filterGroupMemberNameList != null) {
                viewHolder.nameDisplayNameLinearLayout.setVisibility(View.VISIBLE);
                viewHolder.nameSingleTextView.setVisibility(View.GONE);
                viewHolder.displayNameTextView.setText(groupInfo.getName());
                viewHolder.nameTextView.setText(""); // mCharacterParser.getColoredNameList(mFilterString, filterGroupMemberNameList)
            }
        }
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        if (filterGroupId == null)
            return null;

        if (position >= filterGroupId.size())
            return null;

        return filterGroupId.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class GroupViewHolder {
        ImageView portraitImageView;
        LinearLayout nameDisplayNameLinearLayout;
        TextView nameTextView;
        TextView displayNameTextView;
        TextView nameSingleTextView;
    }
}
