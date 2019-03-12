package com.example.server.entity.repository;

import com.example.server.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName PaperRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.entity.repository
 * @Date 2019/3/3 19:30
 */
public interface PaperRepository extends JpaRepository<Paper,String> {
    public Paper findOneByPaperId(String paperId);
    public boolean existsByConferenceIdAndUserName(String conferenceId,String userName);
    public List<Paper> findAllByUserName(String userName);
    public List<Paper> findAllByConferenceId(String conferenceId);
    public Paper findOneByConferenceIdAndUserName(String conferenceId,String userName);
 }
