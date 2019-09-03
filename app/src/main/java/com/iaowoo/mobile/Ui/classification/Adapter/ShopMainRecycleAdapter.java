package com.iaowoo.mobile.Ui.classification.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.ActivtiyModel;
import com.iaowoo.mobile.Ui.classification.Model.JS;
import com.iaowoo.mobile.Ui.classification.Model.SearchGoods;
import com.iaowoo.mobile.Ui.classification.Model.YouHuiQuan;
import com.iaowoo.mobile.Utils.Glide.GlideUtils;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chenda on 2018/4/3.
 */

public class ShopMainRecycleAdapter extends BaseSoEasyAdapter {
    /**
     * 上线文
     */
    private Context context;
    /**
     * 商品数量的最小值，和最大值，已经当前选择的数量
     */
    private int min, max, chooseNumber;
    /**
     * 规格， 描述数据
     */
    private String guige, miaoshu;
    /**
     * 商品个数
     */
    private List<SearchGoods.BodyBean.ContentBean.SubTemplateInfoListBean> subTemplateInfoListBeans;
    /**
     * 图片加载器
     */
    private GlideUtils glideUtils;
    /**
     * 计算出来的数据
     */
    private List<JS.BodyBean.ContentBean.CouponRedParamBean> list;
    /**
     * 红包剩余总数
     */
    private double redBao = 0;
    /**
     *
     */
    private double pay;
    /**
     * 当前使用红包数额
     */
    public double reUse;

    /**
     * 当前订单可使用红包的最大额度
     */
    private double ThisRedUse = 0;

    private double chooseRedZero = 0;

    private List<YouHuiQuan.BodyBean.ContentBean.ListBean> listBeans;

    private ActivtiyModel.BodyBean bodyBean;

    private shopItemRecycleAdapter.callBackok callBackok;

    private boolean oneComeIn=true;

    //当前正在使用的红包值
    private double RedAmount=0;

    private String redBaoChoose;

    private double YouhuiQuan = 0;

    public ShopMainRecycleAdapter(Context context, GlideUtils glideUtils, shopItemRecycleAdapter.callBackok callBackok) {
        this.context = context;
        this.glideUtils = glideUtils;
        this.callBackok = callBackok;
    }


    public void SetData(List<SearchGoods.BodyBean.ContentBean.SubTemplateInfoListBean> subTemplateInfoListBeans, int min, int max, int chooseNumber, String guige, String miaoshu, double redBao, double chooseRedZero,double youhuiQuan,double RedAmount) {
        this.subTemplateInfoListBeans = subTemplateInfoListBeans;
        this.min = min;
        this.max = max;
        this.chooseNumber = chooseNumber;
        this.guige = guige;
        this.miaoshu = miaoshu;
        this.redBao = redBao;
        this.chooseRedZero = chooseRedZero;
        this.YouhuiQuan=youhuiQuan;
        this.RedAmount=RedAmount;
    }

    public void setListYouHuiQuan(List<YouHuiQuan.BodyBean.ContentBean.ListBean> listBeans) {
        this.listBeans = listBeans;
    }


    public void setActivity(ActivtiyModel.BodyBean bodyBean) {
        this.bodyBean = bodyBean;
    }

