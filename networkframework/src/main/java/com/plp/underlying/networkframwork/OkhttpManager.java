package com.plp.underlying.networkframwork;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by chenda on 2018/4/16.
 */

public class OkhttpManager {
    /**
     *请求头
     */
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    private OkHttpClient mOkHttpClient;//okHttpClient 实例
    private Handler okHttpHandler;//全局处理子线程和M主线程通信
    private static OkhttpManager okhttpManager =null;
    public  final static String SUCESS="00000000";
    private Context context;
    /**
     * 单列模式
     *
     * @return 双重校验锁
     */
    public static OkhttpManager getInstance(Context context) {
        if (okhttpManager == null) {
            synchronized (OkhttpManager.class) {
                if (okhttpManager == null) {
                    okhttpManager = new OkhttpManager(context);
                }
            }
        }
        return okhttpManager;
    }


    /**
     * 初始化OkhttpRequestManager
     */
    public OkhttpManager(Context context) {
        this.context=context;
        //缓存文件夹
        File cacheFile = new File(context.getExternalCacheDir().toString(),"cache");
        //缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;

        Cache cache = new Cache(cacheFile,cacheSize);

        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .cache(cache)
                .build();
        //初始化Handler
        okHttpHandler = new Handler(context.getMainLooper());
    }
    /**
     * okHttp get同步请求
     * @param actionUrl  接口地址
     * @param paramsMap   请求参数
     */
    private void requestGetBySyn(String actionUrl, HashMap<String, String> paramsMap) {
        StringBuilder tempParams = new StringBuilder();
        try {
            //处理参数
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                //对参数进行URLEncoder
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //补全请求地址
            String requestUrl = String.format("%s/%s?%s", UtilsOkHttpAll.HTTP_BASE, actionUrl, tempParams.toString());
            //创建一个请求
            Request request = addHeaders().url(requestUrl).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            final Response response = call.execute();
            response.body().string();
        } catch (Exception e) {
            LogPrint.printError(e.toString());
        }
    }
    /**
     * okHttp post同步请求
     * @param actionUrl  接口地址
     * @param paramsMap   请求参数
     */
    private void requestPostBySyn(String actionUrl, HashMap<String, String> paramsMap) {
        try {
            //处理参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //补全请求地址
            String requestUrl = String.format("%s/%s", UtilsOkHttpAll.HTTP_BASE, actionUrl);
            //生成参数
            String params = tempParams.toString();
            //创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            //创建一个请求
            final Request request = addHeaders().url(requestUrl).post(body).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            Response response = call.execute();
            //请求执行成功
            if (response.isSuccessful()) {
                //获取返回数据 可以是String，bytes ,byteStream
                LogPrint.printError("response ----->" + response.body().string());
            }
        } catch (Exception e) {
            LogPrint.printError( e.toString());
        }
    }
    /**
     * okHttp post同步请求表单提交
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     */
    private void requestPostBySynWithForm(String actionUrl, HashMap<String, String> paramsMap) {
        try {
            //创建一个FormBody.Builder
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : paramsMap.keySet()) {
                //追加表单信息
                builder.add(key, paramsMap.get(key));
            }
            //生成表单实体对象
            RequestBody formBody = builder.build();
            //补全请求地址
            String requestUrl = String.format("%s/%s", UtilsOkHttpAll.HTTP_BASE, actionUrl);
            //创建一个请求
            final Request request = addHeaders().url(requestUrl).post(formBody).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            Response response = call.execute();
            if (response.isSuccessful()) {
                LogPrint.printError("response ----->" + response.body().string());
            }
        } catch (Exception e) {
            LogPrint.printError(e.toString());
        }
    }

    /**
     * okHttp get异步请求
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack 请求返回数据回调
     * @param <T> 数据泛型
     * @return
     */
    public <T> Call requestGetByAsyn(String actionUrl, HashMap<String, Object> paramsMap, final ReCallBack<T> callBack) {
        StringBuilder tempParams = new StringBuilder();
        try {
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key,  paramsMap.get(key), "utf-8"));
                pos++;
            }
            String requestUrl = String.format("%s/%s?%s", UtilsOkHttpAll.HTTP_BASE, actionUrl, tempParams.toString());
            final Request request = addHeaders().url(requestUrl).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack("访问失败",callBack);
                    LogPrint.printError( e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        LogPrint.printError("response ----->" + string);
                        successCallBack((T) string, callBack);
                    } else {
                        failedCallBack("请求错误",callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            LogPrint.printError( e.toString());
        }
        return null;
    }
    /**
     * okHttp post异步请求
     * @param cache     是否需要缓存
     * @param actionUrl 接口地址
     * @param pageNum   分页条数  ：第几页   -1  表示没有分页纯数据
     * @param paramsMap 请求参数
     * @param callBack 请求返回数据回调
     * @param <T> 数据泛型
     * @return
     */
    public <T> Call requestPostByAsyn(final boolean cache, String actionUrl, String tag,final int pageNum , HashMap<String, Object> paramsMap, final ReCallBack<T> callBack) {
        final  StringBuffer fileNameOver = new StringBuffer();
        fileNameOver.append(tag);
        for (String key : paramsMap.keySet()) {
            fileNameOver.append(paramsMap.get(key));
        }
        //开始读取数据
        final String responJson = load(fileNameOver + "");
        //读取缓存数据
        if (!TextUtils.isEmpty(responJson)) {
            LogPrint.printError("读取本地缓存数据：" + fileNameOver + responJson);
        }

        try {
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, paramsMap.get(key), "utf-8"));
                pos++;
            }
            String params = tempParams.toString();
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            String requestUrl = String.format("%s%s", UtilsOkHttpAll.HTTP_BASE, actionUrl);
            LogPrint.printError("请求url："+requestUrl);
            final Request request = addHeaders().url(requestUrl).post(body).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack("访问失败" + e.getLocalizedMessage(), callBack);
                    LogPrint.printError(e.toString());
                    successCallBack((T) responJson, callBack);
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        //分页数据
                        if (pageNum!= -1) {
                            //只需要针对第一页
                            if(pageNum==1){
                                //第一次进入
                                if(TextUtils.isEmpty(responJson)){
                                    //开始文件缓存到本地
                                    save(fileNameOver+"",string);
                                    //缓存为空表示第一次还没缓存开始走网络回调
                                    successCallBack((T) string,callBack);
                                }else{
                                    //需要更新数据
                                    if(!responJson.endsWith(string)){
                                        LogPrint.printError("需要更新数据");
                                        //开始文件缓存到本地
                                        save(fileNameOver+"",string);
                                        //缓存为空表示第一次还没缓存开始走网络回调
                                        successCallBack((T) string,callBack);
                                    }else{
                                        //开始文件缓存到本地
                                        save(fileNameOver+"",string);
                                        successCallBack((T) string,callBack);
                                    }
                                }

                            }else{
                                successCallBack((T) string,callBack);
                            }
                        }
                        //不是分页数据
                        else{
                            //第一次进入
                            if(TextUtils.isEmpty(responJson)){
                                //开始文件缓存到本地
                                save(fileNameOver+"",string);
                                //缓存为空表示第一次还没缓存开始走网络回调
                                successCallBack((T) string,callBack);
                            }else{
                                //需要更新数据
                                if(!responJson.endsWith(string)){
                                    LogPrint.printError("需要更新数据");
                                    //开始文件缓存到本地
                                    save(fileNameOver+"",string);
                                    //缓存为空表示第一次还没缓存开始走网络回调
                                    successCallBack((T) string,callBack);
                                }else{
                                    //开始文件缓存到本地
                                    save(fileNameOver+"",string);
                                    successCallBack((T) string,callBack);
                                }
                            }
                        }
                    } else {
                        failedCallBack("网络异常", callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            LogPrint.printError(e.toString());
        }
        return null;
    }

    /**
     * okHttp post异步请求表单提交
     * @param actionUrl 接口地址
     * @param fileName  为空 null " " 表示不缓存 否 缓存
     * @param pageNum   分页条数  ：第几页   -1  表示没有分页纯数据
     * @param paramsMap 请求参数
     * @param callBack 请求返回数据回调
     * @param <T> 数据泛型
     * @return
     */
    private <T> Call requestPostByAsynWithForm(String actionUrl, final String fileName, int pageNum, HashMap<String, Object> paramsMap, final ReCallBack<T> callBack) {
        final StringBuffer fileNameOver=new StringBuffer();
        fileNameOver.append(fileName);
        if(pageNum!=-1){
            fileNameOver.append(pageNum+"");
        }
        //开始读取数据
        final String responJson=load(fileNameOver+"");
        //读取缓存数据
        if(!TextUtils.isEmpty(responJson)){
            LogPrint.printError("读取本地缓存数据："+fileNameOver+responJson);
            successCallBack((T) responJson,callBack);
        }
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : paramsMap.keySet()) {
                builder.add(key, paramsMap.get(key) + "");
            }
            RequestBody formBody = builder.build();
            String requestUrl = String.format("%s/%s", UtilsOkHttpAll.HTTP_BASE, actionUrl);
            final Request request = addHeaders().url(requestUrl).post(formBody).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack("访问失败", callBack);
                    LogPrint.printError(e.toString());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        //缓存为空表示第一次还没缓存开始走网络回调
                        if(TextUtils.isEmpty(responJson)){
                            LogPrint.printError("读取网络");
                            successCallBack((T) string,callBack);
                        }
                        //不为空表示缓存
                        if(!TextUtils.isEmpty(fileName))
                            //开始文件缓存到本地
                            save(fileNameOver+"",string);
                        else
                            //为空表示不需要缓存直接回调数据
                            successCallBack((T) string,callBack);
                    } else {
                        failedCallBack("网络异常", callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            LogPrint.printError(e.toString());
        }
        return null;
    }
    /**
     * 统一为请求添加头信息
     * @return
     */
    private Request.Builder addHeaders() {
        Request.Builder builder = new Request.Builder()
                .addHeader("Connection", "keep-alive")
                .addHeader("platform", "2")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE)
                .addHeader("appVersion", "3.2.0");
        return builder;
    }
    /**
     * 统一同意处理成功信息
     * @param result
     * @param callBack
     * @param <T>
     */
    private <T> void successCallBack(final T result, final ReCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onReqSuccess(result);
                }
            }
        });
    }
    /**
     * 统一处理失败信息
     * @param errorMsg
     * @param callBack
     * @param <T>
     */
    private <T> void failedCallBack(final String errorMsg, final ReCallBack<T> callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onReqFailed(errorMsg);
                }
            }
        });
    }

    /**
     * 文件存储之存数据
     */
    public void save(String fileName,String json) {
        LogPrint.printError("key:"+fileName+"开始缓存更新"+json);
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //设置文件名称，以及存储方式
            out =context.openFileOutput(fileName, Context.MODE_PRIVATE);
            //创建一个OutputStreamWriter对象，传入BufferedWriter的构造器中
            writer = new BufferedWriter(new OutputStreamWriter(out));
            //向文件中写入数据
            writer.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer!=null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件存储之取数据
     * @param fileName
     * @return
     */
    public String load(String fileName) {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            //设置将要打开的存储文件名称
            in =context.openFileInput(fileName);
            //FileInputStream -> InputStreamReader ->BufferedReader
            reader = new BufferedReader(new InputStreamReader(in));
            String line = new String();
            //读取每一行数据，并追加到StringBuilder对象中，直到结束
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    public  interface ReCallBack<T> {
        /**
         * 响应成功
         */
        void onReqSuccess(T result);

        /**
         * 响应失败
         */
        void onReqFailed(String errorMsg);
    }
}
