package com.example.server.security.core.validatorcode.imagecode.filter;


import com.example.server.security.core.handle.MyAuthenticationFaiurelHandle;
import com.example.server.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @ClassName ValidatorCodeFilterConfig
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.remote.security.core.properties.code.imagecode.filter
 * @Date 2018/9/13 19:42
 */
@Component
public class ValidatorCodeFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

//    @Autowired
//    private MyAnthenticationSuccessHandle myAnthenticationSuccessHandle;
    @Autowired
    private MyAuthenticationFaiurelHandle myAuthenticationFaiurelHandle;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ValidatorCodeFilter validatorCodeFilter = new ValidatorCodeFilter();
        validatorCodeFilter.setMyAuthenticationFaiurelHandle(myAuthenticationFaiurelHandle);
        validatorCodeFilter.setSecurityProperties(securityProperties);
        validatorCodeFilter.afterPropertiesSet();
        http.addFilterBefore(validatorCodeFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