    public void setPay(List<JS.BodyBean.ContentBean.CouponRedParamBean> list, double pay) {
        this.list = list;
        this.pay = pay;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_layout, parent, false);
        return new CommentsViewHolder(view);
    }

    /**
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final CommentsViewHolder holder = (CommentsViewHolder) viewHolder;
        //活动头部是否显示
        if (bodyBean != null) {
            holder.activity_layout.setVisibility(View.VISIBLE);
        }
        //显示商品的布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.goods_item_recycler.setLayoutManager(layoutManager);
        holder.goods_item_recycler.setItemAnimator(new DefaultItemAnimator());
        holder.goods_item_recycler.setNestedScrollingEnabled(false);
        shopItemRecycleAdapter shopItemRecycleAdapters = new shopItemRecycleAdapter(context, glideUtils, callBackok);
        shopItemRecycleAdapters.SetData(subTemplateInfoListBeans, min, max, chooseNumber, guige, miaoshu, pay);
        holder.goods_item_recycler.setAdapter(shopItemRecycleAdapters);
        //设置剩余红包总数
        holder.red_bao_text.setText("剩余" + redBao + "元");
        //设置可使用的优惠券
        if (listBeans != null) {
            int number = 0;
            for (int i = 0; i < listBeans.size(); i++) {
                if (pay >= listBeans.get(i).getLimitValue()) {
                    number += 1;
                }
            }
            holder.you_hui_quan_text.setText(number + "张可用");
        } else {
            holder.you_hui_quan_text.setText("0张可用");
        }
        if (list != null) {
            holder.baoshiquan_text.setText("有几率获得" + list.get(0).getGemStoneCouponAmount() + "张宝石券");
        }
        LogPrint.printError("优惠券的额度"+YouhuiQuan);
        //显示优惠券
        if(YouhuiQuan!=0){
            holder.youhuiquan_max_text.setVisibility(View.VISIBLE);
            holder.youhuiquan_max_text.setText("-"+YouhuiQuan);
            String temp=holder.you_hui_quan_text.getText().toString();
            int leng=temp.indexOf("张");
            String result=temp.substring(0,leng);
            holder.you_hui_quan_text.setText((Integer.parseInt(result)-1)+ "张可用");
        }else{
            //不显示优惠券
            holder.youhuiquan_max_text.setVisibility(View.GONE);
        }
        /************************************************************/
        //可使用额度为0去获取并赋值
        if(oneComeIn) {
            oneComeIn=false;
            if (list != null) {
                ThisRedUse = list.get(0).getMaxRedAmount();
                reUse = ThisRedUse;
                LogPrint.printError(">>>>>>>reUse" + reUse + "<<<<<<<redBao" + redBao);
                //红包余额大于或者等于使用的红包
                if (redBao >= reUse) {
                    callBackok.redCount(list.get(0).getMaxRedAmount());
                    holder.red_bao_max_text.setText("-" + list.get(0).getMaxRedAmount());
                } else {
                    callBackok.redCount(redBao);
                    holder.red_bao_max_text.setText("-" + redBao);
                }
            }
        }
        //当前使用的红包额度>0
        if (chooseRedZero == 0) {
            holder.red_bao_max_text.setVisibility(View.GONE);
        } else {
            holder.red_bao_max_text.setVisibility(View.VISIBLE);
            if (reUse >0) {
                //可用红包数额大于使用的情况直接显示使用余额
                if (redBao > reUse) {
                    holder.red_bao_text.setText("剩余" + UtilsAll.DoubleTo_2((redBao-reUse ))+ "元");
                    //设置红包使用情况
                    holder.red_bao_max_text.setText("-" + reUse);
                }else{
                    holder.red_bao_text.setText("剩余" + 0+ "元");
                    holder.red_bao_max_text.setText("-" +redBao);
                }
            }else{
                if(RedAmount==0) {
                    holder.red_bao_max_text.setVisibility(View.GONE);
                }
            }
        }
        //优惠券点击事件
        holder.you_hui_quan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicKRecycleAdapter.onItemClick(position);
            }
        });
        //红包点击事件
        holder.red_bao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRedDialog();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (subTemplateInfoListBeans != null) {
            return subTemplateInfoListBeans.size();
        }
        return 0;
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder {
        public TextView activity_name,youhuiquan_max_text;
        public TextView you_hui_quan_text, red_bao_text, baoshiquan_text, red_bao_max_text;
        public RelativeLayout you_hui_quan, red_bao, baoshiquan;
        public RecyclerView goods_item_recycler;
        public LinearLayout activity_layout;

        public CommentsViewHolder(View itemView) {
            super(itemView);
            activity_name = itemView.findViewById(R.id.activity_name);
            goods_item_recycler = itemView.findViewById(R.id.goods_item_recycler);
            red_bao_max_text = itemView.findViewById(R.id.red_bao_max_text);
            you_hui_quan = itemView.findViewById(R.id.you_hui_quan);
            you_hui_quan_text = itemView.findViewById(R.id.you_hui_quan_text);
            red_bao = itemView.findViewById(R.id.red_bao);
            red_bao_text = itemView.findViewById(R.id.red_bao_text);
            baoshiquan = itemView.findViewById(R.id.baoshiquan);
            baoshiquan_text = itemView.findViewById(R.id.baoshiquan_text);
            activity_layout = itemView.findViewById(R.id.activity_layout);
            youhuiquan_max_text=itemView.findViewById(R.id.youhuiquan_max_text);
        }

    }

    /**
     * 弹出红包输入界面
     */
    private void createRedDialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.red_layout, null);// 得到加载view
        RelativeLayout layout = v.findViewById(R.id.layout_ok);// 加载布局
        final EditText edite = v.findViewById(R.id.edite);
        TextView red_yu_e = v.findViewById(R.id.red_yu_e);
        red_yu_e.setText("红包余额：  " + redBao);
        RelativeLayout cancle = v.findViewById(R.id.cancle);
        RelativeLayout ok_queren = v.findViewById(R.id.ok_queren);

        final Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        edite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    if (Double.parseDouble(s.toString()) > redBao) {
                        ToastUtilsAll.getInstance().showShortToast("红包余额不足");
                    }
                }
            }
        });
        //延迟200毫秒展示系统键盘
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(edite, 0);
                timer.cancel();
            }
        }, 200);
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.cancel();
            }
        });
        ok_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(edite.getText().toString());
                if (a <= redBao) {
                    if (a <= ThisRedUse) {
                        reUse = a;
                        callBackok.redCount(reUse);
                    } else {
                        ToastUtilsAll.getInstance().showShortToast("超出可使用红包最大额度！！");
                    }
                } else {
                    ToastUtilsAll.getInstance().showShortToast("红包无法使用！！");
                }
                notifyDataSetChanged();
                loadingDialog.cancel();
            }
        });
    }
}
