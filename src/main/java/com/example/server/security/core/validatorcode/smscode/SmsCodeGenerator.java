package com.example.server.security.core.validatorcode.smscode;


import com.example.server.security.core.properties.SecurityProperties;
import com.example.server.security.core.validatorcode.basecode.CodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName SmsCodeGenerator
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.core.validatorcode.smscode
 * @Date 2018/9/19 21:34
 */
public class SmsCodeGenerator implements CodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public SmsCode createCode(ServletWebRequest httpServletRequest) {
        int length=ServletRequestUtils.getIntParameter(httpServletRequest.getRequest(),"length",securityProperties.getValidatorProperties().getSmsCodeProperties().getLength());
        return  new SmsCode(RandomStringUtils.randomNumeric(length),securityProperties.getValidatorProperties().getSmsCodeProperties().getExpiretime());
    }
}

