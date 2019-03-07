package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName User
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.entity
 * @Date 2019/2/22 15:03
 */
@Entity
public class User  implements Serializable {
    public interface simpleValue {
    }

    ;

    public interface detailValue extends simpleValue {
    }

    ;


    private String id;
    @Id
    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private String hone;

    @Email
    private String email;

    @ManyToMany
    @JsonIgnoreProperties("userSet")
    private Set<Conference> conferenceSet;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHone() {
        return hone;
    }

    public void setHone(String hone) {
        this.hone = hone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Conference> getConferenceSet() {
        return conferenceSet;
    }

    public void setConferenceSet(Set<Conference> conferenceSet) {
        this.conferenceSet = conferenceSet;
    }

    public User(@NotBlank String name, @NotBlank String password, String hone, @Email String email) {
        this.name = name;
        this.password = password;
        this.hone = hone;
        this.email = email;
    }

    public User() {
    }


}