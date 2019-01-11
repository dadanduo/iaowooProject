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

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageHomeTag;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.SettingActivity;
import com.iaowoo.mobile.Ui.classification.Activity.ShopActivity;
import com.iaowoo.mobile.Ui.classification.Activity.UseguideActivity;
import com.iaowoo.mobile.Ui.classification.Activity.VideoListActivity;
import com.iaowoo.mobile.Ui.classification.Model.Configtion;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideImageLoader;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;

/**
 * 首页一级分类adpter
 *
 * Created by chenda on 2018/4/3.
 */

public class ClassInfoRecycleAdapter extends BaseSoEasyAdapter{

    private Context context;

    private List<Configtion.BodyBean.ContentBean.ListBean> shops;

    private boolean home=true;

    private SoftReference<DialogUtils> dialogUtilsSoftReference;


    public ClassInfoRecycleAdapter(boolean home,List<Configtion.BodyBean.ContentBean.ListBean> shops, Context context, OnclicKRecycleAdapter onRecyclerViewListener) {
        this.shops = shops;
        this.context=context;
        this.onclicKRecycleAdapter=onRecyclerViewListener;
        this.home=home;
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(home){
            int  width = context.getResources().getDisplayMetrics().widthPixels / 5;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_info_adapter_home,parent,false);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.width = width;
            view.setLayoutParams(params);

        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_info_adapter_my,parent,false);
        }
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        //显示文字
        if(shops.get(position).getType()==2){
            holder.text_my.setVisibility(View.VISIBLE);
            holder.image_class.setVisibility(View.GONE);
            holder.text_my.setText(""+shops.get(position).getDescribes());
        }else{
            //显示图片icon
            holder.text_my.setVisibility(View.GONE);
            holder.image_class.setVisibility(View.VISIBLE);
        }

        if(shops.get(position).getStatus()==1){
            holder.ico_classi_info.setVisibility(View.VISIBLE);
        }else{
            holder.ico_classi_info.setVisibility(View.GONE);
        }
        //显示title
        holder.showText_class.setText(""+shops.get(position).getTitle());
        GlideUtils glideUtils=new GlideUtils();
        if(shops.get(position).getIcon()!=null) {
            glideUtils.glides(context, shops.get(position).getIcon(),holder.image_class);
        }
        holder.ico_classi_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //h5
                if(shops.get(position).getAndroidCurrent()==1){
                    //第三方连接直接跳带title的webview
                    if(!TextUtils.isEmpty(shops.get(position).getUrl())) {
                        if (!shops.get(position).getUrl().contains(ConfigH5Url.HTTP_H5)) {
                            Intent i = new Intent(context, UseguideActivity.class);
                            i.putExtra("title", shops.get(position).getTitle());
                            i.putExtra("url", shops.get(position).getUrl());
                            context.startActivity(i);
                        } else {
                            //铺铺连接走铺铺逻辑
                            HashMap<String, String> hashMap = UtilsAll.getUrlParameter(shops.get(position).getUrl());
                            // 需要去登录
                            if (!TextUtils.isEmpty(hashMap.get("needLogin"))) {
                                //需要去登录
                                if (hashMap.get("needLogin").endsWith("1")) {
                                    if (TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                                        if (dialogUtilsSoftReference.get() != null) {
                                            dialogUtilsSoftReference.get().LoginTo(context);
                                        } else {
                                            dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                                            dialogUtilsSoftReference.get().LoginTo(context);
                                        }
                                    } else {
                                        //已经登录
                                        realName(shops, hashMap, position,1);
                                    }
                                } else {
                                    //不需要登录
                                    realName(shops, hashMap, position,1);
                                }
                            } else {
                                //不需要登录
                                realName(shops, hashMap, position,1);
                            }
                        }
                    }
                }else if(shops.get(position).getAndroidCurrent()==2){//weex
                    LogPrint.printError("路径"+shops.get(position).getUrl());
                    if(!TextUtils.isEmpty(shops.get(position).getUrl())) {
                        //铺铺连接走铺铺逻辑
                        HashMap<String, String> hashMap = UtilsAll.getUrlParameter(shops.get(position).getUrl());
                        // 需要去登录
                        if (!TextUtils.isEmpty(hashMap.get("needLogin"))) {
                            //需要去登录
                            if (hashMap.get("needLogin").endsWith("1")) {
                                if (TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                                    if (dialogUtilsSoftReference.get() != null) {
                                        dialogUtilsSoftReference.get().LoginTo(context);
                                    } else {
                                        dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                                        dialogUtilsSoftReference.get().LoginTo(context);
                                    }
                                } else {
                                    //已经登录
                                    realName(shops, hashMap, position, 2);
                                }
                            } else {
                                //不需要登录
                                realName(shops, hashMap, position, 2);
                            }
                        } else {
                            //不需要登录
                            realName(shops, hashMap, position, 2);
                        }
                    }
                }else if(shops.get(position).getAndroidCurrent()==3){//android
                    //全球集市
                    if(shops.get(position).getUrl().endsWith("EearthFragment")){
                        EventBus.getDefault().post(new EventBusMessageHomeTag("2"));
                    }else if(shops.get(position).getUrl().endsWith("ChinaGoodsFragment")){
                        //中国好物
                        EventBus.getDefault().post(new EventBusMessageHomeTag("1"));
                    } else if(shops.get(position).getUrl().endsWith("VideoListActivity")){
                        //铺铺小视频
                        context.startActivity(new Intent(context,VideoListActivity.class));
                    }else if(shops.get(position).getUrl().endsWith("ShopAddActivity")){
                        //商家联盟
//                        context.startActivity(new Intent(context,ShopAddActivity.class));
                    }else if(shops.get(position).getUrl().endsWith("VideoFragment")){
                        //小視頻
                        EventBus.getDefault().post(new EventBusMessageHomeTag("3"));
                    } else if(shops.get(position).getUrl().endsWith("ShopAddFragment")){
                        //商家聯盟activity
//                        context.startActivity(new Intent(context,ShopAddActivity.class));
                    }else if(shops.get(position).getUrl().endsWith("intoQiyu")){
                        if(TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                            if(dialogUtilsSoftReference.get()!=null) {
                                dialogUtilsSoftReference.get().LoginTo(context);
                            }
                        }else{
                            //客服
                            if (TextUtils.isEmpty(PrefManager.getInstance().getHeadIcon())) {
                                ZApplication.getInstance().options("https://files.shbs008.com/images/headImg/defaultHeadImg.png");
                            } else {
                                ZApplication.getInstance().options(PrefManager.getInstance().getHeadIcon());
                            }
                            // appKey七鱼客服
                            Unicorn.init(context, ConfigAppkey.QIYU, ConfigFlag.ysfOptions, new GlideImageLoader(context));
                            setSource("", PrefManager.getInstance().getMobile(), "", "", "V" + SingleOverAll.getInstance().getVersionName(ZApplication.getInstance().getApplicationContext()) + "&&versionCode:" + SingleOverAll.getInstance().getVersionCode(ZApplication.getInstance().getApplicationContext()), "", "品牌：" + UtilsAll.getPhoneBrand() + "型号：" + UtilsAll.getPhoneModel(), "", "android操作系统");
                            intoQiyu(context, Long.parseLong("1163250"));
                        }
                    }else if(shops.get(position).getUrl().endsWith("ShopActivity")){
                        //商品页面
                        Intent mintent =new Intent(context, ShopActivity.class);
                        mintent.putExtra("title_name",shops.get(position).getTitle());
                        mintent.putExtra("categroy",shops.get(position).getParams());
                        context.startActivity(mintent);
                    }else if(shops.get(position).getUrl().endsWith("SettingActivity")){
                        //设置
                        Intent mintent =new Intent(context, SettingActivity.class);
                        context.startActivity(mintent);
                    }
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        if(shops!=null){
            return shops.size();
        }
        return  0;
    }

    class PersonViewHolder extends RecyclerView.ViewHolder{

        public ImageView image_class;
        public TextView showText_class;
        public LinearLayout ico_classi_info;
        public TextView text_my;
        public PersonViewHolder(View itemView) {
            super(itemView);
            image_class=itemView.findViewById(R.id.image_class);
            showText_class=itemView.findViewById(R.id.showText_class);
            ico_classi_info=itemView.findViewById(R.id.ico_classi_info);
            text_my=itemView.findViewById(R.id.text_my);
        }

    }

    /**
     * 跳转到客服页面
     * @param context
     */
    public  void intoQiyu(Context context,long groupId){
        String title = context.getResources().getString(R.string.app_name)+"客服";
        /**
         * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
         * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
         * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
         */
        String sourceUrl="https://pulpu.qiyukf.com";
        String sourceTitle=context.getResources().getString(R.string.app_name);
        String custom="custom information string";
        ConsultSource source = new ConsultSource(sourceUrl, sourceTitle, custom);
        source.groupId=groupId;
//        source.staffId=309480;
        /**
         * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
         * 如果返回为false，该接口不会有任何动作
         *
         * @param context 上下文
         * @param title   聊天窗口的标题
         * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
         */
        Unicorn.openServiceActivity(context, title, source);
    }

    /**
     * 设置来源
     */
    private void setSource(String real_name,String mobile_phone,String email,String gender,String app_version,String app_source,String device_info,String orderId,String device_sys){
        String json="[{\"key\":\"real_name\",\"value\":\"%s\"},{\"key\":\"mobile_phone\",\"value\":\"%s\"},{\"key\":\"email\",\"value\":\"%s\",\"hidden\":true},{\"index\":0,\"key\":\"gender\",\"label\":\"性别\",\"value\":\"%s\"},{\"index\":1,\"key\":\"app_version\",\"label\":\"版本\",\"value\":\"%s\"},{\"index\":2,\"key\":\"app_source\",\"label\":\"入口\",\"value\":\"%s\"},{\"index\":3,\"key\":\"device_info\",\"label\":\"机型\",\"value\":\"%s\"},{\"index\":3,\"key\":\"orderId\",\"label\":\"订单号\",\"value\":\"%s\"},{\"index\":4,\"key\":\"device_sys\",\"label\":\"系统\",\"value\":\"%s\"}]";
        String show=String.format(json,real_name,mobile_phone,email,gender,app_version,app_source,device_info,orderId,device_sys);
        YSFUserInfo userInfo = new YSFUserInfo();
        if(!TextUtils.isEmpty(PrefManager.getInstance().getInvite())){
            userInfo.userId = PrefManager.getInstance().getInvite();
            LogPrint.printError("sb"+PrefManager.getInstance().getInvite());
        }
        userInfo.data=show;
        Unicorn.setUserInfo(userInfo);
    }

    /**
     * 去实名判断
     */
    private void realName(List<Configtion.BodyBean.ContentBean.ListBean> shops,HashMap<String,String> hashMap,int position,int type){
        LogPrint.printError("Url点击"+shops.get(position).getUrl());
        if(!TextUtils.isEmpty(hashMap.get("isRealName"))) {
            //需要实名跳实名页面
            if (hashMap.get("isRealName").endsWith("1")) {
                //没有实名去实名
                if (PrefManager.getInstance().getIsTrueName().endsWith("0") || TextUtils.isEmpty(PrefManager.getInstance().getIsTrueName())) {
                    DialogUtils dialogUtils=new DialogUtils();
                    dialogUtils.AlertDilog(context, "温馨提示", "需要实名认证", "去认证", "再逛逛", new alertCallBack() {
                        @Override
                        public void OnOk() {
//                            UtilsAll.GoWebviewAll(context, ConfigH5Url.getUrl(ConfigH5Url.verified));
                            UtilsAll.GoWeexAll(context, ConfigH5Url.verifiedWeex(),"","");
                        }
                        @Override
                        public void OnNo() {
                        }
                    });
                }
                //实名直接跳转
                else {
                    if(type==1){
                        UtilsAll.GoWebviewAll(context, shops.get(position).getUrl());
                    }else{
                        UtilsAll.GoWeexAll(context, shops.get(position).getUrl(),"","");
                    }
                }
            } else {
                if(type==1){
                    UtilsAll.GoWebviewAll(context, shops.get(position).getUrl());
                }else{
                    UtilsAll.GoWeexAll(context, shops.get(position).getUrl(),"","");
                }            }
        } else{
            //直接跳转
            if(type==1){
                UtilsAll.GoWebviewAll(context, shops.get(position).getUrl());
            }else{
                UtilsAll.GoWeexAll(context, shops.get(position).getUrl(),"","");
            }
        }
    }
}
