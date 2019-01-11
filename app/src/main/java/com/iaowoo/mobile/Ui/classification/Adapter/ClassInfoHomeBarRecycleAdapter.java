package com.iaowoo.mobile.Ui.classification.Adapter;

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
 * 大分类adpter
 *
 * Created by chenda on 2018/4/3.
 */

public class ClassInfoHomeBarRecycleAdapter extends BaseSoEasyAdapter{

    private Context context;

    private List<String> classinfos;

    private int CHOOS_POSITION=0;

    public ClassInfoHomeBarRecycleAdapter(List<String> classinfos, Context context, OnclicKRecycleAdapter onRecyclerViewListener) {
        this.classinfos= classinfos;
        this.context=context;
        this.onclicKRecycleAdapter=onRecyclerViewListener;
    }

    public void setPosition(int position){
        this.CHOOS_POSITION=position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classinfo_left_adapter,parent,false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        if(CHOOS_POSITION==position){
            holder.oneClickBackgroud.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.choos_ok.setVisibility(View.VISIBLE);
        }else{
            holder.oneClickBackgroud.setBackgroundColor(context.getResources().getColor(R.color.huiclassinfo));
            holder.choos_ok.setVisibility(View.GONE);
        }
        holder.classinfo_Name.setText(classinfos.get(position));
    }

    @Override
    public int getItemCount() {
        if(classinfos!=null){
            return classinfos.size();
        }
        return  0;
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView classinfo_Name;
        public RelativeLayout oneClickBackgroud;
        public View choos_ok;
        public PersonViewHolder(View itemView) {
            super(itemView);
            classinfo_Name=itemView.findViewById(R.id.classinfo_Name);
            choos_ok=itemView.findViewById(R.id.choos_ok);
            oneClickBackgroud=itemView.findViewById(R.id.oneClickBackgroud);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onclicKRecycleAdapter) {
                onclicKRecycleAdapter.onItemClick(getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (null != onclicKRecycleAdapter) {
                return onclicKRecycleAdapter.onItemLongClick(getPosition());
            }
            return false;
        }

    }
}
