package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Presenter.EearthFragmentPresenter;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.BigRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.SearchRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Model.Code;
import com.iaowoo.mobile.Ui.classification.Model.FenLei;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.EearthFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.LogPrint;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
 * @Description: ${todo}(商品页面)
 * @date 2018/9/18
 * @email ${18011009889@163.com}
 */
public class ShopActivity extends  BaseUpDownActivity implements EearthFragmentPresenter.EearthCallBack {
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

    @BindView(R.id.earth_recycler_view)
    WRecyclerView earth_recycler_view;

    @BindView(R.id.title_shop)
    TextView title_shop;

    private String categroy="";

    private FenLei fenLei;

    private EearthFragmentPresenter earthFragmentProsenter = null;

    private BigRecycleAdapter bigRecycleAdapter = null;

    private SearchRecycleAdapter searchRecycleAdapter = null;


    private boolean priceUp = true;


    private boolean grideAndList = true;


    private String orderBy = "", orderType = "desc", queryType = "common";


    @Override
    public int getLayoutResId() {
        return R.layout.shop_activity;
    }

    @Override
    public void getDatas(int curPageIndex, int pageSize) {
        earthFragmentProsenter.getBigType(categroy, queryType, orderBy, orderType, curPageIndex, pageSize);
    }

    @Override
    protected void initView() {
        super.initView();
        this.initState(R.id.ll_bar);
        title_shop.setText(""+getIntent().getStringExtra("title_name"));
//        if(!TextUtils.isEmpty(getIntent().getStringExtra("categroy"))) {
//            String  categroys= getIntent().getStringExtra("categroy");
//            Code code= JSON.parseObject(categroys,Code.class);
//            categroy=code.getCode();
//        }
        categroy= getIntent().getStringExtra("categroy");
        //默认综合
        z_h_txt.setTextColor(getResources().getColor(R.color.andoridMain));
        x_l_txt.setTextColor(getResources().getColor(R.color.txt_666666));
        x_p_txt.setTextColor(getResources().getColor(R.color.txt_666666));
        j_g_txt.setTextColor(getResources().getColor(R.color.txt_666666));
        jg_img.setImageDrawable(getResources().getDrawable(R.mipmap.price_default));
//        this.setViewMarginTop(earth_top);
        tl_top.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initDatas();
//                categroy= (String) tab.getTag();
                earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中了tab的逻辑
                LogPrint.printError("未选中了tab的逻辑");

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中了tab的逻辑
                LogPrint.printError("再次选中了tab的逻辑");
            }
        });

        //清空数据
        initDatas();
        this.grideShow(earth_recycler_view,2);
        this.initSwipeRefreshView1(earth_swiperefreshlayout);

        imge_c.setImageDrawable(getResources().getDrawable(R.mipmap.gray_list_vertical_icon));


    }

    @Override
    protected void initData() {
        super.initData();
        earthFragmentProsenter=new EearthFragmentPresenter(this);
        earthFragmentProsenter.setCallBack(this);
//        earthFragmentProsenter.getFenLei();
        bigRecycleAdapter=new BigRecycleAdapter(this,null,"");
        searchRecycleAdapter=new SearchRecycleAdapter(this,null,"");

        earthFragmentProsenter.getBigType(categroy,queryType,orderBy,orderType,curPageIndex,pageSize);
    }

    @Override
    public void noHaveDatas() {

    }

    @Override
    protected void refreshOk() {
        if(grideAndList) {
            bigRecycleAdapter.notifyDataSetChanged();
        }else{
            searchRecycleAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.retun_shop,R.id.search_item_earth,R.id.zh,R.id.xl,R.id.xp,R.id.jg,R.id.hs})
    public void onclick(View view){
        switch (view.getId()){
            case  R.id.retun_shop:
                finish();
                break;
            case R.id.search_item_earth:
                LogPrint.printError("搜索");
                startActivity(new Intent(this, SearchOneActivity.class));
                overridePendingTransition(0, 0);
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
        }

    }
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        earthFragmentProsenter=null;
    }
}
