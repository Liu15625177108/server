package com.example.server.security.core.validatorcode.smscode;


import com.example.server.security.core.validatorcode.basecode.ValidatorCode;

/**
 * @ClassName MydefaultSmsSender
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.core.validatorcode.smscode
 * @Date 2018/9/19 21:37
 */
public class MydefaultSmsSender implements SmsCodeSender {
    @Override
    public void sendCode(String phone, ValidatorCode code) {
        System.out.println("手机号码为："+phone+",验证码为："+code.getCode());
    }
}
