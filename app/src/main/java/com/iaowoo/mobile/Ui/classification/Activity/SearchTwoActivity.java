package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Presenter.SearchTwoPresenter;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.SearchGrideRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.SearchRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.SearchTwoPresenter;
import com.iaowoo.mobile.Ui.classification.View.EditText_Phone;
import com.iaowoo.mobile.Ui.classification.View.OnItemClick;
import com.iaowoo.mobile.Ui.classification.View.SearchTipsGroupView;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.LogPrint;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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
 * @Description: ${todo}(搜索第二个界面)
 * @date 2018/8/31
 * @email ${18011009889@163.com}
 */
public class SearchTwoActivity extends BaseUpDownActivity implements SearchTwoPresenter.SearchCallBack {

    @BindView(R.id.et_search_two)
    EditText_Phone et_search_two;

    @BindView(R.id.recycler_view_search)
    WRecyclerView recycler_view_search;

    @BindView(R.id.imge_c)
    ImageView imge_c;

    @BindView(R.id.search_history)
    SearchTipsGroupView search_history;


    @BindView(R.id.list_swiperefreshlayout)
    SmartRefreshLayout list_swiperefreshlayout_search;

    @BindView(R.id.result_L)
    LinearLayout result_L;

    @BindView(R.id.search_L)
    LinearLayout search_L;

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

    @BindView(R.id.title_conditions)
    LinearLayout title_conditions;


    private SearchRecycleAdapter searchRecycleAdapter = null;

    private SearchTwoPresenter searchTwoProsenter = null;

    private SearchGrideRecycleAdapter searchGrideRecycleAdapter = null;

    /**
     * 搜索的关键字
     */
    private String keywordAll = "";

    private List<String> historyItem = null;
    /**
     * 搜索的记录
     */
    private String[] items;

    private String orderBy="",orderType="desc",queryType="common";
    /**
     * 价格升序   或者降序
     */
    private boolean  priceUp=true;


    @Override
    public int getLayoutResId() {
        return R.layout.search_two_activity;
    }

