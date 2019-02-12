package com.iaowoo.mobile.Ui.classification.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageHomeTag;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageHomeTag;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.Configtion;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.ImageViewHolder;
import com.iaowoo.mobile.Ui.classification.View.TextviewRount.TextViewSwitcher;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.PageGridView;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页dapter
 * Created by chenda on 2018/4/3.
 */

public class TopRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    //item的高度
    private List<Integer> mHeights;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mHeaderCount=1;//头部View个数
    private ConvenientBanner mcb=null;
    //    private GallerViewPager mcb2;
    private ConvenientBanner mcb2;
    private GlideUtils glideUtils;
    private HomeFragmentPresenter homeFragmentProsenter;
    private Banner bannerTop;
    private Banner.BodyBean.ContentBean.ListBean  zq1_banner;
    /**
     * 轮播数据
     */
    private List<String> LurC = null;

    private  List<Banner.BodyBean.ContentBean.ListBean> banners;

    private  List<Banner.BodyBean.ContentBean.ListBean> banners2;


    /**
     * 轮播2区数据
     */
    private List<String> LurC2 = null;

    private List<Shop.BodyBean.ContentBean.ListBean> shops;

    public TopRecycleAdapter(Context context, HomeFragmentPresenter homeFragmentProsenter){
        this.mContext = context;
        this.homeFragmentProsenter=homeFragmentProsenter;

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
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.home_head_xml, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.itme_home_xml_h, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {

            ((HeaderViewHolder) holder).imge_z_q_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SingleOverAll.getInstance().bannerClick(mContext,zq1_banner);
                }
            });

        } else if (holder instanceof ContentViewHolder) {

            if(position%2==0){
                if(position!=1||position!=2){
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,5,5,8,0);
                }else{
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,5,0,8,0);
                }
            }else{
                if(position!=1||position!=2){
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,8,5,5,0);
                }else{
                    UtilsAll.setMargins( ((ContentViewHolder) holder).home_item,8,0,5,0);
                }
            }
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

            //设置积分
            if(PrefManager.getInstance().getIntegralRatio()!=0){
                if(shops.get(position-1).getSubTemplateInfoList().get(0)!=null) {
                    Float pv = (float) shops.get(position - 1).getSubTemplateInfoList().get(0).getPv();
                    Float integral = PrefManager.getInstance().getIntegralRatio();
                    ((ContentViewHolder) holder).integral_text.setText(UtilsAll.DoubleTo_2(pv / integral) + "");
                }
            }

            ((ContentViewHolder) holder).seld.setText("销量" + shops.get(position-1).getSales() + "件");
            if (shops.get(position-1).getProductInfo().getHomeImage() != null) {
                glideUtils.glides(mContext, shops.get(position-1).getProductInfo().getMainImage(), ((ContentViewHolder) holder).image_home);
            }

            ((ContentViewHolder) holder). home_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(shops.get(position-1).getType()==1){
                        UtilsAll.GoWebviewAll(mContext,ConfigH5Url.setGoodsDetails(shops.get(position-1).getType(),shops.get(position-1).getTemplateId()));
                    }else if(shops.get(position-1).getType()==2){
                        UtilsAll.GoNativeGoodsDetails(mContext,shops.get(position-1).getTemplateId()+"","","");
                    }else{
                        UtilsAll.GoNativeGoodsDetails(mContext,shops.get(position-1).getTemplateId()+"","","");
                    }
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
        private TextView seld,integral_text;
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
            integral_text=itemView.findViewById(R.id.integral_text);

            ViewGroup.LayoutParams para;
            para =  image_home.getLayoutParams();
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            int width = dm.widthPixels;// 屏幕宽度（像素）

            para.height = (width-22)/2;
            para.width =(width-22)/2;
            image_home.setLayoutParams(para);

        }
    }
    //头部 ViewHolder
    public  class HeaderViewHolder extends RecyclerView.ViewHolder implements HomeFragmentPresenter.HomeAdapterCallBack {
        private TextView text_distance;
        private RecyclerView hor,pin_recycle,all_recycle,chinaGoodshops;
        private PageGridView cusom_swipe_view;
        private RelativeLayout see_more;
        private ImageView imge_z_q_1;
        private View view_z_q_1;
        private TextViewSwitcher msg_Text;
        LinearLayout every_day_s_h,chi_huo_jie,liang_dian,gu_wan_cheng,wang_hong_jie,backgroud_two;
        RelativeLayout msg_re,five_click;
        ImageView china_goods,quan_qiu,back_imge;
        FrameLayout earth_all_frame,china_goods_frame;
        RelativeLayout line_magin_left,line_magin_right;
        ImageView show_or_hidden;

        /**
         * @param itemView
         */
        public HeaderViewHolder(View itemView) {
            super(itemView);
            homeFragmentProsenter.setAdapterCallBack(this);
            mcb=itemView.findViewById(R.id.mcb);
            mcb2=itemView.findViewById(R.id.mcb2);
            text_distance=itemView.findViewById(R.id.text_distance);
            hor=itemView.findViewById(R.id.hor);
            //专区一
            imge_z_q_1=itemView.findViewById(R.id.imge_z_q_1);
            msg_Text=itemView.findViewById(R.id.msg_Text);
            every_day_s_h=itemView.findViewById(R.id.every_day_s_h);
            china_goods_frame=itemView.findViewById(R.id.china_goods_frame);
            view_z_q_1=itemView.findViewById(R.id.view_z_q_1);
            see_more=itemView.findViewById(R.id.see_more);
            pin_recycle=itemView.findViewById(R.id.pin_recycle);
            all_recycle=itemView.findViewById(R.id.all_recycle);
            chinaGoodshops=itemView.findViewById(R.id.chinaGoodshops);
            cusom_swipe_view=itemView.findViewById(R.id.cusom_swipe_view);
            msg_re=itemView.findViewById(R.id.msg_re);
            china_goods=itemView.findViewById(R.id.china_goods);
            quan_qiu=itemView.findViewById(R.id.quan_qiu);
            earth_all_frame=itemView.findViewById(R.id.earth_all_frame);
            line_magin_left=itemView.findViewById(R.id.line_magin_left);
            line_magin_right=itemView.findViewById(R.id.line_magin_right);
            chi_huo_jie=itemView.findViewById(R.id.chi_huo_jie);
            liang_dian=itemView.findViewById(R.id.liang_dian);
            gu_wan_cheng=itemView.findViewById(R.id. gu_wan_cheng);
            wang_hong_jie=itemView.findViewById(R.id.wang_hong_jie);
            backgroud_two=itemView.findViewById(R.id.backgroud_two);
            five_click=itemView.findViewById(R.id.five_click);
            back_imge=itemView.findViewById(R.id.back_imge);
            show_or_hidden=itemView.findViewById(R.id.show_or_hidden);
            /******************数据加载******************/
            //获取banner数据
            homeFragmentProsenter.getSlideShowData();
            //首页分类
            homeFragmentProsenter.getConfiguration();
            //每日最火
            homeFragmentProsenter.getDayFire();
            //拼购推荐
            homeFragmentProsenter.getPGTJ();
            //中国好物
            homeFragmentProsenter.getGoodsChina(1,20);
            //全球集市
            homeFragmentProsenter.getQQJS();
            /****************************************/


            hor.setHasFixedSize(true);//设置固定大小
            hor.setItemAnimator(new DefaultItemAnimator());//设置默认动画
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            //为recyclerView设置布局管理器
            hor.setLayoutManager(mLayoutManager);
            pin_recycle.setHasFixedSize(true);//设置固定大小
            pin_recycle.setItemAnimator(new DefaultItemAnimator());//设置默认动画
            LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            //为recyclerView设置布局管理器
            pin_recycle.setLayoutManager(mLayoutManager1);
            //拼购推荐点击事件
            see_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilsAll.GoWebviewAll(mContext,ConfigH5Url.getUrl(ConfigH5Url.collage));
                }
            });
            chinaGoodshops.setHasFixedSize(true);//设置固定大小
            chinaGoodshops.setItemAnimator(new DefaultItemAnimator());//设置默认动画
            LinearLayoutManager mLayoutManager3 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            //为recyclerView设置布局管理器
            chinaGoodshops.setLayoutManager(mLayoutManager3);

            all_recycle.setHasFixedSize(true);//设置固定大小
            all_recycle.setItemAnimator(new DefaultItemAnimator());//设置默认动画
            LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            //为recyclerView设置布局管理器
            all_recycle.setLayoutManager(mLayoutManager2);
        }

        /**
         *每日最火
         * @param shops
         */
        @Override
        public void DayFire(final List<Shop.BodyBean.ContentBean.ListBean> shops) {
            every_day_s_h.setVisibility(View.VISIBLE);
            EverdayRecycleAdapter everdayRecycleAdapter=new EverdayRecycleAdapter(shops,mContext);
            hor.setAdapter(everdayRecycleAdapter);

            everdayRecycleAdapter.setOnRecyclerViewListener(new EverdayRecycleAdapter.OnRecyclerViewListener() {
                @Override
                public void onItemClick(int position) {
                    if(shops.get(position).getType()==1) {
                        UtilsAll.GoWebviewAll(mContext,ConfigH5Url.setGoodsDetails(shops.get(position).getType(),shops.get(position).getTemplateId()));
                    }else{
                        UtilsAll.GoNativeGoodsDetails(mContext, shops.get(position).getTemplateId() + "", "", "");
                    }
                }
                @Override
                public boolean onItemLongClick(int position) {
                    return false;
                }
            });
        }

        /**
         * 中国好物
         * @param shops
         */
        @Override
        public void GoodsChina(final List<Shop.BodyBean.ContentBean.ListBean> shops) {
            china_goods_frame.setVisibility(View.GONE);
            ChinaGoodShopsAndQQRecycleAdapter chinaGoodShopsAndQQRecycleAdapter =new ChinaGoodShopsAndQQRecycleAdapter(mContext, shops, new ItemClickInterface() {
                @Override
                public void onItemClick(Object Item, int position) {
                    UtilsAll.GoNativeGoodsDetails(mContext,shops.get(position).getTemplateId()+"","","");
//                    UtilsAll.GoWebviewAll(mContext,ConfigH5Url.setGoodsDetails(shops.get(position).getType(),shops.get(position).getTemplateId()));
                }
            });
            chinaGoodshops.setAdapter(chinaGoodShopsAndQQRecycleAdapter);
            View view=LayoutInflater.from(mContext).inflate(R.layout.more_datas,null);
            LinearLayout click_more=view.findViewById(R.id.click_more);
            click_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogPrint.printError("好物更多");
                    EventBus.getDefault().post(new EventBusMessageHomeTag("1"));
                }
            });
            china_goods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new EventBusMessageHomeTag("1"));

                }
            });


            chinaGoodShopsAndQQRecycleAdapter.addFooterView(view);
        }

        /**
         * 拼购
         * @param shops
         */
        @Override
        public void SpellRemmand(final List<Shop.BodyBean.ContentBean.ListBean> shops) {
            see_more.setVisibility(View.VISIBLE);
            PGRecycleAdapter pgRecycleAdapter=new PGRecycleAdapter(shops,mContext);
            pin_recycle.setAdapter(pgRecycleAdapter);
            pgRecycleAdapter.setOnRecyclerViewListener(new PGRecycleAdapter.OnRecyclerViewListener() {
                @Override
                public void onItemClick(int position) {
//                    UtilsAll.GoNativeGoodsDetails(mContext,shops.get(position).getTemplateId()+"","");
                    UtilsAll.GoWebviewAll(mContext,ConfigH5Url.setGoodsDetails(shops.get(position).getType(),shops.get(position).getTemplateId()));
                }
                @Override
                public boolean onItemLongClick(int position) {
                    return false;
                }
            });
        }

        /**
         * 全球集市
         * @param shops
         */
        @Override
        public void EearthAll(final List<Shop.BodyBean.ContentBean.ListBean> shops) {
            earth_all_frame.setVisibility(View.GONE);
            ChinaGoodShopsAndQQRecycleAdapter chinaGoodShopsAndQQRecycleAdapter =new ChinaGoodShopsAndQQRecycleAdapter(mContext, shops, new ItemClickInterface() {
                @Override
                public void onItemClick(Object Item, int position) {
                    UtilsAll.GoNativeGoodsDetails(mContext,shops.get(position).getTemplateId()+"","","");
//                    UtilsAll.GoWebviewAll(mContext,ConfigH5Url.setGoodsDetails(shops.get(position).getType(),shops.get(position).getTemplateId()));
                }
            });
            all_recycle.setAdapter(chinaGoodShopsAndQQRecycleAdapter);
            View view=LayoutInflater.from(mContext).inflate(R.layout.more_datas,null);
            LinearLayout click_more=view.findViewById(R.id.click_more);
            click_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogPrint.printError("全球更多");
                    EventBus.getDefault().post(new EventBusMessageHomeTag("2"));

                }
            });
            quan_qiu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new EventBusMessageHomeTag("2"));

                }
            });
            chinaGoodShopsAndQQRecycleAdapter.addFooterView(view);

        }

        /**
         *轮播图
         * @param banner
         */
        @Override
        public void getSlideShow(final Banner banner) {
            banners=new ArrayList<>();
            banners2=new ArrayList<>();
            String[] all=new String[6];
            int size = banner.getBody().getContent().getList().size();
            if (size != 0) {
                //轮播图
                LurC = new ArrayList<>();
                LurC2 = new ArrayList<>();
                //专区1
                String ZQ1 = "";
                //添加轮播数据
                for (int i = 0; i < size; i++) {
                    if(!TextUtils.isEmpty(banner.getBody().getContent().getList().get(i).getBannerImg())) {
                        if (banner.getBody().getContent().getList().get(i).getLocation() == 1) {
                            banners.add(banner.getBody().getContent().getList().get(i));
                            LurC.add(banner.getBody().getContent().getList().get(i).getBannerImg());
                        } else if (banner.getBody().getContent().getList().get(i).getLocation() == 19) {
                            LurC2.add(banner.getBody().getContent().getList().get(i).getBannerImg());
                            banners2.add(banner.getBody().getContent().getList().get(i));
                        }else if(banner.getBody().getContent().getList().get(i).getLocation() == 38){
                            ZQ1=banner.getBody().getContent().getList().get(i).getBannerImg();
                            zq1_banner=banner.getBody().getContent().getList().get(i);
                        }else if(banner.getBody().getContent().getList().get(i).getLocation()==7){
                            LogPrint.printError("跳转的" +banner.getBody().getContent().getList().get(i).getJumpUrl());
                            backgroud_two.setVisibility(View.VISIBLE);
                            back_imge.setVisibility(View.VISIBLE);
                            glideUtils.glides(mContext,banner.getBody().getContent().getList().get(i).getBannerImg(),back_imge);
                            all=banner.getBody().getContent().getList().get(i).getJumpUrl().split("¥");
                        }
                    }
                }
                if (!TextUtils.isEmpty(ZQ1)) {
                    imge_z_q_1.setVisibility(View.VISIBLE);
                    glideUtils.glides(mContext, ZQ1, imge_z_q_1);
                    view_z_q_1.setVisibility(View.VISIBLE);
                }
                imge_z_q_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SingleOverAll.getInstance().bannerClick(mContext,zq1_banner);
                    }
                });
                show_or_hidden.setVisibility(View.VISIBLE);
                lunBo(mcb,LurC,banners);
                lunBo(mcb2,LurC2,banners2);
                final String[] finalAll = all;
                chi_huo_jie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogPrint.printError("吃货街");
                        UtilsAll.GoWeexAll(mContext,finalAll[0],"","");
                    }
                });
                liang_dian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogPrint.printError("粮店");
                        UtilsAll.GoWeexAll(mContext,finalAll[1],"","");
                    }
                });
                gu_wan_cheng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogPrint.printError("古玩城");
                        UtilsAll.GoWeexAll(mContext,finalAll[2],"","");
                    }
                });
                wang_hong_jie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogPrint.printError("网红街");
                        UtilsAll.GoWeexAll(mContext,finalAll[3],"","");
                    }
                });
                five_click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(finalAll.length==5){
                            UtilsAll.GoWeexAll(mContext,finalAll[4],"","");
                        }
                    }
                });

