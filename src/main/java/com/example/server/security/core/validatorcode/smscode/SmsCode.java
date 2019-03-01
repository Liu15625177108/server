package com.example.server.security.core.validatorcode.smscode;



import com.example.server.security.core.validatorcode.basecode.ValidatorCode;

import java.time.LocalDateTime;

/**
 * @ClassName SmsCode
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.core.validatorcode.smscode
 * @Date 2018/9/19 21:07
 */
public class SmsCode extends ValidatorCode {
    public SmsCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }

    public SmsCode(String code, int expireSecond) {
        super(code, expireSecond);
    }

    public SmsCode() {

    }
}
