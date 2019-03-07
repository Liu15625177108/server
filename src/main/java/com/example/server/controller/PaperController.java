package com.example.server.controller;

import com.example.server.entity.Paper;
import com.example.server.service.PaperService;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName PaperController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.controller
 * @Date 2019/3/7 18:20
 */
@RestController
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private UserService userService;
    @GetMapping("/conference/{conferenceId}")
    public List<Paper> findAllByConferenceId(@PathVariable String conferenceId){
        return  null;
    }
}
