package com.plp.underlying.networkframwork;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * @author chenda
 * @ClassName:
 * @Description: ${描述：}(网络请求的层)
 * @date 2018/7/4
 */
public class Xutils_3 {
    public Xutils_3(){

    }
    public interface  Xutils3CallBack{
        void Ok(String json);
        void Faild(String error);
    }

    /**
     * xutils json请求
     * @param url
     * @param maps
     * @param xutils3CallBack
     */
    public void JsonTypeRequestAsyn(String url, Map<String, String> maps , final Xutils3CallBack xutils3CallBack){
        JSONObject js_request = new JSONObject();//服务器需要传参的json对象
        try {
            for (Map.Entry<String,String> entry : maps.entrySet()){
                js_request.put(entry.getKey(),entry.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(url);
        params.setAsJsonContent(true);
        params.setBodyContent(js_request.toString());

        x.http().post(params, new Callback.CommonCallback<String>() {//发起传参为json的post请求，
            // Callback.CacheCallback<String>的泛型为后台返回数据的类型，
            // 根据实际需求更改
            private boolean hasError = false;
            private String result = null;
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                xutils3CallBack.Faild("网络异常");
            }
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {
                if (!hasError && result != null && xutils3CallBack!=null) {
                    xutils3CallBack.Ok(result);
                }
            }
        });
    }
}
