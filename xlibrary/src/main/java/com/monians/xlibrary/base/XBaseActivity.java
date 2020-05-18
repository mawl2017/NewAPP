package com.monians.xlibrary.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.monians.xlibrary.manager.XAppManager;
import com.monians.xlibrary.manager.SystemBarTintManager;
import com.monians.xlibrary.okhttp.OkHttpUtils;

/**
 * 功能:
 * 作者: ibore
 * 时间: 2016/5/25 15:35
 * 邮箱: bore521@live.com
 */
public abstract class XBaseActivity extends AppCompatActivity{

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // 添加Activity到堆栈
        XAppManager.getAppManager().addActivity(this);
        // 修改状态栏颜色，4.4+生效
        setSystemBarTint();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁时取消网络请求
        OkHttpUtils.getInstance().cancelTag(this);
    }

    /**
     * 设置系统状态栏颜色
     */
    protected void setSystemBarTint() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus();
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(setStatusBarTintColor());
    }

    protected abstract int setStatusBarTintColor();

    @TargetApi(19)
    protected void setTranslucentStatus() {
        Window window = getWindow();
        // Translucent status bar
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // Translucent navigation bar
//        window.setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    /**
     * 设置半透明状态
     *
     * @param on 是否设置透明
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
