package com.iaowoo.mobile.Ui.classification.Presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.MyOrderActivtiy;
import com.iaowoo.mobile.Ui.classification.Adapter.InfrastructureRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Model.ParameterEntity;
import com.iaowoo.mobile.Ui.classification.Model.SearchGoods;
import com.iaowoo.mobile.Ui.classification.View.FlowLayout;
import com.iaowoo.mobile.Ui.classification.View.TextEditTextView;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @Description: ${todo}(商品详情dialog)
 * @date 2018/11/26
 * @email ${18011009889@163.com}
 */
public class ShopGoodsDialogPresenters {

    private Context mContext;
    public ShopGoodsDialogPresenters(Context mContext){
        this.mContext=mContext;
    }

    private Map<Integer,String> combination;

    /**
     * 选择规格
     */
    private StringBuffer ChooseGG;

    private String[] chooseOk;


    private SoftReference<DialogUtils> dialogUtilsSoftReference;

    private SearchGoods.BodyBean.ContentBean contentBean;

    private List<SearchGoods.BodyBean.ContentBean.SubTemplateInfoListBean> subTemplateInfoListBeans;

    private SearchGoods.BodyBean.ContentBean.SubTemplateInfoListBean thisSub;

    private List<SearchGoods.BodyBean.ContentBean.SubTemplateInfoListBean> subTemplateInfoListBeans1;


    private List<ParameterEntity> list1;

    private List<ParameterEntity> list2;

    private List<ParameterEntity> list3;

    private AttrAdapter attrAdapter1;
    private AttrAdapter attrAdapter2;
    private AttrAdapter attrAdapter3;

    private boolean  chooseAreYouOK=false;

    private ImageView shop_image;

    private TextView prices,choose_text;

    private GlideUtils glideUtils;

    private int BuyNumber;

    private StringBuffer guige;


    public interface  addShopCar{
        void addSuccessful();
    }

