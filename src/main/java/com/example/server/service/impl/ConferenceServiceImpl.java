package com.example.server.service.impl;

import com.example.server.common.uuid.IdCreator;
import com.example.server.entity.Conference;
import com.example.server.entity.repository.ConferenceRepository;
import com.example.server.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ConferenceServiceImpl
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.service.impl
 * @Date 2019/3/6 9:18
 */
@Service
public class ConferenceServiceImpl implements ConferenceService {
    @Autowired
    private ConferenceRepository conferenceRepository;
    @Autowired
    private IdCreator idCreator;
    @Override
    public boolean createConference(Conference conference) {

            conference.setId(idCreator.createId());
            conferenceRepository.save(conference);
            return  true;

    }

    @Override
    public List<Conference> findAll() {
            return conferenceRepository.findAll();
    }

    @Override
    public Conference findOneByConferenceId(String conferenceId) {
        return conferenceRepository.findOneById(conferenceId);
    }
}
