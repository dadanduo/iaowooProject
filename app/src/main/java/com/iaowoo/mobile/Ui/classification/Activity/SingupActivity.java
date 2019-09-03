package com.iaowoo.mobile.Ui.classification.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.Ui.classification.Presenter.SingupPresenter;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.SingupCallBack;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.EvenBus.EventBusMessageWeex;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Presenter.SingupPresenter;
import com.iaowoo.mobile.Ui.classification.View.EditChangeredListend;
import com.iaowoo.mobile.Ui.classification.View.EditText_Phone;
import com.iaowoo.mobile.Utils.DialogUtils;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.SingupCallBack;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by chenda on 2018/3/26.
 * 注册页面
 */

@ContentView(R.layout.signup)
public class SingupActivity extends TitleActivity implements SingupCallBack {
    //手机号码
    @ViewInject(R.id.signup_phone_input)
    EditText_Phone signup_phone_input;
    //验证码
    @ViewInject(R.id.signup_validation_code)
    EditText_Phone signup_validation_code;
    //密码
    @ViewInject(R.id.edite_password)
    EditText_Phone edite_password;
    //邀请码
    @ViewInject(R.id.invite_code)
    EditText_Phone invite_code;
    //获取验证码
    @ViewInject(R.id.signup_get_code)
    TextView signup_get_code;
    @ViewInject(R.id.back_aihaowu)
    ImageView back_aihaowu;
    @ViewInject(R.id.singup_login)
    Button singup_login;
    private Dialog showCode;


    private SingupPresenter singupPresenter=null;

    private String invet="";
    //手机号输入
    private boolean PhoneEdit=false;
    //验证码
    private  boolean code=false;
    //密码
    private boolean password=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this); //绑定注解
        setTitle("注册");
        showBackwardView_show(true);
        this.hidden();
        invet=getIntent().getStringExtra("invate");
        if(!TextUtils.isEmpty(invet)){
            invite_code.setText(invet);
        }
        singupPresenter=new SingupPresenter();
        singupPresenter.setInterface(this);
        singup_login.getBackground().mutate().setAlpha(100);
        //返回按钮
        back_aihaowu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //手机号码监控
        signup_phone_input.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                //是否为手机号码
                if(UtilsAll.isMobileNO(signup_phone_input.getText().toString())){
                    PhoneEdit=true;
                    if(code==true&&password==true) {
                        singup_login.getBackground().mutate().setAlpha(255);
                    }
                }else{
                    PhoneEdit=false;
                    singup_login.getBackground().mutate().setAlpha(100);
                }
            }
        }));
        //密码监控
        edite_password.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                if(!TextUtils.isEmpty(edite_password.getText().toString())){
                    password=true;
                    if(PhoneEdit==true&&code==true) {
                        singup_login.getBackground().mutate().setAlpha(255);
                    }
                }else{
                    password=false;
                    singup_login.getBackground().mutate().setAlpha(100);
                }

            }
        }));
        //验证码监控
        signup_validation_code.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                if(!TextUtils.isEmpty( signup_validation_code.getText().toString())){
                    code=true;
                    if(PhoneEdit==true&&password==true) {
                        singup_login.getBackground().mutate().setAlpha(255);
                    }
                }else{
                    code=false;
                    singup_login.getBackground().mutate().setAlpha(100);
                }
            }
        }));

    }


    //注册按钮
    @Event(value = {R.id.singup_login}, type = View.OnClickListener.class)
    private void singup_login(View view) {
        singupPresenter.register(signup_phone_input.getText().toString(),edite_password.getText().toString(),signup_validation_code.getText().toString(),invite_code.getText().toString());
    }

    //获取验证码
    @Event(value = {R.id.signup_get_code}, type = View.OnClickListener.class)
    private void signupcode(View view) {
        singupPresenter.getValidation(signup_phone_input.getText().toString());
    }

    @Override
    public void showTime(String time) {
        signup_get_code.setText(time);
    }

    @Override
    public void singUpSuccess() {
        //通知weex页面登录成功
        EventBus.getDefault().post(new EventBusMessageWeex("loginOk",""));
        SingleOverAll.getInstance().addMessageTabble();
        showCode.cancel();
        setResult(2);
        finish();
    }

    @Override
    public void showD() {
        DialogUtils dialogUtils= new DialogUtils();
        showCode =  dialogUtils.createLoadingDialog(SingupActivity.this, "注册中请稍后...",true);
    }

    @Override
    public void hideD() {
        if(showCode.isShowing()){
            showCode.dismiss();
        }
    }

    @Override
    public void getRegisterId() {
        ZApplication.REGISTERID = JPushInterface.getRegistrationID(this);

    }
    public void service_signup(View view) {
        Intent mintent=new Intent(SingupActivity.this,UseguideActivity.class);
        mintent.putExtra("title","用户协议");
        mintent.putExtra("url", ConfigH5Url.URL_USERS_AGREEMENT);
        startActivity(mintent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        singupPresenter=null;
    }
}

