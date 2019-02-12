package com.iaowoo.mobile.Ui.classification.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.DB.LoginInfo;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.LoginActivity;
import com.iaowoo.mobile.Ui.classification.Activity.MyTwoCodeActivity;
import com.iaowoo.mobile.Ui.classification.Activity.SettingActivity;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.Configtion;
import com.iaowoo.mobile.Ui.classification.Model.MYMESSAGE;
import com.iaowoo.mobile.Ui.classification.Model.OrderNumber;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Model.YUE_JIFEN_CAIHONGBAO;
import com.iaowoo.mobile.Ui.classification.Presenter.MyFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.BadgeFactory;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenda on 2018/4/3.
 * 我的adapter
 */

public class MyRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    //item的高度
    private List<Integer> mHeights;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mHeaderCount=1;//头部View个数
    GlideUtils glideUtils;
    private MyFragmentPresenter myFragmentProsenter;
    private SoftReference<DialogUtils> dialogUtilsSoftReference;


    private int isCityProxy;

    private int isProvinceProxy;

    private int isTrueName=0;
    /**
     * 个人信息对象
     */
    public MYMESSAGE mymessageThis;
    /**
     * 轮播数据
     */
    List<String> LurC = null;
    List<Shop.BodyBean.ContentBean.ListBean> shops;

    private Banner.BodyBean.ContentBean.ListBean  zq1_banner;

    public MyRecycleAdapter(Context context,MyFragmentPresenter myFragmentProsenter){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        glideUtils=new GlideUtils();
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
        this.myFragmentProsenter=myFragmentProsenter;
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
            return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.my_f_recycler, parent, false));
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
            ((ContentViewHolder) holder).home_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    UtilsAll.GoNativeGoodsDetails(mContext,shops.get(position-1).getTemplateId()+"");
                    UtilsAll.GoWebviewAll(mContext, ConfigH5Url.setGoodsDetails(shops.get(position-1).getType(),shops.get(position-1).getTemplateId()));
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
        private CircleImageView image_home;
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
    public  class HeaderViewHolder extends RecyclerView.ViewHolder implements MyFragmentPresenter.MyAdapterCallBack {
        private LinearLayout Dynamic_add_layout;
        private ImageView headIcon;
        private RelativeLayout no_login;
        private LinearLayout login_ok;
        private TextView name,ji_fen,pu_tong,yu_e_money,jifen,cai_hong_bao;
        private LinearLayout click_my,s_m;
        private  ImageView go_q_d,sheng_fen;
        private RelativeLayout d_f_k,d_f_x,d_f_h,d_s_h,d_p_j;
        private LinearLayout d_f_k_m,d_f_x_m,d_f_h_m,d_s_h_m,d_p_j_m,my_show_hi;
        private RelativeLayout setting,all_order,shi_ming_show;
        private ImageView goods_station_my,er_wei_ma_shao_miao;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            myFragmentProsenter.setMyCallBack(this);
            headIcon=itemView.findViewById(R.id.headIcon);
            Dynamic_add_layout=itemView.findViewById(R.id.Dynamic_add_layout);
            no_login=itemView.findViewById(R.id.no_login);
            login_ok=itemView.findViewById(R.id.login_ok);
            name=itemView.findViewById(R.id.name);
            ji_fen=itemView.findViewById(R.id.ji_fen);
            pu_tong=itemView.findViewById(R.id.pu_tong);
            s_m=itemView.findViewById(R.id.s_m);
            click_my=itemView.findViewById(R.id.click_my);
            setting=itemView.findViewById(R.id.setting);
            go_q_d=itemView.findViewById(R.id.go_q_d);
            d_f_k=itemView.findViewById(R.id.d_f_k);
            d_f_x=itemView.findViewById(R.id.d_f_x);
            d_f_h=itemView.findViewById(R.id.d_f_h);
            d_s_h=itemView.findViewById(R.id.d_s_h);
            d_p_j=itemView.findViewById(R.id.d_p_j);
            d_f_k_m=itemView.findViewById(R.id.d_f_k_m);
            d_f_x_m=itemView.findViewById(R.id.d_f_x_m);
            d_f_h_m=itemView.findViewById(R.id.d_f_h_m);
            d_s_h_m=itemView.findViewById(R.id.d_s_h_m);
            d_p_j_m=itemView.findViewById(R.id.d_p_j_m);
            all_order=itemView.findViewById(R.id.all_order);
            sheng_fen=itemView.findViewById(R.id.sheng_fen);
            my_show_hi=itemView.findViewById(R.id.my_show_hi);
            yu_e_money=itemView.findViewById(R.id.yu_e_money);
            cai_hong_bao=itemView.findViewById(R.id.cai_hong_bao);
            jifen=itemView.findViewById(R.id.jifen);
            er_wei_ma_shao_miao=itemView.findViewById(R.id.er_wei_ma_shao_miao);
            shi_ming_show=itemView.findViewById(R.id.shi_ming_show);
            goods_station_my=itemView.findViewById(R.id.goods_station_my);

            //跳转到二维码页面
            er_wei_ma_shao_miao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mintent=new Intent(mContext,MyTwoCodeActivity.class);
                    mintent.putExtra("myMsg",mymessageThis);
                    mContext.startActivity(mintent);
                }
            });

            headIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
