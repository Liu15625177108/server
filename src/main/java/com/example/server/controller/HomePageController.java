package com.example.server.controller;

import com.example.server.common.entity.ResultInfo;
import com.example.server.entity.User;
import com.example.server.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {

    @Autowired
    UserRepository userRepository;

//    @RequestMapping("/")
//    public String home(){
//        return "/index.html";
//    }

    @ResponseBody
    @GetMapping("/loginBuf")
    public ResultInfo loginBuf(Authentication authentication){
        User user = userRepository.findOneByName(authentication.getName());
        return new ResultInfo(HttpStatus.OK,"success",user);
    }
}
