package com.antai.app.newapp;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antai.app.newapp.utils.zxing.UtilsNew;
import com.monians.xlibrary.log.XLog;
import com.monians.xlibrary.okhttp.OkHttpUtils;
import com.monians.xlibrary.okhttp.callback.FastjsonCallback;
import com.monians.xlibrary.utils.XToast;

import java.io.FileNotFoundException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends Activity implements View.OnClickListener,EasyPermissions.PermissionCallbacks{

    private EditText et_serialNo;
    private ImageView iv_sao1;
    private EditText et_lou,et_danyuan,et_menpai;
    private ImageView iv_sao22;
    private EditText et_mozu,et_SIM;
    private TextView btn_submit;


    public static final int SAO_YI_SAO_ONE = 1; //扫一扫
    public static final int SAO_YI_SAO = 10; //扫一扫


    private ProgressBarDialog dialog_process;
    private static final int RC_CAMERA_PERM = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_serialNo=(EditText)findViewById(R.id.et_serialNo);
        iv_sao1=(ImageView)findViewById(R.id.iv_saoyisao);

        et_lou=(EditText)findViewById(R.id.et_louhao__edit_son);
        et_danyuan=(EditText)findViewById(R.id.et_danyua__edit_son);
        et_menpai=(EditText)findViewById(R.id.et_menpai__edit_son);

        iv_sao22=(ImageView)findViewById(R.id.iv_saoyisao2);
        et_mozu=(EditText)findViewById(R.id.et_mozu);
        et_SIM=(EditText)findViewById(R.id.et_SIM);

        btn_submit=(TextView) findViewById(R.id.btn_submit);

        iv_sao1.setOnClickListener(this);
        iv_sao22.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        dialog_process = new ProgressBarDialog(MainActivity.this);

        dialog_process.setMessage("加载中...");


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_saoyisao:
                //扫燃气表

                cameraTask1();
                break;
            case R.id.iv_saoyisao2:
                //扫 底盒
                cameraTask();
                break;
            case R.id.btn_submit:

                //提交数据
                if (TextUtils.isEmpty(et_serialNo.getText())) {
                    XToast.showShort(MainActivity.this, "燃气表号不能为空");
                    return;
                }
                if(et_serialNo.getText().toString().length()>9){
                    XToast.showShort(MainActivity.this, "燃气表号不能超过9位");
                    return;
                }
                if (TextUtils.isEmpty(et_mozu.getText())) {
                    XToast.showShort(MainActivity.this, "底盒模组号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(et_SIM.getText())) {
                    XToast.showShort(MainActivity.this, "底盒SIM不能为空");
                    return;
                }

                submit(et_serialNo.getText().toString(),et_mozu.getText().toString(),et_SIM.getText().toString());

                break;

        }

    }

    /**
     * t提交数据
     * @param serialNO
     * @param mozu
     * @param SIM
     */
    private void submit(String serialNO, String mozu, String SIM) {


//          http://39.107.254.93:8090/WCFForInstall/CGMSServiceNew.svc/QuickUserAndMeter?SerialNo=&BuildNo=&Unit=&DoorNo=&MCUModel=&Sim=

//            正式地址：  http://60.205.95.183:8090

             dialog_process.show();

            OkHttpUtils.get("http://60.205.95.183:8090/WCFForInstall/CGMSServiceNew.svc/QuickUserAndMeter")
                    .params("SerialNo", serialNO)
                    .params("BuildNo", et_lou.getText().toString())
                    .params("Unit", et_danyuan.getText().toString())
                    .params("DoorNo", et_menpai.getText().toString())
                    .params("MCUModel", mozu)
                    .params("Sim", SIM)
                    .execute(new FastjsonCallback<Bean>(Bean.class) {
                        @Override
                        public void onResponse(boolean isFromCache, Bean bean, Request request, @Nullable Response response) {
//                                    XToast.showShort(mContext,getGasBean.getRes_msg());
                            XLog.d("提交数据结果=" + bean.getRes_code());
                            switch (bean.getRes_code()) {
                                case 1:

                                    Log.e("TAG", "上传 成功");
                                    dialog_process.dismiss();
                                    XToast.showShort(MainActivity.this, "数据提交成功");
                                    et_serialNo.setText("");
                                    et_lou.setText("");
                                    et_danyuan.setText("");
                                    et_menpai.setText("");
                                    et_mozu.setText("");
                                    et_SIM.setText("");

                                    break;
                               case 0:
                                    Log.e("TAG", "上传失败 请重试");
                                    XToast.showShort(MainActivity.this, "数据库已存在该表，不能进行操作");
                                    dialog_process.dismiss();
                                    break;
                                case -1:
                                    Log.e("TAG", "上传失败 请重试");
                                    XToast.showShort(MainActivity.this, "数据提交错误");
                                    dialog_process.dismiss();
                                    break;
                            }
                        }

                        @Override
                        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                            Log.d("TAG", "上传失败 请重试 11");
                            XToast.showShort(MainActivity.this, "提交失败 请重试");
                            dialog_process.dismiss();

                        }
                    });

        }


    //扫一扫

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask1() {

        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            XLog.d("'进入扫码界面");
            //打开扫描界面扫描条形码或二维码
            Intent openCameraIntent = new Intent(MainActivity.this, CaptureActivity.class);
            startActivityForResult(openCameraIntent, SAO_YI_SAO_ONE);
        } else {
            XLog.d("'进入扫码界面   开启权限");

            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, SAO_YI_SAO_ONE);
            }

        }

    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {

        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            XLog.d("'进入扫码界面");
            //打开扫描界面扫描条形码或二维码
            Intent openCameraIntent = new Intent(MainActivity.this, CaptureActivity.class);
            startActivityForResult(openCameraIntent, SAO_YI_SAO);
        } else {
            XLog.d("'进入扫码界面   开启权限");

            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, SAO_YI_SAO);
            }

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        XLog.d("回调 - 图片 - 数据=="+resultCode+",,,"+requestCode);

        if(requestCode == SAO_YI_SAO_ONE){
            //扫码燃气表
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");

            try{

                String gasTable = UtilsNew.formatScanResult(scanResult);

                et_serialNo.setText(gasTable);

            }catch (Exception e){
                e.printStackTrace();
            }


        }else  if (requestCode == SAO_YI_SAO) {
            //扫码底盒
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");

            XLog.d("返回的燃气表号===" + scanResult);
            try {

                if (scanResult.contains(",")) {

                    String[] strings = scanResult.split(",");
                    if (strings.length == 2) {

                        et_mozu.setText(strings[0]);
                        et_SIM.setText(strings[1]);

                    } else {

                        XToast.showShort(getApplicationContext(), "扫码失败 请手动输入" + strings.length);

                    }

                } else {
                    XToast.showShort(getApplicationContext(), "扫码失败 请手动输入");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

          }
        }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        XLog.d("onPermissionsGranted:" + requestCode + ":" + perms.size());
        Intent openCameraIntent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        XLog.d("onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied permissions and checked NEVER ASK AGAIN.
        // This will display a dialog directing them to enable the permission in app settings.
        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,
                getString(R.string.rationale_ask_again),
                R.string.setting, R.string.cancel, perms);
    }

}