//                        SingleOverAll.getInstance().LoginTo(mContext);
                        mContext.startActivity(new Intent(mContext, LoginActivity.class));

                    }else{
//                        UtilsAll.GoWebviewAll(mContext,ConfigH5Url.getUrl(ConfigH5Url.information));
                        UtilsAll.GoWeexAll(mContext,ConfigH5Url.MsgMy(),"","");
                    }
                }
            });
            no_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
//                        SingleOverAll.getInstance().LoginTo(mContext);
                        mContext.startActivity(new Intent(mContext, LoginActivity.class));

                    }else{
//                        UtilsAll.GoWebviewAll(mContext,ConfigH5Url.getUrl(ConfigH5Url.information));
                        UtilsAll.GoWeexAll(mContext,ConfigH5Url.MsgMy(),"","");
                    }
                }
            });



            //去实名认证
            s_m.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UtilsAll.GoWebviewAll(mContext,ConfigH5Url.getUrl(ConfigH5Url.verified));
                }
            });
            //签到领积分
            go_q_d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                        if(dialogUtilsSoftReference.get()!=null) {
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }else{
                            dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }
                    }else{
                        if(PrefManager.getInstance().getIsTrueName().endsWith("0")) {
                            DialogUtils dialogUtils=new DialogUtils();
                            dialogUtils.AlertDilog(mContext, "温馨提示", "需要实名认证", "去认证", "再逛逛", new alertCallBack() {
                                @Override
                                public void OnOk() {
                                    UtilsAll.GoWebviewAll(mContext, ConfigH5Url.getUrl(ConfigH5Url.verified));
                                }

                                @Override
                                public void OnNo() {
                                }
                            });
                        }else{
                            UtilsAll.GoWebviewAll(mContext,ConfigH5Url.getUrl(ConfigH5Url.signin));
                        }
                    }
                }
            });
            //全部订单
            all_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                        if(dialogUtilsSoftReference.get()!=null) {
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }else{
                            dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }
                    }else{
                        //全部订单跳转到weex
                        UtilsAll.GoWeexAll(mContext,ConfigH5Url.ORDER_ER_INDEX+"?type=0","","");
//                        UtilsAll.GoWebviewAll(mContext,ConfigH5Url.order(0));
                    }
                }
            });
            myFragmentProsenter.getSlideShowData();
            myFragmentProsenter.getConfiguration();
            //token不为空查询个人余额、积分和彩虹宝接口
            if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                myFragmentProsenter.getQuery_data(PrefManager.getInstance().getToken());
            }
            //没有登录状态
            if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                no_login.setVisibility(View.VISIBLE);
                login_ok.setVisibility(View.GONE);
                headIcon.setImageResource(R.mipmap.mine_default_avatar);
            }else{
                no_login.setVisibility(View.GONE);
                login_ok.setVisibility(View.VISIBLE);
                myFragmentProsenter.getPERSON(PrefManager.getInstance().getToken());
                myFragmentProsenter.getDisplayOrderNum(PrefManager.getInstance().getToken());
            }
            //设置
            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, SettingActivity.class));
                }
            });
            goods_station_my.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SingleOverAll.getInstance().bannerClick(mContext,zq1_banner);
                }
            });
            //待付款
            d_f_k.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                        if(dialogUtilsSoftReference.get()!=null) {
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }else{
                            dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }
                    }else{
                        UtilsAll.GoWeexAll(mContext,ConfigH5Url.ORDER_ER_INDEX+"?type=2","","");
//                        UtilsAll.GoWebviewAll(mContext,ConfigH5Url.order(2));

                    }
                }
            });
            //待分享
            d_f_x.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                        if(dialogUtilsSoftReference.get()!=null) {
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }else{
                            dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }
                    }else{
                        UtilsAll.GoWeexAll(mContext,ConfigH5Url.ORDER_ER_INDEX+"?type=1","","");
//                        UtilsAll.GoWebviewAll(mContext,ConfigH5Url.order(1));
                    }
                }
            });
            //待发货
            d_f_h.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                        if(dialogUtilsSoftReference.get()!=null) {
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }else{
                            dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }
                    }else{
//                        UtilsAll.GoWebviewAll(mContext,ConfigH5Url.order(3));
                        UtilsAll.GoWeexAll(mContext,ConfigH5Url.ORDER_ER_INDEX+"?type=3","","");
                    }
                }
            });
            //待收货
            d_s_h.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                        if(dialogUtilsSoftReference.get()!=null) {
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }else{
                            dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }
                    }else{
//                        UtilsAll.GoWebviewAll(mContext,ConfigH5Url.order(4));
                        UtilsAll.GoWeexAll(mContext,ConfigH5Url.ORDER_ER_INDEX+"?type=4","","");
                    }
                }
            });
            //待评价
            d_p_j.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                        if(dialogUtilsSoftReference.get()!=null) {
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }else{
                            dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }
                    }else{
//                        UtilsAll.GoWebviewAll(mContext,ConfigH5Url.order(5));
                        UtilsAll.GoWeexAll(mContext,ConfigH5Url.ORDER_ER_INDEX+"?type=5","","");
                    }
                }
            });


        }
        @Override
        public void getConfigtion(Configtion configtion) {
            int size=configtion.getBody().getContent().getList().size();
            List<Configtion.BodyBean.ContentBean.ListBean> list1=new ArrayList<>();
            List<Configtion.BodyBean.ContentBean.ListBean> list2=new ArrayList<>();
            List<Configtion.BodyBean.ContentBean.ListBean> list3=new ArrayList<>();
            for(int i=0;i<size;i++){
                //第一列加入動態加入button
                if(configtion.getBody().getContent().getList().get(i).getLocation()==3){
                    if(configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==1||configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==2||configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==3) {
                        list1.add(configtion.getBody().getContent().getList().get(i));
                    }
                }else if(configtion.getBody().getContent().getList().get(i).getLocation()==4){
                    //第二列加入動態加入button
                    if(configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==1||configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==2||configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==3) {
                        list2.add(configtion.getBody().getContent().getList().get(i));
                    }
                }else if(configtion.getBody().getContent().getList().get(i).getLocation()==5){
                    if(configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==1||configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==2||configtion.getBody().getContent().getList().get(i).getAndroidCurrent()==3) {
                        if (configtion.getBody().getContent().getList().get(i).getUrl().contains("m2.shbs008.com/#/proxyProfit?type=city")) {
                            if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                                if (isCityProxy == 1) {
                                    list3.add(configtion.getBody().getContent().getList().get(i));
                                }
                            }
                        } else if (configtion.getBody().getContent().getList().get(i).getUrl().contains("m2.shbs008.com/#/supplier")) {
                            if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                                if (isProvinceProxy == 1) {
                                    list3.add(configtion.getBody().getContent().getList().get(i));
                                }
                            }
                        } else if (configtion.getBody().getContent().getList().get(i).getUrl().contains("m2.shbs008.com/#/proxyProfit?from=App")) {
                            if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                                if (isProvinceProxy == 1) {
                                    list3.add(configtion.getBody().getContent().getList().get(i));
                                }
                            }
                        } else {
                            list3.add(configtion.getBody().getContent().getList().get(i));
                        }

                    }
                }
            }

            if(list1.size()>0) {
                Dynamic_add_layout.addView(addViewShow(list1));
            }
            if(list2.size()>0){
                Dynamic_add_layout.addView(addViewShow(list2));
            }
            if(list3.size()>0){
                Dynamic_add_layout.addView(addViewShow(list3));
            }
        }
        @Override
        public void Mymessage(MYMESSAGE mymessage) {
            mymessageThis=mymessage;
            //保存用户是否被人邀请过了
            PrefManager.getInstance().setUserYaoQing(mymessage.getBody().getContent().getInviteStatus());
            //保存用戶的id
            PrefManager.getInstance().setUserId(mymessage.getBody().getContent().getUserId()+"");
            //保存用戶的支付密碼
            PrefManager.getInstance().setPayPassword(mymessage.getBody().getContent().getPayPassword()+"");
            //保存用戶信息
            PrefManager.getInstance().setUserMsg(ZApplication.gson.toJson(mymessage.getBody().getContent()));
            //保存头像
            PrefManager.getInstance().saveHeadIcon(mymessage.getBody().getContent().getHeadImgUrl());
            if(mymessage.getBody().getContent().getAuthentication()==1){
                s_m.setVisibility(View.GONE);
                PrefManager.getInstance().saveIsTrueName("1");
                shi_ming_show.setVisibility(View.GONE);
            }else{
                s_m.setVisibility(View.VISIBLE);
                shi_ming_show.setVisibility(View.VISIBLE);
                PrefManager.getInstance().saveIsTrueName("0");
            }
            if(!TextUtils.isEmpty(mymessage.getBody().getContent().getNickname())){
                name.setText(""+mymessage.getBody().getContent().getNickname());
            }else{
                if(!TextUtils.isEmpty(mymessage.getBody().getContent().getName())){
                    name.setText(""+mymessage.getBody().getContent().getName());
                }else{
                    if(!TextUtils.isEmpty(mymessage.getBody().getContent().getMobileNo())){
                        name.setText(""+mymessage.getBody().getContent().getMobileNo());
                    }
                }
            }
            if(!TextUtils.isEmpty(mymessage.getBody().getContent().getCheckScore()+"")){
                ji_fen.setText("积分："+mymessage.getBody().getContent().getCheckScore());
            }
            //0.游客，1.会员，2.摊主，3.过期摊主，4.农场主，5.过期农场主
            if(!TextUtils.isEmpty(mymessage.getBody().getContent().getMemberCode())){
                switch (mymessage.getBody().getContent().getMemberCode()) {
                    case "0":
                        sheng_fen.setImageResource(R.mipmap.mine_level_tourists_icon);
                        break;
                    case "1":
                        sheng_fen.setImageResource(R.mipmap.mine_level_members_icon);
                        break;
                    case "2":
                        sheng_fen.setImageResource(R.mipmap.mine_level_farmer_icon);
                        break;
                    case "3":
                        sheng_fen.setImageResource(R.mipmap.mine_level_farmer_overdue_icon);
                        break;
                    case "4":
                        sheng_fen.setImageResource(R.mipmap.mine_level_ranchers_icon);
                        break;
                    case "5":
                        sheng_fen.setImageResource(R.mipmap.mine_level_ranchers_overdue_icon);
                        break;
                    default:
                        break;
                }

            }

            if(!TextUtils.isEmpty(mymessage.getBody().getContent().getMemberName())) {
                switch (mymessage.getBody().getContent().getMemberName()) {
                    case "1":
                        pu_tong.setText("普通会员");
                        break;
                    case "2":
                        pu_tong.setText("实名会员");
                        break;
                    case "3":
                        pu_tong.setText("会员");
                        break;
                    case "4":
                        pu_tong.setText("店主");
                        break;
                    case "5":
                        pu_tong.setText("仓主");
                        break;
                    default:
                        pu_tong.setText("普通会员");
                        break;
                }
            }
            //账号不为空
            if(!TextUtils.isEmpty(PrefManager.getInstance().getMobile())) {
                LoginInfo loginInfo = (LoginInfo) XutilsDBManage.getInstance().searchName(PrefManager.getInstance().getMobile(), LoginInfo.class);
                if(loginInfo!=null){
                    LoginInfo d=new LoginInfo();
                    d.setName(loginInfo.getName());
                    d.setPassWord(loginInfo.getPassWord());
                    d.setId(loginInfo.getId());
                    //更换头像
                    if(!TextUtils.isEmpty(mymessage.getBody().getContent().getHeadImgUrl())){
                        d.setHeadImage(mymessage.getBody().getContent().getHeadImgUrl());
                    }
                    d.setGroudId(loginInfo.getGroudId());
                    if(XutilsDBManage.getInstance().saveOrUpdate(d)){
                    }
                }
            }
            if(TextUtils.isEmpty(mymessage.getBody().getContent().getHeadImgUrl())){
                headIcon.setImageResource(R.mipmap.mine_default_avatar);
            }else{
                glideUtils.glideRound(mContext,mymessage.getBody().getContent().getHeadImgUrl()+"",headIcon);
            }
            isTrueName=mymessage.getBody().getContent().getAuthentication();

            isCityProxy=mymessage.getBody().getContent().getIsCityProxy();
            isProvinceProxy=mymessage.getBody().getContent().getIsProvinceProxy();
        }

        @Override
        public void OrderNumber(OrderNumber orderNumber) {

            //待付款
            if(!TextUtils.isEmpty(orderNumber.getBody().getContent().getWaitPayOrdersNum()+"")){
                if(orderNumber.getBody().getContent().getWaitPayOrdersNum()!=0){
                    if(orderNumber.getBody().getContent().getWaitPayOrdersNum()>100){
                        BadgeFactory.createOvalMy(mContext).setBadgeCount("99+").bind(d_f_k_m);
                    }else{
                        BadgeFactory.createOvalMy(mContext).setBadgeCount(orderNumber.getBody().getContent().getWaitPayOrdersNum()).bind(d_f_k_m);
                    }
                }
            }
            if(!TextUtils.isEmpty(orderNumber.getBody().getContent().getAlreadyAcquiredNum()+"")){
                //待分享
                if(orderNumber.getBody().getContent().getAlreadyAcquiredNum()!=0){
                    if(orderNumber.getBody().getContent().getAlreadyAcquiredNum()>100){
                        BadgeFactory.createOvalMy(mContext).setBadgeCount("99+").bind(d_f_x_m);
                    }else{
                        BadgeFactory.createOvalMy(mContext).setBadgeCount(orderNumber.getBody().getContent().getAlreadyAcquiredNum()).bind(d_f_x_m);
                    }
                }
            }
            if(!TextUtils.isEmpty(orderNumber.getBody().getContent().getToBeSendedNum()+"")){
                //待发货
                if(orderNumber.getBody().getContent().getToBeSendedNum()!=0){
                    if(orderNumber.getBody().getContent().getToBeSendedNum()>100){
                        BadgeFactory.createOvalMy(mContext).setBadgeCount("99+").bind(d_f_h_m);
                    }else{
                        BadgeFactory.createOvalMy(mContext).setBadgeCount(orderNumber.getBody().getContent().getToBeSendedNum()).bind(d_f_h_m);
                    }
                }
            }
            if(!TextUtils.isEmpty(orderNumber.getBody().getContent().getToBeReceivedNum()+"")){
                //待收货
                if(orderNumber.getBody().getContent().getToBeReceivedNum()!=0){
                    if(orderNumber.getBody().getContent().getToBeReceivedNum()>100){
                        BadgeFactory.createOvalMy(mContext).setBadgeCount("99+").bind(d_s_h_m);
                    }else{
                        BadgeFactory.createOvalMy(mContext).setBadgeCount(orderNumber.getBody().getContent().getToBeReceivedNum()).bind(d_s_h_m);
                    }
                }
            }
            if(!TextUtils.isEmpty(orderNumber.getBody().getContent().getToBeJudged()+"")){
                //待评价
                if(orderNumber.getBody().getContent().getToBeJudged()!=0){
                    if(orderNumber.getBody().getContent().getToBeJudged()>100){
                        BadgeFactory.createOvalMy(mContext).setBadgeCount("99+").bind(d_p_j_m);
                    }else{
                        BadgeFactory.createOvalMy(mContext).setBadgeCount(orderNumber.getBody().getContent().getToBeJudged()).bind(d_p_j_m);
                    }
                }
            }

        }

        @Override
        public void getSlideShow(Banner banner) {
            int size = banner.getBody().getContent().getList().size();
            if (size != 0) {
                for (int i = 0; i < size; i++) {
                    if(!TextUtils.isEmpty(banner.getBody().getContent().getList().get(i).getBannerImg())) {
                        if (banner.getBody().getContent().getList().get(i).getLocation()==21) {
                            glideUtils.glides(mContext,banner.getBody().getContent().getList().get(i).getBannerImg(),goods_station_my);
                            zq1_banner=banner.getBody().getContent().getList().get(i);
                        }
                    }

                }
            }
        }

        @Override
        public void query_data(YUE_JIFEN_CAIHONGBAO.BodyBean.ContentBean contentBean) {
            if(contentBean!=null){
                my_show_hi.setVisibility(View.VISIBLE);

                double yue=contentBean.getBalance();
                if(yue>10000){
                    double yue1=(yue/10000);
                    yu_e_money.setText(UtilsAll.calculateProfit(yue1)+"w");
                }else{
                    yu_e_money.setText(UtilsAll.calculateProfit(yue));
                }

                jifen.setText(contentBean.getIntegral()+"");
                cai_hong_bao.setText(""+contentBean.getRainbow());
            }
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
     * 动态加载item
     * @param list
     * @return
     */
    private View addViewShow( List<Configtion.BodyBean.ContentBean.ListBean> list) {
        // TODO 动态添加布局(xml方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater= LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.my_item_layout, null);
        RecyclerView recyclerView=view.findViewById(R.id.my_layout_recycler);
        GridLayoutManager manager = new GridLayoutManager(mContext,4, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        ClassInfoRecycleAdapter classInfoRecycleAdapter=new ClassInfoRecycleAdapter(false,list, mContext, new OnclicKRecycleAdapter() {
            @Override
            public void onItemClick(int position) {

            }
            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
        recyclerView.setAdapter(classInfoRecycleAdapter);

        return view;
    }

}
