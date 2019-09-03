package com.iaowoo.mobile.Ui.classification.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.R;

import java.util.List;

/**
 * Created by chenda on 2018/4/3.
 * 规格adpter
 */

public class itemRecycleAdapter extends BaseSoEasyAdapter{

    private Context context;

    private List<String> name;


    private int selectedPosition = -5; //默认一个参数


    private boolean isok=true;

    public void setDefSelect(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }


    public itemRecycleAdapter(Context context) {
        this.context=context;
    }

    public void setCallBack(OnclicKRecycleAdapter onclicKRecycleAdapter){
        this.onclicKRecycleAdapter=onclicKRecycleAdapter;

    }

    public void SetData(List<String> name){
        this.name= name;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter,parent,false);

        return new CommentsViewHolder(view);
    }

    /**
     * @param viewHolder
     * @param position
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final CommentsViewHolder holder = (CommentsViewHolder) viewHolder;
        holder.name.setText(""+name.get(position));
        holder.bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onclicKRecycleAdapter.onItemClick(position);
            }
        });

        if (selectedPosition != -5) {
            if(selectedPosition==position){
                    holder.bg.setBackgroundResource(R.drawable.item_selector_shop_select);
                    holder.name.setTextColor(R.color.hui);
            }else{
                holder.bg.setBackgroundResource(R.drawable.item_selector_shop);
                holder.name.setTextColor(R.color.color_222222);
            }
        }

    }


    @Override
    public int getItemCount() {
        if(name!=null){
            return name.size();
        }
        return 0;
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public RelativeLayout bg;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            bg=itemView.findViewById(R.id.bg);
        }

    }
}
