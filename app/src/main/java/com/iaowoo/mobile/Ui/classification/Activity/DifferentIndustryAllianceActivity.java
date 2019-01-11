//package com.iaowoo.mobile.Ui.classification.Activity;
//
//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.amap.api.maps.MapView;
//import com.hedgehog.ratingbar.RatingBar;
//import com.iaowoo.mobile.Ui.classification.Presenter.DifferentIndustryAlliancePresenter;
//import com.iaowoo.mobile.Utils.LoadDialog;
//import com.iaowoo.mobile.Utils.LogPrint;
//import com.iaowoo.mobile.Utils.TranslateUtils;
//import com.iaowoo.mobile.Utils.UtilsAll;
//import com.iaowoo.mobile.common.ConfigH5Url;
//import com.iaowoo.mobile.interfaceCallback.DiapCallBack;
//import com.iaowoo.mobile.Controller.Single.SingleOverAll;
//import com.iaowoo.mobile.R;
//import com.iaowoo.mobile.Ui.classification.Adapter.ShopRecycleAdapter;
//import com.iaowoo.mobile.Ui.classification.Model.NearShops;
//import com.iaowoo.mobile.Ui.classification.Model.PARTPOINT;
//import com.iaowoo.mobile.Ui.classification.Presenter.DifferentIndustryAlliancePresenter;
//import com.iaowoo.mobile.Utils.LoadDialog;
//import com.iaowoo.mobile.Utils.LogPrint;
//import com.iaowoo.mobile.Utils.TranslateUtils;
//import com.iaowoo.mobile.Utils.UtilsAll;
//import com.iaowoo.mobile.common.ConfigH5Url;
//import com.iaowoo.mobile.interfaceCallback.DiapCallBack;
//import com.plp.underlying.networkframwork.OkhttpManager;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
///**
// * Created by chenda on 2018/4/17.
// * 异业联盟地图
// */
//public class DifferentIndustryAllianceActivity extends BaseBufferActivity implements  ShopRecycleAdapter.OnRecyclerViewListener ,DiapCallBack {
//    private MapView mapView;
//    //recycle布局
//    @BindView(R.id.shoplistview)
//    LinearLayout shoplistview;
//    @BindView(R.id.text_shop)
//    TextView text_shop;
//    @BindView(R.id.shop_recycle)
//    RecyclerView shop_recycle;
//    @BindView(R.id.choose_shop)
//    RelativeLayout choose_shop;
//    @BindView(R.id.show_go_on)
//    LinearLayout show_go_on;
//    @BindView(R.id.choose_shop_one)
//    RelativeLayout choose_shop_one;
//    @BindView(R.id.text_shop_one)
//    TextView text_shop_one;
//    @BindView(R.id.close_map)
//    RelativeLayout close_map;
//    @BindView(R.id.search_go)
//    LinearLayout search_go;
//    @BindView(R.id.shopItems)
//    LinearLayout shopItems;
//    @BindView(R.id.lookMore)
//    RelativeLayout lookMore;
//
//    @BindView(R.id.shop_name_de)
//    TextView shop_name_de;
//
//    @BindView(R.id.ratingbar_map)
//    RatingBar ratingbar;
//
//    @BindView(R.id.address)
//    TextView address;
//
//    @BindView(R.id.phone)
//    TextView phone;
//
//    @BindView(R.id.time)
//    TextView time;
//
//    private boolean isclick=true;
//
//    ShopRecycleAdapter shopRecycleAdapter=null;
//    private DifferentIndustryAlliancePresenter differentIndustryAlliancePresenter=null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mapView=findViewById(R.id.map_shop);
//        mapView.onCreate(savedInstanceState);
//        close_map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        search_go.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//        allState();
//        differentIndustryAlliancePresenter=new DifferentIndustryAlliancePresenter(DifferentIndustryAllianceActivity.this,mapView);
//        differentIndustryAlliancePresenter.start();
//        differentIndustryAlliancePresenter.setInterface(this);
//
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.different_night_go_on;
//    }
//
//
//    /**
//     *
//     * 加载附近的门店
//     * @param nearShops
//     */
//    public void data( NearShops nearShops)
//    {
//        shop_recycle.setLayoutManager(new LinearLayoutManager(this));
//        //添加Android自带的分割线
////        shop_recycle.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        //设置Adapter
//        shopRecycleAdapter =new ShopRecycleAdapter(nearShops,this);
//        shopRecycleAdapter.setOnRecyclerViewListener(this);
//        shop_recycle.setAdapter( shopRecycleAdapter);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//        differentIndustryAlliancePresenter.onresumeMethod();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//        differentIndustryAlliancePresenter.ondestory();
//        differentIndustryAlliancePresenter=null;
//    }
//    @Override
//    public void onItemClick(int position) {
//        //商户详情页面
//    }
//
//    @Override
//    public boolean onItemLongClick(int position) {
//        return false;
//    }
//
//    @Override
//    public void show_alert() {
//        show_go_on.setVisibility(View.VISIBLE);
//        show_go_on.setAnimation(TranslateUtils.moveToViewLocation());
//        choose_shop_one.setVisibility(View.GONE);
//        isclick=true;
//        LogPrint.printError("显示");
//    }
//
//    @Override
//    public void hide_alert() {
//        shopItems.setVisibility(View.GONE);
//        if(isclick)
//        {
//            show_go_on.setVisibility(View.GONE);
//            show_go_on.setAnimation(TranslateUtils.moveToViewBottom());
//            choose_shop_one.setVisibility(View.VISIBLE);
//            isclick=false;
//        }
//    }
//
//    @Override
//    public void push_data_to_a(PARTPOINT partpoint) {
//
//
//    }
//
//
//
//    @Override
//    public void push_data_to_d(NearShops nearShops) {
//        if(nearShops!=null) {
//            if (nearShops.getCode().equals(OkhttpManager.SUCESS)) {
//                if (nearShops.getBody().getContent().getList().size() == 0) {
//                    text_shop.setText("为你找到附近0家商户");
//                    text_shop_one.setText("为你找到附近0家商户");
//                    if (shopRecycleAdapter != null) {
//                        shopRecycleAdapter.notifyDataSetChanged();
//                    }
//                } else {
//                    text_shop.setText("为你找到附近" + nearShops.getBody().getContent().getList().size() + "家商户");
//                    text_shop_one.setText("为你找到附近" + nearShops.getBody().getContent().getList().size() + "家商户");
//                    data(nearShops);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void showdilog() {
//        showDialog();
//    }
//
//    /**
//     * 展示商户的详情
//     * @param listBean
//     */
//    @Override
//    public void showItem(final NearShops.BodyBean.ContentBean.ListBean listBean) {
//        shopItems.setVisibility(View.VISIBLE);
//        show_go_on.setVisibility(View.GONE);
//
//        shop_name_de.setText(""+listBean.getMerchantName());
//        ratingbar.setStar(Float.parseFloat(listBean.getAvgScore()+""));
//        address.setText("地址："+listBean.getDetailAddress());
//        phone.setText("联系方式："+listBean.getTelephone());
//        if(TextUtils.isEmpty(listBean.getBusinessTime())){
//            time.setText("营业时间：无");
//        }else{
//            time.setText("营业时间："+listBean.getBusinessTime());
//        }
//        lookMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UtilsAll.GoWebviewAll(mContext, ConfigH5Url.merchant_r_d(listBean.getTemplateId()+""));
//            }
//        });
//
//    }
//
//    /**
//     * 友好提示dialog
//     */
//    private void showDialog()
//    {
//        LoadDialog loadDialog =  LoadDialog.getInstance();
//        loadDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.load_dialog);
//        LoadDialog.getInstance().show(DifferentIndustryAllianceActivity.this.getSupportFragmentManager(),"");
//    }
//    public void Homing(View view)
//    {
//        differentIndustryAlliancePresenter.clickRefresh();
//    }
//
//    @OnClick({R.id.lookMore})
//    public void onclick(View view){
//
//        switch (view.getId()){
//            case  R.id.lookMore:
//
//                break;
//
//        }
//
//    }
//}
