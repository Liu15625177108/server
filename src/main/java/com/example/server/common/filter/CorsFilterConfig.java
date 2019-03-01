package com.example.server.common.filter;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @ClassName CorsFilterConfig
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.common.filter
 * @Date 2019/2/24 10:48
 */
@Component
public class CorsFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        CorsFilter corsFilter=new CorsFilter();
        http.addFilterBefore(corsFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
