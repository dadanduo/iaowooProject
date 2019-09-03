package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.YouHuiQuan;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;

import java.util.List;

/**
 * Created by chenda on 2018/4/3.
 * 优惠券
 */

public class youhuiquanRecycleAdapter extends BaseSoEasyAdapter{

    private Context context;

    private List<YouHuiQuan.BodyBean.ContentBean.ListBean> listBeans;

    private GlideUtils glideUtils;

    private double max;

    public youhuiquanRecycleAdapter(Context context, GlideUtils glideUtils,OnclicKRecycleAdapter onclicKRecycleAdapter) {
        this.context=context;
        this.glideUtils=glideUtils;
        this.onclicKRecycleAdapter=onclicKRecycleAdapter;
    }

    public void SetData(List<YouHuiQuan.BodyBean.ContentBean.ListBean> listBeans,double max){
        this.listBeans= listBeans;
        this.max=max;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youhuiquan_adapter,parent,false);

        return new CommentsViewHolder(view);
    }

    /**
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final CommentsViewHolder holder = (CommentsViewHolder) viewHolder;
        if(!TextUtils.isEmpty(listBeans.get(position).getFaceValue()+"")){
            holder.price.setText(""+UtilsAll.DoubleTo_2(listBeans.get(position).getFaceValue()));
        }
        holder.miaoshu.setText(""+listBeans.get(position).getCouponName());

        if(!TextUtils.isEmpty(listBeans.get(position).getCouponType())){
            holder.keyiganma.setText(""+listBeans.get(position).getCouponType());

        }
        holder.name.setText("满"+ UtilsAll.DoubleTo_2(listBeans.get(position).getLimitValue())+"减"+UtilsAll.DoubleTo_2(listBeans.get(position).getFaceValue()));

        if(max>=listBeans.get(position).getLimitValue()){
            holder.layout_shi_fo.setBackgroundResource(R.drawable.youhui_adapter_ok);
            holder.youhuiquan_show.setBackgroundResource(R.drawable.youhui_adapter_ok_all);
        }else{
            holder.layout_shi_fo.setBackgroundResource(R.drawable.youhui_adapter_no);
            holder.youhuiquan_show.setBackgroundResource(R.drawable.youhui_adapter_no_all);
        }

        StringBuffer stringBuffer=new StringBuffer();

        if(!TextUtils.isEmpty(listBeans.get(position).getBeginTime())){
            stringBuffer.append(listBeans.get(position).getBeginTime()+"   -   ");
        }

        if(!TextUtils.isEmpty(listBeans.get(position).getGenerateTime())){
            stringBuffer.append(listBeans.get(position).getGenerateTime());
        }

        holder.date_riqi.setText(""+stringBuffer);

        holder.use_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(max>=listBeans.get(position).getLimitValue()){
                    onclicKRecycleAdapter.onItemClick(position);
                }else{
                    ToastUtilsAll.getInstance().showShortToast("该优惠券不能使用");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listBeans!=null){
            return listBeans.size();
        }
        return 0;
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder {
        public TextView price;
        public TextView name;
        public TextView show_image_baozhang,miaoshu,keyiganma,date_riqi;
        public RelativeLayout layout_shi_fo,youhuiquan_show;
        public LinearLayout use_it;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            use_it=itemView.findViewById(R.id.use_it);
            layout_shi_fo=itemView.findViewById(R.id.layout_shi_fo);
            price=itemView.findViewById(R.id.price);
            name=itemView.findViewById(R.id.name);
            youhuiquan_show=itemView.findViewById(R.id.youhuiquan_show);
            miaoshu=itemView.findViewById(R.id.miaoshu);
            keyiganma=itemView.findViewById(R.id.keyiganma);
            date_riqi=itemView.findViewById(R.id.date_riqi);
        }

    }
}
