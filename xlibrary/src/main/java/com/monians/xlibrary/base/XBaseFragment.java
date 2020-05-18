package com.monians.xlibrary.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.monians.xlibrary.okhttp.OkHttpUtils;

/**
 * 功能:
 * 作者: ibore
 * 时间: 2016/5/25 15:59
 * 邮箱: bore521@live.com
 */
public abstract class XBaseFragment extends Fragment {

    protected FragmentActivity mActivity;
    protected Context mContext;
    protected View view;
    protected Bundle bundle;

    /**
     * 此方法可以得到上下文
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mActivity = getActivity()
//        mContext = getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 当Fragment销毁时，取消网络请求
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
