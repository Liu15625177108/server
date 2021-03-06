package com.example.server.entity.repository;

import com.example.server.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.data.redis.connection.ReactiveListCommands;

import java.util.List;

/**
 * @ClassName ConferenceRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.entity.repository
 * @Date 2019/3/3 19:46
 */
public interface ConferenceRepository extends JpaRepository<Conference,String> {
//    public boolean existsById(String id);

    public Conference findOneById(String id);

    public List<Conference> findAllByCreateName(String createName);

    @Transactional
    public void deleteConferenceById(String id);

}
