package com.example.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

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
    private String conferenceId;

    private String conferenceName;

    private String conferenceAddress;

    private String conferenceDescription;

    private Date  conferenceDate;

    /**会议创办者Id*/
    private  String  userId;

    /**会议联系人信息*/
    private String  contactName;

    private  String contactEmail;

    private String contactPhone;

    public Conference(String conferenceId, String conferenceName, String conferenceAddress,
                      String conferenceDescription, Date conferenceDate, String userId, String contactName,
                      String contactEmail, String contactPhone) {
        this.conferenceId = conferenceId;
        this.conferenceName = conferenceName;
        this.conferenceAddress = conferenceAddress;
        this.conferenceDescription = conferenceDescription;
        this.conferenceDate = conferenceDate;
        this.userId = userId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public Conference(String conferenceId, String conferenceName, String conferenceAddress,
                      String conferenceDescription, Date conferenceDate, String contactName, String contactEmail, String contactPhone) {
        this.conferenceId = conferenceId;
        this.conferenceName = conferenceName;
        this.conferenceAddress = conferenceAddress;
        this.conferenceDescription = conferenceDescription;
        this.conferenceDate = conferenceDate;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public Conference() {
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getConferenceAddress() {
        return conferenceAddress;
    }

    public void setConferenceAddress(String conferenceAddress) {
        this.conferenceAddress = conferenceAddress;
    }

    public String getConferenceDescription() {
        return conferenceDescription;
    }

    public void setConferenceDescription(String conferenceDescription) {
        this.conferenceDescription = conferenceDescription;
    }

    public Date getConferenceDate() {
        return conferenceDate;
    }

    public void setConferenceDate(Date conferenceDate) {
        this.conferenceDate = conferenceDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
