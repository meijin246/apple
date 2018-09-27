package com.spring.boot;

import com.spring.boot.dao.UserDao;
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

    @Test
    public void datasource(){
        userDao.insertData("liuyang","beijing");
    }
}

