package com.example.server.security;

import com.example.server.entity.Conference;
import com.example.server.entity.User;
import com.example.server.entity.repository.ConferenceRepository;
import com.example.server.entity.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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
    public Object aboutMe(Authentication authentication, HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
//        String header=httpServletRequest.getHeader("Authorization");
//        String token = StringUtils.substringAfter(header,"bearer ");
//        Claims claims= Jwts.parser().setSigningKey("liuyuxin".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        return authentication;
//        return  claims;
    }

}

