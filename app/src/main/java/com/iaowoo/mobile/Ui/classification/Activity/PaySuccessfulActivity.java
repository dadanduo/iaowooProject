package com.iaowoo.mobile.Ui.classification.Activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;

import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.PaySuccessRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Model.ISHAVERED;
import com.iaowoo.mobile.Ui.classification.Model.PaySuccessM;
import com.iaowoo.mobile.Ui.classification.Model.Shop;
import com.iaowoo.mobile.Ui.classification.Presenter.HomeFragmentPresenter;
import com.iaowoo.mobile.Ui.classification.View.recyclerview.WRecyclerView;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.umeng.Defaultcontent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.lang.ref.SoftReference;
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
 * @Description: ${todo}(支付成功页面)
 * @date 2018/11/30
 * @email ${18011009889@163.com}
 */
public class PaySuccessfulActivity extends  BaseUpDownActivity  implements HomeFragmentPresenter.HomeCallBack {
    @BindView(R.id.recycler_view)
    WRecyclerView recycler_view;
    @BindView(R.id.list_swiperefreshlayout)
    SmartRefreshLayout list_swiperefreshlayout;
    @BindView(R.id.fenxianghongbao)
    ImageView fenxianghongbao;
    PaySuccessRecycleAdapter paySuccessRecycleAdapter;
    private String orderID;
    private PaySuccessM.BodyBean.ContentBean contentBeanThis;
    private SoftReference<DialogUtils> dialogUtilsSoftReference;
    /**
     * prosenter
     */
    private HomeFragmentPresenter homeFragmentProsenter = null;
    @Override
    public int getLayoutResId() {
        return R.layout.pay_successful_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
        orderID=getIntent().getStringExtra("orderid");
        initDatas();
        this.grideShow(recycler_view,2);
        this.initSwipeRefreshView1(list_swiperefreshlayout);
    }

    @Override
    protected void initData() {
        super.initData();
        homeFragmentProsenter = new HomeFragmentPresenter(this);
        homeFragmentProsenter.setCallBack(this);
        paySuccessRecycleAdapter=new PaySuccessRecycleAdapter(this, homeFragmentProsenter, new PaySuccessRecycleAdapter.BackCall() {
            @Override
            public void zaiguangguang() {
                finish();
            }

            @Override
            public void showShare(String orderID) {
                fenxianghongbao.setVisibility(View.VISIBLE);
            }
        });

        homeFragmentProsenter.getPaySuccessOrder(orderID, new HomeFragmentPresenter.PayOkCallBack() {
            @Override
            public void PaySucess(PaySuccessM.BodyBean.ContentBean contentBean) {
                contentBeanThis=contentBean;
                Defaultcontent.title=getResources().getString(R.string.app_name)+"购物分享派红包";
                Defaultcontent.imageurl="https://files.shbs008.com/static/honeybee2-h5/images/grap_red.png";
                Defaultcontent.text = getResources().getString(R.string.app_name)+"购物分享派红包，快来看看吧！";
                Defaultcontent.code_url = "/pages/shareRedPacket/index?orderId="+contentBean.getShareRedOrderId();
                homeFragmentProsenter.getShopType(curPageIndex,pageSize);
            }
        });
    }

    @Override
    public void getDatas(int curPageIndex, int pageSize) {
        homeFragmentProsenter.getShopType(curPageIndex,pageSize);
    }

    @Override
    public void noHaveDatas() {

    }

    @Override
    protected void refreshOk() {
        paySuccessRecycleAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.back,R.id.fenxianghongbao})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.fenxianghongbao:
                Defaultcontent.code_url="page/pagesGoods/shareRedPacket/index?orderId="+orderID;
                if (dialogUtilsSoftReference.get() != null) {
                    dialogUtilsSoftReference.get().Share(this);
                } else {
                    dialogUtilsSoftReference = new SoftReference<>(new DialogUtils());
                    dialogUtilsSoftReference.get().Share(this);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void Shop(List<Shop.BodyBean.ContentBean.ListBean> shops) {

        this.setData(true);
        this.stopRefreshAndLoading();
        if(recycler_view!=null) {
            //允许上拉加载功能
            recycler_view.setPullLoadEnable(true);
            LogPrint.printError("总共条数:" + shops.size());
            //获取加载的分页总数据
            for (int i = 0; i < shops.size(); i++) {
                datas.add(shops.get(i));
            }
            //第一页数据加载adpter
            if (First) {
                paySuccessRecycleAdapter.setShops(datas,contentBeanThis,orderID);
                recycler_view.setAdapter(paySuccessRecycleAdapter);
                First = false;
            }
            //第二次直接刷新
            else {
                paySuccessRecycleAdapter.setShops(datas,contentBeanThis,orderID);
                paySuccessRecycleAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void NohaveData() {
        LogPrint.printError("没有数据了你还在滑个卵哦！！！");
        this.stopRefreshAndLoading();
        this.setData(false);
    }

    @Override
    public void IshaveRed(ISHAVERED ishavered) {

    }

}
