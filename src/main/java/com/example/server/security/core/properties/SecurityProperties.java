package com.example.server.security.core.properties;

import com.example.server.security.core.properties.browserproperties.BrowserProperties;
import com.example.server.security.core.properties.codeproperties.ValidatorProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName SecurityProperties
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.security.core.properties
 * @Date 2019/2/28 21:01
 */
@ConfigurationProperties(prefix = "mysecurity")
public class SecurityProperties {
    /** 登陆页面以及跳转页面配置类*/
    private BrowserProperties browser=new BrowserProperties();

    /** 验证码的配置类*/
    private ValidatorProperties validatorProperties =new ValidatorProperties();

//    private SocialProperties socialProperties=new SocialProperties();

    /**getter(),setter()方法*/
    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }


    public ValidatorProperties getValidatorProperties() {
        return validatorProperties;
    }

    public void setValidatorProperties(ValidatorProperties validatorProperties) {
        this.validatorProperties = validatorProperties;
    }
//
//    public SocialProperties getSocialProperties() {
//        return socialProperties;
//    }
//
//    public void setSocialProperties(SocialProperties socialProperties) {
//        this.socialProperties = socialProperties;
//    }
}