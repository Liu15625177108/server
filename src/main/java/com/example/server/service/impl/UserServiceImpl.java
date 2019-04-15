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

import java.util.List;
import java.util.Set;

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

    /**
    *@Author Jerry.Liu
    *@Description://用户注册
    *@Parameter
    *@Date:9:57 2019/3/7
    *@Package: com.example.server.service.impl
    */
//    @Override
//    public boolean signup( User user) {
//        if(userRepository.findOneByName(user.getName())==null) {
//            user.setId(idCreator.createId());
//            user.setRole("user");
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userRepository.save(user);
//            return true;
//        }
//        else  {
//            return false;
//        }
//    }


    @Override
    public boolean signup(User user) {
            user.setId(idCreator.createId());
            user.setRole("user");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
    }

    /**
     *@Author Jerry.Liu
     *@Description://查找一个用户信息，并且返回
     *@Date:23:53 2019/2/13
     *@Package: com.example.conferencedemo.user.service.impl
     */
    @Override
    public User showSimple(String userName) {
        return userRepository.findOneByName(userName);
    }

    /**
    *@Author Jerry.Liu
    *@Description://用户报名参加会议，往关联表插入一条记录。
     **@Parameter
    *@Date:9:58 2019/3/7
    *@Package: com.example.server.service.impl
    */
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


    @Override
    public boolean quitConference(String userName, String conferenceId) {
        if(userRepository.existsByName(userName)&&conferenceRepository.existsById(conferenceId)){
            if (attendOrNot(userName,conferenceId)) {
                User user = userRepository.findOneByName(userName);
                Conference conference = conferenceRepository.findOneById(conferenceId);
//            user.getConferenceSet().add(conference);
                user.getConferenceSet().remove(conference);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    /**
    *@Author Jerry.Liu
    *@Description://输出用户创建的会议
    *@Parameter
    *@Date:10:00 2019/3/7
    *@Package: com.example.server.service.impl
    */
    @Override
    public List<Conference> showMyCreateConference(String userName) {
        return conferenceRepository.findAllByCreateName(userName);
    }

    /**
    *@Author Jerry.Liu
    *@Description://输入userName,输出该user参加的会议信息。
    *@Parameter
    *@Date:10:00 2019/3/7
    *@Package: com.example.server.service.impl
    */
    @Override
    public Set<Conference> showMyAttendConference(String userName) {
        User user =userRepository.findOneByName(userName);
        return  user.getConferenceSet();
    }

    /**
    *@Author Jerry.Liu
    *@Description://是否参加此会议，参加放回true；
    *@Parameter
    *@Date:13:53 2019/3/7
    *@Package: com.example.server.service.impl
    */
    @Override
    public boolean attendOrNot(String userName, String conferenceId) {
        User user =userRepository.findOneByName(userName);
        for(Conference conference:user.getConferenceSet()) {
            if (conference.getId().equals(conferenceId)) {
                return true;
            }
        }
        return  false;

    }

    /**
    *@Author Jerry.Liu
    *@Description://输入用户username，以及会议id 返回是否是该用户创建的会议情况，是返回true；
  *@Parameter
    *@Date:17:52 2019/3/7
    *@Package: com.example.server.service.impl
    */
    @Override
    public boolean createOrNot(String userName, String conferenceId) {
        Conference conference=conferenceRepository.findOneById(conferenceId);
        if (conference.getCreateName().equals(userName)){
            return  true;
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean modifyPassword(String userName, String oldPassword, String newPassword) {
        User user = userRepository.findOneByName(userName);
        if(passwordEncoder.matches(oldPassword,user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(String phone, String newPassword) {
        User user = userRepository.findOneByPhone(phone);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean checkPhoneAndSmscode(String phone) {
        User user = userRepository.findOneByPhone(phone);
//        System.out.println(user.getName());
        if(user !=null){
//            System.out.println(user.getName());
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllByRoleIs("user");
    }

    @Override
    public boolean deleteUser(String username) {
        User user = userRepository.findOneByName(username);
        try {
            userRepository.delete(user);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}

