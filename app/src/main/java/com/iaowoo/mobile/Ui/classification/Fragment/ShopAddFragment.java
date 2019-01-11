//package com.iaowoo.mobile.Ui.classification.Fragment;
//
//import android.Manifest;
//import android.app.Dialog;
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.bigkoo.convenientbanner.ConvenientBanner;
//import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
//import com.iaowoo.mobile.Application.ZApplication;
//import com.iaowoo.mobile.Controller.Single.PrefManager;
//import com.iaowoo.mobile.EvenBus.EventBusMessageCity;
//import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
//import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
//import com.iaowoo.mobile.ScottMap.PositionEntity;
//import com.iaowoo.mobile.ScottMap.RegeocodeGetAddress;
//import com.iaowoo.mobile.ScottMap.RegeocodeTask;
//import com.iaowoo.mobile.Ui.classification.Presenter.ShopAddFragementPresenter;
//import com.iaowoo.mobile.Utils.DialogUtils;
//import com.iaowoo.mobile.Utils.LocationApiUtils;
//import com.iaowoo.mobile.Utils.LogPrint;
//import com.iaowoo.mobile.Utils.PressionUtils.PermissionListener;
//import com.iaowoo.mobile.Utils.PressionUtils.PermissionsUtil;
//import com.iaowoo.mobile.common.ConfigFlag;
//import com.iaowoo.mobile.interfaceCallback.OnLocationGetAddressGetListener;
//import com.iaowoo.mobile.interfaceCallback.OnLocationGetListener;
//import com.iaowoo.mobile.interfaceCallback.alertCallBack;
//import com.iaowoo.mobile.Application.ZApplication;
//import com.iaowoo.mobile.Controller.Single.PrefManager;
//import com.iaowoo.mobile.EvenBus.EventBusMessageCity;
//import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
//import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
//import com.iaowoo.mobile.R;
//import com.iaowoo.mobile.ScottMap.PositionEntity;
//import com.iaowoo.mobile.ScottMap.RegeocodeGetAddress;
//import com.iaowoo.mobile.ScottMap.RegeocodeTask;
//import com.iaowoo.mobile.Ui.classification.Activity.CityActivity;
//import com.iaowoo.mobile.Ui.classification.Activity.DifferentIndustryAllianceActivity;
//import com.iaowoo.mobile.Ui.classification.Adapter.ItemClickInterface;
//import com.iaowoo.mobile.Ui.classification.Adapter.NearRecycleAdapter;
//import com.iaowoo.mobile.Ui.classification.Adapter.NearShopsRecycleAdapter;
//import com.iaowoo.mobile.Ui.classification.Model.Banner;
//import com.iaowoo.mobile.Ui.classification.Model.NearShops;
//import com.iaowoo.mobile.Ui.classification.Model.SJLMFL;
//import com.iaowoo.mobile.Ui.classification.Presenter.ShopAddFragementPresenter;
//import com.iaowoo.mobile.Ui.classification.View.ImageViewHolder;
//import com.iaowoo.mobile.Ui.classification.View.recyclerview.Mylayout;
//import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
//import com.iaowoo.mobile.Utils.DialogUtils;
//import com.iaowoo.mobile.Utils.LocationApiUtils;
//import com.iaowoo.mobile.Utils.LogPrint;
//import com.iaowoo.mobile.Utils.PressionUtils.PermissionListener;
//import com.iaowoo.mobile.Utils.PressionUtils.PermissionsUtil;
//import com.iaowoo.mobile.common.ConfigFlag;
//import com.iaowoo.mobile.interfaceCallback.OnLocationGetAddressGetListener;
//import com.iaowoo.mobile.interfaceCallback.OnLocationGetListener;
//import com.iaowoo.mobile.interfaceCallback.alertCallBack;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
///**
// * ////////////////////////
// * //  ┏┓　　　┏┓///////////
// * //┏┛┻━━━┛┻┓ ////////////
// * //┃　　　　　　　┃     ////
// * //┃　　　━　　　┃     ////
// * //┃　┳┛　┗┳　┃       /////
// * //┃　　　　　　　┃     ////
// * //┃　　　┻　　　┃         //
// * //┃　　　　　　　┃        ///
// * //┗━┓　　　┏━┛           ///
// * //    ┃　　　┃   神兽保佑  ///
// * //    ┃　　　┃   代码无BUG！///
// * //    ┃　　　┗━━━┓     ///
// * //    ┃　　　　　　　┣┓ ///
// * //    ┃　　　　　　　┏┛ ///
// * //    ┗┓┓┏━┳┓┏┛      ///
// * //      ┃┫┫　┃┫┫     ///
// * ///////////////////////
// *
// * @author ${chenda}
// * @version V1.0
// * @Description: ${todo}(商家联盟)
// * @date 2018/8/27
// * @email ${18011009889@163.com}
// */
//public class ShopAddFragment extends BaseFragment implements LocationApiUtils.LocationCallBack, OnLocationGetListener, ShopAddFragementPresenter.ShopCallBack {
//    @BindView(R.id.location_text)
//    TextView location_text;
//    @BindView(R.id.swiperefreshlayout)
//    SwipeRefreshLayout swiperefreshlayout;
//    @BindView(R.id.recyclerview)
//    WRecyclerView recyclerview;
//    @BindView(R.id.shop_title)
//    LinearLayout shop_title;
//    @BindView(R.id.shop_margin_title)
//    RelativeLayout shop_margin_title;
//    @BindView(R.id.dotHaveShops)
//    RelativeLayout dotHaveShops;
//    @BindView(R.id.show_shops)
//    LinearLayout show_shops;
//    /**
//     * 轮播数据
//     */
//    private List<String> LurC = null;
//
//    public double la_this=0,lo_this=0;
//
//    public double la_no,lo_no;
//    /**
//     * 搜索范围默认为2000
//     */
//    private int distance = 30000;
//    /**
//     * 根据类别搜索
//     */
//    private String categoryCode = "";
//    /**
//     * 附近数据 智能排序
//     */
//    private List<String> NearList = null;
//    /**
//     * 门店分类数据
//     */
//    private List<String> NearShop = null;
//    /**
//     * 门店分类数据的code
//     */
//    private List<String> NearShopCode = null;
//    /**
//     * 定位辅助工具
//     */
//    private LocationApiUtils locationApiUtils = null;
//    /**
//     * presenter
//     */
//    private ShopAddFragementPresenter shopAddFragementProsenter = null;
//    /**
//     * adpter
//     */
//    private NearShopsRecycleAdapter nearShopsRecycleAdapter = null;
//    /**
//     * 逆地理编码
//     */
//    private RegeocodeTask regeocodeTask = null;
//    /**
//     * 轮播图
//     */
//    private ConvenientBanner mcb_s_j_l_m;
//
//
//    private String cityName_this;
//
//    private boolean fj=true,qb=true;
//
//
//    private boolean F_J=true;
//
//    /**
//     * 门店分类数据
//     */
//    private SJLMFL sjlmfl;
//
//    private String cityname="";
//
//    private Dialog showT;
//
//
//    private TextView fj_show,qb_show;
//
//
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.s_j_l_m;
//    }
//
//
//    @Override
//    public void getDatas(int curPageIndex, int pageSize) {
////        location();
//        shopAddFragementProsenter.getNearShops(lo_this, la_this, curPageIndex, pageSize, distance, categoryCode);
//    }
//
//    /**
//     * 没有数据
//     */
//    @Override
//    public void noHaveDatas() {
//    }
//
//    /**
//     *
//     */
//    @Override
//    protected void initView() {
//        super.initView();
//        regeocodeTask = new RegeocodeTask(getActivity());
//        regeocodeTask.setOnLocationGetListener(this);
//        locationApiUtils = new LocationApiUtils();
//        locationApiUtils.setInterface(this);
//        initDatas();
//        listShow(recyclerview);
//        this.listShow(recyclerview);
//        this.initSwipeRefreshView(swiperefreshlayout);
//        this.setViewMarginTop(shop_margin_title);
//        shopAddFragementProsenter = new ShopAddFragementPresenter(getActivity(), this);
//
//        nearShopsRecycleAdapter = new NearShopsRecycleAdapter(getActivity(), new ItemClickInterface() {
//            @Override
//            public void onItemClick(Object Item, int position) {
//
//            }
//        });
//
//        //头部
//        View view = LayoutInflater.from(getActivity()).inflate(R.layout.s_j_b_j, null);
//        fj_show=view.findViewById(R.id.fj_show);
//        qb_show=view.findViewById(R.id.qb_show);
//        //智能排序
//        RelativeLayout z_n_p_x = view.findViewById(R.id.z_n_p_x);
//        mcb_s_j_l_m = view.findViewById(R.id.mcb_s_j_l_m);
//        //附近
//        RelativeLayout f_j = view.findViewById(R.id.f_j);
//        f_j.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Near();
//                F_J=true;
//                showT=shopFragmentAlert(cityname,0);
//            }
//        });
//        //全部
//        final RelativeLayout q_b = view.findViewById(R.id.q_b);
//        q_b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                all();
//                F_J=false;
//                showT= shopFragmentAlert(cityname,1);
//
//            }
//        });
//        z_n_p_x.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                znpx();
//                showT= shopFragmentAlert(cityname,1);
//
//            }
//        });
//        nearShopsRecycleAdapter.addHeaderView(view);
//        EventBus.getDefault().register(this);
//
//
//        RegeocodeGetAddress regeocodeGetAddress=new RegeocodeGetAddress(getActivity());
//        //城市不为空
//        if(!TextUtils.isEmpty(PrefManager.getInstance().getCitysName())){
//            regeocodeGetAddress.search_Use_address(PrefManager.getInstance().getCitysName());
//            cityname=PrefManager.getInstance().getCitysName();
//            location_text.setText("" + PrefManager.getInstance().getCitysName());
//        }
//        //逆地理编码
//        regeocodeGetAddress.setonLocationGetAddressGetListener(new OnLocationGetAddressGetListener() {
//            @Override
//            public void onLocationAddressGet(PositionEntity entity) {
//                lo_this=entity.longitude;
//                la_this=entity.latitue;
//                la_no=entity.latitue;
//                lo_no=entity.longitude;
//                PrefManager.getInstance().saveLa(entity.latitue+"");
//                PrefManager.getInstance().saveLo(entity.longitude+"");
//                LogPrint.printError("保存城市的经度："+entity.longitude+"维度："+entity.latitue+"");
//                shopAddFragementProsenter.getNearShops(lo_this, la_this, curPageIndex, pageSize, distance, categoryCode);
//                //检测权限
//                requestLoacation();
//            }
//            @Override
//            public void onRegeAddresscodeGet(PositionEntity entity) {
//            }
//        });
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//        NearList = new ArrayList<>();
//        NearShop = new ArrayList<>();
//        NearShopCode = new ArrayList<>();
//        shopAddFragementProsenter.getSlideShow();
//        shopAddFragementProsenter.getSJFL();
//    }
//    /**
//     * EventBus消息处理方法。
//     *
//     * @param eventBusMessageCity
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onShowMessageEvent(EventBusMessageCity eventBusMessageCity) {
//        cityname=eventBusMessageCity.getCityName();
//        location_text.setText("" + eventBusMessageCity.getCityName());
//        PrefManager.getInstance().savecityName(eventBusMessageCity.getCityName());
//        RegeocodeGetAddress regeocodeGetAddress = new RegeocodeGetAddress(getActivity());
//        regeocodeGetAddress.search_Use_address(eventBusMessageCity.getCityName());
//        regeocodeGetAddress.setonLocationGetAddressGetListener(new OnLocationGetAddressGetListener() {
//            @Override
//            public void onLocationAddressGet(PositionEntity entity) {
//                LogPrint.printError("传递经度:" + entity.longitude + "维度：" + entity.latitue);
//                initDatas();
//                lo_this = entity.longitude;
//                la_this = entity.latitue;
//                shopAddFragementProsenter.getNearShops(lo_this, la_this, curPageIndex, pageSize, distance, categoryCode);
//            }
//            @Override
//            public void onRegeAddresscodeGet(PositionEntity entity) {
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//    }
//
//    /**
//     * 定位
//     */
//    private void location() {
//        if (locationApiUtils != null) {
//            //开始定位
//            locationApiUtils.startLo();
//        }
//    }
//
//    /**
//     * 点击事件
//     * @param view
//     */
//    @OnClick({R.id.map, R.id.back_search_two})
//    public void click(View view) {
//        switch (view.getId()) {
//            case R.id.map:
//                startActivity(new Intent(getActivity(), DifferentIndustryAllianceActivity.class));
//                break;
//            case R.id.back_search_two:
//                requestPermissionWrite();
//                break;
//            default:
//                break;
//        }
//    }
//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        LogPrint.printError("停止定位");
//        locationApiUtils.stopLo();
//
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//
//    /**
//     * 定位结果
//     * @param la
//     * @param lo
//     */
//    @Override
//    public void location(double la, double lo) {
//        //保存维度
//        PrefManager.getInstance().saveLa(la + "");
//        //保存经度
//        PrefManager.getInstance().saveLo(lo + "");
//        this.lo_this = lo;
//        this.la_this = la;
//        //开始逆地理编码获取城市
//        regeocodeTask.search(la, lo);
//        //定位成功取得值后取消定位
//        locationApiUtils.stopLo();
//    }
//
//
//    @Override
//    public void onLocationGet(PositionEntity entity) {
//
//    }
//    @Override
//    public void onRegecodeGet(final PositionEntity entity) {
//        //定位城市和之前的城市不一致开始提示
//        LogPrint.printError("之前保存的城市"+PrefManager.getInstance().getCitysName());
//        if(!entity.cityName.contains(PrefManager.getInstance().getCitysName())){
//            DialogUtils dialogUtils=new DialogUtils();
//            dialogUtils.AlertDilog(getActivity(), "温馨提示", "你当前定位城市为" + entity.cityName + "你确定切换到"+entity.cityName+"吗？", "确定", "取消", new alertCallBack() {
//                @Override
//                public void OnOk() {
//                    location_text.setText("" + entity.cityName);
//                    cityname=entity.cityName;
//                    LogPrint.printError("确定"+entity.cityName);
//                    PrefManager.getInstance().savecityName(entity.cityName);
//                    initDatas();
//                    shopAddFragementProsenter.getNearShops(lo_this, la_this, curPageIndex, pageSize, distance, categoryCode);
//                    LogPrint.printError("定位成功后的经度："+lo_this+"维度："+la_this);
//                }
//                @Override
//                public void OnNo() {
//                    LogPrint.printError("取消");
//                    la_this=la_no;
//                    lo_this=lo_no;
//                }
//            });
//
//        }else{
//            initDatas();
//            shopAddFragementProsenter.getNearShops(lo_this, la_this, curPageIndex, pageSize, distance, categoryCode);
//        }
//    }
//
//
//    @Override
//    public void onLocationLL(double la, double lo) {
//    }
//
//    @Override
//    protected void refreshOk() {
//        super.refreshOk();
//        nearShopsRecycleAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void NearM(NearShops nearShops) {
//        dotHaveShops.setVisibility(View.GONE);
//        //有数据隐藏附近没有门店提示layout
//        this.setData(true);
//        this.stopRefreshAndLoading();
//        if(recyclerview!=null) {
//            //允许上拉加载功能
//            recyclerview.setPullLoadEnable(true);
//            LogPrint.printError("总共条数:" + nearShops.getBody().getContent().getList().size());
//            int size = nearShops.getBody().getContent().getList().size();
//            //获取加载的分页总数据
//            for (int i = 0; i < size; i++) {
//                datas.add(nearShops.getBody().getContent().getList().get(i));
//            }
//            //第一页数据加载adpter
//            if (First) {
//                nearShopsRecycleAdapter.setData(datas);
//                recyclerview.setAdapter(nearShopsRecycleAdapter);
//                First = false;
//            }
//            //第二次直接刷新
//            else {
//                nearShopsRecycleAdapter.setData(datas);
//                nearShopsRecycleAdapter.notifyDataSetChanged();
//            }
//        }
//    }
//    /**
//     * 没有数据
//     */
//    @Override
//    public void NoShops() {
//        setData(false);
//        if (datas != null) {
//            if (datas.size() == 0) {
//                nearShopsRecycleAdapter.setData(null);
//                recyclerview.setAdapter(nearShopsRecycleAdapter);
//                dotHaveShops.setVisibility(View.VISIBLE);
//            } else {
//                dotHaveShops.setVisibility(View.GONE);
//            }
//        } else {
//            dotHaveShops.setVisibility(View.VISIBLE);
//        }
//        this.stopRefreshAndLoading();
//    }
//    /**
//     * 轮播图
//     *
//     * @param banner
//     */
//    @Override
//    public void BannerNearShop(Banner banner) {
//        dotHaveShops.setVisibility(View.GONE);
//        int size = banner.getBody().getContent().getList().size();
//        //轮播图
//        LurC = new ArrayList<>();
//        //添加轮播数据
//        for (int i = 0; i < size; i++) {
//            LurC.add(banner.getBody().getContent().getList().get(i).getBannerImg());
//        }
//        if (LurC.size() > 1) {
//            //轮播图配置
//            mcb_s_j_l_m.setPages(new CBViewHolderCreator<ImageViewHolder>() {
//                @Override
//                public ImageViewHolder createHolder() {
//                    return new ImageViewHolder();
//                }
//            }, LurC)
//                    .setPageIndicator(new int[]{R.mipmap.turn_page_unselected_icon, R.mipmap.turn_page_selected_icon}) //设置两个点作为指示器
//                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL); //设置指示器的方向水平居中
//            mcb_s_j_l_m.startTurning(ConfigFlag.TIME_DI);
//        } else {
//            //轮播图配置
//            mcb_s_j_l_m.setPages(new CBViewHolderCreator<ImageViewHolder>() {
//                @Override
//                public ImageViewHolder createHolder() {
//                    return new ImageViewHolder();
//                }
//            }, LurC); //设置指示器的方向水平居中
//
//        }
//        mcb_s_j_l_m.setOnItemClickListener(new com.bigkoo.convenientbanner.listener.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//            }
//        });
//    }
//    /**
//     * 门店分类数据
//     *
//     * @param sjlmfl
//     */
//    @Override
//    public void ClassShops(SJLMFL sjlmfl) {
//        this.sjlmfl = sjlmfl;
//        int size = sjlmfl.getBody().getContent().size();
//        NearShop.clear();
//        NearShop.add("全部");
//        NearShopCode.add("");
//        for (int i = 0; i < size; i++) {
//            NearShop.add(sjlmfl.getBody().getContent().get(i).getName());
//            NearShopCode.add(sjlmfl.getBody().getContent().get(i).getCode());
//        }
//    }
//
//    /**
//     * 读取权限
//     */
//    public  void requestPermissionWrite() {
//        PermissionsUtil.requestPermission(ZApplication.getContext(), new PermissionListener() {
//            @Override
//            public void permissionGranted(@NonNull String[] permissions) {
//                startActivity(new Intent(getActivity(), CityActivity.class));
//            }
//            @Override
//            public void permissionDenied(@NonNull String[] permissions) {
//
//            }
//        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//    }
//
//    /**
//     * 定位权限申请
//     */
//    public void requestLoacation() {
//        PermissionsUtil.requestPermission(ZApplication.getInstance().getApplicationContext(), new PermissionListener() {
//            @Override
//            public void permissionGranted(@NonNull String[] permissions) {
//                //定位到当前城市区域
//                location();
//            }
//            @Override
//            public void permissionDenied(@NonNull String[] permissions) {
//                LogPrint.printError("定位权限被拒绝了");
//            }
//        }, Manifest.permission.ACCESS_FINE_LOCATION);
//    }
//
//
//    /**
//     * 附近
//     */
//    private void Near(RecyclerView recycler_list,TextView f_j_t,TextView  q_b_t) {
////        z_n_p_x_t.setTextColor(getResources().getColor(R.color.marge_3333));
//        f_j_t.setTextColor(getResources().getColor(R.color.andoridMain));
//        q_b_t.setTextColor(getResources().getColor(R.color.marge_3333));
//        NearList.clear();
//        NearList.add("附近");
//        NearList.add("1km");
//        NearList.add("3km");
//        NearList.add("5km");
//        NearList.add("10km");
//        NearList.add("30km");
//        dotHaveShops.setVisibility(View.GONE);
//        NearRecycleAdapter nearRecycleAdapter = new NearRecycleAdapter(getActivity(), NearList, new ItemClickInterface() {
//            @Override
//            public void onItemClick(Object Item, int position) {
//                showT.cancel();
//                switch (NearList.get(position)) {
//                    case "附近":
//                        distance = 2000;
//                        fj_show.setText("附近");
//                        break;
//                    case "1km":
//                        distance = 1000;
//                        fj_show.setText("1km");
//                        break;
//                    case "3km":
//                        distance = 3000;
//                        fj_show.setText("3km");
//                        break;
//                    case "5km":
//                        distance = 5000;
//                        fj_show.setText("5km");
//                        break;
//                    case "10km":
//                        distance = 10000;
//                        fj_show.setText("10km");
//                        break;
//                    case "30km":
//                        distance = 30000;
//                        fj_show.setText("30km");
//                        break;
//                }
//                initDatas();
//                shopAddFragementProsenter.getNearShops(lo_this, la_this, curPageIndex, pageSize, distance, categoryCode);
//                LogPrint.printError(NearList.get(position));
//            }
//        });
//        recycler_list.setAdapter(nearRecycleAdapter);
//    }
//
//    /**
//     * 全部
//     */
//    private void all(RecyclerView recycler_list,TextView f_j_t,TextView  q_b_t) {
////        z_n_p_x_t.setTextColor(getResources().getColor(R.color.marge_3333));
//        f_j_t.setTextColor(getResources().getColor(R.color.marge_3333));
//        q_b_t.setTextColor(getResources().getColor(R.color.andoridMain));
//        initDatas();
//        NearRecycleAdapter nearRecycleAdapter = new NearRecycleAdapter(getActivity(), NearShop, new ItemClickInterface() {
//            @Override
//            public void onItemClick(Object Item, int position) {
//                categoryCode=NearShopCode.get(position);
//                showT.cancel();
//                shopAddFragementProsenter.getNearShops(lo_this, la_this, curPageIndex, pageSize, distance, categoryCode);
//                LogPrint.printError(NearShop.get(position));
//                qb_show.setText(NearShop.get(position));
//
//
//            }
//        });
//        recycler_list.setAdapter(nearRecycleAdapter);
//    }
//
//    /**
//     * 智能排序
//     */
//    private void znpx(RecyclerView recycler_list,TextView z_n_p_x_t,TextView f_j_t,TextView  q_b_t) {
//        z_n_p_x_t.setTextColor(getResources().getColor(R.color.andoridMain));
//        f_j_t.setTextColor(getResources().getColor(R.color.marge_3333));
//        q_b_t.setTextColor(getResources().getColor(R.color.marge_3333));
//        initDatas();
//        NearList.clear();
//        NearList.add("智能排序");
//        NearList.add("距离最近");
//        NearList.add("评分优先");
//        show_shops.setVisibility(View.GONE);
//        dotHaveShops.setVisibility(View.GONE);
//        NearRecycleAdapter nearRecycleAdapter = new NearRecycleAdapter(getActivity(), NearList, new ItemClickInterface() {
//            @Override
//            public void onItemClick(Object Item, int position) {
//                show_shops.setVisibility(View.VISIBLE);
//                shop_title.getBackground().mutate().setAlpha(0);
//                shopAddFragementProsenter.getNearShops(lo_this, la_this, curPageIndex, pageSize, distance, categoryCode);
//                LogPrint.printError(NearList.get(position));
//            }
//        });
//        recycler_list.setAdapter(nearRecycleAdapter);
//    }
//    /**
//     * EventBus消息处理方法。
//     * @param eventBusMessageRefresh
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onShowMessageEvent(EventBusMessageRefresh eventBusMessageRefresh) {
//        //刷新消息
//        if(eventBusMessageRefresh.getTag()==0){
//        }
//    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden) {
//            recyclerview.scrollToPosition(0);
//            EventBus.getDefault().post(new EventBusMessageShopCar(1));
//        }
//    }
//
//
//    /**
//     * @param
//     * @return
//     */
//    public Dialog shopFragmentAlert(String cityname,int tag) {
//        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        View v = inflater.inflate(R.layout.tab_title, null);// 得到加载view
//        FrameLayout show_shop_time=v.findViewById(R.id.show_shop_time);
//
//        final Dialog loadingDialog = new Dialog(getActivity(), R.style.MyDialogStyle);// 创建自定义样式dialog
//        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
//        loadingDialog.setCanceledOnTouchOutside(true); // 点击加载框以外的区域
//        loadingDialog.setContentView(show_shop_time, new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
//        /**
//         *将显示Dialog的方法封装在这里面
//         */
//        Window window = loadingDialog.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setGravity(Gravity.TOP);
//        window.setAttributes(lp);
//        window.setWindowAnimations(R.style.PopWindowAnimStyle);
//        loadingDialog.show();
//
//
//        //定位
//        TextView location_text=v.findViewById(R.id.location_text);
//        location_text.setText(cityname);
//        //城市点击
//        RelativeLayout back_search_two=v.findViewById(R.id.back_search_two);
//        back_search_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                requestPermissionWrite();
//            }
//        });
//        //地图点击事件
//        RelativeLayout map=v.findViewById(R.id.map);
//        map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), DifferentIndustryAllianceActivity.class));
//            }
//        });
//
//        final RecyclerView recycler_list=v.findViewById(R.id.recycler_list);
//        recycler_list.setHasFixedSize(true);//设置固定大小
//        recycler_list.setItemAnimator(new DefaultItemAnimator());//设置默认动画
//        Mylayout mLayoutManager = new Mylayout(getContext(), LinearLayoutManager.VERTICAL, false);
//        //为recyclerView设置布局管理器
//        recycler_list.setLayoutManager(mLayoutManager);
//
//        final TextView f_j_t=v.findViewById(R.id.f_j_t);
//        final TextView q_b_t=v.findViewById(R.id.q_b_t);
//
//        ImageView q_b_i=v.findViewById(R.id.q_b_i);
//        ImageView f_j_i=v.findViewById(R.id.fj_i);
//        if(tag==0) {
//            Near(recycler_list, f_j_t, q_b_t);
//        }else{
//            all(recycler_list,f_j_t,q_b_t);
//        }
//        //附近
//        RelativeLayout f_j_h=v.findViewById(R.id.f_j_h);
//        f_j_h.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(F_J){
//                    loadingDialog.cancel();
//                }else {
//                    Near(recycler_list, f_j_t, q_b_t);
//                    F_J=true;
//                }
//            }
//        });
//        //全部
//        RelativeLayout q_b_h=v.findViewById(R.id.q_b_h);
//        q_b_h.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!F_J){
//                    loadingDialog.cancel();
//                }else {
//                    all(recycler_list, f_j_t, q_b_t);
//                    F_J=false;
//                }
//            }
//        });
//        return loadingDialog;
//    }
//
//}
