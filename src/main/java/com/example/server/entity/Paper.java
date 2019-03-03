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


    public Paper(String paperId, String paperTilte, Date paperSubmitTime, String paperFileName, String conferenceId) {
        this.paperId = paperId;
        this.paperTilte = paperTilte;
        this.paperSubmitTime = paperSubmitTime;
        this.paperFileName = paperFileName;
        this.conferenceId = conferenceId;
    }

    public Paper(String paperId, String paperTilte, Date paperSubmitTime, String paperFileName) {
        this.paperId = paperId;
        this.paperTilte = paperTilte;
        this.paperSubmitTime = paperSubmitTime;
        this.paperFileName = paperFileName;
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
}
