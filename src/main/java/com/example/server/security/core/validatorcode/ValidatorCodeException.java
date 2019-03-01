package com.example.server.security.core.validatorcode;


import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName ValidatorCodeException
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.remote.security.core.properties.code.imagecode
 * @Date 2018/9/9 15:57
 */
public class ValidatorCodeException extends AuthenticationException {
    public ValidatorCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidatorCodeException(String msg) {
        super(msg);
    }
}
