package com.iaowoo.mobile.Ui.classification.Adapter.im;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.iaowoo.mobile.DB.im.model.Friend;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.im.SelectFriendsActivity;

import java.util.List;

public class StartDiscussionAdapter extends BaseAdapter implements SectionIndexer {

    private Context mContext;
    private List<Friend> mList;
    private int isSelected = -1;
    private Boolean isStartPrivateChat;

    public StartDiscussionAdapter(Context context, List<Friend> list, Boolean isStartPrivateChat) {
        mContext = context;
        mList = list;
        this.isStartPrivateChat = isStartPrivateChat;
    }

    public void setData(List<Friend> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.im_item_start_discussion, parent, false);
            viewHolder.tvLetter = convertView.findViewById(R.id.dis_catalog);
            viewHolder.friendItemLayout = convertView.findViewById(R.id.dis_frienditem);
            viewHolder.tvTitle = convertView.findViewById(R.id.dis_friendname);
            viewHolder.mImageView = convertView.findViewById(R.id.dis_frienduri);
            viewHolder.isSelect = convertView.findViewById(R.id.iv_selected_status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Friend friend = mList.get(position);

        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(friend.getLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }

        viewHolder.friendItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStartPrivateChat) {
                    setIsSelected(position);
                    notifyDataSetChanged();
                } else {
                    mList.get(position).setSelected(!mList.get(position).getSelected());
                    notifyDataSetChanged();
                    ((SelectFriendsActivity)mContext).updateSelectedSizeView();
                }
            }
        });

        if (!TextUtils.isEmpty(friend.getName())) {
            viewHolder.tvTitle.setText(friend.getName());
        } else if (!TextUtils.isEmpty(friend.getDisplayName())) {
            viewHolder.tvTitle.setText(friend.getDisplayName());
        }

        if (isStartPrivateChat) {
            if (isSelected == position) {
                viewHolder.isSelect.setBackgroundResource(R.mipmap.pic_btn_selected);
            } else {
                viewHolder.isSelect.setBackgroundResource(R.mipmap.pic_btn_unselected);
            }
        } else {
            if (friend.getSelected()) {
                viewHolder.isSelect.setBackgroundResource(R.mipmap.pic_btn_selected);
            } else {
                viewHolder.isSelect.setBackgroundResource(R.mipmap.pic_btn_unselected);
            }
        }
        return convertView;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mList.get(i).getLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return mList.get(position).getLetters().charAt(0);
    }

    static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        ImageView mImageView;
        ImageView isSelect;
        LinearLayout friendItemLayout;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }
}
