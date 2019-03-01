package com.example.server.security.core.properties.codeproperties;

/**
 * @ClassName CodeProperties
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.properties.codeproperties
 * @Date 2018/9/19 21:10
 */
public class CodeProperties {

    private int length = 6 ;
    private int  expiretime = 300;

    private String url = null;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(int expiretime) {
        this.expiretime = expiretime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}