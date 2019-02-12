package com.iaowoo.mobile.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.LoginActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.CommentsRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.OnclicKRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.im.RedPayTypeAdapter;
import com.iaowoo.mobile.Ui.classification.Model.Comments;
import com.iaowoo.mobile.Ui.classification.Model.CommentsOk;
import com.iaowoo.mobile.Ui.classification.Model.NEW_RED_ED;
import com.iaowoo.mobile.Ui.classification.Model.im.RedPayType;
import com.iaowoo.mobile.Ui.classification.View.FrameAnimation;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.iaowoo.mobile.interfaceCallback.RedPayListener;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.iaowoo.mobile.umeng.ShareUtils;
import com.jungly.gridpasswordview.GridPasswordView;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class DialogUtils {

    /**
     * 防止非正常用户多次点击
     * 提示
     */
    private  boolean click=true;

    /**
     * 游客提示
     * @param context
     */
    public void LoginTo(final Context context) {
        if(click) {
            click=false;
            AlertDilog(context, "温馨提示", "您当前处于游客身份，需要登录账号后才能使用该功能", "再逛逛", "去登录", new alertCallBack() {
                @Override
                public void OnOk() {
                    click=true;
                }
                @Override
                public void OnNo() {
                    click=true;
                    //跳登录
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
            });
        }
    }

    /**
     * 网络异常提示
     * @param context
     * @param alertCallBack
     */
    public void LoginToCallback(final Context context,alertCallBack alertCallBack) {
        AlertDilog(context, "温馨提示", "您当前处于游客身份，需要登录账号后才能使用该功能", "再逛逛", "去登录", alertCallBack);
    }

    /**
     * 防止非正常用户多次点击
     */
    private  boolean click1=true;
    //网络异常提示
    public void LoginTo1(final Context context, final ClickCallBack clickCallBack) {
        if(click1) {
            click1=false;
            AlertDilog(context, "温馨提示", "您当前处于游客身份，需要登录账号后才能使用该功能", "再逛逛", "去登录", new alertCallBack() {
                @Override
                public void OnOk() {
                    clickCallBack.OnClick();
                    click1=true;
                }
                @Override
                public void OnNo() {
                    click1=true;
                    //跳登录
                    context.startActivity(new Intent(context, LoginActivity.class));
                    clickCallBack.OnClick();
                }
            });
        }
    }

    /**
     * 网络异常提示
     * @param context
     */
    public  void abnormal(Context context) {
        if (!SingleOverAll.getInstance().isNetworkConnected(ZApplication.getContext())) {
//            alertContent(context,"网络异常,请打开你的移动数据或者wifi");
            AlertDilog(context, "提示", "网络异常,请打开你的移动数据或者wifi", "", "确认", new alertCallBack() {
                @Override
                public void OnOk() {
                }
                @Override
                public void OnNo() {
                }
            });
        }
    }
    /**
     * 加载弹框
     * @param context
     * @param msg
     * @return
     */
    public  Dialog createLoadingDialog(Context context, String msg ,boolean defalut) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v;
        if(defalut){
            v = inflater.inflate(R.layout.loading_dilog_defalut, null);// 得到加载view
            TextView tipTextView =v.findViewById(R.id.tipTextView);// 提示文字
            tipTextView.setText(msg);// 设置加载信息
        }else{
            v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        }
        LinearLayout layout = v.findViewById(R.id.dialog_loading_view);// 加载布局
        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        ImageView images=v.findViewById(R.id.images);
//        VectorDrawableCompat drawable = VectorDrawableCompat.create(context.getResources(), R.drawable.ic_loading, null);
//        images.setImageDrawable(drawable);
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        return loadingDialog;
    }

    /**
     * 关闭dialog
     *
     * http://blog.csdn.net/qq_21376985
     *
     * @param mDialogUtils
     */
    public  void closeDialog(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }


    /**
     * Alert弹框系统
     * @param context
     * @param Title
     * @param message
     * @param one
     * @param two
     * @param alertCallBack
     */
    public  void AlertDilog(Context context, String Title, String message, String one, String two, final alertCallBack alertCallBack) {
        // 创建构建器
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 设置参数
        builder.setTitle(Title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(one, new DialogInterface.OnClickListener() {// 第一个参数
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // TODO Auto-generated method stub
                        alertCallBack.OnOk();
                    }
                }).setNegativeButton(two, new DialogInterface.OnClickListener() {// 第二个参数

            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                // TODO Auto-generated method stub
                alertCallBack.OnNo();

            }

        });
        builder.create().show();
    }


    private Dialog commenting;

    private Dialog inputComment;

    private int pageNum=1;

    private int pageSize=30;

    private boolean Tag=true;

    private List<Comments.BodyBean.ContentBean.ListBean> listBeansAll;
    /**
     * 评论成功
     */
    public interface  CommentOK{
        void ok();
        void delet();
    }
    private boolean up=false;

    private boolean ok=false;

    private int CommentNumber;









    /**
     * 评论
     * @param videoId
     * @param context
     * @return
     */
    public  Dialog Comments(final MockData mockData, final String videoId, final Context context, final CommentOK commentOK) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.comments_dialog, null);// 得到加载view
        FrameLayout custom_frame=v.findViewById(R.id.comments_all);
        RelativeLayout comments_go=v.findViewById(R.id.comments_go);
        RelativeLayout close=v.findViewById(R.id.close);
        final TextView title_comments=v.findViewById(R.id.title_comments);
        final TextView no_have_comments=v.findViewById(R.id.no_have_comments);

        final RecyclerView recyler_comments=v.findViewById(R.id.recyler_comments);
        recyler_comments.setHasFixedSize(true);//设置固定大小
        recyler_comments.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        listBeansAll=new ArrayList<>();
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        //为recyclerView设置布局管理器
        recyler_comments.setLayoutManager(mLayoutManager);

        final CommentsRecycleAdapter commentsRecycleAdapter =new CommentsRecycleAdapter(context,mockData);

        commentsRecycleAdapter.setCallBack(new CommentsRecycleAdapter.CallBackCommentsOk() {
            @Override
            public void ok(int position) {
                commentOK.delet();
                listBeansAll.remove(position);
                commentsRecycleAdapter.SetData(listBeansAll);
                commentsRecycleAdapter.notifyDataSetChanged();
                if(CommentNumber>10000){
                    title_comments.setText(UtilsAll.division(CommentNumber,10000)+"w条评论");
                }else{
                    CommentNumber=CommentNumber-1;
                    title_comments.setText( CommentNumber+"条评论");
                }
                if(CommentNumber==0){
                    no_have_comments.setVisibility(View.VISIBLE);
                }else{
                    no_have_comments.setVisibility(View.GONE);
                }
            }
        });
        //获取评论数据
        mockData.getCommentsListData(videoId,PrefManager.getInstance().getToken(), pageNum, pageSize, new MockData.CallBackCommetnsData() {
            @Override
            public void getListComments(List<Comments.BodyBean.ContentBean.ListBean> listBeans1, int allSizeNumber) {
                for(int i=0;i<listBeans1.size();i++){
                    listBeansAll.add(listBeans1.get(i));
                }
                CommentNumber=allSizeNumber;
                commentsRecycleAdapter.SetData(listBeansAll);
                recyler_comments.setAdapter(commentsRecycleAdapter);
                if(allSizeNumber>10000){
                    title_comments.setText((allSizeNumber)/10000+"w条评论");
                }else{
                    title_comments.setText(allSizeNumber+"条评论");
                }
                no_have_comments.setVisibility(View.GONE);

            }

            @Override
            public void noHaveData() {
                title_comments.setText("0条评论");
                no_have_comments.setVisibility(View.VISIBLE);
//                ToastUtilsAll.getInstance().showShortToast("没有数据");
//                Snackbar.make(recyler_comments, "                 没有数据", Snackbar.LENGTH_SHORT).show();
            }
        });
        commentsRecycleAdapter.setOnclicKRecycleAdapter(new OnclicKRecycleAdapter() {
            @Override
            public void onItemClick(int position) {
            }
            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });


        final Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle_no_bg);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(true); // 点击加载框以外的区域
        loadingDialog.setContentView(custom_frame, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        //RecyclerView的滑动监听
        recyler_comments.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //当RecyclerView滑动时触发
            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //到达顶部
                if (!recyclerView.canScrollVertically(-1)) {
                    up=true;
                } else if (!recyclerView.canScrollVertically(1)) {
                } else if (dy < 0) {
                    //网上滑动
                    ok=true;
                } else if (dy > 0) {
                    //往下滑动
                    up=false;
                }
                if(up&&ok){
                    loadingDialog.dismiss();
                }

                //获取可见item个数
                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                //说明是最后一条数据
                if (lastVisibleItemPosition + 1 == commentsRecycleAdapter.getItemCount()) {
                    //加载到最后一条数据时候
//                    Snackbar.make(recyclerView, "                                     加载中...", Snackbar.LENGTH_SHORT).show();
                    //获取评论数据
                    if(Tag) {
                        pageNum++;
                        Tag=false;
                        mockData.getCommentsListData(videoId, PrefManager.getInstance().getToken(), pageNum, pageSize, new MockData.CallBackCommetnsData() {
                            @Override
                            public void getListComments(List<Comments.BodyBean.ContentBean.ListBean> listBeans1, int allSizeNumber) {
                                for (int i = 0; i < listBeans1.size(); i++) {
                                    listBeansAll.add(listBeans1.get(i));
                                }
                                Tag=true;
                                commentsRecycleAdapter.SetData(listBeansAll);
                                commentsRecycleAdapter.notifyDataSetChanged();
                            }
                            @Override
                            public void noHaveData() {
//                            Snackbar.make(recyclerView, "                 没有数据", Snackbar.LENGTH_SHORT).show();
                                Tag=false;
                            }
                        });
                    }
                }
            }
        });

        custom_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
            }
        });
        comments_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                    context.startActivity(new Intent(context,LoginActivity.class));
                }else {
                    inputComment= CommentsContent(mockData,context, videoId, new MockData.CallBackCommentsOkData() {
                        @Override
                        public void start() {
                            commenting=createLoadingDialog(context,"评论中...",true);
                        }
                        @Override
                        public void getCommentsData(CommentsOk.BodyBean.ContentBean contentBean) {
                            if(commenting!=null){
                                commenting.dismiss();
                            }
                            if(inputComment!=null){
                                inputComment.dismiss();
                            }
                            Comments.BodyBean.ContentBean.ListBean listBean=new Comments.BodyBean.ContentBean.ListBean();
                            listBean.setCommentId(contentBean.getCommentId());
                            listBean.setContent(contentBean.getContent());
                            listBean.setCreateTime(contentBean.getCreateTime());
                            listBean.setCreateUser(contentBean.getCreateUser());
                            listBean.setHeadImgUrl(contentBean.getHeadImgUrl());
                            listBean.setParentCommentId(contentBean.getParentCommentId());
                            listBean.setVideoId(contentBean.getVideoId());
                            listBean.setPraiseNum(contentBean.getPraiseNum());
                            listBean.setTreadNum(contentBean.getTreadNum());
                            listBean.setNickname(contentBean.getNickname());
                            listBean.setUserId(contentBean.getUserId());
                            no_have_comments.setVisibility(View.GONE);

                            if(listBeansAll.size()>0){
                                listBeansAll.add(0,listBean);
                                commentsRecycleAdapter.SetData(listBeansAll);
                                commentsRecycleAdapter.notifyDataSetChanged();
                            }else{
                                listBeansAll.add(0,listBean);
                                commentsRecycleAdapter.SetData(listBeansAll);
                                recyler_comments.setAdapter(commentsRecycleAdapter);
                            }
                            ToastUtilsAll.getInstance().showShortToast("评论成功");
                            if(CommentNumber>10000){
                                title_comments.setText(UtilsAll.division(CommentNumber,10000)+"w条评论");
                            }else{
                                CommentNumber=CommentNumber+1;
                                title_comments.setText( CommentNumber+"条评论");                            }
                        }

                        @Override
                        public void error(String error) {
                            if(!TextUtils.isEmpty(error)) {
                                ToastUtilsAll.getInstance().showShortToast(error);
                            }
                            if(commenting!=null){
                                commenting.dismiss();
                            }
                            if(inputComment!=null){
                                inputComment.dismiss();
                            }

                        }


                    },commentOK);
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
            }
        });
        return loadingDialog;
    }

    /**
     * 开始评论
     * @param context
     * @return
     */
    public Dialog CommentsContent(final MockData mockData, final Context context, final String videoId, final MockData.CallBackCommentsOkData callBackCommentsOkData, final CommentOK commentOK){
        LogPrint.printError("评论视频的id"+videoId);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.comments_input, null);// 得到加载view
        LinearLayout custom_frame=v.findViewById(R.id.comment_input_all);
        RelativeLayout sendButton=v.findViewById(R.id.sendButton);
        final EditText comment_edit_txt=v.findViewById(R.id.comment_edit_txt);
        //开始评论
        final ImageView send=v.findViewById(R.id.send);
        showKeyboard(comment_edit_txt);
        final Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle_plp);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(true); // 点击加载框以外的区域
        loadingDialog.setContentView(custom_frame, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        comment_edit_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s)){
                    send.setImageResource(R.mipmap.gray_send_icon);
                }else{
                    send.setImageResource(R.mipmap.red_send_icon);
                }
            }
        });

        custom_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(comment_edit_txt.getText().toString().replace(" ", ""))){
                    mockData.CommentStart(videoId,PrefManager.getInstance().getToken(),comment_edit_txt.getText().toString(),callBackCommentsOkData,commentOK);
                }else{
                    ToastUtilsAll.getInstance().showShortToast("不能输入空格");
                }
            }
        });

        return loadingDialog;

    }

    /**
     * 打开软件盘
     * @param et
     */
    public void showKeyboard(final EditText et){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                et.setFocusable(true);
                et.setFocusableInTouchMode(true);
                et.requestFocus();
                InputMethodManager inputManager = (InputMethodManager) et.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(et, 0);
            }
        }, 300);
    }
    /**
     * 分享弹窗
     * @param context
     * @return
     */
    public  Dialog Share(final Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.share_dialog, null);// 得到加载view
        FrameLayout custom_frame=v.findViewById(R.id.share_all);


        RelativeLayout wechat_btn=v.findViewById(R.id.wechat_btn);
        RelativeLayout qq_btn=v.findViewById(R.id.qq_btn);
        RelativeLayout weibo_btn=v.findViewById(R.id.weibo_btn);
        RelativeLayout kongjian=v.findViewById(R.id.kongjian);
        RelativeLayout wetcat_pengyou=v.findViewById(R.id.wetcat_pengyou);
        RelativeLayout small_pc=v.findViewById(R.id.small_pc);

        final Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(true); // 点击加载框以外的区域
        loadingDialog.setContentView(custom_frame, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        //微信分享
        wechat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //微信好友的分享
                ShareUtils.shareWeb((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.WEIXIN,null);
                loadingDialog.cancel();
            }
        });
        //qq分享
        qq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.QQ,null);
                loadingDialog.cancel();
            }
        });
        //新浪分享
        weibo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.SINA,null);
                loadingDialog.cancel();

            }
        });
        //空间分享
        kongjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.QZONE,null);
                loadingDialog.cancel();
            }
        });
        //朋友圈
        wetcat_pengyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.WEIXIN_CIRCLE,null);
                loadingDialog.cancel();
            }
        });
        //小程序分享
        small_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //小程序的分享
                ShareUtils.shareSmallPc((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo,Defaultcontent.small_id,Defaultcontent.code_url, SHARE_MEDIA.WEIXIN,null);
                loadingDialog.cancel();

            }
        });
        return loadingDialog;
    }
    /**
     * 分享弹窗
     * @param context
     * @return
     */
    public  Dialog Share1(final Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.share_dialog_1, null);// 得到加载view
        FrameLayout custom_frame=v.findViewById(R.id.share_all);
        RelativeLayout wechat_btn=v.findViewById(R.id.wechat_btn);
        RelativeLayout qq_btn=v.findViewById(R.id.qq_btn);
        RelativeLayout weibo_btn=v.findViewById(R.id.weibo_btn);
        RelativeLayout kongjian=v.findViewById(R.id.kongjian);
        RelativeLayout wetcat_pengyou=v.findViewById(R.id.wetcat_pengyou);
        RelativeLayout small_pc=v.findViewById(R.id.small_pc);

        final Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(true); // 点击加载框以外的区域
        loadingDialog.setContentView(custom_frame, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        //微信分享
        wechat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //微信好友的分享
                ShareUtils.shareWeb((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.WEIXIN,null);
                loadingDialog.cancel();
            }
        });
        //qq分享
        qq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.QQ,null );
                loadingDialog.cancel();
            }
        });
        //新浪分享
        weibo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.SINA,null );
                loadingDialog.cancel();

            }
        });
        //空间分享
        kongjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.QZONE,null);
                loadingDialog.cancel();
            }
        });
        //朋友圈
        wetcat_pengyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareWeb((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo, SHARE_MEDIA.WEIXIN_CIRCLE,null);
                loadingDialog.cancel();
            }
        });
        //小程序分享
        small_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //小程序的分享
                ShareUtils.shareSmallPc((Activity) context, Defaultcontent.url, Defaultcontent.title, Defaultcontent.text, Defaultcontent.imageurl, R.mipmap.logo,Defaultcontent.small_id,Defaultcontent.code_url, SHARE_MEDIA.WEIXIN,null);
                loadingDialog.cancel();

            }
        });
        return loadingDialog;
    }

    /**
     * 更新自定义弹窗
     * @param context
     * @return
     */
    public  Dialog Update(final Context context, String version_name_txt, String update_content_txt, final ClickCallBack clickCallBack) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.upadate_layout, null);// 得到加载view
        FrameLayout update_all=v.findViewById(R.id.update_all);


        TextView vesion_name=v.findViewById(R.id.vesion_name);
        TextView update_content=v.findViewById(R.id.update_content);
        FrameLayout no=v.findViewById(R.id.no);
        FrameLayout yes=v.findViewById(R.id.yes);
        vesion_name.setText(version_name_txt);
        update_content.setText(update_content_txt);


        final Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(update_all, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallBack.OnClick();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();

            }
        });

        return loadingDialog;
    }
    private int[] mImgResIds = new int[]{
            R.mipmap.icon_open_red_packet1,
            R.mipmap.icon_open_red_packet2,
            R.mipmap.icon_open_red_packet3,
            R.mipmap.icon_open_red_packet4,
            R.mipmap.icon_open_red_packet5,
            R.mipmap.icon_open_red_packet6,
            R.mipmap.icon_open_red_packet7,
            R.mipmap.icon_open_red_packet7,
            R.mipmap.icon_open_red_packet8,
            R.mipmap.icon_open_red_packet9,
            R.mipmap.icon_open_red_packet4,
            R.mipmap.icon_open_red_packet10,
            R.mipmap.icon_open_red_packet11,
    };
    private FrameAnimation mFrameAnimation;

    /**
     * 新人红包
     * @param context
     * @return
     */
    public  Dialog ThecoupleRedPackets(final Context context, final ClickCallBack clickCallBack) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.new_red, null);// 得到加载view
        FrameLayout update_all=v.findViewById(R.id.new_all);
        ImageView new_light_s=v.findViewById(R.id.new_light_s);
        final RelativeLayout bg=v.findViewById(R.id.bg);
        final RelativeLayout red_layout_h=v.findViewById(R.id.red_layout_h);
        final TextView red_money=v.findViewById(R.id.red_money);
        ImageView use=v.findViewById(R.id.use);
        final ImageView open_red=v.findViewById(R.id.open_red);
        RelativeLayout close_red=v.findViewById(R.id.close_red);


        final Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(update_all, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        close_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim();
                loadingDialog.dismiss();
            }
        });
        use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim();
                loadingDialog.dismiss();
            }
        });

        //控件旋转
        RotateAnimation rotateAnimation = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        //让旋转动画一直转，不停顿的重点
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);
        new_light_s.startAnimation(rotateAnimation);

        open_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                open_red.setImageResource(R.mipmap.golden_img);
                if (mFrameAnimation != null) {
                    //如果正在转动，则直接返回
                    return;
                }
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
//                  SingleOverAll.getInstance().LoginToCallback(context, new alertCallBack() {
//                      @Override
//                      public void OnOk() {
//
//                      }
//                      @Override
//                      public void OnNo() {
//                          loadingDialog.dismiss();
//                      }
//                  });
                    context.startActivity(new Intent(context, LoginActivity.class));
                }else{
                    startAnim(open_red);
                    newRedEnvelope(PrefManager.getInstance().getToken(),new OkhttpManager.ReCallBack<Object>() {
                        @Override
                        public void onReqSuccess(Object result) {
                            LogPrint.printError("狗日的"+result.toString());
                            if(!TextUtils.isEmpty(result.toString())){
                                NEW_RED_ED new_red_ed= JSON.parseObject(result.toString(),NEW_RED_ED.class);
                                if(new_red_ed.getCode().endsWith(OkhttpManager.SUCESS)){
                                    bg.setBackgroundResource(R.mipmap.congratulations_img);
                                    red_layout_h.setVisibility(View.VISIBLE);
                                    red_money.setText("¥"+new_red_ed.getBody().getContent());
                                    stopAnim();
                                    open_red.setVisibility(View.GONE);
                                }else{
                                    stopAnim();
                                    loadingDialog.dismiss();
                                    ToastUtilsAll.getInstance().showShortToast("你已经领过新手红包");
                                }
                            }
                        }

                        @Override
                        public void onReqFailed(String errorMsg) {

                        }
                    });
                }
            }
        });


        return loadingDialog;
    }


    /**
     * 开始转动
     * @param mIvOpen
     */
    public void startAnim(final ImageView mIvOpen) {
        mFrameAnimation = new FrameAnimation(mIvOpen, mImgResIds, 125, true);
        mFrameAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
            @Override
            public void onAnimationStart() {
                LogPrint.printError("start");
            }

            @Override
            public void onAnimationEnd() {
                LogPrint.printError("end");
            }

            @Override
            public void onAnimationRepeat() {
                LogPrint.printError("repeat");
            }

            @Override
            public void onAnimationPause() {
                mIvOpen.setBackgroundResource(R.mipmap.icon_open_red_packet1);
            }
        });
    }

    /**
     * 停止转动
     */
    public void stopAnim() {
        if (mFrameAnimation != null) {
            mFrameAnimation.release();
            mFrameAnimation = null;
        }
    }


    /**
     * 新人红包
     */
    private void newRedEnvelope(String token,OkhttpManager.ReCallBack<Object> ok){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",token);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.redEnvelope, "RED", -1, paramsMap, ok);
    }


    public Dialog showRedPayConfirmDialog(Context context, RedPayType redPayType, String totalAmount, final RedPayListener redPayListener) {
        if (context == null) {
            return null;
        }
        final Dialog dialog = new Dialog(context, R.style.MyDialogStyle);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pay_confirm, null);
        ImageView closeImg = view.findViewById(R.id.iv_quit);
        TextView payNameText = view.findViewById(R.id.tv_pay_name);
        TextView totalAmountText = view.findViewById(R.id.tv_total_amount);
        final GridPasswordView passwordView = view.findViewById(R.id.pswView);
        final Button sureBtn = view.findViewById(R.id.btn_confirm_pay);

        passwordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {

            @Override
            public void onTextChanged(String psw) {
                if (psw.length() == 6){
                    sureBtn.setEnabled(true);
//                    redPayListener.onRedPayClick(passwordView.getId(), psw);
                }
            }

            @Override
            public void onInputFinish(String psw) {

            }
        });

        LinearLayout selectPayTypeLayout = view.findViewById(R.id.ll_select_pay_type);
        if (redPayType != null) {
            payNameText.setText(redPayType.getPayName());
        }
        totalAmountText.setText(totalAmount);

        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        selectPayTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (redPayListener != null) {
                    redPayListener.onRedPayClick(v.getId(), "");
                }
                dialog.dismiss();
            }
        });
        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (redPayListener != null) {
                    redPayListener.onRedPayClick(v.getId(), passwordView.getPassWord());
                }
            }
        });

        dialog.setContentView(view);
        dialog.show();
        return dialog;
    }

    public Dialog showPayTypeListDialog(Context context, int res, List<RedPayType> mList, final RedPayListener redPayListener) {
        if (context == null) {
            return null;
        }
        final Dialog dialog = new Dialog(context, R.style.MyDialogStyle);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(res, null);

        ListView listView = view.findViewById(R.id.lv_pay_type);
        RedPayTypeAdapter adapter = new RedPayTypeAdapter(context, mList);
        listView.setAdapter(adapter);

        ImageView closeImg = view.findViewById(R.id.iv_quit);
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (redPayListener != null) {
                    redPayListener.onItemRedPayTypeClick(position);
                }
                dialog.dismiss();
            }
        });

        dialog.setContentView(view);
        dialog.show();
        return dialog;
    }


    public Dialog getChatRedPackets(final Context context, final ClickCallBack clickCallBack) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_get_red_peak, null);
        FrameLayout frameLayout = view.findViewById(R.id.im_red_peak_layout);
        final RelativeLayout bg = view.findViewById(R.id.bg);
        final TextView redPeakName = view.findViewById(R.id.tv_red_peak_name);
        final TextView redPeakTip = view.findViewById(R.id.tv_red_peak_tip);
        final TextView senderName = view.findViewById(R.id.tv_red_peak_sender);
        final ImageView senderAvatarImg = view.findViewById(R.id.tv_red_peak_sender_avatar);
        final ImageView openRedImg = view.findViewById(R.id.open_red);
        RelativeLayout closeLayout = view.findViewById(R.id.rl_close);
        final RelativeLayout redBottomLayout = view.findViewById(R.id.red_layout_h);
        final TextView redMoneyText = view.findViewById(R.id.red_money);


        final Dialog dialog= new Dialog(context, R.style.MyDialogStyle);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(frameLayout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        dialog.show();

        closeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim();
                dialog.dismiss();
            }
        });

        openRedImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFrameAnimation != null) {
                    return;
                }
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                    context.startActivity(new Intent(context, LoginActivity.class));
                }else{
                    startAnim(openRedImg);
                    getIMRedEnvelope(PrefManager.getInstance().getToken(),new OkhttpManager.ReCallBack<Object>() {
                        @Override
                        public void onReqSuccess(Object result) {
                            if(!TextUtils.isEmpty(result.toString())){
                                NEW_RED_ED new_red_ed= JSON.parseObject(result.toString(),NEW_RED_ED.class);
                                if(new_red_ed.getCode().endsWith(OkhttpManager.SUCESS)){
                                    bg.setBackgroundResource(R.mipmap.congratulations_img);
                                    redBottomLayout.setVisibility(View.VISIBLE);
                                    redMoneyText.setText("¥"+new_red_ed.getBody().getContent());
                                    stopAnim();
                                    openRedImg.setVisibility(View.GONE);
                                }else{
                                    stopAnim();
                                    dialog.dismiss();
                                    ToastUtilsAll.getInstance().showShortToast("你已经领过红包了");
                                }
                            }

                            if (clickCallBack != null) {
                                clickCallBack.OnClick();
                            }
                        }

                        @Override
                        public void onReqFailed(String errorMsg) {

                        }
                    });
                }
            }
        });
        return dialog;
    }

    private void getIMRedEnvelope(String token,OkhttpManager.ReCallBack<Object> ok){
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken",token);
        OkhttpManager.getInstance(ZApplication.getContext()).requestPostByAsyn(true, UtilsOkHttpAll.redEnvelope, "RED", -1, paramsMap, ok);
    }
}