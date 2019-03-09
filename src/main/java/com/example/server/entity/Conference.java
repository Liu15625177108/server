package com.example.server.entity;


import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
    public interface  simpleView{
    }
    public interface defaultView extends  simpleView{
    }


    @javax.persistence.Id
//    @GeneratedValue(generator = "idGenerator")
//    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    private String name;

    private String address;

    private String description;

    private Date  date;

//    @JsonBackReference
    @JsonIgnoreProperties("conferenceSet")
   @ManyToMany(mappedBy = "conferenceSet")
    private Set<User>  userSet;

    /**会议创办者姓名*/
    private  String  createName;

    /**会议联系人信息*/
    private String  contactName;

    private  String contactEmail;

    private String contactPhone;


    public Conference(String name, String address, String description, Date date, String userName,
                      String contactName, String contactEmail, String contactPhone) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.date = date;
        this.createName = userName;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public Conference() {
    }
    @JsonView(simpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(simpleView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(simpleView.class)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonView(simpleView.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }



    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }


}