//                WebBannerAdapter webBannerAdapter=new WebBannerAdapter(mContext,LurC2,0);
//                webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
//                    @Override
//                    public void onItemClick(int position) {
//                        SingleOverAll.getInstance().bannerClick(mContext,banners2.get(position));
//                    }
//                });
//                mcb2.setAdapter(webBannerAdapter);
//
//                mcb2.isPlaying();
//                GallerAdapter gallerAdapter=new GallerAdapter(mContext,banners2);
//                mcb2.setPageTransformer(true, new ScaleGallerTransformer());
//                mcb2.setDuration(3000);
//                mcb2.startAutoCycle();
//                mcb2.setSliderTransformDuration(1500, null);
//                mcb2.setAdapter(gallerAdapter);

            }

        }

        int x_margin=0;
        /**
         * 首页分类
         * @param configtion
         */
        @Override
        public void getConfigtion(Configtion configtion) {
            if (configtion.getBody().getContent().getList() != null) {
                int size = configtion.getBody().getContent().getList().size();
                final List<Configtion.BodyBean.ContentBean.ListBean> list = new ArrayList<>();
                final List<Configtion.BodyBean.ContentBean.ListBean> list1 = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    if (configtion.getBody().getContent().getList().get(i).getLocation() == 1) {
                        if(configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==1||configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==2||configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==3) {
                            list.add(configtion.getBody().getContent().getList().get(i));
                        }
                    } else if (configtion.getBody().getContent().getList().get(i).getLocation() == 2) {
                        list1.add(configtion.getBody().getContent().getList().get(i));
                    }
                }

                if (list1.size() > 0) {
                    msg_re.setVisibility(View.GONE);
                    msg_Text.setAdapter(new RollingTextAdapter() {
                        @Override
                        public int getCount() {
                            return list1.size();
                        }

                        @SuppressLint("ResourceAsColor")
                        @Override
                        public View getView(Context context, View contentView, int position) {
                            View view = View.inflate(context, R.layout.item_layout, null);
                            ((TextView) view.findViewById(R.id.tv_1)).setText(list1.get(position).getTitle());
                            return view;
                        }
                    });
                    msg_Text.startFlipping();
                }


                ClassInfoRecycleAdapter classInfoRecycleAdapter = new ClassInfoRecycleAdapter(true, list, mContext, new OnclicKRecycleAdapter() {
                    @Override
                    public void onItemClick(int position) {
                    }
                    @Override
                    public boolean onItemLongClick(int position) {
                        return false;
                    }
                });
                cusom_swipe_view.setAdapter(classInfoRecycleAdapter);

                //不满5个就不显示导航线
                if(list.size()<=5){
                    line_magin_left.setVisibility(View.GONE);
                    line_magin_right.setVisibility(View.GONE);
                }else{
                    cusom_swipe_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        /**
                         * Callback method to be invoked when the RecyclerView has been scrolled. This will be
                         * called after the scroll has completed.
                         * This callback will also be called if visible item range changes after a layout
                         * calculation. In that case, dx and dy will be 0.
                         * @param recyclerView The RecyclerView which scrolled.
                         * @param dx           The amount of horizontal scroll.
                         * @param dy           The amount of vertical scroll.
                         */
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);
                            x_margin+=dx;
                            LogPrint.printError("横向距离："+x_margin);
                            if(x_margin>80){
                                line_magin_left.setVisibility(View.GONE);
                                line_magin_right.setVisibility(View.VISIBLE);
                            }else{
                                line_magin_left.setVisibility(View.VISIBLE);
                                line_magin_right.setVisibility(View.GONE);
                            }
                            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();//获取LayoutManager
                            //经过测试LinearLayoutManager和GridLayoutManager有以下的方法,这里只针对LinearLayoutManager
                            if (manager instanceof LinearLayoutManager) {
                                //经测试第一个完整的可见的item位置，若为0则是最上方那个;在item超过屏幕高度的时候只有第一个item出现的时候为0 ，其他时候会是一个负的值
                                //此方法常用作判断是否能下拉刷新，来解决滑动冲突
                                int findFirstCompletelyVisibleItemPosition = ((LinearLayoutManager) manager).findFirstCompletelyVisibleItemPosition();
                                //最后一个完整的可见的item位置
                                int findLastCompletelyVisibleItemPosition = ((LinearLayoutManager) manager).findLastCompletelyVisibleItemPosition();
                                //第一个可见的位置
                                int findFirstVisibleItemPosition = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
                                //最后一个可见的位置
                                int findLastVisibleItemPosition = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
                                //如果有滑动冲突--可以用以下方法解决(如果可见位置是position==0的话才能有下拉刷新否则禁掉)
//                            mSwipeRefreshLayout.setEnabled(findFirstCompletelyVisibleItemPosition == 0);
                                //在网上还看到一种解决滑动冲突的方法
                                int topPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                            }
                        }
                    });

                }}
        }
    }


    /**
     * 开始启动轮播
     */
    public void startLur(){
        if(mcb!=null){
            mcb.startTurning(ConfigFlag.TIME_DI);
        }
        if(mcb2!=null){
        }
    }
    /**
     * 停止轮播
     */
    public void stopLur(){
        if(mcb!=null){
            mcb.stopTurning();   //停止轮播
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
