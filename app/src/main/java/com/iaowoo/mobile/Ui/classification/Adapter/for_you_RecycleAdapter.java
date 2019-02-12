package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Activity.GoodsDetailsActivity;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.GoodsDetailsActivity;
import com.iaowoo.mobile.Ui.classification.Model.RecommentList;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;

import java.util.List;

/**
 * 商品详情评价
 * Created by chenda on 2018/4/3.
 */

public class for_you_RecycleAdapter extends BaseSoEasyAdapter{


    private Context context;

    private GlideUtils glideUtils;

    private List<RecommentList.BodyBean.ContentBean.RecommendListBean> recommendListBeans;

    public for_you_RecycleAdapter(Context context, GlideUtils glideUtils, OnclicKRecycleAdapter onclicKRecycleAdapter) {
        this.context=context;
        this.glideUtils=glideUtils;
        this.onclicKRecycleAdapter=onclicKRecycleAdapter;
    }

    public void setData(List<RecommentList.BodyBean.ContentBean.RecommendListBean> recommendListBeans){
        this.recommendListBeans=recommendListBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_you_ok,parent,false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        //设置图片
        if(!TextUtils.isEmpty(recommendListBeans.get(position).getProductInfo().getMainImage())){
            glideUtils.glides(context,recommendListBeans.get(position).getProductInfo().getMainImage(),holder.image_main);
        }
        //设置描述
        if(!TextUtils.isEmpty(recommendListBeans.get(position).getProductInfo().getQueryName())){
            holder.descridb_ok.setText(""+recommendListBeans.get(position).getProductInfo().getQueryName());
        }
        //设置价格
        if(!TextUtils.isEmpty(recommendListBeans.get(position).getSubTemplateInfoList().get(0).getSellPrice()+"")){
            holder.price_ok.setText("￥"+recommendListBeans.get(position).getSubTemplateInfoList().get(0).getSellPrice());
        }
        //设置已卖数量
        if(!TextUtils.isEmpty(recommendListBeans.get(position).getSales()+"")){
            holder.ok_ok.setText("已售"+recommendListBeans.get(position).getSales()+"件");
        }
        //设置积分
        if(PrefManager.getInstance().getIntegralRatio()!=0){
            if(recommendListBeans.get(position).getSubTemplateInfoList().get(0)!=null) {
                Float pv = (float) recommendListBeans.get(position).getSubTemplateInfoList().get(0).getPv();
                Float integral = PrefManager.getInstance().getIntegralRatio();
                holder.integral_text.setText(UtilsAll.DoubleTo_2(pv / integral) + "");
            }
        }

        //item点击
        holder.for_you_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicKRecycleAdapter.onItemClick(position);
                Intent mintent = new Intent(context, GoodsDetailsActivity.class);
                mintent.putExtra("goodsId", recommendListBeans.get(position).getTemplateId());
                mintent.putExtra("activityId","0");
                mintent.putExtra("tagNoReturn","no");
                context.startActivity(mintent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(recommendListBeans!=null){
            LogPrint.printError("总数据"+recommendListBeans.size());
            return recommendListBeans.size();
        }
        return  0;
    }

    class PersonViewHolder extends RecyclerView.ViewHolder  {
        public ImageView image_main;
        public TextView descridb_ok,price_ok,ok_ok,integral_text;
        public LinearLayout for_you_click;
        public PersonViewHolder(View itemView) {
            super(itemView);
            image_main=itemView.findViewById(R.id.image_main);
            descridb_ok=itemView.findViewById(R.id.descridb_ok);
            price_ok=itemView.findViewById(R.id.price_ok);
            ok_ok=itemView.findViewById(R.id.ok_ok);
            integral_text=itemView.findViewById(R.id.integral_text);
            for_you_click=itemView.findViewById(R.id.for_you_click);
        }
    }
}
