package com.iaowoo.mobile.Ui.classification.Activity.im;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Activity.TitleBarActivity;
import com.iaowoo.mobile.Ui.classification.Adapter.im.NewFriendAdapter;
import com.iaowoo.mobile.Ui.classification.Model.im.FriendshipListResponse;
import com.plp.underlying.networkframwork.OkhttpManager;
import com.plp.underlying.networkframwork.UtilsOkHttpAll;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewFriendListActivity extends TitleBarActivity {

    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.lv_ship)
    ListView lvShip;
    private List<FriendshipListResponse.BodyBean.ContentBean.ListBean> mList;
    private NewFriendAdapter adapter;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_new_friendlist);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        setTitle("新朋友");
    }

    private void initData() {
        mList = new ArrayList<>();
        adapter = new NewFriendAdapter(this, mList);
        lvShip.setAdapter(adapter);
        adapter.setOnItemButtonClick(new NewFriendAdapter.OnItemButtonClick() {
            @Override
            public boolean onButtonClick(int position, View view, int status) {
                index = position;
                switch (status) {
                    case 11: //收到了好友邀请
                        int friendId = mList.get(position).getId();
                        agreeAddToFriend(friendId);
                        break;
                    case 10: // 发出了好友邀请
                        break;
                    case 21: // 忽略好友邀请
                        break;
                    case 20: // 已是好友
                        break;
                    case 30: // 删除了好友关系
                        break;
                }
                return false;
            }
        });
        getFriendAll();
    }

    private void getFriendAll() {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("loginToken","");
        OkhttpManager.getInstance(this).requestPostByAsyn(false, UtilsOkHttpAll.IM_QUERY_FRIEND_LIST, "IM_QUERY_FRIEND_LIST", -1, paramsMap, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                FriendshipListResponse friendshipListResponse = JSON.parseObject(result.toString(), FriendshipListResponse.class);
                if (friendshipListResponse.getCode().equals(OkhttpManager.SUCESS)) {
                    FriendshipListResponse.BodyBean.ContentBean contentBean = friendshipListResponse.getBody().getContent();
                    if (contentBean != null) {
                        List<FriendshipListResponse.BodyBean.ContentBean.ListBean> list = contentBean.getList();

                        if (list != null && list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                FriendshipListResponse.BodyBean.ContentBean.ListBean re = list.get(i);
                                if (re.getStatus() == 10) {//是我发起的添加好友请求
                                    list.remove(re);
                                    i--;
                                }
                            }
                        }


                        if (list != null && list.size() > 0) {
                            lvShip.setVisibility(View.VISIBLE);
                            tvNoData.setVisibility(View.GONE);
                            mList.clear();
                            mList.addAll(list);
                            if (adapter != null)
                                adapter.notifyDataSetChanged();
                        } else {
                            lvShip.setVisibility(View.GONE);
                            tvNoData.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }

    private void agreeAddToFriend(int friendId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("loginToken", "");
        params.put("friendId", friendId);
        params.put("status", 20);

        OkhttpManager.getInstance(this).requestPostByAsyn(false, UtilsOkHttpAll.IM_FRIEND_OPT, "IM_FRIEND_OPT", -1, params, new OkhttpManager.ReCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {

            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }
}
