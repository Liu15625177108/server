package com.example.server;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
*@Author Jerry.Liu
*@Description://TODO
*@Parameter
*@Date:16:13 2019/2/27
*@Package: com.example.server
*/
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServerApplication.class);
    }

}