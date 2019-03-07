package com.example.server.service.impl;

import com.example.server.common.uuid.IdCreator;
import com.example.server.entity.Paper;
import com.example.server.entity.repository.PaperRepository;
import com.example.server.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PaperServiceImpl
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.service.impl
 * @Date 2019/3/7 10:58
 */
@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private IdCreator idCreator;
    @Override
    public boolean create(Paper paper) {
            paper.setPaperId(idCreator.createId());
            paper.setPaperSubmitTime(new Date());
            paperRepository.save(paper);
            return  true;
    }

    @Override
    public Paper findOneByPaperId(String paperId) {
        return  paperRepository.findOneByPaperId(paperId);
    }


    @Override
    public List<Paper> findAllByUserName(String userName) {
        return paperRepository.findAllByUserName(userName);
    }

    @Override
    public List<Paper> findAllByConferenceId(String conferenceId) {
        return  paperRepository.findAllByConferenceId(conferenceId);
    }
}