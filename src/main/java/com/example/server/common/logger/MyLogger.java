package com.example.server.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyLogger
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.common.logger
 * @Date 2019/2/22 17:44
 */
@Component
public class MyLogger {
    private Logger logger=LoggerFactory.getLogger(this.getClass());

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}