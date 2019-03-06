package com.example.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

/**
 * @ClassName Conference
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.entity
 * @Date 2019/2/24 14:54
 */
@Entity
public class Conference implements Serializable {
    @Id
    private String id;

    private String name;

    @ManyToMany(mappedBy = "conferenceSet")
    private Set<User> users;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Conference(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Conference() {
    }
}
