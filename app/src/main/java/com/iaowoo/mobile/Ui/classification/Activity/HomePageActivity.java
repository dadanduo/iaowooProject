package com.iaowoo.mobile.Ui.classification.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageHomeTag;
import com.iaowoo.mobile.EvenBus.EventBusMessageRedPick;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
import com.iaowoo.mobile.J_push.J_edit;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.im.ContactsActivity;
import com.iaowoo.mobile.Ui.classification.Fragment.ClassificationFragment;
import com.iaowoo.mobile.Ui.classification.Fragment.MyFragment;
import com.iaowoo.mobile.Ui.classification.Fragment.VideoFragment;
import com.iaowoo.mobile.Ui.classification.Fragment.ViewPagerFragment;
import com.iaowoo.mobile.Ui.classification.Fragment.messageFragment;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.MsgModel;
import com.iaowoo.mobile.Ui.classification.Model.StartPage;
import com.iaowoo.mobile.Ui.classification.Model.VERSION;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.Presenter.MesssageProsenter;
import com.iaowoo.mobile.Ui.classification.View.BadgeFactory;
import com.iaowoo.mobile.Ui.classification.View.BadgeView;
import com.iaowoo.mobile.Ui.classification.View.im.MorePopWindow;
import com.iaowoo.mobile.Utils.BadgeHelper;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.DownLoad.DownLoaderTask;
import com.iaowoo.mobile.Utils.DownLoad.ZipExtractorTask;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.Tools;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.UtilsTimer;
import com.iaowoo.mobile.Utils.XutilsHttp;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.im.RongIMUtils;
import com.iaowoo.mobile.interfaceCallback.OkhttpCallBack;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnreadCountChangeListener;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.io.File;
import java.lang.ref.SoftReference;

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
 * @Description: ${todo}(主页)
 * @date 2018/8/27
 * @email ${18011009889@163.com}
 */
public class HomePageActivity extends BaseBufferActivity  implements HomeFragmentPresenter.HomeMainCallBack,MesssageProsenter.messageNumberCallBack{

    @BindView(R.id.home_page_img)
    ImageView home_page_img;
    @BindView(R.id.integrate_purchase_img)
    ImageView integrate_purchase_img;
    @BindView(R.id.earth_inone_img)
    ImageView earth_inone_img;
    @BindView(R.id.show_add_img)
    ImageView show_add_img;
    @BindView(R.id.my_img)
    ImageView my_img;
    @BindView(R.id.home_page_text)
    TextView home_page_text;
    @BindView(R.id.integrate_purchase_text)
    TextView integrate_purchase_text;
    @BindView(R.id.earth_inone_text)
    TextView earth_inone_text;
    @BindView(R.id.show_add_text)
    TextView show_add_text;
    @BindView(R.id.my_text)
    TextView my_text;
    @BindView(R.id.goon)
    Button goon;
    //购物车
    @BindView(R.id.shopNumber)
    RelativeLayout shopNumber;

    //活动福袋布局
    @BindView(R.id.activity_image)
    RelativeLayout activity_image;

    //福袋
    @BindView(R.id.f_dai)
    ImageView f_dai;

    @BindView(R.id.shopingCar)
    ImageView shopingCar;

    //启动页面
    @BindView(R.id.start_page)
    FrameLayout start_page;

    @BindView(R.id.image_start)
    ImageView image_start;

    @BindView(R.id.main_fragment)
    FrameLayout main_fragment;

    Fragment currentFragment;


