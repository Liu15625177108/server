package com.example.server.common.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =EmailOnlyConfig.class)
public @interface EmailOnly {
    String message() default "邮箱已被注册";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}