package com.iaowoo.mobile.Ui.classification.Adapter.im;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.iaowoo.mobile.DB.im.model.Friend;
import com.iaowoo.mobile.R;

import java.util.List;

public class ContactAdapter extends BaseAdapter implements SectionIndexer {
    private Context mContext;
    private List<Friend> mList;

    public ContactAdapter(Context context, List<Friend> list) {
        mContext = context;
        mList = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.im_item_contact, parent, false);
            holder = new ViewHolder();
            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.tvLetter = convertView.findViewById(R.id.tv_catalog);
            holder.mImageView = convertView.findViewById(R.id.iv_friend_uri);
            holder.tvUserId = convertView.findViewById(R.id.tv_friend_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Friend mContent = mList.get(position);


        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);
        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            holder.tvLetter.setVisibility(View.VISIBLE);
            String letterFirst = mContent.getLetters();
            if (!TextUtils.isEmpty(letterFirst)) {
                if (!isLetterDigitOrChinese(letterFirst)) {
                    letterFirst = "#";
                }else {
                    letterFirst = String.valueOf(letterFirst.toUpperCase().charAt(0));
                }
            }
            holder.tvLetter.setText(letterFirst);
        } else {
            holder.tvLetter.setVisibility(View.GONE);
        }

        holder.tvName.setText(mContent.getDisplayName());
        return convertView;
    }


    private boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mList.get(i).getLetters();
            char firstChar = sortStr.charAt(0);
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
        private TextView tvName;
        TextView tvLetter;
        ImageView mImageView;
        TextView tvUserId;
    }
}
