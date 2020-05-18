package com.antai.app.newapp;

import android.app.Application;

import com.monians.xlibrary.okhttp.OkHttpUtils;

import org.xutils.x;

/**
 * Created by tecocity on 2019/6/5.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        try{

            x.Ext.init(this);

            //必须调用初始化
            OkHttpUtils.init(this);

            OkHttpUtils.getInstance()
                    .debug("OkHttpUtils")     //是否打开调试
                    .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)               //全局的连接超时时间
                    .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                  //全局的读取超时时间
                    .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS);                 //全局的写入超时时间

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
