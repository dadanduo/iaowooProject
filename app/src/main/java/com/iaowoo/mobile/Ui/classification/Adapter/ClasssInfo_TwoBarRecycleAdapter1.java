package com.iaowoo.mobile.Ui.classification.Adapter;


import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.FenLei;
import com.iaowoo.mobile.Ui.classification.Model.ReMenResponse;
import com.iaowoo.mobile.Ui.classification.Presenter.ClassInfoPresenter;
import com.iaowoo.mobile.Ui.classification.Presenter.EearthFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.ImageViewHolder;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigFlag;

import java.util.ArrayList;
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
 * @Description: ${todo}(二级分类)
 * @date 2018/9/10
 * @email ${18011009889@163.com}
 */
public class ClasssInfo_TwoBarRecycleAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    private  ClassInfoPresenter classInfoProsenter;

    private List<ReMenResponse.BodyBean.ContentBean.ListBean> mData;

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

    public ClasssInfo_TwoBarRecycleAdapter1(Context mContext, List<ReMenResponse.BodyBean.ContentBean.ListBean> mData, ClassInfoPresenter classInfoProsenter, String categroy) {
        this.context = mContext;
        mLayoutInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.classInfoProsenter=classInfoProsenter;
        this.categroy=categroy;
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }
    //内容长度
    public int getContentItemCount(){
        if(mData!=null){
            return mData.size();
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
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.fenlei_head, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.classinfo_right_two_adapter, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
        } else if (holder instanceof ContentViewHolder) {
            if (mData != null) {
                ((ContentViewHolder)holder).classinfo_two_Name.setText(mData.get(position-1).getTemplateActivityDescribe()+"");
                GlideUtils glideUtils=new GlideUtils();
                if(!TextUtils.isEmpty(mData.get(position-1).getActivityImageUrl())) {
                    glideUtils.glides(context, mData.get(position-1).getActivityImageUrl(), ((ContentViewHolder)holder).two_class_imge);
                }
                ((ContentViewHolder)holder).two_class_imge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UtilsAll.GoNativeGoodsDetails(context,mData.get(position-1).getTemplateId(),mData.get(position-1).getActivityId(),PrefManager.getInstance().getInvite());
                    }
                });
            }
        }
    }


    //内容 ViewHolder
    public  class ContentViewHolder extends RecyclerView.ViewHolder {
        public ImageView two_class_imge;
        public TextView classinfo_two_Name;

        public ContentViewHolder(View itemView) {
            super(itemView);
            two_class_imge=itemView.findViewById(R.id.two_class_imge);
            classinfo_two_Name=itemView.findViewById(R.id.classinfo_two_Name);
        }
    }
    //头部 ViewHolder
    public  class HeaderViewHolder extends RecyclerView.ViewHolder implements ClassInfoPresenter.ClassInfoCallBack {
        public HeaderViewHolder(View itemView) {
            super(itemView);
            fenlei_two=itemView.findViewById(R.id.fenlei_two);
            if(classInfoProsenter!=null) {
                classInfoProsenter.getSlideShowData1();
                classInfoProsenter.setCallBack(this);
            }
        }

        @Override
        public void Fenlei(FenLei fenLei) {

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

        @Override
        public void nohaveData() {
            fenlei_two.setVisibility(View.GONE);
        }

        @Override
        public void ReMenData(ReMenResponse.BodyBean.ContentBean contentBean) {

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

