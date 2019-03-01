package com.example.server.security.core.validatorcode.imagecode;



import com.example.server.security.core.validatorcode.basecode.ValidatorCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @ClassName ImageCode
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.core.validatorcode.imagecode
 * @Date 2018/9/19 21:06
 */
public class ImageCode extends ValidatorCode {


    /** 验证框的图片*/
    private BufferedImage bufferedImage;




    /** 构造函数*/
    public ImageCode(){}
    public ImageCode(BufferedImage bufferedImage, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.bufferedImage = bufferedImage;
    }
    /** 传入的过期时间是秒数*/
    public ImageCode(BufferedImage bufferedImage, String code, int expireTime) {
        super(code,expireTime);
        this.bufferedImage = bufferedImage;

//        this.code = code;
//        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }


    /** getter setter函数*/
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    @Override
    public BufferedImage showImage(){
        return bufferedImage;

    }

}