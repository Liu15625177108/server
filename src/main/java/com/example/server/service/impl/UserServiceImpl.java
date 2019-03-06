package com.example.server.service.impl;

import com.example.server.common.uuid.IdCreator;
import com.example.server.entity.Conference;
import com.example.server.entity.User;
import com.example.server.entity.repository.ConferenceRepository;
import com.example.server.entity.repository.UserRepository;
import com.example.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.service.impl
 * @Date 2019/2/22 17:36
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConferenceRepository conferenceRepository;
    @Autowired
    private IdCreator idCreator;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean signup( User user) {
        if(userRepository.findOneByName(user.getName())==null) {
//            user.setUserid(idCreator.createId());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
        else  {
            return false;
        }
    }

    /**
     *@Author Jerry.Liu
     *@Description://TODO
     *@Parameter
     *@Date:23:53 2019/2/13
     *@Package: com.example.conferencedemo.user.service.impl
     */
    @Override
    public User showSimple(String userName) {
        return userRepository.findOneByName(userName);
    }

    @Override
    public boolean attendConference(String userName,String conferenceId) {
        if(userRepository.existsByName(userName)&&conferenceRepository.existsById(conferenceId)){
            User user=userRepository.findOneByName(userName);
            Conference conference =conferenceRepository.findOneById(conferenceId);
            user.getConferenceSet().add(conference);
            userRepository.save(user);
            return  true;
        }
        return false;
    }
}

