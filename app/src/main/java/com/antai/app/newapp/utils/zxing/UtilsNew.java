package com.antai.app.newapp.utils.zxing;

import com.monians.xlibrary.log.XLog;

/**
 * Created by tecocity on 2019/4/2.
 */

public class UtilsNew {



    //    燃气表扫一扫方法
    public static String formatScanResult(String scanResult) {
        String result = null;
        if (scanResult.contains(",")) {

            String[] strings = scanResult.split(",");
            if (strings.length <= 1) {
                return null;
            } else {
                result = strings[2];
                return result;
            }

        } else if (scanResult.contains(".")) {
            String str2 = scanResult.replaceAll(" ", "");

            XLog.d("管理通扫码返回22==" + str2);
            //            XIUH2.5 186900007201820280411712111F0
            String s2 = str2.substring(7, 16);

            if (s2.length() <= 1) {
                return null;
            } else {
                result = s2;
                return result;
            }

        } else if (scanResult.contains(" ")) {

            String str2 = scanResult.replaceAll(" ", "");

            XLog.d("管理通扫码返回111==" + str2);
//            XIUH6 186900007201820280411712111F0
            String s2 = str2.substring(5, 14);

            if (s2.length() <= 1) {
                return null;
            } else {
                result = s2;
                return result;
            }
        }

        /*else if (!"".equals(scanResult)){
            result = scanResult;
        }*/


        return result;

    }
}
