package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaowoo.mobile.DB.im.DBManager;
import com.iaowoo.mobile.DB.im.model.Friend;
import com.iaowoo.mobile.DB.im.model.GroupMember;
import com.iaowoo.mobile.DB.im.model.Groups;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.im.SearchChattingRecordsAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.im.SearchFriendListAdapter;
import com.iaowoo.mobile.Ui.classification.Model.im.MySearchConversationResult;
import com.iaowoo.mobile.Ui.classification.Model.im.SearchResult;
import com.iaowoo.mobile.Ui.classification.View.im.NestedListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.SearchConversationResult;

public class IMSearchActivity extends AppCompatActivity {
    @BindView(R.id.ac_iv_press_back)
    ImageView pressBack;
    @BindView(R.id.ac_et_search)
    EditText etSearch;
    @BindView(R.id.ac_iv_search_loading)
    ImageView ivSearchLoading;

    @BindView(R.id.ac_lv_filtered_friends_list)
    NestedListView mFriendListView;
    @BindView(R.id.ac_ll_more_friends)
    LinearLayout llMoreFriends;
    @BindView(R.id.ac_ll_filtered_friend_list)
    LinearLayout llFilteredFriendList;

    @BindView(R.id.ac_lv_filtered_groups_list)
    NestedListView mGroupsListView;
    @BindView(R.id.ac_ll_more_groups)
    LinearLayout llMoreGroups;
    @BindView(R.id.ac_ll_filtered_group_list)
    LinearLayout llFilteredGroupList;

    @BindView(R.id.ac_lv_filtered_chatting_records_list)
    NestedListView mChattingRecordsListView;
    @BindView(R.id.ac_ll_more_chatting_records)
    LinearLayout llMoreChattingRecords;
    @BindView(R.id.ac_ll_filtered_chatting_records_list)
    LinearLayout llFilteredChattingRecordsList;

    @BindView(R.id.ac_tv_search_no_results)
    TextView tvSearchNoResults;
    @BindView(R.id.ac_ll_filter_view)
    LinearLayout llFilterView;

