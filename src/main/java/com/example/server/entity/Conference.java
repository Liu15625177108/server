package com.example.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @ClassName Conference
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.entity
 * @Date 2019/2/24 14:54
 */
@Entity
public class Conference implements Serializable {
    @javax.persistence.Id
    private String Id;


}
