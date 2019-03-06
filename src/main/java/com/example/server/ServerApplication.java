package com.example.server;

import com.example.server.entity.Conference;
import com.example.server.entity.ConferenceRepository;
import com.example.server.entity.User;
import com.example.server.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.Set;

@SpringBootApplication
@ServletComponentScan
@EnableCaching
@RestController
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    ConferenceRepository conferenceRepository;

    @GetMapping("/users")
    public String user(){
        User user;
        user = userRepository.findOneByUserName("tom");
        Set<Conference> conferenceSet=user.getConferences();
        Conference conference=conferenceRepository.findOneById("0001");
        Set<User>users=conference.getUsers();
//        System.out.println(conferenceSet);

        for(Conference value:conferenceSet){
            System.out.println(value.getName()+"eeeee");
        }
        for(User value:users){
            System.out.println(value.getUserName()+"hhhh");
        }
        return "hello";
    }

}
