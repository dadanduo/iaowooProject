package com.iaowoo.mobile.Ui.classification.Adapter.im;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaowoo.mobile.R;

public class NewFriendListAdapter extends BaseAdapter {
    private Context mContext;

    public NewFriendListAdapter(Context context) {
       mContext = context;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.im_item_friend_list, parent, false);
            holder.mName = (TextView) convertView.findViewById(R.id.ship_name);
            holder.mMessage = (TextView) convertView.findViewById(R.id.ship_message);
            holder.mHead = convertView.findViewById(R.id.new_header);
            holder.mState = (TextView) convertView.findViewById(R.id.ship_state);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    static class ViewHolder{
        ImageView mHead;
        TextView mName;
        TextView mState;
        TextView mMessage;
    }
}
