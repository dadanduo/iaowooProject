package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.mention.RongMentionManager;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

public class SendGroupEnvelopesActivity extends TitleBarActivity implements RedPayListener {
    private static final String TARGET_ID = "targetId";
    private static final int ENVELOPES_ORDINARY = 0; // 普通红包
    private static final int ENVELOPES_LUCKY = 1; // 拼手气红包
    @BindView(R.id.tv_top_tip)
    TextView tvTopTip;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.ll_amount_layout)
    LinearLayout llAmountLayout;
    @BindView(R.id.tv_peak_type)
    TextView tvPeakType;
    @BindView(R.id.et_peak_num)
    EditText etPeakNum;
    @BindView(R.id.ll_peak_num_layout)
    LinearLayout llPeakNumLayout;
    @BindView(R.id.tv_group_member_num)
    TextView tvGroupMemberNum;
    @BindView(R.id.et_peak_message)
    EditText etPeakMessage;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.tv_bottom_tip)
    TextView tvBottomTip;
    @BindView(R.id.tv_peak_amount_icon)
    TextView tvPeakAmountIcon;

    private String mAmount;
    private String mPeakNum;
    private String targetId;
    private double totalAmount = 0;

    private int envelopesType = ENVELOPES_LUCKY;
    private List<RedPayType> redPayTypeList = new ArrayList<>();
    private RedPayType redPayType;

    private DialogUtils dialogUtils;
    private NoUnderClickableSpan peakTypeClick = new NoUnderClickableSpan() {
        public void onClick(View var1) {
            if (envelopesType == ENVELOPES_LUCKY) {
                envelopesType = ENVELOPES_ORDINARY;
            } else if (envelopesType == ENVELOPES_ORDINARY) {
                envelopesType = ENVELOPES_LUCKY;
            }
            setPeakTypeStyle();
            checkType();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_send_group_peak);
        ButterKnife.bind(this);
        initListener();
        initData();
    }

    private void initListener() {
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAmount = s.toString().trim();
                if (TextUtils.isEmpty(mAmount) || !isNumber(mAmount)) {
                    btnSure.setEnabled(false);
                    return;
                }

                double dAmount = Double.parseDouble(mAmount);
                if(envelopesType == ENVELOPES_ORDINARY) { // 普通红包
                    // 普通红包， 单个红包金额不可超过200元
                    if (!amountIsSafeLimitRange(dAmount, envelopesType)) {
                        return;
                    }

                    // 处理红包数逻辑
                    if (!TextUtils.isEmpty(mPeakNum)) {
                        int iPeakNum = Integer.parseInt(mPeakNum);
                        totalAmount =  iPeakNum * dAmount;

                        // 判断红包数是否在限制范围内
                        if (!peakIsSafeLimitRange(iPeakNum)){
                            return;
                        }

                        tvAmount.setText("" + totalAmount);
                        tvTopTip.setVisibility(View.GONE);
                        btnSure.setEnabled(true);
                    } else {
                        tvTopTip.setVisibility(View.GONE);
                        btnSure.setEnabled(false);
                    }

                } else if (envelopesType == ENVELOPES_LUCKY) { // 拼手气红包
                    tvAmount.setText(mAmount);

                    // 拼手气红包， 总金额不可超过20000元
                    if (!amountIsSafeLimitRange(dAmount, envelopesType)) {
                        return;
                    }

                    // 处理红包数逻辑
                    if (!TextUtils.isEmpty(mPeakNum)) {
                        int iPeakNum = Integer.parseInt(mPeakNum);
                        double singlePeakAmount = dAmount / iPeakNum;
                        totalAmount =  iPeakNum * dAmount;

                        // 判断红包数是否在限制范围内
                        if (!peakIsSafeLimitRange(iPeakNum)){
                            return;
                        }

                        if(singlePeakAmount > 200) {
                            showErrorTip(R.string.single_red_peak_tip);
                            return;
                        }

                        tvTopTip.setVisibility(View.GONE);
                        btnSure.setEnabled(true);
                    } else {
                        tvTopTip.setVisibility(View.GONE);
                        btnSure.setEnabled(false);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPeakNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPeakNum = s.toString().trim();
                if (TextUtils.isEmpty(mPeakNum)) {
                    btnSure.setEnabled(false);
                    return;
                }

                // 红包数限制提示
                int iPeakNum = Integer.parseInt(mPeakNum);
                if (!peakIsSafeLimitRange(iPeakNum)){
                    return;
                }

                if (!TextUtils.isEmpty(mAmount) && isNumber(mAmount)) {
                    double dAmount = Double.parseDouble(mAmount);
                    double singlePeakAmount = dAmount / iPeakNum;
                    totalAmount =  iPeakNum * dAmount;

                    if (mAmount.startsWith(".") || mAmount.endsWith(".")) {
                        btnSure.setEnabled(false);
                        tvTopTip.setVisibility(View.GONE);
                        return;
                    }

                    if (envelopesType == ENVELOPES_ORDINARY) { // 普通红包
                        tvAmount.setText("" + totalAmount);
                        if (!amountIsSafeLimitRange(dAmount, envelopesType)) {
                            return;
                        }
                        tvTopTip.setVisibility(View.GONE);
                        btnSure.setEnabled(true);

                    } else if(envelopesType == ENVELOPES_LUCKY) { // 拼手气红包
                        tvAmount.setText("" + mAmount);

                        if (!amountIsSafeLimitRange(dAmount, envelopesType)) {
                            return;
                        }

                        if (singlePeakAmount > 200) {
                            showErrorTip(R.string.single_red_peak_tip);
                            return;
                        }

                        tvTopTip.setVisibility(View.GONE);
                        btnSure.setEnabled(true);
                    }
                } else {
                    btnSure.setEnabled(false);
                    tvTopTip.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    // 红包数限制在1-100之间包括100
    private boolean peakIsSafeLimitRange(int iPeakNum) {
        boolean flag = true;
        if (iPeakNum == 0) {
            showErrorTip(R.string.red_peak_num_zero_tip);
            flag = false;
        } else if(iPeakNum > 100) {
            showErrorTip(R.string.red_peak_num_limit_tip);
            flag = false;
        }
        return flag;
    }

    // 发红包，输入信息不合格时提示错误信息
    private void showErrorTip(int tipRes){
        tvTopTip.setVisibility(View.VISIBLE);
        tvTopTip.setText(tipRes);
        btnSure.setEnabled(false);
    }

    private boolean amountIsSafeLimitRange(double dAmount, int redType){
        boolean flag = true;
        switch (redType){
            case ENVELOPES_ORDINARY:
                // 普通红包， 单个红包金额不可超过200元
                if (dAmount> 200) {
                    showErrorTip(R.string.single_red_peak_tip);
                    flag = false;
                }
                break;
            case ENVELOPES_LUCKY:
                // 拼手气红包， 总金额不可超过20000元
                if (dAmount > 20000) {
                    showErrorTip(R.string.single_pay_tip);
                    flag = false;
                }
                break;
            default:
                break;
        }
        return flag;
    }

    //金额验证
    public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    private void initData() {
        setTitle("发红包");
        setPeakTypeStyle();

        dialogUtils = new DialogUtils();
        targetId = getIntent().getStringExtra(TARGET_ID);
        if (TextUtils.isEmpty(targetId)) {
            return;
        }

        for (int i=0; i<2;i++) {
            RedPayType redPayType = new RedPayType();
            redPayType.setPayName("红包"+i);
            redPayType.setBalance("20"+i);
            redPayTypeList.add(redPayType);
        }
        rongCloundNumber();
    }

    private void setPeakTypeStyle() {
        String peakTypeStr = "";
        if (envelopesType == ENVELOPES_LUCKY) {
            peakTypeStr = getResources().getString(R.string.peck_lucky_to_nomal);
            Drawable drawableLeft = getResources().getDrawable(R.mipmap.spell_red_envelope_icon);
            drawableLeft.setBounds( 0, 0, drawableLeft.getMinimumWidth(),drawableLeft.getMinimumHeight());
            tvPeakAmountIcon.setCompoundDrawables(drawableLeft, null, null, null);
            tvPeakAmountIcon.setText("总金额");
        } else if (envelopesType == ENVELOPES_ORDINARY) {
            peakTypeStr = getResources().getString(R.string.peck_nomal_to_lucky);
            tvPeakAmountIcon.setCompoundDrawables(null, null, null, null);
            tvPeakAmountIcon.setText("单个金额");
        }
        int splitIndex = peakTypeStr.indexOf("，") + 1;
        if (splitIndex >= 1) {
            SpannableString spannableString = new SpannableString(peakTypeStr);
            spannableString.setSpan(peakTypeClick, splitIndex, peakTypeStr.length(), 33);
            spannableString.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.perk_type_click_color)), splitIndex, peakTypeStr.length(), 33);
            tvPeakType.setText(spannableString);
            tvPeakType.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void rongCloundNumber() {
        if (RongMentionManager.getInstance().getGroupMembersProvider() != null) {
            RongMentionManager.getInstance().getGroupMembersProvider().getGroupMembers(targetId, new RongIM.IGroupMemberCallback() {
                public void onGetGroupMembersResult(List<UserInfo> userInfoList) {
                    if (userInfoList != null && userInfoList.size() > 0) {
                        tvGroupMemberNum.setVisibility(View.VISIBLE);
                        String groupNumberStr = getResources().getString(R.string.group_number);
                        tvGroupMemberNum.setText(String.format(groupNumberStr, userInfoList.size()));
                    } else {
                        tvGroupMemberNum.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    private void checkType() {
        String amount = etAmount.getText().toString().trim();
        String num = etPeakNum.getText().toString().trim();

        if (envelopesType == ENVELOPES_LUCKY) {
            if (!TextUtils.isEmpty(amount) && !TextUtils.isEmpty(num)) {
                double singleAmount = Double.parseDouble(mAmount);
                int peakNum = Integer.parseInt(num);
                double totalAmount = singleAmount * peakNum;
                etAmount.setText("" + totalAmount);

                if (!amountIsSafeLimitRange(totalAmount, ENVELOPES_LUCKY)) {
                    return;
                }

                if (singleAmount > 200) {
                    showErrorTip(R.string.single_red_peak_tip);
                    return;
                }

                if (!peakIsSafeLimitRange(peakNum)) {
                    return;
                }

                tvTopTip.setVisibility(View.GONE);
                btnSure.setEnabled(true);
            } else {
                tvTopTip.setVisibility(View.GONE);
                btnSure.setEnabled(false);
            }
        } else if (envelopesType == ENVELOPES_ORDINARY) {
            if (!TextUtils.isEmpty(amount) && !TextUtils.isEmpty(num)) {
                double totalAmount = Double.parseDouble(mAmount);
                int peakNum = Integer.parseInt(num);
                double singleAmount = totalAmount / peakNum;

                etAmount.setText(""+singleAmount);
                if (!amountIsSafeLimitRange(singleAmount, ENVELOPES_ORDINARY)) {
                    return;
                }

                tvTopTip.setVisibility(View.GONE);
                btnSure.setEnabled(true);
            } else {
                tvTopTip.setVisibility(View.GONE);
                btnSure.setEnabled(false);
            }
        }
    }

    // BindView相关事件
    @OnClick({R.id.tv_peak_type, R.id.btn_sure, R.id.tv_bottom_tip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_peak_type:
                break;
            case R.id.btn_sure:
                if (isFastDoubleClick()) {
                    return;
                }
                redPayType = redPayTypeList.get(0);
                dialogUtils.showRedPayConfirmDialog(this, redPayType, ""+totalAmount, this);
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
        dialogUtils.showRedPayConfirmDialog(this, redPayType, ""+totalAmount, this);
    }



    // 生成红包
    public void generateRedEnvelope(final String amount, final String msg, final String name, String pwd) {
        showD();
        String loginToken = PrefManager.getInstance().getToken();
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken", loginToken);
        paramsMap.put("groupId", targetId);
        paramsMap.put("amount", amount);
        paramsMap.put("remark", msg);
        paramsMap.put("password", pwd);

        String redNum = etPeakNum.getText().toString().trim();
        paramsMap.put("num", Integer.parseInt(redNum));

        // 请求参数 1:普通红包，2：随机红包，3：普通余额红包，4：随机余额红包
        if (envelopesType == ENVELOPES_ORDINARY) { // 普通红包
            paramsMap.put("type", 1);
        } else if (envelopesType == ENVELOPES_LUCKY) { // 拼手气红包
            paramsMap.put("type", 2);
        }

        OkhttpManager.getInstance(this).requestPostByAsyn(false, UtilsOkHttpAll.GENERATE_RED_ENVELOPE, "GENERATE_RED_ENVELOPE_GROUP", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                CommonResponse commonResponse = JSON.parseObject(result.toString(), CommonResponse.class);
                if (commonResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    sendRedMsg(amount, msg, name);
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

    // 发送红包消息
    private void sendRedMsg(String amount, String msg, String name) {
        final RedPacketMessage redPacketMessage = new RedPacketMessage();
        redPacketMessage.setRemark(msg);
        redPacketMessage.setAmount(amount);
        redPacketMessage.setExtra("0");
        Message message = Message.obtain(targetId, Conversation.ConversationType.GROUP, redPacketMessage);

        RongIM.getInstance().sendMessage(message, "niaiai", "ananan", new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

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

    // 启动本activity
    public static void start(Context context, String targetId) {
        Intent intent = new Intent(context, SendGroupEnvelopesActivity.class);
        intent.putExtra(TARGET_ID, targetId);
        context.startActivity(intent);
    }

    //  避免多次点击问题
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
}