    @BindView(R.id.rl_title_layout)
    RelativeLayout rlTitleLayout;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.rl_contact)
    RelativeLayout rlContact;
    @BindView(R.id.rl_more)
    RelativeLayout rlMore;

    /**
     * 倒计时器
     */
    private UtilsTimer utilsTimer;


    private BadgeView badgeView=null;

    private  StartPage startPage;


    /**
     * 购物车是否显示   2:福袋和购物车都不显示
     */
    private int tagThis=0;

    /**
     * 启动时间
     */
    private int TIME=5;


    /**
     * prosenter
     */
    private HomeFragmentPresenter homeFragmentProsenter=null;
    /**
     * 福袋数据
     */
    private Banner.BodyBean.ContentBean.ListBean beanThis;

    /**
     * 启动数据
     */
    private Banner.BodyBean.ContentBean.ListBean startPageImage;

    private SoftReference<DialogUtils> dialogUtilsSoftReference;

    private MesssageProsenter messsageProsenter;

    BadgeHelper badgeHelper;

    @Override
    public int getLayoutResId() {
        return R.layout.main_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        this.allState();
        //注册evenbus
        EventBus.getDefault().register(this);
        // 融云IM连接
//        RongIMUtils.connect(this);
    }

    // 退出界面时，必须撤销，以免造成资源泄露
    final UnreadCountChangeListener listener = new UnreadCountChangeListener() { // 声明一个成员变量
        @Override
        public void onUnreadCountChange(int count) {
            // 在此更新界面, count 为当前未读数，
            // 也可以用 Unicorn.getUnreadCount() 获取总的未读数
            LogPrint.printError("没有读的消息"+count);
            J_edit j_edit=new J_edit();
            j_edit.messageM(5);
        }
    };

    private void addUnreadCountChangeListener(boolean add) {
        Unicorn.addUnreadCountChangeListener(listener, add);
    }
    @Override
    protected void initData() {
        super.initData();
        rlTitleLayout.setVisibility(View.GONE);
        //默认第一个首页
        defaultOne();
        this.adaptive();
        this.requestPermission();
        initialis();
        String msg=PrefManager.getInstance().getJGMsm();
        LogPrint.printError(msg);
//        PrefManager.getInstance().saveJGMsm("");
        if (!TextUtils.isEmpty(msg)&&msg.length()>30) {
            LogPrint.printError("有极光消息需要处理");
            J_edit j_edit=new J_edit();
            j_edit.setCostomMsg1(msg,this);
        }
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());

        homeFragmentProsenter=new HomeFragmentPresenter(this);
        homeFragmentProsenter.setHomeMainCallBack(this);
        homeFragmentProsenter.getStartPage();
        homeFragmentProsenter.getQureryIntegralRatio();
        //获取购物车
        homeFragmentProsenter.getShopCar(PrefManager.getInstance().getToken()==null?"":PrefManager.getInstance().getToken());
        replaceFragment(0);

        messsageProsenter=new MesssageProsenter();
        messsageProsenter.setCallBack(this);

        //实例化小红点
        badgeHelper = new BadgeHelper(this)
                .setBadgeType(BadgeHelper.Type.TYPE_POINT)
                .setBadgeSize(100,100)
                .setBadgeOverlap(false);
        badgeHelper.bindToTargetView(earth_inone_img);
        //用户登录情况下
        if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())){
            messsageProsenter.Get_message_number();
        }

//        List<City> personList=new ArrayList<>();
//        int testMaxCount=2;//测试的最大数据条数
//        //添加测试数据
//        for(int i=0;i<testMaxCount;i++){
//            City city=new City();
//            city.setName("唐人街");
//            city.setPinyi("tangrenjie");
//            personList.add(city);
//        }
        //FastJson生成json数据

//        String jsonData= ZApplication.gson.toJson(personList);
//        LogPrint.printError("加密前"+jsonData);
//        LogPrint.printError("加密前长度"+jsonData.length());
//
//        KeyPair keyPair=RSA.generateRSAKeyPair(RSA.DEFAULT_KEY_SIZE);
//        // 公钥
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        // 私钥
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();


        //公钥加密
