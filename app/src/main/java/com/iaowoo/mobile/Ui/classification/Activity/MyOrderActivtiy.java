package com.iaowoo.mobile.Ui.classification.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.H5toAndroid.JsMethod2;
import com.iaowoo.mobile.H5toAndroid.modle.WECHATEPAY;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.OnclicKRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.ShopMainRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.shopItemRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.youhuiquanRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.BroadcastCallBack;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.GlobalBroadcastReceiver;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Model.ActivtiyModel;
import com.iaowoo.mobile.Ui.classification.Model.AddressM;
import com.iaowoo.mobile.Ui.classification.Model.CoupRed;
import com.iaowoo.mobile.Ui.classification.Model.JS;
import com.iaowoo.mobile.Ui.classification.Model.OrderOk;
import com.iaowoo.mobile.Ui.classification.Model.RedBaoM;
import com.iaowoo.mobile.Ui.classification.Model.RelayItem;
import com.iaowoo.mobile.Ui.classification.Model.RelayPrice;
import com.iaowoo.mobile.Ui.classification.Model.SearchGoods;
import com.iaowoo.mobile.Ui.classification.Model.WeeXAddress;
import com.iaowoo.mobile.Ui.classification.Model.YouHuiQuan;
import com.iaowoo.mobile.Ui.classification.Presenter.MyOrderProsenter;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Weex.WeexActicity;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.alertCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
 * @Description: ${todo}(我的订单页面)
 * @date 2018/11/27
 * @email ${18011009889@163.com}
 */
