package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Ui.classification.Fragment.CommentsFragment;
import com.iaowoo.mobile.Ui.classification.Fragment.GoodsFragment;
import com.iaowoo.mobile.Ui.classification.Fragment.GraphicFragment;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.Presenter.ShopGoodsDialogPresenters;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideImageLoader;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Fragment.CommentsFragment;
import com.iaowoo.mobile.Ui.classification.Fragment.GoodsFragment;
import com.iaowoo.mobile.Ui.classification.Fragment.GraphicFragment;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.Presenter.ShopGoodsDialogPresenters;
import com.iaowoo.mobile.Ui.classification.View.BadgeFactory;
import com.iaowoo.mobile.Ui.classification.View.BadgeView;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideImageLoader;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigAppkey;
import com.iaowoo.mobile.common.ConfigFlag;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.ProductDetail;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
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
 * @Description: ${todo}(商品详情页面)
 * @date 2018/10/18
 * @email ${18011009889@163.com}
 */
public class GoodsDetailsActivity extends BaseBufferActivity  implements GoodsFragment.GoodsBack,GraphicFragment.GraphicBack,CommentsFragment.CommentsBack,HomeFragmentPresenter.HomeMainCallBack {

    private String goodsId;
    private GlideUtils glideUtils;
    private SoftReference<DialogUtils> dialogUtilsSoftReference;
    /**
     * 轮播图数据
     */
    private List<String> list1;
    /**
     *详情images
     */
    private  List<String> list;

    private String[] titles = {"商品", "图文详情", "评价"};

    private List<Fragment> listFragment;

    private String  activityId;

    private BadgeView badgeView=null;

