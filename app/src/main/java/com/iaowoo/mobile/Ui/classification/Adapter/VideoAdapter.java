package com.iaowoo.mobile.Ui.classification.Adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.LoginActivity;
import com.iaowoo.mobile.Ui.classification.Activity.UseguideActivity;
import com.iaowoo.mobile.Ui.classification.Model.CommentsOk;
import com.iaowoo.mobile.Ui.classification.Model.VideoEntity;
import com.iaowoo.mobile.Ui.classification.View.DouYinMusic.MusicalNoteLayout;
import com.iaowoo.mobile.Ui.classification.View.Love;
import com.iaowoo.mobile.Ui.classification.View.listvideo.ListVideoView;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.MockData;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.superluo.textbannerlibrary.TextBannerView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author ArcherYc
 * @date on 2018/9/12  下午3:19
 * @mail 247067345@qq.com
 */
public class VideoAdapter extends WRecyclerView.Adapter {

    private List<VideoEntity.BodyBean.ContentBean.ListBean> dataList;

    private Context mContext;

    private WRecyclerView recyclerView;

    private Dialog commenting,inputComment;

    private  int timeout=400;//双击间四百毫秒延时

    private int clickCount = 0;//记录连续点击次数

    private Handler handler;

    /**
     * 双击单击事件
     */
    private VieoCallBack vieoCallBack;

    /**
     * dilog
     */
    private SoftReference<DialogUtils> dialogUtilsSoftReference;

    /**
     * 记录小视频的点赞数量
     */
    public List<Integer> integers;

    /**
     * 记录小视频的评论数量
     */
    public List<Integer> commentsIntegar;

    /**
     * 点赞状态
     */
    public List<Integer> DokiStatus;

    private SoftReference<GlideUtils> glideUtilsSoftReference;

    private MockData mockData;

    public interface VieoCallBack{
        void oneClick();//点击一次的回调
        void doubleClick();//连续点击两次的回调
    }

    public VideoAdapter(Context context, WRecyclerView recyclerView,MockData mockData, VieoCallBack vieoCallBack) {
        this.mContext = context;
        this.recyclerView = recyclerView;
        this.mockData=mockData;
        this.vieoCallBack=vieoCallBack;
        integers=new ArrayList<>();
        commentsIntegar=new ArrayList<>();
        DokiStatus=new ArrayList<>();
        handler = new Handler();
        glideUtilsSoftReference=new SoftReference<>(new GlideUtils());

    }

