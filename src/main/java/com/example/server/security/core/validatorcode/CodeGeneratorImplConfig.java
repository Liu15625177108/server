package com.example.server.security.core.validatorcode;

import com.example.server.security.core.validatorcode.basecode.CodeGenerator;
import com.example.server.security.core.validatorcode.imagecode.ImageCodeGenerator;
import com.example.server.security.core.validatorcode.smscode.MydefaultSmsSender;
import com.example.server.security.core.validatorcode.smscode.SmsCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName CodeImageGeneratorImplConfig
 * @Author:Jerry.Liu;
 * @Description:配置类 配置imagecode smscode的生成类以及smscode的发送。
 * @Package com.example.remote.security.core.properties.code.imagecode
 * @Date 2018/9/9 21:54
 */
@Configuration
public class CodeGeneratorImplConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public CodeGenerator imageCodeGenerator(){
        return  new ImageCodeGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    public CodeGenerator smsCodeGenerator(){
        return  new SmsCodeGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(name = "mydefaultSmsSender")
    public MydefaultSmsSender mydefaultSmsSender(){
        return  new MydefaultSmsSender();
    }
}
