package com.iaowoo.mobile.Ui.classification.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.PaySuccessM;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.UtilsAll;

import java.util.List;

/**
 * 支付成功dapter
 * Created by chenda on 2018/4/3.
 */

public class PaySuccessRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    //item的高度
    private List<Integer> mHeights;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mHeaderCount=1;//头部View个数
    private GlideUtils glideUtils;
    private HomeFragmentPresenter homeFragmentProsenter;

    private List<Shop.BodyBean.ContentBean.ListBean> shops;

    private BackCall backCall;

    private boolean oneNumber=true;

    private PaySuccessM.BodyBean.ContentBean contentBean;

    private String orderID;


    public PaySuccessRecycleAdapter(Context context, HomeFragmentPresenter homeFragmentProsenter,BackCall backCall){
        this.mContext = context;
        this.homeFragmentProsenter=homeFragmentProsenter;
        this.backCall=backCall;
        mLayoutInflater = LayoutInflater.from(context);
        glideUtils=new GlideUtils();
    }

    public interface  BackCall{
        void zaiguangguang();
        void showShare(String orderID);
    }

    public void setShops(List<Shop.BodyBean.ContentBean.ListBean> shops,PaySuccessM.BodyBean.ContentBean contentBean,String orderID){
        this.shops=shops;
        this.contentBean=contentBean;
        this.orderID=orderID;
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }
    //内容长度
    public int getContentItemCount(){
        if(shops!=null){
            return shops.size();
        }
        return  0;
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (isHeaderView(position)) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType ==ITEM_TYPE_HEADER) {
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.pay_success_head, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.itme_home_xml_h, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder)holder).shang_p.setText(orderID);
            ((HeaderViewHolder)holder).xiao_fei.setText(contentBean.getPayAmount()+"元");
            ((HeaderViewHolder)holder).fan_xian.setText(contentBean.getProfit()+"元");
            ((HeaderViewHolder)holder).ji_fen.setText( contentBean.getIntegralValue()+"");

            if(contentBean.getGemNum()!=0){
                if(oneNumber) {
                    oneNumber=false;
                    createBaoShiQaunDialog(mContext, contentBean.getGemNum());
                }
            }
            if(contentBean.isHaveShareRedEnvelope()){
                backCall.showShare(contentBean.getShareRedOrderId());
            }
        } else if (holder instanceof ContentViewHolder) {
            if(position%2==0){
                if(position!=1||position!=2){
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,5,10,8,0);
                }else{
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,5,0,8,0);
                }
            }else{
                if(position!=1||position!=2){
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,8,10,5,0);
                }else{
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,8,0,5,0);
                }            }
//            if(shops.get(position-1).getType()==1||shops.get(position-1).getType()==3){
                ((ContentViewHolder) holder).show_shop_name.setText(Html.fromHtml(SingleOverAll.getInstance().descString(shops.get(position-1).getProductInfo().getName()), SingleOverAll.getInstance().getImageGetterInstance(), null));
//            }else if(shops.get(position-1).getType()==5){
//                ((ContentViewHolder) holder).show_shop_name.setText(Html.fromHtml(SingleOverAll.getInstance().descString2(shops.get(position-1).getProductInfo().getName()), SingleOverAll.getInstance().getImageGetterInstance(), null));
//            }
            //表示共享
            double price=shops.get(position-1).getSubTemplateInfoList().get(0).getSellPrice();
            //如果小数点后面的为零直接去掉不然保留
            if(price%1==0){
                int tmp = (int)price;
                ((ContentViewHolder) holder).price.setText("¥" +tmp);
            }else{
                ((ContentViewHolder) holder).price.setText("¥" +price);
            }

            ((ContentViewHolder) holder).seld.setText("销量" + shops.get(position-1).getSales() + "件");
            if (shops.get(position-1).getProductInfo().getHomeImage() != null) {
                glideUtils.glides(mContext, shops.get(position-1).getProductInfo().getMainImage(), ((ContentViewHolder) holder).image_home);
            }

            ((ContentViewHolder) holder). home_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    UtilsAll.GoWebviewAll(mContext,ConfigH5Url.setGoodsDetails(shops.get(position-1).getType(),shops.get(position-1).getTemplateId()));
                    UtilsAll.GoNativeGoodsDetails(mContext,shops.get(position-1).getTemplateId()+"","","");
                }
            });

        }
    }
    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount();
    }

    //内容 ViewHolder
    public  class ContentViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_home;
        private TextView show_shop_name;
        private TextView price;
        private TextView seld;
        private LinearLayout home_item;

        public ContentViewHolder(View itemView) {
            super(itemView);
            show_shop_name=itemView.findViewById(R.id.show_shop_name);
            price=itemView.findViewById(R.id.price_this);
            seld=itemView.findViewById(R.id.seld);
            image_home=itemView.findViewById(R.id.image_home);
            home_item=itemView.findViewById(R.id.home_item);
        }
    }
    //头部 ViewHolder
    public  class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView shang_p,xiao_fei,fan_xian,ji_fen;
        private RelativeLayout zai_gaung_g;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            zai_gaung_g=itemView.findViewById(R.id.zai_gaung_g);
            shang_p=itemView.findViewById(R.id.shang_p);
            xiao_fei=itemView.findViewById(R.id.xiao_fei);
            fan_xian=itemView.findViewById(R.id.fan_xian);
            ji_fen=itemView.findViewById(R.id.ji_fen);
            zai_gaung_g.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backCall.zaiguangguang();
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == ITEM_TYPE_HEADER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 宝石券
     */
    private  void createBaoShiQaunDialog(Context context,int number) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View  v = inflater.inflate(R.layout.baoshiquan, null);// 得到加载view
        LinearLayout layout = v.findViewById(R.id.bg);// 加载布局
        TextView baoshiquan_number=v.findViewById(R.id.baoshiquan_number);
        RelativeLayout zhidaole=v.findViewById(R.id.zhidaole);

        baoshiquan_number.setText("获得"+number+"张宝石券");
        /**
         *将显示Dialog的方法封装在这里面
         */
        final Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.TOP);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        zhidaole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
            }
        });
    }


}
