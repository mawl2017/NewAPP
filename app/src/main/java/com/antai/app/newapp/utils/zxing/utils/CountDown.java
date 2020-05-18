package com.antai.app.newapp.utils.zxing.utils;

import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/4/24.
 */

public class CountDown {
    private volatile int limitSec; //记录倒计时时间
    private int curSec;   //记录倒计时当下时间

    public CountDown(int limitSec) throws InterruptedException {
        this.limitSec = limitSec;
        this.curSec = limitSec;
        Log.e("TAG", "count down form" + limitSec);

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(new Task(), 0, 1, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(limitSec);   //暂停本线程
        exec.shutdownNow();
        Log.e("TAG", "Time out!");
    }

    private class Task implements Runnable {
        public void run() {
            Log.e("TAG", "Time remains" + --curSec + " s");
        }
    }
}
