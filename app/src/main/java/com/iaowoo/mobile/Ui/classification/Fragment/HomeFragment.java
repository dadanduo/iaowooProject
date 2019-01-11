package com.iaowoo.mobile.Ui.classification.Fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.EvenBus.EventBusMessageJG;
import com.iaowoo.mobile.EvenBus.EventBusMessageRedPick;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
import com.iaowoo.mobile.Ui.classification.Model.YUE_JIFEN_CAIHONGBAO;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.Presenter.MyFragmentPresenter;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.UtilsTimer;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.DB.MessageNumber;
import com.iaowoo.mobile.EvenBus.EventBusMessageJG;
import com.iaowoo.mobile.EvenBus.EventBusMessageRedPick;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.MessageActivity;
import com.iaowoo.mobile.Ui.classification.Activity.ScanActicvity;
import com.iaowoo.mobile.Ui.classification.Activity.SearchOneActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.TopRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.Configtion;
import com.iaowoo.mobile.Ui.classification.Model.ISHAVERED;
import com.iaowoo.mobile.Ui.classification.Model.MYMESSAGE;
import com.iaowoo.mobile.Ui.classification.Model.OrderNumber;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.Presenter.MyFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.BadgeFactory;
import com.iaowoo.mobile.Ui.classification.View.BadgeView;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Utils.UtilsTimer;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
 * @Description: ${todo}(首页)
 * @date 2018/8/27
 * @email ${18011009889@163.com}
 */
public class HomeFragment extends BaseFragment implements HomeFragmentPresenter.HomeCallBack {

    @BindView(R.id.recycler_view)
    WRecyclerView recycler_view;
//    @BindView(R.id.list_swiperefreshlayout)
//    SwipeRefreshLayout list_swiperefreshlayout;
    @BindView(R.id.list_swiperefreshlayout)
     SmartRefreshLayout list_swiperefreshlayout;

    @BindView(R.id.search_top)
    LinearLayout search_top;
    @BindView(R.id.ser)
    RelativeLayout ser;
    @BindView(R.id.message_img)
    RelativeLayout message_img;
    @BindView(R.id.ok_layout)
    LinearLayout ok_layout;
    @BindView(R.id.image_head)
    CircleImageView image_head;
    @BindView(R.id.ok_text)
    TextView ok_text;
    private SoftReference<DialogUtils> dialogUtilsSoftReference;


    private BadgeView badgeView=null;
    /**
     * adapter
     */
    private TopRecycleAdapter topRecycleAdapter = null;
    /**
     * prosenter
     */
    private HomeFragmentPresenter homeFragmentProsenter = null;


    private MyFragmentPresenter myFragmentProsenter = null;


    @Override
    public int getLayoutResId() {
        return R.layout.home_fragment_xml;
    }

