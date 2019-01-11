package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.SearchGoods;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;

import java.util.List;

/**
 * Created by chenda on 2018/4/3.
 * 保障adapter
 */

public class InfrastructureRecycleAdapter extends BaseSoEasyAdapter{

    private Context context;

    private List<SearchGoods.BodyBean.ContentBean.PromiseInfoListBean> promiseInfoListBeans;

    private GlideUtils glideUtils;

    public InfrastructureRecycleAdapter(Context context,GlideUtils glideUtils) {
        this.context=context;
        this.glideUtils=glideUtils;
    }

    public void SetData(List<SearchGoods.BodyBean.ContentBean.PromiseInfoListBean> promiseInfoListBeans){
        this.promiseInfoListBeans= promiseInfoListBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_bao_zhang,parent,false);

        return new CommentsViewHolder(view);
    }

    /**
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final CommentsViewHolder holder = (CommentsViewHolder) viewHolder;
        if(!TextUtils.isEmpty(promiseInfoListBeans.get(position).getPromiseDescribe())){
            holder.name_baozhang.setText(promiseInfoListBeans.get(position).getPromiseDescribe()+"");
        }
        if(!TextUtils.isEmpty(promiseInfoListBeans.get(position).getDetailDescription())){
            holder.describe_baozhang.setText(promiseInfoListBeans.get(position).getDetailDescription()+"");
        }
        if(!TextUtils.isEmpty(promiseInfoListBeans.get(position).getPromiseUrl())){
           glideUtils.glides(context,promiseInfoListBeans.get(position).getPromiseUrl(),holder.show_image_baozhang);
        }
    }

    @Override
    public int getItemCount() {
        if(promiseInfoListBeans!=null){
            return promiseInfoListBeans.size();
        }
        return 0;
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder {
        public TextView describe_baozhang;
        public TextView name_baozhang;
        public ImageView show_image_baozhang;
        public CommentsViewHolder(View itemView) {
            super(itemView);
            show_image_baozhang=itemView.findViewById(R.id.show_image_baozhang);
            name_baozhang=itemView.findViewById(R.id.name_baozhang);
            describe_baozhang=itemView.findViewById(R.id.describe_baozhang);
        }

    }
}
