package com.example.server.security.core.validatorcode.smscode;


import com.example.server.security.core.validatorcode.basecode.ValidatorCode;

/**
 * @ClassName SmsCodeSender
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.core.validatorcode.smscode
 * @Date 2018/9/19 21:36
 */
public interface SmsCodeSender {
    public void sendCode(String phone, ValidatorCode code);
}
