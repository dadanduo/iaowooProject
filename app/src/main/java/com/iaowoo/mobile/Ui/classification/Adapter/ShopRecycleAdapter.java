package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.DIFFRENT;
import com.iaowoo.mobile.Ui.classification.Model.NearShops;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;

/**
 * Created by chenda on 2018/4/3.
 */

public class ShopRecycleAdapter extends RecyclerView.Adapter{


    public interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    protected boolean isScrolling = false;

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    private Context context;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
    private NearShops list;

    public ShopRecycleAdapter(NearShops list, Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_recycle_item,parent,false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        final NearShops.BodyBean.ContentBean.ListBean dataBean = list.getBody().getContent().getList().get(position);
        holder.type_name.setText(""+dataBean.getDetailAddress());
        holder.time_about.setText((dataBean.getDistance()/1000)+"km");
        holder.km.setText("");
        holder.shop_name.setText(""+dataBean.getMerchantName());
        holder.type.setText(""+dataBean.getCategoryName());
        holder.ratingbar.setmClickable(false);
        holder.ratingbar.setStar(Math.round((dataBean.getAvgScore()/20)));
        LogPrint.printError("星星啦啦啦"+Math.round((dataBean.getAvgScore()/20)));

        if(dataBean.getBusinessStatus()==0){
            holder.x_x.setVisibility(View.VISIBLE);
        }else{
            holder.x_x.setVisibility(View.GONE);
        }

        GlideUtils glideUtils=new GlideUtils();
        glideUtils.glides(context,dataBean.getMainImg(),holder.imagurl);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsAll.GoGoodDetailsAndLocation(context, ConfigH5Url.merchant_r_d(dataBean.getTemplateId()+""));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.getBody().getContent().getList().size();
    }
    class PersonViewHolder extends RecyclerView.ViewHolder  {
        public RelativeLayout rootView,x_x;
        public ImageView imagurl;
        public RatingBar ratingbar;
        public TextView shop_name;
        public TextView km;
        public TextView type_name;
        public TextView time_about,type;


        public PersonViewHolder(View itemView) {
            super(itemView);
            shop_name=itemView.findViewById(R.id.shop_name);
            km=itemView.findViewById(R.id.km);
            type_name=itemView.findViewById(R.id.type_name);
            time_about=itemView.findViewById(R.id.time_about);
            ratingbar=itemView.findViewById(R.id.ratingbar);
            rootView = itemView.findViewById(R.id.show_click);
            imagurl=itemView.findViewById(R.id.show_image);
            type=itemView.findViewById(R.id.type);
            x_x=itemView.findViewById(R.id.x_x);

        }

    }
}
