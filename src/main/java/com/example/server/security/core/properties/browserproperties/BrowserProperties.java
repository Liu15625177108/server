package com.example.server.security.core.properties.browserproperties;

/**
 * @ClassName BrowserProperties
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.properties
 * @Date 2018/9/19 15:12
 */
public class BrowserProperties {
    private String loginPage="/login.html";

    private LoginType loginType=LoginType.JSON;

    private int rememberMeSecond = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSecond() {
        return rememberMeSecond;
    }

    public void setRememberMeSecond(int rememberMeSecond) {
        this.rememberMeSecond = rememberMeSecond;
    }
}
