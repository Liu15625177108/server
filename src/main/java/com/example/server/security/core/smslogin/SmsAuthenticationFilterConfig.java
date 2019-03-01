package com.example.server.security.core.smslogin;

import com.example.server.security.core.handle.MyAuthenticationFaiurelHandle;
import com.example.server.security.core.handle.MyAuthenticationSuccessHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName SmsAuthenticationFilterConfig
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.security.core.smslogin
 * @Date 2019/2/28 15:55
 */
@Configuration
public class SmsAuthenticationFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private MyAuthenticationSuccessHandle myAnthenticationSuccessHandle;

    @Autowired
    private MyAuthenticationFaiurelHandle myAuthenticationFaiurelHandle;


    @Autowired
    private SmsUserDetailService smsUserDetailService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter=new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationFilter.setAuthenticationSuccessHandler(myAnthenticationSuccessHandle);
        smsAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFaiurelHandle);

        SmsAuthenticationProvider smsAuthenticationProvider=new SmsAuthenticationProvider();
        smsAuthenticationProvider.setSmsUserDetailService(smsUserDetailService);

        http.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(smsAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }
}