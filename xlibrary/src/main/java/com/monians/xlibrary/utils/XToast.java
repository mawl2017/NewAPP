package com.monians.xlibrary.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 功能:
 * 作者: ibore
 * 时间: 2016/6/3 13:46
 * 邮箱: bore521@live.com
 */
public class XToast {

    private static Toast mToast = null;
    public static boolean isShow = true;

    private XToast() {
        throw new UnsupportedOperationException("XToast不能被实例化");
    }

    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param message 要显示的消息
     */
    public static void showShort(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param message 要显示的消息
     */
    public static void showShort(Context context, int message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param message 要显示的消息
     */
    public static void showShort(Context context, String message) {
//        show(context, message, Toast.LENGTH_SHORT);
        show(context, message, Integer.valueOf(3));
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 要显示的消息
     */
    public static void showLong(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 要显示的消息
     */
    public static void showLong(Context context, int message) {
        show(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 要显示的消息
     */
    public static void showLong(Context context, String message) {
        show(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context 上下文
     * @param message 要显示的消息
     * @param duration 显示的时间
     */
    public static void show(Context context, CharSequence message, int duration) {
        show(context, String.valueOf(message), duration);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context 上下文
     * @param message 要显示的消息
     * @param duration 显示的时间
     */
    public static void show(Context context, int message, int duration) {
        show(context, String.valueOf(message), duration);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context 上下文
     * @param message 要显示的消息
     * @param duration 显示的时间
     */
    public static void show(Context context, String message, int duration) {
        if( null == context || TextUtils.isEmpty( message ) ){
            return;
        }

        if( null == mToast ){
            mToast = Toast.makeText( context, message, Integer.valueOf(3) );
        }else{
            mToast.setText( message );
        }
        mToast.show( );
    }

    /**
     * 取消Toast
     */
    public static void hideToast( ){
        mToast.cancel( );
    }
}
