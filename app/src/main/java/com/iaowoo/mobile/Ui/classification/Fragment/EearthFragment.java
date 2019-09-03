package com.iaowoo.mobile.Ui.classification.Fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Presenter.EearthFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.ImageViewHolder;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageShopCar;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.SearchOneActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.BigRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.SearchRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Model.FenLei;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.EearthFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.common.ConfigFlag;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
 * @Description: ${todo}(全球集市)
 * @date 2018/8/30
 * @email ${18011009889@163.com}
 */
public class EearthFragment extends BaseFragment implements EearthFragmentPresenter.EearthCallBack {
    @BindView(R.id.tl_top)
    TabLayout tl_top;
//    @BindView(R.id.viewpager)
//    MyCustomViewPager viewPager;

    @BindView(R.id.imge_c)
    ImageView imge_c;

    @BindView(R.id.earth_top)
    RelativeLayout earth_top;

    @BindView(R.id.zhtxt)
    TextView z_h_txt;

    @BindView(R.id.xltxt)
    TextView x_l_txt;

    @BindView(R.id.xptxt)
    TextView x_p_txt;

    @BindView(R.id.jgtxt)
    TextView j_g_txt;

    @BindView(R.id.jgimg)
    ImageView jg_img;

    @BindView(R.id.list_swiperefreshlayout)
    SmartRefreshLayout earth_swiperefreshlayout;

    @BindView(R.id.earth_recycler_view_f)
    WRecyclerView earth_recycler_view;

    private String categroy;

    private  FenLei fenLei;

    private EearthFragmentPresenter earthFragmentProsenter=null;

    /**
     *grid adapter
     */
    private BigRecycleAdapter bigRecycleAdapter=null;

    /**
     *list adapter
     */
    private SearchRecycleAdapter searchRecycleAdapter = null;



    private boolean  priceUp=true;


    private  boolean grideAndList=true;


    private String orderBy="",orderType="desc",queryType="common";

    /**
     * 设置分类名字
     * @param className
     */
    public void setClassName(String className){
        this.categroy=className;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.main_fragment;
    }

    @Override
    public void getDatas(int curPageIndex, int pageSize) {
        earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);
    }

    @Override
    public void noHaveDatas() {

    }

    @Override
    protected void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        //默认综合
        z_h_txt.setTextColor(getResources().getColor(R.color.andoridMain));
        x_l_txt.setTextColor(getResources().getColor(R.color.txt_666666));
        x_p_txt.setTextColor(getResources().getColor(R.color.txt_666666));
        j_g_txt.setTextColor(getResources().getColor(R.color.txt_666666));
        jg_img.setImageDrawable(getResources().getDrawable(R.mipmap.price_default));
        this.setViewMarginTop(earth_top);