    @Override
    protected void initView() {
        super.initView();
        //默认综合
        z_h_txt.setTextColor(getResources().getColor(R.color.andoridMain));
        x_l_txt.setTextColor(getResources().getColor(R.color.txt_666666));
        x_p_txt.setTextColor(getResources().getColor(R.color.txt_666666));
        j_g_txt.setTextColor(getResources().getColor(R.color.txt_666666));
        jg_img.setImageDrawable(getResources().getDrawable(R.mipmap.price_default));

        imge_c.setImageResource(R.mipmap.gray_list_vertical_icon);


        searchLogic();

        et_search_two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    search_L.setVisibility(View.VISIBLE);
                    result_L.setVisibility(View.GONE);
                    title_conditions.setVisibility(View.GONE);
                    searchLogic();
                }
            }
        });

        //系统键盘搜索的点击
        et_search_two.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //搜索按键action
                    String content = et_search_two.getText().toString();
                    if (!TextUtils.isEmpty(content)) {
                        //历史记录中没有
                        boolean haveNo = true;
                        for (int i = 0; i < historyItem.size(); i++) {
                            if (historyItem.get(i).equals(content)) {
                                //历史记录中已经存在
                                haveNo = false;
                            }
                        }
                        //不存在才保存
                        if (haveNo) {
                            historyItem.add(content);
                            PrefManager.getInstance().setDataList(ZApplication.SEARCH_TAG, historyItem);
                        }
                        SearchThis(content);
                        return true;
                    }
                    return true;
                }
                return false;
            }
        });


        keywordAll = getIntent().getStringExtra("keyword");
        et_search_two.setText(keywordAll);
        //沉浸式
        this.initState(R.id.ll_bar);

        initDatas();

        searchGrideRecycleAdapter = new SearchGrideRecycleAdapter(this);

        searchRecycleAdapter = new SearchRecycleAdapter(this,null,"");

        //gride
        changeHor();

        initSwipeRefreshView1(list_swiperefreshlayout_search);

        searchTwoProsenter = new SearchTwoPresenter(this);
        searchTwoProsenter.setCallBack(this);

        searchTwoProsenter.getShopType("",queryType,orderBy,orderType,curPageIndex, pageSize, keywordAll);

    }


    @Override
    protected void initData() {
        super.initData();
        closeKey();
    }

    @Override
    public void getDatas(int curPageIndex, int pageSize) {
        //开始加载分页数据
        if(searchTwoProsenter!=null) {
            searchTwoProsenter.getShopType("", queryType, orderBy, orderType, curPageIndex, pageSize, keywordAll);
        }
    }

    @Override
    public void noHaveDatas() {

    }

    @Override
    public void refreshOk() {
        if (HENGZHE) {
            searchGrideRecycleAdapter.notifyDataSetChanged();
        } else {
            searchRecycleAdapter.notifyDataSetChanged();
        }

    }

    @OnClick({R.id.search_two,R.id.back_search_two, R.id.click_change,R.id.zh,R.id.xl,R.id.xp,R.id.jg})
    public void click(View view) {
        switch (view.getId()) {
            case  R.id.search_two:
                //搜索按键action
                String content = et_search_two.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    //历史记录中没有
                    boolean haveNo = true;
                    for (int i = 0; i < historyItem.size(); i++) {
                        if (historyItem.get(i).equals(content)) {
                            //历史记录中已经存在
                            haveNo = false;
                        }
                    }
                    //不存在才保存
                    if (haveNo) {
                        historyItem.add(content);
                        PrefManager.getInstance().setDataList(ZApplication.SEARCH_TAG, historyItem);
                    }
                    SearchThis(content);
                }
                break;
            case R.id.back_search_two:
                setResult(2);
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.click_change:
                //横行变竖行
                if (this.HENGZHE) {
                    listshowThis();
                    imge_c.setImageResource(R.mipmap.gray_list_block_icon);
                } else {
                    changeHor();
                    imge_c.setImageResource(R.mipmap.gray_list_vertical_icon);
                }

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
                searchTwoProsenter.getShopType("",queryType,orderBy,orderType,curPageIndex, pageSize, keywordAll);

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
                searchTwoProsenter.getShopType("",queryType,orderBy,orderType,curPageIndex, pageSize, keywordAll);

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
                searchTwoProsenter.getShopType("",queryType,orderBy,orderType,curPageIndex, pageSize, keywordAll);
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
                    searchTwoProsenter.getShopType("",queryType,orderBy,orderType,curPageIndex, pageSize, keywordAll);
                    //价格高到底（降序）
                } else{
                    First=true;
                    initDatas();
                    priceUp=true;
                    jg_img.setImageDrawable(getResources().getDrawable(R.mipmap.price_down));
                    orderBy="price";
                    orderType="desc";
                    queryType="";
                    searchTwoProsenter.getShopType("",queryType,orderBy,orderType,curPageIndex, pageSize, keywordAll);
                }
                break;
        }
    }

    /**
     * 搜索逻辑处理
     */
    private void searchLogic() {
        search_history.removeAllViews();
        historyItem = new ArrayList<>();
        historyItem = PrefManager.getInstance().getDataList(ZApplication.SEARCH_TAG);
        if (historyItem.size() != 0) {
            items = new String[historyItem.size()];
            for (int i = 0; i < historyItem.size(); i++) {
                items[historyItem.size() - i - 1] = historyItem.get(i);
            }
            search_history.initViews(items, new OnItemClick() {
                @Override
                public void onClick(int position) {
                    SearchThis(items[position]);
                    et_search_two.setText(items[position]);
                }
            });
        }
    }
    /**
     * 当前页面的关键字搜索
     * @param content
     */
    private void SearchThis(String content) {
        keywordAll = content;
        closeKey();
        search_L.setVisibility(View.GONE);
        result_L.setVisibility(View.VISIBLE);
        title_conditions.setVisibility(View.VISIBLE);
        First = true;
        initDatas();
        //开始加载分页数据
        searchTwoProsenter.getShopType("",queryType,orderBy,orderType,curPageIndex, pageSize, keywordAll);
    }

    /**
     * 关闭软键盘
     */
    private void closeKey() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    /**
     * 切换成gride的
     */
    private void changeHor() {
        First = true;
        HENGZHE = true;
        this.grideShow(recycler_view_search, 2);

        if(datas!=null) {
            searchGrideRecycleAdapter.setBigData(datas);
            recycler_view_search.setAdapter(searchGrideRecycleAdapter);
        }

    }
    /**
     * 切换成list
     */
    private void listshowThis() {
        this.First = true;
        this.HENGZHE = false;
        this.listShow(recycler_view_search);
        if(datas!=null) {
            searchRecycleAdapter.setBigData(datas);
            recycler_view_search.setAdapter(searchRecycleAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchTwoProsenter=null;
    }



    @Override
    public void Shop(List<Shop.BodyBean.ContentBean.ListBean> shops) {
        //有数据进入
        this.setNoData(true);
        stopRefreshAndLoading();
        //允许上拉加载功能
        if (recycler_view_search != null) {
            recycler_view_search.setPullLoadEnable(true);
            LogPrint.printError("总共条数:" + shops.size());
            //获取加载的分页总数据
            for (int i = 0; i < shops.size(); i++) {
                datas.add(shops.get(i));
            }
            //第一页数据加载adpter
            if (First) {
                if (HENGZHE) {
                    LogPrint.printError("gride");
                    searchGrideRecycleAdapter.setBigData(datas);
                    recycler_view_search.setAdapter(searchGrideRecycleAdapter);
                } else {
                    LogPrint.printError("list");
                    searchRecycleAdapter.setBigData(datas);
                    recycler_view_search.setAdapter(searchRecycleAdapter);
                }
                First = false;
            }
            //第二次直接刷新
            else {
                if (HENGZHE) {
                    searchGrideRecycleAdapter.setBigData(datas);
                    searchGrideRecycleAdapter.notifyDataSetChanged();
                } else {
                    searchRecycleAdapter.setBigData(datas);
                    searchRecycleAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * 数据总页数的回调
     *
     * @param pages
     */
    @Override
    public void allPages(int pages) {
        LogPrint.printError("总共的页数");
    }

    /**
     * 没有数据的回调
     */
    @Override
    public void NoHaveData() {
//        this.setNoData(false);
//        initDatas();
//        if (HENGZHE) {
//            searchGrideRecycleAdapter.setBigData(datas);
//            searchGrideRecycleAdapter.notifyDataSetChanged();
//        } else {
//            searchRecycleAdapter.setBigData(datas);
//            searchRecycleAdapter.notifyDataSetChanged();
//        }
    }
}
