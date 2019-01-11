package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Application.AppConst;
import com.iaowoo.mobile.DB.im.DBManager;
import com.iaowoo.mobile.DB.im.model.Friend;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.im.ContactAdapter;
import com.iaowoo.mobile.Ui.classification.View.im.SideBar;
import com.iaowoo.mobile.Utils.PingYinUtil;
import com.iaowoo.mobile.im.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends TitleBarActivity implements View.OnClickListener {

    @BindView(R.id.lv_contact_list)
    ListView lvContactList;
    @BindView(R.id.group_dialog)
    TextView groupDialog;
    @BindView(R.id.side_bar)
    SideBar sideBar;

    private View mHeadView;
    private TextView mNoFriends;
    private TextView mUnreadTextView;

    private PinyinComparator pinyinComparator;
    private ContactAdapter adapter;
    private List<Friend> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_contacts);
        ButterKnife.bind(this);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        setTitle("联系人");
        sideBar.setTextView(groupDialog);
        initListViewHeader();
        registerBR();
    }

    private void initListViewHeader() {
        mHeadView = LayoutInflater.from(this).inflate(R.layout.im_item_contact_list_header, null);
        mUnreadTextView = (TextView) mHeadView.findViewById(R.id.tv_unread);
        RelativeLayout newFriendsLayout = (RelativeLayout) mHeadView.findViewById(R.id.re_new_friends);
        RelativeLayout groupLayout = (RelativeLayout) mHeadView.findViewById(R.id.re_chatroom);
        lvContactList.addHeaderView(mHeadView);

        groupLayout.setOnClickListener(this);
        newFriendsLayout.setOnClickListener(this);
    }

    private void initListener() {
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    lvContactList.setSelection(position);
                }
            }
        });

        lvContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lvContactList.getHeaderViewsCount() > 0) {
                    startFriendDetailsPage(mList.get(position - 1));
                } else {
//                    startFriendDetailsPage(mFilteredFriendList.get(position));
                }
            }
        });
    }

    private void initData() {
        pinyinComparator = PinyinComparator.getInstance();
        adapter = new ContactAdapter(this, mList);
        lvContactList.setAdapter(adapter);

        mList = DBManager.getInstance().getFriends();
        for (int i=0; i<mList.size();i++) {
            Friend friend = mList.get(i);
            String pinyin = PingYinUtil.converterToFirstSpell(friend.getDisplayName());
            String sortString;
            if (!TextUtils.isEmpty(pinyin)) {
                sortString = pinyin.substring(0, 1).toUpperCase();
            } else {
                sortString = "#";
            }
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                friend.setLetters(sortString);
            } else {
                friend.setLetters("#");
            }
        }
        Collections.sort(mList, pinyinComparator);
        adapter.setData(mList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_new_friends:
                mUnreadTextView.setVisibility(View.GONE);
                Intent intent = new Intent(this, NewFriendListActivity.class);
                startActivityForResult(intent, 20);
                break;
            case R.id.re_chatroom:
                startActivity(new Intent(this, GroupListActivity.class));
                break;
        }
    }


    private void startFriendDetailsPage(Friend friend) {
        Intent intent = new Intent(ContactsActivity.this, UserDetailActivity.class);
        intent.putExtra(UserDetailActivity.CLICK_ORIGIN, AppConst.CLICK_CONTACT_FRAGMENT_FRIEND);
        intent.putExtra(UserDetailActivity.USER_ID, friend.getUserId());
        startActivity(intent);
    }

    private void registerBR() {

    }

    private void unregisterBR() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBR();
    }
}