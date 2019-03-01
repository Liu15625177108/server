package com.example.server.security;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Controller
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server
 * @Date 2019/2/21 23:02
 */
@RestController
public class Controller {
    @GetMapping("/hello")
    public Map sayHello(){
        Map map =new HashMap<String ,String>();
        map.put("aa","1234");
        return map;
    }

    @GetMapping("/me")
    public Object aboutMe(Authentication authentication) {
        return authentication;
    }

}

