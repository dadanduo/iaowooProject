package com.iaowoo.mobile.Ui.classification.Adapter.im;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.widget.adapter.ConversationListAdapter;

public class ConversationListAdapterEx extends ConversationListAdapter {
    private Context mContext;

    public ConversationListAdapterEx(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected View newView(Context context, int position, ViewGroup group) {
        /*View v = super.newView(context, position, group);
        View headerView = v.findViewById(R.id.rc_item_conversation_header);
        View contentView = v.findViewById(R.id.rc_item_conversation);

        headerView.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        contentView.setVisibility(position == 0 ? View.GONE : View.VISIBLE);

        if (position == 0) {
            RelativeLayout orderLayout = headerView.findViewById(R.id.rc_header_order);
            RelativeLayout tradeLayout = headerView.findViewById(R.id.rc_header_trade);
            RelativeLayout systemLayout = headerView.findViewById(R.id.rc_header_system);
            RelativeLayout customerLayout = headerView.findViewById(R.id.rc_header_customer);
            RelativeLayout noticeLayout = headerView.findViewById(R.id.rc_header_notice);

            orderLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "orderLayout", Toast.LENGTH_LONG).show();
                }
            });

            tradeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "tradeLayout", Toast.LENGTH_LONG).show();
                }
            });

            systemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "systemLayout", Toast.LENGTH_LONG).show();
                }
            });

            noticeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "noticeLayout", Toast.LENGTH_LONG).show();
                }
            });

            customerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "customerLayout", Toast.LENGTH_LONG).show();
                }
            });

        } else {

        }*/
        return super.newView(context, position, group);
    }
}