    @NonNull
    @Override
    public WRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_video, parent, false);
        return new VideoViewHolder(view);
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull WRecyclerView.ViewHolder holder, final int position) {
        final VideoEntity.BodyBean.ContentBean.ListBean listBeans= dataList.get(position);
        final VideoViewHolder viewHolder = (VideoViewHolder) holder;



        if(listBeans.getVideo()!=null) {
            //设置点赞数量
            integers.add(position, listBeans.getVideo().getPraiseNum());
            //设置评论数量
            commentsIntegar.add(position,listBeans.getStatistics().getCommentNum());
            //设置封面
            if (!TextUtils.isEmpty(listBeans.getVideo().getOriginCover())) {
                viewHolder.sdvCover.setImageURI(listBeans.getVideo().getOriginCover() + "");
            }
            //设置描述
            if (!TextUtils.isEmpty(listBeans.getVideo().getDescribes() + "")) {
                viewHolder.from_describe.setText(listBeans.getVideo().getDescribes() + "");
            }
            //商品
            if(listBeans.getVideo().getActivityType()==2){
                viewHolder.search_items.setVisibility(View.GONE);
                viewHolder.buy_go_on.setVisibility(View.VISIBLE);
            }
            //活动
            else if(listBeans.getVideo().getActivityType()==3){
                viewHolder.search_items.setVisibility(View.VISIBLE);
                viewHolder.buy_go_on.setVisibility(View.GONE);
            }
            //商品和活动
            else if(listBeans.getVideo().getActivityType()==4){
                viewHolder.search_items.setVisibility(View.VISIBLE);
                viewHolder.buy_go_on.setVisibility(View.GONE);
            }
        }
        if(listBeans.getAuthor()!=null){
            //设置名称
            if (!TextUtils.isEmpty(listBeans.getAuthor().getNickname() + "")) {
                viewHolder.from_name.setText("@"+listBeans.getAuthor().getNickname());
            }
            //设置头像
            if (TextUtils.isEmpty(listBeans.getAuthor().getHeadImgUrl())) {
                viewHolder.headIcon_v.setImageResource(R.mipmap.mine_default_avatar);
            } else {
                if(glideUtilsSoftReference.get()!=null){
                    glideUtilsSoftReference.get().glides(mContext,listBeans.getAuthor().getHeadImgUrl(),viewHolder.headIcon_v);
                }else{
                    glideUtilsSoftReference=new SoftReference<>(new GlideUtils());
                    glideUtilsSoftReference.get().glides(mContext,listBeans.getAuthor().getHeadImgUrl(),viewHolder.headIcon_v);
                }
            }
        }

        //设置音乐头像
        if(listBeans.getMusic()!=null) {
            if (TextUtils.isEmpty(listBeans.getMusic().getCoverLarge())) {
                viewHolder.music_image.setImageResource(R.mipmap.mine_default_avatar);
            } else {
                if(glideUtilsSoftReference.get()!=null){
                    glideUtilsSoftReference.get().glides(mContext,listBeans.getMusic().getCoverLarge(),viewHolder.music_image);
                }else{
                    glideUtilsSoftReference=new SoftReference<>(new GlideUtils());
                    glideUtilsSoftReference.get().glides(mContext,listBeans.getMusic().getCoverLarge(),viewHolder.music_image);
                }
            }
        }

        if(DokiStatus.get(position)==1){
            viewHolder.like_dok.setImageResource(R.mipmap.give_love_icon);
        }else{
            viewHolder.like_dok.setImageResource(R.mipmap.no_give_love_icon);
        }
        if(listBeans.getStatistics()!=null){
            //设置点赞数量
            if(!TextUtils.isEmpty(listBeans.getStatistics().getPraiseNum()+"")){
                DokiStatus.add(position,listBeans.getStatistics().getPraiseNum());
                if(listBeans.getStatistics().getPraiseNum()>10000){
                    viewHolder.give_like_text.setText(UtilsAll.division(listBeans.getStatistics().getPraiseNum(),10000)+"w");
                }else{
                    viewHolder.give_like_text.setText(listBeans.getStatistics().getPraiseNum()+"");
                }
            }
            //设置评论数量
            if (!TextUtils.isEmpty(listBeans.getStatistics().getCommentNum() + "")) {
                if(listBeans.getStatistics().getCommentNum()>10000){
                    viewHolder.comments_text.setText(UtilsAll.division(listBeans.getStatistics().getCommentNum(),10000)+"w");
                }else{
                    viewHolder.comments_text.setText(listBeans.getStatistics().getCommentNum() + "");
                }
            }
            //分享数量
//        if(listBeans.getStatistics()!=null){
//            if(TextUtils.isEmpty(listBeans.getStatistics().getCommentNum()+"")){
//                viewHolder.comments_text.setText(listBeans.getStatistics().getCommentNum()+"");
//                //屏蔽
//                viewHolder.comments_text.setVisibility(View.GONE);
//            }
//        }
        }
        //单击事件
        viewHolder.videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (clickCount == 1) {
                            vieoCallBack.oneClick();
                        }else if(clickCount==2){
                            vieoCallBack.doubleClick();
                        }
                        handler.removeCallbacksAndMessages(null);
                        //清空handler延时，并防内存泄漏
                        clickCount = 0;//计数清零
                    }
                },timeout);//延时timeout后执行run方法中的代码Ø
            }
        });
        //点击点赞
        viewHolder.like_dok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UtilsAll.isDoubleClick1()){
                    //没有点赞状态
                    if (DokiStatus.get(position) == 0) {
                        //是否登录
                        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                            //登录状态开始点赞
                            mockData.DokiVieo(listBeans.getVideo().getVideoId(), PrefManager.getInstance().getToken(), 1, new MockData.CallBackCommentsOk() {
                                @Override
                                public void ok() {
                                    LogPrint.printError("点赞成功");
                                    //记录点赞成功标识
                                    DokiStatus.set(position, 1);
                                    //开始心跳
                                    startShakeByView(viewHolder.like_dok);
                                    //设置为点赞状态
                                    viewHolder.like_dok.setImageResource(R.mipmap.give_love_icon);
                                    //改变点赞数量
                                    int sum = integers.get(position);
                                    sum++;
                                    integers.set(position, sum);
                                    viewHolder.give_like_text.setText(sum + "");
                                }
                            });
                        } else {
                            mContext.startActivity(new Intent(mContext, LoginActivity.class));
                        }
                    } else {
                        //点赞状态去取消点赞
                        if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                            mockData.DokiVieo(listBeans.getVideo().getVideoId(), PrefManager.getInstance().getToken(), 0, new MockData.CallBackCommentsOk() {
                                @Override
                                public void ok() {
                                    LogPrint.printError("取消点赞成功");
                                    DokiStatus.set(position, 0);
                                    viewHolder.like_dok.setImageResource(R.mipmap.no_give_love_icon);
                                    int sum = integers.get(position);
                                    sum--;
                                    integers.set(position, sum);
                                    if(sum<0) {
                                        viewHolder.give_like_text.setText("0");
                                    }else{
                                        viewHolder.give_like_text.setText(sum + "");
                                    }
                                }
                            });
                        } else {
                            mContext.startActivity(new Intent(mContext, LoginActivity.class));
                        }
                    }

                }
            }
        });
        //头像点击
        viewHolder.headIcon_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogPrint.printError("头像");
            }
        });
        //双击点赞
        viewHolder. give_two_love.setOnItemClick(new Love.LoveCallBack() {
            @Override
            public void ClickLove() {
                if (!UtilsAll.isDoubleClick1()) {
                    startShakeByView(viewHolder.like_dok);
                    if (!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                        if (DokiStatus.get(position) == 0) {
                            mockData.DokiVieo(listBeans.getVideo().getVideoId(), PrefManager.getInstance().getToken(), 1, new MockData.CallBackCommentsOk() {
                                @Override
                                public void ok() {
                                    LogPrint.printError("点赞成功");
                                    DokiStatus.set(position, 1);
                                    startShakeByView(viewHolder.like_dok);
                                    viewHolder.like_dok.setImageResource(R.mipmap.give_love_icon);
                                    int sum = integers.get(position);
                                    sum++;
                                    integers.set(position, sum);
                                    viewHolder.give_like_text.setText(sum + "");
                                }
                            });
                        }
                    }
                }
            }
        });

        //分享
        viewHolder.share_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Defaultcontent.title = listBeans.getVideo().getTitle();
                Defaultcontent.imageurl = listBeans.getVideo().getOriginCover();
                Defaultcontent.text = listBeans.getVideo().getDescribes();
                Defaultcontent.url = listBeans.getVideo().getShareUrl();
                Defaultcontent.code_url = "";
                dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                if (dialogUtilsSoftReference.get() != null) {
                    dialogUtilsSoftReference.get().Share1(mContext);
                } else {
                    dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                    dialogUtilsSoftReference.get().Share1(mContext);
                }
            }
        });

        //评论
        viewHolder.comments_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                if (dialogUtilsSoftReference.get() != null) {
                    dialogUtilsSoftReference.get().Comments(mockData,listBeans.getVideo().getVideoId(), mContext, new DialogUtils.CommentOK() {
                        @Override
                        public void ok() {
                            int size= commentsIntegar.get(position);
                            size++;
                            commentsIntegar.set(position,size);
                            viewHolder.comments_text.setText(size + "");
                        }

                        @Override
                        public void delet() {
                            int size= commentsIntegar.get(position);
                            size--;
                            commentsIntegar.set(position,size);
                            viewHolder.comments_text.setText(size + "");
                        }
                    });
                } else {
                    dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                    dialogUtilsSoftReference.get().Comments(mockData,listBeans.getVideo().getVideoId(), mContext, new DialogUtils.CommentOK() {
                        @Override
                        public void ok() {
                            int size= commentsIntegar.get(position);
                            size++;
                            commentsIntegar.set(position,size);
                            viewHolder.comments_text.setText(size + "");
                        }

                        @Override
                        public void delet() {
                            int size= commentsIntegar.get(position);
                            size--;
                            commentsIntegar.set(position,size);
                            viewHolder.comments_text.setText(size + "");
                        }
                    });
                }
            }
        });
        //喜欢就说出来
        viewHolder.say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                    mContext.startActivity(new Intent(mContext,LoginActivity.class));
                }else {
                    dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                    if (dialogUtilsSoftReference.get() != null) {
                        inputComment= dialogUtilsSoftReference.get().CommentsContent(mockData,mContext, listBeans.getVideo().getVideoId(), new MockData.CallBackCommentsOkData() {
                            @Override
                            public void start() {
                                commenting = dialogUtilsSoftReference.get().createLoadingDialog(mContext, "评论中...", true);
                            }
                            @Override
                            public void getCommentsData(CommentsOk.BodyBean.ContentBean contentBean) {
                                if (commenting != null) {
                                    commenting.dismiss();
                                }
                                if (inputComment != null) {
                                    inputComment.dismiss();
                                }
                                ToastUtilsAll.getInstance().showShortToast("评论成功");
                                int size= commentsIntegar.get(position);
                                size++;
                                commentsIntegar.set(position,size);
                                viewHolder.comments_text.setText(size + "");
                            }
                            @Override
                            public void error(String error) {
                                if (commenting != null) {
                                    commenting.dismiss();
                                }
                                if (inputComment != null) {
                                    inputComment.dismiss();
                                }
                                if (!TextUtils.isEmpty(error)) {
                                    ToastUtilsAll.getInstance().showShortToast(error);
                                }
                            }
                        }, new DialogUtils.CommentOK() {
                            @Override
                            public void ok() {

                            }

                            @Override
                            public void delet() {
                                int size= commentsIntegar.get(position);
                                size--;
                                commentsIntegar.set(position,size);
                                viewHolder.comments_text.setText(size + "");
                            }
                        });
                    } else {
                        dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                        inputComment=  dialogUtilsSoftReference.get().CommentsContent(mockData,mContext, listBeans.getVideo().getAudioId(), new MockData.CallBackCommentsOkData() {
                            @Override
                            public void start() {
                                commenting = dialogUtilsSoftReference.get().createLoadingDialog(mContext, "评论中...", true);
                            }
                            @Override
                            public void getCommentsData(CommentsOk.BodyBean.ContentBean contentBean) {
                                if (commenting != null) {
                                    commenting.dismiss();
                                }
                                if (inputComment != null) {
                                    inputComment.dismiss();
                                }
                                ToastUtilsAll.getInstance().showShortToast("评论成功");
                                int size= commentsIntegar.get(position);
                                size++;
                                commentsIntegar.set(position,size);
                                viewHolder.comments_text.setText(size + "");
                            }
                            @Override
                            public void error(String error) {
                                if (commenting != null) {
                                    commenting.dismiss();
                                }
                                if (inputComment != null) {
                                    inputComment.dismiss();
                                }
                                if (!TextUtils.isEmpty(error)) {
                                    ToastUtilsAll.getInstance().showShortToast(error);
                                }
                            }
                        }, new DialogUtils.CommentOK() {
                            @Override
                            public void ok() {

                            }

                            @Override
                            public void delet() {
                                int size= commentsIntegar.get(position);
                                size--;
                                commentsIntegar.set(position,size);
                                viewHolder.comments_text.setText(size + "");
                            }
                        });
                    }
                }

            }
        });
        //查看详情
        viewHolder.search_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //\(^o^)/~
                if (listBeans.getActivity() != null) {
                    if (listBeans.getActivity().getActivityUrlType() == 1) {
                        UtilsAll.GoWebviewAll(mContext, listBeans.getActivity().getActivityUrl());
                    } else {
                        //安卓头部
                        Intent mintent = new Intent(mContext, UseguideActivity.class);
                        mintent.putExtra("title", listBeans.getActivity().getTitle());
                        mintent.putExtra("url", listBeans.getActivity().getActivityUrl());
                        mContext.startActivity(mintent);
                    }
                }
            }
        });
        //立即购买
        viewHolder.buy_go_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listBeans.getProductInfos()!=null) {
                    if(listBeans.getProductInfos().size()>0) {
                        UtilsAll.GoWebviewAll(mContext, ConfigH5Url.setGoodsDetails(listBeans.getProductInfos().get(0).getType(), listBeans.getProductInfos().get(0).getProductId()));
                    }
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<VideoEntity.BodyBean.ContentBean.ListBean> newDataList) {
        this.dataList=newDataList;
    }

    public VideoEntity.BodyBean.ContentBean.ListBean getDataByPosition(int position) {
        return dataList.get(position);
    }


    public class VideoViewHolder extends WRecyclerView.ViewHolder {

        /**
         * 视频播放器
         */
        public ListVideoView videoView;
        /**
         * 封面视频第一针
         */
        public SimpleDraweeView sdvCover;
        /**
         * 视频暂停按钮
         */
        public RelativeLayout music_play_pause;
        /**
         * 视频头像    音乐头像
         */
        public CircleImageView headIcon_v,music_image;
        /**
         * 点赞
         */
        public ImageView like_dok;
        /**
         * 双击点赞
         */
        public Love give_two_love;
        /**
         * 点赞数量
         */
        public TextView give_like_text;
        /**
         * 评论image
         */
        public ImageView comments_image;
        /**
         * 评论数量
         */
        public TextView comments_text;

        /**
         * 分享
         */
        public ImageView share_video;

        /**
         * 分享数量
         */
        public TextView share_text,from_name,from_describe;

        /**
         * 没有网络背景图
         */
        public ImageView background_image;

        /**
         * 喜欢就说出来
         */
        public RelativeLayout say;

        public RelativeLayout buy_go_on,search_items;
        /**
         * 跑马灯
         */
        public TextBannerView tv_banner;
        public MusicalNoteLayout music_note_layout;
        public VideoViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            sdvCover = itemView.findViewById(R.id.sdv_cover);
            music_play_pause=itemView.findViewById(R.id.music_play_pause);
            headIcon_v=itemView.findViewById(R.id.headIcon_v);
            like_dok=itemView.findViewById(R.id.like_dok);
            give_two_love=itemView.findViewById(R.id.give_two_love);
            give_like_text =itemView.findViewById(R.id.give_like_text);
            comments_image =itemView.findViewById(R.id.comments_image);
            comments_text =itemView.findViewById(R.id.comments_text);
            share_video =itemView.findViewById(R.id.share_video);
            share_text =itemView.findViewById(R.id.share_text);
            background_image=itemView.findViewById(R.id.background_image);
            from_name=itemView.findViewById(R.id.from_name);
            from_describe=itemView.findViewById(R.id.from_describe);
            music_image=itemView.findViewById(R.id.music_image);
            say=itemView.findViewById(R.id.say);
            search_items=itemView.findViewById(R.id.search_items);
            buy_go_on=itemView.findViewById(R.id.buy_go_on);
            tv_banner=itemView.findViewById(R.id.tv_banner);
            music_note_layout=itemView.findViewById(R.id.music_note_layout);
        }
    }

    /**
     * 心跳
     * @param imageView
     */
    private void startShakeByView(ImageView imageView) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(imageView, "scaleX", 1.4f, 1f);
        anim1.setRepeatCount(2);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView, "scaleY", 1.4f, 1f);
        anim2.setRepeatCount(2);
        AnimatorSet set = new AnimatorSet();
        set.play(anim1).with(anim2);
        set.setDuration(300);
        set.start();
    }
}
