package com.example.server.security.core.validatorcode.basecode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName CodeGenerator
 * @Author:Jerry.Liu;
 * @Description:验证码的接口（主要有验证码的生成方法）
 * @Package com.example.conferencedemo.security.core.validatorcode.basecode
 * @Date 2018/9/19 21:04
 */
public interface CodeGenerator {
    ValidatorCode createCode(ServletWebRequest httpServletRequest);
}
