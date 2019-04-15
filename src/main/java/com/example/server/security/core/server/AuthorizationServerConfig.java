package com.example.server.security.core.server;

import com.example.server.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @ClassName AuthorizationServerConfig
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server
 * @Date 2019/2/21 22:52
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends  AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore jwtTokenStore;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("client")
//                .redirectUris("http://example.com")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("password", "refresh_token","authorization_code")
                .accessTokenValiditySeconds(3600)
                .scopes("app")
                .and()
                .withClient("tomomm")
                .redirectUris("http://example.com")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("refresh_token","authorization_code")
                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                .userDetailsService(myUserDetailsService)
                .authenticationManager(authenticationManager);
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");
    }
}
