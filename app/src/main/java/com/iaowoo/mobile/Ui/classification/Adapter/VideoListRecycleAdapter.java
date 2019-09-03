package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.MockData;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.VideoEntity;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.CustomRoundAngleImageView;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.MockData;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 小视频列表dapter
 * Created by chenda on 2018/4/3.
 */

public class VideoListRecycleAdapter extends BaseSoEasyAdapter  {
    //item类型
    public   int ITEM_TYPE_HEADER = 0;
    public   int ITEM_TYPE_CONTENT = 1;
    //item的高度
    private List<Integer> mHeights;
    private LayoutInflater mLayoutInflater;
    private List<VideoEntity.BodyBean.ContentBean.ListBean> videoList;
    private Context mContext;
    private int mHeaderCount=1;//头部View个数
    private SoftReference<GlideUtils> glideUtilsSoftReference;
    private MockData mockData;

    public VideoListRecycleAdapter(Context context,MockData mockData){
        this.mContext = context;
        this.mockData=mockData;
        mLayoutInflater = LayoutInflater.from(context);
        glideUtilsSoftReference=new SoftReference<>(new GlideUtils());
    }
    /**
     * @param videoList
     */
    public void setShops(List<VideoEntity.BodyBean.ContentBean.ListBean> videoList){
        this.videoList=videoList;
    }
    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }
    //内容长度
    public int getContentItemCount(){
        if(videoList!=null){
            return videoList.size();
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
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.video_head_xml, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.video_list_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof ContentViewHolder) {
//            loadAnimation(((ContentViewHolder) holder).svga_Image,((ContentViewHolder) holder).top_image);
            if(videoList.get(position-1).getVideo()!=null) {
                if (!TextUtils.isEmpty(videoList.get(position-1).getVideo().getOriginCover())) {
                    //设置封面
                    if(glideUtilsSoftReference.get()!=null){
                        glideUtilsSoftReference.get().glides(mContext, videoList.get(position - 1).getVideo().getOriginCover(), ((ContentViewHolder) holder).top_image);

                    }else{
                        glideUtilsSoftReference=new SoftReference<>(new GlideUtils());
                        glideUtilsSoftReference.get().glides(mContext, videoList.get(position - 1).getVideo().getOriginCover(), ((ContentViewHolder) holder).top_image);
                    }
                }
            }
            if(videoList.get(position-1).getAuthor()!=null) {
                //设置作者名字
                if (!TextUtils.isEmpty(videoList.get(position - 1).getAuthor().getNickname() + "")) {
                    ((ContentViewHolder) holder).id_name.setText(videoList.get(position - 1).getAuthor().getNickname());
                }
            }
            if(videoList.get(position-1).getVideo()!=null) {
                //设置标题
                if (!TextUtils.isEmpty(videoList.get(position - 1).getVideo().getTitle() + "")) {
                    ((ContentViewHolder) holder).content.setText(videoList.get(position - 1).getVideo().getTitle() + "");
                }
            }
            if(videoList.get(position-1).getStatistics()!=null) {
                //设置点赞数
                if (!TextUtils.isEmpty(videoList.get(position - 1).getStatistics().getPraiseNum() + "")) {
                    if(videoList.get(position - 1).getStatistics().getPraiseNum()>10000){
                        ((ContentViewHolder) holder).give_number.setText(UtilsAll.division(videoList.get(position - 1).getStatistics().getPraiseNum(),10000)+"w");
                    }else{
                        ((ContentViewHolder) holder).give_number.setText(videoList.get(position - 1).getStatistics().getPraiseNum() + "");
                    }
                }
            }
            //设置头像
            if(videoList.get(position-1).getAuthor()!=null) {
                if (TextUtils.isEmpty(videoList.get(position - 1).getAuthor().getHeadImgUrl())) {
                    ((ContentViewHolder) holder).head_image_item.setImageResource(R.mipmap.mine_default_avatar);
                } else {
                    //设置封面
                    if(glideUtilsSoftReference.get()!=null){
                        glideUtilsSoftReference.get().glides(mContext, videoList.get(position - 1).getAuthor().getHeadImgUrl(), ((ContentViewHolder) holder).head_image_item);
                    }else{
                        glideUtilsSoftReference=new SoftReference<>(new GlideUtils());
                        glideUtilsSoftReference.get().glides(mContext, videoList.get(position - 1).getAuthor().getHeadImgUrl(), ((ContentViewHolder) holder).head_image_item);
                    }
                }
            }
            //item点击事件
            ((ContentViewHolder) holder).item_list_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclicKRecycleAdapter.onItemClick(position);
                }
            });

        }
    }
    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount();
    }

    /**
     *
     */
    //内容 ViewHolder
    public  class ContentViewHolder extends RecyclerView.ViewHolder {
        public CustomRoundAngleImageView top_image;
        /**
         * svag封面图
         */
        public SVGAImageView svga_Image;
        /**
         * 作者头像
         */
        public CircleImageView head_image_item;
        /**
         * 视频列表描述，作者名字，点赞数量
         */
        public TextView content,id_name,give_number;
        public FrameLayout item_list_click;
        public ContentViewHolder(View itemView) {
            super(itemView);
            svga_Image=itemView.findViewById(R.id.svga_Image);
            head_image_item=itemView.findViewById(R.id.head_image_item);
            content=itemView.findViewById(R.id.content);
            id_name=itemView.findViewById(R.id.id_name);
            give_number=itemView.findViewById(R.id.give_number);
            top_image=itemView.findViewById(R.id.top_image);
            item_list_click=itemView.findViewById(R.id.item_list_click);
        }
    }

    /**
     * 头部 ViewHolder
     */
    public  class HeaderViewHolder extends RecyclerView.ViewHolder {
        public ImageView video_top_img;
        Banner.BodyBean.ContentBean.ListBean beans;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            video_top_img=itemView.findViewById(R.id.video_top_img);

            mockData.getStartPage(new HomeFragmentPresenter.HomeMainCallBack() {
                @Override
                public void getSlideShowHome(Banner.BodyBean.ContentBean.ListBean bean) {

                }
                @Override
                public void getStartAd(Banner.BodyBean.ContentBean.ListBean bean) {
                    beans=bean;
                    if(!TextUtils.isEmpty(bean.getBannerImg())){
                        //设置头部图片
                        if(glideUtilsSoftReference.get()!=null){
                            if(bean!=null) {
                                if(!TextUtils.isEmpty(bean.getBannerImg())) {
                                    glideUtilsSoftReference.get().glides(mContext, bean.getBannerImg(), video_top_img);
                                }
                            }
                        }else{
                            glideUtilsSoftReference=new SoftReference<>(new GlideUtils());
                            if(bean!=null) {
                                if(!TextUtils.isEmpty(bean.getBannerImg())) {
                                    glideUtilsSoftReference.get().glides(mContext, bean.getBannerImg(), video_top_img);
                                }
                            }
                        }
                    }
                }
                @Override
                public void ShopCarNumber(int number) {

                }
                @Override
                public void noAd() {

                }
            });
            video_top_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(beans!=null) {
                        SingleOverAll.getInstance().bannerClick(mContext, beans);
                    }

                }
            });
        }
    }


    /**
     * recycle头部加gride 2
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == ITEM_TYPE_HEADER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /*************************************************svag********************************/
    /**
     * @param svgaImageView
     */
    private void loadAnimation(final SVGAImageView svgaImageView, final ImageView hideImageView) {
        SVGAParser parser = new SVGAParser(mContext);
        resetDownloader(parser);
        try {
            parser.parse(new URL("https://github.com/yyued/SVGA-Samples/blob/master/kingset.svga?raw=true"), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    SVGADrawable drawable = new SVGADrawable(videoItem, requestDynamicItemWithSpannableText());
                    svgaImageView.setVisibility(View.VISIBLE);
                    hideImageView.setVisibility(View.GONE);
                    svgaImageView.setImageDrawable(drawable);
                    svgaImageView.startAnimation();
                    LogPrint.printError("加载成功");
                }
                @Override
                public void onError() {
                }
            });
        } catch (Exception e) {
            LogPrint.printError("❌"+e.getMessage().toString());
        }
    }

    /**
     * 进行简单的文本替换
     * @return
     */
    private SVGADynamicEntity requestDynamicItem() {
        SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(28);
        dynamicEntity.setDynamicText("Pony 送了一打风油精给主播", textPaint, "banner");
        return dynamicEntity;
    }

    /**
     * 设置下载器，这是一个可选的配置项。
     * @param parser
     */
    private void resetDownloader(SVGAParser parser) {
        parser.setFileDownloader(new SVGAParser.FileDownloader() {
            @Override
            public void resume(final URL url, final Function1<? super InputStream, Unit> complete, final Function1<? super Exception, Unit> failure) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder().url(url).get().build();
                        try {
                            Response response = client.newCall(request).execute();
                            complete.invoke(response.body().byteStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                            failure.invoke(e);
                        }
                    }
                }).start();
            }
        });
    }
    /**
     * 你可以设置富文本到 ImageKey 相关的元素上
     * 富文本是会自动换行的，不要设置过长的文本
     * @return
     */
    private SVGADynamicEntity requestDynamicItemWithSpannableText() {
        SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("Pony 送了一打风油精给主播");
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.YELLOW), 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(28);
        dynamicEntity.setDynamicText(new StaticLayout(
                spannableStringBuilder,
                0,
                spannableStringBuilder.length(),
                textPaint,
                0,
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0.0f,
                false
        ), "banner");
        dynamicEntity.setDynamicDrawer(new Function2<Canvas, Integer, Boolean>() {
            @Override
            public Boolean invoke(Canvas canvas, Integer frameIndex) {
                Paint aPaint = new Paint();
                aPaint.setColor(Color.WHITE);
                canvas.drawCircle(50, 54, frameIndex % 5, aPaint);
                return false;
            }
        }, "banner");
        return dynamicEntity;
    }
    /*************************************************svag********************************/

}
