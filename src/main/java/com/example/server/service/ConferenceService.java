package com.example.server.service;

import com.example.server.entity.Conference;

import java.util.List;

/**
 * @ClassName ConferenceService
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.service
 * @Date 2019/3/6 9:15
 */
public interface ConferenceService {
    public  boolean createConference(Conference conference);
    public List<Conference> findAll();
    public  Conference findOneByConferenceId(String conferenceId);

}
