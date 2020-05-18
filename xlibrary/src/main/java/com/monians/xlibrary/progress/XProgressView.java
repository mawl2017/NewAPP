package com.monians.xlibrary.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.monians.xlibrary.log.XLog;

/**
 * 功能:
 * 作者: ibore
 * 时间: 2016/5/31 11:04
 * 邮箱: bore521@live.com
 */
public class XProgressView extends View {

    //分段颜色
    private static final int[] SECTION_COLORS = {Color.GREEN, Color.YELLOW, Color.RED};

    private float maxCount;
    private float currentCount;
    private long taktTime;
    private float count = 0;
    private Paint mPaint;
    private Paint mTextPaint;
    private int mWidth, mHeight;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            XLog.i("count:" + count + "maxCount:" + maxCount + "taktTime:" + taktTime);
            if (count >= currentCount) {
                handler.removeCallbacks(runnable);
            } else {
                invalidate();
                handler.postDelayed(runnable, taktTime);
                count++;
            }
        }
    };


    public XProgressView(Context context) {
        this(context, null);
    }

    public XProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化参数
     *
     * @param context 上下文
     */
    private void init(Context context) {
        mPaint = new Paint();
        mTextPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        // 设置圆的大小
        RectF rectFBlackBg = new RectF(20, 20, mWidth - 20, mHeight - 20);
        // 画圆
        canvas.drawArc(rectFBlackBg, 90, 360, false, mPaint);
        // 设置中间字体颜色
        mPaint.setColor(Color.parseColor("#19b1ff"));
        canvas.drawText(count + "m³", mWidth / 2 + 20, mHeight / 2 + 20, mTextPaint);
        XLog.i(count + "----------------");

        float section = count / maxCount;

        canvas.drawArc(rectFBlackBg, 270, section * 360, false, mPaint);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 抗锯齿设置
        mPaint.setAntiAlias(true);
        // 设置线宽
        mPaint.setStrokeWidth((float) 14.0);
        // 设置样式
        mPaint.setStyle(Paint.Style.STROKE);
        // 画笔笔刷类型
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // 画笔颜色
        mPaint.setColor(Color.parseColor("#2019b1ff"));

        // 抗锯齿设置
        mTextPaint.setAntiAlias(true);
        // 设置线宽
        mTextPaint.setStrokeWidth((float) 3.0);
        // 设置文字对齐方式
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        // 设置文字大小
        mTextPaint.setTextSize(100);
        // 画笔颜色
        mTextPaint.setColor(Color.parseColor("#1bb731"));
    }

    public float getMaxCount() {
        return maxCount;
    }

    public float getCurrentCount() {
        return currentCount;
    }

    /***
     * 设置最大的进度值
     *
     * @param maxCount 最大的进度值
     */
    public void setMaxCount(float maxCount) {
        this.maxCount = maxCount;
        invalidate();
    }

    /***
     * 设置当前的进度值
     *
     * @param currentCount 当前进度值
     */
    public void setCurrentCount(float currentCount) {
        this.count = currentCount > maxCount ? maxCount : currentCount;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY
                || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 0;
        }
        if (heightSpecMode == MeasureSpec.AT_MOST
                || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = dipToPx(15);
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    public void start(float currentCount, float maxCount, long taktTime, long delayTime) {
        this.currentCount = currentCount;
        this.maxCount = maxCount;
        this.taktTime = taktTime;
        if (count != 0) {
            setCurrentCount(0);
            handler.postDelayed(runnable, 0);
        } else {
            handler.postDelayed(runnable, delayTime);
        }
    }
    /**
     * dip转px
     *
     * @param dip dip
     * @return px
     */
    private int dipToPx(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }
}
