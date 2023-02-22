package com.example.chapter04.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateUtil {
    public static String getNowTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
}
