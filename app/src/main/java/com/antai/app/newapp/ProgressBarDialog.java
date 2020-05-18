package com.antai.app.newapp;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.widget.TextView;


/**
 * Created by tecocity on 2018/1/18.
 */

public class ProgressBarDialog extends Dialog {
    private TextView tv_tishi;


    public ProgressBarDialog(@NonNull Context context) {
        super(context, R.style.HintDialog);
        initView();
    }

    private void initView() {
        setContentView(R.layout.progress_dialog);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        tv_tishi=(TextView) findViewById(R.id.tv_tishi);


    }

    public void setMessage(String str){
        if(str.equals("")||str==null){

            tv_tishi.setText("加载中..");
        }else {

            tv_tishi.setText(str);
        }
    }

    public void setTextSize(float max){
        tv_tishi.setTextSize(max);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(ProgressBarDialog.this.isShowing())
                    ProgressBarDialog.this.dismiss();
                break;
        }
        return true;
    }




}
