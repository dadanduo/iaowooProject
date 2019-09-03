package com.iaowoo.mobile.Ui.classification.Adapter.im;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.iaowoo.mobile.DB.im.model.Friend;
import com.iaowoo.mobile.R;

import java.util.List;

public class SearchFriendListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Friend> filterFriendList;
    private String mFilterString;

    public SearchFriendListAdapter(Context context, List<Friend> filterFriendList, String mFilterString) {
        this.mContext = context;
        this.filterFriendList = filterFriendList;
        this.mFilterString = mFilterString;
    }

    @Override
    public int getCount() {
        if (filterFriendList != null) {
            return filterFriendList.size() > 3 ? 3 : filterFriendList.size();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Friend friendInfo = (Friend) getItem(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.im_item_filter_friend_list, null);
            viewHolder.portraitImageView = convertView.findViewById(R.id.item_aiv_friend_image);
            viewHolder.nameDisplayNameLinearLayout = (LinearLayout) convertView.findViewById(R.id.item_ll_friend_name);
            viewHolder.displayNameTextView = (TextView) convertView.findViewById(R.id.item_tv_friend_display_name);
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.item_tv_friend_name);
            viewHolder.nameSingleTextView = (TextView) convertView.findViewById(R.id.item_tv_friend_name_single);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*String portraitUri = SealUserInfoManager.getInstance().getPortraitUri(friendInfo);
        ImageLoader.getInstance().displayImage(portraitUri, viewHolder.portraitImageView, App.getOptions());*/

        if (!TextUtils.isEmpty(friendInfo.getDisplayName())) {
            viewHolder.nameSingleTextView.setVisibility(View.GONE);
            viewHolder.nameDisplayNameLinearLayout.setVisibility(View.VISIBLE);
            viewHolder.displayNameTextView.setText(friendInfo.getDisplayName()); // getColoredDisplayName(mFilterString, friendInfo.getDisplayName())
            viewHolder.nameTextView.setText(friendInfo.getName()); // getColoredName(mFilterString, friendInfo.getName())
        } else if (!TextUtils.isEmpty(friendInfo.getName())) {
            viewHolder.nameDisplayNameLinearLayout.setVisibility(View.GONE);
            viewHolder.nameSingleTextView.setVisibility(View.VISIBLE);
            viewHolder.nameSingleTextView.setText(friendInfo.getName()); // getColoredName(mFilterString, friendInfo.getName())
        }

        return convertView;
    }

    @Override
    public Object getItem(int position) {
        if (filterFriendList == null)
            return null;

        if (position >= filterFriendList.size())
            return null;

        return filterFriendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView portraitImageView;
        LinearLayout nameDisplayNameLinearLayout;
        TextView nameTextView;
        TextView displayNameTextView;
        TextView nameSingleTextView;
    }
}
