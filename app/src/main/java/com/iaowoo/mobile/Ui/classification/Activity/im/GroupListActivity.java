package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import com.iaowoo.mobile.DB.im.model.Groups;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.im.GroupAdapter;
import com.iaowoo.mobile.Ui.classification.View.im.NestedListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;

public class GroupListActivity extends TitleActivity {
    @BindView(R.id.group_search)
    EditText groupSearch;
    @BindView(R.id.show_no_group)
    TextView showNoGroup;
    @BindView(R.id.lv_group)
    NestedListView lvGroup;
    @BindView(R.id.foot_group_size)
    TextView footGroupSize;


    private List<Groups> mList = new ArrayList<>();
    private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_group_list);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        setTitle("群组");
        showBackwardView_show(true);
    }

    private void initData() {
        adapter = new GroupAdapter(this, mList);
        lvGroup.setAdapter(adapter);

        lvGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Groups bean = (Groups) adapter.getItem(position);
                RongIM.getInstance().startGroupChat(GroupListActivity.this, bean.getGroupId(), bean.getName());
            }
        });

        for (int i = 0; i < 20; i++) {
            Groups groups = new Groups();
            groups.setName("nihao" + i);
            groups.setGroupId("311");
            mList.add(groups);
        }

        if (mList.size() > 0) {
            showNoGroup.setVisibility(View.GONE);
            footGroupSize.setVisibility(View.VISIBLE);
            footGroupSize.setText(getString(R.string.ac_group_list_group_number, mList.size()));
            groupSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterData(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        } else {
            showNoGroup.setVisibility(View.VISIBLE);
        }

        adapter.updateData(mList);
    }

    private void filterData(String s) {
        List<Groups> filterDataList = new ArrayList<>();
        if (TextUtils.isEmpty(s)) {
            filterDataList = mList;
        } else {
            for (Groups groups : mList) {
                if (groups.getName().contains(s)) {
                    filterDataList.add(groups);
                }
            }
        }
        adapter.updateData(filterDataList);
        footGroupSize.setText(getString(R.string.ac_group_list_group_number, filterDataList.size()));
    }
}
