package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.ToastUtilsAll;

import java.util.List;

/**
 * Created by chenda on 2018/4/3.
 */

public class PGRecycleAdapter extends RecyclerView.Adapter{


    public interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    private Context context;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
    private List<Shop.BodyBean.ContentBean.ListBean> shops;

    public PGRecycleAdapter(List<Shop.BodyBean.ContentBean.ListBean> shops, Context context) {
        this.shops = shops;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.p_g_adpter,parent,false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        double price=shops.get(position).getSubTemplateInfoList().get(0).getSellPrice();
        if(price%1==0){
            int tmp = (int)price;
            holder.price.setText("¥" +tmp);
        }else{
            holder.price.setText("¥" +price);
        }
        holder.showshop.setText((shops.get(position).getProductInfo().getName()));
        holder.much.setText(shops.get(position).getSales()+"人拼单");
        GlideUtils glideUtils=new GlideUtils();
        if(shops.get(position).getProductInfo().getHomeImage()!=null) {
            glideUtils.glides(context, shops.get(position).getProductInfo().getMainImage(),holder.image_shop);
        }
    }

    @Override
    public int getItemCount() {
        if(shops!=null){
            return shops.size();
        }
        return  0;
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public ImageView image_shop;
        public TextView showshop;
        public TextView price,much;
        public LinearLayout item_click;
        public PersonViewHolder(View itemView) {
            super(itemView);
            image_shop=itemView.findViewById(R.id.image_shop);
            showshop=itemView.findViewById(R.id.showshop);
            price=itemView.findViewById(R.id.price);
            item_click=itemView.findViewById(R.id.item_click);
            much=itemView.findViewById(R.id.much);
            item_click.setOnClickListener(this);
            item_click.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (null != onRecyclerViewListener) {
                return onRecyclerViewListener.onItemLongClick(getPosition());
            }
            return false;
        }

    }
}
