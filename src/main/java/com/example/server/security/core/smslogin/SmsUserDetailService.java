package com.example.server.security.core.smslogin;

import com.example.server.entity.repository.UserRepository;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
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
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return new SmsUser(s,"jerry",AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        if (userRepository.existsByPhone(s)) {
            com.example.server.entity.User user = userRepository.findOneByPhone(s);
            if(user.getRole().equals("user")) {
                return new User(user.getName(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
            }else {
                return new  User(user.getName(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
            }
        }
        else {
            throw  new UsernameNotFoundException("该手机号码暂未注册");
        }
    }
}