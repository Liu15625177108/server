package com.example.server.common.validation;

import com.example.server.entity.repository.UserRepository;
import com.example.server.service.UserService;
import com.example.server.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName PhoneOnlyConfig
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.common.validation
 * @Date 2019/4/16 0:15
 */
public class PhoneOnlyConfig implements ConstraintValidator<PhoneOnly,Object> {


    @Autowired
    @Qualifier("userRepository")
    UserRepository userRepository;
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println(userRepository);
        if (userRepository.existsByPhone(value.toString())){
            return  false;
        }
        else {
            return  true;
        }
    }
}
