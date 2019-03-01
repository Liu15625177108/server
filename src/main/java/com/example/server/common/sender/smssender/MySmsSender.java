package com.example.server.common.sender.smssender;



import com.example.server.security.core.validatorcode.basecode.ValidatorCode;
import com.example.server.security.core.validatorcode.smscode.SmsCodeSender;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

/**
 * @ClassName MySmsSender
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.remote.common.sender
 * @Date 2018/9/13 9:25
 */
//@Component("mydefaultSmsSender")
public class MySmsSender implements SmsCodeSender {

    private static String operation = "/industrySMS/sendSMS";
    private static String accountSid = Config.ACCOUNT_SID;
    private  String smsContent = "【学术小牧】尊敬的用户，您的验证码为";
    @Override
    public void sendCode(String phone, ValidatorCode code) {



        String to=phone;
        String tmpSmsContent = null;
        try{
            smsContent=smsContent+code.getCode();
            tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
        }catch(Exception e){

        }
        String url = Config.BASE_URL + operation;
        String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
                + HttpUtil.createCommonParam();

        // 提交请求
        String result = HttpUtil.post(url, body);
        System.out.println("result:" + System.lineSeparator() + result);

    }

}