    private ArrayList<String> mFilterGroupId;
    private ArrayList<Friend> mFilterFriendList;
    private List<SearchConversationResult> mSearchConversationResultsList;
    private ArrayList<SearchConversationResult> mSearchConversationResultsArrayList;
    private String mFilterString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_search);
        ButterKnife.bind(this);

        initView();
        initListener();
        initData();
    }

    private void initListener() {
        mFriendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object selectObject = parent.getItemAtPosition(position);
                if (selectObject instanceof Friend) {
                    Friend friend = (Friend) selectObject;
                    if (!TextUtils.isEmpty(friend.getDisplayName())) {
                        RongIM.getInstance().startPrivateChat(IMSearchActivity.this, friend.getUserId(), friend.getDisplayName());
                    } else {
                        RongIM.getInstance().startPrivateChat(IMSearchActivity.this, friend.getUserId(), friend.getName());
                    }
                }
            }
        });
        mGroupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object selectObject = parent.getItemAtPosition(position);
                if (selectObject instanceof String) {
                    String groupId = (String) selectObject;
                    Groups groupInfo = DBManager.getInstance().getGroupsById(groupId);
                    if (groupInfo != null) {
                        RongIM.getInstance().startGroupChat(IMSearchActivity.this, groupInfo.getGroupId(), groupInfo.getName());
                    }
                }
            }
        });
        mChattingRecordsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object selectObj = parent.getItemAtPosition(position);
                if (selectObj instanceof MySearchConversationResult) {
                    MySearchConversationResult result = (MySearchConversationResult) selectObj;
                    int count = result.getMatchCount();
                    Conversation conversation = result.getConversation();
//                    Log.i("TAG", "count:"+count);
                    if (count >= 1) {
                        RongIM.getInstance().startConversation(IMSearchActivity.this, conversation.getConversationType(), conversation.getTargetId(), result.getTitle(), result.getConversation().getSentTime());
                    } else {
                        /*Intent intent = new Intent(IMSearchActivity.this, SearchChattingDetailActivity.class);
                        intent.putExtra("filterString", mFilterString);
                        intent.putExtra("searchConversationResult", result);
                        intent.putExtra("flag", SEARCH_TYPE_FLAG);
                        startActivity(intent);*/
                    }
                }
            }
        });
    }

    private void initData() {
        mFilterFriendList = new ArrayList<>();
        for (int i=0; i<20;i++) {
            Friend friend = new Friend();
            friend.setName("name"+ i);
            friend.setUserId("userId"+i);
            mFilterFriendList.add(friend);
        }
        llFilteredFriendList.setVisibility(View.VISIBLE);
        SearchFriendListAdapter searchFriendListAdapter = new SearchFriendListAdapter(this, mFilterFriendList, mFilterString);
        mFriendListView.setAdapter(searchFriendListAdapter);

//        SearchGroupListAdapter adapter = new SearchGroupListAdapter()


    }

    private void initView() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFilterFriendList = new ArrayList<>();
                mFilterGroupId = new ArrayList<>();
                mSearchConversationResultsList = new ArrayList<>();
                mFilterString = s.toString();
                // 开始请求数据
                if (!TextUtils.isEmpty(mFilterString)) {
                    getChatRecordData();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.ac_iv_press_back, R.id.ac_ll_more_friends})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.ac_iv_press_back:
                finish();
                break;
            default:
                break;
        }
    }

    public void getChatRecordData() {
        RongIMClient.getInstance().searchConversations(mFilterString,
                new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE, Conversation.ConversationType.GROUP},
                new String[]{"RC:TxtMsg", "RC:ImgTextMsg", "RC:FileMsg"},
                new RongIMClient.ResultCallback<List<SearchConversationResult>>(){

                    @Override
                    public void onSuccess(List<SearchConversationResult> searchConversationResults) {
                        mSearchConversationResultsList = searchConversationResults;
                        mSearchConversationResultsArrayList = new ArrayList<>();

                        for (SearchConversationResult result : searchConversationResults) {
                            mSearchConversationResultsArrayList.add(result);
                        }
                        if (searchConversationResults.size() > 0) {
                            llFilteredChattingRecordsList.setVisibility(View.VISIBLE);
                            if (searchConversationResults.size() > 3) {
                                llMoreChattingRecords.setVisibility(View.VISIBLE);
                            } else {
                                llMoreChattingRecords.setVisibility(View.GONE);
                            }
                        } else {
                            llFilteredChattingRecordsList.setVisibility(View.GONE);
                        }
                        if (mFilterString.equals("")) {
                            llFilteredChattingRecordsList.setVisibility(View.GONE);
                            llMoreChattingRecords.setVisibility(View.GONE);
                        }

                        if (mFilterFriendList.size() == 0 && mFilterGroupId.size() == 0 && mSearchConversationResultsList.size() == 0) {
                            if (mFilterString.equals("")) {
                                tvSearchNoResults.setVisibility(View.GONE);
                            } else {
                                tvSearchNoResults.setVisibility(View.VISIBLE);
                                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                                spannableStringBuilder.append(getResources().getString(R.string.ac_search_no_result_pre));
                                SpannableStringBuilder colorFilterStr = new SpannableStringBuilder(mFilterString);
                                colorFilterStr.setSpan(new ForegroundColorSpan(Color.parseColor("#0099ff")), 0, mFilterString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                spannableStringBuilder.append(colorFilterStr);
                                spannableStringBuilder.append(getResources().getString(R.string.ac_search_no_result_suffix));
                                tvSearchNoResults.setText(spannableStringBuilder);
                            }
                        } else {
                            tvSearchNoResults.setVisibility(View.GONE);
                        }
                        SearchChattingRecordsAdapter chattingRecordsAdapter = new SearchChattingRecordsAdapter(IMSearchActivity.this, mSearchConversationResultsList);
                        mChattingRecordsListView.setAdapter(chattingRecordsAdapter);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        if (mFilterString.equals("")) {
                            llFilteredChattingRecordsList.setVisibility(View.GONE);
                            llMoreChattingRecords.setVisibility(View.GONE);
                        }
                        if (mFilterFriendList.size() == 0 && mFilterGroupId.size() == 0 && mSearchConversationResultsList.size() == 0) {
                            if (mFilterString.equals("")) {
                                tvSearchNoResults.setVisibility(View.GONE);
                            } else {
                                tvSearchNoResults.setVisibility(View.VISIBLE);
                                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                                spannableStringBuilder.append(getResources().getString(R.string.ac_search_no_result_pre));
                                SpannableStringBuilder colorFilterStr = new SpannableStringBuilder(mFilterString);
                                colorFilterStr.setSpan(new ForegroundColorSpan(Color.parseColor("#0099ff")), 0, mFilterString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                                spannableStringBuilder.append(colorFilterStr);
                                spannableStringBuilder.append(getResources().getString(R.string.ac_search_no_result_suffix));
                                tvSearchNoResults.setText(spannableStringBuilder);
                            }
                        } else {
                            tvSearchNoResults.setVisibility(View.GONE);
                        }
                    }
                });
    }



    private synchronized SearchResult filterInfo(String filterStr) {

        List<Friend> filterFriendList = new ArrayList<>();
        List<String> filterGroupId = new ArrayList<>();
        Map<String, List<GroupMember>> filterGroupNameListMap = new HashMap<>();
        Map<String, List<GroupMember>> filterGroupMemberNameListMap = new HashMap<>();
        SearchResult searchResult = new SearchResult();

        if (filterStr.equals("")) {
            SearchResult result = new SearchResult();
            result.setFilterStr("");
            result.setFilterFriendList(filterFriendList);
            result.setFilterGroupId(filterGroupId);
            result.setFilterGroupNameListMap(filterGroupNameListMap);
            result.setFilterGroupNameListMap(filterGroupMemberNameListMap);
            return result;
        }
        if (filterStr.contains("'")) {
            SearchResult result = new SearchResult();
            result.setFilterStr(filterStr);
            result.setFilterFriendList(filterFriendList);
            result.setFilterGroupId(filterGroupId);
            result.setFilterGroupNameListMap(filterGroupNameListMap);
            result.setFilterGroupNameListMap(filterGroupMemberNameListMap);
            return result;
        }

        /**
         * 从数据库里边查询符合条件的数据
         */
        filterFriendList = DBManager.getInstance().getFriendsByFilter(filterStr);
        filterGroupId = DBManager.getInstance().getGroupIdsInGroupMembers(filterStr);

        for (String groupId : filterGroupId) {
            List<GroupMember> filterGroupNameList = DBManager.getInstance().getGroupNameList(groupId, filterStr);

            List<GroupMember> filterGroupMemberNameList = DBManager.getInstance().getGroupMemberNameList(groupId, filterStr);

            if (filterGroupNameList.size() != 0) {
                filterGroupNameListMap.put(groupId, filterGroupNameList);
            } else {
                filterGroupNameListMap.put(groupId, null);
            }
            if (filterGroupMemberNameList.size() != 0) {
                filterGroupMemberNameListMap.put(groupId, filterGroupMemberNameList);
            } else {
                filterGroupMemberNameListMap.put(groupId, null);
            }
        }
        searchResult.setFilterStr(filterStr);
        searchResult.setFilterFriendList(filterFriendList);
        searchResult.setFilterGroupId(filterGroupId);
        searchResult.setFilterGroupNameListMap(filterGroupNameListMap);
        searchResult.setFilterGroupMemberNameListMap(filterGroupMemberNameListMap);
        return searchResult;
    }
}
