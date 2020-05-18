package com.monians.xlibrary.okhttp.callback;

import android.support.annotation.Nullable;

import com.monians.xlibrary.okhttp.request.BaseRequest;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 功能: 该类主要用于在所有请求之前添加公共的情iqutouhuo参数，例如登陆授权的token，使用设备信息等
 * 作者: ibore
 * 时间: 2016/6/20 15:29
 * 邮箱: bore521@live.com
 */
public abstract class CommonCallback<T> extends AbsCallback<T> {
    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        // 如果账号已经登陆，就添加token等等
        request.params("appkey", "35NQ07S4SJLU59M1HMXRPI7FJDQITBJJ")
                .params("signa", "4E6B01FD7AB3D0E86C96247E8AFD27F4");

    }
}
