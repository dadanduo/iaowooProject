package com.iaowoo.mobile.im;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;

import org.xutils.ex.HttpException;

import java.util.List;

public class JsonManager {
    static {
        TypeUtils.compatibleWithJavaBean = true;
    }

    private static final String tag = JsonManager.class.getSimpleName();


    public static <T> T jsonToBean(String json, Class<T> cls) throws HttpException {
        return JSON.parseObject(json, cls);
    }


    public static <T> List<T> jsonToList(String json, Class<T> cls) throws HttpException {
        return JSON.parseArray(json, cls);
    }

    public static String beanToJson(Object obj) throws HttpException {
        String result = JSON.toJSONString(obj);
        Log.e(tag, "beanToJson: " + result);
        return result;
    }

}

