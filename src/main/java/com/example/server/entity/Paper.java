package com.example.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Paper
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.entity
 * @Date 2019/3/3 19:08
 */
@Entity
public class Paper  implements Serializable {
    @Id
    private String paperId;

    private String paperTilte;

    private Date  paperSubmitTime;

    private  String  paperFileName;

    private  String conferenceId;

    private String userName;

    private String paperComment;

    public Paper(String paperTilte, String conferenceId, String userName) {
        this.paperTilte = paperTilte;
//        this.paperFileName = paperFileName;
        this.conferenceId = conferenceId;
        this.userName = userName;
    }

    public Paper() {
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperTilte() {
        return paperTilte;
    }

    public void setPaperTilte(String paperTilte) {
        this.paperTilte = paperTilte;
    }

    public Date getPaperSubmitTime() {
        return paperSubmitTime;
    }

    public void setPaperSubmitTime(Date paperSubmitTime) {
        this.paperSubmitTime = paperSubmitTime;
    }

    public String getPaperFileName() {
        return paperFileName;
    }

    public void setPaperFileName(String paperFileName) {
        this.paperFileName = paperFileName;
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPaperComment() {
        return paperComment;
    }

    public void setPaperComment(String paperComment) {
        this.paperComment = paperComment;
    }
}
