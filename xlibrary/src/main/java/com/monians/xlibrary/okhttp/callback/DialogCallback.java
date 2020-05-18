package com.monians.xlibrary.okhttp.callback;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.Window;

import com.monians.xlibrary.okhttp.request.BaseRequest;

import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 功能:
 * 作者: ibore
 * 时间: 2016/6/20 15:28
 * 邮箱: bore521@live.com
 */
public abstract class DialogCallback<T> extends FastjsonCallback<T> {

    private ProgressDialog dialog;

    private void initDialog(Context context, String message) {
        dialog = new ProgressDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(message);
    }

    public DialogCallback(Context context, Class<T> clazz, String message) {
        super(clazz);
        initDialog(context, message);
    }

    public DialogCallback(Context context, Type type, String message) {
        super(type);
        initDialog(context, message);
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onAfter(boolean isFromCache, @Nullable T t, Call call, @Nullable Response response, @Nullable Exception e) {
        super.onAfter(isFromCache, t, call, response, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
