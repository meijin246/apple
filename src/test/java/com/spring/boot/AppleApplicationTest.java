package com.spring.boot;

import com.spring.boot.dao.UserDao;
import com.spring.boot.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest
@Configuration
@EnableTransactionManagement
public class AppleApplicationTest {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    private TestService service;


    @Test
    public void datasource() throws InterruptedException {
//        userDao.insertData("liuyang","beijing");
        service.sendMessage("kate");
//        Thread.sleep(2000);
//        service.receiveMessage();
    }
}

