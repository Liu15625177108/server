package com.example.server.security.core.properties.codeproperties;

/**
 * @ClassName CodeProperties
 * @Author:Jerry.Liu;
 * @Description:验证码配置类。
 * @Package com.example.conferencedemo.security.properties
 * @Date 2018/9/19 15:13
 */
public class ValidatorProperties {

    /**图片验证码的配置类*/
    private ImageCodeProperties imageCodeProperties=new ImageCodeProperties();

    /**短信验证码的配置类*/
    private SmsCodeProperties smsCodeProperties=new SmsCodeProperties();

    public ImageCodeProperties getImageCodeProperties() {
        return imageCodeProperties;
    }

    public void setImageCodeProperties(ImageCodeProperties imageCodeProperties) {
        this.imageCodeProperties = imageCodeProperties;
    }

    public SmsCodeProperties getSmsCodeProperties() {
        return smsCodeProperties;
    }

    public void setSmsCodeProperties(SmsCodeProperties smsCodeProperties) {
        this.smsCodeProperties = smsCodeProperties;
    }
}
