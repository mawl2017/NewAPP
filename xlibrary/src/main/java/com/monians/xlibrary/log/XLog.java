package com.monians.xlibrary.log;

import java.io.File;

/**
 * 功能: 自定义的日志打印工具，用来替代{@link android.util.Log}
 * 作者: ibore
 * 时间: 2016/6/05 12:01
 * 邮箱: bore521@live.com
 */
public final class XLog {



    public static String TAG;
    public static boolean IS_SHOW_LOG = true;

    private XLog() {
        throw new RuntimeException("不能被实例化");
    }

    /**
     * 初始化（在Application中）
     * @param isShowLog 是否显示Log
     */
    public static void init(boolean isShowLog) {
        init(isShowLog, null);
    }

    /**
     * 初始化（在Application中）
     * @param isShowLog 是否显示Log
     * @param tag TAG（标记）
     */
    public static void init(boolean isShowLog, String tag) {
        IS_SHOW_LOG = isShowLog;
        if (tag != null) TAG = tag;
        else TAG = LogHelper.DEFAULT_TAG;
    }

    public static void v() {
        LogDefault.printLog(LogHelper.V, null, LogHelper.DEFAULT_MESSAGE);
    }

    public static void v(Object msg) {
        LogDefault.printLog(LogHelper.V, null, msg);
    }

    public static void v(String tag, Object... objects) {
        LogDefault.printLog(LogHelper.V, tag, objects);
    }

    public static void d() {
        LogDefault.printLog(LogHelper.D, null, LogHelper.DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        LogDefault.printLog(LogHelper.D, null, msg);
    }

    public static void d(String tag, Object... objects) {
        LogDefault.printLog(LogHelper.D, tag, objects);
    }

    public static void i() {
        LogDefault.printLog(LogHelper.I, null, LogHelper.DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        LogDefault.printLog(LogHelper.I, null, msg);
    }

    public static void i(String tag, Object... objects) {
        LogDefault.printLog(LogHelper.I, tag, objects);
    }

    public static void w() {
        LogDefault.printLog(LogHelper.W, null, LogHelper.DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        LogDefault.printLog(LogHelper.W, null, msg);
    }

    public static void w(String tag, Object... objects) {
        LogDefault.printLog(LogHelper.W, tag, objects);
    }

    public static void e() {
        LogDefault.printLog(LogHelper.E, null, LogHelper.DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        LogDefault.printLog(LogHelper.E, null, msg);
    }

    public static void e(String tag, Object... objects) {
        LogDefault.printLog(LogHelper.E, tag, objects);
    }

    public static void a() {
        LogDefault.printLog(LogHelper.A, null, LogHelper.DEFAULT_MESSAGE);
    }

    public static void a(Object msg) {
        LogDefault.printLog(LogHelper.A, null, msg);
    }

    public static void a(String tag, Object... objects) {
        LogDefault.printLog(LogHelper.A, tag, objects);
    }

    public static void json(String jsonFormat) {
        LogJson.printJson(null, jsonFormat);
    }

    public static void json(String tag, String jsonFormat) {
        LogJson.printJson(tag, jsonFormat);
    }

    public static void xml(String xml) {
        LogXml.printXml(null, xml);
    }

    public static void xml(String tag, String xml) {
        LogXml.printXml(tag, xml);
    }

    public static void file(File targetDirectory, Object msg) {
        LogFile.printFile(null, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, Object msg) {
        LogFile.printFile(tag, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, String fileName, Object msg) {
        LogFile.printFile(tag, targetDirectory, fileName, msg);
    }
}
