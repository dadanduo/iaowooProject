package com.iaowoo.mobile.Ui.classification.View;

import android.text.Editable;
import android.text.TextWatcher;

import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Utils.LogPrint;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}()
 * @date 2018/6/15
 */
public class EditChangeredListend implements TextWatcher {
    private CharSequence temp; // 监听前的文本
    private int editStart; // 光标开始位置
    private int editEnd; // 光标结束位置
    private EditeCallBack editeCallBack;
    public EditChangeredListend(EditeCallBack editeCallBack)
    {
        this.editeCallBack=editeCallBack;
    }

    // 输入文本之前的状态
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,int after) {
        temp = s;
    }

    // 输入文字中的状态，count是一次性输入字符数
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//          if (charMaxNum - s.length() <= 5) {
//              tip.setText("还能输入" + (charMaxNum - s.length()) + "字符");
//          }
//        tip.setText((s.length()) + "/" + charMaxNum);
        LogPrint.printError("输入文字中");
    }

    // 输入文字后的状态
    @Override
    public void afterTextChanged(Editable s) {

        editeCallBack.editeOver();
    }
   public  interface  EditeCallBack{
        void editeOver();
    }
}
