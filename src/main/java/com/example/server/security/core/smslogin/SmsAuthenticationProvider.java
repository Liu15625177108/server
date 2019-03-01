package com.example.server.security.core.smslogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @ClassName SmsAuthenticationProvider
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.security.core.smslogin
 * @Date 2019/2/28 15:54
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {
//    private UserDetailsService userDetailsService;

    private  SmsUserDetailService smsUserDetailService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken=(SmsAuthenticationToken)authentication;
        String phone=(String)authentication.getPrincipal();
        SmsUser userDetails = (SmsUser) smsUserDetailService.loadUserByUsername(phone);
        if(userDetails==null){
            throw  new InternalAuthenticationServiceException("无法获取用户信息");
        }
        else{
            SmsAuthenticationToken authenticationResult= new SmsAuthenticationToken(userDetails,userDetails.getAuthorities());
            authenticationResult.setDetails(smsAuthenticationToken.getDetails());
            return  authenticationResult;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public SmsUserDetailService getSmsUserDetailService() {
        return smsUserDetailService;
    }

    public void setSmsUserDetailService(SmsUserDetailService smsUserDetailService) {
        this.smsUserDetailService = smsUserDetailService;
    }
}
