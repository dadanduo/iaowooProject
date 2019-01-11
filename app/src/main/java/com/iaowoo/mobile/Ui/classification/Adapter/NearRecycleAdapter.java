package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
 * @Description: ${todo}(附近  全部   智能排序)
 * @date 2018/9/10
 * @email ${18011009889@163.com}
 */
public class NearRecycleAdapter extends  AdvancedAdapter<NearRecycleAdapter.ViewHolder,String> {

    private Context mContext;

    public NearRecycleAdapter(Context mContext, List<String> mData, ItemClickInterface listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.listener = listener;
    }
    @Override
    public void onBindAdvanceViewHolder(ViewHolder holder, int i) {
       String data = getItem(i);
        if (data != null) {
             holder.nearName.setText(data.trim()+"");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateAdvanceViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listview_shop_map, parent, false));
        return vh;
    }

    public class ViewHolder extends AdvancedAdapter.ViewHolder {

        public TextView nearName;

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
            nearName=itemView.findViewById(R.id.nearName);
        }

        @Override
        public int getAdpPosition() {
            return getAdapterPosition()-getmHeaderViews();
        }
    }
}