//        tl_top.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                initDatas();
//                categroy= (String) tab.getTag();
//                LogPrint.printError("参数"+categroy);
//                earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                //未选中了tab的逻辑
//                LogPrint.printError("未选中了tab的逻辑");
//
//            }
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                //再次选中了tab的逻辑
//                LogPrint.printError("再次选中了tab的逻辑");
//            }
//        });

        //清空数据
        initDatas();
        this.grideShow(earth_recycler_view,2);
        this.initSwipeRefreshView1(earth_swiperefreshlayout);
        imge_c.setImageDrawable(getResources().getDrawable(R.mipmap.gray_list_vertical_icon));
    }


    @Override
    protected void initData() {
        super.initData();
        earthFragmentProsenter=new EearthFragmentPresenter(getActivity());
        earthFragmentProsenter.setCallBack(this);
        earthFragmentProsenter.getFenLei();
        bigRecycleAdapter=new BigRecycleAdapter(getActivity(),earthFragmentProsenter,categroy);
        searchRecycleAdapter=new SearchRecycleAdapter(getActivity(),earthFragmentProsenter,categroy);
        earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);
    }

    @OnClick({R.id.search_item_earth,R.id.zh,R.id.xl,R.id.xp,R.id.jg,R.id.hs})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.search_item_earth:
                LogPrint.printError("搜索");
                startActivity(new Intent(getActivity(), SearchOneActivity.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.zh:
                First=true;
                initDatas();
                z_h_txt.setTextColor(getResources().getColor(R.color.andoridMain));
                x_l_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                x_p_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                j_g_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                jg_img.setImageDrawable(getResources().getDrawable(R.mipmap.price_default));
                orderBy="";
                orderType="desc";
                queryType="common";
                earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);
                break;
            case R.id.xl:
                First=true;
                initDatas();
                z_h_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                x_l_txt.setTextColor(getResources().getColor(R.color.andoridMain));
                x_p_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                j_g_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                jg_img.setImageDrawable(getResources().getDrawable(R.mipmap.price_default));
                orderBy="sales";
                orderType="desc";
                queryType="";
                earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);

                break;
            case R.id.xp:
                First=true;
                initDatas();
                z_h_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                x_l_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                x_p_txt.setTextColor(getResources().getColor(R.color.andoridMain));
                j_g_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                jg_img.setImageDrawable(getResources().getDrawable(R.mipmap.price_default));
                orderBy="createTime";
                orderType="desc";
                queryType="";
                earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);
                break;
            case R.id.jg:
                z_h_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                x_l_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                x_p_txt.setTextColor(getResources().getColor(R.color.txt_666666));
                j_g_txt.setTextColor(getResources().getColor(R.color.andoridMain));
                //价格从低到高（升序）
                if(priceUp) {
                    First=true;
                    initDatas();
                    priceUp = false;
                    jg_img.setImageDrawable(getResources().getDrawable(R.mipmap.price_up));
                    orderBy="price";
                    orderType="asc";
                    queryType="";
                    earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);
                    //价格高到底（降序）
                } else{
                    First=true;
                    initDatas();
                    priceUp=true;
                    jg_img.setImageDrawable(getResources().getDrawable(R.mipmap.price_down));
                    orderBy="price";
                    orderType="desc";
                    queryType="";
                    earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);

                }
                break;
            case R.id.hs:
                //切换为list
                if(grideAndList){
                    First=true;
                    imge_c.setImageDrawable(getResources().getDrawable(R.mipmap.gray_list_block_icon));
                    this.listShow(earth_recycler_view);
                    grideAndList=false;
                    if(datas!=null){
                        searchRecycleAdapter.setBigData(datas);
                        earth_recycler_view.setAdapter(searchRecycleAdapter);
                    }
                }else{
                    First=true;
                    //切换成gride
                    imge_c.setImageDrawable(getResources().getDrawable(R.mipmap.gray_list_vertical_icon));
                    this.grideShow(earth_recycler_view,2);
                    grideAndList=true;

                    if(datas!=null){
                        bigRecycleAdapter.setBigData(datas);
                        earth_recycler_view.setAdapter(bigRecycleAdapter);
                    }
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //    @Override
//    public void Fenlei(FenLei fenLei) {
//        this.fenLei=fenLei;
//        int size= fenLei.getBody().getContent().size();
//        //解决viewpager+fragment的生命周期混乱问题
//        viewPager.setOffscreenPageLimit(size);
//        titles= new String[size];
//        tl_top.addTab(tl_top.newTab());
//        for(int i=0;i<size;i++){
//            tl_top.addTab(tl_top.newTab());
//            titles[i]=fenLei.getBody().getContent().get(i).getName();
//        }
//        viewPager.setAdapter(new ShareFragmentAdapter(getChildFragmentManager(), titles));
//        tl_top.setupWithViewPager(viewPager);
//    }

    @Override
    public void Fenlei(FenLei fenLei) {
        this.fenLei=fenLei;
        int size= fenLei.getBody().getContent().size();
//        categroy=fenLei.getBody().getContent().get(0).getCode();
//        earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);
//        tl_top.addTab(tl_top.newTab().setTag("").setText("全部商品"));
//        for(int i=0;i<size;i++){
//            tl_top.addTab(tl_top.newTab().setTag(fenLei.getBody().getContent().get(i).getCode()).setText(""+fenLei.getBody().getContent().get(i).getName()));
//        }
    }

    @Override
    protected void refreshOk() {
        super.refreshOk();
        tempY=0;
        if(grideAndList){
            bigRecycleAdapter.notifyDataSetChanged();
        }else{
            searchRecycleAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void Shops(List<Shop.BodyBean.ContentBean.ListBean> shops) {
        this.setData(true);
        this.stopRefreshAndLoading();
        //允许上拉加载功能
        if(earth_recycler_view!=null) {
            earth_recycler_view.setPullLoadEnable(true);
            if (curPageIndex == 1) {
                if (shops.size() < 15) {
                    curPageIndex++;
                    earthFragmentProsenter.getBigType(categroy, queryType, orderBy, orderType, curPageIndex, pageSize);
                }
            }
            //获取加载的分页总数据
            for (int i = 0; i < shops.size(); i++) {
                datas.add(shops.get(i));
            }
            if (First) {
                if (grideAndList) {
                    bigRecycleAdapter.setBigData(datas);
                    earth_recycler_view.setAdapter(bigRecycleAdapter);
                } else {
                    searchRecycleAdapter.setBigData(datas);
                    earth_recycler_view.setAdapter(searchRecycleAdapter);
                }
                First = false;
            } else {
                if (grideAndList) {
                    bigRecycleAdapter.setBigData(datas);
                    bigRecycleAdapter.notifyDataSetChanged();
                } else {
                    searchRecycleAdapter.setBigData(datas);
                    searchRecycleAdapter.notifyDataSetChanged();
                }
            }
        }
    }
    @Override
    public void NohaveData() {
        this.stopRefreshAndLoading();
        if(curPageIndex==1){
            datas.clear();
            if(grideAndList) {
                bigRecycleAdapter.setBigData(datas);
                bigRecycleAdapter.notifyDataSetChanged();
            }else{
                searchRecycleAdapter.setBigData(datas);
                searchRecycleAdapter.notifyDataSetChanged();
            }
        }
    }
    /**
     * EventBus消息处理方法。
     * @param eventBusMessageRefresh
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBusMessageRefresh eventBusMessageRefresh) {
        //刷新消息
        if(eventBusMessageRefresh.getTag()==0){
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            EventBus.getDefault().post(new EventBusMessageShopCar(1));
            earth_recycler_view.scrollToPosition(0);
            LogPrint.printError("进来了");
//            earthFragmentProsenter.getShopCar(PrefManager.getInstance().getToken()==null?"":PrefManager.getInstance().getToken());
        }
    }


}
