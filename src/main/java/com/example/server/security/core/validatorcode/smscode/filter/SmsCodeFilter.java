package com.example.server.security.core.validatorcode.smscode.filter;


import com.example.server.common.entity.ValidatorName;
import com.example.server.common.redis.RedisService;
import com.example.server.security.core.handle.MyAuthenticationFaiurelHandle;
import com.example.server.security.core.handle.MyAuthenticationSuccessHandle;
import com.example.server.security.core.properties.SecurityProperties;
import com.example.server.security.core.validatorcode.ValidatorCodeController;
import com.example.server.security.core.validatorcode.ValidatorCodeException;

import com.example.server.security.core.validatorcode.smscode.SmsCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
//import org.springframework.social.connect.web.HttpSessionSessionStrategy;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName SmsCodeFilter
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.remote.security.core.properties.code.smscode.filter
 * @Date 2018/9/15 15:31
 */
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 导入处理匹配失败的方法处理类
     */
    private MyAuthenticationFaiurelHandle myAuthenticationFaiurelHandle;

    private MyAuthenticationSuccessHandle myAuthenticationSuccessHandle;

    /**
     * 导入处理session的类
     */
//    private HttpSessionSessionStrategy httpSessionSessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 导入配置类
     */
    private SecurityProperties securityProperties;

    /**
     * 导入需要验证码验证的url
     */
    private Set<String> urls = new HashSet<String>();


    /**
     * 用于path匹配的类
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private RedisService redisService;

    /**
     * @Author Jerry.Liu
     * @Description：配置需要验证的url;
     * @Parameter
     * @Date:19:54 2018/9/13
     * @Package: com.example.remote.security.core.properties.code.imagecode.filter
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        if (securityProperties.getValidatorProperties().getSmsCodeProperties().getUrl() != null) {
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidatorProperties().getImageCodeProperties().getUrl(), ",");
            for (String url : configUrls) {
                urls.add(url);
            }
        }
        urls.add("/authentication/smsform");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

//            if (StringUtils.equals("/authentication/form",httpServletRequest.getRequestURI())
//                    &&StringUtils.endsWithIgnoreCase(httpServletRequest.getMethod(),"post")){

        /**判断值，如果为true 则url需要验证码验证*/

        boolean action = false;
        for (String url : urls) {
            if (antPathMatcher.match(url, httpServletRequest.getRequestURI())) {
                action = true;
                break;
            }
        }
        if (action) {
            try {
//                    System.out.println("进入filter");
                validateSmsCode(new ServletWebRequest(httpServletRequest));
            } catch (ValidatorCodeException e) {
                myAuthenticationFaiurelHandle.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    /**
     * @Author Jerry.Liu
     * @Description://图形验证码的验证方法。
     * @Parameter [servletRequest]
     * @Date:21:12 2018/9/9
     * @Package: com.example.remote.security.core.properties.code.imagecode
     */
    private void validateSmsCode(ServletWebRequest servletRequest) throws ServletRequestBindingException {

        /**从session中拿到生成的验证码信息*/
//       SmsCode smsCode = (SmsCode) httpSessionSessionStrategy.getAttribute(servletRequest,
//                ValidatorCodeController.getSessionSmsKey());
        SmsCode smsCode =(SmsCode)redisService.getValue(ValidatorName.getSmsKey());
//        String smsCode=(String)redisService.getValue("123456");
        /** 从request中获取登陆提交表单的验证码信息*/
        String codeInRequest = ServletRequestUtils.getStringParameter(servletRequest.getRequest(), "smscode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidatorCodeException("输入验证码信息为空");
        }
        if (smsCode == null) {
            throw new ValidatorCodeException("验证码信息不存在");
        }
        if (smsCode.getExpireTime().isBefore(LocalDateTime.now())) {
//            httpSessionSessionStrategy.removeAttribute(servletRequest, ValidatorCodeController.getSessionImageKey());
//            redisService.delete("code");
            redisService.delete(ValidatorName.getSmsKey());
            throw new ValidatorCodeException("验证码信息已经过期");
        }
        if (!StringUtils.equals(smsCode.getCode(), codeInRequest)) {
            throw new ValidatorCodeException("验证码不匹配");
        }
//        if(!smsCode.getCode().equals(codeInRequest)){
//            throw new ValidatorCodeException("验证码不匹配");
//        }
//        httpSessionSessionStrategy.removeAttribute(servletRequest, ValidatorCodeController.getSessionImageKey());
        redisService.delete(ValidatorName.getSmsKey());

    }

    public MyAuthenticationFaiurelHandle getMyAuthenticationFaiurelHandle() {
        return myAuthenticationFaiurelHandle;
    }

    public void setMyAuthenticationFaiurelHandle(MyAuthenticationFaiurelHandle myAuthenticationFaiurelHandle) {
        this.myAuthenticationFaiurelHandle = myAuthenticationFaiurelHandle;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public MyAuthenticationSuccessHandle getMyAnthenticationSuccessHandle() {
        return myAuthenticationSuccessHandle;
    }

    public void setMyAnthenticationSuccessHandle(MyAuthenticationSuccessHandle myAnthenticationSuccessHandle) {
        this.myAuthenticationSuccessHandle = myAnthenticationSuccessHandle;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }



    public RedisService getRedisService() {
        return redisService;
    }

    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }
}
