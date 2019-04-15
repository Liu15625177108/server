package com.example.server.common.validation;

import com.example.server.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
    private UserRepository userRepository;
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (userRepository.existsByPhone(value.toString())){
            return false;
        }
        return true;
    }
}
