package com.iaowoo.mobile.Ui.classification.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Ui.classification.Presenter.LoginPresenter;
import com.iaowoo.mobile.Ui.classification.View.EditChangeredListend;
import com.iaowoo.mobile.Ui.classification.View.EditText_Phone;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.TranslateUtils;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.im.RongIMUtils;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.iaowoo.mobile.interfaceCallback.LoginCallBack;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

/**
 * 登录页面
 * Created by chenda on 2018/3/26.
 */

public class LoginActivity extends TitleActivity implements ClickCallBack,LoginCallBack {
    //验证码登录手机号
    @BindView(R.id.phone_input)
    EditText_Phone editText_phone;
    //验证码
    @BindView(R.id.validation_code)
    EditText validation_code;
    //获取验证码
    @BindView(R.id.get_code)
    TextView get_code;
    @BindView(R.id.password_logoin)
    LinearLayout password_logoin;
    @BindView(R.id.phone_login_in)
    LinearLayout phone_login_in;
    @BindView(R.id.updatepassword)
    TextView updatePassword;
    //密码登录手机号码
    @BindView(R.id.phone_password_intput)
    EditText_Phone phone_password_intput;
    //密码登录密码框
    @BindView(R.id.password_eite_in)
    EditText_Phone password_eite_in;
    //第三方登录
    @BindView(R.id.the_third_party)
    LinearLayout the_third_party;
    @BindView(R.id.login_in)
    Button login_in;
    @BindView(R.id.woshuai)
    ImageView woshuai;
    @BindView(R.id.signup)
    TextView signup;
    @BindView(R.id.close_aihaowu)
    RelativeLayout close_aihaowu;
    @BindView(R.id.forget_password)
    TextView forget_password;
    //是否是密码登录
    private boolean phone_and_password=true;
    private LoginPresenter loginPresenter=null;
    //手机号输入
    private boolean PhoneEdit=false;
    //验证码
    private  boolean code=false;
    //密码登录手机号码
    private boolean PassWordEdit=false;
    //密码
    private boolean pas=false;
    private Dialog showCode;
    private String url_h5;
    private  int tags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        tags=getIntent().getIntExtra("tags",0);
        setOnclick(this);
        setTitle("登录");
        this.hidden();
        showForwardView("忘记密码",true);
        updatePassword.setText("切换成验证码登录>>");
        showBackwardView_show(true);
        loginPresenter= new LoginPresenter(tags);
        loginPresenter.setLoginInterface(this);
        loginPresenter.getAppConfig();
        login_in.getBackground().mutate().setAlpha(100);
        //隐藏
        if(tags==1){
            updatePassword.setVisibility(View.GONE);
            signup.setVisibility(View.GONE);
            the_third_party.setVisibility(View.GONE);
            showForwardView("忘记密码",false);
        }
        //关闭登录页面
        close_aihaowu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish_activity();
            }
        });
        //忘记密码
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent =new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivityForResult(mintent,2);
            }
        });
        //读取本地缓存的账号
        String mobile= PrefManager.getInstance().getMobile();
        //展示在phoneText上面
        if(!TextUtils.isEmpty(mobile)) {
            editText_phone.setText(mobile);
            phone_password_intput.setText(mobile);
        }
        if(UtilsAll.isMobileNO(editText_phone.getText().toString())){
            PhoneEdit=true;
        }
        if(UtilsAll.isMobileNO(phone_password_intput.getText().toString())){
            PassWordEdit=true;
        }
        editText_phone.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                if(UtilsAll.isMobileNO(editText_phone.getText().toString())){
                    PhoneEdit=true;
                    if(code)
                    login_in.setEnabled(true);
                    login_in.getBackground().mutate().setAlpha(255);
                }else{
                    PhoneEdit=false;
                    login_in.setEnabled(false);
                    login_in.getBackground().mutate().setAlpha(100);
                }
            }
        }));
        validation_code.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                if(!TextUtils.isEmpty(validation_code.getText().toString())){
                    code=true;
                    if(PhoneEdit)
                    login_in.setEnabled(true);
                    login_in.getBackground().mutate().setAlpha(255);
                }else{
                    code=false;
                    login_in.setEnabled(false);
                    login_in.getBackground().mutate().setAlpha(100);
                }

            }
        }));
        phone_password_intput.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                if(UtilsAll.isMobileNO(phone_password_intput.getText().toString())){
                    PassWordEdit=true;
                    if(pas)
                    login_in.setEnabled(true);
                    login_in.getBackground().mutate().setAlpha(255);
                }else{
                    PassWordEdit=false;
                    login_in.setEnabled(false);
                    login_in.getBackground().mutate().setAlpha(100);
                }
            }
        }));
        password_eite_in.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                if(!TextUtils.isEmpty(password_eite_in.getText().toString())){
                    pas=true;
                    if(PassWordEdit)
                    login_in.setEnabled(true);
                    login_in.getBackground().mutate().setAlpha(255);
                }else{
                    pas=false;
                    login_in.setEnabled(false);
                    login_in.getBackground().mutate().setAlpha(100);
                }
            }
        }));
        //沃帅商城
        woshuai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsAll.GoWebviewAll(LoginActivity.this,url_h5);
            }
        });
    }
    /**
     * 获取验证码
     * @param view
     */
    public  void get_code(View view) {
        loginPresenter.getValidation(editText_phone.getText().toString());
    }

    /**
     * 注册
     * @param view
     */
    public void signup(View view){
        Intent mintent =new Intent(LoginActivity.this,SingupActivity.class);
        startActivityForResult(mintent,1);
    }
    /**
     * 登录
     * @param view
     */
    public void login_in(View view){
        loginPresenter.startLogin(editText_phone.getText().toString(),phone_and_password,validation_code.getText().toString(),password_eite_in.getText().toString(),phone_password_intput.getText().toString());
    }

    /**
     * 切换登录方式
     * @param view
     */
    public void updatepassword(View view){
        if(!phone_and_password) {
            password_logoin.setVisibility(View.VISIBLE);
            phone_login_in.setVisibility(View.GONE);
            password_logoin.setAnimation(TranslateUtils.moveToViewShow());
            //密码登录从右边滑入
            password_logoin.startAnimation(AnimationUtils.loadAnimation(
                    LoginActivity.this, R.anim.slide_from_left
            ));
            updatePassword.setText("切换成验证码登录>>");
            showForwardView("忘记密码",true);
            phone_and_password=true;
            if(!TextUtils.isEmpty(password_eite_in.getText().toString())&&UtilsAll.isMobileNO(phone_password_intput.getText().toString())){
                login_in.setEnabled(true);
                login_in.getBackground().mutate().setAlpha(255);
            }else{
                login_in.setEnabled(false);
                login_in.getBackground().mutate().setAlpha(100);
            }
        }else{
            password_logoin.setVisibility(View.GONE);
            phone_login_in.setVisibility(View.VISIBLE);
            //手机登录布局从左边滑入
            phone_login_in.startAnimation(AnimationUtils.loadAnimation(
                    LoginActivity.this, R.anim.slide_from_right
            ));
            updatePassword.setText("切换成密码登录>>");
            phone_and_password=false;
            showForwardView("忘记密码",false);
            if(!TextUtils.isEmpty(validation_code.getText().toString())&&UtilsAll.isMobileNO(editText_phone.getText().toString())){
                login_in.setEnabled(true);
                login_in.getBackground().mutate().setAlpha(255);
            }else{
                login_in.setEnabled(false);
                login_in.getBackground().mutate().setAlpha(100);
            }

        }
    }
    //忘记密码
    @Override
    public void OnClick() {
    }
    /**
     * 展示时间
     * @param time
     */
    @Override
    public void showTime(String time) {
        get_code.setText(time);
    }
    /**
     * 结束app
     */
    @Override
    public void finish_activity() {
        //通知页面刷新
        Intent intent = new Intent();
        intent.putExtra(RdioBroadCast.SHOWTAG,"login");
        intent.putExtra(RdioBroadCast.DATA,"");
        intent.setAction(RdioBroadCast.BOARD);
        sendBroadcast(intent);
        //app改版后刷新小红点和新人红包
        EventBus.getDefault().post(new EventBusMessageRefresh(0));
        EventBus.getDefault().post(new EventBusMessageRefresh(1));
        //通知weex页面登录成功
        EventBus.getDefault().post(new EventBusMessageWeex("loginOk",""));
        LogPrint.printError("添加消息表");
        SingleOverAll.getInstance().addMessageTabble();
        setResult(3);
        finish();

    }
    /**
     *发送验证码
     */
    @Override
    public void showD() {
        showCode =createLoadingDialog(LoginActivity.this, "准备发送验证码中",true);

    }
    @Override
    public void hideD() {
        if(showCode.isShowing()){
            showCode.dismiss();
        }
    }

    @Override
    public void getRegisteId() {
        ZApplication.REGISTERID = JPushInterface.getRegistrationID(LoginActivity.this);
    }

    /**
     * @param mobile
     */
    @Override
    public void saveM(String mobile) {
        //记住手机号码
        PrefManager.getInstance().saveMobile(mobile);
    }

    /**
     * 第三方登录app配置
     * @param status
     * @param h5_URL
     */
    @Override
    public void AppConfig(int status, String h5_URL) {
        //status==1显示第三方登录
        if(status==1){
            the_third_party.setVisibility(View.GONE);
        }
        this.url_h5=h5_URL;
    }

    @Override
    public void connetRongIM(String loginToken) {
        RongIMUtils.connect(this, loginToken);
    }

    /**
     * 回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            if(resultCode==2) {
                finish_activity();
            }
        }
    }

    /**
     * ondestory
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(showCode!=null){
            showCode.cancel();
        }
        loginPresenter=null;
    }
}
