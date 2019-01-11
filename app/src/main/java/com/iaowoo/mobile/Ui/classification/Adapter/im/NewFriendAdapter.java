package com.iaowoo.mobile.Ui.classification.Adapter.im;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.im.FriendshipListResponse;

import java.util.List;

public class NewFriendAdapter extends BaseAdapter {
    private List<FriendshipListResponse.BodyBean.ContentBean.ListBean> list;
    private Context mContext;
    private OnItemButtonClick mOnItemButtonClick;

    public NewFriendAdapter(Context context, List<FriendshipListResponse.BodyBean.ContentBean.ListBean> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.im_item_new_friend, parent, false);
            viewHolder.ivHeader = convertView.findViewById(R.id.iv_new_header);
            viewHolder.tvMessage = convertView.findViewById(R.id.tv_ship_message);
            viewHolder.tvName = convertView.findViewById(R.id.tv_ship_name);
            viewHolder.tvStatus = convertView.findViewById(R.id.tv_ship_state);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final FriendshipListResponse.BodyBean.ContentBean.ListBean listBean = list.get(position);
        viewHolder.tvName.setText(listBean.getDisplayName());
        viewHolder.tvMessage.setText(listBean.getMessage());
        viewHolder.tvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemButtonClick != null) {
                    mOnItemButtonClick.onButtonClick(position, v, listBean.getStatus());
                }
            }
        });


        switch (listBean.getStatus()) {
            case 11: //收到了好友邀请
                viewHolder.tvStatus.setText(R.string.agree);
//                viewHolder.tvStatus.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.de_add_friend_selector));
                break;
            case 10: // 发出了好友邀请
                viewHolder.tvStatus.setText(R.string.request);
                viewHolder.tvStatus.setBackgroundDrawable(null);
                break;
            case 21: // 忽略好友邀请
                viewHolder.tvStatus.setText(R.string.ignore);
                viewHolder.tvStatus.setBackgroundDrawable(null);
                break;
            case 20: // 已是好友
                viewHolder.tvStatus.setText(R.string.added);
                viewHolder.tvStatus.setBackgroundDrawable(null);
                break;
            case 30: // 删除了好友关系
                viewHolder.tvStatus.setText(R.string.deleted);
                viewHolder.tvStatus.setBackgroundDrawable(null);
                break;
        }

        return convertView;
    }


    static class ViewHolder {
        ImageView ivHeader;
        TextView tvName;
        TextView tvMessage;
        TextView tvStatus;
    }


    public void setOnItemButtonClick(OnItemButtonClick onItemButtonClick) {
        this.mOnItemButtonClick = onItemButtonClick;
    }

    public interface OnItemButtonClick {
        boolean onButtonClick(int position, View view, int status);
    }
}
