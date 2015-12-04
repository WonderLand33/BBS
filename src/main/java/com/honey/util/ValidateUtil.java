package com.honey.util;

import java.io.UnsupportedEncodingException;

/**
 * @description 提供数据验证
 * @author: chenPeng
 * @date: 2015/9/18 14:21
 * Copyright © 2015/9/18 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class ValidateUtil {


    public static int strLength(String str) {
        try {
            return (new String(str.getBytes("GBK"), "ISO8859_1")).length();
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException(var2);
        }
    }

    /**
     * 邮箱验证
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return email.matches("\\b(^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-])+(\\.[a-zA-Z0-9-]+)*((\\.[a-zA-Z0-9]{2,})|(\\.[a-zA-Z0-9]{2,}\\.[a-zA-Z0-9]{2,}))$)\\b");
    }

    /**
     * 密码验证
     * @param str
     * @return
     */
    public static boolean checkPwd(String str) {
        return str.matches("^[a-zA-Z0-9]{6,16}$");
    }

    /**
     * 用户名 4-12位
     * @param str
     * @return
     */
    public static boolean checkUserName(String str) {
        return strLength(str) >= 4 && strLength(str) <= 12/*?str.matches("^[a-zA-Z0-9\\u4e00-\\u9fa5]{4,12}$"):false*/;
    }



}
