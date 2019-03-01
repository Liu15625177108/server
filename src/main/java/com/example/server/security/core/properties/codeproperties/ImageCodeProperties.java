package com.example.server.security.core.properties.codeproperties;

/**
 * @ClassName ImageCodeProperties
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.properties.codeproperties
 * @Date 2018/9/19 21:11
 */
public class ImageCodeProperties extends CodeProperties {
    private  int width= 67;
    private  int height= 23;

    public ImageCodeProperties() {
        super();
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
