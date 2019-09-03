package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Presenter.ForgetPasswordPresenter;
import com.iaowoo.mobile.Ui.classification.View.EditChangeredListend;
import com.iaowoo.mobile.interfaceCallback.ForgetPasswordCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}(设置密码)
 * @date 2018/6/16
 */
public class SettingPasswordActivtiy extends TitleActivity  implements ForgetPasswordCallBack {
    @BindView(R.id.password_forget)
    EditText passwordForget;
    @BindView(R.id.reset_password_forget)
    EditText resetPasswordForget;
    @BindView(R.id.password_ok_forget)
    Button passwordOkForget;
    private ForgetPasswordPresenter forgetPasswordPresenter=null;
    private String phone;
    private String validationcode;

    private Boolean pass=false;
    private Boolean repass=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_password);
        ButterKnife.bind(this);
        showBackwardView_show(true);
        setTitle("设置密码");
        phone=getIntent().getStringExtra("phone");
        validationcode=getIntent().getStringExtra("validationcode");
        forgetPasswordPresenter = new ForgetPasswordPresenter();
        forgetPasswordPresenter.setInterface(this);

        passwordForget.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                if(!TextUtils.isEmpty(passwordForget.getText().toString())){
                    pass=true;
                    if(repass) {
                        passwordOkForget.getBackground().mutate().setAlpha(255);
                        passwordOkForget.setEnabled(true);
                    }
                }else{
                    pass=false;
                    passwordOkForget.getBackground().mutate().setAlpha(100);
                    passwordOkForget.setEnabled(false);
                }
            }
        }));
        resetPasswordForget.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                if(!TextUtils.isEmpty(resetPasswordForget.getText().toString())){
                    repass=true;
                    if(pass) {
                        passwordOkForget.setEnabled(true);
                        passwordOkForget.getBackground().mutate().setAlpha(255);
                    }
                }else{
                    repass=false;
                    passwordOkForget.setEnabled(false);
                    passwordOkForget.getBackground().mutate().setAlpha(100);
                }
            }
        }));

    }

    @OnClick(R.id.password_ok_forget)
    public void onClick() {
        forgetPasswordPresenter.submitOk(phone,validationcode, passwordForget.getText().toString(), resetPasswordForget.getText().toString());
    }

    @Override
    public void ShowTime(String time) {

    }

    @Override
    public void finish(String msg) {
        //数据是使用Intent返回
        Intent intent = new Intent();
        //设置返回数据
        this.setResult(2, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        forgetPasswordPresenter=null;
    }
}
