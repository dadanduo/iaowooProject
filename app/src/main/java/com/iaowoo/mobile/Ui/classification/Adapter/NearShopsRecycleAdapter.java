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

import com.bumptech.glide.Glide;
import com.hedgehog.ratingbar.RatingBar;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.FenLei;
import com.iaowoo.mobile.Ui.classification.Model.NearShops;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;

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
 * @Description: ${todo}(附近门店)
 * @date 2018/9/10
 * @email ${18011009889@163.com}
 */
public class NearShopsRecycleAdapter extends  AdvancedAdapter<NearShopsRecycleAdapter.ViewHolder,NearShops.BodyBean.ContentBean.ListBean> {

    private Context mContext;

    public NearShopsRecycleAdapter(Context mContext,  ItemClickInterface listener) {
        this.mContext = mContext;
        this.listener = listener;
    }

    public void setData(List<NearShops.BodyBean.ContentBean.ListBean> mData){
        this.mData = mData;
    }
    @Override
    public void onBindAdvanceViewHolder(ViewHolder holder, int i) {
        final NearShops.BodyBean.ContentBean.ListBean data = getItem(i);
        if (data != null) {
            holder.type_name.setText(""+data.getDetailAddress());
            holder.time_about.setText((data.getDistance()/1000)+"km");
            holder.km.setText("");
            holder.shop_name.setText(""+data.getMerchantName());
            holder.type.setText(""+data.getCategoryName());
            holder.ratingbar.setmClickable(false);
            holder.ratingbar.setStar(Math.round((data.getAvgScore()/20)));
            LogPrint.printError("星星啦啦啦"+Math.round((data.getAvgScore()/20)));
            if(data.getBusinessStatus()==0){
                holder.x_x.setVisibility(View.VISIBLE);
            }else{
                holder.x_x.setVisibility(View.GONE);
            }

            GlideUtils glideUtils=new GlideUtils();
            glideUtils.glides(mContext,data.getMainImg(),holder.imagurl);
            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogPrint.printError("路由"+ ConfigH5Url.merchant_r_d(data.getTemplateId()));
                    UtilsAll.GoGoodDetailsAndLocation(mContext, ConfigH5Url.merchant_r_d(data.getTemplateId()+""));
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateAdvanceViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.shop_recycle_item, parent, false));
        return vh;
    }

    public class ViewHolder extends AdvancedAdapter.ViewHolder {
        public RelativeLayout rootView,x_x;
        public ImageView imagurl;
        public RatingBar ratingbar;
        public TextView shop_name;
        public TextView km;
        public TextView type_name;
        public TextView time_about,type;

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

        @Override
        public int getAdpPosition() {
            return getAdapterPosition()-getmHeaderViews();
        }
    }
}