//        long start=System.currentTimeMillis();
//        byte[] encryptBytes= new byte[0];
//        try {
//            encryptBytes = RSA.encryptByPublicKeyForSpilt(jsonData.getBytes(),publicKey.getEncoded());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        long end=System.currentTimeMillis();
//        LogPrint.printError("公钥加密耗时 cost time---->"+(end-start));
//        String encryStr= Base64Encoder.encode(encryptBytes);
//        LogPrint.printError("加密后json数据 --1-->"+encryStr);
//        LogPrint.printError("加密后json数据长度 --1-->"+encryStr.length());
//
//
//        //私钥解密
//        start=System.currentTimeMillis();
//        byte[] decryptBytes= new byte[0];
//        try {
//            decryptBytes = RSA.decryptByPrivateKeyForSpilt(Base64Decoder.decodeToBytes(encryStr),privateKey.getEncoded());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String decryStr=new String(decryptBytes);
//        end=System.currentTimeMillis();
//        LogPrint.printError("私钥解密耗时 cost time---->"+(end-start));
//        LogPrint.printError("解密后json数据 --1-->"+decryStr);
//
//        //下载zip
//        showDownLoadDialog();


    }

    /**
     * 页面刚进入初始化
     */
    private void  initialis(){
        // 获取本版本号，是否更新
        Tools tools=new Tools();
        int vision =tools.getVersion(this);
        LogPrint.printError("版本号："+vision);
        tools.getVersionStart(vision,this);
        //版本控制（监控器）
        XutilsHttp.getInstance().getViewShowOrHidden("Android", SingleOverAll.getInstance().getVersionName(this), new OkhttpCallBack() {
            @Override
            public void OnSuccess(String json) {
                VERSION version= JSON.parseObject(json,VERSION.class);
                if(version.getCode().equals(OkhttpManager.SUCESS)){
                    if(!TextUtils.isEmpty(version.getBody().getContent().getIsReview()+"")) {
                        LogPrint.printError("是否显示" + version.getBody().getContent().getIsReview());
                        PrefManager.getInstance().setIsReview(version.getBody().getContent().getIsReview() + "");
                    }
                }
            }
            @Override
            public void OnFaild(String faildM) {
            }
        });

    }
    /**
     * EventBus消息处理方法。
     * @param eventBusMessageHomeTag
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageHomeTag eventBusMessageHomeTag) {
        //中国好物
        if(eventBusMessageHomeTag.getTag().endsWith("1")){
            home_page_img.setImageResource(R.mipmap.tabbar_syl_normal);
            integrate_purchase_img.setImageResource(R.mipmap.tabbar_fl_selected);
            earth_inone_img.setImageResource(R.mipmap.tabbar_hwl_normal);
            show_add_img.setImageResource(R.mipmap.tabbar_hwsj_normal);
            my_img.setImageResource(R.mipmap.tabbar_wd_normal);
            home_page_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            integrate_purchase_text.setTextColor(getResources().getColor(R.color.tablesSelect));
            earth_inone_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            show_add_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            my_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            replaceFragment(1);
        }
        //全球集市
        else if(eventBusMessageHomeTag.getTag().endsWith("2")){
            home_page_img.setImageResource(R.mipmap.tabbar_syl_normal);
            integrate_purchase_img.setImageResource(R.mipmap.tabbar_fl_normal);
            earth_inone_img.setImageResource(R.mipmap.tabbar_hwl_selected);
            show_add_img.setImageResource(R.mipmap.tabbar_hwsj_normal);
            my_img.setImageResource(R.mipmap.tabbar_wd_normal);
            home_page_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            integrate_purchase_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            earth_inone_text.setTextColor(getResources().getColor(R.color.tablesSelect));
            show_add_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            my_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            replaceFragment(2);
        }
        //商家联盟
        else if(eventBusMessageHomeTag.getTag().endsWith("3")){
            home_page_img.setImageResource(R.mipmap.tabbar_syl_normal);
            integrate_purchase_img.setImageResource(R.mipmap.tabbar_fl_normal);
            earth_inone_img.setImageResource(R.mipmap.tabbar_hwl_normal);
            show_add_img.setImageResource(R.mipmap.tabbar_hwsj_selected);
            my_img.setImageResource(R.mipmap.tabbar_wd_normal);
            home_page_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            integrate_purchase_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            earth_inone_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            show_add_text.setTextColor(getResources().getColor(R.color.tablesSelect));
            my_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
            replaceFragment(3);
        }

    }
    @OnClick({R.id.home_page, R.id.integrate_purchase, R.id.earth_inone, R.id.show_add,R.id.my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_page:
                LogPrint.printError("首页");
                //购物车图标显示
                shopNumber.setVisibility(View.VISIBLE);
                home_page_img.setImageResource(R.mipmap.tabbar_syl_selected);
                integrate_purchase_img.setImageResource(R.mipmap.tabbar_fl_normal);
                earth_inone_img.setImageResource(R.mipmap.tabbar_hwl_normal);
                show_add_img.setImageResource(R.mipmap.tabbar_hwsj_normal);
                my_img.setImageResource(R.mipmap.tabbar_wd_normal);
                home_page_text.setTextColor(getResources().getColor(R.color.tablesSelect));
                integrate_purchase_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                earth_inone_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                show_add_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                my_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                rlTitleLayout.setVisibility(View.GONE);
                replaceFragment(0);
                break;
            case R.id.integrate_purchase:
                LogPrint.printError("分类");
                //购物车图标显示
                shopNumber.setVisibility(View.VISIBLE);
                home_page_img.setImageResource(R.mipmap.tabbar_syl_normal);
                integrate_purchase_img.setImageResource(R.mipmap.tabbar_fl_selected);
                earth_inone_img.setImageResource(R.mipmap.tabbar_hwl_normal);
                show_add_img.setImageResource(R.mipmap.tabbar_hwsj_normal);
                my_img.setImageResource(R.mipmap.tabbar_wd_normal);
                home_page_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                integrate_purchase_text.setTextColor(getResources().getColor(R.color.tablesSelect));
                earth_inone_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                show_add_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                my_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                rlTitleLayout.setVisibility(View.GONE);
                replaceFragment(1);
                break;
            case R.id.earth_inone:
                LogPrint.printError("消息");
                //购物车图标不显示
                shopNumber.setVisibility(View.GONE);
                home_page_img.setImageResource(R.mipmap.tabbar_syl_normal);
                integrate_purchase_img.setImageResource(R.mipmap.tabbar_fl_normal);
                earth_inone_img.setImageResource(R.mipmap.tabbar_hwl_selected);
                show_add_img.setImageResource(R.mipmap.tabbar_hwsj_normal);
                my_img.setImageResource(R.mipmap.tabbar_wd_normal);
                home_page_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                integrate_purchase_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                earth_inone_text.setTextColor(getResources().getColor(R.color.tablesSelect));
                show_add_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                my_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                rlTitleLayout.setVisibility(View.GONE);
                replaceFragment(2);
                break;
            case  R.id.show_add:
                LogPrint.printError("好物视界");
                //购物车图标不显示
                shopNumber.setVisibility(View.GONE);
                home_page_img.setImageResource(R.mipmap.tabbar_syl_normal);
                integrate_purchase_img.setImageResource(R.mipmap.tabbar_fl_normal);
                earth_inone_img.setImageResource(R.mipmap.tabbar_hwl_normal);
                show_add_img.setImageResource(R.mipmap.tabbar_hwsj_selected);
                my_img.setImageResource(R.mipmap.tabbar_wd_normal);
                home_page_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                integrate_purchase_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                earth_inone_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                show_add_text.setTextColor(getResources().getColor(R.color.tablesSelect));
                my_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                rlTitleLayout.setVisibility(View.GONE);
                replaceFragment(3);
                break;
            case R.id.my:
                LogPrint.printError("我的");
                //购物车图标不显示
                shopNumber.setVisibility(View.GONE);
                home_page_img.setImageResource(R.mipmap.tabbar_syl_normal);
                integrate_purchase_img.setImageResource(R.mipmap.tabbar_fl_normal);
                earth_inone_img.setImageResource(R.mipmap.tabbar_hwl_normal);
                show_add_img.setImageResource(R.mipmap.tabbar_hwsj_normal);
                my_img.setImageResource(R.mipmap.tabbar_wd_selected);
                home_page_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                integrate_purchase_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                earth_inone_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                show_add_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
                my_text.setTextColor(getResources().getColor(R.color.tablesSelect));
                rlTitleLayout.setVisibility(View.GONE);
                replaceFragment(4);
                break;
            default:
                break;
        }
    }
    /**
     * 默认tabbler1
     */
    private  void  defaultOne(){
        home_page_img.setImageResource(R.mipmap.tabbar_syl_selected);
        integrate_purchase_img.setImageResource(R.mipmap.tabbar_fl_normal);
        earth_inone_img.setImageResource(R.mipmap.tabbar_hwl_normal);
        show_add_img.setImageResource(R.mipmap.tabbar_hwsj_normal);
        my_img.setImageResource(R.mipmap.tabbar_wd_normal);
        home_page_text.setTextColor(getResources().getColor(R.color.tablesSelect));
        integrate_purchase_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
        earth_inone_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
        show_add_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
        my_text.setTextColor(getResources().getColor(R.color.tablesNoSelect));
    }

    /**
     * 多个fragment之间的切换
     * @param tag
     */
    public void replaceFragment(int tag) {
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
        }
        currentFragment = getSupportFragmentManager().findFragmentByTag(tag+"");
        if (currentFragment == null) {
            switch (tag) {
                case 0:
                    currentFragment = new ViewPagerFragment();
                    break;
                case 1:
//                    currentFragment = new ChinaGoodsFragment();
                    currentFragment=new ClassificationFragment();
                    activity_image.setVisibility(View.GONE);
                    shopNumber.setVisibility(View.VISIBLE);
                    tagThis=1;
                    break;
                case 2:
//                    this.initState(R.id.ll_bar);
                    //消息页面fragment
                    currentFragment = new messageFragment();
                    //爱好物聊天系统入口暂时屏蔽二期开发
//                    ConversationListFragmentEx conversationListFragment = new ConversationListFragmentEx();
//                    Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
//                            .appendPath("conversationlist")
//                            .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
//                            .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
//                            .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
//                            .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
//                            .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//系统
//                            .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
//                            .build();
//                    conversationListFragment.setUri(uri);
//                    currentFragment = conversationListFragment;


                    activity_image.setVisibility(View.GONE);
                    shopNumber.setVisibility(View.GONE);
                    tagThis=1;
                    break;
                case 3:
                    currentFragment = new VideoFragment();
                    activity_image.setVisibility(View.GONE);
                    shopNumber.setVisibility(View.GONE);
                    tagThis=2;
                    break;
                case 4:
                    currentFragment = new MyFragment();
                    activity_image.setVisibility(View.GONE);
                    shopNumber.setVisibility(View.GONE);
                    tagThis=2;
                    break;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, currentFragment, tag+"").commit();
        }else {
            getSupportFragmentManager().beginTransaction().show(currentFragment).commit();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        homeFragmentProsenter=null;
        EventBus.getDefault().unregister(this);
        addUnreadCountChangeListener(false);
    }
    /**
     * 返回两次返回退出程序
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0) {
            this.exit();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * EventBus消息处理方法。
     * @param eventBusMessageRefresh
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageRefresh eventBusMessageRefresh) {
        //刷新消息
        if(eventBusMessageRefresh.getTag()==0){
            if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                messsageProsenter.Get_message_number();
            }
            LogPrint.printError("小红点刷新");
            homeFragmentProsenter.getShopCar(PrefManager.getInstance().getToken()==null?"":PrefManager.getInstance().getToken());
        }else if(eventBusMessageRefresh.getTag()==1){
            if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                messsageProsenter.Get_message_number();
            }
        }
    }
    /**
     * 底部tabbbar点击事件
     * @param shopCar
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageShopCar shopCar) {
        //刷新消息
        if(shopCar.getTag()==0){
            tagThis=0;
            LogPrint.printError("come baby");
            //获取福袋
            homeFragmentProsenter.getSlideShowDataHome();
        }else if(shopCar.getTag()==1){
            tagThis=1;
            //福袋隐藏
            activity_image.setVisibility(View.GONE);
            //购物车显示
            shopNumber.setVisibility(View.VISIBLE);
        }else if(shopCar.getTag()==2){
            tagThis=2;
            //福袋隐藏
            activity_image.setVisibility(View.GONE);
            //购物车隐藏
            shopNumber.setVisibility(View.GONE);
        }

    }


    /**
     * 福袋显示
     * @param
     */
    @Override
    public void getSlideShowHome(Banner.BodyBean.ContentBean.ListBean bean) {
        if(activity_image!=null) {
            activity_image.setVisibility(View.VISIBLE);
        }
        shopNumber.setVisibility(View.GONE);
        this.beanThis=bean;
        SoftReference<GlideUtils> glideUtils=new SoftReference<>(new GlideUtils());
        LogPrint.printError("url"+bean.getBannerImg());
        if(glideUtils.get()!=null) {
            glideUtils.get().glides(HomePageActivity.this, bean.getBannerImg(), f_dai);
        }else{
            SoftReference<GlideUtils> glideUtils1=new SoftReference<>(new GlideUtils());
            glideUtils1.get().glides(HomePageActivity.this, bean.getBannerImg(), f_dai);
        }
    }

    /**
     * 启动页面
     * @param bean
     */
    @Override
    public void getStartAd(Banner.BodyBean.ContentBean.ListBean bean) {
        startPageImage=bean;
        //显示广告页面
        start_page.setVisibility(View.VISIBLE); //调用弹框
        main_fragment.setVisibility(View.GONE);
        GlideUtils glideUtils=new GlideUtils();
        //渲染图片
        glideUtils.glides(HomePageActivity.this, bean.getBannerImg(), image_start);
        //倒计时
        utilsTimer = new UtilsTimer(goon, new UtilsTimer.UtilsTimerCallBack() {
            @Override
            public void timeOver() {
                start_page.setVisibility(View.GONE); //调用弹框
                main_fragment.setVisibility(View.VISIBLE);
                EventBus.getDefault().post(new EventBusMessageRedPick(15));
            }
        });
        utilsTimer.goOn(0, 1000, TIME);
    }

    /**
     *
     * 购物车数量
     * @param number
     */
    @Override
    public void ShopCarNumber(int number) {
        LogPrint.printError("购物车数量哈哈哈哈:"+number);
        //需要去获取福袋数据
        if(tagThis==0){
            //购物车显示
            if (activity_image != null) {
                activity_image.setVisibility(View.GONE);
            }
            if (shopNumber != null) {
                shopNumber.setVisibility(View.VISIBLE);
            }
            if (number!= 0) {
                if (badgeView == null) {
                    badgeView = BadgeFactory.createOvalBig1(this).bind(shopingCar);
                }
                badgeView.setBadgeCount(number);
            } else {
                if (badgeView != null) {
                    badgeView.unbind();
                    badgeView = null;
                }
            }
            if(homeFragmentProsenter!=null) {
                homeFragmentProsenter.getSlideShowDataHome();
            }
        }else if(tagThis==1) {
            //购物车显示
            if (activity_image != null) {
                activity_image.setVisibility(View.GONE);
            }
            if (shopNumber != null) {
                shopNumber.setVisibility(View.VISIBLE);
            }
            if (number != 0) {
                if (badgeView == null) {
                    badgeView = BadgeFactory.createOvalBig1(this).bind(shopingCar);
                }
                badgeView.setBadgeCount(number);
            } else {
                if (badgeView != null) {
                    badgeView.unbind();
                    badgeView = null;
                }
            }
        }else if(tagThis==2){
            if (number!= 0) {
                if (badgeView == null) {
                    badgeView = BadgeFactory.createOvalBig1(this).bind(shopingCar);
                }
                badgeView.setBadgeCount(number);
            } else {
                if (badgeView != null) {
                    badgeView.unbind();
                    badgeView = null;
                }
            }
        }
    }

    /**
     * 没有广告
     */
    @Override
    public void noAd() {
        LogPrint.printError(" 没有广告");
        start_page.setVisibility(View.GONE); //调用弹框
        main_fragment.setVisibility(View.VISIBLE);
        EventBus.getDefault().post(new EventBusMessageRedPick(15));
    }

    @OnClick({R.id.shopingCar,R.id.f_dai,R.id.image_start,R.id.goon, R.id.rl_contact, R.id.rl_more})
    public void onclick(View view){
        switch (view.getId()){
            //跳转到购物车页面
            case R.id.shopingCar:
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                    if(dialogUtilsSoftReference.get()!=null) {
                        dialogUtilsSoftReference.get().LoginTo(this);
                    }else{
                        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                        dialogUtilsSoftReference.get().LoginTo(this);
                    }
                }else {
                    //跳转购物车
                    UtilsAll.GoWebviewAll(this, ConfigH5Url.shopCar());
                }
                break;
            //福袋点击
            case  R.id.f_dai:
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                    if(dialogUtilsSoftReference.get()!=null) {
                        dialogUtilsSoftReference.get().LoginTo(this);
                    }else{
                        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                        dialogUtilsSoftReference.get().LoginTo(this);
                    }
                }else {
                    //跳转到banner
                    SingleOverAll.getInstance().bannerClick(this,beanThis);
                }

                break;
            //启动页点击效果
            case R.id.image_start:
                if(startPageImage!=null){
                    SingleOverAll.getInstance().bannerClick(this,startPageImage);
                }
                break;
            //启动广告跳过
            case R.id.goon:
                start_page.setVisibility(View.GONE); //调用弹框
                main_fragment.setVisibility(View.VISIBLE);
                utilsTimer.destoryTimer();
                break;
            case R.id.rl_contact:
                Intent intent = new Intent(HomePageActivity.this, ContactsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_more:
                MorePopWindow morePopWindow = new MorePopWindow(HomePageActivity.this);
                morePopWindow.showPopupWindow(rlMore);
                break;
            default:
                break;
        }
    }

    private void showDownLoadDialog(){
        File lib_js= SingleOverAll.getInstance().getDiskCacheDir(this, ZApplication.JSLib);
        if (!lib_js.exists()) {
            lib_js.mkdirs();
        }
        ZApplication.JSLib=lib_js.getAbsolutePath();
        new AlertDialog.Builder(this).setTitle("温馨提示")
                .setMessage("是否下载")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        LogPrint.printError( "onClick 1 = "+which);
                        doDownLoadWork();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        LogPrint.printError( "onClick 2 = "+which);
                    }
                })
                .show();
    }

    public void showUnzipDialog(){
        new AlertDialog.Builder(this).setTitle("温馨提示")
                .setMessage("是否解压？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        LogPrint.printError( "onClick 1 = "+which);
                        doZipExtractorWork();
                    }
                })
                .setNegativeButton("不需要", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        LogPrint.printError( "onClick 2 = "+which);
                    }
                })
                .show();
    }

    public void doZipExtractorWork(){
        ZipExtractorTask task = new ZipExtractorTask( ZApplication.JSLib+"/plp-h5-2.zip",  ZApplication.JSLib+"/plp_h5", this, true);
        task.execute();
    }


    private void doDownLoadWork(){
        DownLoaderTask task = new DownLoaderTask("http://shzhuoji-files-test.oss-cn-shanghai.aliyuncs.com/static/ios-css/plp-h5-2.zip", ZApplication.JSLib, this);
        task.execute();
    }

    @Override
    public void Msg(MsgModel.BodyBean.ContentBean contentBean) {
        if(contentBean!=null)
            if(contentBean.getSystem()+contentBean.getOrder()+Integer.parseInt(contentBean.getTransaction())>0){
                badgeHelper.setBadgeEnable(true);
            }else{
                badgeHelper.setBadgeEnable(false);
            }
    }
}
