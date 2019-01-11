package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.SearchGoods;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;

import java.util.List;

/**
 * Created by chenda on 2018/4/3.
 * 保障adapter
 */

public class shopItemRecycleAdapter extends BaseSoEasyAdapter{

    private Context context;

    private int min,max,chooseNumber;

    private String guige,miaosu;

    private List<SearchGoods.BodyBean.ContentBean.SubTemplateInfoListBean>  subTemplateInfoListBeans;

    private GlideUtils glideUtils;

    private callBackok callBackok;

    private double pay;

    public interface  callBackok{
        void numberCall(int numbersCallNo);
        void redCount(double redEd);

    }

    public shopItemRecycleAdapter(Context context, GlideUtils glideUtils,callBackok callBackok) {
        this.context=context;
        this.glideUtils=glideUtils;
        this.callBackok=callBackok;
    }



    public void SetData(List<SearchGoods.BodyBean.ContentBean.SubTemplateInfoListBean>  subTemplateInfoListBeans, int min, int max, int chooseNumber, String guige, String miaosu,double pay){
        this.subTemplateInfoListBeans= subTemplateInfoListBeans;
        this.min=min;
        this.max=max;
        this.chooseNumber=chooseNumber;
        this.guige=guige;
        this.miaosu=miaosu;
        this.pay=pay;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_two_layout,parent,false);

        return new CommentsViewHolder(view);
    }

    /**
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final CommentsViewHolder holder = (CommentsViewHolder) viewHolder;
        //设置头像
        if(!TextUtils.isEmpty(subTemplateInfoListBeans.get(position).getMainImage())){
            glideUtils.glides(context,subTemplateInfoListBeans.get(position).getMainImage(),holder.goods_image);
        }
        holder.describe_item.setText(""+miaosu);
        holder.gui_ge.setText(""+guige);
        //设置价格
        if(!TextUtils.isEmpty(subTemplateInfoListBeans.get(position).getSellPrice()+"")){
            holder.price_text.setText("￥"+ UtilsAll.DoubleTo_2(subTemplateInfoListBeans.get(position).getSellPrice())+"/件");
        }
        //设置选择件数
        holder.show_text.setText(""+chooseNumber);
        holder.jian_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(holder.show_text.getText().toString());
                if(number>min) {
                    --number;
                    holder.show_text.setText("" + number);
                    callBackok.numberCall(number);
                }else{
                    ToastUtilsAll.getInstance().showShortToast("亲！该商品至少要购买"+min+"件");
                }
            }
        });
        holder.add_btm_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number= Integer.parseInt(holder.show_text.getText().toString());
                if(number<max){
                    ++number;
                    holder.show_text.setText(""+number);
                    callBackok.numberCall(number);
                }else{
                    ToastUtilsAll.getInstance().showShortToast("亲！该商品最多只能购买"+max+"件");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(subTemplateInfoListBeans!=null){
            return subTemplateInfoListBeans.size();
        }
        return 0;
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder {
        public TextView gui_ge;
        public TextView describe_item,price_text,show_text;
        public ImageView goods_image;
        public RelativeLayout jian_two,add_btm_two;
        public CommentsViewHolder(View itemView) {
            super(itemView);
            goods_image=itemView.findViewById(R.id.goods_image);
            describe_item=itemView.findViewById(R.id.describe_item);
            gui_ge=itemView.findViewById(R.id.gui_ge);
            price_text=itemView.findViewById(R.id.price_text);
            show_text=itemView.findViewById(R.id.show_text);
            jian_two=itemView.findViewById(R.id.jian_two);
            add_btm_two=itemView.findViewById(R.id.add_btm_two);
        }

    }
}
