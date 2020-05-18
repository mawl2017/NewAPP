package com.monians.xlibrary.utils;

import java.util.regex.Pattern;

/**
 * 功能: 校验类
 * 作者: ibore
 * 时间: 2016/6/20 16:46
 * 邮箱: bore521@live.com
 */
public class XValidator {
    /**
     * 校验手机号
     * @param phoneNumber 需要校验的手机号
     * @return 是否是手机号
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        String telRegex = "(^(13\\d|14[57]|15[^4,\\D]|17|18\\d)\\d{8}|170\\d{8})$";
        return Pattern.matches(telRegex, phoneNumber);
    }
}
