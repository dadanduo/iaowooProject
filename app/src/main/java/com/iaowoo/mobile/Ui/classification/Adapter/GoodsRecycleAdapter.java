package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Ui.classification.Presenter.ChinaGoodsPresenter;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.ChinaGoodsPresenter;
import com.iaowoo.mobile.Ui.classification.View.ImageViewHolder;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;

import java.util.ArrayList;
import java.util.List;

/**
 * 中国好物dapter
 * Created by chenda on 2018/4/3.
 */

public class GoodsRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    //item的高度
    private List<Integer> mHeights;
    private LayoutInflater mLayoutInflater;
    private  List<Banner.BodyBean.ContentBean.ListBean> banners;
    private Context mContext;
    private int mHeaderCount=1;//头部View个数
    private GlideUtils glideUtils;
    private ChinaGoodsPresenter chinaGoodsProsenter;
    private RelativeLayout recycler_ver_r;
//    private GalleryCycleImageView recycler_ver;
    private ConvenientBanner recycler_ver;
    /**
     * 轮播数据                lunBo(recycler_ver,LurC,banners);

     */
    private List<String> LurC = null;

    private List<Shop.BodyBean.ContentBean.ListBean> shops;


    private Banner.BodyBean.ContentBean.ListBean  zq1_banner;



    public GoodsRecycleAdapter(Context context,ChinaGoodsPresenter chinaGoodsProsenter){
        this.mContext = context;
        this.chinaGoodsProsenter=chinaGoodsProsenter;

        mLayoutInflater = LayoutInflater.from(context);
        glideUtils=new GlideUtils();
    }

    public void setShops(List<Shop.BodyBean.ContentBean.ListBean> shops){
        this.shops=shops;
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
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.p_g_itme_xml, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.itme_home_xml_h, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
        } else if (holder instanceof ContentViewHolder) {

            if(position%2==0){
                if(position!=1||position!=2){
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,5,10,28,0);
                }else{
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,5,0,28,0);
                }
            }else{
                if(position!=1||position!=2){
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,28,10,5,0);
                }else{
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,28,0,5,0);
                }            }
            //表示共享
            double price=shops.get(position-1).getSubTemplateInfoList().get(0).getSellPrice();
            //如果小数点后面的为零直接去掉不然保留
            if(price%1==0){
                int tmp = (int)price;
                ((ContentViewHolder) holder).price.setText("¥" +tmp);
            }else{
                ((ContentViewHolder) holder).price.setText("¥" +price);
            }

//            if(shops.get(position-1).getType()==1||shops.get(position-1).getType()==3){
                ((ContentViewHolder) holder).show_shop_name.setText(Html.fromHtml(SingleOverAll.getInstance().descString(shops.get(position-1).getProductInfo().getName()), SingleOverAll.getInstance().getImageGetterInstance(), null));
//            }else if(shops.get(position-1).getType()==5){
//                ((ContentViewHolder) holder).show_shop_name.setText(Html.fromHtml(SingleOverAll.getInstance().descString2(shops.get(position-1).getProductInfo().getName()), SingleOverAll.getInstance().getImageGetterInstance(), null));
//            }
            ((ContentViewHolder) holder).seld.setText("销量" + shops.get(position-1).getSales() + "件");
            if (shops.get(position-1).getProductInfo().getMainImage() != null) {
                glideUtils.glides(mContext, shops.get(position-1).getProductInfo().getMainImage(), ((ContentViewHolder) holder).image_home);
            }

            ((ContentViewHolder) holder). home_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(shops.get(position-1).getType()==1||shops.get(position-1).getType()==3){
                        UtilsAll.GoNativeGoodsDetails(mContext,shops.get(position-1).getTemplateId()+"","","");
                    }else if(shops.get(position-1).getType()==5){
                        UtilsAll.GoWebviewAll(mContext, ConfigH5Url.setGoodsDetails(shops.get(position-1).getType(),shops.get(position-1).getTemplateId()));
                    }
                }
            });
        }
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null)
        {
            //itemView:ViewHolder的一个item
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
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
        //        private LinearLayout pg_view;
//        private TextView person_num;
        private LinearLayout home_item;

        public ContentViewHolder(View itemView) {
            super(itemView);
            show_shop_name=itemView.findViewById(R.id.show_shop_name);
            price=itemView.findViewById(R.id.price_this);
            seld=itemView.findViewById(R.id.seld);
            image_home=itemView.findViewById(R.id.image_home);
//            person_num=itemView.findViewById(R.id.person_num);
//            pg_view=itemView.findViewById(R.id.pg_view);
            home_item=itemView.findViewById(R.id.home_item);
        }
    }
    //头部 ViewHolder
    public  class HeaderViewHolder extends RecyclerView.ViewHolder implements ChinaGoodsPresenter.ChinaGoodsCallBack {


        private ImageView goods_station;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            recycler_ver=itemView.findViewById(R.id.recycler_ver);
            recycler_ver_r=itemView.findViewById(R.id.recycler_ver_r);
            chinaGoodsProsenter.setCallBack(this);
            //获取banner数据
            chinaGoodsProsenter.getBanner();
            int height= SingleOverAll.getInstance().getStatusBarHeight1(ZApplication.getContext());
            UtilsAll.setMargins(recycler_ver_r,36,height,36,0);
            goods_station=itemView.findViewById(R.id.goods_station);
            goods_station.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogPrint.printError("中国好物馆");
                    SingleOverAll.getInstance().bannerClick(mContext,zq1_banner);
                }
            });
        }

        @Override
        public void getBanner(Banner banner) {
            int size = banner.getBody().getContent().getList().size();
            if (size != 0) {
                //轮播图
                LurC = new ArrayList<>();
                banners=new ArrayList<>();
                //添加轮播数据
                for (int i = 0; i < size; i++) {
                    if (!TextUtils.isEmpty(banner.getBody().getContent().getList().get(i).getBannerImg())) {
                        if (banner.getBody().getContent().getList().get(i).getLocation() == 14) {
                            LurC.add(banner.getBody().getContent().getList().get(i).getBannerImg());
                            banners.add(banner.getBody().getContent().getList().get(i));

                        }else if(banner.getBody().getContent().getList().get(i).getLocation() == 20){
                            zq1_banner=banner.getBody().getContent().getList().get(i);
                            glideUtils.glides(mContext,banner.getBody().getContent().getList().get(i).getBannerImg(), goods_station);
                        }
                    }
                }
                LogPrint.printError("🐔🐔崩" + LurC.size());
                lunBo(recycler_ver,LurC,banners);

            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
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
    public void lunBo(ConvenientBanner mcb, List<String> L, final List<Banner.BodyBean.ContentBean.ListBean>  list){
        if(L.size()>1){
            //轮播图配置
            mcb.setPages(new CBViewHolderCreator<ImageViewHolder>() {
                @Override
                public ImageViewHolder createHolder() {
                    return new ImageViewHolder();
                }
            },L)
                    .setPageIndicator(new int[]{R.mipmap.turn_page_unselected_icon,R.mipmap.turn_page_selected_icon}) //设置两个点作为指示器
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL); //设置指示器的方向水平居中

            mcb.startTurning(ConfigFlag.TIME_DI);
        }else{
            //轮播图配置
            mcb.setPages(new CBViewHolderCreator<ImageViewHolder>() {
                @Override
                public ImageViewHolder createHolder() {
                    return new ImageViewHolder();
                }
            },L); //设置指示器的方向水平居中

        }
        mcb.setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SingleOverAll.getInstance().bannerClick(mContext,list.get(position));

            }
        });
    }


}
