package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.DIFFRENT;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;

import java.util.List;

/**
 * 每日最火
 * Created by chenda on 2018/4/3.
 */

public class EverdayRecycleAdapter extends RecyclerView.Adapter{


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

    public EverdayRecycleAdapter(List<Shop.BodyBean.ContentBean.ListBean> shops, Context context) {
        this.shops = shops;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.everday_xml,parent,false);
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
        GlideUtils glideUtils=new GlideUtils();
        if(shops.get(position).getProductInfo().getMainImage()!=null) {
            glideUtils.glides(context, shops.get(position).getProductInfo().getMainImage(),holder.image_shop);
        }

        if(PrefManager.getInstance().getIntegralRatio()!=0){
            if(shops.get(position).getSubTemplateInfoList().get(0)!=null) {
                Float pv = (float) shops.get(position).getSubTemplateInfoList().get(0).getPv();
                Float integral = PrefManager.getInstance().getIntegralRatio();
                holder.integral_text.setText(UtilsAll.DoubleTo_2(pv / integral) + "");
            }
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
        public TextView price,integral_text;
        public LinearLayout item_click;
        public PersonViewHolder(View itemView) {
            super(itemView);
            image_shop=itemView.findViewById(R.id.image_shop);
            showshop=itemView.findViewById(R.id.showshop);
            price=itemView.findViewById(R.id.price);
            item_click=itemView.findViewById(R.id.item_click);
            integral_text=itemView.findViewById(R.id.integral_text);
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
