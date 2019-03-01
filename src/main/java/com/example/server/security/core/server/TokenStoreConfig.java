package com.example.server.security.core.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @ClassName TokenStoreConfig
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server
 * @Date 2019/2/22 11:35
 */
@Configuration
public class TokenStoreConfig {
    @Bean
    public TokenStore jwtTokenStore(){
        return  new JwtTokenStore(jwtAccessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter=new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("liuyuxin");
        return  accessTokenConverter;
    }

}
