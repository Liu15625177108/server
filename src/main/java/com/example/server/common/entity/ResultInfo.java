package com.example.server.common.entity;

import org.springframework.http.HttpStatus;

/**
 * @ClassName ResultInfo
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.server.common.entity
 * @Date 2019/2/22 17:42
 */
public class ResultInfo {
    /**状态码*/
    private HttpStatus httpStatus;
    /**状态描述*/
    private String msg;
    /**接口数据*/
    private Object data;

    public ResultInfo(){}
    public ResultInfo(HttpStatus httpStatus, String msg, Object content) {
        this.httpStatus = httpStatus;
        this.msg = msg;
        this.data = content;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
