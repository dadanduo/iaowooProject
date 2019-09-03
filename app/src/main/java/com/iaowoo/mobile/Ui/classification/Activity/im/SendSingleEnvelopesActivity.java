package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;
import com.iaowoo.mobile.Ui.classification.Model.im.CommonResponse;
import com.iaowoo.mobile.Ui.classification.Model.im.RedPayType;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.im.RedPacketMessage;
import com.iaowoo.mobile.interfaceCallback.RedPayListener;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

public class SendSingleEnvelopesActivity extends TitleBarActivity implements TitleBarActivity.TitleBarClickListener, RedPayListener {
    private static final String TARGET_ID = "targetId";
    @BindView(R.id.tv_top_tip)
    TextView tvTopTip;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.ll_amount_layout)
    LinearLayout llAmountLayout;
    @BindView(R.id.et_peak_message)
    EditText etPeakMessage;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.tv_bottom_tip)
    TextView tvBottomTip;

    private String targetId;
    private List<RedPayType> redPayTypeList = new ArrayList<>();
    private DialogUtils dialogUtils;
    private String mAmount;
    private RedPayType redPayType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("TAG", "SendSingleEnvelopesActivity====");
        setContentView(R.layout.activity_im_send_single_peak);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        showRightImgView(R.mipmap.view_more_icon);
    }

    private void initListener() {
        setTitleTarListener(this);
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAmount = s.toString().trim();
                if (TextUtils.isEmpty(mAmount) || !isNumber(mAmount)) {
                    btnSure.setEnabled(false);
                } else if (Double.parseDouble(mAmount) > 200) {
                    tvTopTip.setVisibility(View.VISIBLE);
                    btnSure.setEnabled(false);
                } else {
                    tvTopTip.setVisibility(View.GONE);
                    btnSure.setEnabled(true);
                    tvAmount.setText(mAmount);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //金额验证
    public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    private void initData() {
        setTitle("发红包");
        dialogUtils = new DialogUtils();
        for (int i=0; i<2;i++) {
            RedPayType redPayType = new RedPayType();
            redPayType.setPayName("红包");
            redPayType.setBalance("2000");
            redPayTypeList.add(redPayType);
        }
        targetId = getIntent().getStringExtra(TARGET_ID);
    }

    // BindView相关事件
    @OnClick({R.id.btn_sure, R.id.tv_bottom_tip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sure:
                if (isFastDoubleClick()) {
                    return;
                }
                redPayType = redPayTypeList.get(0);
                dialogUtils.showRedPayConfirmDialog(this, redPayType, mAmount, this);

                break;
            case R.id.tv_bottom_tip:
                break;
        }
    }


    // 红包支付相关事件
    @Override
    public void onRedPayClick(int id, String pwd) {
        switch (id){
            case R.id.ll_select_pay_type:
                if (dialogUtils == null) {
                    dialogUtils = new DialogUtils();
                }
                dialogUtils.showPayTypeListDialog(this, R.layout.dialog_pay_type_list, redPayTypeList, this);
                break;
            case R.id.btn_confirm_pay:
                if (isFastDoubleClick()) {
                    return;
                }
                String amount = etAmount.getText().toString().trim();
                String remark = etPeakMessage.getText().toString().trim();
                sendRedMsg(amount, remark, "");
//                generateRedEnvelope(amount, remark, "", pwd);
                break;
        }
    }

    // 选择支付类型事件
    @Override
    public void onItemRedPayTypeClick(int position) {
        redPayType = redPayTypeList.get(position);
        if (dialogUtils == null) {
            dialogUtils = new DialogUtils();
        }
        dialogUtils.showRedPayConfirmDialog(this, redPayType, mAmount, this);
    }

    // 生成红包
    public void generateRedEnvelope(final String amount, final String remark, final String name, String pwd) {
        Log.i("TAG", "PWD"+pwd);
        showD();
        String loginToken = PrefManager.getInstance().getToken();
        BigDecimal bigDecimal = new BigDecimal(amount);
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken", loginToken);
        paramsMap.put("friendId", targetId);
        paramsMap.put("num", 1);
        paramsMap.put("amount", bigDecimal);
        paramsMap.put("remark", remark);
        paramsMap.put("password", pwd);
        paramsMap.put("type", 1); // 1:普通红包，2：随机红包，3：普通余额红包，4：随机余额红包
        OkhttpManager.getInstance(this).requestPostByAsyn(false, UtilsOkHttpAll.GENERATE_RED_ENVELOPE, "GENERATE_RED_ENVELOPE_SINGLE", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                CommonResponse commonResponse = JSON.parseObject(result.toString(), CommonResponse.class);
                if (commonResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    sendRedMsg(amount, remark, name);
                } else {
                    ToastUtilsAll.getInstance().show(commonResponse.getDescribe());
                }
                hideD();
            }

            @Override
            public void onReqFailed(String errorMsg) {
                hideD();
                ToastUtilsAll.getInstance().show(errorMsg);
            }
        });
    }

    // 发送红包信息
    private void sendRedMsg(String amount, String msg, String name) {
        final RedPacketMessage redPacketMessage = new RedPacketMessage();
        redPacketMessage.setRemark(msg);
        redPacketMessage.setAmount(amount);
        redPacketMessage.setExtra("0");
        Message message = Message.obtain(targetId, Conversation.ConversationType.PRIVATE, redPacketMessage);

        RongIM.getInstance().sendMessage(message, "niaiai", "ananan", new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {
                Log.i("TAG", "onAttached" + message);
            }

            @Override
            public void onSuccess(Message message) {
                Log.i("TAG", "onSuccess" + message);
                finish();
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                Log.i("TAG", "onError" + message);
            }
        });
    }

    public static void start(Context context, String targetId) {
        Intent intent = new Intent(context, SendSingleEnvelopesActivity.class);
        intent.putExtra(TARGET_ID, targetId);
        context.startActivity(intent);
    }

    private long lastClickTime;

    private boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    // titleBar点击事件
    @Override
    public void OnTitleBarClick(int id) {
        switch (id) {
            case R.id.rl_title_bar_right:
                break;
        }
    }
}