    @BindView(R.id.tops)
    RelativeLayout tops;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.top_tab)
    RelativeLayout top_tab;
    @BindView(R.id.goods_return_btn)
    ImageView goods_return_btn;
    @BindView(R.id.goods_share)
    ImageView goods_share;
    @BindView(R.id.car_goods)
    RelativeLayout car_goods;

    private MyAdapter adapter;
    /**
     * 商品页面
     */
    private  GoodsFragment goodsFragment;

    //当前所在位置默认为商品详情上部   0:上部  1:下部
    private int ThisPages=0;

    /**
     * 商品评价
     */
    private CommentsFragment commentsFragment;

    /**
     * prosenter
     */
    private HomeFragmentPresenter homeFragmentProsenter=null;
    /**
     *当前所在页面  默认为第一个页面
     */
    private int  ThisPageSize=0;
    /**
     *记录当前的滑动y轴距
     */
    private int HeadY=0;

    private String tagR;

    @Override
    public int getLayoutResId() {
        return R.layout.goods_details;
    }
    @Override
    protected void initView() {
        super.initView();
        this.allState();
        this.setViewMarginTop(tops);
        this.setViewMarginTop(top_tab);
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
        goodsId= getIntent().getStringExtra("goodsId");
        activityId=getIntent().getStringExtra("activityId");
        tagR=getIntent().getStringExtra("tagNoReturn");
        LogPrint.printError("商品详情id"+goodsId);

        CommentsFragment commentsFragment=new CommentsFragment();
        commentsFragment.setTemplateId(goodsId);
        commentsFragment.setActivityId(activityId);

        goodsFragment=new GoodsFragment();
        goodsFragment.setGoodsId(goodsId);
        goodsFragment.setActivityId(activityId);

        GraphicFragment graphicFragment= new GraphicFragment();
        graphicFragment.setTemplateId(goodsId);

        //页面，数据源
        listFragment = new ArrayList<>();
        listFragment.add(goodsFragment);
        listFragment.add(graphicFragment);
        listFragment.add(commentsFragment);
        //ViewPager的适配器
        adapter = new MyAdapter(getSupportFragmentManager());
        //解决viewpager+fragment的生命周期混乱问题
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(adapter);

        //绑定
        tablayout.setupWithViewPager(viewpager);
    }
    @Override
    protected void initData() {
        super.initData();
        homeFragmentProsenter=new HomeFragmentPresenter(this);
        homeFragmentProsenter.setHomeMainCallBack(this);
        if(!TextUtils.isEmpty(PrefManager.getInstance().getToken())){
            //获取购物车
            homeFragmentProsenter.getShopCar(PrefManager.getInstance().getToken()==null?"":PrefManager.getInstance().getToken());
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.goods_return_btn,R.id.goods_share,R.id.car_goods,R.id.Customer,R.id.add_car_goods,R.id.buy_ok,R.id.bootem})
    public void onclick(View view){
        switch (view.getId()){
            //返回
            case R.id.goods_return_btn:
                //滑到图文详情页面或者是评论页面的返回逻辑
                if(ThisPageSize!=0){
                    tablayout.getTabAt(0).select();
                }else if(ThisPageSize==3){
                    //商品详情下面的页面的返回逻辑
                    finish();
                }else{
                    //当前商品详情的返回逻辑
                    finish();
                }
                break;
            //分享
            case R.id.goods_share:
                Defaultcontent.code_url="page/pagesGoods/goods/goodDetail?goodsId="+goodsId+"&inviteCode="+PrefManager.getInstance().getInvite()+"&form=share";
                if (dialogUtilsSoftReference.get() != null) {
                    dialogUtilsSoftReference.get().Share(this);
                } else {
                    dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                    dialogUtilsSoftReference.get().Share(this);
                }
                break;
            //购物车图标
            case R.id.car_goods:
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
            //客服
            case R.id.Customer:
                if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                    if(dialogUtilsSoftReference.get()!=null) {
                        dialogUtilsSoftReference.get().LoginTo(this);
                    }else{
                        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                        dialogUtilsSoftReference.get().LoginTo(this);
                    }
                }else{
                    // appKey七鱼客服
                    Unicorn.init(this, ConfigAppkey.QIYU, ConfigFlag.ysfOptions, new GlideImageLoader(this));
                    setSource("", PrefManager.getInstance().getMobile(), "", "", "V" + SingleOverAll.getInstance().getVersionName(ZApplication.getContext()) + "&&versionCode:" + SingleOverAll.getInstance().getVersionCode(ZApplication.getInstance().getApplicationContext()), "", "品牌：" + UtilsAll.getPhoneBrand() + "型号：" + UtilsAll.getPhoneModel(), "", "android操作系统");
                    intoQiyu(this, Long.parseLong(ConfigFlag.SHOUQIAN),Defaultcontent.title,Defaultcontent.desc,Defaultcontent.url,Defaultcontent.price,Defaultcontent.imageurl);
                }
                break;
            //加入购物车
            case  R.id.add_car_goods:
                goodsFragment.buy(1, new ShopGoodsDialogPresenters.addShopCar() {
                    @Override
                    public void addSuccessful() {
                        homeFragmentProsenter.getShopCar(PrefManager.getInstance().getToken()==null?"":PrefManager.getInstance().getToken());
                    }
                });
                break;
            //立即购买
            case R.id.buy_ok:
                goodsFragment.buy(2, new ShopGoodsDialogPresenters.addShopCar() {
                    @Override
                    public void addSuccessful() {
                    }
                });
                break;
            case R.id.bootem:
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到客服页面
     * @param context
     */
    public  void intoQiyu(Context context, long groupId,String shopTitle,String Desc,String urlGo,String price,String image){
        String title = this.getResources().getString(R.string.app_name)+"客服";
        /**
         * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
         * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
         * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
         */
        String sourceUrl="https://pulpu.qiyukf.com";
        String sourceTitle=this.getResources().getString(R.string.app_name);
        String custom="custom information string";
        ProductDetail.Builder product = new ProductDetail.Builder();
        product.setTitle(shopTitle);
        product.setDesc(Desc);
        product.setNote(price);
        product.setShow(1);
        product.setUrl(urlGo);
        product.setAlwaysSend(true);
        product.setSendByUser(true);
        product.setActionText("发送");
        product.setActionTextColor(R.color.tables);
        product.setPicture(image);


        ConsultSource source = new ConsultSource(sourceUrl, sourceTitle, custom);
        source.groupId=groupId;
        source.productDetail=product.build();
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
        }
        userInfo.data=show;
        Unicorn.setUserInfo(userInfo);
    }

    @Override
    public void back() {
        if(!tagR.endsWith("ok")){
            finish();
        }
    }
    //进入商品详情页面
    @Override
    public void GoodsComeIn() {
        ThisPageSize=0;
        if(ThisPages==0) {
            if (HeadY > 0) {
                top_tab.setVisibility(View.VISIBLE);
                goods_return_btn.setVisibility(View.VISIBLE);
                goods_share.setVisibility(View.VISIBLE);
                goods_return_btn.setImageResource(R.mipmap.normal_back_icon);
                goods_share.setImageResource(R.mipmap.normal_share_icon);
            } else {
                top_tab.setVisibility(View.GONE);
                goods_return_btn.setImageResource(R.mipmap.circular_back_icon);
                goods_share.setImageResource(R.mipmap.circular_share_icon);
            }
        }else{
            top_tab.setVisibility(View.GONE);
            goods_return_btn.setVisibility(View.GONE);
            goods_share.setVisibility(View.GONE);
        }
    }
    //进入图文详情页面
    @Override
    public void comeInGraphic() {
        top_tab.setVisibility(View.VISIBLE);
        goods_return_btn.setVisibility(View.VISIBLE);
        goods_share.setVisibility(View.VISIBLE);
        goods_return_btn.setImageResource(R.mipmap.normal_back_icon);
        goods_share.setImageResource(R.mipmap.normal_share_icon);
        ThisPageSize=1;
    }
    //进入评价页面
    @Override
    public void comeinComments() {
        ThisPageSize=2;
        top_tab.setVisibility(View.VISIBLE);
        goods_return_btn.setVisibility(View.VISIBLE);
        goods_share.setVisibility(View.VISIBLE);
        goods_return_btn.setImageResource(R.mipmap.normal_back_icon);
        goods_share.setImageResource(R.mipmap.normal_share_icon);
    }

    @Override
    public void one() {
        goods_return_btn.setVisibility(View.VISIBLE);
        goods_share.setVisibility(View.VISIBLE);
        ThisPageSize=0;
        ThisPages=0;
    }

    @Override
    public void two() {
        goods_return_btn.setVisibility(View.GONE);
        goods_share.setVisibility(View.GONE);
        top_tab.setVisibility(View.GONE);
        ThisPageSize=3;
        ThisPages=1;
    }

    @Override
    public void getSlideShowHome(Banner.BodyBean.ContentBean.ListBean bean) {

    }

    @Override
    public void getStartAd(Banner.BodyBean.ContentBean.ListBean bean) {

    }

    @Override
    public void ShopCarNumber(int number) {
        if (number!= 0) {
            if (badgeView == null) {
                badgeView = BadgeFactory.createOvalBig2(this).bind(car_goods);
            }
            badgeView.setBadgeCount(number);
        }
        LogPrint.printError("购物车数量啦啦啦："+number);
    }

    @Override
    public void noAd() {

    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }
        @Override
        public int getCount() {
            return listFragment.size();
        }
        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


    /**
     * 选中的tab位置
     */
    @Override
    public void select() {
        tablayout.getTabAt(2).select();
    }

    @Override
    public void byebye() {
        if(!tagR.endsWith("ok")){
            finish();
        }
    }

    @Override
    public void scroDistans(int y) {
        HeadY=y;
        //获取状态栏的高度
        int mHeight= SingleOverAll.getInstance().getStatusBarHeight1(ZApplication.getContext());
        if (y <= 0) {//未滑动
            if(ThisPageSize==0) {
                if(ThisPages==0) {
                    top_tab.setVisibility(View.GONE);
                    goods_return_btn.setVisibility(View.VISIBLE);
                    goods_share.setVisibility(View.VISIBLE);
                    goods_return_btn.setImageResource(R.mipmap.circular_back_icon);
                    goods_share.setImageResource(R.mipmap.circular_share_icon);
                    top_tab.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
                }
            }
        } else if (y > 0 && y <= mHeight) { //滑动过程中 并且在mHeight之内
            float scale = (float) y / mHeight;
            float alpha = (255 * scale);
            if(ThisPageSize==0) {
                if(ThisPages==0) {
                    top_tab.setVisibility(View.VISIBLE);
                    goods_return_btn.setVisibility(View.VISIBLE);
                    goods_share.setVisibility(View.VISIBLE);
                    goods_return_btn.setImageResource(R.mipmap.normal_back_icon);
                    goods_share.setImageResource(R.mipmap.normal_share_icon);
                    top_tab.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                }
            }
        } else {//超过mHeight
            if(ThisPageSize==0) {
                if(ThisPages==0) {
                    top_tab.setVisibility(View.VISIBLE);
                    goods_return_btn.setVisibility(View.VISIBLE);
                    goods_share.setVisibility(View.VISIBLE);
                    goods_return_btn.setImageResource(R.mipmap.normal_back_icon);
                    goods_share.setImageResource(R.mipmap.normal_share_icon);
                    top_tab.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                }
            }
        }
    }
    //返回
    @Override
    public void returnMethod() {
        finish();
    }
}
