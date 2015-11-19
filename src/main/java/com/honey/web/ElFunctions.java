package com.honey.web;

import java.util.Date;

/**
 * User: chenPeng
 * DateTime: 2015-06-18 18:38
 * Copyright © 2015/06/18 Shanghai Raxtone Software Co.,Ltd Allright Reserved
 */
public class ElFunctions {


    public static String formatSecond(Long second) {
        long timeHour = second / 3600;
        long timeMin = (second - timeHour * 3600) / 60;
        return (timeHour > 0 ? timeHour + "小时" : "") + (timeMin > 0 ? timeMin + "分钟" : "")+
                (timeHour  == 0 && timeMin == 0 ? "0分钟" : "");
    }

    /**
     * Date类型转long
     * @param second
     * @return
     */
    public static long formatToLong(Date date) {
        return date.getTime()/1000;
    }

}
