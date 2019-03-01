package com.example.server.security.core.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SecurityPropertiesConfig
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.properties
 * @Date 2018/9/19 15:17
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityPropertiesConfig {
}

