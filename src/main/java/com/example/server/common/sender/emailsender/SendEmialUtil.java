package com.example.server.common.sender.emailsender;






import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @ClassName SendEmialUtil
 * @Author:Jerry.Liu;
 * @Description:实现发送邮件功能，发送者默认为"2447152234@qq.com（个人qq邮箱）"
 * @Package com.example.remote.common.sender.emailsender
 * @Date 2018/9/13 9:45
 */
@Service
public class SendEmialUtil {
    public boolean sendEmail(String Title,String Text,String toEmail){
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", "true");//设置访问smtp服务器需要认证
            props.setProperty("mail.transport.protocol", "smtp"); //设置访问服务器的协议

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true); //打开debug功能

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("2447152234@qq.com")); //设置发件人.
            msg.setText(Text); //设置邮件内容
            msg.setSubject(Title); //设置邮件主题
            Transport trans = session.getTransport();
            trans.connect("smtp.qq.com", 25, "2447152234", "rfapbcytoktaebaf"); //连接邮箱smtp服务器，25为默认端口

            trans.sendMessage(msg, new Address[]{new InternetAddress(toEmail)}); //发送邮件

            trans.close(); //关闭连接
        }catch (MessagingException e){
//            e.printStackTrace();
            return  false;
        }
        return  true;

    }

    /**
     *@Author Jerry.Liu
     *@Description://邮箱验证方法，若为真的则返回true，否则返回false。
     *@Date:22:31 2018/7/22
     *@Package: com.study.demo.test6.util
     */
    public static boolean checkEmaile(String emaile){
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(emaile);
        //进行正则匹配
        return m.matches();
    }
//    public static void main(String[] args) {
//        SendEmialUtil sendEmialUtil=new SendEmialUtil();
//        sendEmialUtil.sendEmail("test","hello","jerry.liu@m.scnu.edu.cn");
//    }
}
