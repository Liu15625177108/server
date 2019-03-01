package com.example.server.security.core.smslogin;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @ClassName SmsUserDetailService
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.security.core.smslogin
 * @Date 2019/2/28 15:56
 */
@Component("smsUserDetailService")
public class SmsUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new SmsUser(s,"jerry",AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}