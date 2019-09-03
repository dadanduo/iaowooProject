package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.EearthFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.ImageViewHolder;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *拼购推荐adapter gride 分类adapter
 * Created by chenda on 2018/4/3.
 */

public class BigRecycleAdapter   extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    private EearthFragmentPresenter earthFragmentProsenter;
    private OnRecyclerViewListener onRecyclerViewListener;
    private LayoutInflater mLayoutInflater;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 当前数据
     */
    private List<Shop.BodyBean.ContentBean.ListBean> shops;
    /**
     * 头部View个数
     */
    private int mHeaderCount=1;

    private String categroy;

    private ConvenientBanner fenlei_two;

    /**
     * 轮播数据
     */
    private List<String> LurC = null;

    private  List<Banner.BodyBean.ContentBean.ListBean> banners;


    public interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    /**
     * 设置回调接口
     * @param onRecyclerViewListener
     */
    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
    /**
     * 设置内容和prosenter
     * @param context
     */
    public BigRecycleAdapter( Context context,EearthFragmentPresenter earthFragmentProsenter,String categroy) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context=context;
        this.earthFragmentProsenter=earthFragmentProsenter;
        this.categroy=categroy;
    }
    /**
     * 设置数据
     * @param shops
     */
    public void setBigData(List<Shop.BodyBean.ContentBean.ListBean> shops){
        this.shops = shops;
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
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.fenlei_head1, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.p_g_gride_adpter, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
        } else if (holder instanceof ContentViewHolder) {
            double price=shops.get(position-1).getSubTemplateInfoList().get(0).getSellPrice();
            if(price%1==0){
                int tmp = (int)price;
                ((ContentViewHolder) holder).price.setText("¥" +tmp);
            }else{
                ((ContentViewHolder) holder).price.setText("¥" +price);
            }
            if(shops.get(position-1).getType()==1||shops.get(position-1).getType()==1){
                ((ContentViewHolder) holder).showshop.setText(Html.fromHtml(SingleOverAll.getInstance().descString(shops.get(position-1).getProductInfo().getName()), SingleOverAll.getInstance().getImageGetterInstance(), null));
            }else if(shops.get(position-1).getType()==2){
                ((ContentViewHolder) holder).showshop.setText(Html.fromHtml(SingleOverAll.getInstance().descString2(shops.get(position-1).getProductInfo().getName()), SingleOverAll.getInstance().getImageGetterInstance(), null));
            }
            ((ContentViewHolder) holder).much.setText("销量"+shops.get(position-1).getSales()+"件");
            GlideUtils glideUtils=new GlideUtils();
            if(shops.get(position-1).getProductInfo().getMainImage()!=null) {
                if(shops.get(position-1).getProductInfo().getMainImage().contains(".gif")){
                    glideUtils.glidesGif(context, shops.get(position-1).getProductInfo().getMainImage(),((ContentViewHolder) holder).image_shop);
                }else{
                    glideUtils.glides(context, shops.get(position-1).getProductInfo().getMainImage(),((ContentViewHolder) holder).image_shop);
                }
            }
            ((ContentViewHolder) holder).item_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(shops.get(position-1).getType()==1){
                        UtilsAll.GoWebviewAll(context, ConfigH5Url.setGoodsDetails(shops.get(position-1).getType(),shops.get(position-1).getTemplateId()));
                    }else{
                        UtilsAll.GoNativeGoodsDetails(context,shops.get(position-1).getTemplateId()+"","","");
                    }
                }
            });
            if(PrefManager.getInstance().getIntegralRatio()!=0){
                if(shops.get(position-1).getSubTemplateInfoList().get(0)!=null) {
                    Float pv = (float) shops.get(position-1).getSubTemplateInfoList().get(0).getPv();
                    Float integral = PrefManager.getInstance().getIntegralRatio();
                    ((ContentViewHolder) holder).integral_text.setText(UtilsAll.DoubleTo_2(pv / integral) + "");
                }
            }

        }
    }

    //内容 ViewHolder
    public  class ContentViewHolder extends RecyclerView.ViewHolder {
        public ImageView image_shop;
        public TextView showshop;
        public TextView price,much,integral_text;
        public LinearLayout item_click;
        public ContentViewHolder(View itemView) {
            super(itemView);
            image_shop=itemView.findViewById(R.id.image_shop);
            showshop=itemView.findViewById(R.id.showshop);
            price=itemView.findViewById(R.id.price);
            much=itemView.findViewById(R.id.much);
            integral_text=itemView.findViewById(R.id.integral_text);
            item_click=itemView.findViewById(R.id.item_click);

            ViewGroup.LayoutParams para;
            para =  image_shop.getLayoutParams();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;// 屏幕宽度（像素）

            para.height = (width-22)/2;
            para.width =(width-22)/2;
            image_shop.setLayoutParams(para);
        }
    }
    //头部 ViewHolder
    public  class HeaderViewHolder extends RecyclerView.ViewHolder implements EearthFragmentPresenter.EearthCallBack1 {
        public HeaderViewHolder(View itemView) {
            super(itemView);
            fenlei_two=itemView.findViewById(R.id.fenlei_two);
            if(earthFragmentProsenter!=null) {
                earthFragmentProsenter.getSlideShowData(categroy);
                earthFragmentProsenter.setCallBack1(this);
            }
        }

        @Override
        public void getSlideShow(Banner banner) {
            fenlei_two.setVisibility(View.VISIBLE);
            banners=new ArrayList<>();
            int size = banner.getBody().getContent().getList().size();
            //轮播图
            LurC = new ArrayList<>();
            for(int i=0;i<size;i++){
                LurC.add(banner.getBody().getContent().getList().get(i).getBannerImg());
                banners.add(banner.getBody().getContent().getList().get(i));
            }
            lunBo(fenlei_two,LurC,banners);
        }
    }
    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount();
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
     * 开始启动轮播
     */
    public void startLur(){
        if(fenlei_two!=null){
            fenlei_two.startTurning(ConfigFlag.TIME_DI);
        }

    }
    /**
     * 停止轮播
     */
    public void stopLur(){
        if(fenlei_two!=null){
            fenlei_two.stopTurning();   //停止轮播
        }
    }

    /**
     * 轮播点击事件
     * @param mcb
     * @param L
     * @param list
     */
    public void lunBo(ConvenientBanner mcb, List<String> L, final List<Banner.BodyBean.ContentBean.ListBean>  list){
        if(L.size()>1){
            //轮播图配置
            mcb.setPages(new CBViewHolderCreator<ImageViewHolder>() {
                @Override
                public ImageViewHolder createHolder() {
                    return new ImageViewHolder();
                }
            },L).setPageIndicator(new int[]{R.mipmap.turn_page_unselected_icon,R.mipmap.turn_page_selected_icon}) //设置两个点作为指示器
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
                SingleOverAll.getInstance().bannerClick(context,list.get(position));
            }
        });
    }

}
