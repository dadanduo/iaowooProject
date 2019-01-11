package com.iaowoo.mobile.Ui.classification.Adapter.im;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaowoo.mobile.DB.im.model.Groups;
import com.iaowoo.mobile.R;
import java.util.List;

public class GroupAdapter extends BaseAdapter {

    private Context mContext;
    private List<Groups> mList;

    public GroupAdapter(Context context, List<Groups> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void updateData(List<Groups> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mList == null)
            return null;
        if (position >= mList.size())
            return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.im_item_group_list, parent, false);
            viewHolder.groupName = convertView.findViewById(R.id.groupname);
            viewHolder.mImageView = convertView.findViewById(R.id.groupuri);
            viewHolder.groupId = convertView.findViewById(R.id.group_id);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Groups mContent = mList.get(position);
        viewHolder.groupName.setText(mContent.getName());

//        String portraitUri = SealUserInfoManager.getInstance().getPortraitUri(mContent);
//        ImageLoader.getInstance().displayImage(portraitUri, viewHolder.mImageView, App.getOptions());

//        viewHolder.groupId.setVisibility(View.VISIBLE);
//        viewHolder.groupId.setText(mContent.getGroupId());
        return convertView;
    }

    class ViewHolder {
        TextView groupName;
        ImageView mImageView;
        TextView groupId;
    }
}
