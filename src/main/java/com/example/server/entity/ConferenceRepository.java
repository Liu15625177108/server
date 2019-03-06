package com.example.server.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference,String> {
    public Conference findOneById(String id);
}