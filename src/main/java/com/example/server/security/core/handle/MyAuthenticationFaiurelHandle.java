package com.example.server.security.core.handle;

import com.example.server.common.logger.MyLogger;
import com.example.server.security.core.properties.browserproperties.LoginType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName MyAuthenticationFaiurelHandle
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.security.core.handle
 * @Date 2019/2/28 15:58
 */
@Component("MyAuthenticationFailureHandle")
public class MyAuthenticationFaiurelHandle extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MyLogger myLogger;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        myLogger.getLogger().info("登陆失败");

            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString((Object)e.getMessage()));

    }
}