public class MyOrderActivtiy extends BaseBufferActivity implements MyOrderProsenter.AllCallBack, OnclicKRecycleAdapter, shopItemRecycleAdapter.callBackok {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.shops_recycler)
    RecyclerView shops_recycler;
    @BindView(R.id.yu_e)
    ImageView yu_e;
    @BindView(R.id.wechat_ok)
    ImageView wechat_ok;
    @BindView(R.id.zhifubao_ok)
    ImageView zhifubao_ok;
    @BindView(R.id.all_price)
    TextView all_price;
    @BindView(R.id.free)
    TextView free;
    @BindView(R.id.nohaveaddress)
    TextView nohaveaddress;
    @BindView(R.id.haveAddress)
    LinearLayout haveAddress;
    /**
     * 地址数据
     */
    private List<AddressM.BodyBean.ContentBean> contentBeansAdrress;

    private SearchGoods.BodyBean.ContentBean.SubTemplateInfoListBean thisSub;

    private List<SearchGoods.BodyBean.ContentBean.SubTemplateInfoListBean> subTemplateInfoListBeans;
    /**
     * 计算数据的返回数据
     */
    private List<JS.BodyBean.ContentBean.CouponRedParamBean> jisuan;

    private int min, max, chooseNumber;

    private String miaoshu, guige;
    /**
     * 用户是否有默认地址
     */
    private boolean HaveAddress = false;
    private SoftReference<DialogUtils> dialogUtilsSoftReference;
    /**
     * 全局广播
     */
    private GlobalBroadcastReceiver globalBroadcastReceiver = null;


    private ShopMainRecycleAdapter shopItemRecycleAdapters;

    private MyOrderProsenter myOrderProsenter;

    private double redBao = 0;

    /**
     * 只会多算一次
     */
    private boolean sun_sun = true;

    /**
     * 支付总价格
     */
    private double allPayCount;

    /**
     * 券id
     */
    private String CouponId = "";

    /**
     * 券的金额
     */
    private double CouponAmount = 0;

    /**
     * 红包的金额
     */
    private double RedAmount = 0;

    /**
     * 券最大使用金额
     */
    private double CouponLimitAmount = 0;

    /**
     * 邀请码
     */
    private String InviteCode = "";
    /**
     * 默认支付方式  默认为余额支付
     */
    private int payType = 6;
    /**
     * 默认地址id
     */
    private String deliveryId = "";
    /**
     * 默认地址的联系人手机号码
     */
    private String recipientsPhone;
    /**
     * 默认地址的联系人
     */
    private String recipientsName;
    /**
     * 默认地址的详细地址
     */
    private String recipientsAddress;
    /**
     * 加密后的密码
     */
    private String passwordJM = "";

    private String activityId;

    private SoftReference<JsMethod2> jsMethod;

    private String orderID;

    private Timer timer;

    /**
     * 1 ：表示红包显示为该使用值  0：表示为使用红包为0置空
     */
    private double chooseRedZero = 1;


    /**
     * 优惠券
     */
    private List<YouHuiQuan.BodyBean.ContentBean.ListBean> listBeans1;

    @Override
    public int getLayoutResId() {
        return R.layout.my_order_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        //注册evenbus
        EventBus.getDefault().register(this);
        dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
        ZApplication.ALL_TAG_I = 0;
        //默认为支付方式为   余额支付
        yu_e.setVisibility(View.VISIBLE);
        wechat_ok.setVisibility(View.VISIBLE);
        zhifubao_ok.setVisibility(View.VISIBLE);
        yu_e.setImageResource(R.mipmap.submit_orders_select_icon);
        wechat_ok.setImageResource(R.mipmap.submit_orders_unselect_icon);
        zhifubao_ok.setImageResource(R.mipmap.submit_orders_unselect_icon);
        //接收上个页面传入的数据
        thisSub = (SearchGoods.BodyBean.ContentBean.SubTemplateInfoListBean) getIntent().getSerializableExtra("shops");
        min = getIntent().getIntExtra("min", 0);
        max = getIntent().getIntExtra("max", 0);
        miaoshu = getIntent().getStringExtra("miaoshu");
        guige = getIntent().getStringExtra("guige");
        chooseNumber = getIntent().getIntExtra("chooseNumber", 0);
        activityId = getIntent().getStringExtra("activityId");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shops_recycler.setLayoutManager(layoutManager);
        shops_recycler.setItemAnimator(new DefaultItemAnimator());
        shops_recycler.setNestedScrollingEnabled(false);
        shopItemRecycleAdapters = new ShopMainRecycleAdapter(this, glideUtils, this);
        shopItemRecycleAdapters.setOnclicKRecycleAdapter(this);
        jsMethod = new SoftReference<>(new JsMethod2());
        //注册广播
        pulianpuBroadCast();
    }

    @Override
    protected void initData() {
        super.initData();
        myOrderProsenter = new MyOrderProsenter(this);
        //有多少件商品数据
        subTemplateInfoListBeans = new ArrayList<>();
        subTemplateInfoListBeans.add(thisSub);
        //计算所有商品的价格
        jisuanPrice();
        //获取活动数据
        myOrderProsenter.getActivity(thisSub.getTemplateId());
        //获取默认地址信息
        myOrderProsenter.getMorenAddress();
        //获取红包余额信息
        myOrderProsenter.getRedBao();
        //获取优惠券信息
        myOrderProsenter.getYouHui();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (globalBroadcastReceiver != null) {
            unregisterReceiver(globalBroadcastReceiver);
        }
        myOrderProsenter = null;
        if (timer != null) {
            timer.cancel();
        }
    }

    @OnClick({R.id.back, R.id.address_go_layout, R.id.yue_pay, R.id.wechat_pay, R.id.zhifubao_pay, R.id.go_buy})
    public void onclick(View view) {
        switch (view.getId()) {
            //返回
            case R.id.back:
                finish();
                break;
            //地址点击事件
            case R.id.address_go_layout:
                LogPrint.printError("地址点击");
                //没有默认地址去添加
                Intent mintent = new Intent(this, WeexActicity.class);
                mintent.putExtra("weexUrl", ConfigH5Url.ADDRESS);
                startActivity(mintent);
                break;
            //余额支付
            case R.id.yue_pay:
                yu_e.setVisibility(View.VISIBLE);
                wechat_ok.setVisibility(View.VISIBLE);
                zhifubao_ok.setVisibility(View.VISIBLE);
                yu_e.setImageResource(R.mipmap.submit_orders_select_icon);
                wechat_ok.setImageResource(R.mipmap.submit_orders_unselect_icon);
                zhifubao_ok.setImageResource(R.mipmap.submit_orders_unselect_icon);
                payType = 6;
                break;
            //微信支付
            case R.id.wechat_pay:
                yu_e.setVisibility(View.VISIBLE);
                wechat_ok.setVisibility(View.VISIBLE);
                zhifubao_ok.setVisibility(View.VISIBLE);
                yu_e.setImageResource(R.mipmap.submit_orders_unselect_icon);
                wechat_ok.setImageResource(R.mipmap.submit_orders_select_icon);
                zhifubao_ok.setImageResource(R.mipmap.submit_orders_unselect_icon);
                payType = 1;
                break;
            //支付宝支付
            case R.id.zhifubao_pay:
                yu_e.setVisibility(View.VISIBLE);
                wechat_ok.setVisibility(View.VISIBLE);
                zhifubao_ok.setVisibility(View.VISIBLE);
                yu_e.setImageResource(R.mipmap.submit_orders_unselect_icon);
                wechat_ok.setImageResource(R.mipmap.submit_orders_unselect_icon);
                zhifubao_ok.setImageResource(R.mipmap.submit_orders_select_icon);
                payType = 2;
                break;
            //立即购买
            case R.id.go_buy:
                //有收货地址
                if (HaveAddress) {
                    //余额支付弹出支付密码框
                    if (payType == 6) {
                        if (!TextUtils.isEmpty(PrefManager.getInstance().getPayPassword())) {
                            //没有设置过密码
                            if (PrefManager.getInstance().getPayPassword().endsWith("0")) {
                                if (dialogUtilsSoftReference.get() != null) {
                                    dialogUtilsSoftReference.get().AlertDilog(this, "操作提示！", "还没设置支付密码是否前去设置？", "确认", "取消", new alertCallBack() {
                                        @Override
                                        public void OnOk() {
                                            startActivity(SettingPayPasswordActivity.class);
                                        }

                                        @Override
                                        public void OnNo() {
                                            LogPrint.printError("取消");
                                        }
                                    });
                                } else {
                                    dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                                    dialogUtilsSoftReference.get().AlertDilog(this, "操作提示！", "还没设置支付密码是否前去设置？", "确认", "取消", new alertCallBack() {
                                        @Override
                                        public void OnOk() {
                                            startActivity(SettingPayPasswordActivity.class);
                                        }

                                        @Override
                                        public void OnNo() {
                                            LogPrint.printError("取消");
                                        }
                                    });
                                }
                            } else {
                                createPasswordPayDialog();
                            }
                        } else {
                            if (dialogUtilsSoftReference.get() != null) {
                                dialogUtilsSoftReference.get().AlertDilog(this, "操作提示！", "还没设置支付密码是否前去设置？", "去设置", "暂不", new alertCallBack() {
                                    @Override
                                    public void OnOk() {
                                        startActivity(SettingPayPasswordActivity.class);
                                    }

                                    @Override
                                    public void OnNo() {
                                        LogPrint.printError("取消");
                                    }
                                });
                            } else {
                                dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                                dialogUtilsSoftReference.get().AlertDilog(this, "操作提示！", "还没设置支付密码是否前去设置？", "确认", "取消", new alertCallBack() {
                                    @Override
                                    public void OnOk() {
                                        startActivity(SettingPayPasswordActivity.class);
                                    }

                                    @Override
                                    public void OnNo() {
                                        LogPrint.printError("取消");
                                    }
                                });
                            }
                        }
                    } else {
                        orderOK();
                    }
                } else {
                    //没有收货地址
                    ToastUtilsAll.getInstance().showShortToast("亲！请先选择收货地址");
                }
                break;
        }
    }

    private void setData() {
        LogPrint.printError("当前状态：" + ZApplication.ALL_TAG_I);
        if (ZApplication.ALL_TAG_I == 0) {
            if (contentBeansAdrress != null) {
                //设置默认地址
                if (contentBeansAdrress.size() == 0) {
                    HaveAddress = false;
                    haveAddress.setVisibility(View.GONE);
                    nohaveaddress.setVisibility(View.VISIBLE);
                } else {
                    HaveAddress = true;
                    haveAddress.setVisibility(View.VISIBLE);
                    nohaveaddress.setVisibility(View.GONE);
                    for (int i = 0; i < contentBeansAdrress.size(); i++) {
                        if (contentBeansAdrress.get(i).getIsDefault() == 1) {
                            deliveryId = contentBeansAdrress.get(i).getDeliveryId();
                            recipientsPhone = contentBeansAdrress.get(i).getRecipientsMobilePhone();
                            recipientsName = contentBeansAdrress.get(i).getRecipientsName();
                            recipientsAddress = contentBeansAdrress.get(i).getRecipientsProvince() + contentBeansAdrress.get(i).getRecipientsCity() + contentBeansAdrress.get(i).getRecipientsDistrict() + contentBeansAdrress.get(i).getRecipientsDetailAddress();
                            name.setText("" + contentBeansAdrress.get(i).getRecipientsName());
                            phone.setText("" + contentBeansAdrress.get(i).getRecipientsMobilePhone());
                            address.setText("" + recipientsAddress);
                        }
                    }
                }
            } else {
                HaveAddress = false;
                haveAddress.setVisibility(View.GONE);
                nohaveaddress.setVisibility(View.VISIBLE);
            }

            shopItemRecycleAdapters.SetData(subTemplateInfoListBeans, min, max, chooseNumber, guige, miaoshu, redBao, chooseRedZero, CouponAmount, RedAmount);
            shopItemRecycleAdapters.setPay(jisuan, allPayCount);
            shopItemRecycleAdapters.setListYouHuiQuan(listBeans1);
            shops_recycler.setAdapter(shopItemRecycleAdapters);
            all_price.setText("" + UtilsAll.DoubleTo_2(allPayCount));
        }
    }


    /**
     * 我的默认地址
     *
     * @param contentBeans
     */
    @Override
    public void Address(List<AddressM.BodyBean.ContentBean> contentBeans) {
        contentBeansAdrress = contentBeans;
        setData();
    }

    /**
     * 获取剩余红包
     *
     * @param contentBean
     */
    @Override
    public void getRedBao(RedBaoM.BodyBean.ContentBean contentBean) {
        redBao = contentBean.getBalance();
        setData();
    }

    /**
     * 暂时不需要
     *
     * @param relayItem
     */
    @Override
    public void mount(RelayItem relayItem) {
//        all_price.setText(""+relayItem.getJson().get(0).getTotalSellPrice());
//        allPayCount=relayItem.getJson().get(0).getTotalSellPrice();
//        LogPrint.printError("实际付款额度"+relayItem.getJson().get(0).getTotalSellPrice());
    }

    /**
     * 获取所有的可以使用的优惠券
     *
     * @param listBeans
     */
    @Override
    public void getYouHuiQuan(List<YouHuiQuan.BodyBean.ContentBean.ListBean> listBeans) {
        this.listBeans1 = listBeans;
        setData();
    }

    /**
     * 计算价格
     *
     * @param list
     * @param payCount
     */
    @Override
    public void jisun(List<JS.BodyBean.ContentBean.CouponRedParamBean> list, double payCount) {
        allPayCount = payCount;
        jisuan = list;
        if (sun_sun) {
            jisuanPrice();
            sun_sun = false;
        }
        setData();
    }

    @Override
    public void activity(ActivtiyModel.BodyBean bodyBean) {
        setData();
    }

    @Override
    public void Order(OrderOk.BodyBean.ContentBean orderBean) {
        orderID = orderBean.getOrderId();
        if (payType == 6) {
            LogPrint.printError("订单号：" + orderBean.getOrderString());
            Intent mintent = new Intent(MyOrderActivtiy.this, PaySuccessfulActivity.class);
            mintent.putExtra("orderid", orderID);
            startActivity(mintent);
            finish();
        } else if (payType == 1) {//微信
            LogPrint.printError("微信：" + orderBean.getOrderString());
            //保存type支付结束回调给H5
            PrefManager.getInstance().savePayType("1");
            if (!TextUtils.isEmpty(orderBean.getOrderString())) {
                WECHATEPAY wechatepay = JSON.parseObject(orderBean.getOrderString(), WECHATEPAY.class);
                if (jsMethod.get() != null) {
                    jsMethod.get().wechatPay(wechatepay.getAppid(), wechatepay.getNoncestr(), wechatepay.getPackageX(), wechatepay.getPartnerid(), wechatepay.getPrepayid(), wechatepay.getSign(), wechatepay.getTimestamp());
                } else {
                    jsMethod = new SoftReference<JsMethod2>(new JsMethod2());
                    jsMethod.get().wechatPay(wechatepay.getAppid(), wechatepay.getNoncestr(), wechatepay.getPackageX(), wechatepay.getPartnerid(), wechatepay.getPrepayid(), wechatepay.getSign(), wechatepay.getTimestamp());
                }
            }
        } else if (payType == 2) {//支付宝
            LogPrint.printError("支付宝：" + orderBean.getOrderString());
            //保存type支付结束回调给H5
            LogPrint.printError("H5选择支付宝支付（拼够和充值）" + orderBean.getOrderString());
            PrefManager.getInstance().savePayType("2");
            if (!TextUtils.isEmpty(orderBean.getOrderString())) {
                if (jsMethod.get() != null) {
                    jsMethod.get().Alipay(this, orderBean.getOrderString());
                } else {
                    jsMethod = new SoftReference<JsMethod2>(new JsMethod2());
                    jsMethod.get().Alipay(this, orderBean.getOrderString());
                }
            }
        }
    }

    /**
     * EventBus消息处理方法。
     *
     * @param eventBusMessageWeex
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageWeex eventBusMessageWeex) {
        if (eventBusMessageWeex.getTagName().endsWith("returnWithOptions")) {
            if (!TextUtils.isEmpty(eventBusMessageWeex.getMsg())) {
                WeeXAddress weeXAddress = JSON.parseObject(eventBusMessageWeex.getMsg(), WeeXAddress.class);
                if (weeXAddress != null) {
                    haveAddress.setVisibility(View.VISIBLE);
                    nohaveaddress.setVisibility(View.GONE);
                    HaveAddress = true;
                    recipientsPhone = weeXAddress.getAddress().getRecipientsName();
                    recipientsName = weeXAddress.getAddress().getRecipientsMobilePhone();
                    deliveryId = weeXAddress.getAddress().getDeliveryId();
                    recipientsAddress = weeXAddress.getAddress().getRecipientsProvince() + weeXAddress.getAddress().getRecipientsCity() + weeXAddress.getAddress().getRecipientsDistrict() + weeXAddress.getAddress().getRecipientsDetailAddress();
                    name.setText("" + weeXAddress.getAddress().getRecipientsName());
                    phone.setText("" + weeXAddress.getAddress().getRecipientsMobilePhone());
                    address.setText("" + recipientsAddress);
                }
            }
        }
    }
    /**
     * EventBus消息处理方法。
     * @param eventBusMessageRefresh
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageRefresh eventBusMessageRefresh) {
        if(eventBusMessageRefresh.getTag()==886){
            //获取默认地址信息
            myOrderProsenter.getMorenAddress();
        }
    }

    /**
     * 优惠券点击
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        alertYouhuiquan();
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }


    /**
     * 下单
     */
    private void orderOK() {
        RelayPrice relayPrice = new RelayPrice();
        relayPrice.setActivityId(activityId);
        relayPrice.setBuyNumber(chooseNumber);
        relayPrice.setSubTemplateId(thisSub.getSubTemplateId());
        relayPrice.setInviteCode(InviteCode);
        List<RelayPrice> relayPrices = new ArrayList<>();
        relayPrices.add(relayPrice);
        /**
         *
         *实体转为json
         */
        String s = JSONObject.toJSONString(relayPrices);

        CoupRed coupRed = new CoupRed();
        coupRed.setActivityId(activityId);
        coupRed.setCouponAmount(CouponAmount);
        coupRed.setCouponId(CouponId);
        coupRed.setRedAmount(RedAmount);
        coupRed.setCouponLimitAmount(CouponLimitAmount);
        List<CoupRed> coupReds = new ArrayList<>();
        coupReds.add(coupRed);
        /**
         *
         *实体转为json
         */
        String d = JSONObject.toJSONString(coupReds);
        myOrderProsenter.PlaceTheOrder(s, d, deliveryId, recipientsAddress, recipientsPhone, recipientsName, payType, passwordJM, "");
    }


    /**
     * 优惠券点击弹框
     */
    private void alertYouhuiquan() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.youhuiquan_layout, null);// 得到加载view
        FrameLayout youhuiquan_layout_main = v.findViewById(R.id.youhuiquan_layout_main);
        RecyclerView youhuiquan_recycle = v.findViewById(R.id.youhuiquan_recycle);
        RelativeLayout close = v.findViewById(R.id.close);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        youhuiquan_recycle.setLayoutManager(layoutManager);
        youhuiquan_recycle.setItemAnimator(new DefaultItemAnimator());


        final Dialog loadingDialog = new Dialog(this, R.style.MyDialogStyle_plp);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(true); // 点击加载框以外的区域
        loadingDialog.setContentView(youhuiquan_layout_main, new LinearLayout.LayoutParams(
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
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CouponId = "";
                CouponAmount = 0;
                CouponLimitAmount = 0;
                chooseRedZero = 1;
                RedAmount = 0;
                shopItemRecycleAdapters.reUse = 0;
                jisuanPrice();
                loadingDialog.cancel();
            }
        });
        //优惠券选择
        youhuiquanRecycleAdapter youhuiquanRecycleAdapter1 = new youhuiquanRecycleAdapter(this, glideUtils, new OnclicKRecycleAdapter() {
            @Override
            public void onItemClick(int position) {
                CouponId = listBeans1.get(position).getCouponRecorId();
                CouponAmount = listBeans1.get(position).getFaceValue();
                CouponLimitAmount = listBeans1.get(position).getLimitValue();
                if (RedAmount != 0) {
                    RedAmount = 0;
                    chooseRedZero = 0;
                    ToastUtilsAll.getInstance().showShortToast("亲红包和优惠券不能同时使用！！");
                    shopItemRecycleAdapters.SetData(subTemplateInfoListBeans, min, max, chooseNumber, guige, miaoshu, redBao, chooseRedZero, CouponAmount, RedAmount);
                    shopItemRecycleAdapters.notifyDataSetChanged();
                }
                jisuanPrice();
                loadingDialog.cancel();
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });
        youhuiquanRecycleAdapter1.SetData(listBeans1, allPayCount);
        youhuiquan_recycle.setAdapter(youhuiquanRecycleAdapter1);
    }

    /**
     * 余额支付框
     */
    private void createPasswordPayDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.password_layout, null);// 得到加载view
        RelativeLayout layout = v.findViewById(R.id.layout_ok);// 加载布局
        final EditText edite = v.findViewById(R.id.edite);
        RelativeLayout cancle = v.findViewById(R.id.cancle);
        RelativeLayout ok_queren = v.findViewById(R.id.ok_queren);
        //延迟200毫秒展示系统键盘
        timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(edite, 0);
                           }
                       },
                200);
        final Dialog loadingDialog = new Dialog(this, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        edite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.cancel();
            }
        });
        ok_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.cancel();
                LogPrint.printError(">>>>>>>加密前：" + edite.getText().toString());
                try {
                    String str1 = UtilsAll.encryptionPassword(PrefManager.getInstance().getMobile(), edite.getText().toString());
                    String s = str1.replaceAll("\n", "");
                    String password_new = URLEncoder.encode(s, "UTF-8");
                    passwordJM = password_new;
                    LogPrint.printError(">>>>>>>加密后：" + passwordJM);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                orderOK();
            }
        });
    }

    /**
     * 计算最终价格
     */
    private void jisuanPrice() {
        RelayPrice relayPrice = new RelayPrice();
        relayPrice.setActivityId(activityId);
        relayPrice.setBuyNumber(chooseNumber);
        relayPrice.setSubTemplateId(thisSub.getSubTemplateId());
        relayPrice.setInviteCode(InviteCode);
        List<RelayPrice> relayPrices = new ArrayList<>();
        relayPrices.add(relayPrice);
        /**
         *
         *实体转为json
         */
        String s = JSONObject.toJSONString(relayPrices);

        CoupRed coupRed = new CoupRed();
        coupRed.setActivityId(activityId);
        coupRed.setCouponAmount(CouponAmount);
        coupRed.setCouponId(CouponId);
        coupRed.setRedAmount(RedAmount);
        coupRed.setCouponLimitAmount(CouponLimitAmount);
        List<CoupRed> coupReds = new ArrayList<>();
        coupReds.add(coupRed);
        /**
         *
         *实体转为json
         */
        String d = JSONObject.toJSONString(coupReds);
        myOrderProsenter.getJisuan(s, d);
    }


    /**
     * 商品加减
     *
     * @param numbersCallNo
     */
    @Override
    public void numberCall(int numbersCallNo) {
        chooseNumber = numbersCallNo;
        jisuanPrice();
    }

    /**
     * 选择红包
     *
     * @param redEd
     */
    @Override
    public void redCount(double redEd) {
        RedAmount = redEd;
        if (!TextUtils.isEmpty(CouponId) || CouponAmount != 0) {
            chooseRedZero = 1;
            ToastUtilsAll.getInstance().showShortToast("亲红包和优惠券不能同时使用！！");
            CouponId = "";
            CouponAmount = 0;
        }
        jisuanPrice();
    }

    /**
     * 广播
     */
    public void pulianpuBroadCast() {
        //注册全局广播
        globalBroadcastReceiver = new GlobalBroadcastReceiver(new BroadcastCallBack() {
            @Override
            public void ReceiverData(String tag, String data) {
                switch (tag) {
                    case "paySucess":
                        if (data.equals("0")) {
                            //微信支付成功
                            LogPrint.printError("微信支付成功");
                            Intent mintent = new Intent(MyOrderActivtiy.this, PaySuccessfulActivity.class);
                            mintent.putExtra("orderid", orderID);
                            startActivity(mintent);
                            finish();
                        } else {
                            //支付宝支付成功
                            LogPrint.printError("支付宝支付成功");
                            Intent mintent = new Intent(MyOrderActivtiy.this, PaySuccessfulActivity.class);
                            mintent.putExtra("orderid", orderID);
                            startActivity(mintent);
                            finish();
                        }
                        break;
                    case "payFaild":
                        //微信支付失败
                        if (data.equals("0")) {
                            //微信支付失败
                            LogPrint.printError("微信支付失败");
                            UtilsAll.GoWeexAll(mContext, ConfigH5Url.ORDER_ER_INDEX + "?type=2", "", "");
                            finish();
                        } else {
                            //支付宝支付失败
                            LogPrint.printError("支付宝支付失败");
                            UtilsAll.GoWeexAll(mContext, ConfigH5Url.ORDER_ER_INDEX + "?type=2", "", "");
                            finish();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RdioBroadCast.BOARD);
        registerReceiver(globalBroadcastReceiver, intentFilter);
    }
}
