package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Application.ZApplication;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.View.EditText_Phone;
import com.iaowoo.mobile.Ui.classification.View.OnItemClick;
import com.iaowoo.mobile.Ui.classification.View.SearchTipsGroupView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

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
 * @Description: ${todo}(搜索第一个界面)
 * @date 2018/8/31
 * @email ${18011009889@163.com}
 */
public class SearchOneActivity extends  BaseBufferActivity implements OnItemClick{

    @BindView(R.id.et_search)
    EditText_Phone et_search;
    @BindView(R.id.search_history)
    SearchTipsGroupView search_history;
    @BindView(R.id.search_null)
    TextView search_null;
    Timer timer;
    List<String> historyItem=null;
    /**
     * 搜索的记录
     */
    private String[] items;

    @Override
    public int getLayoutResId() {
        return R.layout.search_one_activity;
    }

    @Override
    protected void initView() {
        super.initView();
        this.initState(R.id.ll_bar);
        showHistoryItem();
    }

    /**
     * 展示或者刷新历史item
     */
    public void showHistoryItem(){
        search_history.removeAllViews();
        historyItem=new ArrayList<>();
        historyItem= PrefManager.getInstance().getDataList(ZApplication.SEARCH_TAG);
        if(historyItem!=null) {
            if (historyItem.size() != 0) {
                search_null.setVisibility(View.GONE);
                items = new String[historyItem.size()];
                for (int i = 0; i < historyItem.size(); i++) {
                    items[historyItem.size() - i - 1] = historyItem.get(i);
                }
                search_history.initViews(items, SearchOneActivity.this);
            }
        }else{
            historyItem=new ArrayList<>();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        et_search.setFocusable(true);
        et_search.setFocusableInTouchMode(true);
        et_search.requestFocus();
        //延迟200毫秒展示系统键盘
        timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(et_search, 0);
                           }},
                200);
        //系统键盘搜索的点击
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    //搜索按键action
                    String content = et_search.getText().toString();
                    if (!TextUtils.isEmpty(content)){
                        //历史记录中没有
                        boolean haveNo=true;
                        for (int i = 0; i < historyItem.size(); i++) {
                            if (historyItem.get(i).equals(content)) {
                                //历史记录中已经存在
                                haveNo = false;
                            }
                        }
                        //不存在才保存
                        if(haveNo){
                            historyItem.add(content);
                            PrefManager.getInstance().setDataList(ZApplication.SEARCH_TAG,historyItem);
                        }
                        Intent intent=new Intent(SearchOneActivity.this,SearchTwoActivity.class);
                        intent.putExtra("keyword",content);
                        SearchOneActivity.this.startActivityForResult(intent,1);
                        overridePendingTransition(0, 0);

                        return true;
                    }
                    return true;
                }
                return false;
            }
        });


    }
    @OnClick({R.id.cancel_txt,R.id.search_one})
    public void click(View view){
        switch (view.getId()){
            case R.id.cancel_txt:
                finish();
                overridePendingTransition(0, 0);
                break;
            case R.id.search_one:
                //搜索按键action
                String content = et_search.getText().toString();
                if (!TextUtils.isEmpty(content)){
                    //历史记录中没有
                    boolean haveNo=true;
                    for (int i = 0; i < historyItem.size(); i++) {
                        if (historyItem.get(i).equals(content)) {
                            //历史记录中已经存在
                            haveNo = false;
                        }
                    }
                    //不存在才保存
                    if(haveNo){
                        historyItem.add(content);
                        PrefManager.getInstance().setDataList(ZApplication.SEARCH_TAG,historyItem);
                    }
                    Intent intent=new Intent(SearchOneActivity.this,SearchTwoActivity.class);
                    intent.putExtra("keyword",content);
                    SearchOneActivity.this.startActivityForResult(intent,1);
                    overridePendingTransition(0, 0);
                }
                break;

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onClick(int position) {
        Intent intent=new Intent(SearchOneActivity.this,SearchTwoActivity.class);
        intent.putExtra("keyword",items[position]);
        SearchOneActivity.this.startActivityForResult(intent,1);
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==2){
                showHistoryItem();
            }
        }
    }
}
