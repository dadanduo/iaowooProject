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

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.DB.LoginInfo;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Model.Response;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.qiyukf.unicorn.api.Unicorn;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * 账号切换
 *
 * Created by chenda on 2018/4/3.
 */

public class SwtichLoginRecycleAdapter extends BaseSoEasyAdapter{

    private Context context;

    /**
     * 数据源
     */
    private List<LoginInfo> loginInfos;


    private SoftReference<DialogUtils> dialogUtilsSoftReference;

    private Show show;

    public interface Show{
        /**
         * 点击账号
         * @param name
         */
        void Name(String name);
    }


    public SwtichLoginRecycleAdapter( Context context, OnclicKRecycleAdapter onRecyclerViewListener,Show show) {
        this.context=context;
        this.show=show;
        this.onclicKRecycleAdapter=onRecyclerViewListener;
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
    }

    /**
     * 设置数据
     * @param loginInfos
     */
    public void setData(List<LoginInfo> loginInfos){
        this.loginInfos = loginInfos;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.switch_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        //显示账号
        holder.name_phone.setText(loginInfos.get(position).getName());
        //显示头像
        if(!TextUtils.isEmpty(loginInfos.get(position).getHeadImage())){
            SoftReference<GlideUtils> glideUtilsSoftReference=new SoftReference<>(new GlideUtils());
            if(glideUtilsSoftReference.get()!=null){
                glideUtilsSoftReference.get().glides(context,loginInfos.get(position).getHeadImage(),holder.head_image_switch);
            }else{
                SoftReference<GlideUtils> glideUtilsSoftReference1=new SoftReference<>(new GlideUtils());
                glideUtilsSoftReference1.get().glides(context,loginInfos.get(position).getHeadImage(),holder.head_image_switch);
            }
        }else{
            //若没有显示默认头像
            holder.head_image_switch.setImageResource(R.mipmap.mine_default_avatar);
        }

        //长按删除账号事件
        holder.click_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(dialogUtilsSoftReference.get()!=null){
                    dialogUtilsSoftReference.get().AlertDilog(context,"确认删除该账号？",loginInfos.get(position).getName(), "删除", "取消", new alertCallBack() {
                        @Override
                        public void OnOk() {
                            show.Name(loginInfos.get(position).getName());
                            if(UtilsAll.DeletLoginInfo(loginInfos.get(position).getId())){
                                onclicKRecycleAdapter.onItemLongClick(position);
                            }
                        }
                        @Override
                        public void OnNo() {
                        }
                    });
                }else{
                    dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                    dialogUtilsSoftReference.get().AlertDilog(context,"确认删除该账号？",loginInfos.get(position).getName(), "删除", "取消", new alertCallBack() {
                        @Override
                        public void OnOk() {
                            show.Name(loginInfos.get(position).getName());
                            if(UtilsAll.DeletLoginInfo(loginInfos.get(position).getId())){
                                onclicKRecycleAdapter.onItemLongClick(position);
                            }
                        }
                        @Override
                        public void OnNo() {
                        }
                    });
                }
                return true;
            }
        });

        //单击切换账号事件
        holder.click_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.ok_layout_img.getVisibility()==View.GONE){
                    if(!TextUtils.isEmpty(loginInfos.get(position).getPassWord())) {
                        logout(loginInfos.get(position).getName(), loginInfos.get(position).getPassWord());
                        onclicKRecycleAdapter.onItemClick(position);
                    }else{
                        ToastUtilsAll.getInstance().showShortToast("该账号为验证码登录请切换到密码登录再使用该功能！！！");
                    }
                }
            }
        });
        if(loginInfos.get(position).getName().equals(PrefManager.getInstance().getMobile())){
            holder.ok_layout_img.setVisibility(View.VISIBLE);
        }else{
            holder.ok_layout_img.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        if(loginInfos!=null){
            return loginInfos.size();
        }
        return  0;
    }

    /**
     *
     */
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView head_image_switch;
        public TextView name_phone;
        public LinearLayout click_item;
        public ImageView ok_layout_img;
        public ViewHolder(View itemView) {
            super(itemView);
            head_image_switch=itemView.findViewById(R.id.head_image_switch);
            name_phone=itemView.findViewById(R.id.name_phone);
            click_item=itemView.findViewById(R.id.click_item);
            ok_layout_img=itemView.findViewById(R.id.ok_layout_img);
        }
    }

    /**
     * @param mobileNo
     * @param password
     */
    private void  login(final String mobileNo, final String password){
        LogPrint.printError("账号"+mobileNo+"密码："+password);
        XutilsHttp.getInstance().postEncodedByPasswordLogin(new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                Response response= JSON.parseObject(json,Response.class);
                if (response.getCode().equals(OkhttpManager.SUCESS)) {
                    if(!TextUtils.isEmpty(response.getBody().getContent().getLoginToken())) {
                        //把token存起来
                        PrefManager.getInstance().setToken(response.getBody().getContent().getLoginToken());
                        if(!TextUtils.isEmpty(response.getBody().getContent().getInviteCode())) {
                            //把邀请码存起来
                            PrefManager.getInstance().setInvite(response.getBody().getContent().getInviteCode());
                        }
                        PrefManager.getInstance().setPassWord(password);
                        PrefManager.getInstance().saveMobile(mobileNo);
                        //把token和registerid给后台
                        if(TextUtils.isEmpty(ZApplication.REGISTERID)) {
                            LogPrint.printError("register还是为空"+ZApplication.REGISTERID);
                            ToastUtilsAll.getInstance().showShortToast("registerId为空");
                        }else {
                            sendRegister(ZApplication.REGISTERID, response.getBody().getContent().getLoginToken());
                            LogPrint.print("token："+response.getBody().getContent(),4);
                        }
                        LogPrint.print("token：" + response.getBody().getContent(), 4);
                        ToastUtilsAll.getInstance().showShortToast("登录成功");
                        //记住账户
                    }
                }else{
                    ToastUtilsAll.getInstance().showShortToast(response.getDescribe());
                }
            }
            @Override
            public void OnFaild(String faildM) {
                ToastUtilsAll.getInstance().showShortToast("网络出现问题了！");
            }
        },mobileNo,UtilsAll.encryptionPassword(mobileNo,password), XutilsHttp.CHANNEL, XutilsHttp.PRODUCT);
    }

    /**
     * @param registerid
     * @param token
     */
    public  void sendRegister(String registerid,String token)
    {
        XutilsHttp.getInstance().postRegisterIdSendService(new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                LogPrint.printError("register:"+json);
            }
            @Override
            public void OnFaild(String faildM) {
                LogPrint.printError("register:"+faildM);
            }
        },registerid,token);
    }

    /**
     * 登出
     * @param name
     * @param password
     */
    public void logout(final String name, final String password){
        XutilsHttp.getInstance().postEncodedLogout(new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                Response response = new Response();
                response = ZApplication.gson.fromJson(json, Response.class);
                Unicorn.logout();
                //把token设置为空
                PrefManager.getInstance().setToken("");
                Intent intent = new Intent();
                intent.putExtra(RdioBroadCast.SHOWTAG,"logout");
                intent.putExtra(RdioBroadCast.DATA,"");
                intent.setAction(RdioBroadCast.BOARD);
                //app改版后刷新小红点
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
                //登出成功后再登录
                login(name,password);

            }
            @Override
            public void OnFaild(String faildM) {
            }
        }, PrefManager.getInstance().getToken());
    }
}