    /**
     *选择规格
     * @return
     */
    public Dialog ChoooseSpecifications(final Goods_DetailsPresenter goods_detailsPresenter, final SearchGoods.BodyBean.ContentBean contentBean, GlideUtils glideUtils, final String miaoshu, final String activityId, final int type, final addShopCar addShopCar){
        this.contentBean=contentBean;
        this.glideUtils=glideUtils;
        chooseAreYouOK=false;
        dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.specifications_layout, null);// 得到加载view
        FrameLayout guige_all=v.findViewById(R.id.guige_all);
        FlowLayout one=v.findViewById(R.id.one);
        FlowLayout two=v.findViewById(R.id.two);
        FlowLayout three=v.findViewById(R.id.three);
        TextView one_text=v.findViewById(R.id.one_text);
        LinearLayout one_layout=v.findViewById(R.id.one_layout);
        LinearLayout two_layout=v.findViewById(R.id.two_layout);
        LinearLayout three_layout=v.findViewById(R.id.three_layout);
        FrameLayout edite_numbers=v.findViewById(R.id.edite_numbers);
        final TextView show_text_txt=v.findViewById(R.id.show_text_txt);
        final RelativeLayout jian=v.findViewById(R.id.jian);
        final EditText show_text=v.findViewById(R.id.show_text);
        final RelativeLayout add_btm=v.findViewById(R.id.add_btm);
        LinearLayout show_one=v.findViewById(R.id.show_one);
        Button show_two=v.findViewById(R.id.show_two);
        shop_image=v.findViewById(R.id.shop_image);
        RelativeLayout add_car_goods=v.findViewById(R.id.add_car_goods);
        RelativeLayout buy_ok=v.findViewById(R.id.buy_ok);
        show_text.setCursorVisible(false);
        show_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_text.setCursorVisible(true);
            }
        });
        //btton显示文字
        if(type==1){
            show_two.setText("加入购物车");
            show_two.setVisibility(View.VISIBLE);
            show_one.setVisibility(View.GONE);
            show_two.setBackgroundResource(R.mipmap.determine_gradient_bt);
        }else if(type==2){
            show_two.setText("确认");
            show_two.setVisibility(View.VISIBLE);
            show_one.setVisibility(View.GONE);
            show_two.setBackgroundResource(R.mipmap.determine_gradient_bt);

        }else{
            show_two.setVisibility(View.GONE);
            show_one.setVisibility(View.VISIBLE);
        }
        show_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                LogPrint.printError("输入完了" + s);
                if (!TextUtils.isEmpty(s.toString())) {
                    int num = Integer.parseInt(s.toString());
                    int min = contentBean.getMinimumCount();
                    int max = contentBean.getLimitCount();
                    if (num < min) {
                        show_text_txt.setText(min + "");
                    } else if (num > max) {
                        show_text_txt.setText(max + "");
                    } else {
                        show_text_txt.setText(num + "");
                    }
                }else{
                    show_text_txt.setText(contentBean.getMinimumCount()+"");
                }
            }
        });
        prices=v.findViewById(R.id.prices);
        choose_text=v.findViewById(R.id.choose_text);
        RelativeLayout close=v.findViewById(R.id.close);
        StringBuffer stringBuffer=new StringBuffer();
        TextView two_text=v.findViewById(R.id.two_text);
        TextView tree_text=v.findViewById(R.id.tree_text);

        //显示规格默认图片
        if(!TextUtils.isEmpty(contentBean.getProductInfo().getMainImage())) {
            glideUtils.glides(mContext, contentBean.getProductInfo().getMainImage(), shop_image);
        }

        chooseOk=new String[3];
        //默认为最小值
        show_text_txt.setText(""+contentBean.getMinimumCount());
        BuyNumber=contentBean.getMinimumCount();
        //得到官网提供的所有配置
        combination=new HashMap<>();
        LogPrint.printError("所有组合"+contentBean.getSubTemplateInfoList().size());
        for(int i=0;i<contentBean.getSubTemplateInfoList().size();i++){
            StringBuffer stringBuffer1=new StringBuffer();
            if(!TextUtils.isEmpty(contentBean.getSubTemplateInfoList().get(i).getSpecificationOneVal())){
                stringBuffer1.append(contentBean.getSubTemplateInfoList().get(i).getSpecificationOneVal());
            }
            if(!TextUtils.isEmpty(contentBean.getSubTemplateInfoList().get(i).getSpecificationTwoVal())){
                stringBuffer1.append(contentBean.getSubTemplateInfoList().get(i).getSpecificationTwoVal());
            }
            if(!TextUtils.isEmpty(contentBean.getSubTemplateInfoList().get(i).getSpecificationThreeVal())){
                stringBuffer1.append(contentBean.getSubTemplateInfoList().get(i).getSpecificationThreeVal());
            }
            combination.put(i,stringBuffer1+"");
        }
        final Dialog loadingDialog = new Dialog(mContext, R.style.MyDialogStyle_plp);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(true); // 点击加载框以外的区域
        loadingDialog.setContentView(guige_all, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

        if(!TextUtils.isEmpty(contentBean.getSpecificationInfoOneName())){
            one_layout.setVisibility(View.VISIBLE);
            list1=new ArrayList<>();
            for(int i=0;i<contentBean.getSkuInfo().getSpecificationInfoOneName().size();i++){
                String name=contentBean.getSkuInfo().getSpecificationInfoOneName().get(i);
                ParameterEntity parameterEntity=new ParameterEntity(name);
                parameterEntity.selected=false;
                list1.add(parameterEntity);
            }
            one_text.setText(""+contentBean.getSpecificationInfoOneName());
            stringBuffer.append("请选择 "+contentBean.getSpecificationInfoOneName());
            attrAdapter1 = new AttrAdapter(0, list1);
            one.setAdapter(attrAdapter1);
        }
        if(!TextUtils.isEmpty(contentBean.getSpecificationInfoTwoName())){
            list2 =new ArrayList<>();
            for(int i=0;i<contentBean.getSkuInfo().getSpecificationInfoTwoName().size();i++){
                String name=contentBean.getSkuInfo().getSpecificationInfoTwoName().get(i);
                ParameterEntity parameterEntity=new ParameterEntity(name);
                parameterEntity.selected=false;
                list2.add(parameterEntity);
            }
            attrAdapter2 = new AttrAdapter(1, list2);
            two.setAdapter(attrAdapter2);

            two_layout.setVisibility(View.VISIBLE);
            stringBuffer.append(" "+contentBean.getSpecificationInfoTwoName());
            two_text.setText(""+contentBean.getSpecificationInfoTwoName());
        }
        if(!TextUtils.isEmpty(contentBean.getSpecificationInfoThreeName())){
            three_layout.setVisibility(View.VISIBLE);
            stringBuffer.append(" "+contentBean.getSpecificationInfoThreeName());
            tree_text.setText(""+contentBean.getSpecificationInfoThreeName());
            list3 =new ArrayList<>();
            for(int i=0;i<contentBean.getSkuInfo().getSpecificationInfoThreeName().size();i++){
                String name=contentBean.getSkuInfo().getSpecificationInfoThreeName().get(i);
                ParameterEntity parameterEntity=new ParameterEntity(name);
                parameterEntity.selected=false;
                list3.add(parameterEntity);
            }
            attrAdapter3 = new AttrAdapter(2, list3);
            three.setAdapter(attrAdapter3);
        }

        choose_text.setText(""+stringBuffer);
        prices.setText("￥"+ UtilsAll.DoubleTo_2(contentBean.getMinSellPrice()));

        edite_numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.view_slide_anim);
        loadingDialog.show();

        //关闭弹窗
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
            }
        });
        //减
        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(show_text_txt.getText().toString());
                if(number>contentBean.getMinimumCount()) {
                    --number;
                    show_text.setText("" + number);
                }else{
                    ToastUtilsAll.getInstance().showShortToast("亲！该商品至少要购买"+contentBean.getMinimumCount()+"件");
                }
            }
        });
        //加
        add_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number= Integer.parseInt(show_text_txt.getText().toString());
                if(number<contentBean.getLimitCount()) {
                    ++number;
                    show_text_txt.setText("" + number);
                }else{
                    ToastUtilsAll.getInstance().showShortToast("亲！该商品最多只能购买"+contentBean.getLimitCount()+"件");
                }
            }
        });

        //加入购物车
        add_car_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseAreYouOK){
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                        if(dialogUtilsSoftReference.get()!=null) {
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }else{
                            dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }
                    }else{
                            goods_detailsPresenter.addShopCar(thisSub.getSubTemplateId(), Integer.parseInt(show_text_txt.getText().toString()), new Goods_DetailsPresenter.GoodsCallBack2() {
                                @Override
                                public void addOK() {
                                    addShopCar.addSuccessful();
                                    loadingDialog.dismiss();
                                }
                            });
                            LogPrint.printError("加到购物车中>>>>>>>>");
                    }
                }else{
                    ToastUtilsAll.getInstance().showShortToast("请先选中属性");
                }
            }
        });
        show_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==1){
                    if(chooseAreYouOK){
                        if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                            if(dialogUtilsSoftReference.get()!=null) {
                                dialogUtilsSoftReference.get().LoginTo(mContext);
                            }else{
                                dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                                dialogUtilsSoftReference.get().LoginTo(mContext);
                            }
                        }else{
                            goods_detailsPresenter.addShopCar(thisSub.getSubTemplateId(), Integer.parseInt(show_text_txt.getText().toString()), new Goods_DetailsPresenter.GoodsCallBack2() {
                                @Override
                                public void addOK() {
                                    addShopCar.addSuccessful();
                                    loadingDialog.dismiss();
                                }
                            });
                            LogPrint.printError("加到购物车中>>>>>>>>");
                        }
                    }else{
                        ToastUtilsAll.getInstance().showShortToast("请先选中属性");
                    }
                }else if(type==2){
                    if(chooseAreYouOK){
                        if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                            if(dialogUtilsSoftReference.get()!=null) {
                                dialogUtilsSoftReference.get().LoginTo(mContext);
                            }else{
                                dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                                dialogUtilsSoftReference.get().LoginTo(mContext);
                            }
                        }else{
                            int min=contentBean.getMinimumCount();
                            int max=contentBean.getLimitCount();
                            Intent mintent = new Intent(mContext, MyOrderActivtiy.class);
                            mintent.putExtra("shops", (Serializable) thisSub);
                            mintent.putExtra("min", min);
                            mintent.putExtra("max", max);
                            mintent.putExtra("miaoshu", miaoshu);
                            mintent.putExtra("guige", guige + "");
                            mintent.putExtra("chooseNumber", Integer.parseInt(show_text_txt.getText().toString()));
                            mintent.putExtra("activityId", activityId);
                            mContext.startActivity(mintent);
                            loadingDialog.dismiss();
                        }
                    }else{
                        ToastUtilsAll.getInstance().showShortToast("请先选中属性");
                    }
                }
            }
        });
        //直接 购买
        buy_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseAreYouOK){
                    if(TextUtils.isEmpty(PrefManager.getInstance().getToken())){
                        if(dialogUtilsSoftReference.get()!=null) {
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }else{
                            dialogUtilsSoftReference=new SoftReference<>(new DialogUtils());
                            dialogUtilsSoftReference.get().LoginTo(mContext);
                        }
                    }else{
                            int min=contentBean.getMinimumCount();
                            int max=contentBean.getLimitCount();
                            Intent mintent = new Intent(mContext, MyOrderActivtiy.class);
                            mintent.putExtra("shops", (Serializable) thisSub);
                            mintent.putExtra("min", min);
                            mintent.putExtra("max", max);
                            mintent.putExtra("miaoshu", miaoshu);
                            mintent.putExtra("guige", guige + "");
                            mintent.putExtra("chooseNumber", Integer.parseInt(show_text_txt.getText().toString()));
                            mintent.putExtra("activityId", activityId);
                            mContext.startActivity(mintent);
                            loadingDialog.dismiss();
                    }
                }else{
                    ToastUtilsAll.getInstance().showShortToast("请先选中属性");
                }
            }
        });
        return loadingDialog;
    }

    /**
     * 计算是否可以点击
     *
     * @return
     */
    private void computEnable(int position,String spacValue) {
        switch (position) {
            case 0:
                for (int c = 0; c < list1.size(); c++) {
                    list1.get(c).enable = true;
                }
                attrAdapter1.notifyDataSetChanged();
                //计算第一组选中状态下关联的第二组可选属性
                subTemplateInfoListBeans = new ArrayList<>();
                for (int i = 0; i < contentBean.getSubTemplateInfoList().size(); i++) {
                    if (contentBean.getSubTemplateInfoList().get(i).getSpecificationOneVal().endsWith(spacValue)) {
                        subTemplateInfoListBeans.add(contentBean.getSubTemplateInfoList().get(i));
                    }
                }
                if (list2 != null) {
                    //第三组
                    subTemplateInfoListBeans1 = new ArrayList<>();
                    for (int c = 0; c < list2.size(); c++) {
                        boolean f = false;
                        for (int j = 0; j < subTemplateInfoListBeans.size(); j++) {
                            String name = subTemplateInfoListBeans.get(j).getSpecificationTwoVal();
                            //如果有说明可以选
                            if (name.endsWith(list2.get(c).name)) {
                                f = true;
                                subTemplateInfoListBeans1.add(subTemplateInfoListBeans.get(j));
                            }
                        }
                        if (f) {
                            list2.get(c).enable = true;
                        } else {
                            list2.get(c).enable = false;
                            list2.get(c).selected = false;
                        }
                    }
                }

                if (list3 != null) {
                    for (int c = 0; c < list3.size(); c++) {
                        boolean f = false;
                        //开始遍历第三组
                        for (int w = 0; w < subTemplateInfoListBeans1.size(); w++) {
                            String name1 = subTemplateInfoListBeans1.get(w).getSpecificationThreeVal();
                            //如果有说明可以选
                            if (name1.endsWith(list3.get(c).name)) {
                                f = true;
                            }
                        }
                        if (f) {
                            list3.get(c).enable = true;
                        } else {
                            list3.get(c).enable = false;
                            list3.get(c).selected = false;

                        }
                    }
                }
                if (attrAdapter1 != null) {
                    attrAdapter1.notifyDataSetChanged();
                }
                if (attrAdapter2 != null) {
                    attrAdapter2.notifyDataSetChanged();
                }
                if (attrAdapter3 != null) {
                    attrAdapter3.notifyDataSetChanged();
                }
                break;
            case 1:
                for (int c = 0; c < list2.size(); c++) {
                    list2.get(c).enable = true;
                }
                attrAdapter2.notifyDataSetChanged();
                //计算第一组选中状态下关联的第二组可选属性
                subTemplateInfoListBeans = new ArrayList<>();
                for (int i = 0; i < contentBean.getSubTemplateInfoList().size(); i++) {
                    if (contentBean.getSubTemplateInfoList().get(i).getSpecificationTwoVal().endsWith(spacValue)) {
                        subTemplateInfoListBeans.add(contentBean.getSubTemplateInfoList().get(i));
                    }
                }
                //第三组
                subTemplateInfoListBeans1 = new ArrayList<>();
                if (list1 != null) {
                    for (int c = 0; c < list1.size(); c++) {
                        boolean f = false;
                        for (int j = 0; j < subTemplateInfoListBeans.size(); j++) {
                            String name = subTemplateInfoListBeans.get(j).getSpecificationOneVal();
                            //如果有说明可以选
                            if (name.endsWith(list1.get(c).name)) {
                                f = true;
                                subTemplateInfoListBeans1.add(subTemplateInfoListBeans.get(j));
                            }
                        }
                        if (f) {
                            list1.get(c).enable = true;
                        } else {
                            list1.get(c).enable = false;
                            list1.get(c).selected = false;
                        }
                    }
                }

                if (list3 != null) {
                    for (int c = 0; c < list3.size(); c++) {
                        boolean f = false;
                        //开始遍历第三组
                        for (int w = 0; w < subTemplateInfoListBeans1.size(); w++) {
                            String name1 = subTemplateInfoListBeans1.get(w).getSpecificationThreeVal();
                            //如果有说明可以选
                            if (name1.endsWith(list3.get(c).name)) {
                                f = true;
                            }
                        }
                        if (f) {
                            list3.get(c).enable = true;
                        } else {
                            list3.get(c).enable = false;
                            list3.get(c).selected = false;
                        }
                    }
                }
                if (attrAdapter1 != null) {
                    attrAdapter1.notifyDataSetChanged();
                }
                if (attrAdapter2 != null) {
                    attrAdapter2.notifyDataSetChanged();
                }
                if (attrAdapter3 != null) {
                    attrAdapter3.notifyDataSetChanged();
                }
                break;
            case 2:
                for (int c = 0; c < list3.size(); c++) {
                    list3.get(c).enable = true;
                }
                attrAdapter3.notifyDataSetChanged();
                //计算第一组选中状态下关联的第二组可选属性
                subTemplateInfoListBeans = new ArrayList<>();
                for (int i = 0; i < contentBean.getSubTemplateInfoList().size(); i++) {
                    if (contentBean.getSubTemplateInfoList().get(i).getSpecificationThreeVal().endsWith(spacValue)) {
                        subTemplateInfoListBeans.add(contentBean.getSubTemplateInfoList().get(i));
                    }
                }
                //第三组
                subTemplateInfoListBeans1 = new ArrayList<>();
                if (list2 != null) {
                    for (int c = 0; c < list2.size(); c++) {
                        boolean f = false;
                        for (int j = 0; j < subTemplateInfoListBeans.size(); j++) {
                            String name = subTemplateInfoListBeans.get(j).getSpecificationTwoVal();
                            //如果有说明可以选
                            if (name.endsWith(list2.get(c).name)) {
                                f = true;
                                subTemplateInfoListBeans1.add(subTemplateInfoListBeans.get(j));
                            }
                        }
                        if (f) {
                            list2.get(c).enable = true;
                        } else {
                            list2.get(c).enable = false;
                            list2.get(c).selected = false;
                        }
                    }
                }

                if (list1 != null) {
                    for (int c = 0; c < list1.size(); c++) {
                        boolean f = false;
                        //开始遍历第三组
                        for (int w = 0; w < subTemplateInfoListBeans1.size(); w++) {
                            String name1 = subTemplateInfoListBeans1.get(w).getSpecificationOneVal();
                            //如果有说明可以选
                            if (name1.endsWith(list1.get(c).name)) {
                                f = true;
                            }
                        }
                        if (f) {
                            list1.get(c).enable = true;
                        } else {
                            list1.get(c).enable = false;
                            list1.get(c).selected = false;
                        }
                    }
                }
                if (attrAdapter1 != null) {
                    attrAdapter1.notifyDataSetChanged();
                }
                if (attrAdapter2 != null) {
                    attrAdapter2.notifyDataSetChanged();
                }
                if (attrAdapter3 != null) {
                    attrAdapter3.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }
    private void Tocompare(){
        StringBuffer stringBuffers=new StringBuffer();
        guige=new StringBuffer();
        StringBuffer showtime=new StringBuffer();
        for(int j=0;j<chooseOk.length;j++){
            if(!TextUtils.isEmpty(chooseOk[j])) {
                stringBuffers.append(chooseOk[j]);
                //没有选中
                if(j==0){
                    if(!TextUtils.isEmpty(contentBean.getSpecificationInfoOneName())){
                        guige.append(contentBean.getSpecificationInfoOneName()+": ");
                        guige.append(chooseOk[j]+"    ");
                    }
                }else if(j==1){
                    if(!TextUtils.isEmpty(contentBean.getSpecificationInfoTwoName())) {
                        guige.append(contentBean.getSpecificationInfoTwoName()+": ");
                        guige.append(chooseOk[j]+"    ");
                    }
                }else{
                    if(!TextUtils.isEmpty(contentBean.getSpecificationInfoThreeName())) {
                        guige.append(contentBean.getSpecificationInfoThreeName()+": ");
                        guige.append(chooseOk[j]);
                    }
                }
            }else{
                //没有选中
                if(j==0){
                    if(!TextUtils.isEmpty(contentBean.getSpecificationInfoOneName())){
                        showtime.append(contentBean.getSpecificationInfoOneName());
                    }
                }else if(j==1){
                    if(!TextUtils.isEmpty(contentBean.getSpecificationInfoTwoName())) {
                        showtime.append(contentBean.getSpecificationInfoTwoName());
                    }
                }else{
                    if(!TextUtils.isEmpty(contentBean.getSpecificationInfoThreeName())) {
                        showtime.append(contentBean.getSpecificationInfoThreeName());
                    }
                }
            }
        }
        chooseAreYouOK=false;
        for(int i=0;i<combination.size();i++){
            String a=combination.get(i);
            LogPrint.printError("数据"+a);
            if(stringBuffers.toString().endsWith(a)){
                LogPrint.printError("相同");
                thisSub=contentBean.getSubTemplateInfoList().get(i);
                chooseAreYouOK=true;
                if(!TextUtils.isEmpty(contentBean.getSubTemplateInfoList().get(i).getMainImage())){
                    glideUtils.glides(mContext,contentBean.getSubTemplateInfoList().get(i).getMainImage(),shop_image);
                }
                if(!TextUtils.isEmpty(contentBean.getSubTemplateInfoList().get(i).getSellPrice()+"")){
                    prices.setText("￥"+UtilsAll.DoubleTo_2(contentBean.getSubTemplateInfoList().get(i).getSellPrice()));
                }
            }
        }
        choose_text.setText(""+guige);
    }


    class AttrAdapter extends BaseAdapter {
        private int outPosition;
        private List<ParameterEntity> list;
        public AttrAdapter( int position, List<ParameterEntity> list) {
            this.outPosition = position;
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public Object getItem(int position) {
            return list.isEmpty() ? null : list.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View vi = convertView;
            final Holder holder;
            if (vi == null) {
                vi = LayoutInflater.from(mContext).inflate(R.layout.item_choice_button, null);
                holder = new Holder(vi);
                vi.setTag(holder);
            } else {
                holder = (Holder) vi.getTag();
            }
            final ParameterEntity param = list.get(position);
            holder.tv.setText(param.name);
            holder.tv.setEnabled(param.enable);
            holder.tv.setSelected(param.selected);
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogPrint.printError("点击"+param.name);
                    if (!v.isEnabled()) return;
                    holder.tv.setSelected(true);
                    chooseOk[outPosition]=param.name;
                    //适配其他类型是否可以点击
                    computEnable(outPosition,param.name);
                    Tocompare();
                    //改变选中装态
                    for (int i = 0; i < list.size(); i++) {
                        if(i!=position){
                            list.get(i).selected = false;
                        }else{
                            list.get(i).selected = true;
                        }
                    }
                    notifyDataSetChanged();


                }
            });
            return vi;
        }
    }
    class Holder {
        TextView tv;
        public Holder(View view) {
            tv=view.findViewById(R.id.tv);
        }
    }



    /**
     *基础保障
     * @return
     */
    public Dialog InfrastructureAssurance(List<SearchGoods.BodyBean.ContentBean.PromiseInfoListBean> promiseInfoListBeans, GlideUtils glideUtils){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.infrastructure_assurance_layout, null);// 得到加载view
        FrameLayout shop_all_baozhang=v.findViewById(R.id.shop_all_baozhang);
        RecyclerView recycler_bao_z=v.findViewById(R.id.recycler_bao_z);
        Button ok_layout_btm=v.findViewById(R.id.ok_layout_btm);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_bao_z.setLayoutManager(layoutManager);
        recycler_bao_z.setItemAnimator(new DefaultItemAnimator());
        recycler_bao_z.setNestedScrollingEnabled(false);
        InfrastructureRecycleAdapter infrastructureRecycleAdapter=new InfrastructureRecycleAdapter(mContext,glideUtils);
        infrastructureRecycleAdapter.SetData(promiseInfoListBeans);
        recycler_bao_z.setAdapter(infrastructureRecycleAdapter);


        final Dialog loadingDialog = new Dialog(mContext, R.style.MyDialogStyle_plp);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(true); // 点击加载框以外的区域
        loadingDialog.setContentView(shop_all_baozhang, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.view_slide_anim);
        loadingDialog.show();
        ok_layout_btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.dismiss();
            }
        });
        return loadingDialog;
    }
}
