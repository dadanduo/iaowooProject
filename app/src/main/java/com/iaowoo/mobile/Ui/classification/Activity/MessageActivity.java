package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.Utils.Glide.GlideImageLoader;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.DB.MessageNumber;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.View.BadgeFactory;
import com.iaowoo.mobile.Ui.classification.View.BadgeView;
import com.iaowoo.mobile.Utils.Glide.GlideImageLoader;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
 * @Description: ${todo}(消息界面)
 * @date 2018/9/18
 * @email ${18011009889@163.com}
 */
public class MessageActivity  extends  BaseBufferActivity{

    @BindView(R.id.button_backward)
    RelativeLayout button_backward;

    @BindView(R.id.text_title)
    TextView text_title;

    @BindView(R.id.g_g_number_message)
    RelativeLayout g_g_number_message;

    @BindView(R.id.x_t_number_message)
    RelativeLayout x_t_number_message;

    @BindView(R.id.j_y_number_message)
    RelativeLayout j_y_number_message;

    @BindView(R.id.d_d_number_message)
    RelativeLayout d_d_number_message;

    @BindView(R.id.k_f_number_message)
    RelativeLayout k_f_number_message;


    private BadgeView badgeView1=null,badgeView2=null,badgeView3=null,badgeView4=null,badgeView5=null;



    @Override
    public int getLayoutResId() {
        return R.layout.message_list;
    }

