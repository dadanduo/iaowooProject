package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gui.ninegrideview.LGNineGrideView;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.CommentsModel;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 商品详情评价
 * Created by chenda on 2018/4/3.
 */

public class CommentsGoodRecycleAdapter extends RecyclerView.Adapter{

    private Context context;

    private GlideUtils glideUtils;

    private onclickItme onclickItme;

    private List<String> list;



    private List<CommentsModel.BodyBean.ContentBean.JudgesBean.ListBean> shops;

    public CommentsGoodRecycleAdapter(Context context, GlideUtils glideUtils) {
        this.context=context;
        this.glideUtils=glideUtils;
    }

    public interface onclickItme{
        void onClickNumber(String images,int position);
    }

    public void setData(List<CommentsModel.BodyBean.ContentBean.JudgesBean.ListBean> shops,onclickItme onclickItme){
          this.shops=shops;
          this.onclickItme=onclickItme;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_comments_item,parent,false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        //设置头像
        if (!TextUtils.isEmpty(shops.get(position).getHeadImgUrl())) {
            glideUtils.glides(context, shops.get(position).getHeadImgUrl(), holder.head_image_comments);
        } else {
            holder.head_image_comments.setImageResource(R.mipmap.mine_default_avatar);
        }
        //设置用户名字
        if (!TextUtils.isEmpty(shops.get(position).getNickname())) {
            holder.name_comment_item.setText("" + shops.get(position).getNickname());
        }
        //设置日期
        if (!TextUtils.isEmpty(shops.get(position).getUpdateTime())) {
            holder.date_text.setText("" + shops.get(position).getUpdateTime());
        }
        //设置评价描述
        if (!TextUtils.isEmpty(shops.get(position).getContent())) {
            holder.describe_comments.setText("" + shops.get(position).getContent());
        }
        //设置九宫格图片
        if (!TextUtils.isEmpty(shops.get(position).getImages())) {
            String imagelist = shops.get(position).getImages();
            String str2 = imagelist.replace(" ", "");
            list = Arrays.asList(str2.split(","));
            holder.grideView.setUrls(list);
        }
        StringBuffer stringBuffer=new StringBuffer();
        //设置规格
        if (!TextUtils.isEmpty(shops.get(position).getSku())) {
            try {
                JSONObject json = new JSONObject(shops.get(position).getSku());
                Iterator<String> it = json.keys();//使用迭代器
                while (it.hasNext()) {
                    String key = it.next();//获取key
                    String values = json.getString(key);
                    stringBuffer.append(key+":"+values+"   ");

                    LogPrint.printError("key-value" + "key=" + key + " value=" + values);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        holder.name_describe.setText(""+stringBuffer);
        holder.grideView.setOnItemClickListener(new LGNineGrideView.OnItemClickListener() {
            @Override
            public void onClickItem(int position1, View view) {
                onclickItme.onClickNumber(shops.get(position).getImages(),position1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(shops!=null){
            LogPrint.printError("总数据"+shops.size());
            return shops.size();
        }
        return  0;
    }

    class PersonViewHolder extends RecyclerView.ViewHolder  {
        public CircleImageView head_image_comments;
        public TextView name_comment_item,date_text;
        public TextView describe_comments,name_describe;
        public LGNineGrideView grideView;
        public PersonViewHolder(View itemView) {
            super(itemView);
            head_image_comments=itemView.findViewById(R.id.head_image_comments);
            name_comment_item=itemView.findViewById(R.id.name_comment_item);
            date_text=itemView.findViewById(R.id.date_text);
            describe_comments=itemView.findViewById(R.id.describe_comments);
            grideView=itemView.findViewById(R.id.grideView);
            name_describe=itemView.findViewById(R.id.name_describe);
        }
    }
}
