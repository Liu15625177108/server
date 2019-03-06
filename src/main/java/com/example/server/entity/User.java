package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
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

    @Id
    private String userId;

    @NotBlank
    private String userName;

    private String password;

    private String userPhone;

    @Email
    private String UserEmail;

    private String UserAddress;

    @ManyToMany
    private Set<Conference> conferenceSet;

    public User(@NotBlank String username, String password, String userPhone, @Email String userEmail, String userAddress) {
        userName = username;
        this.password = password;
        this.userPhone = userPhone;
        UserEmail = userEmail;
        UserAddress = userAddress;
    }

    public User() {
    }

    @JsonView(detailValue.class)
    public String getUserid() {
        return userId;
    }

    public void setUserid(String userid) {
        userId = userid;
    }

    @JsonView(simpleValue.class)
    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        userName = username;
    }

    @JsonView(detailValue.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(detailValue.class)
    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    @JsonView(detailValue.class)
    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

    @JsonView(simpleValue.class)
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Conference> getConferences() {
        return conferenceSet;
    }

    public void setConferences(Set<Conference> conferenceSet) {
        this.conferenceSet = conferenceSet;
    }
}