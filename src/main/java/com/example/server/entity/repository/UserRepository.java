package com.example.server.entity.repository;

import com.example.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @ClassName UserRepository
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.entity
 * @Date 2019/2/22 15:30
 */
public interface UserRepository extends JpaRepository<User,String> {
    /**
     *@Author Jerry.Liu
     *@Description://TODO
     *@Parameter [name]
     *@Date:16:58 2018/9/5
     *@Package: com.example.remote.user.entity
     */
    public User findOneByName(String name);
    public User findOneByPhone(String phone);
    public List<User> findAllByRoleIs(String role);
    public boolean existsByName(String name);

    public  boolean existsByPhone(String phone);
    public boolean deleteUserByName(String name);





}
