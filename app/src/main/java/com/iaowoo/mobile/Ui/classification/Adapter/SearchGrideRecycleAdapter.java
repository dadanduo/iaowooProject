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

import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.UtilsAll;

import java.util.List;

/**
 * Created by chenda on 2018/4/3.
 */

public class SearchGrideRecycleAdapter extends RecyclerView.Adapter{

    private Context context;

    private List<Shop.BodyBean.ContentBean.ListBean> shops;

    public SearchGrideRecycleAdapter(Context context) {
        this.context=context;
    }
    public void setBigData(List<Shop.BodyBean.ContentBean.ListBean> shops){
        this.shops = shops;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.p_g_gride_adpter,parent,false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        double price=shops.get(position).getSubTemplateInfoList().get(0).getSellPrice();
        if(price%1==0){
            int tmp = (int)price;
            holder.price.setText("¥" +tmp);
        }else{
            holder.price.setText("¥" +price);
        }
        holder.much.setText("销量"+shops.get(position).getSales()+"件");
        GlideUtils glideUtils=new GlideUtils();
        if(shops.get(position).getProductInfo().getHomeImage()!=null) {
            glideUtils.glides(context, shops.get(position).getProductInfo().getMainImage(),holder.image_shop);
        }
//        if(shops.get(position).getType()==1||shops.get(position).getType()==3){
            holder.showshop.setText(Html.fromHtml(SingleOverAll.getInstance().descString(shops.get(position).getProductInfo().getName()), SingleOverAll.getInstance().getImageGetterInstance(), null));
//        }else if(shops.get(position).getType()==5){
//            holder.showshop.setText(Html.fromHtml(SingleOverAll.getInstance().descString2(shops.get(position).getProductInfo().getName()), SingleOverAll.getInstance().getImageGetterInstance(), null));
//        }
        holder.item_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsAll.GoNativeGoodsDetails(context,shops.get(position).getTemplateId()+"","","");
//                UtilsAll.GoWebviewAll(context, ConfigH5Url.setGoodsDetails(shops.get(position).getType(),shops.get(position).getTemplateId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(shops!=null){
            return shops.size();
        }
        return  0;
    }


    class PersonViewHolder extends RecyclerView.ViewHolder  {

        public ImageView image_shop;
        public TextView showshop;
        public TextView price,much;
        public LinearLayout item_click;
        public PersonViewHolder(View itemView) {
            super(itemView);
            image_shop=itemView.findViewById(R.id.image_shop);
            showshop=itemView.findViewById(R.id.showshop);
            price=itemView.findViewById(R.id.price);
            much=itemView.findViewById(R.id.much);
            item_click=itemView.findViewById(R.id.item_click);
        }


    }
}