    @Override
    protected void initView() {
        super.initView();
        button_backward.setVisibility(View.VISIBLE);
        text_title.setText("消息通知");
        this.initState(R.id.ll_bar);
        badgeView1=messageDeal(g_g_number_message);
        badgeView2=messageDeal(x_t_number_message);
        badgeView3=messageDeal(j_y_number_message);
        badgeView4=messageDeal(d_d_number_message);
        badgeView5=messageDeal(k_f_number_message);
        messageNumber();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick({R.id.button_backward,R.id.g_g,R.id.x_t,R.id.j_y,R.id.d_d,R.id.k_f})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.button_backward:
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
                finish();
                break;
            case R.id.g_g:
                goUrl(ConfigH5Url.MeassgeUrl("50000","公告消息"));
                messageM(1);
                break;
            case R.id.x_t:
                goUrl( ConfigH5Url.MeassgeUrl("10000","系统消息"));
                messageM(2);
                break;
            case R.id.j_y:
                goUrl(ConfigH5Url.MeassgeUrl("20000","交易消息"));
                messageM(3);
                break;
            case R.id.d_d:
                goUrl(ConfigH5Url.MeassgeUrl("30000","订单消息"));
                messageM(4);
                break;
            case R.id.k_f:
                messageM(5);
                //客服
                if (TextUtils.isEmpty(PrefManager.getInstance().getHeadIcon())) {
                    ZApplication.getInstance().options("https://files.shbs008.com/images/headImg/defaultHeadImg.png");
                } else {
                    ZApplication.getInstance().options(PrefManager.getInstance().getHeadIcon());
                }
                // appKey七鱼客服
                Unicorn.init(this, ConfigAppkey.QIYU, ConfigFlag.ysfOptions, new GlideImageLoader(this));
                setSource("", PrefManager.getInstance().getMobile(), "", "", "V" + SingleOverAll.getInstance().getVersionName(ZApplication.getContext()) + "&&versionCode:" + SingleOverAll.getInstance().getVersionCode(ZApplication.getInstance().getApplicationContext()), "", "品牌：" + UtilsAll.getPhoneBrand() + "型号：" + UtilsAll.getPhoneModel(), "", "android操作系统");
                intoQiyu(this, Long.parseLong("1163250"));
                break;
        }

    }

    /**
     * EventBus消息处理方法。
     * @param eventBusMessageRefresh
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageRefresh eventBusMessageRefresh) {
        messageNumber();
    }

    private void goUrl(String url){
        Intent mintent =new Intent(MessageActivity.this, WebviewAcitvity.class);
        mintent.putExtra("webview_url",url);
        startActivityForResult(mintent,1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogPrint.printError("回来了");
        messageNumber();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    /**
     *处理首页消息总数
     */
    private void messageNumber(){
        List<MessageNumber> messageNumbers=XutilsDBManage.getInstance().loadTableAll(MessageNumber.class);
        if(messageNumbers!=null){
            if(messageNumbers.size()>0){
                for(int i=0;i<messageNumbers.size();i++){
                    if(messageNumbers.get(i).getUid().endsWith(PrefManager.getInstance().getInvite()==null?"":PrefManager.getInstance().getInvite())){
                        MessageNumber messageNumber=messageNumbers.get(i);
                        LogPrint.printError("uid:"+messageNumber.getUid());
                        LogPrint.printError("查到结果为"+messageNumber.getX_t()+">>>>>"+messageNumber.getG_g()+">>>>"+messageNumber.getD_d()+">>>>"+messageNumber.getJ_y()+">>>>>>"+messageNumber.getK_f());
                        if(messageNumber!=null) {
                            if (messageNumber.getG_g() != 0) {
                                badgeView1.setBadgeCount(messageNumber.getG_g()).bind(g_g_number_message);
                            } else {
                                badgeView1.unbind();
                            }
                            if (messageNumber.getX_t() != 0) {
                                badgeView2.setBadgeCount(messageNumber.getX_t()).bind(x_t_number_message);
                            } else {
                                badgeView2.setBadgeCount(0).bind(x_t_number_message).unbind();
                            }
                            if (messageNumber.getJ_y() != 0) {
                                badgeView3.setBadgeCount(messageNumber.getJ_y()).bind(j_y_number_message);
                            } else {
                                badgeView3.setBadgeCount(0).bind(j_y_number_message).unbind();
                            }
                            if (messageNumber.getD_d() != 0) {
                                badgeView4.setBadgeCount(messageNumber.getD_d()).bind(d_d_number_message);
                            } else {
                                badgeView4.setBadgeCount(0).bind(d_d_number_message).unbind();
                            }
                            if (messageNumber.getK_f() != 0) {
                                badgeView5.setBadgeCount(messageNumber.getK_f()).bind(k_f_number_message);
                            } else {
                                badgeView5.setBadgeCount(0).bind(k_f_number_message).unbind();
                            }
                        }
                    }
                }
            }
        }
    }

    private  void  messageM(int tag){
        //查询表里所有数据
        List<MessageNumber> messageNumbers=XutilsDBManage.getInstance().loadTableAll(MessageNumber.class);
        if(messageNumbers!=null) {
            for (int i = 0; i < messageNumbers.size();i++){
                LogPrint.printError("数据来"+messageNumbers.get(i).getUid());
                if(messageNumbers.get(i).getUid().endsWith(PrefManager.getInstance().getInvite()==null?"":PrefManager.getInstance().getInvite())){
                    MessageNumber messageNumber=messageNumbers.get(i);
                    if (messageNumber!= null) {
                        int j;
                        switch (tag){
                            case 1:
                                j= messageNumber.getG_g();
                                LogPrint.printError("公告的消息数" + j);
                                messageNumber.setG_g(0);
                                break;
                            case 2:
                                j= messageNumber.getX_t();
                                LogPrint.printError("系统的消息数" + j);
                                messageNumber.setX_t(0);
                                break;
                            case 3:
                                j= messageNumber.getJ_y();
                                LogPrint.printError("交易的消息数" + j);
                                messageNumber.setJ_y(0);
                                XutilsDBManage.getInstance().saveOrUpdate(messageNumber);
                                break;
                            case 4:
                                j= messageNumber.getD_d();
                                LogPrint.printError("订单的消息数" + j);
                                messageNumber.setD_d(0);
                                break;
                            case 5:
                                j= messageNumber.getK_f();
                                LogPrint.printError("订单的消息数" + j);
                                messageNumber.setK_f(0);
                                break;

                        }
                        XutilsDBManage.getInstance().saveOrUpdate(messageNumber);
                    }

                }
            }
        }
    }
    /**
     * 跳转到客服页面
     * @param context
     */
    public  void intoQiyu(Context context, long groupId){
        String title = "铺连铺客服";
        /**
         * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
         * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
         * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
         */
        String sourceUrl="https://pulpu.qiyukf.com";
        String sourceTitle="铺连铺";
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

    @Override
    protected void onStart() {
        super.onStart();
        LogPrint.printError("onstart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogPrint.printError("onresume");
        messageNumber();

    }

    @Override
    protected void onStop() {
        super.onStop();
        LogPrint.printError("onstop");
        BadgeFactory.createOvalBig(this).unbind();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        EventBus.getDefault().post(new EventBusMessageRefresh(0));
        return super.onKeyDown(keyCode, event);
    }

    private BadgeView messageDeal(View view){
        return  BadgeFactory.createOvalBig(this).bind(view);
    }
}
