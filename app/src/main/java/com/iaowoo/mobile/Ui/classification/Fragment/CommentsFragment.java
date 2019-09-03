package com.iaowoo.mobile.Ui.classification.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.RelativeLayout;

import com.iaowoo.mobile.Ui.classification.Presenter.Goods_DetailsPresenter;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.ImagePagerActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.CommentsGoodRecycleAdapter1;
import com.iaowoo.mobile.Ui.classification.Model.CommentsModel;
import com.iaowoo.mobile.Ui.classification.Presenter.Goods_DetailsPresenter;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
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
 * @Description: ${todo}(评价fragment)
 * @date 2018/11/24
 * @email ${18011009889@163.com}
 */
public class CommentsFragment extends BaseFragment implements Goods_DetailsPresenter.GoodsCallBack1,CommentsGoodRecycleAdapter1.onclickItme {
    public interface  CommentsBack{
        void comeinComments();
        void back();
    }
    private   CommentsBack commentsBack;

    private Goods_DetailsPresenter goods_detailsPresenter;

    private String templateId;

    private GlideUtils glideUtils;

    private String activityId;


    public void setTemplateId(String templateId){
        this.templateId=templateId;
    }

    public void setActivityId(String activityId){
        this.activityId=activityId;
    }

    @BindView(R.id.list_swiperefreshlayout)
    SmartRefreshLayout comment_swiperefreshlayout;
    @BindView(R.id.comment_recycler_view)
    WRecyclerView comment_recycler_view;
    @BindView(R.id.top_comments)
    RelativeLayout top_comments;
    @BindView(R.id.no_have_layout)
    RelativeLayout no_have_layout;

    private boolean oneTage=true;

    private CommentsGoodRecycleAdapter1 commentsGoodRecycleAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof  CommentsBack) {
            commentsBack= (CommentsBack)activity; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }
    @Override
    public int getLayoutResId() {
        return R.layout.layout_comments;
    }
    @Override
    protected void initView() {
        super.initView();
        initDatas();
        this.listShow(comment_recycler_view);
        this.initSwipeRefreshView1(comment_swiperefreshlayout);
        //动态设置高度
//        this.setViewMarginTop(top_comments);
    }

    @Override
    protected void initData() {
        super.initData();
        glideUtils=new GlideUtils();
        goods_detailsPresenter=new Goods_DetailsPresenter(this);
        LogPrint.printError("templateId"+templateId);
        commentsGoodRecycleAdapter=new CommentsGoodRecycleAdapter1(getActivity(),glideUtils);
        goods_detailsPresenter.getGoodsCommentsData(templateId,curPageIndex,pageSize);

    }

    @Override
    public void getDatas(int curPageIndex, int pageSize) {
        goods_detailsPresenter.getGoodsCommentsData(templateId,curPageIndex,pageSize);
    }

    @Override
    public void noHaveDatas() {
        LogPrint.printError("没有数据了你还在滑个卵哦！！！");

    }

    @Override
    protected void refreshOk() {
        commentsGoodRecycleAdapter.notifyDataSetChanged();
        tempY=0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    /**
//     * EventBus消息处理方法。
//     * @param eventBusMessageGoods
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onShowMessageEvent(EventBusMessageGoods eventBusMessageGoods) {
//    }
    @Override
    public void onDetach() {
        super.onDetach();
//        EventBus.getDefault().unregister(this);
        commentsBack=null;
        glideUtils=null;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isInit  默认是false 没有初始化控件过，不然会空指针
        if(isVisibleToUser) {
            LogPrint.printError("可见");
            commentsBack.comeinComments();
        }
    }

    @OnClick({R.id.return_image_comments})
    public void click(View view){
        switch (view.getId()){
            case  R.id.return_image_comments:
                LogPrint.printError("返回");
                commentsBack.back();
                break;
        }
    }
    @Override
    public void ListBean(List<CommentsModel.BodyBean.ContentBean.JudgesBean.ListBean> listBean) {
        no_have_layout.setVisibility(View.GONE);
        this.setData(true);
        this.stopRefreshAndLoading();
        oneTage=false;
        if(comment_recycler_view!=null) {
            //允许上拉加载功能
            comment_recycler_view.setPullLoadEnable(true);
            LogPrint.printError("总共条数:" + listBean.size());
            //获取加载的分页总数据
            for (int i = 0; i < listBean.size(); i++) {
                datas.add(listBean.get(i));
            }
            //第一页数据加载adpter
            if (First) {
                commentsGoodRecycleAdapter.setData(datas,this);
                comment_recycler_view.setAdapter(commentsGoodRecycleAdapter);
                First = false;
            }
            //第二次直接刷新
            else {
                commentsGoodRecycleAdapter.setData(datas,this);
                commentsGoodRecycleAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void nohave() {
        if(oneTage) {
            no_have_layout.setVisibility(View.VISIBLE);
            oneTage=false;
        }
    }

    /**
     * 跳转到图片浏览器
     * @param context
     * @param imgUrls
     * @param position
     * @param imageSize
     */
    public  void startImagePagerActivity(Context context, List<String> imgUrls, int position, ImagePagerActivity.ImageSize imageSize) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        intent.putStringArrayListExtra(ImagePagerActivity.INTENT_IMGURLS, new ArrayList<String>(imgUrls));
        intent.putExtra(ImagePagerActivity.INTENT_POSITION, position);
        intent.putExtra(ImagePagerActivity.INTENT_IMAGESIZE, imageSize);
        context.startActivity(intent);
        getActivity().overridePendingTransition(0,0);
    }
    @Override
    public void onClickNumber(String images,int position) {
        String str2 =images.replace(" ", "");
        List<String> list = Arrays.asList(str2.split(","));
        startImagePagerActivity(getActivity(),list,position, new ImagePagerActivity.ImageSize(0, 0));
    }
}