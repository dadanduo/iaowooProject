package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Presenter.ForgetPasswordPresenter;
import com.iaowoo.mobile.Ui.classification.View.EditChangeredListend;
import com.iaowoo.mobile.Ui.classification.View.EditText_Phone;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.common.ConfigH5Url;
import com.iaowoo.mobile.interfaceCallback.ClickCallBack;
import com.iaowoo.mobile.interfaceCallback.ForgetPasswordCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码
 * Created by chenda on 2018/4/3.
 */

public class ForgetPasswordActivity extends TitleActivity implements ForgetPasswordCallBack,ClickCallBack {


    @BindView(R.id.phone_input)
    EditText_Phone phoneInput;
    @BindView(R.id.validation_code)
    EditText validationCode;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.phone_login_in)
    LinearLayout phoneLoginIn;
    @BindView(R.id.password_ok_forget)
    Button passwordOkForget;
    private ForgetPasswordPresenter forgetPasswordPresenter=null;

    private boolean PhoneEdit=false;

    private boolean valiteEdit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        ButterKnife.bind(this);
        setTitle("忘记密码");
        phoneLoginIn.setVisibility(View.VISIBLE);
        showBackwardView_show(true);
        if(!TextUtils.isEmpty(PrefManager.getInstance().getIsReview())) {
            //为1显示 不然这不显示
            if (PrefManager.getInstance().getIsReview().equals("1")){
                showForwardView("申述",false);
                setOnclick(this);
            }else{
                showForwardView("申述",true);
                setOnclick(this);
            }
        }
        passwordOkForget.getBackground().mutate().setAlpha(100);

        forgetPasswordPresenter = new ForgetPasswordPresenter();
        forgetPasswordPresenter.setInterface(this);

        phoneInput.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                if(UtilsAll.isMobileNO(phoneInput.getText().toString())){
                    PhoneEdit=true;
                    if(valiteEdit) {
                        passwordOkForget.setEnabled(true);
                        passwordOkForget.getBackground().mutate().setAlpha(255);

                    }
                }else{
                    PhoneEdit=false;
                    passwordOkForget.setEnabled(false);
                    passwordOkForget.getBackground().mutate().setAlpha(100);
                }
            }
        }));
        validationCode.addTextChangedListener(new EditChangeredListend(new EditChangeredListend.EditeCallBack() {
            @Override
            public void editeOver() {
                if(!TextUtils.isEmpty(validationCode.getText().toString())){
                    valiteEdit=true;
                    if(PhoneEdit){
                        passwordOkForget.setEnabled(true);
                        passwordOkForget.getBackground().mutate().setAlpha(255);
                    }
                }else{
                    valiteEdit=false;
                    passwordOkForget.setEnabled(false);
                    passwordOkForget.getBackground().mutate().setAlpha(100);
                }
            }
        }));
    }


    @Override
    public void ShowTime(String time) {
        getCode.setText(time);
    }

    @Override
    public void finish(String msg) {
        finish();
    }

    @OnClick({R.id.get_code, R.id.password_ok_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_code:
                forgetPasswordPresenter.getValidation(phoneInput.getText().toString());
                break;
            case R.id.password_ok_forget:
                Intent intent = new Intent(this, SettingPasswordActivtiy.class);
                intent.putExtra("phone", phoneInput.getText().toString());
                intent.putExtra("validationcode", validationCode.getText().toString());
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==2){
                finish();
            }
        }
    }
    @Override
    public void OnClick() {
        Intent mintent =new Intent(ForgetPasswordActivity.this, WebviewAcitvity.class);
        mintent.putExtra("webview_url",ConfigH5Url.FORGETPASSWORD);
        mintent.putExtra("tag","0");
        startActivity(mintent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        forgetPasswordPresenter=null;
    }
}
