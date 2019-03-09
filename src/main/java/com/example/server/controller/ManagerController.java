package com.example.server.controller;

import com.example.server.entity.Conference;
import com.example.server.entity.User;
import com.example.server.service.ConferenceService;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ManagerController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.controller
 * @Date 2019/3/7 20:10
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private ConferenceService conferenceService;

    @GetMapping("/showusers")
    public List<User> showall(){
        return  userService.findAll();
    }

    @GetMapping("/showconferences")
    public List<Conference> showConference(){
        return conferenceService.findAll();
    }
}
