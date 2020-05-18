package com.monians.xlibrary.okhttp.callback;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.monians.xlibrary.utils.XToast;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 功能:
 * 作者: ibore
 * 时间: 2016/6/20 15:28
 * 邮箱: bore521@live.com
 */
public abstract class FastjsonCallback<T> extends CommonCallback<T> {
    private Class<T> clazz;
    private Type type;
    private ArrayList arrayList;

    public FastjsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

//    public FastjsonCallback(ArrayList<Object> arrayList) {
//        this.arrayList = arrayList;
//    }

    public FastjsonCallback(Type type) {
        this.type = type;
    }

    //该方法是子线程处理，不能做ui相关的工作
    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        try {
            String responseData = response.body().string();
            if (TextUtils.isEmpty(responseData)) return null;
            if (clazz == String.class) return (T) responseData;
            if (clazz != null) return JSON.parseObject(responseData, clazz);
            if (type != null) return JSON.parseObject(responseData, type);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("OkHttpUtils", "网络IO流读取错误");
        }
        return null;
    }

}
