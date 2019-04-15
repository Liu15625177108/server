package com.example.server.controller;

import com.example.server.common.entity.ResultInfo;
import com.example.server.entity.User;
import com.example.server.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

    @Autowired
    private UserRepository userRepository;
  @GetMapping("/loginBuf")
    public ResultInfo login(Authentication authentication){
      User user=userRepository.findOneByName(authentication.getName());
      return new ResultInfo(HttpStatus.OK,"success",user);

  }
}