    @Override
    protected void initView() {
        super.initView();
        initDatas();
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
        EventBus.getDefault().register(this);
        //有消息存在显示
        if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            messageNumber();
        }
        this.grideShow(recycler_view,2);
//        this.initSwipeRefreshView(list_swiperefreshlayout);
        this.initSwipeRefreshView1(list_swiperefreshlayout);
        //设置为透明
//        search_top.getBackground().mutate().setAlpha(0);
        //动态设置高度
        this.setViewMarginTop(ser);
        /****************************头部样式变化*************************************/
//        this.setRecyclerOnscrocListenr(recycler_view, new ScrollListenerCallBack() {
//            @Override
//            public void updateTransparency(int transparencyData) {
//                search_top.setVisibility(View.VISIBLE);
//                search_top.getBackground().mutate().setAlpha(transparencyData);
//            }
//            @Override
//            public void HideTile() {
//                search_top.getBackground().mutate().setAlpha(0);
//            }
//
//            @Override
//            public void LoadingOK() {
//                search_top.getBackground().mutate().setAlpha(255);
//            }
//        });
        /*******************************************************************/



    }

    @Override
    protected void initData() {
        super.initData();
        homeFragmentProsenter = new HomeFragmentPresenter(getActivity());
        homeFragmentProsenter.setCallBack(this);
        homeFragmentProsenter.getShopType(curPageIndex,pageSize);
        topRecycleAdapter=new TopRecycleAdapter(getActivity(),homeFragmentProsenter);

        myFragmentProsenter=new MyFragmentPresenter(getActivity());
        myFragmentProsenter.setMyCallBack(new MyFragmentPresenter.MyAdapterCallBack() {
            @Override
            public void getConfigtion(Configtion configtion) {

            }
            @Override
            public void Mymessage(MYMESSAGE mymessage) {
                //保存用戶的id
                PrefManager.getInstance().setUserId(mymessage.getBody().getContent().getUserId()+"");
                //保存用戶的支付密碼
                PrefManager.getInstance().setPayPassword(mymessage.getBody().getContent().getPayPassword()+"");
                //保存用戶信息
                PrefManager.getInstance().setUserMsg(ZApplication.gson.toJson(mymessage.getBody().getContent()));
                //保存头像
                PrefManager.getInstance().saveHeadIcon(mymessage.getBody().getContent().getHeadImgUrl());
                if(mymessage.getBody().getContent().getAuthentication()==1){
                    PrefManager.getInstance().saveIsTrueName("1");
                }else{
                    PrefManager.getInstance().saveIsTrueName("0");
                }
            }
            @Override
            public void OrderNumber(OrderNumber orderNumber) {

            }
            @Override
            public void getSlideShow(Banner banner) {

            }
            @Override
            public void query_data(YUE_JIFEN_CAIHONGBAO.BodyBean.ContentBean contentBean) {

            }
        });
        //token不為空拉用戶信息
        if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            myFragmentProsenter.getPERSON(PrefManager.getInstance().getToken());
        }
    }

    @Override
    public void getDatas(int curPageIndex, int pageSize) {
        homeFragmentProsenter.getShopType(curPageIndex,pageSize);
    }

    @Override
    public void noHaveDatas() {
        LogPrint.printError("没有数据了你还在滑个卵哦！！！");

    }

    @Override
    protected void refreshOk() {
        super.refreshOk();
        topRecycleAdapter.notifyDataSetChanged();
        tempY=0;
    }

    /**
     * 分页数据返回
     * @param shops
     */
    @Override
    public void Shop(List<Shop.BodyBean.ContentBean.ListBean> shops) {
        this.setData(true);
        this.stopRefreshAndLoading();
        if(recycler_view!=null) {
            //允许上拉加载功能
            recycler_view.setPullLoadEnable(true);
            LogPrint.printError("总共条数:" + shops.size());
            //获取加载的分页总数据
            for (int i = 0; i < shops.size(); i++) {
                datas.add(shops.get(i));
            }
            //第一页数据加载adpter
            if (First) {
                topRecycleAdapter.setShops(datas);
                recycler_view.setAdapter(topRecycleAdapter);
                First = false;
            }
            //第二次直接刷新
            else {
                topRecycleAdapter.setShops(datas);
                topRecycleAdapter.notifyDataSetChanged();
            }
        }
    }


    /**
     * @param view
     */
    @OnClick({R.id.scan_img,R.id.search_item,R.id.message_img,R.id.classification})
    public void click(View view){
        switch (view.getId()){
            case R.id.scan_img:
                IntentIntegrator intentIntegrator = new IntentIntegrator((getActivity()));
                intentIntegrator
                        .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                        .setPrompt(ZApplication.CODE_TEXT)//写那句提示的话
                        .setOrientationLocked(false)//扫描方向固定
                        .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
                        .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                        .setCaptureActivity(ScanActicvity.class) // 设置自定义的activity是CustomActivity
                        .initiateScan(); // 初始化扫描
                break;
            case R.id.search_item:
                LogPrint.printError("搜索");
                startActivity(new Intent(getActivity(), SearchOneActivity.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.message_img:
                LogPrint.printError("消息");
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                    if(dialogUtilsSoftReference.get()!=null) {
                        dialogUtilsSoftReference.get().LoginTo(getActivity());
                    }else{
                        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                        dialogUtilsSoftReference.get().LoginTo(getActivity());
                    }
                }else{
                    startActivity(MessageActivity.class);
                }
                break;
            case  R.id.classification:
//                startActivity(new Intent(getActivity(), ClassificationActivity.class));
                //先走h5
                UtilsAll.GoWebviewAll(getActivity(), ConfigH5Url.Classinfo());
                break;
            default:
                break;
        }
    }

    @Override
    public void NohaveData() {
        LogPrint.printError("没得数据了");
        this.stopRefreshAndLoading();
        this.setData(false);
    }

    @Override
    public void IshaveRed(ISHAVERED ishavered) {
        int size=ishavered.getBody().getContent().size();
        for(int i=0;i<size;i++){
            if(ishavered.getBody().getContent().get(i).getCategory()==1){
                if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                    DialogUtils dialogUtils=new DialogUtils();
                    dialogUtils.ThecoupleRedPackets(getActivity(), new ClickCallBack() {
                        @Override
                        public void OnClick() {
                        }
                    });
                }
            }
        }
    }

    /**
     * 轮播继续
     */
    @Override
    public void onResume() {
        super.onResume();
        if (topRecycleAdapter != null) {
            topRecycleAdapter.startLur();
        }
    }

    /**
     * 轮播停止
     */
    @Override
    public void onStop() {
        super.onStop();
        if (topRecycleAdapter != null) {
            topRecycleAdapter.stopLur();
        }
    }

    /**
     * 是否含有新人红包
     */
    boolean lala=true;

    public void isHaveRed(){
        if(lala) {
            if (TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
                DialogUtils dialogUtils = new DialogUtils();
                dialogUtils.ThecoupleRedPackets(getActivity(), new ClickCallBack() {
                    @Override
                    public void OnClick() {

                    }
                });
                lala=false;
            } else {
                homeFragmentProsenter.IsGetHaveNewRed(PrefManager.getInstance().getToken());
            }
        }
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
                        LogPrint.printError("首页Uid:"+messageNumber.getUid());
                        int messageAll=messageNumber.getD_d()+messageNumber.getK_f()+messageNumber.getJ_y()+messageNumber.getG_g()+messageNumber.getX_t();
                        LogPrint.printError("消息总数"+messageAll);
                        if(messageAll==0) {
                            //消息数为零不显示
                            if(badgeView!=null) {
                                badgeView.unbind();
                                badgeView=null;
                            }
                        }else{
                            if(badgeView==null) {
                                badgeView = BadgeFactory.createOval(getActivity()).bind(message_img);
                            }
                            //不为零显示消息数
                            badgeView.setBadgeCount(messageAll);
                        }
                    }
                }
            }
        }
    }

    /**
     * 切换到首页时逻辑处理
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            EventBus.getDefault().post(new EventBusMessageShopCar(0));
            //新人红包处理
//            isHaveRed();
            //消息处理
            if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                if(badgeView!=null) {
                    //不为零显示消息数
                    badgeView.unbind();
                    badgeView = null;
                }
            }else {
                if(badgeView==null){
                    badgeView=BadgeFactory.createOval(getActivity()).bind(message_img);
                }
                messageNumber();

            }
            recycler_view.scrollToPosition(0);
            tempY=0;
//            search_top.getBackground().mutate().setAlpha(0);
        }
    }
    /**
     * EventBus消息处理方法。
     * @param eventBusMessageRefresh
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageRefresh eventBusMessageRefresh) {
        LogPrint.printError("更新界面");
        //token不為空拉用戶信息
        if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())) {
            myFragmentProsenter.getPERSON(PrefManager.getInstance().getToken());
        }        //刷新消息
        if(eventBusMessageRefresh.getTag()==0){
            LogPrint.printError("小红点刷新");
            messageNumber();
        }else if(eventBusMessageRefresh.getTag()==1){
            isHaveRed();//刷新新人红包的判断
        }
    }
    /**
     * EventBus消息处理方法。
     * @param eventBusMessageRedPick
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageRedPick eventBusMessageRedPick) {
        if(eventBusMessageRedPick.getTag()==15){
            isHaveRed();//刷新新人红包的判断
        }
    }

    /**
     * EventBus中奖消息处理
     * @param eventBusMessageJG
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(final EventBusMessageJG eventBusMessageJG) {
        if(!TextUtils.isEmpty(eventBusMessageJG.getContent())) {
            ok_layout.setVisibility(View.VISIBLE);
            if(TextUtils.isEmpty(eventBusMessageJG.getImageHead())){
                image_head.setImageResource(R.mipmap.mine_default_avatar);
            }else{
                GlideUtils glideUtils=new GlideUtils();
                glideUtils.glides(getActivity(),eventBusMessageJG.getImageHead(),image_head);
            }
            ok_text.setText(eventBusMessageJG.getContent());
            final UtilsTimer timer = new UtilsTimer();
            timer.goOn(0, 1000, 3);
            timer.setInterface(new UtilsTimer.UtilsTimerCallBack() {
                @Override
                public void timeOver() {
                    LogPrint.printError("通知时间到");
                    ok_layout.setVisibility(View.GONE);
                    timer.destoryTimer();

                }
            });
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(myFragmentProsenter!=null) {
            myFragmentProsenter = null;
        }
        EventBus.getDefault().unregister(this);
    }
}
