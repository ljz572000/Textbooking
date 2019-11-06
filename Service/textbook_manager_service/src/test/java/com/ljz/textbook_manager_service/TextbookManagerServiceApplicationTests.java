package com.ljz.textbook_manager_service;

import com.ljz.textbook_manager_service.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextbookManagerServiceApplicationTests {
    @Autowired
    UserRepo userRepository;
    @Test
    public void changeData(){
//        userRepository.changeData(5,"上海电力学院","计算机科学与技术","2373861592@qq.com","1997-05-15 00:00:00");
    }
//    @Test
//    public void sendMail(){
//        String from = "ljz572000@foxmail.com";
//        String to = "2373861592@qq.com";
//        String subject = "修改密码";
//        String content = "您的密码已修改为xxxx";
//        Properties properties = new Properties();
//        properties.put("mail.transport.protocol", "smtp");// 连接协议
//        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
//        properties.put("mail.smtp.port", 465);// 端口号
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
//        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
//        // 得到回话对象
//        Session session = Session.getInstance(properties);
//        // 获取邮件对象
//        Message message = new MimeMessage(session);
//        try {
//            // 设置发件人邮箱地址
//            message.setFrom(new InternetAddress(from));
//            // 设置收件人邮箱地址
//            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(to)});
//            message.setSubject(subject);
//            // 设置邮件内容
//            message.setText(content);
//            // 得到邮差对象
//            Transport transport = session.getTransport();
//            // 连接自己的邮箱账户
//            transport.connect(from, "ktmjfbtmkhqkdhhg");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
//            // 发送邮件
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }



//    @Test
//    public void insertNewUser() {
//        List<String> users = new ArrayList<>();
//        Boolean isAdmin = false;
//        users.add("李金洲");
//        users.add("李玮光");
//        users.add("韩青杨");
//        users.add("吴仁珑");
//        users.add("杨壹麟");
//        users.add("杨永旭");
//        Integer userId = 20160750;
//        for (String name:
//                users) {
//            sendData(name,isAdmin,""+userId);
//            userId++;
//        }
//        users.clear();
//        isAdmin = true;
//        userId = 20190001;
//        users.add("李老师");
//        users.add("王老师");
//        users.add("张老师");
//        users.add("韩老师");
//        users.add("杨老师");
//        users.add("谢老师");
//        for (String name:
//                users) {
//            sendData(name,isAdmin,""+userId);
//            userId++;
//        }
//    }
//
//    private void sendData(String username,Boolean isAdmin,String userId){
//            userRepository.insertNewUser(
//                    userId,
//                    isAdmin,
//                    BCrypt.hashpw("1234", BCrypt.gensalt()),
//                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572716673727&di=b245301eaef365c8623ce611c26b44b0&imgtype=0&src=http%3A%2F%2Fwww.itmop.com%2Fupload%2F2017-9%2F15046867122689390.jpeg",
//                    username,
//                    1000.0,
//                    "CS",
//                    "上海电力大学一号学生公寓A609",
//                    "2373861592@qq.com", "1997-07-01 11:11:11",false
//            );
//
//    }
}
