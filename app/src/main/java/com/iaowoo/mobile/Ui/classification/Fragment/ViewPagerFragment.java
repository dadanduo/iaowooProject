package com.iaowoo.mobile.Ui.classification.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Presenter.EearthFragmentPresenter;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.ScanActicvity;
import com.iaowoo.mobile.Ui.classification.Activity.SearchOneActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.BaseViewPagerFragmentAdapter;
import com.iaowoo.mobile.Ui.classification.Model.FenLei;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.ClassInfoPresenter;
import com.iaowoo.mobile.Ui.classification.Presenter.EearthFragmentPresenter;
import com.iaowoo.mobile.Utils.LogPrint;

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
 * @Description: ${todo}(viewpager_fragment页面)
 * @date 2018/12/28
 * @email ${18011009889@163.com}
 */
public class ViewPagerFragment extends BaseBufferFragment implements EearthFragmentPresenter.EearthCallBack {
    @BindView(R.id.tablayout_fragment)
    TabLayout tablayout;
    @BindView(R.id.viewpager_fragment)
    ViewPager viewpager;
    @BindView(R.id.search_top)
    LinearLayout search_top;
    private  FenLei fenLei;
    /**
     *
     */
    private List<Fragment> listFragment;
    /**
     *
     */
    private BaseViewPagerFragmentAdapter baseViewPagerFragment;

    /**
     * 分类数据
     */
    private EearthFragmentPresenter earthFragmentProsenter=null;
    /**
     *
     */
    private String[] titles;

    /**
     *引用tablayout
     */
    ArrayList<String> tabList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.view_pager_xml;
    }
    @Override
    protected void initView() {
        super.initView();
        //设置距离顶部
        this.setViewMarginTop(search_top);
        //获取分类的数据
        earthFragmentProsenter=new EearthFragmentPresenter(getActivity());
        earthFragmentProsenter.setCallBack(this);
        earthFragmentProsenter.getFenLei();

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void Fenlei(FenLei fenLei) {
        this.fenLei=fenLei;
        int size= fenLei.getBody().getContent().size();
        tablayout.addTab( tablayout.newTab().setTag("").setText("精选"));
        titles=new String[size+1];
        titles[0]="精选";
        tabList.add("精选");
        HomeFragment homeFragment=new HomeFragment();
        //页面，数据源
        listFragment = new ArrayList<>();
        listFragment.add(homeFragment);
        for(int i=0;i<size;i++){
            EearthFragment earthFragment=new EearthFragment();
            String className=fenLei.getBody().getContent().get(i).getName();
            String cateID=fenLei.getBody().getContent().get(i).getCode();
            titles[i+1]=className;
            tabList.add(className);
            earthFragment.setClassName(cateID);
            tablayout.addTab(tablayout.newTab().setTag(fenLei.getBody().getContent().get(i).getCode()).setText(""+fenLei.getBody().getContent().get(i).getName()));
            listFragment.add(earthFragment);
        }
        //ViewPager的适配器
        baseViewPagerFragment = new BaseViewPagerFragmentAdapter(getChildFragmentManager(),listFragment,titles);
        //解决viewpager+fragment的生命周期混乱问题
        viewpager.setOffscreenPageLimit(size);
        viewpager.setAdapter(baseViewPagerFragment);
        //绑定
        tablayout.setupWithViewPager(viewpager);

        for (int i = 0; i <  tablayout.getTabCount(); i++) {
            TabLayout.Tab tab =  tablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
        }
        updateTabTextView(tablayout.getTabAt(0), true);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabTextView(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabTextView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private View getTabView(int currentPosition) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab_item, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_item_textview);
        textView.setText(tabList.get(currentPosition));
        return view;
    }
    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {
        if (isSelect) {
            //选中加粗
            TextView tabSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tabSelect.setTextSize(18);
            tabSelect.setText(tab.getText());
        } else {
            TextView tabUnSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setTextSize(14);
            tabUnSelect.setText(tab.getText());
        }
    }

    /**
     * 没啥卵用
     * @param shops
     */
    @Override
    public void Shops(List<Shop.BodyBean.ContentBean.ListBean> shops) {

    }
    /**
     * 没啥卵用
     */
    @Override
    public void NohaveData() {
    }


    @OnClick({R.id.search_item,R.id.classification,R.id.message_img})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.search_item:
                LogPrint.printError("搜索");
                startActivity(new Intent(getActivity(), SearchOneActivity.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            //扫码
            case  R.id.classification:
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
            //签到
            case R.id.message_img:
                break;
        }
    }
}
