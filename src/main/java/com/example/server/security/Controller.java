package com.example.server.security;

import com.example.server.entity.Conference;
import com.example.server.entity.User;
import com.example.server.entity.repository.ConferenceRepository;
import com.example.server.entity.repository.UserRepository;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName Controller
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server
 * @Date 2019/2/21 23:02
 */
@RestController
public class Controller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConferenceRepository conferenceRepository;


    @GetMapping("/hello")
    public String sayhello(){
        List<User> list=userRepository.findAll();
        List<Conference> conferences=conferenceRepository.findAll();

            for(Conference conference:conferences) {
//                user.getConferenceSet().add(conference);
                for (User user : conference.getUserSet()) {
                    System.out.println(user.getName());
                }
            }
//            userRepository.save(user);'






        return  "hello";
    }

    @GetMapping("/me")
    public Object aboutMe(Authentication authentication) {
        return authentication;
    }

}

