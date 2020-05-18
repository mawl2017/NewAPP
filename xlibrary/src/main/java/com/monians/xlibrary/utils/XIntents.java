package com.monians.xlibrary.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * 功能: Intent封装工具类
 * 作者: ibore
 * 时间: 2016/6/1 16:17
 * 邮箱: bore521@live.com
 */
public class XIntents {

    public XIntents() {
        throw new UnsupportedOperationException("XIntents不能被实例化");
    }

    public static Intent getIntent() {
        return new Intent();
    }
    /**
     * 默认的Activity启动方式
     * @param context 上下文
     * @param clazz 要启动Activity的class
     */
    public static void startActivity(Context context, Class clazz) {
        context.startActivity(new Intent(context, clazz));
    }

    /**
     * 默认的Activity启动方式
     * @param context 上下文
     * @param clazz 要启动Activity的class
     * @param name 标识
     * @param data 要传递的数据
     */
    public static void startActivity(Context context, Class clazz, String name, String data) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(name, data);
        context.startActivity(intent);
    }

    /**
     * 默认的Activity启动方式
     * @param context 上下文
     * @param clazz 要启动Activity的class
     * @param name 标识
     * @param data 要传递的数据
     */
    public static void startActivity(Context context, Class clazz, String name, int data) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(name, data);
        context.startActivity(intent);
    }

    /**
     * 默认的Activity启动方式
     * @param context 上下文
     * @param clazz 要启动Activity的class
     * @param name 标识
     * @param data 要传递的数据
     */
    public static void startActivity(Context context, Class clazz, String name, Character data) {
        Intent intent = new Intent(context, clazz);
        intent.putExtra(name, data);
        context.startActivity(intent);
    }

    /**
     * 默认的Activity启动方式
     * @param context
     * @param clazz
     * @param bundle
     */
    public static void startActivity(Context context, Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 隐式跳转，安全的
     * @param packageContext 上下文
     * @param clazz 要启动Activity的class
     * @param action 意图
     */
    public static void startActivity(Context packageContext, Class clazz, Intent action) {
        PackageManager packageManager = packageContext.getPackageManager();
        List activities = packageManager.queryIntentActivities(action, PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;

        // 开始一个活动如果是安全的
        if (isIntentSafe) {
            packageContext.startActivity(action);
        }
    }

    /**
     * 直接拨号，不需要跳转到拨号界面
     * @param context 上下文
     * @param number 电话号码
     */
    public static void startCall(Context context, String number) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel://" + number)));
    }

    /**
     * 跳转到拨号盘，不发起呼叫
     * @param context 上下文
     * @param number 电话号码
     */
    public static void startDial(Context context, String number) {
        context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel://" + number)));
    }


    public static final void startIntent(Context context, Object object) {
        context.startActivity(new Intent());
    }
}
