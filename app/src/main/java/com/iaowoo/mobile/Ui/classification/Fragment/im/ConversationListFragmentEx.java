package com.iaowoo.mobile.Ui.classification.Fragment.im;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.im.IMSearchActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.im.ConversationListAdapterEx;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.widget.adapter.ConversationListAdapter;

public class ConversationListFragmentEx extends ConversationListFragment {
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        mListView = findViewById(v, io.rong.imkit.R.id.rc_list);
        View searchView = findViewById(v, io.rong.imkit.R.id.rl_search_layout);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().startActivity(new Intent(getActivity(), IMSearchActivity.class));
                }
            }
        });
        searchView.setVisibility(View.VISIBLE);

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.im_conversation_list_header, null, false);
        RelativeLayout orderLayout = headerView.findViewById(R.id.rc_header_order);
        RelativeLayout tradeLayout = headerView.findViewById(R.id.rc_header_trade);
        RelativeLayout systemLayout = headerView.findViewById(R.id.rc_header_system);
        RelativeLayout customerLayout = headerView.findViewById(R.id.rc_header_customer);
        RelativeLayout noticeLayout = headerView.findViewById(R.id.rc_header_notice);

        orderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tradeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        systemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        noticeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        customerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mListView.addHeaderView(headerView);

        View emptyView = this.findViewById(v, io.rong.imkit.R.id.rc_conversation_list_empty_layout);
        emptyView.setVisibility(View.GONE);
        mListView.setEmptyView(null);
        return v;
    }

    @Override
    public ConversationListAdapter onResolveAdapter(Context context) {
        return new ConversationListAdapterEx(context);
    }
}
