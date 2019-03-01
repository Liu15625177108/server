package com.example.server.security.core.validatorcode.imagecode;


import com.example.server.security.core.properties.SecurityProperties;
import com.example.server.security.core.validatorcode.basecode.CodeGenerator;
import com.example.server.security.core.validatorcode.basecode.ValidatorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @ClassName ImageCodeGenerator
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.core.validatorcode.imagecode
 * @Date 2018/9/19 21:08
 */
public class ImageCodeGenerator implements CodeGenerator {
    /** 注入配置类*/
    @Autowired
    private SecurityProperties securityProperties;

    /**
     *@Author Jerry.Liu
     *@Description://TODO
     *@Parameter [httpServletRequest]
     *@Date:21:46 2018/9/9
     *@Package: com.example.remote.security.core.properties.code.imagecode
     */
    @Override
    public ValidatorCode createCode(ServletWebRequest httpServletRequest) {
        //        int width=securityProperties.getValidatorProperties().getImageCodeproperties().getWidth();
        /**从请求中找，若找不到，则用默认配置值*/
        int width=ServletRequestUtils.getIntParameter(httpServletRequest.getRequest(),"width",securityProperties.getValidatorProperties().getImageCodeProperties().getWidth());
//        int height=securityProperties.getValidatorProperties().getImageCodeproperties().getHeight();
        int height=ServletRequestUtils.getIntParameter(httpServletRequest.getRequest(),"height",securityProperties.getValidatorProperties().getImageCodeProperties().getHeight());
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics graphics=image.getGraphics();
        Random random=new Random();
        graphics.setColor(getRandColor(200,250));
        graphics.fillRect(0,0,width,height);
        graphics.setFont(new Font("Times New Roman",Font.ITALIC,20));
        graphics.setColor(getRandColor(160,200));

        /** 往验证码框中加入障碍条纹*/
        for (int i=0;i<155;i++){
            int x=random.nextInt(width);
            int y=random.nextInt(height);
            int x1=random.nextInt(12);
            int y1=random.nextInt(12);
            graphics.drawLine(x,y,x+x1,y+y1);
        }
        String randomCode="";

        for (int i = 0; i<securityProperties.getValidatorProperties().getImageCodeProperties().getLength(); i++){
            String rand=String.valueOf(random.nextInt(10));
            randomCode+=rand;
            graphics.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            graphics.drawString(rand,13*i+6,16);
        }
        graphics.dispose();
        return new ImageCode(image,randomCode,securityProperties.getValidatorProperties().getImageCodeProperties().getExpiretime());
    }

    /**
     *@Author Jerry.Liu
     *@Description://TODO
     *@Parameter
     *@Date:16:18 2018/9/9
     *@Package: com.example.remote.security.core.properties.code.imagecode
     */
    public static Color getRandColor(int min, int max) {
        Random r=new Random();
        if (min > 255){
            min = 255;
        }
        if (max > 255) {
            max = 255;
        }
        int red = r.nextInt(max - min) + min;
        int green = r.nextInt(max - min) + min;
        int blue = r.nextInt(max - min) + min;
        return new Color(red, green, blue);
    }
}
