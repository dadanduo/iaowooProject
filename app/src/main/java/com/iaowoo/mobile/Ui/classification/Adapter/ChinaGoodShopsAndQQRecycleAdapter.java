package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;

import java.util.List;

/**
 * ////////////////////////
 * //  ┏┓　　　┏┓///////////
 * //┏┛┻━━━┛┻┓ ////////////
 * //┃　　　　　　　┃     ////
 * //┃　　　━　　　┃     ////
 * //┃　┳┛　┗┳　┃       /////
 * //┃　　　　　　　┃     ////
 * //┃　　　┻　　　┃         //
 * //┃　　　　　　　┃        ///
 * //┗━┓　　　┏━┛           ///
 * //    ┃　　　┃   神兽保佑  ///
 * //    ┃　　　┃   代码无BUG！///
 * //    ┃　　　┗━━━┓     ///
 * //    ┃　　　　　　　┣┓ ///
 * //    ┃　　　　　　　┏┛ ///
 * //    ┗┓┓┏━┳┓┏┛      ///
 * //      ┃┫┫　┃┫┫     ///
 * ///////////////////////
 *
 * @author ${chenda}
 * @version V1.0
 * @Description: ${todo}(中国好货and全球集市)
 * @date 2018/9/10
 * @email ${18011009889@163.com}
 */
public class ChinaGoodShopsAndQQRecycleAdapter extends  AdvancedAdapter<ChinaGoodShopsAndQQRecycleAdapter.ViewHolder,Shop.BodyBean.ContentBean.ListBean> {

    private Context mContext;

    public ChinaGoodShopsAndQQRecycleAdapter(Context mContext, List<Shop.BodyBean.ContentBean.ListBean> mData, ItemClickInterface listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.listener = listener;
    }
    @Override
    public void onBindAdvanceViewHolder(ViewHolder holder, int i) {
        Shop.BodyBean.ContentBean.ListBean data = getItem(i);
        if (data != null) {
            double price=data .getSubTemplateInfoList().get(0).getSellPrice();
            if(price%1==0){
                int tmp = (int)price;
                holder.price.setText("¥" +tmp);
            }else{
                holder.price.setText("¥" +price);
            }
            holder.showshop.setText((data.getProductInfo().getName()));
            GlideUtils glideUtils=new GlideUtils();
            if(data .getProductInfo().getHomeImage()!=null) {
                glideUtils.glides(mContext, data.getProductInfo().getMainImage(),holder.image_shop);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateAdvanceViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.everday_xml, parent, false));
        return vh;
    }

    public class ViewHolder extends AdvancedAdapter.ViewHolder {
        public ImageView image_shop;
        public TextView showshop;
        public TextView price;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(mData.get(getAdpPosition()), getAdpPosition());
                    }
                }
            });
            image_shop=itemView.findViewById(R.id.image_shop);
            showshop=itemView.findViewById(R.id.showshop);
            price=itemView.findViewById(R.id.price);
        }

        @Override
        public int getAdpPosition() {
            return getAdapterPosition()-getmHeaderViews();
        }
    }
}
