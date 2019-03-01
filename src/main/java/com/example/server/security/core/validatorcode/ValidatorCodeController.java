package com.example.server.security.core.validatorcode;


import com.example.server.common.redis.RedisService;
import com.example.server.security.core.validatorcode.basecode.CodeGenerator;
import com.example.server.security.core.validatorcode.basecode.ValidatorCode;
import com.example.server.security.core.validatorcode.smscode.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * @ClassName ValidatorCodeController
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.remote.security.core.properties.code.imagecode
 * @Date 2018/9/9 14:53
 */
@RestController
public class ValidatorCodeController {

    /** 注入配置类*/
//    @Autowired
//    @Qualifier("imageCodeGenerator")
//    private CodeGenerator imageCodeGenerator;
//
//    @Autowired
//    @Qualifier("smsCodeGenerator")
//    private CodeGenerator smsCodeGenerator;

    @Autowired
    private Map<String,CodeGenerator> map;

    @Autowired
    private SmsCodeSender mydefaultSmsSender;

    @Autowired
    private RedisService redisService;

    /**获取session*/
//    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    private  static  final String SESSION_IMAGE_KEY="SESSION_KEY_IMAGE_CODE";

    private  static  final String SESSION_SMS_KEY="SESSION_KEY_SMS_CODE";
    private  static  final String SESSION_KEY="SESSION_KEY_CODE";

//    @GetMapping("/code/image")
//    public  void createCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
//       ImageCode imageCode = (ImageCode) imageCodeGenerator.createCode(new ServletWebRequest(httpServletRequest));
//        /** 往sesseion里面加CodeImage*/
//        sessionStrategy.setAttribute(new ServletWebRequest(httpServletRequest),SESSION_IMAGE_KEY, imageCode);
//        /** 输入验证码图片*/
//        ImageIO.write(imageCode.getBufferedImage(),"JPEG",httpServletResponse.getOutputStream());
//    }
//
//    @GetMapping("/code/sms")
//    public  void createSmsCode(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws ServletRequestBindingException {
//        SmsCode smsCode = (SmsCode) smsCodeGenerator.createCode(new ServletWebRequest(httpServletRequest));
//        sessionStrategy.setAttribute(new ServletWebRequest(httpServletRequest),SESSION_SMS_KEY,smsCode);
//        String phone=ServletRequestUtils.getRequiredStringParameter(httpServletRequest,"phone");
//        mydefaultSmsSender.sendCode(phone,smsCode);
//    }

    @GetMapping("/code/{type}")
    public  void createCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable String type) throws IOException, ServletRequestBindingException {
        CodeGenerator codeGenerator = map.get(type+"CodeGenerator");
        ValidatorCode validatorCode = codeGenerator.createCode(new ServletWebRequest(httpServletRequest));
        if(type.equals("sms")){
//            sessionStrategy.setAttribute(new ServletWebRequest(httpServletRequest),SESSION_SMS_KEY, validatorCode);
            redisService.setKey("123456",validatorCode.getCode());
            String phone=ServletRequestUtils.getRequiredStringParameter(httpServletRequest,"phone");
            mydefaultSmsSender.sendCode(phone,validatorCode);
        }
        if(type.equals("image")){
            ValidatorCode code =new ValidatorCode();
            code.setCode(validatorCode.getCode());
            code.setExpireTime(validatorCode.getExpireTime());
//            sessionStrategy.setAttribute(new ServletWebRequest(httpServletRequest),SESSION_IMAGE_KEY, code);
            ImageIO.write(validatorCode.showImage(),"JPEG",httpServletResponse.getOutputStream());
        }

    }



    /**getter setter*/
//    public SessionStrategy getSessionStrategy() {
//        return sessionStrategy;
//    }
//
//    public void setSessionStrategy(SessionStrategy sessionStrategy) {
//        this.sessionStrategy = sessionStrategy;
//    }

    public static String getSessionImageKey() {
        return SESSION_IMAGE_KEY;
    }

    public static String getSessionSmsKey() {
        return SESSION_SMS_KEY;
    }

    public static String getSessionKey() {
        return SESSION_KEY;
    }
}
