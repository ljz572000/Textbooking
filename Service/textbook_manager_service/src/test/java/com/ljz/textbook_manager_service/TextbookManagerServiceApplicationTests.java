package com.ljz.textbook_manager_service;

import com.ljz.textbook_manager_service.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextbookManagerServiceApplicationTests {
    @Autowired
    UserRepo userRepository;
    @Test
    public void insertNewUser() {
        List<String> users = new ArrayList<>();
        Boolean isAdmin = false;
        users.add("李金洲");
        users.add("李玮光");
        users.add("韩青杨");
        users.add("吴仁珑");
        users.add("杨壹麟");
        users.add("杨永旭");
        Integer userId = 20160750;
        for (String name:
                users) {
            sendData(name,isAdmin,""+userId);
            userId++;
        }
        users.clear();
        isAdmin = true;
        userId = 20190001;
        users.add("李老师");
        users.add("王老师");
        users.add("张老师");
        users.add("韩老师");
        users.add("杨老师");
        users.add("谢老师");

        for (String name:
                users) {
            sendData(name,isAdmin,""+userId);
            userId++;
        }
    }

    private void sendData(String username,Boolean isAdmin,String userId){
        userRepository.insertNewUser(
                userId,isAdmin,BCrypt.hashpw("1234", BCrypt.gensalt()),"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572716673727&di=b245301eaef365c8623ce611c26b44b0&imgtype=0&src=http%3A%2F%2Fwww.itmop.com%2Fupload%2F2017-9%2F15046867122689390.jpeg",username
        );
    }

}
