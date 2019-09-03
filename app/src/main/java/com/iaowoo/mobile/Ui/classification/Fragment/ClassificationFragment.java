
package com.iaowoo.mobile.Ui.classification.Fragment;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.SearchOneActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.ClassInfoHomeBarRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.ClassInfo_TwoBarRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.ClasssInfo_TwoBarRecycleAdapter1;
import com.iaowoo.mobile.Ui.classification.Adapter.OnclicKRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.FenLei;
import com.iaowoo.mobile.Ui.classification.Model.ReMenResponse;
import com.iaowoo.mobile.Ui.classification.Presenter.ClassInfoPresenter;
import com.iaowoo.mobile.Utils.LogPrint;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
 * @Description: ${todo}(分类fragment)
 * @date 2019/1/8
 * @email ${18011009889@163.com}
 */
public class ClassificationFragment extends  BaseBufferFragment implements ClassInfoPresenter.ClassInfoCallBack {
    @BindView(R.id.left_recycle)
    RecyclerView left_recycle;
    @BindView(R.id.right_recycle)
    RecyclerView right_recycle;
    @BindView(R.id.top_show)
    LinearLayout top_show;
    private List<String> classInfoName= null;
    private List<String>  caterary=null;
    @BindView(R.id.list_swiperefreshlayout)
    SmartRefreshLayout list_swiperefreshlayout;

    private String ThisCaterary;

    private ClassInfo_TwoBarRecycleAdapter classInfo_twoBarRecycleAdapter;
    /**
     * 轮播数据
     */
    private List<String> LurC = null;
    private  List<Banner.BodyBean.ContentBean.ListBean> banners;
    private ClassInfoPresenter classInfoProsenter=null;
    private ClassInfoHomeBarRecycleAdapter classInfoHomeBarRecycleAdapter=null;
    @Override
    public int getLayoutResId() {
        return R.layout.classification_xml;
    }

    @Override
    protected void initView() {
        super.initView();
        this.setViewMarginTop(top_show);
        left_recycle.setHasFixedSize(true);//设置固定大小
        left_recycle.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //为recyclerView设置布局管理器
        left_recycle.setLayoutManager(mLayoutManager);

        list_swiperefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                classInfoProsenter.getFenLei();
                classInfoProsenter.GetActivityListForUser();
                classInfoHomeBarRecycleAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        classInfoName= new ArrayList<>();
        caterary=new ArrayList<>();
        classInfoProsenter=new ClassInfoPresenter();
        classInfoProsenter.setCallBack(this);
        classInfoProsenter.getFenLei();
        classInfoProsenter.GetActivityListForUser();
    }
    @OnClick({R.id.search_classinfo_click,R.id.back_re})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.search_classinfo_click:
                LogPrint.printError("搜索");
                startActivity(new Intent(getActivity(), SearchOneActivity.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.back_re:
                break;
        }

    }
    @Override
    public void Fenlei(final  FenLei fenLei) {
        final int size= fenLei.getBody().getContent().size();
        classInfoName.add("热门推荐");
        for(int i=0;i<size;i++){
            classInfoName.add(fenLei.getBody().getContent().get(i).getName());
            caterary.add(fenLei.getBody().getContent().get(i).getCode());
        }
        classInfoHomeBarRecycleAdapter =new ClassInfoHomeBarRecycleAdapter(classInfoName, getActivity(), new OnclicKRecycleAdapter() {
            @Override
            public void onItemClick(int position) {
                classInfoHomeBarRecycleAdapter.setPosition(position);
                classInfoHomeBarRecycleAdapter.notifyDataSetChanged();
                LogPrint.printError("分类"+ classInfoName.get(position));
                //热门推荐以外的点击事件
                if(position>0) {
                    ThisCaterary=caterary.get(position - 1);
                    classInfoProsenter.getSlideShowData(caterary.get(position - 1));
                }else{
                    classInfoProsenter.setCallBack(new ClassInfoPresenter.ClassInfoCallBack() {
                        @Override
                        public void Fenlei(FenLei fenLei) {

                        }
                        @Override
                        public void getSlideShow(Banner banner) {

                        }
                        @Override
                        public void nohaveData() {

                        }
                        @Override
                        public void ReMenData(ReMenResponse.BodyBean.ContentBean contentBean) {
                            if(contentBean!=null) {
                                if (contentBean.getList().size() > 0) {
                                    //===================网络请求获取列表=====================
                                    GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
                                    right_recycle.setLayoutManager(manager);
                                    ClasssInfo_TwoBarRecycleAdapter1 classInfo_twoBarRecycleAdapter = new ClasssInfo_TwoBarRecycleAdapter1(getActivity(), contentBean.getList(), classInfoProsenter, ThisCaterary);
                                    right_recycle.setAdapter(classInfo_twoBarRecycleAdapter);
                                }
                            }
                        }
                    });
                    classInfoProsenter.GetActivityListForUser();
                }
                for(int i=0;i<size;i++){
                    if(classInfoName.get(position).endsWith(fenLei.getBody().getContent().get(i).getName())){
                        OneClassClick(fenLei.getBody().getContent().get(i).getChildProductCategoryInfos());
                    }
                }
            }
            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

        left_recycle.setAdapter(classInfoHomeBarRecycleAdapter);
    }

    @Override
    public void getSlideShow(Banner banner) {

    }

    @Override
    public void nohaveData() {

    }

    @Override
    public void ReMenData(ReMenResponse.BodyBean.ContentBean contentBean) {
        if(contentBean!=null) {
            if (contentBean.getList().size() > 0) {
                //===================网络请求获取列表=====================
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
                right_recycle.setLayoutManager(manager);
                ClasssInfo_TwoBarRecycleAdapter1 classInfo_twoBarRecycleAdapter = new ClasssInfo_TwoBarRecycleAdapter1(getActivity(), contentBean.getList(), classInfoProsenter, ThisCaterary);
                right_recycle.setAdapter(classInfo_twoBarRecycleAdapter);
            }
        }
    }

    /**
     * 一级分类的点击事件
     * @param datas
     */
    private void OneClassClick(final List<FenLei.BodyBean.ContentBean.ChildProductCategoryInfosBean> datas){
        //===================网络请求获取列表=====================
        GridLayoutManager manager = new GridLayoutManager(getActivity(),3, LinearLayoutManager.VERTICAL,false);
        right_recycle.setLayoutManager(manager);
        classInfo_twoBarRecycleAdapter=new ClassInfo_TwoBarRecycleAdapter(getActivity(),datas,classInfoProsenter,ThisCaterary);
        right_recycle.setAdapter( classInfo_twoBarRecycleAdapter);
    }
}
