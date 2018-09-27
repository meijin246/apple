package com.spring.boot.dao.impl;

import com.spring.boot.dao.UserDao;
import com.spring.boot.dao.mapper.UserMapper;
import com.spring.boot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("userDao")
public class UserDaoImpl implements UserDao{
/*
   @Autowired
   @Qualifier("jdbcTemplate")
   private JdbcTemplate template;
*/
   @Autowired
   private UserMapper userMapper;

    public void insertData(String userName, String address) {
        User user = new User();
        user.setUsername(userName);
        user.setAddress(address);
        userMapper.insertData(user);
//            String sql = "insert into user(username,address) values(?,?)";
//            template.update(sql,new Object[]{userName,address});
    }
/*  */
    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

}
