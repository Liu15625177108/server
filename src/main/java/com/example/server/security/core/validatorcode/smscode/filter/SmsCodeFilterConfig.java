package com.example.server.security.core.validatorcode.smscode.filter;



import com.example.server.common.redis.RedisService;
import com.example.server.security.core.handle.MyAuthenticationFaiurelHandle;
import com.example.server.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @ClassName SmsCodeFilterConfig
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.remote.security.core.properties.code.smscode.filter
 * @Date 2018/9/15 15:38
 */
@Component
public class SmsCodeFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {
    @Autowired
    private MyAuthenticationFaiurelHandle myAuthenticationFaiurelHandle;

    @Autowired
    private SecurityProperties securityProperties;
//
    @Autowired
    private RedisService redisService;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeFilter smsCodeFilter =new SmsCodeFilter();
        smsCodeFilter.setMyAuthenticationFaiurelHandle(myAuthenticationFaiurelHandle);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.setRedisService(redisService);
        smsCodeFilter.afterPropertiesSet();
        http.addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
