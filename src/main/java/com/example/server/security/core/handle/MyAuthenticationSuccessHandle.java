package com.example.server.security.core.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Base64;

/**
 * @ClassName MyAnthenticationSuccessHandle
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.core.handle
 * @Date 2018/9/19 15:15
 */
@Component
public class MyAuthenticationSuccessHandle extends SavedRequestAwareAuthenticationSuccessHandler {

    /**注入objectMapper,用于类型转化*/
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private PasswordEncoder passwordEncoder;



    /**
     *@Author Jerry.Liu
     *@Description://TODO
     *@Parameter
     *@Date:21:28 2018/9/7
     *@Package: com.example.remote.security.core.handle
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String header =httpServletRequest.getHeader("Authorization");
        if (header != null && header.toLowerCase().startsWith("basic ")) {

            String[] tokens = this.extractAndDecodeHeader(header, httpServletRequest);
            assert tokens.length == 2;
            String clientId = tokens[0];
            String clientSecret= tokens[1];

            ClientDetails clientDetails =clientDetailsService.loadClientByClientId(clientId);
            if (clientDetails==null){
                throw  new UnapprovedClientAuthenticationException("ClientId 对应的配置信息不存在"+clientId);
            }
//            if(!clientDetails.getClientSecret().equals(clientSecret)){
            if(!passwordEncoder.matches(clientSecret,clientDetails.getClientSecret())){
                throw  new UnapprovedClientAuthenticationException("ClientSecret 不匹配");
            }
            TokenRequest tokenRequest =new TokenRequest(null,clientId,clientDetails.getScope(),"custom");

            OAuth2Request oAuth2Request =tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication=new OAuth2Authentication(oAuth2Request,authentication);
            OAuth2AccessToken oAuth2AccessToken=authorizationServerTokenServices.createAccessToken(oAuth2Authentication);


            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(oAuth2AccessToken));
        }else {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }



    }
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");

        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException var7) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }
}
