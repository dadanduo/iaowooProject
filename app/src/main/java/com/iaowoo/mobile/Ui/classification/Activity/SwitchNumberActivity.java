package com.iaowoo.mobile.Ui.classification.Activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.LogPrint;
import com.iaowoo.mobile.Controller.Single.PrefManager;
import com.iaowoo.mobile.Controller.Single.SingleOverAll;
import com.iaowoo.mobile.Controller.Single.XutilsDBManage;
import com.iaowoo.mobile.DB.LoginInfo;
import com.iaowoo.mobile.EvenBus.EventBusMessageRefresh;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Adapter.OnclicKRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.Adapter.SwtichLoginRecycleAdapter;
import com.iaowoo.mobile.Ui.classification.BroadcastReceiverClass.RdioBroadCast;
import com.iaowoo.mobile.Utils.LogPrint;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

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
 * @Description: ${todo}(切换账号   v.2.0.6)
 * @date 2018/11/19
 * @email ${18011009889@163.com}
 */
public class SwitchNumberActivity  extends  BaseBufferActivity implements OnclicKRecycleAdapter,SwtichLoginRecycleAdapter.Show{
    @BindView(R.id.recycler)
    RecyclerView recycler;
    List<LoginInfo> loginInfos;
    SwtichLoginRecycleAdapter swtichLoginRecycleAdapter;
    @Override
    public int getLayoutResId() {
        return R.layout.switch_number_layout;
    }

    @Override
    protected void initView() {
        super.initView();
        loginInfos=getLogin();
        recycler.setHasFixedSize(true);//设置固定大小
        recycler.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //为recyclerView设置布局管理器
        recycler.setLayoutManager(mLayoutManager);
        swtichLoginRecycleAdapter=new SwtichLoginRecycleAdapter(this, this,this);
        swtichLoginRecycleAdapter.setData(loginInfos);
        recycler.setAdapter(swtichLoginRecycleAdapter);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    /**
     * 返回   登录页面
     * @param view
     */
    @OnClick({R.id.back_re_switch,R.id.switch_number_btn})
    public void click(View view){
        switch (view.getId()){
            case R.id.back_re_switch:
                finish();
                break;
            case  R.id.switch_number_btn:
                Intent mintent=new Intent(this,LoginActivity.class);
                mintent.putExtra("tags",1);
                startActivityForResult(mintent,1);
                break;
            default:
                break;
        }
    }

    /**
     * 获取数据
     * @return
     */
    public List<LoginInfo> getLogin(){
        List<LoginInfo> loginInfos=XutilsDBManage.getInstance().loadTableAll(LoginInfo.class);
        List<LoginInfo> loginInfos1=new ArrayList<>();
        if(loginInfos!=null) {
            if(loginInfos.size()>0){
                LogPrint.printError("当前账号："+PrefManager.getInstance().getMobile());
                for (int i = 0; i < loginInfos.size(); i++) {
                    LogPrint.printError("账号数据：" + loginInfos.get(i).getGroudId() + "头像" + loginInfos.get(i).getHeadImage() + "账号：" + loginInfos.get(i).getName() + "密码：" + loginInfos.get(i).getPassWord());
                    if (loginInfos.get(i).getGroudId()==PrefManager.getInstance().getGroudId()) {
                        loginInfos1.add(loginInfos.get(i));
                    }
                }
            }
        }
        return loginInfos1;
    }

    /**
     * 切换账号
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        //通知页面刷新
        Intent intent = new Intent();
        intent.putExtra(RdioBroadCast.SHOWTAG,"refresh");
        intent.putExtra(RdioBroadCast.DATA,"");
        intent.setAction(RdioBroadCast.BOARD);
        sendBroadcast(intent);
        //app改版后刷新小红点和新人红包
        EventBus.getDefault().post(new EventBusMessageRefresh(0));
        EventBus.getDefault().post(new EventBusMessageRefresh(1));
        LogPrint.printError("添加消息表");
        SingleOverAll.getInstance().addMessageTabble();
        setResult(3);
        finish();
    }

    /**
     * 删除账号
     * @param position
     * @return
     */
    @Override
    public boolean onItemLongClick(int position) {
        loginInfos=getLogin();
        swtichLoginRecycleAdapter.setData(loginInfos);
        swtichLoginRecycleAdapter.notifyDataSetChanged();
        if(XutilsDBManage.getInstance().searchByGroudID(PrefManager.getInstance().getGroudId(),LoginInfo.class)){
        }
        return false;
    }

    /**
     * 换新账号刷新列表
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==3){
                loginInfos=getLogin();
                swtichLoginRecycleAdapter.setData(loginInfos);
                swtichLoginRecycleAdapter.notifyDataSetChanged();
                //通知页面刷新
                Intent intent = new Intent();
                intent.putExtra(RdioBroadCast.SHOWTAG,"refresh");
                intent.putExtra(RdioBroadCast.DATA,"");
                intent.setAction(RdioBroadCast.BOARD);
                sendBroadcast(intent);
                //app改版后刷新小红点和新人红包
                EventBus.getDefault().post(new EventBusMessageRefresh(0));
                EventBus.getDefault().post(new EventBusMessageRefresh(1));
                LogPrint.printError("添加消息表");
                SingleOverAll.getInstance().addMessageTabble();
                setResult(3);
                finish();
            }
        }
    }

    /**
     * 删除登录中的账号
     * @param name
     */
    @Override
    public void Name(String name) {
        if(name.equals(PrefManager.getInstance().getMobile())) {
            setResult(2);
            finish();
        }
    }
}
