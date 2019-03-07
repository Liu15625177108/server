package com.example.server.service;

import com.example.server.entity.Conference;
import com.example.server.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @ClassName UserService
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.service
 * @Date 2019/2/22 17:36
 */
public interface UserService {
    public boolean signup(User user);
    public User  showSimple(String userName);
    public boolean attendConference(String userName,String conferenceId);
    public List<Conference> showMyCreateConference(String userName);
    public Set<Conference> showMyAttendConference(String userName);
    public  boolean attendOrNot(String userName,String conferenceId);
    public  boolean createOrNot(String userName,String conferenceId);
    public  List<User> findAll();
}
