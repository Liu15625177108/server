package com.example.server.entity.repository;

import com.example.server.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.connection.ReactiveListCommands;

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
}
