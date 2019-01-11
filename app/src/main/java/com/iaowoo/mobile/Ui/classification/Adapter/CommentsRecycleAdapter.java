package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Activity.LoginActivity;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.MockData;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.LoginActivity;
import com.iaowoo.mobile.Ui.classification.Model.Comments;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.MockData;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenda on 2018/4/3.
 * 评论adapter
 */

public class CommentsRecycleAdapter extends BaseSoEasyAdapter{

    private Context context;

    private List<Comments.BodyBean.ContentBean.ListBean> listBeans;

    /**
     * 记录点赞状态
     */
    public List<Integer> listTag;

    /**
     * 记录点赞数量
     */
    public List<Integer> SizeAll;

    CallBackCommentsOk callBackCommentsOk;

    private MockData mockData;

    public interface  CallBackCommentsOk{
        void ok(int position);
    }

    public CommentsRecycleAdapter( Context context,MockData mockData) {
        this.context=context;
        this.mockData=mockData;
        listTag=new ArrayList<>();
        SizeAll=new ArrayList<>();
    }
    public void setCallBack(CallBackCommentsOk callBackCommentsOk){
        this.callBackCommentsOk=callBackCommentsOk;
    }

    public void SetData(List<Comments.BodyBean.ContentBean.ListBean> listBeans){
        this.listBeans= listBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_item,parent,false);
        return new CommentsViewHolder(view);
    }

    /**
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final CommentsViewHolder holder = (CommentsViewHolder) viewHolder;
        //设置头像
        if(!TextUtils.isEmpty(listBeans.get(position).getHeadImgUrl())){
            GlideUtils glideUtils=new GlideUtils();
            glideUtils.glides(context, listBeans.get(position).getHeadImgUrl(),holder.headIcon_comments);
        }else{
            holder.headIcon_comments.setImageResource(R.mipmap.mine_default_avatar);
        }
        //设置评论作者
        if(!TextUtils.isEmpty(listBeans.get(position).getNickname())) {
            holder.name_item.setText(""+listBeans.get(position).getNickname());
        }
        //设置评论内容
        if(!TextUtils.isEmpty(listBeans.get(position).getContent())) {
            holder.comments_content.setText(""+listBeans.get(position).getContent());
        }
        //设置创建时间
        if(!TextUtils.isEmpty(listBeans.get(position).getCreateTime()+"")) {
            holder.time.setText(""+listBeans.get(position).getCreateTime());
        }
        //设置点赞数量
        if(!TextUtils.isEmpty(listBeans.get(position).getPraiseNum()+"")){
            holder.dokiNumber.setText(listBeans.get(position).getPraiseNum()+"");
            SizeAll.add(position,listBeans.get(position).getPraiseNum());
        }

        LogPrint.printError("showTime："+SizeAll.get(position));

        listTag.add(position,listBeans.get(position).getPraiseStatus());
        //设置状态栏
        if(listBeans.get(position).getPraiseStatus()==1){
            holder.doki_comments_image.setImageResource(R.mipmap.comment_like_red_icon);
        }else{
            holder.doki_comments_image.setImageResource(R.mipmap.comment_like_black_icon);
        }

        //删除
        holder.click_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogPrint.printError("删除");
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                    context.startActivity(new Intent(context,LoginActivity.class));
                }else{
                    if(!TextUtils.isEmpty(listBeans.get(position).getUserId()+"")) {
                        LogPrint.printError("啦啦啦"+listBeans.get(position).getUserId()+"滴滴滴"+PrefManager.getInstance().getUserId());
                        if ((listBeans.get(position).getUserId() + "").equals(PrefManager.getInstance().getUserId()+"")){
                            SoftReference<DialogUtils> dialogSoftReference=new SoftReference<>(new DialogUtils());
                            if(dialogSoftReference.get()!=null){
                                dialogSoftReference.get().AlertDilog(context,  "温馨提示", "删除该评论？","确定", "取消", new alertCallBack() {
                                    @Override
                                    public void OnOk() {
                                        LogPrint.printError("确定");
                                        mockData.NoComments(listBeans.get(position).getCommentId(), PrefManager.getInstance().getToken(), new MockData.CallBackCommentsOk() {
                                            @Override
                                            public void ok() {
                                                callBackCommentsOk.ok(position);
                                            }
                                        });
                                    }
                                    @Override
                                    public void OnNo() {

                                    }
                                });
                            }else{
                                SoftReference<DialogUtils> dialogSoftReference1=new SoftReference<>(new DialogUtils());
                                dialogSoftReference1.get().AlertDilog(context, "删除该评论？", "温馨提示", "确定", "取消", new alertCallBack() {
                                    @Override
                                    public void OnOk() {
                                        LogPrint.printError("确定");
                                        mockData.NoComments(listBeans.get(position).getCommentId(), PrefManager.getInstance().getToken(), new MockData.CallBackCommentsOk() {
                                            @Override
                                            public void ok() {
                                                callBackCommentsOk.ok(position);
                                            }
                                        });
                                    }
                                    @Override
                                    public void OnNo() {

                                    }
                                });
                            }

                        }
                    }
                }
            }
        });
        //点赞
        holder.doki_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UtilsAll.isDoubleClick1()) {
                    if (TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                        context.startActivity(new Intent(context, LoginActivity.class));
                    } else {
                        if (listTag.get(position) == 1) {
                            //取消点赞
                            mockData.DokiComments(listBeans.get(position).getCommentId(), PrefManager.getInstance().getToken(), 0, new MockData.CallBackCommentsOk() {
                                @Override
                                public void ok() {
                                    //取消点赞成功
                                    listTag.set(position, 0);
                                    int txt = SizeAll.get(position);
                                    txt--;
                                    SizeAll.set(position, txt);
                                    if(txt<0){
                                        holder.dokiNumber.setText( "0");
                                    }else {
                                        holder.dokiNumber.setText(txt + "");
                                    }
                                    holder.doki_comments_image.setImageResource(R.mipmap.comment_like_black_icon);
                                }
                            });
                        } else {
                            //开始点赞
                            mockData.DokiComments(listBeans.get(position).getCommentId(), PrefManager.getInstance().getToken(), 1, new MockData.CallBackCommentsOk() {
                                @Override
                                public void ok() {
                                    listTag.set(position, 1);
                                    int txt = SizeAll.get(position);
                                    txt++;
                                    SizeAll.set(position, txt);
                                    if(txt<0){
                                        holder.dokiNumber.setText( "0");
                                    }else {
                                        holder.dokiNumber.setText(txt + "");
                                    }
                                    holder.doki_comments_image.setImageResource(R.mipmap.comment_like_red_icon);
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listBeans!=null){
            return listBeans.size();
        }
        return  0;
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public CircleImageView headIcon_comments;
        public TextView name_item,comments_content;
        public TextView time,dokiNumber;
        public LinearLayout  click_comments;
        public LinearLayout doki_click;
        public ImageView doki_comments_image;
        public CommentsViewHolder(View itemView) {
            super(itemView);
            headIcon_comments=itemView.findViewById(R.id.headIcon_comments);
            name_item=itemView.findViewById(R.id.name_item);
            comments_content=itemView.findViewById(R.id.comments_content);
            time=itemView.findViewById(R.id.time);
            click_comments=itemView.findViewById(R.id.click_comments);
            dokiNumber=itemView.findViewById(R.id.dokiNumber);
            doki_click=itemView.findViewById(R.id.doki_click);
            doki_comments_image=itemView.findViewById(R.id.doki_comments_image);
            click_comments.setOnClickListener(this);
            click_comments.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onclicKRecycleAdapter) {
                onclicKRecycleAdapter.onItemClick(getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (null != onclicKRecycleAdapter) {
                return onclicKRecycleAdapter.onItemLongClick(getPosition());
            }
            return false;
        }

    }
}
