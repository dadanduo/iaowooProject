package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iaowoo.mobile.Ui.classification.Model.Banner;
import com.iaowoo.mobile.Ui.classification.Model.ReMenResponse;
import com.iaowoo.mobile.Ui.classification.Presenter.ClassInfoPresenter;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.ClassInfoHomeBarRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.OnclicKRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Model.FenLei;

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
 * @Description: ${todo}(分类activity)
 * @date 2018/9/7
 * @email ${18011009889@163.com}
 */
public class ClassificationActivity  extends BaseBufferActivity implements ClassInfoPresenter.ClassInfoCallBack {
    @BindView(R.id.left_recycle)
    RecyclerView left_recycle;

    @BindView(R.id.right_recycle)
    RecyclerView right_recycle;

    private List<String> classInfoName= null;


    private ClassInfoPresenter classInfoProsenter=null;

    private ClassInfoHomeBarRecycleAdapter classInfoHomeBarRecycleAdapter=null;

    @Override
    public int getLayoutResId() {
        return R.layout.classification_xml;
    }

    @Override
    protected void initView() {
        super.initView();
        this.initState(R.id.ll_bar);
        left_recycle.setHasFixedSize(true);//设置固定大小
        left_recycle.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //为recyclerView设置布局管理器
        left_recycle.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void initData() {
        super.initData();
        classInfoName= new ArrayList<>();
        classInfoProsenter=new ClassInfoPresenter();
        classInfoProsenter.setCallBack(this);
        classInfoProsenter.getFenLei();
    }

    @OnClick({R.id.search_classinfo_click,R.id.back_re})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.search_classinfo_click:
                LogPrint.printError("搜索");
                startActivity(new Intent(this, SearchOneActivity.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.back_re:
                finish();
                break;
        }

    }

    @Override
    public void Fenlei(final FenLei fenLei) {
        final int size= fenLei.getBody().getContent().size();
        classInfoName.add("热门推荐");
        for(int i=0;i<size;i++){
            classInfoName.add(fenLei.getBody().getContent().get(i).getName());
        }
        classInfoHomeBarRecycleAdapter =new ClassInfoHomeBarRecycleAdapter(classInfoName, this, new OnclicKRecycleAdapter() {
            @Override
            public void onItemClick(int position) {
                classInfoHomeBarRecycleAdapter.setPosition(position);
                classInfoHomeBarRecycleAdapter.notifyDataSetChanged();
                LogPrint.printError("分类"+ classInfoName.get(position));
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

    }

    /**
     * 一级分类的点击事件
     * @param datas
     */
    private void OneClassClick(List<FenLei.BodyBean.ContentBean.ChildProductCategoryInfosBean> datas){
        //===================网络请求获取列表=====================
        GridLayoutManager manager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return Bigeall.get(position);
//            }
//        });
        right_recycle.setLayoutManager(manager);
//        ClassInfo_TwoBarRecycleAdapter classInfo_twoBarRecycleAdapter=new ClassInfo_TwoBarRecycleAdapter(this, datas, new ItemClickInterface() {
//            @Override
//            public void onItemClick(Object Item, int position) {
//
//            }
//        });
//        right_recycle.setAdapter(classInfo_twoBarRecycleAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        classInfoProsenter=null;
    }
}
