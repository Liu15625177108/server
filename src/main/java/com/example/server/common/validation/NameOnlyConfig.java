package com.example.server.common.validation;

import com.example.server.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName NameOnlyConfig
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.common.validation
 * @Date 2019/4/16 0:47
 */
public class NameOnlyConfig  implements ConstraintValidator<NameOnly,Object> {
    @Autowired
    @Qualifier("userRepository")
    UserRepository userRepository;
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (userRepository.existsByName(value.toString())) {
            return false;
        }
        else {
            return  true;
        }
    }
}
