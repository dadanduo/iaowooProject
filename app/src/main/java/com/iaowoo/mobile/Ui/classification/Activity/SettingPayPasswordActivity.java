package com.iaowoo.mobile.Ui.classification.Activity;

import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Ui.classification.Presenter.SettingPayPasswordProsenter;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Presenter.SettingPayPasswordProsenter;
import com.iaowoo.mobile.Ui.classification.View.EditText_Phone;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.ToastUtilsAll;
import com.iaowoo.mobile.Utils.UtilsAll;
import com.iaowoo.mobile.Weex.extend.Model.UserInfoWithToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

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
 * @Description: ${todo}()
 * @date 2018/12/7
 * @email ${18011009889@163.com}
 */
public class SettingPayPasswordActivity extends  BaseBufferActivity {


    @BindView(R.id.password_txt)
    EditText_Phone password_txt;
    @BindView(R.id.repasswrod_txt)
    EditText_Phone repasswrod_txt;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.titile_all)
    RelativeLayout titile_all;
    @BindView(R.id. return_all_title)
    RelativeLayout return_all_title;
    @BindView(R.id.title_text)
    TextView title_text;
    //两次密码均输入
    private boolean Alledite=false;
    private String passwordOne,passwordTwo;
    private  TextView get_code_setpassword;
    private boolean isOkGetSms=true;
    private SettingPayPasswordProsenter settingPayPasswordProsenter;
    @Override
    public int getLayoutResId() {
        return R.layout.setting_pay_password;
    }
    @Override
    protected void initView() {
        super.initView();
        this.allState();
        this.setViewMarginTop(titile_all);
        title_text.setText("设置支付密码");
        name.setText("请为您的账号"+ PrefManager.getInstance().getMobile()+"设置支付密码");
    }
    @Override
    protected void initData() {
        super.initData();
        settingPayPasswordProsenter =new SettingPayPasswordProsenter();
        password_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                passwordOne=s.toString();
                if(!TextUtils.isEmpty(passwordOne)&&!TextUtils.isEmpty(passwordTwo)){
                    next.getBackground().mutate().setAlpha(255);
                    next.setClickable(true);
                }else{
                    next.getBackground().mutate().setAlpha(100);
                    next.setClickable(false);
                }
            }
        });
        repasswrod_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                passwordTwo=s.toString();
                if(!TextUtils.isEmpty(passwordOne)&&!TextUtils.isEmpty(passwordTwo)){
                    next.getBackground().mutate().setAlpha(255);
                    next.setClickable(true);
                }else{
                    next.getBackground().mutate().setAlpha(100);
                    next.setClickable(false);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!passwordOne.endsWith(passwordTwo)){
                    ToastUtilsAll.getInstance().showShortToast("亲！您输入的密码不一致哦！");
                }else if(password_txt.getText().length()<6){
                    ToastUtilsAll.getInstance().showShortToast("支付密码为6-20位");
                }else if(repasswrod_txt.getText().length()<6){
                    ToastUtilsAll.getInstance().showShortToast("支付密码为6-20位");
                }else{
                    createPasswordPayDialog();
                }
            }
        });
        return_all_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private  void createPasswordPayDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View  v = inflater.inflate(R.layout.yazhengma, null);// 得到加载view
        LinearLayout  layout = v.findViewById(R.id.bg);// 加载布局
        final EditText_Phone shuru=v.findViewById(R.id.shuru);
        RelativeLayout cancle=v.findViewById(R.id.cancle);
        TextView phone=v.findViewById(R.id.phone);
        RelativeLayout ok_queren=v.findViewById(R.id.ok_queren);
        get_code_setpassword=v.findViewById(R.id.get_code_setpassword);

        phone.setText(PrefManager.getInstance().getMobile()+"");
        //延迟200毫秒展示系统键盘
        final Timer  timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(shuru, 0);
                               timer.cancel();
                           }},
                200);
        final Dialog loadingDialog = new Dialog(this, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        shuru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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
        //取消
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.cancel();
            }
        });
        //确认
        ok_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(shuru.getText().toString())){
                    ToastUtilsAll.getInstance().showShortToast("验证码不能为空，亲！");
                }else if(!TextUtils.isEmpty(passwordOne)&&!TextUtils.isEmpty(passwordTwo)){
                    try {
                        String str1= UtilsAll.encryptionPassword(PrefManager.getInstance().getMobile(),passwordOne);
                        String s=str1.replaceAll("\n", "");
                        String password_new = URLEncoder.encode(s,"UTF-8");

                        String str2= UtilsAll.encryptionPassword(PrefManager.getInstance().getMobile(),passwordTwo);
                        String s2=str2.replaceAll("\n", "");
                        String password_new1 = URLEncoder.encode(s,"UTF-8");
                        settingPayPasswordProsenter.setPayPassword_sms_code(shuru.getText().toString(),password_new, password_new1, new SettingPayPasswordProsenter.CallBack() {
                            @Override
                            public void SettingOk() {
                                ToastUtilsAll.getInstance().showShortToast("恭喜您支付密码设置成功！请继续操作！");
                                PrefManager.getInstance().setPayPassword("1");
                                finish();
                            }
                        });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //获取验证码
        get_code_setpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( isOkGetSms) {
                    startTime();
                    settingPayPasswordProsenter.sendPayPassword_sms_code(PrefManager.getInstance().getMobile() + "");
                }else{
                    ToastUtilsAll.getInstance().showShortToast("亲！请稍后再试 ");
                }
            }
        });
    }

    //开启倒计时
    public  void  startTime() {
        isOkGetSms=false;
        /** 倒计时60秒，一次1秒 */
        CountDownTimer timer = new CountDownTimer(60*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                get_code_setpassword.setText(millisUntilFinished/1000+"秒"+"后重试");
            }
            @Override
            public void onFinish() {
                get_code_setpassword.setText("获取验证码");
                isOkGetSms=true;
            }
        }.start();
    }

}
