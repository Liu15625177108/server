package com.example.server.security.core.smslogin;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SmsAuthenticationFilter
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.security.core.smslogin
 * @Date 2019/2/28 15:53
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SPRING_SMS_KEY = "phone";
    private String PhoneParameter = "phone";

    private boolean postOnly = true;

    public SmsAuthenticationFilter() {
            super(new AntPathRequestMatcher("/authentication/smsform", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String phone = this.obtainPhone(request);

            if (phone == null) {
                phone= "";
            }

            phone=phone.trim();
            SmsAuthenticationToken authRequest=new SmsAuthenticationToken(phone);
            this.setDetails(request, authRequest);
            AuthenticationManager authenticationManager=this.getAuthenticationManager();
            Authentication authentication= authenticationManager.authenticate(authRequest);
            return authentication;
        }
    }

    protected String obtainPhone(HttpServletRequest request) {
        return request.getParameter(this.PhoneParameter);
    }



    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPhoneParameter(String phoneParameter) {
        Assert.hasText(PhoneParameter, "phone parameter must not be empty or null");
        this.PhoneParameter=phoneParameter;
    }



    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }


}