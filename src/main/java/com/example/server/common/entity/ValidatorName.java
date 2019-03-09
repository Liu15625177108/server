package com.example.server.common.entity;

/**
 * @ClassName ValidatorName
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.common.entity
 * @Date 2019/3/8 13:45
 */
public class ValidatorName {
    private  static String IMAGE_KEY;
    private  static  String SMS_KEY;
//    private  static   String SESSION_KEY;

    public static String getImageKey() {
        return IMAGE_KEY;
    }

    public static void setImageKey(String imageKey) {
        IMAGE_KEY = imageKey;
    }

    public static String getSmsKey() {
        return SMS_KEY;
    }

    public static void setSmsKey(String smsKey) {
        SMS_KEY = smsKey;
    }
}
