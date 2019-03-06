package com.example.server.service;

import com.example.server.entity.User;

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
}
