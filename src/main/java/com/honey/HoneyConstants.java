package com.honey;

import java.util.Properties;

/**
 * @description
 * @author: chenPeng
 * @date: 2015/8/17 9:36
 * Copyright © 2015/8/17 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class HoneyConstants {
    private static final String FILE_NAME = "conf/honey-config.properties";
    private static Properties config;
    static {
        config = new Properties();
        try {
            config.load(HoneyConstants.class.getClassLoader().getResourceAsStream(FILE_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String WEB_DOMAIN;
    public static final String LOGIN_COOKIE_ID = "honey"; //登录cookie名
    public static final String LOGIN_COOKIE_PWD = "honey_ivv"; //登录cookie名
    public static final String LOGIN_SESSION = "honey"; //登录session名

    public static final String GRAVATAR_URL;

    static {
        WEB_DOMAIN = config.getProperty("domain");
        GRAVATAR_URL = config.getProperty("gravatar"); // 全球公认头像

    }


}
