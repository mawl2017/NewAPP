package com.monians.xlibrary.recycler;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 功能: 重写NestedScrollView，防止5.0以上机器 滑动冲突问题，实际上是NestedScrollView禁止滑动
 * 作者: ibore
 * 时间: 2016/5/19 15:13
 * 邮箱: bore521@live.com
 */
public class XNestedScrollView extends NestedScrollView {

    private int downX;
    private int downY;
    private int moveX;
    private int moveY;

    private int mScaledTouchSlop;

    public XNestedScrollView(Context context) {
        super(context);
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public XNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public XNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getRawX();
                downY = (int) ev.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = (int) ev.getRawX();
                moveY = (int) ev.getRawY();
                if (Math.abs(moveX - downX) > mScaledTouchSlop) {
                    return true;
                }
                if (Math.abs(moveY - downY) > mScaledTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}